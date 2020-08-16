/*
 * 文  件  名:  FtpUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2018年2月8日
 * 修改内容:  修改内容
 */
package jum.utility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * @author 李锦忠, 2018年2月8日
 * @since 产品/模块
 */
public class FtpUtils {
    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.FtpUtils.class);
    
    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";
    public static final String IP = "IP";
    public static final String PORT = "PORT";
    public static final String LCD = "LCD";
    public static final String RCD = "RCD";
    public static final String BAK = "BAK";
    public static final String OPERATE = "OPERATE";
    public static final String SAVE = "SAVE";
    public static final String FILE_FILTER = "FILE_FILTER";
    public static final String FIRST_TIME = "FIRST_TIME";
    public static final String ENDECODE = "ENDECODE";
    
    public static final String DOWNLOAD = "DOWNLOAD";
    public static final String UPLOAD = "UPLOAD";
    
    public static final String ALL = "ALL";
    public static final String ADDITION = "ADDITION";
    
    public static final String FILE_SPARATOR = "/";

    /**
     * 总的处理方法，上传下载可调用此方法
     * @param ftpMap
     * @return boolean
     */

    /**
     * 通过账号密码和地址连接ftp服务器
     * @param user
     * @param passwd
     * @param ip
     * @param port
     * @param enCode
     * @return
     */
    public static FTPClient connectFtp(String user, String passwd, String ip, int port, String enCode) {
        FTPClient ftp = new FTPClient();

        if(StringUtils.isBlank(enCode)) {
            enCode = "GBK";
        }
        
        try {
            int reply;
            // 连接FTP服务器
            ftp.connect(ip, Integer.valueOf(port));
            // 登录
            ftp.login(user, passwd);
            ftp.setControlEncoding(enCode);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
        } catch (IOException e) {
            log.error("IOException", e);
        }
        return ftp;
    }
    
    /** 
     * 功能简述:<br>
     * 详细描述:<br>
     * @author 李锦忠
     * @CreateTime 2018年8月13日下午5:25:19
     * @param ftpClient
     * @param file  本地文件
     * @param remotePath ftp目录
     * @param fileName  上传到ftp的文件名称
     * @return
     * @throws IOException
     */
    public static boolean upload(FTPClient ftpClient, File file, String remotePath, String fileName) throws IOException {
        boolean res = false;
        // 判断文件是否存在
        if (!file.exists()) {
            log.error("需要上传的文件【{}】不存在", file.getAbsolutePath());
            return res;
        }
        FileInputStream in = new FileInputStream(file);
        FTPFile[] ftpFiles = GetDirAndFilesInfo(remotePath, ftpClient);
        for (FTPFile ftpfile : ftpFiles) {
            String name = ftpfile.getName();
            if (name.equals(fileName)) {
                ftpClient.deleteFile(remotePath + File.separator + fileName);
            }
        }
        
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        String remoteFile = remotePath + FILE_SPARATOR + fileName;
        res = ftpClient.storeFile(remoteFile, in);
        in.close();
        res = true;
        // 记录日志
        log.info("把文件【{}】上传到地址【{}】----", file.getName(), remotePath);
        
        return res;
    }
    
    /**
     * 此方法描述的是： FTP文件下载
     * @param ftpClient
     * @param ftpfile
     * @param localPath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean download(FTPClient ftpClient, FTPFile ftpfile,
            String localPath, String fileName) throws IOException {
        boolean success = false;
        
        String filePath = localPath + com.ly.mp.common.utils.FtpUtils.FILE_SPARATOR + fileName;
        File f = new File(filePath);

        long lRemoteSize = ftpfile.getSize();
        // 本地存在文件，跳过下载
        if (f.exists()) {
            //f.delete();
            log.debug("文件{}存在，跳过下载", filePath);
            return true;
        }
        OutputStream out = new FileOutputStream(f);
 
        InputStream in = ftpClient.retrieveFileStream(ftpfile.getName());
        byte[] bytes = new byte[1024];
        long step = lRemoteSize / 100;
        if (step == 0) {
            step = 1;
        }

        long process = 0;
        long localSize = 0L;
        int c;
        try {
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
                localSize += c;
                long nowProcess = localSize / step;
                if (nowProcess > process) {
                    process = nowProcess;
                    if (process % 10 == 0) {
                        log.debug("fileName dowload percent: " + process + "%");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception", e);
        } finally {
            in.close();
            out.close();
        }

        boolean upNewStatus = ftpClient.completePendingCommand();

        if (upNewStatus) {
            success = true;
            log.info("{} downloaded",localPath + com.ly.mp.common.utils.FtpUtils.FILE_SPARATOR + fileName);
        } else {
            success = false;
        }

        return success;
    }
    
    public static boolean download(FTPClient ftpClient, String ftpFilePath, OutputStream outputStream) throws IOException {
        boolean success = false;
        int index = ftpFilePath.lastIndexOf("/");
        if (index == -1) {
            return false;
        }
        String ftpPath = ftpFilePath.substring(0, index);
        if (StringUtils.isNotBlank(ftpPath)) {
            ftpClient.changeWorkingDirectory(ftpPath);
        }
        ftpFilePath = ftpFilePath.substring(index+1, ftpFilePath.length());
        InputStream in = ftpClient.retrieveFileStream(ftpFilePath);
        byte[] bytes = new byte[1024];
        int c;
        try {
            while ((c = in.read(bytes)) != -1) {
                outputStream.write(bytes, 0, c);
            }
        } catch (Exception e) {
            log.error("Exception", e );
        } finally {
            in.close();
        }

        boolean upNewStatus = ftpClient.completePendingCommand();
        if (upNewStatus) {
            success = true;
        } else {
            success = false;
        }

        return success;
    }

    /**
     * 此方法描述的是： 文件过滤
     * @param type
     * @param suffix
     * @return
     */
    public static FilenameFilter filter(final String type, final String suffix) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String filename = new File(name).getName();
                Pattern p = Pattern.compile(type);
                if (StringUtils.isBlank(suffix)) {
                    return p.matcher(name).matches();
                } else {
                    return p.matcher(name).matches()
                            && filename.endsWith(suffix);
                }
            }
        };
    }

    /**
     * 此方法描述的是： FTP目录文件清单提取
     * @param currentDir
     * @param ftp
     * @return
     */
    private static FTPFile[] GetDirAndFilesInfo(String currentDir, FTPClient ftp) {
        FTPFile[] files = null;
        try {
            if (currentDir == null) {
                files = ftp.listFiles();
            } else {
                files = ftp.listFiles(currentDir);
            }
        } catch (IOException ex) {
            log.error("IOException", ex);
        }
        return files;
    }

    /**
     * 此方法描述的是： 文件归档
     * @param sourceFile
     * @param targetFile
     * @return
     * @throws IOException
     */
    public static boolean removeFile(File sourceFile, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
        return sourceFile.delete();
    }

    /**
     * 此方法描述的是： 通过NIO复制/合并文件
     * @param aSourceFile
     * @param aTargetFile
     * @param aAppend
     */
    public static void copyWithChannels(File aSourceFile, File aTargetFile,
            boolean aAppend) {
        // log("Copying files with channels.");
        // ensureTargetDirectoryExists(aTargetFile.getParentFile());
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            try {
                inStream = new FileInputStream(aSourceFile);
                inChannel = inStream.getChannel();
                outStream = new FileOutputStream(aTargetFile, aAppend);
                outChannel = outStream.getChannel();
                long bytesTransferred = 0;
                while (bytesTransferred < inChannel.size()) {
                    bytesTransferred += inChannel.transferTo(0, inChannel
                            .size(), outChannel);
                }
            } finally {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
                if (inStream != null)
                    inStream.close();
                if (outStream != null)
                    outStream.close();
            }
        } catch (FileNotFoundException ex) {
            log.error("FileNotFoundException", ex);
        } catch (IOException ex) {
            log.error("IOException", ex);
        }
    }

    /**
     * 此方法描述的是： 将文件转入到备份目录
     * @param aSourceFile
     * @param targetPath
     * @throws IOException
     */
    public static void bakWithStreams(File aSourceFile, String targetPath)
            throws IOException {
        String name = aSourceFile.getName();
        if (StringUtils.isNotBlank(targetPath)) {
            // 创建目录
            mkDir(targetPath);

            File targetFile = new File(targetPath + File.separator + name);
            copyWithStreams(aSourceFile, targetFile, false, true);
            deleteFile(aSourceFile);
        } else {
            // nothing to do
        }
    }

    /**
     * 此方法描述的是： 新建目錄
     * @param path
     */
    public static void mkDir(String path) {
        try {
        	File dir = new File(path);
        	if (dir.exists()) {
        		return;
        	}
            FileUtils.forceMkdir(dir);
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    /**
     * 此方法描述的是： 文件删除
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            boolean result = file.delete();
            if(!result){
                log.error("File delete error:" + file);
            }
        }
    }

    /**
     * 
     * 此方法描述的是： 流方式文件复制/合并
     * 
     * @author: ber
     * @version: Nov 7, 2012 10:03:13 AM
     */
    public static void copyWithStreams(File aSourceFile, File aTargetFile,
            boolean aAppend, boolean local) throws FileNotFoundException,IOException {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {

            byte[] bucket = new byte[32 * 1024];
            inStream = new BufferedInputStream(new FileInputStream(aSourceFile));
            outStream = new BufferedOutputStream(new FileOutputStream(
                    aTargetFile, aAppend));
            if (!local) {// 如果文件存在,则需要进行文件合并,写入换行符
                outStream.write(new byte[] { (byte) 0x0D0A });
            }

            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = inStream.read(bucket); // -1, 0, or more
                if (bytesRead > 0) {
                    outStream.write(bucket, 0, bytesRead);
                }
            }
            outStream.flush();
        } catch (FileNotFoundException ex) {
            log.error("FileNotFoundException", ex);
            throw ex;
        } catch (IOException ex) {
            log.error("FileNotFoundException", ex);
            throw ex;
        } finally {
            if (inStream != null)
                inStream.close();
            if (outStream != null)
                outStream.close();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean pat = Pattern.compile("051ecs_sale_.*2016[0-9]{4}.txt").matcher("051ecs_sale_modify_20160524.txt").matches();
        System.out.println("------------"+pat);
    }

}

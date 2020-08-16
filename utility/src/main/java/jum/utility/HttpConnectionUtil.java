package jum.utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;


/**
 * 功能简述:下载文件<br>
 * 详细描述:<br> 
 * 
 * @author 邓孔建, 2018年3月20日
 * @since 产品/模块
 */
public class HttpConnectionUtil {
	
	private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.HttpConnectionUtil.class);
    /**
     * @param rootPath 下载路径
     * @param downloadDir 下载存放目录
     * @return 返回下载文件
     * @throws IOException 
     */
	public static boolean downloadFile(String rootPath,String fileName, String downloadDir) throws IOException {
		File file = null;
        BufferedInputStream bin=null;
        OutputStream out=null;
        try {
            // 统一资源
            //URL url = new URL(urlPath);
        	URL url = new URL(rootPath+URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20"));
        	// 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                String filePathUrl =URLDecoder.decode(httpURLConnection.getURL().getPath(),"UTF-8") ;
                bin = new BufferedInputStream(httpURLConnection.getInputStream());
                String path = downloadDir+filePathUrl;
                log.warn("path=", path);
                File f = new File(path.substring(0, path.lastIndexOf("/")));
                if(!f.exists()){
                	f.mkdirs();
                }
                file = new File(path);
                if(file.exists()) {
                    log.warn("文件{}存在，跳过下载", path);
                    return true;
                }else{
                	boolean flag = file.createNewFile();
                	if(!flag){
                	    log.error("Create file error");
                    }
                }
                
                /*if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }*/
                out = new FileOutputStream(file);
                int size = 0;
                int len = 0;
                byte[] buf = new byte[1024];
                while ((size = bin.read(buf)) != -1) {
                    len += size;
                    out.write(buf, 0, size);
                }
                
            }else{
            	log.warn("文件服务器连接异常");
            	return true;
            }
            
        } catch (MalformedURLException e) {
            log.error("MalformedURLException:", e);
        } catch (IOException e) {
            log.error("IOException:", e);
        } finally {
        	if(bin!=null)
        		bin.close();
        	if(out!=null)
        		out.close();
        }
        return false;
    }
}



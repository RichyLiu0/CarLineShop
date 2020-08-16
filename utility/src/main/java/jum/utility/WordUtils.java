/*
 * 文  件  名:  WordUtils.java
 * 版        权:  Copyright     -     SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2018年9月15日
 * 修改内容:  修改内容
 */
package jum.utility;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * @author 李锦忠, 2018年9月15日
 * @since 产品/模块
 */
public class WordUtils {

    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.WordUtils.class);
    /** 
     * 功能简述:替换doc文件里的某些文字<br>
     * 详细描述:被替换的变量规则xxx<br>
     * @author 李锦忠
     * @CreateTime 2018年9月15日下午2:12:55
     * @param srcFile
     * @param destFile
     * @param map
     */
    public static boolean replaceDoc(String srcFile, String destFile, Map<String, Object> map) {
        try {
            // 读取word模板
            InputStream fis = null;
            File file = new File(srcFile);
            if (file.exists()) {
                fis =new FileInputStream(file);
            } else {
                fis = Thread.currentThread().getContextClassLoader().getResource(srcFile).openStream();
            }
            HWPFDocument doc = new HWPFDocument(fis);
            // 读取word文本内容
            Range bodyRange = doc.getRange();
            // 替换文本内容
            for (Entry<String, Object> entry : map.entrySet()) {
                bodyRange.replaceText(entry.getKey(), String.valueOf(entry.getValue()));
            }
            //输出word文件
            OutputStream outs = new FileOutputStream(destFile);
            doc.write(outs);
            outs.close();
        } catch (Exception e) {
            log.error("Exception", e);
            return false;
        }
        return true;
    }
    
    /** 
     * 功能简述:替换docx文件里的某些文字<br>
     * 详细描述:被替换的变量规则xxx<br>
     * @author 李锦忠
     * @CreateTime 2018年9月15日下午2:12:55
     * @param srcFile
     * @param destFile
     * @param map
     */
    public static boolean replaceDocx(String srcFile, String destFile, Map<String, Object> map) {
        try {
            //获取docx解析对象
            InputStream fis = null;
            File file = new File(srcFile);
            if (file.exists()) {
                fis =new FileInputStream(file);
            } else {
                fis = Thread.currentThread().getContextClassLoader().getResource(srcFile).openStream();
            }
            XWPFDocument document = new XWPFDocument(fis);
            //获取段落集合
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                //判断此段落时候需要进行替换
                String text = paragraph.getText();
                if(checkText(text)){
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        //替换模板原来位置
                        run.setText(changeValue(run.toString(), map),0);
                    }
                }
            }
          //获取表格对象集合
            List<XWPFTable> tables = document.getTables();
            for (int i = 0; i < tables.size(); i++) {
                //只处理行数大于等于2的表格，且不循环表头
                XWPFTable table = tables.get(i);
                if(table.getRows().size()>1){
                    if(checkText(table.getText())){
                        List<XWPFTableRow> rows = table.getRows();
                        //遍历表格,并替换模板
                        eachTable(rows, map);
                    }
                }
            }
            //输出word文件
            OutputStream outs = new FileOutputStream(destFile);
            document.write(outs);
            outs.close();
        } catch (Exception e) {
            log.error("Exception", e);
            return false;
        }
        return true;
    }
   
   /**
    * 判断文本中时候包含{
    * @param text 文本
    * @return 包含返回true,不包含返回false
    */
   public static boolean checkText(String text){
       if(text.indexOf("{") != -1){
           return true;
       }
       return true;

   }

   /**
    * 匹配传入信息集合与模板
    * @param value 模板需要替换的区域
    * @param textMap 传入信息集合
    * @return 模板需要替换区域信息集合对应值
    */
   public static String changeValue(String value, Map<String, Object> textMap){
       for (Entry<String, Object> textSet : textMap.entrySet()) {
           //匹配模板与替换值 格式key
           String key = textSet.getKey();
           if(value.indexOf(key)!= -1){
               value = String.valueOf(textSet.getValue());
           }
       }
       return value;
   }

   /**
    * 遍历表格
    * @param rows 表格行对象
    * @param textMap 需要替换的信息集合
    */
   public static void eachTable(List<XWPFTableRow> rows ,Map<String, Object> textMap){
       for (XWPFTableRow row : rows) {
           List<XWPFTableCell> cells = row.getTableCells();
           for (XWPFTableCell cell : cells) {
               //判断单元格是否需要替换
               if(checkText(cell.getText())){
                   List<XWPFParagraph> paragraphs = cell.getParagraphs();
                   for (XWPFParagraph paragraph : paragraphs) {
                       List<XWPFRun> runs = paragraph.getRuns();
                       for (XWPFRun run : runs) {
                           run.setText(changeValue(run.toString(), textMap),0);
                       }
                   }
               }
           }
       }
   }

    
}

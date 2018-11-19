/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： FileUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;

/**
 * 
 * 文件操作工具类
 * @author 仝玉锐
 * @since  2015-8-21
 */
public class FileUtils {

	 private static final FileUtils instance = new FileUtils();
	 
	    /**
	     * 单例构造
	     * 
	     * @return
	     */
	    public static FileUtils getInstance() {
	        return instance;
	    }
	 
	    /**
	     * 创建文件
	     * 
	     * @param str
	     * @param fileName
	     * @param absoluteFilePath
	     * @return
	     * @throws ApplicationException
	     */
	    public boolean createFile(String fileName, String absoluteFilePath)
	            throws Exception {
	        if (absoluteFilePath == null || absoluteFilePath.length() == 0) {
	            throw new Exception("创建文件的绝对路径为空");
	        }
	        if (fileName == null || fileName.length() == 0) {
	            throw new Exception("文件名为空");
	        }
	        File file = new File(absoluteFilePath + File.separator + fileName);
	        try {
	            if (!file.exists()) {
	                file.createNewFile();
	                return true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	 
	    }
	 
	    /**
	     * 往文件写入内容
	     * 
	     * @param str
	     * @param filePath
	     * @throws ApplicationException
	     */
	    public void writeTXTtoFile(String str, String filePath)
	            throws Exception {
	        if (str == null || str.length() == 0) {
	            throw new Exception("需要写入的内容为空");
	        }
	        if (filePath == null || filePath.length() == 0) {
	            throw new Exception("文件路径为空");
	        }
	 
	        File file = new File(filePath);
	        
	        if (!file.getParentFile().exists()) {  
	            if (!file.getParentFile().mkdirs()) {  
	            }  
	        }  
	        
	        if (!file.exists()) {
	            try {
	                file.createNewFile();
	                // 写入内容
	                OutputStream os=new FileOutputStream(file);  
	                Writer r=new OutputStreamWriter(os,"gbk"); 
	                BufferedWriter output = new BufferedWriter(r);
	                output.write(str);
	                output.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }else{
	        	OutputStream os=new FileOutputStream(file,true);  
                Writer r=new OutputStreamWriter(os,"gbk");
	        	BufferedWriter output = new BufferedWriter(r);
	        	output.write(str);
                output.close();
	        }
	 
	    }
	    
	    /**
	     * 读取文件内容
	     * @param file
	     * @return
	     * @throws Exception
	     * @author 仝玉锐
	     */
	    public static List<String> readTXTtoFile(File file)
	            throws Exception {
	    	List<String> list = new ArrayList<String>();
			try {
				InputStream is = new FileInputStream(file);
				Reader reader = new InputStreamReader(is, "gbk");
				BufferedReader br = new BufferedReader(reader);
				String s = null;
				int i=0;
				while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
					list.add(s);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	    }
	    /**
	     * 获取扩展名
	     * @param fileName
	     * @return
	     * @author LZX 20170621 1424
	     */
	    public static final String getFileSuffix(String fileName) {
	    	String suffix = "";
	        int j = fileName.lastIndexOf('.');
	        if ((j > 0) && (j < fileName.length() - 1)) {
	          suffix = fileName.substring(j + 1).toLowerCase();
	        }

	        return suffix;
	    }
	    
	    /**
	     * 获取扩展名
	     * @param fileName
	     * @return
	     * @author LZX 20170726 1553
	     */
	    public static final String getErecordid(String fileName) {
	    	String erecordid = "";
	        int j = fileName.lastIndexOf('.');
	        if ((j > 0) && (j < fileName.length() - 1)) {
	        	erecordid = fileName.substring(0, j).split("_")[0];
	        }

	        return erecordid;
	    }
	    
	    public static void main(String[] args) throws Exception{
	    	FileUtils fileutils = FileUtils.getInstance();
	    	fileutils.writeTXTtoFile("abc\r\n","c:\\aa\\a.txt");
	    	fileutils.writeTXTtoFile("dfd\r\n","c:\\aa\\a.txt");
	    }
}


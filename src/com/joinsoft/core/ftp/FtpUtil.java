/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： FtpUtil.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 /**
  * 
  * FTP上传下载工具类
  * @author 仝玉锐
  * @since  2015-8-21
  */
public class FtpUtil {
	private static Logger        logger = LoggerFactory.getLogger(FtpUtil.class);
    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param input
     *            输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String url, int port, String username,
            String password, String path, String filename, InputStream input) {
        boolean returnValue = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.setControlEncoding("gbk");
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.info("FTP SERVER REFUSED CONNECTION.");
                return returnValue;
            }
            String home_path = ftp.printWorkingDirectory();
            ftp.changeWorkingDirectory(home_path + path);
 
            ftp.storeFile(filename, input);
 
            input.close();
 
            ftp.logout();
            returnValue = true;
        } catch (IOException e) {
            logger.info("FTPUPLOADUTIL UPLOD FILE ERROR :" + e);
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return returnValue;
    }
 
 
    /**
     * Description: 向FTP服务器下载文件
     *
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param localPath
     *            输出路径
     * @return 成功返回true，否则返回false
     */
    public static boolean downloadFile(String url, int port, String username,
            String password, String path, String filename, String localPath) {
        boolean returnValue = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);// 连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.setControlEncoding("gbk");
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.info("FTP SERVER REFUSED CONNECTION.");
                return returnValue;
            }
            String home_path = ftp.printWorkingDirectory();
            ftp.changeWorkingDirectory(home_path + path);
 
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(filename)) {
                    File localFile = new File(localPath+"/"+ff.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), os);
                    returnValue = true;
                }
            }
 
            ftp.logout();
        } catch (IOException e) {
            logger.info("FTPDOWNLOADUTIL DOWNLOAD FILE ERROR :" + e);
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return returnValue;
    }

}
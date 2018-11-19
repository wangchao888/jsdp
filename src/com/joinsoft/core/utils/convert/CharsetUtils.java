/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.core.utils.convert;

import java.io.UnsupportedEncodingException;


/**
 * 
 * 各类字符编码间转换。
 * @author 
 * @since  2014-9-5
 */
public class CharsetUtils {
    private static final String US_ASCII   = "US-ASCII";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String UTF_8      = "UTF-8";
    private static final String GBK        = "GBK";
    
    
    /**
     * 
     * 功能: 字符串编码转换核心方法
     * 
     * @param str         待转换的字符串
     * @param newCharset  转换的目标编码
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:04:43
     */
    private static String changeCharset(String str,String newCharset) throws UnsupportedEncodingException{
        if(str != null){
            byte[] bs = str.getBytes();
            return new String(bs,newCharset);
        }
        return null;
    }
    /**
     * 
     * 功能: 字符串编码转换核心方法
     * 
     * @param str         待转换的字符串
     * @param oldCharset  待转换的字符串编码
     * @param newCharset  字符串目标编码
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:04:59
     */
    public String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }
    
    /**
     * 
     * 功能: 字符串转换为ascii编码
     * 
     * @param str  需要转换的字符串
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:05:09
     */
    public  static String toASCII(String str) throws UnsupportedEncodingException{ 
        return changeCharset(str, US_ASCII); 
    }
    
    /**
     * 
     *	功能: 字符串转换为gbk编码
     * 
     * @param str    需要转换的字符串
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:08:06
     */
    public  static String toGBK(String str) throws UnsupportedEncodingException{ 
        return changeCharset(str, GBK); 
    }
    
    /**
     * 
     * 功能: 字符串转换为iso编码
     * 
     * @param str   需要转换的字符串
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:08:18
     */
    public  static String toISO_8859_1(String str) throws UnsupportedEncodingException{ 
        return changeCharset(str, ISO_8859_1); 
    }
    
    /**
     * 
     * 功能: 字符串转换为utf-8编码
     * 
     * @param str    需要转换的字符串
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author 
     * @since 2014-9-5下午4:08:30
     */
    public  static String toUTF_8(String str) throws UnsupportedEncodingException{ 
        return changeCharset(str, UTF_8); 
    }

}

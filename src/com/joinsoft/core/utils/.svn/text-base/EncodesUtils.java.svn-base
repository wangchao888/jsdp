/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： EncodesUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 各类编码。
 * @author wkd
 * @since  2014年8月18日
 */
public class EncodesUtils {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

    private EncodesUtils() {
    }
    
    /**
     * 
     * 进行hex编码
     * 
     * @param input
     * @return
     *
     * @author wkd
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }
    
    /**
     * 
     * hex解码
     * 
     * @param input
     * @return
     *
     * @author wkd
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }
    
    
    /**
     * 
     * url编码
     * 
     * @param part
     * @return
     *
     * @author wkd
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    
    /**
     * 
     * url解码
     * 
     * @param part
     * @return
     *
     * @author wkd
     */
    public static String urlDecode(String part) {

        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    // 根据传入编号生成条形码,采用交叉二五码(ITF二五码)
    public static String getBarITF25(String code, String webContext) {
        String[][] barCode = {{"0", "0", "1", "1", "0"}, {"1", "0", "0", "0", "1"}, {"0", "1", "0", "0", "1"},
                {"1", "1", "0", "0", "0"}, {"0", "0", "1", "0", "1"}, {"1", "0", "1", "0", "0"},
                {"0", "1", "1", "0", "0"}, {"0", "0", "0", "1", "1"}, {"1", "0", "0", "1", "0"},
                {"0", "1", "0", "1", "0"}};
       /* StringBuffer codeBuff = new StringBuffer("<img src='" + webContext + "/static/img/barcode/start.gif' width='8' height='43'/>");
        String str10 = "<img src='" + webContext + "/static/img/barcode/10.gif' width='2' height='43'/>";
        String str20 = "<img src='" + webContext + "/static/img/barcode/20.gif' width='4' height='43'/>";
        String str11 = "<img src='" + webContext + "/static/img/barcode/11.gif' width='2' height='43'/>";
        String str21 = "<img src='" + webContext + "/static/img/barcode/21.gif' width='4' height='43'/>";*/
        StringBuffer codeBuff = new StringBuffer("<div style='height:43px;width:2px;background:black;display:inline; float:left'></div><div style='height:43px;width:2px;background:white;display:inline; float:left'></div><div style='height:43px;width:2px;background:black;display:inline; float:left'></div><div style='height:43px;width:2px;background:white;display:inline; float:left'></div>");
        String str10 = "<div style='height:43px;width:2px;background:white;display:inline; float:left'></div>";
        String str20 = "<div style='height:43px;width:4px;background:white;display:inline; float:left'></div>";
        String str11 = "<div style='height:43px;width:2px;background:black;display:inline; float:left'></div>";
        String str21 = "<div style='height:43px;width:4px;background:black;display:inline; float:left'></div>";
        int len = code.length();
        if (len % 2 == 1) {
            code = "0" + code;
        } else {
            len--;
        }
        int i = 0;
        while (i < len) {
            int one = 0;
            int two = 0;
            try {
                one = Integer.parseInt(code.substring(i, (i + 1)));
            } catch (Exception exp) {
                one = 0;
            }
            try {
                two = Integer.parseInt(code.substring((i + 1), (i + 2)));
            } catch (Exception exp) {
                two = 0;
            }
            for (int m = 0; m < 5; m++) {
                if (barCode[one][m].equals("0")) {
                    codeBuff.append(str11);
                } else {
                    codeBuff.append(str21);
                }
                if (barCode[two][m].equals("0")) {
                    codeBuff.append(str10);
                } else {
                    codeBuff.append(str20);
                }
            }
            i = i + 2;
        }
        codeBuff.append("<div style='height:43px;width:4px;background:black;display:inline; float:left'></div><div style='height:43px;width:2px;background:white;display:inline; float:left'></div><div style='height:43px;width:2px;background:black;display:inline; float:left'></div>");
        return (codeBuff.toString());
    }
}


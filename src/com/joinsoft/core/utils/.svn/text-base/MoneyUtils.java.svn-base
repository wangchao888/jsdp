/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： MoneyUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.text.NumberFormat;


public class MoneyUtils {

	private static int    YUAN_TO_FEN_RATIO    = 100;      /* 元到分的转换比率 */
    private static double FIX_FLOAT_ERR_NUMBER = 0.000001; /* 去除浮点误差 */
    
    /**
     * 
     * double类型的数值转为中文货币格式。
     * @param money
     * @return
     *
     * @author wkd
     * @since 2014年9月3日下午7:36:41
     */
    public static String convertChinese(double money) {

        char[] s1 = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
        char[] s4 = { '分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万' };
        String str = String.valueOf(Math.round(money * YUAN_TO_FEN_RATIO + FIX_FLOAT_ERR_NUMBER));
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(str.length() - 1 - i) - '0';
            result = s1[n] + "" + s4[i] + result;
        }

        result = result.replaceAll("零仟", "零");
        result = result.replaceAll("零佰", "零");
        result = result.replaceAll("零拾", "零");
        result = result.replaceAll("零亿", "亿");
        result = result.replaceAll("零万", "万");
        result = result.replaceAll("零元", "元");
        result = result.replaceAll("零角", "零");
        result = result.replaceAll("零分", "零");

        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零亿", "亿");
        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零万", "万");
        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零元", "元");
        result = result.replaceAll("亿万", "亿");

        result = result.replaceAll("零$", "");
        result = result.replaceAll("元$", "元整");
        result = result.replaceAll("角$", "角整");
        return result;
    }
    

    /**
     * 
     * 字符串转换为中文货币格式。
     * @param money
     * @return
     *
     * @author wkd
     * @since 2014年9月3日下午7:37:12
     */
    public static String convertChinese(String money) {
        double dmoney = (Double) StringUtils.fromString(money, double.class);
        return convertChinese(dmoney);
    }
    
    public static void main(String[] args){
    	System.out.println(getStrByDouble(11111111.1254,2));
    }
    
    /**
     * 
     * 三位一节表示法。
     * 小数位数n+1位进行四舍五入
     * @param v  输入double数值
     * @param n  保留小数位数
     * @return
     *
     * @author wkd
     * @since 2014年9月3日下午7:40:50
     */
    public static String getStrByDouble(Double v, int n){
        if(v==null){
            return "0";
        }
        NumberFormat numFormat = NumberFormat.getNumberInstance();
        numFormat.setMaximumFractionDigits(n);
        String str = numFormat.format(v);
        return str;
    }
    /**
     * 只将数字转换成大写 如 1001 转换成 壹零零壹
     * %方法功能的一句话概括%。
     * <p>%方法详述（简单方法可不必）%</p> 用于套打数字
     * @param money
     * @return
     *
     * @author 张福勋
     * @since 2015-3-4上午9:44:32
     */
    public static String convertChineseNum(String money) {
    	if(!money.contains(".")){
    		money += ".00";
    	}else{
    		if(money.split("\\.")[1].length() == 1){
    			money += "0";
    		}
    	}
    	money = StringUtils.addZero(money, 10);//补零操作，如果金额为4213.23，那么数字格式为：000004213.23,打印票据的前面有这几个零的操作。
    	money = money.split("\\.")[0]+money.split("\\.")[1];
    	String convertChineseNum = money.replaceAll("0", "零");
    	convertChineseNum = convertChineseNum.replaceAll("1", "壹");
    	convertChineseNum = convertChineseNum.replaceAll("2", "贰");
    	convertChineseNum = convertChineseNum.replaceAll("3", "叁");
    	convertChineseNum = convertChineseNum.replaceAll("4", "肆");
    	convertChineseNum = convertChineseNum.replaceAll("5", "伍");
    	convertChineseNum = convertChineseNum.replaceAll("6", "陆");
    	convertChineseNum = convertChineseNum.replaceAll("7", "柒");
    	convertChineseNum = convertChineseNum.replaceAll("8", "捌");
    	convertChineseNum = convertChineseNum.replaceAll("9", "玖");
        return convertChineseNum;
    }
}


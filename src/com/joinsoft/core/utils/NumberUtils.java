/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： NumberUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.math.BigDecimal;

public class NumberUtils {

	private NumberUtils() {

	}

	
	/**
	 * 
	 * 精度加法运算。
	 * @param num1
	 * @param num2
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:28:46
	 */
	public static double add(double num1, double num2) {
		BigDecimal bignum1 = new BigDecimal(Double.toString(num1));
		BigDecimal bignum2 = new BigDecimal(Double.toString(num2));
		return bignum1.add(bignum2).doubleValue();
	}
	
	/**
	 * 
	 * 精度减法运算。
	 * @param num1
	 * @param num2
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:29:09
	 */
	public static double subtract(double num1, double num2) {
		BigDecimal bignum1 = new BigDecimal(Double.toString(num1));
		BigDecimal bignum2 = new BigDecimal(Double.toString(num2));
		return bignum1.subtract(bignum2).doubleValue();
	}

	/**
	 * 
	 * 精度乘法运算。
	 * @param num1
	 * @param num2
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:29:29
	 */
	public static double multiply(double num1, double num2) {
		BigDecimal big1 = new BigDecimal(Double.toString(num1));
		BigDecimal big2 = new BigDecimal(Double.toString(num2));
		return big1.multiply(big2).doubleValue();
	}

	/**
	 * 
	 * 功能: 
	 * 
	 * @param num1
	 * @param num2
	 * @param scale
	 * @return
	 * 
	 *         作者：Exodus
	 */
	
	/**
	 * 
	 * 精度乘法运算，并对结果截位.
	 * @param num1
	 * @param num2
	 * @param scale  小数点后保留的位数
	 * @return
	 *
	 * @author 
	 * @since 2014年9月3日下午7:29:50
	 */
	public static double multiply(double num1, double num2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(" 截取位数必须是正整数或者零  ");
		}
		BigDecimal big1 = new BigDecimal(Double.toString(num1));
		BigDecimal big2 = new BigDecimal(Double.toString(num2));
		return big1.multiply(big2).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 
	 * 功能: 精确除法运算
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * 
	 *         作者：Exodus
	 */
	
	/**
	 * 
	 * 精确除法运算。
	 * <p>
	 * 例如：维修费 1000元。分摊面积50平 求每平米分摊维修费。
	 * divide(1000,50)
	 * </p>
	 * @param num1 被除数
	 * @param num2 除数
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:31:40
	 */
	public static double divide(double num1, double num2) {
		BigDecimal big1 = new BigDecimal(Double.toString(num1));
		BigDecimal big2 = new BigDecimal(Double.toString(num2));
		return big1.divide(big2).doubleValue();
	}
	

	/**
	 * 
	 * 精确的除法运算，并截位。
	 * @param num1
	 * @param num2
	 * @param scale
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:34:55
	 */
	public static double divide(double num1, double num2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(" 截取位数必须是正整数或者零 ");
		}

		BigDecimal big1 = new BigDecimal(Double.toString(num1));
		BigDecimal big2 = new BigDecimal(Double.toString(num2));
		return big1.divide(big2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * 功能: 对数据进行截取
	 * 
	 * @param v
	 *            待操作的数据
	 * @param scale
	 *            截取位数
	 * @return
	 * 
	 *         作者：Exodus
	 */
	
	/**
	 * 
	 *对数据进行截取（四舍五入）。
	 * @param v
	 * @param scale
	 * @return
	 *
	 * @author wkd
	 * @since 2014年9月3日下午7:35:19
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("截取位数必须是正整数或者零 ");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 取整函数 把小数点后面的去掉
	 */
	public static double integer(double v){
		return Math.floor(v);
	}
	public static void main(String[] args) {
		String parm = "04";
		double dueAmount = 112.435;
		double amount = 0.00;
		if(parm.equals("01")){
			amount = dueAmount;
		}else if(parm.equals("02")){ //舍厘-进位到分
			amount = NumberUtils.round(dueAmount, 2);
		}else if(parm.equals("03")){//舍分-进位到角
			amount = NumberUtils.round(dueAmount, 1);
		}else if(parm.equals("04")){//四舍五入到角
//			double d = dueAmount + 0.049;
			double d = dueAmount;
			amount = NumberUtils.round(d, 1);
		}else if(parm.equals("05")){//四舍五入到元
//			double d = dueAmount+0.49;
			double d = dueAmount;
			amount = NumberUtils.round(d, 0);
		}else if(parm.equals("06")){ //取整数
			amount = NumberUtils.integer(dueAmount);
		}else{
			amount = dueAmount;
		}
		System.out.println(amount); 
		
		
		
		//System.out.println(divide(1000,50));
	}

}

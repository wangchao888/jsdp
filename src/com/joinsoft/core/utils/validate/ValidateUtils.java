/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils.validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.dubbo.common.json.ParseException;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.convert.IntegerUtils;

/**
 * 
 * %类功能的概括%。
 * @author 
 * @since  2014-9-5
 */
public class ValidateUtils {

public static final String ACCEPT = "";	// 检查通过是返回的的成功标识字符串
	
	public static final int EIGHTEEN_IDCARD = 18;	//标识18位身份证号码
	public static final int FIFTEEN_IDCARD = 15;	//标识15位身份证号码
	
	public static final int MAX_MAINLAND_AREACODE = 659004;	//大陆地区地域编码最大值
	public static final int MIN_MAINLAND_AREACODE = 110000;	//大陆地区地域编码最小值
	public static final int HONGKONG_AREACODE = 810000;	//香港地域编码值
	public static final int TAIWAN_AREACODE = 710000;	//台湾地域编码值
	public static final int MACAO_AREACODE = 820000;	//澳门地域编码值
	
	
	//储存18位身份证校验码
		private static final String[] SORTCODES = new String[]{"1","0","X","9","8","7","6","5","4","3","2"};


    public static String[] validate(String val,String rules) {
        String[] msg = new String[2];
        boolean rtn = false;
        String[] tmprules = StringUtils.split(rules,",|,/,-");
        for(int i=0;i<tmprules.length;i++){
            String rule =  tmprules[i];
            if("required".equals(rule)){
                rtn = required(val);
                if(!rtn){
                    msg[1] ="输入项不能为空";
                }
            }else if("email".equals(rule)){
                rtn = email(val);
                if(!rtn){
                    msg[1] ="电子邮箱校验未通过";
                }
            }else if("url".equals(rule)){
                rtn = url(val);
                if(!rtn){
                    msg[1] ="url格式校验未通过";
                }
            }else if("date".equals(rule)){
                rtn = date(val);
                if(!rtn){
                    msg[1] ="日期校验未通过";
                }
            }else if("number".equals(rule)){
                rtn = number(val);
                if(!rtn){
                    msg[1] ="数值校验未通过";
                }
            }else if("digits".equals(rule)){
                rtn = digits(val);
                if(!rtn){
                    msg[1] ="整数校验未通过";
                }
            }else if(rule.contains("maxlength")){
                int len = getLen(rule);
                if(len==0){
                    msg[0] = "false";
                    msg[1] = "最大长度校验未通过";
                    return msg;
                }
                rtn = maxlength(val, IntegerUtils.toInteger(len));
                if(!rtn){
                    msg[1] ="最大长度校验未通过";
                }
            }else if(rule.contains("minlength")){
                //得到长度
                int len = getLen(rule);
                if(len==0){
                    msg[0] = "false";
                    msg[1] = "最小长度校验未通过";
                    return msg;
                }
                rtn = minlength(val,IntegerUtils.toInteger(len));
                if(!rtn){
                    msg[1] ="最小长度校验未通过";
                }
            }else if(rule.contains("idcard")){
            	rtn = chekIdCard(val);
            	if(!rtn){
            		msg[1] ="身份证号不合法";
            	}
            }else{
                rtn = false;
                msg[1] = "未定义的规则";
            }
            if(rtn == false){
                break;
            }
        }
        msg[0] = StringUtils.toString(rtn);
        return msg;
    }

    /**
     * 
     * 功能：字段不能为空。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:18:59
     */
    public static boolean  required(String val){
        if(!StringUtils.isBlank(val)){
             return true;
        }
        return false;
    }

    /**
     * 
     * 功能：email
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:19:10
     */
    public static boolean email(String val){
        String regex = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
        return regex(regex,val);
    }

    /**
     * 
     * 功能：url
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:19:10
     */
    public static boolean url(String val){
        String regex = "^(http|www|ftp|)?(://)?(//w+(-//w+)*)(//.(//w+(-//w+)*))*((://d+)?)(/(//w+(-//w+)*))*(//.?(//w)*)(//?)?(((//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*(//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*)*(//w*)*)$";
        return regex(regex,val);
    }

    /**
     * 日期校验
     * %方法功能的一句话概括%。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:19:51
     */
    public static boolean  date(String val){
    	Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
        Matcher m = p.matcher(val);
    	return m.matches();
    }
    

    /**
     * 功能：数值校验 (包括负数和小数)
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:22:13
     */
    public static  boolean number(String val){
        String regex = "(-)?\\d+(\\.(\\d)+)?";
        return regex(regex,val);
    }

    /**
     * 功能：整数校验
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:22:19
     */
    public static boolean digits(String val){
        String regex = "(-)?\\d+$";
        return regex(regex,val);
    }

    /**
     * 功能：判断输入字符串长度是否<=指定长度
     * @param val  输入字符串
     * @param num  最大长度
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:22:30
     */
    public static boolean maxlength(String val,int num){
        int tmp = val.length();
        if(tmp<=num){
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能：输入字符串长度>=指定长度
     * <p>%方法详述（简单方法可不必）%</p>
     * @param val
     * @param num
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:22:48
     */
    public static boolean minlength(String val,int num){
        int tmp = val.length();
        if(tmp>=num){
            return true;
        }
        return false;
    }
    /**
     * 
     * 功能:正则表达式。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param regex
     * @param value
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:22:58
     */
    public static boolean regex(String regex, String value)
    {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

    /**
     * 
     * 功能：得到rule中指定的长度。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param rule
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:23:09
     */
    private static int getLen(String rule){
        //得到长度
        int startlen = rule.indexOf("[");
        int endlen   = rule.indexOf("]");
        if(startlen == -1 || endlen == -1){
            return 0;
        }
        String tmplen = rule.substring(startlen+1,endlen);
        if(tmplen.length()==0){
            return 0;
        }
        return IntegerUtils.toInteger(tmplen);
    }
    /**
     * 
     * 验证身份证主方法。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param idCardInput
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:23:17
     */
	public static boolean chekIdCard( String idCardInput){
		boolean flag = false;
		if( idCardInput == null || "".equals(idCardInput)){
			flag = false;
		}
		if( idCardInput.length() != 18 && idCardInput.length() !=15 ){
			flag = false;
		}
		if( idCardInput.length() == 15){
			if("".equals(checkIdCard15(idCardInput))){
				flag = true;
			}else{
				flag = false;
			}
		}else if(idCardInput.length() == 18){
			if("".equals(checkIdCard18(idCardInput))){
				flag = true;
			}else{
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * 验证15位身份证号码。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:23:25
	 */
	public static String checkIdCard15(String idCardInput ){
		String numberResult = checkNumber(FIFTEEN_IDCARD,idCardInput);
		if( !ACCEPT.equals(numberResult))
			return numberResult;
		
		String areaResult = checkArea(idCardInput);
		if( !ACCEPT.equals(areaResult))
			return areaResult;
		
		String birthResult = checkBirthDate( FIFTEEN_IDCARD, idCardInput);
		if( !ACCEPT.equals(birthResult))
			return birthResult;
		
		String sortCodeResult = checkSortCode(FIFTEEN_IDCARD,idCardInput);
		if( !ACCEPT.equals(sortCodeResult))
			return sortCodeResult;
		
//		String checkCodeResult = checkCheckCode(FIFTEEN_IDCARD,idCardInput);
//		if( !ACCEPT.equals(checkCodeResult))
//			return checkCodeResult;
		
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证18位身份证号码。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:23:33
	 */
	public static String checkIdCard18(String idCardInput ){
		
		String numberResult = checkNumber(EIGHTEEN_IDCARD,idCardInput);
		if( !ACCEPT.equals(numberResult))
			return numberResult;
		
		String areaResult = checkArea(idCardInput);
		if( !ACCEPT.equals(areaResult))
			return areaResult;
		
		String birthResult = checkBirthDate( EIGHTEEN_IDCARD, idCardInput);
		if( !ACCEPT.equals(birthResult))
			return birthResult;

		String sortCodeResult = checkSortCode(EIGHTEEN_IDCARD,idCardInput);
		if( !ACCEPT.equals(sortCodeResult))
			return sortCodeResult;
		
//		String checkCodeResult = checkCheckCode(EIGHTEEN_IDCARD,idCardInput);
//		if( !ACCEPT.equals(checkCodeResult))
//			return checkCodeResult;
		
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证的地域编码是符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:23:40
	 */
	private static String checkArea( String idCardInput ){
		String subStr = idCardInput.substring(0, 6);
		int areaCode = Integer.parseInt(subStr);
		if( areaCode != HONGKONG_AREACODE && areaCode != TAIWAN_AREACODE
				&& areaCode != MACAO_AREACODE 
				&& ( areaCode > MAX_MAINLAND_AREACODE || areaCode < MIN_MAINLAND_AREACODE) )
			return "输入的身份证号码地域编码不符合大陆和港澳台规则";
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证号码数字字母组成是否符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCard
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:23:48
	 */
	private static String checkNumber( int idCardType ,String idCard ){
		char[] chars = idCard.toCharArray();
		if( idCardType == FIFTEEN_IDCARD ){
			for( int i = 0; i<chars.length;i++){
				if( chars[i] > '9' )
					return idCardType+"位身份证号码中不能出现字母";
			}
		} else {
			for( int i = 0; i < chars.length; i++ ) {
				if( i < chars.length-1 ){
					if( chars[i] > '9' )
						return EIGHTEEN_IDCARD+"位身份证号码中前"+(EIGHTEEN_IDCARD-1)+"不能出现字母";
				} else {
					if( chars[i] > '9' && chars[i] != 'X')
						return idCardType+"位身份证号码中最后一位只能是数字0~9或字母X";
				}
			}
			
		}
			
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证号码出生日期是否符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:23:56
	 */
	private static String checkBirthDate(int idCardType, String idCardInput ){
		String yearResult = checkBirthYear(idCardType,idCardInput);
		if( !ACCEPT.equals(yearResult))
			return yearResult;
		
		String monthResult = checkBirthMonth(idCardType,idCardInput);
		if( !ACCEPT.equals(monthResult))
			return monthResult;
		
		String dayResult = checkBirthDay(idCardType,idCardInput);
		if( !ACCEPT.equals(dayResult))
			return dayResult;

		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证号码出生日期年份是否符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:05
	 */
	private static String checkBirthYear(int idCardType, String idCardInput){
		if( idCardType == FIFTEEN_IDCARD){
			int year = Integer.parseInt(idCardInput.substring(6, 8));
			if( year < 0 || year > 99 )
				return idCardType+"位的身份证号码年份须在00~99内";
		} else {
			int year = Integer.parseInt(idCardInput.substring(6, 10));
			int yearNow = getYear();
			if( year < 1900 || year > yearNow )
				return idCardType+"位的身份证号码年份须在1900~"+yearNow+"内";
		}
		return ACCEPT;
	}

	/**
	 * 
	 * 验证身份证号码出生日期月份是否符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:13
	 */
	private static String checkBirthMonth(int idCardType, String idCardInput){
		int month = 0;
		if( idCardType == FIFTEEN_IDCARD)
			month = Integer.parseInt(idCardInput.substring(8, 10));	
		else 
			month = Integer.parseInt(idCardInput.substring(10, 12));
		
		if( month < 1 || month > 12)
			return "身份证号码月份须在01~12内";
			
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证号码出生日期天数是否符合规则。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:20
	 */
	private static String checkBirthDay(int idCardType, String idCardInput){
		boolean bissextile = false; 
		int year,month,day;
		if( idCardType == FIFTEEN_IDCARD){
			year = Integer.parseInt("19"+idCardInput.substring(6, 8));
			month = Integer.parseInt(idCardInput.substring(8, 10));	
			day = Integer.parseInt(idCardInput.substring(10, 12));
		} else {
			year = Integer.parseInt(idCardInput.substring(6, 10));
			month = Integer.parseInt(idCardInput.substring(10, 12));
			day = Integer.parseInt(idCardInput.substring(12, 14));
		}
		if( year%4 == 0 && year%100 != 0 || year%400 ==0 )	
			bissextile = true;
		
		switch( month ){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if( day < 1 || day > 31 )
				return "身份证号码大月日期须在1~31之间";
				break;
		case 4:
		case 6:
		case 9:
		case 11:
			if( day < 1 || day > 30 )
				return "身份证号码小月日期须在1~30之间";
				break;
		case 2:
			if(bissextile){
				if( day < 1 || day > 29 )
					return "身份证号码闰年2月日期须在1~29之间";
			}else {
				if( day < 1 || day > 28 )
					return "身份证号码非闰年2月日期年份须在1~28之间";
			}
			break;
		}
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证身份证号码顺序码是否符合规则,男性为偶数,女性为奇数。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCardInput
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:29
	 */
	private static String checkSortCode(int idCardType ,String idCardInput){
		int sortCode = 0;
		if( idCardType == FIFTEEN_IDCARD ){
			sortCode = Integer.parseInt(idCardInput.substring(12, 15));
		} else {
			sortCode = Integer.parseInt(idCardInput.substring(14, 17));
		}
		return ACCEPT;
	}
	
	/**
	 * 
	 * 验证18位身份证号码校验码是否符合规则
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param idCardType
	 * @param idCard
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:38
	 */
	@SuppressWarnings("unused")
	private static String checkCheckCode( int idCardType , String idCard ){
		if( idCardType == EIGHTEEN_IDCARD ){
			int sum = 0;
			char[] chars = idCard.toCharArray();
			for( int i=0; i<chars.length; i++ ){
				if( i==0 ) sum = sum+(chars[i]*7);
				if( i==1 ) sum = sum+(chars[i]*9);
				if( i==2 ) sum = sum+(chars[i]*10);
				if( i==3 ) sum = sum+(chars[i]*5);
				if( i==4 ) sum = sum+(chars[i]*5);
				if( i==5 ) sum = sum+(chars[i]*8);
				if( i==6 ) sum = sum+(chars[i]*4);
				if( i==7 ) sum = sum+(chars[i]*1);
				if( i==8 ) sum = sum+(chars[i]*6);
				if( i==9 ) sum = sum+(chars[i]*3);
				if( i==10 ) sum = sum+(chars[i]*7);
				if( i==11 ) sum = sum+(chars[i]*9);
				if( i==12 ) sum = sum+(chars[i]*10);
				if( i==13 ) sum = sum+(chars[i]*5);
				if( i==14 ) sum = sum+(chars[i]*8);
				if( i==15 ) sum = sum+(chars[i]*4);
				if( i==16 ) sum = sum+(chars[i]*2);
			}
			
			int checkCode = sum%11;
			String sortCode = SORTCODES[checkCode];
			
			if(!sortCode.equals(String.valueOf(chars[chars.length-1])))
				return "身份中的校验码不正确";
		}
		return ACCEPT;
	}
	
	/**
	 * 返回当前年份
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:45
	 */
	private static int getYear(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd"); 
		String nowStr = format.format(now);
		return Integer.parseInt(nowStr.substring(0, 4));
	}
	
	/**
	 * 奇偶校验算法
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param curSerial
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:24:50
	 */
	public static String checkServialOne(String curSerial) {
		int jyfs = 1; // 校验方式1
		// 校验方式2
		int ver21[] = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		// 校验方式1
		int ver12[] = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 };
		// 得到传入的编号 并转化为数组
		int len = curSerial.length();
		int[] card = new int[len + 1];
		int i, k, m;
		k = 0;
		while (k < len) {
			card[k] = Character.getNumericValue(curSerial.charAt(k));
			k++;
		}
		k = 0;
		m = 0;
		while (k < len) {
			if (jyfs == 2) {
				i = card[k] * ver21[k];
			} else {
				i = card[k] * ver12[k];
			}
			m = m + i / 10 + i % 10;
			k++;
		}
		i = 10 - m % 10;
		if (i == 10) {
			i = 0;
		}
		// 校验位为i
		card[len] = i;
		curSerial = "";
		for (k = 0; k < len + 1; k++) {
			curSerial += card[k];
		}
		return curSerial;
	}
	
	public static void main(String[] args){
		String aa = "650113".substring(4);
		System.out.println(aa);
	}
	/*---------------------------------2017-08-23添加-------------------------------------------*/
	/**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
    /**
     * 功能：身份证的有效验证
     * 
     * @param IDStr
     *            身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static boolean IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.toUpperCase().equals(IDStr.toUpperCase()) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }
    /**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 功能：设置地区编码
     * 
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
}

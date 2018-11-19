/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/

package com.joinsoft.core.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import com.joinsoft.core.utils.StringUtils;


/**
 * 
 * 功能:获取各类格式的日期工具类。
 * @author 
 * @since  2014-9-5
 */
public class DateUtils {

    /**
     * 
     * 功能: 带分割符格式的当天日期 [yyyy-mm-dd]。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:11:04
     */
    public static String getDateAsSep() {
        GregorianCalendar gt = new GregorianCalendar();
        String lsdate = "";
        int year = gt.get(Calendar.YEAR);
        int month = gt.get(Calendar.MONTH) + 1;
        int date = gt.get(Calendar.DATE);
        lsdate = "" + year + "-";
        if (month < 10)
            lsdate = lsdate + "0" + month;
        else
            lsdate += month;
        lsdate += "-";
        if (date < 10)
            lsdate = lsdate + "0" + date;
        else
            lsdate += date;
        return lsdate;
    }

    /**
     * 
     * 功能: 无分割符格式的当天日期 [yyyymmdd]
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:11:26
     */
    public static String getDateNotSep() {
        GregorianCalendar gc = new GregorianCalendar();
        String ymd = "";
        int ye = gc.get(Calendar.YEAR);
        int mo = gc.get(Calendar.MONTH) + 1;
        int da = gc.get(Calendar.DATE);
        ymd = "" + ye;
        if (mo < 10)
            ymd = ymd + "0" + mo;
        else
            ymd += mo;
        if (da < 10)
            ymd = ymd + "0" + da;
        else
            ymd += da;
        return ymd;
    }

    /**
     * 
     * 功能: 返回中文格式的当天日期 [yyyy年mm月dd日]。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:11:34
     */
    public static String getChineseDay() {
        GregorianCalendar gt = new GregorianCalendar();
        String lsdate = "";
        int year = gt.get(Calendar.YEAR);
        int month = gt.get(Calendar.MONTH) + 1;
        int date = gt.get(Calendar.DATE);

        lsdate = "" + year + "年";
        if (month < 10)
            lsdate = lsdate + "0" + month;
        else
            lsdate += month;
        lsdate += "月";
        if (date < 10)
            lsdate = lsdate + "0" + date;
        else
            lsdate += date;
        lsdate += "日";

        return lsdate;
    }

    /**
     * 
     * 返回当前的年份 。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:11:43
     */
    public static int getYear() {
        GregorianCalendar gt = new GregorianCalendar();
        int year = gt.get(Calendar.YEAR);
        return year;
    }

    /**
     * 
     * 返回当前的月份。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:11:51
     */
    public static String getMonth() {
        GregorianCalendar gt = new GregorianCalendar();
        int month = gt.get(Calendar.MONTH) + 1;
        String monthString = month + "";
        if (month < 10)
            monthString = "0" + month;
        return monthString;
    }

    /**
     * 
     * 功能: 返回当前的日期。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:12:18
     */
    public static String getDay() {
        GregorianCalendar gt = new GregorianCalendar();
        int day = gt.get(Calendar.DATE);
        String dayString = day + "";
        if (day < 10)
            dayString = "0" + day;
        return dayString;
    }

    /**
     * 
     * 功能: 获取当前是星期几。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:15:37
     */
    public static String getWeekOfDate() {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 
     * 功能: 对指定日期增加指定的天数
     * 
     * @param str  日期[yyyy-mm-dd]
     * @param day  增加的天数
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:15:49
     */
    public static String addDay(String str, int day) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(str);
            date = org.apache.commons.lang.time.DateUtils.addDays(date, day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StringUtils.toString(sdf.format(date));
    }
    
    

    /**
     * 
     * 功能: 得到当前时间19位 [yyyy-mm-dd HH:mm:ss]。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:16:01
     */
    public static String getTimeNow() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        return time;
    }
    
    public static String getTimeNowNotSep(){
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        return time;
    }
    
    /**
     *
     *  功能: 获取当前月份的所有天数
     * <p>%方法详述（简单方法可不必）%</p>
     * @param year
     * @param month
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:16:13
     */
    public static int getDaysbyYearMonth(int year, int month){
    	//year 也是从0开始，所以传递进来2001，实际获取的试2000年信息
		//month 从0开始，到11截止。所以传进month：5月份，必须为 4；
    	//year = year - 1;
		month = month - 1;
		int days = 0;
		if(month<0 || month >11){
			days = -1; //错误；
		}else{
			Calendar cal = new GregorianCalendar(year,month,1);
			days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		return days;
	}
    
    
    
    /**
     * 
     * 功能: 获取当前日期格式为yyyy-MM-dd的日期 的各种日期类型值；
     * @param time      为需要获取的日期
     * @param timetype  为需要获取类型  Calendar 为主
     * 例：
     * String month=getFormatTime("2013-01-01",Calendar.MONTH);  //此为获取月份 01
     * String year=getFormatTime("2013-01-01",Calendar.YEAR);  //此为获取年  2013
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:16:28
     */
    
	public static String getFormatTime(String time,int timetype)
    {
    	String thistime="";
    	try
    	{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(time);//获取业务日期 中的月份
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		if(timetype==Calendar.MONTH)
		{
			int s=ca.get(Calendar.MONTH)+1;
			if(s<10)
			{
				thistime=StringUtils.addZero(String.valueOf(s),2);
			}
			else
			{
				thistime=String.valueOf(s);
			}
		}
		else
		{
			int s=ca.get(timetype);
			String temp=String.valueOf(s);
			if(temp.length()==1)
			{
				thistime=StringUtils.addZero(temp,2);
			}
			else
			{
				thistime=temp;
			}
		}
		return thistime;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return thistime;
    	}
    }
	
	 /**
	  * 
	  * 功能: 获取当前日期格式为yyyy-MM的日期 最大日期和最小日期；
      * @param time      为需要获取的日期
      * @return String[] date date[0]为startdate date[1]为enddate 
	  *
	  * @author 
	  * @since 2014-9-5下午4:16:41
	  */
	public static String[] getDateByMonth(String time)
	{
		String[] rtime=new String[2];
		try
    	{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date date=sdf.parse(time);//获取业务日期 中的月份
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		int e=ca.getActualMaximum(Calendar.DAY_OF_MONTH);
		int s=ca.getActualMinimum(Calendar.DAY_OF_MONTH);
		if(s<10)
		{
			rtime[0]=time+"-0"+s;
		}
		else
		{
			rtime[0]=time+"-"+s;
		}
		
		if(e<10)
		{
			rtime[1]=time+"-0"+e;
		}
		else
		{
			rtime[1]=time+"-"+e;
		}
		return rtime;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return rtime;
    	}
	}
	
	/**
	 * 
	 * 功能: 返回两个日期之间的时间差。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param depDate
	 * @param thisDate
	 * @return
	 *
	 * @author 
	 * @since 2014-9-5下午4:16:58
	 */
    public static long betweenDate(String depDate, String thisDate) {
        Calendar rightNow = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance();
        long long1 = 0, long2 = 0;
        try {
            rightNow.setTime(df.parse(depDate));
            long1 = rightNow.getTimeInMillis();
            rightNow.setTime(df.parse(thisDate));
            long2 = rightNow.getTimeInMillis();
        } catch (ParseException e) {
        }
        return ((long2 - long1) / (24 * 60 * 60 * 1000));
    }
    
    

    /**
     * 
     * 获取月份list。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:17:10
     */
       public static List<LinkedHashMap<String,String>> getMonthList(){
       	List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String,String>>();
       	String month = "";
       	for (int i = 1; i < 13; i++) {
       		LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
   			month = i + "";
       		if (month.length() == 1) {
   				month = "0" + i; 
   			}
       		map.put("monkey", month);
       		map.put("monval", i+"");
       		list.add(map);
   		}
       	return list;
       }
       
       
       public static void main(String[] args){
    	   System.out.println(DateUtils.betweenDate("2014-06-30", "2013-11-19"));
       }
       
       /**
        * 
        * 功能: 日期转化 大小写   。
        * <p>%方法详述（简单方法可不必）%</p>
        * @param dateStr
        * @return
        *
        * @author 
        * @since 2014-9-5下午4:17:25
        */
		public static String dataToUpper(String dateStr) {   
			String res="";   
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
			Date date = null;   
			try {   
			    date = df.parse(dateStr);   
			} catch (Exception e) {   
			   // 日期型字符串格式错误   
			    System.out.println("日期型字符串格式错误");   
			}   
			if(date!=null){   
			    Calendar ca = Calendar.getInstance();   
			    ca.setTime(date);   
			    int year = ca.get(Calendar.YEAR);   
			    int month = ca.get(Calendar.MONTH) + 1;   
			    int day = ca.get(Calendar.DAY_OF_MONTH);   
			    res=numToUpper(year) + "   年     " + monthToUppder(month) + "   月    "+dayToUppder(day) + "  日    ";   
			}   
			return res;   
		}
		
		/**
		 * 
		 * 月转化为大写   
		 * <p>%方法详述（简单方法可不必）%</p>
		 * @param month
		 * @return
		 *
		 * @author 
		 * @since 2014-9-5下午4:17:35
		 */
		public static String monthToUppder(int month) {   
		    if (month < 10) {   
		       return numToUpper2(month);   
		    } else if (month == 10) {   
		        return "零壹拾";   
		    } else{
		        return "壹拾" + numToUpper(month - 10);   
		    }   
		}   
		
		/**
		 * 
		 * 功能: 数字转化为大写   。
		 * <p>%方法详述（简单方法可不必）%</p>
		 * @param num
		 * @return
		 *
		 * @author 
		 * @since 2014-9-5下午4:17:45
		 */
		public static String numToUpper(int num) {
		    String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};   
//			    String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		    char[] str = String.valueOf(num).toCharArray();   
		    String rstr = "";   
		    for (int i = 0; i < str.length; i++) {   
		        rstr = rstr + u[Integer.parseInt(str[i] + "")];   
		    }   
		    return rstr;   
		}
		
		/**
		 * 
		 * 日转化为大写  。
		 * <p>%方法详述（简单方法可不必）%</p>
		 * @param day
		 * @return
		 *
		 * @author 
		 * @since 2014-9-5下午4:17:53
		 */
		public static String dayToUppder(int day) {   
			 if (day < 20) {   
		         return monthToUppder(day);   
		     } else {   
		       char[] str = String.valueOf(day).toCharArray();   
		       if (str[1] == '0') {   
		           return "零"+numToUpper(Integer.parseInt(str[0] + "")) + "拾";   
		       } else {   
		            return numToUpper(Integer.parseInt(str[0] + "")) + "拾"  
		                    + numToUpper(Integer.parseInt(str[1] + ""));   
		        }   
		    }   
		}
		
		/**
		 * 
		 * 功能: 将数字转化为大写   如果小于10 加零
		 * <p>%方法详述（简单方法可不必）%</p>
		 * @param num
		 * @return
		 *
		 * @author 
		 * @since 2014-9-5下午4:18:03
		 */
		public static String numToUpper2(int num) {
			String strNum = String.valueOf(num);
		    String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};   
//		    String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		    if(num<10){
		    	strNum = "0"+strNum;
			}
		    char[] str = strNum.toCharArray();   
		    String rstr = "";   
		    for (int i = 0; i < str.length; i++) {   
		        rstr = rstr + u[Integer.parseInt(str[i] + "")];   
		    }   
		    return rstr;   
		}  
		
		public static Date DateFormat(String date) throws ParseException {
		    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		    Date s = sf.parse(date);
		    return s;
		  }

		public static String DateFormatType(String date) throws ParseException {
		    Date sd = DateFormat(date);
		    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		    String s = sf.format(sd);
		    return s;
		}
}

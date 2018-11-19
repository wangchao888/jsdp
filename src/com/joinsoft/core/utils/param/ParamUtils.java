package com.joinsoft.core.utils.param;

import javax.servlet.http.HttpServletRequest;

public class ParamUtils {

    /**
     * 
     * 功能: 返回paramName的参数的值。
     * 
     * @param request
     * @param paramName       参数名称
     * @param emptyStringsOK  开关变量   如果为false 当返回值为""时，返回null。为true时返回"".
     * @return
     *
     * 作者：Exodus
     */
    public static String getParameter(HttpServletRequest request, String paramName,
            boolean emptyStringsOK) {
        String temp = request.getParameter(paramName);
        if (temp != null) {
            if (temp.equals("") && !emptyStringsOK) {
                return null;
            } else {
                return temp;
            }
        } else {
            return null;
        }
    }

    /**
     * 
     * 功能: 返回paramName的参数的值，如果值为"",则返回null
     * 
     * @param request
     * @param paramName
     * @return
     *
     * 作者：Exodus
     */
    public static String getParameter(HttpServletRequest request, String paramName) {
        return getParameter(request, paramName, false);
    }

    /**
     * 
     * 功能: 返回paramName的参数的值。如果值为空,则返回默认值
     * 
     * @param request
     * @param paramName
     * @param defaultValue  默认值
     * @return
     *
     * 作者：Exodus
     */
    public static String getParameter(HttpServletRequest request, String paramName,
            String defaultValue) {
        String temp = request.getParameter(paramName);
        if (temp != null) {
            return temp;
        } else {
            return defaultValue;
        }
    }

    /**
     * 
     * 功能: 如果返回值为字符串true，则返回boolean类型true
     * 
     * @param request
     * @param paramName
     * @return
     *
     * 作者：
     */
    public static boolean getBooleanParameter(HttpServletRequest request, String paramName) {
        String temp = request.getParameter(paramName);
        if (temp != null && temp.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * 功能: 得到paramName的值，把值转变为int类型，如果无法转变使用默认值defaultNum
     * 
     * @param request
     * @param paramName
     * @param defaultNum
     * @return
     *
     * 作者：
     */
    public static int getIntParameter(HttpServletRequest request, String paramName, int defaultNum) {
        String temp = request.getParameter(paramName);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
                ignored.getMessage();
            }
            return num;
        } else {
            return defaultNum;
        }
    }
    
    
    public static double getDoubleParameter(HttpServletRequest request, String paramName, double defaultNum) {
        String temp = request.getParameter(paramName);
        if (temp != null && !temp.equals("")) {
            double num = defaultNum;
            try {
                num = Double.parseDouble(temp);
            } catch (Exception ignored) {
                ignored.getMessage();
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    

}

package com.joinsoft.core.utils.page;

/**
 * 文   件  名   : PageTools.java
 * 版          本   : 
 * 创建日期   : 2013-1-10
 *
 * Copyright © 1995-2013 JoinSoft Co.Ltd. All right reserved. 
 */



import javax.servlet.http.HttpServletRequest;

import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.param.ParamUtils;


/**
 * 
 * 功能: 获取页面page信息
 *
 * 作者：Exodus
 *
 * 版本：
 */
public class PageTools {

    /**
     * 
     * 功能: 获取request中ui界面的page参数
     * 
     * @param request
     * @return
     *
     * 作者：Exodus
     */
    public static Page getPageByParam(HttpServletRequest request) {
        String pageNo = ParamUtils.getParameter(request, "pager.pageNo", "1");      /* 当前页 */
        //String JqgridPageNo = ParamUtils.getParameter(request, "page");				/* jqgrid当前页 */	
        String pageSize = ParamUtils.getParameter(request, "pager.pageSize", "10"); /* 页面记录数 */
        String sort = ParamUtils.getParameter(request, "sort", "");                 /* 排序条件 */
        String direction = ParamUtils.getParameter(request, "direction", "");       /* 排序方向 */
        /*datatables 使用分页参数*/
        String start = ParamUtils.getParameter(request, "start", "1");              /* 第一条数据的起始位置，比如0代表第一条数据 */
        String length = ParamUtils.getParameter(request, "length", "10");           /* 服务器每页显示的条数 */
        String draw = ParamUtils.getParameter(request, "draw", "");
        System.out.println("第一条数据的起始位置: " + start);
        System.out.println("服务器每页显示的条数: " + length);
        System.out.println("draw: " + draw);
        Page page = new Page();
        page.setCurrentPage(Integer.parseInt(pageNo));
        if(start != null && !"".equals(start)){
//        	System.out.println((Integer.parseInt(start)/Integer.parseInt(length))+1);
        	page.setCurrentPage((Integer.parseInt(start)/Integer.parseInt(length))+1);
        	pageSize = length;
        }
        /*jqgrid 分页使用注释*/
        /*if(JqgridPageNo != null && JqgridPageNo != ""){
        page.setCurrentPage(Integer.parseInt(JqgridPageNo));	
        }*/
        page.setShowCount(Integer.parseInt(pageSize));
        page.setOrderBy(sort + direction);
        return page;
    }
    
}

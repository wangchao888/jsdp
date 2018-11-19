<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
  <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                <li class="site-menu-category">开发平台</li>
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">系统管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">                            
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/application/list" menuid="yygl">应用管理</a>
                            </li>                                                
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/dept/tree" menuid="jggl">机构管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/role/list" menuid="jsgl">角色管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/menu/list" menuid="cdgl">菜单管理</a>
                            </li>                                                 
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/dictype/list" menuid="dic">字典管理</a>

                            </li> 
                            <li>
                                <a class="J_menuItem" href="${ctx}/system/param/list" menuid="param">系统参数</a>
                            </li>                                                 

                            </li>                                                                        

                        </ul>

                    </li>                   
                </ul>
            </div>
        </nav>
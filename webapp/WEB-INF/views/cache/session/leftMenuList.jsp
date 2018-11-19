<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
</head>

<body class="gray-bg">
  <div class="col-xs-12">
    <div  class="tabs-container">
	  <div  class="tab-pane">
        <div style="width: 100%;">
		<div class="Tabular_frame">
          <table class="table dataTable table-bordered table-hover "  cellspacing="0" width="100%">
		    <thead>
              <tr>
           	    <th class="width_44">序号</th>
           	    <th>主键</th>
                <th>菜单名称</th>
                <th>菜单链接</th>
                <th>菜单序号</th>
             </tr>
  		   </thead>
  		   <!-- 内容 开始 -->
           <tbody>
             <c:forEach items="${leftMenuList}" var="leftMenu" varStatus="vs"> 								 
			   <tr>
			     <td style="text-align:center">${vs.index+1 }</td>
			  	 <td>${leftMenu.sid}</td>
                 <td>${leftMenu.menuname }</td>
                 <td>${leftMenu.menuurl }</td>
                 <td>${leftMenu.menuno }</td>
               </tr>																				
             </c:forEach>  
		  </tbody>
		  <!-- 内容 结束 -->
        </table>
         </div>
          </div>
    </div>
  </div>
 </div>
 
 <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
 <script type="text/javascript" src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
</head>

<body class="white-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
  <div class="col-xs-12">
    <div  class="tabs-container">
<!-- 	  <div class="ibox-title"> -->
<!-- 	    <h3>字典内容列表</h3> -->
<!--       </div> -->
	  <div  class="tab-pane">
        <div class="panel-body" style="height:100%;">
           <table class="table table-bordered table-hover"  cellspacing="0" width="100%" >
    	     <thead>
               <tr>
           	     <th>序号</th>
           	     <th>主键</th>
           	     <th>字典类型类别</th>
                 <th>字典标签</th>
	             <th>字典值</th>		
	             <th>排序号</th>					                          
               </tr>
      		 </thead>
      		 <!-- 内容 开始 -->
        	 <tbody>
               <c:forEach items="${dictContentList}" var="dictContent" varStatus="vs"> 								 
		         <tr>
		  	       <td>${vs.index+1 }</td>
		  	       <td>${dictContent.sid}</td>
                   <td>${dictContent.dictno}</td>
                   <td>${dictContent.dictlabel}</td>
                   <td>${dictContent.dictvalue}</td>
                   <td>${dictContent.sortno}</td>
                 </tr>																				
              </c:forEach>  
			 </tbody>
			 <!-- 内容  结束 -->
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

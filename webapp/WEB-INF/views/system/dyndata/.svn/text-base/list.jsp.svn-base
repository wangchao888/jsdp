<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <!-- Data Tables -->
    
</head>

<body class="white-bg">
	<input type="hidden" name="extid" id="extid" value="${extid }"/>
	<input type="hidden" name="keyname" id="keyname" value="${keyname }"/>
	<input type="hidden" name="menuid" id="menuid" value="${menuid }"/>
	
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-title">
              <h5>数据列表</h5>
            </div>
            <div class="ibox-content padding0">
              <div class="dataTables_filter fr">
                <button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button><br><br>             
              </div>   
              <!-- 表格开始 -->
              <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-xs-12">
                    <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                      <thead>                   
                       <tr>                      
                       <c:forEach items="${headerList}" var="obj" varStatus="status"> 
                           <c:if test="${obj.isdisp=='1'}">               	  		                   	  	          
	                   	  		<th width="${obj.width}px">${obj.display }</th>
	                   	   </c:if>                  	  	                   	  	
		               </c:forEach>
		            	<th>操作</th>
                       </tr>
                      </thead>
                      <tbody>                 		              
		            	<c:forEach items="${dataList}" var="objdata" varStatus="status">
							<tr class="gradeC"> 
			            	<c:forEach items="${headerList}" var="obj" varStatus="status"> 
			            		<c:if test="${obj.isdisp=='1'}">               	  		                   	  	          
	                   	  	    	<td>${objdata[obj.name] }</td> 
	                   	  	    </c:if>    	  			                   	  	          	                     	  	          	  	                   	  	                 	  			            			            			            	
			            	</c:forEach>			            	
			            	 <td>
			            	 	<button class="operation_primary" type="button" 
			            	 	onclick="editEntity('${objdata.KEYID }');">修改</button>
			            	 	
								<button class="operation_primary" type="button"
								 onclick="delEntity('${objdata.KEYID }');">删除</button>
							</td>		          
		            		</tr>
		            	</c:forEach>		            	
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <!-- 表格结束 -->
              <!-- 分页开始 -->
              ${pagehtml }
              <!-- 分页结束 -->
            </div>
          </div>
        </div>
      </div>
    </div>
    
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    
    <script type="text/javascript">    
    //iframe的url
    function addEntity(){
    	var extid = $("#extid").val();
    	layer.open({
    	    type: 2,
    	    title: false,
    	    id:'layer_addAppEntity',
    	    shadeClose: true,
    	    shade: 0.8,
    	    closeBtn:true,
    	    area: ['50%', '75%'], //width height
    	    content: '${ctx}/system/dyndata/initAdd?extid='+extid
    	});
    }    
    
    function editEntity(keyid){
    	var extid = $("#extid").val();
    	layer.open({
    	    type: 2,
    	    title: false,
    	    shadeClose: true,
    	    shade: 0.8,
    	    area: ['50%', '75%'],
    	    content: '${ctx}/system/dyndata/initUpdate?keyid='+keyid+'&extid='+extid //iframe的url
    	}); 
    }
    
    function delEntity(id){
    	var extid = $("#extid").val();
    	var keyname = $("#keyname").val();
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/dyndata/delete",//提交地址
                data:{id:id,extid:extid,keyname:keyname},
                type:"get",
                dataType:"json",
                success:function(result){
                    if (result.retFlag == 'T'){
                    	parent.layer.confirm(result.retMsg, {
                    	    btn: ['确定'], //按钮
                    	    shade: false //不显示遮罩
                    	}, function(){
                    		parent.layer.closeAll();
                    		refresh();
                    	});
                    	
                    }else{
                    	parent.layer.alert(result.retMsg);
                    }
                }
        	})
    	});
    	
    }
  	
    function refresh(){
    	window.location="${ctx}/system/dyndata/list?menuid=${menuid}&extid=${extid}";
    }
        
    </script>
</body>
</html>

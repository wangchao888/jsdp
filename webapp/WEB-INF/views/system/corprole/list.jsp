<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-content padding0">
				<div style="width: 100%;">
				<div class="Table_title_box">
					单位角色
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table class="table dataTable table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>                   
                       <tr>    
                       	<th class="width_44">序号</th>
                       	<th>单位类型</th> 
                       	<th>角色名称</th>   
                       	<th class="width_100">操作</th>              
                       </tr>
                      </thead>
                      <tbody> 
                          <c:forEach items="${dictContentList}" var="dic" varStatus="vs">
                        <tr>
			            	 <td style="text-align:center;width:44px">${vs.index+1 }</td>
			            	 <td>${dic.DICTVALUE }</td>
			            	 <td>${dataMap[dic.DICTLABEL] }</td>
	            	 		 <td style="text-align:center;width:100px">
	            	 		 	<a class="operation_primary" type="button" onclick="editEntity('${dic.DICTLABEL }');">编辑</a>
	            	 			<a class="operation_primary" type="button" onclick="delEntity('${dic.DICTLABEL}');">删除</a>
	            	 		 </td>
		                </tr>
                          </c:forEach>  
                          	<c:if test="${empty dictContentList }">
						        <td align="center" colspan="4">
						        	没有可显示的数据
						        </td>
					        </c:if>              		              
                      </tbody>
               </table>
               </div>
               </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="loading_bg">
    	<div class="loading_box">
       		 <img src="${ctx}/static/img/wait.png"/>
             <h3 style="color: #fff;">loading...</h3>
  		</div>
    </div>
    
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    
    <script type="text/javascript">
    
    function addEntity(){
    	if ("${flag}" == "1") {
    		layer.alert("已有全部角色！");
    		return false;
    	}
    	layer.open({ 
    		type: 2, 
    		title:'单位赋角色',
    		id:'layer_addParam',
    		area: ['50%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '取消'],
    	    yes: function(index, layero){
    	      document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
    	    	layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/corprole/initAdd?dictlabelCorp='+"${dictlabelCorp}"
        });
    }        
    
    function editEntity(corptype){
    	layer.open({ 
    		type: 2, 
    		title:'单位赋角色',
    		id:'layer_addParam',
    		area: ['50%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '取消'],
    	    yes: function(index, layero){
    	      document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
    	    	layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/corprole/initUpdate?corptype='+corptype+'&dictlabelCorp='+"${dictlabelCorp}"
        });
    }        
    function delEntity(corptype){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/corprole/delete",//提交地址
                data:"corptype="+corptype,//将表单数据序列化
                type:"get",
                dataType:"json",
                success:function(result){
                    if (result.retFlag == 'T'){
                    	layer.confirm(result.retMsg, {
                    	    btn: ['确定'], //按钮
                    	    shade: false //不显示遮罩
                    	}, function(){
                    		layer.closeAll();
                    		refresh();
                    	});
                    }else{
                    	layer.alert(result.retMsg);
                    }
                }
        	})
    	});
    	
    }
    
    function refresh(){
    	window.location.reload();
    }
    </script>
</body>
</html>

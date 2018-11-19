<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
		<%-- <%@ include file="/common/meta.jsp"%> --%>
	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-content padding0">
				<div style="width: 100%;">
				<div class="Table_title_box">
					字典内容列表
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
					<input type="hidden" name="dictno" id="dictno" value="${dictno}" />
					<button type="button" class="btn btn-danger btn-sm" onclick="returnSuper()">返回</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table id="datatable" class="table table-bordered table-hover">
                  <thead>
                     <tr>
                     	 <th id="0" name="center" class="width_44">序号</th>
                         <th id="1" name="center" >字典类别名称</th>
                         <th id="2" name="center" >字典项代码</th>
                         <th id="3" name="left" >字典项含义</th>
                         <th id="4" name="center" >是否启用</th>
                         <th id="5" name="center" class="width_100">操作</th> 
                     </tr>
                 </thead>
               </table>
               </div>
               </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    
    <script type="text/javascript">
    var table;
    $(document).ready(function(){
    	// 默认禁用搜索和排序
    	$.extend( $.fn.dataTable.defaults, {
    	    searching: false
    	} );
    	var dictno = $("#dictno").val();
    	table = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/dictcontent/show?dictno='+dictno,
    	        type: 'POST'
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<6; i++){
    	    		var align = $('#'+i).attr("name");
    	    	    $(row).children('td').eq(i).attr('style', 'text-align:' +align);
    	    	}
    	    	    	    	 
            },
    	    "columns": [
						{"data": null,//定义空的data
							render : function(data, type, row, meta) {  
							//行号递加 
							var startIndex = meta.settings._iDisplayStart;  
							return startIndex + meta.row + 1;  
							}  	
						},
    	                { "data": "DICTNAME" },
    	                { "data": "DICTLABEL" },
    	                { "data": "DICTVALUE" },
    	                { "data": "STATEV" },
    	                { "data": "SID",
    	                    "render": function (data, type, row, meta) {
    	                      var ret = '<a class="operation_primary margin_right10" type="button" onclick="editEntity(\''+row.SID+'\')">修改</a>'+
    	    	  						'<a class="operation_primary " type="button" onclick="delEntity(\''+row.SID+'\',\''+row.DICTNO+'\')">删除</a>';
    	                     return ret;	  
    	                    }
    	                } 
    	               	
    	             ]
    	} );
   	
    });

       
    //iframe的url
    function addEntity(){
    	var dictno = $("#dictno").val();
    	layer.open({ 
    		type: 2, 
    		title:'字典内容新增',
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
    	    content: '${ctx}/system/dictcontent/initAdd?dictno='+dictno
        });
    }    
    //修改
    function editEntity(data){
    	layer.open({ 
    		type: 2, 
    		title:'字典内容修改',
    		id:'layer_editParam',
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
    	    content: '${ctx}/system/dictcontent/initUpdate?sid='+data
        });
    } 
    //删除
    function delEntity(sid,dictno){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/dictcontent/delete",//提交地址
                data:{sid:sid,dictno:dictno},
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
        	});
    	});
    	
    } 
    
    function refresh(){
    	var dictno = $("#dictno").val();
    	table.api().ajax.reload();
//     	window.location="${ctx}/system/dictcontent/list?dictno="+dictno+"&menuid=${menuid}";
    }
    /* 返回上级 */
    function returnSuper(){
    	window.location.replace(document.referrer)
    }  
    </script>
</body>
</html>

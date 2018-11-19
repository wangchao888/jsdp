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
              <div class="dataTables_top">
              		  <input type="search" class="form-control input-sm input_big_display" placeholder="输入角色名称" name="srolename" id="srolename">
              		  <span class="Search_reset_box">
  						<button type="button" class="btn btn-primary btn-sm " onclick="searchRole()">搜索</button>
					  </span> 
			  </div>
			  <div style="width: 100%;">
			<div class="Table_title_box">
				角色列表
				<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntity()">新增</button>
				</div>
			</div>
			<div class="Tabular_frame">
              <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                 <thead>
                     <tr>
                         <th id="0" name="center" class="width_44">序号</th>
                         <th id="1" name="left" >角色名称</th>
                         <th id="2" name="center" >角色状态</th>
                         <th id="3" name="left" >角色说明</th>
                         <th id="4" name="left" >所属机构</th>
                         <th id="5" name="center" class="width_150">操作</th>
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
    <div class="loading_bg">
    	<div class="loading_box">
       		 <img src="${ctx}/static/img/wait.png"/>
             <h3 style="color: #fff;">loading...</h3>
  		</div>
    </div>
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    <script src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
    
    <script type="text/javascript">
    var table;
    $(document).ready(function(){
    	// 默认禁用搜索和排序
    	$.extend( $.fn.dataTable.defaults, {
    	    searching: false
    	} );
    	
    	table = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/role/show',
    	        type: 'POST',
    	        data: function(d){
    	        	d.rolename = $("#srolename").val();
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<6; i++){
    	    		var align = $('#'+i).attr("name");
    	    	    $(row).children('td').eq(i).attr('style', 'text-align:' +align);
    	    	}
    	    	    	    	 
            },  
    	    "columns":[
				{"data": null,
					render : function(data, type, row, meta) {  
	                    // 显示行号  
	                    var startIndex = meta.settings._iDisplayStart;  
	                    return startIndex + meta.row + 1;  
	                }  	
				},       			
                {"data":"ROLENAME"},
                {"data":"DELFLAGS"}, 
                {"data":"ROLEDESC"},
                {"data":"ORGNAME"},
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary margin_right10" type="a" onclick="editEntity(\''+row.SID+'\');">编辑</a>'
                		  +'<a class="operation_primary margin_right10" type="a" onclick="delEntity(\''+row.SID+'\');">删除</a>'
                		  +'<a class="operation_primary " type="a" onclick="autho(\''+row.SID+'\');">授权</a>';
                		return ret;
                	}
                }
            ]
    	} );
      });
    
      function addEntity(){
    	layer.open({ 
    		type: 2, 
    		title:'新增角色',
    		id:'layer_addParam',
    		area: ['35%', '50%'],
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
    	    content: '${ctx}/system/role/initAdd' 
        });
	 };
     function editEntity(id){
    	layer.open({ 
    		type: 2, 
    		title:'修改角色',
    		id:'layer_editParam',
    		area: ['35%', '50%'],
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
    	    content: '${ctx}/system/role/initUpdate?sid='+id 
        });
     };
     
    function delEntity(id){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/role/delete",//提交地址
                data:"sid="+id,//将表单数据序列化
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
//     function autho(id){
//     	layer.open({
//     	    type: 2,
//     	    title: false,
//     	    shadeClose: true,
//     	    shade: 0.8,
//     	    area: ['50%', '85%'],
//     	    content: '${ctx}/system/role/initautho?sid='+id //iframe的url
//     	});  	
//      };
     function autho(id){
     	layer.open({ 
     		type: 2, 
     		title:'角色授权',
     		id:'lay1212d3ram',
     		area: ['30%', '85%'],
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
     	   content: '${ctx}/system/role/initautho?sid='+id //iframe的url
         });
 	 };
     
     function searchRole(){
    	 table.api().ajax.reload();
     }
     
    //刷新页面
     function refresh(){
    	 table.api().ajax.reload();
//     	window.location="${ctx}/system/role/list?menuid=${menuid}";
    }
        
    </script>
</body>
</html>

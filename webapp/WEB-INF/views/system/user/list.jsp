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
            		  <input type="search" class="form-control input-sm input_big_display" placeholder="输入登录名" name="loginname" id="loginname">
            		  <input type="search" class="form-control input-sm input_big_display" placeholder="输入姓名" name="username" id="username">
              		  <span class="Search_reset_box">
  <button type="button" class="btn btn-primary btn-sm " onclick="searchUser()">搜索</button>
						</span> 
				</div>
				<div style="width: 100%;">
				<div class="Table_title_box">
					用户列表
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm" onclick="addEntity()">新增</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                           <th id="0" name="center" class="width_44">序号 </th>
                           <th id="1" name="center">登录名</th>
                           <th id="2" name="left">姓名</th>
                           <th id="3" name="center">状态</th>
                           <th id="4" name="center"class="width_300">操作</th>
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
    	table = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		"ajax": {
    	        url: '${ctx}/system/user/show',
    	        type: 'POST',
    	        data: function(d){
    	        	d.orgid = '${orgid}';
    	        	d.loginname = $("#loginname").val();
    	        	d.username = $("#username").val();
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<5; i++){
    	    		var align = $('#'+i).attr("name");
    	    	    $(row).children('td').eq(i).attr('style', 'text-align:' +align);
    	    	}
    	    	    	    	 
            },   
    	    "columns":[
				{"data": null,//定义空的data
					render : function(data, type, row, meta) {  
					//行号递加 
					var startIndex = meta.settings._iDisplayStart;  
					return startIndex + meta.row + 1;  
					}  	
				},
                {"data":"LOGINNAME"},
                {"data":"USERNAME"},
                {
                	"data":"STATE",
                	"render": function(data, type, row, meta) {
                		if(data == '0'){
                			return "停用";
                		}else{
                			return "启用";
                		}
                	} 
                }, 
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary margin_right10" type="a" onclick="editEntity(\''+row.SID+'\');">编辑</a>'
                		  +'<a class="operation_primary margin_right10" type="a" onclick="delEntity(\''+row.SID+'\');">删除</a>'
                		  +'<a class="operation_primary margin_right10" type="a" onclick="roleEntity(\''+row.SID+'\');">角色</a>'
                		  +'<a class="operation_primary margin_right10" type="a" onclick="resetPsswd(\''+row.SID+'\');">重置密码</a>'
                		  if(row.STATE == '0'){
                			  ret +='<a class="operation_primary " type="a" onclick="updatState(\''+row.SID+'\',\''+row.STATE+'\');">启用</a>';
                		  }
                		  if(row.STATE == '1'){
                			  ret +='<a class="operation_primary " type="a" onclick="updatState(\''+row.SID+'\',\''+row.STATE+'\');">停用</a>';
                		  }
                		return ret;
                	}
                }
            ]
    	} );
    });
    //搜索
    function searchUser(){
	   	 table.api().ajax.reload();
	}
    //iframe的url
    function addEntity(){
    	parent.layer.open({ 
    		type: 2, 
    		title:'用户新增',
    		id:'layer_addParam',
    		area: ['50%', '50%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '取消'],
    	    yes: function(index, layero){
    	    	parent.document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
    	    	parent.layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			parent.layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/user/initAdd?orgid=${orgid}'
        });
    }    
    
    function editEntity(sid){
    	parent.layer.open({ 
    		type: 2, 
    		title:'用户修改',
    		id:'layer_editParam',
    		area: ['50%', '50%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '取消'],
    	    yes: function(index, layero){
    	    	parent.document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
    	    	parent.layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			parent.layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/user/initUpdate?sid='+sid
        });
    }
    
    
    function delEntity(sid){
    	var orgid = "${orgid}";
    	parent.layer.confirm('该操作将用户对应的角色信息一并删除，是否删除？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/user/delete",//提交地址
                data:"sid="+sid,//将表单数据序列化
                type:"get",
                dataType:"json",
                success:function(result){
                    if (result.retFlag == 'T'){
                    	parent.layer.confirm(result.retMsg, {
                    	    btn: ['确定'], //按钮
                    	    shade: false //不显示遮罩
                    	}, function(){
                    		refreshList(orgid)
                    	});
                    	
                    }else{
                    	parent.layer.alert(result.retMsg);
                    }
                }
        	})
    	});
    }
    
    function updatState(sid,state){
    	var orgid = "${orgid}";
    	parent.layer.confirm('确定改变状态吗？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/user/updateState",//提交地址
                data:{sid:sid,state:state},
                type:"get",
                dataType:"json",
                success:function(result){
                    if (result.retFlag == 'T'){
                    	parent.layer.confirm(result.retMsg, {
                    	    btn: ['确定'], //按钮
                    	    shade: false //不显示遮罩
                    	}, function(){
                    		refreshList(orgid);
                    	});
                    	
                    }else{
                    	parent.layer.alert(result.retMsg);
                    }
                }
        	});
    	});
    }
    function resetPsswd(sid){
    	var orgid = "${orgid}";
    	parent.layer.confirm('确定重置密码？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/user/resetPsswd",//提交地址
                data:{sid:sid},
                type:"get",
                dataType:"json",
                success:function(result){
                    if (result.retFlag == 'T'){
                    	parent.layer.confirm(result.retMsg, {
                    	    btn: ['确定'], //按钮
                    	    shade: false //不显示遮罩
                    	}, function(){
                    		refreshList(orgid);
                    	});
                    	
                    }else{
                    	parent.layer.alert(result.retMsg);
                    }
                }
        	});
    	});
    }

    function roleEntity(sid){
    	parent.layer.open({ 
    		type: 2, 
    		title:'用户角色授权',
    		id:'layer_editParam',
    		area: ['50%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '取消'],
    	    yes: function(index, layero){
    	    	parent.document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
    	    	parent.layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			parent.layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/userrole/initrole?sid='+sid
        });
    }
    
    
  //刷新父页面列表
	function refreshList(orgid){
	  	//关闭窗口
	  	var doc = window.parent;
	  	doc.refreshUserList(orgid);
	  	parent.layer.closeAll();
	}
   //刷新右侧用户信息列表，用户信息维护弹出框使用该方法
	function refreshUserList(orgid){
 		$("#deptLoad").attr("src","${ctx}/system/user/list?orgid="+orgid);
	}
	
    </script>
</body>
</html>

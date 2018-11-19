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
              		  <input type="search" class="form-control input-sm input_big_display" placeholder="输入通知标题" name="ntctitle" id="ntctitle">
              		  <input type="search" class="form-control input-sm input_big_display" data-toggle="date" placeholder="输入通知日期" name="releasedate" id="releasedate">
              		  <span class="Search_reset_box">
  <button type="button" class="btn btn-primary btn-sm " onclick="searchRole()">搜索</button>
						</span>
			  </div>
			<div style="width: 100%;">
			<div class="Table_title_box">
				消息通知
				<div style="float: right">
				<button type="button" class="btn btn-primary btn-sm" onclick="addEntity()">新增</button>
				</div>
			</div>
			<div class="Tabular_frame">			  
              <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                 <thead>
                     <tr>
                         <th id="0" name="center" class="width_44">序号</th>
                         <th id="1" name="left" >通知标题</th>
                         <th id="2" name="center" >接收用户类型</th>
                         <th id="3" name="left" >通知类型</th>
                         <th id="4" name="center" >通知日期</th>
                         <th id="5" name="center" >消息状态</th>
                         <th id="6" name="center" class="width_250">操作</th>
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
    <script src="${ctx}/static/js/verificationDate.js"></script>
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
    	        url: '${ctx}/system/notice/infoShow',
    	        type: 'POST',
    	        data: function(d){
    	        	d.ntctitle = $("#ntctitle").val();
    	        	d.releasedate = $("#releasedate").val();
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<7; i++){
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
                {"data":"NTCTITLE"},
                {"data":"USERTYPE",
	               	"render": function(data, type, row, meta){
	               		var ret = "";
	               		if(data == "00"){ret = "所有用户";}
	               		if(data == "01"){ret = "主管用户";}
	               		if(data == "02"){ret = "企业用户";}
	               		if(data == "03"){ret = "银行用户";}
	               		return ret;
	               	}
                }, 
                {"data":"NTCTYPE",
                	"render": function(data, type, row, meta){
                   		var ret = "";
                   		if(data == "01"){ret = "信息公告";}
                   		if(data == "02"){ret = "政策法规";}
                   		if(data == "03"){ret = "消息通知";}
                   		if(data == "04"){ret = "业务变更";}
                   		return ret;
                   	}
                },
                {"data":"RELEASEDATE"},
                {"data":"STATE",
                	"render": function(data, type, row, meta){
                   		var ret = "";
                   		if(data == "0"){ret = "待发送";}
                   		if(data == "1"){ret = "已发送";}
                   		return ret;
                   	}
                },
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = "";
                		if(row.STATE == '0'){
                			 ret = '<a class="operation_primary margin_right10" type="a" onclick="editEntity(\''+row.SID+'\',\''+row.STATE+'\');">编辑</a>'
                  		  +'<a class="operation_primary margin_right10" type="a" onclick="delEntity(\''+row.SID+'\',\''+row.STATE+'\');">删除</a>'
                  		  +'<a class="operation_primary margin_right10" type="a" onclick="details(\''+row.SID+'\');">详情</a>'
                  		  +'<a class="operation_primary " type="a" onclick="sendMessage(\''+row.SID+'\');">发送消息</a>';
                		}else{
                	         ret = '<a class="operation_danger margin_right10" type="a" title="消息已成功发送，请先撤销发送在修改！" disabled >编辑</a>'
                  		  +'<a class="operation_danger margin_right10" type="a" title="消息已成功发送，请先撤销发送在删除！" disabled>删除</a>'
                  		  +'<a class="operation_primary margin_right10" type="a" onclick="details(\''+row.SID+'\');">详情</a>'
                  		  +'<a class="operation_primary " type="a" onclick="undoMessage(\''+row.SID+'\');">撤销发送</a>';
                		}
                		
                		return ret;
                	}
                }
            ]
    	} );
      });
    
      function addEntity(){
    	layer.open({ 
    		type: 2, 
    		title:'新增通知',
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
    	    content: '${ctx}/system/notice/infoInitAdd' 
        });
	 };
     function editEntity(sid,state){
    	layer.open({ 
    		type: 2, 
    		title:'修改通知',
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
    	    content: '${ctx}/system/notice/infoInitEdit?sid='+sid
        });
     };
     
    function delEntity(id,state){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/notice/delete",//提交地址
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
    function details(sid){
    	layer.open({ 
    		type: 2, 
    		title:'详情显示',
    		id:'layer_addParam',
    		area: ['50%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    		content: '${ctx}/system/notice/details?sid='+sid
    	});	
     };
     function searchRole(){
    	 table.api().ajax.reload();
     }
     
    //刷新页面
     function refresh(){
    	 table.api().ajax.reload();
    }
    //发送
    function sendMessage(id){
    	layer.confirm('确定发送消息吗？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/notice/sendMessage",//提交地址
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
   //撤销发送
   function undoMessage(id){
   	layer.confirm('确定撤销已发送的消息吗？', {
   	    btn: ['确定','取消'], //按钮
   	    shade: false //不显示遮罩
   	}, function(){
   		$.ajax({
               url:"${ctx}/system/notice/undoMessage",//提交地址
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
        
    </script>
</body>
</html>

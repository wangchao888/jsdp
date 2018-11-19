<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
    <!-- Data Tables -->
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-content padding0">
					<div style="width: 100%;">
					<div class="Table_title_box">
					应用管理
						<div style="float: right">
						<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
						</div>
					</div>
					<div class="Tabular_frame">
                <table class="table table-bordered table-hover" id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                       	   <th id="0" name="center" class="width_44" >序号</th>
                           <th id="1" name="left"  >应用名称</th>
                           <th id="2" name="right"  >IP</th>
                           <th id="3" name="right"  >域名</th>
                           <th id="4" name="center"  >端口</th>
                           <th id="5" name="left"  >视图</th>
                           <th id="6" name="left"  >应用说明</th>
                           <th id="7" name="center" class="width_100">操作</th>
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
    <script type="text/javascript">
    var table;
    $(document).ready(function(){
    	// 默认禁用搜索和排序
    	$.extend( $.fn.dataTable.defaults, {
    	    searching: false
    	} );
    	/* 注意 $('#datatable').DataTable 和 $('#datatable').dataTable 是有区别的 刷新表的方法有区别 */
    	table = $('#datatable').dataTable( { //1.10版本以后的
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/application/show',
    	        type: 'POST',
    	        data: function(d){
    	        	//d.orgid = '${orgid}';
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<8; i++){
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
				{"data":"APPNAME"},
                {"data":"APPIP"},
                {"data":"DOMAIN"},
                {"data":"APPPORT"},
                {"data":"WEBVIEW"},
                {"data":"APPDESC"},
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary margin_right10"  onclick="editEntity(\''+row.SID+'\');">编辑</a>'
                		  +'<a class="operation_primary "  onclick="delEntity(\''+row.SID+'\');">删除</a>';
                		return ret;
                	} 
                }
            ]
    	} );
    });
    
    //iframe的url
    function addEntity(){
    	layer.open({ 
    		type: 2, 
    		title:'应用增加',
    		id:'layer_addParam',
    		area: ['35%', '75%'],
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
    	    content: '${ctx}/system/application/initAdd'
        });
    }    
    
    function editEntity(sid){
    	layer.open({ 
    		type: 2, 
    		title:'应用修改',
    		id:'layer_editParam',
    		area: ['35%', '75%'],
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
    	    content: '${ctx}/system/application/initUpdate?sid='+sid
        });
    }
    
    function delEntity(sid){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/application/delete",//提交地址
                data:"sid="+sid,//将表单数据序列化
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
    /*
    * 改为表格刷新数据，最开始使用window.location 页面刷新效果太明显，所以使用表格自带的异步刷新
    * 注意表格版本不同刷新写法不同 
    */
    function refresh(){
    	//window.location="${ctx}/system/application/list?menuid=${menuid}";
    	table.api().ajax.reload(); //早期版本 1.10以前 对应 $('#datatable').dataTable()
    	//table.ajax.reload(); //1.10版本以后的
    }
        
    </script>
</body>
</html>

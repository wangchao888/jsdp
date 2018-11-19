<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
    <!-- Data Tables -->
    
</head>

<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-content padding0">    
				<div style="width: 100%;">
				<div class="Table_title_box">
					系统信息
					<div style="float: right">
					</div>
				</div>
				<div class="Tabular_frame">                    	
                <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                       	   <th id="0" name="center" class="width_44" >序号</th>
                           <th id="1" name="left"  >系统名称</th>
                           <th id="2" name="right"  >图片名称</th>
                           <th id="3" name="right"  >图片路径</th>
                           <th id="4" name="center"  >测试环境系统标识</th>
                           <th id="5" name="center" class="width_44" >操作</th>
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
    	        url: '${ctx}/system/sysinfo/show',
    	        type: 'POST',
    	        data: function(d){
    	        	//d.orgid = '${orgid}';
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<6; i++){
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
				{"data":"SYSNAME"},
                {"data":"SYSICO"},
                {"data":"ICOPATH"},
                {"data":"TESTFLAG",
                	"render": function(data, type, row, meta){
                		var ret = '';
                		if (data == '0'){ret = '否'};
                		if (data == '1'){ret = '是'};
                		return ret;
                	}
                },
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary" onclick="editEntity(\''+row.SID+'\');">编辑</a>'
                		return ret;
                	}
                }
            ]
    	} );
    });
    
    
    function editEntity(sid){
    	layer.open({ 
    		type: 2, 
    		title:'系统信息修改',
    		id:'layer_editParam',
    		area: ['50%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    		btn: ['确定', '下载图片','取消'],	
    	    btn1: function(index, layero){
    	      document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
    	    },  
    	    btn2: function(index, layero){
         	      document.getElementById("layui-layer-iframe"+index).contentWindow.downloadImg();
         	      return false;
         	},  
    	    btn3: function(index, layero){
    	    	layer.confirm('确定取消操作?', {
    	    		  btn: ['确定'] //按钮
    	    		}, function(){
    	    			layer.closeAll();
    	    		});
    		    return false;
    	    },
    	    content: '${ctx}/system/sysinfo/initUpdate?sid='+sid //iframe的url
        });
    }
    function refresh(){
    	table.api().ajax.reload(); 
    }
        
    </script>
</body>
</html>

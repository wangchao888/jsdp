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
					系统参数列表
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                       	   <th id="0" name="center" class="width_44">序号</th>
                           <th id="1" name="center"   >参数编号</th>
                           <th id="2" name="left"   >参数值</th>
                           <th id="3" name="left"   >参数描述</th>
                           <th id="4" name="center"   >是否允许修改</th>
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
    	table = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		"ajax": {
    	        url: '${ctx}/system/param/show',
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
				{"data":"PARAMNO"},
                {"data":"PARAMVALUE"},
                {"data":"PARAMDESC"},
                {"data":"ISMODIFYNAME"},
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		if(row.ISMODIFYNAME == '否'){
                			ret = '<a class="operation_danger margin_right10" type="button"  title="当前状态不能编辑" disabled>编辑</a>'
                				+'<a class="operation_primary margin_right10" type="button" onclick="delEntity(\''+row.SID+'\');">删除</a>';
                		}else{
                			ret = '<a class="operation_primary margin_right10" type="button" onclick="editEntity(\''+row.SID+'\');">编辑</a>'
                    		  +'<a class="operation_primary margin_right10" type="button" onclick="delEntity(\''+row.SID+'\');">删除</a>';
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
    		title:'系统参数新增',
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
    	    content: '${ctx}/system/param/initAdd'
        });
    }    
    
    function editEntity(sid){
    	layer.open({ 
    		type: 2, 
    		title:'系统参数修改',
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
    	    content: '${ctx}/system/param/initUpdate?sid='+sid 
        });
    }
    
    function delEntity(sid){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/param/delete",//提交地址
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
    
    function refresh(){
//     	window.location="${ctx}/system/param/list?menuid=${menuid}";
    	table.api().ajax.reload();
    }
        
    </script>
</body>
</html>

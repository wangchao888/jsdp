<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
</head>

<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeIn padd0">
      <div class="row">
        <div class="col-xs-12">
          <div class="ibox float-e-margins">
            <div class="ibox-content padding0">
				<div style="width: 100%;">
				<div class="Table_title_box">
					字典类型列表
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table id="datatable" class="table table-bordered table-hover">
                  <thead>
                     <tr>
                     	 <th id="0" name="center" class="width_44">序号</th>
                         <th id="1" name="center" >字典标识</th>
                         <th id="2" name="left" >字典名称</th>
                         <th id="3" name="left" >字典描述</th>
                         <th id="4" name="center" class="width_150">明细项维护</th> 
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
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/dictype/show',
    	        type: 'POST'
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<5; i++){
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
    	                { "data": "DICTNO" },
    	                { "data": "DICTNAME" },
    	                { "data": "DICTDESC" },
    	                { "data": "SID",            
    	                  "render": function (data, type, row, meta) {
    	                  	var ret = '<a class="operation_primary margin_right10" type="a" onclick="editEntity(\''+row.SID+'\')"">编辑</a>'
    	        		  			 +'<a class="operation_primary margin_right10" type="a" onclick="delEntity(\''+row.SID+'\',\''+row.DICTNO+'\')">删除</a>'
    	        		  			 +'<a class="operation_primary " type="a" onclick="dictcon(\''+row.DICTNO+'\');">明细</a>';
    	                  	return ret;			
    	                   		  }
    	                } 
    	               	
    	             ]
    	} );
   	
    });
    function dictcon(data){
    	  window.location = "${ctx }/system/dictcontent/list?dictno="+data+"&menuid=${menuid}";  	
    }
    //添加 
    function addEntity(){    	
    	layer.open({ 
    		type: 2, 
    		title:'字典类型增加',
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
    	    content: '${ctx}/system/dictype/initAdd'
        });
    }    
    
    //修改
    function editEntity(sid){
    	layer.open({ 
    		type: 2, 
    		title:'字典类型修改',
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
    	    content: '${ctx}/system/dictype/initUpdate?sid='+sid
        });
    }
    //删除
    function delEntity(sid,dictno){
    	var str = "";
    	$.ajax({
            url:"${ctx}/system/dictype/validation",//提交地址
            data:"dictno="+dictno, //将表单数据序列化
            type:"get",
            async:false,
            dataType:"json",
            success:function(result){
            	if (result.retFlag == 'T'){
            		str = result.retMsg;                    	
                }else{
                	layer.alert(result.retMsg);
                }
            }
    	});
    	layer.confirm(str, {
    	    btn: ['确定','取消'], //按钮
    	    shade: false, //不显示遮罩
    	    yes: function(){
        		$.ajax({
                    url:"${ctx}/system/dictype/delete",//提交地址
                    data:{sid:sid,dictno:dictno},
                    type:"get",
                    dataType:"json",
                    success:function(result){
                    	$('.loading_bg').css('display','none');
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
    	    }
    	});
    	
    } 
    
    function refresh(){
    	table.api().ajax.reload();
    }   
    </script>
</body>
</html>

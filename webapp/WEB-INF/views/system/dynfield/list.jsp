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
              <input type="hidden" id="extid" value="${extid }"/>
              <div style="width: 100%;">
				<div class="Table_title_box">
					字段维护
					<div style="float: right">
					<button type="button" class="btn btn-primary btn-sm " onclick="addEntity()">新增</button>
					<button type="button" class="btn btn-danger btn-sm" onclick="returnSuper()">返回</button>
					</div>
				</div>
				<div class="Tabular_frame">
                <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                       	   <th id="0" name="center"  class="width_44">序号</th>
                           <th id="1" name="left"  >字段定义</th>
                           <th id="2" name="left"  >字段名</th>
                           <th id="3" name="right"  >显示顺序</th>
                           <th id="4" name="left"  >字段类型</th>
                           <th id="5" name="right"  >字段长度</th>
                           <th id="6" name="right"  >字段宽度</th>
                           <th id="7" name="center"  >是否主键</th>
                           <th id="8" name="center"  >是否显示</th>
                           <th id="9" name="center"  >是否重复</th>
                           <th id="10" name="center"  >是否补零</th>
                           <th id="11" name="center"  >是否为空</th>
                           <th id="12" name="center"  class="width_100">操作</th>
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
	var t;
    $(document).ready(function(){
    	var extid = $("#extid").val();
    	// 默认禁用搜索和排序
    	$.extend( $.fn.dataTable.defaults, {
    	    searching: false
    	} );
    	 t = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/dynfield/show?extid='+extid,
    	        type: 'POST',
    	        data: function(d){
    	        	//d.orgid = '${orgid}';
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<13; i++){
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
				{"data":"FIELDNAME"},
                {"data":"FIELDNAMECN"},
                {"data":"ORDERNO"},
                {"data":"FIELDTYPE"},
                {"data":"FIELDLEN"},
                {"data":"FIELDWIDTH"},
                {"data":"ISKEYV"},
                {"data":"ISDISPV"},
                {"data":"ISREPTV"},            
                {"data":"ISZEROV"},
                {"data":"ISNULLV"},
                {
                	"data":"ID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary margin_right10"  onclick="editEntity(\''+row.ID+'\');">编辑</a>'
                		  +'<a class="operation_primary "  onclick="delEntity(\''+row.ID+'\');">删除</a>';
                		return ret;
                	}
                }
            ]
    	} );
    });
    
    //iframe的url
    function addEntity(){
    	var id = $("#extid").val();
    	layer.open({ 
    		type: 2, 
    		title:'字段新增',
    		id:'layer_addParam',
    		area: ['75%', '75%'],
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
    	    content: '${ctx}/system/dynfield/initAdd?id='+id
        });
    }    
    
    function editEntity(id){
    	layer.open({ 
    		type: 2, 
    		title:'字段修改',
    		id:'layer_editParam',
    		area: ['75%', '75%'],
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
    	    content: '${ctx}/system/dynfield/initUpdate?id='+id 
        });
    }
    
    function delEntity(id){
    	layer.confirm('是否删除当前记录？', {
    	    btn: ['确定','取消'], //按钮
    	    shade: false //不显示遮罩
    	}, function(){
    		$.ajax({
                url:"${ctx}/system/dynfield/delete",//提交地址
                data:"id="+id,//将表单数据序列化
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
                    	parent.layer.alert("删除失败！");
                    }
                }
        	})
    	});
    	
    }
    
    function refresh(){
//     	var extid = $("#extid").val();
//     	window.location="${ctx}/system/dynfield/list?extid="+extid+"&menuid=${menuid}";
    	window.location.reload();
    }
    /* 返回上级 */
    function returnSuper(){
    	window.location.replace(document.referrer)
    }       
    </script>
</body>
</html>

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
              		  <input type="search" class="form-control input-sm input_big_display" placeholder="选择通知日期" name="releasedate" id="releasedate">
              		  <select class="form-control input-sm input_big_display" id="viewflag" name="viewflag">
              		      <option value="">全部消息</option>
	              		  <option value="1">已读消息</option>
	              		  <option value="0">未读消息</option>
              		  </select>
              		  <span class="Search_reset_box">
 						<button type="button" class="btn btn-primary btn-sm " onclick="searchRole()">搜索</button>
						</span>
			  </div>
			    <div style="width: 100%;">
				<div class="Tabular_frame">
              <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                 <thead>
                     <tr>
                         <th id="0" name="center" class="width_44">序号</th>
                         <th id="1" name="left" class="width_100">通知标题</th>
                         <th id="2" name="left" >通知内容</th>
                         <th id="3" name="center" class="width_100">通知日期</th>
                         <th id="4" name="center" class="width_100">是否查阅</th>
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
    		"bAutoWidth":false,
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/notice/allMessageShow',
    	        type: 'POST',
    	        data: function(d){
    	        	d.ntctitle = $("#ntctitle").val();
    	        	d.releasedate = $("#releasedate").val();
    	        	d.viewflag = $("#viewflag").val();
    	        	d.userid = "${userid}";
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
                {"data":"NTCTITLE"},
                {"data":"DESCRIBE"}, 
                {"data":"RELEASEDATE"},
                {"data":"VIEWFLAG",
                	"render": function(data, type, row, meta){
                   		var ret = "";
                   		if(data == "0"){ret = "未查阅";}
                   		if(data == "1"){ret = "已查阅";}
                   		return ret;
                   	}
                },
                {
                	"data":"SID",
                	"render": function(data, type, row, meta){
                		var ret = '<a class="operation_primary " rowid="'+row.SID+'" rowuid="'+row.RUSERID+'" onclick="details(\''+row.SID+'\',\''+row.RUSERID+'\');">详情</a>';
                		return ret;
                	}
                }
            ]
    	} );
    	$('#datatable tbody').on('dblclick', 'tr', function () {
            var tr = $(this).closest('tr');
            var sid = tr.find('td:last-child a:first-child').attr('rowid');
            var userid = tr.find('td:last-child a:first-child').attr('rowuid');
            parent.layer.open({ 
     	 		type: 2, 
     	 		title:'消息详情',
     	 		id:'laysdfm',
     	 		area: ['50%', '50%'],
     	 		fixed: false, //不固定
     	 		maxmin: true,
     	 		content: '${ctx}/system/notice/allMessage?sid='+sid+"&userid="+userid+"&type=1"
     	 	});	  
        });
      });
    
     
    function details(sid,userid){
    	 $.ajax({
             type: "GET",
             url: "${ctx}/system/notice/detailsLength",
             async: false,//因为下面要刷新，所以这里要同步加载
             data:{userid:userid,sid:sid},
             dataType: "json",
             success: function(data){
           	  var dataLength = data.length;
           	  var msgLabel = window.parent.parent.document.getElementById("msgLabel");
           	  var messageHeader = window.parent.parent.document.getElementById("messageHeader");
           	  $(msgLabel).html(dataLength);
           	  $(messageHeader).html('你有'+dataLength+'条未读消息');
           	  parent.layer.open({ 
         	 		type: 2, 
         	 		title:'消息详情',
         	 		id:'layer_addParam',
         	 		area: ['50%', '50%'],
         	 		fixed: false, //不固定
         	 		maxmin: true,
         	 		content: '${ctx}/system/notice/allMessage?sid='+sid+"&userid="+userid+"&type=1"
         	 	});	  
             }
		  })
		  refresh();  
     };
     function searchRole(){
    	 table.api().ajax.reload();
     }
     
    //刷新页面
     function refresh(){
    	 table.api().ajax.reload();
    }
        
    </script>
</body>
</html>

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
              		  	<button type="button" class="btn btn-danger btn-sm" onclick="reset()">重置</button>
						</span> 
				</div>
				<div style="width: 100%;">
				</div>
				<div class="Tabular_frame">
                <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
                   <thead>
                       <tr>
                           <th id="0" name="center" class="width_44">序号 </th>
                           <th id="1" name="center">登录名</th>
                           <th id="2" name="center">姓名</th>
                           <th id="3" name="center">日志类别</th>
                           <th id="4" name="center">操作IP</th>
                           <th id="5" name="center">操作时间</th>
                           <th id="6" name="center">操作结果</th>
                           <th id="7" name="center">备注</th>
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
    <!-- 防重复提交 -->
    <div class="loading_bg">
      <div class="loading_box">
        <img src="${ctx }/static/img/wait.png">
        <h3 style="color:#fff;">loading...</h3>
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
    	        url: '${ctx}/system/user/logshow',
    	        type: 'POST',
    	        data: function(d){
    	        	d.loginname = $("#loginname").val();
    	        	d.username = $("#username").val();
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<7; i++){
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
                	"data":"LOGTYPE",
                	"render": function(data, type, row, meta) {
                		if(data == '0'){
                			return "登录";
                		}else{
                			return "退出";
                		}
                	} 
                }, 
                {"data":"OPERIP"},
                {"data":"OPERTIME"},
                {
                    "data":"RESULT",
                    "render": function(data, type, row, meta) {
                        if(data == '0'){
                            return "操作失败";
                        }else{
                            return "操作成功";
                        }
                    } 
                }, 
                {"data":"REMARK"}
            ]
    	} );
    });
    //搜索
    function searchUser(){
    	loadStart();
	    table.api().ajax.reload();
	    loadEnd();
	}
    function reset(){
        $("#loginname").val("");
        $("#username").val("");
    }
    </script>
</body>
</html>

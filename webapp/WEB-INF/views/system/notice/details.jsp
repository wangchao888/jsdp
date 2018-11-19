<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
</head>
<body class="white-bg">
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
            	<div class="form-group">
            		<div class="col-xs-6 ">
                     <label class="col-xs-12 control-label font14">通知标题<span class="star">*</span></label>
                     <div class="col-xs-12">
                     	<span class="form-control border_none border_bom_dashed">${dataMap.NTCTITLE }</span>
                     	<input type="hidden" name="infoid" id="infoid" value="${infoid }" />
                     </div>
                     </div>
                     <div class="col-xs-6 ">
                     <label class="col-xs-12 control-label font14">通知类型<span class="star">*</span></label>
                     <div class="col-xs-12">
                     <span class="form-control border_none border_bom_dashed"><jsdp:dicshow dictno="01" dictlabel="${dataMap.NTCTYPE}"></jsdp:dicshow></span>
                	 </div>
                	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-6 ">
                     <label class="col-xs-12 control-label font14">通知日期<span class="star">*</span></label>
                     <div class="col-xs-12">
                     	<span class="form-control border_none border_bom_dashed">${dataMap.RELEASEDATE }</span>
                	 </div>
                	 </div>
                	 <div class="col-xs-6 ">
                     <label class="col-xs-12 control-label font14">通知内容<span class="star">*</span></label>
                     <div class="col-xs-12">
                     <span class="form-control border_none border_bom_dashed">${dataMap.DESCRIBE }</span>
                	 </div>
                	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-6 ">
                     <label class="col-xs-12 control-label font14">接收用户<span class="star">*</span></label>
                     <div class="col-xs-12">
                     		<c:choose>
								<c:when test="${dataMap.USERTYPE == '00'}">
									<span class="form-control border_none border_bom_dashed">所有用户</span>
								</c:when>
								<c:when test="${dataMap.USERTYPE == '01'}">
									<span class="form-control border_none border_bom_dashed">主管用户</span>
								</c:when>
								<c:when test="${dataMap.USERTYPE == '02'}">
									<span class="form-control border_none border_bom_dashed">企业用户</span>
								</c:when>
								<c:otherwise>
									<span class="form-control border_none border_bom_dashed">银行用户</span>
								</c:otherwise>
							</c:choose>
                	 </div>
                	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <div class="col-xs-12">
                     <a class="operation_primary " type="button" onclick="details();">用户列表</a>
                	 </div>
                	 </div>
                 </div>
                 <div class="ibox-content" id="tables" style="display:none;">
              <div class="dataTables_top">
              		  <input type="search" class="form-control input-sm input_big_display" placeholder="输入用户姓名" name="username" id="username">
              		  <select class="form-control input-sm input_big_display" name="viewflag" id="viewflag">
              		  <option value="">全部</option>
              		  <option value="0">未查阅</option>
              		  <option value="1">已查阅</option>
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
                         <th id="1" name="left" >用户姓名</th>
                         <th id="2" name="center" >所属单位</th>
                         <th id="3" name="left" >查阅状态</th>
                     </tr>
                 </thead>
              </table>
              </div>
              </div>
            </div>
                 
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
    
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
     <script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    <script type="text/javaScript">
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
    	        url: '${ctx}/system/notice/detailShow',
    	        type: 'POST',
    	        data: function(d){
    	        	d.username = $("#username").val();
    	        	d.viewflag = $('#viewflag option:selected').val(); 
    	        	d.infoid = $("#infoid").val();
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
                {"data":"USERNAME"},
                {"data":"ORGNAME"}, 
                {"data":"VIEWFLAG",
                	"render": function(data, type, row, meta){
                   		var ret = "";
                   		if(data == "0"){ret = "未查阅";}
                   		if(data == "1"){ret = "已查阅";}
                   		return ret;
                   	}}
            ]
    	} );
      });
    function searchRole(){
   	 table.api().ajax.reload();
    }
    //窗口关闭并刷新父页面
  	function closeWin(){ 
    	//刷新
    	var doc = window.parent;
    	doc.refresh();
    	//关闭窗口
    	parent.layer.closeAll();
    	
  	}   
    num = "0"
  	function details(){	
    	if (num == "0") {
    		$("#tables").attr("style","display:block;");//显示div
    		num = "1"
    	} else {
    		$("#tables").attr("style","display:none;");//隐藏div
    		num = "0"
    	}
  	}
    </script>
</body>
</html>

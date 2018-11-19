<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
     <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
    <style type="text/css">
	  .col-xs-3{padding-right:8px !important;}
   </style>
</head>
<body style="background-color:#ECF0F5;height:100%;" >
    <div style="height:100%;">
        <div class="row" style="height:100%;">
	        <div class="col-xs-3" style="height:100%;width:21%;">
                <div class="ibox float-e-margins" style="height:100%;background-color:#fff;">
                    <div class="ibox-title">
                        <h5>菜单树</h5>
                    </div>
                    <div>
                        <div class="ibox-content white-bg" style="height:calc(100% - 50px)">
                           <ul id="tree" class="ztree"></ul>
                          <hr>
						   <ul class="tag-list" >
						      <li><button class="btn btn-sm btn-primary" onclick="addMenu()"><i class="fa fa-tag"></i> 添加菜单</a></li>
						  </ul>
                        </div>
                    </div>
                </div>
            </div>
        	<div class="col-xs-9"  style="height:100%;padding-left:0;padding-right:0;width:79%;background-color:#fff"> 
 				<iframe scrolling="no" width="100%" height="100%" frameborder="0" name="deptLoad" id="deptLoad" class="gaodu"></iframe>  
 			</div>     
      </div>
  </div>
	
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script> 	
	<script type="text/javascript">
		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : ""
				}
			},
			callback : {
				beforeClick : beforeClick,
				onClick : onClick
			}
		};

		var zNodes = ${treelist};

		$(document).ready(function() {
			var dhight = document.documentElement.clientHeight - 100;
			$("#content").height(700);
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam("id", 101));
		});

		var log, className = "dark";

		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "" : "dark");

			return (treeNode.click != false);
		}
		//点击事件  
		var id;
		function onClick(event, treeId, treeNode, clickFlag) {
			id = treeNode.id;
			
			//window.open("${ctx}/system/menu/initUpdate?name="+treeNode.id+"&pid="+treeNode.pid,'deptLoad','');	
			$("#deptLoad").attr("src", "${ctx}/system/menu/initUpdate?name="+treeNode.id+"&pid="+treeNode.pid);
		}
		function addMenu(){
			if(id == undefined){
				  $("#deptLoad").attr("src","${ctx}/system/menu/initAdd");
				}else{
				  $("#deptLoad").attr("src","${ctx}/system/menu/initAdd?pid="+id);
				}
			
	     }
		 //刷新页面
	    function refresh(){
			 window.location="${ctx}/system/menu/list?menuid=${menuid}"; 
	    }
	</script>
    <script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>

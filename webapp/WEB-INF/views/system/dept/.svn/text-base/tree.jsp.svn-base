<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css"  type="text/css">
  <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
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
                        <h5>机构树</h5>
                    </div>
                    <div>
                        <div class="ibox-content white-bg" style="height:calc(100% - 50px)">
                           <ul id="tree" class="ztree"></ul>
                           <hr>
						   <ul class="tag-list" >
						      <li><button class="btn btn-sm btn-primary" onclick="addOrg()"><i class="fa fa-tag"></i> 添加机构</button></li>
						  </ul>
                        </div>
                    </div>
                </div>
            </div>
        	<div class="col-xs-9"  style="height:100%;padding-left:0;padding-right:0;width:79%;"> 
 				<div class="ibox float-e-margins" style="overflow:hidden;height:100%;background-color:#fff">
                    <iframe class="ibox-content"  scrolling="no" width="100%" height="100%" style="padding:0;" frameborder="0" name="deptLoad" id="deptLoad" class="gaodu"></iframe> 
                </div>  
 			</div>     
      </div>
  </div>
	<script type="text/javascript"
		src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>
	
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
				onClick : editOrg
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
		function editOrg(event, treeId, treeNode, clickFlag) {
			id = treeNode.id;
			//机构跳转
			if (treeNode.other == 'org') {
				$("#deptLoad").attr(
						"src",
						"${ctx}/system/dept/initUpdate?sid=" + treeNode.id
								+ "&pid=" + treeNode.pid);
			} else {
				//用户信息列表跳转
				$("#deptLoad").attr(
						"src",
						"${ctx}/system/user/list?orgid=" + treeNode.pid
								+ "&menuid=${menuid}");
			}

		}
		function addOrg(){
			if(id == undefined){
				var admin = "${admin}";
				if(admin == '0'){
					layer.alert("当前用户无法添加顶级机构！");	
					return false;
				}
				$("#deptLoad").attr("src","${ctx}/system/dept/initAdd");
			}
			else{
				$("#deptLoad").attr("src","${ctx}/system/dept/initAdd?pid="+id);
			}
	    }
		//刷新树，机构信息维护完后需要异步刷新左侧的树结构
		function refresh() {
			$.ajax({
				url : "${ctx}/system/dept/refreshTree",//提交地址
				type : "POST",
				dataType : "json",
				success : function(result) {
					var t = $("#tree");
					t = $.fn.zTree.init(t, setting, result);
					var zTree = $.fn.zTree.getZTreeObj("tree");
					zTree.selectNode(zTree.getNodeByParam("id", 101));
				}
			})
		}
		//刷新右侧用户信息列表，用户信息维护弹出框使用该方法
		function refreshUserList(orgid){
			$("#deptLoad").attr("src","${ctx}/system/user/list?menuid=${menuid}&orgid="+orgid);
		}
	</script>
</body>
</html>

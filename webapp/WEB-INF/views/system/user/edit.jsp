<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
	 <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
	<link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css"> 
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
	<link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet"> 
	
</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
              <c:if test="${add == 'update' }">
				                <div class="form-group">
				                  	<div class="col-xs-6 ">
				                  		<label class="col-xs-12 control-label">所属部门<span class="star">*</span></label>
	              <div class="col-xs-12">
	                  <input id="orgname" readonly name="orgname" type="text" disabled="disabled" value="${orgName}" onclick="showOrg();" class="form-control">
	                  <input name="orgid" type="hidden" id="orgid" value="${orgId}"/>
	              </div>
	              <div id="orgContent" style="display:none; position: absolute;z-index:1;">
					  <ul id="treeOrg" class="ztree"></ul>
				  </div>
									</div>
									
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label">用户类型</label>
	              <div class="col-xs-12">
	              	<input  readonly disabled="false" type="text"  value="<jsdp:dicshow dictno="73" dictlabel="${userType}"></jsdp:dicshow>"  class="form-control">
	              </div>
									</div>
									
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label">登录名<span class="star">*</span></label>
	              <div class="col-xs-12">
	                  <input type="text" name="loginname" id="loginname"  disabled="disabled" value="${entityMap.LOGINNAME}" class="form-control">
	                  <input type="hidden" name="sid" value="${entityMap.SID}" >
	              </div>
									</div>
				                </div>              
              </c:if>
               <c:if test="${add != 'update' }">
				                <div class="form-group">
				                  	<div class="col-xs-6 ">
				                  		<label class="col-xs-12 control-label">所属部门<span class="star">*</span></label>
	
	              <div class="col-xs-12">
	                  <input id="orgname" readonly name="orgname" type="text"  value="${orgName}" onclick="showOrg();" class="form-control">
	                  <input name="orgid" type="hidden" id="orgid" value="${orgId}"/>
	              </div>
	              <div id="orgContent" style="display:none; position: absolute;z-index:1;">
					  <ul id="treeOrg" class="ztree"></ul>
				  </div>
									</div>
									
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label">用户类型</label>
	              <div class="col-xs-12">
						<input  readonly disabled="false" type="text"  value='<jsdp:dicshow dictno="73" dictlabel="${userType}"></jsdp:dicshow>'  class="form-control">	
						<input type="hidden" value="${userType}" name="usertype" />
	              </div>
									</div>
									
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label">登录名<span class="star">*</span></label>
	              <div class="col-xs-12">
	                  <input type="text" name="loginname" id="loginname"  value="${loginName}" class="form-control">
	              </div>
									</div>
				                </div>               
              </c:if>
				                <div class="form-group">
				                  	<div class="col-xs-6 ">
				                  		<label class="col-xs-12 control-label">用户姓名<span class="star">*</span></label>
	              <div class="col-xs-12">
	                  <input type="text" name="username"  value="${entityMap.USERNAME}" class="form-control">
	              </div>
									</div>
									
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label">状态</label>
	              <div class="col-xs-12">
					 <c:if test="${update == '0' }">
					 	<input type="checkbox" class="js-switch" checked /> 
	              		<input type="hidden" name="state" id="state" >
					 </c:if>
	              	<c:if test="${update == '1' }">
					 	<input type="checkbox" class="js-switch" <c:if test="${entityMap.STATE == '1' }">checked</c:if> /> 
	              		<input type="hidden" name="state" id="state" value="${entityMap.STATE }">
					 </c:if>
	              </div>
									</div>
									
									<div class="col-xs-6 ">
									
									</div>
				                </div>              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/plugins/switchery/switchery.js"></script>
    
	<script type="text/javascript">
	var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var zNodes =  ${treelist};
		
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeOrg"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			x = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				x += nodes[i].id + ",";
			}
			if (v.length > 0 ) {
				v = v.substring(0, v.length-1);
			}
			x = x.replace(new RegExp(/(,)/g),'');//去掉逗号
			var orgname = $("#orgname");
			orgname.attr("value", v);
			var orgid = $("#orgid");
			orgid.attr("value", x);
			//点击后关闭
			$("#orgContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByOrg);
		}
		
		function showOrg() {
			var cityObj = $("#orgname");//获取上层对象
			var cityOffset = $("#orgname").offset();//元素的偏移坐标
			$("#orgContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#orgContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "orgname" || $(event.target).parents("#orgContent").length>0)) {
				hideMenu();
			}
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeOrg"), setting, zNodes);
			 var isCore = document.querySelector(".js-switch"), t = new Switchery(isCore,{color: "#168EE3 "});
		  	  isCore.onchange = function() {
		          if(isCore.checked){
		          	$('#state').attr("value","1");
		          	
		          }else{
		          	$('#state').attr("value","0");
		          }
		  	  };
	 		var state = "${entityMap.STATE}";
	 		//初始化选中
	 		$.each($("#state option"),function(){
	 			  if($(this).val()==state)
	 				  $(this).attr('selected',"selected");
	 		});
	 		
	 		var e = "<i class='fa fa-times-circle'></i> ";
			$("#dataForm").validate({ignore: ":hidden:not(select)",
		        rules: {
		        	orgname: "required",
		        	loginname: "required",
		        	username: "required"
		        },
		        messages: {
		        	orgname: e + "请输入所属部门",
		        	loginname: e + "请输入登陆名",
		        	username: e + "请输入用户名"
		        }
		    });
		});
		//刷新父页面列表
		function refreshList(){
			//获取当前用户的机构id
			var orgid = $("#orgid").val();
			
		  	//关闭窗口
		  	var doc = window.parent;
		  	doc.refreshUserList(orgid);
		  	parent.layer.closeAll();
		}
		function saveForm(){
			if (!$("#dataForm").valid()) {
		        return;
		     }
			$.ajax({
	            url:"${ctx}/system/user/${add}",//提交地址
	            data:$("#dataForm").serialize(),//将表单数据序列化
	            type:"POST",
	            dataType:"json",
	            success:function(result){
	                if (result.retFlag == 'T'){
	                	parent.layer.confirm(result.retMsg, {
	                	    btn: ['确定'], //按钮                   	    
	                	    shade: false //不显示遮罩
	                	}, function(){                		
	                		refreshList();
	                	}); 
	                }else{
	                	parent.layer.alert(result.retMsg);
	                }
	            }
	    	})
		};
		function queryMaxLoginName(obj){
			$.ajax({
                url:"${ctx}/system/user/queryMaxLoginName",//提交地址
                data:"usertype="+obj.value,//将表单数据序列化
                type:"get",
                dataType:"json",
                success:function(result){
                	$("#loginname").val(result);
                }
        	})
		}
	</script>
  <script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
 <!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet"> 
	<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
     <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
    <link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css"> 
</head>

<body class="white-bg">
	<div class="animated fadeInRight"
		style="padding-bottom: 200px !important;">
		<div class="row">
			<div class="ibox float-e-margins">
				<!-- 标题头部分 开始 -->
				<div class="ibox-title">
					<h3>机构信息</h3>
				</div>
				<div class="ibox-content white-bg">
					<form role="form" id="dataForm" class="form-horizontal">
						<div class="panel panel-default"
							style="width: 80%; margin: 0 auto;">
							<div class="panel-body padd00020">
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">组织名称<span
											class="star">*</span></label>
										<div class="col-xs-12">
											<input type="text" name="orgname" value="${orgMap.ORGNAME}"
												class="form-control"> <input type="hidden"
												value="${orgMap.SID}" name="sid">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">是否顶级机构</label>
										<div class="col-xs-12">
											<c:if test="${add =='update' }">
												<input type="checkbox" class="js-switch"
													<c:if test="${orgMap.PID == null || orgMap.PID == ''}">checked</c:if> />
											</c:if>

											<c:if test="${add =='add' }">
												<input type="checkbox" class="js-switch"
													<c:if test="${orgvMap.SID == null || orgvMap.SID == ''}">checked</c:if> />
											</c:if>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">上级组织</label>
										<div class="col-xs-12">
											<c:if test="${add == 'update'}">
												<input id="pidName" type="text"
													style="background-color: #FFF" type="text" readonly
													value="${orgMap.PORGNAME}" onclick="showOrg();"
													class="form-control"
													<c:if test="${orgMap.PID == null || orgMap.PID == ''}">disabled="false" </c:if>>
											</c:if>

											<c:if test="${add == 'add'}">
												<input id="pidName" type="text"
													style="background-color: #FFF" type="text" readonly
													value="${pidName}" onclick="showOrg();"
													class="form-control"
													<c:if test="${orgvMap.SID == null || orgvMap.SID == ''}">disabled="false"  </c:if>>
											</c:if>
											<input name="pid" type="hidden" id="pid" value="${pid}" />
											<div id="orgContent"
												style="display: none; position: absolute; z-index: 1;">
												<ul id="treeOrg" class="ztree"></ul>
											</div>
										</div>

									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">组织类型</label>
										<div class="col-xs-12">
											<c:if test="${empty pid}">
												<input id="orgname" style="background-color: #FFF"
													type="text" readonly type="text" value="机构"
													class="form-control" disabled="false">
												<input id="orgtype" type="hidden" value="01" />
											</c:if>
											<c:if test="${not empty pid}">
												<input id="orgname" style="background-color: #FFF"
													type="text" readonly type="text" value="部门"
													class="form-control" disabled="false">
												<input id="orgtype" type="hidden" value="02" />
											</c:if>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">行政代码<span
											class="star">*</span></label>

										<div class="col-xs-12">
											<input id="zonecodeName" name="zonecodeName" type="text"
												style="background-color: #FFF" type="text" readonly
												value="${orgMap.ZONENAME}" onclick="showZone();"
												class="form-control"> <input name="zonecode"
												type="hidden" id="zonecode" value="${orgMap.ZONECODE}" />
											<div id="zoneContent"
												style="display: none; position: absolute; z-index: 1;">
												<ul id="treeZone" class="ztree"></ul>
											</div>
										</div>

									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">行政级别<span
											class="star">*</span></label>
										<div class="col-xs-12">
											<select class="form-control" name="orglevl" id="orglevl"
												onclick="orgtypeselect(this)">
												<jsdp:dic dictno="68"></jsdp:dic>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">数据范围<span
											class="star">*</span></label>
										<div class="col-xs-12">
											<input name="dataZoneNameVerify" id="dataZoneName"
												type="text" style="background-color: #FFF" type="text"
												readonly value="${zoneMap.ZONENAME}"
												onclick="showDataZone();" class="form-control"> <input
												name="dataZone" type="hidden" id="dataZone"
												value="${zoneMap.ZONECODE}" />
											<div id="dataContent"
												style="display: none; position: absolute; z-index: 1;">
												<ul id="treeDataZone" class="ztree"></ul>
											</div>
										</div>

									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12 text-center" style="padding-top:10px;">
											<button class="btn btn-sm btn-primary" type="button"
												id="saveBtn">保存</button>
											<c:if test="${add == 'update'}">
												<button class="btn btn-sm btn-danger" type="button"
													id="delBtn" onclick="delOrg('${orgMap.SID}');">删除</button>
											</c:if>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<!-- 标题头部分 结束 -->
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
	<script type="text/javascript" src="${ctx}/static/js/plugins/switchery/switchery.js"></script>
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript">
	//初始化选中
	//组织类型
   	 var orgtype = "${orgMap.ORGTYPE}";
		$.each($("#orgtype option"),function(){	
			  if($(this).val()==orgtype)
				  $(this).attr('selected',"selected");
		});	
	//行政级别
   	 var orglevl = "${orgMap.ORGLEVL}";
		$.each($("#orglevl option"),function(){	
			  if($(this).val()==orglevl)
				  $(this).attr('selected',"selected");
		});
		//下拉树：
		var orgNodes =  ${orgTree};
		var zoneNodes =  ${zoneTree};
		var dataNodes = ${zoneTree};
	    //机构树
		var settingOrg = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClickOrg,
				onClick: onClickOrg
			}
		};
	    
		function beforeClickOrg(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		
		function onClickOrg(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeOrg"),
			nodes = zTree.getSelectedNodes(),
			v = "";
		    x = ""; 
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			    x += nodes[i].id + ","; 
			}
			if (v.length > 0 ){
				v = v.substring(0, v.length-1);
			} 
			if (x.length > 0 ){
				x = x.substring(0, x.length-1);
			} 
			var orgObj = $("#pidName");
			orgObj.val(v);
			var orgPidObj = $("#pid");
			orgPidObj.val(x);
			//点击后关闭
			$("#orgContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByOrg);
		}
		function showOrg() {
			var cityObj = $("#pidName");              //获取上层对象
			var cityOffset = $("#pidName").offset();  //元素的偏移坐标。
			$("#orgContent").slideDown("fast");

			$("body").bind("mousedown", onBodyDownByOrg);
		}
		function hideTreeOrg() {
			$("#orgContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByOrg);
		}
		
		function onBodyDownByOrg(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "pidName" || event.target.id == "orgContent" || $(event.target).parents("#orgContent").length>0)) {
				hideTreeOrg();
			}
		}
		
	    //行政代码树
		var settingZone = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClickZone,
				onClick: onClickZone
			}
		};
	    
		function beforeClickZone(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		//zonecode 存在了remark字段中
		function onClickZone(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeZone"),
			nodes = zTree.getSelectedNodes(),
			v = "";
		    x = ""; 
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			    x += nodes[i].remark + ","; 
			}
			if (v.length > 0 ){
				v = v.substring(0, v.length-1);
			}
			if (x.length > 0 ){
				x = x.substring(0, x.length-1);
			}
			var orgObj = $("#zonecodeName");
			orgObj.attr("value", v);
			var orgPidObj = $("#zonecode");
			orgPidObj.attr("value", x);
			//点击后关闭
			$("#zoneContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByZone);
		}
		
		function showZone() {
			var cityObj = $("#zonecodeName");              //获取上层对象
			var cityOffset = $("#zonecodeName").offset();  //元素的偏移坐标。
			$("#zoneContent").slideDown("fast");

			$("body").bind("mousedown", onBodyDownByZone);
		}
		
		function hideTreeZone() {
			$("#zoneContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByZone);
		}
		
		function onBodyDownByZone(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "zonecodeName" || event.target.id == "zoneContent" || $(event.target).parents("#zoneContent").length>0)) {
				hideTreeZone();
			}
		}
		//数据范围树
		var settingDataZone = {
				check: {
					enable: true,
// 					chkboxType: {"Y":"", "N":""}
					chkboxType: { "Y" : "ps", "N" : "ps" }
				},
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClickDataZone,
					onCheck: onCheck
				}
		};
		function beforeClickDataZone(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDataZone");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		//zonecode 存在了remark字段中
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDataZone"),			
			nodes = zTree.getCheckedNodes(true),
			v = "";
		    x = ""; 
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			    x += nodes[i].remark + ","; 
			}
			if (v.length > 0 ){
				v = v.substring(0, v.length-1);
			}
			if (x.length > 0 ){
				x = x.substring(0, x.length-1);
			}
			var orgObj = $("#dataZoneName");
			orgObj.attr("value", v);
			var orgPidObj = $("#dataZone");
			orgPidObj.attr("value", x);
		}
		
		function showDataZone() {
			var cityObj = $("#dataZoneName");              //获取上层对象
			var cityOffset = $("#dataZoneName").offset();  //元素的偏移坐标。
			$("#dataContent").slideDown("fast");

			$("body").bind("mousedown", onBodyDownByDataZone);
		}
		
		function hideTreeDataZone() {
			$("#dataContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDownByDataZone);
		}
			
		function onBodyDownByDataZone(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "dataZoneName" || event.target.id == "dataContent" || $(event.target).parents("#dataContent").length>0)) {
				hideTreeDataZone();
			}
		}
		
		$(document).ready(function(){
 			$.fn.zTree.init($("#treeOrg"), settingOrg, orgNodes);
			$.fn.zTree.init($("#treeZone"), settingZone, zoneNodes);
			$.fn.zTree.init($("#treeDataZone"), settingDataZone, dataNodes);
			//是否顶级结构
			var rootOrg = document.querySelector(".js-switch"), t = new Switchery(rootOrg,{color: "#168EE3 "});
        	rootOrg.onchange = function() {
                if(rootOrg.checked){
                	$('#orgname').val("机构");
                	$('#orgtype').val("01");
                	$('#pidName').attr("disabled","disabled");
                	$('#pidName').val("");
                	$('#pid').val("");
                	
                }else{
                	$('#orgname').val("部门");
                	$('#orgtype').val("02");
                	$('#pidName').attr("disabled",false);
                }
        	};
        	
        	var e = "<i class='fa fa-times-circle'></i> ";
        	$("#dataForm").validate({ignore: ":hidden:not(select)",
    	        rules: {
    	        	orgname: "required",
    	        	zonecodeName: "required",
    	        	orglevl: "required",
    	 dataZoneNameVerify:"required"
    	        },
    	        messages: {
    	        	orgname: e + "请输入组织名称",
    	        	zonecodeName: e + "请输入行政代码",
    	        	orglevl: e + "请输入行政级别",
    	 dataZoneNameVerify: e + "请输入数据范围"
    	        }
    	    });
        	//保存操作
			$("#saveBtn").click(function(){
				if (!$("#dataForm").valid()) {
			        return;
			     }
				$.ajax({
		            url:"${ctx}/system/dept/${add}",//提交地址
		            data:$("#dataForm").serialize(),//将表单数据序列化
		            type:"POST",
		            dataType:"json",
		            success:function(result){
		                if (result.retFlag == 'T'){
		                	parent.layer.confirm(result.retMsg, {
		                	    btn: ['确定'], //按钮                   	    
		                	    shade: false //不显示遮罩
		                	}, function(){                		
		                		refreshTree();
		                	}); 
		                }else{
		                	parent.layer.alert(result.retMsg);
		                }
		            }
		    	})
			});
		});
		function refreshTree(){
			var flag = '${add}';
	    	//关闭窗口
	    	var doc = window.parent; 
	    	doc.refresh();
	    	if(flag == 'add'){
	    		doc.addOrg();
	    	}
	    	parent.layer.closeAll();
	  	}
		
		function refreshTreeDel(){
			var doc = window.parent; 
	    		doc.refresh();
	    		doc.addOrg();//删除后右侧进入到添加页面
	    		parent.layer.closeAll();
		}
		
		function deleteEntity(){
			document.myForm.action = "${ctx}/system/dept/delete";
	    }
		
		function delOrg(sid){
			parent.layer.confirm('是否删除当前机构？', {
	    	    btn: ['确定','取消'], //按钮
	    	    shade: false //不显示遮罩
	    	}, function(){
	    		$.ajax({
	         		 url:"${ctx}/system/dept/delete",
	         		 data:{sid:sid},
	         		 type:"get",
	         		 dataType:"json",
	         		 success:function(result){
	  		                if (result.retFlag == 'T'){
	  		                	parent.layer.confirm(result.retMsg, {
	  		                	    btn: ['确定'], //按钮                   	    
	  		                	    shade: false //不显示遮罩
	  		                	}, function(){ 
	  		                		refreshTreeDel();
	  		                	}); 
	  		                }else{
	  		                	parent.layer.alert(result.retMsg);
	  		                }
	  		            }
	         	   });
	    		
	    	});
	    	
	    }
	function orgtypeselect(value){ 	
// 	    var selectedOption=value.options[value.selectedIndex].value; 
// 	    if(selectedOption == '02'){
// 	    	$('#orglevl').attr("disabled","disabled");
// 	    	$('#zonecodeName').attr("disabled","disabled");	    	
// 	    }else{
// 	    	$('#orglevl').attr("disabled",false);
// 	    	$('#zonecodeName').attr("disabled",false);
// 	    }
	      
	    
	}  
		
	</script>
</body>


</html>

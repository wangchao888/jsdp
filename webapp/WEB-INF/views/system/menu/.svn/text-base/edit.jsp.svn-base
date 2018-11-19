<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
 <!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet"> 
	<link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
     <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
</head>

<body class="white-bg">
	<div class="animated fadeInRight">
		<div class="row">
			<div class="ibox float-e-margins">
				<!-- 标题头部分 开始 -->
				<div class="ibox-title">
					<h3>菜单信息</h3>
				</div>
				<div class="ibox-content white-bg">
					<form role="form" id="dataForm" class="form-horizontal">
						<div class="panel panel-default"
							style="width: 80%; margin: 0 auto;">
							<div class="panel-body padd00020">
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">菜单名称<span
											class="star">*</span></label>
										<div class="col-xs-12">
											<input id="menuname" name="menuname" type="text"
												value="${menuMap.MENUNAME}" class="form-control">
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">是否顶级菜单</label>
										<div class="col-xs-12 mtb10">
											<c:if test="${add =='update' }">
												<input type="checkbox" class="js-switch"
													<c:if test="${menuMap.PID == null || menuMap.PID == ''}">checked</c:if>
													disabled="false" />
											</c:if>

											<c:if test="${add =='add' }">
												<input type="checkbox" class="js-switch"
													<c:if test="${pidMap.SID == null || pidMap.SID == ''}">checked</c:if> />
											</c:if>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">上级菜单</label>

										<div class="col-xs-12">
											<c:if test="${add == 'update'}">
												<input id="pidName" type="text" readonly value="${menuName}"
													onclick="showMenu();" class="form-control" disabled="false">
											</c:if>
											<c:if test="${add == 'add'}">
												<input id="pidName" type="text" readonly
													value="${menuName }" onclick="showMenu();"
													class="form-control"
													<c:if test="${pid == null || pid == ''}">disabled="false"</c:if>>
											</c:if>
											<input name="pid" type="hidden" id="pid" value="${pid}" /> <input
												type="hidden" value="${menuMap.SID}" name="sid" id="sid">
										</div>
										<div id="menuContent"
											style="display: none; position: absolute; z-index: 1;">
											<ul id="treeMenu" class="ztree"></ul>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">所属应用</label>
										<div class="col-xs-12">
											<select class="form-control" name="appid" id="appid">
												<c:forEach items="${appList }" var="obj" varStatus="status">
													<option value="${obj.SID }"
														<c:if test="${menuMap.APPID == obj.SID }">selected</c:if>>${obj.APPNAME
														}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">菜单链接</label>

										<div class="col-xs-12">
											<input id="menuurl" name="menuurl" type="text"
												value="${menuMap.MENUURL}" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">菜单图标</label>

										<div class="col-xs-12">
											<input id="menuico" name="menuico"
												style="background-color: #FFF" type="text" readonly
												type="text" value="${menuMap.MENUICO}" class="form-control"
												onclick="choseMenuIco()">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label class="col-xs-12 control-label">菜单序号<span
											class="star">*</span></label>

										<div class="col-xs-12">
											<input id="menuno" name="menuno" type="text"
												value="${menuNo }" class="form-control">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12 text-center" style="padding-top: 10px;">
										<button class="btn btn-sm btn-primary"
											type="button" id="saveBtn">保存</button>
										<c:if test="${add == 'update' }">
											<button class="btn btn-sm btn-danger"
												type="button" id="delBtn" onClick="del();">删除</button>
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
			var zTree = $.fn.zTree.getZTreeObj("treeMenu"),
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
			var cityObj = $("#pidName");
			cityObj.attr("value", v);
			var cityObj1 = $("#pid");
			cityObj1.attr("value", x);
			//选中后关闭
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
			//动态更新菜单序号
			$.ajax({
	            url:"${ctx}/system/menu/menuno?pid="+x,//提交地址
	            type:"POST",
	            dataType:"json",
	            success:function(result){
	            	if (result.retFlag == 'T'){
	                	$("#menuno").attr("value",result.retMsg);
	                }else{
	                	parent.layer.alert(result.retMsg);
	                }
	            }
	    	});
			
		}
		
		function showMenu() {
			var cityObj = $("#pidName");
			var cityOffset = $("#pidName").offset();
			$("#menuContent").css({left:cityOffset.left+"px", top:cityOffset.top+cityObj.outerHeight()+20 +"px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "pidName" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
			//是否顶级菜单
			var rootMenu = document.querySelector(".js-switch"), 
			    t = new Switchery(rootMenu,{color: "#168EE3 "});			
			if("${add}" == "add"){
				rootMenu.onchange = function() {
	                if(rootMenu.checked){
	                	$('#pidName').attr("disabled","disabled");
	                	$('#pidName').attr("value","");
	                	$('#pid').attr("value","");
	                }else{
	                	$('#pidName').attr("disabled",false);
	                } 
	        	};
			};
			
			var e = "<i class='fa fa-times-circle'></i> ";
			$("#dataForm").validate({ignore: ":hidden:not(select)",
		        rules: {
		        	menuname: "required",
	        		menuno : {
	            		  required : true,
	    					digits : true,
	    				}
		        },
		        messages: {
		        	menuname: e + "请输入菜单名称",
		        	menuno : {
		        		  required :  e + "请输入序号",
		        		    digits :  e + "序号只能为数字",
						},
		        }
		    });
			$('#saveBtn').click(function(){
				if (!$("#dataForm").valid()) {
			        return;
			     }
				$.ajax({
		            url:"${ctx}/system/menu/${add}",//提交地址
		            data:$("#dataForm").serialize(),//将表单数据序列化
		            type:"POST",
		            dataType:"json",
		            success:function(result){
		            	if (result.retFlag == 'T'){
		                	parent.layer.confirm(result.retMsg, {
		                	    btn: ['确定'],
		                	    shade: false 
		                	}, function(){
		                		closeWin();
		                	});
		                }else{
		                	parent.layer.alert(result.retMsg);
		                }
		            }
		    	});
			});
		});
		
		function jquery_load1(){
			document.myForm.action = "${ctx}/system/menu/delete";
	    }
		
		//选择菜单图标
		function choseMenuIco(){
			parent.layer.open({
				type: 2, 
	    		title:'菜单图标列表(双击选中)',
	    		id:'layer_addParam',
	    		area: ['75%', '75%'],
	    		fixed: false, //不固定
	    		maxmin: true,
	    	    content: '${ctx}/system/menu/menuIco'
	    	});
		}
		
		//菜单图标样式赋值
		function menuIco(menuico){
			$("#menuico").val(menuico);
		}
		function del(){
			var sid = $("#sid").val();
			$.ajax({
                url:"${ctx}/system/menu/delete",//提交地址
                data:"sid="+sid,//将表单数据序列化
                type:"get",
                dataType:"json",
                success:function(result){
	            	if (result.retFlag == 'T'){
	            		parent.layer.confirm(result.retMsg, {
	                	    btn: ['确定'],
	                	    shade: false 
	                	}, function(){
	                		closeWin();
	                	});
	                }else{
	                	parent.layer.alert(result.retMsg);
	                }
	            }
        	});
		}
		
		//窗口关闭并刷新父页面
	  	function closeWin(){
	    	//关闭窗口
	    	var doc = window.parent;
	    	doc.refresh();
	    	parent.layer.closeAll();
	  	}  
	</script>
</body>


</html>

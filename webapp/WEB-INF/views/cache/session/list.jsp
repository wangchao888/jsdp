<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
</head>
<body class="white-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-xs-12">
				<div class="tabs-container">
					<!-- 标签页 开始 -->

					<ul id="comboxs" class="nav nav-tabs">
						<li class="active "><a data-toggle="tab" href="#tab-1">用户信息</a>
						</li>
						<li class=""><a data-toggle="tab" href="#tab-2">机构信息</a></li>
						<li class=""><a data-toggle="tab" href="#tab-3">菜单信息</a></li>
					</ul>

					<!-- 内容 开始 -->
					<div class="tab-content">
						<!-- tab-1 -->
						<div id="tab-1" class="tab-pane active">
							<div class="panel-body" >
								<form role="form" id="dataForm" class="form-horizontal">
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">主键</label>
										<div class="col-xs-12">
											<span name="sid"
												class="form-control border_none border_bom_dashed">
												${userEntity.sid}</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">用户类型</label>
										<div class="col-xs-12">
											<span name="usertype"class="form-control border_none border_bom_dashed">
											<c:if test="${userEntity.usertype == '00'}">超级管理员</c:if>
											<c:if test="${userEntity.usertype == '01'}">主管用户</c:if>
											<c:if test="${userEntity.usertype == '02'}">企业用户</c:if>
											<c:if test="${userEntity.usertype == '03'}">银行用户</c:if>
											<c:if test="${userEntity.usertype == '04'}">单位用户</c:if>
											</span>
										</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-12 control-label">部门编号</label>
										<div class="col-xs-12">
											<span name="orgid" class="form-control border_none border_bom_dashed">${userEntity.orgid}</span>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">登陆名</label>
										<div class="col-xs-12">
											<span name="loginname"
												class="form-control border_none border_bom_dashed">${userEntity.loginname}</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">用户名</label>
										<div class="col-xs-12">
											<span name="username"
												class="form-control border_none border_bom_dashed">${userEntity.username}</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">密码</label>
										<div class="col-xs-12">
											<span name="passwd"
												class="form-control border_none border_bom_dashed">${userEntity.passwd}</span>
										</div>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- tab-1 结束 -->
						<!-- tab-2 开始 -->
						<div id="tab-2" class="tab-pane">
							<div class="panel-body" >
								<form role="form" id="dataForm" class="form-horizontal">
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">主键</label>
										<div class="col-xs-12">
											<span name="sid"
												class="form-control border_none border_bom_dashed">${orgEntity.sid}</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">组织名称</label>
										<div class="col-xs-12">
											<span name="usertype"
												class="form-control border_none border_bom_dashed">${orgEntity.orgname}</span>
										</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-12 control-label">行政代码</label>
										<div class="col-xs-12">
											<span name="orgid"
												class="form-control border_none border_bom_dashed">${orgEntity.zonecode}</span>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">行政级别</label>
										<div class="col-xs-12">
											<span name="loginname"class="form-control border_none border_bom_dashed">
												<c:if test="${orgEntity.orglevl == '01'}">市</c:if>
												<c:if test="${orgEntity.orglevl == '02'}">区/县</c:if>
												<c:if test="${orgEntity.orglevl == '03'}">市辖区/县</c:if>
												<c:if test="${orgEntity.orglevl == '04'}">街道办</c:if>
											</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">组织类型</label>
										<div class="col-xs-12">
											<span name="username"class="form-control border_none border_bom_dashed">
												<c:if test="${orgEntity.orgtype == '01'}">机构</c:if>
												<c:if test="${orgEntity.orgtype == '02'}">部门</c:if>
											</span>
										</div>
										</div>
									</div>
									<div class="form-group">
									<div class="col-xs-12 ">
										<label class="col-xs-12 control-label">机构标识</label>
										<div class="col-xs-12">
											<span name="passwd"class="form-control border_none border_bom_dashed">
												<c:if test="${orgEntity.orgflag == '1'}">内部机构</c:if>
												<c:if test="${orgEntity.orgflag == '2'}">外部机构</c:if>
												<c:if test="${orgEntity.orgflag == '3'}">承办银行</c:if>
											</span>
										</div>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- tab-2 结束 -->
						<!-- tab-3 开始 -->
						<div id="tab-3" class="tab-pane">
							<div class="panel-body" >
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th class="width_300">应用编号</th>
											<th>菜单名称</th>
											<th>菜单链接</th>
											<th>菜单序号</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${navMenuList}" var="navMenu" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${navMenu.sid}</td>
												<td>${navMenu.appid }</td>
												<td>${navMenu.menuname }</td>
												<td>${navMenu.menuurl }</td>
												<td>${navMenu.menuno }</td>
												<td class="width_100 t_center"><a
														class="operation_primary" 
														onclick="childMenu('${navMenu.sid}');">子菜单</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- tab-3 结束 -->
					</div>
					<!-- 内容 结束 -->

					<!-- 标签页 结束 -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script type="text/javascript"
		src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
	
	<script type="text/javascript">
		function childMenu(sid) {
			layer.open({
				type : 2,
				title : '子菜单',
				id : 'layer_editParam',
				area : [ '75%', '75%' ],
				fixed : false, //不固定
				maxmin : true,
				content : "${ctx}/cache/session/childMenuList?pid=" + sid
						+ "&menuid=${menuid}"
			});
		}
	</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<!-- Data Tables -->

</head>
<body class="white-bg">
	<div class="wrapper wrapper-content animated fadeIn">
		<div class="row">
			<div class="col-xs-12">
				<div class="tabs-container">
					<ul id="comboxs" class="nav nav-tabs">
						<li
							class="active">
							<a data-toggle="tab" href="#tab-1">应用信息</a>
						</li>
						<li
							class="">
							<a data-toggle="tab" href="#tab-2" >机构信息</a>
						</li>
						<li
							class="">
							<a data-toggle="tab" href="#tab-3">角色信息</a>
						</li>
						<!-- <li class="">
							<a data-toggle="tab" href="#tab-4" >参数信息</a>
						</li>
						<li class="">
							<a data-toggle="tab" href="#tab-5" >字典信息</a> 
						</li> -->
						<li
							class="">
							<a data-toggle="tab" href="#tab-6" >用户信息</a>
						</li>
					</ul>

					<!-- 内容 开始 -->
					<div class="tab-content ">
						<!-- tab-1 -->
						<div id="tab-1" class="tab-pane active">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th>应用名称</th>
											<th>IP</th>
											<th>端口</th>
											<th>视图</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${appList}" var="app" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${app.sid}</td>
												<td>${app.appname}</td>
												<td>${app.appip}</td>
												<td>${app.domain}</td>
												<td>${app.webview}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- tab-1 结束 -->
						<!-- tab-2 开始 -->
						<div id="tab-2" class="tab-pane">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th class="width_300">上级主键</th>
											<th>组织名称</th>
											<th>行政代码</th>
											<th>行政级别</th>
											<th>组织类型</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orgList}" var="org" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${org.sid}</td>
												<td>${org.pid}</td>
												<td>${org.orgname}</td>
												<td>${org.zonecode}</td>
												<td><jsdp:dicshow dictno="68"
														dictlabel="${org.orglevl}"></jsdp:dicshow></td>
												<td class="width_50 t_center"><jsdp:dicshow dictno="67"
														dictlabel="${org.orgtype}"></jsdp:dicshow></td>
												<td class="width_50 t_center"><a
														class="operation_primary" 
														onclick="userEntity('${org.sid}');">用户</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- tab-2 结束 -->
						<!-- tab-3 开始 -->
						<div id="tab-3" class="tab-pane">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th>角色名称</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${roleList}" var="role" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${role.sid}</td>
												<td>${role.rolename}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- tab-3 结束 -->
						<div id="tab-4" class="tab-pane">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th>参数编号</th>
											<th>参数值</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${paramList}" var="paramList" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${paramList.sid}</td>
												<td>${paramList.paramno}</td>
												<td>${paramList.paramvalue}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div id="tab-5" class="tab-pane">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th>字典标识</th>
											<th>字典名称</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${dicTypeList}" var="dicType" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${dicType.sid}</td>
												<td>${dicType.dictno}</td>
												<td>${dicType.dictname}</td>
												<td><button class="btn btn-primary btn-xs"
														type="button"
														onclick="dictContentEntity('${dicType.dictno}');">字典明细</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div id="tab-6" class="tab-pane">
							<div class="panel-body">
								<table class="table table-bordered table-hover "
									id="datatable" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th name="center" class="width_44">序号</th>
											<th class="width_300">主键</th>
											<th>用户类型</th>
											<th class="width_300">机构主键</th>
											<th>登陆名</th>
											<th>用户名</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userList}" var="user" varStatus="vs">
											<tr>
												<td class="t_center">${vs.index+1 }</td>
												<td>${user.sid}</td>
												<td>
												<c:if test="${user.usertype == '00'}">超级管理员</c:if>
												<c:if test="${user.usertype == '01'}">主管用户</c:if>
												<c:if test="${user.usertype == '02'}">企业用户</c:if>
												<c:if test="${user.usertype == '03'}">银行用户</c:if>
												<c:if test="${user.usertype == '04'}">单位用户</c:if>
												</td>
												<td>${user.orgid}</td>
												<td>${user.loginname}</td>
												<td>${user.username}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- 内容 结束 -->
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
	<script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
	
	<script type="text/javascript">
		function dictContentEntity(dictno) {
			layer.open({
				type : 2,
				title : false,
				shadeClose : true,
				shade : 0.8,
				closeBtn : true,
				area : [ '75%', '75%' ],
				content : "${ctx}/cache/global/dictContentList?dictno="
						+ dictno + "&menuid=${menuid}"
			});
		}

		function userEntity(orgid) {
			layer.open({
				type : 2,
				title : false,
				shadeClose : true,
				shade : 0.8,
				closeBtn : true,
				area : [ '75%', '75%' ],
				content : "${ctx}/cache/global/userList?orgid=" + orgid
						+ "&menuid=${menuid}"
			});
		}
	</script>
</body>
</html>

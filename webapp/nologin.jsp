<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Error</title>
		<link rel="stylesheet" href="${ctx}/static/css/error.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="cont">
				<div class="contLeft fleft">
					<img src="${ctx}/static/img/error/pic01.png" />
				</div>
				<div class="contRight fright">
					<h1>抱歉，登录失败！</h1>
					<h2>Sorry, login failed!</h2>
					<p>仔细找过啦，没有发现您要找的页面。最可能的原因是：</p>
					<p>一、您还未登录，请先登录；</p>
					<p>二、您长时间未操作，登陆失效，请重新登录。</p>
					<p>三、用户在其他客户端登录，被迫下线</p>
					<div>
						<button onclick="login();">登&nbsp;&nbsp;录</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	 <script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript">
    function login(){
    	window.top.location.href="${sessionScope.jsdp_url}";
    }
    </script>
</html>

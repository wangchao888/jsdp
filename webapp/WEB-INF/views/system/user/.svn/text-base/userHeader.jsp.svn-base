<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<style type="text/css">
		  *{margin:0;padding:0}
		  img{width:120px;height:120px;}
		  ul{list-style:none;overflow:auto; display: table;margin:0 auto;}	
		  ul li{float:left;padding:20px;}
		  .man_head,.woman_head{margin:0 auto;text-align:center;margin:20px;}	   
		</style>
	</head>
	<body>
		<div class="head">
			<div class="man_head">
				<h1>Man</h1>
				<ul>
					<li><img src="${ctx}/static/img/user_header/man1.png" value="user1" id="/static/img/user_header/man1.png"/></li>
					<li><img src="${ctx}/static/img/user_header/man2.png" value="user2" id="/static/img/user_header/man2.png"/></li>
					<li><img src="${ctx}/static/img/user_header/man3.png" value="user3" id="/static/img/user_header/man3.png"/></li>
					<li><img src="${ctx}/static/img/user_header/man4.png" value="user4" id="/static/img/user_header/man4.png"/></li>
					<li><img src="${ctx}/static/img/user_header/man5.png" value="user5" id="/static/img/user_header/man5.png"/></li>
				</ul>
			</div>
			<div class="woman_head">
				<h1>Woman</h1>
				<ul>
					<li><img src="${ctx}/static/img/user_header/woman1.png" value="user6" id="/static/img/user_header/woman1.png"/></li>
					<li><img src="${ctx}/static/img/user_header/woman2.png" value="user7" id="/static/img/user_header/woman2.png"/></li>
					<li><img src="${ctx}/static/img/user_header/woman3.png" value="user8" id="/static/img/user_header/woman3.png"/></li>
					<li><img src="${ctx}/static/img/user_header/woman4.png" value="user9" id="/static/img/user_header/woman4.png"/></li>
					<li><img src="${ctx}/static/img/user_header/woman5.png" value="user10" id="/static/img/user_header/woman5.png"/></li>
				</ul>
			</div>
		</div>
		 <script src="${ctx}/static/js/Adminlte/jquery.min.js"></script> 
		 <script type="text/javascript">
		    $(document).ready(function(){
		    	$('img').bind('click',function(){
		    	   var headicon = $(this).attr('value');
		    	   var icopath = $(this).attr('id');
		    	   var url = $(this).attr('src');
		    	   parent.layer.closeAll();
		    	   $.ajax({
		    		    url:"${ctx}/system/user/update",
		    	        type: 'POST',
		    	        data:{headicon:headicon,icopath:icopath},
		    	        dataType:"json",
		    	        success : function(data) {
		    	        	parent.changeHeader(url); 
		    	        }     
		    	    });
		    	});
		    });
		 </script>
	</body>
</html>
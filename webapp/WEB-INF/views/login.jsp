<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
	<title>${sysName }</title>
	<link rel="stylesheet" href="${ctx }/static/css/login.css">
    <link rel="shortcut icon" href="${ctx }/static/favicon.ico" type="image/x-icon">
	<link href="${ctx }/static/css/popup-box.css" rel="stylesheet" type="text/css" media="all" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="维修资金" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script src="${ctx }/static/js/jquery.magnific-popup.js" type="text/javascript"></script>
	<script src="${ctx}/static/layer/layer.js"></script>
</head>
<body>
    <div class="lxwm">
        <li><a href="#">联系我们</a></li>
        <li><a href="#">控件下载</a></li>
        <li><a href="#">帮助中心</a></li>
    </div>
	<c:if test="${testFlag == '0' }">
    <h1 class="logo" style="background:url(${ctx }/static/img/log/logo.png) no-repeat center;"></h1>
    </c:if>
	<c:if test="${testFlag == '1' }">
    <h1 class="logo" style="background:url(${ctx }/static/img/log/logo_test.png) no-repeat center;"></h1>
    </c:if>
	<div class="w3layouts">
    <div class="signup-agileinfo xfbl">
			<h3>关于</h3>
			<p>嘉友互联软件多年来一直专注于住宅专项维修资金领域，致力于为客户提供全面的解决方案，基于多年的产品开发经验、业务理解、政策分析，全新推出了住宅专项维修资金管理平台（HFMP 2017版），全面支持住宅专项维修资金管理办法（建设部、财政部令第165号）,并灵活兼顾各地方政策。</p>
			 <div class="more">
				<a class="book popup-with-zoom-anim button-isi zoomIn animated" data-wow-delay=".5s" onclick="addReference()">单位注册</a>				
			</div> 
		</div>
		<div class="signin-agile">
			<h2>登录</h2>
			<form action="#" method="post">
				<input type="text" name="username" id="username"  placeholder="用户名" maxlength="20"  required>
                <!-- <p class="ukey-name"><span>用户</span>山东嘉友互联软件股份有限公司</p>  -->
				<input type="password" name="password" id="password"  placeholder="密码" maxlength="20" required>
				<!--<ul>
					<li>
						<input type="checkbox" id="brand1" value="">
						<label for="brand1"><span></span>记住密码</label>
					</li>
				</ul>
				<a href="#">忘记密码?</a>--><br><br><br>
				<div class="clear"></div>
				<input type="button" value="登录" data-login = "true">
			</form>
		</div>		
		<div class="clear"></div>
	</div>
	<div class="footer-w3l">
	  <p class="agileinfo"> 2017&copy; CopyRight<img src="${ctx }/static/img/join.png">版权所有 </p>
	</div>
	<div class="pop-up"> 
	<div id="small-dialog" class="mfp-hide book-form">
		<h3>注册 </h3>
			<form action="#" method="post">
				<input type="text" name="Name" placeholder="用户名" required/>
				<input type="text" name="Email" class="email" placeholder="邮件" required/>
				<input type="password" name="Password" class="password" placeholder="密码" required/>
				<input type="password" name="Password" class="password" placeholder="重复密码" required/>					
				<input type="submit" value="点击注册">
			</form>
	</div>
</div>	
<script src="${ctx }/static/js/placeholder.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.popup-with-zoom-anim').magnificPopup({
		type: 'inline',
		fixedContentPos: false,
		fixedBgPos: true,
		overflowY: 'auto',
		closeBtnInside: true,
		preloader: false,
		midClick: true,
		removalDelay: 300,
		mainClass: 'my-mfp-zoom-in'
	});
	$('[data-login = "true"]').bind('click',login);
	 var random_bg=Math.floor(Math.random()*10+1);
     var bg='url(${ctx }/static/img/bg/bg_'+random_bg+'.jpg)';
     $("body").css("background-image",bg);
	 if(window.top!==window.self){window.top.location=window.location};
	 $("#username").focus();
		/* $("#username").keydown(function(event){
			if(event.keyCode==13){	
				alert('点击了回车')
			    $("#password").focus();		
			}
		});
		$(".signin-agile #password").keydown(function(event){
			if(event.keyCode==13){
				alert('又点击了回车')
			  login();
			}
		});  */
		$('.signin-agile').keydown(function(){
			if (event.keyCode == 13){
		        if (event.srcElement.name == "password"){
		        	login();
		        } else{
		        	$("#password").focus();		
		        }
		        return false ;
		    }else{
		        return true ;
		    }
		})
		
		function login() {	 
    		var errorMsg = "";
    		var username = $("#username").val();
   		    var password = $("#password").val();
   		    if(username == ""){
   			    alert("登录用户名不能为空！");
   			    return false;
   		    }else if(password == ""){
   			    alert("登录密码不能为空！");
   			    return false;
   		    }else{   		    
    		    //登录处理
    		    $.post("${ctx}/login",
    		    {"username":username,"password":password},
    		    function(result){
    		        if(result == null){
    		        	alert("&nbsp;&nbsp;登陆失败！");
    		            return false;
    		        }else if(result.state=="true" || result.state==true){
    			        //$(".login_info").html("&nbsp;&nbsp;登录成功，正在转到主页...");
    			        //window.location="${ctx}/main?sessionId="+result.sessionId;
    			        window.location="${ctx}/main";
    			    }
    		        else{
    					alert(result.message);
    		        }
    		    },"json");
    		}
        }
})
function addReference(){
    		layer.open({ 
    			title:'单位注册',
    			type: 2, 
	    		id:'layer_addParam',
	    		area: ['50%', '75%'],
	    		fixed: false, //不固定
	    		maxmin: true,
        	    content: '${address}/hfmp/build/reference/initAddRefer'
            });
    	}
</script>
<body>
</html>
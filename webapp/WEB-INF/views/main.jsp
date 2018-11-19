<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <title>${sysName }</title>
  <link rel="shortcut icon" href="${ctx }/static/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link href="${ctx}/static/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet"> 
  <!-- web-icons -->
  <link rel="stylesheet" href="${ctx}/static/fonts/web-icons/web-icons.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${ctx}/static/css/Adminlte/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${ctx}/static/css/Adminlte/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx}/static/css/Adminlte/_all-skins.min.css">
  <!-- 框架样式 -->
  <link rel="stylesheet" href="${ctx}/static/css/Adminlte/frame.css">
  <!-- Google Font -->
  <link rel="stylesheet" href="${ctx}/static/css/Adminlte/googleapis-fonts/fonts.css">
  <style type="text/css">
  .control-sidebar-tabs li:not(:last-child){display:none;}
   .control-sidebar-tabs{display:none;}
  .main-header{
	line-height:1
   }
   #water_box {
	  margin: auto;
	  position: relative;
	}  
	.box {
	  float: left;
	  padding: 10px;
	  border: 1px solid #ccc;
	  background: #f7f7f7;
	  box-shadow: 0 0 8px #ccc;
	}  
	.box:hover { box-shadow: 0 0 10px #999; }  
	.box img { width: 200px; }
	.sidebar-collapse .treeview a,.sidebar-collapse .treeview ul{
	   background:#FCFCFC;
	}
  </style>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <span href="index2.html" class="logo pos_r">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>HFMP</b></span>
      <!-- logo for regular state and mobile devices -->
      <c:if test="${testFlag == '0' }">
      	<span class="logo-lg pos_r"><b>${sysName }</b></span>
      </c:if>
	  <c:if test="${testFlag == '1' }">
	  	<span class="logo-lg"><b>${sysName }</b></span>
	  	<img class="pos_a right_0 top_0" src="${ctx }/static/img/testBan.png"/>
      </c:if>
    </span>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- 顶部菜单 -->
      <ul class="nav navbar-nav" id="myTab">
       
      </ul>
      <!-- /.顶部菜单 -->
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu" >
            <!-- Menu toggle button -->
            <a class="dropdown-toggle" data-toggle="dropdown"  id="messages_toggle">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success" id="msgLabel">${count}</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header" id="messageHeader">你有${count}条未读信息</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu" id="messages">
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="javascript:void(0);"onclick = seeAll() id="allMessage">查看所有信息</a></li>
            </ul>
          </li>
       
          <!-- Tasks Menu -->
<!--           <li class="dropdown tasks-menu"> -->
<!--             Menu Toggle Button -->
<!--             <a class="dropdown-toggle" data-toggle="dropdown"> -->
<!--               <i class="fa fa-flag-o"></i> -->
<!--               <span class="label label-danger">9</span> -->
<!--             </a> -->
<!--             <ul class="dropdown-menu"> -->
<!--               <li class="header">你有9个待办任务</li> -->
<!--               <li> -->
<!--                 Inner menu: contains the tasks -->
<!--                 <ul class="menu"> -->
<!--                   <li>Task item -->
<!--                     <a> -->
<!--                       Task title and progress text -->
<!--                       <h3> -->
<!--                         保利花园维修项目申请 -->
<!--                         <small class="pull-right">20%</small> -->
<!--                       </h3> -->
<!--                       The progress bar -->
<!--                       <div class="progress xs"> -->
<!--                         Change the css width attribute to simulate progress -->
<!--                         <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" -->
<!--                              aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"> -->
<!--                           <span class="sr-only">20% 完成</span> -->
<!--                         </div> -->
<!--                       </div> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   end task item -->
<!--                 </ul> -->
<!--               </li> -->
<!--               <li class="footer"> -->
<!--                 <a href="#">查看所有待办任务</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="${ctx }${icoPath}" class="user-image" alt="User Image">${userName }
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">${sessionScope.userbean.username }</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header" id="user_header">
                <img src="${ctx }${icoPath}" class="img-circle" alt="User Image">
                <p>
                  ${userName }
                  <small>${rootOrgName }</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a class="btn btn-default btn-flat" id='user_data'>密码修改</a>
                </div>
                <div class="pull-right">
                  <a class="btn btn-default btn-flat">关闭</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- navbarFullscreen -->
          <li id="admui_navbarFullscreen">
             <a class="icon icon-fullscreen"></a>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- 左侧布局，包含logo和sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
    
      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" id="search_all" placeholder="Search...">
          <span class="input-group-btn">
              <button type="button" name="search" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu 左侧菜单 -->
      <ul class="sidebar-menu" data-widget="tree" id="side-menu">
       
      </ul>
      <!-- /.sidebar-menu 左侧菜单 -->
    </section>
    <!-- /.sidebar -->
  </aside>
  <!-- /.左侧布局，包含logo和sidebar -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <!-- <section class="content-header"> -->
      <div class="content-tabs">
         <button class="roll-nav roll-left J_tabLeft">
             <i class="fa fa-backward"></i>
         </button>
         <nav class="page-tabs J_menuTabs">
             <div class="page-tabs-content">
                 <a id="s12" href="javascript:;" class="active J_menuTab" data-id="${ctx}/index">首页</a>
             </div>
         </nav>
         <button class="roll-nav roll-right J_tabRight">
             <i class="fa fa-forward"></i>
         </button>
         <div class="btn-group roll-nav roll-right">
             <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
             </button>
             <ul role="menu" class="dropdown-menu dropdown-menu-right">
                 <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                 <li class="divider"></li>
                 <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                 <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
             </ul>
         </div>
         <a href="${ctx }/logout?userid=${userid}" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
      </div>
    <!-- </section> -->

    <!-- Main content -->
    <!-- <section class="content container-fluid"> -->
      <div class="content container-fluid J_mainContent" id="content-main"><!-- 内容页 ${ctx}/right -->
         <iframe class="J_iframe" name="iframe0" width="100%" height="100%"  scrolling="yes" src="${ctx}/index" frameborder="0" data-id="${ctx}/index" seamless></iframe>
      </div> 

    <!-- </section> -->
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li ><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery -->
<script src="${ctx}/static/js/Adminlte/jquery.min.js"></script> 
<!-- Bootstrap 3.3.7 -->
<script src="${ctx}/static/js/Adminlte/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/static/js/Adminlte/adminlte.min.js"></script>
<script src="${ctx}/static/js/Adminlte/demo.js"></script>
<script src="${ctx}/static/js/Adminlte/reload.js"></script>
<!-- 加密 -->
<script src="${ctx}/static/js/Adminlte/base64.min.js"></script>
<!-- 禁止url参数显示 -->
<script src="${ctx}/static/js/Adminlte/url_prohibite.js"></script>
<!-- 弹出框引入 -->
<script src="${ctx}/static/layer/layer.js"></script>
<!-- websocket 引入 -->
<script type="text/javascript" src="${ctx}/static/js/websocket/sockjs.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/websocket/stomp.min.js"></script>
<!--   瀑布流插件 -->
<script src="${ctx}/static/js/masonry-docs.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    	var navMenus = $.parseJSON('${navMenus}');
    	var leftMenus =$.parseJSON('${leftMenus}');
        var fullScreenState = 0;
         getNavMenus();
         getLeftMenus(navMenus[0].sid,leftMenus[0]);
    	 $(window).resize(function() {	
    		 getNavMenus();
    	 }); 
          /*-----------  全屏 -----------------*/
        $("#admui_navbarFullscreen").click(function(){
        	if(fullScreenState==0){
        		fullScreen();
        		fullScreenState=1;
        	}else{
        		exitFullScreen();
        		fullScreenState=0;
        	}
        });
        $("#user_header").bind('click',function(){
        	layer.open({ 
        		type: 2, 
        		title:'设置头像',
        		id:'layer_addParam',
        		area: ['75%', '75%'],
        		fixed: false, //不固定
        		maxmin: true,
        	    content: '${ctx}/system/user/userHeader' 
        	});	
        });
        //展示用户数据 修改用户密码
        $("#user_data").bind('click',function(){
//         	layer.open({ 
//         		type: 2, 
//         		title:'用户数据修改',
//         		id:'layer_addParam',
//         		area: ['60%', '45%'],
//         		fixed: false, //不固定
//         		maxmin: true,
//         		btn: ['确定', '取消'],
//         	    yes: function(index, layero){
//         	      document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
//         	    },  
//         	    btn2: function(index, layero){
//         	    	layer.confirm('确定取消操作?', {
//         	    		  btn: ['确定'] //按钮
//         	    		}, function(){
//         	    			layer.closeAll();
//         	    		});
//         		    return false;
//         	    },
//         	    content: '${ctx}/system/user/editPass'
//             });
        	layer.open({ 
        		type: 2, 
        		title:'用户数据修改',
        		id:'lay123wsdqaram',
        		area: ['60%', '45%'],
        		fixed: false, //不固定
        		maxmin: true,
        		btn: ['确定', '取消'],
        	    yes: function(index, layero){
        	      document.getElementById("layui-layer-iframe"+index).contentWindow.saveForm();
        	    },  
        	    btn2: function(index, layero){
        	    	layer.confirm('确定取消操作?', {
        	    		  btn: ['确定'] //按钮
        	    		}, function(){
        	    			layer.closeAll();
        	    		});
        		    return false;
        	    },
        	    content: '${ctx}/system/user/editPass'
            });
        });

        // websocket 消息通知处理
        var stompClient = null;
        var wsUri = '${coreAppURL}/getNoticeinfo';
        var socket = new SockJS(wsUri);
        stompClient = Stomp.over(socket);
        stompClient.connect({name:'${userid}'}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/user/topic/notices', function (retData) {
                var labelVal = $('#msgLabel').text();
                $('#msgLabel').html(parseInt(labelVal,10)+parseInt(JSON.parse(retData.body),10));
                $('#messageHeader').html('你有'+parseInt(labelVal,10)+parseInt(JSON.parse(retData.body),10)+'条未读消息');
            });
        });
      $('#messages_toggle').bind('click',function(){
    	  $.ajax({
              type: "GET",
              url: "${ctx}/system/notice/mainList",
              data:{userid:'${userid}'},
              dataType: "json",
              success: function(data){
                          $('#messages').html('');   //清空resText里面的所有内容                          
                          $.each(data, function(commentIndex, comment){
                        	  var str = ''; 
                        	  var $li = $('<li></li>');
                        	  str += '<a href="javascript:void(0);">';
                        	  str += '<div class="pull-left">';
                        	  str += '<img src="${ctx}'+comment.ICOPATH +'" class="img-circle" alt="User Image">';
                        	  str += '</div>';
                        	  str += '<h4>';
                        	  str += '<p class="w133 vor_hide text_ovr_hide">'+comment.NTCTITLE+'</p>';
                        	  str += '<small><i class="fa fa-clock-o">'+comment.RELEASEDATE+'</i></small>';
                        	  str += '</h4>';
                        	  str += '<p>'+comment.DESCRIBE+'</p>' ;
                        	  str += '</a>';
                        	  $li.html(str);
                        	  $li.bind('click',comment.SID,function(event){
                        		  $.ajax({
                                      type: "GET",
                                      url: "${ctx}/system/notice/mainList",
                                      data:{userid:'${userid}'},
                                      dataType: "json",
                                      success: function(data){
                                    	  var dataLength = data.length-1;
                                    	  $('#msgLabel').html(dataLength);
                                    	  $('#messageHeader').html('你有'+dataLength+'条未读消息');
                                    	  layer.open({ 
                                  	 		type: 2, 
                                  	 		title:'消息详情',
                                  	 		id:'layer_addParam',
                                  	 		area: ['75%', '75%'],
                                  	 		fixed: false, //不固定
                                  	 		maxmin: true,
                                  	 	    content: '${ctx}/system/notice/allMessage?sid='+event.data+"&userid="+"${userid}"
                                  	 	});	  
                                    	  
                                      }
                        		  });
                        		 
                        	  });
                        	  $('#messages').append($li);
                          });                         
                       }
          });
      });
      $("#search_all").bind('click',function(){ 
    	  var str = '<div class="input-group" style="width:300px;margin:20px auto;"><input type="text" class="form-control" id="searchAllInput" placeholder="Search..."/>'
    		  str += '<span class="input-group-btn">'
    		  str += ' <button type="button" name="search" id="searchItem" class="btn btn-flat"><i class="fa fa-search"></i>'
    		  str += '</button>'
    		  str += '</span>'
    		  str += '</div>'
    	  str += '<div id="masonry" class="container-fluid" style="padding-top:20px;">'; 
    	  $.each(navMenus, function(i, item) {
    		  str += '<ul class="box" style="margin-bottom: 20px;border-radius: none;float: left;width:230px;list-style:none;">';
    		  str += '<li data='+item.sid+'>';
    		  str += '<i class="'+item.menuico+'" style="padding-right:7px;"></i>';
    		  str +=  item.menuname;
    		  str += '</li><ul>';   		 
    			  $.each(leftMenus[i][item.sid], function(j,iTem) {
    				  if(iTem.childmenu){
    					  str += '<li>';
    					  str += '<i class="'+iTem.menuico+'" style="padding-right:7px;"></i><span>'+iTem.menuname+'</span>';
    					  str += '<span>';
    					  str += '<i></i>';
    					  str += '</span>';
    					  str += '<ul>';
    	    			   $.each(iTem.childmenu, function(k, ITEM) {
    	    				   str += '<li name="'+iTem.childmenu[k].pid+'">';
    	    				   str += '<a class="J_menuItem" href ='+ITEM.menuurl+' menuid='+Base64.encode(ITEM.sid)+' activeData='+item.sid+' sessionId='+Base64.encode(ITEM.sessionId)+'>';
    	    				   str += '<i></i>'+ ITEM.menuname;
    	    				   str += '</a>';
    	    				   str += '</li>';
    	    			   });     
    	    			   str += '</ul>';
    	    			   str += '</li>';
    				  }else{
    					  str +=  '<li>';
    					  str +=  '<a class="J_menuItem" href ='+iTem.menuurl+' menuid='+Base64.encode(iTem.sid)+' activeData='+item.sid+' sessionId='+Base64.encode(iTem.sessionId)+'>';
    					  str +=  '<i class="'+iTem.menuico+'" style="padding-right:7px;"></i><span>'+iTem.menuname+'</span>';
    					  str +=  '</a>';
    					  str +=  ' </li>';
    				  }
    			  });
    		  str += '</ul></ul>';   		 
    	  }); 
    	  str += '</div>'
          layer.open({ 
      		type: 1, 
      		title:'选择菜单',
      		area: ['80%', '80%'],
      		fixed: false, //不固定
      		maxmin: false,
      	    content:str
          });
    	  addingMultipletags();
    	  var $container = $('#masonry');
    	    $container.imagesLoaded(function() {
    	        $container.masonry({
    	                itemSelector: '.box',
    	                gutter: 20,
    	                isAnimated: true,
    	            });
    	     });
    	    
    	  $('#searchItem').bind('click',function(){ 	   		
    		$('#masonry ul:hidden').show();
    		$('#masonry li:hidden').show();
    		var value =  $(this).parent().parent('.input-group').children('input').val();   
    		$.each($("#masonry").find('ul.box'),function(index,item){
    			if($(item).html().indexOf(value) !== -1){
    				 $.each($("#masonry").find('li'),function(index,iTem){
    	    			 if($(iTem).html().indexOf(value) !== -1){
    	    				 $(iTem).show()
    	    			 }else{
    	    				 $(iTem).hide()
    	    			 }
    	    		 })  
	   			 }else{
	   				 $(item).hide()
	   			 }
    		}) 
 	    	 var $container = $('#masonry');
 	    	    $container.imagesLoaded(function() {
 	    	        $container.masonry({
 	    	                itemSelector: '.box',
 	    	                gutter: 20,
 	    	                isAnimated: true,
 	    	        });
 	    	  });
    	  })  
    	  $('#searchAllInput').bind('keypress',function(event){
              if(event.keyCode == "13")    
              {
            	  $('#masonry ul:hidden').show();
  	    		$('#masonry li:hidden').show();
  	    		var value =  $(this).val();   
  	    		$.each($("#masonry").find('ul.box'),function(index,item){
  	    			if($(item).html().indexOf(value) !== -1){
  	    				 $.each($("#masonry").find('li'),function(index,iTem){
  	    	    			 if($(iTem).html().indexOf(value) !== -1){
  	    	    				 $(iTem).show()
  	    	    			 }else{
  	    	    				 $(iTem).hide()
  	    	    			 }
  	    	    		 })  
  		   			 }else{
  		   				 $(item).hide()
  		   			 }
  	    		}) 
  	 	    	 var $container = $('#masonry');
  	 	    	    $container.imagesLoaded(function() {
  	 	    	        $container.masonry({
  	 	    	                itemSelector: '.box',
  	 	    	                gutter: 20,
  	 	    	                isAnimated: true,
  	 	    	        });
  	 	    	  });
              }
          });
      });
      function getNavMenus(){
     	 var $totalWidth = 0;
     	 var activeIndex = $('li[name="topMenu"]').index($('li[name="topMenu"].active')[0]);
 	      $('#myTab').children("li").remove();
         	$.each(navMenus, function(i, item) {
         		var navli  = '';
         			navli += '<li name="topMenu" data='+item.sid+'>';
         			navli += '<a>';
         			navli += '<i class="'+item.menuico+'" style="padding-right:7px;"></i>';
         			navli += item.menuname;
         			navli += '</a>';
         			navli += '</li>';
         		$('#myTab').append(navli);
         		var $li_width = $('ul>[data='+item.sid+']').width();
         		var $ul_width =  $(window).width() - 500;
         		$totalWidth += $li_width;
         		if($totalWidth > $ul_width){
         			var $li_all = $('#myTab').children('li[name="topMenu"]');
         			var $li_last = $li_all[$li_all.length-1];
         			if($('#myTab').children('li.dropdown').length > 0){
         				$('#myTab .dropdown>ul').append($li_last);
         			}else{
         				var more = '';
         		        more += '<li class="dropdown">';
         		        more += '<a class="dropdown-toggle" data-toggle="dropdown">';
         		        more +='<i class="icon wb-more-vertical"></i>';
         		        more +='</a>';
         		        more +='<ul class="dropdown-menu"></ul>';
         		        more +='</li>';
         		        $('#myTab').append(more);
         		        $('#myTab ul.dropdown-menu').append($li_last);
         			}
         		}
         		if (i==(activeIndex ? activeIndex : 0)) {
         	    	$($('li[name="topMenu"]')[i]).attr('class','active');
         	    };  
         		$('ul>[data='+item.sid+']').bind('click',function(){
         			getLeftMenus($(this).attr('data'),leftMenus[i]);
         			$('#myTab').find('li[name="topMenu"]').removeClass('active');
         			$(this).addClass('active');
         		});
     	
         	});
     	}
       function getLeftMenus(sid,leftdatas,activeText,activeName){
    	   $('#side-menu>li').remove();
    	   $.each(leftdatas[sid], function(i, item) {
    		   if(item.childmenu){
    			   var liMenu = '';
    			   liMenu += item.sid == activeName ? '<li class="treeview menu-open">' : '<li class="treeview">';
    			   liMenu += '<a href="#" style="font-size:14px;color:#4A4A4A;">';
    			   liMenu += '<i class="'+item.menuico+'"></i> <span>'+item.menuname+'</span>';
    			   liMenu += '<span class="pull-right-container">';
    			   liMenu += '<i class="fa fa-angle-left pull-right"></i>';
    			   liMenu += '</span>';
    			   liMenu += '</a>';
    			   liMenu += item.sid == activeName ? '<ul class="treeview-menu" style="display:block;">' : '<ul class="treeview-menu">';
    			   $.each(item.childmenu, function(j, iTem) {
    				   var childMenu = '';
    				   childMenu +='<li name="'+item.childmenu[j].pid+'">' 
    				   childMenu += iTem.menuname == activeText ?
    						   '<a class="J_menuItem" href='+iTem.menuurl+' style="color:#168EE3;background-color:rgba(43, 155, 235,0.09);border-left:2px solid #168EE3" menuid='+Base64.encode(iTem.sid)+' sessionId='+Base64.encode(item.sessionId)+'>' :
    					       '<a class="J_menuItem" href='+iTem.menuurl+' style="color:#666666;" menuid='+Base64.encode(iTem.sid)+' sessionId='+Base64.encode(item.sessionId)+'>';
    			       childMenu += '<i class="fa fa-circle-o"></i>'+ iTem.menuname;
    				   childMenu += '</a>';
    			       childMenu += '</li>';
    			       liMenu += childMenu;
    			   });     
    		       liMenu += '</ul>';
    		       liMenu += '</li>';
    		       $('#side-menu').append(liMenu);
    		   }else{
    			   var liMenu = '';
    			   liMenu +=  '<li>';
    			   liMenu +=  item.menuname == activeText ?
    				   '<a class="J_menuItem" href='+item.menuurl+' style="color:#168EE3;font-size:14px;" menuid='+Base64.encode(item.sid)+' sessionId='+Base64.encode(item.sessionId)+'>' :
    				   '<a class="J_menuItem" href='+item.menuurl+' style="color:#4A4A4A;font-size:14px;" menuid='+Base64.encode(item.sid)+' sessionId='+Base64.encode(item.sessionId)+'>';
    			   liMenu +=  '<i class="'+item.menuico+'"></i><span style="padding-left:5px;">'+item.menuname+'</span>';
    			   liMenu +=  '</a>';
    			   liMenu +=  ' </li>';
    			   $('#side-menu').append(liMenu);
    		   };
    	   });
    	   $('#side-menu li>a.J_menuItem').bind('click',function(){
    		   $('li>a.J_menuItem').css({'color':'#666666','background-color':'rgba(43, 155, 235,0)','border-left':'0px solid #168EE3'});
    		   $(this).css({'color':'#168EE3','background-color':'rgba(43, 155, 235,0.09)','border-left':'2px solid #168EE3'});
    	   });
    	   addingMultipletags();  
       };    
       /*----------- 全屏 -----------*/
       function fullScreen() {
         var el = document.documentElement;
         var rfs = el.requestFullScreen || el.webkitRequestFullScreen || 
             el.mozRequestFullScreen || el.msRequestFullScreen;
         if(typeof rfs != "undefined" && rfs) {
           rfs.call(el);	
         }else if(typeof window.ActiveXObject != "undefined") {	
           //for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏	
           try{
        	   var wscript = new ActiveXObject("WScript.Shell");	
               if(wscript != null) {
                   wscript.SendKeys("{F11}");	
               };
           }catch(err){
			   alert('注：ie调用ActiveX控件，需要在ie浏览器安全设置里面把 ‘未标记为可安全执行脚本的ActiveX控件初始化并执行脚本’ 设置为启用')
		  }
          	
         };
       }
         /* ---------- 退出全屏 -----------*/
       function exitFullScreen() {
         var el = document;
         var cfs = el.cancelFullScreen || el.webkitCancelFullScreen || 
       	      el.mozCancelFullScreen || el.exitFullScreen;
         if(typeof cfs != "undefined" && cfs) {
       	 cfs.call(el);
         } else if(typeof window.ActiveXObject != "undefined") {
       	  //for IE，这里和fullScreen相同，模拟按下F11键退出全屏
         var wscript = new ActiveXObject("WScript.Shell");
        if(wscript != null) {
       	  wscript.SendKeys("{F11}");
         };
        };
       };  
       function addingMultipletags(){
     	  $(function() {
    	    	function f(l) {
    	    		var width = 0; //定义一开始标签页内容宽度为0
    	    		$(l).each(function() { //对标签页内容进行循环
    	    			width += $(this).outerWidth(true) //计算出标签页的总长度
    	    		});
    	    		return width
    	    	}
    	    	function g(n) { //添加标签页
    	    		var o = f($(n).prevAll()), q = f($(n).nextAll());//点击左侧列表添加前后标签页的宽度
    	    		var l = f($(".content-tabs").children().not(".J_menuTabs"));//获取没有被选中的标签页宽度
    	    		var k = $(".content-tabs").outerWidth(true) - l;  //获取整个标签页的宽度
    	    		var p = 0;
    	    		if ($(".page-tabs-content").outerWidth() < k) { //根据标签页总长度与标签页已固定长度的比较，来决定所有标签的左右移动
    	    			p = 0
    	    		} else {
    	    			if (q <= (k - $(n).outerWidth(true) - $(n).next().outerWidth(true))) {
    	    				if ((k - $(n).next().outerWidth(true)) > q) {
    	    					p = o;
    	    					var m = n;
    	    					while ((p - $(m).outerWidth()) > ($(".page-tabs-content")
    	    							.outerWidth() - k)) {
    	    						p -= $(m).prev().outerWidth();
    	    						m = $(m).prev()
    	    					}
    	    				}
    	    			} else {
    	    				if (o > (k - $(n).outerWidth(true) - $(n).prev().outerWidth(
    	    						true))) {
    	    					p = o - $(n).prev().outerWidth(true)
    	    				}
    	    			}
    	    		}
    	    		$(".page-tabs-content").animate({
    	    			marginLeft : 0 - p + "px"
    	    		}, "fast")
    	    	}
    	    	function leftShift() {  //点击向左箭头的方法
    	    		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
    	    		var l = f($(".content-tabs").children().not(".J_menuTabs"));
    	    		var k = $(".content-tabs").outerWidth(true) - l;
    	    		var p = 0;
    	    		if ($(".page-tabs-content").width() < k) {
    	    			return false
    	    		} else {
    	    			var m = $(".J_menuTab:first");
    	    			var n = 0;
    	    			while ((n + $(m).outerWidth(true)) <= o) {
    	    				n += $(m).outerWidth(true);
    	    				m = $(m).next()
    	    			}
    	    			n = 0;
    	    			if (f($(m).prevAll()) > k) {
    	    				while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
    	    					n += $(m).outerWidth(true);
    	    					m = $(m).prev()
    	    				}
    	    				p = f($(m).prevAll())
    	    			}
    	    		}
    	    		$(".page-tabs-content").animate({
    	    			marginLeft : 0 - p + "px"
    	    		}, "fast")
    	    	}
    	    	function rightShift() {  //点击向右箭头的方法
    	    		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
    	    		var l = f($(".content-tabs").children().not(".J_menuTabs"));
    	    		var k = $(".content-tabs").outerWidth(true) - l;
    	    		var p = 0;
    	    		if ($(".page-tabs-content").width() < k) {
    	    			return false
    	    		} else {
    	    			var m = $(".J_menuTab:first");
    	    			var n = 0;
    	    			while ((n + $(m).outerWidth(true)) <= o) {
    	    				n += $(m).outerWidth(true);
    	    				m = $(m).next()
    	    			}
    	    			n = 0;
    	    			while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
    	    				n += $(m).outerWidth(true);
    	    				m = $(m).next()
    	    			}
    	    			p = f($(m).prevAll());
    	    			if (p > 0) {
    	    				$(".page-tabs-content").animate({
    	    					marginLeft : 0 - p + "px"
    	    				}, "fast")
    	    			}
    	    		}
    	    	}
    	    	$(".J_menuItem").each(function(index) {//页面加载时获取J_menuItem循环
    	    		if (!$(this).attr("data-index")) {//如果目标没有data-index属性时添加data-index属性,并赋值为index
    	    			$(this).attr("data-index", index);
    	    		}
    	    	});
    	    	//左侧导航栏点击事件
    	    	function addpage() {  
    	    		var o_href = $(this).attr("href"),/*获取href里的值*/ 
//    	    			m = $(this).data("index"),/*获取index里的值 */
    	    			m =Base64.decode($(this).attr("menuid")),/*获取index里的值 */
    	    			l = $.trim($(this).text()),/*获取值并去掉空格*/ 
    	    			menuid =Base64.decode($(this).attr('menuid')),
    	    			sessionId = Base64.decode($(this).attr('sessionId')),
    	    			k = true;
    	    		if (o_href == undefined || o_href == "#" || $.trim(o_href).length == 0) {//href里没有值的话返回false			
    	    			return false
    	    		}
    	    		//if(o_href.indexOf('?')>0){
    	    			//var o = o_href + '&menuid=' + menuid + '&sessionId=' +sessionId;
    	    			//var o = o_href + '&menuid=' + menuid;
    	    		//}else{
    	    			//var o = o_href + '?menuid=' + menuid;
    	    		//}
    	    		var o = o_href;
    	    		
    	    		$(".J_menuTab").each(//获取标签			
    	    				function() {
    	    					if ($(this).data("id") == o) {//判断标签页是否已存在
    	    						if (!$(this).hasClass("active")) {//判断是否包含active类
    	    							$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
    	    							g(this);
    	    							$(".J_mainContent .J_iframe").each(
    	    									function() {
    	    										if ($(this).data("id") == o) {
    	    											$(this).show().siblings(".J_iframe").hide();//隐藏所有.J_iframe只显示当前.J_iframe
    	    											return false
    	    										}
    	    									})
    	    						}
    	    						k = false;
    	    						return false
    	    					}
    	    				});
    	    		if (k) {
    	    			var p = '<a href="javascript:;" class="active J_menuTab" data-id="'
    	    					+ o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';//标签
    	    			$(".J_menuTab").removeClass("active");//移除active类
    	    			var n = '<iframe class="J_iframe" name="iframe' + m
    	    					+ '" width="100%" height="100%" src="' + o
    	    					+ '" frameborder="0" data-id="' + o
    	    					+ '" seamless></iframe>';//内容页
    	    			$(".J_mainContent").find("iframe.J_iframe").hide().parents(
    	    					".J_mainContent").append(n);//添加内容页
    	    			$(".J_menuTabs .page-tabs-content").append(p);//添加标签页
    	    			g($(".J_menuTab.active"))
    	    		}
    	    		
    	    		var activeData =  $(this).attr('activeData');
    	    		var activeName = $(this).parent().attr('name');
    	    		var activeText = $(this).text();    		
    	    		$.each($('[name="topMenu"]'), function(x, item) {
    	    			if($(this).attr('data') == activeData){
    	    				$('#myTab').find('li[name="topMenu"]').removeClass('active');
    	         			$(this).addClass('active');
    	         		    getLeftMenus($(this).attr('data'),leftMenus[x],activeText,activeName);
    	    			}
    	    		})     	
    	    		window.layer.closeAll(); 
    	    		return false;    	    		
    	    	}
    	    	$(".J_menuItem").on("click", addpage);//左侧导航栏点击
    	    	function tabClick() {  //标签页tab上的点击事件
    	    		var m = $(this).parents(".J_menuTab").data("id");//获取标签页对应内容页地址     父元素？
    	    		var l = $(this).parents(".J_menuTab").width();//获取标签页宽度     		父元素？
    	    		if ($(this).parents(".J_menuTab").hasClass("active")) {//判断标签页是否是当前页面标签页
    	    			if ($(this).parents(".J_menuTab").next().length) {//判断有没有下一个标签页
    	    				var k = $(this).parents(".J_menuTab").next(".J_menuTab:eq(0)")
    	    						.data("id");//第一个标签页的地址
    	    				$(this).parents(".J_menuTab").next(".J_menuTab:eq(0)")
    	    						.addClass("active");//给首个标签页+active类
    	    				$(".J_mainContent .J_iframe").each(function() {//获取内容页div和内容页
    	    					if ($(this).data("id") == k) {
    	    						$(this).show().siblings(".J_iframe").hide();
    	    						return false
    	    					}
    	    				});
    	    				var n = parseInt($(".page-tabs-content").css("margin-left"));
    	    				if (n < 0) {
    	    					$(".page-tabs-content").animate({
    	    						marginLeft : (n + l) + "px"
    	    					}, "fast")
    	    				}
    	    				$(this).parents(".J_menuTab").remove();
    	    				$(".J_mainContent .J_iframe").each(function() {
    	    					if ($(this).data("id") == m) {
    	    						$(this).remove();
    	    						return false
    	    					}
    	    				})
    	    			}
    	    			if ($(this).parents(".J_menuTab").prev(".J_menuTab").length) {
    	    				var k = $(this).parents(".J_menuTab").prev(".J_menuTab:last")
    	    						.data("id");
    	    				$(this).parents(".J_menuTab").prev(".J_menuTab:last").addClass(
    	    						"active");
    	    				$(".J_mainContent .J_iframe").each(function() {
    	    					if ($(this).data("id") == k) {
    	    						$(this).show().siblings(".J_iframe").hide();
    	    						return false
    	    					}
    	    				});
    	    				$(this).parents(".J_menuTab").remove();
    	    				$(".J_mainContent .J_iframe").each(function() {
    	    					if ($(this).data("id") == m) {
    	    						$(this).remove();
    	    						return false
    	    					}
    	    				})
    	    			}
    	    		} else {
    	    			$(this).parents(".J_menuTab").remove();
    	    			$(".J_mainContent .J_iframe").each(function() {
    	    				if ($(this).data("id") == m) {
    	    					$(this).remove();
    	    					return false
    	    				}
    	    			});
    	    			g($(".J_menuTab.active"))
    	    		}
    	    		return false
    	    	}
    	    	$(".J_menuTabs").on("click", ".J_menuTab i", tabClick);
    	    	function closeOther() {  //关闭其他tab的点击事件
    	    		$(".page-tabs-content").children("[data-id]").not(":first").not(
    	    				".active").each(function() {
    	    			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
    	    			$(this).remove()
    	    		});
    	    		$(".page-tabs-content").css("margin-left", "0")
    	    	}
    	    	$(".J_tabCloseOther").on("click", closeOther);
    	    	function j() {
    	    		g($(".J_menuTab.active"))
    	    	}
    	    	$(".J_tabShowActive").on("click", j);
    	    	function e() {
    	    		if (!$(this).hasClass("active")) {
    	    			var k = $(this).data("id");
    	    			$(".J_mainContent .J_iframe").each(function() {
    	    				if ($(this).data("id") == k) {
    	    					$(this).show().siblings(".J_iframe").hide();
    	    					return false
    	    				}
    	    			});
    	    			$(this).addClass("active").siblings(".J_menuTab").removeClass(
    	    					"active");
    	    			g(this)
    	    		}
    	    	}
    	    	$(".J_menuTabs").on("click", ".J_menuTab", e);
    	    	function d() {		
    	    		//H+自带代码 不知道有什么用
    	    		var l = $('.J_iframe[data-id="' + $(this).data("id") + '"]');
    	    		var k = l.attr("src")
    	    		
    	    		/**
    	    		 * 功能：实现双击刷新页面
    	    		 * 		
    	    		 *       作者：孙晨阳
    	    		 *         	2017-04-25
    	    		 */
    	    		var o = $(this).data("id");//获取标签页对应内容页地址 
    	    		var m;	//获取标题页index	
    	    		$(".J_menuItem").each(function() {
    	    			if($(this).attr("href") == o){
    	    				//m = $(this).data("index");
    	    				m = Base64.decode($(this).attr("menuid"));
    	    			}		
    	    		})
    	    		//关闭内容页
    	    		$(".J_mainContent .J_iframe").each(function() {
    	    			if ($(this).data("id") == o) {								
    	    				$(this).remove();
    	    				return false
    	    			}
    	    		})
    	    		var n = '<iframe class="J_iframe" name="iframe' + m
    	    					+ '" width="100%" height="100%" src="' + o
    	    					+ '" frameborder="0" data-id="' + o
    	    					+ '" seamless></iframe>';//内容页	
    	    		$(".J_mainContent").find("iframe.J_iframe").hide().parents(
    	    					".J_mainContent").append(n);//添加内容页
    	    		/*------------------双击刷新结束-----------------*/
    	    	}
    	    	$(".J_menuTabs").on("dblclick", ".J_menuTab", d);
    	    	$(".J_menuTabs").on("click", "#s12", d);
    	    	$(".J_tabLeft").on("click", leftShift);
    	    	$(".J_tabRight").on("click", rightShift);
    	    	$(".J_tabCloseAll")
    	    			.on(
    	    					"click",
    	    					function() {
    	    						$(".page-tabs-content").children("[data-id]").not(
    	    								":first")
    	    								.each(
    	    										function() {
    	    											$(
    	    													'.J_iframe[data-id="'
    	    															+ $(this)
    	    																	.data("id")
    	    															+ '"]').remove();
    	    											$(this).remove()
    	    										});
    	    						$(".page-tabs-content").children("[data-id]:first")
    	    								.each(
    	    										function() {
    	    											$(
    	    													'.J_iframe[data-id="'
    	    															+ $(this)
    	    																	.data("id")
    	    															+ '"]').show();
    	    											$(this).addClass("active")
    	    										});
    	    						$(".page-tabs-content").css("margin-left", "0")
    	    					})
    	    						
    	    });
       }
       /* $(".dropdown-toggle").on("mouseover", function() {
    	   $(this).parent('li').addClass('open');
           $(this).attr('aria-expanded','true');
       })
       $(".dropdown-toggle").on("mouseout", function() {
    	   $(this).parent('li').removeClass('open');
    	   $(this).attr('aria-expanded','false');
       })
        $(".dropdown-menu").on("mouseover", function() {
    	   $(this).parent('li').addClass('open');
    	   $(this).prev(".dropdown-toggle").attr('aria-expanded','true');
       })
       $(".dropdown-menu").on("mouseout", function() {
    	   $(this).parent('li').removeClass('open');
    	   $(this).prev(".dropdown-toggle").attr('aria-expanded','false');
       }) */
 });
    function seeAll(){
  	  layer.open({ 
    		type: 2, 
    		title:'消息详情',
    		id:'layer_seeAll',
    		area: ['75%', '75%'],
    		fixed: false, //不固定
    		maxmin: true,
    	    content: '${ctx}/system/notice/allMessageList?userid='+'${userid}'
    		});	
  };
    function changeHeader(url){
    	$('.img-circle').attr('src',url);
    	$('.user-image').attr('src',url);
    };
    function open(href,name){
		var o_href = href,//链接地址
		m =Base64.decode("ZDEzYWNlNzQwYjdmNGY3ZmE0Y2E0NmYwNzI4MzI1ZWY"),//menuid 应该没有用了
		l = $.trim(name),//
		menuid =Base64.decode("ZDEzYWNlNzQwYjdmNGY3ZmE0Y2E0NmYwNzI4MzI1ZWY"),
// 		sessionId = Base64.decode("${sessionId}"),
// 		sessionId = Base64.decode("dW5kZWZpbmVk"),
		k = true;
	if (o_href == undefined || o_href == "#" || $.trim(o_href).length == 0) {//href里没有值的话返回false			
		return false
	}
	var o = o_href;
	$(".J_menuTab").each(//获取标签			
			function() {
				if ($(this).data("id") == o) {//判断标签页是否已存在
					if (!$(this).hasClass("active")) {//判断是否包含active类
						$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
						g(this);
						$(".J_mainContent .J_iframe").each(
								function() {
									if ($(this).data("id") == o) {
										$(this).show().siblings(".J_iframe").hide();//隐藏所有.J_iframe只显示当前.J_iframe
										return false
									}
								})
					}
					k = false;
					return false
				}
			});
	if (k) {
		var p = '<a href="javascript:;" class="active J_menuTab" data-id="'
				+ o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';//标签
		$(".J_menuTab").removeClass("active");//移除active类
		var n = '<iframe class="J_iframe" name="iframe' + m
				+ '" width="100%" height="100%" src="' + o
				+ '" frameborder="0" data-id="' + o
				+ '" seamless></iframe>';//内容页
		$(".J_mainContent").find("iframe.J_iframe").hide().parents(
				".J_mainContent").append(n);//添加内容页
		$(".J_menuTabs .page-tabs-content").append(p);//添加标签页
		g($(".J_menuTab.active"))
	}
	
	var activeData =  $(this).attr('activeData');
	var activeName = $(this).parent().attr('name');
	var activeText = $(this).text();    		
	$.each($('[name="topMenu"]'), function(x, item) {
		if($(this).attr('data') == activeData){
			$('#myTab').find('li[name="topMenu"]').removeClass('active');
 			$(this).addClass('active');
 		    getLeftMenus($(this).attr('data'),leftMenus[x],activeText,activeName);
		}
	})     	
	window.layer.closeAll(); 
	return false;     	
    }
    function saveMenu(id){
		  alert(id); 
	}
</script>
</body>
</html>
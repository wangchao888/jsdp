<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx}/static/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${ctx}/static/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/css/animate.min.css" rel="stylesheet">
<link href="${ctx}/static/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-select.css" rel="stylesheet">
<link href="${ctx}/static/css/loading.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>	
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6"rel="stylesheet">
<script src="${ctx}/static/layer/layer.js"></script>	
<style type="text/css">
div.dataTables_info,.dataTables_length,.dataTables_paginate{
  display:none;
} 
table#datatable {
    table-layout: fixed !important;
}
table#datatable tbody tr td {
    font-family: PingFang-SC-Medium;
    font-size: 14px;
    color: #666666;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.table > thead:first-child > tr:first-child > th {
    font-family: PingFang-SC-Medium;
    font-size: 14px;
    color: #333333;
    text-align: center;
    background-color: rgba(240,243,250,0.5);
}
table.dataTable thead .sorting:after {
    content: "";
    float: right;
    font-family: fontawesome;
    color: rgba(50,50,50,.5);
}
 .form-control{
   height:auto !important;
 }
 .form-control, .single-line{
   padding:6px 12px !important;
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
	

.container a{
  display:block;
  color:#333;
}

body{
  background:rgba(240,243,250,0.72);
}
h5 {
	font-weight: 700;
}

.panel{
   margin-bottom: 15px;
}
.container {
	width: 100% !important;
	padding: 0px;
}

.head_portrait {
	width: 70px;
	height: 60px;
	line-height: 60px;
	float: left;
}

.head_portrait img {
	width: 56px;
	height: 56px;
}

.date {
	width: 150px;
	float: left;
	text-align: center;
}

.brief_introduction {
	width: calc(100% -   220px);
	float: left;
}

.Icon {
	width: 44px;
	height: 38px;
	line-height: 38px;
	float: left;
}

.Icon img {
	width: 32px;
	height: 32px;
}

.business_name {
	width: calc(100% -   94px);
	height: 38px;
	line-height: 38px;
	float: left;
}

.number {
	width: 50px;
	height: 38px;
	line-height: 38px;
	text-align: right;
	float: left;
}

.number>p{
   font-family: MicrosoftYaHei;
	font-size: 14px;
	color: #FF4D64;
}
.panel-default>.panel-heading {
	background-color: #FFF;
}

.col-sm-3 {
	width: 20%;
}


 @media screen and (max-width:1920px) {
    .panel-body {
	padding: 15px;
	background: #FFFFFF;
    box-shadow: 0 1px 2px 0 rgba(0,0,0,0.15);
    border-radius: 4px;
}

 }
   @media screen and (max-width:1680px) {
     .panel-body {
	padding: 15px;
	background: #FFFFFF;
    box-shadow: 0 1px 2px 0 rgba(0,0,0,0.15);
    border-radius: 4px;
}

 }
  @media screen and (max-width:1366px) {
     .panel-body {
	padding: 7px;
	background: #FFFFFF;
    box-shadow: 0 1px 2px 0 rgba(0,0,0,0.15);
    border-radius: 4px;
}

 }
.panel-body .content {
	padding-bottom: 5px;
	border-bottom: 1px solid #ECECEC;
}

.panel-body .content img {
	width: 22px;
	height: 22px;
	float: right;
	cursor: pointer;
}

.panel-body .content>div:first-child {
	float: left;
	width: 32px;
	height: 22px;
	padding-right: 10px;
}

.panel-body .content>div:first-child p {
	font-family: MicrosoftYaHei;
	font-size: 14px;
	color: #4A4A4A;
	text-align: left;
	line-height: 14px;
}

.panel-body .content>div:nth-child(2) {
	float: left;
	width: calc(100% -   32px);
	height: 22px;
}

.panel-body .content>div:nth-child(2) p {
	float: left;
}

.panel-body .content>div.clear {
	clear: both;
}
.m_content .span{
    width:25%;
    float:left;
    text-align:center;
    padding:5px;
}
.m_content .span>p,.smallPanel_p{
	font-family: MicrosoftYaHei;
	font-size: 12px;
	color: #4A4A4A;
    padding:15px;
    background-image:url("${ctx}/static/img/qietu/btn_back1.png");
    background-size:100% 100%;
    border: 1px solid #ECECEC;
    border-radius: 4px;
    margin-bottom:0;
    cursor:pointer;
}
.smallPanel_p{
   background-image:url("${ctx}/static/img/qietu/btn_back2.png") !important;
}
.smallPanel_div{
   width:25%;
   float:left;
   padding:15px 5px;
}

.smallPanel_p p{
  text-align:center;
}
.smallPanel_p b{
  font-family: MicrosoftYaHei;
  font-size: 20px;
  color: #666666;
}
.addApplication{
  font-family: MicrosoftYaHei;
  border: 1px dashed #168EE3 !important;
  font-size: 12px;
  color: #168EE3 !important;
  background-color:#fff !important;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9{
  padding-right:8px;padding-left:8px;
}
.row {
    margin-right: -6px;
    margin-left: -6px;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="head_portrait">
							<img src="${ctx }${icoPath}" />
						</div>
						<div class="brief_introduction">
							<h5>${userName }  ${indexText }</h5>
							<p>欢迎登陆，${sysName }</p>
						</div>
						<div class="date">
							<h5>${WeekOfDate }</h5>
							<p>${date }</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<a href="javascript:open('jc')" class="panel-body">
						<div class="Icon">
							<img src="${ctx}/static/img/qietu/group_4.png" />
						</div>
						<div class="business_name">
							<p>资金交存</p>
						</div>
						<div class="number">
							<p id="4" >error</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-default">
					<a href="javascript:open('tk')" class="panel-body">
						<div class="Icon">
							<img src="${ctx}/static/img/qietu/group_9.png" />
						</div>
						<div class="business_name">
							<p>过户退款</p>
						</div>
						<div class="number">
							<p id="0" style="color:#017BFF">error</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-default">
					<a href="javascript:open('zy')" class="panel-body">
						<div class="Icon">
							<img src="${ctx}/static/img/qietu/group_10.png" />
						</div>
						<div class="business_name">
							<p>维修支用</p>
						</div>
						<div class="number">
							<p id="1" style="color:#FCA62E">error</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-default">
					<a href="javascript:open('da')" class="panel-body">
						<div class="Icon">
							<img src="${ctx}/static/img/qietu/group_11.png" />
						</div>
						<div class="business_name">
							<p>档案变更</p>
						</div>
						<div class="number">
							<p id="2" style="color:#684BFF">error</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-default">
					<a href="javascript:open('zj')" class="panel-body">
						<div class="Icon">
							<img src="${ctx}/static/img/qietu/group_12.png" />
						</div>
						<div class="business_name">
							<p>资金变更</p>
						</div>
						<div class="number">
							<p id="3" style="color:#7DB405">error</p>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="panel panel-default">
					<div class="panel-body" style="min-height:460px;">
						<div class="content">
							<div>
								<img src="${ctx}/static/img/qietu/group_13.png" >
							</div>
							<div>
								<p>快捷入口</p>							
								<img src="${ctx}/static/img/edit.png"  id="editApplication">
								<p style="color:#666;" class="dray_Click">（点击拖动调整排序）</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="m_content"  id="drag_container">
						<c:if test="${not empty shortcutList}">  
						  <c:forEach items="${shortcutList }" var="obj" varStatus="status">
                   			 <a href="javascript:openThre('${obj.MENUURL }','${obj.MENUNAME }')" orderno="${obj.ORDERNO }" menuid="${obj.MENUID }" shortid="${obj.SHORTID }" class="span module1 js_module">
						     	<p>${obj.MENUNAME }<i class="fa fa-minus-square" style="color:red;padding-left:10px;"></i></p>
						  	 </a>
                   		  </c:forEach>
                   		  </c:if>
                   		  		<div class="span" id="addApplication">
						     <p><img src="${ctx}/static/img/add.png"  style="margin-right:5px;"></p>
						  </div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="content">
									<div>
										<img src="${ctx}/static/img/qietu/group_13.png">
									</div>
									<div>
										<p>在途业务跟踪</p>
									</div>
									<div class="clear"></div>
								</div>
								<div class="smallPanel">
								   <a href="javascript:openToo('tk')" class="smallPanel_div">
								      <div class="smallPanel_p">
								        <p><b id="5">error</b>笔</p>
								        <p>过户退款</p>
								      </div>
								   </a>
								    <a href="javascript:openToo('zy')" class="smallPanel_div">
								      <div class="smallPanel_p">
								        <p ><b id="6">error</b>笔</p>
								        <p>维修支用</p>
								      </div>
								   </a>
								   <a href="javascript:openToo('da')" class="smallPanel_div">
								      <div class="smallPanel_p">
								        <p ><b id="7">error</b>笔</p>
								        <p>档案变更</p>
								      </div>
								   </a>
								   <a href="javascript:openToo('zj')" class="smallPanel_div">
								       <div class="smallPanel_p">
								        <p ><b id="8">error</b>笔</p>
								        <p>资金变更</p>
								      </div>
								   </a>
								   <div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-body" style="min-height:265px;">
								<div class="content">
									<div>
										<img src="${ctx}/static/img/qietu/group_13.png">
									</div>
									<div>
										<p>消息通知</p>
										<img src="${ctx}/static/img/qietu/shape.png" onclick="winOpen()">
							              <table class="table table-bordered table-hover " id="datatable" cellspacing="0" width="100%">
								               <thead>
								                   <tr>
								                       <th id="T0" name="center" style="width: 50px;" >序号</th>
								                       <th id="T1" name="left" >通知标题</th>
								                       <th id="T2" name="left" >通知内容</th>
								                       <th id="T3" name="center" class="width_80">是否查阅</th>
								                   </tr>
								               </thead>
								            </table>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
   <script src="${ctx}/static/js/Adminlte/jquery.min.js"></script> 
	<script src="${ctx}/static/js/Adminlte/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/plugins/drag/Sortable.min.js"></script>
	<script src="${ctx}/static/js/plugins/drag/moduleSet.min.js"></script>
	<script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    <script src="${ctx}/static/js/Adminlte/base64.min.js"></script>
    <!--   瀑布流插件 -->
   <script src="${ctx}/static/js/masonry-docs.min.js"></script>
	<script type="text/javascript">
	var table;
    $(document).ready(function(){
    	// 默认禁用搜索和排序
    	$.extend( $.fn.dataTable.defaults, {
    	    searching: false
    	} );
    	
    	table = $('#datatable').dataTable( {
    		//开启服务器模式
    		"serverSide": true,
    		"bAutoWidth":false,
    		//去除分页功能
    		"language": {
                "url": "${ctx}/static/js/plugins/dataTables/zh_CN.txt"
            },
    		//scrollY: 400,
    		"ajax": {
    	        url: '${ctx}/system/notice/allMessageShow',
    	        type: 'POST',
    	        data: function(d){
    	        	d.userid = "${userid}";
    	        	d.type = "1";
    	        	
    	        }
    	    },
    	    "createdRow": function (row, data, dataIndex) {      	    	
    	    	for(var i=0; i<4; i++){
    	    		var align = $('#T'+i).attr("name");
    	    	    $(row).children('td').eq(i).attr('style', 'text-align:' +align);
    	    	}
    	    	    	    	 
            },  
    	    "columns":[
				{"data": null,
					render : function(data, type, row, meta) {  
	                    // 显示行号  
	                    var startIndex = meta.settings._iDisplayStart;  
	                    return startIndex + meta.row + 1;  
	                }  	
				},       			
                {"data":"NTCTITLE"},
                {"data":"DESCRIBE"},
                {"data":"VIEWFLAG",
                	"render": function(data, type, row, meta){
                   		var ret = "";
                   		if(data == "0"){ret = '<span  rowid="'+row.SID+'" rowuid="'+row.RUSERID+'" style="color:#FF4D64" >未查阅</span>'}
                   		if(data == "1"){ret = '<span  rowid="'+row.SID+'" rowuid="'+row.RUSERID+'" >已查阅</span>';}
                   		return ret;
                   	}
                },
            ]
    	} );
    	$('#datatable tbody').on('dblclick', 'tr', function () {
            var tr = $(this).closest('tr');
            var sid = tr.find('td:last-child span:first-child').attr('rowid');
            var userid = tr.find('td:last-child span:first-child').attr('rowuid');
            $.ajax({
                type: "GET",
                url: "${ctx}/system/notice/detailsLength",
                async: false,//因为下面要刷新，所以这里要同步加载
                data:{userid:userid,sid:sid},
                dataType: "json",
                success: function(data){
              	  var dataLength = data.length;
              	  var msgLabel = window.parent.parent.document.getElementById("msgLabel");
              	  var messageHeader = window.parent.parent.document.getElementById("messageHeader");
              	  $(msgLabel).html(dataLength);
              	  $(messageHeader).html('你有'+dataLength+'条未读消息');
              	  parent.layer.open({ 
            	 		type: 2, 
            	 		title:'消息详情',
            	 		id:'layer_addParam',
            	 		area: ['50%', '50%'],
            	 		fixed: false, //不固定
            	 		maxmin: true,
            	 		content: '${ctx}/system/notice/allMessage?sid='+sid+"&userid="+userid+"&type=1"
            	 	});	  
                }
   		  })
   		  refresh();  
        });
      });
    
    
	   $(document).ready(function(){
		   $('.fa-minus-square').hide()
		   $('.dray_Click').hide()
		   $('#editApplication').bind('click',function(){
				// 排序
				var container = document.getElementById("drag_container");
				var sort = Sortable.create(container, {
				  animation: 150, // ms, animation speed moving items when sorting, `0` — without animation
				  handle: ".module1", // Restricts sort start click/touch to the specified element
				  draggable: ".module1", // Specifies which items inside the element should be sortable
				  onUpdate: function (evt/**Event*/){
				     var item = evt.item; // the current dragged HTMLElement
				  }
				});
				 $('.fa-minus-square').show()
				 $('#addApplication').hide()
				 $('#editApplication').hide()
				  $('.dray_Click').show()
				 $(this).parent().append('<div id="OperationBtn" style="float:right;"><button class="saveBtn btn btn-primary btn-xs" style="margin-right:6px;">保存</button><button class="cancaleBtn btn btn-primary btn-xs" >取消</button></div>');
				 $('.fa-minus-square').parent().parent('a').bind('click',function(){
					 $(this).hide();
					 return false;	 
				 })
				 $('.cancaleBtn').bind('click',function(){
					 $('.fa-minus-square').hide()
					 $('#addApplication').show()
					 $('#OperationBtn').remove()
					 $('#editApplication').show()	
					  $('.dray_Click').hide()
					 $('.fa-minus-square').parent().parent('a').unbind('click')
					 $('.fa-minus-square').parent().parent('a').show()
				 })
				 $('.saveBtn').bind('click',function(){
					 var menuid = ""
					    $("#drag_container a:visible").each(function(index, element) {
					    	menuid += $(this).attr('menuid')+","
					    });
					 menuid = menuid.substring(0, menuid.length-1);
					 $.ajax({
		    	           url:"${ctx}/system/menu/shortUpdate",
		    	           data:{menuid:menuid},
		    	           type:"POST",
		    	           dataType:"json",
		    	           success:function(result){
		    	        	   if (result.retFlag == 'T'){
		    	               	layer.confirm(result.retMsg, {
		    	               	    btn: ['确定'], //按钮
		    	               	    shade: false //不显示遮罩
		    	               	}, function(){
			    	               	 $('.fa-minus-square').hide()
			    					 $('#addApplication').show()
			    					 $('#OperationBtn').remove()
			    					 $('#editApplication').show()	
			    					 $('.dray_Click').hide()
			    					 $('.fa-minus-square').parent().parent('a').unbind('click')
		    	               		layer.closeAll();
		    	               	});
		    	                }else{
		    	                	parent.layer.alert(result.retMsg);
		    	                }
		    	            }
		    	    	}) 
				 })
		   })
		    //获取当前用户的待办任务条数
	        $.ajax({
	            url:"${address}"+"hfmp/build/daiban/getCount?userid=${userid }&zonecodes=${zonecodes}&compno=${compno}&username="+encodeURI(encodeURI("${userName}")),
	            type:"POST",
	            dataType:"json",
	            success:function(result){
	            	if (result.retFlag == 'T'){
	            		strs=result.retMsg.split(","); //字符分割 
	            		for (i=0;i<strs.length ;i++ ) { 
	            			$("#"+i).html(strs[i]);
	            		} 
	                }
	            }
	    	})
	   })
	   function open(param){
		   var doc = window.parent;
		   var href = '';
		   if (param != "jc") {
 			   href = "${address}"+"hfmp/build/daiban/list?username=${userName }&type="+param
		   }else {
			   href = "${address}"+"hfmp/build/daiban/label?username=${userName }"
		   }
		   var name = "";
		   if (param == "tk") {
			   name = "退款"
		   } else if (param == "zy") {
			   name = "支用"
		   } else if (param == "da") {
			   name = "档案变更"
		   } else if (param == "zj") {
			   name = "资金变更"
		   } else {
			   name = "交存"
		   }
		   doc.open(encodeURI(encodeURI(href)),name);
	   }
	   
	   function openToo(param){
		   var doc = window.parent;
		   var href = "${address}"+"/hfmp/build/trackFlow/list?username=${userName }&type="+param;
		   var name = "";
		   if (param == "tk") {
			   name = "退款"
		   } else if (param == "zy") {
			   name = "支用"
		   } else if (param == "da") {
			   name = "档案变更"
		   } else if (param == "zj") {
			   name = "资金变更"
		   }
		   doc.open(encodeURI(encodeURI(href)),name);
	   }
	   
	   function winOpen(){
		   layer.open({ 
	    		type: 2, 
	    		title:'消息详情',
	    		id:'layer_seeAll',
	    		area: ['75%', '75%'],
	    		fixed: false, //不固定
	    		maxmin: true,
	    	    content: '${ctx}/system/notice/allMessageList?userid='+'${userid}'
	    		});	
	   }
	   
	   function refresh(){
		   window.location.reload();
	   }
	   
	   function openThre(url,name){
		   var doc = window.parent;
		   doc.open(url,name);
	   }
	   
	   var navMenus = $.parseJSON('${navMenus}');
	   var leftMenus =$.parseJSON('${leftMenus}');
	   $("#addApplication").bind('click',function(){ 
	    	  var str = '<div class="input-group" style="width:300px;margin:20px auto;"><input type="text" class="form-control" id="searchAllInput"  placeholder="Search..."/>'
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
	    	    				   str += '<a class="J_menuItem" menuid='+ITEM.sid+' activeData='+item.sid+' sessionId='+Base64.encode(ITEM.sessionId)+'>';
	    	    				   str += '<i></i>'+ ITEM.menuname;
	    	    				   str += '</a>';
	    	    				   str += '</li>';
	    	    			   });     
	    	    			   str += '</ul>';
	    	    			   str += '</li>';
	    				  }else{
	    					  str +=  '<li>';
	    					  str +=  '<a class="J_menuItem" menuid='+iTem.sid+' activeData='+item.sid+' sessionId='+Base64.encode(iTem.sessionId)+'>';
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
	      		area: ['95%', '95%'],
	      		fixed: false, //不固定
	      		maxmin: false,
	      	    content:str
	          });
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
	    	  //保存
	    	  $('.J_menuItem').bind('click',function(){
	    		  var menuid = $(this).attr('menuid')
	    		  $.ajax({
	    	           url:"${ctx}/system/menu/shortSave",//提交地址
	    	           data:{menuid:menuid},
	    	           type:"POST",
	    	           dataType:"json",
	    	           success:function(result){
	    	        	   if (result.retFlag == 'T'){
	    	               		refresh();
	    	               		layer.closeAll();
	    	                }else{
	    	                	parent.layer.alert(result.retMsg);
	    	                }
	    	            }
	    	    	}) 
	    	  })
	      });
	</script>
</body>

</html>
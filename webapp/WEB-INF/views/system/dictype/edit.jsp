<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
				<div class="form-group">
				<div class="col-xs-12 ">
                       <label class="col-xs-12 control-label">字典标识<span class="star">*</span></label>
                       <div class="col-xs-12">
                       <input type="hidden" name="formToken" value="${formToken }" />  
                       <c:if test="${add =='update' }">
                       <input type="text"  name="dictno" id="dictno"  class="form-control" value="${rolelist.DICTNO}">
                       </c:if>
                       <c:if test="${add !='update' }">
                       <input type="text" name="dictno" id="dictno" class="form-control" >
                       </c:if>
                       <input type="hidden" name="sid" value="${rolelist.SID}" />
                       </div>
                       </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">字典名称<span class="star">*</span></label>
                     <div class="col-xs-12">
                     <input type="text" name="dictname" id="dictname"  class="form-control" value="${rolelist.DICTNAME}">
                 	 </div>
                 	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">字典描述</label>
                     <div class="col-xs-12">
                     <input type="text" name="dictdesc" id="dictdesc"  class="form-control" value="${rolelist.DICTDESC}">
                     </div>
                     </div>
                 </div>
            </form>
          </div>
        </div>
      </div>
    </div>
   </div> 
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
     
    <script type="text/javaScript">
    $(document).ready(function(){
    	var e = "<i class='fa fa-times-circle'></i> ";
    	$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	dictno : {
          		  required : true,
          			maxlength:2
  				},
	        	dictname: "required"
	        },
	        messages: {
	        	dictno : {
	        		"required":e+"请输入字典标识",
	        		"maxlength":e+"输入字典标识长度为2位"
					},
	        	dictname: e + "请输入字典名称"
	        }
	    });
		
		$("#closeBtn").click(function(){
			parent.layer.closeAll();
		});
		
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	    	return;
	     }else{
	    	 loadStart(); //开始调用加载提示的方法
	    	 $.ajax({
	             url:"${ctx}/system/dictype/${add}",
	             data:$("#dataForm").serialize(),
	             type:"POST",
	             dataType:"json",
	             success:function(result){
	            	 loadEnd();  //结束调用加载提示的方法
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
	             },
	           error:function(){
	        	   loadEnd();   //结束调用加载提示的方法
	        	   parent.layer.alert('请求出错!');	        	   
	           }
	     	}) 
	     }
	};
    //窗口关闭并刷新父页面
  	function closeWin(){ 
    	//刷新
    	var doc = window.parent;
    	doc.refresh();
    	//关闭窗口
    	parent.layer.closeAll();
    	
  	} 
    </script>
</body>
</html>

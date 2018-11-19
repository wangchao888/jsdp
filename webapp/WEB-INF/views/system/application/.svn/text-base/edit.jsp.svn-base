<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet"> 
</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
              <input type="hidden" name="sid" id="sid" value="${dataMap.SID }">
              
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">应用名称<span class="star">*</span></label>
                <div class="col-xs-12">
                  <input type="text" name="appname" id="appname" value="${dataMap.APPNAME }" class="form-control">
                  <input type="hidden" name="appflag" id="appflag" value="${dataMap.APPFLAG }">
                  <input type="hidden" name="formToken" value="${formToken }" />
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12">
	              <label class="col-xs-12 control-label">是否核心应用</label>	
	              <div class="col-xs-8 mtb10">
	                <input type="checkbox" class="js-switch" <c:if test="${dataMap.APPFLAG == '1' }">checked</c:if> /> 
	              </div>
	              </div>
	          </div>
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">IP<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="appip" id="appip" value="${dataMap.APPIP }" class="form-control">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">域名<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="domain" id="domain" value="${dataMap.DOMAIN }" class="form-control">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">端口<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="appport" id="appport" value="${dataMap.APPPORT }" class="form-control">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">视图<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="webview" id="webview" value="${dataMap.WEBVIEW }" class="form-control">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12">
                <label class="col-xs-12 control-label">应用说明</label>      
                <div class="col-xs-12">                   
             	  <textarea id="appdesc" name="appdesc" class="form-control">${dataMap.APPDESC}</textarea> 
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
  <script type="text/javascript" src="${ctx}/static/js/plugins/switchery/switchery.js"></script>
  <script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
  <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
  <script type="text/javaScript">
    $(document).ready(function(){
  	  var isCore = document.querySelector(".js-switch"), t = new Switchery(isCore,{color: "#168EE3 "});
  	  isCore.onchange = function() {
          if(isCore.checked){
          	$('#appflag').attr("value","1");
          	
          }else{
          	$('#appflag').attr("value","0");
          }
  	  };
	  	var e = "<i class='fa fa-times-circle'></i> ";
		$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	appname: "required",
	        	appip: "required",
	        	domain: "required",
	        	appport: "required",
	            webview: "required"
	        },
	        messages: {
	        	appname: e + "请输入应用名称",
	        	appip: e + "请输入应用IP",
	        	domain: e + "请输入应用域名",
	        	appport: e + "请输入应用端口",
	        	webview: e + "请输入应用视图"
	        }
	    });
  	})
    function saveForm(){
    	if (!$("#dataForm").valid()) {
	        return;
	     }
    	$.ajax({
            url:"${ctx}/system/application/update",//提交地址
            data:$("#dataForm").serialize(),//将表单数据序列化
            type:"POST",
            dataType:"json",
            success:function(result){
                if (result.retFlag == 'T'){
                	parent.layer.confirm(result.retMsg, {
                	    btn: ['确定'], //按钮
                	    shade: false //不显示遮罩
                	}, function(){
                		closeWin();
                	});
                	
                }else{
                	parent.layer.alert(result.retMsg);
                }
            }
    	})
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

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet"> 
	
</head>

<body class="white-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" method="post" enctype="multipart/form-data" class="form-horizontal">
              <input type="hidden" name="sid" id="sid" value="${dataMap.SID }">
              <div class="form-group">
              	<div class="col-xs-12 ">
	              <label class="col-xs-12 control-label">是否测试环境</label>	
	              <div class="col-xs-8 mtb10">
	                <input type="checkbox" class="js-switch" <c:if test="${dataMap.TESTFLAG == '1' }">checked</c:if> /> 
	                <input type="hidden" name="testflag" id="testflag" />
	              </div>
	               </div>
	          </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">系统名称</label>
                <div class="col-xs-12">
                  <input type="text" name="sysname" id="sysname" value="${dataMap.SYSNAME }" class="form-control">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">系统图片</label>
                <div class="col-xs-12">
                  <img src="${ctx }/static/img/log/logo.png"/>
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">替换图片</label>
                <div class="col-xs-12">
                  <input type="file" name="file" id="file" placeholder="点击选择图片" class="form-control"/>
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
  <script type="text/javaScript">
    $(document).ready(function(){
  	  var isCore = document.querySelector(".js-switch"), t = new Switchery(isCore,{color: "#168EE3 "});
  	  isCore.onchange = function() {
          if(isCore.checked){
          	$('#testflag').attr("value","1");
          	
          }else{
          	$('#testflag').attr("value","0");
          }
  	  };
  	if("${message}" == "T"){
		//刷新	
    	var doc = window.parent;
    	doc.refresh();
    	parent.layer.alert("导入成功");
		parent.layer.closeAll();
	}else{
		if("${message}" != ""){
			parent.layer.alert("${message}");
		}
	}
  	})
    function saveForm(){
    	if($.trim($("#file").val()) != ""){
    		var filePath = $.trim($("#file").val());
        	//截取、并把后缀名全部改为小写。
        	var sufFix = (filePath.substring(filePath.lastIndexOf(".")+1,filePath.length)).toLowerCase();
        	if(sufFix != "png"){
        		alert("选择文件错误，请选择png图片");
        		return false;
        	}
    	}
    	$("#dataForm").attr("action","${ctx}/system/sysinfo/update");
    	$("#dataForm").submit();
    }  
    //图片下载
    function downloadImg(){
		$("#dataForm").attr("action","${ctx}/system/sysinfo/downloadImg");
		$("#dataForm").submit();	
	};
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

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"> 
   <%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet">
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
	
</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
            <input type="hidden" name="id" id="id" value="${dataMap.ID }">
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">表代码<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="tableid" id="tableid"  class="form-control" value="${dataMap.TABLEID }">
                  <input type="hidden" name="formToken" value="${formToken }" />
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">表名称<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="tablename" id="tablename" class="form-control" value="${dataMap.TABLENAME }">
                </div>
                </div>
              </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">表中文名<span class="star">*</span></label>
                <div class="col-xs-8">
                  <input type="text" name="tablenamecn" id="tablenamecn"  class="form-control" value="${dataMap.TABLENAMECN }">
                </div>
                </div>
              </div>                        
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">条件</label>      
                <div class="col-xs-12">                   
             	  <textarea id="conditions" name="conditions"  class="form-control">${dataMap.CONDITIONS}</textarea> 
             	</div>
             	</div>
              </div>
              <div class="form-group">
              <div class="col-xs-12 ">
                <label class="col-xs-12 control-label">可否维护：</label>
                <div class="col-xs-12"> 
                	<input type="checkbox" class="js-switch" id="stat" <c:if test="${dataMap.ISMODIFY == 1}">checked</c:if>/>
	                <input type="hidden" name="ismodify" id="ismodify" value="${dataMap.ISMODIFY == 1}"/>
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
    var rootDict
    $(document).ready(function(){ 
    	//初始化按钮
    	rootDict = document.querySelector(".js-switch"), t = new Switchery(rootDict,{color: "#168EE3 "});
    	rootDict.onchange = function() {
            if(rootDict.checked){
            	$('#ismodify').val("1");
            }else{
            	$('#ismodify').val("0");
            }
    	};
    	var e = "<i class='fa fa-times-circle'></i> ";
		$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	tableid: "required",
	        	tablename: "required",
	        	tablenamecn: "required"
	        },
	        messages: {
	        	tableid: e + "请输入表代码",
	        	tablename: e + "请输入表名称",
	        	tablenamecn: e + "请输入表中文名"
	        }/* ,
	        submitHandler: function(form){
	        	
	        } */
	    });
	});
        
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		if(rootDict.checked){
        	$('#ismodify').attr("value","1");
        }else{
        	$('#ismodify').attr("value","0");
        }
		$.ajax({
            url:"${ctx}/system/dyntable/update",//提交地址
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

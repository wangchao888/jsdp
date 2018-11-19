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
                <input type="hidden" name="sid" id="sid" value="${dataMap.SID }">
                      <div class="form-group">
                      <div class="col-xs-12 ">
                          <label class="col-xs-12 control-label">参数编号<span class="star">*</span></label>
                          <div class="col-xs-12">
                            <input type="text" name="paramno" id="paramno" value="${dataMap.PARAMNO }" class="form-control">
                            <input type="hidden" name="formToken" value="${formToken }" />  
                          </div>
                          </div>
                      </div>
                      <div class="form-group">
                      <div class="col-xs-12 ">
                          <label class="col-xs-12 control-label">参数值<span class="star">*</span></label>
                          <div class="col-xs-12">
                            <input type="text" name="paramvalue" id="paramvalue" value="${dataMap.PARAMVALUE }" class="form-control">
                          </div>
                          </div>
                      </div>
                      <div class="form-group">
                      <div class="col-xs-12 ">
                          <label class="col-xs-12 control-label">参数描述</label>
                          <div class="col-xs-12">
                            <input type="text" name="paramdesc" id="paramdesc" value="${dataMap.PARAMDESC }" class="form-control">
                          </div>
                          </div>
                      </div>
                      <div class="form-group">
                      <div class="col-xs-12 ">
                          <label class="col-xs-12 control-label">是否允许修改</label>
                          <div class="col-xs-12">
							<select name="ismodify" id="ismodify"class="form-control">
								<option  name="ismodify1" value="0" >是</option>
								<option  name="ismodify2" value="1">否</option>
							</select>
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
    $(function(){
    	var ismodify = "${dataMap.ISMODIFY}";
 		//初始化选中
 		$.each($('option'),function(){
 			  if($(this).val()==ismodify)
 				  $(this).attr('selected',"selected");
 		});
 		
 		var e = "<i class='fa fa-times-circle'></i> ";
 		$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	paramno : {
	          		  required : true,
	          			maxlength:2
	  				},
	        	paramvalue: "required"
	        },
	        messages: {
	        	paramno : {
	        		"required":e+"请输入参数编号",
	        		"maxlength":e+"输入参数编号长度为2位"
					},
	        	paramvalue: e + "请输入参数值"
	        }
	    });
 		
 		$("#closeBtn").click(function(){
			parent.layer.closeAll();
		});
    });
    
    function saveForm(){
    	if (!$("#dataForm").valid()) {
	        return;
	     }
    	$.ajax({
            url:"${ctx}/system/param/update",//提交地址
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

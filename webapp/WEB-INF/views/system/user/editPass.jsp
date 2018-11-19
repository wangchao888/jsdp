<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
	 <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
	<link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css"> 
</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
	      <!-- 标题头部分 开始 -->
          <div class="ibox-content white-bg">
            <form role="form" id="dataForm" class="form-horizontal">
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">所属部门：</label>
							              <div class="col-xs-12">
							                  <input readonly  type="text" disabled="disabled" value="${rootOrgName}"  class="form-control">
							                  <input type="hidden" value="${sid }" name="sid" />
							              </div>
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">用户姓名：</label>
					              <div class="col-xs-12">
					                  <input readonly  type="text" disabled="disabled" value="${userName}"  class="form-control">
					              </div>
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">工号：</label>
	              <div class="col-xs-12">
	                  <input readonly  type="text" disabled="disabled" value="${loginName}"  class="form-control">
	              </div>
									</div>
				                </div>   
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">输入原密码：</label>
	              <div class="col-xs-12">
	                  <input  name="originalPass" type="text"   class="form-control">
	              </div>
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">设置新密码：</label>
	              <div class="col-xs-12">
	                  <input id="passwd"   name="passwd" type="text"   class="form-control">
	              </div>
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">确认新密码：</label>
	              <div class="col-xs-12">
	                  <input id="passwd2"  name="passwd2" type="text"   class="form-control">
	              </div>
									</div>
				                </div>				                         
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    
	<script type="text/javascript">
	function saveForm(){
    	if (!$("#dataForm").valid()) {
	        return;
	     } 
    	if ($('#passwd').val() != $('#passwd2').val()) {
    		layer.alert("两次密码输入不同");
    		return;
    	}
    	$.ajax({
            url:"${ctx}/system/user/update",//提交地址
            data:$("#dataForm").serialize(),//将表单数据序列化
            type:"POST",
            dataType:"json",
            success:function(result){
                if (result.retFlag == 'T'){
                	parent.layer.confirm(result.retMsg, {
                	    btn: ['确定'], //按钮
                	    shade: false //不显示遮罩
                	}, function(){
                		parent.layer.closeAll();
                	});
                }else{
                	parent.layer.alert(result.retMsg);
                }
            }
    	})
    } 
	</script>
  <script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>

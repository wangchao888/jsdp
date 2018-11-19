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
									<div class="col-xs-6 ">
									<label class="col-xs-12 control-label font14">通知标题 </label>
                     <div class="col-xs-12">
                     	<span class="form-control border_none border_bom_dashed">${dataMap.NTCTITLE }</span>
                     	<input type="hidden" name="infoid" id="infoid" value="${infoid }" />
                     </div>
									</div>
									<div class="col-xs-6 ">
				                  		<label class="col-xs-12 control-label font14">消息发送人 </label>
                    <div class="col-xs-12">
                    	<span class="form-control border_none border_bom_dashed">${dataMap.USERNAME }</span>
                    </div>
									</div>
				                </div>    
				                <div class="form-group">
				                  	<div class="col-xs-6 ">
									<label class="col-xs-12 control-label font14">通知类型 </label>
                     <div class="col-xs-12">
                     <span class="form-control border_none border_bom_dashed"><jsdp:dicshow dictno="01" dictlabel="${dataMap.NTCTYPE}"></jsdp:dicshow></span>
                	 </div>
									</div>
									
									<div class="col-xs-6 ">
				                  		<label class="col-xs-12 control-label font14">通知日期 </label>
                     <div class="col-xs-12">
                     	<span class="form-control border_none border_bom_dashed">${dataMap.RELEASEDATE }</span>
                	 </div>
									</div>
				                </div> 				                
				                <div class="form-group">
				                  	
									
									<div class="col-xs-12 ">
				                	 <label class="col-xs-12 control-label font14">通知内容 </label>
                     <div class="col-xs-12">
                     	<textarea  readonly="readonly"  rows="5" style="width: 100%;">${dataMap.DESCRIBE }</textarea>
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
     <script src="${ctx}/static/js/plugins/dataTables/jquery.d.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.b.js"></script>
    <script type="text/javaScript">
    </script>
</body>
</html>

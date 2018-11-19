<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
	<link href="${ctx}/static/css/jquery-ui.css" rel="stylesheet">
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
                     <label class="col-xs-12 control-label">所属机构名称</label>
                     <div class="col-xs-12">
                     <select class="form-control" name="orgno" id="orgno">
                		<c:forEach items="${orgNameList }" var="obj" varStatus="status">
                   			<option value="${obj.ORGNO }" >${obj.ORGNAME }</option>
                   		</c:forEach>
                     </select>
                     </div>
                     </div>
                 </div>
				 <div class="form-group">
				 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">角色名称<span class="star">*</span></label>
                     <div class="col-xs-12">
                     <input type="text" name="rolename" id="rolename" class="form-control" value="${rolelist.ROLENAME}">
                     <input type="hidden" name="sid" value="${rolelist.SID}" />
                     <input type="hidden" name="formToken" value="${formToken }" />
                     </div>
                     </div>
                 </div>
                 <div class="form-group">
                 	<div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">角色说明</label>
                     <div class="col-xs-12">
                     <input type="text" name="roledesc" id="roledesc"  class="form-control" value="${rolelist.ROLEDESC}">
                	 </div>
                	 </div>
                 </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
    
    <script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.10.4.min.js"></script>
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    <script type="text/javaScript">
    $(document).ready(function(){
    	var e = "<i class='fa fa-times-circle'></i> ";
    	$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	rolename: "required"
	        },
	        messages: {
	        	rolename: e + "请输入角色名称"
	        }
	    });
    	$("#rolename").autocomplete({  
			source: function( request, response ) {  
			    $.ajax({  
			        type:"post",  
			        url:"${ctx}/system/role/autocomplete",//提交地址
			        dataType: "json",  
			        data:{  
			        	rolename: request.term  
			        },  
			        success: function(data) {
			            response( $.map( data.data, function( item ) {  
			                return {   //lable为下拉列表显示数据源。value为选中放入到文本框的值，这种方式可以自定义显示  			                	
			                    lable:item.LABLE,      
			                    value: item.VALUE  
			                }  
			            }));  
			        }  
			    });  
			},  
			minLength: 1,  
			select: function( event, ui ) { //移动键盘上下键，自动将下拉列表的数据放入到文本框，不过不写这个配置也是可以的，默认就行，具体这个还不知道是做什么  
			    $("#userName").val(ui.item.userName);  
			}  
		});  
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		$.ajax({
            url:"${ctx}/system/role/${add}",//提交地址
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

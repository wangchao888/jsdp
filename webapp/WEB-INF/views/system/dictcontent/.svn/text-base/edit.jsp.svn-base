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
            <form role="form" id="dataForm" class="form-horizontal">
			   <div class="form-group">
			   <div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">字典标签<span class="star">*</span></label>
                   <div class="col-xs-12">
                   <input type="text" name="dictlabel" id="dictlabel"  class="form-control" value="${rolelist.DICTLABEL}">
                   <input type="hidden" name="sid" value="${rolelist.SID}" />
                   <input type="hidden" name="dictno" value="${dictno}" />
                   <input type="hidden" name="formToken" value="${formToken }" />
                   </div>
                   </div>
               </div>
               <div class="form-group">
               	<div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">字典值<span class="star">*</span></label>
                   <div class="col-xs-12">
                   <input type="text" name="dictvalue" id="dictvalue"  class="form-control" value="${rolelist.DICTVALUE}">
               	   </div>
               	   </div>
               </div>
               <div class="form-group">
               	<div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">排序号<span class="star">*</span></label>
                   <div class="col-xs-12">                         
              	   <input type="text" name="sortno" id="sortno" class="form-control" value="${rolelist.SORTNO}">
               	   </div>
               	   </div>
               </div>
               
               <div class="form-group">
               	<div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">是否启用</label>                         
              	  <div class="col-xs-12">
	                 <input type="checkbox" class="js-switch" id="stat" <c:if test="${rolelist.STATE == '1'}">checked</c:if> <c:if test="${add == 'add'}">checked</c:if> />
	                 <input type="hidden" name="state" id="state" value="${rolelist.STATE}"/>
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
    var rootDict;
    $(document).ready(function(){
		//初始化按钮
    	rootDict = document.querySelector(".js-switch"), 
    		t = new Switchery(rootDict,{color: "#168EE3 "});
    	var e = "<i class='fa fa-times-circle'></i> ";
    	$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	dictlabel : {
	          		  required : true,
	          			maxlength:2
	  				},
	        	dictvalue: "required",
	        		sortno : {
	            		  required : true,
	    					digits : true,
	    				}
	        },
	        messages: {
	        	dictlabel : {
	        		"required":e+"请输入字典标签",
	        		"maxlength":e+"输入字典标签长度为2位"
					},
	        	dictvalue: e + "请输入字典名称",
	        	sortno : {
	        		  required :  e + "请输入序号",
	        		    digits :  e + "序号只能为数字",
					},
	        }
	    });
		
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		if(rootDict.checked){
        	$('#state').attr("value","1");
        }else{
        	$('#state').attr("value","0");
        }
		$.ajax({
            url:"${ctx}/system/dictcontent/${add}",//提交地址
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

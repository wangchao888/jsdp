<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"> 
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
            <input type="hidden" name="extid" value="${dataMap.EXTID }"/>
            <input type="hidden" name="id" value="${dataMap.ID }"/>
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">字段定义<span class="star">*</span></label>
                <div class="col-xs-12">
                  <input type="text" name="fieldname" id="tableid"  class="form-control" value="${dataMap.FIELDNAME }">
                  <input type="hidden" name="formToken" value="${formToken }" />
                </div>  
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">字段名<span class="star">*</span></label>
                <div class="col-xs-12">
                  <input type="text" name="fieldnamecn" id="tablename"  class="form-control" value="${dataMap.FIELDNAMECN }">
                </div>      
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">显示顺序</label>
                <div class="col-xs-12">
                  <input type="text" name="orderno" id="tableid"  class="form-control" value="${dataMap.ORDERNO }">
                </div>   
									</div>
				                </div>    
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">字段类型</label>
                <div class="col-xs-12">
                	 <select class="form-control  " name="fieldtype">
                                        <option value="S">字符串</option>
                                        <option value="D">日期</option>
                                        <option value="N">数值</option>
                     </select>
                </div>                 
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">字段长度<span class="star">*</span></label>
                <div class="col-xs-12">
                  <input type="text" name="fieldlen" id="fieldlen"  class="form-control" value="${dataMap.FIELDLEN }">
                </div> 
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">页面显示宽度</label>
                <div class="col-xs-12">
                  <input type="text" name="fieldwidth" id="tablename"  class="form-control" value="${dataMap.FIELDWIDTH }">
                </div>        
									</div>
				                </div>				                        
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">是否主键</label>
                <div class="col-xs-12">
                  <select class="form-control  " name="iskey" id="iskey">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                  </select>
                </div> 
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">是否为空</label>
                <div class="col-xs-12">
                  <select class="form-control  " name="isnull" id="isnull">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                  </select>
                </div>           
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">是否显示</label>
                <div class="col-xs-12">
                <select class="form-control  " name="isdisp" id="isdisp">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                  </select>
                </div>
									</div>
				                </div>              
              
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">是否重复</label>
                <div class="col-xs-12">
                 <select class="form-control  " name="isrept" id="isrept">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                  </select>
                </div> 
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">字典名称</label>
                <div class="col-xs-12">
                <input type="text" name="dictablename" id="tablename"  class="form-control" value="${dataMap.DICTABLENAME }">
                </div> 
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">是否补零</label>
                <div class="col-xs-12">
                  
                <select class="form-control  " name="iszero" id="iszero">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                  </select>
                </div>              
									</div>
				                </div>              
              
              
              
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">字典key</label>
                <div class="col-xs-12">
                  <input type="text" name="dickey" id="tableid"  class="form-control" value="${dataMap.DICKEY }">
                </div>   
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">字典value</label>
                <div class="col-xs-12">
                  <input type="text" name="dicvalue" id="tablename"  class="form-control" value="${dataMap.DICVALUE }">
                </div>        
									</div>
									
									<div class="col-xs-4 ">
									<label class="col-xs-12 control-label">条件</label>
                <div class="col-xs-12">
                  <textarea id="conditions" name="conditions"  class="form-control">${dataMap.CONDITIONS }</textarea> 
                </div>    
									</div>
				                </div>              
              
				                <div class="form-group">
				                  	<div class="col-xs-4 ">
				                  		<label class="col-xs-12 control-label">固定值</label>
                <div class="col-xs-12">
                 <textarea id="conditions" name="fixvalue"  class="form-control">${dataMap.FIXVALUE }</textarea> 
                </div>
									</div>
									
									<div class="col-xs-4 ">
									
									</div>
									
									<div class="col-xs-4 ">
									
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
    //初始化选中：
    $(function(){
     //是否主键	
   	 var iskey = "${dataMap.ISKEY}";
		$.each($("#iskey option"),function(){	
			  if($(this).val()==iskey)
				  $(this).attr('selected',"selected");
		});
	
	//是否为空	
   	 var isnull = "${dataMap.ISNULL}";
		$.each($("#isnull option"),function(){	
			  if($(this).val()==isnull)
				  $(this).attr('selected',"selected");
		});		
		
	//是否显示	
   	 var isdisp = "${dataMap.ISDISP}";
		$.each($("#isdisp option"),function(){	
			  if($(this).val()==isdisp)
				  $(this).attr('selected',"selected");
		});	
	//是否重复	
   	 var isrept = "${dataMap.ISREPT}";
		$.each($("#isrept option"),function(){	
			  if($(this).val()==isrept)
				  $(this).attr('selected',"selected");
		});	
	//是否补零	
   	 var iszero = "${dataMap.ISZERO}";
		$.each($("#iszero option"),function(){	
			  if($(this).val()==iszero)
				  $(this).attr('selected',"selected");
		});	
		
   });
    $(document).ready(function(){
    	var e = "<i class='fa fa-times-circle'></i> ";
		$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	fieldname: "required",
	        	fieldnamecn: "required",
	        	fieldlen: "required"
	        },
	        messages: {
	        	fieldname: e + "请输入字段定义",
	        	fieldnamecn: e + "请输入字段名",
	        	fieldlen: e + "请输入字段长度"
	        }/* ,
	        submitHandler: function(form){
	        	
	        } */
	    });
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		$.ajax({
            url:"${ctx}/system/dynfield/update",//提交地址
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

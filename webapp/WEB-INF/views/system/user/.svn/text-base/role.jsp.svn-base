<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
 <!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/iCheck/custom.css" rel="stylesheet">
    
    
    <link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
	
	<script type="text/javascript">
	function saveForm(){
		//传值 
		var str = $("#roles option").map(function(){return $(this).val();}).get().join(", ");
        var cityObj = $("#roleval");
		cityObj.attr("value", str);
		$.ajax({
            url:"${ctx}/system/userrole/${add}",//提交地址
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
	};
	//选中添加
	function role_n1(){			     
		 var options=$("#rolen option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#roles").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//选中删除
	function role_s1(){			     
		 var options=$("#roles option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolen").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部添加
	function role_n2(){			     
		 var options=$("#rolen option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#roles").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部删除
	function role_s2(){			     
		 var options=$("#roles option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolen").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//双击添加
	function role_n(){			     
	     var options=$("#rolen option:selected");//获取选中对象
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#roles").append("<option value='"+val+"'>"+text+"</option>");	     
	}
	//双击删除
	function role_s(){			     
	     var options=$("#roles option:selected");
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#rolen").append("<option value='"+val+"'>"+text+"</option>");	     
	}
	
	//传值提交
	$(function(){
	    $("#sub").click(function() {
	        var str = $("#roles option").map(function(){return $(this).val();}).get().join(", ");
	        var cityObj = $("#roleval");
			cityObj.attr("value", str);
	    });

	});	
	
	
	</script>
	<style type="text/css">
	</style>


</head>

<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight">
<form role="form" id="dataForm" class="form-horizontal">
 <div class="ibox-content">
      <div class="form-group">
      <div class="col-xs-12 ">
								<label class="col-xs-12 control-label">用户名</label>
								<div class="col-xs-12">
									<span class="form-control border_none border_bom_dashed">${username }<input type="hidden" id="roleval" name="roleval" /></span>
								</div>
								</div>
							</div>
      <div class="form-group">
      <div class="col-xs-12 ">
								<label class="col-xs-12 control-label">角色</label>
								<div class="col-xs-12">
									<table class="table">
										<tbody>
											<tr>
												<td>待选</td>
												<td></td>
												<td>已选<input type="hidden" id="conlist" name="conlist" /></td>
											</tr>
											<tr>
												<td>
													 <select ondblclick="role_n()" id='rolen' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
									    				<c:forEach items="${roleMap}" var="obj" varStatus="status">
									                   	  <option value="${obj.SID }">${obj.ROLENAME }</option>
											            </c:forEach>
													  </select> 
													   <input type="hidden" name="userId" value="${userid }"/>	
												</td>
												<td class="m_auto">
													<button onclick="role_n2()" class="btn btn-primary" type="button">
														<i class="fa fa-step-forward"></i>全部添加
													</button>
													<br>
												<br>
													<button onclick="role_n1()" class="btn btn-primary" type="button">
														<i class="fa fa-chevron-right"></i>选中添加
													</button>
													<br>
												<br>
													<button onclick="role_s1()" class="btn btn-primary" type="button">
														<i class="fa fa-chevron-left"></i>选中删除
													</button>
													<br>
												<br>
													<button onclick="role_s2()" class="btn btn-primary" type="button">
														<i class="fa fa-step-backward"></i>全部删除
													</button>
												</td>
												<td>
													<select ondblclick="role_s()" id='roles' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
													 <c:forEach items="${roleMap2}" var="obj2" varStatus="status">
								                   	  <option value="${obj2.SID }">${obj2.ROLENAME }</option>
										            </c:forEach>
												  	 </select>	 
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								</div>
							</div>
      </div>
     </form>       
    </div>
    <script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${ctx}/static/js/content.min.js?v=1.0.0"></script> 
    
    <script src="${ctx}/static/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/static/js/demo/peity-demo.min.js"></script> 
</body>
</html>

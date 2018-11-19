<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	
	<link href="${ctx}/static/css/loading.css" rel="stylesheet">
	<link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
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
                     <label class="col-xs-12 control-label">通知标题<span class="star">*</span></label>
                     <div class="col-xs-12">
                     	<input type="text" name="ntctitle" id="ntctitle"  class="form-control">
                     	<input type="hidden" name="formToken" value="${formToken }" />
                     </div>
                     </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">通知类型<span class="star">*</span></label>
                     <div class="col-xs-12">
                     	<select class="form-control m-b " name="ntctype" id="ntctype">
	                    	<jsdp:dic dictno="01"></jsdp:dic>
	                    </select>
                	 </div>
                	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">通知内容<span class="star">*</span></label>
                     <div class="col-xs-12">
                     <textarea id="describe" name="describe"  class="form-control"></textarea>
                	 </div>
                	 </div>
                 </div>
                 <div class="form-group">
                 <div class="col-xs-12 ">
                     <label class="col-xs-12 control-label">接收用户<span class="star">*</span></label>
                     <div class="col-xs-12">
                        <input id="userids" name="userids" type="hidden" />
                     	<select class="form-control m-b " name="usertype" id="usertype" onchange="changecombox();">
	                    	<option value="00" >所有用户</option>
	                    	<option value="01" >主管用户</option>
	                    	<option value="02" >企业用户</option>
	                    	<option value="03" >银行用户</option>
	                    </select>
                	 </div>
                	 </div>
                 </div>
                 
                 <div class="form-group" id="bank" style="display: none;">
                 <div class="col-xs-12 ">
                         <label class="col-xs-12 control-label">银行用户</label>
                               <div class="col-xs-12">                                 
                                 <table class="table">
						            <tbody> 
						                <tr>					                
						                	<td>备选</td>
						                    <td></td>
						                    <td>已选<input type="hidden" /></td>
						                </tr>
						               <tr>
						                  <td>
						                  <select ondblclick="role_nb()" id='rolenb' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
						    				<c:forEach items="${bankMap}" var="obj" varStatus="status">
						                   	  <option value="${obj.SID }">${obj.USERNAME }</option>
								            </c:forEach>
										  </select> 				 				  	     		
						                  </td>
						                  <td class="v_middle">
						                  	  <button onclick="role_n2b()" class="btn btn-primary" type="button"><i class="fa fa-step-forward"></i>全部添加</button><br><br>
						                  	  <button onclick="role_n1b()" class="btn btn-primary" type="button"><i class="fa fa-chevron-right"></i>选中添加</button><br><br>
						                  	  <button onclick="role_s1b()" class="btn btn-primary" type="button"><i class="fa fa-chevron-left"></i>选中删除</button><br><br>
						                  	  <button onclick="role_s2b()" class="btn btn-primary" type="button"><i class="fa fa-step-backward"></i>全部删除</button>               
						                  </td>             
						                  <td>
						                  	 <select ondblclick="role_sb()" id='rolesb' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
											 <c:forEach items="${bankMap}" var="obj2" varStatus="status">
								            </c:forEach>
										  	 </select>	 
						                  </td>                                 
						               </tr>                                
						          </tbody>
						      </table>
						      </div>
                           </div>
                </div>
                 <div class="form-group" id="unit" style="display: none;">
                 <div class="col-xs-12 ">
                         <label class="col-xs-12 control-label">主管用户</label>
                               <div class="col-xs-12">                                 
                                 <table class="table">
						            <tbody> 
						                <tr>					                
						                	<td>备选</td>
						                    <td></td>
						                    <td>已选<input type="hidden" /></td>
						                </tr>
						               <tr>
						                  <td>
						                  <select ondblclick="role_nc()" id='rolenc' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
						    				<c:forEach items="${userMap}" var="obj" varStatus="status">
						                   	  <option value="${obj.SID }">${obj.USERNAME }</option>
								            </c:forEach>
										  </select> 				 				  	     		
						                  </td>
						                  <td class="v_middle">
						                  	  <button onclick="role_n2c()" class="btn btn-primary" type="button"><i class="fa fa-step-forward"></i>全部添加</button><br><br>
						                  	  <button onclick="role_n1c()" class="btn btn-primary" type="button"><i class="fa fa-chevron-right"></i>选中添加</button><br><br>
						                  	  <button onclick="role_s1c()" class="btn btn-primary" type="button"><i class="fa fa-chevron-left"></i>选中删除</button><br><br>
						                  	  <button onclick="role_s2c()" class="btn btn-primary" type="button"><i class="fa fa-step-backward"></i>全部删除</button>               
						                  </td>             
						                  <td>
						                  	 <select ondblclick="role_sc()" id='rolesc' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
											 <c:forEach items="${userMap}" var="obj2" varStatus="status">
								            </c:forEach>
										  	 </select>	 
						                  </td>                                 
						               </tr>                                
						          </tbody>
						      </table>
						      </div>
                           </div>
                </div>
                <div class="form-group" id="enterprise" style="display: none;">
                <div class="col-xs-12 ">
                         <label class="col-xs-12 control-label">企业类型</label>
                               <div class="col-xs-12">                                 
                                 <table class="table">
						            <tbody> 
						                <tr>					                
						                	<td>备选</td>
						                    <td></td>
						                    <td>已选<input type="hidden"  /></td>
						                </tr>
						               <tr>
						                  <td>
 						                  <select ondblclick="role_n()" id='rolen' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc"> 
 						    				<c:forEach items="${corpMap}" var="obj" varStatus="status"> 
 						                   	  <option value="${obj.DICTLABEL }">${obj.DICTVALUE }</option> 
 								            </c:forEach> 
 										  </select> 
						                  </td>
						                  <td class="v_middle">
						                  	  <button onclick="role_n2()" class="btn btn-primary" type="button"><i class="fa fa-step-forward"></i>全部添加</button><br><br>
						                  	  <button onclick="role_n1()" class="btn btn-primary" type="button"><i class="fa fa-chevron-right"></i>选中添加</button><br><br>
						                  	  <button onclick="role_s1()" class="btn btn-primary" type="button"><i class="fa fa-chevron-left"></i>选中删除</button><br><br>
						                  	  <button onclick="role_s2()" class="btn btn-primary" type="button"><i class="fa fa-step-backward"></i>全部删除</button>               
						                  </td>             
						                  <td>
						                  	 <select ondblclick="role_s()" id='roles' multiple="multiple" style="width:220px;height:240px;border: 1px solid #ccc">
										  	 </select>	 
						                  </td>                                 
						               </tr>                                
						          </tbody>
						      </table>
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
    <script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script type="text/javaScript">
    $(document).ready(function(){
    	$('#dataForm .input-group.date').datepicker({//时间控件
     	     todayBtn: "linked",
    	         keyboardNavigation: !1,
    	         forceParse: !1,
    	         calendarWeeks: !0,
    	         autoclose: !0
     	    });
    	var e = "<i class='fa fa-times-circle'></i> ";
    	$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	ntctitle: "required",
	        	ntctype: "required",
	        	releasedate: "required",
	        	describe: "required"
	        },
	        messages: {
	        	ntctitle: e + "请输入通知标题",
	        	ntctype: e + "请输入通知类型",
	        	releasedate: e + "请输入通知日期",
	        	describe: e + "请输入通知内容",
	        }
	    });
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		var usertype = $('#usertype option:selected').val(); 
		var str
		if (usertype == '01'){ 
			str = $("#rolesc option").map(function(){return $(this).val();}).get().join("|");
		}
		if (usertype == '02') {
			str = $("#roles option").map(function(){return $(this).val();}).get().join("|");
		}
		if (usertype == '03'){ 
			str = $("#rolesb option").map(function(){return $(this).val();}).get().join("|");
		}
        var cityObj = $("#userids");
        if (usertype != '00'){ 
        	
        	cityObj.attr("value", str.replace(/\s/g,''));//去除所有的空格
		}
		$.ajax({
            url:"${ctx}/system/notice/saveInfo",//提交地址
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
	function changecombox(){	
  		var usertype = $('#usertype option:selected').val(); 
  		if (usertype == "01") {
  			$("#enterprise").attr("style","display:none;");//隐藏div
  			$("#bank").attr("style","display:none;");//隐藏div
  			$("#unit").attr("style","display:block;");//显示div
  		} 
  		if (usertype == "02") {
  			$("#unit").attr("style","display:none;");//隐藏div
  			$("#bank").attr("style","display:none;");//隐藏div
  			$("#enterprise").attr("style","display:block;");//显示div
  		}
  		if (usertype == "03") {
  			$("#unit").attr("style","display:none;");//隐藏div
  			$("#enterprise").attr("style","display:none;");//隐藏div
  			$("#bank").attr("style","display:block;");//显示div
  		}
  		if (usertype == "00") {
  			$("#unit").attr("style","display:none;");//隐藏div
  			$("#enterprise").attr("style","display:none;");//隐藏div
  			$("#bank").attr("style","display:none;");//隐藏div
  		}
  	}
    
    
    //窗口关闭并刷新父页面
  	function closeWin(){ 
    	//刷新
    	var doc = window.parent;
    	doc.refresh();
    	//关闭窗口
    	parent.layer.closeAll();
    	
  	}    
    //企业部分
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
	 //主管部分
 	 //选中添加
	function role_n1c(){			     
		 var options=$("#rolenc option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolesc").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        }); 	     
	}
	//选中删除
	function role_s1c(){	
		 var options=$("#rolesc option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolenc").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部添加
	function role_n2c(){			     
		 var options=$("#rolenc option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolesc").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部删除
	function role_s2c(){			     
		 var options=$("#rolesc option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolenc").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//双击添加
	function role_nc(){	
	     var options=$("#rolenc option:selected");//获取选中对象
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#rolesc").append("<option value='"+val+"'>"+text+"</option>");	     
	}
	//双击删除
	function role_sc(){			     
	     var options=$("#rolesc option:selected");
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#rolenc").append("<option value='"+val+"'>"+text+"</option>");	     
	}
	//银行
	 //选中添加
	function role_n1b(){			     
		 var options=$("#rolenb option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolesb").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        }); 	     
	}
	//选中删除
	function role_s1b(){	
		 var options=$("#rolesb option:selected");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolenb").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部添加
	function role_n2b(){			     
		 var options=$("#rolenb option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolesb").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//全部删除
	function role_s2b(){			     
		 var options=$("#rolesb option");//获取选中对象
		 var text = options.map(function(){return $(this).text();}).get().join(", ").split(',');
	     var val = options.map(function(){return $(this).val();}).get().join(", ").split(',');
	     options.remove();
	     $.each(text,function(n,value){	 
	    	 $("#rolenb").append("<option value='"+val[n]+"'>"+value+"</option>");  	
	        });       
	}
	//双击添加
	function role_nb(){	
	     var options=$("#rolenb option:selected");//获取选中对象
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#rolesb").append("<option value='"+val+"'>"+text+"</option>");	     
	}
	//双击删除
	function role_sb(){			     
	     var options=$("#rolesb option:selected");
	     var text = options.text();
	     var val = options.val();
	     options.remove();
	     //向另一个框追加 
	     $("#rolenb").append("<option value='"+val+"'>"+text+"</option>");	     
	}
    </script>
</body>
</html>

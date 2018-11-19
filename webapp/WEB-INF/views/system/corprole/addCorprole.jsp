<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
 <!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
	<link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css"> 
    <link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
	
	<script type="text/javascript">
	//行政代码树
    var zoneNodes =  ${zoneTree};
	var settingZone = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClickZone,
			onClick: onClickZone
		}
	};
    
	function beforeClickZone(treeId, treeNode) {
		var check = (treeNode);
		return check;
	}
	
	//zonecode 存在了remark字段中
	function onClickZone(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeZone"),
		nodes = zTree.getSelectedNodes(),
		v = "";
	    x = ""; 
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		    x += nodes[i].remark + ","; 
		}
		if (v.length > 0 ){
			v = v.substring(0, v.length-1);
		}
		if (x.length > 0 ){
			x = x.substring(0, x.length-1);
		}
		var orgObj = $("#zonecodeName");
		orgObj.attr("value", v);
		var orgPidObj = $("#zonecode");
		orgPidObj.attr("value", x);
		//点击后关闭
		$("#zoneContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownByZone);
	}
	
	function showZone() {
		var cityObj = $("#zonecodeName");              //获取上层对象
		var cityOffset = $("#zonecodeName").offset();  //元素的偏移坐标。
		$("#zoneContent").css({left:cityOffset.left+"px", top:cityOffset.top+cityObj.outerHeight()+20 +"px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDownByZone);
	}
	
	function hideTreeZone() {
		$("#zoneContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownByZone);
	}
	
	function onBodyDownByZone(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "zonecodeName" || event.target.id == "zoneContent" || $(event.target).parents("#zoneContent").length>0)) {
			hideTreeZone();
		}
	}
	$(document).ready(function(){
		$.fn.zTree.init($("#treeZone"), settingZone, zoneNodes);
	});
	
	function saveForm(){
		var str = $("#roles option").map(function(){return $(this).val();}).get().join(", ");
        var cityObj = $("#roleval");
		cityObj.attr("value", str);
		if($('#roles').children('option').length > 0){
			$.ajax({
	    		url:"${ctx}/system/corprole/${add}",//提交地址
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
		}else{
			parent.layer.alert("已选角色不能为空，请添加！");
			return false;
		}  	
	};
	//窗口关闭并刷新父页面
  	function closeWin(){
    	//关闭窗口
    	var doc = window.parent;
    	doc.refresh();
    	parent.layer.closeAll();
  	}
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
            	<input type="hidden" name="roleval" id="roleval" />
                <label class="col-xs-12 control-label">单位类型</label>
                <div class="col-xs-12">
				  <select class="form-control " name="corptype" id="corptype">
				  	  <c:if test="${not empty corptype}">
					 	 <option value="${corptype }" selected="selected">${corpName }</option>
					  </c:if>
					<c:forEach items="${dictContentList}" var="obj" varStatus="status">
                        <option value="${obj.DICTLABEL }" >${obj.DICTVALUE }</option>
		            </c:forEach>
                  </select>
                  <input type="hidden" name="orgno" value="${orgno }" />
                  <input type="hidden" name="oldcorptype" value="${corptype }" />
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
												<td>备选</td>
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
													 <c:forEach items="${seledRoleMap}" var="obj2" varStatus="status">
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
    <script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/static/js/demo/peity-demo.min.js"></script> 
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
</body>
</html>

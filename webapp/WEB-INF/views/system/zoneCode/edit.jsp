<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <link href="${ctx}/static/css/plugins/switchery/switchery.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
     <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
    <link rel="stylesheet" href="${ctx}/static/ztree/css/mytree.css" type="text/css"> 
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
		          <div class="col-xs-12 ">
		              <label class="col-xs-12 control-label">所属上级区域</label>
		              <div class="col-xs-12">
		                  <input id="zonecodeName" type="text" readonly  onclick="showZone();" class="form-control" value="${pDataMap.ZONENAME }">
		                  <input name="pid" type="hidden" id="zonecode" value="${pDataMap.SID }" />
		                  <input type="hidden" name="formToken" value="${formToken }" />  
		              </div>
		              <div id="zoneContent" style="display:none; position: absolute;z-index:1;">
						  <ul id="treeZone" class="ztree"></ul>
					  </div>
					  </div>
		          </div>
			   <div class="form-group">
			   <div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">区域编码<span class="star">*</span></label>
                   <div class="col-xs-12">
                       <input type="text" name="zonecode" id="zonecode"  class="form-control" value="${dataMap.ZONECODE}">
                       <input type="hidden" name="sid" value="${dataMap.SID}" />
                   </div>
                   </div>
               </div>
               <div class="form-group">
               <div class="col-xs-12 ">
                   <label class="col-xs-12 control-label">区域名称<span class="star">*</span></label>
                   <div class="col-xs-12">
                       <input type="text" name="zonename" id="zonename"  class="form-control" value="${dataMap.ZONENAME}">
               	   </div>
               	   </div>
               </div>
               <div class="form-group">
               <div class="col-xs-12 ">
                    <label class="col-xs-12 control-label">区域类型${dataMap.ZONETYPE }<span class="star">*</span></label>
                    <div class="col-xs-12">
                    		<select class="form-control m-b "  name="zonetype" id="zonetype"> 
		                    	<jsdp:dic dictno="92" dictlabel="${dataMap.ZONETYPE }"></jsdp:dic>
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
	<script type="text/javascript" src="${ctx}/static/js/plugins/switchery/switchery.js"></script>
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
    <script type="text/javaScript">
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
		    x += nodes[i].id + ","; 
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
		$("#zoneContent").css({left:cityOffset.left+"px", top:cityOffset.top+cityObj.outerHeight()+"px"}).slideDown("fast");

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
    	var e = "<i class='fa fa-times-circle'></i> ";
    	$("#dataForm").validate({ignore: ":hidden:not(select)",
	        rules: {
	        	zonecode: "required",
	        	zonename: "required",
	        	zonetype: "required"
	        },
	        messages: {
	        	zonecode: e + "请输入区域编码",
	        	zonename: e + "请输入区域名称",
	        	zonetype: e + "请输入区域类型"
	        }
	    });
	});
    function saveForm(){
		if (!$("#dataForm").valid()) {
	        return;
	     }
		$.ajax({
            url:"${ctx}/system/zoneCode/update",//提交地址
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
    	//关闭
    	parent.layer.closeAll();
  	}  
    </script>
</body>
</html>

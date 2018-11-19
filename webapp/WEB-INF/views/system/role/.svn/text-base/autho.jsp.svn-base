<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <title>HFMP住宅专项维修资金系统</title>

    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"> 
    <%@ include file="/common/meta.jsp"%>
	
	<!-- ztree所需 -->
	<link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 	
	 <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/bootstrapStyle.css" type="text/css"> 
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>   
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script> 
	<SCRIPT type="text/javascript">
	
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
			/* view: {
				dblClickExpand: false
			},
			 callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}  */
		};

		var zNodes =  ${treelist}; 
		
		var code;
		
		function setCheck() {
			setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		/* //开始选中
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		//选中时
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
		    x = ""; 
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			    x += nodes[i].id + ","; 
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#val");
			cityObj.attr("value", v);
			var cityObj1 = $("#data");
			cityObj1.attr("value", x);
		} */
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
		}); 
		
		
	</SCRIPT>
	<!-- ztree所需结束 -->
    <style>
        #alertmod_table_list_2 {
            top: 900px !important;
        }
    </style>
	<script type="text/javascript">
	</script>
</head>

<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
        	<div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-xs-12">
		                   <form role="form" id="dataForm">
		                   <div class="zTreeDemoBackground left">
								<ul id="treeDemo" class="ztree"></ul>
						   </div>
						   <input name="menuids" type="hidden" id="data" value=""/>
						   <!-- <input name="pid" type="text" id="val" value=""/> -->
						   <input name="sid" type="hidden"  value="${sid }"/> 
<!--                                <div>     -->
<!--                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" onclick="saveForm();"><strong>保  存</strong></button>    -->
                                   <input type="hidden" name="formToken" value="${formToken }" />                              
<!--                                </div> -->
                           </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javaScript">
    function saveForm(){
    	//获取选中节点数据
    	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        nodes=treeObj.getCheckedNodes(true),
        v="";            
        for(var i=0;i<nodes.length;i++){
        v+=nodes[i].id + ",";          
        };        
        var cityObj1 = $("#data");
		cityObj1.attr("value", v); 
    	//提交数据
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
    } 
    
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

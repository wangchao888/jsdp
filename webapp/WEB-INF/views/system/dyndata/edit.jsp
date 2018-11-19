<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
    <title>HFMP住宅专项维修资金系统</title>
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"> 
    <%@ include file="/common/meta.jsp"%>
</head>
<body class="white-bg">
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-xs-12">
        <div class="ibox float-e-margins">
	      <!-- 标题头部分 开始 -->
          <div class="ibox-content">
            <form role="form" id="dataForm" class="form-horizontal">
            <input type="hidden" name="extid" id="extid" value="${extid }"/>
            <input type="hidden" name="keyid" value="${dataObj.KEYID }" />
            <input type="hidden" name="keyname" value="${dataObj.KEYNAME }" />
            <input type="hidden" name="tablename" value="${tableName }" />
             <c:forEach items="${ddynFieldList}" var="obj" varStatus="status">
            <!-- NORMALVALUE: 正常文本框输入存储 -->
			<!-- FIXVALUE: 固定值，不可更改的 -->
			<!-- DICVALUE: 字典值，不可更改的 -->   			         
		     		 <div class="form-group">
                     <label class="col-xs-3 control-label">${obj.FIELDNAMECN }</label>
                     <div class="col-xs-8">		
            <c:choose>            
            	<c:when test="${obj.TYPEVALUE == 'NORMALVALUE' }">
            		<input type="text" name="${obj.FIELDNAME }"
            		placeholder="请输入${obj.FIELDNAMECN }" 
            		class="form-control" value="${dataObj[obj.FIELDNAME] }">                    
                     	</div>
                 		</div>            	
            	</c:when>            
            	<c:when test="${obj.TYPEVALUE == 'DICVALUE' }">
					<select class="form-control m-b " name="${obj.FIELDNAME }" id="">
						<option value="">请选择</option>
					<c:forEach items="${obj.DICMAP}" var="objdic">												
						<option <c:if test="${objdic.VALUE == dataObj[obj.FIELDNAME]}">selected</c:if> value="${objdic.VALUE}">${objdic.KEY}</option>						
					</c:forEach>	        	                    
	                 </select>	 
	                 	</div>
                 		</div> 
				</c:when>				
				<c:when test="${obj.TYPEVALUE == 'FIXVALUE' }">
            		<input type="hidden" name="${obj.FIELDNAME }"
            		placeholder="请输入${obj.FIELDNAMECN }" 
            		class="form-control" value="${obj.FIXVALUE }">
            		${obj.FIXVALUE }                    
                     	</div>
                 		</div>            	
            	</c:when>                      
            </c:choose>           
           </c:forEach> 				
              <div class="form-group">
	            <div class="col-xs-offset-3 col-xs-8">
	              <button class="btn btn-sm btn-primary pull-center m-t-n-xs" type="button" id="saveBtn">保存</button>
	              <button class="btn btn-sm btn-default pull-center m-t-n-xs" type="button" id="closeBtn">关闭</button>
	            </div>
		      </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
    
	<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javaScript">
    $(document).ready(function(){
    	var extid = $("#extid").val();
		$("#saveBtn").click(function(){
			$.ajax({
	            url:"${ctx}/system/dyndata/${add}",//提交地址
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
		});
		
		$("#closeBtn").click(function(){
			parent.layer.closeAll();
		});
		
	});
        
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

<%@ page language="java" pageEncoding="UTF-8"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="Expires" content="-1"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script language="text/javascript">
    var _ctx = "${ctx}";
</script>
<title>HFMP住宅专项维修资金系统</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--框架必需start-->
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx}/static/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${ctx}/static/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/css/animate.min.css" rel="stylesheet">
<link href="${ctx}/static/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-select.css" rel="stylesheet">
<link href="${ctx}/static/css/loading.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>	
<script src="${ctx}/static/js/notbackspace.js"></script>
<script src="${ctx}/static/js/bootstrap-select.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script type="text/javascript">
	$(document).ready(
		function() {
			$('#dataForm').on('keydown','input, select',function(e) {
				var self = $(this), form = self.parents('form:eq(0)'), focusable, next;
					if (e.keyCode == 13) {
						focusable = form.find('input,a,select,button').filter(':visible');
						next = focusable.eq(focusable.index(this) + 1);
						if (next.length) {
							$('.datepicker').css('display','none')
							next.focus();
						} else {
							//saveForm();
						}
						return false;
					}
				});
						//获取多标签内容页高度
		function reinitIframe() {
			var iframe = document.getElementsByTagName("iframe");
			$.each(iframe,function(index, item) {
				if (item.name.indexOf('layer') !== -1) {
					return false;
				} else {
					try {
						var bHeight = this.contentWindow.document.body
								.getElementsByTagName("div")[0].scrollHeight;
						Mheight = bHeight > 350 ? bHeight
								: 350;
						item.height = Mheight;
						$(item).css(
								'min-height',
								Mheight)
					} catch (ex) {
					}
				}
			})
		}
		window.setInterval(reinitIframe, 200);
		if($('.Table_title_box')){
			$('.Table_title_box').append('<div style="clear:both;"></div>')
		}						
		if(window.screen.width < 1400){
			$("<link>").attr({ rel: "stylesheet",
			type: "text/css",
			tik:'link_small_css',
			href: "${ctx}/static/css/smallResolution.css"
			}).appendTo("head");
		}else if(window.screen.width <= 1680){
			$("<link>").attr({ rel: "stylesheet",
				type: "text/css",
				tik:'link_middle_css',
				href: "${ctx}/static/css/middleResolution.css"
				}).appendTo("head");
	    }else if(window.screen.width <= 1920){
			$("<link>").attr({ rel: "stylesheet",
				type: "text/css",
				tik:'link_big_css',
				href: "${ctx}/static/css/bigResolution.css"
				}).appendTo("head");
	    }else{
			$('[tik = "link_small_css"]').remove() 
			$('[tik = "link_middle_css"]').remove() 
			$('[tik = "link_big_css"]').remove() 
		}
		//页面进入等待效果添加
		var html = '';
		html += '<div class="loading_bg" style="z-index:20891019;">';
		html += '<div class="loading_box">';
		html += '<img src="${ctx}/static/img/wait.png"/>';
		html += '<h3 style="color: #fff">服务器正在处理，请等待...</h3>';
		html += '</div>';
		html += '</div>';
		$('body').append(html);
	})
	function loadStart() {
		$('.loading_bg').css('display', 'block');
	}
	function loadEnd() {
		$('.loading_bg').css('display', 'none');
	}
	function conditionReset(){
		$('.dataTables_top').find('select').each(function(){
			var  z_option =	$(this).next('div').find('ul.dropdown-menu').children('li:first').children('a').children('span:first').html();
			$(this).next('div').children('button').attr('title',z_option).find('span:first').html(z_option);
			$(this).next('div').find('ul.dropdown-menu').children('li').removeClass('selected');
			$(this).next('div').find('ul.dropdown-menu').children('li:first').addClass('selected');
			$(this).children('option').attr('selected',false);
			$(this).children('option:first').attr('selected',true);
		});
		$('.dataTables_top').find('input').each(function(){
			$(this).val('');
		});
	}
	 function DisplayBtn(j,arrBtn){ 
	    	if(arrBtn.length>0){
	    		arrBtn.hide()
	    		$('.Search_reset_box').append('<a style="color:#4A4A4A;display:inline-block;font-size:12px;letter-spacing:0;" id="showInput">展开<i class="fa fa-caret-down" style="margin-left:6px;"></i></a>')				
				$('.dataTables_top').append('<div style="clear:both;float:none !important;"></div>')
		        $("#showInput").click(function() {
		            if(j==0){
			    		 arrBtn.show()
		            	 $('#showInput').html('<p>收起<i class="fa fa-caret-up" style="margin-left:6px;"></i></p>')
		                 $('.Search_reset_box').css({'width':'100%','border-top':'1px dotted #C5C1C1','text-align':'right','padding-top':'5px'})
		                 $('.dataTables_top').find('div.bootstrap-select').css('margin-bottom','5px')
		                 $('.dataTables_top').find('input').css('margin-bottom','5px')
		                 j=1;		                    
		            }else  {
		            	 arrBtn.hide()
		                 $('#showInput').html('<p>展开<i class="fa fa-caret-down" style="margin-left:6px;"></i></p>')
		                 $('.Search_reset_box').css({'width':'auto','border-top':'0px dotted #C5C1C1','text-align':'left','padding-top':'0'})
		                 $('.dataTables_top').find('div.bootstrap-select').css('margin-bottom','0')
		                 $('.dataTables_top').find('input').css('margin-bottom','0')
		                 j=0;
		            }
		        });
	    		for(var k=0;k<arrBtn.length;k++){
	    			if($(arrBtn[k]).val() !== ''){
		    			 arrBtn.show()
		            	 $('#showInput').html('<p>收起<i class="fa fa-caret-up" style="margin-left:6px;"></i></p>')
		                 $('.Search_reset_box').css({'width':'100%','border-top':'1px dotted #C5C1C1','text-align':'right','padding-top':'5px'})
		                 $('.dataTables_top').find('div.bootstrap-select').css('margin-bottom','5px')
		                 $('.dataTables_top').find('input').css('margin-bottom','5px')
		                 j=1;	
		    			 break; 
		    		}
	    		}
	    	}else{
	    		return false;
	    	}
	    }

	 function outputmoney(number) {
	 	if (isNaN(number) || number == "") return "";
	 	number = Math.round(number * 100) / 100;
	 	if (number < 0)
	 		return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
	 	else
	 		return outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);
	 }
	 //格式化金额 
	 function outputdollars(number) {
	 	if (number.length <= 3)
	 		return (number == '' ? '0' : number);
	 	else {
	 		var mod = number.length % 3;
	 		var output = (mod == 0 ? '' : (number.substring(0, mod)));
	 		for (i = 0; i < Math.floor(number.length / 3); i++) {
	 			if ((mod == 0) && (i == 0))
	 				output += number.substring(mod + 3 * i, mod + 3 * i + 3);
	 			else
	 				output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
	 		}
	 		return (output);
	 	}
	 }
	 function outputcents(amount) {
	 	amount = Math.round(((amount) - Math.floor(amount)) * 100);
	 	return (amount < 10 ? '.0' + amount : '.' + amount);
	 }
</script>
<%-- <script src="${ctx}/static/js/placeholder.js"></script> --%>
<!--框架必需end-->
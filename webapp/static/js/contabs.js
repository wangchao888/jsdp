$(function() {
	function f(l) {
		var width = 0; //定义一开始标签页内容宽度为0
		$(l).each(function() { //对标签页内容进行循环
			width += $(this).outerWidth(true) //计算出标签页的总长度
		});
		return width
	}
	function g(n) { //添加标签页
		var o = f($(n).prevAll()), q = f($(n).nextAll());//点击左侧列表添加前后标签页的宽度
		var l = f($(".content-tabs").children().not(".J_menuTabs"));//获取没有被选中的标签页宽度
		var k = $(".content-tabs").outerWidth(true) - l;  //获取整个标签页的宽度
		var p = 0;
		if ($(".page-tabs-content").outerWidth() < k) { //根据标签页总长度与标签页已固定长度的比较，来决定所有标签的左右移动
			p = 0
		} else {
			if (q <= (k - $(n).outerWidth(true) - $(n).next().outerWidth(true))) {
				if ((k - $(n).next().outerWidth(true)) > q) {
					p = o;
					var m = n;
					while ((p - $(m).outerWidth()) > ($(".page-tabs-content")
							.outerWidth() - k)) {
						p -= $(m).prev().outerWidth();
						m = $(m).prev()
					}
				}
			} else {
				if (o > (k - $(n).outerWidth(true) - $(n).prev().outerWidth(
						true))) {
					p = o - $(n).prev().outerWidth(true)
				}
			}
		}
		$(".page-tabs-content").animate({
			marginLeft : 0 - p + "px"
		}, "fast")
	}
	function leftShift() {  //点击向左箭头的方法
		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
		var l = f($(".content-tabs").children().not(".J_menuTabs"));
		var k = $(".content-tabs").outerWidth(true) - l;
		var p = 0;
		if ($(".page-tabs-content").width() < k) {
			return false
		} else {
			var m = $(".J_menuTab:first");
			var n = 0;
			while ((n + $(m).outerWidth(true)) <= o) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			n = 0;
			if (f($(m).prevAll()) > k) {
				while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
					n += $(m).outerWidth(true);
					m = $(m).prev()
				}
				p = f($(m).prevAll())
			}
		}
		$(".page-tabs-content").animate({
			marginLeft : 0 - p + "px"
		}, "fast")
	}
	function rightShift() {  //点击向右箭头的方法
		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
		var l = f($(".content-tabs").children().not(".J_menuTabs"));
		var k = $(".content-tabs").outerWidth(true) - l;
		var p = 0;
		if ($(".page-tabs-content").width() < k) {
			return false
		} else {
			var m = $(".J_menuTab:first");
			var n = 0;
			while ((n + $(m).outerWidth(true)) <= o) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			n = 0;
			while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			p = f($(m).prevAll());
			if (p > 0) {
				$(".page-tabs-content").animate({
					marginLeft : 0 - p + "px"
				}, "fast")
			}
		}
	}
	$(".J_menuItem").each(function(index) {//页面加载时获取J_menuItem循环
		if (!$(this).attr("data-index")) {//如果目标没有data-index属性时添加data-index属性,并赋值为index
			$(this).attr("data-index", index);
		}
	});
	//左侧导航栏点击事件
	function addpage() {
		var o_href = $(this).attr("href"),/*获取href里的值*/ 
//			m = $(this).data("index"),/*获取index里的值 */
			m = $(this).attr("menuid"),/*获取index里的值 */
			l = $.trim($(this).text()),/*获取值并去掉空格*/ 
			menuid = $(this).attr('menuid'),
			sessionId = $(this).attr('sessionId'),
			k = true;
		if (o_href == undefined || $.trim(o_href).length == 0) {//href里没有值的话返回false			
			return false
		}
		if(o_href.indexOf('?')>0){
			var o = o_href + '&menuid=' + menuid + '&sessionId=' +sessionId;
		}else{
			var o = o_href + '?menuid=' + menuid + '&sessionId=' +sessionId;
		}
		
		$(".J_menuTab").each(//获取标签			
				function() {
					if ($(this).data("id") == o) {//判断标签页是否已存在
						if (!$(this).hasClass("active")) {//判断是否包含active类
							$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
							g(this);
							$(".J_mainContent .J_iframe").each(
									function() {
										if ($(this).data("id") == o) {
											$(this).show().siblings(".J_iframe").hide();//隐藏所有.J_iframe只显示当前.J_iframe
											return false
										}
									})
						}
						k = false;
						return false
					}
				});
		if (k) {
			var p = '<a href="javascript:;" class="active J_menuTab" data-id="'
					+ o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';//标签
			$(".J_menuTab").removeClass("active");//移除active类
			var n = '<iframe class="J_iframe" name="iframe' + m
					+ '" width="100%" height="100%" src="' + o
					+ '" frameborder="0" data-id="' + o
					+ '" seamless></iframe>';//内容页
			$(".J_mainContent").find("iframe.J_iframe").hide().parents(
					".J_mainContent").append(n);//添加内容页
			$(".J_menuTabs .page-tabs-content").append(p);//添加标签页
			g($(".J_menuTab.active"))
		}
		return false
	}
	$(".J_menuItem").on("click", addpage);//左侧导航栏点击
	function tabClick() {  //标签页tab上的点击事件
		var m = $(this).parents(".J_menuTab").data("id");//获取标签页对应内容页地址     父元素？
		var l = $(this).parents(".J_menuTab").width();//获取标签页宽度     		父元素？
		if ($(this).parents(".J_menuTab").hasClass("active")) {//判断标签页是否是当前页面标签页
			if ($(this).parents(".J_menuTab").next().length) {//判断有没有下一个标签页
				var k = $(this).parents(".J_menuTab").next(".J_menuTab:eq(0)")
						.data("id");//第一个标签页的地址
				$(this).parents(".J_menuTab").next(".J_menuTab:eq(0)")
						.addClass("active");//给首个标签页+active类
				$(".J_mainContent .J_iframe").each(function() {//获取内容页div和内容页
					if ($(this).data("id") == k) {
						$(this).show().siblings(".J_iframe").hide();
						return false
					}
				});
				var n = parseInt($(".page-tabs-content").css("margin-left"));
				if (n < 0) {
					$(".page-tabs-content").animate({
						marginLeft : (n + l) + "px"
					}, "fast")
				}
				$(this).parents(".J_menuTab").remove();
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == m) {
						$(this).remove();
						return false
					}
				})
			}
			if ($(this).parents(".J_menuTab").prev(".J_menuTab").length) {
				var k = $(this).parents(".J_menuTab").prev(".J_menuTab:last")
						.data("id");
				$(this).parents(".J_menuTab").prev(".J_menuTab:last").addClass(
						"active");
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == k) {
						$(this).show().siblings(".J_iframe").hide();
						return false
					}
				});
				$(this).parents(".J_menuTab").remove();
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == m) {
						$(this).remove();
						return false
					}
				})
			}
		} else {
			$(this).parents(".J_menuTab").remove();
			$(".J_mainContent .J_iframe").each(function() {
				if ($(this).data("id") == m) {
					$(this).remove();
					return false
				}
			});
			g($(".J_menuTab.active"))
		}
		return false
	}
	$(".J_menuTabs").on("click", ".J_menuTab i", tabClick);
	function closeOther() {  //关闭其他tab的点击事件
		$(".page-tabs-content").children("[data-id]").not(":first").not(
				".active").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
			$(this).remove()
		});
		$(".page-tabs-content").css("margin-left", "0")
	}
	$(".J_tabCloseOther").on("click", closeOther);
	function j() {
		g($(".J_menuTab.active"))
	}
	$(".J_tabShowActive").on("click", j);
	function e() {
		if (!$(this).hasClass("active")) {
			var k = $(this).data("id");
			$(".J_mainContent .J_iframe").each(function() {
				if ($(this).data("id") == k) {
					$(this).show().siblings(".J_iframe").hide();
					return false
				}
			});
			$(this).addClass("active").siblings(".J_menuTab").removeClass(
					"active");
			g(this)
		}
	}
	$(".J_menuTabs").on("click", ".J_menuTab", e);
	function d() {		
		//H+自带代码 不知道有什么用
		var l = $('.J_iframe[data-id="' + $(this).data("id") + '"]');
		var k = l.attr("src")
		
		/**
		 * 功能：实现双击刷新页面
		 * 		
		 *       作者：孙晨阳
		 *         	2017-04-25
		 */
		var o = $(this).data("id");//获取标签页对应内容页地址 
		var m;	//获取标题页index	
		$(".J_menuItem").each(function() {
			if($(this).attr("href") == o){
				//m = $(this).data("index");
				m = $(this).attr("menuid");
			}		
		})
		//关闭内容页
		$(".J_mainContent .J_iframe").each(function() {
			if ($(this).data("id") == o) {								
				$(this).remove();
				return false
			}
		})
		var n = '<iframe class="J_iframe" name="iframe' + m
					+ '" width="100%" height="100%" src="' + o
					+ '" frameborder="0" data-id="' + o
					+ '" seamless></iframe>';//内容页	
		$(".J_mainContent").find("iframe.J_iframe").hide().parents(
					".J_mainContent").append(n);//添加内容页
		/*------------------双击刷新结束-----------------*/
	}
	$(".J_menuTabs").on("dblclick", ".J_menuTab", d);
	$(".J_tabLeft").on("click", leftShift);
	$(".J_tabRight").on("click", rightShift);
	$(".J_tabCloseAll")
			.on(
					"click",
					function() {
						$(".page-tabs-content").children("[data-id]").not(
								":first")
								.each(
										function() {
											$(
													'.J_iframe[data-id="'
															+ $(this)
																	.data("id")
															+ '"]').remove();
											$(this).remove()
										});
						$(".page-tabs-content").children("[data-id]:first")
								.each(
										function() {
											$(
													'.J_iframe[data-id="'
															+ $(this)
																	.data("id")
															+ '"]').show();
											$(this).addClass("active")
										});
						$(".page-tabs-content").css("margin-left", "0")
					})
						
});

$(document).ready(function(){
	$.each($('[data-toggle="date"]'),function(){
		var _this = this;
		$(this).bind('blur.selfBlur',function(e){
			if(isDate($(this)[0]))
				$(this).trigger('blur.frameBlur');
		})
	});
	if($('.input-group.date').length>0){
		$('.input-group.date').datepicker({
	         todayBtn: "linked",
	         keyboardNavigation: !1,
	         forceParse: !1,
	         calendarWeeks: !0,
	         todayHighlight: 1,
	         autoclose: !0,         
	 }).on('changeDate',function(ev){
		 $('.input-group.date').children('input').focus()
	  });
	}
	
//ÅÐ¶ÏÊÇ·ñÈÕÆÚ,¸ñÊ½Îªyyyy-MM-dd;yyyyMMDD;yyyy/mm/dd;yyyy.mm.dd
function isDate(element){
  if(element.value == null)
    return true;
  element.value = element.value.trim();
  if(element.value == "")
    return true;

  var date = new String(element.value);

  while (date.indexOf(".") != -1)
  {
    date = date.replace(".","-");
  }
  while (date.indexOf("/") != -1)
  {
    date = date.replace("/","-");
  }

  if ((date.indexOf(".") == -1)&&(date.indexOf("-") == -1)&&(date.indexOf("/") == -1))
  {
    date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
  }
  var pattern1 = /[^0-9\-]/;
  var pattern2 = /([0-9]{4})+\-([0-9]{1,2})+\-([0-9]{1,2})$/;
  if(pattern1.test(date) || !pattern2.test(date))
  {
    //alert(info + "ÈÕÆÚ¸ñÊ½´íÎó£¡ÇëÊäÈë¸ñÊ½Îª yyyy-MM-dd µÄÈÕÆÚ¡£");
    element.focus();
    //element.select();
    element.value = "";
	 alert('请输入正确的日期格式  例:2018-01-01或20180101');
    return false;
  }
  
  sdate = date.split("-");

  var year = parseInt(sdate[0],10);
  var month = parseInt(sdate[1],10);
  var day = parseInt(sdate[2],10);

  if(year<1900 || year>2050)
  {
    //alert(info + "ÈÕÆÚ¸ñÊ½´íÎó£¡ÇëÊäÈë 1900-2050 Ö®¼äµÄÄê·Ý¡£");
    element.focus();
    //element.select();
    element.value = "";
	 alert('请输入正确的日期格式  例:2018-01-01或20180101');
    return false;
  }

  var months = new Array(0,31,28,31,30,31,30,31,31,30,31,30,31);
  
  if(month<1 || month>12)
  {
    //alert(info + "ÈÕÆÚ¸ñÊ½´íÎó£¡ÇëÊäÈë 01-12 Ö®¼äµÄÔÂ·Ý¡£");
    element.focus();
    //element.select();
    element.value = "";
	alert('请输入正确的日期格式  例:2018-01-01或20180101');
    return false;
  }
  var leapyear = (year%400==0 || (year%100!=0&&year%4==0))?true:false;
  if(month!=2 || (month==2 && leapyear==false))
  {
    if(day<1 || day>months[month])
    {
      //alert(info + "ÈÕÆÚ¸ñÊ½´íÎó£¡ÇëÊäÈë 01-" + months[month] + "Ö®¼äµÄÈÕÊý¡£");
      element.focus();
      //element.select();
      element.value = "";
      alert('请输入正确的日期格式  例:2018-01-01或20180101');
      return false;
    }
  }
  else
  {
    if(day<1 || day>29)
    {
      //alert(info + "ÈÕÆÚ¸ñÊ½´íÎó£¡ÇëÊäÈë 01-29 Ö®¼äµÄÈÕÊý¡£");
      element.focus();
      element.value = "";
      alert('请输入正确的日期格式  例:2018-01-01或20180101');
      return false;
    }
  }
  
  element.value = year + "-" + (month<10?"0"+month:month) + "-" + (day<10?"0"+day:day);
  return true;
}
})
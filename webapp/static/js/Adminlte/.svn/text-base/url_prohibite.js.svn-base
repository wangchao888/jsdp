 /* ----------- F12禁用 ------------ */
document.oncontextmenu = function () { return false; };
   document.onkeydown = function () {
     if (window.event && window.event.keyCode == 123) {
        event.keyCode = 0;
        event.returnValue = false;
        return false;
       /*---------- Ctrl+Shift+I禁用---------*/
      }else if((window.event.ctrlKey) && (window.event.shiftKey) && (window.event.keyCode == 73)) {
    	  return false;
      }
  };
/*--------------禁用鼠标右键------------- */
document.onmousedown=function norightclick(e){
    if (window.Event){
        if (e.which == 2 || e.which == 3)
           return false;
     } else if (event.button == 2 || event.button == 3) {
          event.cancelBubble = true;
          event.returnvalue = false;
          return false;
     }
};

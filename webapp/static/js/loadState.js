$(document).ready(function(){
	var html='';
	html += '<div class="loading_bg">';
	html += '<div class="loading_box">';
	html += '<img src="../../static/img/wait.png"/>';
	html += '<h3 style="color: #fff">loading...</h3>';
	html  += '</div>';
	html += '</div>';
    $('body').append(html);
})
function loadStart(){
   $('.loading_bg').css('display','block');
} 
function loadEnd(){
	$('.loading_bg').css('display','none');
}
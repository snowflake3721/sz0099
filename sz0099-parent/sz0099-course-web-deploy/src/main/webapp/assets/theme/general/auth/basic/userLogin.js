function userLogin(){
	var username=$("#username").val();
	var mobile=$("#mobile").val();
	var password=$("#password").val();
	var showTipDivId="id_tip_show";
	var usernameChecked=validateContentEmpty(username, "ID不能为空！", showTipDivId);
	if(!usernameChecked){
		return false;
	}
	usernameChecked = checkOnlyNum(username);
	if(!usernameChecked){
		var tip="ID格式不正确!";
		popTip(tip);
		return false;
	}
	
	var usernameLength = username.length;
	if(usernameLength<6){
		var tip="ID格式不正确!";
		popTip(tip);
		return false;
	}
	
	var mobileChecked=validateContentEmpty(mobile, "手机号不能为空！", showTipDivId);
	if(!mobileChecked){
		return false;
	}
	mobileChecked=isPoneAvailable(mobile);
	if(!mobileChecked){
		var tip="手机号格式不正确!";
		popTip(tip);
		return false;
	}
	
	var appIdChecked=validateContentEmpty(password, "密码不能为空！", showTipDivId);
	if(!appIdChecked){
		return false;
	}
	$("#id_form_sys_user_login").submit();
}

function initLoginSwiper(){
	var mySwiper = new Swiper('.swiper-container', {
	  	autoplay: {
	    delay: 3000,
	    stopOnLastSlide: false,
	    disableOnInteraction: true,
	    },
	    effect : 'fade',
	  	fadeEffect: {
	    crossFade: true,
	  }
	});
}
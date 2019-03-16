function validateMobile(){
	var id_login_mobile = $("#id_login_mobile");
	var mobile=id_login_mobile.val();
	console.log(" -- validateMobile : >>> " + mobile);
	$("#messageTip").html("");
	if(mobile==''){
		var tip = "手机号码不能为空";
		showError(tip);
		return false;
	}
	if(mobile.length<11){
		showError("手机号码长度为11位，请检查");
		return false;
	}
	//执行校验
	if(!isPoneAvailable(mobile)){
    	showError("手机号格式不正确");
		return false;
	}
	return true;
}

function validatePwd(){
	var id_login_pwd = $("#id_login_pwd");
	var pwd = id_login_pwd.val();
	if(pwd==''){
	 	showError('!!密码不能为空');
	 	return false;
	 }
	 
	 if(pwd.length<6){
	 	showError('!!密码长度应大于6');
	 	return false;
	 }
	 var id_login_jcaptchaCode = $("#id_login_jcaptchaCode");
	 var jcaptchaCode = id_login_jcaptchaCode.val();
	 if(jcaptchaCode==''){
	 	showError('!!验证码不能为空');
	 	return false;
	 }
	 
	 if(jcaptchaCode.length!=4){
	 	showError('!!验证码长度是4位');
	 	return false;
	 }
	 return true;
	
}

function doLogin(){

	var mobileChecked = validateMobile();
	if(!mobileChecked){
		return false;
	}
	var pwdChecked = validatePwd();
	if(!pwdChecked){
		return false;
	}
	$("#messageTip").html("");
	var id_login_mobile = $("#id_login_mobile");
	var mobile=id_login_mobile.val();
	var id_login_jcaptchaCode = $("#id_login_jcaptchaCode");
	var jcaptchaCode = id_login_jcaptchaCode.val();
	var id_login_pwd = $("#id_login_pwd");
	var pwd = id_login_pwd.val();
	axios({
	    method: 'post',
	    url: '/login/ansy',
	    data: 
	    	Qs.stringify({
	    		'mobile':mobile,
			    'pwd': pwd,
			    'validateCode': jcaptchaCode,
			    'jcaptchaCode': jcaptchaCode
	    	})
	    
	})
	.then(function (response) {
	    
	    var id_input_success_code=$("#id_input_success_code");
	    var successCode = id_input_success_code.val();
	    var respCode = response.data.respCode;
	    var respMsg = response.data.respMsg;
	    if(successCode==respCode){
	    	$.showSuccessTimeout(respMsg, function(){
	    		var currentUrl=window.location.href;
            	window.location.href=currentUrl;
	    	});
	    }else{
	    	showError(respMsg);
	    }
	    var refresh=response.data.content;
	    if("refresh"==refresh){
				reloadValidateCode('btn_jcaptcha_code_refresh', '看不清？');//刷新验证码
		}
	    
	})
	.catch(function (error) {
	    console.log(error);
	});
	
}
function showError(tip){
	$("#messageTip").html(tip);
	$.showErr(tip);
}

function forgetPasswd(){
  	window.location.href="/findpwdUIa?findType=10&findStep=10";
  }
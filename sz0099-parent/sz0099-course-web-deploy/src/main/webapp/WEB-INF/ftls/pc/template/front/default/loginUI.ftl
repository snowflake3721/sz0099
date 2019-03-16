<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>用户登录</title>
</head>

<body>

<div class="container " id="body_content">


  <div class="text-center"><h3>登   录</h3></div>
  
		<form id="form_user_login" role="form" action="/login" method="post" enctype="application/x-www-form-urlencoded">
		
				<input type="hidden" name="token" value="${token}"/> 
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="email_1">Email:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="username" class="form-control" id="username_1" placeholder="输入Email登陆邮箱"/>
				    </div>
				   </div>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="password_1">密码:
				    </label>
				    <div class="col-sm-10">
				    	<input type="password" name="password" class="form-control" id="password_1" placeholder="输入密码"/>
				    </div>
				   </div> 
				    
				   <div class="form-group row">
					    <label class="col-sm-2 col-form-label" for="validateCode_1">验证码：
					    </label>
					    <div class="col-sm-10">
					    	<input type="text" name="jcaptchaCode" class="form-control" id="validateCode_1"/>&nbsp;&nbsp;
					    </div>
				   </div>
				   <div class="form-group row">
				   		<div class="col-sm-2"></div>
				   		<div class="col-sm-10">
					 		<img id="validateCodeImg" src="/jcaptcha_code" />&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
					    </div>
				    </div>
				    
				   <button class="btn btn-lg btn-primary btn-block" type="submit">登  录</button>
			       <ul class="list-inline">
					  <li><span class="text-info">已有帐号，点击登录 </span></li>
					  <li><a class="text-danger" href='/registerUserUI'>没有帐号?<strong>注册一个</strong></a></li>
				   </ul>
		</form>


<script type="text/javascript">
<!--
function reloadValidateCode(){
	var url="/jcaptcha_code?data=" + new Date() + Math.floor(Math.random()*24);
	document.getElementById("validateCodeImg").src=url;
    $("#validateCodeImg").attr("src","/jcaptcha_code?data=" + new Date() + Math.floor(Math.random()*24));
}
//-->
</script>
</div>
</body>
</html>
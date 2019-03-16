<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>
<title>用户登录-卓玛拉山</title>
</head>

<body>
<div class="container " id="body_content">
		<#if imageList! && imageList?size gt 0>
		<div class="swiper-container">
		  <div class="swiper-wrapper">
		  	<#list imageList as image>
		    <div class="swiper-slide"><img src="${image.accessUrl}" class="img-responsive" alt="${image.alt}" title="${image.alt}"/></div>
		    </#list>
		  </div>
		</div>
		</#if>
		
  		<div class="text-center"><h3>用户登录</h3></div>
  		<div class="text-center text-danger" id="id_tip_show">${user.respMsg}</div>
		<form id="id_form_sys_user_login" role="form" action="/user/doLogin" method="post" enctype="application/x-www-form-urlencoded">
				  <input type="hidden" name="token" value="${token}"/>
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="username">用户ID:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="username" value="${user.username}" class="form-control" id="username" placeholder="输入用户ID" />
				    </div>
				   </div>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="mobile">手机号:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="mobile" value="${user.mobile}" class="form-control" id="mobile" placeholder="输入手机号" />
				    </div>
				  </div>
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="password">密码:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="pwd" value="" class="form-control" id="password" placeholder="输入密码" />
				    </div>
				  </div>
				  <p><span class="text-danger">提示：</span>本站施行<strong>邀请注册制</strong>，请使用微信扫描<span class="text-danger">已注册用户的邀请二维码</span>注册</p>
				  
				   <button class="btn btn-info btn-block" type="button" onclick="userLogin()">登  录</button>
				   <hr/>
				   <p class="text-right"><a href="/sz0099/ood/index" class="pull-left">回首页</a><small>本页仅用于手机网页端登录</small></p>
		</form>

</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/auth/basic/userLogin.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	if($(".swiper-container").length > 0){
		initLoginSwiper();
	}
});
</script>
</div>
</body>
</html>
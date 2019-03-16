<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>自动登录检测</title>
</head>

<body>

<div class="container " id="body_content">
  <div class="text-center"><h3>自动登录检测</h3></div>
  
  	<@shiro.user>
 	 欢迎[<@shiro.principal property="nickname"/>]登录
 	 <hr/>
 	 <a href = "/logout">退出</a>
	</@shiro.user>
	<br/>
	<@shiro.guest>
     <a href = "/basic/autologincheckUI"> 游客访问 </a>
	</@shiro.guest>
</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">

$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');	
});
	
	
	
</script>
</div>
</body>
</html>
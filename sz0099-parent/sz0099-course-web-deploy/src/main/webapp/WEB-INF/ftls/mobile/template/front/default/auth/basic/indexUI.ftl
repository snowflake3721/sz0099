<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>首页</title>
</head>

<body>

<div class="container " id="body_content">
  <div class="text-center"><h3>首页</h3></div>
  
   <@shiro.user>
 	<p> 欢迎 [<@shiro.principal property="nickname"/>]  登录系统</p>
 	 <hr/>
 	 <p> <a class="btn btn-danger" href="/logout">退出</a></p>
	</@shiro.user>
	<br/>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	
	<div class="container">
		<img class="img-thumbnail" src="/assets/jit4j_jui/image/160276515608108578.jpg" title="液压式速开帐篷 3~4人"/>
		<br/>
		<div class="text-info">长*宽*高(cm) : 
								200*230*140
		</div>
		<div class="text-info">
		350元/顶
		</div>
		<img class="img-thumbnail" src="/assets/jit4j_jui/image/20180422122515.jpg" title="群侠户外百公里"/>
		<div class="text-info">群侠户外
		</div>
	</div>
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
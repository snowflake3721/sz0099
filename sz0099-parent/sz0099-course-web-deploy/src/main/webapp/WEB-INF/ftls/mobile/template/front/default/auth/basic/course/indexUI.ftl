<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>首页</title>
</head>

<body>

<div class="container " id="body_content">
  <div class="text-center"><h3>技术课程学习</h3></div>
  
   <@shiro.user>
 	<p> 欢迎 [<@shiro.principal property="nickname"/>]  进入学习</p>
 	 <hr/>
 	 <p>JAVA技术栈视频教学</p>
 	 <p>架构：399元/套</p>
 	 <p>大数据： 299 元/套 </p>
 	 <p>vue2.0： 99 元/套 </p>
	</@shiro.user>
	<br/>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	
	<div class="container">
		<img class="img-thumbnail" src="/assets/jit4j_jui/image/160276515608108578.jpg" title="液压式速开帐篷 3~4人"/>
		<br/>
		<div class="text-info">加QQ:275060435  付款后 获取资料
		</div>
		<div class="text-info">
		加微信：ly275060435 付款后 获取链接
		</div>
		<img class="img-thumbnail" src="/assets/jit4j_jui/image/20180422122515.jpg" title="群侠户外百公里"/>
		<div class="text-info">价目表
		
			<!-- On rows -->
			<table class="table table-condensed">
				<tr class="info"><th>架构</th><th>399</th></tr>
				<tr class="warning"><td>架构</td><td>399</td></tr>
				<tr class="success"><td>大数据</td><td>399</td></tr>
			</table>
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
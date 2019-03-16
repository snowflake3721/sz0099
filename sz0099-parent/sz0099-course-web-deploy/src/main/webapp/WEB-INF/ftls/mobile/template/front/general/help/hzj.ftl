<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>

<title>通知</title>
</head>

<body>
<div class="container " id="body_content">
<button class="btn btn-danger" type="button">嫦娥四号，已飞月球！</button>
<#-- 
<@M_homeIndex/>
<h3 class="text-center">请阅读“压测”须知</h3>
<blockquote>
  <p class="text-center">本软件用于压力测试，请勿用于其他用途</p>
</blockquote>
<hr/>
<div class="panel panel-success">
	<div class="panel-heading">
    <h3 class="panel-title text-center">用于测试，验证开发成果</h3>
    </div>
  <div class="panel-body">
  	<p><strong>任何用于其他用途的行为，后果自负，与本软件无关，与网站无关</strong></p>
  </div>
</div>

<p>下载该软件，默认您已“同意”上述规约</p>
<p>本软件用于DD压力测试，请勿用于其他用途</p>
<@shiro.guest>
<p><a href="/assets/common/dx/jsz.apk" class="btn btn-warning">Jin--指D</a></p>
<p><a href="/assets/common/dx/sj_hzj_v3.0_vip.apk" class="btn btn-warning">压力测试D</a></p>
<p><a href="/assets/common/dx/xiaoer.apk" class="btn btn-warning">XE机D</a></p>
<p><a href="/assets/common/dx/MY_2.4.apk" class="btn btn-warning">MY机D 推荐</a></p>

</@shiro.guest>
<@shiro.user>
<h4 class="text-danger">您在微信中 登录，不可以 查看下载链接，请到浏览器中打开</h4>
<p>0.点击右上角...，选择“在浏览器中打开”</p>
<p>或者 复制链接，到浏览器中打开</p>
</@shiro.user>
<p>1.下载后，安装即可（仅支持安卓）</p>
<p>2.推荐【MY机D】，四款均可同时使用，建议一起使用</p>
<br/>
<br/>
<hr/>
<@M_homeIndex/>
 -->
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
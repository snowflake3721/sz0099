<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_help.ftl">
<html>
<head>

<title>数据初始化</title>
</head>

<body>

<div class="container " id="body_content">
<@M_homeNavigate/>
<hr/>
<br/>
<br/>
<br/>
<h3 class="text-center">数据初始化</h3>
<p class="text-center">
<button type="button" class="btn btn-danger btn-sm" 
id="id_init_data_appModule" 
data-saved="0"
data-url="/tech/doInitData/appModule"
onclick="initData('id_init_data_appModule')">
初始化Module
</button>
<button type="button" class="btn btn-info btn-sm" 
id="id_init_data_wechatConfig" 
data-saved="0"
data-url="/tech/doInitData/initWechatConfig"
onclick="initData('id_init_data_wechatConfig')">
初始化WechatConfig
</button>
</p>
<br/>
<br/>
<br/>
<hr/>
<@M_homeNavigate/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/dataInit.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	
});
</script>

</div>
</body>
</html>
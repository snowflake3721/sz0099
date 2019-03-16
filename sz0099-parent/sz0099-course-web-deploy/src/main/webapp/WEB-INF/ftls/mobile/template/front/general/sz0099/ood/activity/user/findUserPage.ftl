<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order_user.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<html>
<head>

<title>活动报名列表</title>
</head>

<body>
<div class="container " id="body_content">
<a class="btn btn-xs btn-primary" href="javascript:void(0)" onclick="history.go(-1)">返回列表</a>
<input type="hidden" id="id_hidden_orderPage" data-wrapperId="id_page_wrapper"/>
<@Mg_actOrderUserPage entityPage=entityPage activity=activity condition=condition/>

<@M_navBottomForCategory category="qunxia" subCategory="activity"  entity=null/>
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_order.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoOauthPageUrlLogin('${login_status}');
	console.log("after initPay");
	initPageForActUser("id_hidden_orderPage");
});
</script>

</div>
</body>
</html>
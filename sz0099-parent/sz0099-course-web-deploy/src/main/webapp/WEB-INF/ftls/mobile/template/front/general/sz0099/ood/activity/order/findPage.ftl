<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<html>
<head>

<title>活动订单</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>
<div class="container " id="body_content">
<@M_navBreadForCategory category="actOrder" subCategory="activity" showView="detail" entity=entity/>
<input type="hidden" id="id_hidden_orderPage" data-wrapperId="id_page_wrapper"/>
<@Mg_actOrderPage entityPage=pageResult />

<@M_navBottomForCategory category="ood" subCategory="activity"  entity=entity/>
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_order.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoOauthPageUrlLogin('${login_status}');
	console.log("after initPay");
	initPageForActOrder("id_hidden_orderPage");
});
</script>

</div>
</body>
</html>
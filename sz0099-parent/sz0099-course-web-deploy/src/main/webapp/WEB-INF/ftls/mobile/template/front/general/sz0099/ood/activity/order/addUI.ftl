<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<html>
<head>

<title>活动报名确认-群侠</title>
</head>

<body>
<div class="container " id="body_content">
<@shiro.user>
<@Mg_actOrder entity=entity/>
</@shiro.user>
<@M_navBottomForCategory category="qunxia" subCategory="activity"  entity=null/>
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_order.js?v=1.0.2'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	var actOrderId="id_act_order_id";
	calTotalAmount(actOrderId);
});
</script>

</div>
</body>
</html>
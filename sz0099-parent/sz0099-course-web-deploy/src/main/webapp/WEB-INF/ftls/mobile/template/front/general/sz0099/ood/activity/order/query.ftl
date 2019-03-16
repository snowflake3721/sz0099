<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<html>
<head>

<title>订单支付查询-卓玛拉山</title>
</head>

<body>
<div class="container " id="body_content">
<input type="text" name="id" value="${entity.id}" 
id="id_orderquery"
data-url="/sz0099/ood/activity/order/doOrderquery"
data-wrapper="id_orderquery_wrapper"
data-saved="0"
/>
<button type="button" class="btn btn-sm btn-primary" onclick="doOrderquery('id_orderquery')">查询</button>
<div id="id_orderquery_wrapper">
</div>
<@M_navBottomForCategory category="qunxia" subCategory="activity"  entity=null/>
</div>
<div class="container" id="body_footer_js">	
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jWechatPay.js?v=1.0.0'><\/script>");</script>
 -->
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_order.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoOauthPageUrlLogin('${login_status}');
	//initPay();
	console.log("after initPay");
});
</script>

</div>
</body>
</html>
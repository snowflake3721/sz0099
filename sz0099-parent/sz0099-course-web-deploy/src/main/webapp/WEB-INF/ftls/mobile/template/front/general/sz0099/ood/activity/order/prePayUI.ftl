<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<html>
<head>

<title>订单支付-卓玛拉山</title>
</head>

<body>
<div class="container " id="body_content">
<@shiro.user>
<br/>

<strong>订单编号 : </strong>${entity.outTradeNo}
<strong>支付金额 : </strong>${AmountUtil.changeF2Y(entity.totalRmb)} 元
<input type="hidden" id="respCode" name="respCode" 
	value="${respWechat.respCode}" 
/>
<p id="id_resp_wechat_respTip"></p>
<input type="hidden" id="id_resp_wechat" value="${respWechat.respCode}" 
	data-appId="${respWechat.payWechatCommon.appId}"
	data-timeStamp="${respWechat.payWechatCommon.timeStamp}"
	data-nonceStr="${respWechat.payWechatCommon.nonceStr}"
	data-package="${respWechat.payWechatCommon._package}"
	data-signType="${respWechat.payWechatCommon.signType}"
	data-paySign="${respWechat.payWechatCommon.paySign}"
	data-respTip="id_resp_wechat_respTip"
	data-redirectUrl="/sz0099/ood/home/article/index/recommend?st=general"
/>
<h4 class="text-center text-danger">${respWechat.respMsg}</h4>

</@shiro.user>
<br/>
<br/>
<br/>
<@M_navBottomForCategory category="qunxia" subCategory="activity"  entity=null/>
</div>
<div class="container" id="body_footer_js">	
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jWechatPay.js?v=1.0.0'><\/script>");</script>
 -->
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_order.js?v=1.0.2'><\/script>");</script>
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
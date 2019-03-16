<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#-- 
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForSuper.ftl">
 -->
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_help.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>

<title>圈设置</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<#-- 
<@M_navBreadForSuper category="quan" subCategory="setting"  entity=null/>
 -->
 <@M_navBreadForCategory category="setting" subCategory="quan" entity=entity/>
	<@shiro.user>
 	 欢迎大神 <strong><span class="text-success bg-info">≡ <@shiro.principal property="nickname"/> ≡</strong></span>
		<@shiro.hasRole name="plat_creator">
		<div class="list-group text-center">
			<a type="button" href="/quan/manage/initData" class="list-group-item list-group-item-warning">圈数据初始化</a>
			<a type="button" href="/quan/manage/syncFromUser" class="list-group-item list-group-item-primary">圈数据同步（用户圈子）</a>
		</div>
		</@shiro.hasRole>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
<#-- 
<@M_navBottomForSuper category="quan" subCategory="setting"  entity=null/>
 -->
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>

</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
});
</script>
</div>
</body>
</html>
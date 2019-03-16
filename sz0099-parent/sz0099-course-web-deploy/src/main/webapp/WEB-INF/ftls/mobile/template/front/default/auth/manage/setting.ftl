<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForSuper.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_help.ftl">
<html>
<head>

<title>用户设置</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForSuper category="usersetting" subCategory="setting"  entity=null/>
	<@shiro.user>
 	 欢迎大神 <strong><span class="text-success bg-info">≡ <@shiro.principal property="nickname"/> ≡</strong></span>
		<@shiro.hasRole name="plat_creator">
		<div class="list-group text-center">
			<a type="button" href="/auth/userqr/manage/clearAllUserQr" class="list-group-item list-group-item-warning">清除用户二维码</a>
			<#-- 
			<a type="button" href="/quan/manage/syncFromUser" class="list-group-item list-group-item-primary">圈数据同步（用户圈子）</a>
			 -->
		</div>
		</@shiro.hasRole>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
<@M_navBottomForSuper category="usersetting" subCategory="setting"  entity=null/>
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
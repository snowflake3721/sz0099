<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_activity_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<html>
<head>

<title>群侠户外-点赞用户最新文章列表</title>
</head>

<body>

<div class="container " id="body_content">
	<div id="id_profession_personal" class="container" style="z-index:5002"></div>
    <@M_activitySearchPraisePage entity=entity url="/sz0099/ood/activity/findActivityForPraisePage/"/>
    <@shiro.user>
 	<p>侠 <span class="text-success"> [<@shiro.principal property="nickname"/>]</span> 技:<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
 	<span class="pull-right">
 	<a href="/sz0099/ood/home/activity/index/recommend?st=general">户外圈</a>&nbsp;&nbsp;
 	</span>
 	</p>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	<p>===111====</p>
	<@M_mainTypeForPraisePage entity=entity url="/sz0099/ood/activity/findActivityForPraisePage/" showListLoadMore=true/>
	<@M_introduction/>
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="search" subCategory="activity"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/activity/coeActivity_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
});
//为点赞技能列表初始化分页对象
initPageForPraisePage();
</script>

</div>
</body>
</html>
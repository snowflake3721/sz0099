<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_article.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>美文分享-群侠户外</title>
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<@Mg_articleCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree currentUser=currentUser tagPage=tagPage/>
    <@Mg_swiper_article dto=headDto entityPage=recommendPage/>
    <@shiro.user>
		
		<#-- 
		<@M_explorerWord2Img dto=explorerDto/>
		<@M_rememberScreen dto=rememberDto/>
		<@M_farmstayRollerMulti dto=farmstayDto/>
		<@M_outdoorRollerMulti dto=outdoorDto/>
		<@M_equipRealImg2Word  dto=equipRealDto/>
		<@M_actionFlagImg2Img  dto=actionFlagDto/>
		 -->
	 	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	<@M_introduction/>
	<@M_navBottomForCategory category="qunxia" subCategory="article"  entity=null/>
	<input type="hidden" value="1" name="userId" id="id_current_userId"/>
	<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	 data-url="/sz0099/ood/article/findPageForInvitor"
	 />
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	var login_status=$("#id_login_status").val();
	autoCurrentOauthPageUrlLogin('${login_status}');
	var currentUserId=$("#id_current_userId").val();
	if(login_status=='true'){
		initBloomingForJsonWhenLoaded('/sz0099/ood/article/findPageForInvitor','10','id_current_userId') ;
	}
	
	initSwiper();
});
</script>
</div>
</body>
</html>
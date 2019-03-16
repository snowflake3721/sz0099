<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_profession.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_profession.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_profession.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>
<title>技能资源-卓玛拉山</title>
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<@Mg_professionCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree currentUser=currentUser tagPage=tagPage/>
    <@shiro.user>
    	<@Mgp_swiper_profession dto=headDto entityPage=recommendPage entity=entity/>
	 	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	</@shiro.user>
	<#-- 
	<@shiro.guest>
    <a href="/sz0099/ood/home/profession/index/recommend?st=general">游客访问 </a>
	</@shiro.guest>
	 -->
	<hr/>
	<@Mgp_introduction/>
	<@M_navBottomForCategory category="qunxia" subCategory="profession"  entity=null/>
	<input type="hidden" value="1" name="userId" id="id_current_userId"/>
	<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	 data-url="/sz0099/ood/personal/profession/findPageForInvitor"
	 />
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/personal/profession_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	var login_status=$("#id_login_status").val();
	autoCurrentOauthPageUrlLogin('${login_status}');
	var currentUserId=$("#id_current_userId").val();
	if(login_status=='true'){
		initBloomingForJsonWhenLoaded('/sz0099/ood/personal/profession/findPageForInvitor','10','id_current_userId') ;
	}
	
	initSwiper();
	
});
</script>
</div>
</body>
</html>
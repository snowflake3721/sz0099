<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_activity.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>
<title>活动-卓玛拉山户外联盟</title>
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<@Mg_activityCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree currentUser=currentUser tagPage=tagPage/>
    <@shiro.user>
    	<@Mg_swiper_activity dto=headDto entityPage=recommendPage entity=entity/>
	 	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	</@shiro.user>
	<hr/>
	
	<@Mga_introduction/>
	<@M_navBottomForCategory category="qunxia" subCategory="activity"  entity=null/>
	<input type="hidden" value="1" name="userId" id="id_current_userId"/>
	<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	 data-url="/sz0099/ood/activity/findPageForInvitor"
	 />
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	var login_status=$("#id_login_status").val();
	autoCurrentOauthPageUrlLogin('${login_status}');
	var currentUserId=$("#id_current_userId").val();
	if(login_status=='true'){
		initBloomingForJsonWhenLoaded('/sz0099/ood/activity/findPageForInvitor','10','id_current_userId') ;
	}
	
	initSwiper();
	
});
</script>
</div>
</body>
</html>
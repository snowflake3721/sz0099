<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_home_profession.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_home_profession.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/macro/macro_qrCode_view.ftl">
<html>
<head>

<title>群侠户外-技能</title>

</head>

<body>

<div class="container " id="body_content">
	<@M_professionCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree/>
    <@shiro.user>
    
 	<p>
 	侠 <span class="text-danger" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">◎ <@shiro.principal property="nickname"/> ◎</span></span> 
 	<span class="badge" style="background-color:#994455">神技</span>
 	：<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
 	</p>
 	
 	<@M_layout_invitorPage positon=null entityPage=invitorPage idPre="ly_invitor" />
 	<@M_headRoller dto=headDto/>
 	
 	<#-- 推荐技 -->
 	<@M_recommendImg3Multi dto=recommendDto/>
 	<#-- 能工巧匠技 -->
 	<@M_artisanImg2Img dto=artisanDto/>
 	<#-- 今日神技 -->
 	<@M_todayScreen dto=todayDto/>
 	
 	<#-- 户外专享 -->
 	<@M_outdoorWord2Img dto=outdoorDto/>
 	
 	<#-- 大侠必杀 -->
 	<@M_killImg2Word dto=killDto/>
 	
 	<#--我的邀请二维码 -->
	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	 	
	 	
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	
	<div id="id_profession_personal" class="container" style="z-index:5002"></div>
	<@M_dramala_wechat/>
	<@M_navBottomForCategory category="qunxia" subCategory="profession"  entity=null/>
	<input type="hidden" value="1" name="userId" id="id_current_userId"/>
	<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	 data-url="/sz0099/ood/personal/profession/findPageForInvitor"
	 />
</div>


<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/personal/profession_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	var bloomingMenu = null;
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
	//initBlooming(1,5);
	var login_status=$("#id_login_status").val();
	var currentUserId=$("#id_current_userId").val();
	if(login_status=='true'){
		initBloomingForJsonWhenLoaded('/sz0099/ood/personal/profession/findPageForInvitor','10','id_current_userId') ;
	}
	
	var circleMain='${circleMain}';
	if(circleMain){
		//var json= $.parseJSON(circleMain);
		//console.log(json);
		//initBloomingForJsonAndAdMove(json,"id_current_userId");
		
	}

});


//为文章首页初始化分页对象
//initPageForProduct();
</script>

</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_home_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_home_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>美文分享-群侠户外</title>
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<@M_articleCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree/>
    <@shiro.user>
 	<p> 欢迎户外大神 <span class="text-success" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">≡ <@shiro.principal property="nickname"/> ≡</span> <span class="badge" style="background-color:#449955"><span class="glyphicon glyphicon-grain"></span></span> <@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/></p>
	
		<@M_headRoller dto=headDto/>
		
		<@M_explorerWord2Img dto=explorerDto/>
		<@M_rememberScreen dto=rememberDto/>
		<@M_farmstayRollerMulti dto=farmstayDto/>
		<@M_outdoorRollerMulti dto=outdoorDto/>
		<@M_equipRealImg2Word  dto=equipRealDto/>
		<@M_actionFlagImg2Img  dto=actionFlagDto/>
		
		<#--
		我的邀请二维码 
	 	-->
	 	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	 	<#-- 
	 	<div id="inviteQrCodeWrapper" class="container text-center hidden" 
		data-title="我的邀请码"
		data-src="${UserUtils.getUser().getQrCode()}" data-url="/auth/userqr/viewInvite/ansy"
		>
			<div id="inviteQrCodeContent">		
			<img src="${UserUtils.getUser().getQrCode()}" title="我的邀请码" alt="邀请码"/>
			</div>
			<p class="text-center">
			我的邀请码
			</p>
		</div>
		 -->
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	
	<@M_dramala_wechat/>
	<@M_navBottomForCategory category="qunxia" subCategory="article"  entity=null/>
	<input type="hidden" value="1" name="userId" id="id_current_userId"/>
	<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	 data-url="/sz0099/ood/article/findPageForInvitor"
	 />
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	var login_status=$("#id_login_status").val();
	autoCurrentOauthPageUrlLogin('${login_status}');
	var currentUserId=$("#id_current_userId").val();
	if(login_status=='true'){
		initBloomingForJsonWhenLoaded('/sz0099/ood/article/findPageForInvitor','10','id_current_userId') ;
	}
	
	var circleMain='${circleMain}';
	if(circleMain){
		var json= $.parseJSON(circleMain);
		//initBloomingForJsonAndAdMove(json,"id_current_userId");
		
	}
});
//为文章首页初始化分页对象
//initPageForProduct();
</script>
</div>
</body>
</html>
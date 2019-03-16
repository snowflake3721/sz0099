<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<html>
<head>

<title>群侠户外</title>
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<@M_articleCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree/>
    <@shiro.user>
 	<p> 欢迎户外大神 <span class="text-success" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">≡ <@shiro.principal property="nickname"/> ≡</span> <span class="badge"><span class="glyphicon glyphicon-grain"></span></span> <@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/></p>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	
	 
	 <@M_layout_imgWordRollerH_single positon=position entityPage=page idPre="layout_" showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showLoadMore=false/>
	 <#--
	 <@M_layout_imgCoverRollerWordAbreast_list positon=position entityPage=page idPre="ly_list"/>
	 
	 <@M_layout_imgCoverRollerAbreast_list positon=position entityPage=page idPre="ly_imgList" showHeadTip=false url="#" showListLoadMore=true withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=true linkable=true/>
	 
	 <@M_layout_imgBannerRollerAbreast_multi positon=position entityPage=page idPre="ly_imgList" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=true linkable=true />
	 
	 <#assign contentList=page.content/>
	 <#if contentList!>
	 <@M_layout_jumbotron_single entity0=contentList?first idPre="ly_jb_" withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true/>
	 </#if>
	 <@M_layout_wordList positon=position entityPage=page idPre="ly_wd_list" showHeadTip=false url="#" showListLoadMore=true withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
	 
	 <@M_layout_imgAdList positon=position entityPage=page idPre="ly_img_ad_list" showHeadTip=false url="#" showListLoadMore=true withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
	  -->
	 <#-- 
	<@M_coeArticlePage itemPage=page moreLoaded="false" url="/sz0099/ood/article/queryList" condition=coeArticleBo/>
	 -->
	<@M_dramala_wechat/>
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="qunxia" subCategory="article"  entity=null/>
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
	var circleMain='${circleMain}';
	if(circleMain){
		var json= $.parseJSON(circleMain);
		//console.log(json);
		initBloomingForJsonAndAdMove(json,"id_current_userId");
		
	}
});
//为文章首页初始化分页对象
//initPageForProduct();
</script>
</div>
</body>
</html>
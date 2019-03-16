<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>

<title>技能详情-搜索-群侠</title>

</head>

<body>

<div class="container " id="body_content">
	<div id="id_profession_personal" class="container" style="z-index:5002"></div>
  	<@Mg_professionCategoryForSearchFromDetail entityPage=page categoryRef=entity categoryTree=categoryTree url="/sz0099/ood/personal/profession/searchForCategoryFromDetail"/>

    <@shiro.user>
 	<p class="text-right"> 
 	<#-- 
 	<span class="text-info" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">
 	☯☯
 	  <@shiro.principal property="nickname"/> 
 	  </span>
 	<span class="badge">技</span>
 	<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
 	 -->
 	<span>
 	<a href="/sz0099/ood/home/profession/index/recommend">技能圈</a>&nbsp;&nbsp;
 	</span>
 	</p>
	</@shiro.user>
	<#-- 
	<@shiro.guest>
    <p>&nbsp;&nbsp; 游客访问 <a href="/user/loginUI?st=general">登录</a></p>
	</@shiro.guest>
	 -->
	<hr/>
	
	<@M_categProfessionPage itemPage=page moreLoaded="false" url="/sz0099/ood/personal/profession/searchForCategoryFromDetail" condition=entity/>
	<@Mgp_introduction/>
	<#-- <#include "mobile/template/front/general/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="search" subCategory="profession"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<#--
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
-->
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	var circleMain='${circleMain}';
	if(circleMain){
		var json= $.parseJSON(circleMain);
		console.log(json);
		initBloomingForJsonAndAdMove(json,"id_current_userId");
	}
});
//为文章首页初始化分页对象
initForSearchForCategoryFromDetail('${categoryRef.mainId}');
</script>

</div>
</body>
</html>
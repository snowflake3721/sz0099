<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<html>
<head>

<title>群侠户外-技能搜索</title>
</head>

<body>

<div class="container " id="body_content">
	<div id="id_profession_personal" class="container" style="z-index:5002"></div>
  	<@M_professionCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree/>

    <@shiro.user>
 	<p>
 	侠 <span class="text-info" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">
 	 ∴
 	  <@shiro.principal property="nickname"/> 
 	  ∵
 	  </span>
 	<span class="badge">技</span>
 	<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
 	
 	<span class="pull-right">
 	<a href="/sz0099/ood/home/profession/index">技能圈</a>&nbsp;&nbsp; <a href="/sz0099/ood/home/article/index">户外圈</a>&nbsp;&nbsp;
 	</span>
 	</p>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	<@M_categProfessionPage itemPage=page moreLoaded="false" url="/sz0099/ood/personal/profession/searchForCategory" condition=entity/>
	<@M_dramala_wechat/>
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="search" subCategory="profession"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
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
initPageForSearchForCategory();
</script>

</div>
</body>
</html>
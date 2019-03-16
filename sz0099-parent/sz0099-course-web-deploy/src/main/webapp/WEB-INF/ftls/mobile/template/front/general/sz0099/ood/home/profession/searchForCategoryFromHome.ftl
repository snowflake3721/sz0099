<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
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
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>

<title>群侠户外-首页-技能圈搜索</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
	<div id="id_profession_personal" class="container" style="z-index:5002"></div>
    <@shiro.user>
  	<@M_professionSearchFromHomeDto dto=commonDto url="/sz0099/ood/home/profession/searchForCategoryFromHome" category=categoryTree idPre="ly_search"/>
    <#-- 
 	 -->
 	<p> 
 	侠 <span class="text-info" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">
 	☯
 	  <@shiro.principal property="nickname"/> 
 	  </span>
 	<#-- 
 	<span class="badge">文</span>
 	：<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
 	 -->
 	<span class="pull-right">
 	<#-- <a href="javascript:void(0)" onclick="window.history.go(-1)">首页</a> |  -->
 	返回：<a href="/sz0099/ood/home/profession/index/recommend">技能圈</a>&nbsp;&nbsp;
 	</span>
 	</p>
 	<hr/>
 	<@M_searchImg2Word dto=commonDto idPre="ly_search"/>
	</@shiro.user>
	<#-- 
	<@shiro.guest>
    <p>&nbsp;&nbsp; 游客访问 <a href="/user/loginUI?st=general">登录</a></p>
    <hr/>
	</@shiro.guest>
	 -->
	
	
	<@Mgp_introduction/>
	<@M_navBottomForCategory category="search" subCategory="article"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<#--
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/profession/coeArticle_index.js?v=1.0.1'><\/script>");</script>
-->
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
});
//为文章搜索初始化分页对象
<#assign position=commonDto.position />
<#assign baseId=position.baseId/>
initPageForSearchHiddenFromHome('${baseId}','ly_search');
</script>

</div>
</body>
</html>
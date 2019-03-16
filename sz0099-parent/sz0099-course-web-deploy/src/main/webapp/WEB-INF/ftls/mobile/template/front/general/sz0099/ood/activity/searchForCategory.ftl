<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>

<title>群侠户外-活动搜索</title>
</head>

<body>

<div class="container " id="body_content">
  	<@Mg_activityCategoryForSearch entityPage=page categoryRef=entity categoryTree=categoryTree/>
	<#--
    <@shiro.user>
 	<p> 大神 <span class="text-primary" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">§ <@shiro.principal property="nickname"/> § </span> <span class="badge"><span class="glyphicon glyphicon-grain"></span></span> <@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/></span>  <a href="/sz0099/ood/home/activity/index/recommend?st=general" class="pull-right">回首页&nbsp;&nbsp;</a></p>
	</@shiro.user>
	 
	<@shiro.guest>
    <p>&nbsp;&nbsp; 游客访问 <a href="/user/loginUI?st=general">登录</a></p>
	</@shiro.guest>
	 -->
	<hr/>
	
	<@M_coeCategActivityPage itemPage=page moreLoaded="false" url="/sz0099/ood/activity/searchForCategory" condition=entity/>
	<@M_introduction/>
	<#-- <#include "mobile/template/front/general/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="search" subCategory="activity"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/coeActivity_index.js?v=1.0.1'><\/script>");</script>
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
//为活动首页初始化分页对象
initPageForSearchForCategory();
</script>

</div>
</body>
</html>
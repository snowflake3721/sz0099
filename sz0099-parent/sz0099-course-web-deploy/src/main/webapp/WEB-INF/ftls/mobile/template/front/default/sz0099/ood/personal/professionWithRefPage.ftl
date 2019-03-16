<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<html>
<head>

<title>群侠户外-用户技能列表</title>
</head>

<body>

<div class="container " id="body_content">
	<@M_navBreadForCategory category="qunxia" subCategory="profession"  entity=null/>
	<a href="/sz0099/ood/home/profession/index">返回技能</a>
	<hr/>
	<@M_professionWithRefPage entityPage=entityPage/>
	<@M_dramala_wechat/>
	<@M_navBottomForCategory category="qunxia" subCategory="profession"  entity=null/>
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
});
//为文章首页初始化分页对象
//initPageForSearchForCategory();
</script>

</div>
</body>
</html>
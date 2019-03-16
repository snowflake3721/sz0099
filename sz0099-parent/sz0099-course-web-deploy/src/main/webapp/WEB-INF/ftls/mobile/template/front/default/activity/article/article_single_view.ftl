<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_tab_nav.ftl">
<#include "mobile/template/front/default/macro/macro_tab_content_index.ftl">
<#include "mobile/template/front/default/macro/macro_article_view_single.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">

<html>
<head>

<title>${article.title}-群侠户外-卓玛拉山联盟</title>
</head>

<body>

<div class="container" id="top_menu">
	<#include "mobile/template/front/default/home/top_menu_nav.ftl">
</div>

<div class="container" id="top_login">
	<#include "/mobile/template/front/default/home/top_login.ftl">
</div>

<div class="container" id="body_content">
	
	<#include "/mobile/template/front/default/activity/article/article_single_view_detail.ftl">
</div>

<div class="container" id="body_footer">

</div>

<div class="container" id="body_footer_js">
	<#include "/mobile/template/front/default/activity/article/body_footer_js_article_single_view.ftl">
</div>
</body>
</html>
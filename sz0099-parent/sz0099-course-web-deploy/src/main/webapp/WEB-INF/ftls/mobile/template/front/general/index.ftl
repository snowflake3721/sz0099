<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_tab_nav.ftl">
<#include "mobile/template/front/default/macro/macro_tab_content_index.ftl">

<html>
<head>

<title>群侠户外-卓玛拉山联盟</title>
</head>

<body>

<div class="container" id="top_menu">
	<#include "mobile/template/front/default/home/top_menu_index.ftl">
</div>

<div class="container" id="top_login">
	<#include "/mobile/template/front/default/home/top_login.ftl">
</div>

<div class="container" id="body_content">
	
	<#include "/mobile/template/front/default/home/body_content_article_bonus.ftl">
</div>

<div class="container" id="body_footer">

</div>

<div class="container" id="body_footer_js">
	<#include "/mobile/template/front/default/home/body_footer_js_home.ftl">
</div>
</body>
</html>
<!doctype html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
<title><sitemesh:write property="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/assets/common/tools/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<sitemesh:write property="head"/>
</head>
<body>
<div class="container">
	<sitemesh:write property="div.body_header"/>
</div>
<div class="container">
	<sitemesh:write property="div.body_content"/>	
</div>
<div class="container">
	<sitemesh:write property="div.body_footer"/>	
</div>
<#include "/pc/template/common/default/footer.ftl">
<script src="/assets/common/tools/jquery/3.2.1/jquery.slim.min.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
 <script src="/assets/common/tools/vue/2.5.6/vue.js"></script>
 <script src="/assets/common/tools/vue/2.5.6/vuex.js"></script>
 <sitemesh:write property="div.body_footer_js"/>
</body>
</html>
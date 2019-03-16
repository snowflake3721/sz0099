<!doctype html>
<html lang="zh-CN">
<head>
<meta content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
<title><sitemesh:write property="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="/assets/common/tools/jquery/3.2.1/jquery-3.2.1.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/jquery/3.2.1/jquery.goup.min.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/assets/common/tools/bootstrap/3.3.7/css/bootstrap.css?v=1.15" crossorigin="anonymous">
<link rel="stylesheet" href="/assets/common/tools/bootstrap/3.3.7/css/bootstrap-theme.css" crossorigin="anonymous">
<link href="/assets/common/tools/bootstrap-fileinput/4.4.8-7/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

<sitemesh:write property="head"/>
</head>
<body class="bg-info">
<#assign browserPrefix=ThreadLocalHolder.getBrowserPrefix() />
<div class="container" id="appEui">
	<sitemesh:write property="div.body_header"/>
	<sitemesh:write property="div.body_content"/>	
	<sitemesh:write property="div.body_footer"/>	

<#include "/${browserPrefix}/template/common/default/footer.ftl">
</div>
<#-- <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->

<script src="/assets/common/tools/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
 <script src="/assets/common/tools/axios/0.18.0/axios.js?v=1.0"></script>
 <script src="/assets/common/tools/qs/6.5.2/qs.js?v=1.1"></script>
 <script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
 <script src="/assets/common/tools/vue/2.5.6/vuex.js"></script>
 

<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/piexif.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap/3.3.7/js/bootstrap.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/fileinput.min.js"></script> 
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/locales/zh.js"></script>  
 <script src="/assets/common/tools/wechat/1.2.0/jweixin-1.2.0.js"></script>
 <script src="/assets/common/tools/wechat/wechatPay.js"></script>
    <sitemesh:write property="div.body_footer_js"/>	
    
    
</body>
</html>
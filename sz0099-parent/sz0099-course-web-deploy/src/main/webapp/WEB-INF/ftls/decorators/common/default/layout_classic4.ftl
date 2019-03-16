<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
<link rel="icon" href="/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
<title><sitemesh:write property="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="/assets/common/tools/jquery/3.2.1/jquery-3.2.1.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/jquery/3.2.1/jquery.goup.min.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/jit4j/jit4jCollapse.js?v=1.0.2"></script>
<script src="/assets/common/tools/jit4j/jit4jCarousel.js?v=1.0.3"></script>
<script src='/assets/common/tools/jit4j/jit4jCssClazz.js?v=1.0.1'></script>
<link rel="stylesheet" href="/assets/common/tools/bootstrap/3.3.7/css/bootstrap.css?v=1.0.13" crossorigin="anonymous">
<link rel="stylesheet" href="/assets/common/tools/bootstrap/3.3.7/css/bootstrap-theme.css" crossorigin="anonymous">
<link rel="stylesheet" href="/assets/common/tools/bootstrapvalidator/css/bootstrapValidator.css?v=1.20" crossorigin="anonymous">
<link href="/assets/common/tools/bootstrap-fileinput/4.4.8-7/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/bootstrap3-dialog/css/bootstrap-dialog.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/bootstrap-datepicker/css/bootstrap-datepicker3.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/blooming-menu/css/blooming-menu.css" media="all" rel="stylesheet" type="text/css" />

<#--
<link href="/assets/common/tools/jquery-ui/1.12.1/jquery-ui.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/jquery-ui/1.12.1/jquery-ui.theme.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/jquery-ui/1.12.1/jquery-ui.structure.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/jquery-weui/css/jquery-weui.css" media="all" rel="stylesheet" type="text/css" />
 <link href="/assets/common/tools/jquery-weui/css/mui.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/jquery-weui/css/weui.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/wangEditor/wangEditor.css" media="all" rel="stylesheet" type="text/css" />
 -->
<link href="/assets/common/tools/layer/theme/default/layer.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/wangEditor/3.1.1/wangEditor.css?v=1.0.5" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/swiper/4.4.2/css/swiper.css" media="all" rel="stylesheet" type="text/css" />
<link href="/assets/common/tools/swiper/4.4.2/css/jit4j-swiper.css" media="all" rel="stylesheet" type="text/css" />
<sitemesh:write property="head"/>
</head>
<body class="bg-info">
<#assign browserPrefix=ThreadLocalHolder.getBrowserPrefix() />
<#assign modelStyle=ThreadLocalHolder.getModelStyle() />
<input type="hidden" id="loginStatus" name="loginStatus" value="${Jit4jConfigShiro.LOGIN_STATUS}"/> 
<div class="container" id="app">
	<sitemesh:write property="div.body_header"/>
	<sitemesh:write property="div.body_content"/>	
	<sitemesh:write property="div.body_footer"/>	
<#-- 
<#include "/${browserPrefix}/template/common/default/footer.ftl">
 -->
</div>
 <script src="/assets/common/tools/axios/0.18.0/polyfill.js?v=1.0.2"></script>
 <script src="/assets/common/tools/axios/0.18.0/axios.js?v=1.0.3"></script>
 <script src="/assets/common/tools/qs/6.5.2/qs.js?v=1.1"></script>
<script src='/assets/common/tools/layer/layer.js?v=1.0.0'></script>

<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/piexif.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="/assets/common/tools/bootstrap/3.3.7/js/bootstrap.js?v=1.0.1" crossorigin="anonymous"></script>
<script src="/assets/common/tools/bootstrapvalidator/js/bootstrapValidator.js" crossorigin="anonymous"></script>

<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/fileinput.min.js"></script> 
<script src="/assets/common/tools/bootstrap-fileinput/4.4.8-7/js/locales/zh.js"></script>  

<script src="/assets/common/tools/bootstrap-paginator/bootstrap-paginator.js"></script>  
<script src="/assets/common/tools/bootstrap3-dialog/js/bootstrap-dialog.js?v=1.0.0"></script>  
<script src="/assets/common/tools/bootstrap-datepicker/js/bootstrap-datepicker.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/blooming-menu/blooming-menu.js"></script>  
<script src="/assets/common/tools/jit4j/jit4jBlooming.js?v=1.0.6"></script>  

<script src="/assets/common/tools/jquery-ui/1.12.1/jquery-ui.js"></script> 
<script src="/assets/common/tools/jquery-ui/1.12.1/jquery.ui.touch-punch.js"></script> 
<script src="/assets/common/tools/wechat/1.4.0/jweixin-1.4.0.js"></script>
<script src="/assets/common/tools/jit4j/jit4jDate.js?v=1.0.1"></script>
<script src="/assets/common/tools/jit4j/jit4jCommon.js?v=1.1.23"></script>
<script src="/assets/common/tools/jit4j/jit4jDialog.js?v=1.2.23"></script>
<script src="/assets/common/tools/jit4j/jit4jPhoto.js?v=1.0.9"></script>
<script src="/assets/common/tools/jit4j/jit4jBtn.js?v=1.0.2"></script>
<script src='/assets/common/tools/jit4j/jit4jSelect.js?v=1.0.1'></script>
<script src='/assets/theme/${modelStyle}/sz0099/ood/user/infocommon.js?v=1.0.4'></script>

<script src='/assets/common/tools/swiper/4.4.2/js/swiper.js?v=1.0.0'></script>
<script src='/assets/common/tools/jit4j/jit4jSwiper.js?v=1.0.2'></script>

<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jWechat.js?v=1.0.27'><\/script>")</script>
<#-- <script src="/assets/common/tools/wechat/wechatPay.js"></script> -->
<sitemesh:write property="div.body_footer_js"/>	
</body>
</html>
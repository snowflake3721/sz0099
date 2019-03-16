<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_article.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<html>
<head>

<title>群侠户外</title>
<link href="/assets/common/tools/blooming-menu3/css/circle.css?v=1.0.1" media="all" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="container " id="body_content">
	<div class="jumbotron">
	  <h3 class="text-center">
	  <@shiro.user>
 	 欢迎大神 <strong><span class="text-success bg-info" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">≡ <@shiro.principal property="nickname"/> ≡</strong></span>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	  </h3>
	  
	</div>
	
	<@M_introduction/>
	

<!--图片上传-->
<div class="weui-gallery" id="gallery">
	<span class="weui-gallery__img" id="galleryImg"></span>
	<div class="weui-gallery__opr">
		<a href="javascript:" class="weui-gallery__del">
			<i class="weui-icon-delete weui-icon_gallery-delete"></i>
		</a>
	</div>
</div>
<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
		<div class="weui-cell__bd">
			<div class="weui-uploader">
				<div class="weui-uploader__hd">
					<p class="weui-uploader__title">图片上传</p>
				</div>
				<div class="weui-uploader__bd">
					<ul class="weui-uploader__files" id="uploaderFiles">
						
					</ul>
					<div class="weui-uploader__input-box">
						<input id="uploaderInput" class="weui-uploader__input zjxfjs_file" type="file" accept="image/*" multiple="">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	
	
	
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/blooming-menu3/js/circle.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/fetchByPosition.js?v=1.0.2'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/user/infocommon.js?v=1.0.4'><\/script>");</script>
 -->
<script type="text/javascript">
	$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	//initSwiper();
});
</script>

</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_detail_view.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_praise_list.ftl">
<#include "mobile/template/front/default/macro/macro_qrCode_view.ftl">

<html>
<head>
<title>${entity.title}</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForCategory category="qunxia" subCategory="article"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="publishStatus" id="id_hidden_publishStatus${entity.id}" value="${entity.publishStatus}" />
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <input type="hidden" name="name" id="id_name${entity.id}" value="${entity.name}"/>
  
  <@M_coeArticleDetailView entity=entity paragraphPage=page moreLoaded="true" />
</#if>

<@M_navBottomForCategory category="qunxia" subCategory="article"  entity=entity/>
<@shiro.user>
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<#assign description=getSubstring(entity.description,30,' -- 群侠户外')/>
<#assign imgUrl='/assets/common/tools/blooming-menu3/img/qunxia_6_2.jpg'/>
<#assign bannerImageList=entity.bannerList />
<#if bannerImageList!?size &gt; 0 >      
  	<#assign bannerImage=bannerImageList?first/>
  	<#if bannerImage! && bannerImage.expectedUrl!> 
  	<#assign imgUrl=bannerImage.expectedUrl/>
	</#if>
</#if>
<input type="hidden" value="${login_status}" name="login_status" id="id_login_status"
	data-sharedCode="${UserUtils.getUser().getSharedCode()}"
	data-title="${entity.title}-<@shiro.principal property="nickname"/>"
	data-imgUrl="${rc.getMobileDomain() + imgUrl}"
	data-desc="${description} 【from QX】"
/>
</@shiro.user>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/manage/coeArticle_manage.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/manage/coeArticle_create.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/praise.js?v=1.0.2'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeProduct_index.js?v=1.0.0'><\/script>");</script>
 -->
<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	var loginHidden=$("#id_login_status");
	if(loginHidden.length>0){
		var login_status=$("#id_login_status").val();
		if(login_status=='true'){
			var sharedCode=$("#id_login_status").attr("data-sharedCode");
			var title=$("#id_login_status").attr("data-title");
			var desc=$("#id_login_status").attr("data-desc");
			var imageUrl=$("#id_login_status").attr("data-imgUrl");
			getSharedUrl(sharedCode,title,desc,imageUrl);
		}
	}
});
</script>
</div>
</body>
</html>


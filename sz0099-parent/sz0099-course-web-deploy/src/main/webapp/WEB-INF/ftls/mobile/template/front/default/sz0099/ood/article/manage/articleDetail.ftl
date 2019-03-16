<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_detail_view.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">

<html>
<head>
<title>${entity.title}</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForManage category="article" subCategory="detail"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="publishStatus" id="id_hidden_publishStatus${entity.id}" value="${entity.publishStatus}" />
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <input type="hidden" name="name" id="id_name${entity.id}" value="${entity.name}"/>
  	<ul class="list-inline">
  		<li><a href="/sz0099/ood/article/manage/create?id=${entity.id}" class="btn btn-primary" role="button">编辑</a></li>
  		<#assign publishStatus=entity.publishStatus />
  		<li id="id_li_shevled_${entity.id}${CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt}" class="<#if publishStatus==CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt}')" class="btn btn-info" role="button">发布</a></li>
  		<li id="id_li_shevled_${entity.id}${CoeArticle.PUBLISH_STATUS_DRAFT.valueInt}" class="<#if publishStatus==CoeArticle.PUBLISH_STATUS_DRAFT.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${CoeArticle.PUBLISH_STATUS_DRAFT.valueInt}')" class="btn btn-warning" role="button">撤回</a></li>
		<li class="pull-right"><a href="javascript:void(0)" onclick="mergeForDeleted('${entity.id}')" class="btn btn-danger pull-right" role="button">删除</a></li>
	</ul>
    
  <span id="id_span_shelved${entity.id}" class="<#if publishStatus==CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt>text-success<#else>text-danger</#if>"><#if publishStatus==CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt>文章已发布<#else>文章还是草稿</#if></span>
  
  <@M_coeArticleDetail entity=entity paragraphPage=page moreLoaded="true" />
</#if>

  <@M_navBottomForManage category="article" subCategory="detail"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/manage/coeArticle_manage.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/manage/coeArticle_create.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeProduct_index.js?v=1.0.0'><\/script>");</script>
 -->
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</div>
</body>
</html>


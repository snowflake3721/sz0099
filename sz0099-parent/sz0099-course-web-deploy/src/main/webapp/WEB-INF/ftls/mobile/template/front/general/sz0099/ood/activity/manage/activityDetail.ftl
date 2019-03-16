<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">

<html>
<head>
<title>${entity.title}</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForCategory category="activity" subCategory="detail"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="publishStatus" id="id_hidden_publishStatus${entity.id}" value="${entity.publishStatus}" />
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <input type="hidden" name="name" id="id_name${entity.id}" value="${entity.name}"/>
  	<ul class="list-inline">
  		<li><a href="/sz0099/ood/activity/manage/queryDraftList" class="btn btn-primary btn-xs">返回列表</a></li>
  		<li><a href="/sz0099/ood/activity/manage/create?id=${entity.id}" class="btn btn-primary btn-xs" role="button">编辑</a></li>
  		<#assign publishStatus=entity.publishStatus />
  		<li id="id_li_shevled_${entity.id}${CoeActivity.PUBLISH_STATUS_PUBLISH.valueInt}" class="<#if publishStatus==CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${CoeActivity.PUBLISH_STATUS_PUBLISH.valueInt}')" class="btn btn-info btn-xs" role="button">发布</a></li>
  		<li id="id_li_shevled_${entity.id}${CoeActivity.PUBLISH_STATUS_DRAFT.valueInt}" class="<#if publishStatus==CoeActivity.PUBLISH_STATUS_DRAFT.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${CoeActivity.PUBLISH_STATUS_DRAFT.valueInt}')" class="btn btn-warning btn-xs" role="button">撤回</a></li>
		<@shiro.hasRole name="plat_creator">
		<li class="pull-right"><a href="javascript:void(0)" onclick="mergeForDeleted('${entity.id}')" class="btn btn-danger btn-xs pull-right" role="button">删除</a></li>
		</@shiro.hasRole>
	</ul>
    
  <span id="id_span_shelved${entity.id}" class="<#if publishStatus==CoeActivity.PUBLISH_STATUS_PUBLISH.valueInt>text-success<#else>text-danger</#if>"><#if publishStatus==CoeActivity.PUBLISH_STATUS_PUBLISH.valueInt>活动已发布<#else>活动还是草稿</#if></span>
  
  <@Mg_coeActivityDetail entity=entity paragraphPage=page moreLoaded="true" />
</#if>

  <@M_navBottomForCategory category="activity" subCategory="detail"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_manage.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_create.js?v=1.0.3'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/coeProduct_index.js?v=1.0.0'><\/script>");</script>
 -->
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</div>
</body>
</html>


<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
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
<@M_navBreadForCategory category="profession" subCategory="detailList"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="publishStatus" id="id_hidden_publishStatus${entity.id}" value="${entity.publishStatus}" />
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <input type="hidden" name="name" id="id_name${entity.id}" value="${entity.name}"/>
  	<ul class="list-inline">
  		<li><a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/personal/profession/manage/queryProfessionList">返回列表</a></li>
  		<li><a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}" class="btn btn-primary btn-xs" role="button">编辑</a></li>
  		<#assign publishStatus=entity.publishStatus />
  		<li id="id_li_shevled_${entity.id}${Profession.PUBLISH_STATUS_PUBLISH.valueInt}" class="<#if publishStatus==Profession.PUBLISH_STATUS_PUBLISH.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${Profession.PUBLISH_STATUS_PUBLISH.valueInt}')" class="btn btn-info btn-xs" role="button">发布</a></li>
  		<li id="id_li_shevled_${entity.id}${Profession.PUBLISH_STATUS_DRAFT.valueInt}" class="<#if publishStatus==Profession.PUBLISH_STATUS_DRAFT.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="publilsh('${entity.id}', '${Profession.PUBLISH_STATUS_DRAFT.valueInt}')" class="btn btn-warning btn-xs" role="button">撤回</a></li>
		
		<li>
				<input type="hidden" id="id_mainType_current${entity.id}" value="${Profession.MAINTYPE_9_MAIN.valueInt}" 
					  data-url="/sz0099/ood/personal/profession/manage/merge/mainType" 
					  data-mainType=""
					  data-id="${entity.id}"
					  data-btnMainId="id_btn_btnMainId${entity.id}"
					  data-btnMainUnId="id_btn_un_btnMainId${entity.id}"
					  >
				    <button type="button" id="id_btn_un_btnMainId${entity.id}" class="btn btn-primary btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt!=entity.mainType>hidden</#if>"><span class="glyphicon glyphicon-star" aria-hidden="true" style="font-size: 16px;"></span>
				    	已是主技能
				    </button>
				    <button type="button" id="id_btn_btnMainId${entity.id}" class="btn btn-danger btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt==entity.mainType>hidden</#if>" onclick="mergeForMainType('${entity.id}','${Profession.MAINTYPE_9_MAIN.valueInt}', 'id_mainType_current${entity.id}')">设为主技能</button>

		</li>
		<@shiro.hasRole name="plat_creator">
		<li class="pull-right"><a href="javascript:void(0)" onclick="mergeForDeleted('${entity.id}')" class="btn btn-danger btn-xs pull-right" role="button">删除</a></li>
		</@shiro.hasRole>
	
	</ul>
    
  <span id="id_span_shelved${entity.id}" class="<#if publishStatus==Profession.PUBLISH_STATUS_PUBLISH.valueInt>text-success<#else>text-danger</#if>"><#if publishStatus==Profession.PUBLISH_STATUS_PUBLISH.valueInt>技能已发布<#else>技能还在修炼中，抓紧炼好出山啊！</#if></span>
  
  <@Mg_professionDetail entity=entity paragraphPage=page moreLoaded="true" />
</#if>
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="profession"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_manage.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_create.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/article/coeProduct_index.js?v=1.0.0'><\/script>");</script>
 -->
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</div>
</body>
</html>


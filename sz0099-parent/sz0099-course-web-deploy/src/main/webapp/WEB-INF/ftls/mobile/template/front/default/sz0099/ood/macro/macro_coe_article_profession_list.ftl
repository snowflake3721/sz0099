<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#-- 选择文章 -->
<#macro M_selectArticleProfessionPage page positionRef=null moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<#if page??>
	  <#assign itemList=page.content />
        <#assign viewType=PositionRef.VIEWTYPE_1_ARTICLE.valueInt />
		<#assign entityId=positionRef.baseId />
      <#list itemList as item>
        <#assign entity=item.mainEntity />
     <div class="list-group">
     <div class="btn-group btn-group-xs btn-group-justified" role="group" aria-label="id_art${item.id}">
	      <a id="id_art${positionRef.baseId}_${entity.id}" class="btn btn-default btn-xs"  role="button" data-toggle="collapse" href="#id_art_content${positionRef.baseId}_${entity.id}" aria-expanded="true" aria-controls="id_art_content${positionRef.baseId}_${entity.id}">
						 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
						  ${entity.articleNo} -- <span id="id_p_title${positionRef.baseId}_${entity.id}">${getSubstring(entity.title,10)}</span> <span class="pull-right"> <strong>#${item?index}</strong></span>
		  	
		  </a>
	  </div>
      <div id="id_art_content${positionRef.baseId}_${entity.id}" class="panel-collapse collapse">
		  <div class="panel panel-default">
				  <div class="panel-body">
				    <p><strong>名称:</strong> ${entity.name}</p>
				    <p><strong>笔名:</strong> ${entity.penname}</p>
					 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
				    <p><strong>引导语:</strong> ${currentPreIntro.label}</p>
				    <p><strong>标题:</strong> ${entity.title}</p>
				    <p><strong>子标题:</strong> ${entity.title}</p>
					 <p>
				     	<strong>发布状态:</strong> 
				      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
				      	${publishLabel}
				      </p>
				     
				  	  <p>
				  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
				  	  </p>
				  	  <p>
				  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
				  	  </p>
				  	  <p><strong>简介:</strong> ${getSubstring(entity.description,60,'暂无')}</p>
				  	  <p><strong>封面图:</strong><@M_showImageForSelect imageList=entity.coverList width="40px" /> </p>
				  	  <p><strong>头部图:</strong><@M_showImageForSelect imageList=entity.bannerList width="40px" /> </p>
				  </div>
				  <div class="panel-footer">
					  <p class="text-center">
					  <a id="id_btn_bind_${positionRef.baseId}_${entity.id}" href="javascript:void(0)" onclick="addPositionRef('${viewType}','${positionRef.baseId}','${positionRef.positionId}','${entity.id}','id_btn_bind_${positionRef.baseId}_${entity.id}','id_btn_bind_un_${positionRef.baseId}_${entity.id}')" class="btn btn-info btn-xs <#if item.aid!=null>hidden</#if>" data-url="/sz0099/ood/personal/profession/ref/manage/add">添加关联</a>
					  
					  <a id="id_btn_bind_un_${positionRef.baseId}_${entity.id}" href="javascript:void(0)" onclick="deletePositionRef('${item.id}','${viewType}','${positionRef.baseId}','${positionRef.positionId}','${entity.id}','id_btn_bind_${positionRef.baseId}_${entity.id}','id_btn_bind_un_${positionRef.baseId}_${entity.id}')" data-wrapperId="id_li_positionRef_${item.id}" data-url="/sz0099/ood/personal/profession/ref/manage/delete" class="btn btn-danger btn-xs <#if item.aid==null>hidden</#if>">移除关联</a>
					  
					  </p>
				  </div>
			  </div>
		</div><!--end collapse-->
  	  </#list>
  	  <div id="id_art_content_loadMore">
  	  </div>
  	  <!-- 下一页 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  		<#if !page.first>
			  	  	  <a href="javascript:void(0)" onclick="searchForRefSelect(${page.number-1},'id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-info btn-xs">上一页</a>
			  	  	  	</#if>
			  	  	  	<#if !page.last>
			  	  	  	<a href="javascript:void(0)" onclick="searchForRefSelect(${page.number+1},'id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-warning btn-xs">下一页</a>
				  		</#if>
				  </p>
			</div>
	  </#if>
	  
 </#if>
 </#macro>
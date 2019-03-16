<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_position_list.ftl">

<#macro M_articleSearch page=null entity=null searchUrl="/sz0099/ood/position/manage/selectNeedBindRefList">
<#assign viewType=PositionRef.VIEWTYPE_1_ARTICLE.valueInt />
<#assign entityId=entity.baseId />
<div class="input-group">
<input type="hidden" id="id_search_url${viewType}${entityId}" name="searchUrl" class="form-control" value=${searchUrl}>
<input type="hidden" id="id_page_currentPage${viewType}${entityId}" name="page" class="form-control" value="${page.number}">
<input type="hidden" id="id_page_totalPages${viewType}${entityId}" name="totalPages" class="form-control" value="${page.totalPages}">
<input type="hidden" id="id_page_size${viewType}${entityId}" name="size" class="form-control" value="${page.size}">
<input type="text" id="id_search_title${viewType}${entityId}" name="title" class="form-control" placeholder="标题，快速定位" value="${entity.title}">
<span class="input-group-btn">
    <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchForRefSelect(0,'id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')">搜索</button>
</span>
<input type="text" id="id_search_mainNo${viewType}${entityId}" name="articleNo" class="form-control" placeholder="编号，精确定位" value="${entity.articleNo}">
</div>
</#macro>

<#macro M_articleSearchForSelect page=null entity=null searchUrl="/sz0099/ood/position/manage/selectNeedBindRefList">
<@M_articleSearch page=page entity=entity searchUrl=searchUrl />
<@M_selectArticlePositionPage page=page positionRef=entity moreLoaded="true" url=searchUrl condition=entity />
</#macro>
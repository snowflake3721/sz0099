<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">

<#macro M_professionRefPageEdit page=null entity=null deep=0 wrapperId="" tipId="" editWrapperId="">
<#assign entityId=entity.baseId />
<input type="hidden" id="id_page_currentPage_bindedRef${entityId}" name="page" class="form-control" value="${page.number}">
<input type="hidden" id="id_page_totalPages_bindedRef${entityId}" name="totalPages" class="form-control" value="${page.totalPages}">
<input type="hidden" id="id_page_size_bindedRef${entityId}" name="size" class="form-control" value="${page.size}">


<#if page!>
<#assign contentList=page.content />
<#if deep==0>

<ul class="list-group" id="id_ul_positionRef_${entity.id}">
</#if>
	<#if contentList! && contentList?size gt 0>
	<#if deep gt 0>
	<ul class="list-group" id="id_ul_positionRef_${entity.id}">
	</#if>
		<#list contentList as child>
			<#assign clazz=getClazzByDepth(deep)/>
			<li class="list-group-item list-group-item-${clazz}" id="id_li_positionRef_${child.id}">
			<p>
			<span class="text-danger pull-left">
				<span class="glyphicon glyphicon-trash" aria-hidden="true" 
				id="id_btn_bind_un_${child.baseId}_${child.mainId}_span"
				onclick="deletePositionRef('${child.id}','${child.viewType}','${child.baseId}','${child.positionId}','${child.mainId}','id_btn_bind_${child.baseId}_${child.mainId}','id_btn_bind_un_${child.baseId}_${child.mainId}_span')" data-wrapperId="id_li_positionRef_${child.id}" data-url="/sz0099/ood/personal/profession/ref/manage/delete"
				>&nbsp;</span>
			</span>
			<span class="">${child.nickname} - 
			<input type="text" size="5" class="input-sm" value="${child.topLevel}" id="id_positionRef_topLevel${child.id}"
			onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
    		onblur="saveSimpleSingle('${child.id}','/sz0099/ood/personal/profession/ref/manage/saveSimpleSingle')"
    		placeholder="置顶序号"
			/>
			</span> 
			
			<span class="text-info pull-right">
				
				<span class="glyphicon glyphicon-eye-open <#if PositionRef.STATUS_1_OPEN.valueInt!=child.status>hidden</#if>" aria-hidden="true" 
				id="id_open_${child.id}_span"
				onclick="openPositionRef('${child.id}','${PositionRef.STATUS_2_CLOSED.valueInt}','${child.viewType}','${child.baseId}','${child.positionId}','${child.mainId}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/sz0099/ood/personal/profession/ref/manage/open"
				>&nbsp;</span>
				<span class="glyphicon glyphicon-eye-close <#if PositionRef.STATUS_1_OPEN.valueInt==child.status>hidden</#if>" aria-hidden="true" 
				id="id_closed_${child.id}_span"
				onclick="openPositionRef('${child.id}','${PositionRef.STATUS_1_OPEN.valueInt}','${child.viewType}','${child.baseId}','${child.positionId}','${child.mainId}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/sz0099/ood/personal/profession/ref/manage/open"
				>&nbsp;</span>
			
				<span class="glyphicon glyphicon-edit" aria-hidden="true"
				id="id_edit_ref_${child.id}"
				onclick="editPositionRef('${child.id}', 'id_edit_ref_${child.id}')"
				data-saveUrl="/sz0099/ood/personal/profession/ref/manage/mergeProfessionRef"
				data-queryUrl="/sz0099/ood/personal/profession/ref/manage/findByIdForEdit"
				></span>
			</span>
			
			<input type="hidden" id="id_positionRef_orderSeq${child.id}" name="orderSeq" value="${child.orderSeq}" />
			<input type="hidden" id="id_positionRef_name${child.id}" name="name" value="${child.name}" />
			<input type="hidden" id="id_positionRef_baseId${child.id}" name="baseId" value="${child.baseId}" />
			</p>
			<p id="id_p_title${child.id}">${getSubstring(child.title,10)}</p>
			</li>
			
		</#list>
	<#if deep gt 0>
	</ul>
	</#if>
	</#if>
<#if depth==0>
</ul>
</#if>

<div class="container">
  	  <p class="text-right">
  	  
  	  		<#if !page.first>
  	  	  	<a href="javascript:void(0)" onclick="searchForBindedRefPage(${page.number-1}, 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-info btn-xs">上一页</a>
  	  	  	</#if>
  	  	  	<#if !page.last>
  	  	  	<a href="javascript:void(0)" onclick="searchForBindedRefPage(${page.number+1}, 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-warning btn-xs">下一页</a>
	  		</#if>
	  </p>
</div>
</#if>
</#macro>


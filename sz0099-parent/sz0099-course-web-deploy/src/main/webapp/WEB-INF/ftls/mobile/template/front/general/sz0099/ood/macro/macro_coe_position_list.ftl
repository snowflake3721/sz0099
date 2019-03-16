<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">

<#macro M_positionExtendList contentList=null url="/sz0099/ood/position/extend/manage/create?id="> 
<div class="text-center">
<div id="id_tip"></div>
<ul class="list-group">
<#if contentList?? && contentList?size gt 0>
	<#list contentList as content>
		<li id="id_li_${content.id}" class="list-group-item <#if content?is_even_item>list-group-item-warning</#if>">	  
	  	<span class="pull-left"
	  		onclick="deleteVarietySingle('${content.id}', '/sz0099/ood/position/extend/manage/delete', 'id_li_${content.id}', 'id_tip')"
	  	>
	  	<strong><span class="glyphicon glyphicon-trash text-danger" aria-hidden="true" style="font-size: 16px;"></span>
	  	</strong>
	  	</span>
	  	<a type="button" href="${url}${content.id}" class="btn btn-xs">${content.project}-${content.module}-${content.variety} 
	  	&nbsp;&nbsp;<span class="pull-right"><strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
	  	</a>
	  	<input type="hidden" value="${content.id}" id="id_id${content.id}" name="id" />
	  	<input type="hidden" value="${content.variety}" id="id_variety${content.id}" name="variety"/>
	  	<input type="hidden" value="${content.module}" id="id_module${content.id}" name="module"/>
	  	<input type="hidden" value="${content.project}" id="id_project${content.id}" name="project"/>
	  	</li>
	</#list>
		<#if contentList?size lt 5>
	  	<li class="list-group-item">
	  	<a type="button" href="${url}" class="btn btn-info">新建项目品种</a>
	  	</li>
	  	</#if>
<#else>
<li class="list-group-item">
<a type="button" href="${url}" class="list-group-item btn btn-info">新建项目品种</a>
</li>
</#if>
</ul>
</div>
</#macro>





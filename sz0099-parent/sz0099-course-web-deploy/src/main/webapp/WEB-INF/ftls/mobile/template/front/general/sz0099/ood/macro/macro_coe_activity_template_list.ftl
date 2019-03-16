<#include "mobile/template/front/general/function/func_basic.ftl">
<#macro M_actTemplate entityId=null> 
<div id="id_tab_template">
	<p class="bg-warning">[载入]模板后将覆盖当前活动内容，请谨慎执行</p>
	<ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active" >
			    <a href="#template_user" id="id_a_template_user${entityId}" aria-controls="template_user" role="tab" data-toggle="tab" 
			    data-url="/sz0099/ood/activity/findTemplate/ansy"
			    onclick="findTemplateForCommon('id_a_template_user${entityId}')"
			    data-position="template_user"
			    data-template="10"
			    data-saved="0"
			    data-entityId="${entityId}"
			    >用户</a>
		    </li>
		    <li role="presentation">
			    <a href="#template_common" id="id_a_template_common${entityId}" aria-controls="template_common" role="tab" data-toggle="tab"
			    data-url="/sz0099/ood/activity/findTemplate/ansy"
			    onclick="findTemplateForCommon('id_a_template_common${entityId}')"
			    data-position="template_common"
			    data-template="20"
			    data-saved="0"
			    data-entityId="${entityId}"
			    >公众</a>
		    </li>
	</ul>
	<div class="tab-content">
	    <div role="tabpanel" class="tab-pane active fade in" id="template_user">
	    	
	    </div>
	    <div role="tabpanel" class="tab-pane fade in" id="template_common" >
	    	
	    </div>
	</div>
</div>
</#macro>

<#macro M_actTemplatePage position="" templatePage=null condition=null> 
<br/>
<#if templatePage!>
<#assign detailList=templatePage.content />
<div class="list-group text-center" id="id_data_list_activity${position}">
	<#if detailList?? && detailList?size gt 0>
		<@M_actTemplateList contentList=detailList condition=condition/>
	<#else>
		<#if templatePage.totalPages lt 1>
		<a type="button" href="javascript:void(0)" class="list-group-item btn btn-info">还没有设置模板</a>
		</#if>
	</#if>
</div>

<div class="container">
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/activity/findTemplate/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${templatePage.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${templatePage.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${templatePage.totalPages}"/>
  	<input id="id_data_template${position}" type="hidden" name="template" value="${condition.template}"/>
  	<input id="id_data_position${position}" type="hidden" name="position" value="${position}"/>
  	<input id="id_data_id${position}" type="hidden" name="id" value="${condition.id}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		$(function () {
		  	$('[data-toggle="tooltip"]').tooltip()
  			initPageAnsyForActTemplate('${position}');
		})
  	</script>
</div>
</#if>
</#macro>


<#macro M_actTemplateList contentList=null condition=null> 
<br/>
<ul class="list-group text-center">
<#if contentList?? && contentList?size gt 0>
	<#list contentList as content>
		<li class="list-group-item <#if content?is_even_item>list-group-item-warning</#if>">
			<a type="button" class="pull-left" href="javascript:void(0)" data-toggle="tooltip" data-placement="top" title="模板${content.activityNo}">
			<strong>
				<span class="glyphicon glyphicon-flag text-danger"
				 aria-hidden="true" style="font-size: 16px;"
				 ></span>
			 </strong>
			</a>
		  <span id="id_name${content.id}">${content.activityNo}-${content.title}</span> 
		  <span class="pull-right">
		  <strong><span class="glyphicon glyphicon-duplicate text-warning" aria-hidden="true" style="font-size: 16px;"></span></strong>
		  <a type="button" href="javascript:void(0)" 
		  class="btn btn-xs btn-warning" onclick="loadTemplate('id_act_template${content.id}')"
		  id="id_act_template${content.id}"
		  data-url="/sz0099/ood/activity/manage/loadTemplate"
		  data-saved="0"
		  data-templateId="${content.id}"
		  data-entityId="${condition.id}"
		  data-showTemplate="id_dialog_showTemplate${condition.id}"
		  data-showTip="id_act_template${content.id}_showTip"
		  >载入</a>
		  </span>
		  <div id="id_act_template${content.id}_showTip" class="hidden"></div>
	  </li>
	</#list>
<#else>
	<li class="list-group-item btn btn-info">
		<a type="button" href="javascript:void(0)" class="list-group-item btn btn-info">还没有设置模板</a>
	</li>
</#if>
</ul>
</#macro>
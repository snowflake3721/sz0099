<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_joinItem entity=null>
	<ul class="list-group" id="id_joinItem_wrapper${entity.mainId}">
		<@M_joinItemList entityList=entity.joinItemList />
   </ul>
</#macro>

<#macro M_joinItemList entityList=null>
<#if entityList! && entityList?size gt 0 >
	<#list entityList as entity0 >
		<@M_joinItemSingle entity0=entity0 />
	</#list>
</#if>
</#macro>
<#macro M_joinItemSingleWithoutLi entity0=null>
		<div class="row">
				<div class="col-xs-1">
			   	#${entity0.orderSeq}
			   </div>
				<div class="col-xs-3 text-right">
				集合时间
				</div>
    			<div class="col-xs-6">
			    	<div class="input-group input-group-sm">
			    	<input id="id_picker_joinItem_joinTime${entity0.id}" 
			    	name="joinTime" type="text" 
			    	class="form-control input-sm" 
			    	value="${DateUtils.formatToString(entity0.joinTime ,'yyyy-MM-dd HH:mm')}" 
			    	data-saveUrl="/sz0099/ood/activity/joinItem/manage/mergeJoinTime"
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-saved="0"
			    	placeholder="集合时间" 
			    	readonly="true"
			    	data-date-format="yyyy-mm-dd HH:ii">
				    </div>
				    <div class="hidden" id="id_picker_joinItem_joinTime${entity0.id}_showTip"></div>
			   </div>
			   <div class="col-xs-2 text-right">
			   <button type="button" class="close"><span class="glyphicon glyphicon-remove" onclick="deleteJoinItem('id_act_time_id','id_joinItem_li${entity0.id}','${entity0.id}')"></span></button>
			   </div>
			   
			   <div class="col-xs-4 text-right">
				集合地点
				</div>
			   <div class="col-xs-6">
			    	<div class="input-group input-group-sm">
			    	<input id="id_joinItem_place${entity0.id}" 
			    	name="place" type="text" 
			    	class="form-control input-sm" 
			    	value="${HtmlUtil.textarea2UnEscape(entity0.place)}" 
			    	data-saveUrl="/sz0099/ood/activity/joinItem/manage/mergePlace"
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-allowedLength="24"
			    	data-saved="0"
			    	onblur="commitPlace('id_joinItem_place${entity0.id}',  '${entity0.id}')"
			    	onkeyup="watchField('id_joinItem_place${entity0.id}',  '${entity0.id}')"
			    	placeholder="集合地点">
				    </div>
				    <span class="hidden" id="id_joinItem_place${entity0.id}_showTip"></span>
			   </div>
			   <div class="col-xs-2">
			   <span class="pull-right"><span id="id_joinItem_place${entity0.id}_length">${entity0.place?length}</span>/24</span>
			   </div>
			   <div class="col-xs-12 text-left">
				补充说明 <small><span id="id_joinItem_description${entity0.id}_length">${HtmlUtil.countTextLength(entity0.description)}</span>/300</small>
				</div>
			   <div class="col-xs-12">
			   	<textarea id="id_joinItem_description${entity0.id}" 
			   	placeholder="集合时补充说明" class="form-control input-sm" 
			   	name="description" 
			   	data-saveUrl="/sz0099/ood/activity/joinItem/manage/mergeDescription"
		    	data-id="${entity0.id}"
		    	data-baseId="${entity0.baseId}"
		    	data-mainId="${entity0.mainId}"
		    	data-allowedLength="300"
		    	data-saved="0"
		    	onblur="commitField('id_joinItem_description${entity0.id}', '${entity0.id}')"
		    	onkeyup="watchField('id_joinItem_description${entity0.id}', '${entity0.id}')"
			   	rows="3">${HtmlUtil.textarea2UnEscape(entity0.description)}</textarea>
			   	<div class="text-right">
			   	<span class="hidden" id="id_joinItem_description${entity0.id}_showTip"></span>
			   	</div>
			   </div>
		   </div>
</#macro>
<#macro M_joinItemSingle entity0=null>
<#if entity0!>
		<li class="list-group-item list-group-item-info" id="id_joinItem_li${entity0.id}" data-saved="0">
			<@M_joinItemSingleWithoutLi entity0=entity0 />
		</li>
</#if>
</#macro>

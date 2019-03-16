<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#-- 关联编辑面板 begin -->
<#macro M_professionRefEdit entity=null >
<div id="id_ref_tip${entity.id}"></div>
<form class="form-horizontal">
<input type="hidden" id="id_ref_id${entity.id}" name="id" value="${entity.id}" />
<input type="hidden" id="id_ref_viewType${entity.id}" name="viewType" value="${entity.viewType}" />
<input type="hidden" id="id_ref_extendId${entity.id}" name="extendId" value="${entity.extendId}" />
<input type="hidden" id="id_ref_positionId${entity.id}" name="positionId" value="${entity.positionId}" />
<input type="hidden" id="id_ref_baseId${entity.id}" name="baseId" value="${entity.baseId}" />
<@M_dropdownBarSame idPre="id_ref_" entityId=entity.id propertyContext=ProfessionRef.STATUS current=ProfessionRef.STATUS.getContext(entity.status,0) readonly=true />
<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_topLevel${entity.id}">序号</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_topLevel${entity.id}"
  	value="${entity.topLevel}" id="id_ref_topLevel${entity.id}"
	onkeyup="keyPressPositive(this)"  
	onafterpaste="onAfterPastePositive(this)"
	placeholder="置顶序号"
	/>
</div>

<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_name${entity.id}">名称</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_name${entity.id}"
  	value="${entity.name}" id="id_ref_name${entity.id}"
	placeholder="名称"
	/>
</div>

<#assign currentPreIntro=PositionRef.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
<@M_dropdownBarDiff idPre="id_ref_" entityId=entity.id propertyInput="preIntro" propertyContext=PositionRef.PREINTRO_TYPE current=currentPreIntro readonly=false />

<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_title${entity.id}">标题</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_title${entity.id}"
  	value="${entity.title}" id="id_ref_title${entity.id}"
	placeholder="主标题"
	/>
</div>

<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_subTitle${entity.id}">子标题</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_subTitle${entity.id}"
  	value="${entity.subTitle}" id="id_ref_subTitle${entity.id}"
	placeholder="子标题"
	/>
</div>
<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_originalLink${entity.id}">原链接</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_originalLink${entity.id}"
  	value="${entity.originalLink}" id="id_ref_originalLink${entity.id}"
	placeholder="原链接"
	/>
</div>
<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_link${entity.id}">导向链接</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_reflink${entity.id}"
  	value="${entity.link}" id="id_ref_link${entity.id}"
	placeholder="导向链接"
	/>
</div>
<div class="input-group input-group-sm">
	<span class="input-group-addon" id="id_addon_ref_remark${entity.id}">备注</span>
	<input type="text" class="form-control" 
  	aria-describedby="id_addon_ref_remark${entity.id}"
  	value="${entity.remark}" id="id_ref_remark${entity.id}"
	placeholder="备注"
	/>
</div>
<hr/>
<div class="form-group form-group-sm">
    <label class="col-sm-2 control-label" for="id_file_${entity.id}">
   	<div id="id_ref_photoPreview" >
	 	 
   		<#assign coverImage=entity.coverImage/>
	  	<#if coverImage!>
		 <span class="text-success" id="id_ref_coverImage_addon">封面图:</span>
		  <img class="img-rounded" width="60px" src="${coverImage}">
		 <#else>
		封面图
		 </#if>
		 <div id="id_ref_messageTip_preview" class="text-center"></div>
	 </div>
    </label>
  	 <#-- 
  	 <div class="input-group input-group-sm">
		 <div class="file-loading">
			<input id="id_cover_file_${entity.id}" data-devId="sz0099" data-project="ood" data-module="article" data-variety="article" data-position="cover" data-strategy="2" data-mainId="${entity.id}" data-subId="3" type="file" name="files"/>
		 </div>
	 </div>
	  -->
	<p><strong>头部图:</strong><@M_showImageForRefEdit imageList=entity.bannerList width="40px" /> </p>	 
	 	 
</div>
</form>
<script>
initPositionRefForCover('${entity.id}');
</script>
</#macro>
<#-- 关联编辑面板 end -->

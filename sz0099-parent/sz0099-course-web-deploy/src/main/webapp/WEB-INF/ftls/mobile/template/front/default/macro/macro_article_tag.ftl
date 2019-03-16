<#macro M_articleTag tagPage> 
<div class="text-center" style="padding: 5px 0 5px 0">
<#assign tagList=TagUtils.getSampleTags() />
<#list tagList as tag>
	<#assign tagVo=TagUtils.getRandomTagSizeVo() />
	<span class="label label-${tagVo.clazzName}" style="display: inline-block;font-size:${tagVo.fontSize}px;margin:2px 2px;">${tag}</span>
</#list>
</div>
</#macro>
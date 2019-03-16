<#-- tag begin -->

<#-- random color or font size for tag-->
<#macro M_showTagListRandom productTagList=null fontSize=15 randomSize=false withWrapper=true> 
<#if withWrapper>
<div class="text-center" style="padding: 5px 0 5px 0">
</#if>
<#if productTagList! && productTagList?size gt 0 >

<#list productTagList as ptag>
	<#if randomSize >
	<#assign tagSize=TagUtils.getRandomTagSize() />
	<#else>
	<#if fontSize lt 10>
		<#assign fontSize=10/>
	</#if>
	<#if fontSize gt 25 >
		<#assign fontSize=25/>
	</#if>
	<#assign tagSize=TagUtils.getRandomTagClazz(fontSize) />
	</#if>
	<span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;margin:2px 2px;">${ptag.name}</span>
</#list>
</#if>
<#if withWrapper>
</div>
</#if>
</#macro>


<#-- fixed fontsize and fixed color -->
<#macro M_showTagList productTagList=null onlyView=true deletedUrl="">
<#if productTagList! && productTagList?size gt 0 >
<#list productTagList as ptag>
<@M_showTag productTag=ptag onlyView=onlyView deletedUrl=deletedUrl/>
</#list>
</#if>
</#macro>

<#macro M_showTag productTag=null onlyView=true deletedUrl="">
<#if productTag!>

<#assign pname=productTag.name />
	<#if pname==null>
		<#assign atag=productTag.tag />
		<#if atag!>
			<#assign pname=atag.name/>
		</#if>
	</#if>
	<#if pname!>
		<span id="id_span_tag${productTag.id}" class="label label-info" style="display: inline-block;font-size:15px;margin:2px 2px;">
			${pname}
			<#if !onlyView>
			<span aria-hidden="true" onclick="deleteTag('${productTag.id}', '${productTag.mainId}', '${productTag.tagId}', this, '${deletedUrl}')">&times;</span>
			</#if>
		</span>
	</#if>
</#if>
</#macro>


<#macro M_showTagFirst tagList=null url="javascript:void(0)" fontSize=12 randomSize=false withWrapper=false onlyFirst=true> 
<#if withWrapper>
<div class="text-center" style="padding: 5px 0 5px 0">
</#if>
<#if tagList! && tagList?size gt 0 >

<#list tagList as ptag>
	<#if randomSize >
	<#assign tagSize=TagUtils.getRandomTagSize() />
	<#else>
	<#if fontSize lt 10>
		<#assign fontSize=10/>
	</#if>
	<#if fontSize gt 25 >
		<#assign fontSize=25/>
	</#if>
	<#assign tagSize=TagUtils.getRandomTagClazz(fontSize) />
	</#if>
	<a href="${url}"><span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;margin:2px 2px;">${ptag.name}</span></a>
	<#if onlyFirst>
	<#break/>
	</#if>
</#list>
</#if>
<#if withWrapper>
</div>
</#if>
</#macro>
<#-- tag end -->
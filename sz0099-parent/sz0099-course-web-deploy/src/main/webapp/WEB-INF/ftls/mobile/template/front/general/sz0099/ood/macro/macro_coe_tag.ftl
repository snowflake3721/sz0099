<#-- tag begin -->

<#-- random color or font size for tag-->
<#macro M_showTagListRandom productTagList=null idPre="tag_" entity0=null fontSize=15 randomSize=false withWrapper=true> 
<#if withWrapper>
<div class="text-center" style="padding: 5px 0 5px 0">
</#if>
<#if productTagList! && productTagList?size gt 0 >
<ul class="list-inline">
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
	<li>
	<#-- 
	<span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;margin:2px 2px;">${ptag.name}</span>
	 -->
	<a href="javascript:void(0)" onclick="showTag('标签导航','${idPre}_${entity0.id}_${ptag.id}')"><span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;">${ptag.name}</span></a>
	
	<div id="${idPre}_${entity0.id}_${ptag.id}" class="hidden">
		 <h4 class="text-center">
		 	<span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;margin:2px 2px;">${ptag.name}</span>
		 	<a href="${entity0.originalLink}">${entity0.title} <span class="glyphicon glyphicon-hand-right"></span></a>
		 </h4>
		 <hr/>
		 <div id="${idPre}_${entity0.id}_${ptag.id}_content">
		 	 <p class="text-right"><small>相关文章</small></p>
			 <div id="${idPre}_${entity0.id}_${ptag.id}_relative" 
			 data-loaded="0" data-name="${ptag.name}" data-mainId="${entity0.id}"
			 data-url="/sz0099/ood/article/findRelative"
			 >
			 	<ul class="list-group" id="${idPre}_${entity0.id}_${ptag.id}_relative_ul">
			 	<#-- load -->
			 	</ul>
			 </div>
		 </div>
	 </div>
	
	</li>
</#list>
</ul>
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


<#macro M_showTagFirst tagList=null idPre="tag_" entity0=null url="javascript:void(0)" fontSize=12 randomSize=false withWrapper=false onlyFirst=true> 
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
	<a href="${url}" onclick="showTag('标签导航','${idPre}_${entity0.id}')"><span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;">${ptag.name}</span></a>
	
	<div id="${idPre}_${entity0.id}" class="hidden">
		 <h4 class="text-center">
		 	<span class="label label-${tagSize.clazzName}" style="display: inline-block;font-size:${tagSize.fontSize}px;margin:2px 2px;">${ptag.name}</span>
		 	<a href="${entity0.originalLink}">${entity0.title} <span class="glyphicon glyphicon-hand-right"></span></a>
		 </h4>
		 <hr/>
		 <div id="${idPre}_${entity0.id}_content">
		 	 <p class="text-right"><small>相关文章</small></p>
			 <div id="${idPre}_${entity0.id}_relative" 
			 data-loaded="0" data-name="${ptag.name}" data-mainId="${entity0.id}"
			 data-url="/sz0099/ood/article/findRelative"
			 >
			 	<ul class="list-group" id="${idPre}_${entity0.id}_relative_ul">
			 	<#-- load -->
			 	</ul>
			 </div>
		 </div>
	 </div>
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
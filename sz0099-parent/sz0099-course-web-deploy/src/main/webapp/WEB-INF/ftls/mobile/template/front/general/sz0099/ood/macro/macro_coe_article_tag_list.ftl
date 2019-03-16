<#include "mobile/template/front/general/function/func_basic.ftl">

<#macro M_coeArticleTagPage itemPage moreLoaded=false>   
<!--列表开始--> 
<!--文章-->
<#if itemPage?? && itemPage.totalElements gt 0 >
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
      <li class="list-group-item <#if entity?is_even_item>list-group-item-warning</#if>">
			<span class="label label-info" style="display: inline-block;font-size:10px;margin:2px 2px;">${entity.name}</span>
			<a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a>
			<span class="glyphicon glyphicon-hand-right"></span>
	  </li>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded>
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="javascript:void(0)" class="btn btn-warning btn-xs">more</a>
				  </p>
			</div>
	  </#if>
<#else>
	<li class="list-group-item">
		该标签没有相关文章
	</li>  
</#if>
</#macro>


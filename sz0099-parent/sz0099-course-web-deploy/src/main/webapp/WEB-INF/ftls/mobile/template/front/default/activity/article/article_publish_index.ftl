<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container" id="top_menu">
<ol class="breadcrumb">
  <li><a href="#">个人中心 </a></li>
  <li><a href="#">发表美文</a></li>
  <li class="active">选择草稿</li>
</ol>
<#-- 个人中心 > 发表美文 > <span class="text-warning" id="id_span_nav">选择草稿</span> -->
</div>
<#-- 
<div class="container" id="body_top">
	<#include "mobile/template/front/default/activity/article/category/category_select.ftl">
</div>
 -->
 
<div class="container" id="body_content">

<#-- 选择草稿列表  begin -->
	<div  id="div_artilce_list">
		<#if articleBo??>
			<#assign articlePage=articleBo.articlePage/>
			<#if articlePage?? && articlePage.content?? && articlePage.content?size gt 0>
			    <h5>请选择一个草稿进行编辑 </h5>
				<ul class="list-group">
					  <#list articlePage.content as article>
					    <a href="/activity/article/publishUI/${article.id}" class="list-group-item list-group-item-warning" id="id_step_draft_${article.id}">
					    <span class="glyphicon glyphicon-pencil"></span> 
					    <span>${article.title}</span> 
					    </a>
					  </#list>
				</ul>
				<#if articlePage.content?size lt 4>	
					<h5>您也可以新建一篇草稿</h5>
				    <p class="text-center">
				  		<a href="/activity/article/publishUI" class="list-group-item list-group-item-danger" id="id_step_draft_${article.id}">
				  		<span class="glyphicon glyphicon-plus"></span>  <span>新建草稿</span>
				  		</a>
				    </p>
			    </#if>
			    
			 <#else>
			 	<p class="text-center">
			  		<a href="/activity/article/publishUI" class="list-group-item list-group-item-danger" id="id_step_draft_${article.id}">
				  	<span class="glyphicon glyphicon-plus"></span>	<span>新建草稿</span>
				  	</a>
			    </p>
		    </#if>
		    
		</#if>
	</div>
<#-- 选择草稿列表  end -->
</div>


<div class="container" id="body_footer_js">
<#include "/mobile/template/front/default/activity/article/body_footer_js_article_publish_index.ftl">
</div>
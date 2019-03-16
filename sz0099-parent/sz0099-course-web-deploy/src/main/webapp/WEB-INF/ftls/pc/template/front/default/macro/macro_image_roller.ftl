<#-- imageRollerId:给定imageRollerId，必须唯一, imageInfoListName 给定vue Json数组对象名称 -->
<#macro M_imageRoller imageRollerId imageInfoList withTitle="false">
<!--列表页的图片滚动，可用于广告发布，开始-->
<div id="carousel-image-roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
    <#if imageInfoList!?size &gt; 0>
           <#list imageInfoList as imageInfo>
          	<li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="${imageInfo?index}" class="<#if imageInfo?index==0>active</#if>" id="image_roller_${imageInfo.id}"></li>
           <#else>
            <li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="0" class="" id="_no_item">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    <div class="carousel-inner" role="listbox">
    <#if imageInfoList!?size &gt; 0 >
          <#list imageInfoList as imageInfo>
	          <div class="item <#if imageInfo?index==0>active</#if>">
	            	<img data-src="holder.js/900x500/auto/#777:#777" alt="${imageInfo.title}" src="${imageInfo.relPathFullname}${ImageGenerateStrategy.SYMBOL_UNDER_LINE}${ImageGenerateStrategy.W_900_H_500.getName()}${imageInfo.imageType}" title="${imageInfo.title}" data-holder-rendered="true">
	          		<#if withTitle=="true">
	          		<div class="carousel-caption">
			        	<h3>${imageInfo.title}</h3>
			        	<p>${imageInfo.description}</p>
			      	</div>
			      	</#if>
	          </div>
	          <#else>
		                正在准备中......
          </#list>
     </#if>
    </div>
    <a class="left carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
<!--列表页图片滚动结束-->    
 </#macro>


<#macro M_articlePageImageRoller imageRollerId articlePage withTitle="false" guideTip=">>>">
<!--文章列表：文章图片滚动，文章banner的首图，开始-->
<#if articlePage??>
	<#assign articleList=articlePage.content />
	<#if articleList!?size &gt; 0>
		<div id="carousel-image-roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
		    <ol class="carousel-indicators">
		           <#list articleList as article>
		           		<li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="${article?index}" class="<#if article?index==0>active</#if>" id="image_roller_${article.id}"></li>
		           <#else>
		           <li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="0" class="" id="${imageRollerId}_no_item">正在准备中......</li>
		           </#list>
		    </ol>
		    <div class="carousel-inner" role="listbox">
		          <#list articleList as article>
			          <div class="item <#if article?index==0>active</#if>">
			            	<img data-src="holder.js/900x500/auto/#777:#777" alt="${article.title}" src="${article.firstBannerImage}" title="${article.title}" data-holder-rendered="true">
			          		<#if withTitle=="true">
			          		<div class="carousel-caption">
			          			<h5 class="text-left">${guideTip}</h5>
					        	<h3>${article.title}</h3>
					        	<p>${article.subTitle}</p>
					      	</div>
					      	</#if>
			          </div>
			          <#else>
				                正在准备中......
		          </#list>
		    </div>
	
		    <a class="left carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		</div>
	</#if>
</#if>
<!--文章列表：文章首图滚动，文章banner的首图，结束-->   
 </#macro>
 
<#macro M_articleImageRoller imageRollerId articleList withTitle="false">
<!--文章列表：文章首图滚动，文章banner的首图，开始-->
<div id="carousel-image-roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
    <#if articleList!?size &gt; 0>
           <#list articleList as article>
           		<li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="${article?index}" class="<#if article?index==0>active</#if>" id="image_roller_${article.id}"></li>
           <#else>
           <li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="0" class="" id="${imageRollerId}_no_item">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    <div class="carousel-inner" role="listbox">
    <#if articleList!?size &gt; 0 >
          <#list articleList as article>
	          <div class="item <#if article?index==0>active</#if>">
	            	<img data-src="holder.js/900x500/auto/#777:#777" alt="${article.title}" src="${article.firstBannerImage}" title="${article.title}" data-holder-rendered="true">
	          		<#if withTitle=="true">
	          		<div class="carousel-caption">
			        	<h3>${article.title}</h3>
			        	<p>${article.subTitle}</p>
			      	</div>
			      	</#if>
	          </div>
	          <#else>
		                正在准备中......
          </#list>
     </#if>
    </div>
    <a class="left carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
<!--文章列表：文章首图滚动，文章banner的首图，结束-->   
 </#macro>
 
 
<#macro M_singleArticleImageRoller imageRollerId article withTitle="false">
<!--单篇文章：文章banner的图片滚动，开始-->
<div id="carousel-image-roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
    <#if article??>
    <ol class="carousel-indicators">
    	  <#assign bannerImageUrlList=article.bannerImageUrlList />
		    <#if bannerImageUrlList!?size &gt; 0 >      
		          <#list bannerImageUrlList as bannerImageUrl>
		           <li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="${bannerImageUrl?index}" class="<#if bannerImageUrl?index==0>active</#if>" id="image_roller_${article.id}_${bannerImageUrl?index}"></li>
		           <#else>
		           <li data-target="#carousel-image-roller-${imageRollerId}"  data-slide-to="0" class="" id="${imageRollerId}_no_item">正在准备中......</li>
		          </#list>
		    </#if>
    
    </ol>
    <div class="carousel-inner" role="listbox">
	    <#if bannerImageUrlList!?size &gt; 0 >
	          <#list bannerImageUrlList as bannerImageUrl>
		          <div class="item <#if bannerImageUrl?index==0>active</#if>">
		            	<img data-src="holder.js/900x500/auto/#777:#777" alt="${article.title}_${bannerImageUrl?index}" src="${bannerImageUrl}${ImageGenerateStrategy.SYMBOL_UNDER_LINE}${ImageGenerateStrategy.W_900_H_500.getName()}${imageInfo.imageType}" title="${article.title}" data-holder-rendered="true">
		          		<#if withTitle=="true">
		          		<div class="carousel-caption">
				        	<h3>${article.title}</h3>
				        	<p>${article.subTitle}</p>
				      	</div>
				      	</#if>
		          </div>
		          <#else>
			                正在准备中......
	          </#list>
	     </#if>
     </#if>
    </div>
    <a class="left carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-image-roller-${imageRollerId}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
<!--列表页图片滚动结束-->    
</#macro>
 
 
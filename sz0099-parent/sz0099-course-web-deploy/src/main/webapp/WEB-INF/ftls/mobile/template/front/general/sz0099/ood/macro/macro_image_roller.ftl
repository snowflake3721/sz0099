<#macro Mg_bannerImageRollerForView imageRollerId entity withTitle=false>
<!--单篇文章：文章banner的图片滚动，开始-->
<#if entity! && entity??>
<#assign bannerImageList=entity.bannerList />
<#if bannerImageList!?size gt 0>
<div id="roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
    
    <ol class="carousel-indicators">
    	  
		    <#if bannerImageList!?size &gt; 0 >      
		          <#list bannerImageList as bannerImage>
		           <li data-target="#roller-${imageRollerId}"  data-slide-to="${bannerImage?index}" class="<#if bannerImage?index==0>active</#if>" id="image_roller_${entity.id}_${bannerImage?index}"></li>
		           <#else>
		           <li data-target="#roller-${imageRollerId}"  data-slide-to="0" class="" id="${imageRollerId}_no_item">正在准备中......</li>
		          </#list>
		    </#if>
    
    </ol>
    <div class="carousel-inner" role="listbox">
	    <#if bannerImageList!?size &gt; 0 >
	          <#list bannerImageList as bannerImage>
		          <div class="item <#if bannerImage?index==0>active</#if>" style="height:210px">
		            	<img data-src="holder.js/720x540/auto/#777:#777" alt="${entity.title}_${bannerImage?index}" src="${PhotoUtil.getShowUrlForExpected2(bannerImage.fullurl,720, bannerImage.width)}" title="${entity.title}" data-holder-rendered="true">
		          		<#if withTitle>
		          		<div class="carousel-caption">
				        	<#-- <h3>${article.title}</h3> -->
				        	<p>${bannerImage.title}</p>
				      	</div>
				      	</#if>
		          </div>
		          <#else>
			                正在准备中......
	          </#list>
	     </#if>
     
    </div>
    <a class="left carousel-control" href="#roller-${imageRollerId}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#roller-${imageRollerId}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
</#if>
</#if>
<!--列表页图片滚动结束-->    
</#macro> 


<#macro M_rollerArticlePage imageRollerId page withTitle=false>
<#--文章列表：文章首图滚动，文章cover的首图，开始-->
<#if page??>
<#assign articleList=page.content />
<div id="roller-${imageRollerId}" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
    <#if articleList!?size &gt; 0>
           <#list articleList as article>
           		<li data-target="#roller-${imageRollerId}"  data-slide-to="${article?index}" class="<#if article?index==0>active</#if>" id="image_roller_${article.id}"></li>
           <#else>
           <li data-target="#roller-${imageRollerId}"  data-slide-to="0" class="" id="${imageRollerId}_no_item">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    <div class="carousel-inner" role="listbox">
    <#if articleList!?size &gt; 0 >
          <#list articleList as article>
          	  <#assign coverList=article.coverList />
          	  <#if coverList! && coverList?size gt 0 >
	          <div class="item <#if article?index==0>active</#if>">
	            	<a href="/activity/article/view/single/${article.id}" target="_self">
	            	<img data-src="holder.js/720x540/auto/#777:#777" alt="${article.title}" height="540px" src="${coverList.get(0).fullurl}" title="${article.title}" data-holder-rendered="true">
	          		<div class="carousel-caption">
			        	<h3>${article.title}</h3>
	          			<#if withTitle>
			        		<p>${article.subTitle}</p>
			      		</#if>
			      	</div>
	          		</a>
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
     </#if>
    </div>
    <a class="left carousel-control" href="#roller-${imageRollerId}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#roller-${imageRollerId}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</#if>    
</div>
<#--文章列表：文章首图滚动，文章cover的首图，结束-->   
 </#macro>
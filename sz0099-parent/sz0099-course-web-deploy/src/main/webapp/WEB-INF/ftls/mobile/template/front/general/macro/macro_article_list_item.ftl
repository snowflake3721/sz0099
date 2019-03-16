<#macro M_articlePage itemPage moreLoaded="true">   
<!--列表开始--> 
<!--文章开始-->
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as itemSingle>
		  <div class="media">
		      <div class="media-left">
		        <a href="${itemSingle.accessUrl}">
		           <#assign itemCoverImageList=itemSingle.coverImageUrlList />
		          <img class="media-object" data-src="holder.js/80x80" alt="80x80" src='${itemSingle.firstCoverImageUrl!"/assets/common/images/logo/img_cover_100X100.gif"}' data-holder-rendered="true" style="width: 80px; height: 80px;">
		        </a>
		      </div>
		      <div class="media-body">
		        <h4 class="media-heading">${itemSingle.title}</h4>
		        <p>${itemSingle.subTitle}</p>
		        <p>${itemSingle.roadLine}</p>
		      </div>
		  </div>
  	  </#list>
  	  <!-- 查看专题，跳至列表页 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
					  <button type="button" class="btn btn-warning btn-sm text-right">查看专题...</button>
				  </p>
			</div>
	  </#if>
 </#if>
 </#macro>

<#macro MV_articlePage itemPage> 
<!--文章列表开始,VUE控制-->
<div class="media" v-for="article in ${itemPage.name}.content">
      <div class="media-left">
        <a :href="article.accessUrl">
          <img class="media-object" data-src="holder.js/80x80" alt="80x80" :src='article.firstCoverImageUrl' data-holder-rendered="true" style="width: 80px; height: 80px;">
        </a>
      </div>
      <div class="media-body">
        <h4 class="media-heading">{{article.title}}</h4>
        <p>{{article.subTitle}}</p>
        <p>{{article.roadLine}}</p>
      </div>
</div>
  <#-- !"/assets/common/images/logo/img_cover_100X100.gif" -->
<p class="text-right">
  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
</p>
<!--文章列表结束,VUE控制-->
</#macro>

<#macro M_articleList itemList>
<!--文章列表开始--> 
<#if itemList??>
      <#list itemList as itemSingle>
		  <div class="media">
		      <div class="media-left">
		        <a href="${itemSingle.accessUrl}">
		           <#assign itemCoverImageList=itemSingle.coverImageUrlList />
		           <img class="media-object" data-src="holder.js/80x80" alt="80x80" src='${itemSingle.firstCoverImageUrl!"/assets/common/images/logo/img_cover_100X100.gif"}' data-holder-rendered="true" style="width: 80px; height: 80px;">
		        </a>
		      </div>
		      <div class="media-body">
		        <h4 class="media-heading">${itemSingle.title}</h4>
		        <p>${itemSingle.subTitle}</p>
		        <p>${itemSingle.roadLine}</p>
		      </div>
		  </div>
  	  </#list>
 <#-- 	   -->
 </#if>

<p class="text-right">
  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
</p>

  <!--文章列表结束-->
 </#macro>
 
<#macro MV_articlePageForQX articlePage="" name="articleList"> 
 	<div class="text-center "><span class="label label-success">群侠户外</span></div>
    <div class="jumbotron text-center center-block">
	  <h2>群聚一起 侠义出行</h2>
	  <p>
		  <div class="row">
			  <div class="col-xs-6 col-md-3" v-for="article in ${name}" :key="article.id">
			    <a href="#" class="thumbnail">
			      <img :src="article.imageUrl" :alt="article.title">
			    </a>
			    <div class="caption">
			        <h6>{{article.title}}</h6>
			    </div>
			  </div>
		  </div>
	  </p>
	  <p><a class="btn btn-primary btn-sm" href="#" role="button">查看更多...</a></p>
	</div>
</#macro>

<#macro M_articlePageForQX articlePage=null name="articlePage" title="群侠户外" subTitle="群聚一起 侠义出行"> 
 	<#if articlePage??>
 		<@M_articlePageForBase articlePage=articlePage name=name title=title subTitle=subTitle panelTitle="打赏先锋"/>
	</#if>
</#macro>

<#macro M_articlePageForTravel articlePage=null name="articlePage" title="走过的路" subTitle="时光静好 随性无言"> 
 	<#if articlePage??>
 		<@M_articlePageForBase articlePage=articlePage name=name title=title subTitle=subTitle panelTitle="步步感悟"/>
	</#if>
</#macro>

<#macro M_articlePageForBase articlePage=null name="articlePage" title="群侠户外" subTitle="群聚一起 侠义出行" panelTitle="打赏先锋"> 
 	<#if articlePage??>
 		<#assign articleList=articlePage.content />
 		
	 	<div class="text-center "><span class="label label-success">${title}</span></div>
	    <div class="jumbotron text-center">
		   <p class="alert alert-danger">${panelTitle}</p>
		  <h2>${subTitle}</h2>
		  <p>
			  <div class="row">
		  <#list articleList as article >
				  <div class="col-xs-6 col-md-3" id="${article.id}">
				    <a href="#" class="thumbnail">
				      <img src="${article.firstCoverImageUrl}" alt="${article.title}" style="width:80px">
				    </a>
				    <div class="caption">
				        <h6>${article.title}</h6>
				    </div>
				  </div>
		  </#list>
			  </div>
		  </p>
		  <p><a class="btn btn-primary btn-sm" href="#" role="button">查看更多...</a></p>
		</div>
	</#if>
</#macro>


<#macro M_articlePageForBiking page>
<!--文章列表_骑行_开始--> 
<#if page??>
	<#assign itemList=page.content />
	<div class="container text-center">
	<h4 class="texte-danger text-center bg-info" style="line-height:2;">行者骑行在路上</h4>
		  
	<#if itemList!?size &gt; 0 >
		<ul class="list-inline">
	      <#list itemList as itemSingle>
			  <li class="text-center">
			    <img src="${itemSingle.firstCoverImageUrl!"/assets/common/images/logo/img_cover_100X100.gif"}" alt="${itemSingle.title}" width="130" class="img-circle">
			    <h5>${StringUtils.substring(itemSingle.title,0,12)}</h5>
			    <p>${StringUtils.substring(itemSingle.roadLine,0,12)}</p>
		  	  </li>
	  	  </#list>
	  	</ul>  
	</#if>
	</div>
</#if>
	<div class="container">
		<p class="text-right">
		  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
		</p>
	</div>
<hr/>
<!--文章列表_骑行_结束-->
</#macro>
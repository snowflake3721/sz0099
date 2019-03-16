<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#-- 图文横滚 -->
<#macro M_layout_imgWordRollerH_multi positon=null entityPage=null idPre="layout_" showHeadTip=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false>
<#if entityPage! && entityPage.totalElements gt 0 >
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
<#if position?? && showHeadTip>
<div>${Position.PANEL.getLabel(position.panel)}</div>
</#if>
<div id="id_roller_${idPre}_${positon.id}" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
    <#if entityList!?size &gt; 0>
           <#list entityList as entity>
           		<li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${entity?index}" class="<#if entity?index==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${entity.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${positon.id}">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    <div class="carousel-inner" role="listbox">
    <#if entityList!?size &gt; 0 >
          <#list entityList as entity>
          	  <#assign coverImage=PhotoUtil.getShowUrlForExpected2(entity.coverImage,720, banner.width)/>
          	  <#if coverImage! >
	          <div class="item <#if entity?index==0>active</#if>">
	            	<a href="${getLink(entity, "/")}" target="_self">
	            	<img data-src="holder.js/720x540/auto/#777:#777" alt="${entity.title}" height="540px" src="${coverImage}" title="${entity.title}" data-holder-rendered="true">
	          		<div class="carousel-caption">
	          			<#if withPreIntro>
			        	<p class="text-left"><small>${entity.preIntro}</small></p>
			      		</#if>
	          			<#if withTitle>
			        	<h3>${entity.title}</h3>
			      		</#if>
	          			<#if withSubTitle>
			        	<p class="text-right"><small>${entity.subTitle}</small></p>
			      		</#if>
			      	</div>
	          		</a>
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <a class="left carousel-control" href="#id_roller_${idPre}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
<#-- 
<script>
carouselWrapperInit('id_roller_${idPre}_${positon.id}',2300);
</script>
 -->
</#if>    
</#macro>

<#-- 单篇图滚 可加载下一篇-->
<#macro M_layout_imgWordRollerH_single positon=null entityPage=null idPre="layout_" showHeadTip=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
<#if entityList!?size &gt; 0>
<#assign entity0=null/>
<#list entityList as entityItem>
<#if entityItem?is_first>
<#assign entity0=entityItem/>
</#if>
<#break>
</#list>
<#assign bannerList=entity0.coverList/>
<#if bannerList!>

<div id="id_roller_${idPre}_${positon.id}" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
    <#if bannerList!?size &gt; 0>
           <#list bannerList as banner>
           		<li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${banner?index}" class="<#if banner?index==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${banner.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${positon.id}">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    <div class="carousel-inner" role="listbox">
    <#if bannerList!?size &gt; 0 >
          <#list bannerList as banner>
          	  <#assign bannerImage=PhotoUtil.getShowUrlForExpected2(banner.fullurl,720, banner.width) />
          	  <#if bannerImage! >
	          <div class="item <#if banner?index==0>active</#if>">
	            	<a href="${getLink(entity0, "/")}" target="_self">
	            	<img data-src="holder.js/720x540/auto/#777:#777" alt="${entity0.title}" height="540px" src="${bannerImage}" title="${entity0.title}" data-holder-rendered="true">
	          		<div class="carousel-caption">
	          			<#if withPreIntro>
			        	<p class="text-left"><small>${entity0.preIntro}</small></p>
			      		</#if>
	          			<#if withTitle>
			        	<h3>${entity0.title}</h3>
			      		</#if>
	          			<#if withSubTitle>
			        	<p class="text-right"><small>${entity0.subTitle}</small></p>
			      		</#if>
			      	</div>
	          		</a>
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <a class="left carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
<script>
$(document).ready(function(){
//carouselWrapperInit('id_roller_${idPre}_${positon.id}',2000);
carouselInit();
});
</script>
<#-- -->
</#if>
</#if>
</#if>
</#macro> 

<#-- 单篇封面图滚 -->
<#macro M_layout_imgCoverRollerH_single entity0=null idPre="layout_" withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false >
<#if entity0??>
<#assign coverList=entity0.coverList/>
<#if coverList! && coverList?size &gt; 0>
<div id="id_roller_${idPre}_${entity0.id}" class="carousel slide" data-ride="carousel">
    <#if showDot>
    <ol class="carousel-indicators">
    <#if coverList!?size &gt; 1>
           <#list coverList as banner>
           		<li data-target="#id_roller_${idPre}_${entity0.id}"  data-slide-to="${banner?index}" class="<#if banner?index==0>active</#if>" id="image_roller_${idPre}_${entity0.id}_${banner.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${entity0.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${entity0.id}">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    </#if>
    <div class="carousel-inner" role="listbox">
    <#if coverList!  && coverList?size &gt; 0 >
          <#list coverList as banner>
          	  <#assign bannerImage=PhotoUtil.getShowUrlForExpected2(banner.fullurl,300, banner.width) />
          	  <#if bannerImage! >
	          <div class="item <#if banner?index==0>active</#if>">
	          	<#-- onclick="showBigView('${banner.fullurl}',720, ${banner.width},'${banner.id}')" -->
	            	<a href="<#if linkable>${getLink(entity0, "/")}<#else>javascript:void(0)</#if>" class="thumbnail" alt="${entity0.title}" target="_self">
	            	<img data-src="holder.js/300x225/auto/#777:#777"  height="108px" src="${bannerImage}" title="${entity0.title}" data-holder-rendered="true">
	          		<div class="carousel-caption">
	          			<#if withPreIntro>
			        	<p class="text-left"><small>${entity0.preIntro}</small></p>
			      		</#if>
	          			<#if withTitle>
			        	<h4>${entity0.title}</h4>
			      		</#if>
	          			<#if withSubTitle>
			        	<p class="text-right"><small>${entity0.subTitle}</small></p>
			      		</#if>
			      		
			      	</div>
			      	</img>
			      	<#if showPenname>
	          		<div class="container">
	          		<img id='id_tpl_media_headImg${entity0.id}' class='img-circle pull-left'  width='20px' height='20px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
		   			<span class="pull-right">--- ${entity0.penname}</span>
	          		</div>
	          		</#if>
	          		</a>
	          		
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <#if showPreNext>
    <a class="left carousel-control" href="#id_roller_${idPre}_${entity0.id}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}_${entity0.id}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    </#if>
</div>
<script>
$(document).ready(function(){
//carouselWrapperInit('id_roller_${idPre}_${entity0.id}',500);
carouselInit();
});
</script>
<#-- -->
<#else>
<#assign coverImage=entity0.coverImage/>
<#if coverImage! >
<#-- onclick="showBigView('${coverImage}',720, 720,'${entity0.id}')" -->
		<a href='<#if linkable>${getLink(entity0, "/")}<#else>javascript:void(0)</#if>' class="thumbnail" >
		  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${coverImage}' alt='${entity0.title}'>
		  <div class="carousel-caption">
	  			<#if withPreIntro>
	        	<p class="text-left"><small>${entity0.preIntro}</small></p>
	      		</#if>
	  			<#if withTitle>
	        	<h4>${entity0.title}</h4>
	      		</#if>
	  			<#if withSubTitle>
	        	<p class="text-right"><small>${entity0.subTitle}</small></p>
	      		</#if>
	       </div>
	       </img>
		  <#if showPenname>
		  <div class="container">
		  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle pull-left'  width='20px' height='20px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
		   <span class="pull-right">--- ${entity0.penname}</span>
		   </div>
		  </#if>
		</a> 
</#if>
</#if>

</#if>
</#macro>

<#-- 单篇封面图滚 -->
<#macro M_layout_imgCoverRollerH_single2 entity0=null idPre="layout_" withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false >
<#if entity0??>
<#assign coverList=entity0.coverList/>
<#if coverList! && coverList?size &gt; 0>
<div id="id_roller_${idPre}_${entity0.id}" class="carousel slide" data-ride="carousel">
    <#if showDot>
    <ol class="carousel-indicators">
    <#if coverList!?size &gt; 1>
           <#list coverList as banner>
           		<li data-target="#id_roller_${idPre}_${entity0.id}"  data-slide-to="${banner?index}" class="<#if banner?index==0>active</#if>" id="image_roller_${idPre}_${entity0.id}_${banner.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${entity0.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${entity0.id}">正在准备中......</li>
           </#list>
       </#if>
    </ol>
    </#if>
    <div class="carousel-inner" role="listbox">
    <#if coverList!  && coverList?size &gt; 0 >
          <#list coverList as banner>
          	  <#assign bannerImage=PhotoUtil.getShowUrlForExpected2(banner.fullurl,300, banner.width) />
          	  <#if bannerImage! >
	          <div class="item <#if banner?index==0>active</#if>">
	          <#-- onclick="showBigView('${banner.fullurl}',720, ${banner.width},'${banner.id}')" -->
	            	<a href="<#if linkable>${getLink(entity0, "/")}<#else>javascript:void(0)</#if>" class="thumbnail" alt="${entity0.title}" target="_self">
	            	<img data-src="holder.js/300x225/auto/#777:#777"  height="108px" src="${bannerImage}" title="${entity0.title}" data-holder-rendered="true">
			      	</img>
	          		</a>
	          		<#if withTitle>
	          		<h5><strong><a class="text-left" href="${getLink(entity0, '/')}">${entity0.title}</a></strong></h5>
				    </#if>
				    <#if withSubTitle>
			        	<p class="text-right"><small>${entity0.subTitle}</small></p>
			      	</#if>
				   <div class="container">
				   <#if showPenname>
					    <p>
					    <span class="pull-left">
					    <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
						  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
						</a> 
						</span>
						<#if withPreIntro>
						<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${entity0.preIntro}</span><br/> ${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</small>
						</#if>
						</p>
					</#if>
					</div>
	          		
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <#if showPreNext>
    <a class="left carousel-control" href="#id_roller_${idPre}_${entity0.id}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}_${entity0.id}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    </#if>
</div>
<script>
$(document).ready(function(){
//carouselWrapperInit('id_roller_${idPre}_${entity0.id}',500);
carouselInit();
});
</script>
<#-- -->
<#else>
<#assign coverImage=entity0.coverImage/>
<#if coverImage! >
		<#-- onclick="showBigView('${coverImage}',720, 720,'${entity0.id}')" -->
		<a href='<#if linkable>${getLink(entity0, "/")}<#else>javascript:void(0)</#if>' class="thumbnail" >
		  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${coverImage}' alt='${entity0.title}'>
		  <div class="carousel-caption">
	  			<#if withPreIntro>
	        	<p class="text-left"><small>${entity0.preIntro}</small></p>
	      		</#if>
	  			<#if withTitle>
	        	<h4>${entity0.title}</h4>
	      		</#if>
	  			<#if withSubTitle>
	        	<p class="text-right"><small>${entity0.subTitle}</small></p>
	      		</#if>
	       </div>
	       </img>
		  <#if showPenname>
		  <div class="container">
		  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle pull-left'  width='20px' height='20px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
		   <span class="pull-right">--- ${entity0.penname}</span>
		   </div>
		  </#if>
		</a> 
</#if>
</#if>

</#if>
</#macro>


<#-- 图文并排 IMG_WORD_2_ABREAST_LIST-->
<#macro M_layout_imgCoverRollerWordAbreast_list positon=null entityPage=null idPre="ly_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
    <#list entityList as entity0>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=entity0.author />
			  	<a href='javascript:void(0)' onclick="showSayword('${entity0.nickname}','${entity0.sayword.description}')">
				  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
				</a> 
			    <span class="pull-right">${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}<strong> &nbsp;&nbsp;</strong></span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-6 col-md-4">
						  	
						    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
						    <p>${getSubstring(entity0.description,60,'暂无')}</p>
						    
						  </div>
						  <div class="col-xs-6 col-md-4">
						    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>

<#-- 双图并排 IMG_2_ABREAST_LIST-->
<#macro M_layout_imgCoverRollerAbreast_list positon=null entityPage=null idPre="ly_imgList" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=true linkable=true>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
	<div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <#if showListLoadMore>
			  <div class="panel-heading text-right">
			  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more...</a>
			  </div>
			  </#if>
			  <div class="panel-body" sytle="margin-bottom:0px">
    <#list entityList as entity0>
    			<#assign isOddItem=entity0?is_odd_item />
    			<#assign isEvenItem=entity0?is_even_item />
    			<#if isOddItem>
				  	<div class="row">
				  	</#if>
						  <div class="col-xs-6 col-md-4">
						    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
						  </div>
					<#if isEvenItem || entity0?is_last>
					</div>
					</#if>
  	  </#list>
  	  			</div><#-- end body -->
	</div><!--end panel-->
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>

<#-- 一文三图 多文滚播 IMG_3_ABREAST_ROLLER-->
<#macro M_layout_imgBannerRollerAbreast_multi positon=null entityPage=null idPre="ly_imgList" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=true linkable=true>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
    


<div id="id_roller_${idPre}_${positon.id}" class="carousel slide" data-ride="carousel">
    <#if showDot>
    <ol class="carousel-indicators">
    <#if entityList!?size &gt; 0>
           <#list entityList as entity0>
           		<li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${entity0?index}" class="<#if entity0?index==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${entity0.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${positon.id}_${entity0.id}">正在准备中......</li>
           </#list>
    </#if>
    </ol>
    </#if>
    <div class="carousel-inner" role="listbox">
    <#if entityList!?size &gt; 0 >
          <#list entityList as entity0>
          	  <#if entity0! >
	          <div class="item <#if entity0?index==0>active</#if>">
	            	<div class="panel panel-default">
	            	<#if showPenname>
							  <div class="container text-right">
							  <span class="pull-left"><a href="${getLink(entity0, "/")}" target="_self">${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd')}</a><strong> &nbsp;&nbsp;</strong></span>
							  <span>--- ${entity0.penname}&nbsp;&nbsp;</span>
							  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='20px' height='20px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
							  </div>
					</#if>
					<div class="container">
			            	
									<#assign bannerList=entity0.bannerList />
									
									<#if withTitle>
						        	<p class="text-center"><a href="${getLink(entity0, "/")}" target="_self"><strong>${entity0.title}</strong></a></p>
						      		</#if>
						  	
									<#if bannerList! && bannerList?size gt 0 >
									 	<div class="row">
									 	<#list bannerList as image>
											<div class="col-xs-4 col-md-4">
											<a href='javascript:void(0)' class="thumbnail" onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
											  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${image.expectedUrl}' alt='${image.id}'>
										       </img>
											</a> 
											</div>
										</#list>
										</div>
									</#if>
						  			<#if withPreIntro>
						        	<p class="text-left"><small>${entity0.preIntro}</small></p>
						      		</#if>
						  			
						  			<#if withSubTitle>
						        	<p class="text-right"><small>${entity0.subTitle}</small></p>
						      		</#if>
			          		
			          	</div>
	          		</div>
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <#if showPreNext>
    <a class="left carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    </#if>
</div>
<#-- 
<script>
$(document).ready(function(){
carouselWrapperInit('id_roller_${idPre}_${positon.id}',2000);
//carouselInit();
});
</script>	
 -->			
					
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>

<#-- 大屏幕单篇特写 -->
<#macro M_layout_jumbotron_single entity0=null idPre="ly_jb_" tip="独家记忆" tipShowable=true btnLabel="进入>>>"  withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true >
<#if entity0!>
<#assign coverList=entity0.coverList/>
<#if coverList!>
<#assign coverItem=coverList?first />
<#if coverItem!>
<#assign coverImage=PhotoUtil.getShowUrlForExpected2(coverItem.fullurl,720, coverItem.width) />
<div class="jumbotron center-block" id="id_jumbotron_${idPre}_${entity0.id}" style="background: url('${coverImage}') no-repeat center center;  width: 100%;">
  <#if tipShowable>
  <span class="bg-info text-center text-danger">${tip}</span>
  </#if>
  <#if withPreIntro>
  <p><span><small>${entity0.preIntro}</small></span></p>
  </#if>
  <h1>${entity0.title}</h1>
  <#if withSubTitle>
  <p class="text-right">${entity0.subTitle}</p>
  </#if>
  <#if linkable>
  <p><a class="btn btn-primary btn-lg" href="${getLink(entity0, "/")}" role="button">${btnLabel}</a></p>
  </#if>
</div>
</#if>
</#if>
</#if>
</#macro>

<#-- 大屏幕单篇特写，取coverImage -->
<#macro M_layout_jumbotron_single2 entity0=null tip="" idPre="ly_jb_" withPreIntro=true withTitle=true withSubTitle=true linkable=true >
<#if entity0!>
<#assign coverImage=entity0.coverImage/>
	<#assign coverList=entity0.coverList/>
	<#if coverList!>
		<#assign coverItem=coverList?first />
		<#if coverItem!>
		<#assign coverImage=PhotoUtil.getShowUrlForExpected2(coverItem.fullurl,720, coverItem.width) />
		</#if>
	</#if>
<div class="jumbotron center-block" id="id_jumbotron_${idPre}_${entity0.id}" style="background: url('${coverImage}') no-repeat center center;  width: 100%;height:100%">
  <span class="bg-info text-center text-danger">${tip}</span>
  <#-- <p class="text-center">大自然传鬼斧神功，人世间酿能工巧匠</p> -->
  <img src="${coverImage}" class="img-responsive img-thumbnail" title="${entity0.title}"/>
  <#if withPreIntro>
  <p><span><small>${entity0.preIntro}</small></span></p>
  </#if>
  <h3><strong>${entity0.title}</strong></h3>
  <#if withSubTitle>
  <p class="text-right"><span class="bg-success">${entity0.subTitle}</span></p>
  </#if>
  <#if linkable>
  <#-- 
  <small><span class="label label-success"></span></small>
   -->
  <p>
  <a class="btn btn-primary" href="${entity0.link}" role="button">进入>>></a>
  </p>
  </#if>
</div>
</#if>
</#macro>

<#macro M_layout_jumbotron_single3 entity0=null tip="独家记忆" idPre="ly_jb_" withPreIntro=true withTitle=true withSubTitle=true linkable=true >
<#if entity0!>
<#assign coverImage=entity0.coverImage/>
	<#assign coverList=entity0.coverList/>
	<#if coverList!>
		<#assign coverItem=coverList?first />
		<#if coverItem!>
		<#assign coverImage=PhotoUtil.getShowUrlForExpected2(coverItem.fullurl,720, coverItem.width) />
		</#if>
	</#if>
<div class="jumbotron center-block" id="id_jumbotron_${idPre}_${entity0.id}" style="background: url('${coverImage}') no-repeat center center;  width: 100%;height:100%">
  <span class="bg-info text-center text-danger">${tip}</span>
  <#-- <p class="text-center">大自然传鬼斧神功，人世间酿能工巧匠</p> -->
  <img src="${coverImage}" class="img-responsive img-thumbnail" title="${entity0.title}"/>
  <#if withPreIntro>
  <p><span><small>${entity0.preIntro}</small></span></p>
  </#if>
  <h3><strong>${entity0.title}</strong></h3>
  <#if withSubTitle>
  <p class="text-right"><span class="bg-success">${entity0.subTitle}</span></p>
  </#if>
  <#if linkable>
  <#-- 
  <small><span class="label label-success"></span></small>
   -->
  <p>
  <a class="btn btn-primary" href="${entity0.link}" role="button">进入>>></a>
  </p>
  </#if>
</div>
</#if>
</#macro>


<#-- 文字列表 WORD_1_LIST-->
<#macro M_layout_wordList positon=null entityPage=null idPre="ly_word_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
	<div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			最新文章
	  			<a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more...</a>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
    	<#list entityList as entity0>
		  <#assign isEvenItem=entity0?is_even_item/>
				  	<div class="row <#if isEvenItem>bg-warning</#if>">
					  	<div class="col-xs-9 col-md-9">
							    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, "/")}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
							    <p>${getSubstring(entity0.description,60,'暂无')}</p>
							    <#assign author=entity0.author />
							  	
							    <p>@${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</p>
						</div>
						<div class="col-xs-3 col-md-3">
								<a href='javascript:void(0)' onclick="alert('${author.nickname}')">
								  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='60px' height='60px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
								</a> 
						</div>
					</div><#-- end row -->
		
  	  	</#list>
  	  	  </div><#-- end body -->
	 </div>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>

<#-- 缩略图列表 IMG_AD_LIST 很小很多 80张小图放这-->
<#macro M_layout_imgAdList positon=null entityPage=null idPre="ly_img_ad_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
	<div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			缩图定位
	  			<a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more...</a>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="container">
    	<#list entityList as entity0>
		  <#assign isEvenItem=entity0?is_even_item/>
				  	<#assign coverList=entity0.coverList/>
				  	<#if coverList!?size &gt; 0>
           				<#assign image=coverList?first />
						  	
						  	<#list 1..20 as i>
						  	<a href='javascript:void(0)'  class="img-rounded" style="margin:0px 0px;padding:0px 0px;border-radius:0px" onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
							  <img id='id_tpl_media_common_img${image.id}' style="margin:0px 0px;padding:0px 0px;border-radius:0px"  height='34px' width="34px" src='${image.expectedUrl}' alt='${image.id}'>
						       </img>
							</a> 
							</#list>
					</#if>  	
					  		<#-- 
							    <h5><strong>
							    <a type="button" class="text-left" href="${getLink(entity0, "/")}">${entity0.articleNo}-${entity0.title}</a>
							    </strong></h5>
							    <p>${getSubstring(entity0.description,60,'暂无')}</p>
							    <#assign author=entity0.author />
							  	
							    <p>@${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</p>
							     -->
						
		
  	  	</#list>
						
					</div><#-- end row -->
  	  	  </div><#-- end body -->
	 </div>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>


<#-- 两栏，左图右文  仅图滚播，文字不动 begin-->
<#macro M_imgBannerRoller2Word_single entity0=null user=null url="/sz0099/ood/personal/profession/searchForCategory">   
<!--技能-->
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=entity.author />
	  			<#if author!>
			  	<a href='javascript:void(0)' onclick="showSaywordHistory('${entity.nickname}','${entity.sayword.description}', '@${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd')}')">
				  <img id='id_tpl_media_headImg${entity.id}' class='img-circle'  width='25px' height='25px' src='${entity.headImg}' alt='${entity.nickname}' title='${entity.nickname}'>
				</a> 
				</#if>
			    <span class="pull-right">${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}<strong> &nbsp;&nbsp;</strong></span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-6 col-md-4">
						  	
						    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${profession.professionNo}-${profession.title}</a></strong></h5>
						    <p>${getSubstring(entity0.description,60,'暂无')}</p>
						    
						  </div>
						  <div class="col-xs-6 col-md-4">
						    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre="layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
	  
 </#macro>
<#-- 两栏，左图右文 end-->

<#-- 一文三图，分上下 ，整体滚播begin 用于邀请人技能展示-->
<#macro M_layout_imgBannerRollerAbreast3_multi positon=null entityPage=null idPre="ly_imgA3" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=true linkable=true mainCircle=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
    


<div id="id_roller_${idPre}_${positon.id}" class="carousel slide" data-ride="carousel">
    <#if showDot>
    <ol class="carousel-indicators">
    <#if entityList!?size &gt; 0>
           <#list entityList as entity0>
           		<li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${entity0?index}" class="<#if entity0?index==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${entity0.id}"></li>
           <#else>
           <li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="0" class="" id="id_no_item_${idPre}_${positon.id}_${entity0.id}">正在准备中......</li>
           </#list>
    </#if>
    </ol>
    </#if>
    <div class="carousel-inner" role="listbox">
    <#if entityList!?size &gt; 0 >
          <#list entityList as entity0>
          	  <#if entity0! >
          	  <#assign author=entity0.author/>
	          <div class="item <#if entity0?index==0>active</#if>">
	            	<div class="panel panel-default">
	            	<#if showPenname>
							  <div class="container">
							  <span class="pull-left">
							  <strong> 
							  <#if entity0.proTagList!?size gt 0 > 
							  	<@M_showTagListRandom productTagList=entity0.proTagList fontSize=12 randomSize=false withWrapper=false/>
							  	</#if>
			  					</strong>
			  					</span>
							  <img id='id_tpl_media_headImg${entity0.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${author.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
							   <span class="pull-right">--- ${entity0.penname}&nbsp;&nbsp;</span>
							  </div>
					</#if>
					<div class="container">
			            	<a href="${getLink(entity0, "/")}" target="_self">
									<#assign bannerList=entity0.bannerList />
									
									<#if withTitle>
						        	<p class="text-center"><strong>${entity0.title}</strong></p>
						      		</#if>
						  	</a>
									<#if bannerList! && bannerList?size gt 0 >
									 	<div class="row">
									 	<#list bannerList as image>
											<div class="col-xs-4 col-md-4">
											<a href='javascript:void(0)' class="thumbnail" onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
											  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${image.expectedUrl}' alt='${image.id}'>
										       </img>
											</a> 
											</div>
										</#list>
										</div>
									</#if>
						  			<#if withPreIntro>
						        	<p class="text-left"><small>${entity0.preIntro}</small></p>
						      		</#if>
						  			
						  			<#if withSubTitle>
						        	<p class="text-right"><small>${entity0.subTitle}</small></p>
						      		</#if>
			          		
			          	</div>
	          		</div>
	          </div>
	          </#if>
	          <#else>
		                正在准备中......
          </#list>
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
     </#if>
    </div>
    <#if showPreNext>
    <a class="left carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
    </#if>
</div>
<#-- 
<script>
$(document).ready(function(){
carouselWrapperInit('id_roller_${idPre}_${positon.id}',2000);
//carouselInit();
});
</script>				
	 -->				
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <#-- 
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	   -->
 </#if>
</#macro>
<#-- 两栏，左图右文 ，整体滚播end-->



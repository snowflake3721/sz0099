<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">


<#macro M_coeCategArticlePage itemPage moreLoaded="true" url="/sz0099/ood/article/queryList" condition="">   
<!--列表开始--> 
<!--文章-->
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
	  			<#-- onclick="showSayword('${article.nickname}','${article.sayword}')" -->
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right"><small><strong>
			      
			     <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
				  ${ShowNameUtil.getShowname(entity0.penname, entity0.nickname, 'dml')}
				 </a>
			     <span class="label label-success">出品于</span> ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd HH:mm')} &nbsp;</strong></small></span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						  	
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.articleNo}-${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						    
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<#-- 
						    	<@M_showImageForView imageList=itemSingle.coverList />
						    	 -->
						    	<@M_layout_imgCoverRollerH_single entity0=article idPre="layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
								 
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${itemPage.number+1}&size=${itemPage.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	  
 </#if>
 </#macro>


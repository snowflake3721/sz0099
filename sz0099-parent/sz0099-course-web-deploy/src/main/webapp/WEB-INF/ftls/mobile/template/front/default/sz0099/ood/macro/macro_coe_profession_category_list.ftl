<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">


<#macro M_categProfessionPage itemPage moreLoaded="true" url="/sz0099/ood/personal/profession/searchForCategory" condition="">   
<!--列表开始--> 
<!--技能-->
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
	  			<#if author!>
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')">
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.nickname}'>
				</a> 
				</#if>
			    <span class="pull-right"><small><strong> 
			    	<a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
				  	${profession.nickname}
				 	</a>
			    <span class="label label-primary">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;</strong><small></span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-6 col-md-4">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.articleNo}-${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,60,'暂无')}</p>
						    
						  </div>
						  <div class="col-xs-6 col-md-4">
						    	<#-- 
						    	<@M_showImageForView imageList=itemSingle.coverList />
						    	 -->
						    	<@M_layout_imgCoverRollerH_single entity0=profession idPre="layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
								 
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


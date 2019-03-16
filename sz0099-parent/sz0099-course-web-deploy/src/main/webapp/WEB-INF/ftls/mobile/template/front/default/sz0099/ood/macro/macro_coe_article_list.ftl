<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">

<#macro M_coeArticleDraftList draftList=null url="/sz0099/ood/article/manage/create?id="> 
<div class="list-group text-center">
<#if draftList?? && draftList?size gt 0>
	<#list draftList as draft>
	  <a type="button" href="${url}${draft.id}" class="list-group-item <#if draft?is_even_item>list-group-item-warning</#if>">${draft.articleNo}-${draft.name} 
	  <span class="pull-right"><strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
	  </a>
	  
	</#list>
	<#if draftList?size lt 5>
	  <a type="button" href="${url}" class="list-group-item btn btn-info">新建文章</a>
	  </#if>
<#else>
<a type="button" href="${url}" class="list-group-item btn btn-info">新建文章</a>
</#if>
</div>
<p class="bg-primary">1.好看的，好吃的，好玩的，都得分享！</p>
<p class="bg-warning">2.文章是你对外分享的第一表达方式.</p>
<p class="bg-primary">3.透过你的文章让朋友们认识你，熟知你...</p>
<p class="bg-warning">4.发表文章，有可能获得别人的打赏呢！</p>
<p class="bg-primary">5.文章配合技能，还能拓展你的服务哦！</p>
<p class="bg-warning">6.发布了文章，你的传说就留在江湖啦...</p>
<p class="bg-primary">7.文章被点赞的时候，也会留下大侠的传说呢...</p>
</#macro>

<#macro M_coeArticlePage itemPage moreLoaded="true" url="/sz0099/ood/article/queryList" condition="">   
<!--列表开始--> 
<!--文章-->
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as itemSingle>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=itemSingle.author />
			  	<a href='javascript:void(0)' onclick="alert('${author.userId}')">
				  <img id='id_tpl_media_headImg${itemSingle.id}' class='img-circle'  width='25px' height='25px' src='${author.headImg}' alt='${author.nickname}' title='${author.nickname}'>
				</a> 
			    <span class="pull-right">${DateUtils.formatToString(itemSingle.publishTime ,'yyyy-MM-dd HH:mm')}<strong> &nbsp;&nbsp; #${itemSingle?index}</strong></span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-6 col-md-4">
						  	
						    <h5><strong><a type="button" class="text-left" href="/sz0099/ood/article/detail/${itemSingle.id}">${itemSingle.articleNo}-${itemSingle.title}</a></strong></h5>
						    <p>${getSubstring(itemSingle.description,60,'暂无')}</p>
						    
						  </div>
						  <div class="col-xs-6 col-md-4">
						    	<#-- 
						    	<@M_showImageForView imageList=itemSingle.coverList />
						    	 -->
						    	<@M_layout_imgCoverRollerH_single entity0=itemSingle idPre="layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
								 
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

<#macro M_coeArticleDetailPage detailPage=null moreLoaded="true" url="/sz0099/ood/article/manage/detail/" condition=""> 
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center">
	<#if detailList?? && detailList?size gt 0>
		<#list detailList as detail>
		  <a type="button" href="${url}${detail.id}" class="list-group-item <#if detail?is_even_item>list-group-item-warning</#if>">${detail.articleNo}-${detail.name} 
		  <span class="pull-right"><strong><#if detail.publishStatus==CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt>
		  <span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;">
		  </span><#else><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;">
		  </span>
		  </#if>
		  </strong></span>
		  </a>
		</#list>
	<#else>
	<a type="button" href="/sz0099/ood/article/manage/queryDraftList" class="list-group-item btn btn-info">没有发布文章，去完成草稿</a>
	</#if>
</div>
<div class="container">
  	<ul id="pageContainer"></ul>
</div>
</#if>
<p class="bg-warning">美文美篇会增加你的人气值...</p>
<p class="bg-warning">你的乐趣分享给大家，岂不乐哉！</p>
</#macro>

<#-- 管理页面 课程列表，执行局部修改，如：调整价格，链接，标题，上下架操作 -->
<#macro M_coeArticleManagePage page moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<!--文章列表开始--> 
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
      <#assign strategy=entity.strategy/>
		  <div class="panel panel-default">
			  <div class="panel-heading">${entity.articleNo} -- ${entity.name} <span class="pull-right"> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
			  	 
				  <div class="form-group form-group-sm">
				    <label for="id_name${entity.id}">名称(简短，少于20字符)</label>
				    <input type="text" id="id_name${entity.id}" name="name" value="${entity.name}" class="form-control" placeholder="文章名称">
				  </div>
			    <p>简介: ${getSubstring(entity.description,60,'暂无')}</p>
				<div class="form-group form-group-sm">
				    <label for="id_penname${entity.id}">绰号/笔名，若填写则优先显示此名</label>
				    <input type="text" id="id_penname${entity.id}" name="penname" 
				    value="${entity.penname}"
				    class="form-control" placeholder="绰号/笔名,优先显示">
				</div>
				 
				 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
				 <#-- <@M_dropdownBar id="id_" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false /> -->
				 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false />
				<div class="form-group form-group-sm">
				    <label for="id_title${entity.id}">主标题(少于30字)</label>
				    <input type="text" id="id_title${entity.id}" name="title" 
				    value="${entity.title}"
				    class="form-control" placeholder="主标题，少于30字">
				 </div>
				 <div class="form-group form-group-sm">
				    <label for="id_subTitle${entity.id}"><small>子标题，少于18字</small></label>
				    <input type="text" id="id_subTitle${entity.id}" name="subTitle" 
				    value="${entity.subTitle}"
				    class="form-control" placeholder="子标题，少于18字">
				 </div>
				
			     <p>
			     	<strong>发布状态:</strong> 
			      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
			      	${publishLabel}
			      </p>
			  	  <p>
			  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  	  </p>
			  	  <p>
			  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
			  	  </p>
			  	  <p id="id_messageTip_edit_quickly${entity.id}" class="text-center"> </p>
			  	  <p>
			  	  <a href="javascript:void(0)" onclick="editQuickly('${entity.id}')" type="button" class="btn btn-success btn-xs" id="id_btn_edit_quickly_${entity.id}">快速保存</a>
			  	  <a href="/sz0099/ood/article/manage/create?id=${entity.id}" type="button" class="btn btn-danger btn-xs pull-right" id="id_btn_edit_fully_${entity.id}">全面编辑</a>
			  	  </p>
			  </div>
			  <div class="panel-footer">
  				
  				<a href="javascript:void(0)" onclick="refreshArticle('${entity.id}')" class="btn btn-primary" role="button">刷新</a>
  				<a href="javascript:void(0)" onclick="mergeForClosed('${entity.id}')" class="btn btn-danger pull-right" role="button">关闭</a>
			  </div>
		 </div>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${page.number+1}&size=${page.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	  
 </#if>
 </#macro>
 




<#-- 选择文章 -->
<#macro M_selectArticlePage page moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
		  <div class="panel panel-default">
			  <div class="panel-heading">${entity.articleNo} -- ${entity.name} <span class="pull-right"> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
				  
			    <p><strong>笔名:</strong> ${entity.penname}</p>
				 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
			    <p><strong>引导语:</strong> ${currentPreIntro.label}</p>
			    <p><strong>标题:</strong> ${entity.title}</p>
			    <p><strong>子标题:</strong> ${entity.title}</p>
				 <p>
			     	<strong>发布状态:</strong> 
			      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
			      	${publishLabel}
			      </p>
			     
			  	  <p>
			  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  	  </p>
			  	  <p>
			  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
			  	  </p>
			  	  <p><strong>简介:</strong> ${getSubstring(entity.description,60,'暂无')}</p>
			  	  <p><strong>封面图:</strong><@M_showImageForSelect imageList=entity.coverList width="40px" /> </p>
			  	  <p><strong>头部图:</strong><@M_showImageForSelect imageList=entity.bannerList width="40px" /> </p>
			  </div>
			  <div class="panel-footer">
				  <p class="text-center">
				  <a id="id_btn_bind_${entity.id}" href="${url}?page=${page.number+1}&size=${page.size}&title=${condition.title}" class="btn btn-info btn-xs">添加关联</a>
				  <a id="id_btn_bind_un_${entity.id}" href="${url}?page=${page.number+1}&size=${page.size}&title=${condition.title}" class="btn btn-danger btn-xs">移除关联</a>
				  </p>
			  </div>
		 </div>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${page.number+1}&size=${page.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	  
 </#if>
 </#macro>
 
 
 
<#macro M_mainTypeList entity=null url="/sz0099/ood/article/findArticleForPraisePage/">
<#if entity!>
<div id="id_list_mainType">
	  <#assign entityList=entity.mainTypeList />
	  <#if entityList! && entityList??>
	  <#list entityList as entity0>
	  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
  		  <#assign author=entity0.author />
		  <div class="panel-body" sytle="margin-bottom:0px">
			  	<div class="row">
					  <div class="col-xs-7 col-md-7">
					    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
					  </div>
					  <div class="col-xs-5 col-md-5">
					  		<a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
						  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
							</a> 
					    <span class="pull-right"> 
					    <span class="label label-warning">出品于</span><br/>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;
					    </span>
					   </div>
				</div>
		  </div><#-- end body -->
	 </div><#-- end panel -->
  </#list>
  <#-- 用于详情页展示列表，始终显示，跳转至点赞用户列表页 -->
  		<div class="container">
		  	  <p class="text-right">
		  	  	  <a href="${url}${entity.id}" class="btn btn-warning btn-xs">more...</a>
			  </p>
		</div>
  </#if>
</div>
</#if>
</#macro>

<#-- 点赞用户最新文章分页列表 -->
<#macro M_mainTypeForPraisePage entity=null url="/sz0099/ood/article/findArticleForPraisePage/" showListLoadMore=false>
<#if entity!>
<#assign praisePage=entity.praisePage />
<#if praisePage!>
<#assign entityList=praisePage.content />
<div id="id_page_mainType">
	  <#if entityList! && entityList??>
	  <#list entityList as entityItem>
	  <#assign entity0=entityItem.article />
	  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
  		  <#-- praise里面包含用户头像信息 -->
  		  <#assign author=entityItem />
		  <div class="panel-body" sytle="margin-bottom:0px">
			  	<div class="row">
					  <div class="col-xs-8 col-md-8">
					    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
					  	<div class="container">
						  	<a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
							  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
								</a> 
						    <span class="pull-right"> 
						    <span class="bg-warning"><small>&nbsp;出品于&nbsp;</small></span>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;
						    </span>
					  	</div>
					  </div>
					  <div class="col-xs-4 col-md-4">
					  <@M_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_praise" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
					   </div>
				</div>
		  </div><#-- end body -->
	   </div><#-- end panel -->
       </#list>
       </#if>
  	    <!-- more -->
       
       <#if showListLoadMore && entityPage.hasNext() >
       <#-- 加载更多，一般不显示 -->
		<div class="container">
		  	  <p class="text-right">
		  	  	  <a href="${url}${entity.id}" class="btn btn-warning btn-xs">more...</a>
			  </p>
		</div>
	   </#if>
	   
	   <#-- 加装分页组件 -->
	   <div class="container">
	  	<ul id="pageContainer"></ul>
	   </div>
</div>
</#if>
</#if>
</#macro>



<#macro M_refreshPage entity=null showPage=false>
<#if entity!>
<#assign entityPage=entity.refreshPage />
<#if entityPage!>
<div id="id_list_refresh">
	  <#assign entityList=entityPage.content />
	  <#if entityList! && entityList??>
		  <#list entityList as entity0>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
	  		  <#assign author=entity0.author />
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-7 col-md-7">
						    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
						  </div>
						  <div class="col-xs-5 col-md-5">
						  		<a href='javascript:void(0)' onclick="showSayword('${entity0.nickname}','${entity0.sayword.description}')">
							  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
								</a> 
						    	<span class="pull-right"><span class="label label-success">出品于</span> <br/>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;</span>
						    </div>
					</div>
			  </div><#-- end body -->
		  </div><#-- end panel -->
	  	  </#list>
	  	  <#-- <#if entityPage.hasNext() > -->
	  	  <!-- more -->
		  <@M_articleCategoryForSearchHidden  entityPage=entityPage entity=entity url="/sz0099/ood/article/searchForCategoryFromDetail"/>
		 <#--  </#if> -->
		 <#-- 加装分页组件 -->
		   <#if showPage >
		   <div class="container">
		  	<ul id="pageContainer"></ul>
		   </div>
		   </#if> 
  	  </#if>
</div>
</#if>
</#if>
</#macro>
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_category_search.ftl">



<#macro M_coeArticleDraftList draftList=null url="/sz0099/ood/article/manage/create?id="> 
<br/>
<ul class="list-group text-center">
<#if draftList?? && draftList?size gt 0>
	<#list draftList as draft>
		<li class="list-group-item <#if draft?is_even_item>list-group-item-warning</#if>">
			<span class="pull-left"><strong><span onclick="mergeForDeletedDraft('${draft.id}')" class="glyphicon glyphicon-trash text-danger" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
		  <a href="${url}${draft.id}">
		  <span id="id_name${draft.id}">${draft.articleNo}-${draft.title}</span> 
		  <span class="pull-right"><strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
		  </a>
	  </li>
	</#list>
	<#if draftList?size lt 5>
		<li class="list-group-item btn btn-info">
	  	<a type="button" href="${url}">新建文章</a>
	  	</li>
	  </#if>
<#else>
<li class="list-group-item btn btn-info">
<a type="button" href="${url}">新建文章</a>
</li>
</#if>
</ul>
<hr/>
<div class="container">
<p class="bg-primary">1.好看的，好吃的，好玩的，都得分享！</p>
<p class="bg-warning">2.文章是你对外分享的第一表达方式.</p>
<p class="bg-primary">3.透过你的文章让朋友们认识你，熟知你...</p>
<p class="bg-warning">4.发表文章，有可能获得别人的打赏呢！</p>
<p class="bg-primary">5.文章配合技能，还能拓展你的服务哦！</p>
<p class="bg-warning">6.发布了文章，你的传说就留在江湖啦...</p>
<p class="bg-primary">7.文章被点赞的时候，也会留下大侠的传说呢...</p>
</div>
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
						    	<@Mg_layout_imgCoverRollerH_single entity0=itemSingle idPre="layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
								 
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

<#macro M_coeArticleDetailPage position="" detailPage=null moreLoaded="true" url="/sz0099/ood/article/manage/detail/" condition=""> 
<br/>
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center" id="id_data_list_article${position}">
	<#if detailList?? && detailList?size gt 0>
		<@Mg_article_editList contentList=detailList url=url/>
	<#else>
		<#if detailPage.totalPages lt 1>
		<a type="button" href="/sz0099/ood/article/manage/queryDraftList" class="list-group-item btn btn-info">没有发布文章，去完成草稿</a>
		</#if>
	</#if>
</div>

<div class="container">
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/article/manage/queryArticleList/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${detailPage.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${detailPage.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${detailPage.totalPages}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		initPageAnsyForArticleManage('${position}');
  	</script>
</div>
</#if>
<p class="bg-warning">美文美篇会增加你的人气值...</p>
<p class="bg-warning">分享乐趣给大家，岂不乐哉！</p>
</#macro>

<#macro M_searchArticlePage position="" detailPage=null moreLoaded="true" url="/sz0099/ood/article/manage/detail/" condition=""> 
<br/>
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center" id="id_data_list_article${position}">
	<#if detailList?? && detailList?size gt 0>
		<@Mg_article_editList contentList=detailList url=url/>
	<#else>
		没有搜索到文章
	</#if>
</div>

<div class="container">
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/article/manage/searchArticleList/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${detailPage.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${detailPage.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${detailPage.totalPages}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		initPageAnsySearchForArticleManage('${position}');
  	</script>
</div>
</#if>
</#macro>

<#macro Mg_article_editList contentList=null url=null>
<#list contentList as entity0>
  <div class="row">
		  <div class="col-xs-9 col-md-9">
		  	<div class="container">
			  	<a type="button" href="${url}${entity0.id}" class="list-group-item <#if entity0?is_even_item>list-group-item-warning</#if>">
				  ${entity0.articleNo}-${entity0.title} 
			    	  <p>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd HH:mm')} &nbsp;
					  <span class="pull-right">
					  <strong>
					  <#if entity0.publishStatus==CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt>
					  <span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;">
					  </span>
					  <#else>
					  <span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;">
					  </span>
					  </#if>
					  </strong>
					  </span>
					  <span class="pull-left"><strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
					  </p>
				  </a>
		  	</div>
		  </div>
		  <div class="col-xs-3 col-md-3">
		  <@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_manage_page" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
		   </div>
	</div>
</#list>
</#macro>


<#-- 管理页面 快速编辑，执行局部修改，如：调整价格，链接，标题，上下架操作 -->
<#macro M_coeArticleManagePage position="" page=null moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<!--文章列表开始--> 
<br/>
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
      <#assign strategy=entity.strategy/>
		  <div class="panel <#if entity?is_odd_item>panel-default<#else>panel-warning</#if>">
			  <div class="panel-heading">${entity.articleNo} -- ${entity.title} <span class="pull-right"> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
				  <#--
				  <div class="form-group form-group-sm">
				    <label for="id_name${entity.id}">名称(简短，少于32字符)
				    <small><span class="text-success" id="id_name${entity.id}_length">${HtmlUtil.countTextLength(entity.name)}</span>/32</small>
				    </label>
				    <input type="text" id="id_name${entity.id}" name="name" value="${entity.name}" class="form-control" 
				    onblur="showLength('id_name${entity.id}', 32, 'id_name${entity.id}_length')"
		    		onkeyup="showLength('id_name${entity.id}', 32, 'id_name${entity.id}_length')"
				    placeholder="文章名称，少于32字">
				  </div>
				  -->
			    <p>概要: ${getSubstring(entity.description,60,'暂无')}</p>
				<div class="form-group form-group-sm">
				    <label for="id_penname${entity.id}">绰号/笔名，若填写则优先显示此名
				    <small><span class="text-success" id="id_penname${entity.id}_length">${HtmlUtil.countTextLength(entity.penname)}</span>/12</small>
				    </label>
				    <input type="text" id="id_penname${entity.id}" name="penname" 
				    value="${entity.penname}"
				    onblur="showLength('id_penname${entity.id}', 12, 'id_penname${entity.id}_length')"
		    		onkeyup="showLength('id_penname${entity.id}', 12, 'id_penname${entity.id}_length')"
				    class="form-control" placeholder="绰号/笔名,优先显示">
				</div>
				 
				 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
				 <#-- <@M_dropdownBar id="id_" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false /> -->
				 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false />
				<div class="form-group form-group-sm">
				    <label for="id_title${entity.id}" class="text-danger">
				    	主标题(少于32字)★
				    <small><span class="text-success" id="id_title${entity.id}_length">${HtmlUtil.countTextLength(entity.title)}</span>/32</small>
				    </label>
				    <input type="text" id="id_title${entity.id}" name="title" 
				    value="${entity.title}"
				    onblur="showLength('id_title${entity.id}', 32, 'id_title${entity.id}_length')"
		    		onkeyup="showLength('id_title${entity.id}', 32, 'id_title${entity.id}_length')"
				    class="form-control" placeholder="主标题，少于32字">
				 </div>
				 <div class="form-group form-group-sm">
				    <label for="id_subTitle${entity.id}"><small>子标题，少于18字</small>
				    <small><span class="text-success" id="id_subTitle${entity.id}_length">${HtmlUtil.countTextLength(entity.subTitle)}</span>/18</small>
				    </label>
				    <input type="text" id="id_subTitle${entity.id}" name="subTitle" 
				    value="${entity.subTitle}"
				    onblur="showLength('id_title${entity.id}', 18, 'id_title${entity.id}_length')"
		    		onkeyup="showLength('id_title${entity.id}', 18, 'id_title${entity.id}_length')"
				    class="form-control" placeholder="子标题，少于18字">
				 </div>
				
			     <p>
			     	<strong>发布状态:</strong> 
			      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
			      	<span class="label label-<#if CoeArticle.PUBLISH_STATUS_PUBLISH.valueInt==entity.publishStatus>success<#elseif CoeArticle.PUBLISH_STATUS_DRAFT.valueInt>warning<#else>danger</#if>">${publishLabel}</span>
			      </p>
			  	  <p>
			  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  	  </p>
			  	  <p>
			  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
			  	  <a href="javascript:void(0)" onclick="refreshArticle('${entity.id}')" class="btn btn-info btn-xs" role="button">刷新</a>
			  	  </p>
			  	  <p id="id_messageTip_edit_quickly${entity.id}" class="text-center"> </p>
			  	  <p>
			  	 		  	  </p>
			  </div>
			  <div class="panel-footer">
  				 <a href="javascript:void(0)" onclick="editQuickly('${entity.id}')" type="button" class="btn btn-success btn-xs" id="id_btn_edit_quickly_${entity.id}">快速保存</a>
			  	 <a href="/sz0099/ood/article/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_btn_edit_fully_${entity.id}">全面编辑</a>
	
  				
  				<@shiro.hasRole name="plat_creator">
  				<a href="javascript:void(0)" onclick="mergeForClosed('${entity.id}')" class="btn btn-danger btn-xs pull-right" role="button">关闭</a>
  				</@shiro.hasRole>
			  </div>
		 </div>
		 <br/>
  	  </#list>
	  
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/article/manage/queryArticleManageList/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${page.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${page.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${page.totalPages}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		initPageAnsyForArticleManage('${position}');
  	</script>
	  
 </#if>
 </#macro>
 




<#-- 选择文章 -->
<#macro M_selectArticlePage page moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
		  <div class="panel panel-default">
			  <div class="panel-heading">${entity.articleNo} -- ${entity.title} <span class="pull-right"> <strong>#${entity?index}</strong></span>
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
					  <@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_praise" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
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
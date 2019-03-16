<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">


<#macro Mg_recommendPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/recommend/ansy" condition="">   
<!--列表开始--> 
<!--推荐文章-->
<div id="id_page_wrapper_index_recommend">
	  <@Mg_recommendPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_recommend" name="loaded" type="hidden" value="0" data-index="0"/>
			  <input id="id_page_url_index_recommend" type="hidden" name="url" value="/sz0099/ood/home/article/index/recommend/more/ansy"/>
			  <input id="id_page_currentPage_index_recommend" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_recommend" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_recommend" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_recommend_index_recommend" type="hidden" name="recommend" value="1"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexRecommend('_index_recommend')" class="btn btn-warning btn-xs">加载更多...</a>
		  	  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_recommendPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as article>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_recommend_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_flagPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/flag/ansy" condition=null>   
<!--旗帜文章-->
<div id="id_page_wrapper_index_flag">
	  <@Mg_flagPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_flag" name="loaded" type="hidden" value="0" data-index="1"/>
			  <input id="id_page_url_index_flag" type="hidden" name="url" value="/sz0099/ood/home/article/index/flag/more/ansy"/>
			  <input id="id_page_currentPage_index_flag" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_flag" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_flag" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_flag" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexFlag('_index_flag')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_flagPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  		  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_flag_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_footPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/foot/ansy" condition="">   
<!--列表开始--> 
<!--徒步文章-->
<div id="id_page_wrapper_index_foot">
	  <@Mg_footPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_foot" name="loaded" type="hidden" value="0" data-index="2"/>
			  <input id="id_page_url_index_foot" type="hidden" name="url" value="/sz0099/ood/home/article/index/foot/more/ansy"/>
			  <input id="id_page_currentPage_index_foot" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_foot" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_foot" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_foot" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexFoot('_index_foot')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_footPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_foot_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_travelPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/travel/ansy" condition="">   
<!--列表开始--> 
<!--旅行文章-->
<div id="id_page_wrapper_index_travel">
	  <@Mg_travelPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_travel" name="loaded" type="hidden" value="0" data-index="3"/>
			  <input id="id_page_url_index_travel" type="hidden" name="url" value="/sz0099/ood/home/article/index/travel/more/ansy"/>
			  <input id="id_page_currentPage_index_travel" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_travel" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_travel" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_travel" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexTravel('_index_travel')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_travelPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_travel_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_bikePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/bike/ansy" condition="">   
<!--列表开始--> 
<!--骑行文章-->
<div id="id_page_wrapper_index_bike">
	  <@Mg_bikePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_bike" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_bike" type="hidden" name="url" value="/sz0099/ood/home/article/index/bike/more/ansy"/>
			  <input id="id_page_currentPage_index_bike" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_bike" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_bike" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_bike" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexBike('_index_bike')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_bikePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_bike_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_roadlinePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/roadline/ansy" condition="">   
<!--列表开始--> 
<!--线路文章-->
<div id="id_page_wrapper_index_roadline">
	  <@Mg_bikePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_roadline" name="loaded" type="hidden" value="0" data-index="5"/>
			  <input id="id_page_url_index_roadline" type="hidden" name="url" value="/sz0099/ood/home/article/index/roadline/more/ansy"/>
			  <input id="id_page_currentPage_index_roadline" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_roadline" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_roadline" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_roadline" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexRoadline('_index_roadline')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_roadlinePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_roadline_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_welfarePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/welfare/ansy" condition="">   
<!--列表开始--> 
<!--公益文章-->
<div id="id_page_wrapper_index_welfare">
	  <@Mg_welfarePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_welfare" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_welfare" type="hidden" name="url" value="/sz0099/ood/home/article/index/welfare/more/ansy"/>
			  <input id="id_page_currentPage_index_welfare" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_welfare" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_welfare" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_welfare" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexWelfare('_index_welfare')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_welfarePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_welfare_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_equipPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/equip/ansy" condition="">   
<!--列表开始--> 
<!--公益文章-->
<div id="id_page_wrapper_index_equip">
	  <@Mg_equipPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_equip" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_equip" type="hidden" name="url" value="/sz0099/ood/home/article/index/equip/more/ansy"/>
			  <input id="id_page_currentPage_index_equip" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_equip" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_equip" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_equip" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexEquip('_index_equip')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_equipPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_equip_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>

<#macro Mg_eatPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/article/index/eat/ansy" condition="">   
<!--列表开始--> 
<!--美食文章-->
<div id="id_page_wrapper_index_eat">
	  <@Mg_eatPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_eat" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_eat" type="hidden" name="url" value="/sz0099/ood/home/article/index/eat/more/ansy"/>
			  <input id="id_page_currentPage_index_eat" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_eat" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_eat" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_eat" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexEat('_index_eat')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_eatPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign article=entity.article />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=article.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${article.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${article.id}' class='img-circle'  width='25px' height='25px' src='${article.headImg}' alt='${article.nickname}' title='${article.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${article.nickname}','${article.sayword.description}', '@${DateUtils.formatToString(article.refreshTime ,'yyyy-MM-dd')}')">
			     ${article.nickname}
			     </a> @ ${DateUtils.formatToString(article.publishTime ,'yyyy-MM-dd')} &nbsp;#${article.articleNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(article, "/")}">${article.title}</a></strong></h5>
						    <p>${getSubstring(article.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=article idPre="index_eat_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>
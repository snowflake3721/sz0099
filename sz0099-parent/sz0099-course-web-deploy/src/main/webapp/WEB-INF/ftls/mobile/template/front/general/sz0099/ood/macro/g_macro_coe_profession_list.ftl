<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">


<#macro Mgp_recommendPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/recommend/ansy" condition="">   
<!--列表开始--> 
<!--推荐文章-->
<div id="id_page_wrapper_index_recommend">
	  <@Mgp_recommendPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_recommend" name="loaded" type="hidden" value="0" data-index="0"/>
			  <input id="id_page_url_index_recommend" type="hidden" name="url" value="/sz0099/ood/home/profession/index/recommend/more/ansy"/>
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
<#macro Mgp_recommendPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as profession>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-success">出品于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_recommend_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mgp_majorPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/major/ansy" condition=null>   
<!--旗帜文章-->
<div id="id_page_wrapper_index_major">
	  <@Mgp_majorPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_major" name="loaded" type="hidden" value="0" data-index="1"/>
			  <input id="id_page_url_index_major" type="hidden" name="url" value="/sz0099/ood/home/profession/index/major/more/ansy"/>
			  <input id="id_page_currentPage_index_major" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_major" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_major" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_major" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexMajor('_index_major')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_majorPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  		  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_major_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mgp_outdoorPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/outdoor/ansy" condition="">   
<!--列表开始--> 
<!--徒步文章-->
<div id="id_page_wrapper_index_outdoor">
	  <@Mgp_outdoorPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_outdoor" name="loaded" type="hidden" value="0" data-index="2"/>
			  <input id="id_page_url_index_outdoor" type="hidden" name="url" value="/sz0099/ood/home/profession/index/outdoor/more/ansy"/>
			  <input id="id_page_currentPage_index_outdoor" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_outdoor" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_outdoor" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_outdoor" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexOutdoor('_index_outdoor')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_outdoorPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_outdoor_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mgp_lifePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/life/ansy" condition="">   
<!--列表开始--> 
<!--旅行文章-->
<div id="id_page_wrapper_index_life">
	  <@Mgp_lifePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_life" name="loaded" type="hidden" value="0" data-index="3"/>
			  <input id="id_page_url_index_life" type="hidden" name="url" value="/sz0099/ood/home/profession/index/life/more/ansy"/>
			  <input id="id_page_currentPage_index_life" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_life" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_life" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_life" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexLife('_index_life')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_lifePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_travel_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mgp_ingenuityPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/ingenuity/ansy" condition="">   
<!--列表开始--> 
<!--骑行文章-->
<div id="id_page_wrapper_index_ingenuity">
	  <@Mgp_ingenuityPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_ingenuity" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_ingenuity" type="hidden" name="url" value="/sz0099/ood/home/profession/index/ingenuity/more/ansy"/>
			  <input id="id_page_currentPage_index_ingenuity" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_ingenuity" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_ingenuity" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_ingenuity" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexIngenuity('_index_ingenuity')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_ingenuityPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_ingenuity_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mgp_relaxPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/relax/ansy" condition="">   
<!--列表开始--> 
<!--线路文章-->
<div id="id_page_wrapper_index_relax">
	  <@Mgp_relaxPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_relax" name="loaded" type="hidden" value="0" data-index="5"/>
			  <input id="id_page_url_index_relax" type="hidden" name="url" value="/sz0099/ood/home/profession/index/roadline/more/ansy"/>
			  <input id="id_page_currentPage_index_relax" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_relax" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_roadline" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_relax" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexRelax('_index_relax')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_relaxPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_roadline_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mgp_nativePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/native/ansy" condition="">   
<!--列表开始--> 
<!--公益文章-->
<div id="id_page_wrapper_index_native">
	  <@Mgp_nativePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_native" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_native" type="hidden" name="url" value="/sz0099/ood/home/profession/index/native/more/ansy"/>
			  <input id="id_page_currentPage_index_native" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_native" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_native" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_native" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexNative('_index_native')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_nativePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_welfare_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mgp_equipPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/equip/ansy" condition="">   
<!--列表开始--> 
<div id="id_page_wrapper_index_equip">
	  <@Mgp_equipPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_equip" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_equip" type="hidden" name="url" value="/sz0099/ood/home/profession/index/equip/more/ansy"/>
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
<#macro Mgp_equipPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_equip_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mgp_factoryPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/profession/index/factory/ansy" condition="">   
<!--列表开始--> 
<div id="id_page_wrapper_index_factory">
	  <@Mgp_factoryPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_factory" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_factory" type="hidden" name="url" value="/sz0099/ood/home/profession/index/factory/more/ansy"/>
			  <input id="id_page_currentPage_index_factory" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_factory" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_factory" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_factory" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexFactory('_index_factory')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mgp_factoryPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign profession=entity.profession />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=profession.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/personal/profession/findByUserId','${profession.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${profession.id}' class='img-circle'  width='25px' height='25px' src='${profession.headImg}' alt='${profession.nickname}' title='${profession.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${profession.nickname}','${profession.sayword.description}', '@${DateUtils.formatToString(profession.refreshTime ,'yyyy-MM-dd')}')">
			     ${profession.nickname}
			     </a> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(profession.publishTime ,'yyyy-MM-dd')} &nbsp;#${profession.professionNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-8 col-md-8">
						    <h5><strong><a type="button" class="text-left" href="${getLink(profession, "/")}">${profession.title}</a></strong></h5>
						    <p>${getSubstring(profession.description,20,'暂无')}</p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=profession idPre="index_equip_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>
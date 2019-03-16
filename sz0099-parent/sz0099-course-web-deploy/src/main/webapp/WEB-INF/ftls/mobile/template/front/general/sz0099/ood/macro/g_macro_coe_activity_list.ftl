<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">


<#macro Mg_recommendPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/recommend/ansy" condition="">   
<!--列表开始--> 
<!--推荐活动-->
<div id="id_page_wrapper_index_recommend">
	  <@Mg_recommendPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_recommend" name="loaded" type="hidden" value="0" data-index="0"/>
			  <input id="id_page_url_index_recommend" type="hidden" name="url" value="/sz0099/ood/home/activity/index/recommend/more/ansy"/>
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
      <#list itemList as activity>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_flagPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/flag/ansy" condition=null>   
<!--旗帜活动-->
<div id="id_page_wrapper_index_flag">
	  <@Mg_flagPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_flag" name="loaded" type="hidden" value="0" data-index="1"/>
			  <input id="id_page_url_index_flag" type="hidden" name="url" value="/sz0099/ood/home/activity/index/flag/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>

<#macro Mg_joinPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/foot/ansy" condition="">   
<!--集合活动开始--> 
<div id="id_page_wrapper_index_foot">
	  <@Mg_joinPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_join" name="loaded" type="hidden" value="0" data-index="2"/>
			  <input id="id_page_url_index_join" type="hidden" name="url" value="/sz0099/ood/home/activity/index/all/more/ansy"/>
			  <input id="id_page_currentPage_index_join" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_join" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_join" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_join" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexJoin('_index_join')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
		  	  	  
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_joinPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign activity=entity.activity />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading text-left">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a>
				
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> <#-- <span class="label label-success"> -->@ <#-- </span> --> ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>

<#macro Mg_footPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/foot/ansy" condition="">   
<!--列表开始--> 
<!--徒步活动-->
<div id="id_page_wrapper_index_foot">
	  <@Mg_footPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_foot" name="loaded" type="hidden" value="0" data-index="2"/>
			  <input id="id_page_url_index_foot" type="hidden" name="url" value="/sz0099/ood/home/activity/index/foot/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_travelPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/travel/ansy" condition="">   
<!--列表开始--> 
<!--旅行活动-->
<div id="id_page_wrapper_index_travel">
	  <@Mg_travelPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_travel" name="loaded" type="hidden" value="0" data-index="3"/>
			  <input id="id_page_url_index_travel" type="hidden" name="url" value="/sz0099/ood/home/activity/index/travel/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_bikePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/bike/ansy" condition="">   
<!--列表开始--> 
<!--骑行活动-->
<div id="id_page_wrapper_index_bike">
	  <@Mg_bikePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_bike" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_bike" type="hidden" name="url" value="/sz0099/ood/home/activity/index/bike/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_carPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/car/ansy" condition="">   
<!--列表开始--> 
<!--自驾活动-->
<div id="id_page_wrapper_index_car">
	  <@Mg_carPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_car" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_car" type="hidden" name="url" value="/sz0099/ood/home/activity/index/car/more/ansy"/>
			  <input id="id_page_currentPage_index_car" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_car" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_car" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_car" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexCar('_index_car')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_carPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_freePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/free/ansy" condition="">   
<!--列表开始--> 
<!--自由行活动-->
<div id="id_page_wrapper_index_free">
	  <@Mg_carPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_free" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_free" type="hidden" name="url" value="/sz0099/ood/home/activity/index/free/more/ansy"/>
			  <input id="id_page_currentPage_index_free" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_free" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_free" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_free" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexFree('_index_free')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_freePage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_eatPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/eat/ansy" condition="">   
<!--列表开始--> 
<!--聚餐活动-->
<div id="id_page_wrapper_index_eat">
	  <@Mg_carPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_eat" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_eat" type="hidden" name="url" value="/sz0099/ood/home/activity/index/eat/more/ansy"/>
			  <input id="id_page_currentPage_index_eat" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_eat" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_eat" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_eat" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexFree('_index_eat')" class="btn btn-warning btn-xs">加载更多...</a>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_pickPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/pick/ansy" condition="">   
<!--列表开始--> 
<!--采摘活动-->
<div id="id_page_wrapper_index_pick">
	  <@Mg_pickPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_pick" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_pick" type="hidden" name="url" value="/sz0099/ood/home/activity/index/pick/more/ansy"/>
			  <input id="id_page_currentPage_index_pick" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_pick" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_pick" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_pick" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexPick('_index_eat')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_pickPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>

<#macro Mg_volunteerPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/pick/ansy" condition="">   
<!--列表开始--> 
<!--采摘活动-->
<div id="id_page_wrapper_index_pick">
	  <@Mg_volunteerPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_pick" name="loaded" type="hidden" value="0" data-index="4"/>
			  <input id="id_page_url_index_pick" type="hidden" name="url" value="/sz0099/ood/home/activity/index/pick/more/ansy"/>
			  <input id="id_page_currentPage_index_pick" type="hidden" name="page" value="${itemPage.number}"/>
			  <input id="id_page_size_index_pick" type="hidden" name="size" value="${itemPage.size}"/>
			  <input id="id_page_totalPages_index_pick" type="hidden" name="totalPages" value="${itemPage.totalPages}"/>
			  <input id="id_data_category_code_index_pick" type="hidden" name="category.code" value="${condition.category.code}"/>
		  	  <p class="text-right">
		  	  	<#if itemPage.totalPages gt 1>
		  	  	  <a href="javascript:void(0)" onclick="searchForIndexPick('_index_eat')" class="btn btn-warning btn-xs">加载更多...</a>
			  	<#else>
		  	  	◎◎◎已全部加载◎◎◎
		  	  	</#if>
			  </p>
		</div>
  </#if>
</#macro>
<#macro Mg_volunteerPage itemPage=null>
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as entity>
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>

<#macro Mg_roadlinePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/roadline/ansy" condition="">   
<!--列表开始--> 
<!--线路活动-->
<div id="id_page_wrapper_index_roadline">
	  <@Mg_bikePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_roadline" name="loaded" type="hidden" value="0" data-index="5"/>
			  <input id="id_page_url_index_roadline" type="hidden" name="url" value="/sz0099/ood/home/activity/index/roadline/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>



<#macro Mg_welfarePageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/welfare/ansy" condition="">   
<!--列表开始--> 
<!--公益活动-->
<div id="id_page_wrapper_index_welfare">
	  <@Mg_welfarePage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_welfare" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_welfare" type="hidden" name="url" value="/sz0099/ood/home/activity/index/welfare/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>


<#macro Mg_equipPageWrapper itemPage moreLoaded="true" url="/sz0099/ood/home/activity/index/equip/ansy" condition="">   
<!--列表开始--> 
<!--公益活动-->
<div id="id_page_wrapper_index_equip">
	  <@Mg_equipPage itemPage=itemPage />
</div>
<!-- 下一组 -->
  <#if moreLoaded=="true">
		<div class="container" style="margin-bottom: 20px">
			  <input id="id_page_loaded_index_equip" name="loaded" type="hidden" value="0" data-index="6"/>
			  <input id="id_page_url_index_equip" type="hidden" name="url" value="/sz0099/ood/home/activity/index/equip/more/ansy"/>
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
      <#assign activity=entity.activity />
		  <div class="panel panel-success" sytle="margin-bottom:0px;padding-bottom:0px">
			  <div class="panel-heading">
	  			<#assign author=activity.author />
			  	<a href='javascript:void(0)' onclick="changeBloomingForJson('/sz0099/ood/activity/findByUserId','${activity.userId}','id_current_userId')"
			  	>
				  <img id='id_tpl_media_headImg${activity.id}' class='img-circle'  width='25px' height='25px' src='${activity.headImg}' alt='${activity.nickname}' title='${activity.sayword.description}'>
				</a> 
			    <span class="pull-right">
			    <small>
			    <strong>
			    <a href='javascript:void(0)' onclick="showSaywordHistory('${activity.nickname}','${activity.sayword.description}', '@${DateUtils.formatToString(activity.refreshTime ,'yyyy-MM-dd')}')">
			     ${activity.nickname}
			     </a> @ ${DateUtils.formatToString(activity.publishTime ,'yyyy-MM-dd')} &nbsp;#${activity.activityNo}
			     </strong>
			     </small>
			     </span>
			  </div>
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
				  	<#assign actTime=activity.actTime/>
				  	<#assign actFee=activity.actFee/>
						  <div class="col-xs-8 col-md-8">
						    <h5>
						    <strong>
						    <a type="button" class="text-left" href="${getLink(activity, "/")}">${activity.title}</a>
						    </strong>
						    </h5>
						    <p>
						    <small>${getSubstring(activity.description,20,'暂无')}</small>
						    <span class="label label-success">${CoeActivity.ACT_STATUS.getLabel(activity.actStatus)}</span>
						    </p>
						  </div>
						  <div class="col-xs-4 col-md-4">
						    	<@Mg_layout_imgCoverRollerH_single entity0=activity idPre="index_join_layout_list" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false/>
						  </div>
						  
					  		<div class="col-xs-12">
							    <h5 class="text-center">
								<#if actFee!>
								    <#if actFee.feeType!>
								    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
								    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>
								    	&nbsp;&nbsp;
								    </#if>
							     </#if>
							     <#if actFee!>
									    <#if actFee.feeType!>
									    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
									    	<strong>人均金额：<span class="bg-danger">
									    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
										    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
										    	<#else>
										    	待定
									    	</#if>
									    	</span>
									    	</strong>
									    </#if>
								  </#if>
						    	  </h5>
								    <h5 class="text-center">
										  <#assign kilometer=activity.kilometer/>
										  <#if kilometer! && kilometer gt 0 >
										  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
										  &nbsp;&nbsp;
										  </#if>
										  <#assign difficulty=activity.difficulty/>
										  <#if difficulty! && difficulty gt 0 >
										  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
										  &nbsp;&nbsp;
										  </#if>
										  
										  <span class="label label-info">成行人数: &nbsp; ${activity.minNum}-${activity.maxNum}</span>
										  
									  </h5>
							  </div>
						  
						  <#if actTime!>
								  
								  <div class="col-xs-12 text-center">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5 class="text-center">
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==activity.actStatus >
										<a class="btn btn-xs btn-danger" href="${getLink(activity, "/")}">我要报名</a>
										</#if>
								    </h5>
								  </div>
						</#if>
						  
					</div>
			  </div><#-- end body -->
		 </div>
  	  </#list>
 </#if>
</#macro>
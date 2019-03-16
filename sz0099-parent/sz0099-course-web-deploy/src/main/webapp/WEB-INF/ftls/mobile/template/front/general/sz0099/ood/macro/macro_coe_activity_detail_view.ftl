<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_image_roller.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_praise_list.ftl">
<#include "mobile/template/front/general/macro/macro_userRole_list.ftl">

<#-- 活动详情，活动信息与段落分开查询，个人编辑用浏览 -->
<#macro Mg_coeActivityDetail entity paragraphPage=null moreLoaded="true">   
<#if entity!>
		 <div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		 ${entity.activityNo} -- ${entity.title} <span class="pull-right"> </span>
		 
		 </div>
		  <div class="panel-body">
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#assign author=entity.author/>
			  	<h4 class="text-center"> <strong>${HtmlUtils.htmlUnescape(entity.title)} </strong></h4>
			  		<#assign subTitle=entity.subTitle/>
				  	<#if subTitle!>
				  	<p class="text-right"> <small>${subTitle} &nbsp;&nbsp;</small></p>
				  	</#if>
			  		<p class="text-center"> 
					  	<small>[<@M_userRoleClubLeader userRole=userRole entity=entity/>] 
					  	<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${ShowNameUtil.getShowname(entity.penname, entity.nickname, '大侠无名')}</a> 
					  	</small>
					  	<small>
					  	 &nbsp;@ ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
				  	 	</small>
				  	</p>
				  	
				  	<div class="alert alert-warning text-center" role="alert">
					  	<#assign actTime=entity.actTime/>
					  	<#assign actFee=entity.actFee/>
					  	<div class="row">
						  	<#if actFee!>
						  		<div class="col-xs-12">
							    <#if actFee.feeType!>
							    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
							    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>&nbsp;&nbsp; 
							    	
							    	<strong>报名费：
							    	<span class="bg-danger">
							    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
								    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
								    	<#else>
								    	待定
							    	</#if>
							    	</span>
							    	</strong>
							    	<#if actFee.rmbAmountOri! && actFee.rmbAmountOri gt 0>
							    		<span><small><del>原价：${AmountUtil.changeF2Y(actFee.rmbAmountOri)}￥</del></small></span>
							    	</#if>
							    </#if>
							    </div>
						    </#if>
							
							<#if actTime!>
								  <div class="col-xs-12">
								  <h5>
									  <#assign kilometer=entity.kilometer/>
									  <#if kilometer! && kilometer gt 0 >
									  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
									  &nbsp;&nbsp;
									  </#if>
									  <#assign difficulty=entity.difficulty/>
									  <#if difficulty! && difficulty gt 0 >
									  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
									  &nbsp;&nbsp;
									  </#if>
									  
									  <span class="label label-info">成行人数: &nbsp; ${entity.minNum}-${entity.maxNum}</span>
								  </h5>
								  </div>
								  <div class="col-xs-12">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5>
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
									    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==entity.actStatus >
										    <#if status >
												<a class="btn btn-xs btn-info" href="javascript:void(0)">已报名</a>
											<#else>
												<a class="btn btn-xs btn-danger" href="/sz0099/ood/activity/order/manage/addUI?mainId=${entity.id}">立即报名</a>
											</#if>
										</#if>
								     </h5>
								  </div>
							</#if>
					  	</div><#-- end row -->
				  	</div><#-- end alert -->
				  	<div>
					   <small><strong>费用说明 : </strong> </small>
					   <h5>${HtmlUtil.textarea2UnEscapeForHtml(entity.actFee.description)}</h5>
					   <br/>
				   </div>
				   <#if userPage! && userPage.totalElements gt 0 >
				   			<#assign userList=userPage.content />
						   <#if userList!?size gt 0 >
					   		<div class="panel panel-default">
					   			  <div class="panel-heading">报名列表 ( ${userPage.totalElements}/${entity.maxNum} )</div>
								  <div class="panel-body">
								    	<table class="table table-condensed">
							   			<#list userList as user>
									  		<#assign isOwner=UserUtils.getUserId()==user.userId />
												<tr>
												  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.realname, 1, 0)}</td>
												  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.mobile, 3, 1)}</td>
												  <td class="<#if isOwner>danger</#if>">${Order.ORDER_STATUS.getLabel(user.status)}</td>
												</tr>
							   			</#list>
										</table>
								  </div>
							</div>
					   		</#if>
			   		</#if>
			  	
			  	<#if entity.activityTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.activityTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
			  	</#if>
			  	
			    <blockquote>
			    ${HtmlUtil.textarea2Html(entity.description)}
			    </blockquote>
			    
			  	<@Mg_activityReward id="id_activityReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
  			
	  
			  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
		  	  
		  	  <p class="text-center"><div class="alert alert-success text-center" role="alert">
			    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
			    	data-url="/sz0099/ood/activity/praise" 
			    	data-saveUrl="/sz0099/ood/activity/doPraise"
			    	data-refreshUrl="/sz0099/ood/activity/refreshPraise"
			    	/>
			    	
			    	<a href="javascript:void(0)" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
			  		&nbsp; &nbsp; 
			  		<a href="javascript:void(0)" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
			  		&nbsp; &nbsp; 
			  		<a href="javascript:void(0)" class="btn btn-lg btn-info" id="id_btn_favirate_${entity.id}">收</a>
			  		<hr/>
			  		</div>
			  	</p>
		  	  <br/>
		  	  <div class="container text-center bg-success"><small>◎◎◎※※※※※※ 本文结束 ※※※※※※◎◎◎</small></div>
		  	  <br/>
  	  		</div><!--end panel-body-->
		    
	  </div><!--end panel-->
 </#if>
 </#macro>
 
 
<#macro Mg_actShortDetailView entity=null >
<#if entity!>
 				<h4 class="text-center">
			  	<strong>${HtmlUtils.htmlUnescape(entity.title)} </strong>
			  	<small>[${CoeActivity.ACT_STATUS.getLabel(entity.actStatus)}]</small>
			  	</h4>
 				<div class="alert alert-warning text-center" role="alert">
				  	<#assign actTime=entity.actTime/>
				  	<#assign actFee=entity.actFee/>
				  	<div class="row">
					  	<#if actFee!>
					  		<div class="col-xs-12">
						    <#if actFee.feeType!>
						    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
						    	<span class="bg-warning">缴费方式：</span>${feeLabel} 
						    	&nbsp;&nbsp;
						    	<span class="bg-danger"><strong>报名费：
						    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
							    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
							    	<#else>
							    	待定
						    	</#if>
						    	</strong></span>
						    	&nbsp;&nbsp;
						    	<#if actFee.rmbAmountOri! && actFee.rmbAmountOri gt 0>
						    		<span class="bg-info">
						    		<small>
						    		<del>原价：${AmountUtil.changeF2Y(actFee.rmbAmountOri)}￥
						    		</del>
						    		</small>
						    		</span>
						    	</#if>
						    </#if>
						    </div>
					    </#if>
						
						<#if actTime!>
								  <div class="col-xs-12">
								  <h5>
									  <#assign kilometer=entity.kilometer/>
									  <#if kilometer! && kilometer gt 0 >
									  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
									  &nbsp;&nbsp;
									  </#if>
									  <#assign difficulty=entity.difficulty/>
									  <#if difficulty! && difficulty gt 0 >
									  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
									  &nbsp;&nbsp;
									  </#if>
								  </h5>
								  </div>
								  <div class="col-xs-12">
								  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
								  </div>
								  <div class="col-xs-12">
								  	<h5>
									    <small>
									    活动时间：
									    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
									    至
									    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
									    </small>
								    </h5>
								  </div>
						</#if>
				  	</div><#-- end row -->
			  	</div><#-- end alert -->
</#if>
</#macro>
 
 <#-- 活动详情，活动信息与段落分开查询,用户浏览页 -->
<#macro Mg_coeActivityDetailView entity paragraphPage=null moreLoaded="true">   
<#if entity!>
		 
		  <#assign author=entity.author/>
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
			  	<h3 class="text-center">
			  	<strong>${HtmlUtils.htmlUnescape(entity.title)} </strong>
			  	<small>[${CoeActivity.ACT_STATUS.getLabel(entity.actStatus)}]</small>
			  	</h3>
			  	<p class="text-right"> ${HtmlUtils.htmlUnescape(entity.subTitle)}  &nbsp; &nbsp;
			  	<#assign currentUserId=UserUtils.getUserId()/>
			  	<#if currentUserId! && currentUserId==entity.userId>
			  	<a href="/sz0099/ood/activity/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_btn_edit_fully_${entity.id}">编辑</a>
			  	</#if>
			  	</p>
			  	<p class="text-center"> 
			  	<small><@M_userRoleClubLeader userRole=userRole entity=entity/>  
			  	◎ <a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${ShowNameUtil.getShowname(entity.penname, entity.nickname, '大侠无名')}</a> 
			  	</small>
			  	<small>
			  	 &nbsp;@ ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
		  	 	</small>
			  	</p>
			  	
			  	<div class="alert alert-warning text-center" role="alert">
				  	<#assign actTime=entity.actTime/>
				  	<#assign actFee=entity.actFee/>
				  	<div class="row">
					  	<#if actFee!>
					  		<div class="col-xs-12">
						    <#if actFee.feeType!>
						    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
						    	<span class="bg-warning">缴费方式：</span>${feeLabel} 
						    	&nbsp;&nbsp;
						    	<span class="bg-danger"><strong>报名费：
						    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
							    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
							    	<#else>
							    	待定
						    	</#if>
						    	</strong></span>
						    	&nbsp;&nbsp;
						    	<#if actFee.rmbAmountOri! && actFee.rmbAmountOri gt 0>
						    		<span class="bg-info">
						    		<small>
						    		<del>原价：${AmountUtil.changeF2Y(actFee.rmbAmountOri)}￥
						    		</del>
						    		</small>
						    		</span>
						    	</#if>
						    </#if>
						    </div>
					    </#if>
						
						<#if actTime!>
							  <div class="col-xs-12">
							  <h5>
								  <#assign kilometer=entity.kilometer/>
								  <#if kilometer! && kilometer gt 0 >
								  <span class="label label-info">里程: &nbsp; ${kilometer} km</span>
								  &nbsp;&nbsp;
								  </#if>
								  <#assign difficulty=entity.difficulty/>
								  <#if difficulty! && difficulty gt 0 >
								  <span class="label label-info">难度系数: &nbsp; ${difficulty}%</span>
								  &nbsp;&nbsp;
								  </#if>
								  
								  <span class="label label-info">成行人数: &nbsp; ${entity.minNum}-${entity.maxNum}</span>
							  </h5>
							  </div>
							  <div class="col-xs-12">
							  <span class="label label-warning">报名截止: ${DateUtils.formatToString(actTime.offTime ,'yyyy-MM-dd HH:mm')}</span>
							  </div>
							  <div class="col-xs-12">
							  	<h5>
								    <small>
								    活动时间：
								    ${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
								    至
								    ${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
								    </small>
								    <#if CoeActivity.ACT_STATUS_CALLING.valueInt==entity.actStatus >
											<#if status >
												<a class="btn btn-xs btn-info" href="javascript:void(0)">已报名</a>
											<#else>
												<a class="btn btn-xs btn-danger" href="/sz0099/ood/activity/order/manage/addUI?mainId=${entity.id}">立即报名</a>
											</#if>
									</#if>
							    </h5>
							  </div>
						</#if>
				  	</div><#-- end row -->
			  	</div><#-- end alert -->
			  	<div>
				   <small><strong>费用说明 : </strong> </small>
				   <h5>${HtmlUtil.textarea2UnEscapeForHtml(entity.actFee.description)}</h5>
				   <br/>
			   </div>
			   
		   		<#if userList!?size gt 0 >
		   		<div class="panel panel-default">
		   			  <div class="panel-heading">报名列表 ( ${userList?size}/${entity.maxNum} )</div>
					  <div class="panel-body">
					    	<table class="table table-condensed">
				   			<#list userList as user>
						  		<#assign isOwner=UserUtils.getUserId()==user.userId />
									<tr>
									  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.realname, 1, 0)}</td>
									  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.mobile, 3, 1)}</td>
									  <td class="<#if isOwner>danger</#if>">${Order.ORDER_STATUS.getLabel(user.status)}</td>
									</tr>
				   			</#list>
							</table>
					  </div>
				</div>
		   		</#if>
			  	
			  	<#if entity.activityTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.activityTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
			  	</#if>
			  	<blockquote>
			   	${HtmlUtil.textarea2Html(entity.description)}
			    </blockquote>
			    
			    <#if entity.praisePage! && entity.praisePage.totalPages gt 0>
			    <p class="text-center">
			    <div class="alert alert-success text-center" role="alert">
			    	点赞用户<hr/>
			  		<@M_showHeadImgPage authorPage=entity.praisePage/>
			  	 </div>
			  	</p>
			  	</#if>
			  	<@Mg_activityReward id="id_activityReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
			  	
	  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
	  <p class="text-center">
	    <div class="alert alert-success text-center" role="alert">
	    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
	    	data-url="/sz0099/ood/activity/praise" 
	    	data-saveUrl="/sz0099/ood/activity/doPraise"
	    	data-refreshUrl="/sz0099/ood/activity/refreshPraise"
	    	/>
	  		<a href="javascript:void(0)" onclick="praise('${entity.id}','id_hidden_praise','id_praise_word')" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="reward('${entity.id}','/sz0099/ood/activity/reward','id_activityReward')" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="favirate('${entity.id}','/sz0099/ood/activity/favirate')" class="btn btn-lg btn-info" id="id_btn_favirate_${entity.id}">收</a>
	  	 </div>
	  	</p>
  	  <br/>
		<@M_showWordPage praisePage=entity.praisePage/>
  	  <br/>
  	  <div class="container text-center bg-success"><small>◎◎◎※※※※※※ 本文结束 ※※※※※※◎◎◎</small></div>
  	  <br/>
  	  <#-- 点赞列表输出 -->
  	  <#assign mainTypeList=entity.mainTypeList />
	  <#if mainTypeList! && mainTypeList??>
  	  	<@M_mainTypeList entity=entity url="/sz0099/ood/activity/findActivityForPraisePage/"/>
  	  </#if>
  	  
  	  <#-- 刷新列表输出 -->
  	  <#assign refreshPage=entity.refreshPage />
	  <#if refreshPage! && refreshPage.totalPages gt 0>
  	  		<@M_refreshPage entity=entity showPage=false/>
  	  </#if>
	  
 </#if>
 </#macro>
 
 <#macro Mg_activityReward id="id_activityReward" coeUser=null quanzhuUrl="">
 <div id="${id}" class="hidden">
 	 <p class="text-center">长按二维码，识别，进行打赏</p>
 	 <p class="text-center">该打赏直接到帐用户零钱，平台不记录，不抽成</p>
	 <div class="row">
	 	  <#if coeUser!>
	 	  <#assign authorUrl=coeUser.payRecieveImg/>
			  <div class="col-xs-6 col-md-3">
		 	  <#if authorUrl! && CoeUser.PAYRECIEVABLE_1_YES.valueInt==coeUser.payRecievable && CoeUserVerify.IDSTATUS_2_YES.valueInt==coeUser.userVerify.idstatus>
			    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_wechat">
			      <img src="${authorUrl}" alt="微信二维码" class="img-rounded">
			      	<p class="text-danger text-center">微信扫码打赏</p>
			    </a>
			    <p>打赏作者，感谢原创，继续加油、创作精品</p>
			  <#else><p><br/><br/>本文作者还没上传打赏码</p>
			  </#if>
			  
			  </div>
		  </#if>
		  <div class="col-xs-6 col-md-3">
		    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_wechat">
		      <img src="${quanzhuUrl}" alt="微信二维码" class="img-rounded">
		      	<p class="text-danger text-center">微信扫码打赏</p>
		    </a>
		    <p>打赏群主，辛苦写码，日夜奋战、码成平台</p>
		  </div>
	</div>
</div>
 </#macro>
 
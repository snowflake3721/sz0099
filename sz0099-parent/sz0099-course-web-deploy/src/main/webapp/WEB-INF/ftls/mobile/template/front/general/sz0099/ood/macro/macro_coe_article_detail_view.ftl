<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_image_roller.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_praise_list.ftl">

<#-- 文章详情，文章信息与段落分开查询，个人编辑用浏览 -->
<#macro Mg_coeArticleDetail entity paragraphPage=null moreLoaded="true">   
<#if entity!>
		 <div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		 ${entity.articleNo} -- ${entity.title} <span class="pull-right"> </span>
		 
		 </div>
		  <div class="panel-body">
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#assign author=entity.author/>
			  	<h4 class="text-center"> <strong>${HtmlUtils.htmlUnescape(entity.title)} </strong></h4>
			  	<#assign subTitle=entity.subTitle/>
			  	<#if subTitle!>
			  	<p class="text-right"> <small>${subTitle} &nbsp;&nbsp;</small></p>
			  	</#if>
			  	<p class="text-center"> <small>[文] 
			  	<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${ShowNameUtil.getShowname(entity.penname, entity.nickname, '大侠无名')}</a>
			  	 @ ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  	 </small>
			  	 </p>
			  	<#if entity.articleTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.articleTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
			  	</#if>
			  	
			    <blockquote>
			    ${HtmlUtil.textarea2Html(entity.description)}
			    </blockquote>
			    
			  	<@Mg_articleReward id="id_articleReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
  			
	  
			  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
		  	  
		  	  <p class="text-center"><div class="alert alert-success text-center" role="alert">
			    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
			    	data-url="/sz0099/ood/article/praise" 
			    	data-saveUrl="/sz0099/ood/article/doPraise"
			    	data-refreshUrl="/sz0099/ood/article/refreshPraise"
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
 
 
 
 <#-- 文章详情，文章信息与段落分开查询,用户浏览页 -->
<#macro Mg_coeArticleDetailView entity paragraphPage=null moreLoaded="true">   
<#if entity!>
		 
		  <#assign author=entity.author/>
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
			  	<h3 class="text-center"> <strong>${HtmlUtils.htmlUnescape(entity.title)} </strong></h3>
			  	<p class="text-right"> ${HtmlUtils.htmlUnescape(entity.subTitle)}  &nbsp; &nbsp;
			  	<#assign currentUserId=UserUtils.getUserId()/>
			  	<#if currentUserId! && currentUserId==entity.userId>
			  	<a href="/sz0099/ood/article/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_btn_edit_fully_${entity.id}">编辑</a>
			  	</#if>
			  	</p>
			  	<p class="text-center"> <small>[文] 
			  	<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${ShowNameUtil.getShowname(entity.penname, entity.nickname, '大侠无名')}</a> 
			  	@ ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  		<a href='${ProfessionController.LINK_BANG+entity.userId}?linkTitle=${HtmlUtil.substring(entity.title,0,10)?url}&linkBack=${entity.originalLink?url}'>
			  	 	<button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> 技</button>
			  	 	</a>
			  	</small>
			  	</p>
			  	<#if entity.articleTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.articleTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
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
			  	<@Mg_articleReward id="id_articleReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
			  	<#-- 
			  	<#assign praiseList = entity.praiseList />
			  	<#if praiseList!?size gt 0 > 
				  	<@M_showHeadImg imageList=praiseList/>
			  	</#if>
			  	 -->
			  	
		    
	  
	  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
	  <p class="text-center">
	    <div class="alert alert-success text-center" role="alert">
	    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
	    	data-url="/sz0099/ood/article/praise" 
	    	data-saveUrl="/sz0099/ood/article/doPraise"
	    	data-refreshUrl="/sz0099/ood/article/refreshPraise"
	    	/>
	  		<a href="javascript:void(0)" onclick="praise('${entity.id}','id_hidden_praise','id_praise_word')" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="reward('${entity.id}','/sz0099/ood/article/reward','id_articleReward')" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="favirate('${entity.id}','/sz0099/ood/article/favirate')" class="btn btn-lg btn-info" id="id_btn_favirate_${entity.id}">收</a>
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
  	  	<@M_mainTypeList entity=entity url="/sz0099/ood/article/findArticleForPraisePage/"/>
  	  </#if>
  	  
  	  <#-- 刷新列表输出 -->
  	  <#assign refreshPage=entity.refreshPage />
	  <#if refreshPage! && refreshPage.totalPages gt 0>
  	  		<@M_refreshPage entity=entity showPage=false/>
  	  </#if>
	  
 </#if>
 </#macro>
 
 <#macro Mg_articleReward id="id_articleReward" coeUser=null quanzhuUrl="">
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
 
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_image_roller.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_praise_list.ftl">

<#-- 技能详情，技能信息与段落分开查询 -->
<#macro Mg_professionDetail entity paragraphPage=null moreLoaded="true">   
<!--技能详情-->
<#if entity!>
		 <div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		 ${entity.professionNo} -- ${entity.title} <span class="pull-right"> </span>
		 
		 </div>
		  <div class="panel-body">
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#assign author=entity.author/>
			  	<h4 class="text-center"> <strong>${HtmlUtils.htmlUnescape(entity.title)} </strong></h4>
			  	<#assign subTitle=HtmlUtils.htmlUnescape(entity.subTitle)/>
			  	<#if subTitle!>
			  	<p class="text-right"> <small>${subTitle} </small></p>
			  	</#if>
			  	<p class="text-center"> 
			  	<small>大侠≡<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${entity.nickname}</a> ⊙神技≡ ${entity.penname} </small>
			  	</p>
			  	<#if entity.proTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.proTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
			  	</#if>
			  	
			    <blockquote>
			    ${HtmlUtil.textarea2Html(entity.description)}
			    </blockquote>
			    
			  	<@M_professionReward id="id_professionReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
			  	<#-- 
			  	<@M_showHeadImgPage authorPage=entity.praisePage/>
 				-->
			  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
		  	  <p class="text-center">
			    <div class="alert alert-success text-center" role="alert">
			    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
			    	data-url="/sz0099/ood/personal/profession/praise" 
			    	data-saveUrl="/sz0099/ood/personal/profession/doPraise"
			    	data-refreshUrl="/sz0099/ood/personal/profession/refreshPraise"
			    	/>
			    	&nbsp; &nbsp;
			  		<a href="javascript:void(0)" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
			  		&nbsp; &nbsp; 
			  		<a href="javascript:void(0)" onclick="reward('${entity.id}','/sz0099/ood/personal/profession/reward','id_professionReward')" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
			  		&nbsp; &nbsp; 
			  		<a href="javascript:void(0)" class="btn btn-lg btn-info" id="id_btn_favirate_${entity.id}">收</a>
			  		&nbsp; &nbsp;
			  		</div>
			  	</p>
		  	  <br/>
		  	  <div class="container text-center bg-success"><small>◎◎◎※※※※※※ 本技能结束 ※※※※※※◎◎◎</small></div>
		  	  <br/>
  			</div><!--end panel-body-->
	  </div><!--end panel-->	  
	  
 </#if>
 </#macro>
 
 
 
 <#-- 技能详情，技能信息与段落分开查询 -->
<#macro M_professionDetailView entity paragraphPage=null moreLoaded="true">   
<!--技能详情-->
<#if entity!>
		 <#assign author=entity.author/>
		  <@Mg_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#-- 获取category -->
		  <#assign categoryList=entity.categoryList/>
		  <#assign categoryRef=null/>
		  <#if categoryList! && categoryList??>
		  <#assign categoryRef=categoryList?first/>
		  </#if>
		  <#assign verifyTypeContext=ProfessionVerify.VERIFY_TYPE.getContext(entity.verifyType,0) />
		  
			  	<h3 class="text-center"> <strong>${HtmlUtils.htmlUnescape(entity.title)} </strong></h3>
			  	<p class="text-right"> ${HtmlUtils.htmlUnescape(entity.subTitle)}  &nbsp; &nbsp;
			  	<#assign currentUserId=UserUtils.getUserId()/>
			  	<#if currentUserId! && currentUserId==entity.userId>
			  	<a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_btn_edit_fully_${entity.id}">编辑</a>
			  	</#if>
			  	</p>
			  	<p class="text-center"> 
			  	<small>大侠≡<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${entity.nickname}</a> ⊙神技≡ ${entity.penname} </small>
			  	<br/>
			  	<small><span class="text-danger">${CategoryUtil.getName(categoryRef.baseId)} </span> <span class="glyphicon glyphicon-queen"></span> <span class="text-center bg-warning text-primary">修炼于 ${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd')}</span></small>
			  	<span onclick="showFriendTip('友情提示', 'id_showTip')">${verifyTypeContext.symbol}</span>
			  	</p>
			  	<p class="hidden" id="id_showTip">${verifyTypeContext.description}</p>
			  	<#if entity.proTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.proTagList idPre="tag_detail_" entity0=entity fontSize=12 randomSize=false/>
			  	</#if>
			  	<#assign description=entity.description/>
			  	<#if description!>
			  	<blockquote>
			    ${HtmlUtil.textarea2Html(entity.description)}
			    </blockquote>
			    </#if>
			    
			    <#if entity.praisePage! && entity.praisePage.totalPages gt 0>
			    <p class="text-center">
			    <div class="alert alert-success text-center" role="alert">
			    	点赞用户<hr/>
			  		<@M_showHeadImgPage authorPage=entity.praisePage/>
			  	 </div>
			  	</p>
			  	</#if>
			    
			  	<@M_professionReward id="id_professionReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
	  
	  <@Mg_paragraphViewContainer id="v001" paragPage=paragraphPage />
  	  
  	  <br/>
  	  <address>
  	  <p>
  	  <#if CoeUser.PRIVACY_SETTING_1_SHOW.valueInt==author.mobileShow && author.mobile!>
  	  <abbr title="Phone">联系方式：</abbr> ${author.mobile} <#if CoeUser.PRIVACY_SETTING_1_SHOW.valueInt==author.postnameShow><strong>绰号：${author.postname}</strong></#if>
  	  <#else>
  	  	大侠 ≡<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${entity.nickname}</a>≡ 行踪飘忽不定，无法联系
  	  </#if>
  	  </p>  
  	  <p><strong><span class="text-danger">切记：</span>联系时声明来自<span class="text-danger"> 群侠户外  </span> 【卓玛拉山 公众号】<strong></p>
  	  </address>
  	  
  	  	<p>
  	  	${verifyTypeContext.description} 
  	  	<#if entity.verifyLevel != null && entity.verifyLevel gt 0>◎ <span class="text-success">认证等级：</span>
	  	  	<#list 0..entity.verifyLevel as x>
	  	  		${verifyTypeContext.symbol} &nbsp;
	  	  	</#list>
  	  	</#if>
  	  	</p>
  	  	<#assign statusContext=ProfessionVerify.VERIFY_STATUS.getContext(entity.verifyStatus,0) />
  	  	<#if ProfessionVerify.VERIFY_STATUS_PASS.valueInt!=entity.verifyStatus>
  	  	<p><span class="text-danger"><span class="glyphicon glyphicon-flag"></span> ${statusContext.description}</span></p>
  	  	<#else>
  	  	<p><span class="text-success"><span class="glyphicon glyphicon-flag"></span> ${statusContext.description}</span></p>
  	  	</#if>
  	  <br/>
  	  
  	  <hr/>
  	  <br/>
  	  <p class="text-center"><div class="alert alert-success text-center" role="alert">
	    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
	    	data-url="/sz0099/ood/personal/profession/praise" 
	    	data-saveUrl="/sz0099/ood/personal/profession/doPraise"
	    	data-refreshUrl="/sz0099/ood/personal/profession/refreshPraise"
	    	/>
	  		<a href="javascript:void(0)" onclick="praise('${entity.id}','id_hidden_praise','id_praise_word')" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="reward('${entity.id}','/sz0099/ood/personal/profession/reward','id_professionReward')" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
	  		&nbsp; &nbsp; &nbsp; &nbsp;
	  		<a href="javascript:void(0)" onclick="favirate('${entity.id}','/sz0099/ood/personal/profession/favirate')" class="btn btn-lg btn-info" id="id_btn_favirate_${entity.id}">收</a>
	  		</div>
	  	</p>
  	  <@M_showWordPage praisePage=entity.praisePage/>
  	  <br/>
  	  <div class="container text-center bg-success"><small>◎◎◎※※※※※※ 本技能结束 ※※※※※※◎◎◎</small></div>
  	  <br/>
  	  <#-- 点赞列表输出 -->
  	  <#assign mainTypeList=entity.mainTypeList />
	  <#if mainTypeList! && mainTypeList??>
  	  	<@M_mainTypeProfessionList entity=entity url="/sz0099/ood/personal/profession/findProfessionForPraisePage/"/>
  	  </#if>
  	  
  	  <#-- 刷新列表输出 -->
  	  <#assign refreshPage=entity.refreshPage />
	  <#if refreshPage! && refreshPage.totalPages gt 0>
  	  		<@M_refreshProfessionPage entity=entity showPage=false/>
  	  </#if>
	  
 </#if>
 </#macro>
 
 <#macro M_professionReward id="id_professionReward" coeUser=null quanzhuUrl="">
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
			    <p>打赏作者，精湛技能，充分发挥、做好服务</p>
			  <#else>
			  <p><br/><br/>用户未核实认证或未上传打赏码</p>
			  </#if>
			  </div>
		  </#if>
		  <div class="col-xs-6 col-md-3">
		    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_wechat">
		      <img src="${quanzhuUrl}" alt="微信二维码" class="img-rounded">
		      	<p class="text-danger text-center">微信扫码打赏</p>
		    </a>
		    <p>打赏群主，写码技能，继续优化、服务群友</p>
		  </div>
	</div>
</div>
 </#macro>
 
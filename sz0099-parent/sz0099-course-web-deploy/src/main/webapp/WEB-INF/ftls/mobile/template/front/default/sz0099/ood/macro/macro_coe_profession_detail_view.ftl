<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_paragraph.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_image_roller.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_praise_list.ftl">

<#-- 技能详情，技能信息与段落分开查询 -->
<#macro M_professionDetail entity paragraphPage=null moreLoaded="true">   
<!--技能详情-->
<#if entity!>
		 <div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		 ${entity.professionNo} -- ${entity.name} <span class="pull-right"> </span>
		 
		 </div>
		  <div class="panel-body">
		  <@M_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#assign author=entity.author/>
			  	<p class="text-left"> <small>${entity.penname}</small></p>
			  	<h4 class="text-center"> <strong>${entity.title} </strong></h4>
			  	<p class="text-right"> <small>${entity.subTitle} </small></p>
			  	<#if entity.proTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.proTagList fontSize=12 randomSize=false/>
			  	</#if>
			  	
			    <p>${entity.description}</p>
			    
			    <p class="text-center"><div class="alert alert-success text-center" role="alert">
			    	<input type="hidden" id="id_hidden_praise" name="word" value="" 
			    	data-url="/sz0099/ood/personal/profession/praise" 
			    	data-saveUrl="/sz0099/ood/personal/profession/doPraise"
			    	data-refreshUrl="/sz0099/ood/personal/profession/refreshPraise"
			    	/>
			  		<a href="javascript:void(0)" onclick="praise('${entity.id}','id_hidden_praise','id_praise_word')" class="btn btn-lg btn-primary" id="id_btn_praise_${entity.id}">赞</a>
			  		&nbsp; &nbsp; &nbsp; &nbsp;
			  		<a href="javascript:void(0)" onclick="reward('${entity.id}','/sz0099/ood/personal/profession/reward','id_professionReward')" class="btn btn-lg btn-danger" id="id_btn_reward_${entity.id}">赏</a>
			  		</div>
			  	</p>
			  	<@M_professionReward id="id_professionReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
			  	<@M_showHeadImgPage authorPage=entity.praisePage/>
  			</div><!--end panel-body-->
		    
		    <div class="panel-footer">
		     ${entity.professionNo} -- ${entity.name}
		    </div>
	  </div><!--end panel-->
	  
	  <@M_paragraphViewContainer id="v001" paragPage=paragraphPage />
  	  
  	  <!--上一个 下一个 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	<div class="row text-center">
				  <div class="col-xs-6"><span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span></div>
				  <div class="col-xs-6"><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></div>
				</div>
			</div>
	  </#if>
	  
 </#if>
 </#macro>
 
 
 
 <#-- 技能详情，技能信息与段落分开查询 -->
<#macro M_professionDetailView entity paragraphPage=null moreLoaded="true">   
<!--技能详情-->
<#if entity!>
		 <#assign author=entity.author/>
		  <@M_bannerImageRollerForView imageRollerId=entity.id entity=entity withTitle=false/>
		  <#-- 获取category -->
		  <#assign categoryList=entity.categoryList/>
		  <#assign categoryRef=null/>
		  <#if categoryList! && categoryList??>
		  <#assign categoryRef=categoryList?first/>
		  
		  </#if>
			  	<h3 class="text-center"> <strong>${entity.title} </strong></h3>
			  	<p class="text-right"> ${entity.subTitle}  &nbsp; &nbsp;
			  	</p>
			  	<p class="text-center"> 
			  	<small>大侠≡<a href='javascript:void(0)' onclick="showSayword('${entity.nickname}','${entity.sayword.description}')">${entity.nickname}</a> ⊙神技≡ ${entity.penname} </small>
			  	<br/>
			  	<small><span class="text-danger">${CategoryUtil.getName(categoryRef.baseId)} </span> <span class="glyphicon glyphicon-queen"></span> <span class="text-center bg-warning text-primary">修炼于 ${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd')}</span></small>
			  	<span class="text-danger">${ProfessionVerify.VERIFY_TYPE_NO.symbol}</span>
			  	</p>
			  	<#if entity.proTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.proTagList fontSize=12 randomSize=false/>
			  	</#if>
			  	<#assign description=entity.description/>
			  	<#if description!>
			    <p class="lead"><strong><span class="text-danger">摘要：</span>${description!'这家伙很懒，没写摘要...'}</strong></p>
			    </#if>
			    
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
			  		<hr/>
			  		<@M_showHeadImgPage authorPage=entity.praisePage/>
			  		</div>
			  	</p>
			  	<@M_professionReward id="id_professionReward" coeUser=entity.author quanzhuUrl="/assets/jit4j_jui/user/wechat_pay_code.png" />
	  
	  <@M_paragraphViewContainer id="v001" paragPage=paragraphPage />
  	  
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
  	  	<p><strong>特别提醒：</strong><span class="text-danger">${ProfessionVerify.VERIFY_TYPE_NO.label}</span> 该大侠技能未经过<span class="text-danger"> 群侠户外 </span>官方认证，请谨慎合作！</p>
  	  	<p>${ProfessionVerify.VERIFY_TYPE_NO.description}</p>
  	  <br/>
  	  
  	  <hr/>
  	  <br/>
  	  <@M_showWordPage praisePage=entity.praisePage/>
  	  <br/>
  	  <div class="container text-center bg-success"><small>◎◎◎※※※※※※ 本文结束 ※※※※※※◎◎◎</small></div>
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
		 	  <#if authorUrl! && CoeUser.PAYRECIEVABLE_1_YES.valueInt==coeUser.payRecievable && CoeUserVerify.IDSTATUS_2_YES.valueInt==coeUser.idstatus>
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
 
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_coeActivityCreate entity=null url="/sz0099/ood/home/activity/index">   
<!--创建活动--> 

<@M_tabIndexForCreate currentMenu="" contentModel=entity/>
<@M_tabContentForCreate contentModel=entity/>
<!--课程活动-->

  <!--
  <div class="panel panel-default">
	  <div class="panel-body">
		
	  </div>
  </div>
  -->
<div class="list-group">
  <a href="javascript:void(0)" class="list-group-item active">
    <h4 class="list-group-item-heading">活动发布步骤</h4>
    <p class="list-group-item-text">1.录入活动>>标题</p>
	<p class="list-group-item-text">2.录入活动>>内容</p>
	<p class="list-group-item-text">3.录入活动>>时间（活动起止时间，报名截止时间等）</p>
	<p class="list-group-item-text">4.录入活动>>费用（报名费，费用说明等）</p>
	<p class="list-group-item-text">5.录入活动>>标签(可选)</p>
	<p class="list-group-item-text">6.录入活动>>发布</p>
  </a>
</div>

 </#macro>
 
 

<#macro M_tabIndexForCreate currentMenu=null contentModel=null>
  <!--导航开始-->
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/activity/manage/merge/baseinfo"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" ><a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/activity/manage/merge/baseinfo">标题</a></li>
	    <li role="presentation"><a href="#contentPhoto" aria-controls="contentPhoto" role="tab" data-toggle="tab" data-saveUrl="">内容</a></li>
	    <li role="presentation"><a href="#contentTime" aria-controls="contentTime" role="tab" data-toggle="tab" data-saveUrl="">时间</a></li>
	    <li role="presentation"><a href="#contentFee" aria-controls="contentFee" role="tab" data-toggle="tab" data-saveUrl="">费用</a></li>
	    <li role="presentation"><a href="#contentTag" aria-controls="contentTag" role="tab" data-toggle="tab" data-saveUrl="">标签</a></li>
	     
	     <#if contentModel! && contentModel.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
	    <li role="presentation"><a href="#panel_publish" aria-controls="panel_publish" role="tab" data-toggle="tab" data-saveUrl="">发布</a></li>
	    </#if>
</ul>
 </#macro>
 
<#macro M_tabContentForCreate contentModel=null>
<!-- Tab panes -->
<form>
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/activity/manage/merge/baseinfo">
    <div class="text-danger text-center">★ 为必填项</div>
    <div class="pull-right">
    <input type="hidden" id="id_mainType_current${contentModel.id}" value="${CoeActivity.MAINTYPE_9_MAIN.valueInt}" 
	  data-url="/sz0099/ood/activity/manage/merge/mainType" 
	  data-mainType=""
	  data-id="${contentModel.id}"
	  data-btnMainId="id_btn_btnMainId${contentModel.id}"
	  data-btnMainUnId="id_btn_un_btnMainId${contentModel.id}"
	  >
    <#-- 
    <button type="button" id="id_btn_un_btnMainId${contentModel.id}" class="btn btn-primary btn-xs <#if CoeActivity.MAINTYPE_9_MAIN.valueInt!=contentModel.mainType>hidden</#if>"><span class="glyphicon glyphicon-star" aria-hidden="true" style="font-size: 16px;"></span>
    	已是主推
    </button>
    <button type="button" id="id_btn_btnMainId${contentModel.id}" class="btn btn-danger btn-xs <#if CoeActivity.MAINTYPE_9_MAIN.valueInt==contentModel.mainType>hidden</#if>" onclick="mergeForMainType('${contentModel.id}','${CoeActivity.MAINTYPE_9_MAIN.valueInt}', 'id_mainType_current${contentModel.id}')">设为主推</button>
     -->
    </div>
    
    <div id="id_messageTip_baseinfo${contentModel.id}" class="text-center"></div>
    	  <div class="row">
			  <div class="col-xs-6 col-md-6">
			   <#assign currentActOrganize=CoeActivity.ACT_ORGANIZE.getContext(contentModel.actOrganize,1)/>
		 		<@M_dropdownBar id="id_act_" propertyContext=CoeActivity.ACT_ORGANIZE current=currentActOrganize readonly=false />
			  </div>
			  <div class="col-xs-6 col-md-6">
			  <@M_selectCategory entity=contentModel id="id_mainId" isAnsy=true jsFunction="changeCategory"/>
			  </div>
		  </div>
    	  
		  <#assign currentPreIntro=CoeActivity.PREINTRO_TYPE.getContext(contentModel.preIntroType,0)/>
		  <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=CoeActivity.PREINTRO_TYPE current=currentPreIntro readonly=false />
		 
		  
		 <div class="form-group form-group-sm">
		    <div  class="text-danger">标题(少于60字)★
		    <small><span class="text-success" id="id_title${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.title)}</span>/60</small>
		    <span id="id_title_showTip${contentModel.id}"></span>
		    </div>
		    <input type="text" id="id_title${contentModel.id}" name="title" 
		    value="${contentModel.title}"
		     data-saveUrl="/sz0099/ood/activity/manage/merge/title/ansy"
		    onblur="commitTitle('id_title${contentModel.id}','${contentModel.id}')"
		    onkeyup="watchTitle('id_title${contentModel.id}','${contentModel.id}')"
		    data-saved="0"
		    class="form-control" placeholder="标题，少于60字">
		 </div>
		 
		  <div class="form-group form-group-sm">
		    <div  class="text-danger">简介(少于1000字符)★
		    <small><span class="text-success" id="id_area_description${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.description)}</span>/1000</small>
		    <span id="id_area_description_showTip${contentModel.id}"></span>
		    </div>
		    <textarea id="id_area_description${contentModel.id}" rows="8" class="form-control" placeholder="总结一下本文的精华，少于1000字"
		     onblur="commitDescription('id_area_description${contentModel.id}','${contentModel.id}')"
		     data-saveUrl="/sz0099/ood/activity/manage/merge/description/ansy"
		     data-saved="0"
		    onkeyup="watchDescription('id_area_description${contentModel.id}','${contentModel.id}')"
		    >${contentModel.description}</textarea>
		  </div>
		  <div class="row">
			  <div class="col-xs-6 col-md-6">
				  <div class="form-group form-group-sm">
				    <div for="id_minNum${contentModel.id}"><small>人数下限(数字)</small>
				    <span id="id_minNum_showTip${contentModel.id}"></span>
				    </div>
				    <input type="text" id="id_minNum${contentModel.id}" name="minNum" 
				    value="${contentModel.minNum}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="人数下限(数字)">
				 </div>
		 	 </div>
		 	<div class="col-xs-6 col-md-6">
				  <div class="form-group form-group-sm">
				    <div for="id_maxNum${contentModel.id}"><small>人数上限(数字)</small>
				    <span id="id_maxNum_showTip${contentModel.id}"></span>
				    </div>
				    <input type="text" id="id_maxNum${contentModel.id}" name="maxNum" 
				    value="${contentModel.maxNum}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="人数上限(数字)">
				 </div>
			 </div>
		  </div><!--end row-->
		  <div class="row">
			<div class="col-xs-6 col-md-6">
				  <div class="form-group form-group-sm">
				    <div for="id_kilometer${contentModel.id}">里程(km)
				    <span id="id_kilometer_showTip${contentModel.id}"></span>
				    </div>
				    <input type="text" id="id_kilometer${contentModel.id}" name="kilometer" 
				    value="${contentModel.kilometer}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="里程(km)">
				 </div>
			</div>
		 	<div class="col-xs-6 col-md-6">
				 <div class="form-group form-group-sm">
				    <div for="id_difficulty${contentModel.id}">难度系数(0-99)
				    <span id="id_difficulty_showTip${contentModel.id}"></span>
				    </div>
				    <input type="text" id="id_difficulty${contentModel.id}" name="difficulty" 
				    value="${contentModel.difficulty}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="例：60">
				 </div>
		 	</div>
		  </div><!--end row-->
		 
		  <div class="form-group form-group-sm">
		    <div for="id_penname${contentModel.id}">发布者，填写时优先显示
		    <small><span class="text-success" id="id_penname${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.penname)}</span>/12</small>
		    <span id="id_penname_showTip${contentModel.id}"></span>
		    </div>
		    <input type="text" id="id_penname${contentModel.id}" name="penname" 
		    value="${contentModel.penname}"
		    onblur="showLength('id_penname${contentModel.id}', 12, 'id_penname${contentModel.id}_length')"
		    onkeyup="showLength('id_penname${contentModel.id}', 12, 'id_penname${contentModel.id}_length')"
		    class="form-control" placeholder="发布者,不填显示昵称">
		 </div>
		 
		  <div class="text-center">
		    <button type="button" id="id_btn_confirm2" class="btn btn-danger" onclick="commitSingle('id_btn_confirm2','id_hidden_current_panel', '${contentModel.id}')">保存★</button>
		  </div>
    		<#-- 封面图开始 -->
    			<@Mga_uploadConverImage id="coverUploaderInput"+contentModel.id mainId=contentModel.id subId=PhotoCover.SUBID_COVER_HEAD strategy=1 extend=coverExtend/>
				<div id="id_cover_photoPreview${PhotoCover.SUBID_COVER_HEAD}" >
				<div id="id_cover_messageTip_preview${PhotoCover.SUBID_COVER_HEAD}" class="text-center"></div>
				<ul class="list-group" id="id_wrapper_coverUploaderFiles" >
				<@Mga_coverImageForEdit id="coverUploaderInput"+contentModel.id imageList=contentModel.coverList subId=PhotoCover.SUBID_COVER_HEAD />
				</ul>
				</div>
				<#-- 封面图结束 -->
				<br/>
    			<@Mga_uploadBannerImage id="bannerUploaderInput"+contentModel.id mainId=contentModel.id subId=PhotoBanner.SUBID_BANNER_HEAD strategy=1 extend=bannerExtend/>
				<#-- 头部滚动图开始 -->
				<div id="id_banner_photoPreview${PhotoBanner.SUBID_BANNER_HEAD}" >
				<div id="id_banner_messageTip_preview${PhotoBanner.SUBID_BANNER_HEAD}" class="text-center"></div>
				<ul class="list-group" id="id_wrapper_bannerUploaderFiles" >
				<@Mga_bannerImageForEdit id="bannerUploaderInput"+contentModel.id imageList=contentModel.bannerList subId=PhotoBanner.SUBID_BANNER_HEAD />
				</ul>
				</div>
				<#-- 头部滚动图结束 -->
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentPhoto" data-saveUrl="">
    	<p class="text-right"><span class="pull-left">&nbsp;限99段</span>
    	<button class="btn btn-primary btn-xs" type="button" onclick="resetOrderSeq('${contentModel.id}','编号重排','/sz0099/ood/activity/manage/paragraph/resetOrderSeq')">编号重排</button> 
    	<button class="btn btn-danger btn-xs" type="button" onclick="refreshParagraphOrder('${contentModel.id}','/sz0099/ood/activity/manage/paragraph/editListUI','#contentPhoto')">刷新排序</button> 
    	<button class="btn btn-default btn-xs" type="button" onclick="showInstruction('id_content_help','段落图片编辑说明')">帮助</button>
    	</p>
    	<div class="hidden" id="id_content_help">
	    	<p><strong>1.内容图片，最多<b> 99 </b> 张 </strong></p>
		    <p><strong>2.排序：默认按图片编号升序排列（编号不连续不影响升序结果）</strong></p>
		    <p><strong>3.排序：可手动更改图片编号，即刻生效;如需查看排后结果，可点 <button class="btn btn-danger btn-xs">刷新排序</button></strong></p>
		    <p><strong>4.点 <button class="btn btn-primary btn-xs">预览</button> 查看本文最终效果</strong></p>
		    <hr/>
		    <br/>
		    <h4 class="text-center">段落编辑按钮说明</h4>
		    <div class="row">
		    <div class="col-xs-6"> <img src="/assets/common/help/article/3o_edit_parag_1.gif" class="img-responsive" alt="段落编辑-按钮说明" onclick="popBigViewOnly('/assets/common/help/article/3o_edit_parag_1.gif','段落编辑-按钮说明', false)"></div>
		    <div class="col-xs-6"> <img src="/assets/common/help/article/3o_edit_parag_2.gif" class="img-responsive" alt="段落编辑-编辑图片说明" onclick="popBigViewOnly('/assets/common/help/article/3o_edit_parag_2.gif','段落编辑-编辑图片说明', false)"></div>
		    </div>
		    <hr/>
		    <br/>
		    <br/>
		    <hr/>
		    <h4 class="text-center"><button class="btn btn-danger btn-xs">刷新排序</button>示例:</h4>
		    <div class="row">
		    <div class="col-xs-4"> <img src="/assets/common/help/article/2o_refresh_order_1.gif" class="img-responsive" alt="1.排序前" onclick="popBigViewOnly('/assets/common/help/article/2o_refresh_order_1.gif','刷新排序-1.排序前', false)"></div>
		    <div class="col-xs-4"> <img src="/assets/common/help/article/2o_refresh_order_2.gif" class="img-responsive" alt="2.修改排序编号" onclick="popBigViewOnly('/assets/common/help/article/2o_refresh_order_2.gif','刷新排序-2.修改排序编号', false)"></div>
		    <div class="col-xs-4"> <img src="/assets/common/help/article/2o_refresh_order_4.gif" class="img-responsive" alt="3.点击刷新排序" onclick="popBigViewOnly('/assets/common/help/article/2o_refresh_order_4.gif','刷新排序-3.点击刷新排序', false)"></div>
		    </div>
	    </div>
    	<div id="id_messageTip_paragraph${contentModel.id}" class="text-center"></div>
    	<#-- 图片上传开始 -->
    	<div id="id_contentPhoto_content${contentModel.id}"></div>
    	<#-- 图片上传结束 -->
    	
    </div>
    <div role="tabpanel" class="tab-pane fade" id="contentTime" data-saveUrl="">
    	<br/>
    	<input type="hidden" id="id_act_time_id" value="${contentModel.actTime.id}"
    	data-addJoinItemUrl="/sz0099/ood/activity/joinItem/manage/add"
    	data-deleteJoinItemUrl="/sz0099/ood/activity/joinItem/manage/delete"
    	data-mainId="${contentModel.id}"
    	data-joinItemWrapperId="id_joinItem_wrapper${contentModel.id}"
    	>
    	<div class="row">
    		<div class="col-xs-12">
    	 		<label class="col-sm-2 control-label">活动起止时间</label>
    	 	</div>
    	 	<div class="col-xs-12">
		    	<div class="input-group input-group-sm">
		    	<input id="id_picker_actBegin" 
		    	name="beginTime" type="text" 
		    	class="form-control input-sm" 
		    	value="${DateUtils.formatToString(contentModel.actTime.beginTime ,'yyyy-MM-dd HH:mm')}" 
		    	data-saveUrl="/sz0099/ood/activity/time/manage/mergeBeginTime"
		    	data-id="${contentModel.actTime.id}"
		    	data-saved="0"
		    	data-date-format="yyyy-mm-dd HH:ii" 
		    	readonly="true"
		    	placeholder="开始时间">
		    	<div class="input-group-addon">to</div>
		    	<input id="id_picker_actEnd" 
		    	name="endTime" 
		    	type="text" class="form-control input-sm" 
		    	value="${DateUtils.formatToString(contentModel.actTime.endTime ,'yyyy-MM-dd HH:mm')}" 
		    	data-saveUrl="/sz0099/ood/activity/time/manage/mergeEndTime"
		    	data-id="${contentModel.actTime.id}"
		    	data-saved="0"
		    	data-date-format="yyyy-mm-dd HH:ii" 
		    	readonly="true"
		    	placeholder="结束时间">
		    	</div>
		    	<div class="hidden" id="id_picker_actBegin_showTip"></div>
			    <div class="hidden" id="id_picker_actEnd_showTip"></div>
		    	
	    	</div>
	    </div>
	    <br/>
	   <div class="row">
	    	<div class="col-xs-5">
    	 		<label class="col-sm-2 control-label">报名截止时间</label>
    	 	</div>
    	 	<div class="col-xs-7">
		    	<div class="input-group input-group-sm">
		    	<input id="id_picker_offTime" name="offTime" type="text" class="form-control input-sm" 
		    	value="${DateUtils.formatToString(contentModel.actTime.offTime ,'yyyy-MM-dd HH:mm')}" 
		    	data-saveUrl="/sz0099/ood/activity/time/manage/mergeOffTime"
		    	data-id="${contentModel.actTime.id}"
		    	data-saved="0"
		    	data-date-format="yyyy-mm-dd HH:ii" 
		    	readonly="true"
		    	placeholder="截止报名时间"
		    	>
			    </div>
			    <div class="hidden" id="id_picker_offTime_showTip"></div>
		   </div>
    	</div>
    	
    	<br/>
    	<div class="row">
    			<div class="col-xs-12">
	    	 		<label class="col-sm-2 control-label">集合时间与地点</label>
	    	 		<span class="glyphicon glyphicon-plus" 
	    	 		onclick="addJoinItem('id_act_time_id','id_btn_addJoinItem')"
	    	 		data-saved="0"
	    	 		id="id_btn_addJoinItem"
	    	 		></span>(可加多条)
	    	 	</div>
	    </div>
		<ul class="list-group" id="id_joinItem_wrapper${contentModel.id}">
			<@M_joinItemList entityList=contentModel.actTime.joinItemList />
	   </ul>
    	<br/>
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentFee" data-saveUrl="">
    	<br/>
    	<input type="hidden" id="id_act_fee_id" value="${contentModel.actFee.id}"
    	data-saveUrl="/sz0099/ood/activity/fee/manage/mergeFee"
    	data-mainId="${contentModel.id}"
    	data-tipId="id_fee_tip${contentModel.actFee.id}"
    	data-saved="0"
    	>
    	<div id="id_fee_tip${contentModel.actFee.id}" class="text-center"></div>
    	<div class="row">
	    	<div class="col-xs-6">
    	 		<#assign currentCurrency=CoeActivityFee.CURRENCY.getContext(contentModel.actFee.currency,0)/>
				<@M_dropdownBar id="id_fee_" propertyContext=CoeActivityFee.CURRENCY current=currentCurrency readonly=true />
    	 	</div>
    	 	<div class="col-xs-6">
    	 		<#assign currentPriceType=CoeActivityFee.PRICETYPE.getContext(contentModel.actFee.priceType,0)/>
				<@M_dropdownBar id="id_fee_" propertyContext=CoeActivityFee.PRICETYPE current=currentPriceType readonly=true />
    	 	</div>
    	 </div>
    	 	
    	<div class="row">
	    	
	    	<div class="col-xs-6">
    	 		<#assign currentRecieveType=CoeActivityFee.RECIEVETYPE.getContext(contentModel.actFee.recieveType,0)/>
				<@M_dropdownBar id="id_fee_" propertyContext=CoeActivityFee.RECIEVETYPE current=currentRecieveType readonly=true />
				
    	 	</div>
    	 	<div class="col-xs-6">
		    	<#assign currentFeeType=CoeActivityFee.FEETYPE.getContext(contentModel.actFee.feeType,0)/>
				<@M_dropdownBar id="id_fee_" propertyContext=CoeActivityFee.FEETYPE current=currentFeeType readonly=false />
		   </div>
			<div class="col-xs-6">
				 <div class="form-group form-group-sm">
				    <label for="id_fee_rmbAmount${contentModel.actFee.id}">
				 	<small>   
				    	报名费(单位：分)
				    	<span id="id_fee_rmbAmount${contentModel.actFee.id}_showTip"></span>
				 	</small>   
				    </label>
				    <input type="text" id="id_fee_rmbAmount${contentModel.actFee.id}" name="rmbAmount" 
				    value="${contentModel.actFee.rmbAmount}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="金额(如：10000)">
				 </div>
			 </div>
			 <div class="col-xs-6">
				 <div class="form-group form-group-sm">
				    <label for="id_fee_rmbAmountOri${contentModel.actFee.id}">
				    <small>
				    	原价(单位：分)
				    	<span id="id_fee_rmbAmountOri_showTip${contentModel.actFee.id}"></span>
				    </small>
				    </label>
				    <input type="text" id="id_fee_rmbAmountOri${contentModel.actFee.id}" name="rmbAmountOri" 
				    value="${contentModel.actFee.rmbAmountOri}"
				    onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
				    class="form-control" placeholder="金额(如：15000)">
				 </div>
			 </div>
		 </div>
		 
		 <div class="form-group form-group-sm">
		    <label for="id_fee_description${contentModel.actFee.id}">费用说明
		    <small><span class="text-success" id="id_fee_description${contentModel.actFee.id}_length">${HtmlUtil.countTextLength(contentModel.actFee.description)}</span>/512</small>
		    <span id="id_fee_description${contentModel.actFee.id}_showTip"></span>
		    </label>
		 <textarea 
		 	placeholder="费用说明" 
		 	id="id_fee_description${contentModel.actFee.id}"
		  	class="form-control input-sm" 
		  	name="description" 
		  	data-saveUrl="/sz0099/ood/activity/fee/manage/mergeDescription"
	    	data-id="${contentModel.actFee.id}"
	    	data-mainId="${contentModel.id}"
	    	data-allowedLength="512"
	    	data-saved="0"
	    	<#-- 
	    	onblur="commitField('id_fee_description${contentModel.actFee.id}', '${contentModel.actFee.id}')"
	    	 -->
	    	onkeyup="watchField('id_fee_description${contentModel.actFee.id}', '${contentModel.actFee.id}')"
		  rows="5">${HtmlUtil.textarea2UnEscape(contentModel.actFee.description)}</textarea>
    	</div>
    	<p class="text-center">
    	<button type="button" class="btn btn-danger" onclick="mergeFee('id_act_fee_id','${contentModel.actFee.id}')">保存★</button>
    	</p>
    	<br/>
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentTag" data-saveUrl="">
    	
    	<br/>
    	<p><strong>1.恰当的标签，可增加活动搜索机率，最多加<b> 5  </b>个</strong></p>
    	<p><strong>2.标签长度越短越好，且必须小于7个字</strong></p>
    	<div id="id_contentTag_tag${contentModel.id}" class="container">
    		<h6>设置标签：</h6>
			<div id="id_messageTip_tag" class="text-center"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group input-group-sm">
				      		<input type="text" class="form-control" id="id_tag${contentModel.id}" name="name" placeholder="输入标签名称" >
				      		<span class="input-group-btn">
				      		<button class="btn btn-primary btn-xs" type="button" onclick="saveTag('${contentModel.id}','/sz0099/ood/activity/manage/tag/add')">添加</button>
				      		<button class="btn btn-warning btn-xs" type="button" onclick="clearTag('${contentModel.id}')">清空</button>
				      		</span>		
				    </div><!-- /input-group -->
				</div>
			</div>
			<br/>
			<p id="id_p_tags${contentModel.id}">
				<@M_showTagList productTagList=contentModel.activityTagList onlyView=false deletedUrl="/sz0099/ood/activity/manage/tag/delete"/>
			</p>
    	<br/>
    	</div>
		<p><strong>“存模板”是将该活动保存为一个样板，以供下次新建活动时选择，简化设置时间</strong></p>
    	<div class="row">
				<div class="col-xs-9">
				<#assign currentTemplate=CoeActivity.TEMPLATE.getContext(contentModel.template,0)/>
				<@M_dropdownBar id="id_act_" propertyContext=CoeActivity.TEMPLATE current=currentTemplate 
					readonly=true
					instructionDivId="instruction_id_template" 
					instructionHtml="<a href='javascript:void(0)' onclick='showInstruction(\"instruction_id_template\",\"模板说明\")'>模板说明</a>"
				/>
				<#assign currentCascadeParag=CoeActivity.CASCADEPARAG.getContext(contentModel.cascadeParag,1)/>
				<@M_dropdownBar id="id_act_" propertyContext=CoeActivity.CASCADEPARAG current=currentCascadeParag 
					readonly=true
					instructionDivId="instruction_id_cascadeParag" 
					instructionHtml="<a href='javascript:void(0)' onclick='showInstruction(\"instruction_id_cascadeParag\",\"级联内容说明\")'>模板设置-级联内容说明</a>"
				/>
				
				<input type="hidden" id="id_template" name="template" value="${contentModel.template}" 
				data-saved="0"
				data-tipId="id_template_showTip"
				data-saveUrl="/sz0099/ood/activity/manage/merge/template"
				/>
				<span id="id_template_showTip"></span>
				<span id="id_cascadeParag_showTip"></span>
		    	</div>
				<div class="col-xs-3">
					<br/>
			    	<a href="javascript:void(0)" onclick="mergeForTemplate('id_template','${contentModel.id}')" class="btn btn-warning btn-sm " role="button">存模板</a>
		    	</div>
    	</div>
    	<br/>
    	<br/>
    </div>
    
    <#if contentModel.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
    <div role="tabpanel" class="tab-pane fade" id="panel_publish" data-saveUrl="">
    	<br/>
    	<p><strong>1.请确认[标题、内容、时间、费用]必填步骤已填写完毕！<span class="text-danger text-center">★ 为必填项</span></strong></p>
    	<p><strong>2.点击发布按钮</strong></p>
    	
    	<div id="id_messageTip_publish" class="text-center"></div>
    	<ul class="list-inline text-center">
		<li class="text-right"><a href="javascript:void(0)" onclick="mergeForPublish('${contentModel.id}','/sz0099/ood/activity/manage/merge/publish')" class="btn btn-danger btn-lg " role="button">发布★</a></li>
		</ul>
		<br/>
		<br/>
    </div>
    </#if>
</div>
</form>

<#-- 
<p class="text-right">
<button type="button" id="id_btn_confirm" class="btn btn-warning" onclick="commitSingle('id_btn_confirm','id_hidden_current_panel', '${contentModel.id}')">保存当前★</button>
</p>
 -->
</#macro>

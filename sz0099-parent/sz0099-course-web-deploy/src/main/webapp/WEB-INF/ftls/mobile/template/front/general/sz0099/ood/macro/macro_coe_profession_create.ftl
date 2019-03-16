<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_paragraph.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_professionCreate profession=null url="/sz0099/ood/product/index">   
<!--创建技能--> 

<@M_tabIndexForCreate currentMenu="" contentModel=profession/>
<@M_tabContentForCreate contentModel=profession/>

  <!--
  <div class="panel panel-default">
	  <div class="panel-body">
		
	  </div>
  </div>
  -->
<div class="list-group">
  <a href="javascript:void(0)" class="list-group-item active">
    <h4 class="list-group-item-heading">技能说明</h4>
    <p class="list-group-item-text">1.技能是你的业务输出能力，添加技能后，更方便让圈里人了解你，拓展交往范畴</p>
	<p class="list-group-item-text">2.当前只开放三个技能，可分别代表您的 <strong>兴趣爱好、主营业务、特殊绝活</strong></p>
	<p class="list-group-item-text">3.对技能简要描述，并配以最多（99图）展示</p>
	<p class="list-group-item-text">4.技能作为你的肖像主要输出，以便在圈里形成知名度</p>
	<p class="list-group-item-text">5.技能底部可关联你发布类别为【技能】里的文章，方便用户直接导航，深入合作</p>
  </a>
</div>

 </#macro>
 
 

<#macro M_tabIndexForCreate currentMenu=null contentModel=null>
  <!--导航开始-->
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/personal/profession/manage/merge/baseinfo"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" >
	    <a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" 
	    data-saveUrl="/sz0099/ood/personal/profession/manage/merge/baseinfo"
	    data-toggle="tab" 
	    data-url="/sz0099/ood/personal/profession/manage/create?id=${contentModel.id}"
	    data-loaded="1"
	    data-tabId="panel_baseinfo"
	    id="id_a_panel_baseinfo"
   		data-tabWrapperId='panel_baseinfo'
	    >标题</a>
	    </li>
	    
	    <li role="presentation">
	    <a href="#panel_contentPhoto" aria-controls="panel_contentPhoto" role="tab" data-toggle="tab" 
	     data-saveUrl="/sz0099/ood/personal/profession/manage/paragraph/editListUI"
	     data-toggle="tab" 
	     data-url="/sz0099/ood/personal/profession/manage/paragraph/editListUI"
	     data-loaded="0"
	     data-tabId="panel_contentPhoto"
	     id="id_a_panel_contentPhoto"
   		 data-tabWrapperId="id_contentPhoto_content${contentModel.id}"
   		 data-extraDataId="id_hidden_id"
   		 >内容</a>
   		 </li>
   		 
	    <li role="presentation">
	    <a href="#panel_contentTag" aria-controls="panel_contentTag" role="tab" data-toggle="tab" 
	    data-saveUrl=""
	    data-url="/sz0099/ood/personal/profession/manage/bindListUI"
	    data-loaded="1"
	    data-tabId="panel_contentTag"
	    id="id_a_panel_contentTag"
		data-tabWrapperId='panel_contentTag${contentModel.id}'
	    >标签</a>
	    </li>
	    
	    <li role="presentation">
	    <a href="#panel_position_ref_bind" aria-controls="panel_position_ref_bind" role="tab" data-toggle="tab" 
	    data-saveUrl="/sz0099/ood/personal/profession/manage/bindListUI"
    	data-url="/sz0099/ood/personal/profession/manage/bindListUI"
	    data-loaded="0"
	    data-tabId="panel_position_ref_bind"
	    id="id_a_panel_position_ref_bind"
		data-tabWrapperId='panel_position_ref_bind_content${contentModel.id}'
		data-extraDataId="id_hidden_id"
    	>关联</a>
    	</li>
	     
	    <#if contentModel! && contentModel.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
	    <li role="presentation"><a href="#panel_publish" aria-controls="panel_publish" role="tab" data-toggle="tab"
	    data-loaded="1"
	    >发布</a></li>
	    </#if>
</ul>
 </#macro>
 
<#macro M_tabContentForCreate contentModel=null>
<!-- Tab panes -->
<form>
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/personal/profession/manage/merge/baseinfo">
    <div class="text-danger text-center">★ 为必填项</div>
    <div class="pull-right">
    <input type="hidden" id="id_mainType_current${contentModel.id}" value="${Profession.MAINTYPE_9_MAIN.valueInt}" 
	  data-url="/sz0099/ood/personal/profession/manage/merge/mainType" 
	  data-mainType=""
	  data-id="${contentModel.id}"
	  data-btnMainId="id_btn_btnMainId${contentModel.id}"
	  data-btnMainUnId="id_btn_un_btnMainId${contentModel.id}"
	  >
    <button type="button" id="id_btn_un_btnMainId${contentModel.id}" class="btn btn-primary btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt!=contentModel.mainType>hidden</#if>"><span class="glyphicon glyphicon-star" aria-hidden="true" style="font-size: 16px;"></span>
    	已是主技能
    </button>
    <button type="button" id="id_btn_btnMainId${contentModel.id}" class="btn btn-danger btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt==contentModel.mainType>hidden</#if>" onclick="mergeForMainType('${contentModel.id}','${Profession.MAINTYPE_9_MAIN.valueInt}', 'id_mainType_current${contentModel.id}')">设为主技能</button>
    
    </div>
    
    <div id="id_messageTip_baseinfo${contentModel.id}" class="text-center"></div>
    	  <@M_selectCategory entity=contentModel id="id_mainId" isAnsy=true jsFunction="changeCategory"/>
    	  
		  <div class="form-group form-group-sm">
		    <div  class="text-danger">标题(少于32字)★
		    <small><span class="text-success" id="id_title${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.title)}</span>/32</small>
		    <span id="id_title_showTip${contentModel.id}"></span>
		    </div>
		    <input type="text" id="id_title${contentModel.id}" name="title" 
		    value="${contentModel.title}"
		    data-saveUrl="/sz0099/ood/personal/profession/manage/merge/title/ansy"
		    onblur="commitTitle('id_title${contentModel.id}','${contentModel.id}')"
		    onkeyup="watchTitle('id_title${contentModel.id}','${contentModel.id}')"
		    data-saved="0"
		    class="form-control" placeholder="标题，少于32字">
		 </div>
		 
		  <div class="form-group form-group-sm">
		    <div class="text-danger">技能概述(少于1000字符)★
		    <small><span class="text-success" id="id_area_description${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.description)}</span>/1000</small>
		    <span id="id_area_description_showTip${contentModel.id}"></span>
		    </div>
		    <textarea id="id_area_description${contentModel.id}" rows="8" class="form-control" placeholder="总结一下 本技能的精华，少于1000字"
		    onblur="commitDescription('id_area_description${contentModel.id}','${contentModel.id}')"
		     data-saveUrl="/sz0099/ood/personal/profession/manage/merge/description/ansy"
		     data-saved="0"
		    onkeyup="watchDescription('id_area_description${contentModel.id}','${contentModel.id}')"
		    >${contentModel.description}</textarea>
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_penname${contentModel.id}">技能别名（填写时优先显示,少于12字符）
		    <small><span class="text-success" id="id_penname${contentModel.id}_length">${HtmlUtil.countTextLength(contentModel.penname)}</span>/12</small>
		    </label>
		    <input type="text" id="id_penname${contentModel.id}" name="penname" 
		    value="${contentModel.penname}"
		    onblur="showLength('id_penname${contentModel.id}', 12, 'id_penname${contentModel.id}_length')"
		    onkeyup="showLength('id_penname${contentModel.id}', 12, 'id_penname${contentModel.id}_length')"
		    class="form-control" placeholder="技能别名,填写时优先显示">
		 </div>
		 
		 <#-- 
		 <#assign currentPreIntro=Profession.PREINTRO_TYPE.getContext(contentModel.preIntroType,30)/>
		 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=Profession.PREINTRO_TYPE current=currentPreIntro readonly=false />
	  	 -->
	  	<br/>
	  	<div class="text-center">
		<button type="button" id="id_btn_confirm" class="btn btn-danger" onclick="commitSingle('id_btn_confirm','id_hidden_current_panel', '${contentModel.id}')">保存★</button>
		</div>
    			<#-- 封面图开始 -->
    			<@Mgp_uploadConverImage id="coverUploaderInput"+contentModel.id mainId=contentModel.id subId=PhotoCover.SUBID_COVER_HEAD strategy=1 extend=coverExtend/>
				<div id="id_cover_photoPreview${PhotoCover.SUBID_COVER_HEAD}" >
				<div id="id_cover_messageTip_preview${PhotoCover.SUBID_COVER_HEAD}" class="text-center"></div>
				<ul class="list-group" id="id_wrapper_coverUploaderFiles" >
				<@Mgp_coverImageForEdit id="coverUploaderInput"+contentModel.id imageList=contentModel.coverList subId=PhotoCover.SUBID_COVER_HEAD />
				</ul>
				</div>
				<#-- 封面图结束 -->
				<br/>
    			<@Mgp_uploadBannerImage id="bannerUploaderInput"+contentModel.id mainId=contentModel.id subId=PhotoBanner.SUBID_BANNER_HEAD strategy=1 extend=bannerExtend/>
				<#-- 头部滚动图开始 -->
				<div id="id_banner_photoPreview${PhotoBanner.SUBID_BANNER_HEAD}" >
				<div id="id_banner_messageTip_preview${PhotoBanner.SUBID_BANNER_HEAD}" class="text-center"></div>
				<ul class="list-group" id="id_wrapper_bannerUploaderFiles" >
				<@Mgp_bannerImageForEdit id="bannerUploaderInput"+contentModel.id imageList=contentModel.bannerList subId=PhotoBanner.SUBID_BANNER_HEAD />
				</ul>
				</div>
				<#-- 头部滚动图结束 -->
    </div>
    
    
    <div role="tabpanel" class="tab-pane fade" id="panel_position_ref_bind"
    data-saveUrl="/sz0099/ood/personal/profession/manage/bindListUI"
    data-wrapperId='panel_position_ref_bind_content${contentModel.id}'
    >
		<p class="text-center"><strong>可关联你发布分类为【技能】的文章</strong></p> 
		 
		    <p class="text-right">
		    <a class="btn btn-xs btn-info" href="javascript:void(0)" 
		    onclick="refreshParagraph('#panel_position_ref_bind','panel_position_ref_bind_content${contentModel.id}', '/sz0099/ood/personal/profession/manage/bindListUI', 'id_messageTip_position_ref_bind${contentModel.id}')">
		    	刷新技能绑定
		    </a>
		     &nbsp;&nbsp;
		    </p>
		    <br/>
    	<div id="id_messageTip_position_ref_bind${contentModel.id}" class="text-center"></div>
		<div id="panel_position_ref_bind_content${contentModel.id}"></div>
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="panel_contentPhoto"
    >
    	<p class="text-right"><span class="pull-left">&nbsp;限99段</span>
    	<button class="btn btn-primary btn-xs" type="button" onclick="resetOrderSeq('${contentModel.id}','编号重排','/sz0099/ood/personal/profession/manage/paragraph/resetOrderSeq')">编号重排</button> 
    	<button class="btn btn-danger btn-xs" type="button" onclick="refreshParagraphOrder('${contentModel.id}','/sz0099/ood/personal/profession/manage/paragraph/editListUI','#contentPhoto')">刷新排序</button> 
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
    	
    	<!--段落大组件开始-->
    	<#-- id=contentModel.id 传递productId -->
    	<#-- 
    	<@M_paragraphBtn btnId="btn1" id=contentModel.id/>
    	<div id="id_contentPhoto_content${contentModel.id}"></div>
    	<@M_paragraphBtn btnId="btn2" id=contentModel.id/>
    	 -->
    	<!--段落大组件结束-->
    </div>
    <div role="tabpanel" class="tab-pane fade" id="panel_contentTag">
    	
    	<br/>
    	<p><strong>1.为技能贴标签，最多绑<b> 5  </b>个</strong></p>
    	<p><strong>2.标签长度越短越好，且必须小于 7 个字符</strong></p>
    	<div id="id_contentTag_tag${contentModel.id}" class="container">
    		<h6>设置标签：</h6>
			<div id="id_messageTip_tag" class="text-center"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group input-group-sm">
				      		<input type="text" class="form-control" id="id_tag${contentModel.id}" name="name" placeholder="输入标签名称" >
				      		<span class="input-group-btn">
				      		<button class="btn btn-primary btn-xs" type="button" onclick="saveTag('${contentModel.id}','/sz0099/ood/personal/profession/manage/tag/add')">添加</button>
				      		<button class="btn btn-warning btn-xs" type="button" onclick="clearTag('${contentModel.id}')">清空</button>
				      		</span>		
				    </div><!-- /input-group -->
				</div>
			</div>
			<br/>
			<p id="id_p_tags${contentModel.id}">
				<@M_showTagList productTagList=contentModel.proTagList onlyView=false deletedUrl="/sz0099/ood/personal/profession/manage/tag/delete"/>
			</p>
    	<br/>
    	<br/>
    	</div>
    	
    	<#-- 
    	<#include "/mobile/template/front/general/home/panel_travel.ftl">
    	-->
    </div>
    
    <#if contentModel.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
    <div role="tabpanel" class="tab-pane fade" id="panel_publish">
    <div class="text-danger text-center">★ 为必填项</div>
    	<br/>
    	<p><strong>1.请确认[标题、内容]必填项已填写完毕！</strong></p>
    	<p><strong>2.点击发布按钮</strong></p>
    	<div id="id_messageTip_publish" class="text-center"></div>
    	<ul class="list-inline text-center">
		<li class="text-right"><a href="javascript:void(0)" onclick="mergeForPublish('${contentModel.id}')" class="btn btn-danger btn-lg " role="button">发布★</a></li>
		</ul>
		<br/>
		<br/>
    </div>
    </#if>
</div>
</form>

</#macro>


<#-- 编辑技能 begin -->
 <#macro M_professionListEdit content=null entity=null>
 <#if content!>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_professionEdit entity=ce />
		</#list>
	<#else>
	<@M_professionEdit entity=entity />
	</#if>
 </#if>
 </#macro>
 
<#macro M_professionPageEdit page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_professionEdit entity=ce />
		</#list>
	<#else>
	<@M_professionEdit entity=entity />
	</#if>
<#else>
<@M_professionEdit entity=entity />
</#if>
</#macro>

<#-- 技能编辑面板 begin -->
<#macro M_professionEdit entity>
<div id="position_${entity.id}"> <!--div wrapper-->
	<#assign pname = entity.name />
	<a id="id_a_subposition${entity.id}" class="btn btn-warning btn-xs" role="button" data-toggle="collapse" href="#id_subposition_${entity.id}" aria-expanded="false" aria-controls="id_subposition_${entity.id}">
	 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
	  <span id="id_position_orderSeq_show${entity.id}_col"> ${entity.orderSeq}</span>#<span id="id_position_name_show${entity.id}_col">${getSubstring(pname,10,'编辑技能')}</span> : ${Position.SUBID.getLabel(entity.subId)} >> ${Position.PANEL.getLabel(entity.panel)} >> ${Position.LAYOUT.getLabel(entity.layout)}
	</a>
	<div id="id_subposition_${entity.id}" class="panel-collapse collapse">
		
			<div class="panel panel-danger" id="id_panel_positionId${entity.id}" name="position">
				  <div class="panel-heading">
				    	<p class="panel-title text-center">
				    	<button type="button" class="btn btn-xs btn-primary pull-left" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存</button>
				    	<strong>#<span id="id_position_orderSeq_show${entity.id}">${entity.orderSeq}</span></strong>
				    	
				    	
				    	<span id="id_position_name_show${entity.id}">${getSubstring(pname,10,'编辑技能')}</span>
				    	<button type="button" class="btn btn-xs btn-danger pull-right" onclick="deleteSingle('${entity.id}', '/sz0099/ood/position/manage/deleteSingle','id_tip_${entity.id}','position_${entity.id}')">删除</button>
					    <input type="hidden" id="id_position_positionId${entity.id}" name="positionId" value="${entity.positionId}" class="form-control" >
					    <input type="hidden" id="id_position_id${entity.id}" name="id" value="${entity.id}" class="form-control" >
				    	</p>
				    	
				  </div>
				  <div class="panel-body">
				  <div id="id_tip_${entity.id}" class="text-center"></div>
				  	  <div class="form-group form-group-sm">
					    <label for="id_position_orderSeq${entity.id}">技能排序（数字，例如：1,2,3...）</label>
					    <input type="text" id="id_position_orderSeq${entity.id}" name="orderSeq" 
					    value="${entity.orderSeq}" class="form-control" 
					    onkeyup="keyPressPositive(this)"  
						onafterpaste="onAfterPastePositive(this)"
						onchange="onChangeShow('id_position_orderSeq_show${entity.id}',this.value)"
					    placeholder="技能排序，输入数字">
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_name${entity.id}">技能名称(英文少于15字母，汉字少于5个字)</label>
					    <input type="text" id="id_position_name${entity.id}" 
					    name="name" 
					    onchange="onChangeShow('id_position_name_show${entity.id}',this.value,10)"
					    value="${entity.name}" class="form-control" placeholder="技能名称，如：首页">
					  </div>
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.SUBID current=Position.SUBID.getContext(entity.subId,0) readonly=true />
					  
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.PANEL current=Position.PANEL.getContext(entity.panel,0) readonly=true />
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.LAYOUT current=Position.LAYOUT.getContext(entity.layout,0) readonly=false />
					  
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.STATUS current=Position.STATUS.getContext(entity.status,0) readonly=true />
					  
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_title${entity.id}">技能标题(少于16字符)</label>
					    <input type="text" id="id_position_title${entity.id}" name="title" value="${entity.title}" class="form-control" placeholder="技能标题">
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_description${entity.id}">技能描述(少于200字符)</label>
					    <textarea id="id_position_description${entity.id}" rows="5" class="form-control" placeholder="技能描述，少于200字">${entity.description}</textarea>
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_link${entity.id}">技能导向链接</label>
					    <input type="text" id="id_position_link${entity.id}" name="link" value="${entity.link}" class="form-control" placeholder="技能导向链接">
					  </div>
					  
				    </div><!--end panel-body-->
				    
				    <div class="panel-footer">
				    	<p class="text-center">每个大类都要点保存哦！-->
				    	<button type="button" class="btn btn-sm btn-primary pull-right" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存当前技能</button>
				    	</p>
				    </div>
			  </div><!--end panel-->
		</div><#-- 展开与折叠 -->
</div><!--end div wrapper-->
<hr/>
<script>
$(document).ready(function(){

});
</script>
</#macro>

<#-- 绑定技能 begin -->
 <#macro M_professionListBind content=null entity=null>
 <#if content!>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_professionBind entity=ce />
		</#list>
	<#else>
	<@M_professionEdit entity=entity />
	</#if>
 </#if>
 </#macro>
 
<#macro M_professionPageBind page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_professionBind entity=ce />
		</#list>
	<#else>
	<@M_professionBind entity=entity />
	</#if>
<#else>
<@M_professionBind entity=entity />
</#if>
</#macro>

<#-- 绑定编辑面板 begin -->
<#macro M_professionBind entity>
<div id="position_${entity.id}"> <!--div wrapper-->
	<#assign pname = entity.title />
	<a id="id_a_position_bind${entity.id}" class="btn btn-warning btn-xs" role="button" data-toggle="collapse" href="#id_position_bind_${entity.id}" aria-expanded="false" aria-controls="id_position_bind_${entity.id}">
	 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
	  <span id="id_position_orderSeq_show${entity.id}_col_bind"> ${entity.orderSeq}</span>#<span id="id_position_name_show${entity.id}_col_bind">${getSubstring(pname,10,'编辑技能')}</span>: ${Profession.PREINTRO_TYPE.getLabel(entity.preIntroType)} >> ${entity.penname}
	<script>
		loadDataOnShow('id_position_bind_${entity.id}','id_collapse_content_${entity.id}','id_hidden_list_loaded${entity.id}','id_collapse_tip_${entity.id}','${entity.id}','${entity.extendId}');
	</script>
	</a>
	<div id="id_position_bind_${entity.id}" class="panel-collapse collapse">
		
			<div class="panel panel-danger" id="id_panel_bind_positionId${entity.id}" name="position">
				  <div class="panel-heading">
				    	<p class="panel-title text-center">
				    	<#-- 
				    	<button type="button" class="btn btn-xs btn-primary pull-left" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存</button>
				    	 -->
				    	<strong>#<span id="id_position_bind_orderSeq_show${entity.id}">${entity.orderSeq}</span></strong>
				    	
				    	
				    	<span id="id_position_bind_name_show${entity.id}">${getSubstring(pname,10,'无名技能')}</span>
				    	<button type="button" class="btn btn-xs btn-danger pull-right" 
				    	onclick="deleteRefByBaseId('id_collapse_content_${entity.id}', 'id_hidden_list_loaded${entity.id}', '/sz0099/ood/personal/profession/ref/manage/deleteRefByBaseId','id_collapse_tip_${entity.id}','${entity.id}', '${entity.extendId}')">删除</button>
					    <input type="hidden" id="id_position_bind_positionId${entity.id}" name="positionId" value="${entity.positionId}" class="form-control" >
					    <input type="hidden" id="id_position_bind_id${entity.id}" name="id" value="${entity.id}" class="form-control" >
				    	</p>
				    	
				  </div>
				  <div class="panel-body">
					  	
					  	<div id="id_collapse_tip_${entity.id}" class="text-center"></div>
					  	<#if 246150346295701504==entity.positionId>
					  	<@refBindBtn viewType=PositionRef.VIEWTYPE_1_ARTICLE.valueInt entity=entity showTip=PositionRef.VIEWTYPE_1_ARTICLE.label />
					  	</#if>
					  	<#-- 
					  	<#if 246150346295701504==entity.positionId>
					  	<@refBindBtn viewType=PositionRef.VIEWTYPE_3_PROFESSION.valueInt entity=entity showTip=PositionRef.VIEWTYPE_3_PROFESSION.label />
				  	  	</#if>
				  	  	 -->
				  	  	<input type="hidden" id="id_hidden_list_loaded${entity.id}" value="0"
				  	  	data-url='/sz0099/ood/personal/profession/ref/manage/findRefLsit'
				  	  	data-baseId="${entity.id}"
				  	  	data-extendId="${entity.extendId}"
				  	  	data-page="0"
				  	  	data-size="20"
				  	  	/>
				  	  	<div id="id_collapse_content_${entity.id}">
				  	  	</div>
					  
				   </div><!--end panel-body-->
				    
				   <div class="panel-footer">
				   <#-- 
				    	<p class="text-center">每个绑定都要点保存哦！>>>
				    	<button type="button" class="btn btn-sm btn-primary pull-right" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存当前技能</button>
				    	</p>
				    -->
				  </div><!--end panel-footer-->
			  </div><!--end panel-->
		</div><#-- 展开与折叠 -->
</div><!--end div wrapper-->
<hr/>
<script>
$(document).ready(function(){

});
</script>
</#macro>

<#-- 技能编辑面板 end -->

<#macro refBindBtn viewType=0 entity=null showTip="文章绑定">
<#assign entityId=entity.id/>
<#assign positionId=entity.positionId/>
<#assign mainId=entity.mainId/>
	<a id="id_btn_position_bind_${viewType}${entityId}" class="btn btn-primary btn-xs" role="button" href="javascript:void(0)"
  	onclick="selectRefs('id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entity.id}','id_hidden_list_loaded${entity.id}', 'id_collapse_tip_${entity.id}')"
  	>
	 <span class="glyphicon glyphicon-plus" aria-hidden="false"></span>
	  ${showTip}
	</a>
  	<input type="hidden" id="id_hidden_refs_loaded_${viewType}${entityId}" value="0" 
  	data-page="0" data-size="20" 
  	data-title=""
  	data-mainNo=""
  	data-url="/sz0099/ood/personal/profession/manage/selectNeedBindRefList"
  	data-reloadUrl='/sz0099/ood/personal/profession/ref/manage/findRefLsit'
  	data-id='${entityId}'
  	data-positionId='${positionId}'
  	data-mainId='${mainId}'
  	data-viewType='${viewType}'
  	/>
	<div id="id_refs_${viewType}${entityId}" class="hidden" >
	
			  <div  id="id_refs_panel_${viewType}${entityId}">
			  <#-- 异步加载文章列表 -->
			  </div>
	 </div>
</#macro>


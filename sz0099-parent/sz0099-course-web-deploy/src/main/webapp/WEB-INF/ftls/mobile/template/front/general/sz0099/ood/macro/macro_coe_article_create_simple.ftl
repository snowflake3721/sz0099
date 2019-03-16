<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_paragraph.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_coeArticleCreate entity=null url="/sz0099/ood/home/article/index/recommend?st=general">   
<!--创建文章，简易版--> 

<@M_tabIndexForCreate currentMenu="" contentModel=entity/>
<@M_tabContentForCreate contentModel=entity/>
<!--课程文章-->

  <!--
  <div class="panel panel-default">
	  <div class="panel-body">
		
	  </div>
  </div>
  -->
<div class="list-group">
  <a href="javascript:void(0)" class="list-group-item active">
    <h4 class="list-group-item-heading">文章发布步骤</h4>
    <p class="list-group-item-text">1.录入文章>>基本信息</p>
	<p class="list-group-item-text">2.录入文章>>标题</p>
	<p class="list-group-item-text">3.录入文章>>段落主体及图片</p>
	<p class="list-group-item-text">4.录入文章>>设置标签</p>
	<p class="list-group-item-text">5.录入文章>>发布</p>
  </a>
</div>

 </#macro>
 
 

<#macro M_tabIndexForCreate currentMenu=null contentModel=null>
  <!--导航开始-->
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/article/manage/merge/baseinfo"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" ><a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/article/manage/merge/baseinfo">基本</a></li>
	    <li role="presentation"><a href="#panel_price" aria-controls="panel_price" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/article/manage/merge/title">标题</a></li>
	    <li role="presentation"><a href="#contentPhoto" aria-controls="contentPhoto" role="tab" data-toggle="tab" data-saveUrl="">内容</a></li>
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
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/article/manage/merge/baseinfo">
    <div class="text-danger text-center">★ 为必填项</div>
    <div class="pull-right">
    <input type="hidden" id="id_mainType_current${contentModel.id}" value="${CoeArticle.MAINTYPE_9_MAIN.valueInt}" 
	  data-url="/sz0099/ood/article/manage/merge/mainType" 
	  data-mainType=""
	  data-id="${contentModel.id}"
	  data-btnMainId="id_btn_btnMainId${contentModel.id}"
	  data-btnMainUnId="id_btn_un_btnMainId${contentModel.id}"
	  >
    <button type="button" id="id_btn_un_btnMainId${contentModel.id}" class="btn btn-primary btn-xs <#if CoeArticle.MAINTYPE_9_MAIN.valueInt!=contentModel.mainType>hidden</#if>"><span class="glyphicon glyphicon-star" aria-hidden="true" style="font-size: 16px;"></span>
    	已是主推
    </button>
    <button type="button" id="id_btn_btnMainId${contentModel.id}" class="btn btn-danger btn-xs <#if CoeArticle.MAINTYPE_9_MAIN.valueInt==contentModel.mainType>hidden</#if>" onclick="mergeForMainType('${contentModel.id}','${CoeArticle.MAINTYPE_9_MAIN.valueInt}', 'id_mainType_current${contentModel.id}')">设为主推</button>
    </div>
    
    <div>
    <button type="button" id="id_btn_confirm2" class="btn btn-warning btn-xs" onclick="commitSingle('id_btn_confirm2','id_hidden_current_panel', '${contentModel.id}')">保存★</button>
    </div>
    <div id="id_messageTip_baseinfo${contentModel.id}" class="text-center"></div>
    	  
    	  <@M_selectCategory entity=contentModel id="id_mainId" isAnsy=true jsFunction="changeCategory"/>
    	  
    	  <div class="form-group form-group-sm">
		    <label for="id_name${contentModel.id}"  class="text-danger">标题(少于32字符)★</label>
		    <input type="text" id="id_name${contentModel.id}" name="name" 
		     onblur="syncTitle(this,'${contentModel.id}')"  
    		 onafterpaste="syncTitle(this,'${contentModel.id}')"
		    value="${contentModel.name}" class="form-control" placeholder="文章标题">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_area_description${contentModel.id}"  class="text-danger">简要描述(少于255字符)★</label>
		    <textarea id="id_area_description${contentModel.id}" rows="8" class="form-control" placeholder="文章描述，少于255字">${contentModel.description}</textarea>
		  </div>
    		<#-- 封面图开始 -->
    			<label class="col-sm-2 control-label text-danger" for="id_cover_file_${contentModel.id}" >封面图★</label>
    			<div class="file-loading">
					<input id="id_cover_file_${contentModel.id}" data-devId="sz0099" data-project="ood" data-module="article" data-variety="article" data-strategy="1" data-position="cover" data-mainId="${contentModel.id}" data-subId="${PhotoCover.SUBID_COVER_HEAD}" type="file" name="files" />
				</div>
				<p class="text-danger">
					<small>
						<em>选择图片（限1张），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</small>
				</p>
				
				<div id="id_cover_photoPreview${PhotoCover.SUBID_COVER_HEAD}" >
				<div id="id_cover_messageTip_preview${PhotoCover.SUBID_COVER_HEAD}" class="text-center"></div>
				<@M_showImageForEdit imageList=contentModel.coverList subId=PhotoCover.SUBID_COVER_HEAD/>
				</div>
				<#-- 封面图结束 -->
				<br/>
				<#-- 头部滚动图开始 -->
				<label class="col-sm-2 control-label text-danger" for="id_banner_file_${contentModel.id}" >头部滚动图★</label>
    			<div class="file-loading">
					<input id="id_banner_file_${contentModel.id}" data-devId="sz0099" data-project="ood" data-module="article" data-variety="article" data-strategy="3" data-position="banner" data-mainId="${contentModel.id}" data-subId="${PhotoBanner.SUBID_BANNER_HEAD}" type="file" name="files" />
				</div>
				<p class="text-danger">
					<small>
						<em>选择图片（限3张），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</small>
				</p>
				
				<div id="id_banner_photoPreview${PhotoBanner.SUBID_BANNER_HEAD}" >
				<div id="id_banner_messageTip_preview${PhotoBanner.SUBID_BANNER_HEAD}" class="text-center"></div>
				<@M_showImageForEdit imageList=contentModel.bannerList subId=PhotoBanner.SUBID_BANNER_HEAD/>
				</div>
				<#-- 头部滚动图结束 -->
    </div>
    <div role="tabpanel" class="tab-pane fade" id="panel_price" data-saveUrl="/sz0099/ood/article/manage/merge/title">
    	<div class="text-danger text-center">★ 为必填项</div>
    	<div id="id_messageTip_price${contentModel.id}" class="text-center"></div>
    	<div class="form-group form-group-sm">
		    <label for="id_penname${contentModel.id}">绰号/笔名，若填写则优先显示此名</label>
		    <input type="text" id="id_penname${contentModel.id}" name="penname" 
		    value="${contentModel.penname}"
		    class="form-control" placeholder="绰号/笔名,优先显示">
		 </div>
		 
		 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(contentModel.preIntroType,0)/>
		 <#-- <@M_dropdownBar id="id_" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false />
		  -->
		 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false />
		 <#-- 
		 <div class="form-group form-group-sm">
		    <label for="id_preIntro${contentModel.id}">导语标记(自定义，滚播画面展示)</label>
		    <input type="text" id="id_preIntro${contentModel.id}" name="preIntro" 
		    value="${contentModel.preIntro}"
		    class="form-control" placeholder="导语，少于10字">
		 </div>
		  -->
		 <div class="form-group form-group-sm">
		    <label for="id_title${contentModel.id}"  class="text-danger">主标题(少于32字)★</label>
		    <input type="text" id="id_title${contentModel.id}" name="title" 
		    value="${contentModel.title}"
		    class="form-control" placeholder="主标题，少于32字">
		 </div>
		 <div class="form-group form-group-sm">
		    <label for="id_subTitle${contentModel.id}"><small>子标题，少于32字</small></label>
		    <input type="text" id="id_subTitle${contentModel.id}" name="subTitle" 
		    value="${contentModel.subTitle}"
		    class="form-control" placeholder="子标题，少于32字">
		 </div>
		<#-- 
    	<#include "/mobile/template/front/default/home/panel_footing.ftl">
    	 -->
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentPhoto" data-saveUrl="">
    <div class="text-danger text-center">★ 为必填项</div>
    <br/>
    <p><strong>1.撰写段落，最多允许<b> 19 </b>个 段落 </strong></p>
    <p><strong>2.每段最多  5 张图片</strong></p>
    	<div id="id_messageTip_paragraph${contentModel.id}" class="text-center"></div>
    	<!--段落大组件开始-->
    	<#-- id=contentModel.id 传递productId -->
    	<@M_paragraphBtn btnId="btn1" id=contentModel.id/>
    	<div id="id_contentPhoto_content${contentModel.id}"></div>
    	<@M_paragraphBtn btnId="btn2" id=contentModel.id/>
    	<!--段落大组件结束-->
    	
    	
    	
    	
    	<#-- 
    	<#include "/mobile/template/front/default/home/panel_biking.ftl">
    	-->
    </div>
    <div role="tabpanel" class="tab-pane fade" id="contentTag" data-saveUrl="">
    	
    	<br/>
    	<p><strong>1.为文章增加标签，最多加<b> 5  </b>个</strong></p>
    	<p><strong>2.标签长度越短越好，且必须小于15个字符</strong></p>
    	<div id="id_contentTag_tag${contentModel.id}" class="container">
    		<h6>设置标签：</h6>
			<div id="id_messageTip_tag" class="text-center"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group input-group-sm">
				      		<input type="text" class="form-control" id="id_tag${contentModel.id}" name="name" placeholder="输入标签名称" >
				      		<span class="input-group-btn">
				      		<button class="btn btn-primary btn-xs" type="button" onclick="saveTag('${contentModel.id}','/sz0099/ood/article/manage/tag/add')">添加</button>
				      		<button class="btn btn-warning btn-xs" type="button" onclick="clearTag('${contentModel.id}')">清空</button>
				      		</span>		
				    </div><!-- /input-group -->
				</div>
			</div>
			<br/>
			<p id="id_p_tags${contentModel.id}">
				<@M_showTagList productTagList=contentModel.articleTagList onlyView=false deletedUrl="/sz0099/ood/article/manage/tag/delete"/>
			</p>
    	<br/>
    	<br/>
    	</div>
    	
    	<#-- 
    	<#include "/mobile/template/front/default/home/panel_travel.ftl">
    	-->
    </div>
    
    <#if contentModel.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
    <div role="tabpanel" class="tab-pane fade" id="panel_publish" data-saveUrl="">
    	<br/>
    	<p><strong>1.请确认[1-4]步骤已填写完毕，无误！<div class="text-danger text-center">★ 为必填项</div></strong></p>
    	<p><strong>2.点击发布按钮</strong></p>
    	<div id="id_messageTip_publish" class="text-center"></div>
    	<ul class="list-inline text-center">
		<li class="text-right"><a href="javascript:void(0)" onclick="mergeForPublish('${contentModel.id}','/sz0099/ood/article/manage/merge/publish')" class="btn btn-danger btn-lg " role="button">发布★</a></li>
		</ul>
		<br/>
		<br/>
    </div>
    </#if>
</div>
</form>
<p class="text-right">
<button type="button" id="id_btn_confirm" class="btn btn-warning" onclick="commitSingle('id_btn_confirm','id_hidden_current_panel', '${contentModel.id}')">保存当前★</button>
<#-- <button type="button" class="btn btn-danger">提交全部</button> -->
</p>
</#macro>

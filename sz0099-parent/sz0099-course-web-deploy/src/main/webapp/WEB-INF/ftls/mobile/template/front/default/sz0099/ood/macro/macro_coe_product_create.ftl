<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_paragraph.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_coeProductCreate product=null url="/sz0099/ood/product/index">   
<!--创建产品--> 

<@M_tabIndexForCreate currentMenu="" contentModel=product/>
<@M_tabContentForCreate contentModel=product/>
<!--课程产品-->

  <!--
  <div class="panel panel-default">
	  <div class="panel-body">
		
	  </div>
  </div>
  -->
<div class="list-group">
  <a href="javascript:void(0)" class="list-group-item active">
    <h4 class="list-group-item-heading">产品发布步骤</h4>
    <p class="list-group-item-text">1.录入产品>>基本信息</p>
	<p class="list-group-item-text">2.录入产品>>设置价格</p>
	<p class="list-group-item-text">3.录入产品>>主体内容及图片</p>
	<p class="list-group-item-text">4.录入产品>>设置标签</p>
	<p class="list-group-item-text">5.录入产品>>发布</p>
  </a>
</div>

 </#macro>
 
 

<#macro M_tabIndexForCreate currentMenu=null contentModel=null>
  <!--导航开始-->
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/product/manage/merge/baseinfo"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" ><a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/product/manage/merge/baseinfo">基本</a></li>
	    <li role="presentation"><a href="#panel_price" aria-controls="panel_price" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/product/manage/merge/price">价格</a></li>
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
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/product/manage/merge/baseinfo">
    <div id="id_messageTip_baseinfo${contentModel.id}" class="text-center"></div>
    <div class="pull-right"><button type="button" id="id_btn_confirm2" class="btn btn-warning btn-xs" onclick="commitSingle('id_btn_confirm2','id_hidden_current_panel', '${contentModel.id}')">保存</button></div>
    	  
    	  <@M_selectCategory entity=contentModel id="id_mainId" isAnsy=true jsFunction="changeCategory"/>
    	  
    	  <div class="form-group form-group-sm">
		    <label for="id_name${contentModel.id}">名称(简短，少于15字符)</label>
		    <input type="text" id="id_name${contentModel.id}" name="name" value="${contentModel.name}" class="form-control" placeholder="产品名称">
		  </div>
		  
    	  <div class="form-group form-group-sm">
		    <label for="id_title${contentModel.id}">产品标题(少于45字符)</label>
		    <input type="text" id="id_title${contentModel.id}" name="title" value="${contentModel.title}" class="form-control" placeholder="产品标题">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_area_description${contentModel.id}">简要描述(少于200字符)</label>
		    <textarea id="id_area_description${contentModel.id}" rows="8" class="form-control" placeholder="产品描述，少于200字">${contentModel.description}</textarea>
		    <#-- <div id="id_div_description"></div> -->
		  </div>
    	<#-- <#include "/mobile/template/front/default/home/panel_home.ftl"> -->
    		<#-- 封面图开始 -->
    			<label class="col-sm-2 control-label" for="id_cover_file_${contentModel.id}">封面图</label>
    			<div class="file-loading">
					<input id="id_cover_file_${contentModel.id}" data-devId="sz0099" data-project="ood" data-module="product" data-variety="product" data-strategy="1" data-position="cover" data-mainId="${contentModel.id}" data-subId="${PhotoCover.SUBID_COVER_HEAD}" type="file" name="files" />
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
				<label class="col-sm-2 control-label" for="id_banner_file_${contentModel.id}">头部滚动图</label>
    			<div class="file-loading">
					<input id="id_banner_file_${contentModel.id}" data-devId="sz0099" data-project="ood" data-module="product" data-variety="product" data-strategy="3" data-position="banner" data-mainId="${contentModel.id}" data-subId="${PhotoBanner.SUBID_BANNER_HEAD}" type="file" name="files" />
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
    <div role="tabpanel" class="tab-pane fade" id="panel_price" data-saveUrl="/sz0099/ood/product/manage/merge/price">
    	<div id="id_messageTip_price${contentModel.id}" class="text-center"></div>
    	<div class="form-group form-group-sm">
		    <label for="id_priceOri${contentModel.id}">原价，单位[分], 15元，即输入1500</label>
		    <input type="text" id="id_priceOri${contentModel.id}" name="priceOri" 
		    value="${contentModel.priceOri}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="原价，例：1500">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_priceCur${contentModel.id}">现价，单位[分], 9元，即输入900</label>
		    <input type="text" id="id_priceCur${contentModel.id}" name="priceCur"
		    value="${contentModel.priceCur}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="现价，例：900">
		  </div>
		  
		  <#assign currentStrategy=CoeProduct.STRATEGY.getContext(contentModel.strategy,3)/>
		  <@M_dropdownBar id="id_" propertyContext=CoeProduct.STRATEGY current=currentStrategy readonly=true instructionDivId="instruction_id_strategy" instructionHtml="<a href='javascript:void(0)' onclick='showInstruction(\"instruction_id_strategy\",\"产品优惠策略说明\")'>查看说明</a>"/>
		  <br/>
		  
		  <#assign gradeProperty=L.getPropertyContext('grade','dml.sz0099.course.app.persist.entity.product.CoeProduct')>
		   
		  <#assign currentGrade=gradeProperty.getContext(contentModel.grade,0)/>
		  <@M_dropdownBar id="id_" propertyContext=gradeProperty current=currentGrade readonly=true instructionDivId="instruction_id_grade" instructionHtml="<a href='javascript:void(0)' onclick='showInstruction(\"instruction_id_grade\",\"等级优惠说明\")'>查看说明</a>"/>
		  <br/>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_rates${contentModel.id}"><span class="text-danger">最低折扣系数:范围【35-100】</span><span onclick='showInstruction("instruction_id_rates","最低折扣说明")'>查看说明</span></label>
		    <input type="text" id="id_rates${contentModel.id}" name="rates" value="${contentModel.rates}" class="form-control" placeholder="折扣系数:35-100之间数字">
		  </div>
		  
		  <#assign currentPullMethod=CoeProduct.PULL_METHOD.getContext(contentModel.pullMethod,0)/>
		  <@M_dropdownBar id="id_" propertyContext=CoeProduct.PULL_METHOD current=currentPullMethod readonly=true instructionDivId="instruction_id_pullMethod" instructionHtml="<a href='javascript:void(0)' onclick='showInstruction(\"instruction_id_pullMethod\",\"提取方式说明\")'>查看说明</a>"/>
		  <br/>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_link${contentModel.id}">提取链接 <span class="text-danger" onclick='showInstruction("instruction_id_link","提取链接说明")'>查看说明</span></label>
		    <input type="text" id="id_link${contentModel.id}" name="link" value="${contentModel.link}"  class="form-control" placeholder="提取链接">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_pullCode${contentModel.id}">提取密码 <span class="text-danger" onclick='showInstruction("instruction_id_pullCode","提取密码说明")'>注意事项</span></label>
		    <input type="text" id="id_pullCode${contentModel.id}" name="pullCode" value="${contentModel.pullCode}"  class="form-control" placeholder="提取密码">
		  </div>
		 
		  <div id="instruction_id_link" class="hidden">
		        <ul>
		  	    <li>用户下单后，下载链接将展示给用户，客服需要确认用户已付款，操作【发货】之后，用户将看到此链接与提取码。</li>
		  		<li>采用云盘提取方式时，链接 容易失效，失效后要及时更新此链接。</li>
		  		<li>同时，用户若提取时链接失效，需要补发给客户新链接，系统不会更新用户订单里的链接。</li>
		  		<li>提取方式若选择：<span class="text-danger">【自主下载、云盘】时，此项必填</span></li>
		  		</ul>
		  </div>
		  
		  <div id="instruction_id_pullCode" class="hidden">
		  		<ul>
		  		<li>只有当客服操作【发货】之后，提取密码才会展示给用户，默认30天有效期。</li>
		  		<li>超过有效期后，提取密码将失效<br/></li>
		  		<li>另外，在有效期内，采用云盘提取方式时（如百度云），提取密码会失效，请及时更新并通知用户。</li>
		  		</ul>
		  </div>
		  
		  <div id="instruction_id_rates" class="hidden">
		  		<ul>
		  		<li>折扣系数取值范围【35-100】</li>
		  		<li>例如：输入 100 表示无折扣， 90 表示九折, 85 表示八五折...</li>
		  		<li>只允许输入数字，最低 35</li>
		  		<li>此折扣是在现价基础上打的【折上折】</li>
		  		<li>此折扣值是最低价格保护值，成交价格不会低于该折扣的限定</li>
		  		<li>优惠策略 选择为 【等级or不限】时，该值有效</li>
		  		</ul>
		  </div>
		   
		<#-- 
    	<#include "/mobile/template/front/default/home/panel_footing.ftl">
    	 -->
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentPhoto" data-saveUrl="">
    <br/>
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
    	<p><strong>1.为产品增加标签，最多加<b> 5  </b>个</strong></p>
    	<p><strong>2.标签长度越短越好，且必须小于15个字符</strong></p>
    	<div id="id_contentTag_tag${contentModel.id}" class="container">
    		<h6>设置标签：</h6>
			<div id="id_messageTip_tag" class="text-center"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group input-group-sm">
				      		<input type="text" class="form-control" id="id_tag${contentModel.id}" name="name" placeholder="输入标签名称" >
				      		<span class="input-group-btn">
				      		<button class="btn btn-primary btn-xs" type="button" onclick="saveTag('${contentModel.id}','/sz0099/ood/product/manage/tag/add')">添加</button>
				      		<button class="btn btn-warning btn-xs" type="button" onclick="clearTag('${contentModel.id}')">清空</button>
				      		</span>		
				    </div><!-- /input-group -->
				</div>
			</div>
			<br/>
			<p id="id_p_tags${contentModel.id}">
				<@M_showTagList productTagList=contentModel.proTagList onlyView=false deletedUrl="/sz0099/ood/product/manage/tag/delete"/>
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
    	<p><strong>1.请确认[1-4]步骤已填写完毕，无误！</strong></p>
    	<p><strong>2.点击发布按钮</strong></p>
    	<div id="id_messageTip_publish" class="text-center"></div>
    	<ul class="list-inline text-center">
		<li class="text-right"><a href="javascript:void(0)" onclick="mergeForPublish('${contentModel.id}')" class="btn btn-danger btn-lg " role="button">发布</a></li>
		</ul>
		<br/>
		<br/>
    </div>
    </#if>
</div>
</form>
<p class="text-right">
<button type="button" id="id_btn_confirm" class="btn btn-warning" onclick="commitSingle('id_btn_confirm','id_hidden_current_panel', '${contentModel.id}')">保存当前</button>
<#-- <button type="button" class="btn btn-danger">提交全部</button> -->
</p>
</#macro>

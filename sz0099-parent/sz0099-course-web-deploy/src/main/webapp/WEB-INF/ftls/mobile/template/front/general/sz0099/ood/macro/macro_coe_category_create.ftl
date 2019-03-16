<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_categoryBtn btnId="btn1" id="001" divWrapperId="" tipId="001">
<div id="id_categ_${btnId}${id}" class="btn-group btn-group-xs btn-group-justified" role="group">

<a class="btn btn-xs btn-primary" href="javascript:void(0)" onclick="addSingle('${id}','${CategoryExtend.TOP_PARENTID}','/sz0099/ood/category/manage/add','${divWrapperId}', '${tipId}')">添加大类</a>
<a class="btn btn-xs btn-warning" href="javascript:void(0)" onclick="refresh('${id}','/sz0099/ood/category/manage/editListUI', '${tipId}')">刷新分类</a>
<a class="btn btn-xs btn-danger" href="javascript:void(0)" onclick="deleteAll('${id}','${CategoryExtend.TOP_PARENTID}','/sz0099/ood/category/manage/deleteAll', '${divWrapperId}', '${tipId}')">全部删除</a>
</div>
<br/>
</#macro>

<#macro M_categoryExtendCreate entity=null url="">   
<!--项目类别--> 
<#if entity!>
	<#if entity.success==CategoryExtend.SUCCESS_YES>
	<@M_tabIndexForCreate currentMenu="" entity=entity/>
	<@M_tabContentForCreate entity=entity/>
	<div class="list-group">
	  <a href="javascript:void(0)" class="list-group-item active">
	    <h4 class="list-group-item-heading">品种类别</h4>
	    <p class="list-group-item-text">1.录入品种>>基本信息</p>
	  </a>
	</div>
	<#else>
	<p class="text-danger text-center">${entity.respMsg}</p>
	<input type="hidden" value="${entity.respCode}" name="respCode"/>
	</#if>
</#if>
 </#macro>
 
 

<#macro M_tabIndexForCreate currentMenu=null entity=null>
  <!--导航开始-->
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/category/extend/manage/merge"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" ><a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/product/manage/merge/baseinfo">基本</a></li>
	   
	   
	    <li role="presentation"><a href="#contentPhoto" aria-controls="contentPhoto" role="tab" data-toggle="tab" data-saveUrl="">类别</a></li>
	   <#--   
	      <li role="presentation"><a href="#panel_price" aria-controls="panel_price" role="tab" data-toggle="tab" data-saveUrl="/sz0099/ood/product/manage/merge/price">价格</a></li>
	   	  <li role="presentation"><a href="#contentTag" aria-controls="contentTag" role="tab" data-toggle="tab" data-saveUrl="">标签</a></li>
	     
	     <#if entity.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
	      <li role="presentation"><a href="#panel_publish" aria-controls="panel_publish" role="tab" data-toggle="tab" data-saveUrl="">发布</a></li>
	     </#if>
	     -->
</ul>
 </#macro>
 
<#macro M_tabContentForCreate entity=null>
<!-- Tab panes -->
<form>
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/category/extend/manage/merge">
    <div id="id_messageTip_baseinfo${entity.id}" class="text-center"></div>
    <div class="pull-right"><button type="button" id="id_btn_confirm2" class="btn btn-warning btn-xs" onclick="commitSingle('id_btn_confirm2','id_hidden_current_panel', '${entity.id}')">保存</button></div>
    	  <div class="form-group form-group-sm">
		    <label for="id_title${entity.id}">开发者id(如: sz0099)</label>
		    <input type="text" id="id_devId${entity.id}" name="devId" value="${entity.devId}" class="form-control" placeholder="开发者id，如：sz0099">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_project${entity.id}">所属项目(字母，如: course)</label>
		    <input type="text" id="id_project${entity.id}" name="project" value="${entity.project}" class="form-control" placeholder="所属项目，如：course">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_module${entity.id}">所属模块(字母，如: product)</label>
		    <input type="text" id="id_module${entity.id}" name="module" value="${entity.module}" class="form-control" placeholder="所属模块，如：product">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_variety${entity.id}">所属品种(字母，如: product--产品, article--文章)</label>
		    <input type="text" id="id_variety${entity.id}" name="variety" value="${entity.variety}" class="form-control" placeholder="所属类别，例：article">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_position${entity.id}">所属位置(如：系统固有，用户自定义，圈子)</label>
		    <input type="text" id="id_position${entity.id}" name="position" value="${entity.position}" class="form-control" placeholder="所属位置，例：system">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_domain${entity.id}">领域(domain全名)</label>
		    <input type="text" id="id_domain${entity.id}" name="domain" value="${entity.domain}" class="form-control" placeholder="领域对象全名">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_mainMaxnum${entity.id}">品种主类下所允许的最大总类目，例: 20</label>
		    <input type="text" id="id_mainMaxnum${entity.id}" name="mainMaxnum" 
		    value="${entity.mainMaxnum}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="品种最大总类目，例: 20">
		  </div>
		  
		  <div class="form-group form-group-sm">
		    <label for="id_subMaxnum${entity.id}">每个类下的子类数目，例: 5</label>
		    <input type="text" id="id_subMaxnum${entity.id}" name="subMaxnum"
		    value="${entity.subMaxnum}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="最大子类数目,例：5">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_depthMaxnum${entity.id}">品种最大层级数目，例: 4</label>
		    <input type="text" id="id_depthMaxnum${entity.id}" name="depthMaxnum"
		    value="${entity.depthMaxnum}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="品种最大层级数目，例: 4">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_refMaxnum${entity.id}">品种所能挂载产品总数，例: 90000</label>
		    <input type="text" id="id_refMaxnum${entity.id}" name="refMaxnum"
		    value="${entity.refMaxnum}"
		    onkeyup="keyPressPositive(this)"  
    		onafterpaste="onAfterPastePositive(this)"
		    class="form-control" placeholder="品种所能挂载产品总数，例: 90000">
		  </div>
		  <div class="form-group form-group-sm">
		    <label for="id_positionId${entity.id}">positionId</label>
		    <input type="text" id="id_positionId${entity.id}" name="positionId"
		    value="${entity.positionId}"
		    class="form-control" placeholder="位置ID"
		    readonly=true>
		    <button type="button" id="id_clipboard_${entity.id}" class="btn btn-xs btn-success" data-clipboard-action="copy" data-clipboard-target="#id_positionId${entity.id}">复制</button>
		    
		  </div>
		  
    		<#-- 封面图开始
    			<label class="col-sm-2 control-label" for="id_cover_file_${entity.id}">封面图</label>
    			<div class="file-loading">
					<input id="id_cover_file_${entity.id}" data-devId="sz0099" data-project="ood" data-module="product" data-variety="product" data-strategy="1" data-position="cover" data-mainId="${entity.id}" data-subId="${PhotoCover.SUBID_COVER_HEAD}" type="file" name="files" />
				</div>
				<p class="text-danger">
					<small>
						<em>选择图片（限1张），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</small>
				</p>
				
				<div id="id_cover_photoPreview${PhotoCover.SUBID_COVER_HEAD}" >
				<div id="id_cover_messageTip_preview${PhotoCover.SUBID_COVER_HEAD}" class="text-center"></div>
				<@M_showImageForEdit imageList=entity.coverList subId=PhotoCover.SUBID_COVER_HEAD/>
				</div>
				封面图结束 -->
				<br/>
				<#-- 头部滚动图开始 
				<label class="col-sm-2 control-label" for="id_banner_file_${entity.id}">头部滚动图</label>
    			<div class="file-loading">
					<input id="id_banner_file_${entity.id}" data-devId="sz0099" data-project="ood" data-module="product" data-variety="product" data-strategy="3" data-position="banner" data-mainId="${entity.id}" data-subId="${PhotoBanner.SUBID_BANNER_HEAD}" type="file" name="files" />
				</div>
				<p class="text-danger">
					<small>
						<em>选择图片（限3张），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</small>
				</p>
				
				<div id="id_banner_photoPreview${PhotoBanner.SUBID_BANNER_HEAD}" >
				<div id="id_banner_messageTip_preview${PhotoBanner.SUBID_BANNER_HEAD}" class="text-center"></div>
				<@M_showImageForEdit imageList=entity.bannerList subId=PhotoBanner.SUBID_BANNER_HEAD/>
				</div>
				 头部滚动图结束 -->
    </div>
    <div role="tabpanel" class="tab-pane fade" id="panel_price" data-saveUrl="/sz0099/ood/product/manage/merge/price">
    	<div id="id_messageTip_price${entity.id}" class="text-center"></div>
		<#-- 
    	<#include "/mobile/template/front/general/home/panel_footing.ftl">
    	 -->
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentPhoto" data-saveUrl="">
    <br/>
    	<@M_categoryBtn btnId="btn1" id=entity.id divWrapperId="id_contentPhoto_content"+entity.id tipId="id_messageTip_category"+entity.id/>
    	<div id="id_messageTip_category${entity.id}" class="text-center"></div>
    	<div id="id_contentPhoto_content${entity.id}" ></div>
    	<@M_categoryBtn btnId="btn2" id=entity.id divWrapperId="id_contentPhoto_content"+entity.id tipId="id_messageTip_category"+entity.id/>
    	<#-- 
    	<#include "/mobile/template/front/general/home/panel_biking.ftl">
    	-->
    </div>
    <div role="tabpanel" class="tab-pane fade" id="contentTag" data-saveUrl="">
    	
    	<br/>
    	<p><strong>1.为产品增加标签，最多加<b> 5  </b>个</strong></p>
    	<p><strong>2.标签长度越短越好，且必须小于15个字符</strong></p>
    	<div id="id_contentTag_tag${entity.id}" class="container">
    		<h6>设置标签：</h6>
			<div id="id_messageTip_tag" class="text-center"></div>
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group input-group-sm">
				      		<input type="text" class="form-control" id="id_tag${entity.id}" name="name" placeholder="输入标签名称" >
				      		<span class="input-group-btn">
				      		<button class="btn btn-primary btn-xs" type="button" onclick="saveTag('${entity.id}','/sz0099/ood/product/manage/tag/add')">添加</button>
				      		<button class="btn btn-warning btn-xs" type="button" onclick="clearTag('${entity.id}')">清空</button>
				      		</span>		
				    </div><!-- /input-group -->
				</div>
			</div>
			<br/>
			<p id="id_p_tags${entity.id}">
				<@M_showTagList productTagList=entity.proTagList onlyView=false deletedUrl="/sz0099/ood/product/manage/tag/delete"/>
			</p>
    	<br/>
    	<br/>
    	</div>
    	
    	<#-- 
    	<#include "/mobile/template/front/general/home/panel_travel.ftl">
    	-->
    </div>
    
    <#if entity.publishStatus==CoeProduct.PUBLISH_STATUS_DRAFT.valueInt>
    <div role="tabpanel" class="tab-pane fade" id="panel_publish" data-saveUrl="">
    	<br/>
    	<p><strong>1.请确认[1-4]步骤已填写完毕，无误！</strong></p>
    	<p><strong>2.点击发布按钮</strong></p>
    	<div id="id_messageTip_publish" class="text-center"></div>
    	<ul class="list-inline text-center">
		<li class="text-right"><a href="javascript:void(0)" onclick="mergeForPublish('${entity.id}')" class="btn btn-danger btn-lg " role="button">发布</a></li>
		</ul>
		<br/>
		<br/>
    </div>
    </#if>
</div>
</form>
<p class="text-right">
<button type="button" id="id_btn_confirm" class="btn btn-warning" onclick="commitSingle('id_btn_confirm','id_hidden_current_panel', '${entity.id}')">保存当前</button>
<#-- <button type="button" class="btn btn-danger">提交全部</button> -->
</p>
</#macro>




 <#macro M_categoryListEdit content=null entity=null>
 <#if content!>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_categoryEdit entity=ce />
		</#list>
	<#else>
	<@M_categoryEdit entity=entity />
	</#if>
 </#if>
 </#macro>
 
<#macro M_categoryPageEdit page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_categoryEdit entity=ce />
		</#list>
	<#else>
	<@M_categoryEdit entity=entity />
	</#if>
<#else>
<@M_categoryEdit entity=entity />
</#if>
</#macro>

<#-- 大类编辑面板 begin -->
<#macro M_categoryEdit entity>
<div id="category_${entity.id}"> <!--div wrapper-->
	
	<div class="panel panel-danger" id="id_panel_categoryId${entity.id}" name="category">
		  <div class="panel-heading">
		    	<p class="panel-title text-center">
		    	<button type="button" class="btn btn-xs btn-primary pull-left" onclick="saveSingle('${entity.id}','/sz0099/ood/category/manage/mergeSingle', 'id_tip_${entity.id}')">保存</button>
		    	<strong>#<span id="id_category_orderSeq_show${entity.id}">${entity.orderSeq}</span></strong>
		    	<#assign pname = entity.name />
		    	
		    	<span id="id_category_name_show${entity.id}">${getSubstring(pname,10,'编辑分类')}</span>
		    	<button type="button" class="btn btn-xs btn-danger pull-right" onclick="deleteSingle('${entity.id}', '/sz0099/ood/category/manage/deleteSingle','id_tip_${entity.id}','category_${entity.id}')">删除</button>
			    <input type="hidden" id="id_category_parentId${entity.id}" name="parentId" value="${entity.parentId}" class="form-control" >
			    <input type="hidden" id="id_categoryId${entity.id}" name="id" value="${entity.id}" class="form-control" >
		    	</p>
		    	
		  </div>
		  <div class="panel-body">
		  <div id="id_tip_${entity.id}" class="text-center"></div>
		  	  <div class="form-group form-group-sm">
			    <label for="id_category_orderSeq${entity.id}">分类排序（数字，例如：1,2,3...）</label>
			    <input type="text" id="id_category_orderSeq${entity.id}" name="orderSeq" 
			    value="${entity.orderSeq}" class="form-control" 
			    onkeyup="keyPressPositive(this)"  
				onafterpaste="onAfterPastePositive(this)"
				onchange="onChangeShow('id_category_orderSeq_show${entity.id}',this.value)"
			    placeholder="分类排序，输入数字">
			  </div>
			  
			  <div class="form-group form-group-sm">
			    <label for="id_category_name${entity.id}">分类名称(英文少于15字母，汉字少于5个字)</label>
			    <input type="text" id="id_category_name${entity.id}" 
			    name="name" 
			    onchange="onChangeShow('id_category_name_show${entity.id}',this.value,10)"
			    value="${entity.name}" class="form-control" placeholder="分类名称，如：技术">
			  </div>
			  
			  <div class="form-group form-group-sm">
			    <label for="id_category_code${entity.id}">分类代码([0-9a-zA-Z_],少于30字符)</label>
			    <input type="text" id="id_category_code${entity.id}" name="code" value="${entity.code}" class="form-control" placeholder="分类代码， 如：product_it">
			  </div>
			
				<a id="id_a_subcategory${entity.id}" class="btn btn-primary btn-xs" role="button" data-toggle="collapse" href="#id_subcategory_${entity.id}" aria-expanded="false" aria-controls="id_subcategory_${entity.id}">
				 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
				   子类展开
				</a>
				
				<div id="id_subcategory_${entity.id}" class="panel-collapse collapse">
				<p class="text-right">
				<a id="id_a_subcategory_refresh${entity.id}" class="btn btn-warning btn-xs" role="button" 
				onclick="refreshSub('${entity.extendId}','${entity.id}','/sz0099/ood/category/manage/refreshSub','id_wrapper_subcategory_${entity.id}', 'id_tip_subcategory_${entity.id}')"
				>
				 <span class="glyphicon glyphicon-refresh" aria-hidden="false"></span>
				   刷新子类
				</a>
				</p>
												
				<div id="id_tip_subcategory_${entity.id}" class="text-center"></div>
				<div id="id_wrapper_subcategory_${entity.id}">
				
				<@M_subCategoryInput entity=entity tipId="id_tip_subcategory_"+entity.id/>
				<@M_subCategoryEdit entity=entity deep=0 topId=entity.id wrapperId="id_wrapper_subcategory_"+entity.id editWrapperId="id_edit_wrapper"+entity.id tipId="id_tip_subcategory_"+entity.id/>
				</div>
				<button type="button" class="btn btn-xs btn-warning"
				 onclick="addSingleSub('${entity.extendId}','${entity.id}','/sz0099/ood/category/manage/addSub','id_wrapper_subcategory_${entity.id}', 'id_tip_subcategory_${entity.id}')"
				 >添加子类</button>
				 
				
				</div>
				
		    </div><!--end panel-body-->
		    
		    <div class="panel-footer">
		    	<p class="text-center">每个大类都要点保存哦！-->
		    	<button type="button" class="btn btn-sm btn-primary pull-right" onclick="saveSingle('${entity.id}','/sz0099/ood/category/manage/mergeSingle', 'id_tip_${entity.id}')">保存当前分类</button>
		    	</p>
		    </div>
	  </div><!--end panel-->
</div><!--end div wrapper-->
<script>
$(document).ready(function(){

});
</script>
</#macro>

<#-- 大类编辑面板 end -->
<#-- 子类编辑，1弹出当前类下的所有子类、孙子类，2 选择其中的一个类别，在修改提示框中进行修改保存，名称、代码、父类 -->
<#macro M_subCategoryEdit entity deep=0 topId="" wrapperId="" tipId="" editWrapperId="">

<#if entity!>
<#if deep==0>

<ul class="list-group" id="id_ul_subcategory_${entity.id}">
</#if>
	<#assign children=entity.children/>
	<#if children! && children?size gt 0>
	<#if deep gt 0>
	<ul class="list-group" id="id_ul_subcategory_${entity.id}">
	</#if>
		<#list children as child>
			<#assign clazz=getClazzByDepth(deep)/>
			<li class="list-group-item list-group-item-${clazz}" id="id_subcategory_${child.id}">
			<span class="text-danger pull-left">
				<span class="glyphicon glyphicon-trash" aria-hidden="true" onclick="deleteSingle('${child.id}', '/sz0099/ood/category/manage/deleteSingle','${tipId}','id_subcategory_${child.id}')">&nbsp;</span>
			</span>
			<span class="">${getSubstring(child.name,10)} - ${child.orderSeq}</span> 
			
			<span class="text-info pull-right">
			<#if depth lt 1>
			<span class="glyphicon glyphicon-plus" aria-hidden="true"
			onclick="addSingleSub('${child.extendId}','${child.id}','/sz0099/ood/category/manage/addSub','${wrapperId}', '${tipId}')"
			></span>
			&nbsp;
			</#if>
			<span class="glyphicon glyphicon-edit" aria-hidden="true"
			onclick="editSingleSub('${child.extendId}','${child.id}', '${topId}', '/sz0099/ood/category/manage/mergeSingle','${wrapperId}','${editWrapperId}', '${tipId}')"
			></span>
			</span>
			
			<input type="hidden" id="id_category_orderSeq${child.id}" name="orderSeq" value="${child.orderSeq}" />
			<input type="hidden" id="id_category_name${child.id}" name="name" value="${child.name}" />
			<input type="hidden" id="id_category_parent_name${child.id}" name="parent.name" value="${entity.name}" />
			<input type="hidden" id="id_category_code${child.id}" name="code" value="${child.code}" />
			<input type="hidden" id="id_category_parentId${child.id}" name="parentId" value="${child.parentId}" />
			
			</li>
			
			<#assign nextEntity=child.children />
			<#if nextEntity! && nextEntity?size gt 0>
			<li class="list-group-item list-group-item-${clazz}">
			<@M_subCategoryEdit entity=child deep=deep+1 topId=topId wrapperId=wrapperId editWrapperId=editWrapperId tipId=tipId/>
			</li>
			</#if>
		</#list>
	<#if deep gt 0>
	</ul>
	</#if>
	</#if>
<#if depth==0>
</ul>
</#if>
</#if>
</#macro>


<#macro M_subCategoryInput entity tipId="" >
<div id="id_edit_wrapper${entity.id}" class="hidden">
<h5 class="text-center"><strong>修改子类</strong></h5>
<input type="hidden" value="${entity.id}" id="hidden_id_subcategory_id${entity.id}" name="id" />
  <div class="form-group form-group-sm">
    <label for="id_subcategory_orderSeq${entity.id}">子类排序（数字，例如：1,2,3...）</label>
    <input type="text" id="id_subcategory_orderSeq${entity.id}" name="orderSeq" 
    value="${entity.orderSeq}" class="form-control" 
    onkeyup="keyPressPositive(this)"  
	onafterpaste="onAfterPastePositive(this)"
	onchange="onChangeShow('id_subcategory_orderSeq_show${entity.id}',this.value)"
    placeholder="子类排序，输入数字">
  </div>
  
  <div class="form-group form-group-sm">
    <label for="id_subcategory_name${entity.id}">子类名称(英文少于15字母，汉字少于5个字)</label>
    <input type="text" id="id_subcategory_name${entity.id}" 
    name="name" 
    onchange="onChangeShow('id_subcategory_name_show${entity.id}',this.value,10)"
    value="${entity.name}" class="form-control" placeholder="子类名称，如：技术">
  </div>
  
  <div class="form-group form-group-sm">
    <label for="id_subcategory_code${entity.id}">子类代码([0-9a-zA-Z_],少于30字符)</label>
    <input type="text" id="id_subcategory_code${entity.id}" name="code" value="${entity.code}" class="form-control" placeholder="子类代码， 如：product_it">
  </div>
  <div class="form-group form-group-sm">
  	<@M_dropdownBarCategory current=entity category=entity id="id_subcategory_parentId"/>
  </div>
  <p class="text-right">
  <button type="button" class="btn btn-xs btn-info"
  onclick="doEditSingleSub('${entity.extendId}',$('#hidden_id_subcategory_id${entity.id}').val(), '${entity.id}', '/sz0099/ood/category/manage/mergeSingle', '${tipId}')"
  >确认子类修改</button></p>
 </div>
</#macro>


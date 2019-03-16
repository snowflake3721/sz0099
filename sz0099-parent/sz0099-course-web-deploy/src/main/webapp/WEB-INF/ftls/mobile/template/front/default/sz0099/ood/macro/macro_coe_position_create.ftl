<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_position_search.ftl">

<#macro M_positionBtn btnId="btn1" id="001" divWrapperId="" tipId="001">
<div id="id_categ_${btnId}${id}" class="btn-group btn-group-xs btn-group-justified" role="group">
	<a class="btn btn-xs btn-primary" href="javascript:void(0)" onclick="addSingle('${id}','id_subId${id}','/sz0099/ood/position/manage/add','${divWrapperId}', '${tipId}')">添加位置</a>
	<a class="btn btn-xs btn-warning" href="javascript:void(0)" onclick="refresh('#contentPhoto','id_contentPhoto_content${entity.id}', '/sz0099/ood/position/manage/editListUI', '${tipId}')">刷新位置</a>
	<a class="btn btn-xs btn-danger" href="javascript:void(0)" onclick="deleteAll('${id}','id_subId${id}','/sz0099/ood/position/manage/deleteAll', '${divWrapperId}', '${tipId}')">全部删除</a>
</div>
<br/>
</#macro>

<#macro M_positionExtendCreate entity=null url="">   
<!--项目类别--> 
<#if entity!>
	<#if entity.success==PositionExtend.SUCCESS_YES>
	<@M_tabIndexForCreate currentMenu="" entity=entity/>
	<@M_tabContentForCreate entity=entity/>
	<div class="list-group">
	  <a href="javascript:void(0)" class="list-group-item active">
	    <h4 class="list-group-item-heading">品种类别</h4>
	    <p class="list-group-item-text">1.录入品种>>基本信息</p>
	    <p class="list-group-item-text">2.录入品种>>添加位置</p>
	    <p class="list-group-item-text">3.录入品种>>绑定文章或产品</p>
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
  <input type="hidden" id="id_hidden_current_panel" value="#panel_baseinfo" data-saveUrl="/sz0099/ood/position/extend/manage/merge"/>
<ul class="nav nav-tabs" role="tablist" id="id_tab_create">
	    <li role="presentation" class="active" ><a href="#panel_baseinfo" aria-controls="panel_baseinfo" role="tab" data-toggle="tab" 
	    data-saveUrl="/sz0099/ood/position/manage/merge/baseinfo"
	    data-wrapperId='id_baseinfo_content${entity.id}'
	    >基本</a></li>
	   
	   
	    <li role="presentation"><a href="#contentPhoto" aria-controls="contentPhoto" role="tab" data-toggle="tab" 
	    data-saveUrl="/sz0099/ood/position/manage/editListUI"
	    data-wrapperId='id_contentPhoto_content${entity.id}'
	    >位置</a>
	    </li>
	    
	      <li role="presentation"><a href="#panel_position_ref_bind" aria-controls="panel_position_ref_bind" role="tab" data-toggle="tab" 
	      data-saveUrl="/sz0099/ood/position/manage/bindListUI"
	      data-wrapperId='id_price_content${entity.id}'
	      >绑定</a></li>
	    <#-- 
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
    <div role="tabpanel" class="tab-pane active fade in" id="panel_baseinfo" data-saveUrl="/sz0099/ood/position/extend/manage/merge">
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
		    <label for="id_domain${entity.id}">域名(为模块子域设立)</label>
		    <input type="text" id="id_domain${entity.id}" name="domain" value="${entity.domain}" class="form-control" placeholder="域名">
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
		  
			<br/>
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="panel_position_ref_bind" 
    data-saveUrl="/sz0099/ood/position/manage/bindListUI"
    data-wrapperId='id_price_content${entity.id}'
    >
    <br/>
    <p class="text-right">
    <a class="btn btn-xs btn-info" href="javascript:void(0)" 
    onclick="refresh('#panel_position_ref_bind','id_price_content${entity.id}', '/sz0099/ood/position/manage/bindListUI', '${tipId}')">
    	刷新位置绑定
    </a>
     &nbsp;&nbsp;
    </p>
    
    <hr/>
    	<div id="id_messageTip_price${entity.id}" class="text-center"></div>
		<div id="id_price_content${entity.id}" >
		<#-- 异步加载位置信息 -->
		</div>
		
    </div>
    
    <div role="tabpanel" class="tab-pane fade" id="contentPhoto" 
    data-saveUrl="/sz0099/ood/position/manage/editListUI"
    data-wrapperId='id_contentPhoto_content${entity.id}'
    >
    <br/>
    	<@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="name" propertyContext=Position.SUBID current=Position.SUBID_0_NO readonly=true />

    	<@M_positionBtn btnId="btn1" id=entity.id divWrapperId="id_contentPhoto_content"+entity.id tipId="id_messageTip_position"+entity.id/>
    	<div id="id_messageTip_position${entity.id}" class="text-center"></div>
    	<div id="id_contentPhoto_content${entity.id}" ></div>
    	<@M_positionBtn btnId="btn2" id=entity.id divWrapperId="id_contentPhoto_content"+entity.id tipId="id_messageTip_position"+entity.id/>
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



<#-- 编辑位置 begin -->
 <#macro M_positionListEdit content=null entity=null>
 <#if content!>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_positionEdit entity=ce />
		</#list>
	<#else>
	<@M_positionEdit entity=entity />
	</#if>
 </#if>
 </#macro>
 
<#macro M_positionPageEdit page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_positionEdit entity=ce />
		</#list>
	<#else>
	<@M_positionEdit entity=entity />
	</#if>
<#else>
<@M_positionEdit entity=entity />
</#if>
</#macro>

<#-- 位置编辑面板 begin -->
<#macro M_positionEdit entity>
<div id="position_${entity.id}"> <!--div wrapper-->
	<#assign pname = entity.name />
	<a id="id_a_subposition${entity.id}" class="btn btn-warning btn-xs" role="button" data-toggle="collapse" href="#id_subposition_${entity.id}" aria-expanded="false" aria-controls="id_subposition_${entity.id}">
	 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
	  <span id="id_position_orderSeq_show${entity.id}_col"> ${entity.orderSeq}</span>#<span id="id_position_name_show${entity.id}_col">${getSubstring(pname,10,'编辑位置')}</span> : ${Position.SUBID.getLabel(entity.subId)} >> ${Position.PANEL.getLabel(entity.panel)} >> ${Position.LAYOUT.getLabel(entity.layout)}
	</a>
	<div id="id_subposition_${entity.id}" class="panel-collapse collapse">
		
			<div class="panel panel-danger" id="id_panel_positionId${entity.id}" name="position">
				  <div class="panel-heading">
				    	<p class="panel-title text-center">
				    	<button type="button" class="btn btn-xs btn-primary pull-left" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存</button>
				    	<strong>#<span id="id_position_orderSeq_show${entity.id}">${entity.orderSeq}</span></strong>
				    	
				    	
				    	<span id="id_position_name_show${entity.id}">${getSubstring(pname,10,'编辑位置')}</span>
				    	<button type="button" class="btn btn-xs btn-danger pull-right" onclick="deleteSingle('${entity.id}', '/sz0099/ood/position/manage/deleteSingle','id_tip_${entity.id}','position_${entity.id}')">删除</button>
					    <input type="hidden" id="id_position_positionId${entity.id}" name="positionId" value="${entity.positionId}" class="form-control" >
					    <input type="hidden" id="id_position_id${entity.id}" name="id" value="${entity.id}" class="form-control" >
				    	</p>
				    	
				  </div>
				  <div class="panel-body">
				  <div id="id_tip_${entity.id}" class="text-center"></div>
				  	  <div class="form-group form-group-sm">
					    <label for="id_position_orderSeq${entity.id}">位置排序（数字，例如：1,2,3...）</label>
					    <input type="text" id="id_position_orderSeq${entity.id}" name="orderSeq" 
					    value="${entity.orderSeq}" class="form-control" 
					    onkeyup="keyPressPositive(this)"  
						onafterpaste="onAfterPastePositive(this)"
						onchange="onChangeShow('id_position_orderSeq_show${entity.id}',this.value)"
					    placeholder="位置排序，输入数字">
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_name${entity.id}">位置名称(英文少于15字母，汉字少于5个字)</label>
					    <input type="text" id="id_position_name${entity.id}" 
					    name="name" 
					    onchange="onChangeShow('id_position_name_show${entity.id}',this.value,10)"
					    value="${entity.name}" class="form-control" placeholder="位置名称，如：首页">
					  </div>
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.SUBID current=Position.SUBID.getContext(entity.subId,0) readonly=true />
					  
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.PANEL current=Position.PANEL.getContext(entity.panel,0) readonly=true />
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.LAYOUT current=Position.LAYOUT.getContext(entity.layout,0) readonly=false />
					  
					  <@M_dropdownBarSame idPre="id_position_" entityId=entity.id propertyContext=Position.STATUS current=Position.STATUS.getContext(entity.status,0) readonly=true />
					  
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_title${entity.id}">位置标题(少于16字符)</label>
					    <input type="text" id="id_position_title${entity.id}" name="title" value="${entity.title}" class="form-control" placeholder="位置标题">
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_description${entity.id}">位置描述(少于200字符)</label>
					    <textarea id="id_position_description${entity.id}" rows="5" class="form-control" placeholder="位置描述，少于200字">${entity.description}</textarea>
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_position_link${entity.id}">位置导向链接</label>
					    <input type="text" id="id_position_link${entity.id}" name="link" value="${entity.link}" class="form-control" placeholder="位置导向链接">
					  </div>
					  
				    </div><!--end panel-body-->
				    
				    <div class="panel-footer">
				    	<p class="text-center">每个大类都要点保存哦！-->
				    	<button type="button" class="btn btn-sm btn-primary pull-right" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存当前位置</button>
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

<#-- 大类编辑面板 end -->

<#-- 绑定位置 begin -->
 <#macro M_positionListBind content=null entity=null>
 <#if content!>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_positionBind entity=ce />
		</#list>
	<#else>
	<@M_positionEdit entity=entity />
	</#if>
 </#if>
 </#macro>
 
<#macro M_positionPageBind page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_positionBind entity=ce />
		</#list>
	<#else>
	<@M_positionBind entity=entity />
	</#if>
<#else>
<@M_positionBind entity=entity />
</#if>
</#macro>

<#-- 绑定编辑面板 begin -->
<#macro M_positionBind entity>
<div id="position_${entity.id}"> <!--div wrapper-->
	<#assign pname = entity.name />
	<a id="id_a_position_bind${entity.id}" class="btn btn-warning btn-xs" role="button" data-toggle="collapse" href="#id_position_bind_${entity.id}" aria-expanded="false" aria-controls="id_position_bind_${entity.id}">
	 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
	  <span id="id_position_orderSeq_show${entity.id}_col_bind"> ${entity.orderSeq}</span>#<span id="id_position_name_show${entity.id}_col_bind">${getSubstring(pname,10,'编辑位置')}</span>: ${Position.SUBID.getLabel(entity.subId)} >> ${Position.PANEL.getLabel(entity.panel)} >> ${Position.LAYOUT.getLabel(entity.layout)}
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
				    	
				    	
				    	<span id="id_position_bind_name_show${entity.id}">${getSubstring(pname,10,'无名位置')}</span>
				    	<button type="button" class="btn btn-xs btn-danger pull-right" 
				    	onclick="deleteRefByBaseId('id_collapse_content_${entity.id}', 'id_hidden_list_loaded${entity.id}', '/sz0099/ood/position/ref/manage/deleteRefByBaseId','id_collapse_tip_${entity.id}','${entity.id}', '${entity.extendId}')">删除</button>
					    <input type="hidden" id="id_position_bind_positionId${entity.id}" name="positionId" value="${entity.positionId}" class="form-control" >
					    <input type="hidden" id="id_position_bind_id${entity.id}" name="id" value="${entity.id}" class="form-control" >
				    	</p>
				    	
				  </div>
				  <div class="panel-body">
					  	
					  	<div id="id_collapse_tip_${entity.id}" class="text-center"></div>
					  	<#if 242156811024297984==entity.positionId>
					  	<@refBindBtn viewType=PositionRef.VIEWTYPE_1_ARTICLE.valueInt entity=entity showTip=PositionRef.VIEWTYPE_1_ARTICLE.label />
					  	</#if>
					  	<#if 242156981732470784==entity.positionId>
					  	<@refBindBtn viewType=PositionRef.VIEWTYPE_3_PROFESSION.valueInt entity=entity showTip=PositionRef.VIEWTYPE_3_PROFESSION.label />
				  	  	</#if>
				  	  	<input type="hidden" id="id_hidden_list_loaded${entity.id}" value="0"
				  	  	data-url='/sz0099/ood/position/ref/manage/findRefList'
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
				    	<button type="button" class="btn btn-sm btn-primary pull-right" onclick="saveSingle('${entity.id}','/sz0099/ood/position/manage/mergeSingle', 'id_tip_${entity.id}')">保存当前位置</button>
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

<#-- 位置编辑面板 end -->

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
  	data-url="/sz0099/ood/position/manage/selectNeedBindRefList"
  	data-reloadUrl='/sz0099/ood/position/ref/manage/findRefList'
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

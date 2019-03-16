<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#-- 选择文章 -->
<#macro M_selectArticlePositionPage page positionRef=null moreLoaded="true" url="/sz0099/ood/article/manage/queryArticleManageList" condition="">   
<#if page??>
	  <#assign itemList=page.content />
        <#assign viewType=PositionRef.VIEWTYPE_1_ARTICLE.valueInt />
		<#assign entityId=positionRef.baseId />
      <#list itemList as item>
        <#assign entity=item.article />
     <div class="list-group">
     <div class="btn-group btn-group-xs btn-group-justified" role="group" aria-label="id_art${item.id}">
	      <a id="id_art${positionRef.baseId}_${entity.id}" class="btn btn-default btn-xs"  role="button" data-toggle="collapse" href="#id_art_content${positionRef.baseId}_${entity.id}" aria-expanded="true" aria-controls="id_art_content${positionRef.baseId}_${entity.id}">
						 <span class="pull-left">
						 <span id="icon_id_btn_bind_un_${positionRef.baseId}_${entity.id}" class="glyphicon glyphicon-ok-sign <#if item.aid==null>hidden</#if>" aria-hidden="false"></span>
						 <span id="icon_id_btn_bind_${positionRef.baseId}_${entity.id}" class="glyphicon glyphicon-minus-sign <#if item.aid!=null>hidden</#if>" aria-hidden="false"></span>
						 </span>
						 <span class="glyphicon glyphicon-list" aria-hidden="false"></span>
						  ${entity.articleNo} -- ${entity.name} <span class="pull-right"> <strong>#${item?index}</strong></span>
		  	
		  </a>
	  </div>
      <div id="id_art_content${positionRef.baseId}_${entity.id}" class="panel-collapse collapse">
		  <div class="panel panel-default">
				  <div class="panel-body">
				    <p><strong>笔名:</strong> ${entity.penname}</p>
					 <#assign currentPreIntro=CoeArticle.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
				    <p><strong>引导语:</strong> ${currentPreIntro.label}</p>
				    <p><strong>标题:</strong> ${entity.title}</p>
				    <p><strong>子标题:</strong> ${entity.title}</p>
					 <p>
				     	<strong>发布状态:</strong> 
				      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
				      	${publishLabel}
				      </p>
				     
				  	  <p>
				  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
				  	  </p>
				  	  <p>
				  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
				  	  </p>
				  	  <p><strong>简介:</strong> ${getSubstring(entity.description,60,'暂无')}</p>
				  	  <p><strong>封面图:</strong><@M_showImageForSelect imageList=entity.coverList width="40px" /> </p>
				  	  <p><strong>头部图:</strong><@M_showImageForSelect imageList=entity.bannerList width="40px" /> </p>
				  </div>
				  <div class="panel-footer">
					  <p class="text-center">
					  <a id="id_btn_bind_${positionRef.baseId}_${entity.id}" href="javascript:void(0)" onclick="addPositionRef('${viewType}','${positionRef.baseId}','${positionRef.positionId}','${entity.id}','id_btn_bind_${positionRef.baseId}_${entity.id}','id_btn_bind_un_${positionRef.baseId}_${entity.id}')" class="btn btn-info btn-xs <#if item.aid!=null>hidden</#if>" data-url="/sz0099/ood/position/ref/manage/add">添加关联</a>
					  
					  <a id="id_btn_bind_un_${positionRef.baseId}_${entity.id}" href="javascript:void(0)" onclick="deletePositionRef('${item.id}','${viewType}','${positionRef.baseId}','${positionRef.positionId}','${entity.id}','id_btn_bind_${positionRef.baseId}_${entity.id}','id_btn_bind_un_${positionRef.baseId}_${entity.id}')" data-wrapperId="id_li_positionRef_${item.id}" data-url="/sz0099/ood/position/ref/manage/delete" class="btn btn-danger btn-xs <#if item.aid==null>hidden</#if>">移除关联</a>
					  
					  </p>
				  </div>
			  </div>
		</div><!--end collapse-->
  	  </#list>
  	  <div id="id_art_content_loadMore">
  	  </div>
  	  <!-- 下一页 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  		<#if !page.first>
			  	  	  <a href="javascript:void(0)" onclick="searchForRefSelect(${page.number-1},'id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-info btn-xs">上一页</a>
			  	  	  	</#if>
			  	  	  	<#if !page.last>
			  	  	  	<a href="javascript:void(0)" onclick="searchForRefSelect(${page.number+1},'id_refs_${viewType}${entityId}','id_hidden_refs_loaded_${viewType}${entityId}', 'id_refs_panel_${viewType}${entityId}', 'id_collapse_content_${entityId}','id_hidden_list_loaded${entityId}', 'id_collapse_tip_${entityId}')" class="btn btn-warning btn-xs">下一页</a>
				  		</#if>
				  </p>
			</div>
	  </#if>
	  
 </#if>
 </#macro>
 
<#macro M_articlePositionPageFlag positon=null entityPage=null flag="QX" tip="插旗行动" >
<div class="container" style="width:300px">
<#if entityPage! && entityPage.totalElements gt 0 >
	<@M_layout_imgWordRollerH_multi positon=positon entityPage=entityPage idPre="layout_" showHeadTip=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false/>
	
	<#else>
	<#-- 展示默认设置图 -->
	<#-- 
	<p>进入群侠户外</p>
	<div class="jumbotron">
	  <h3 class="text-center">
 	 欢迎大神 <strong><span class="text-success bg-info" >≡ <@shiro.principal property="nickname"/> ≡</strong></span>
	  </h3>
	  <p class="bg-success"><font size="3" color="black">卓玛拉山公众平台是一个专为户外圈友打造的社交平台，美景实拍、户外记实、实况直传，技能共享、资源合作，【群侠户外】、【雪峰户外】圈友入驻</font></p>
	  <p class="text-right">
	  <a class="pull-left" class="text-info" onclick="popForIndex('合作入驻说明','拟订中，请稍候来访，或加微信咨询！','微信号： ly275060435 [阿清哥]<br/>@卓玛拉科技')"><small>合作入驻</small></a>
	  <a class="btn btn-danger btn-lg" href="/sz0099/ood/home/article/index" role="button">进入圈里</a>
	  </p>
	</div>
	 -->
	<@M_layout_jumbotron_single2 entity0=positon idPre="ly_index_fetch_"+flag tip=tip withPreIntro=true withTitle=true withSubTitle=true linkable=true/>
</#if>
</div>
</#macro>
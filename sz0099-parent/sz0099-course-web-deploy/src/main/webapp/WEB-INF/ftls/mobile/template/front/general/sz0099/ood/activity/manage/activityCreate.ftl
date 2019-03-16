<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_joinItem.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_template_list.ftl">

<html>
<head>
<title>撰写活动</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForCategory category="activity" subCategory="new" entity=entity/>
  	<p>活动编号：${entity.activityNo} 
    <a href="/sz0099/ood/activity/manage/queryDraftList" class="btn btn-primary btn-xs">列表</a>
    <#if CoeActivity.PUBLISH_STATUS_PUBLISH.valueInt==entity.publishStatus>
    <a href="${entity.originalLink}" class="btn btn-primary btn-xs">详情</a>
    </#if>
    <a href="javascript:void(0)" id="id_template_select${entity.id}" 
	    onclick="findTemplatePageForSelect('id_template_select${entity.id}')" 
	    class="btn btn-info btn-xs"
	    data-wrapperId="id_template_wrapper"
	    data-template="10"
	    data-position="template_user"
	    data-saved="0"
	    data-url="/sz0099/ood/activity/findTemplate/ansy"
	    data-entityId="${entity.id}"
    >选模板</a>
    
    <a href="javascript:void(0)" onclick="detailPreview('/sz0099/ood/activity/manage/detailPreview/${entity.id}','${entity.id}')" class="btn btn-info pull-right">预览</a>
    </p>
    <div id="id_template_wrapper" class="hidden">
    	<@M_actTemplate entityId=entity.id/>
    </div>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <@M_coeActivityCreate entity=entity url="/sz0099/ood/activity/manage/create"/>
</@shiro.user>
<@shiro.guest>
${entity.respMsg}
<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
</@shiro.guest>
  <@M_navBottomForCategory category="ood" subCategory="activity"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonView.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonTag.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_create.js?v=1.0.10'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/activity/manage/coeActivity_joinItem.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jPhoto2.js?v=1.0.28'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jDatePicker.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/localResizeImg/lrz.all.bundle.js?v=1.0.0'><\/script>");</script>

<script>document.write("<script type='text/javascript' src='/assets/common/tools/wangEditor/3.1.1/wangEditor.js?v=1.2.9'><\/script>");</script>
<#--  
<script>document.write("<script type='text/javascript' src='/assets/common/tools/wangEditor/wangEditor-mobile.js?v=1.0.2'><\/script>");</script>
-->
<script type="text/javascript">

$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	
	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = currentPanel.val();
	
	$('#id_tab_create a').click(function (e) {
	  	e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	var saveUrl=$(e.currentTarget).attr("data-saveUrl");
	  	console.log("href:"+href);
	  	console.log("saveUrl:"+saveUrl);
	  	
	  	var currentPanel = $("#id_hidden_current_panel");
	  	console.log("--before currentPanel : "+currentPanel.val());
	  	currentPanel.val(href);
	  	currentPanelValue=href;
	  	console.log("--after currentPanel:"+currentPanel.val());
	  	
	  	var confirmBtnId="id_btn_confirm";
	  	showBtn('id_btn_confirm', href);
	  	currentPanel.attr("data-saveUrl",saveUrl);
	  	
	  	$(this).tab('show');
	  	loadData(href,'/sz0099/ood/activity/manage/paragraph/editListUI');
	  	
	  	//设置自动保存
	  	var entityId = $("#id_hidden_id").val();
	  	if(href=='#panel_baseinfo'){
	  		var descriptionInputId="id_area_description"+entityId;
	  		autoDescriptionCommit(descriptionInputId,entityId);
	  	}else{
	  		clearAutoDescription();
	  	}
	  	
	  	if(href=="#contentTime"){
	  		var fieldIdPre="id_joinItem_description";
	  		initMoreFieldAutoCommit(fieldIdPre);
		}
		
		if(href=="#contentFee"){
	  		var fieldIdPre="id_fee_description";
	  		initMoreFieldAutoCommit(fieldIdPre);
		}
	  	
	});

	//两种获取方式，初始化上传组件	
	var entityId = $("#id_hidden_id").val();
	var coverUploaderInputId="coverUploaderInput"+entityId;
	initUpload(coverUploaderInputId, 'id_wrapper_coverUploaderFiles');
	initUpload("bannerUploaderInput${entity.id}", 'id_wrapper_bannerUploaderFiles');
	initAutoCommit();//初始化概要自动保存
	
	initDateTimePicker("id_picker_actBegin");
	initDateTimePicker("id_picker_actEnd");
	initDateTimePicker("id_picker_offTime");
	initMoreDateTimePicker("id_picker_joinItem_joinTime");
	
});
</script>
</div>
</body>
</html>


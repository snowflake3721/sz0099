<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>技能编辑</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForCategory category="profession" subCategory="new" entity=entity/>

  <p class="word-break">技能编号：${entity.professionNo} 
  <a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/personal/profession/manage/queryProfessionList">返回列表</a>
  <#if Profession.PUBLISH_STATUS_PUBLISH.valueInt==entity.publishStatus>
    <a href="${entity.originalLink}" class="btn btn-primary btn-xs">转到详情</a>
    </#if>
  <a class="btn btn-info pull-right" href="javascript:void(0)" onclick="detailPreview('/sz0099/ood/personal/profession/manage/detailPreview/${entity.id}','${entity.id}')" >预览</a>
  </p>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"
   data-mainId="${entity.id}" data-baseId="${entity.id}"
   data-positionId="${entity.positionId}"
   data-extendId="${entity.extendId}"
   />
 
  <@M_professionCreate profession=entity url="/sz0099/ood/personal/profession/manage/create"/>
</@shiro.user>
<@shiro.guest>
${entity.respMsg}
<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
</@shiro.guest>
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="profession"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/tabLoader.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonView.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonTag.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonCreate.js?v=1.0.7'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_create.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_manage.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_sub.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jPhoto2.js?v=1.0.28'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/localResizeImg/lrz.all.bundle.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/wangEditor/3.1.1/wangEditor.js?v=1.2.7'><\/script>");</script>


<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');

	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = currentPanel.val();
	
	$('#id_tab_create a').click(function (e) {
	  	e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	console.log(href);
	  	$(this).tab('show');
	  	var loaded = $(this).attr("data-loaded");
	  	if(loaded==0){
	  		var id=$(this).attr("id");
	  		loadDataForTab(id);//执行数据加载
	  	}
	  	
	  	//设置自动保存
	  	if(href=='#panel_baseinfo'){
	  		var entityId = $("#id_hidden_id").val();
	  		var descriptionInputId="id_area_description"+entityId;
	  		autoDescriptionCommit(descriptionInputId,entityId);
	  	}else{
	  		clearAutoDescription();
	  	}
	  	console.log("----======----");
	});
	
	
	//两种获取方式	
	var entityId = $("#id_hidden_id").val();
	var coverUploaderInputId="coverUploaderInput"+entityId;
	initUpload(coverUploaderInputId, 'id_wrapper_coverUploaderFiles');
	initUpload("bannerUploaderInput${entity.id}", 'id_wrapper_bannerUploaderFiles');
	initAutoCommit();//初始化概要自动保存
});
</script>
</div>
</body>
</html>


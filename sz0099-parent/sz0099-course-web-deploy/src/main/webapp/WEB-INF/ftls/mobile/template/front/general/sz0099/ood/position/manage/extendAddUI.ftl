<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_position_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#-- 
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
 -->
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">

<html>
<head>
<title>录入位置所属项目</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<#-- 
<@M_navBreadForManage category="position" subCategory="new" entity=entity/>
 -->
<@M_navBreadForCategory category="position" subCategory="new" showView="detail"  entity=entity/>
  <div class="text-center text-primary"><h5>录入位置所属项目</h5></div>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}" data-mainId="${Robot.ROBOT_PLAT.id}" data-subId="${Robot.ROBOT_PLAT.id}"/>
  <@M_positionExtendCreate entity=entity url="/sz0099/ood/position/extend/manage/create"/>
 <#-- 
  <@M_navBottomForManage category="position" subCategory="new"  entity=entity/>
  -->
   <#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="detail"  entity=entity/>

</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/position/manage/position_create.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/position/manage/position_sub.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/clipboard/clipboard.js?v=1.0.0'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){
	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = currentPanel.val();
	
	$('#id_tab_create a').click(function (e) {
	  	e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	var saveUrl=$(e.currentTarget).attr("data-saveUrl");
	  	var wrapperId=$(e.currentTarget).attr("data-wrapperId");
	  	console.log("href >>> " + href);
	  	console.log("saveUrl >>> " + saveUrl);
	  	console.log("wrapperId >>> " + wrapperId);
	  	
	  	var currentPanel = $("#id_hidden_current_panel");
	  	console.log("--before currentPanel--");
	  	console.log("before >>> " + currentPanel.val());
	  	currentPanel.val(href);
	  	currentPanelValue=href;
	  	console.log("--after currentPanel--");
	  	console.log("after >>> " + currentPanel.val());
	  	console.log(e);
	  	console.log("----======----");
	  	
	  	var confirmBtnId="id_btn_confirm";
	  	showBtn('id_btn_confirm', href);
	  	currentPanel.attr("data-saveUrl",saveUrl);
	  	
	  	$(this).tab('show');
	  	console.log(">>> saveUrl: " + saveUrl);
	  	// '/sz0099/ood/position/manage/editListUI'
	  	loadData(href,wrapperId,saveUrl);
	  	
	});

	//两种获取方式，初始化上传组件	
	var entityId = $("#id_hidden_id").val();
	//initParagraph('${entity.id}');//段落上传图片
	//initParagraphForCover('${entity.id}');//封面图
	//initParagraphForBanner('${entity.id}');//头部图
	
	new ClipboardJS('#id_clipboard_${entity.id}');
	
});
</script>
</div>
</body>
</html>


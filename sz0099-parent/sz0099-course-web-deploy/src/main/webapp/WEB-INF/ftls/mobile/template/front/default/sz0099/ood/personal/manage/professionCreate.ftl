<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">

<html>
<head>
<title>技能编辑</title>
</head>

<body>

<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForManage category="profession" subCategory="new" entity=entity/>
<a type="button" class="btn btn-info btn-xs" href="/sz0099/ood/personal/profession/manage/queryProfessionList">返回技能列表</a>
  <div class="text-center text-primary"><h5>技能编辑</h5></div>
  <p class="word-break">技能链接: ${entity.originalLink} </p>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"
   data-mainId="${Robot.ROBOT_PLAT.id}" data-subId="${Robot.ROBOT_PLAT.id}"
   data-positionId="${entity.positionId}"
   data-extendId="${entity.extendId}"
   />
 
  <@M_professionCreate profession=entity url="/sz0099/ood/personal/profession/manage/create"/>
</@shiro.user>
  <@M_navBottomForManage category="profession" subCategory="new"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/personal/manage/profession_create.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/personal/manage/profession_manage.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/personal/manage/profession_sub.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>


<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');

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
	  	console.log(currentPanel.val());
	  	currentPanel.val(href);
	  	currentPanelValue=href;
	  	console.log("--after currentPanel--");
	  	console.log(currentPanel.val());
	  	console.log(e);
	  	console.log("----======----");
	  	
	  	var confirmBtnId="id_btn_confirm";
	  	showBtn(confirmBtnId, href);
	  	currentPanel.attr("data-saveUrl",saveUrl);
	  	
	  	$(this).tab('show');
	  	loadData(href, wrapperId, saveUrl);
	  	
	});
	
	//两种获取方式	
	var entityId = $("#id_hidden_id").val();
	initParagraph('${entity.id}');
	initParagraphForCover('${entity.id}');//封面图
	initParagraphForBanner('${entity.id}');//头部图
});
</script>
</div>
</body>
</html>


<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_category_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">

<html>
<head>
<title>录入类别所属项目</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForManage category="category" subCategory="new" entity=entity/>

  <div class="text-center text-primary"><h5>录入类别所属项目</h5></div>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}" data-mainId="${Robot.ROBOT_PLAT.id}" data-subId="${Robot.ROBOT_PLAT.id}"/>
  <@M_categoryExtendCreate entity=entity url="/sz0099/ood/category/extend/manage/create"/>
 <#-- -->
  <@M_navBottomForManage category="category" subCategory="new"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/category/manage/category_create.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/category/manage/category_sub.js?v=1.0.1'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/clipboard/clipboard.js?v=1.0.0'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){
	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = currentPanel.val();
	
	$('#id_tab_create a').click(function (e) {
	  	e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	var saveUrl=$(e.currentTarget).attr("data-saveUrl");
	  	console.log(href);
	  	console.log(saveUrl);
	  	
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
	  	showBtn('id_btn_confirm', href);
	  	currentPanel.attr("data-saveUrl",saveUrl);
	  	
	  	$(this).tab('show');
	  	loadData(href,'/sz0099/ood/category/manage/editListUI');
	  	
	});

	//两种获取方式，初始化上传组件	
	var entityId = $("#id_hidden_id").val();
	//initParagraph('${entity.id}');//段落上传图片
	//initParagraphForCover('${entity.id}');//封面图
	//initParagraphForBanner('${entity.id}');//头部图
	
	new ClipboardJS('#id_clipboard_${entity.id}');
	
	collapseBind();//绑定子类展开
});
</script>
</div>
</body>
</html>


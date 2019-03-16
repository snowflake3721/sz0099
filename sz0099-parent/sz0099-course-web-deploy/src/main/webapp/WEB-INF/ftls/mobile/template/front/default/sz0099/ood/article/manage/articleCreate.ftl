<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<html>
<head>
<title>撰写文章</title>
</head>

<body>

<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForManage category="article" subCategory="new" entity=entity/>

  <div class="text-center text-primary"><h5>撰写文章</h5></div>
  <p>文章编号：${entity.articleNo} </p>
  <p class="word-break">链接: ${entity.originalLink} </p>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <@M_coeArticleCreate entity=entity url="/sz0099/ood/article/manage/create"/>
</@shiro.user>
  <@M_navBottomForManage category="article" subCategory="new"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/common/tools/wangEditor/3.1.1/wangEditor.js?v=1.2.3'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/manage/coeArticle_create.js?v=1.0.3'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/mainType.js?v=1.0.0'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){
	<#-- 
	var E = window.wangEditor;
	var editor = new E('#id_div_description');
	var descriptionArea = $("#id_area_description");
	editor.customConfig.menus = [
	    'head',
	    'bold',
	    'italic',
	    'underline'
	];
	editor.customConfig.zIndex = 100;
	editor.customConfig.onchange = function (html) {
	    // 监控变化，同步更新到 textarea
	    descriptionArea.val(html);
	}
	editor.create();
	descriptionArea.val(editor.txt.html());
	 -->
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
	  	loadData(href,'/sz0099/ood/article/manage/paragraph/editListUI');
	  	
	});

	//两种获取方式，初始化上传组件	
	var entityId = $("#id_hidden_id").val();
	initParagraph('${entity.id}');//段落上传图片
	initParagraphForCover('${entity.id}');//封面图
	initParagraphForBanner('${entity.id}');//头部图
});
</script>
</div>
</body>
</html>


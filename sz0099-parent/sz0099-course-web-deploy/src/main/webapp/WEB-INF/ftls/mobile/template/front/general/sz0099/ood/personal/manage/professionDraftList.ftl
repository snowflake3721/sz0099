<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_list.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>技能服务</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForCategory category="profession" subCategory="draftList"  entity=entity/>
<div class="row">
	 <div class="col-md-12">
	    <div class="input-group">
	      <input type="text" id="id_data_title" name="title" class="form-control" placeholder="标题，快速定位" value="${entity.title}">
	      <span class="input-group-btn">
	        <button class="btn btn-default" id="id_search_btn" type="button" onclick="ansySearch('panel_search','id_a_panel_search')">搜索</button>
	      </span>
	      <input type="text" id="id_data_professionNo" name="professionNo" class="form-control" placeholder="编号，精确定位" value="${entity.professionNo}">
	    </div>
  	 </div>
</div>

<ul class="nav nav-tabs" role="tablist" id="id_tab_list">
	    <li role="presentation" class="active" >
	    <a href="#panel_draft" aria-controls="panel_draft" role="tab" 
	    data-toggle="tab" 
	    data-url="/sz0099/ood/personal/profession/manage/queryProfessionList/ansy"
	    data-loaded="0"
	    data-tabId="panel_draft"
	    id="id_a_panel_draft"
	    >草稿</a>
	    </li>
	    <li role="presentation">
	    <a href="#panel_publish" aria-controls="panel_publish" role="tab" 
	    data-toggle="tab" 
	    data-url="/sz0099/ood/personal/profession/manage/queryDetailList/ansy"
	    data-loaded="0"
	    data-tabId="panel_publish"
	    id="id_a_panel_publish"
	    >已发布</a>
	    </li>
	    <li role="presentation">
	    <a href="#panel_editquikly" aria-controls="panel_editquikly" role="tab" 
	    data-toggle="tab" 
	    data-url="/sz0099/ood/personal/profession/manage/queryProfessionManageList/ansy"
	    data-loaded="0"
	    data-tabId="panel_editquikly"
	    id="id_a_panel_editquikly"
	    >快速编辑</a>
	    </li>
	    <li role="presentation">
	    <a href="#panel_search" aria-controls="panel_search" role="tab" 
	    data-toggle="tab" 
	    data-url="/sz0099/ood/personal/profession/manage/searchProfessionList/ansy"
	    data-loaded="0"
	    data-tabId="panel_search"
	    id="id_a_panel_search"
	    >搜索</a>
	    </li>
</ul>
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active fade in" id="panel_draft">
		  <#-- <@M_coeArticleDraftList draftList=draftList/> -->
		  <@M_professionDraftList draftList=entityPage.content/>
    </div>
    <div role="tabpanel" class="tab-pane fade in" id="panel_publish" data-url="">
    	正在加载已发布列表...
    </div>
    <div role="tabpanel" class="tab-pane fade in" id="panel_editquikly" data-url="">
    	正在加载快速编辑列表...
    </div>
    <div role="tabpanel" class="tab-pane fade in" id="panel_search" data-url="">
    	<br/>
    	输入搜索条件，点击<button class="btn btn-default" type="button" onclick="ansySearch('panel_search','id_a_panel_search')">搜索</button>按钮
    </div>
</div>    
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="profession"  entity=entity/> 
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/tabLoader.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/profession_manage.js?v=1.0.2'><\/script>");</script>

<script type="text/javascript">
	$('#id_tab_list a').click(function (e) {
	  	e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	console.log(href);
	  	$(this).tab('show');
	  	var loaded = $(this).attr("data-loaded");
	  	if(loaded==0){
	  		var id=$(this).attr("id");
	  		loadDataForTab(id);//执行数据加载
	  	}
	});
	
	function ansySearch(position, tabIdShow){
		var tabPanel=$("#"+tabIdShow);
		var loaded = tabPanel.attr("data-loaded");
	  	if(loaded==0){
	  		loadDataForTab(tabIdShow);//执行数据加载
	  	}else{
			ansySearchForProfessionManage(position);
	  	}
	  	tabPanel.tab('show');
	}
	
	$(document).ready(function(){
		autoCurrentOauthPageUrlLogin('${login_status}');
		
		<#if RequestParameters['showTab']??>
			var showTab="${RequestParameters['showTab']}";
			if(showTab){
				var showTab_a="a[aria-controls='"+showTab+"']"
				$('#id_tab_list '+showTab_a).trigger("click");
			}
		</#if>
	});
</script>
</div>


</body>
</html>


<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_list.ftl">

<html>
<head>
<title>活动详情</title>
</head>

<body>

<div class="container " id="body_content">
 <@M_navBreadForManage category="activity" subCategory="detailList"  entity=entity/>
  <div class="text-center"><h3>选择一个活动查看</h3></div>
  <p>${entity.respMsg}</p>
  
  <div class="row">
		 <div class="col-md-12">
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="/sz0099/ood/activity/manage/queryActivityList">
		      <input type="hidden" id="id_currentPage" name="page" class="form-control" value="${page.number}">
		      <input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${page.totalPages}">
		      <input type="hidden" id="id_size" name="size" class="form-control" value="${page.size}">
		      <input type="text" id="id_search_title" name="title" class="form-control" placeholder="标题，快速定位" value="${entity.title}">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchCourseForManage(0)">搜索</button>
		      </span>
		      <input type="text" id="id_search_activityNo" name="activityNo" class="form-control" placeholder="编号，精确定位" value="${entity.activityNo}">
		    </div>
	  	 </div>
  	</div>
  	<hr/>
  
  <@M_coeActivityDetailPage detailPage=page moreLoaded="false" url="/sz0099/ood/activity/manage/detail/" condition=entity/>
  
  <@M_navBottomForManage category="activity" subCategory="detailList"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script type="text/javascript">
	initPageForProductDetailList();
</script>
</div>
</body>
</html>


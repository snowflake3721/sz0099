<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<html>
<head>

<title>技术课程</title>
</head>

<body>

<div class="container " id="body_content">
  <div class="text-center"><h3>面向技术课程分享</h3></div>
  
   <@shiro.user>
 	<p> 欢迎爱学习的<span class="text-success"> [<@shiro.principal property="nickname"/>]</span> 加入课程</p>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	<div class="row">
		 <div class="col-md-12">
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="/sz0099/ood/product/index">
		      <input type="hidden" id="id_currentPage" name="page" class="form-control" value="${page.number}">
		      <input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${page.totalPages}">
		      <input type="hidden" id="id_size" name="size" class="form-control" value="${page.size}">
		      <input type="text" id="id_search_title" name="title" class="form-control" placeholder="大数据,java,python..." value="${courseProductBo.title}">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchCourse(0)">搜索</button>
		      </span>
		    </div>
	  	 </div>
  	</div>
  	<hr/>
	
	<@M_coeProductPage itemPage=page moreLoaded="false" url="/sz0099/ood/product/index" condition=courseProductBo/>
	<@M_dramala_wechat/>
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="ood" subCategory="detail"  entity=null/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/coeProduct_index.js?v=1.0.5'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');

});
//为产品首页初始化分页对象
initPageForProduct();
</script>

</div>
</body>
</html>
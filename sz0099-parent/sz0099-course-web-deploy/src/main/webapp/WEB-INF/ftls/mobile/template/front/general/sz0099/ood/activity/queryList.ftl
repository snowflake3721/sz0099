<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<html>
<head>

<title>群侠户外</title>
</head>

<body>

<div class="container " id="body_content">
	<div class="row">
		 <div class="col-md-12">
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="/sz0099/ood/article/queryList">
		      <input type="hidden" id="id_currentPage" name="page" class="form-control" value="${page.number}">
		      <input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${page.totalPages}">
		      <input type="hidden" id="id_size" name="size" class="form-control" value="${page.size}">
		      <input type="text" id="id_search_title" name="title" class="form-control" placeholder="七娘山,排牙山,东西冲..." value="${coeArticleBo.title}">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchForCategory(0)">搜索</button>
		      </span>
		    </div>
		    <@M_selectCategoryForSearch entity=coeArticleBo id="id_search_categoryId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/article/queryList" />
	  	 </div>
  	</div>

    <@shiro.user>
 	<p> 欢迎户外大神 <span class="text-success"> [<@shiro.principal property="nickname"/>]</span> 加入</p>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	<hr/>
	
	<@M_coeArticlePage itemPage=page moreLoaded="false" url="/sz0099/ood/article/queryList" condition=coeArticleBo/>
	<@M_introduction/>
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/navBottom.ftl"> -->
	<@M_navBottomForCategory category="qunxia" subCategory="outdoor"  entity=null/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/article/coeArticle_index.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
});
//为文章首页初始化分页对象
initPageForProduct();
</script>

</div>
</body>
</html>
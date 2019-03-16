<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_detail_view.ftl">

<html>
<head>
<title>${entity.title}</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForCategory category="ood" subCategory="detail"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <@M_coeProductDetail entity=entity paragraphPage=page moreLoaded="true" />
</#if>

<@M_navBottomForCategory category="ood" subCategory="detail"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/manage/coeProduct_manage.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/manage/coeProduct_create.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/coeProduct_index.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</div>
</body>
</html>


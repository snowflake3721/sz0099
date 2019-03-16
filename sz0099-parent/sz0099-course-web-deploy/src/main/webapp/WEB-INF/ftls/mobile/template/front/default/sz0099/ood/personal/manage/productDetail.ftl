<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_detail_view.ftl">

<html>
<head>
<title>${entity.title}</title>
</head>

<body>

<div class="container " id="body_content">
<@M_navBreadForManage category="sale" subCategory="detail"  entity=entity/>
<#if page! && entity!>
  <input type="hidden" name="shelved" id="id_hidden_shelved${entity.id}" value="${entity.shelved}" />
  <input type="hidden" name="id" id="id_hidden_id" value="${entity.id}"/>
  <input type="hidden" name="name" id="id_name${entity.id}" value="${entity.name}"/>
  	<ul class="list-inline">
  		<li><a href="/sz0099/ood/product/manage/create?id=${entity.id}" class="btn btn-primary" role="button">编辑</a></li>
  		<#assign shelved=entity.shelved />
  		<li id="id_li_shevled_${entity.id}${CoeProduct.SHELVED_YES.valueInt}" class="<#if shelved==CoeProduct.SHELVED_YES.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="shelvedProduct('${entity.id}', '${CoeProduct.SHELVED_YES.valueInt}')" class="btn btn-info" role="button">上架</a></li>
  		<li id="id_li_shevled_${entity.id}${CoeProduct.SHELVED_NO.valueInt}" class="<#if shelved==CoeProduct.SHELVED_NO.getValueInt()>hidden</#if>"><a href="javascript:void(0)" onclick="shelvedProduct('${entity.id}', '${CoeProduct.SHELVED_NO.valueInt}')" class="btn btn-warning" role="button">下架</a></li>
		<li class="pull-right"><a href="javascript:void(0)" onclick="mergeForDeleted('${entity.id}')" class="btn btn-danger pull-right" role="button">删除</a></li>
	</ul>
    
  <span id="id_span_shelved${entity.id}" class="<#if shelved==CoeProduct.SHELVED_YES.valueInt>text-success<#else>text-danger</#if>"><#if shelved==CoeProduct.SHELVED_YES.valueInt>产品已上架<#else>产品已下架</#if></span>
  
  <@M_coeProductDetail entity=entity paragraphPage=page moreLoaded="true" />
</#if>

  <@M_navBottomForManage category="sale" subCategory="detail"  entity=entity/>
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


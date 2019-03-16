<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_order_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_order_common.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForMy.ftl">
<#-- 查看提取链接与提取码 -->
	
<div class="container">
  <div class="text-center"><h4>提取查询结果</h4></div>
  <#if entity.success == 1>
  <h4 class="text-success">${entity.respMsg}</h4>
  <#assign productList=entity.productList />
	  <#if productList! && productList?size gt 0 >
		  <#list productList as product>
		  <p class="text-success">提取链接#提取码：</p>
		  <p class="text-danger" style="word-break:break-all; word-wrap:break-all;" id="id_p_content${product.id}">${product.link}#${product.pullCode} </p>
		  <button type="button" id="id_btn_clipboard${product.id}" class="btn btn-xs btn-success" data-clipboard-action="copy" data-clipboard-target="#id_p_content${product.id}">复制</button>
		  </#list>
	  </#if>
  <#else>
  <h4 class="text-danger">${entity.respMsg}</h4>
  </#if>
  <input type="hidden" name="respCode" value="${entity.respCode}" />
  
	
</div>
<script>
<#if productList! && productList?size gt 0 >
 <#list productList as product>
	new ClipboardJS('#id_btn_clipboard${product.id}');
 </#list>
</#if>
</script>



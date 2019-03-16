<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">


  <h4 id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>

<#assign content=response.content/>
<#if content!>
<#list content as imageRef>
		<#if imageRef!>
		<@Mg_imageRefEdit imageRef=imageRef />
		</#if>
</#list>
</#if>
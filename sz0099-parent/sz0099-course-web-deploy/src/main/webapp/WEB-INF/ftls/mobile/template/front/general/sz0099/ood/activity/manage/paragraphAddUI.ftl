<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<h4 id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">
${entity.respMsg}
<#if entity.success == 1>
序号：#${entity.orderSeq}
</#if>
</h4>
<#if entity.success == 1>
<@Mg_paragraphEdit entity=entity/>
</#if>
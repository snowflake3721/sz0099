<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_positionRef_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/function/func_basic.ftl">
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<h4 id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">
${entity.respMsg}
</h4>
<#if entity.success == 1>
<@M_positionRefEdit entity=entity/>
</#if>

<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_role_create.ftl">

<div class="container " id="body_content">
<#if entity.success == 1>
<@M_roleEditUI entity=entity />
<h4 class="<#if entity.success == 1>text-success<#else>text-danger</#if>" id="id_common_respMsg">${entity.respMsg}</h4>
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>

<#else >
<h4 class="text-danger" id="id_common_respMsg">${entity.respMsg}</h4>
</#if>
</div>
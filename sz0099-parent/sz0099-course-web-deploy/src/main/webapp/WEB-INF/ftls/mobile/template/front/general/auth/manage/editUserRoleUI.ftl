<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/macro/macro_userRole_create.ftl">
<div class="container " id="body_content">
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<#if entity.success == 1>
<h5 class="text-success" id="id_common_respMsg">${entity.respMsg}</h5>
<@M_userRoleAddUI entity=entity />
<#else >
<h4 class="text-danger" id="id_common_respMsg">${entity.respMsg}</h4>
</#if>
</div>
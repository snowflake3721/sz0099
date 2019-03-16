<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container">
  <h4 class="<#if entity.success == 1>text-success<#else>text-danger</#if>" id="id_common_respMsg">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
  <input type="hidden" name="id" value="${entity.id}" id="id_hidden_common_id"/>
</div>


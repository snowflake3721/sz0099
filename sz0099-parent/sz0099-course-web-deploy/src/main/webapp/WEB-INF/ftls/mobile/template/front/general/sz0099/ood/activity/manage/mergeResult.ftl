<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
  <input type="hidden" name="respMsg" id="id_hidden_common_respMsg" value="${entity.respMsg}" />
  <span name="respMsg" id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</span>

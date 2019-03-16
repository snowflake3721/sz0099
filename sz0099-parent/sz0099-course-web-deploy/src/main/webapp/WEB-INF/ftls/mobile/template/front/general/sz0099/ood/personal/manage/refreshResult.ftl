<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container">
  <div class="text-center"><h4>操作结果</h4></div>
  <h4 id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
  <span id="id_common_data"><#if entity.success == 1>${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</#if></span>
</div>


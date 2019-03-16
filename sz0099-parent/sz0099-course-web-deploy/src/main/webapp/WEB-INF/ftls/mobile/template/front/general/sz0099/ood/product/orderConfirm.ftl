<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container">
  <div class="text-center"><h4>下单结果</h4></div>
  <h4 class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_orderConfirm_respCode"/>
</div>


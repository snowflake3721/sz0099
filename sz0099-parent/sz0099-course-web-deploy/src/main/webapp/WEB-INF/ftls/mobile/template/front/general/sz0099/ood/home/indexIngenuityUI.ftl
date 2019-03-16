<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_position_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<div class="container">
<h4 class="<#if entity.success == 1>text-success<#else>text-danger</#if>" id="id_common_respMsg">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<@M_professionPositionPageIngenuity positon=entity entityPage=entityPage flag="QX" tip="倾诚打造精品，坚守独道匠心"/>
</div>
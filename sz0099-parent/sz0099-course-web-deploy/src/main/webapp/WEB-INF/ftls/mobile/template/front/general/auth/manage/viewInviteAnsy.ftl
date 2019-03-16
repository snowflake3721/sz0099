<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<@M_qrCodeViewForAnsy entity=entity />
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
  <input type="hidden" name="id" value="${entity.id}" id="id_hidden_common_id"/>

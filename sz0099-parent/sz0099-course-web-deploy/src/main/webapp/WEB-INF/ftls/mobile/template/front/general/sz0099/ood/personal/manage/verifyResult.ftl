<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_user_list.ftl">

<div class="container">
  <p id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</p>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
	<div id="hidden_id_btn_wrapper" class="hidden">
		<@Mg_verifyBtn entity=child />
	</div>
</div>


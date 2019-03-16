<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#if entity.success==1>
<li class="list-group-item list-group-item-info" id="id_act_user_li${entity.id}" data-saved="0">
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
  <input type="hidden" name="respMsg" value="${entity.respMsg}" id="id_hidden_common_respMsg"/>
	<span class="glyphicon glyphicon-ok text-success" onclick="showSavedTip(${entity.success},'${DateUtils.formatJota(entity.lastModifiedDate ,"yyyy-MM-dd HH:mm")}')"></span>
	<@Mg_actOrderUserSingleWithoutLi entity0=entity/>
</li>
<#else>
<li class="list-group-item list-group-item-danger">
	<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
    <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
	<input type="hidden" name="respMsg" value="${entity.respMsg}" id="id_hidden_common_respMsg"/>
	<span class="glyphicon glyphicon-remove text-danger" onclick="showSavedTip(${entity.success},'${DateUtils.formatJota(entity.lastModifiedDate ,"yyyy-MM-dd HH:mm")}')">
	${entity.respMsg}
	</span>
	<#if CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN==entity.respCode>
	<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
	</#if>
</li>
</#if>

<script>
initFieldAutoCommit('id_act_user_identity${entity.id}');
</script>
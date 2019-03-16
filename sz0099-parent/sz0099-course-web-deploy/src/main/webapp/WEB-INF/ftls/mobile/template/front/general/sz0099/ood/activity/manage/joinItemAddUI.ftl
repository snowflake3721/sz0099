<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_joinItem.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<li class="list-group-item list-group-item-info" id="id_joinItem_li${entity.id}" data-saved="0">
<#if entity.success==1>
  <input type="hidden" name="respMsg" value="${entity.respMsg}" id="id_hidden_common_respMsg"/>
<#else>
	<input type="hidden" name="respMsg" value="${entity.respMsg}" id="id_hidden_common_respMsg"/>
	${entity.respMsg}
</#if>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<#if entity.success==1>
	<span class="glyphicon glyphicon-ok text-success" onclick="showSavedTip(${entity.success},'${DateUtils.formatJota(entity.lastModifiedDate ,"yyyy-MM-dd HH:mm")}')"></span>
	<@M_joinItemSingleWithoutLi entity0=entity/>
<#else>
	<span class="glyphicon glyphicon-remove text-danger" onclick="showSavedTip(${entity.success},'${DateUtils.formatJota(entity.lastModifiedDate ,"yyyy-MM-dd HH:mm")}')"></span>
	<#if CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN==entity.respCode>
	<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
	</#if>
</#if>
</li>
<script>
initDateTimePicker('id_picker_joinItem_joinTime${entity.id}');
initFieldAutoCommit('id_joinItem_description${entity.id}');
</script>
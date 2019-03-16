<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

  <input type="hidden" name="respMsg" value="${entity.respMsg}" id="id_hidden_common_respMsg"/>
  <input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
  <input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<#if entity.success==1>
	<#-- 
	<span class="glyphicon glyphicon-ok text-success" onclick="showSavedTip(${entity.success},'${DateUtils.formatJota(entity.lastModifiedDate ,"yyyy-MM-dd HH:mm")}')"></span>
	 -->
<#else>
   <span name="respMsg" >${entity.respMsg}</span>
	<#if CoeActivityRespCode.SZ0099_CODE_OOD_ACTIVITY_USER_NOT_LOGIN==entity.respCode>
	<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
	</#if>
</#if>

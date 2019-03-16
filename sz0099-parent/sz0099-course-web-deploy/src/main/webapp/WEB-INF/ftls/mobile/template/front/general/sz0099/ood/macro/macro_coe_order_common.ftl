<#macro showButtonByPayStatus entity itemDivId="">
<#assign payStatus=entity.status />
<#assign flowStatus=entity.flowStatus />
<#assign orderId=entity.id />
<ul class="nav nav-pills">
   <li class="text-left">
   		<#if payStatus==CoeOrder.STATUS_PAY_WAIT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
        <button type="button" class="btn btn-info btn-xs text-left">去支付</button>
   		<#elseif payStatus==CoeOrder.STATUS_PAY_PAYED && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<#-- 
		<button type="button" class="btn btn-danger btn-xs text-right" onclick="mergeForInprocess('${orderId}','${CoeOrder.STATUS_PAY_INPROCESS}', '${itemDivId}')">处理</button>
		<#elseif payStatus==CoeOrder.STATUS_PAY_INPROCESS && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-danger btn-xs text-right" onclick="mergeForSent('${orderId}','${CoeOrder.STATUS_PAY_SENT}')">发货</button>
		 -->
		<#elseif payStatus==CoeOrder.STATUS_PAY_SENT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-danger btn-xs text-right">收货</button>
		<#else>
		<button type="button" class="btn btn-danger btn-xs text-right">再买一份</button>
        <button type="button" class="btn btn-info btn-xs text-left">我已提取</button>
   		</#if>
   </li>
   <li class="pull-right">
   		<#if payStatus==CoeOrder.STATUS_PAY_WAIT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-danger btn-xs text-left">取消</button>
		<button type="button" class="btn btn-danger btn-xs text-left">删除</button>
		</#if>
   </li>
</ul>
</#macro>

<#macro M_mangeButtonByPayStatus entity itemDivId="">
<#assign payStatus=entity.status />
<#assign flowStatus=entity.flowStatus />
<#assign orderId=entity.id />
<ul class="nav nav-pills">
   <li class="text-left">
   		<#-- 
   		<#if payStatus==CoeOrder.STATUS_PAY_WAIT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
        <button type="button" class="btn btn-info btn-xs text-left">去支付</button>
         -->
   		<#if payStatus==CoeOrder.STATUS_PAY_PAYED && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-info btn-xs text-right" onclick="mergeForInprocess('${orderId}','${CoeOrder.STATUS_PAY_INPROCESS}', '${itemDivId}')">处理</button>
		<#elseif payStatus==CoeOrder.STATUS_PAY_INPROCESS && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-info btn-xs text-right" onclick="mergeForSent('${orderId}','${CoeOrder.STATUS_PAY_SENT}')">发货</button>
		<#-- 
		<#elseif payStatus==CoeOrder.STATUS_PAY_SENT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-danger btn-xs text-right">收货</button>
		 -->
		<#elseif payStatus gte CoeOrder.STATUS_PAY_RECIEVED && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-info btn-xs text-right" onclick="mergeForFinish('${orderId}','${CoeOrder.STATUS_PAY_SENT}')">完成</button>
		<#else>
		<button type="button" class="btn btn-info btn-xs text-right" onclick="mergeForLink('${orderId}','${CoeOrder.STATUS_PAY_SENT}')">修改提取链接密码</button>
   		</#if>
   </li>
   <li class="pull-right">
   		<#if payStatus==CoeOrder.STATUS_PAY_PAYED && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
		<button type="button" class="btn btn-danger btn-xs text-left" onclick="mergeForClose('${orderId}','${CoeOrder.STATUS_PAY_SENT}')">关闭</button>
		</#if>
   </li>
</ul>
</#macro>

<#macro showPayStatus payStatus=0 flowStatus=0>
<#assign label=CoeOrder.payStatusMap.get(payStatus) />
	<#if payStatus==CoeOrder.STATUS_PAY_WAIT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
    <span class="text-danger"><strong>${label}</strong></span>
    <#elseif payStatus == CoeOrder.STATUS_PAY_SENT && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
	<span class="text-primary"><strong>${label}</strong></span>
	<#elseif payStatus lt CoeOrder.STATUS_PAY_RECIEVED && flowStatus lt CoeOrder.FLOW_STATUS_COMPLETED>
	<span class="text-success"><strong>${label}</strong></span>
	<#else>
	<span class="text-default"><strong>${label}</strong></span>
	</#if>
</#macro>

<#macro showPullCode payStatus=0 flowStatus=0 pullStatus=2 expiredTime=null pullCode="" entityId=null>
<#assign label=CoeOrder.payStatusMap.get(payStatus) />
	<#if payStatus lt CoeOrder.STATUS_PAY_SENT>
    <span class="text-danger"><strong>${pullCode?replace("\\w","*",'r')}</strong></span>
    <strong><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;" onclick="queryPullCode(${payStatus},'${entityId}')"></span></strong>
	<#elseif payStatus gte CoeOrder.FLOW_STATUS_CANCEL>
	<span class="text-danger"><strong>${pullCode?replace("\\w","*",'r')}</strong></span>
	
	<#elseif pullStatus == CoeOrder.PULL_STATUS_EXPIRED>
	<span class="text-danger"><strong>${pullCode?replace("\\w","*",'r')}</strong></span>
	<#elseif expiredTime == null>
	<span class="text-danger"><strong>${pullCode?replace("\\w","*",'r')}</strong></span>
	<#else>
	${pullCode}
	<strong><span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;" onclick="queryPullCode(${payStatus},'${entityId}')"></span></strong>
	</#if>
</#macro>
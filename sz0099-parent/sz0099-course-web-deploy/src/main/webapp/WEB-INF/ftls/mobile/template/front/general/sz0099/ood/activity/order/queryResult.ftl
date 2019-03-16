<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_order.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<p><strong>订单编号 : </strong>${entity.id}</p>
<p><strong>支付订单编号 : </strong>${response.outTradeNo}</p>
<p><strong>商户号 : </strong>${response.mchId}</p>
<strong>支付金额 : </strong>${response.cashFee}<br/>
<strong>支付状态 : ${response.tradeStatus}</strong><br/>
<strong>交易类型 : ${response.tradeType}</strong><br/>
<strong>通信标识 : ${response.returnCode} ${response.returnMsg}</strong><br/>
<strong>业务状态 : ${response.resultCode} </strong><br/>
<strong>错误信息 : ${response.errCode} ${response.errCodeDes}</strong><br/>
<strong>openid : ${response.openid} </strong><br/>
<strong>支付完成时间 : ${response.timeEnd} </strong><br/>

<#if response.success==CoeActivityOrder.SUCCESS_YES >
<#else>
<input type="hidden" id="respCode" name="respCode" 
	value="${response.respCode}" 
/>
<h4 class="text-center text-danger">${response.respMsg}</h4>
</#if>

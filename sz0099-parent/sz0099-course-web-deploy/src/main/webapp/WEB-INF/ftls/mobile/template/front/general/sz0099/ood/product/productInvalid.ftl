<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">

<div class="container">
  <div class="text-center"><h4>订单结果</h4></div>
  <p class="text-danger">您选择的课程编号：<strong>${entity.productNo}</strong></p>
  <p><strong>${entity.title}</strong></p>
  <p>订单流水编号：<strong>${entity.flowNo}</strong></p>
  <h4 class="text-success">${entity.respMsg}</h4>
  <input type="hidden" name="respCode" value="${entity.respCode}" />
  <@M_introduction/>
</div>


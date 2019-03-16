<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container">
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<input type="hidden" id="id_input_success_code" name="successCode" value="${CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_INSTRUCTION_SUCCESS}"/>
<input type="hidden" id="id_input_opration_resp" name="respCode" value="${entity.respCode}"/>
<#if entity.success == 1>
  <div class="text-center"><h4>请务必阅读以下购买说明</h4></div>
  <p>1.选择课程，点击下单[<span class="text-danger">注意填写手机号与email</span>]</p>
  <p>2.付款(暂不支持公众号直接支付，仅支持微信扫码或支付宝转帐),<span class="text-danger">付款备注务必填写订单流水号</span></p>
  <p>3.付款后，用微信登录本公众号查看订单(<span class="text-danger">位置：菜单栏 [买技术]>[课程]</span>)，提取方式与密码在订单中会有说明</p>
  <p>4.发货方式：方式①云盘数据提取；方式②陆续发送至您下单邮箱;</p>
  <p>5.因网络上传速率影响，已下单用户的学习资料24小时内陆续发送，着急者勿拍</p>
  <p>6.电子资料均不支持退款，下单请慎重</p>
  <p>7.本站资源仅供自主研究学习使用，请勿用于商业，违者必究</p>
  <p>其他：客服微信: ly275060435 <br/>客服QQ: 275060435</p>
  <h5 class="text-danger">您选择的课程编号：<strong>${entity.productNo}</strong></h5>
  <h5><strong>${entity.title}</strong></h5>
</div>
<#else>
<h4 id="id_common_respMsg" class="<#if entity.success == 1>text-success<#else>text-danger</#if>">${entity.respMsg}</h4>
</#if>


<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#-- 修改email -->
	
<div class="container">
  <div class="text-center"><h4>修改Email验证结果</h4></div>
  <#if entity.success==CoeOrder.SUCCESS_NO>
  <div class="text-danger" id="id_div_messageTip">${entity.respMsg}</div>
  <#else>
  <div class="input-group">
	  <span class="input-group-addon text-danger" id="sizing-addon_email">*</span>
	  <input type="text" name="email" id="id_input_email" class="form-control" value="${entity.email}" placeholder="输入email" aria-describedby="sizing-addon_email">
	  <input type="hidden" name="id" id="id_input_orderId" value="${entity.id}" class="form-control" >
	  <input type="hidden" name="status" id="id_input_status" value="${entity.status}" class="form-control" >
  	  <input type="hidden" name="respCode" value="${entity.respCode}" />
  </div>
  </#if>
  
  <input type="hidden" name="successCode" id="id_hidden_successCode" value="${CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_MODIFY_EMAIL_SUCCESS}" />
	
</div>
<script>
</script>



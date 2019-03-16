<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
  <p>该资料不支持退款，下单请慎重</p>
  <div class="panel panel-default">
	  <div class="panel-heading">订单编号: ${entity.flowNo}</div>
	  <div class="panel-body">
	    <form id="id_form_confirm_order">
		      <div class="text-danger" id="id_div_messageTip"></div>
		      <input type="hidden" id="id_input_success_code" name="successCode" value="${CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_FLOWERID_SUCCESS}"/>
			  <input type="hidden" id="id_input_opration_resp" name="respCode" value="${entity.respCode}"/>
			  
			  <input name="orderId" id="id_input_orderId" type="hidden" value="${entity.id}">
			  <input name="payType" id="id_input_payType" type="hidden" value="${CoeOrder.PAYTYPE_OFF_WECHAT}">
			  <input name="flowNo" id="id_input_flowNo" type="hidden" value="${entity.flowNo}">
			  <#assign productList=entity.productList />
			  	<#if productList! && productList?size gt 0>
				  	<#list productList as product>
				  		<p class="panel-heading">课程编号: ${product.productNo}</p>
				  		<p class="text-danger"><strong>${product.title}</strong></p>
					  	<span class="text-info"><del>原价:${PriceUtil.f2y(product.priceOri)}</del></span>  
			  		  	<span class="text-danger"><i>现价: ${PriceUtil.f2y(product.priceCur)}</i></span>
					</#list>
			    </#if>
			  <div class="input-group input-group-sm">
				  <span class="input-group-addon" id="sizing-addon_email"><font color="red">邮箱*</font></span>
				  <input type="text" name="email" id="id_input_email" value="${coeUser.getEmail()}" class="form-control" placeholder="输入email" aria-describedby="sizing-addon_email">
			  </div>
			  
			  <div class="input-group input-group-sm">
				  <span class="input-group-addon" id="sizing-addon_mobile"><font color="red">手机*</font></span>
				  <input type="text" name="mobile" id="id_input_mobile" value="${coeUser.getMobile()}" class="form-control" placeholder="输入手机号" aria-describedby="sizing-addon_mobile">
			  </div>
			  <div class="input-group input-group-sm">
				  <span class="input-group-addon text-danger" id="sizing-addon_remark"><font color="blue">留言&nbsp;</font></span>
				  <input type="text" name="remark" id="id_input_remark" class="form-control" placeholder="附加留言" aria-describedby="sizing-addon_remark">
			  </div>
			  <p class="text-danger">请确定上述信息填写正确！</p>
	    </form>
	  </div>
	  <div class="panel-footer">
	  <span class="text-danger"><strong>应付: ${PriceUtil.f2y(entity.price)} 元</strong></span>
	  </div>
  </div>
  <div class="text-center">
  	<div>扫码支付，支付完成后点 确认下单</div>
  	<div><strong>切记：扫码付款时"添加备注"须填订单流水号:</strong></div>
  	<h4 class="text-danger">${entity.flowNo}</h4>
  	<div class="text-danger">以备客服查验，不填写将视为无效订单</div>
  	<div class="row text-center">
		  <div class="col-xs-6 col-md-3">
		  	<button id="id_btn_wechat" type="button" class="btn btn-primary btn-xs" onclick="changeQrcode('wechat')">微信支付
		  		<span id="id_span_wechat" class="glyphicon glyphicon-ok" aria-hidden="true"></span>
		  	</button>
		  	</div>
		  	<div class="col-xs-6 col-md-3">
		  	<button id="id_btn_ali" type="button" class="btn btn-default btn-xs" onclick="changeQrcode('ali')">支付宝
		  		<span id="id_span_ali" class="glyphicon glyphicon-ok" aria-hidden="true"></span>
		  	</button>
		  	</div>
	</div>
  	<div class="row">
		  <div class="col-xs-6 col-md-3">
		    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_wechat">
		      <img src="/assets/jit4j_jui/user/wechat_pay_code.png" alt="微信二维码" class="img-rounded">
		      	<span class="text-danger">微信扫码付款</span>
		    </a>
		  </div>
		  <div class="col-xs-6 col-md-3">
		    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_ali">
		                 支付宝帐号：feihu00000@163.com
		      <img src="/assets/jit4j_jui/user/ali_pay_code.png" alt="支付宝二维码" class="img-rounded">
		    </a>
		  </div>
	</div>
	
  	
  </div>
  <script>
  	function changeQrcode(qr){
  		if(qr=="wechat"){
  			$("#id_span_wechat").show();
  			$("#id_span_ali").hide();
  			$("#id_a_qr_wechat").show();
  			$("#id_a_qr_ali").hide();
  			$("#id_btn_ali").removeClass("btn-primary");
  			$("#id_btn_ali").addClass("btn-default");
  			$("#id_btn_wechat").removeClass("btn-default");
  			$("#id_btn_wechat").addClass("btn-primary");
  			$("#id_input_payType").val(${CoeOrder.PAYTYPE_OFF_WECHAT});
  		}else{
  			$("#id_span_ali").show();
  			$("#id_span_wechat").hide();
  			$("#id_a_qr_ali").show();
  			$("#id_a_qr_wechat").hide();
  			$("#id_btn_wechat").removeClass("btn-primary");
  			$("#id_btn_wechat").addClass("btn-default");
  			$("#id_btn_ali").removeClass("btn-default");
  			$("#id_btn_ali").addClass("btn-primary");
  			$("#id_input_payType").val(${CoeOrder.PAYTYPE_OFF_ALI});
  		}
  	}
  	changeQrcode("wechat");
	
  	
  </script>

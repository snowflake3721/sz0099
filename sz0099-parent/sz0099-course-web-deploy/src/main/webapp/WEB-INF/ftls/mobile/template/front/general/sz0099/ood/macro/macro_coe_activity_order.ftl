<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#macro Mg_actOrderPage entityPage=null>
<div class="panel panel-default">
  <div class="panel-heading">活动订单列表</div>
  <div class="panel-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<ul class="list-group">
					<#if entityPage! && entityPage.totalElements gt 0>
						<#assign entityList=entityPage.content />
						<#list entityList as entity0 >
							<li class="list-group-item <#if entity0?is_even_item>list-group-item-warning</#if>" id="id_act_order_li${entity0.id}" data-saved="0">
								<p class="text-left" data-status="${entity0.orderStatus}">
								${entity0.title}<br/>
									<small><strong>订单编号：</strong>${entity0.outTradeNo}</small><br/>
									<small><strong>订单状态：</strong>${Order.ORDER_STATUS.getLabel(entity0.orderStatus)}</small>
								</p>
								<p>
									<small><strong>下单时间：${DateUtils.formatToString(entity0.orderTime ,'yyyy-MM-dd HH:mm')}</strong></small><br/>
									<small><strong>订单金额：<span class="text-danger" id="id_order_totalAmount">${AmountUtil.changeF2Y(entity0.totalRmb)}</span> 元</strong></small>
									<br/>
									<#assign orderLog=entity0.orderLog />
									<small><strong>活动时间：${DateUtils.formatToString(orderLog.beginTime ,'yyyy-MM-dd HH:mm')}-${DateUtils.formatToString(orderLog.endTime ,'yyyy-MM-dd HH:mm')}</strong></small>
								</p>
								<p>
									<#-- 取消订单 -->
									<#assign orderStatus=entity0.orderStatus />
									<#if orderStatus==Order.ORDER_STATUS_INIT.valueInt>
										<a class="btn btn-xs btn-danger" href="/sz0099/ood/activity/order/manage/addUI?mainId=${entity0.mainId}">去确认</a>
									<#elseif orderStatus==Order.ORDER_STATUS_GENERATED.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-warning" data-saved="0"
									    href="/sz0099/ood/activity/order/manage/prePay?id=${entity0.id}"
									    > 去付款 </a>
									<#elseif orderStatus==Order.ORDER_STATUS_CANCEL.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-warning" data-saved="0"
									    href="/sz0099/ood/activity/order/manage/addUI?mainId=${entity0.mainId}"
									    > 重新下单 </a>
									<#elseif orderStatus==Order.ORDER_STATUS_CHECKOUT.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-warning" data-saved="0"
									    href="javascript:void(0)"
									    > 结算中 </a>
										    <input type="hidden" name="id" value="${entity0.outTradeNo}" 
											id="id_orderquery${entity0.outTradeNo}"
											data-url="/sz0099/ood/activity/order/doOrderquery"
											data-wrapper="id_orderquery_wrapper${entity0.outTradeNo}"
											data-saved="0"
											/>
											<button type="button" class="btn btn-xs btn-primary" onclick="doOrderquery('id_orderquery${entity0.outTradeNo}')">查询</button>
											<div id="id_orderquery_wrapper${entity0.outTradeNo}">
											</div>
									<#elseif orderStatus==Order.ORDER_STATUS_PAY_SUCCESS.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-warning" data-saved="0"
									    href="javascript:void(0)"
									    > 已支付 </a>
									<#elseif orderStatus==Order.ORDER_STATUS_PAY_FAILURE.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-danger" data-saved="0"
									    href="javascript:void(0)"
									    > 支付失败 </a>
									<#elseif orderStatus==Order.ORDER_STATUS_WAIT_RUN.valueInt>
										<a id="id_btn_payOrder" class="btn btn-xs btn-danger" data-saved="0"
									    href="javascript:void(0)"
									    > 等待出行 </a>
									</#if>
									
									<#if OrderUtil.checkRefundable(orderLog.offTime)>
										<a id="id_btn_payOrder" class="btn btn-xs btn-danger" data-saved="0"
										    href="javascript:void(0)"
										    > 申请退款 </a>
									</#if>
								</p>
							</li>
						</#list>
					<#else>
						<li class="list-group-item list-group-item-info">
							暂无活动订单
						</li>
					</#if>
				</ul>
			</div>
		</div><#-- end row -->
		<div class="row">
			<div class="col-xs-12 col-md-12">
		  	<ul id="id_page_wrapper"></ul>
		  	<input id="id_page_loaded" name="loaded" type="hidden" value="0" data-index="0"/>
		  	<input id="id_page_url" type="hidden" name="url" value="/sz0099/ood/activity/order/findPage"/>
		  	<input id="id_page_currentPage" type="hidden" name="page" value="${entityPage.number}"/>
		  	<input id="id_page_size" type="hidden" name="size" value="${entityPage.size}"/>
		  	<input id="id_page_totalPages" type="hidden" name="totalPages" value="${entityPage.totalPages}"/>
			</div>
		</div>
  </div>
</div>
</#macro>

<#macro Mg_actOrderView entity=null>
		<#assign activity=entity.activity/>
		<@Mg_actShortDetailView entity=activity/>
			<p class="text-left" data-status="${entity.orderStatus}">
				<small><strong>订单编号 : </strong>${entity.outTradeNo}</small>
				<small><strong>订单状态：</strong>${Order.ORDER_STATUS.getLabel(entity.orderStatus)}</small>
			</p>
			<ul class="list-group" id="id_act_user_wrapper${entity.id}">
				<@Mg_actOrderUserListView entityList=entity.userList />
		   </ul>
		   <hr/>
		   <div>
		   <small><strong>费用说明 : </strong> </small>
		   <h5>${HtmlUtil.textarea2UnEscapeForHtml(activity.actFee.description)}</h5>
		   </div>
		   <p class="text-center">
		   单价：<span class="text-danger" id="id_span_act_order_userAmount">${AmountUtil.changeF2Y(entity.rmb)}</span> 元/人 共 <strong>
		   <span class="text-danger" id="id_act_order_user_num">${entity.num}</span></strong> 份, 费用合计：
		   <strong><span class="text-danger" id="id_order_totalAmount">${AmountUtil.changeF2Y(entity.totalRmb)}</span> 元</strong>
		   </p>
		   <br/>
		   <@Mg_actOrderBtn entity=entity />
</#macro>

<#macro Mg_actOrderBtn entity=null>
	
	<p class="text-center"><span id="id_btn_cancelOrder_showTip"></span>
	</p>
    <p class="text-center" id="id_act_btn_wrapper${entity.id}"></p>
	<#if entity.orderStatus==Order.ORDER_STATUS_GENERATED.valueInt>
	<input type="hidden" id="id_act_order_id" value="${entity.id}"
			data-url="/sz0099/ood/activity/order/manage/cancelOrder"
	    	data-mainId="${entity.mainId}"
	    	data-btnWrapperId="id_act_btn_wrapper${entity.id}"
	    	>
   <a id="id_btn_payOrder" class="btn btn-warning" data-saved="0"
    href="/sz0099/ood/activity/order/manage/prePay?id=${entity.id}"
    > 去付款 </a>
    
    <button id="id_btn_cancelOrder" class="btn btn-danger" data-saved="0"
		   	onclick="cancelOrder('id_act_order_id','id_btn_cancelOrder')"> 
		   	取消订单 
	</button>
	<#elseif entity.orderStatus==Order.ORDER_STATUS_PAY_BEGIN.valueInt
	 || entity.orderStatus==Order.ORDER_STATUS_CHECKOUT.valueInt
	 || entity.orderStatus==Order.ORDER_STATUS_PAY_FAILURE.valueInt
	 >
	<input type="hidden" id="id_act_order_id" value="${entity.id}"
			data-url="/sz0099/ood/activity/order/manage/cancelOrder"
	    	data-mainId="${entity.mainId}"
	    	data-btnWrapperId="id_act_btn_wrapper${entity.id}"
	    	>
   <a id="id_btn_payOrder" class="btn btn-warning" data-saved="0"
    href="/sz0099/ood/activity/order/manage/prePay?id=${entity.id}"
    > 继续支付 </a>
    
    <button id="id_btn_cancelOrder" class="btn btn-danger" data-saved="0"
		   	onclick="cancelOrder('id_act_order_id','id_btn_cancelOrder')"> 
		   	取消订单 
	</button>
    <#elseif entity.orderStatus==Order.ORDER_STATUS_INIT.valueInt>
    		<button id="id_btn_confirmOrder" class="btn btn-danger" data-saved="0"
		   	onclick="confirmOrder('id_act_order_id','id_btn_confirmOrder')"> 
		   	确认报名 
		   </button>
	<#else>
    		${orderStatus==Order.ORDER_STATUS.getLabel(entity.orderStatus)}
    		<a id="id_btn_cancelOrder" class="btn btn-primary" data-saved="0"
		    href="/sz0099/ood/home/article/index/recommend?st=general"
		    > 返回列表 </a>
   </#if>
   
</#macro>

<#macro Mg_actOrder entity=null>
	<#if entity!>
		<#assign status=entity.orderStatus />
		
		<#if status==Order.ORDER_STATUS_INIT.valueInt>
			<#assign activity=entity.activity/>
			<@Mg_actShortDetailView entity=activity/>
			<input type="hidden" id="id_act_order_id" value="${entity.id}"
			data-url="/sz0099/ood/activity/order/manage/confirmOrder"
	    	data-addUserUrl="/sz0099/ood/activity/order/user/manage/add"
	    	data-deleteUserUrl="/sz0099/ood/activity/order/user/manage/delete"
	    	data-prePayUrl="/sz0099/ood/activity/order/manage/prePay?id=${entity.id}"
	    	
	    	data-mainId="${entity.mainId}"
	    	data-userWrapperId="id_act_user_wrapper${entity.id}"
	    	data-userNumId="id_act_order_user_num"
	    	data-userAmountId="id_act_order_user_rmbAmount"
	    	data-totalAmountId="id_order_totalAmount"
	    	>
	    	<div>
		   	常用联系人
		   	<button class="btn btn-default btn-xs">关羽</button>
		   	<button class="btn btn-default btn-xs">杨延昭</button>
		   	<button class="btn btn-default btn-xs">秦琼</button>
		    </div>
			<p class="text-left">
				<small><strong>订单编号 : </strong>${entity.outTradeNo}</small>
				<button class="btn btn-xs btn-info pull-right">
				<small>
					<span class="glyphicon glyphicon-plus"
					onclick="addUser('id_act_order_id','id_btn_addUser')"
			 		data-saved="0"
			 		id="id_btn_addUser"
					>增加人员</span>
				</small>
				</button>
			</p>
			<ul class="list-group" id="id_act_user_wrapper${entity.id}">
				<@Mg_actOrderUserList entityList=entity.userList />
		   </ul>
		   
		   <hr/>
		   <div>
		   <small><strong>费用说明 : </strong> </small>
		   <h5>${HtmlUtil.textarea2UnEscapeForHtml(activity.actFee.description)}</h5>
		   </div>
		   <p class="text-center">
		   <input type="hidden" id="id_act_order_user_rmbAmount" name="rmbAmount" data-singleName="rmbAmount" value="${activity.actFee.rmbAmount}"/>
		   单价：<span class="text-danger" id="id_span_act_order_userAmount">${AmountUtil.changeF2Y(activity.actFee.rmbAmount)}</span> 元/人 共 <strong>
		   <span class="text-danger" id="id_act_order_user_num"></span></strong> 份, 费用合计：<strong>
		   <span class="text-danger" id="id_order_totalAmount"></span> 元</strong>
		   </p>
		   <p class="text-center">
		   <span id="id_btn_confirmOrder_showTip"></span>
		   </p>
		   
		   <p class="text-center">
		   <button id="id_btn_confirmOrder" class="btn btn-danger" data-saved="0"
		   onclick="confirmOrder('id_act_order_id','id_btn_confirmOrder')"> 确认报名 
		   </button>
		   </p>
	   	<#else>
	   	<@Mg_actOrderView entity=entity/>
	   	
   		</#if>
   </#if>
</#macro>

<#macro Mg_actOrderUserList entityList=null>
<#if entityList! && entityList?size gt 0 >
	<#list entityList as entity0 >
		<@Mg_actOrderUserSingle entity0=entity0 />
	</#list>
</#if>
</#macro>
<#macro Mg_actOrderUserSingleWithoutLi entity0=null>
		<div class="row">
				<input id="id_act_user_id${entity0.id}" type="hidden" value="${entity0.id}"/> 
				<div class="col-xs-3 text-right">
				真实姓名
				</div>
    			<div class="col-xs-7">
			    	<input id="id_act_user_realname${entity0.id}" 
			    	name="realname" type="text" 
			    	data-singleName="realname"
			    	class="form-control input-sm" 
			    	value="${entity0.realname}" 
			    	data-saveUrl="/sz0099/ood/activity/order/user/manage/mergeRealname"
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-saved="0"
			    	data-allowedLength="10"
			    	placeholder="真实姓名" 
			    	onblur="commitPlace('id_act_user_realname${entity0.id}',  '${entity0.id}')"
			    	onkeyup="watchField('id_act_user_realname${entity0.id}',  '${entity0.id}')"
			    	>
			    	<span class="pull-right"><span id="id_act_user_realname${entity0.id}_length">${entity0.realname?length}</span>/10</span>
				    <span class="hidden" id="id_act_user_realname${entity0.id}_showTip"></span>
			   </div>
			   <div class="col-xs-2 text-right">
			   <button type="button" class="close"><span class="glyphicon glyphicon-remove" onclick="deleteUser('id_act_order_id','id_act_user_li${entity0.id}','${entity0.id}')"></span></button>
			   </div>
			   
		</div>
		<div class="row">	   
			   <div class="col-xs-3 text-right">
				身份证号
				</div>
			   <div class="col-xs-7">
			    	<input id="id_act_user_identity${entity0.id}" 
			    	name="identity" type="text" 
			    	data-singleName="identity"
			    	class="form-control input-sm" 
			    	value="${HtmlUtil.textarea2UnEscape(entity0.identity)}" 
			    	data-saveUrl="/sz0099/ood/activity/order/user/manage/mergeIdentity"
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-allowedLength="19"
			    	data-saved="0"
			    	onblur="commitPlace('id_act_user_identity${entity0.id}',  '${entity0.id}')"
			    	onkeyup="watchField('id_act_user_identity${entity0.id}',  '${entity0.id}')"
			    	placeholder="身份证号">
			    	<span class="pull-right"><span id="id_act_user_identity${entity0.id}_length">${entity0.identity?length}</span>/19</span>
				    <span class="hidden" id="id_act_user_identity${entity0.id}_showTip"></span>
			   </div>
			   <div class="col-xs-2">
			   		
			   </div>
		</div>
		<div class="row">	   
			   <div class="col-xs-3 text-right">
				手机号
				</div>
			   <div class="col-xs-7">
			    	<input id="id_act_user_mobile${entity0.id}" 
			    	name="mobile" type="text" 
			    	data-singleName="mobile"
			    	class="form-control input-sm" 
			    	value="${HtmlUtil.textarea2UnEscape(entity0.mobile)}" 
			    	data-saveUrl="/sz0099/ood/activity/order/user/manage/mergeMobile"
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-allowedLength="11"
			    	data-saved="0"
			    	onblur="commitPlace('id_act_user_mobile${entity0.id}',  '${entity0.id}')"
			    	onkeyup="watchField('id_act_user_mobile${entity0.id}',  '${entity0.id}')"
			    	placeholder="手机号">
			    	<span class="pull-right"><span id="id_act_user_mobile${entity0.id}_length">${entity0.mobile?length}</span>/11</span>
				    
				    <span class="hidden" id="id_act_user_mobile${entity0.id}_showTip"></span>
			   </div>
			   <div class="col-xs-2">
			   		
			   </div>
		  </div>
		  <div class="text-center">
		   <span id="id_act_user_li${entity0.id}_showTip"></span>
		  </div>
</#macro>

<#macro Mg_actOrderUserSingle entity0=null>
<#if entity0!>
		<li class="list-group-item list-group-item-info" id="id_act_user_li${entity0.id}" data-saved="0">
			<@Mg_actOrderUserSingleWithoutLi entity0=entity0 />
		</li>
</#if>
</#macro>

<#macro Mg_actOrderUserListView entityList=null>
<#if entityList! && entityList?size gt 0 >
	<#list entityList as entity0 >
		<@Mg_actOrderUserSingleView entity0=entity0 />
	</#list>
</#if>
</#macro>

<#macro Mg_actOrderUserSingleView entity0=null>
<#if entity0!>
		<li class="list-group-item list-group-item-info" id="id_act_user_li${entity0.id}" data-saved="0">
			<@Mg_actOrderUserSingleWithoutLiView entity0=entity0 />
		</li>
</#if>
</#macro>
<#macro Mg_actOrderUserSingleWithoutLiView entity0=null>
		<div class="row">
				<input id="id_act_user_id${entity0.id}" type="hidden" value="${entity0.id}"/> 
				<div class="col-xs-3 text-right">
				真实姓名
				</div>
    			<div class="col-xs-7">
			    	<input id="id_act_user_realname${entity0.id}" 
			    	name="realname" type="text" 
			    	data-singleName="realname"
			    	class="form-control input-sm" 
			    	value="${entity0.realname}" 
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-saved="0"
			    	placeholder="真实姓名" 
			    	readonly="true"
			    	>
			   </div>
			   <div class="col-xs-2 text-right">
			   </div>
			   
		</div>
		<div class="row">	   
			   <div class="col-xs-3 text-right">
				身份证号
				</div>
			   <div class="col-xs-7">
			    	<input id="id_act_user_identity${entity0.id}" 
			    	name="identity" type="text" 
			    	data-singleName="identity"
			    	class="form-control input-sm" 
			    	value="${HtmlUtil.textarea2UnEscape(entity0.identity)}" 
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-saved="0"
			    	readonly="true"
			    	placeholder="身份证号">
			   </div>
			   <div class="col-xs-2">
			   		
			   </div>
		</div>
		<div class="row">	   
			   <div class="col-xs-3 text-right">
				手机号
				</div>
			   <div class="col-xs-7">
			    	<input id="id_act_user_mobile${entity0.id}" 
			    	name="mobile" type="text" 
			    	data-singleName="mobile"
			    	class="form-control input-sm" 
			    	value="${HtmlUtil.textarea2UnEscape(entity0.mobile)}" 
			    	data-id="${entity0.id}"
			    	data-baseId="${entity0.baseId}"
			    	data-mainId="${entity0.mainId}"
			    	data-saved="0"
			    	readonly="true"
			    	placeholder="手机号">
			   </div>
			   <div class="col-xs-2">
			   		
			   </div>
		  </div>
</#macro>
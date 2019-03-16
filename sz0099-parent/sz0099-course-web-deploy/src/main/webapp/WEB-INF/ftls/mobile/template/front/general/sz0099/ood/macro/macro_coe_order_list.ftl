<#macro M_coeOrderPage itemPage moreLoaded="true" url="/sz0099/ood/product/order/myCoeOrderList" condition="">   
<!--订单列表开始--> 
<#if itemPage??>
	  <#assign itemList=itemPage.content />
	  <#if itemList?size gt 0 >
      <#list itemList as itemSingle>
		  <div class="panel panel-default">
			  <div class="panel-heading">
			  订单流水：${itemSingle.flowNo} at ${DateUtils.format(itemSingle.orderTime,"yyyy-MM-dd HH:mm")}
				 <span class="pull-right">
				 	<strong>#${itemSingle?index}</strong>
				 </span>
			   </div>
			  <div class="panel-body">
			  	<#assign productList=itemSingle.productList />
			  	<#if productList! && productList?size gt 0>
				  	<#list productList as product>
					  	<div class="panel">
						  	<p>课程编号: ${product.productNo} </p>
						    <p>标题：${product.title}</p>
						    <del><span class="text-info">原价:${PriceUtil.f2y(product.priceOri)}</span></del>  
						  	<span class="text-danger">支付金额: ${PriceUtil.f2y(product.pricePay)}</span> 
						  	<p class="text-left" style="word-break:break-all; word-wrap:break-all;">提取链接：${product.link}</p>
					  		<div class="row">
							  <div class="col-xs-6">
							  	提取码：<@showPullCode payStatus=itemSingle.status flowStatus=itemSingle.flowStatus pullStatus=itemSingle.pullStatus expiredTime=DateUtils.formatToString(itemSingle.expiredTime ,'yyyy-MM-dd') pullCode=product.pullCode entityId=itemSingle.id/>
							  </div>
							  <div class="col-xs-6">提取方式：${CoeProduct.PULL_METHOD.getLabel(product.pullMethod)}</div>
							</div>
						</div>
				  	</#list>
			    </#if>
			    
			    <div class="row">
				  <div class="col-xs-6">订单状态：<@showPayStatus payStatus=itemSingle.status flowStatus=itemSingle.flowStatus /></div>
				  <div class="col-xs-6">交易状态：${CoeOrder.flowStatusMap.get(itemSingle.flowStatus)}</div>
				</div>
			    
			    <p>订单备注：${itemSingle.remark}</p>
			    
			    <div class="row">
				  <div class="col-xs-6">
				  	<#-- 
				  	提取码：<@showPullCode payStatus=itemSingle.status flowStatus=itemSingle.flowStatus pullStatus=itemSingle.pullStatus expiredTime=DateUtils.formatToString(itemSingle.expiredTime ,'yyyy-MM-dd') pullCode=itemSingle.pullCode />
				  	<strong><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;" onclick="queryPullCode(${itemSingle.status},'${itemSingle.id}')"></span></strong>
				   -->
				  </div>
				</div>
				<div class="row">
				  <div class="col-xs-12">有效期至：${DateUtils.formatToString(itemSingle.expiredTime ,'yyyy-MM-dd HH:mm')}</div>
				</div>
				<p>下单Email: <span id="id_span_email_show_${itemSingle.id}">${itemSingle.email}</span> &nbsp;
				<strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;" onclick="modifyEmail(${itemSingle.status},'${itemSingle.id}','${itemSingle.email}')"></span></strong>
				</p>
			    <p>提取状态：${CoeOrder.pullStatusMap.get(itemSingle.pullStatus)}</p>
			    <p>提取时间：${DateUtils.formatToString(itemSingle.pullTime ,'yyyy-MM-dd HH:mm')} </p>
			    
			     <@showButtonByPayStatus entity=itemSingle itemDivId=""/> 
			     <#-- 
			     <@showButtonByPayStatus payStatus=itemSingle.status flowStatus=itemSingle.flowStatus orderId=itemSingle.id /> 
			       -->
			  </div>
			  <div class="panel-footer">
			  <span class="text-danger">成交价: ${PriceUtil.f2y(itemSingle.price)}</span> 
			  <span class="text-danger">等级: ${DataDefHolder.getLabel(CoeGradeConverter.SZ0099_COURSE_PROD_COEGRADE,itemSingle.grade,'name')}</span> 
			  
			  </div>
		 </div>
  	  </#list>
  	  <div class="container">
		  	<ul id="pageContainer"></ul>
	  </div>
  	  <#else>
  	   <div class="panel panel-default">
			  <div class="panel-heading">订单列表为空</div>
			  <div class="panel-body">
			  <p class="text-right">暂无订单， <a class="btn btn-info" href="/sz0099/ood/product/index">去下单</a></p>
			  </div>
		</div>
  	  </#if>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${itemPage.number+1}&size=${itemPage.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  
<#else>
<div class="panel panel-default">
	  <div class="panel-heading">暂无订单</div>
	  <div class="panel-body">
	  <p class="text-right">暂无订单， <a class="btn btn-info" href="/sz0099/ood/product/index">去下单</a></p>
	  </div>
</div>	  
 </#if>
 </#macro>
 
 
 
 <#macro M_manageOrderPage itemPage moreLoaded="true" url="/sz0099/ood/product/order/manage/manageOrderList" condition="">   
<!--订单列表开始--> 
<#if itemPage??>
	  <#assign itemList=itemPage.content />
	  <#if itemList?size gt 0 >
      <#list itemList as itemSingle>
		  <div class="panel panel-default" id="id_order_item_${itemSingle.id}">
			  <div class="panel-heading">
			  订单流水：${itemSingle.flowNo} at ${DateUtils.format(itemSingle.orderTime,"yyyy-MM-dd HH:mm")}
				 <span class="pull-right">
				 	<strong>#${itemSingle?index}</strong>
				 </span>
			   </div>
			  <div class="panel-body">
			  <#assign productList=itemSingle.productList />
			  	<#if productList! && productList?size gt 0>
				  	<#list productList as product>
					  	<p>课程编号: ${product.productNo} </p>
					    <p>标题：${product.title}</p>
					    <del><span class="text-info">原价:${PriceUtil.f2y(product.priceOri)}</span></del>  
					  	<span class="text-danger">支付金额: ${PriceUtil.f2y(product.pricePay)}</span> 
					  	<p class="text-left" style="word-break:break-all; word-wrap:break-all;">提取链接：${product.link}</p>
				  		<div class="row">
						  <div class="col-xs-6">
						  	提取码：<@showPullCode payStatus=itemSingle.status flowStatus=itemSingle.flowStatus pullStatus=itemSingle.pullStatus expiredTime=DateUtils.formatToString(itemSingle.expiredTime ,'yyyy-MM-dd') pullCode=product.pullCode entityId=itemSingle.id/>
						  	<strong><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;" onclick="queryPullCode(${itemSingle.status},'${itemSingle.id}')"></span></strong>
						  </div>
						  <div class="col-xs-6">提取方式：${CoeProduct.PULL_METHOD.getLabel(product.pullMethod)}</div>
						</div>
				  	</#list>
			    </#if>
			    <div class="row">
				  <div class="col-xs-6">订单状态：<@showPayStatus payStatus=itemSingle.status flowStatus=itemSingle.flowStatus /></div>
				  <div class="col-xs-6">交易状态：${CoeOrder.flowStatusMap.get(itemSingle.flowStatus)}</div>
				</div>
			    
			    <p>订单备注：${itemSingle.remark}</p>
			    
				<div class="row">
				  <div class="col-xs-12">有效期至：${DateUtils.formatToString(itemSingle.expiredTime ,'yyyy-MM-dd HH:mm')}</div>
				</div>
				<p>下单Email: <span id="id_span_email_show_${itemSingle.id}">${itemSingle.email}</span> &nbsp;
				<strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;" onclick="modifyEmail(${itemSingle.status},'${itemSingle.id}','${itemSingle.email}')"></span></strong>
				</p>
			    <p>提取状态：${CoeOrder.pullStatusMap.get(itemSingle.pullStatus)}</p>
			    <p>提取时间：${DateUtils.formatToString(itemSingle.pullTime ,'yyyy-MM-dd HH:mm')} </p>
			     <@M_mangeButtonByPayStatus entity=itemSingle itemDivId="id_order_item_"+itemSingle.id/> 
			  </div>
			  <div class="panel-footer">
			  <span class="text-danger">成交金额: ${PriceUtil.f2y(itemSingle.price)}</span> 
			  <span class="text-danger">等级: ${DataDefHolder.getLabel(CoeGradeConverter.SZ0099_COURSE_PROD_COEGRADE,itemSingle.grade,'name')}</span> 
			  
			  </div>
		 </div>
  	  </#list>
  	  <div class="container">
		  	<ul id="pageContainer"></ul>
	  </div>
  	  <#else>
  	   <div class="panel panel-default">
			  <div class="panel-heading">订单列表为空</div>
			  <div class="panel-body">
			  <p class="text-center">暂无订单</p>
			  </div>
		</div>
  	  </#if>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${itemPage.number+1}&size=${itemPage.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  
<#else>
<div class="panel panel-default">
	  <div class="panel-heading">暂无订单</div>
	  <div class="panel-body">
	  <p class="text-center">暂无订单</p>
	  </div>
</div>	  
 </#if>
 </#macro>

<#macro MV_coeProductPage itemPage> 
<!--课程产品列表开始,VUE控制-->
 <div class="panel panel-default" v-for="itemSingle in ${itemPage.name}.content">
	  <div class="panel-heading">{{itemSingle.title}}</div>
	  <div class="panel-body">
	    {{itemSingle.description}}
	  </div>
	  <div class="panel-footer">
	  <del><span class="text-info">原价:{{itemSingle.priceOri}}</span></del>  <span class="text-danger">现价: {{itemSingle.priceCur}}</span> <a href="#" class="btn btn-xs btn-danger">购买</a>
	  </div>
 </div>
  <#-- !"/assets/common/images/logo/img_cover_100X100.gif" -->
<p class="text-right">
  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
</p>
<!--课程产品列表结束,VUE控制-->
</#macro>

<#macro M_coeOrderInprocess entity url="/sz0099/ood/order/manage/mergeForSent">   
		<div class="panel panel-default">
			  <div class="panel-heading">
			  订单流水：${entity.flowNo} at ${DateUtils.format(entity.orderTime,"yyyy-MM-dd HH:mm")}
				 <span class="pull-right">
				 	<strong>#</strong>
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
							  	
							  	<strong><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;" onclick="queryPullCode(${itemSingle.status},'${itemSingle.id}')"></span></strong>
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
</#macro>
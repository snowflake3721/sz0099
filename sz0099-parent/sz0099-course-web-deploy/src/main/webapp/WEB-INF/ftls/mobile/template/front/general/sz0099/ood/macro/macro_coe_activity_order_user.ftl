<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_detail_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">

<#macro Mg_actOrderUserPage entityPage=null activity=null condition=null>

<#if activity!>
<h4 class="text-center"> <strong>【${activity.preIntro}】${HtmlUtils.htmlUnescape(activity.title)} </strong></h4>
<#assign actTime=activity.actTime />
活动时间：${DateUtils.formatToString(actTime.beginTime ,'yyyy-MM-dd HH:mm')}
-
${DateUtils.formatToString(actTime.endTime ,'yyyy-MM-dd HH:mm')}
</#if>
<#assign actFee=activity.actFee/>
<div class="row">
<#if actFee!>
	<div class="col-xs-12">
    <#if actFee.feeType!>
    	<#assign feeLabel=CoeActivityFee.FEETYPE.getLabel(actFee.feeType)/>
    	<strong>缴费方式：<span class="bg-warning">${feeLabel}</span></strong>&nbsp;&nbsp; 
    	
    	<strong>报名费：
    	<span class="bg-danger">
    	<#if actFee.rmbAmount! && actFee.rmbAmount gt 0>
	    	${AmountUtil.changeF2Y(actFee.rmbAmount)}￥
	    	<#else>
	    	待定
    	</#if>
    	</span>
    	</strong>
    	<#if actFee.rmbAmountOri! && actFee.rmbAmountOri gt 0>
    		<span><small><del>原价：${AmountUtil.changeF2Y(actFee.rmbAmountOri)}￥</del></small></span>
    	</#if>
    </#if>
    </div>
</#if>
</div><#-- end row -->


<#assign currentPayStatus=CoeActivityUser.PAY_STATUS.getContext(condition.payStatus,0)/>
<div class="row">
	<div class="col-xs-8 col-md-8">
	<@M_dropdownBar id="id_data_" propertyContext=CoeActivityUser.PAY_STATUS current=currentPayStatus readonly=true showLabel=false/>
	</div>
	<div class="col-xs-4 col-md-4">
	<button class="btn btn-sm btn-primary" type="button" onclick="searchRoleForActUser('id_hidden_orderPage')">查询</button>
	</div>
</div>

			<div class="panel panel-default">
	   			  <div class="panel-heading">报名列表 ( ${entityPage.totalElements}/${activity.maxNum} )</div>
<#if entityPage! && entityPage.totalElements gt 0>
	<#assign entityList=entityPage.content />
	<#if entityList! && entityList?size gt 0 >
				  <div class="panel-body">
				    	<table class="table table-condensed">
				    		<thead>
						        <tr>
						          <th>序号</th>
						          <th>姓名</th>
						          <th>手机号</th>
						          <th>状态</th>
						          <th>操作</th>
						        </tr>
					      	</thead>
					      	<tbody>
			   			<#list entityList as user>
					  		<#assign isOwner=UserUtils.getUserId()==user.userId />
								<tr>
								  <td class="<#if isOwner>danger</#if>">${user?index}</td>
								  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.realname, 1, 0)}</td>
								  <td class="<#if isOwner>danger</#if>">${StringUtils.encyptStr(user.mobile, 3, 1)}</td>
								  <td class="<#if isOwner>danger</#if>">${Order.ORDER_STATUS.getLabel(user.status)}</td>
								  <td class="<#if isOwner>danger</#if>"><button type="button" class="btn btn-xs btn-warning">退款</button></td>
								</tr>
			   			</#list>
			   				</tbody>
						</table>
	</#if>
</#if>						
						<div class="row">
							<div class="col-xs-12 col-md-12">
						  	<ul id="id_page_wrapper"></ul>
						  	<input id="id_page_loaded" name="loaded" type="hidden" value="0" data-index="0"/>
						  	<input id="id_page_url" type="hidden" name="url" value="/sz0099/ood/activity/order/user/findUserPage"/>
						  	<input id="id_page_currentPage" type="hidden" name="page" value="${entityPage.number}"/>
						  	<input id="id_page_size" type="hidden" name="size" value="${entityPage.size}"/>
						  	<input id="id_page_totalPages" type="hidden" name="totalPages" value="${entityPage.totalPages}"/>
						  	<input id="id_data_mainId" type="hidden" name="mainId" value="${condition.mainId}"/>
							</div>
						</div><#-- end row -->
				  </div><#-- end body -->
			</div>


<p>
<strong>友情提示:</strong><br/>
<small>
成为领队后可查看联系方式
</small>
</p>
</#macro>


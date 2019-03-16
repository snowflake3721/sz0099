<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/macro/macro_role_create.ftl">

<#macro M_userRolePage entityPage url="/auth/userRole/manage/findUserRolePage">  
<#if entityPage! && entityPage.totalElements gt 0>
	<#assign contentList=entityPage.content/> 
	<ul class="list-group" id="id_ul_role">
		<#list contentList as child>
			<li class="list-group-item list-group-item-<#if child?is_odd_item>warning<#else>default</#if>" id="id_li_role_${child.id}">
			<div class="container text-center">
			<span class="text-danger ">
				<span class="glyphicon glyphicon-trash" aria-hidden="true" 
				id="id_btn_bind_un_${child.id}_span"
				onclick="delete('${child.id}','id_btn_bind_${child.id}','id_btn_bind_un_${child.id}_span')" data-wrapperId="id_li_role_${child.id}" data-url="/auth/role/manage/delete"
				>&nbsp;</span>
			</span>
			<span class="text-info">
				<span class="glyphicon glyphicon-eye-close text-success <#if UserRole.PERMANENT_NO.valueInt!=child.permanent>hidden</#if>" aria-hidden="true" 
				id="id_closed_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/permanent"
				title="未永久认证"
				>久</span>
				<span class="glyphicon glyphicon-eye-open text-success <#if UserRole.PERMANENT_NO.valueInt==child.permanent || child.permanent==null>hidden</#if>" aria-hidden="true" 
				id="id_open_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/permanent"
				title="已永久认证"
				>久</span>
				
				<span class="glyphicon glyphicon-ban-circle text-primary <#if UserRole.FROZEN_YES.valueInt==child.frozen>hidden</#if>" aria-hidden="true" 
				id="id_closed_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/frozen"
				title="已冻结"
				>冻</span>
				<span class="glyphicon glyphicon-ok-circle text-primary <#if UserRole.FROZEN_YES.valueInt!=child.frozen>hidden</#if>" aria-hidden="true" 
				id="id_open_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/frozen"
				title="已解冻"
				>冻</span>
				
				<span class="glyphicon glyphicon-remove text-info <#if UserRole.VERIFIED_YES.valueInt==child.verified>hidden</#if>" aria-hidden="true" 
				id="id_closed_verify${child.id}_span"
				onclick="verify('${child.id}','id_open_verify${child.id}_span','id_closed_verify${child.id}_span')" data-url="/auth/role/manage/verify"
				title="未缴"
				>审</span>
				<span class="glyphicon glyphicon-ok text-info <#if UserRole.VERIFIED_YES.valueInt!=child.verified>hidden</#if>" aria-hidden="true" 
				id="id_open_verify${child.id}_span"
				onclick="verify('${child.id}','id_open_verify${child.id}_span','id_closed_verify${child.id}_span')" data-url="/auth/role/manage/verify"
				title="已缴"
				>审</span>
				
				<span class="glyphicon glyphicon-remove text-danger <#if UserRole.DEPOSITCHECKED_NO.valueInt !=child.depositChecked && child.depositChecked!>hidden</#if>" aria-hidden="true" 
				id="id_closed_depositChecked${child.id}_span"
				onclick="depositChecked('${child.id}','id_open_depositChecked${child.id}_span','id_closed_verify${child.id}_span')" data-url="/auth/role/manage/depositChecked"
				title="未交"
				>信</span>
				<span class="glyphicon glyphicon-ok text-danger <#if UserRole.DEPOSITCHECKED_NO.valueInt==child.depositChecked || child.depositChecked==null>hidden</#if>" aria-hidden="true" 
				id="id_open_depositChecked${child.id}_span"
				onclick="depositChecked('${child.id}','id_open_depositChecked${child.id}_span','id_closed_depositChecked${child.id}_span')" data-url="/auth/role/manage/depositChecked"
				title="已交"
				>信</span>
				
				<span class="glyphicon glyphicon-edit" aria-hidden="true"
				id="id_merge_${child.id}"
				onclick="mergeRole('${child.id}', 'id_merge_${child.id}')"
				data-saveUrl="/auth/userRole/manage/merge"
				data-queryUrl="/auth/userRole/manage/findUserRoleForEditUI"
				title="编辑"
				></span>
				&nbsp;
				<span class="glyphicon glyphicon-plus" aria-hidden="true"
				id="id_add_${child.id}"
				onclick="addUserRole('${child.id}', 'id_add_${child.id}')"
				data-userId="${child.userId}"
				data-saveUrl="/auth/userRole/manage/add"
				data-queryUrl="/auth/userRole/manage/editUI"
				title="增加"
				>&nbsp;</span>
			</span>
			
			<input type="hidden" id="id_role_code${child.id}" name="role.code" value="${child.role.code}" />
			</div>
			
			<div class="row">
			  <div class="col-xs-4 col-md-4">
			  <img class="img-responsive" src="${child.user.avatar}" id="id_img_${child.id}" />
			  </div>
			  <div class="col-xs-8 col-md-8">
			  <strong>username：</strong>${child.user.username} 
			  <br/><strong>UID：</strong>${child.user.id} 
			  <br/><strong>姓名：</strong>${child.user.realname} <strong>${UserWechat.SEX.getLabel(child.user.wechat.sex)}</strong>
			  <br/><strong>手机号：</strong>${child.user.mobile}
			  <br/><strong>昵称：</strong>${Base64Util.decode(child.user.wechat.nickname)}
			  <br/><strong>角色：</strong><span class="bg-danger"><strong> ${child.role.name} - ${child.roleLevel} - ${UserRole.STATUS.getLabel(child.status)}</strong></span>
			  </div>
		    </div>
			<p>
			 
			<br/><strong>创建：</strong>${DateUtils.formatJota(child.createdDate,'yyyy-MM-dd HH:mm')} <strong>期限：</strong>${DateUtils.formatJava(child.expiredTime,'yyyy-MM-dd HH:mm')} 
			<br/><strong>关闭：</strong>${DateUtils.formatJava(child.closedTime,'yyyy-MM-dd HH:mm')} 
			<br/><small><strong>审核费：</strong>${child.verifyFee} <strong>状态：</strong>${UserRole.VERIFIED.getLabel(child.verified)}</small>
			<br/><small><strong>信誉保证金：</strong>${child.depositAmount} <strong>状态：</strong>${UserRole.DEPOSITCHECKED.getLabel(child.depositChecked)}</small>
			<br/><strong>身份证号：</strong>${child.user.identity} 
			
			</p>
			<p id="id_p_description${child.id}"><small>${getSubstring(child.remark,30)}</small></p>
			</li>
			
		</#list>
	</ul>
	<ul id="pageContainer"></ul>
</#if>
 </#macro>
<#macro M_userRoleSingle userRole=null code="">
<#if userRole!>
	<span>
	<#if userRole.role.code==code>
		<span class="label label-success" onclick="popTipCustom('角色信息','${userRole.role.description}<br/><p>名称：${userRole.role.name} - 星级：${userRole.roleLevel}★</p>')">
		${userRole.role.name} 
		<small>
		<#list 1..userRole.roleLevel as i>
		★
		</#list>
		</small>
		</span>
	<#else>
		<span class="label label-warning" onclick="popTipCustom('角色信息提醒','${userRole.role.description}<br/><p>名称：${userRole.role.name} - 该发布人角色非领队，会员参加活动时请慎重！</p>')">
		${userRole.role.name} 
		<span class="glyphicon glyphicon-warning-sign"></span>
		</span>
	</#if>
	</span>
</#if>
</#macro>

<#macro M_userRoleClubLeader userRole=null codes="CLUB_LEADER,CLUB_CREATOR" entity=null>
<#if userRole!>
	<span>
	<#assign hasCode=false />
	<#list codes?split(",") as code>
		<#if userRole.role.code==code>
			<span class="label label-success" onclick="popTipCustom('角色信息','${userRole.role.description}<br/><p>名称：${userRole.role.name} - 星级：${userRole.roleLevel}★</p>')">
			${userRole.role.name} 
			<small>
			<#list 1..userRole.roleLevel as i>
			★
			</#list>
			</small>
			</span>
			<#assign hasCode=true />
			<#break/>
		</#if>
	</#list>
	
	<#if !hasCode>
			<span class="label label-warning" onclick="popTipCustom('角色信息提醒','${userRole.role.description}<br/><p>名称：${userRole.role.name} <#if CoeActivity.ACT_ORGANIZE_CALLING.valueInt==entity.actOrganize && CoeActivity.ACT_STATUS_CALLING.valueInt==entity.actStatus>- 该发布人角色非领队，会员参加活动时请慎重！</#if></p>')">
			${userRole.role.name} 
			<#if CoeActivity.ACT_ORGANIZE_CALLING.valueInt==entity.actOrganize && CoeActivity.ACT_STATUS_CALLING.valueInt==entity.actStatus>
			<span class="glyphicon glyphicon-warning-sign"></span>
			</#if>
			</span>
	</#if>
	</span>
</#if>
</#macro>
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/macro/macro_role_create.ftl">

<#macro M_rolePage entityPage url="/auth/role/manage/findRolePage">  
<#if entityPage! && entityPage.totalElements gt 0>
	<#assign contentList=entityPage.content/> 
	<ul class="list-group" id="id_ul_role">
		<#list contentList as child>
			<#assign clazz=getClazzByDepth(deep)/>
			<li class="list-group-item list-group-item-<#if child?is_odd_item>warning<#else>default</#if>" id="id_li_role_${child.id}">
			<div>
			<span class="text-danger pull-left">
				<span class="glyphicon glyphicon-trash" aria-hidden="true" 
				id="id_btn_bind_un_${child.id}_span"
				onclick="delete('${child.id}','id_btn_bind_${child.id}','id_btn_bind_un_${child.id}_span')" data-wrapperId="id_li_role_${child.id}" data-url="/auth/role/manage/delete"
				>&nbsp;</span>
			</span>
			<span class="text-info pull-right">
				<span class="glyphicon glyphicon-eye-close <#if Role.PERMANENTABLE_YES.valueInt==child.permanentable>hidden</#if>" aria-hidden="true" 
				id="id_closed_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/permanent"
				title="永久认证已关闭"
				>&nbsp;</span>
				<span class="glyphicon glyphicon-eye-open <#if Role.PERMANENTABLE_YES.valueInt!=child.permanentable>hidden</#if>" aria-hidden="true" 
				id="id_open_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/permanent"
				title="永久认证已允许"
				>&nbsp;</span>
				
				<span class="glyphicon glyphicon-ban-circle <#if Role.FROZEN_YES.valueInt!=child.frozen>hidden</#if>" aria-hidden="true" 
				id="id_closed_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/frozen"
				title="已冻结"
				>&nbsp;</span>
				<span class="glyphicon glyphicon-ok-circle <#if Role.FROZEN_YES.valueInt==child.frozen>hidden</#if>" aria-hidden="true" 
				id="id_open_${child.id}_span"
				onclick="open('${child.id}','id_open_${child.id}_span','id_closed_${child.id}_span')" data-url="/auth/role/manage/frozen"
				title="已解冻"
				>&nbsp;</span>
				
				<span class="glyphicon glyphicon-remove <#if Role.VERIFIABLE_YES.valueInt==child.verifiable>hidden</#if>" aria-hidden="true" 
				id="id_closed_verify${child.id}_span"
				onclick="verify('${child.id}','id_open_verify${child.id}_span','id_closed_verify${child.id}_span')" data-url="/auth/role/manage/verify"
				title="审核已禁用"
				>&nbsp;</span>
				<span class="glyphicon glyphicon-ok <#if Role.VERIFIABLE_YES.valueInt!=child.verifiable>hidden</#if>" aria-hidden="true" 
				id="id_open_verify${child.id}_span"
				onclick="verify('${child.id}','id_open_verify${child.id}_span','id_closed_verify${child.id}_span')" data-url="/auth/role/manage/verify"
				title="审核已开启"
				>&nbsp;</span>
				
				<span class="glyphicon glyphicon-edit" aria-hidden="true"
				id="id_merge_${child.id}"
				onclick="mergeRole('${child.id}', 'id_merge_${child.id}')"
				data-saveUrl="/auth/role/manage/merge"
				data-queryUrl="/auth/role/manage/findRoleForEditUI"
				title="编辑"
				></span>
			</span>
			
			<input type="hidden" id="id_role_category${child.id}" name="category" value="${child.category}" />
			</div>
			<p>
			<strong>${child.name} - ${child.code}</strong> 
			</p>
			<p id="id_p_description${child.id}">${getSubstring(child.description,30)}</p>
			</li>
			
		</#list>
	</ul>
	<ul id="pageContainer"></ul>
</#if>
 </#macro>
 

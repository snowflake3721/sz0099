<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_userRoleAddUI entity=null >
<@M_userRoleEditUI entity=entity withBtnSubmit=false url="/auth/userRole/manage/add"/>
</#macro>
<#macro M_userRoleEditUI entity=null withBtnSubmit=false url="/auth/userRole/manage/merge" >
<form action="${url}" method="post" enctype="application/x-www-form-urlencoded">
<div class="row">
	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_user_realname'  class='text-success'>姓名</label>
		${entity.user.realname}
		</div>
	 </div>
  	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<#-- TODO 角色数据应从数据库查询 -->
		<@M_dropdownBarStr id="id_userRole_" propertyContext=Role.CODE_PLAT current=Role.CODE_PLAT.getLabelContextStr(entity.role.code) propertyName='role.code' propertyEnable=true readonly=true showLabel=false/>
		</div>
	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_user_identity'  class='text-success'
		id="id_clipboard_identity" class="btn btn-xs btn-success" data-clipboard-action="copy" data-clipboard-target="#id_span_identity"
		>身份证号</label>
		<span id="id_span_identity">${entity.user.identity}</span>
		</div>
	 </div>
  	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_userRole_roleLevel'  class='text-danger'>角色级别</label>
		<input type='text' id='id_userRole_roleLevel' name='roleLevel' value='${entity.roleLevel}' class='form-control' placeholder='输入 角色级别(数字)'
		onkeyup="keyPressPositive(this)"
		oninput="keyPressPositive(this)"
		onafterpaste="keyPressPositive(this)"
		>
		</div>
	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
	 <label for='id_user_mobile'  class='text-danger'
	 id="id_clipboard_mobile" class="btn btn-xs btn-success" data-clipboard-action="copy" data-clipboard-target="#id_span_mobile"
	 >手机号</label>
  	 <span id="id_span_mobile">${entity.user.mobile}18665859873</span>
	 </div>
	 <div class="col-xs-6">
	 <@M_dropdownBar id="id_userRole_" propertyContext=UserRole.FROZEN current=UserRole.FROZEN.getLabelContext(entity.frozen) readonly=true showLabel=false/>
	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_user_nickname'  class='text-danger'
		id="id_clipboard_nickname" class="btn btn-xs btn-success" data-clipboard-action="copy" data-clipboard-target="#id_span_nickname"
		>昵称</label>
		<span id="id_span_nickname">${Base64Util.decode(entity.user.wechat.nickname)}</span>
		</div>
	 </div>
  	 <div class="col-xs-6">
  	 <@M_dropdownBar id="id_userRole_" propertyContext=UserRole.PERMANENT current=UserRole.PERMANENT.getLabelContext(entity.permanent) readonly=true showLabel=false/>
  	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_user_createdDate'  class='text-success'>创建</label>
		${DateUtils.formatJota(entity.createdDate,'yyyy-MM-dd HH:MM')}
		</div>
	 </div>
  	 <div class="col-xs-6">
		<label for='id_userRole_expiredTime'  class='text-danger'>期限</label>
  	    <input id="id_picker_userRole_expiredTime" name="expiredTime" type="text" class="form-control" value="${DateUtils.formatJava(entity.expiredTime,'yyyy-MM-dd')}" data-date-format="yyyy-mm-dd"/>
  	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
		<div class='form-group form-group-sm' >
		<label for='id_userRole_closedTime'  class='text-success'>关闭</label>
		${DateUtils.formatJava(entity.closedTime,'yyyy-MM-dd HH:MM')}
		</div>
	 </div>
  	 <div class="col-xs-6">
		<@M_dropdownBar id="id_userRole_" propertyContext=UserRole.STATUS current=UserRole.STATUS.getLabelContext(entity.status) readonly=true showLabel=false/>
  	 </div>
</div>
<div class="row">
<div class="col-xs-6">
	 <div class='form-group form-group-sm' >
		<label for='id_userRole_verifyFee'  class='text-danger'>审核费(单位：分)</label>
		<input type='text' id='id_userRole_verifyFee' name='verifyFee' value='${entity.verifyFee}' class='form-control' placeholder='例：12300'
		onkeyup="keyPressPositive(this)"
		oninput="keyPressPositive(this)"
		onafterpaste="keyPressPositive(this)"
		>
		</div>
	 </div>
	 <div class="col-xs-6">
	 <@M_dropdownBar id="id_userRole_" propertyContext=UserRole.VERIFIED current=UserRole.VERIFIED.getLabelContext(entity.verified) readonly=true showLabel=true/>
	 </div>
</div>
<div class="row">
	 <div class="col-xs-6">
		 <div class='form-group form-group-sm' >
			<label for='id_userRole_depositAmount'  class='text-danger'>保证金(单位：分)</label>
			<input type='text' id='id_userRole_depositAmount' name='depositAmount' value='${entity.depositAmount}' class='form-control' placeholder='金额(分)'
			onkeyup="keyPressPositive(this)"
			oninput="keyPressPositive(this)"
			onafterpaste="keyPressPositive(this)"
			>
		 </div>
	 </div>
	 <div class="col-xs-6">
	 <@M_dropdownBar id="id_userRole_" propertyContext=UserRole.DEPOSITCHECKED current=UserRole.DEPOSITCHECKED.getLabelContext(entity.depositChecked) readonly=true showLabel=true/>
	 </div>
</div>
<div class="row">
	 <div class="col-xs-12">
		 <div class='form-group form-group-sm' >
			<input type='text' id='id_userRole_remark' name='remark' value='${entity.remark}' class='form-control' placeholder='备注'>
		 </div>
	 </div>
</div>
<#if withBtnSubmit>
<p class="text-right"><button type="submit" class="btn btn-danger">提交</button></p>
</#if>
<input type='hidden' id='id_userRole_id' name='id' value='${entity.id}'/>
<input type='hidden' id='id_userRole_userId' name='userId' value='${entity.userId}'/>
<input type='hidden' id='id_userRole_roleId' name='roleId' value='${entity.roleId}' />
</form>
<script>
$(document).ready(function(){
	initDatePicker('id_picker_userRole_expiredTime');
	new ClipboardJS('#id_clipboard_mobile');
	new ClipboardJS('#id_clipboard_identity');
	new ClipboardJS('#id_clipboard_nickname');
});
</script>
</#macro>



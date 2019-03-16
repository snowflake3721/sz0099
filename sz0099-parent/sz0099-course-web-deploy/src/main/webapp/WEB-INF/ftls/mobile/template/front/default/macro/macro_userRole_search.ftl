<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">


<#macro M_userRoleSearch entityPage propertyContext=null current=null url="/auth/userRole/manage/findUserRolePage">   
<div class="row">
		 <div class="col-xs-6">
		    <div class="input-group">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchUserRole(0)">搜索</button>
		      </span>
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="${url}">
		      <input type="hidden" id="id_page_currentPage" name="page" class="form-control" value="${entityPage.number}">
		      <input type="hidden" id="id_page_totalPages" name="totalPages" class="form-control" value="${entityPage.totalPages}">
		      <input type="hidden" id="id_page_size" name="size" class="form-control" value="${entityPage.size}">
		      <input type="text" id="id_data_role_name" name="role.name" class="form-control" placeholder="角色名称" value="${current.role.name}">
		      
		    </div>
	  	 </div>
	  	 <div class="col-xs-6">
			<@M_dropdownBarStr id="id_data_role_" propertyContext=Role.CODE_PLAT current=Role.CODE_PLAT.getLabelContextStr(current.role.code) propertyName='role.code' propertyEnable=true readonly=true showLabel=false/>
		 </div>
</div>
<div class="row">
  	<div class="col-xs-6">
  	<input type="text" id="id_data_user_realname" name="user.realname" class="form-control" placeholder="用户姓名" value="${current.user.realname}">
  	</div>
  	<div class="col-xs-6">
  	<input type="text" id="id_data_user_mobile" name="user.mobile" class="form-control" placeholder="手机号" value="${current.user.mobile}">
  	</div>
</div>
<div class="row">
  	<div class="col-xs-6">
  	<input type="text" id="id_data_user_identity" name="user.identity" class="form-control" placeholder="身份证号" value="${current.user.identity}">
  	</div>
  	<div class="col-xs-6">
  	<input type="text" id="id_data_user_wechat_nickname" name="user.wechat.nickname" class="form-control" placeholder="昵称" value="${current.user.wechat.nickname}">
  	</div>
</div>
 </#macro>

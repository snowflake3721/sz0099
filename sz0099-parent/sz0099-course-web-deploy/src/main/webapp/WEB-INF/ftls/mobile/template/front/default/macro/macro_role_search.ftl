<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">

 
 <#macro M_roleCategorySearch entityPage propertyContext=null current=null condition=null url="/auth/role/manage/findRolePage">   
<div class="row">
		<div class="col-xs-6">
		    <@M_dropdownBarStr id="id_data_" propertyContext=propertyContext current=current readonly=false showLabel=false/>
		</div>
		 <div class="col-xs-6">
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="${url}">
		      <input type="hidden" id="id_page_currentPage" name="page" class="form-control" value="${entityPage.number}">
		      <input type="hidden" id="id_page_totalPages" name="totalPages" class="form-control" value="${entityPage.totalPages}">
		      <input type="hidden" id="id_page_size" name="size" class="form-control" value="${entityPage.size}">
		      <input type="text" id="id_data_name" name="name" class="form-control" placeholder="角色名称" value="${condition.name}">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchRoleForCategory(0)">搜索</button>
		      </span>
		    </div>
	  	 </div>
  	</div>
 </#macro>
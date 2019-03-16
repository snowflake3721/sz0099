<#include "mobile/template/front/default/function/func_basic.ftl">
<#macro M_navBottomForSuper category="role" subCategory="" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
	    <ul class="nav nav-pills">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-danger" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">超级 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            
			            <li class="<#if category=="user">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList">用户</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="role">active</#if>"><a href="/auth/role/manage/findRolePage">角色</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="quan">active</#if>"><a href="/quan/manage/setting">圈设置</a></li>
			          	<li role="separator" class="divider"></li>
			            <li class="<#if category=="usersetting">active</#if>"><a href="/auth/user/manage/setting">用户设置</a></li>

			          </ul>
			   </li>
			   
			   <#if category=="user">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="list">active</#if>"><a href="/sz0099/ood/product/manage/queryProductManageList">列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/product/manage/create?id=">新建or编辑</a></li>
			          </ul>
			   </li>
			   <#elseif category=="role">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">角色<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="list">active</#if>"><a href="/auth/role/manage/findRolePage">列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/auth/role/manage/editUI?id=">新建or编辑</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="userRoleList">active</#if>"><a href="/auth/userRole/manage/findUserRolePage">角色用户</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="rolePermissionList">active</#if>"><a href="/auth/role/manage/findRolePage">角色权限</a></li>
			          </ul>
			   </li>
			   <#elseif category=="quan">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">圈子<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="setting">active</#if>"><a href="/quan/manage/setting">设置</a></li>
			            <li role="separator" class="divider"></li>
			          </ul>
			   </li>
			   <#elseif category=="usersetting">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户设置<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="setting">active</#if>"><a href="/auth/user/manage/setting">二维码</a></li>
			            <li role="separator" class="divider"></li>
			          </ul>
			   </li>
			   </#if>
			   <li class="dropdown pull-right">
			          <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">群侠 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="/sz0099/ood/index">首页</a></li>
			             <li><a href="/sz0099/ood/home/profession/index">技能圈</a></li>
			             <li class="<#if category=="qunxia">active</#if>"><a href="/sz0099/ood/home/article/index">户外圈</a></li>
			          </ul>
			   </li>
	    </ul>	
  </div>
</nav>
</#macro>

<#macro M_navBreadForSuper category="user" subCategory="list" entity=null>
<ol class="breadcrumb">
  <li><a href="#">超级管理</a></li>
  <#if category=="user">
  <li><a href="#">用户</a></li>
    <#if subCategory=="list">
  	<li class="active"><a href="#">列表</a></li>
  	<#elseif subCategory=="new">
  	<li class="active"><a href="#">新建or编辑</a></li>
  	</#if>
  	
   <#elseif category=="role">
   <li><a href="#">角色</a></li>
	   	<#if subCategory=="list">
	  	<li class="active"><a href="#">列表</a></li>
	  	<#elseif subCategory=="new">
	  	<li class="active"><a href="#">新建or编辑</a></li>
	  	<#elseif subCategory=="userRoleList">
	  	<li class="active"><a href="#">角色用户</a></li>
	  	</#if>
  </#if>
  
  
</ol>
</#macro>


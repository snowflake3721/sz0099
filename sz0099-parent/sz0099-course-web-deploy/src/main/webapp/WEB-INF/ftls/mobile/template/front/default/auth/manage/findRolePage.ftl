<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForSuper.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_role_search.ftl">
<#include "mobile/template/front/default/macro/macro_role_list.ftl">
<html>
<head>
<title>角色管理</title>
</head>
<body>
<div class="container " id="body_content">
<@M_navBreadForSuper category="role" subCategory="list"  entity=role/>
<@shiro.user>
<@M_roleCategorySearch entityPage=entityPage propertyContext=Role.CATEGORY current=Role.CATEGORY.getLabelContextStr(role.category) condition=role url="/auth/role/manage/findRolePage" />
<@M_rolePage entityPage=entityPage url="/auth/role/manage/findRolePage" />
</@shiro.user>
<@M_navBottomForSuper category="role" subCategory="list"  entity=null/>
	<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/auth/role/roleManage.js?v=1.0.2'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
});
//初始化分页对象
initPageForSearchRoleForCategory();
</script>
</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForSuper.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_userRole_search.ftl">
<#include "mobile/template/front/default/macro/macro_userRole_list.ftl">
<html>
<head>
<title>角色绑定用户管理</title>
</head>
<body>
<div class="container " id="body_content">
<@M_navBreadForSuper category="role" subCategory="userRoleList"  entity=role/>
<@shiro.user>
<@M_userRoleSearch entityPage=entityPage current=userRole url="/auth/userRole/manage/findUserRolePage"/>

<@M_userRolePage entityPage=entityPage url="/auth/role/manage/findRolePage" />

</@shiro.user>
<@M_navBottomForSuper category="role" subCategory="userRoleList"  entity=null/>
<input type="hidden" value="" name="userId" id="id_current_userId"/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/auth/role/userRoleManage.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jDatePicker.js?v=1.0.2'><\/script>");</script>
<script src="/assets/common/tools/bootstrap-datepicker/js/bootstrap-datepicker.js" crossorigin="anonymous"></script>
<script src="/assets/common/tools/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" crossorigin="anonymous"></script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/clipboard/clipboard.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
	$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
});

//初始化分页对象
initPageForSearchUserRole();
</script>
</div>
</body>
</html>
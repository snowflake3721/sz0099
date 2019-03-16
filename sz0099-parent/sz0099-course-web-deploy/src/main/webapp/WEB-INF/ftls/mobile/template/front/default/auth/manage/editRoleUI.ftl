<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForSuper.ftl">
<#include "mobile/template/front/default/macro/macro_role_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<html>
<head>
<title>创建角色</title>
</head>
<body>
<div class="container" id="body_content">
<@M_navBreadForSuper category="role" subCategory="new"  entity=entity/>
<@M_roleAddUI entity=entity />
<@M_navBottomForSuper category="role" subCategory="new"  entity=null/>
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/auth/role/roleManage.js?v=1.0.2'><\/script>");</script>
</div>
</body>
</html>
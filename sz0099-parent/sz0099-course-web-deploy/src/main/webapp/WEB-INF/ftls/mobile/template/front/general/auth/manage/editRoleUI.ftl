<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#-- 
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForSuper.ftl">
 -->
<#include "mobile/template/front/general/macro/macro_role_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>创建角色</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>
<body>
<div class="container" id="body_content">
<#-- 
<@M_navBreadForSuper category="role" subCategory="new"  entity=entity/>
 -->
<@M_navBreadForCategory category="role" subCategory="new" entity=entity/>
<@M_roleAddUI entity=entity />
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
<#-- 
<@M_navBottomForSuper category="role" subCategory="new"  entity=null/>
 -->
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/auth/role/roleManage.js?v=1.0.2'><\/script>");</script>
</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#-- 
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForMy.ftl">
 -->
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>查看邀请码</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>
<body>
<div class="container" id="body_content">
<@M_navBreadForCategory category="myIndex" subCategory="inviteQr" entity=entity/>
<#-- 
<@M_navBreadForMy category="info" subCategory="inviteQr"  entity=entity/>
 -->
<@M_qrCodeView entity=entity />
<#-- 
<@M_navBottomForMy category="info" subCategory="inviteQr"  entity=null/>
 -->
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
</div>
<div class="container" id="body_footer_js">	

</div>
</body>
</html>
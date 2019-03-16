<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForMy.ftl">
<#include "mobile/template/front/default/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>查看邀请码</title>
</head>
<body>
<div class="container" id="body_content">
<@M_navBreadForMy category="info" subCategory="inviteQr"  entity=entity/>
<@M_qrCodeView entity=entity />
<@M_navBottomForMy category="info" subCategory="inviteQr"  entity=null/>
</div>
<div class="container" id="body_footer_js">	

</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_category_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_category_list.ftl">
<#-- 
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
 -->
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">

<html>
<head>
<title>类别定义</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>

<div class="container " id="body_content">
 <#-- 
 <@M_navBreadForManage category="category" subCategory="list"  entity=entity/>
  -->
 <@M_navBreadForCategory category="category" subCategory="list" showView="detail"  entity=entity/>
  <div class="text-center"><h3>选择模块品种</h3></div>
  <p>${entity.respMsg}</p>
  <@M_CategoryExtendList contentList=contentList/>
  <#-- 
  <@M_navBottomForManage category="category" subCategory="list"  entity=entity/>
   -->
   <#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="detail"  entity=entity/>

</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/category/manage/category_create.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">

</script>
</div>
</body>
</html>


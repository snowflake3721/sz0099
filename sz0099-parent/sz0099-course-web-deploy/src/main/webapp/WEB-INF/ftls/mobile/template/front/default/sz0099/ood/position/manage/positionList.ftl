<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_position_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_position_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">

<html>
<head>
<title>位置定义</title>
</head>

<body>

<div class="container " id="body_content">
 <@M_navBreadForManage category="position" subCategory="list"  entity=entity/>
  <div class="text-center"><h3>选择模块品种</h3></div>
  <p>${entity.respMsg}</p>
  <#if entity.success==PositionExtend.SUCCESS_YES >
  <@M_positionExtendList contentList=contentList/>
  </#if>
  <@M_navBottomForManage category="position" subCategory="list"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/position/manage/position_create.js?v=1.0.1'><\/script>");</script>
<script type="text/javascript">

</script>
</div>
</body>
</html>


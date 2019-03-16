<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">

<html>
<head>
<title>录入课程</title>
</head>

<body>

<div class="container " id="body_content">
 <@M_navBreadForManage category="create" subCategory="list"  entity=entity/>
  <div class="text-center"><h3>选择草稿</h3></div>
  <p>${entity.respMsg}</p>
  <@M_coeProductDraftList draftList=draftList/>
  
  <@M_navBottomForManage category="create" subCategory="list"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">

</script>
</div>
</body>
</html>


<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">

<html>
<head>
<title>撰写文章</title>
</head>

<body>

<div class="container " id="body_content">
 <@M_navBreadForManage category="article" subCategory="draftList"  entity=entity/>
  <div class="text-center"><h3>选择草稿</h3></div>
  <p>${entity.respMsg}</p>
  <@M_coeArticleDraftList draftList=draftList/>
  
  <@M_navBottomForManage category="article" subCategory="draftList"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">

</script>
</div>
</body>
</html>


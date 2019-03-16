<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_list.ftl">

<html>
<head>
<title>技能服务</title>
</head>

<body>

<div class="container " id="body_content">
 <@M_navBreadForManage category="profession" subCategory="draftList"  entity=entity/>
  <div class="text-center"><h3>添加技能与服务</h3></div>
  <p>${entity.respMsg}</p>
  
  	<hr/>
  <@M_professionDraftList draftList=professionList/>
  
  <@M_navBottomForManage category="profession" subCategory="draftList"  entity=entity/>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script type="text/javascript">
	initPageForProfessionManageList();
</script>
</div>
</body>
</html>


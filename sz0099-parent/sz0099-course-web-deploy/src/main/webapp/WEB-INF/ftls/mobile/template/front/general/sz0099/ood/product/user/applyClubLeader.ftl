<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_user_clubLeader.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>申请领队</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>
<div class="container " id="body_content">
<@M_navBreadForCategory category="myIndex" subCategory="applyClubLeader" entity=entity/>
	<div id="applyClubLeader">
		<#if userRole==null>
			<h4>申请领队步骤：</h4>
			<ul class="list-group">
				  <li class="list-group-item">1.我的>信息>绑定手机号  <a href="/sz0099/ood/product/personal/myinfoUI">去绑定</a></li>
				  <li class="list-group-item">2.我的>信息>身份认证 <a href="/sz0099/ood/personal/myinfo/verifyUI">去认证</a></li>
				  <li class="list-group-item">3.发布3篇以上曾经带队出行的总结文章 <a href="/sz0099/ood/article/manage/queryDraftList">去发布</a></li>
				  <li class="list-group-item">4.上述材料补充完整，可申请【★星级领队】</li>
				  <li class="list-group-item text-center">
				  		<button class="btn btn-danger btn-sm" onclick="doApplyClubLeader()">申请领队</button>
				  </li>
			</ul>
			<#else>
			<h4>升级领队：</h4>
		</#if>
		<@Mg_clubLeaderInstruction entity=null/>
	</div><#-- end applyClubLeader -->
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<#-- 底部导航 -->
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
</div>
<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/user/applyClubLeader.js?v=1.0.0'><\/script>");</script>
</div>

</body>
</html>
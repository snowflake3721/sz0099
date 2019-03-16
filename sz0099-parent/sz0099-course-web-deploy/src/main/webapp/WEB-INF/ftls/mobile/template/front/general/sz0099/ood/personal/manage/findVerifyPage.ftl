<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#-- 
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForMy.ftl">
 -->
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_user_list.ftl">
<html>
<head>

<title>身份证认证审核列表</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>



<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForCategory category="user" subCategory="verify" entity=entity/>

<div class="row">
	 <div class="col-xs-6">
	      <input type="text" id="id_data_realname" name="realname" class="form-control" placeholder="姓名" value="${entity.realname}">
  	 </div>
	 <div class="col-xs-6">
	      <input type="text" id="id_data_mobile" name="coeUser.mobile" class="form-control" placeholder="手机号" value="${entity.coeUser.mobile}">
  	 </div>
</div>  	 
<div class="row">
	<div class="col-xs-12">
			<#assign currentIdstatus=CoeUserVerify.IDSTATUS.getContext(entity.idstatus,-1)/>
		 	<@M_dropdownBar id="id_verify_" propertyContext=CoeUserVerify.IDSTATUS current=currentIdstatus readonly=false showLabel=false/>
  	</div>
</div>  	 
<div class="row">
	 <div class="col-xs-6">
	      <input type="text" id="id_data_identity" name="identity" class="form-control" placeholder="身份证号" value="${entity.identity}">
  	 </div>
	 <div class="col-xs-6">
	      <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchForVerify(0)">搜索</button>
	      <input type="hidden" id="id_page_url" name="searchUrl" class="form-control" value="/sz0099/ood/personal/myinfo/findVerifyPage">
	      <input type="hidden" id="id_page_currentPage" name="page" class="form-control" value="${entityPage.number}">
	      <input type="hidden" id="id_page_totalPages" name="totalPages" class="form-control" value="${entityPage.totalPages}">
	      <input type="hidden" id="id_page_size" name="size" class="form-control" value="${entityPage.size}">
  	 </div>
</div>
<hr/>
<@Mg_coeUserVerifyPage entityPage=entityPage />
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
</@shiro.user>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/user/userVerify.js?v=1.0.0'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
	initPageForVerify('');
});
</script>

</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>

<title>上传收款码</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>



<div class="container " id="body_content">
<@shiro.user>
<#-- 
<@M_navBreadForMy category="info" subCategory="recieveImg" entity=entity/>
 -->
<@M_navBreadForCategory category="myIndex" subCategory="recieveImg" entity=entity/>
  <div class="text-center"><h4>上传收款码</h4>
  <p>当前认证状态： ${CoeUserVerify.IDSTATUS.getLabel(entity.userVerify.idstatus)}</p>
  <p><small>收款码用于接收用户打赏金<br/>身份认证通过的用户方可使用!</small></p>
  <#assign idstatus=entity.userVerify.idstatus/>
  <#assign readonly=true/>
  <#if idstatus!=CoeUserVerify.IDSTATUS_2_YES.valueInt && idstatus!=CoeUserVerify.IDSTATUS_1_PROCESS.valueInt>
  <#assign readonly=false/>
  </#if>
  </div>
	<hr/>
	<div class="row">
		 <div class="col-md-12">
		      <form class="">
				  <input id="id_userId" type="hidden" name="userId" value="${entity.userId}"/>
				  <input id="id_hidden_realname_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_REALNAME_MODIFY_SUCCESS}"/>
				  
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label" for="nickname">昵称 </label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${Base64Util.decode(entity.nickname)}" readonly="true" name="nickname" class="form-control" id="id_nickname" placeholder="昵称" aria-describedby="id_nickname_addon">
						</div>
				  </div>
				  
				 <#-- begin payRecieve -->
				  <div class="form-group form-group-sm">
				   		 <label class="col-sm-2 control-label text-danger" for="id_file_${entity.id}4">收款码★<small>（身份认证通过后可启用）</small></label>
				      	 <div class="input-group input-group-sm">
						 <div class="file-loading">
							<input id="id_file_${entity.id}4" data-devId="sz0099" data-project="ood" data-module="auth" data-variety="personal" data-position="identity" data-strategy="4" data-mainId="${entity.id}" data-subId="4" type="file" name="files" />
						 </div>
						 </div>
						 <div id="id_parag_photoPreview4" >
						 		<div id="id_messageTip_preview4" class="text-center"></div>
							 	<#assign payRecieveImg=entity.payRecieveImg/>
							 	<#if payRecieveImg! && entity.payRecieveImgE! >
							 	<div class='media' id='id_tpl_media_id${entity.payRecieveImgE.id}'>
					    		  <div class='media-left'>
					    			<a href='javascript:void(0)' onclick="showBigViewSingle('${entity.payRecieveImg}',300, 300,'${entity.id}')">
					    			  <img id='id_tpl_media_common_img${entity.payRecieveImgE.id}' class='media-object' width='250px' src='${entity.payRecieveImg}' alt='${entity.payRecieveImgE.id}'>
					    			</a> 
					    		  </div>
					    		  <div class='media-body'> 
					    			<p><button class='btn btn-xs btn-danger' type='button' onclick="deleteImageRef('${entity.payRecieveImgE.id}','id_messageTip_preview4')">移除</button></p>
					    		  </div>
					    		</div>
					    		</#if>
						 </div> 	
					</div> 
					<#-- end payRecieve -->
					
			   </form>
	  	 </div>
  	</div>
  	<hr/>
  	<#--我的邀请二维码 	-->
	<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
  	<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
	<#-- 
	<@M_navBottomForMy category="info" subCategory="recieveImg" entity=entity />
	 -->
</@shiro.user>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jIdentity.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/user/infocommon.js?v=1.0.4'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/personal/verify.js?v=1.0.0'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
	initFilesForUser();

});

	
</script>

</div>
</body>
</html>
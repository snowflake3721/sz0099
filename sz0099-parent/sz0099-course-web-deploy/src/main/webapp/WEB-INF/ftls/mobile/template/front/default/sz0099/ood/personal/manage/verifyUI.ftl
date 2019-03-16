<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForMy.ftl">
<html>
<head>

<title>身份证认证</title>
</head>

<body>



<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForMy category="info" subCategory="verify" entity=entity/>
  <div class="text-center"><h4>身份证认证审核</h4>
  <p>当前状态： ${CoeUserVerify.IDSTATUS.getLabel(entity.coeUser.idstatus)}</p>
  <#assign idstatus=entity.coeUser.idstatus/>
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
						  <input type="text" value="${entity.coeUser.nickname}" readonly="true" name="nickname" class="form-control" id="id_nickname" placeholder="昵称" aria-describedby="id_nickname_addon">
						</div>
				  </div>
				  
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label text-danger" for="realname">真实姓名★</label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.realname}" <#if readonly>readonly="true"</#if> class="form-control" id="id_realname" placeholder="输入真实姓名" aria-describedby="id_realname_addon">
						  <#if !readonly>
						  <span class="input-group-btn" id="id_realname_addon"><button class="btn btn-xs btn-danger" onclick="changeMyRealname('${entity.id}','id_realname','id_hidden_realname_successCode','id_messageTip_realname${entity.id}')" type="button">确认修改</button></span>
						  </#if>
						</div>
						<div id="id_messageTip_realname${entity.id}" class="text-center"></div>
				    
				  </div>
				  
				 
				  <input id="id_hidden_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_VERIFY_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label text-danger" for="identity">身份证认证★</label>
				      	
				      	
					      	<div class="input-group input-group-sm">
					      	<#assign identityNo=entity.identity/>
					      	<#if readonly>
					      	<#assign identityNo=entity.identity?replace("\\d{12}","******",'r')/>
					      	</#if>
							  <input type="text" value="${identityNo}" name="identity" <#if readonly>readonly="true"</#if>class="form-control" id="id_identity" placeholder="身份证号" aria-describedby="id_identity_addon" >
							  <#if !readonly>
							  <span class="input-group-btn" id="id_identity"><button class="btn btn-xs btn-success" onclick="applyIdVerify('${entity.id}')" type="button">申请认证</button></span>
							  </#if>
							</div>
							<div id="id_messageTip_identity${entity.id}" class="text-center"></div>
						<#if !readonly>	
							<label class="col-sm-2 control-label text-danger" for="id_file_${entity.id}1">身份证正面★</label>
					      	 <div class="input-group input-group-sm">
							 <div class="file-loading">
								<input id="id_file_${entity.id}1" data-devId="sz0099" data-project="ood" data-module="product" data-variety="personal" data-position="identity" data-strategy="3" data-mainId="${entity.id}"  data-subId="1" type="file" name="files" />
							 </div>
							 </div>
							 <div id="id_parag_photoPreview1" >
							 <div id="id_messageTip_preview1" class="text-center"></div>
							 		<#assign identityFace=entity.identityFace/>
								 	<#if identityFace! && entity.idImgFaceE! >
								 	<div class='media' id='id_tpl_media_id${entity.idImgFaceE.id}'>
						    		  <div class='media-left'>
						    			<a href='javascript:void(0)' onclick="showBigViewSingle('${entity.identityFace}',1024, 1024,'${entity.id}','id_messageTip_preview1')">
						    			  <img id='id_tpl_media_common_img${entity.idImgFaceE.id}' class='media-object' width='250px' src='${entity.identityFace}' alt='${entity.idImgFaceE.id}'>
						    			</a> 
						    		  </div>
						    		  <div class='media-body'> 
						    			<p><button class='btn btn-xs btn-danger' type='button' onclick="deleteImageRef('${entity.idImgFaceE.id}','id_messageTip_preview1')">移除</button></p>
						    		  </div>
						    		</div>
						    		</#if>
					 		 </div>
					 		 
					   		 <label class="col-sm-2 control-label text-danger" for="id_file_${entity.id}2">身份证反面★</label>
					      	 <div class="input-group input-group-sm">
							 <div class="file-loading">
								<input id="id_file_${entity.id}2" data-devId="sz0099" data-project="ood" data-module="product" data-variety="personal" data-position="identity" data-strategy="3" data-mainId="${entity.id}" data-subId="2" type="file" name="files" />
							 </div>
							 </div>
							 <div id="id_parag_photoPreview2" >
							 		<div id="id_messageTip_preview2" class="text-center"></div>
								 	<#assign identityBack=entity.identityBack/>
								 	<#if identityBack! && entity.idImgBackE! >
								 	<div class='media' id='id_tpl_media_id${entity.idImgBackE.id}'>
						    		  <div class='media-left'>
						    			<a href='javascript:void(0)' onclick="showBigViewSingle('${entity.identityBack}',1024, 1024,'${entity.id}')">
						    			  <img id='id_tpl_media_common_img${entity.idImgBackE.id}' class='media-object' width='250px' src='${entity.identityBack}' alt='${entity.idImgBackE.id}'>
						    			</a> 
						    		  </div>
						    		  <div class='media-body'> 
						    			<p><button class='btn btn-xs btn-danger' type='button' onclick="deleteImageRef('${entity.idImgBackE.id}','id_messageTip_preview2')">移除</button></p>
						    		  </div>
						    		</div>
						    		</#if>
							 </div>
						<#else>
							 <p>当前状态： ${CoeUserVerify.IDSTATUS.getLabel(entity.coeUser.idstatus)} <#if idstatus==CoeUserVerify.IDSTATUS_2_YES.valueInt>，<span class="text-success">审核已通过！</span><#elseif idstatus==CoeUserVerify.IDSTATUS_1_PROCESS.valueInt>请耐心等待审核！</#if></p>
						</#if>
				  </div>
					
			   </form>
	  	 </div>
  	</div>
  	<hr/>
	<@M_navBottomForMy category="info" subCategory="verify" entity=entity />
</@shiro.user>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jIdentity.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/user/infocommon.js?v=1.0.0'><\/script>");</script>
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
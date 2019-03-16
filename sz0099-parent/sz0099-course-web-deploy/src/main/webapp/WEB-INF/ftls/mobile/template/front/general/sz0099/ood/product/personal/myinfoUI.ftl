<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>

<title>我的基本资料</title>
<style type="text/css">
body {
  padding-top: 70px;
}
</style>
</head>

<body>



<div class="container " id="body_content">
<@shiro.user>
<@M_navBreadForCategory category="myIndex" subCategory="baseinfo" entity=entity/>
  <div class="text-center">
  <h4>个人资料修改</h4>
  <button type="button" id="id_btn_settingpwd" class="btn btn-primary btn-xs" onclick="popChangePwd('${entity.id}')">设置密码</button>
  </div>
	<hr/>
	<div id="id_messageTip_privacy${entity.id}" class="text-center"></div>
	<div class="row">
		 <div class="col-md-12">
		      <form>
		      		 
		      		<div class="form-group form-group-sm">
					    <label class="col-sm-2 control-label" for="id_file_${entity.id}3">
					   	<div id="id_parag_photoPreview3" >
						 	 <div id="id_messageTip_preview3" class="text-center"></div>
					   		<#assign headimg=entity.headImg/>
						  	<#if headimg!>
							  <img class="img-rounded" width="60px" src="${headimg}">
							 <span class="text-success" id="id_headImg_addon">可更换</span>
							 <#else>
							 还没上传头像
							 </#if>
						 </div>
					    </label>
				      	 <div class="input-group input-group-sm">
							 <div class="file-loading">
								<input id="id_file_${entity.id}3" data-devId="sz0099" data-project="ood" data-module="product" data-variety="personal" data-position="identity" data-strategy="2" data-mainId="${entity.id}" data-subId="3" type="file" name="files"/>
							 </div>
						 </div>
						 
						 	 
					</div>
		      
		      
				  <input id="id_hidden_email_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_EMAIL_MODIFY_SUCCESS}"/>
				  <input id="id_hidden_privacy_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_PRIVATE_SETTING_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label" for="email">Email</label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.email}" realonly="true" class="form-control" id="id_email" placeholder="输入email" aria-describedby="id_email_addon">
						  <span class="input-group-btn" id="id_email_addon"><button class="btn btn-xs btn-danger" onclick="changeMyEmail('${entity.id}','id_email','id_messageTip_email${entity.id}')" type="button">绑定/保存</button></span>
						</div>
						<div id="id_messageTip_email${entity.id}" class="text-center"></div>
						<div class="radio">
						   <label class="radio-inline" >
						    <input type="radio" name="emailShow" id="emailShow_yes" value="1" <#if entity.emailShow==CoeUser.PRIVACY_SETTING_1_SHOW.valueInt>checked</#if>>显示
						   </label>
						   <label class="radio-inline" >
						    <input type="radio" name="emailShow" id="emailShow_no" value="0" <#if entity.emailShow==CoeUser.PRIVACY_SETTING_0_HIDE.valueInt>checked</#if>>隐藏
						   </label>
						   <button class="btn btn-xs btn-info radio-inline" type="button" onclick="changeEmailShow('${entity.id}', 'emailShow', 'id_hidden_privacy_successCode',  'id_messageTip_privacy${entity.id}')">设置</button>
						</div>
				    
				  </div>
				  
				  <input id="id_hidden_mobile_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_MOBILE_MODIFY_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    	<label class="col-sm-2 control-label text-danger" for="mobile">手机号(请填写微信绑定的手机号)★</label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.mobile}" realonly="true" class="form-control" id="id_mobile" placeholder="输入mobile" aria-describedby="id_mobile_addon">
						  <span class="input-group-btn" id="id_mobile_addon"><button class="btn btn-xs btn-danger" onclick="changeMyMobile('${entity.id}','id_mobile','id_messageTip_mobile${entity.id}')" type="button">绑定/保存</button></span>
						</div>
						<div class="radio">
						   <label class="radio-inline" >
						    <input type="radio" name="mobileShow" id="mobileShow_yes" value="1" <#if entity.mobileShow==CoeUser.PRIVACY_SETTING_1_SHOW.valueInt>checked</#if>>显示
						   </label>
						   <label class="radio-inline" >
						    <input type="radio" name="mobileShow" id="mobileShow_no" value="0" <#if entity.mobileShow==CoeUser.PRIVACY_SETTING_0_HIDE.valueInt>checked</#if>>隐藏
						   </label>
						   <button class="btn btn-xs btn-info radio-inline" type="button" onclick="changeMobileShow('${entity.id}', 'mobileShow', 'id_hidden_privacy_successCode',  'id_messageTip_privacy${entity.id}')">设置</button>
						   <p><small>选择【显示】将在[技能]中 联系方式展示</small></p>
						  </div>
				  </div>
				  <div id="id_messageTip_mobile${entity.id}" class="text-center"></div>
				  
				  <input id="id_hidden_nickname_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_NICKNAME_MODIFY_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label text-danger" for="nickname">昵称 ★</label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.nickname}" name="nickname" class="form-control" id="id_nickname" placeholder="昵称" aria-describedby="id_nickname_addon">
						  <span class="input-group-btn" id="id_nickname_addon"><button class="btn btn-xs btn-success" onclick="changeMyNickname('${entity.id}','id_nickname','id_hidden_nickname_successCode','id_messageTip_nickname${entity.id}')" type="button">保存</button></span>
						</div>
				  </div>
				  <div id="id_messageTip_nickname${entity.id}" class="text-center"></div>
				  
				  <input id="id_hidden_postname_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_POSTNAME_MODIFY_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label text-danger" for="postname">绰号（联系名）★： 
				    </label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.postname}" name="postname" class="form-control" id="id_postname" placeholder="联系人名称" aria-describedby="id_postname_addon">
						  <span class="input-group-btn" id="id_postname_addon"><button class="btn btn-xs btn-success" onclick="changeMyPostname('${entity.id}','id_postname','id_hidden_postname_successCode','id_messageTip_postname${entity.id}')" type="button">保存</button></span>
						</div>
						  <div class="radio">
						   <label class="radio-inline">
						    <input type="radio" name="postnameShow" id="postnameShow_yes" value="1" <#if entity.postnameShow==CoeUser.PRIVACY_SETTING_1_SHOW.valueInt>checked</#if>>显示
						   </label>
						   <label class="radio-inline" >
						    <input type="radio" name="postnameShow" id="postnameShow_no" value="0" <#if entity.postnameShow==CoeUser.PRIVACY_SETTING_0_HIDE.valueInt>checked</#if>>隐藏
						   </label>
						   <button class="btn btn-xs btn-info radio-inline" type="button" onclick="changePostnameShow('${entity.id}', 'postnameShow', 'id_hidden_privacy_successCode',  'id_messageTip_privacy${entity.id}')">设置</button>
						   <p><small>选择【显示】将在[技能]中绰号（也即业务联系人）展示</small></p>
						  </div>
				  </div>
				  <div id="id_messageTip_postname${entity.id}" class="text-center"></div>
				  
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label" for="grade">等级 <a href="javascript:void(0)" onclick="showGradeInstruction()">等级说明</a></label>
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.grade}" name="grade" class="form-control" id="id_grade" readonly="true" placeholder="升级成会员" aria-describedby="id_grade_addon">
						  <span class="input-group-btn" id="id_grade_addon"><button class="btn btn-xs btn-warning" type="button" onclick="showGradeInstruction()">升级</button></span>
						</div>
				  </div>
				  <#-- 
				  <input id="id_hidden_successCode" type="hidden" value="${CourseProductRespCode.SZ0099_CODE_COURSE_USER_IDENTITY_VERIFY_SUCCESS}"/>
				  <div class="form-group form-group-sm">
				    <label class="col-sm-2 control-label" for="identity">身份证认证:${CoeUserVerify.IDSTATUS.getLabel(entity.idstatus)}</label>
				      	<#if entity.idstatus!=CoeUserVerify.IDSTATUS_2_YES.valueInt && entity.idstatus!=CoeUserVerify.IDSTATUS_1_PROCESS.valueInt>
				      	
				      	<div class="input-group input-group-sm">
						  <input type="text" value="${entity.identity}" name="identity" class="form-control" id="id_identity" placeholder="身份证号" aria-describedby="id_identity_addon" >
						  <span class="input-group-btn" id="id_identity"><button class="btn btn-xs btn-success" onclick="applyIdVerify('${entity.id}')" type="button">申请认证</button></span>
						</div>
						<div id="id_messageTip_identity${entity.id}" class="text-center"></div>
						
						<label class="col-sm-2 control-label" for="id_file_${entity.id}1">身份证正面</label>
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
				 		 
				   		 <label class="col-sm-2 control-label" for="id_file_${entity.id}2">身份证反面</label>
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
						</#if>
				  </div>
				  -->
				  <hr/>
					
			   </form>
	  	 </div>
  	</div>
  	<hr/>
  	<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
	<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=entity/>
</@shiro.user>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/common/tools/jit4j/jit4jIdentity.js?v=1.0.0'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/user/infocommon.js?v=1.0.4'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/general/sz0099/ood/personal/manage/myinfo.js?v=1.0.1'><\/script>");</script>

<script type="text/javascript">
$(document).ready(function(){

	autoCurrentOauthPageUrlLogin('${login_status}');
	
	initFilesForUser();

});

	
</script>
</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">


  <div class="text-center"><h3>登   录</h3></div>
  		<p class="text-center text-danger" id="messageTip">${entity.respMsg}</p>
  		<input type="hidden" id="id_input_opration_resp" name="respCode" value="${entity.respCode}"/> 
  		<input type="hidden" id="id_input_success_code" name="successCode" value="${JIT4J_CODE_LOGIN_SUCCESS}"/> 
		<form id="id_form_login" role="form" action="/login" method="post" enctype="application/x-www-form-urlencoded">
		
		
				 <input type="hidden" name="token" value="${token}"/> 
				 <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="id_login_mobile">手机号:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" id="id_login_mobile" name="mobile" class="form-control" placeholder="输入手机号" onchanged="validateMobile()"/>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="id_login_pwd">密码:
				    </label>
				    <div class="col-sm-10">
				    	<input type="password" id="id_login_pwd" name="pwd" class="form-control" placeholder="输入密码"/>
				    	<div class="text-right">
				    	<button type="button" id="btn_mobile_time" class="btn btn-info btn-xs"  onclick="forgetPasswd('${Jit4jRespCode.JIT4J_CODE_USER_SEND_MOBILE_CODE_SUCCESS}','${Jit4jRespCode.JIT4J_CODE_MOBILE_SUCCESS}');">忘记密码？</button>
		    			</div>
				    </div>
				   </div> 
				    
				   <div class="form-group row">
					    <label class="col-sm-2 col-form-label" for="id_login_jcaptchaCode">验证码：
					    </label>
					    <div class="col-sm-10">
					    	<input type="text" name="jcaptchaCode" id="id_login_jcaptchaCode" class="form-control" />&nbsp;&nbsp;
					    </div>
					    
				   </div>
				   <div class="form-group row">
				   		<div class="col-sm-2"></div>
				   		<div class="col-sm-10">
					 		<img id="validateCodeImg" src="/jcaptcha_code" />&nbsp;&nbsp;<button type="button" class="btn btn-info btn-xs" id="btn_jcaptcha_code_refresh" onclick="reloadValidateCode('btn_jcaptcha_code_refresh', '看不清？')">看不清？</button>
					    </div>
				    </div>
				    
				   <button class="btn btn-primary btn-block" type="button" onclick="doLogin()">登  录</button>
				   <hr/>
			       <ul class="list-inline">
					  <li><span class="text-info">已有帐号，点击登录 </span></li>
					  <li><a class="text-danger" href='/registerUI'>没有帐号?<strong>注册一个</strong></a></li>
				   </ul>
		</form>

</div>

<script type="text/javascript" src="/assets/theme/default/auth/basic/login.js?v=1.0.0" />

<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>用户登录</title>
<script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
</head>

<body>

<div class="container " id="body_content">


  <div class="text-center"><h3>登   录</h3></div>
  <p class="text-center">-- robot : ${Robot.ROBOT_PLAT.getName()}</p>
  <p class="text-center">-- robot : ${Robot.ROBOT_SZ0099_MEIMEI.getName()}</p>
  
  		<p class="text-center" v-bind:class="messageTipClass">{{messageTip}}</p>
  
		<form id="form_user_login" role="form" action="/login" method="post" enctype="application/x-www-form-urlencoded">
		
		
				  <input type="hidden" name="token" value="${token}"/> 
				  <input type="hidden" id="input_id_mobileInvalidCode" name="mobileInvalidCode" v-model="mobileInvalidCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_INVALID}"/> 
				  <input type="hidden" id="input_id_mobileEmptyCode" name="mobileEmptyCode" v-model="mobileEmptyCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_EMPTY}"/> 
				  <input type="hidden" id="input_id_pwdErrorCode" name="pwdErrorCode" v-model="pwdErrorCode" value="${Jit4jRespCode.JIT4J_CODE_SHIRO_USER_PASSWORD_ERROR}"/> 
				  <input type="hidden" id="input_id_userNotFound" name="userNotFound" v-model="userNotFound" value="${Jit4jRespCode.CODE_SHIRO_USER_NOT_FOUND}"/> 
				 <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="phone_1">手机号:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="mobile" v-model="userBase.mobile" class="form-control" id="phone_1" placeholder="输入手机号" v-on:keyup="validateMobile()"/>
				    &nbsp;&nbsp;&nbsp;&nbsp;
		    		<span v-bind:class="mobileCheckTipClass">{{mobileCheckTip}}</span>
				    </div>
				   </div>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="password_1">密码:
				    </label>
				    <div class="col-sm-10">
				    	<input type="password" name="pwd" v-model="userBase.pwd" class="form-control" id="password_1" placeholder="输入密码"/>
				    	&nbsp;&nbsp;&nbsp;&nbsp;
		    			<span v-bind:class="pwdCheckTipClass">{{pwdCheckTip}}</span>
				    	<div class="text-right">
				    	<button type="button" id="btn_mobile_time" class="btn btn-info btn-xs"  v-on:click="forgetPasswd('${Jit4jRespCode.JIT4J_CODE_USER_SEND_MOBILE_CODE_SUCCESS}','${Jit4jRespCode.JIT4J_CODE_MOBILE_SUCCESS}');">忘记密码？</button>
		    			</div>
				    </div>
				   </div> 
				    
				   <div class="form-group row">
					    <label class="col-sm-2 col-form-label" for="validateCode_1">验证码：
					    </label>
					    <div class="col-sm-10">
					    	<input type="text" name="jcaptchaCode" v-model="jcaptchaCode" class="form-control" id="validateCode_1"/>&nbsp;&nbsp;
					    	&nbsp;&nbsp;&nbsp;&nbsp;
		    				<span v-bind:class="jcaptchaCodeCheckTipClass">{{jcaptchaCodeCheckTip}}</span>
					    </div>
					    
				   </div>
				   <div class="form-group row">
				   		<div class="col-sm-2"></div>
				   		<div class="col-sm-10">
					 		<img id="validateCodeImg" src="/jcaptcha_code" />&nbsp;&nbsp;<button type="button" class="btn btn-info btn-xs" id="btn_jcaptcha_code_refresh" onclick="reloadValidateCode('btn_jcaptcha_code_refresh', '看不清？')">看不清？</button>
					    </div>
				    </div>
				    
				   <button class="btn btn-primary btn-block" type="button" v-on:click="login('${Jit4jRespCode.JIT4J_CODE_LOGIN_SUCCESS}')">登  录</button>
				   <hr/>
			       <ul class="list-inline">
					  <li><span class="text-info">已有帐号，点击登录 </span></li>
					  <li><a class="text-danger" href='/registerUI'>没有帐号?<strong>注册一个</strong></a></li>
				   </ul>
		</form>

</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">

$(document).ready(function(){
		var loginUI = new Vue({
		  el: '#app',
		  data: {
		  	userBase:{'username':'','pwd':'','mobile':'','validateCode':''},
		  	jcaptchaCode:'',
		  	mobileCodeTip:"发送验证码",
		  	refreshTip : '看不清？',
		  	mobileCheckTip:"",
		  	mobileCheckTipClass:"",
		  	mobileCodeChecked : false,
		  	mobileCodeCheckTipClass:"",
		  	mobileCodeCheckTip:"",
		  	pwdCheckTip :"",
		  	pwdCheckTipClass:"",
		  	jcaptchaCodeCheckTip:'',
		  	jcaptchaCodeCheckTipClass:'',
		  	mobileInvalidCode:'',
		  	mobileEmptyCode:'',
		  	messageTip : '',
		  	messageTipClass : '',
		  	pwdErrorCode:'',
		  	userNotFound :''
		  },
		  computed:{},
		  methods:{
			 forgetPasswd : function(){
			  	window.location.href="/findpwdUIa?findType=10&findStep=10";
			  },
		  	validateMobile : function(){
		  		var self=this;
		  		console.log(" -- validateMobile : >>> " + self.userBase.mobile);
		  		this.mobileCheckTip="";
		  		this.mobileCheckTipClass="";
		  		this.messageTip="";
		  		this.messageTipClass="";
		  		if(self.userBase.mobile==''){
		  			this.mobileCheckTip="手机号码不能为空";
		  			this.mobileCheckTipClass="text-danger";
		  			this.mobileCodeChecked=false;
		  			return;
		  		}
		  		
		  		if(self.userBase.mobile.length<11){
		  			this.mobileCheckTip="手机号码长度为11位，请检查";
		  			this.mobileCheckTipClass="text-danger";
		  			this.mobileCodeChecked=false;
		  			return;
		  		}
		  		//执行校验
		  		if(!isPoneAvailable(self.userBase.mobile)){
			    	 this.mobileCheckTip="手机号格式不正确";
		  			 this.mobileCheckTipClass="text-danger";
		  			 this.mobileCodeChecked=false;
		  			 return;
		  		}
		  		this.mobileCheckTip="";
		  		this.mobileCheckTipClass="";
		  		this.mobileCodeChecked=true;
		  		 
		  	},
		  	validateUserBase : function(){
		  		var self=this;
		  		self.pwdCheckTip='';
		  		self.pwdCheckTipClass='';
		  		self.jcaptchaCodeCheckTip='';
		  		self.jcaptchaCodeCheckTipClass='';
		  		self.mobileCodeCheckTipClass="";
		  		self.mobileCodeCheckTip="";
		  		
		  		//执行校验
		  		self.validateMobile();
		  		
		  		if(!self.mobileCodeChecked){
		  			return false;
		  		}
		  		 self.userBase.mobile=self.userBase.mobile.trim();
		  		
		  		 if(self.userBase.pwd==''){
		  		 	self.pwdCheckTip='!!密码不能为空';
		  		 	self.pwdCheckTipClass='text-danger';
		  		 	return false;
		  		 }
		  		 
		  		 if(self.userBase.pwd.length<6){
		  		 	self.pwdCheckTip='!!密码长度应大于6';
		  		 	self.pwdCheckTipClass='text-danger';
		  		 	return false;
		  		 }
		  		  
		  		 if(self.jcaptchaCode==''){
		  		 	self.jcaptchaCodeCheckTip='!!验证码不能为空';
		  		 	self.jcaptchaCodeCheckTipClass='text-danger';
		  		 	return false;
		  		 }
		  		 
		  		 if(self.jcaptchaCode.length!=4){
		  		 	self.jcaptchaCodeCheckTip='!!验证码长度是4位';
		  		 	self.jcaptchaCodeCheckTipClass='text-danger';
		  		 	return false;
		  		 }
		  		 return true;
		  	},
		  	sendMobileCode : function(successCode, mobileSuccessCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/sendMobileCode";
		  		 console.log("send begin...");
		  		 
		  		 self.validateMobile();
		  		 console.log("send begin >>>  mobileCodeChecked >>> " + this.mobileCodeChecked);
		  		 
		  		 if(this.mobileCodeChecked){
			    	 //异步注册
			    	 self.$http({
							  method: 'post',
							  url: url,
							  data:Qs.stringify({
								  	'mobile':self.userBase.mobile,
								    'pwd': self.userBase.pwd,
								    'confirmpwd': self.userBase.confirmpwd,
								    'mobileCode': self.userBase.mobileCode
								  })
							  
			 		 }).then(function(response){
		 					if(successCode==response.data.respCode){
								console.log("sendMobileCode: "+ successCode + ">>: " + response.data.respMsg);
								self.mobileCheckTip=response.data.respMsg;
								self.mobileCheckTipClass="text-success";
	        					get_code_time("btn_jcaptcha_code_refresh",10,"看不清？");
		 					}else{
		 						console.log(successCode + " >> error : " + self.userBase.mobile);
		 					}
			  		 });//end http
		  		  }
		  	},
		  	login : function(successCode){
		  		//调用异步登录
		  		 var self=this;
		  		 var url="/login/ansy";
		  		 console.log("login begin...");
		  		 
		  		 var validated = this.validateUserBase();
		  		 
		  		 if(validated){
			    	 //异步登陆
			    	 self.$http({
							  method: 'post',
							  url: url,
							  data:Qs.stringify({
								  	'mobile':self.userBase.mobile,
								    'pwd': self.userBase.pwd,
								    'validateCode': self.jcaptchaCode,
								    'jcaptchaCode': self.jcaptchaCode
								  })
							  
			 		 }).then(function(response){
			 		 		var respCode = response.data.respCode;
			 		 		var respMsg = response.data.respMsg;
		 					if(successCode==respCode){
								console.log(respCode + " >> : " + respMsg);
								self.mobileCodeCheckTip=respMsg;
								self.mobileCodeCheckTipClass="text-success";
								//登陆成功，跳转至首页
								console.log("access indexUI...");
								window.location.href="/index";
								
		 					}else{
		 						console.log(respCode + " >> : " + respMsg);
		 						var refresh=response.data.content;
		 						if(self.mobileEmptyCode==respCode || self.mobileInvalidCode==respCode){
		 							self.mobileCheckTip='';
		 							self.mobileCodeCheckTip=respMsg;
		 							self.mobileCodeCheckTipClass="text-danger";
		 						}else if("shiro.jcaptcha.error.tip"==respCode){
		 							self.jcaptchaCodeCheckTip=respMsg;
		  		 					self.jcaptchaCodeCheckTipClass='text-danger';
		  		 				}else if(self.pwdErrorCode==respCode){
		  		 					self.pwdCheckTip=respMsg;
		  							self.pwdCheckTipClass="text-danger";
		  		 				}else if(self.userNotFound==respCode){
		  		 					self.mobileCheckTip=response.data.respMsg;
									self.mobileCheckTipClass="text-danger";
		  		 				} else {
		  		 					self.messageTipClass="text-danger";
		  		 					self.messageTip=respMsg;
		  		 				}
		  		 				if("refresh"==refresh){
		  		 					reloadValidateCode('btn_jcaptcha_code_refresh', '看不清？');//刷新验证码
		  		 				}
		 					}
			  		 });//end http
		  		}
		  	}
		  },
		  created:function() {
		      console.group('------created创建完毕状态------');
		      this.pwdErrorCode=$("#input_id_pwdErrorCode").val();
		      this.mobileInvalidCode=$("#input_id_mobileInvalidCode").val();
		      this.mobileEmptyCode=$("#input_id_mobileEmptyCode").val();
		      this.userNotFound=$("#input_id_userNotFound").val();
		      
		  }
		});
		
		 autoCurrentOauthPageUrlLogin('${login_status}');
		  
	});

	
</script>
</div>
</body>
</html>
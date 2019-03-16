<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>用户注册</title>
<script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
</head>

<body>

<div class="container" id="body_content">
<div class="text-center"><h3>注   册</h3></div>
<p class="text-center" v-bind:class="messageTipClass">{{messageTip}}</p>
<form id="form_user_login" role="form" action="/login" method="post" enctype="application/x-www-form-urlencoded">
		
		<input type="hidden" name="token" value="${token}"/> 
		<input type="hidden" id="input_id_mobileInvalidCode" name="mobileInvalidCode" v-model="mobileInvalidCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_INVALID}"/> 
		<input type="hidden" id="input_id_mobileEmptyCode" name="mobileEmptyCode" v-model="mobileEmptyCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_EMPTY}"/> 
		<input type="hidden" id="input_id_mobileExistCode" name="mobileExistCode" v-model="mobileExistCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_EXIST}"/> 

		
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="phone_1">手机号:
		 	</label>
		 	<div class="col-sm-10">
		    <input type="text" name="mobile" v-model="userBase.mobile" class="form-control" id="phone_1" placeholder="输入手机号" v-on:keyup="validateMobileCode('${Jit4jRespCode.JIT4J_CODE_MOBILE_SUCCESS}')"/>
		    <button type="button" id="btn_mobile_time" class="btn btn-danger btn-xs text-right"  v-on:click="sendMobileCode('${Jit4jRespCode.JIT4J_CODE_USER_SEND_MOBILE_CODE_SUCCESS}','${Jit4jRespCode.JIT4J_CODE_MOBILE_SUCCESS}');">{{mobileCodeTip}}</button>
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    <span v-bind:class="mobileCheckTipClass">{{mobileCheckTip}}</span>
		    </div>
		   </div>
		   <div class="form-group row">
			    <label class="col-sm-2 col-form-label" for="mobileCode_1">手机验证码：
			    </label>
			    <div class="col-sm-10">
			    	<input type="text" name="mobileCode" v-model="userBase.mobileCode" class="form-control" id="mobileCode_1" placeholder="输入手机验证码"/>&nbsp;&nbsp;
			    	&nbsp;&nbsp;&nbsp;&nbsp;<span v-bind:class="mobileCodeCheckTipClass">{{mobileCodeCheckTip}}</span>
			    </div>
		   </div>
		  
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="password_1">密码:
		    </label>
		    <div class="col-sm-10">
		    	<input type="password" name="pwd" v-model="userBase.pwd" class="form-control" id="password_1" placeholder="输入密码"/>
		    	&nbsp;&nbsp;&nbsp;&nbsp;<span v-bind:class="pwdCheckTipClass">{{pwdCheckTip}}</span>
		    </div>
		   </div> 
		   
		   <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="password_2"> 确认密码:
		    </label>
		    <div class="col-sm-10">
		    	<input type="password" name="confirmpwd" v-model="userBase.confirmpwd" class="form-control" id="password_2" placeholder="再输入一次密码"/>
		    &nbsp;&nbsp;&nbsp;&nbsp;<span v-bind:class="confirmpwdCheckTipClass">{{confirmpwdCheckTip}}</span>
		    </div>
		   </div> 
		    
		   <button id="btn_id_register" class="btn btn-warning btn-block" type="button" v-on:click="register('${Jit4jRespCode.JIT4J_CODE_USER_REGISTER_SUCCESS}')">注册</button>
	       <br/>
	       <ul class="list-inline">
			  <li><a class="text-primary" href='/loginUI'>已有帐号?<strong>点此登录</strong></a></li>
		   </ul>
</form>	  

</div>
<div class="container" id="body_footer_js">	
<script type="text/javascript">
	$(document).ready(function(){
		var registerUI = new Vue({
		  el: '#app',
		  data: {
		  	userBase:{'username':'','pwd':'','confirmpwd':'','mobile':'','mobileCode':''},
		  	mobileCodeTip:"发送验证码",
		  	mobileCheckTip:"",
		  	mobileCheckTipClass:"",
		  	mobileCodeChecked : false,
		  	mobileCodeCheckTipClass:"",
		  	mobileCodeCheckTip:"",
		  	pwdCheckTip :"",
		  	pwdCheckTipClass:"",
		  	confirmpwdCheckTip:"",
		  	confirmpwdCheckTipClass:"",
		  	mobileInvalidCode:'',
		  	mobileEmptyCode:'',
		  	mobileExistCode:'',
		  	messageTip : '',
		  	messageTipClass : ''
		  	
		  },
		  computed:{},
		  methods:{
		  	validateMobileCode : function(successCode){
		  		var url="/register/validateMobile";
		  		var self=this;
		  		console.log(" -- validateMobileCode : " + successCode + ">>> " + self.userBase.mobile);
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
		  		if(self.userBase.mobile!='' && self.userBase.mobile.length==11){
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
			 		 		console.log(successCode);
		 					if(successCode==response.data.respCode){
								console.log(response.data.respCode + " >> : " + response.data.respMsg);
								self.mobileCheckTip=response.data.respMsg;
								self.mobileCheckTipClass="text-success";
								self.mobileCodeChecked=true;
		  						return true;
		 					}else{
		 						console.log(response.data.respCode + " >> : " + response.data.respMsg);
		 						self.mobileCheckTip=response.data.respMsg;
		 						self.mobileCheckTipClass="text-danger";
		 						self.mobileCodeChecked=false;
		  						return false;
		 					}
			  		 });//end http
		  		}
		  		 
		  	},
		  	validateUserBase : function(){
		  		var self=this;
		  		
		  		//清空校验
		  		self.mobileCheckTip='';
		  		self.mobileCheckTipClass='';
		  		self.pwdCheckTipClass='';
		  		self.pwdCheckTip='';
		  		self.confirmpwdCheckTip='';
		  		self.confirmpwdCheckTipClass='';
		  		self.mobileCodeCheckTip='';
		  		self.mobileCodeCheckTipClass='';
		  		
		  		//执行校验
		  		if(self.userBase.mobile==''){
		  		 	self.mobileCheckTip="手机号不能为空";
		  		 	self.mobileCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 self.userBase.mobile=self.userBase.mobile.trim();
		  		 if(self.userBase.pwd==''){
		  		 	self.pwdCheckTip="手机号密码不能为空";
		  		 	self.pwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 if(self.userBase.pwd.length<6){
		  		 	self.pwdCheckTip="手机号密码长度不能小于6位数";
		  		 	self.pwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 if(self.userBase.confirmpwd==''){
		  		 	self.confirmpwdCheckTip='确认密码不能为空';
		  		 	self.confirmpwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		  if(self.userBase.pwd!=self.userBase.confirmpwd){
		  		 	self.confirmpwdCheckTip='手机号密码与确认密码不一致';
		  		 	self.confirmpwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 if(self.userBase.mobileCode==''){
		  		 	self.mobileCodeCheckTipClass='text-danger';
		  		 	self.mobileCodeCheckTip='手机验证码不能为空';
		  		 	return false;
		  		 }
		  		 if(self.userBase.mobileCode.length!=6){
		  		 	self.mobileCodeCheckTipClass='text-danger';
		  		 	self.mobileCodeCheckTip='手机验证码为6位';
		  		 	return false;
		  		 }
		  		 return true;
		  	},
		  	sendMobileCode : function(successCode, mobileSuccessCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/sendMobileCode";
		  		 console.log("send begin...");
		  		 
		  		 self.validateMobileCode(mobileSuccessCode);
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
	        					get_code_time("btn_mobile_time",60,"发送手机验证码");
		 					}else{
		 						console.log(successCode + " >> error : " + self.userBase.mobile);
		 					}
			  		 });//end http
		  		  }
		  	},
		  	register : function(successCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/register";
		  		 console.log("register begin...");
		  		 
		  		 var validated = this.validateUserBase();
		  		 
		  		 if(validated){
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
			 		 		var respCode = response.data.respCode;
			 		 		var respMsg = response.data.respMsg;
		 					if(successCode==response.data.respCode){
								console.log(response.data.respCode + " >> : " + response.data.respMsg);
								self.messageTip=respMsg;
								self.messageTipClass="text-success";
								//注册成功，跳转至登录页面
								console.log("access loginUI...");
								$("#btn_id_register").attr("disabled",true);
								$("#bbtn_mobile_time").attr("disabled",true);
								var jumpToLoginUI="/loginUI";
								get_code_time_fobidden("btn_id_register",4,respMsg, jumpToLoginUI);
		 					}else{
		 						if(self.mobileInvalidCode==respCode || self.mobileEmptyCode==respCode || self.mobileExistCode==respCode){
		 							self.mobileCheckTip=respMsg;
		 							self.mobileCheckTipClass="text-danger";
		 						}else{
			 						console.log(response.data.respCode + " >> : " + response.data.respMsg);
			 						self.messageTip=response.data.respMsg;
			 						self.messageTipClass="text-danger";
		 						}
		 					}
			  		 });//end http
		  		}
		  	},
		  	jumpToLoginUI : function(){
		  		window.location.href="/loginUI";
		  	}
		  	
		  },
		  created:function() {
		      console.group('------created创建完毕状态------');
		      this.mobileInvalidCode=$("#input_id_mobileInvalidCode").val();
		      this.mobileEmptyCode=$("#input_id_mobileEmptyCode").val();
		      this.mobileExistCode=$("#input_id_mobileExistCode").val();
		  }
		});
		
		  
	});
	
</script>
</div>
</body>
</html>
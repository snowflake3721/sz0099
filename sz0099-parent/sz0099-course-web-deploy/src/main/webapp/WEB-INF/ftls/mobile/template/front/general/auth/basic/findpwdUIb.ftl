<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>找回密码</title>
<script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
</head>

<body>

<div class="container" id="body_content">


  <div class="text-center"><h3>找回密码[第二步]</h3></div>
  <p class="text-center" v-bind:class="messageTipClass">{{messageTip}}</p>
		<form id="form_user_login" role="form" action="/findpwd" method="post" enctype="application/x-www-form-urlencoded">
		
				  <input type="hidden" name="token" value="${token}"/> 
				  <input type="hidden" id="input_id_mobileInvalidCode" name="mobileInvalidCode" v-model="mobileInvalidCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_INVALID}"/> 
				  <input type="hidden" id="input_id_mobileEmptyCode" name="mobileEmptyCode" v-model="mobileEmptyCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_EMPTY}"/> 
				  <input type="hidden" id="input_id_mobileCodeExpired" name="mobileCodeExpired" v-model="mobileCodeExpired" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_CODE_EXPIRED}"/> 
				  <input type="hidden" id="input_id_mobileCodeError" name="mobileCodeError" v-model="mobileCodeError" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_CODE_ERROR}"/> 
				  <input type="hidden" id="input_id_mobileCodeEmpty" name="mobileCodeEmpty" v-model="mobileCodeEmpty" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_CODE_EMPTY}"/> 
				  <input type="hidden" id="input_id_extraRetryNum" name="extraRetryNum" v-model="extraRetryNum" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_STEP_2_EXTRA_RETRY_MAX}"/> 
				  
				  
				  <input type="hidden" name="mobile" v-model="userBase.mobile" value="${pwdFinder.mobile}"/> 
				  
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="phone_1">验证码将发送至以下手机号:
				 	</label>
				 	<div class="col-sm-10">
				 	<p class="text-center text-danger"><span id="span_id_mobile">${pwdFinder.mobile}</span></p>
				    </div>
				   </div>
				  <div class="form-group row">
				   <div class="col-sm-12">
				   		<button type="button" id="btn_mobile_send_code" class="btn btn-warning btn-block"  v-on:click="sendMobileCodeForFindpwd('${Jit4jRespCode.JIT4J_CODE_PWD_FIND_STEP_2_SEND_PASSED}');">{{mobileCodeTip}}</button>
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
				   
				   <button id="btn_check_init" class="btn btn-primary btn-block" type="button" v-on:click="findpwd2('${Jit4jRespCode.JIT4J_CODE_PWD_FIND_STEP_2_PASSED}')">下一步</button>
				   <br/>
				   <button class="btn btn-danger btn-xs text-right" type="button" v-on:click="backToForwark()">返回上一步</button>
				   
				   <hr/>
		</form>

</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">

$(document).ready(function(){
		var findpwdUI = new Vue({
		  el: '#app',
		  data: {
		  	userBase:{'username':'','pwd':'','mobile':'','mobileCode':''},
		  	mobileCodeTip:"获取手机验证码",
		  	mobileCodeChecked : false,
		  	mobileCodeCheckTipClass:"",
		  	mobileCodeCheckTip:"",
		  	mobileInvalidCode:'',
		  	mobileEmptyCode:'',
		  	messageTip:'',
		  	messageTipClass:'',
		  	mobileCodeEmpty:'',
		  	mobileCodeError:'',
		  	mobileCodeExpired:'',
		  	extraRetryNum:''
		  	
		  },
		  computed:{},
		  methods:{
		    backToForwark:function(){
		    	window.location.href="/findpwdUIa";
		    },
		  	validateMobile : function(){
		  		var self=this;
		  		console.log(" -- validateMobile : >>> " + self.userBase.mobile);
		  		self.messageTip="";
		 		self.messageTipClass="";
		 		self.mobileCodeCheckTipClass="";
		  		self.mobileCodeCheckTip="";
		 		
		  		if(self.userBase.mobile==''){
		  			this.messageTip="手机号码不能为空";
		  			this.messageTipClass="text-danger";
		  			this.mobileCodeChecked=false;
		  			return;
		  		}
		  		
		  		if(self.userBase.mobile.length<11){
		  			this.messageTip="手机号码长度为11位，请检查";
		  			this.messageTipClass="text-danger";
		  			this.mobileCodeChecked=false;
		  			return;
		  		}
		  		//执行校验
		  		if(!isPoneAvailable(self.userBase.mobile)){
			    	 this.messageTip="手机号格式不正确";
		  			 this.messageTipClass="text-danger";
		  			 this.mobileCodeChecked=false;
		  			 return;
		  		}
		  		
		  		this.mobileCodeChecked=true;
		  		 
		  	},
		  	validateUserBase : function(){
		  		var self=this;
		  		//执行手机号校验
		  		self.validateMobile();
		  		
		  		if(!self.mobileCodeChecked){
		  			return false;
		  		}
		  		 self.userBase.mobile=self.userBase.mobile.trim();
		  		 
		  		 //手机验证码校验
		  		 if(self.userBase.mobileCode==''){
		  		 	self.mobileCodeCheckTipClass='text-danger';
		  		 	self.mobileCodeCheckTip='手机验证码不能为空';
		  		 	return false;
		  		 }
		  		 
		  		 self.userBase.mobileCode=self.userBase.mobileCode.trim();
		  		 if(self.userBase.mobileCode.length != 6){
		  		 	self.mobileCodeCheckTipClass='text-danger';
		  		 	self.mobileCodeCheckTip='手机验证码必须为6位数字';
		  		 	return false;
		  		 }
		  		 
		  		 return true;
		  	},
		  	sendMobileCodeForFindpwd : function(successCode){
		  		//调用异步更新
		  		 var self=this;
		  		 self.messageTip="";
		 		 self.messageTipClass="";
		  		 var url="/sendMobileCodeForFindpwd";
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
								  	'findStep':13,
								    'findType':10
								  })
							  
			 		 }).then(function(response){
		 					if(successCode==response.data.respCode){
								console.log("sendMobileCodeForFindpwd: "+ successCode + ">>: " + response.data.respMsg);
								self.messageTip=response.data.respMsg;
								self.messageTipClass="text-success";
	        					get_code_time("btn_mobile_send_code",60,"发送手机验证码");
		 					}else{
		 						console.log(successCode + " >> error : " + self.userBase.mobile);
		 						self.messageTip=response.data.respMsg;
		 						self.messageTipClass="text-danger";
		 					}
			  		 });//end http
		  		  }
		  		  
		  	},
		  	findpwd2 : function(successCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/findpwd/ansy/2";
		  		 console.log("login begin...");
		  		 
		  		 var validated = this.validateUserBase();
		  		 
		  		 if(validated){
			    	 //异步登陆
			    	 self.$http({
							  method: 'post',
							  url: url,
							  data:Qs.stringify({
								  	'mobile':self.userBase.mobile,
								    'mobileCode': self.userBase.mobileCode
								  })
							  
			 		 }).then(function(response){
		 					if(successCode==response.data.respCode){
								console.log(response.data.respCode + " >> : " + response.data.respMsg);
								self.messageTip=response.data.respMsg;
								self.messageTipClass="text-success";
								console.log("set new password...");
								window.location.href="/findpwdUIc";
								
		 					}else{
		 						console.log(response.data.respCode + " >> : " + response.data.respMsg);
		 						
		 						var respCode=response.data.respCode;
		 						
		 						if(self.mobileCodeEmpty==respCode || self.mobileCodeError==respCode || self.mobileCodeExpired==respCode){
		 							self.messageTip='';
		 							self.mobileCodeCheckTip=response.data.respMsg;
		 							self.mobileCodeCheckTipClass="text-danger";
		 						}else if(self.extraRetryNum==respCode){
		 							self.messageTip=response.data.respMsg;
		 							window.location.href="/findpwdUIa";
		 						}else {
		 							self.messageTip=response.data.respMsg;
		 							self.messageTipClass="text-danger";
		 						}
		 						
		 					}
			  		 });//end http
		  		}
		  	}
		  },
		  created:function() {
		      console.group('------created创建完毕状态------');
		      this.userBase.mobile=$("#span_id_mobile").html();
		      this.mobileCodeEmpty=$("#input_id_mobileCodeEmpty").val();
		      this.mobileCodeError=$("#input_id_mobileCodeError").val();
		      this.mobileCodeExpired=$("#input_id_mobileCodeExpired").val();
		      this.extraRetryNum=$("#input_id_extraRetryNum").val();
		      console.log(" >>> " + this.userBase.mobile);
		      console.log("mobileCodeEmpty >>> " + this.mobileCodeEmpty);
		      console.log("mobileCodeError >>> " + this.mobileCodeError);
		      console.log("mobileCodeExpired >>> " + this.mobileCodeExpired);
		      
		  }
		});
		
		  
	});
	
	
	
</script>
</div>
</body>
</html>
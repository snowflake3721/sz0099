<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>找回密码</title>
<script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
</head>

<body>

<div class="container " id="body_content">


  <div class="text-center"><h3>找回密码[第一步]</h3></div>
  <p class="text-center" v-bind:class="messageTipClass">{{messageTip}}</p>
  
		<form id="form_user_login" role="form" action="/findpwdUIb" method="post" enctype="application/x-www-form-urlencoded">
		
				  <input type="hidden" name="token" value="${token}"/> 
				  <input type="hidden" name="mobileInvalidCode" id="input_id_mobileInvalidCode" v-model="mobileInvalidCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_INVALID}"/> 
				  <input type="hidden" name="mobileEmptyCode" id="input_id_mobileEmptyCode" v-model="mobileEmptyCode" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_EMPTY}"/> 
				  <input type="hidden" name="findTypeInvalidCode" id="input_id_findTypeInvalidCode" v-model="findTypeInvalidCode" value="${Jit4jRespCode.JIT4J_CODE_PWD_FIND_TYPE_INVALID}"/> 
				  <input type="hidden" name="mobile" id="input_id_mobile" value="${userBase.mobile}"/> 
				  <input type="hidden" name="respMsg" id="input_id_respMsg" value="${userBase.respMsg}"/> 
				  <input type="hidden" name="respCode" id="input_id_respCode" value="${userBase.respCode}"/> 
				  <input type="hidden" name="mobileNotExist" id="input_id_mobileNotExist" value="${Jit4jRespCode.JIT4J_CODE_MOBILE_NOT_EXIST}"/> 
				 <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="phone_1">填写您注册时的手机号:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="mobile" v-model="userBase.mobile" class="form-control" id="phone_1" placeholder="输入手机号" v-on:keyup="validateMobile()"/>
				    &nbsp;&nbsp;&nbsp;&nbsp;
				    <span v-bind:class="mobileCheckTipClass">{{mobileCheckTip}}</span>
				    </div>
				   </div>
				   
				   <div class="form-group row">
					    <label class="col-sm-2 col-form-label" for="validateCode_1">输入以下图形验证码：
					    </label>
					    <div class="col-sm-10">
					    	<input type="text" name="jcaptchaCode" v-model="jcaptchaCode" class="form-control" id="validateCode_1"/>&nbsp;&nbsp;
					    	<img id="validateCodeImg" src="/jcaptcha_code" />&nbsp;&nbsp;<button class="btn btn-info btn-xs" id="btn_jcaptcha_code_refresh" onclick="reloadValidateCode('btn_jcaptcha_code_refresh','看不清？')">看不清？</button>
					    	<div><span v-bind:class="jcaptchaCodeCheckTipClass">{{jcaptchaCodeCheckTip}}</span></div>
					    </div>
				   </div>
				   <button id="btn_check_init" class="btn btn-warning btn-block" type="button" v-on:click="findpwd('${Jit4jRespCode.JIT4J_CODE_PWD_FIND_STEP_1_PASSED}')">初步校验</button>
				   
				   <hr/>
			       <ul class="list-inline">
					  <li><span class="text-info">已有帐号，点击登录 </span></li>
					  <li><a class="text-danger" href='/registerUI'>没有帐号?<strong>注册一个</strong></a></li>
				   </ul>
		</form>

</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">
<!--

//-->

$(document).ready(function(){
		var findpwdUI = new Vue({
		  el: '#app',
		  data: {
		  	userBase:{'username':'','pwd':'','mobile':'','validateCode':''},
		  	jcaptchaCode:'',
		  	refreshTip : '看不清？',
		  	mobileCheckTip:"",
		  	mobileCheckTipClass:"",
		  	mobileCodeChecked : false,
		  	pwdCheckTip :"",
		  	pwdCheckTipClass:"",
		  	jcaptchaCodeCheckTip:'',
		  	jcaptchaCodeCheckTipClass:'',
		  	mobileInvalidCode:'',
		  	mobileEmptyCode:'',
		  	findTypeInvalidCode:'',
		  	messageTip:'',
		  	messageTipClass:'',
		  	mobileNotExist:''
		  	
		  },
		  computed:{
		  	
		  },
		  methods:{
		  	validateMobile : function(){
		  		var self=this;
		  		console.log(" -- validateMobile : >>> " + self.userBase.mobile);
		  		this.messageTip="";
		  		this.messageTipClass="";
		  		this.mobileCheckTipClass="";
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
		  		self.mobileCheckTipClass="";
		  		self.mobileCheckTip="";
		  		self.messageTip='';
		  		self.messageTipClass='';
		  		
		  		//执行校验
		  		self.validateMobile();
		  		
		  		if(!self.mobileCodeChecked){
		  			return false;
		  		}
		  		 self.userBase.mobile=self.userBase.mobile.trim();
		  		
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
		  	findpwd : function(successCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/findpwd/ansy/1";
		  		 console.log("login begin...");
		  		 
		  		 var validated = this.validateUserBase();
		  		 
		  		 if(validated){
			    	 //异步校验
			    	 self.$http({
							  method: 'post',
							  url: url,
							  data:Qs.stringify({
								  	'mobile':self.userBase.mobile,
								    'pwd': self.userBase.pwd,
								    'validateCode': self.jcaptchaCode,
								    'jcaptchaCode': self.jcaptchaCode,
								    'findStep':10,
								    'findType':10
								  })
							  
			 		 }).then(function(response){
		 					if(successCode==response.data.respCode){
								console.log(response.data.respCode + " >> : " + response.data.respMsg);
								self.messageTip=response.data.respMsg;
								self.messageTipClass="text-success";
								console.log("access findpwdUIb...");
								window.location.href="/findpwdUIb";
								
		 					}else{
		 					
		 						var respCode=response.data.respCode;
		 						var respMsg=response.data.respMsg;
		 						var refresh=response.data.content;
		 						console.log(respCode + " >> : " + respMsg);
		 						if(self.findTypeInvalidCode==respCode){
		 							self.messageTip=respMsg;
		 							self.messageTipClass="text-danger";
		 						}
		 						
		 						if(self.mobileEmptyCode==respCode || self.mobileInvalidCode==respCode || self.mobileNotExist==respCode){
		 							self.mobileCheckTip=respMsg;
		 							self.mobileCheckTipClass="text-danger";
		 						}
		 						
		 						if("shiro.jcaptcha.error.tip"==response.data.respCode){
		 							self.jcaptchaCodeCheckTip=response.data.respMsg;
		  		 					self.jcaptchaCodeCheckTipClass='text-danger';
		  		 				}
		  		 				
		  		 				if("refresh"==refresh){
		  		 					reloadValidateCode('btn_jcaptcha_code_refresh','看不清？');//刷新验证码
		  		 				}
		  		 				
		  		 				self.messageTip=respMsg;
		  		 				self.messageTipClass='text-danger';
		 						
		 					}
			  		 });//end http
		  		}
		  	}
		  },
		  created:function() {
		      console.group('------created创建完毕状态------');
		      var o=$("#btn_mobile_send_code");
		      o.attr("disabled", true);
		      this.userBase.mobile=$("#input_id_mobile").val();
		      this.mobileInvalidCode=$("#input_id_mobileInvalidCode").val();
		      this.mobileEmptyCode=$("#input_id_mobileEmptyCode").val();
		      this.findTypeInvalidCode=$("#input_id_findTypeInvalidCode").val();
		      this.mobile=$("#input_id_mobile").val();
		      this.mobileNotExist=$("#input_id_mobileNotExist").val();
		      this.messageTip=$("#input_id_respMsg").val();
		      if(this.messageTip != ''){
		      	this.messageTipClass="text-danger";
		      }
		      
		  }
		});
		  
});

</script>
</div>
</body>
</html>
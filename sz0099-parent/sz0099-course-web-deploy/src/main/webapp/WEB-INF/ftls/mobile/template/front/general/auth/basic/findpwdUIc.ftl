<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>找回密码>设置密码</title>
<script src="/assets/common/tools/vue/2.5.6/vue.js?v=1.1"></script>
</head>

<body>

<div class="container" id="body_content">
<div class="text-center"><h3>重设密码</h3></div>
<form id="form_user_login" role="form" action="/findpwd/ansy/3" method="post" enctype="application/x-www-form-urlencoded">
		
		<input type="hidden" name="token" value="${token}"/> 
		
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="phone_1">手机号:
		 	</label>
		 	<div class="col-sm-10">
		    <p class="text-center text-danger"><span id="span_id_mobile">${StringUtils.encyptPhone(pwdFinder.mobile)}</span></p>
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
		   
		   <p v-bind:class="messageTipClass">{{messageTip}}</p>
		    
		   <button class="btn btn-warning btn-block" type="button" v-on:click="findpwd3('${Jit4jRespCode.JIT4J_CODE_PWD_FIND_STEP_3_PASSED}')">确  定</button>
	       <ul class="list-inline">
			  <li><a class="text-primary" href='/loginUI'>已有帐号?<strong>点此登录</strong></a></li>
		   </ul>
</form>	  

</div>
<div class="container" id="body_footer_js">	
<script type="text/javascript">
	$(document).ready(function(){
		var findpwdUI3 = new Vue({
		  el: '#app',
		  data: {
		  	userBase:{'username':'','pwd':'','confirmpwd':'','mobile':'','mobileCode':''},
		  	mobileCheckTip:"",
		  	mobileCheckTipClass:"",
		  	pwdCheckTip :"",
		  	pwdCheckTipClass:"",
		  	confirmpwdCheckTip:"",
		  	confirmpwdCheckTipClass:"",
		  	messageTip : "",
		  	messageTipClass : ""
		  	
		  },
		  computed:{},
		  methods:{
		  	validateMobileCode : function(successCode){
		  		var url="/register/validateMobile";
		  		var self=this;
		  		console.log(" -- validateMobileCode : " + successCode + ">>> " + self.userBase.mobile);
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
		  		self.pwdCheckTipClass='';
		  		self.pwdCheckTip='';
		  		self.confirmpwdCheckTip='';
		  		self.confirmpwdCheckTipClass='';
		  		
		  		//执行校验
		  		 if(self.userBase.pwd==''){
		  		 	self.pwdCheckTip="密码不能为空";
		  		 	self.pwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 if(self.userBase.pwd.length<6){
		  		 	self.pwdCheckTip="密码长度不能小于6位数";
		  		 	self.pwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 if(self.userBase.confirmpwd==''){
		  		 	self.confirmpwdCheckTip='确认密码不能为空';
		  		 	self.confirmpwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		  if(self.userBase.pwd!=self.userBase.confirmpwd){
		  		 	self.confirmpwdCheckTip='密码与确认密码不一致';
		  		 	self.confirmpwdCheckTipClass="text-danger";
		  		 	return false;
		  		 }
		  		 return true;
		  	},
		  	
		  	findpwd3 : function(successCode){
		  		//调用异步更新
		  		 var self=this;
		  		 var url="/findpwd/ansy/3";
		  		 console.log("register begin...");
		  		 
		  		 var validated = this.validateUserBase();
		  		 
		  		 if(validated){
			    	 //异步设置密码
			    	 self.$http({
							  method: 'post',
							  url: url,
							  data:Qs.stringify({
								  	'mobile':self.userBase.mobile,
								    'pwd': self.userBase.pwd,
								    'confirmpwd': self.userBase.confirmpwd
								  })
							  
			 		 }).then(function(response){
		 					if(successCode==response.data.respCode){
								console.log(response.data.respCode + " >> : " + response.data.respMsg);
								self.mobileCodeCheckTip=response.data.respMsg;
								self.mobileCodeCheckTipClass="text-success";
								//密码设置成功，定时跳转至登录页
								console.log("access loginUI...");
								forwardPage("/loginUI");
								
		 					}else{
		 						console.log(response.data.respCode + " >> : " + response.data.respMsg);
		 						self.mobileCheckTip='';
		 						self.mobileCodeCheckTip=response.data.respMsg;
		 						self.mobileCodeCheckTipClass="text-danger";
		 					}
			  		 });//end http
		  		}
		  	}
		  },
		  created:function() {
		      console.group('------created创建完毕状态------');
		  }
		});
		
		var ftime=4;
		forwardPage=function(url){
			if (ftime == 0) {
				ftime=4;
				window.location.href=url;
			}else{
				ftime--;
				findpwdUI3.messageTip=" " + ftime + "s 后自动跳转到登录页面";
				findpwdUI3.messageTipClass="text-success";
				setTimeout(function() {
					forwardPage(url)
				}, 1000)
			}
		}
		  
	});
	
</script>
</div>
</body>
</html>
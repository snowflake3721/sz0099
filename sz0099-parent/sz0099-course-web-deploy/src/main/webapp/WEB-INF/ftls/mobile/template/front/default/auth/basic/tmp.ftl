<form id="form_user_login" role="form" action="/login" method="post" enctype="application/x-www-form-urlencoded">
		
		<input type="hidden" name="token" value="${token}"/> 
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="phone_1">手机号:
		 	</label>
		 	<div class="col-sm-10">
		    <input type="text" name="mobile" v-model="userBase.modbile" class="form-control" id="phone_1" placeholder="输入手机号"/>
		    <button type="button" class="btn btn-danger btn-xs text-right" v-on:click="javascript:sendMobileCode('${Jit4jRespCode.JIT4J_CODE_USER_SEND_MOBILE_CODE_SUCCESS}');">发送验证码</button>
		    </div>
		   </div>
		   <div class="form-group row">
			    <label class="col-sm-2 col-form-label" for="mobileCode_1">手机验证码：
			    </label>
			    <div class="col-sm-10">
			    	<input type="text" name="mobileCode" v-model="userBase.mobileCode" class="form-control" id="mobileCode_1"/>&nbsp;&nbsp;
			    </div>
		   </div>
		  
		  <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="password_1">密码:
		    </label>
		    <div class="col-sm-10">
		    	<input type="password" name="pwd" v-model="userBase.pwd" class="form-control" id="password_1" placeholder="输入密码"/>
		    </div>
		   </div> 
		   
		   <div class="form-group row">
		    <label class="col-sm-2 col-form-label" for="password_2"> 确认密码:
		    </label>
		    <div class="col-sm-10">
		    	<input type="password" name="confirmpwd" v-model="userBase.confirmpwd" class="form-control" id="password_2" placeholder="再输入一次密码"/>
		    </div>
		   </div> 
		    
		   <button class="btn btn-warning btn-block" type="submit">注册</button>
	       <ul class="list-inline">
			  <li><a class="text-danger" href='/registerUserUI'>已有帐号?<strong>点此登录</strong></a></li>
		   </ul>
</form>

	var wait = 60;
	get_code_time = function (o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value = "免费获取验证码";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.value = "(" + wait + ")秒后重新获取";
			wait--;
			setTimeout(function() {
				get_code_time(o)
			}, 1000)
		}
	}
	
	function sendMobileCode() {
		var o = this;
		$.ajax({
			url:"/sendMobileCode?jsoncallback=?",
			type:"post",
			data: {mobile:mobile},
	        dataType: "json",
	        success: function (data) {
	        	if(data.status == 1 && data.code == 200){
	        		alert("验证码已发送至您的手机");
	        		get_code_time(o);
	        	} else {
	        		if(data.msg != ""){
	        			alert(data.msg);
	        		} else {
	        			alert("短信验证码发送失败");
	        		}
	        	}
	        },
	        error: function (data) {
	        	if(data.status == 0) {
	        		alert(data.msg);
	        	} else {
	        		alert("短信验证码发送失败");
	        	}
	        }
		});
	}
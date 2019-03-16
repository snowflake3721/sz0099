
function changeMyEmail(id,emailId, messageTipId){
	var emailInput=$("#"+emailId);
	var email = emailInput.val();
	console.log("------2---email---"+email);
	var messageTipDiv=$("#"+messageTipId);
	var emailChecked = validateEmail(email, messageTipDiv);
	console.log("------2---emailChecked---"+emailChecked);
	if(!emailChecked){
		return false;
	}
	
	console.log("------3---emailChecked---"+emailChecked);
	if(emailChecked){
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/personal/myinfo/doModifyEmail',
    	    data: 
    	    	Qs.stringify({
    	    	'id' : id,
    	    	'email' : email
    	    	})
    	})
    	.then(function (response) {
    	    
    	    var id_hidden_successCode = $("#id_hidden_email_successCode");
    	    var successCode = id_hidden_successCode.val();
    	    BootstrapDialog.show({
    			title: "Email修改结果",
    	    	message: $('<div></div>').html(response.data),
    	    	size: BootstrapDialog.SIZE_SMALL,
    	    	buttons: [{
                    label: '确定',
                    cssClass : "btn-primary",
                    action: function(dialog){
                    	//dialog.close();
                    	var id_hidden_common_respCode=$("#id_hidden_common_respCode");
                    	var resultCode = id_hidden_common_respCode.val();
                    	console.log(successCode + " vs " + resultCode);
                    	var commonMsg = $("#id_common_respMsg").html();
                    	if(successCode == resultCode){
                    		if(messageTipDiv.length>0){
                    			messageTipDiv.html(commonMsg);
                    		}
                    		BootstrapDialog.closeAll();
                    	}
                    },
                }, {
                    label: '关闭',
                    cssClass : "btn-danger",
                    action: function(dialog){
                    	dialog.close();
                    }
                }]
    		});
    	    
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
}

function validateMobile(mobileId, messageTipId){
	var messageTipDiv=$("#"+messageTipId);
	var mobileIdInput=$("#"+mobileId);
	var mobile = mobileIdInput.val();
	var tip="";
	if(''==mobile){
		tip="手机号不能为空";
		messageTipDiv.html(tip);
		popTip(tip);
		return false;
	}
	
	if(!isPoneAvailable(mobile)){
		 tip="手机号格式不正确";
		messageTipDiv.html(tip);
		popTip(tip);
			return false;
	}
	return true;
}

function changeMyMobile(id,mobileId, messageTipId){
	var mobileIdInput=$("#"+mobileId);
	var mobile = mobileIdInput.val();
	console.log("------2---mobile---"+mobile);
	var messageTipDiv=$("#"+messageTipId);
	var mobileChecked = validateMobile(mobileId, messageTipId);
	
	console.log("------2---mobileChecked---"+mobileChecked);
	if(!mobileChecked){
		return false;
	}
	if(mobileChecked){
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/personal/myinfo/doModifyMobile',
    	    data: 
    	    	Qs.stringify({
    	    	'id' : id,
    	    	'mobile' : mobile
    	    	})
    	})
    	.then(function (response) {
    	    
    	    var id_hidden_successCode = $("#id_hidden_mobile_successCode");
    	    var successCode = id_hidden_successCode.val();
    	    BootstrapDialog.show({
    			title: "手机号修改结果",
    	    	message: $('<div></div>').html(response.data),
    	    	size: BootstrapDialog.SIZE_SMALL,
    	    	buttons: [{
                    label: '确定',
                    cssClass : "btn-primary",
                    action: function(dialog){
                    	//dialog.close();
                    	var id_hidden_common_respCode=$("#id_hidden_common_respCode");
                    	var resultCode = id_hidden_common_respCode.val();
                    	console.log(successCode + " vs " + resultCode);
                    	var commonMsg = $("#id_common_respMsg").html();
                    	if(successCode == resultCode){
                    		if(messageTipDiv.length>0){
                    			messageTipDiv.html(commonMsg);
                    		}
                    		BootstrapDialog.closeAll();
                    	}
                    },
                }, {
                    label: '关闭',
                    cssClass : "btn-danger",
                    action: function(dialog){
                    	dialog.close();
                    }
                }]
    		});
    	    
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
}

function changeMyNickname(id, nicknameId, hiddenCodeId, messageTipId){
	var messageTip="昵称不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyNickname';
	var filednameChecked = validateEmpty(nicknameId, messageTip, messageTipId);
	if(!filednameChecked){
		return false;
	}
	var filednameLengthChecked = validateLength(nicknameId, "昵称太长，都超10了", 10, messageTipId);
	if(!filednameLengthChecked){
		return false;
	}
	
	var filednameIdInput=$("#"+nicknameId);
	var filednameV = filednameIdInput.val();
	var uploadData={
			'id' : id,
			'nickname' : filednameV
	}
	
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}
function changeMyPostname(id, postnameId, hiddenCodeId,  messageTipId){
	var messageTip="联系人名称不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyPostname';
	var filednameChecked = validateEmpty(postnameId, messageTip, messageTipId);
	if(!filednameChecked){
		return false;
	}
	var filednameLengthChecked = validateLength(postnameId, "昵称太长，都超10了", 10, messageTipId);
	if(!filednameLengthChecked){
		return false;
	}
	var filednameIdInput=$("#"+postnameId);
	var filednameV = filednameIdInput.val();
	var uploadData={
			'id' : id,
			'postname' : filednameV
	}
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}

function changePostnameShow(id, inputName, hiddenCodeId,  messageTipId){
	$("#"+messageTipId).html('');
	var messageTip="参数不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyPostnameShow';
	var filednameV = $("input[name='"+inputName+"']:checked").val();
	var uploadData={
			'id' : id,
			'postnameShow' : filednameV
	}
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}

function changeMobileShow(id, inputName, hiddenCodeId,  messageTipId){
	$("#"+messageTipId).html('');
	var messageTip="参数不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyMobileShow';
	var filednameV = $("input[name='"+inputName+"']:checked").val();
	var uploadData={
			'id' : id,
			'mobileShow' : filednameV
	}
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}

function changeEmailShow(id, inputName, hiddenCodeId,  messageTipId){
	$("#"+messageTipId).html('');
	
	var messageTip="参数不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyEmailShow';
	var filednameV = $("input[name='"+inputName+"']:checked").val();
	var uploadData={
			'id' : id,
			'emailShow' : filednameV
	}
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}
function changeQqShow(id, inputName, hiddenCodeId,  messageTipId){
	$("#"+messageTipId).html('');
	var messageTip="参数不能为空";
	var url= '/sz0099/ood/product/personal/myinfo/doModifyQqShow';
	var filednameV = $("input[name='"+inputName+"']:checked").val();
	var uploadData={
			'id' : id,
			'qqShow' : filednameV
	}
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}


function popChangePwd(id){
	axios({
	    method: 'post',
	    url: '/auth/user/manage/changePwdUI',
	    data: 
	    	Qs.stringify({
	    	'id' : id
	    	})
	})
	.then(function (response) {
		var msg=response.data;
	    BootstrapDialog.show({
			title: "设置密码",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
		         label: '确定修改',
		         cssClass : "btn-primary",
		         action: function(dialog){
		        	changePwd(id, dialog);
		         	//dialog.close();
		         },
		     }, {
		         label: '关闭',
		         cssClass : "btn-danger",
		         action: function(dialog){
		         	dialog.close();
		         }
		     }]
		});
	    
	})
	.catch(function (error) {
	    console.log(error);
	});
	
}


function changePwd(id, dialog){
	var showTipDivId="id_tip_show";
	var showTipDiv=$("#"+showTipDivId);
	showTipDiv.html('');
	var checked=validatePwd(id);
	var oldpwd="";
	var oldpwdInput=$("#oldpwd");
	if(oldpwdInput.length>0){
		oldpwd=oldpwdInput.val();
	}
	var pwdInput=$("#pwd");
	var pwd=pwdInput.val();
	var confirmpwdInput=$("#confirmpwd");
	var confirmpwd=confirmpwdInput.val();
	var uploadData={'oldpwd':oldpwd, 'pwd':pwd,'confirmpwd':confirmpwd};
	if(checked){
		axios({
		    method: 'post',
		    url: '/auth/user/manage/doChangePwd',
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
			var msg=response.data;
			
			
			showTipDiv.html(msg);
			var id_success=showTipDiv.find("#id_hidden_common_success");
	 	    var successValue = id_success.val();
	 	    if(successValue==1){
	 	    	showTipDiv.removeClass("text-danger");
	 	    	showTipDiv.addClass("text-success");
	 	    	
	 	    	$("#id_pwd_no").addClass("hidden");
	 	    	$("#id_pwd_yes").removeClass("hidden");
	 	    }else{
	 	    	showTipDiv.removeClass("text-success");
	 	    	showTipDiv.addClass("text-danger");
	 	    }
			
		})
		.catch(function (error) {
		    console.log(error);
		});
	}
}
function validatePwd(id){
	var oldpwdInput=$("#oldpwd");
	var oldpwd="";
	var showTipDivId="id_tip_show";
	if(oldpwdInput.length>0){
		oldpwd=oldpwdInput.val();
		if(!$("#id_pwd_yes").hasClass('hidden')){
			var oldpwdChecked=validateContentEmpty(oldpwd, "原密码不能为空！", showTipDivId);
			if(!oldpwdChecked){
				return false;
			}
		}
	}
	var tip ="";
	var pwdInput=$("#pwd");
	var pwd=pwdInput.val();
	var pwdChecked=validateContentEmpty(pwd, "新密码不能为空！", showTipDivId);
	if(!pwdChecked){
		return false;
	}
	 if(pwd.length<6){
	 	tip ="密码长度只能 6-18位数 之间";
	 	popTip(tip);
	 	return false;
	 }
	 if(pwd.length>18){
		 	tip ="密码长度不能大于18位数";
		 	popTip(tip);
		 	return false;
	 }
	 var letterchecked = checkPassWord(pwd);
	 if(!letterchecked){
		 tip ="密码必须是数字、字母至少2种混合[A-Za-z0-9]";
		 popTip(tip);
		 return false;
	 }
	 
	var confirmpwdInput=$("#confirmpwd");
	var confirmpwd=confirmpwdInput.val();
	var confirmpwdChecked=validateContentEmpty(confirmpwd, "'确认新密码'不能为空！", showTipDivId);
	if(!confirmpwdChecked){
		return false;
	}
	 if(confirmpwd.length<6){
	 	tip ="'确认新密码'长度只能 6-18位数 之间";
	 	popTip(tip);
	 	return false;
	 }
	  if(pwd!=confirmpwd){
	 	tip ="'新密码'与'确认新密码'不一致";
	 	popTip(tip);
	 	return false;
	 }
	  return true;
	//changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}

function showGradeInstruction(){
	var msg="试运行期不可进行升级，资源全部免费查看，感谢您的支持！";
	 BootstrapDialog.show({
			title: "升级说明",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
             label: '确定',
             cssClass : "btn-primary",
             action: function(dialog){
             	dialog.close();
             },
         }, {
             label: '关闭',
             cssClass : "btn-danger",
             action: function(dialog){
             	dialog.close();
             }
         }]
	});
	    
}
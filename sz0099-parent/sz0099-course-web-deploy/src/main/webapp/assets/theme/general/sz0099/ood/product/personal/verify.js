function changeMyRealname(id, realnameId, hiddenCodeId, messageTipId){
	$("#"+messageTipId).html("");
	var messageTip="真实姓名不能为空";
	var url= '/sz0099/ood/personal/myinfo/doModifyRealname';
	var filednameChecked = validateEmpty(realnameId, messageTip, messageTipId);
	if(!filednameChecked){
		return false;
	}
	var filednameLengthChecked = validateLength(realnameId, "昵称太长，都超10了", 10, messageTipId);
	if(!filednameLengthChecked){
		return false;
	}
	
	var filednameIdInput=$("#"+realnameId);
	var filednameV = filednameIdInput.val();
	var uploadData={
			'id' : id,
			'realname' : filednameV
	}
	
	changeSingleField(id, uploadData, hiddenCodeId,messageTipId, messageTip, url);
}

function applyIdVerify(id){
	
	var realnameInput = $("#id_realname");
	var realname = realnameInput.val();
	if(realname==null || ''==realname){
		popTip("真实姓名不能为空");
		return false;
	}
	var userIdInput = $("#id_userId");
	var userId = userIdInput.val();
	if(!userId){
		popTip("数据非法，请重新登录再试！");
		return false;
	}
	
	var identityInput = $("#id_identity");
	var identity = identityInput.val();
	if(identity==null || ''==identity){
		popTip("身份证号不能为空");
		return false;
	}
	var idChecked = identityCodeValid(identity);
	if(!idChecked){
		popTip("身份证号不正确，请核对！");
		return false;
	}
	
	if($("#id_parag_photoPreview1 .media").length<1){
		popTip("身份证照片正面未上传!");
		return false;
	}
	
	if($("#id_parag_photoPreview2 .media").length<1){
		popTip("身份证照片反面未上传!");
		return false;
	}
	
	
	
	axios({
	    method: 'post',
	    url: '/sz0099/ood/personal/myinfo/applyIdentity',
	    data: 
	    	Qs.stringify({
	    	'id' : id,
	    	'userId':userId,
	    	'identity' : identity,
	    	'realname':realname
	    	})
	})
	.then(function (response) {
	    
	    var id_hidden_successCode = $("#id_hidden_successCode");
	    var successCode = id_hidden_successCode.val();
	    BootstrapDialog.show({
			title: "提交认证审核",
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
                		var id_messageTip=$("#id_messageTip_identity"+id);
                		console.log(id_messageTip);
                		if(id_messageTip.length>0){
                			id_messageTip.html(commonMsg);
                		}
                		BootstrapDialog.closeAll();
                		window.location.href=window.location.href;
                	}
                },
            }, {
                label: '关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	dialog.close();
                	window.location.href=window.location.href;
                }
            }]
		});
	    
	})
	.catch(function (error) {
	    console.log(error);
	});
	
}
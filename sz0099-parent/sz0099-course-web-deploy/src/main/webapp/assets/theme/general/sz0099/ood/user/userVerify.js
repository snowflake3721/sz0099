function showUserVerify(id,remark){
	var remarkId="id_remark"+id;
	var msgTipId="id_msgTip_"+id;
	var msg="<textarea id='"+remarkId+"' name='remark' class='form-control' rows='3'>"+remark+"</textarea><br/>" +
	"<div id='"+ msgTipId +"'></div>";
	 BootstrapDialog.show({
			title: "身份认证审核",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
             label: '审核通过',
             cssClass : "btn-primary",
             action: function(dialog){
            	verifyIdentityPass(id, remarkId, msgTipId, dialog);
             },
         }, {
             label: '驳回',
             cssClass : "btn-danger",
             action: function(dialog){
            	verifyIdentityReject(id, remarkId, msgTipId, dialog);
             }
         }, {
             label: '关闭',
             cssClass : "btn-default",
             action: function(dialog){
             	dialog.close();
             }
         }]
	});
	    
}
function verifyIdentityPass(id,fieldId, msgTipId, dialog){
	var remarkInput = $("#"+fieldId);
	var remark=remarkInput.val();
	if(remark==''){
		remark="已通过";
	}
	var url="/sz0099/ood/personal/myinfo/idverifyPass";
	verifyIdentity(id, remark, url, msgTipId, dialog);
}

function verifyIdentityReject(id,fieldId, msgTipId, dialog){
	var remarkInput = $("#"+fieldId);
	var remark=remarkInput.val();
	if(remark==''){
		popTip("驳回原因不能为空！");
		return false;
	}
	var url="/sz0099/ood/personal/myinfo/idverifyReject";
	verifyIdentity(id, remark, url,msgTipId,dialog);
}

function verifyIdentity(id, remark, url,msgTipId, dialog){
	var uploadData={id:id, remark:remark};
	var msgTip=$("#"+msgTipId);
	msgTip.html('');
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
		msgTip.html(response.data);
	    var id_hidden_successCode = $("#id_hidden_common_success");
	    var id_message = $("#id_common_respMsg");
	    var successCode = id_hidden_successCode.val();
	    var message=id_message.html();
	    layer.msg(message);
	    
	    if(successCode==1){
	    	if(dialog){
		    	dialog.close();
		    }
	    	var hidden_id_btn_wrapper=$("#hidden_id_btn_wrapper");
		    if(hidden_id_btn_wrapper.length>0){
		    	var id_btn_wrapper="id_btn_wrapper"+id;
		    	var btnWrapper=$("#"+id_btn_wrapper);
		    	if(btnWrapper.length>0){
		    		btnWrapper.html(hidden_id_btn_wrapper.html());
		    	}
		    }
	    }
	})
	.catch(function (error) {
	    console.log(error);
	});
}
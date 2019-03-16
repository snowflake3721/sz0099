function applyClubLeader(){
	
}

function doApplyClubLeader(msgTipId){
	var uploadData={};
	var url="/sz0099/ood/product/personal/doApplyClubLeader";
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
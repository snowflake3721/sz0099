function changeCategory(url, refId, mainId, categoryId, messageTipId){
	//var productId = $("#id_hidden_id").val();
	//var mainId = $("#id_hidden_id").attr("data-mainId");
	//var subId =  $("#id_hidden_id").attr("data-subId");
	var uploadData={
			mainId : mainId,
			//subId : subId,
			baseId : categoryId,
			id : refId
	}
	
	axios({
	    method: 'post',
	    //url: '/sz0099/ood/product/manage/paragraph/add',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var respMsg=$('<div></div>').html(response.data);
	    
	    $.showSuccessTimeout(respMsg, function(){
	    	var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    	var id_messageTip_preview=$("#"+messageTipId);
    	    	id_messageTip_preview.removeClass("text-danger");
    	    	id_messageTip_preview.addClass("text-success");
    	    	id_messageTip_preview.html(commonMsg);
    	    }
	    	
	    	//informUpdate(messageTipId);
    	});
	    //移除通用元素
	    $("#id_hidden_common_success").remove();
	    $("#id_common_respMsg").remove();
	    $("#id_hidden_common_respCode").remove();
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
}
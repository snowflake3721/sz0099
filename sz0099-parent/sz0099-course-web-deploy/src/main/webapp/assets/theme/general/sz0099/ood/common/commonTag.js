//标签操作开始
function saveTag(mainId, url){
	
	var messageTipId="id_messageTip_tag";
	var name = $("#id_tag"+mainId).val();
	var tagId="id_tag"+mainId;
	var tagChecked = validateEmpty(tagId, "标签内容不能为空", messageTipId);
	if(!tagChecked){
		return false;
	}
	
	var tagLengthChecked = validateEmpty(tagId, "标签内容不能为空", messageTipId);
	if(!tagChecked){
		return false;
	}
	var tagLengthChecked = validateLength(tagId, "标签太长了，都超 8 位了", 8, messageTipId);
	if(!tagLengthChecked){
			return false;
	}
	var nameScriptChecked = validateScript(tagId, "标签含有非法字符，自动过滤", messageTipId);
	if(!nameScriptChecked){
		return false;
	}
	
	var uploadData={
    		"mainId" : mainId,
    		"name" : name
    	}
	
	axios({
	    method: 'post',
	    //url: '/sz0099/ood/product/manage/tag/add',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
	    var respMsg=$('<div></div>').html(response.data);
	    $("#id_p_tags"+mainId).append(response.data);
	    
	    var successVal = $("#id_hidden_common_success").val();
	    var commonMsg = $("#id_common_respMsg").html();
    	informUpdate("id_messageTip_tag");
	    if(successVal==1){
	    	respMsg=commonMsg;
	    	$("#id_tag"+mainId).val("");
    	    $.showSuccessTimeout(respMsg, function(){
    	    	
	    	});
	    }else{
	    	popTip(respMsg);
	    }
    	
	    
	  //移除通用元素
	    $("#id_hidden_common_success").remove();
	    $("#id_common_respMsg").remove();
	    $("#id_hidden_common_respCode").remove();
	  
	})
	.catch(function (error) {
		informUpdate(messageTipId);
	    console.log(error);
	});
	
}


function deleteTag(id, mainId, tagId, deleteTagId, url){
	var messageTipId="id_messageTip_tag";
	$(deleteTagId).attr("disabled",true);
	
	if(typeof id == 'undefined' || typeof mainId == 'undefined' || typeof tagId == 'undefined'){
		alert("数据非法，请刷新重试！");
	}
	
	var uploadData={
			"id" : id,
    		"mainId" : mainId,
    		"tagId" : tagId
    	}
	
	axios({
	    method: 'post',
	    // url: '/sz0099/ood/product/manage/tag/delete',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
	    var respMsg=$('<div></div>').html(response.data);
	    
	    $.showSuccessTimeout(respMsg, function(){
	    	informUpdate(messageTipId);
	    	var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    	$("#id_span_tag"+id).remove();
    	    }
    	    //移除通用元素
    	    $("#id_hidden_common_success").remove();
    	    $("#id_common_respMsg").remove();
    	    $("#id_hidden_common_respCode").remove();
    	    $(deleteTagId).removeAttr("disabled");
    	    
    	});
	   
	})
	.catch(function (error) {
		informUpdate(messageTipId);
		$(deleteTagId).removeAttr("disabled");
	    console.log(error);
	});
}

function clearTag(mainId){
	$("#id_tag"+mainId).val('');
}
//标签操作结束
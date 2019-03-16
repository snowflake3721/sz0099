function mergeRole(entityId, inputId){
		
	console.log("---entityId----" + entityId +" ---- ");
	if(typeof entityId=='undefined'){return false;}
	var inputData = $("#"+inputId);
	var url = inputData.attr("data-queryUrl");
	if(!entityId){
		console.log("!!! error for entityId : "+ entityId + "  >>>!!!");
		return false;
	}
	
	var uploadData={
	    	'id' : entityId
    }
	
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
		var respMsg=$('<div></div>').html(response.data);
	    
	    BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "编辑角色",
	    	message: $('<div></div>').html(respMsg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定修改',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeRole(entityId,inputId);
                	//dialog.close();
                },
            }, {
                label: '关闭',
                cssClass : "btn-primary",
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

function doMergeRole(entityId,inputId){
	console.log("=====doMergeRole:"+entityId + " =====");
	var uploadData=getEntity(entityId);
	var url=$("#"+inputId).attr("data-saveUrl");
	axios({
	    method: 'post',
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
    	    	success=true;
    	    	
    	    }
    	    //移除通用元素
    	    $("#id_hidden_common_success").remove();
    	    $("#id_common_respMsg").remove();
    	    $("#id_hidden_common_respCode").remove();
    	});
	    
	})
	.catch(function (error) {
	    console.log(error);
	});
}

function getEntity(entityId){
	var name=$("#id_role_name").val();
	var code=$("#id_role_code").val();
	var description=$("#id_role_description").val();
	var category=$("#hidden_id_role_category").val();
	var depositAmount=$("#id_role_depositAmount").val();
	var frozen=$("#hidden_id_role_frozen").val();
	var verifiable=$("#hidden_id_role_verifiable").val();
	var verifyFee=$("#id_role_verifyFee").val();
	var permanentable=$("#hidden_id_role_permanentable").val();
	var data={
			'id':entityId,
			'name':name,
			'code':code,
			'description':description,
			'category':category,
			'depositAmount':depositAmount,
			'frozen':frozen,
			'verifiable':verifiable,
			'verifyFee':verifyFee,
			'permanentable':permanentable
	}
	return data;
}
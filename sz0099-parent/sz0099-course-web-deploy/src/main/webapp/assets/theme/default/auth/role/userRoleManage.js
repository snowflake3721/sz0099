function addUserRole(entityId, inputId){
	var inputData = $("#"+inputId);
	//var roleCode = $("#"+roleCodeInputId).val();
	var url = inputData.attr("data-queryUrl");
	var userId = inputData.attr("data-userId");
	
	var uploadData={
	    	'userId' : userId,
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
			type: BootstrapDialog.TYPE_PRIMARY,
			title: "添加角色",
	    	message: $('<div></div>').html(respMsg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	
	    	buttons: [{
                label: '确定添加',
                cssClass : "btn-danger",
                action: function(dialog){
                	doAddUserRole('',inputId);
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

function doAddUserRole(entityId,inputId){
	var url=$("#"+inputId).attr("data-saveUrl");
	var uploadData=getEntity('');
	console.log(url);
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
    	    	window.location.href=window.location.href;
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
	    	onshown:function(){
	    		$("#id_hidden_common_success").remove();
	    	    $("#id_common_respMsg").remove();
	    	    $("#id_hidden_common_respCode").remove();
	    	},
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
    	    	window.location.href=window.location.href;
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
	var roleCode=$("#hidden_id_userRole_code").val();
	var roleLevel=$("#id_userRole_roleLevel").val();
	var expiredTime=$("#id_picker_userRole_expiredTime").val();
	if(expiredTime){
		expiredTime=expiredTime+" 00:00:00";
	}
	var verifyFee=$("#id_userRole_verifyFee").val();
	var status=$("#hidden_id_userRole_status").val();
	var verified=$("#hidden_id_userRole_verified").val();
	var depositAmount=$("#id_userRole_depositAmount").val();
	var depositChecked=$("#hidden_id_userRole_depositChecked").val();
	var frozen=$("#hidden_id_userRole_frozen").val();
	var permanent=$("#hidden_id_userRole_permanent").val();
	var userId=$("#id_userRole_userId").val();
	var roleId=$("#id_userRole_roleId").val();
	
	var data={
			'id':entityId,
			'roleLevel':roleLevel,
			'role.code':roleCode,
			'expiredTime':expiredTime,
			'status':status,
			'depositAmount':depositAmount,
			'depositChecked':depositChecked,
			'frozen':frozen,
			'verified':verified,
			'verifyFee':verifyFee,
			'permanent':permanent,
			'userId':userId,
			'roleId':roleId
	}
	return data;
}
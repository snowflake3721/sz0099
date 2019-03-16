	console.log("!!!in manage!!!!!!!!!");
	//技能 撤回|发布 操作开始
	function publilsh(professionId, publishStatus){
		
  		console.log("---professionId----" + professionId + " --- publishStatus: "+publishStatus +" ---- ");
		if(typeof professionId=='undefined'){return false;}
		
		if(!professionId){
			console.log("!!! error for professionId : "+ professionId + "  >>>!!!");
			return false;
		}
		
		var currentShelvedInput = $("#id_hidden_publishStatus"+professionId);
		var currentShelved = currentShelvedInput.val();
		
		var uploadData={
				'publishStatus' : publishStatus,
    	    	'id' : professionId
	    	}
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/personal/profession/manage/merge/unpublish',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    		var respMsg=$('<div></div>').html(response.data);
    	    var success = false;
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	respMsg=commonMsg;
        	    	success=true;
        	    	
        	    	if(publishStatus!=currentShelved){
	        	    	$("#id_li_shevled_"+professionId+currentShelved).removeClass("hidden");
	        	    	$("#id_li_shevled_"+professionId+publishStatus).addClass("hidden");
	        	    	currentShelvedInput.val(publishStatus);
	        	    	changeShelvedSpanShow(professionId, publishStatus);
        	    	}
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
	
	function changeShelvedSpanShow(professionId, val){
		var span = $("#id_span_shelved"+professionId);
		if(val==2){
			span.html("已发布");
			span.removeClass("text-danger");
			span.addClass("text-success");
		}else{
			span.html("已撤回为草稿");
			span.removeClass("text-success");
			span.addClass("text-danger");
		}
	}
	//技能 发布|撤回 操作结束
	
	//刷新技能开始
	function refreshProfession(professionId){
		
  		console.log("---professionId----" + professionId );
		if(typeof professionId=='undefined'){return false;}
		
		if(!professionId){
			console.log("!!! error for professionId : "+ professionId + "  >>>!!!");
			return false;
		}
		
		var refreshTime=$("#id_span_refreshTime"+professionId).html();
		
		var days = 0;
		var currentDate = new Date();
		if(refreshTime){
			var datebegin = parseDate(refreshTime,'yyyy-MM-dd HH:mm');
			console.log(datebegin);
			console.log(currentDate);
			days = daysBeginAndEnd(currentDate,datebegin);
			console.log(days);
		}
		if(days < 7){
			popTip("时间未过有效期，不能刷新！");
			return false;
		}
		
		
		var uploadData={
    	    	'id' : professionId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/personal/profession/manage/merge/refresh',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    		var respMsg=$('<div></div>').html(response.data);
    	    var success = false;
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	respMsg=commonMsg;
        	    	success=true;
        	    	var refreshTime=$("#id_common_data").html();
        	    	//更新刷新时间
        	    	$("#id_span_refreshTime"+professionId).html(refreshTime);
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
	//刷新技能结束
	
	function getEntityForQuickly(professionId){
		var titleId="id_title"+professionId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var nameId = "id_name"+professionId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var preIntroId = "id_preIntro"+professionId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var subTitleId = "id_subTitle"+professionId;
  		var subTitleInput = $("#"+subTitleId);
  		var subTitle = subTitleInput.val();
  		
  		var pennameId = "id_penname"+professionId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+professionId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"subTitle" : subTitle,
  			"penname" : penname,
  			"preIntroType" : preIntroType,
  			"preIntro" : preIntro,
  			"id" : professionId
  		}
  		return data;
	}
	
	
	//快速编辑开始
	function editQuickly(professionId){
		var messageTipId = "id_messageTip_edit_quickly"+professionId;
		var checked = validateEditQuickly(professionId, messageTipId);
		if(checked){
	  		
	  		//var messageTipId = "id_messageTip_edit_quickly"+professionId;
	  		var messageTipEditQuickly=$("#"+messageTipId);
	  		messageTipEditQuickly.html("");
			
			
			var uploadData=getEntityForQuickly(professionId);
			
			axios({
	    	    method: 'post',
	    	    url: '/sz0099/ood/personal/profession/manage/merge/editquickly',
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    		var respMsg=$('<div></div>').html(response.data);
	    	    var success = false;
	    	    $.showSuccessTimeout(respMsg, function(){
	    	    	var successVal = $("#id_hidden_common_success").val();
	        	    var commonMsg = $("#id_common_respMsg").html();
	        	    if(successVal==1){
	        	    	respMsg=commonMsg;
	        	    	success=true;
	        	    	//更新提示状态
	        	    	messageTipEditQuickly.html(commonMsg);
	        	    	
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
		
	}
	
	function validateEditQuickly(professionId, messageTipId){
		
  		//var messageTipId = "id_messageTip_edit_quickly"+professionId;
  		var messageTipBaseinfo=$("#"+messageTipId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		//title
  		var titleId = "id_title"+professionId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		var titleChecked = validateEmpty(titleId, "技能标题不能为空", messageTipId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "技能标题太长，都超32了", 32, messageTipId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "技能标题含有非法字符，自动过滤", messageTipId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		var nameId = "id_name"+professionId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
  		var nameChecked = validateEmpty(nameId, "技能名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "技能名称太长，都超32了", 32, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		
  		var nameScriptChecked = validateScript(nameId, "技能名称含有非法字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		//id_subTitle
  		var subTitleId = "id_subTitle"+professionId;
		var subTitleInput = $("#"+subTitleId);
		var subTitle = subTitleInput.val();
  		//var subTitleChecked = validateEmpty(subTitleId, "子标题不能为空", messageTipId);
  		if(subTitle && subTitle !=''){
  			var subTitleLengthChecked = validateLength(subTitleId, "子标题太长，都超32了", 32, messageTipId);
  	  		if(!subTitleLengthChecked){
  	  			return false;
  	  		}
  	  		
  	  		var subTitleScriptChecked = validateScript(subTitleId, "子标题含有非法字符，自动过滤", messageTipId);
  	  		if(!subTitleScriptChecked){
  	  			return false;
  	  		}
  		}
  		
  		//id_preIntro
  		var preIntroId = "id_preIntro"+professionId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+professionId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		//var preIntroChecked = validateEmpty(preIntroId, "导语不能为空", messageTipId);
  		if(preIntro && preIntro!=''){
  			var preIntroLengthChecked = validateLength(preIntroId, "导语太长，都超10了", 10, messageTipId);
  	  		if(!preIntroLengthChecked){
  	  			return false;
  	  		}
  		}
  		
  		var pennameId = "id_penname"+professionId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		//var pennameChecked = validateEmpty(pennameId, "导语不能为空", messageTipId);
  		if(penname && penname != ''){
  			var pennameLengthChecked = validateLength(pennameId, "技能别名 太长，都超16了", 16, messageTipId);
  	  		if(!pennameLengthChecked){
  	  			return false;
  	  		}
  		}
  		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	//快速编辑结束
	
	function mergeForClosed(professionId){
		var nameId = "id_name"+professionId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "关闭技能后将<b>不能再次开启，不可被编辑，不可搜索查看</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要关闭技能【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "关闭技能提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForClosed(professionId);
                	dialog.close();
                },
            }, {
                label: '取消',
                cssClass : "btn-primary",
                action: function(dialog){
                	dialog.close();
                }
            }]
		});
	}
	
	function doMergeForClosed(professionId){
		var messageTipId = "id_messageTip_edit_quickly"+professionId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : professionId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/personal/profession/manage/merge/closed',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    		var respMsg=$('<div></div>').html(response.data);
    	    var success = false;
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	respMsg=commonMsg;
        	    	success=true;
        	    	//更新提示状态
        	    	messageTipEditQuickly.html(commonMsg);
        	    	
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
	
	function mergeForDeleted(professionId){
		var nameId = "id_name"+professionId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "删除技能后将<b>不可恢复</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要删除技能【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除技能提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(professionId);
                	dialog.close();
                },
            }, {
                label: '取消',
                cssClass : "btn-primary",
                action: function(dialog){
                	dialog.close();
                }
            }]
		});
	}
	
	function doMergeForDeleted(professionId){
		var messageTipId = "id_messageTip_edit_quickly"+professionId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : professionId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/personal/profession/manage/merge/deleted',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    		var respMsg=$('<div></div>').html(response.data);
    	    var success = false;
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	respMsg=commonMsg;
        	    	success=true;
        	    	//更新提示状态
        	    	messageTipEditQuickly.html(commonMsg);
        	    	window.location.href="/sz0099/ood/personal/profession/manage/queryProfessionList";
        	    	
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
	
	
	
	function reward(professionId, url, divId){
		var divWrapper = $("#"+divId);
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "打赏技能",
	    	message: $('<div></div>').html(divWrapper.html()),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定',
                cssClass : "btn-danger",
                action: function(dialog){
                	dialog.close();
                },
            }, {
                label: '关闭',
                cssClass : "btn-primary",
                action: function(dialog){
                	dialog.close();
                }
            }]
		});
	}
	
	function favirate(professionId, url){
		var uploadData={
    	    	'mainId' : professionId
	    }
		
		axios({
    	    method: 'post',
    	    url: url,
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    		var respMsg=$('<div></div>').html(response.data);
    	    var success = false;
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	//respMsg=commonMsg;
        	    	success=true;
        	    	//更新提示状态
        	    	//messageTipEditQuickly.html(commonMsg);
        	    	
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
	
	

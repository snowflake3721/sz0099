	//活动 撤回|发布 操作开始
	function publilsh(activityId, publishStatus){
		
		if(typeof activityId=='undefined'){return false;}
		
		if(!activityId){
			return false;
		}
		
		var currentShelvedInput = $("#id_hidden_publishStatus"+activityId);
		var currentShelved = currentShelvedInput.val();
		
		var uploadData={
				'publishStatus' : publishStatus,
    	    	'id' : activityId
	    	}
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/article/manage/merge/unpublish',
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
	        	    	$("#id_li_shevled_"+activityId+currentShelved).removeClass("hidden");
	        	    	$("#id_li_shevled_"+activityId+publishStatus).addClass("hidden");
	        	    	currentShelvedInput.val(publishStatus);
	        	    	changeShelvedSpanShow(activityId, publishStatus);
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
	
	function changeShelvedSpanShow(activityId, val){
		var span = $("#id_span_shelved"+activityId);
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
	//活动 发布|撤回 操作结束
	
	//刷新活动开始
	function refreshActivity(activityId){
		
		if(typeof activityId=='undefined'){return false;}
		if(!activityId){
			return false;
		}
		var refreshTime=$("#id_span_refreshTime"+activityId).html();
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
    	    	'id' : activityId
	    }
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/article/manage/merge/refresh',
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
        	    	$("#id_span_refreshTime"+activityId).html(refreshTime);
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
	//刷新活动结束
	
	function getEntityForQuickly(activityId){
		var titleId="id_title"+activityId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var nameId = "id_name"+activityId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var preIntroId = "id_preIntro"+activityId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var subTitleId = "id_subTitle"+activityId;
  		var subTitleInput = $("#"+subTitleId);
  		var subTitle = subTitleInput.val();
  		
  		var pennameId = "id_penname"+activityId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+activityId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		var kilometerId = "id_kilometer"+activityId;
  		var kilometerInput = $("#"+kilometerId);
  		var kilometer = kilometerInput.val();
  		
  		var minNumId = "id_minNum"+activityId;
  		var minNumInput = $("#"+minNumId);
  		var minNum = minNumInput.val();
  		
  		var maxNumId = "id_maxNum"+activityId;
  		var maxNumInput = $("#"+maxNumId);
  		var maxNum = maxNumInput.val();
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"subTitle" : subTitle,
  			"penname" : penname,
  			"preIntroType" : preIntroType,
  			"preIntro" : preIntro,
  			"kilometer":kilometer,
  			"minNum":minNum,
  			"maxNum":maxNum,
  			"id" : activityId
  		}
  		return data;
	}
	
	
	//快速编辑开始
	function editQuickly(activityId){
		var messageTipId = "id_messageTip_edit_quickly"+activityId;
		var checked = validateEditQuickly(activityId, messageTipId);
		if(checked){
	  		//var messageTipId = "id_messageTip_edit_quickly"+activityId;
	  		var messageTipEditQuickly=$("#"+messageTipId);
	  		messageTipEditQuickly.html("");
			var uploadData=getEntityForQuickly(activityId);
			axios({
	    	    method: 'post',
	    	    url: '/sz0099/ood/activity/manage/merge/editquickly',
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
	
	function validateEditQuickly(activityId, messageTipId){
		
  		//var messageTipId = "id_messageTip_edit_quickly"+activityId;
  		var messageTipBaseinfo=$("#"+messageTipId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		//title
  		var titleId = "id_title"+activityId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		var titleChecked = validateEmpty(titleId, "活动标题不能为空", messageTipId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "活动标题太长，都超32了", 32, messageTipId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "活动标题含有非法字符，自动过滤", messageTipId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		var nameId = "id_name"+activityId;
		var nameInput = $("#"+nameId);
		if(nameInput.length>0){
			var nameLengthChecked = validateLength(nameId, "活动名称太长，都超32了", 32, messageTipId);
	  		if(!nameLengthChecked){
	  			return false;
	  		}
	  		
	  		var nameScriptChecked = validateScript(nameId, "活动名称含有非法字符，自动过滤", messageTipId);
	  		if(!nameScriptChecked){
	  			return false;
	  		}
		}
		/*var name = nameInput.val();
  		var nameChecked = validateEmpty(nameId, "活动名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}*/
  		
  		
  		//id_subTitle
  		var subTitleId = "id_subTitle"+activityId;
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
  		var preIntroId = "id_preIntro"+activityId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+activityId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		//var preIntroChecked = validateEmpty(preIntroId, "导语不能为空", messageTipId);
  		if(preIntro && preIntro!=''){
  			var preIntroLengthChecked = validateLength(preIntroId, "导语太长，都超20了", 20, messageTipId);
  	  		if(!preIntroLengthChecked){
  	  			return false;
  	  		}
  		}
  		
  		var pennameId = "id_penname"+activityId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		//var pennameChecked = validateEmpty(pennameId, "导语不能为空", messageTipId);
  		if(penname && penname != ''){
  			var pennameLengthChecked = validateLength(pennameId, "绰号/笔名 太长，都超10了", 10, messageTipId);
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
	
	function mergeForClosed(activityId){
		var nameId = "id_name"+activityId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "关闭活动后将<b>不能再次开启，不可被编辑，不可搜索查看</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要关闭活动【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "关闭活动提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForClosed(activityId);
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
	
	function doMergeForClosed(activityId){
		var messageTipId = "id_messageTip_edit_quickly"+activityId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : activityId
	    }
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/article/manage/merge/closed',
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
	
	function mergeForDeletedDraft(activityId){
		var nameId = "id_name"+activityId;
		var nameInput = $("#"+nameId);
		var name = nameInput.html();
		var msg = "删除活动后将<b>不可恢复</b><br>你确定要删除活动【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除活动提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(activityId);
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
	
	function mergeForDeleted(activityId){
		var nameId = "id_name"+activityId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "删除活动后将<b>不可恢复</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要删除活动【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除活动提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(activityId);
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
	
	function doMergeForDeleted(activityId){
		var messageTipId = "id_messageTip_edit_quickly"+activityId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : activityId
	    }
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/article/manage/merge/deleted',
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
        	    	window.location.href="/sz0099/ood/article/manage/queryDraftList";
        	    	
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
	
	function reward(activityId, url, divId){
		var divWrapper = $("#"+divId);
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "打赏活动",
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
	
	function favirate(activityId, url){
		var uploadData={
    	    	'mainId' : activityId
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

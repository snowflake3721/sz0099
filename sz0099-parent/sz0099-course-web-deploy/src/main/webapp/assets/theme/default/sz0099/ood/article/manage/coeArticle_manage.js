	//文章 撤回|发布 操作开始
	function publilsh(articleId, publishStatus){
		
		if(typeof articleId=='undefined'){return false;}
		
		if(!articleId){
			return false;
		}
		
		var currentShelvedInput = $("#id_hidden_publishStatus"+articleId);
		var currentShelved = currentShelvedInput.val();
		
		var uploadData={
				'publishStatus' : publishStatus,
    	    	'id' : articleId
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
	        	    	$("#id_li_shevled_"+articleId+currentShelved).removeClass("hidden");
	        	    	$("#id_li_shevled_"+articleId+publishStatus).addClass("hidden");
	        	    	currentShelvedInput.val(publishStatus);
	        	    	changeShelvedSpanShow(articleId, publishStatus);
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
	
	function changeShelvedSpanShow(articleId, val){
		var span = $("#id_span_shelved"+articleId);
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
	//文章 发布|撤回 操作结束
	
	//刷新文章开始
	function refreshArticle(articleId){
		
		if(typeof articleId=='undefined'){return false;}
		if(!articleId){
			return false;
		}
		var refreshTime=$("#id_span_refreshTime"+articleId).html();
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
    	    	'id' : articleId
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
        	    	$("#id_span_refreshTime"+articleId).html(refreshTime);
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
	//刷新文章结束
	
	function getEntityForQuickly(articleId){
		var titleId="id_title"+articleId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var nameId = "id_name"+articleId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var preIntroId = "id_preIntro"+articleId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var subTitleId = "id_subTitle"+articleId;
  		var subTitleInput = $("#"+subTitleId);
  		var subTitle = subTitleInput.val();
  		
  		var pennameId = "id_penname"+articleId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+articleId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"subTitle" : subTitle,
  			"penname" : penname,
  			"preIntroType" : preIntroType,
  			"preIntro" : preIntro,
  			"id" : articleId
  		}
  		return data;
	}
	
	
	//快速编辑开始
	function editQuickly(articleId){
		var messageTipId = "id_messageTip_edit_quickly"+articleId;
		var checked = validateEditQuickly(articleId, messageTipId);
		if(checked){
	  		
	  		//var messageTipId = "id_messageTip_edit_quickly"+articleId;
	  		var messageTipEditQuickly=$("#"+messageTipId);
	  		messageTipEditQuickly.html("");
			var uploadData=getEntityForQuickly(articleId);
			axios({
	    	    method: 'post',
	    	    url: '/sz0099/ood/article/manage/merge/editquickly',
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
	
	function validateEditQuickly(articleId, messageTipId){
		
  		//var messageTipId = "id_messageTip_edit_quickly"+articleId;
  		var messageTipBaseinfo=$("#"+messageTipId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		//title
  		var titleId = "id_title"+articleId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		var titleChecked = validateEmpty(titleId, "文章标题不能为空", messageTipId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "文章标题太长，都超32了", 32, messageTipId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "文章标题含有非法字符，自动过滤", messageTipId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		var nameId = "id_name"+articleId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
  		var nameChecked = validateEmpty(nameId, "文章名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "文章名称太长，都超32了", 32, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		
  		var nameScriptChecked = validateScript(nameId, "文章名称含有非法字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		//id_subTitle
  		var subTitleId = "id_subTitle"+articleId;
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
  		var preIntroId = "id_preIntro"+articleId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = preIntroInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+articleId);
  		var preIntroType = preIntroTypeInput.val();
  		
  		//var preIntroChecked = validateEmpty(preIntroId, "导语不能为空", messageTipId);
  		if(preIntro && preIntro!=''){
  			var preIntroLengthChecked = validateLength(preIntroId, "导语太长，都超20了", 20, messageTipId);
  	  		if(!preIntroLengthChecked){
  	  			return false;
  	  		}
  		}
  		
  		var pennameId = "id_penname"+articleId;
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
	
	function mergeForClosed(articleId){
		var nameId = "id_name"+articleId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "关闭文章后将<b>不能再次开启，不可被编辑，不可搜索查看</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要关闭文章【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "关闭文章提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForClosed(articleId);
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
	
	function doMergeForClosed(articleId){
		var messageTipId = "id_messageTip_edit_quickly"+articleId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : articleId
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
	
	function mergeForDeletedDraft(articleId){
		var nameId = "id_name"+articleId;
		var nameInput = $("#"+nameId);
		var name = nameInput.html();
		var msg = "删除文章后将<b>不可恢复</b><br>你确定要删除文章【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除文章提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(articleId);
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
	
	function mergeForDeleted(articleId){
		var nameId = "id_name"+articleId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "删除文章后将<b>不可恢复</b>，您是否考虑用【撤回】功能代替此操作？<br>你确定要删除文章【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除文章提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(articleId);
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
	
	function doMergeForDeleted(articleId){
		var messageTipId = "id_messageTip_edit_quickly"+articleId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : articleId
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
	
	function reward(articleId, url, divId){
		var divWrapper = $("#"+divId);
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "打赏文章",
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
	
	function favirate(articleId, url){
		var uploadData={
    	    	'mainId' : articleId
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

	console.log("!!!in create!!!!!!!!!");
	
	var paragraphLoaded=false;
	function loadData(panelName, url){
		var activityId = $("#id_hidden_id").val();
		if(panelName=="#contentPhoto" && !paragraphLoaded){
			//加载段落数据
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/editListUI',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({
		    	    	'mainId' : activityId
	    	    	})
	    	    
	    	})
	    	.then(function (response) {
	    	    console.log("---- photo loaded----");
	    	    $("#id_contentPhoto_content"+activityId).html(response.data);
	    	    var currentParagId= $("#id_paragId_current").val();
	    	    //initParagraph(currentParagId); TODO 仅初始化当前这个
	    	    initParagraph(activityId);//将所有file组件全都初始化一遍
	    	    paragraphLoaded=true;
	    	   
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	});
		
		}
	}
	
	function refreshParagraph(activityId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
	}
	
	function refreshParagraphOrder(activityId, url, panelName){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = panelName;
		currentPanel.val(panelName);
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
	}
	
	function validateBaseinfo(activityId, messageDivId){
		
		var messageTipBaseinfoId = messageDivId+activityId;
  		var messageTipBaseinfo=$("#"+messageTipBaseinfoId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		var uploadData=getEntity(activityId);
  		
  		//title
  		var titleId="id_title"+activityId;
  		var titleChecked = validateEmpty(titleId, "标题不能为空", messageTipBaseinfoId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "标题太长，都超64了", 64, messageTipBaseinfoId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "标题含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//description
  		var descriptionId = "id_area_description"+activityId;
  		var descriptionChecked = validateEmpty(descriptionId, "活动概要不能为空", messageTipBaseinfoId);
  		if(!descriptionChecked){
  			return false;
  		}
  		
  		var descriptionLengthChecked = validateLength(descriptionId, "描述太长，都超1000了", 1000, messageTipBaseinfoId);
  		if(!descriptionLengthChecked){
  			return false;
  		}
  		
  		var descriptionScriptChecked = validateScript(descriptionId, "描述含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!descriptionScriptChecked){
  			return false;
  		}
  		
  		var actOrganizeInput = $("#hidden_id_act_actOrganize");
  		var actOrganize = 0;
  		if(actOrganizeInput.length >0 ){
  			actOrganize = actOrganizeInput.val();
  			if(!actOrganize || actOrganize==0){
  				popTip("请选择一个组织形式");
  				return false;
  			}
  		}
  		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	
	
	function commitSingle(btnId,panelIdHidden, activityId){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		btn.removeClass("hidden");
  		
  		
  		var panel = $("#"+panelIdHidden);
  		var panelId=panel.val();
  		var url = panel.attr("data-saveUrl");
  		
  		if(panelId=="#panel_baseinfo"){
  			delayCommitBaseinfo(btnId, activityId, url);
  			//return ;
  		}else if(panelId=="#contentPhoto"){
  			btn.addClass("hidden");
  		}else if(panelId=="#contentTag"){
  			btn.addClass("hidden");
  		}
  		
  		btn.removeAttr("disabled");
	}
	var commitBaseinfoTm=null;
	function delayCommitBaseinfo(btnId, activityId, url){
		console.log("delayCommitBaseinfo call--");
		if(null != commitBaseinfoTm){
			clearTimeout(commitBaseinfoTm);
		}
		commitBaseinfoTm=setTimeout(function(){
			commitBaseinfo(btnId, activityId, url);
		}, 500);
	}
	function commitBaseinfo(btnId, activityId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var idMessageTipBaseinfoId="id_messageTip_baseinfo"+activityId;
  		
		var checked = validateBaseinfo(activityId, idMessageTipBaseinfoId);
		var uploadData=getEntity(activityId);
		if(checked){
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/merge/baseinfo',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "基本信息上传结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(idMessageTipBaseinfoId);
	                    	dialog.close();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(idMessageTipBaseinfoId);
	                    	dialog.close();
	                    }
	                }]
	    		});
	    	    btn.removeAttr("disabled");
	      		
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	    informUpdate(idMessageTipBaseinfoId);
	    	});
		}
		
	}
	
	//保存单个段落
	function saveParagraphSingle(paragId, url){
		var activityId = $("#id_hidden_id").val();
		
		var nameInput=$("#id_parag_name"+paragId);
		var titleInput=$("#id_parag_title"+paragId);
		var descriptionInput=$("#id_parag_description"+paragId);
		var orderSeqInput=$("#id_parag_orderSeq"+paragId);
		var paragProductInput=$("#id_paragProductId"+paragId);
		var paragIdInput=$("#id_paragId"+paragId);
		
		var nameK = nameInput.attr("name");
		var nameV = nameInput.val();
		var titleK = titleInput.attr("name");
		var titleV = titleInput.val();
		var descriptionK = descriptionInput.attr("name");
		var descriptionV = descriptionInput.val();
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
		
		var paragProductIdK = paragProductInput.attr("name");
		var paragProductIdV = paragProductInput.val();
		
		var paragIdK = paragIdInput.attr("name");
		var paragIdV = paragIdInput.val();
		
		var messageTipId="id_messageTip_paragraph"+activityId;
		/*console.log(paragIdK + ": " + paragIdV);
		console.log(nameK + ": " + nameV);
		console.log(titleK + ": " + titleV);
		console.log(descriptionK + ": " + descriptionV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(paragProductIdK + ": " + paragProductIdV);
		console.log("activityId" + ": " + activityId);*/
		var checked = validateParagraphSingleParam(paragId);
		var uploadData={
	    		"paragraph.name" : nameV,
	    		"paragraph.title" : titleV,
	    		"paragraph.description" : descriptionV,
	    		"orderSeq" : orderSeqV,
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : activityId,
	    		"paragraph.id":paragId
	    	}
		
		if(checked){
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/mergeSingle',
	    	    url : url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "保存段落结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	dialog.close();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	dialog.close();
	                    }
	                }]
	    		});
	    	    
	    	})
	    	.catch(function (error) {
	    		informUpdate(messageTipId);
	    	    console.log(error);
	    	});
		
		}//end if check
		
	}
	
	
	function validateParagraphSingleParam(paragId){
		
		var activityId = $("#id_hidden_id").val();
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){
			validateSessionInvalid("id_messageTip_paragraph","！！当前会话已失效，请重新登录，再操作！！");
			return false;
		}
		
		if(!activityId){
			return false;
		}
		
		var messageTipId = "id_messageTip_paragraph";
		var messageTipParagraph=$("#"+messageTipId);
		messageTipParagraph.html("");
		messageTipParagraph.addClass("text-danger");

		var descriptionInput=$("#id_parag_description"+paragId);
		var orderSeqInput=$("#id_parag_orderSeq"+paragId);
		
		var descriptionK = descriptionInput.attr("name");
		var descriptionV = descriptionInput.val();
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
		
  		
  		orderSeqV = checkOnlyNum(orderSeqV);
  		orderSeqInput.val(orderSeqV);
  		var orderSeqChecked = validateEmpty("id_parag_orderSeq"+paragId, "段落序号要填写数字", messageTipId);
  		if(!orderSeqChecked){
  			return false;
  		}
  		
  		var nameChecked = validateEmpty("id_parag_name"+paragId, "段落名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength("id_parag_name"+paragId, "段落名称太长，都超20字符了", 20, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		
  		//var titleChecked = validateEmpty("id_parag_title"+paragId, "段落标题不能为空", messageTipId);
  		//if(titleChecked){
  			//不做为空校验，可以为空
  			//return false;
  		//}
  		var titleLengthChecked = validateLength("id_parag_title"+paragId, "段落标题太长，都超60字符了", 60, messageTipId);
	  		if(!titleLengthChecked){
	  			return false;
	  	}
  		
  		
  		
  		//var descriptionChecked = validateEmpty("id_parag_description"+paragId, "段落描述不能为空", messageTipId);
  		//if(!descriptionChecked){
  			//不做为空校验，可以为空
  			//return false;
  		//}
  		
  		var descriptionLengthChecked = validateLength("id_parag_description"+paragId, "段落描述太长，都超255字符了", 255, messageTipId);
  		if(!descriptionLengthChecked){
  			return false;
  		}
  		
  		messageTipParagraph.html("段落内容验证通过...");
  		messageTipParagraph.removeClass("text-danger");
  		messageTipParagraph.addClass("text-success");
  		
  		return true;
	}
	
	function deleteParagraphSingle(paragId,url){
		var checked = validateDeleteParagData(paragId);
		var nameInput=$("#id_parag_name"+paragId);
		var nameV=nameInput.val();
		
		var orderSeqInput=$("#id_parag_orderSeq"+paragId);
		var orderSeqV = orderSeqInput.val();
		
		var showV=nameV;
		if(showV==''){
			showV="编号为#"+orderSeqV;
		}
		var messageTipId = "id_messageTip_paragraph";
		var tip="您确定要删除<span class='text-danger'>【"+showV+"】</span>的段落吗？删除后不可恢复，请谨慎操作！"
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteParagraphSingle(paragId, url);
                	}else{
                		alert("数据有误，请刷新后重试！");
                		dialog.close();
                	}
                },
            }, {
                label: '取消',
                cssClass : "btn-primary",
                action: function(dialog){
                	informUpdate(messageTipId);
                	dialog.close();
                }
            }]
		});
	}
	
	function doDeleteParagraphSingle(paragId, url){
		var paragProductInput=$("#id_paragProductId"+paragId);
		var paragProductIdV=paragProductInput.val();
		
		var activityId = $("#id_hidden_id").val();
		
		var paragIdInput=$("#id_paragId"+paragId);
		var paragIdV = paragIdInput.val();
		
		var uploadData={
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : activityId,
	    		"paragraph.id":paragId
	    	}
		var checked=validateDeleteParagData(paragId);
		var messageTipId = "id_messageTip_paragraph";
		if(checked){
			axios({
	    	    method: 'post',
	    	   // url: '/sz0099/ood/product/manage/paragraph/deleteSingle',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "删除段落结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(paragProductIdV);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(paragProductIdV);
	                    	BootstrapDialog.closeAll();
	                    }
	                }]
	    		});
	    	    
	    	})
	    	.catch(function (error) {
	    		informUpdate(messageTipId);
	    	    console.log(error);
	    	});
		
		}//end if check
	}
	
	function removeDeletedDivWrapper(paragProductIdV){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
   		if(successValue==1){
   			$("#paragragh_"+paragProductIdV).remove();
   		}
	}
	
	function validateDeleteParagData(paragId){
		var activityId = $("#id_hidden_id").val();
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){return false;}
		
		if(!activityId){
			return false;
		}
		return true;
	}
	
	//删除全部部落开始
	
	function deleteAllParagraph(activityId, url){
		var checked = validateDeleteAllParagData(activityId);
		var messageTipId = "id_messageTip_paragraph";
		var tip="您确定要删除<span class='text-danger'>【全部段落】</span>吗？删除后不可恢复，请谨慎操作！"
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定全部删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteAllParagraph(activityId, url);
                	}else{
                		alert("数据有误，请刷新后重试！");
                		dialog.close();
                	}
                },
            }, {
                label: '取消',
                cssClass : "btn-primary",
                action: function(dialog){
                	informUpdate(messageTipId);
                	dialog.close();
                }
            }]
		});
	}
	
	function doDeleteAllParagraph(activityId, url){
		var messageTipId = "id_messageTip_paragraph";
		var uploadData={
	    		"mainId" : activityId,
	    	}
		var checked=validateDeleteAllParagData(activityId);
		if(checked){
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/deleteAll',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "删除全部段落结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(activityId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(activityId);
	                    	BootstrapDialog.closeAll();
	                    }
	                }]
	    		});
	    	    
	    	})
	    	.catch(function (error) {
	    		informUpdate(messageTipId);
	    	    console.log(error);
	    	});
		
		}//end if check
	}
	
	function removeDeletedAllDivWrapper(activityId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
   		if(successValue==1){
   			$("#id_contentPhoto_content"+activityId).empty();
   		}
		
	}
	
	function validateDeleteAllParagData(activityId){
		
		if(typeof activityId=='undefined'){return false;}
		var activityIdV = activityId;
		if(!activityId){
			return false;
		}
		
		if(''==activityId){
			activityIdV = $("#id_hidden_id").val();
		}
		
		if(activityIdV=='' || null==activityIdV){
			return false;
		}
		
		return true;
	}
	
	//删除全部段落结束
	
	
	//添加段落开始
	function addParagraphSingle(activityId, url){
		var messageTipId = "id_messageTip_paragraph";
		var activityIdV = null;
		if(typeof activityId !='undefined'){
			activityIdV = activityId;
		}
		
		if(''==activityIdV || null==activityIdV){
			activityIdV = $("#id_hidden_id").val();
		}
		
		if(activityIdV=='' || null==activityIdV ){
			return false;
		}
		
		var uploadData={
	    		"mainId" : activityId
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
    	    $("#id_contentPhoto_content"+activityId).append(response.data);
    	    var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    }
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	informUpdate(messageTipId);
	    	});
    	    //初始化file组件
    	    initParagraph(activityId);
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
	//添加段落结束
	
	
	function getEntity(activityId){
		var titleId="id_title"+activityId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var nameId = "id_name"+activityId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var descriptionId = "id_area_description"+activityId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		
  		var preIntroId = "id_preIntro"+activityId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = "无";
  		if(preIntroInput.length>0){
  			preIntro = preIntroInput.val();
  		}
  		
  		var subTitleId = "id_subTitle"+activityId;
  		var subTitleInput = $("#"+subTitleId);
  		var subTitle = subTitleInput.val();
  		
  		var pennameId = "id_penname"+activityId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+activityId);
  		var preIntroType = 0;
  		if(preIntroTypeInput.length >0 ){
  			preIntroType = preIntroTypeInput.val();
  		}
  		
  		var actOrganizeInput = $("#hidden_id_act_actOrganize");
  		var actOrganize = 0;
  		if(actOrganizeInput.length >0 ){
  			actOrganize = actOrganizeInput.val();
  		}
  		
  		var kilometerId = "id_kilometer"+activityId;
  		var kilometerInput = $("#"+kilometerId);
  		var kilometer = kilometerInput.val();
  		
  		var difficultyId = "id_difficulty"+activityId;
  		var difficultyInput = $("#"+difficultyId);
  		var difficulty = difficultyInput.val();
  		
  		var minNumId = "id_minNum"+activityId;
  		var minNumInput = $("#"+minNumId);
  		var minNum = minNumInput.val();
  		
  		var maxNumId = "id_maxNum"+activityId;
  		var maxNumInput = $("#"+maxNumId);
  		var maxNum = maxNumInput.val();
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"description" : description,
  			//"subTitle" : subTitle,
  			"penname" : penname,
  			"kilometer" : kilometer,
  			"minNum" : minNum,
  			"maxNum" : maxNum,
  			"preIntroType" : preIntroType,
  			"preIntro" : preIntro,
  			"actOrganize" : actOrganize,
  			"difficulty" : difficulty,
  			"id" : activityId
  		}
  		return data;
	}
	
	function mergeForPublish(activityId, url){
		
		var messageTipId = "id_messageTip_publish";
		
		var baseinfoChecked = validateBaseinfo(activityId, messageTipId);
		if(!baseinfoChecked){
			return false;
		}
		var timeChecked=validateTime();
		if(!timeChecked){
			return false;
		}
		var actFeeInput=$("#id_act_fee_id");
		var feeId=actFeeInput.val();
		var feeChecked=validateFee(feeId);
		if(!feeChecked){
			return false;
		}
		var uploadData=getEntity(activityId);
		axios({
    	    method: 'post',
    	    url: url,
    	    data: Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    	    var respMsg=$('<div></div>').html(response.data);
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	informUpdate(messageTipId);
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    if(successVal==1){
        	    	respMsg=commonMsg;
        	    }
        	    //移除通用元素
        	    $("#id_hidden_common_success").remove();
        	    $("#id_common_respMsg").remove();
        	    $("#id_hidden_common_respCode").remove();
        	    
	    	});
    	   
    	})
    	.catch(function (error) {
    		informUpdate(messageTipId);
    	    console.log(error);
    	});
	}
	
	function mergeForTemplate(fieldId, entityId){
		var templateId = "id_template"+ entityId;
  		var templateInput=$("#hidden_id_act_template");
  		var cascadeParagInput=$("#hidden_id_act_cascadeParag");
  		var template=0;
  		if(templateInput.length>0){
  			template = templateInput.val();
  		}
  		var cascadeParag=1;
  		if(cascadeParagInput.length>0){
  			cascadeParag=cascadeParagInput.val();
  		}
  		var uploadData={id: entityId, template:template, cascadeParag:cascadeParag};
  		console.log(uploadData);
  		mergeCommon(fieldId,entityId,uploadData);
	}
	
	function mergeFee(fieldId,feeId){
		var uploadData=getFee(feeId);
		mergeCommon(fieldId,feeId,uploadData);
	}
	
	function mergeCommon(fieldId,entityId, uploadData){
		//var uploadData=getFee(entityId);
		var field=$("#"+fieldId);
		var data_saved=field.attr("data-saved");//监控是否正在执行保存动作，1时为正在保存
		var data_tipId=field.attr("data-tipId");
		var showTip=$("#"+data_tipId);
		if(showTip.length>0){
			showTip.html('');
		}
		var url = field.attr("data-saveUrl");
		if(data_saved!=1 ){
			field.attr("data-saved",1);
			axios({
			    method: 'post',
			    url: url,
			    data:Qs.stringify(uploadData)
			})
			.then(function (response) {
				if(showTip.length>0){
					showTip.html(response.data);
					var successInput=showTip.find("input[name='success']");
					var msgInput=showTip.find("input[name='respMsg']");
					if(successInput.length>0){
						var successVal=successInput.val();
						console.log(successVal);
						if(successVal==1){
							console.log("已保存");
							var msg=msgInput.val();
							layer.msg(msg);
						}else{
							var msg=msgInput.val();
							layer.msg(msg);
						}
					}
				}else{
					console.log("未配置id_XXX_showTip");
				}
				field.attr("data-saved",0);//返回后，可以再次执行保存
			})
			.catch(function (error) {
			    console.log(error);
			    field.attr("data-saved",0);//返回后，可以再次执行保存
			});
		}
	}
	
	function getFee(feeId){
		var currency=$("#hidden_id_fee_currency").val();
		var feeType=$("#hidden_id_fee_feeType").val();
		var recieveType=$("#hidden_id_fee_recieveType").val();
		var priceType=$("#hidden_id_fee_priceType").val();
		
		var rmbAmountInput=$("#id_fee_rmbAmount"+feeId);
		var rmbValue=rmbAmountInput.val();
		var rmbAmount=checkOnlyNum(rmbValue);
		rmbAmountInput.val(rmbAmount);
		
		var rmbAmountOriInput=$("#id_fee_rmbAmountOri"+feeId);
		var rmbOriValue=rmbAmountOriInput.val();
		var rmbAmountOri=checkOnlyNum(rmbOriValue);
		rmbAmountOriInput.val(rmbAmountOri);
		
		var descriptionInput=$("#id_fee_description"+feeId);
		var description=descriptionInput.val();
		var id=feeId;
		var mainId=descriptionInput.attr("data-mainId");
		var uploadData={id:id, mainId:mainId, currency:currency, feeType:feeType,
				priceType:priceType,
				recieveType:recieveType,rmbAmount:rmbAmount, 
				rmbAmountOri:rmbAmountOri, description:description};
		return uploadData;
	}
	
	function validateFee(feeId){
		var uploadData=getFee(feeId);
		if(uploadData.currency !=0 ){
			popTip("货币类型只支持RMB人民币！");
			return false;
		}
		if(uploadData.feeType=='' || uploadData.feeType==null){
			popTip("还没有选择费用分摊方式！");
			return false;
		}
		
		if(uploadData.recieveType=='' || uploadData.recieveType==null){
			popTip("没有选择费用收取方式！");
			return false;
		}
		if(uploadData.priceType=='' || uploadData.priceType==null){
			popTip("没有选择定价类型！");
			return false;
		}
		
		if(uploadData.rmbAmount){
			if(uploadData.rmbAmount<0){
				popTip("报名费用不能小于0！");
				return false;
			}
		}
		
		if(uploadData.rmbAmountOri){
			if(uploadData.rmbAmountOri<0){
				popTip("原价不能小于0！");
				return false;
			}
		}
		if(uploadData.description){
			if(uploadData.description.length>512){
				popTip("费用说明超长了,最大512字符！");
				return false;
			}
		}
		return true;
	}
	
	function validateTime(){
		var actBegin=$("#id_picker_actBegin").val();
		var actEnd=$("#id_picker_actEnd").val();
		var offTimeStr=$("#id_picker_offTime").val();
		if(!actBegin){
			popTip("活动起止时间要设置【开始时间为空】！");
			return false;
		}
		if(!actEnd){
			popTip("活动起止时间要设置【结束时间为空】！");
			return false;
		}
		if(!offTimeStr){
			popTip("活动截止报名时间要设置【截止报名时间为空】！");
			return false;
		}
		var beginTime=parseDate(actBegin,'yyyy-MM-dd HH:mm');
		var endTime=parseDate(actEnd,'yyyy-MM-dd HH:mm');
		var offTime=parseDate(offTimeStr,'yyyy-MM-dd HH:mm');
		if(beginTime>endTime){
			popTip("活动起止报名时间设置有误【开始时间不能大于结束时间】！");
			return false;
		}
		if(offTime>endTime){
			popTip("活动截止报名时间设置有误【截止报名时间不能大于结束时间】！");
			return false;
		}
		return true;
	}
	
	
	function findTemplateForCommon(fieldId){
		var fieldInput=$("#"+fieldId);
		var positionId=fieldInput.attr("data-position");
		var templateId=fieldInput.attr("data-template");
		var entityId=fieldInput.attr("data-entityId");
		var templateInput=$("#"+positionId);
		
		var templateLoaded=templateInput.find("div[id^='id_data_list_activity']");
		var data_saved=fieldInput.attr("data-saved");
		//发送请求，加载数据
		if(data_saved !=1 && templateLoaded.length<1){
			fieldInput.attr("data-saved",1);
			var url=fieldInput.attr("data-url");
			var uploadData={ 'position' : positionId, 'template' : templateId, 'id':entityId};
			axios({
			    method: 'post',
			    url: url,
			    data:Qs.stringify(uploadData)
			})
			.then(function (response) {
				fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
				var content=response.data;
				console.log(content);
				templateInput.html(content);
			})
			.catch(function (error) {
			    console.log(error);
			    fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
			});
		}
	}
	
	function findTemplatePageForSelect(fieldId){
		var fieldInput=$("#"+fieldId);
		var dataWrapperId=fieldInput.attr("data-wrapperId");
		var dataWrapper=$("#"+dataWrapperId);
		var templateId=fieldInput.attr("data-template");
		var positionId=fieldInput.attr("data-position");
		var entityId=fieldInput.attr("data-entityId");
		var templateInput=$("#"+positionId);
		var content=null;
		if(dataWrapper.length>0){
			//1.请求数据，进行填充
			var templateLoaded=templateInput.find("div[id^='id_data_list_activity']");
			var data_saved=fieldInput.attr("data-saved");
			//if(templateLoaded.length>0){
				//已加载，直接将加载数据展示即可，无需重发请求
				showTemplate(dataWrapperId, entityId);
				console.log("----template has loaded ----");
			//}
		/*else{
				//发送请求，加载数据
				if(data_saved !=1){
					fieldInput.attr("data-saved",1);
					var url=fieldInput.attr("data-url");
					var uploadData={ 'position' : positionId, 'template' : templateId, 'id':entityId};
					axios({
					    method: 'post',
					    url: url,
					    data:Qs.stringify(uploadData)
					})
					.then(function (response) {
						fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
						content=response.data;
						console.log(content);
						templateInput.html(content);
						showTemplate(dataWrapperId);
					})
					.catch(function (error) {
					    console.log(error);
					    fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
					});
				}
			}*/
		}
		
	}
	function showTemplate(dataWrapperId,entityId){
		var dataWrapper=$("#"+dataWrapperId);
		var content=dataWrapper.html();
		BootstrapDialog.show({
			id:"id_dialog_showTemplate"+entityId,
			title: "选择数据模板",
	    	message: $('<div id="id_showTemplate"></div>').html(content),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '取消',
                cssClass : "btn-default",
                action: function(dialog){
                	dialog.close();
                }
            }],
            onhide:function(){
            	var content = $("#id_showTemplate").html();
            	dataWrapper.html(content);
            },
            onshown:function(){
            	$("#id_showTemplate").html(content);
            	dataWrapper.html('');
            	
            	//var entityId=fieldInput.attr("data-entityId");
            	var fieldId="id_a_template_user"+entityId;
            	findTemplateForCommon(fieldId);//触发加载
            }
		});
	}
	
	function loadTemplate(fieldId){
		var fieldInput=$("#"+fieldId);
		var templateId=fieldInput.attr("data-templateId");
		var entityId=fieldInput.attr("data-entityId");
		
		var data_saved=fieldInput.attr("data-saved");
		var showTemplateId=fieldInput.attr("data-showTemplate");
		var showTipId=fieldInput.attr("data-showTip");
		var showTip=$("#"+showTipId);
		//发送请求，加载数据
		if(data_saved !=1 && showTip.length>0){
			fieldInput.attr("data-saved",1);
			var url=fieldInput.attr("data-url");
			var uploadData={ 'templateId' : templateId, 'id':entityId};
			axios({
			    method: 'post',
			    url: url,
			    data:Qs.stringify(uploadData)
			})
			.then(function (response) {
				fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
				var content=response.data;
				console.log(content);
				showTip.html(content);
				
				var successInput=showTip.find("input[name='success']");
				var msgInput=showTip.find("input[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					if(successVal==1){
						console.log("模板已载入");
						var msg=msgInput.val();
						layer.msg(msg);
						var dialog=BootstrapDialog.getDialog(showTemplateId);
						if(dialog){
							dialog.close();
						}
						window.location.href=window.location.href;
					}else{
						var msg=msgInput.val();
						layer.msg(msg);
					}
				}
				
			})
			.catch(function (error) {
			    console.log(error);
			    fieldInput.attr("data-saved",0);//返回后，可以再次执行保存
			});
		}
	}

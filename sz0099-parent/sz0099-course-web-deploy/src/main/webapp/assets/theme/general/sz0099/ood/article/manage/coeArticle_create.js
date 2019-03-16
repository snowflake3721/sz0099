	console.log("!!!in create!!!!!!!!!");
	
	var paragraphLoaded=false;
	function loadData(panelName, url){
		var articleId = $("#id_hidden_id").val();
		if(panelName=="#contentPhoto" && !paragraphLoaded){
			//加载段落数据
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/editListUI',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({
		    	    	'mainId' : articleId
	    	    	})
	    	    
	    	})
	    	.then(function (response) {
	    	    console.log("---- photo loaded----");
	    	    $("#id_contentPhoto_content"+articleId).html(response.data);
	    	    var currentParagId= $("#id_paragId_current").val();
	    	    //initParagraph(currentParagId); TODO 仅初始化当前这个
	    	    initParagraph(articleId);//将所有file组件全都初始化一遍
	    	    paragraphLoaded=true;
	    	   
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	});
		
		}
	}
	
	function refreshParagraph(articleId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
	}
	
	function refreshParagraphOrder(articleId, url, panelName){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = panelName;
		currentPanel.val(panelName);
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
	}
	
	function validateBaseinfo(articleId, messageDivId){
		
		var messageTipBaseinfoId = messageDivId+articleId;
  		var messageTipBaseinfo=$("#"+messageTipBaseinfoId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		var uploadData=getEntity(articleId);
  		
  		//title
  		var titleId="id_title"+articleId;
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
  		var descriptionId = "id_area_description"+articleId;
  		var descriptionChecked = validateEmpty(descriptionId, "文章概要不能为空", messageTipBaseinfoId);
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
  		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	
	
	function commitSingle(btnId,panelIdHidden, articleId){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		btn.removeClass("hidden");
  		
  		
  		var panel = $("#"+panelIdHidden);
  		var panelId=panel.val();
  		var url = panel.attr("data-saveUrl");
  		
  		if(panelId=="#panel_baseinfo"){
  			commitBaseinfo(btnId, articleId, url);
  			//return ;
  		}else if(panelId=="#panel_price"){
  			commitPrice(btnId, articleId, url);
  			//return ;
  		}else if(panelId=="#contentPhoto"){
  			btn.addClass("hidden");
  		}else if(panelId=="#contentTag"){
  			btn.addClass("hidden");
  		}
  		
  		btn.removeAttr("disabled");
	}
	
	function commitBaseinfo(btnId, articleId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var idMessageTipBaseinfoId="id_messageTip_baseinfo"+articleId;
  		
		var checked = validateBaseinfo(articleId, idMessageTipBaseinfoId);
		var uploadData=getEntity(articleId);
		if(checked){
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/merge/baseinfo',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData
	    	    			/*{
	    	    		uploadData
		    	    	'name' : uploadData.name,
		    	    	'id' : articleId,
		    	    	'description' : uploadData.description
	    	    		}*/
	    	    			)
	    	    
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
	    	   
	      		
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	    informUpdate(idMessageTipBaseinfoId);
	    	});
		}
		
	}
	
	function validatePrice(articleId){
		var messageTipPriceId = "id_messageTip_price"+articleId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
		messageTipPrice.addClass("text-danger");
		
  		messageTipPrice.html("标题设置验证通过...");
  		messageTipPrice.removeClass("text-danger");
  		messageTipPrice.addClass("text-success");
  		
  		return true;
	}
	
	function commitPrice(btnId, articleId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var id_hidden=$("#id_hidden_id");
  		var id = id_hidden.val();
  		
  		var messageTipPriceId = "id_messageTip_price"+articleId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
  		
  		var uploadData=getEntity(articleId);
		var checked = validatePrice(articleId);
		if(checked){
			
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/merge/price',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "更新标题信息",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipPriceId);
	                    	dialog.close();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipPriceId);
	                    	dialog.close();
	                    }
	                }]
	    		});
	    	   
	      		
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	    informUpdate(messageTipPriceId);
	    	});
		}
		
		
	}
	
	//保存单个段落
	function saveParagraphSingle(paragId, url){
		var articleId = $("#id_hidden_id").val();
		
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
		
		var messageTipId="id_messageTip_paragraph"+articleId;
		/*console.log(paragIdK + ": " + paragIdV);
		console.log(nameK + ": " + nameV);
		console.log(titleK + ": " + titleV);
		console.log(descriptionK + ": " + descriptionV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(paragProductIdK + ": " + paragProductIdV);
		console.log("articleId" + ": " + articleId);*/
		var checked = validateParagraphSingleParam(paragId);
		var uploadData={
	    		"paragraph.name" : nameV,
	    		"paragraph.title" : titleV,
	    		"paragraph.description" : descriptionV,
	    		"orderSeq" : orderSeqV,
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : articleId,
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
		
		var articleId = $("#id_hidden_id").val();
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){
			validateSessionInvalid("id_messageTip_paragraph","！！当前会话已失效，请重新登录，再操作！！");
			return false;
		}
		
		if(!articleId){
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
		
		var articleId = $("#id_hidden_id").val();
		
		var paragIdInput=$("#id_paragId"+paragId);
		var paragIdV = paragIdInput.val();
		
		var uploadData={
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : articleId,
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
		var articleId = $("#id_hidden_id").val();
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){return false;}
		
		if(!articleId){
			return false;
		}
		return true;
	}
	
	//删除全部部落开始
	
	function deleteAllParagraph(articleId, url){
		var checked = validateDeleteAllParagData(articleId);
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
                		doDeleteAllParagraph(articleId, url);
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
	
	function doDeleteAllParagraph(articleId, url){
		var messageTipId = "id_messageTip_paragraph";
		var uploadData={
	    		"mainId" : articleId,
	    	}
		var checked=validateDeleteAllParagData(articleId);
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
	                    	removeDeletedAllDivWrapper(articleId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(articleId);
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
	
	function removeDeletedAllDivWrapper(articleId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
   		if(successValue==1){
   			$("#id_contentPhoto_content"+articleId).empty();
   		}
		
	}
	
	function validateDeleteAllParagData(articleId){
		
		if(typeof articleId=='undefined'){return false;}
		var articleIdV = articleId;
		if(!articleId){
			return false;
		}
		
		if(''==articleId){
			articleIdV = $("#id_hidden_id").val();
		}
		
		if(articleIdV=='' || null==articleIdV){
			return false;
		}
		
		return true;
	}
	
	//删除全部段落结束
	
	
	//添加段落开始
	function addParagraphSingle(articleId, url){
		var messageTipId = "id_messageTip_paragraph";
		var articleIdV = null;
		if(typeof articleId !='undefined'){
			articleIdV = articleId;
		}
		
		if(''==articleIdV || null==articleIdV){
			articleIdV = $("#id_hidden_id").val();
		}
		
		if(articleIdV=='' || null==articleIdV ){
			return false;
		}
		
		var uploadData={
	    		"mainId" : articleId
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
    	    $("#id_contentPhoto_content"+articleId).append(response.data);
    	    var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    }
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	informUpdate(messageTipId);
	    	});
    	    //初始化file组件
    	    initParagraph(articleId);
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
	
	
	function getEntity(articleId){
		var titleId="id_title"+articleId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var nameId = "id_name"+articleId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var descriptionId = "id_area_description"+articleId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		
  		var preIntroId = "id_preIntro"+articleId;
  		var preIntroInput=$("#"+preIntroId);
  		var preIntro = "无";
  		if(preIntroInput.length>0){
  			preIntro = preIntroInput.val();
  		}
  		
  		var subTitleId = "id_subTitle"+articleId;
  		var subTitleInput = $("#"+subTitleId);
  		var subTitle = subTitleInput.val();
  		
  		var pennameId = "id_penname"+articleId;
  		var pennameInput = $("#"+pennameId);
  		var penname = pennameInput.val();
  		
  		var preIntroTypeInput = $("#hidden_id_preIntroType"+articleId);
  		var preIntroType = 0;
  		if(preIntroTypeInput.length >0 ){
  			preIntroType = preIntroTypeInput.val();
  		}
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"description" : description,
  			"subTitle" : subTitle,
  			"penname" : penname,
  			"preIntroType" : preIntroType,
  			"preIntro" : preIntro,
  			"id" : articleId
  		}
  		return data;
	}
	
	function mergeForPublish(articleId, url){
		
		var messageTipId = "id_messageTip_publish";
		
		var baseinfoChecked = validateBaseinfo(articleId, messageTipId);
		if(!baseinfoChecked){
			return false;
		}
		var uploadData=getEntity(articleId);
		axios({
    	    method: 'post',
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

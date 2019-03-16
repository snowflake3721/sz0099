	console.log("!!!in create!!!!!!!!!");
	
	var contentPhotoLoaded=false;
	var contentPriceLoaded=false;
	function loadData(panelName, wrapperId, url){
		var baseId = $("#id_hidden_id").val();
		console.log("---- photo preload 1----"+baseId);
		var positionId = $("#id_hidden_id").attr("data-positionId");
		var mainId = $("#id_hidden_id").attr("data-mainId");
		var subId =  $("#id_hidden_id").attr("data-subId");
		console.log("---- photo preload 1----" + positionId);
		if(panelName=="#contentPhoto" && !contentPhotoLoaded){
			console.log("---- photo preload 2----" + positionId);
			//加载段落数据
			send(baseId, positionId,mainId,subId, url, wrapperId, panelName);
		}else if(panelName=="#panel_position_ref_bind" && !contentPriceLoaded){
			console.log("---- photo preload 3----"+positionId);
			//加载绑定数据
			send(baseId, positionId,mainId,subId, url, wrapperId, panelName);
		}
		
	}
	
	function send(baseId, positionId, mainId, subId, url, wrapperId, panelName){
		axios({
    	    method: 'post',
    	    url: url,
    	    data: 
    	    	Qs.stringify({
    	    		'baseId':baseId,//技能id
	    	    	'positionId' : positionId, //所属adaptor处理id
	    	    	'mainId' : mainId,//平台id
	    	    	'subId' : subId //平台id
    	    	})
    	    
    	})
    	.then(function (response) {
    	    //
    	    console.log("---- photo loaded----");
    	    $("#"+wrapperId).html(response.data);
    	    //var currentParagId= $("#id_paragId_current").val();
    	    //initParagraph(currentParagId); TODO 仅初始化当前这个
    	    initParagraph(baseId);//将所有file组件全都初始化一遍
    	    
    	    //状态置为已加载
    	    if(panelName=="#contentPhoto"){
    	    	contentPhotoLoaded=true;
    	    	initParagraph(professionId);//将所有file组件全都初始化一遍
    	    }else if(panelName=="#panel_position_ref_bind"){
    	    	contentPriceLoaded=true;
    	    }
    	   
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
	
	function refreshParagraph(professionId, wrapperId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		var panelName=currentPanelValue;
		if(panelName=="#contentPhoto"){
	    	contentPhotoLoaded=false;
	    }else if(panelName=="#panel_position_ref_bind"){
	    	contentPriceLoaded=false;
	    }
		loadData(currentPanelValue, wrapperId, url);
	}
	
	function getEntity(professionId){
			var titleId="id_title"+professionId;
			var titleInput=$("#"+titleId);
	  		var title = titleInput.val();
	  		
	  		var nameId = "id_name"+professionId;
	  		var nameInput = $("#"+nameId);
	  		var name = nameInput.val();
	  		
	  		var descriptionId = "id_area_description"+professionId;
	  		var descriptionInput = $("#"+descriptionId);
	  		var description = descriptionInput.val();
	  		
	  		var preIntro = "无";
	  		var preIntroId = "id_preIntro"+professionId;
	  		var preIntroInput=$("#"+preIntroId);
	  		if(preIntroInput.length>0){
	  			preIntro = preIntroInput.val();
	  		}
	  		
	  		var subTitleId = "id_subTitle"+professionId;
	  		var subTitleInput = $("#"+subTitleId);
	  		var subTitle = subTitleInput.val();
	  		
	  		var pennameId = "id_penname"+professionId;
	  		var pennameInput = $("#"+pennameId);
	  		var penname = pennameInput.val();
	  		
	  		var preIntroType = 0;
	  		var preIntroTypeInput = $("#hidden_id_preIntroType"+professionId);
	  		if(preIntroTypeInput.length>0){
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
	  			"id" : professionId
	  		}
	  		return data;
		}
	
	
	
	function validateBaseinfo(professionId){
		
		var messageTipBaseinfoId = "id_messageTip_baseinfo"+professionId;
  		var messageTipBaseinfo=$("#"+messageTipBaseinfoId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		var titleId="id_title"+professionId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var descriptionId = "id_area_description"+professionId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		
  		//title
  		var titleChecked = validateEmpty(titleId, "标题不能为空", messageTipBaseinfoId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "标题太长，都超32了", 32, messageTipBaseinfoId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "标题含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		/*var nameChecked = validateEmpty(nameId, "名称不能为空", messageTipBaseinfoId);
  		if(!nameChecked){
  			return false;
  		}*/
  		
  		var  nameId = "id_name"+professionId;
  		var nameInput = $("#"+nameId);
  		if(nameInput.length>0){
	  		var name = nameInput.val();
	  		var nameLengthChecked = validateLength(nameId, "名称太长，都超32了", 32, messageTipBaseinfoId);
	  		if(!nameLengthChecked){
	  			return false;
	  		}
  		}
  		
  		/*var nameScriptChecked = validateScript(nameId, "名称含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!nameScriptChecked){
  			return false;
  		}*/
  		
  		//description
  		var descriptionChecked = validateEmpty(descriptionId, "概要不能为空", messageTipBaseinfoId);
  		if(!descriptionChecked){
  			return false;
  		}
  		
  		var descriptionLengthChecked = validateLength(descriptionId, "概要太长，都超1000了", 1000, messageTipBaseinfoId);
  		if(!descriptionLengthChecked){
  			return false;
  		}
  		
  		var descriptionScriptChecked = validateScript(descriptionId, "概要含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!descriptionScriptChecked){
  			return false;
  		}
  		
  		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	
	function commitSingle(btnId,panelIdHidden, professionId){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		btn.removeClass("hidden");
  		
  		
  		var panel = $("#"+panelIdHidden);
  		var panelId=panel.val();
  		var url = panel.attr("data-saveUrl");
  		
  		if(panelId=="#panel_baseinfo"){
  			commitBaseinfo(btnId, professionId, url);
  			//return ;
  		}else if(panelId=="#panel_position_ref_bind"){
  			commitPrice(btnId, professionId, url);
  			//return ;
  		}else if(panelId=="#contentPhoto"){
  			btn.addClass("hidden");
  		}else if(panelId=="#contentTag"){
  			btn.addClass("hidden");
  		}
  		
  		btn.removeAttr("disabled");
  		
  		
	}
	
	function commitBaseinfo(btnId, professionId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var idMessageTipBaseinfoId="id_messageTip_baseinfo"+professionId;
  		
  		console.log("---professionId----" + professionId);
		var checked = validateBaseinfo(professionId);
		var uploadData=getEntity(professionId);
		console.log("---commitBaseinfo()----" + checked);
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
	    	   
	      		
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	    informUpdate(idMessageTipBaseinfoId);
	    	});
		}
		
	}
	
	function validatePrice(professionId){
		var messageTipPriceId = "id_messageTip_price"+professionId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
		messageTipPrice.addClass("text-danger");
  		
		
  		
  		messageTipPrice.html("技能设置验证通过...");
  		messageTipPrice.removeClass("text-danger");
  		messageTipPrice.addClass("text-success");
  		
  		return true;
	}
	
	function commitPrice(btnId, professionId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var id_hidden=$("#id_hidden_id");
  		var id = id_hidden.val();
  		
  		
  		
  		var messageTipPriceId = "id_messageTip_price"+professionId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
  		
  		
  		console.log("---id----" + id);
		var checked = validatePrice(professionId);
		console.log("---commitPrice()----" + checked);
		
		
		
	}
	
	//保存单个段落
	function saveParagraphSingle(paragId, url){
		var professionId = $("#id_hidden_id").val();
		
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
		
		var messageTipId="id_messageTip_paragraph"+professionId;
		
		console.log(paragIdK + ": " + paragIdV);
		console.log(nameK + ": " + nameV);
		console.log(titleK + ": " + titleV);
		console.log(descriptionK + ": " + descriptionV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(paragProductIdK + ": " + paragProductIdV);
		console.log("professionId" + ": " + professionId);
		
		
		var checked = validateParagraphSingleParam(paragId);
		console.log("checked" + ": " + checked);
		var uploadData={
	    		"paragraph.name" : nameV,
	    		"paragraph.title" : titleV,
	    		"paragraph.description" : descriptionV,
	    		"orderSeq" : orderSeqV,
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : professionId,
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
		
		var professionId = $("#id_hidden_id").val();
		console.log("....validateParagraphSingleParam: paragId --> "+ paragId);
		console.log("....validateParagraphSingleParam: professionId --> "+ professionId);
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){
			console.log("!!! error for paragId : "+ paragId + "  >>>!!!");
			validateSessionInvalid("id_messageTip_paragraph","！！当前会话已失效，请重新登录，再操作！！");
			return false;
		}
		
		if(!professionId){
			console.log("!!! error for professionId : "+ professionId + "  >>>!!!");
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
  		var titleLengthChecked = validateLength("id_parag_title"+paragId, "段落标题太长，都超32字符了", 32, messageTipId);
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
		
		var professionId = $("#id_hidden_id").val();
		
		var paragIdInput=$("#id_paragId"+paragId);
		var paragIdV = paragIdInput.val();
		
		var uploadData={
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : professionId,
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
 	    console.log("---successValue:" + successValue);
   		if(successValue==1){
   			console.log("---successValue==1:" + successValue==1);
   			$("#paragragh_"+paragProductIdV).remove();
   		}
		
	}
	
	function validateDeleteParagData(paragId){
		var professionId = $("#id_hidden_id").val();
		console.log("....validateParagraphSingleParam: paragId --> "+ paragId);
		console.log("....validateParagraphSingleParam: professionId --> "+ professionId);
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){return false;}
		
		if(!professionId){
			console.log("!!! error for professionId : "+ professionId + "  >>>!!!");
			return false;
		}
		
		return true;
	}
	
	
	
	//删除全部部落开始
	
	function deleteAllParagraph(professionId, url){
		var checked = validateDeleteAllParagData(professionId);
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
                		doDeleteAllParagraph(professionId, url);
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
	
	function doDeleteAllParagraph(professionId, wrapperId, url){
		var messageTipId = "id_messageTip_paragraph";
		var uploadData={
	    		"mainId" : professionId,
	    	}
		
		var checked=validateDeleteAllParagData(professionId);
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
	                    	removeDeletedAllDivWrapper(professionId, wrapperId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(professionId, wrapperId);
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
	
	function removeDeletedAllDivWrapper(professionId, wrapperId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
 	    console.log("---successValue:" + successValue);
   		if(successValue==1){
   			console.log("---successValue==1:" + successValue==1);
   			$("#"+wrapperId).empty();
   		}
		
	}
	
	function validateDeleteAllParagData(professionId){
		
		console.log("....validateDeleteAllParagData: professionId --> "+ professionId);
		
		if(typeof professionId=='undefined'){return false;}
		var professionIdV = professionId;
		if(!professionId){
			console.log("!!! error for professionId : "+ professionId + "  >>>!!!");
			return false;
		}
		
		if(''==professionId){
			professionIdV = $("#id_hidden_id").val();
		}
		
		if(professionIdV=='' || null==professionIdV){
			return false;
		}
		
		return true;
	}
	
	//删除全部段落结束
	
	
	//添加段落开始
	function addParagraphSingle(professionId, wrapperId, url){
		console.log("....addParagraphSingle: professionId --> "+ professionId);
		var messageTipId = "id_messageTip_paragraph";
		var professionIdV = null;
		if(typeof professionId !='undefined'){
			professionIdV = professionId;
		}
		
		if(''==professionIdV || null==professionIdV){
			professionIdV = $("#id_hidden_id").val();
		}
		
		if(professionIdV=='' || null==professionIdV ){
			return false;
		}
		
		var uploadData={
	    		"mainId" : professionId
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
    	    $("#"+wrapperId).append(response.data);
    	    var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    }
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	informUpdate(messageTipId);
	    	});
    	    //初始化file组件
    	    initParagraph(professionId);
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
	
	
	
	function mergeForPublish(professionId){
		
		var baseinfoChecked = validateBaseinfo(professionId);
		if(!baseinfoChecked){
			return false;
		}
		
		var messageTipId = "id_messageTip_publish";
		
		var uploadData=getEntity(professionId);
		
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/personal/profession/manage/merge/publish',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    	    
    	    var respMsg=$('<div></div>').html(response.data);
    	    //$("#id_p_tags"+professionId).append(response.data);
    	    
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

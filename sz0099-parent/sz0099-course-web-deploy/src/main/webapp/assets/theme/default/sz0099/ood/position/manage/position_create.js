	console.log("!!!in create!!!!!!!!!");
	
	var contentPhotoLoaded=false;
	var contentPriceLoaded=false;
	function loadData(panelName, wrapperId, url){
		var extendId = $("#id_hidden_id").val();
		var mainId = $("#id_hidden_id").attr("data-mainId");
		var subId =  $("#id_hidden_id").attr("data-subId");
		console.log("---- photo preload 1----"+extendId);
		if(panelName=="#contentPhoto" && !contentPhotoLoaded){
			console.log("---- photo preload 2----"+extendId);
			//加载段落数据
			send(extendId,mainId,subId, url, wrapperId, panelName);
		}else if(panelName=="#panel_position_ref_bind" && !contentPriceLoaded){
			console.log("---- photo preload 3----"+extendId);
			//加载绑定数据
			send(extendId,mainId,subId, url, wrapperId, panelName);
		}
	}
	function send(extendId,mainId,subId, url, wrapperId, panelName){
		axios({
    	    method: 'post',
    	    //url: '/sz0099/ood/product/manage/paragraph/editListUI',
    	    url: url,
    	    data: 
    	    	Qs.stringify({
	    	    	'extendId' : extendId,
	    	    	'mainId' : mainId,
	    	    	'subId' : subId
    	    	})
    	    
    	})
    	.then(function (response) {
    	    //
    	    console.log("---- photo loaded----");
    	    $("#"+wrapperId).html(response.data);
    	    //var currentParagId= $("#id_paragId_current").val();
    	    //initParagraph(currentParagId); TODO 仅初始化当前这个
    	    initParagraph(extendId);//将所有file组件全都初始化一遍
    	    
    	    //状态置为已加载
    	    if(panelName=="#contentPhoto"){
    	    	contentPhotoLoaded=true;
    	    }else if(panelName=="#panel_position_ref_bind"){
    	    	contentPriceLoaded=true;
    	    }
    	   
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
	
	function refresh(panelName, wrapperId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		if(panelName=="#contentPhoto"){
	    	contentPhotoLoaded=false;
	    }else if(panelName=="#panel_position_ref_bind"){
	    	contentPriceLoaded=false;
	    }
		loadData(currentPanelValue, wrapperId, url);
	}
	
	
	
	
	
	function validateBaseinfo(entityId){
		
		var messageTipBaseinfoId = "id_messageTip_baseinfo"+entityId;
  		var messageTipBaseinfo=$("#"+messageTipBaseinfoId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		var devId="id_devId"+entityId;
		var devInput=$("#"+devId);
  		var dev = devInput.val();
  		
  		//dev
  		var devChecked = validateEmpty(devId, "开发者id不能为空", messageTipBaseinfoId);
  		if(!devChecked){
  			return false;
  		}
  		
  		var devLengthChecked = validateLength(devId, "开发者id太长，都超6了", 6, messageTipBaseinfoId);
  		if(!devLengthChecked){
  			return false;
  		}
  		
  		var devScriptChecked = validateOnlyLetterDigit(devId, "开发者id含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!devScriptChecked){
  			return false;
  		}
  		
  		var projectId = "id_project"+entityId;
  		var projectInput = $("#"+projectId);
  		var project = projectInput.val();
  		//project
  		var projectChecked = validateEmpty(projectId, "项目不能为空", messageTipBaseinfoId);
  		if(!projectChecked){
  			return false;
  		}
  		
  		var projectLengthChecked = validateLength(projectId, "项目太长，都超15了", 15, messageTipBaseinfoId);
  		if(!projectLengthChecked){
  			return false;
  		}
  		
  		var projectScriptChecked = validateOnlyLetterDigit(projectId, "项目含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!projectScriptChecked){
  			return false;
  		}
  		
  		
  		var moduleId = "id_module"+entityId;
  		var moduleInput = $("#"+moduleId);
  		var module = moduleInput.val();
  	//module
  		var moduleChecked = validateEmpty(moduleId, "模块不能为空", messageTipBaseinfoId);
  		if(!moduleChecked){
  			return false;
  		}
  		
  		var moduleLengthChecked = validateLength(moduleId, "模块太长，都超15了", 15, messageTipBaseinfoId);
  		if(!moduleLengthChecked){
  			return false;
  		}
  		
  		var moduleScriptChecked = validateOnlyLetterDigit(moduleId, "模块含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!moduleScriptChecked){
  			return false;
  		}
  		
  		var varietyId = "id_variety"+entityId;
  		var varietyInput = $("#"+varietyId);
  		var variety = varietyInput.val();
  	//variety
  		var varietyChecked = validateEmpty(varietyId, "品种不能为空", messageTipBaseinfoId);
  		if(!varietyChecked){
  			return false;
  		}
  		
  		var varietyLengthChecked = validateLength(varietyId, "品种太长，都超15了", 15, messageTipBaseinfoId);
  		if(!varietyLengthChecked){
  			return false;
  		}
  		
  		var varietyScriptChecked = validateCommonOnly(varietyId, "品种含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!varietyScriptChecked){
  			return false;
  		}
  		
  		
  		var positionId = "id_position"+entityId;
  		var positionInput = $("#"+positionId);
  		var position = positionInput.val();
  	//position
  		var positionChecked = validateEmpty(positionId, "位置不能为空", messageTipBaseinfoId);
  		if(!positionChecked){
  			return false;
  		}
  		
  		var positionLengthChecked = validateLength(positionId, "位置太长，都超15了", 15, messageTipBaseinfoId);
  		if(!positionLengthChecked){
  			return false;
  		}
  		
  		var positionScriptChecked = validateCommonOnly(positionId, "位置含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!positionScriptChecked){
  			return false;
  		}
  		
  		var domainId = "id_domain"+entityId;
  		var domainInput = $("#"+domainId);
  		var domain = domainInput.val();
  		//domain
  		var domainChecked = validateEmpty(domainId, "领域不能为空", messageTipBaseinfoId);
  		if(!domainChecked){
  			return false;
  		}
  		
  		var domainLengthChecked = validateLength(domainId, "领域太长，都超255了", 255, messageTipBaseinfoId);
  		if(!domainLengthChecked){
  			return false;
  		}
  		
  		var domainScriptChecked = validateDomainHttp(domainId, "领域含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!domainScriptChecked){
  			return false;
  		}
  		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	
	function getBaseinfo(entityId){
		var devId="id_devId"+entityId;
		var devInput=$("#"+devId);
  		var dev = devInput.val();
  		
		var projectId = "id_project"+entityId;
  		var projectInput = $("#"+projectId);
  		var project = projectInput.val();
  		
		var moduleId = "id_module"+entityId;
  		var moduleInput = $("#"+moduleId);
  		var module = moduleInput.val();
  		
		var varietyId = "id_variety"+entityId;
  		var varietyInput = $("#"+varietyId);
  		var variety = varietyInput.val();
  		
		var positionId = "id_position"+entityId;
  		var positionInput = $("#"+positionId);
  		var position = positionInput.val();
  		
		var domainId = "id_domain"+entityId;
  		var domainInput = $("#"+domainId);
  		var domain = domainInput.val();
  		
  		var mainMaxnumId = "id_mainMaxnum"+entityId;
  		var mainMaxnumInput = $("#"+mainMaxnumId);
  		var mainMaxnum = mainMaxnumInput.val();
  		
  		var subMaxnumId = "id_subMaxnum"+entityId;
  		var subMaxnumInput = $("#"+subMaxnumId);
  		var subMaxnum = subMaxnumInput.val();
  		
  		var refMaxnumId = "id_refMaxnum"+entityId;
  		var refMaxnumInput = $("#"+refMaxnumId);
  		var refMaxnum = refMaxnumInput.val();
  		
  		var id_hidden=$("#id_hidden_id");
  		var id = id_hidden.val();
  		
			var data = {
		    	'devId' : dev,
		    	'project' : project,
		    	'variety' : variety,
		    	'position' : position,
		    	'domain' : domain,
		    	'id' : id,
		    	'module' : module,
		    	'mainMaxnum' : mainMaxnum,
		    	'subMaxnum' : subMaxnum,
		    	'refMaxnum' : refMaxnum
	    	};
			return data;
		}
	
	
	function commitSingle(btnId,panelIdHidden, productId){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		btn.removeClass("hidden");
  		
  		
  		var panel = $("#"+panelIdHidden);
  		var panelId=panel.val();
  		var url = panel.attr("data-saveUrl");
  		
  		if(panelId=="#panel_baseinfo"){
  			commitBaseinfo(btnId, productId, url);
  			//return ;
  		}else if(panelId=="#panel_position_ref_bind"){
  			btn.addClass("hidden");
  			//commitPrice(btnId, productId, url);
  			//return ;
  		}else if(panelId=="#contentPhoto"){
  			btn.addClass("hidden");
  		}else if(panelId=="#contentTag"){
  			btn.addClass("hidden");
  		}
  		
  		btn.removeAttr("disabled");
	}
	
	
	function commitBaseinfo(btnId, entityId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		var idMessageTipBaseinfoId="id_messageTip_baseinfo"+entityId;
		var checked = validateBaseinfo(entityId);
		console.log("---commitBaseinfo()----" + checked);
		var uploadData=getBaseinfo(entityId);
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
	
	function validatePrice(productId){
		var messageTipPriceId = "id_messageTip_price"+productId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
		messageTipPrice.addClass("text-danger");
  		
		
  		
  		messageTipPrice.html("价格设置验证通过...");
  		messageTipPrice.removeClass("text-danger");
  		messageTipPrice.addClass("text-success");
  		
  		return true;
	}
	
	function commitPrice(btnId, productId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var id_hidden=$("#id_hidden_id");
  		var id = id_hidden.val();
  		
  		var messageTipPriceId = "id_messageTip_price"+productId;
		var messageTipPrice=$("#"+messageTipPriceId);
		messageTipPrice.html("");
  		
  		
  		console.log("---id----" + id);
		var checked = validatePrice(productId);
		console.log("---commitPrice()----" + checked);
		if(checked){
			
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/merge/price',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({
		    	    	'id' : id
	    	    	})
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "产品价格设置结果",
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
	

	//显示操作说明
	function showInstruction(divId,title){
		var div_showInstruction = $("#"+divId);
		console.log(">>>> in showInstruction 1-----" + divId);
		var content = div_showInstruction.html();
		//console.log(content);
		BootstrapDialog.show({
			title: title,
	    	message:  $('<div></div>').html(content),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定',
                cssClass : "btn-primary",
                action: function(dialog){
                	dialog.close();
                }
            }, {
                label: '关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                    	dialog.close();
                	}
	            }]
		  });
  	}
	
	
	
	//修改显示文字
	function onChangeShow(id, value, length){
		if(typeof value =='undefined'){
			$("#"+id).html(value);
		}
		if(typeof length =='undefined'){
			$("#"+id).html(value);
		}
		if(length>0 && ''!=value && value.length>0){
			var vl = value.length;
			if(vl>length){
				var nv = value.substring(0,length)+"...";
				$("#"+id).html(nv);
				$("#"+id+"_col").html(nv);
			}else{
				$("#"+id).html(value);
				$("#"+id+"_col").html(value);
			}
		}
		
	}
	
	
	function getPosition(entityId){
		var extendId = $("#id_hidden_id").val();
		var mainId=$("#id_hidden_id").attr("data-mainId");
		//var subId=$("#id_hidden_id").attr("data-subId");
		
		var subIdInput=$("#hidden_id_position_subId"+entityId);
		var layoutInput=$("#hidden_id_position_layout"+entityId);
		var statusInput=$("#hidden_id_position_status"+entityId);
		var panelInput=$("#hidden_id_position_panel"+entityId);
		var nameInput=$("#id_position_name"+entityId);
		//var topLevelInput=$("#id_position_topLevel"+entityId);
		var orderSeqInput=$("#id_position_orderSeq"+entityId);
		var positionIdInput=$("#id_position_positionId"+entityId);
		var titleInput=$("#id_position_title"+entityId);
		var descriptionInput=$("#id_position_description"+entityId);
		var linkInput=$("#id_position_link"+entityId);
		
		
		var layoutInputK = layoutInput.attr("name");
		var layoutV = layoutInput.val();
		
		var panelInputK = panelInput.attr("name");
		var panelV = panelInput.val();
		
		var statusInputK = statusInput.attr("name");
		var statusV = statusInput.val();
		
		var subIdInputK = subIdInput.attr("name");
		var subIdV = subIdInput.val();
		
		var nameK = nameInput.attr("name");
		var nameV = nameInput.val();
		
		//var topLevelK = topLevelInput.attr("name");
		//var topLevelV = topLevelInput.val();
		
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
		
		var positionIdK = positionIdInput.attr("name");
		var positionIdV = positionIdInput.val();
		
		var titleK = titleInput.attr("name");
		var titleV = titleInput.val();
		
		var descriptionK = descriptionInput.attr("name");
		var descriptionV = descriptionInput.val();
		
		var linkK = linkInput.attr("name");
		var linkV = linkInput.val();
		
		
		console.log(nameK + ": " + nameV);
		//console.log(topLevelK + ": " + topLevelV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(positionIdK + ": " + positionIdV);
		console.log("extendId" + ": " + extendId);
		
		var position={
	    		"name" : nameV,
	    		"orderSeq" : orderSeqV,
	    		"positionId": positionIdV,
	    		"extendId" : extendId,
	    		"mainId" : mainId,
	    		"subId" : subIdV,
	    		"status" : statusV,
	    		"layout" : layoutV,
	    		"panel" : panelV,
	    		"title" : titleV,
	    		"description" : descriptionV,
	    		"link" : linkV,
	    		"id":entityId
	    	}
		return position;
	}
	
	/**
	 * 保存单个位置
	 * @param entityId  位置自身id
	 * @param url
	 * @returns
	 */
	function saveSingle(entityId, url, messageTipId){
		
		var checked = validateSingleParam(entityId, messageTipId);
		console.log("checked" + ": " + checked);
		var uploadData=getPosition(entityId);
		
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
	    			title: "保存位置结果",
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
	
	
	function validateSingleParam(entityId, messageTipId){
		
		var extendId = $("#id_hidden_id").val();
		console.log("....validateSingleParam: entityId --> "+ entityId);
		console.log("....validateSingleParam: extendId --> "+ extendId);
		
		if(typeof entityId=='undefined'){return false;}
		if(!entityId){
			console.log("!!! error for entityId : "+ entityId + "  >>>!!!");
			validateSessionInvalid(messageTipId,"！！当前会话已失效，请重新登录，再操作！！");
			return false;
		}
		
		if(!extendId){
			console.log("!!! error for extendId : "+ extendId + "  >>>!!!");
			return false;
		}
		
		//var messageTipId = "id_messageTip_paragraph";
		var messageTipParagraph=$("#"+messageTipId);
		messageTipParagraph.html("");
		messageTipParagraph.addClass("text-danger");

		var orderSeqInput=$("#id_position_orderSeq"+entityId);
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
  		orderSeqV = checkOnlyNum(orderSeqV);
  		orderSeqInput.val(orderSeqV);
  		
  		//var topLevelInput=$("#id_position_topLevel"+entityId);
		//var topLevelK = topLevelInput.attr("name");
		//var topLevelV = topLevelInput.val();
		//topLevelV = checkOnlyNum(topLevelV);
		//topLevelInput.val(topLevelV);
		
		
		var layoutId="hidden_id_position_layout"+entityId;
  		var layoutChecked = validateEmpty(layoutId, "必须选择一个<layout>布局模板", messageTipId);
  		if(!layoutChecked){
  			return false;
  		}
  		
  		var subId="hidden_id_position_subId"+entityId;
  		var subIdChecked = validateEmpty(subId, "必须选择一个<页面>位置", messageTipId);
  		if(!subIdChecked){
  			return false;
  		}
  		
  		var nameId="id_position_name"+entityId;
  		var nameChecked = validateEmpty(nameId, "位置名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "位置名称太长，都超15字符了", 15, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		var nameScriptChecked = validateScript(nameId, "位置名称含有特殊字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		var titleId="id_position_title"+entityId;
  		
  		var titleChecked = validateEmpty(titleId, "标题不能为空", messageTipId);
  		if(!titleChecked){
  			return false;
  		}
  		var titleLengthChecked = validateLength(titleId, "标题太长，都超16字符了", 16, messageTipId);
	  		if(!titleLengthChecked){
	  			return false;
	  	}
  	
  		var titleScriptChecked = validateScript(titleId, "标题含有非法字符，自动过滤", messageTipId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		
  		
  		messageTipParagraph.html("位置内容验证通过...");
  		messageTipParagraph.removeClass("text-danger");
  		messageTipParagraph.addClass("text-success");
  		
  		return true;
	}
	
	function deleteSingle(entityId,url, messageTipId,divWrapperId){
		var checked = validateDeleteData(entityId);
		
		if(checked){
			var position = getPosition(entityId);
			var orderSeqV = position.orderSeq;
			
			var showV=position.name;
			if(showV==''){
				showV="编号为#"+orderSeqV;
			}
			//var messageTipId = "id_messageTip_paragraph";
			var tip="您确定要删除<span class='text-danger'>【"+showV+"】</span>的位置吗？删除后不可恢复，请谨慎操作！"
			BootstrapDialog.show({
				title: "删除确认？",
		    	message: $('<div></div>').html(tip),
		    	size: BootstrapDialog.SIZE_SMALL,
		    	buttons: [{
	                label: '确定删除',
	                cssClass : "btn-danger",
	                action: function(dialog){
	                	if(checked){
	                		doDeleteSingle(entityId, url, messageTipId,divWrapperId);
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
		}//end if checked
	}
	
	function doDeleteSingle(entityId, url, messageTipId,divWrapperId){
		
		var uploadData=getPosition(entityId);
		
		var checked=validateDeleteData(entityId);
		//var messageTipId = "id_messageTip_paragraph";
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
	    			title: "删除位置结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(divWrapperId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(divWrapperId);
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
	
	function removeDeletedDivWrapper(divWrapperId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
 	    console.log("---successValue:" + successValue);
   		if(successValue==1){
   			console.log("---successValue==1:" + successValue==1);
   			$("#"+divWrapperId).remove();
   		}
		
	}
	
	function validateDeleteData(entityId){
		var extendId = $("#id_hidden_id").val();
		console.log("....validateDeleteData: entityId --> "+ entityId);
		console.log("....validateDeleteData: extendId --> "+ extendId);
		
		if(typeof entityId=='undefined'){return false;}
		if(!entityId){return false;}
		
		if(!extendId){
			console.log("!!! error for extendId : "+ extendId + "  >>>!!!");
			return false;
		}
		
		return true;
	}
	
	
	
	//删除全部位置开始
	function deleteAll(extendId, subIdPre, url, divWrapperId, tipId){
		if(!divWrapperId){
			popTip("divWrapperId 不能为空");
			return false;
		}
		var checked = validateExtendData(extendId,subIdPre,tipId, false);
		
		if(checked){
			var messageTipId = tipId;//"id_messageTip_paragraph";
			var tip="您确定要删除<span class='text-danger'>【全部位置】</span>吗？删除后不可恢复，请谨慎操作！"
			BootstrapDialog.show({
				title: "删除确认？",
		    	message: $('<div></div>').html(tip),
		    	size: BootstrapDialog.SIZE_SMALL,
		    	buttons: [{
	                label: '确定全部删除',
	                cssClass : "btn-danger",
	                action: function(dialog){
	                	if(checked){
	                		doDeleteAll(extendId, subIdPre,url, divWrapperId, tipId);
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
		}//end if checked
	}
	
	function doDeleteAll(extendId, subIdPre, url, divWrapperId, messageTipId){
		//var messageTipId = "id_messageTip_paragraph";
		var uploadData=getCurrentExtend(extendId, subIdPre);
		
		var checked=validateExtendData(extendId, subIdPre, messageTipId, false);
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
	    			title: "删除全部位置结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(divWrapperId, messageTipId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(extendId);
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
	
	function removeDeletedAllDivWrapper(divWrapperId,messageTipId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
 	    console.log("---successValue:" + successValue);
   		if(successValue==1){
   			console.log("---successValue==1:" + successValue==1);
   			$("#"+divWrapperId).empty();
   		}
		
	}
	
	function validateExtendData(extendId, subIdPre, messageTipId, validateSubId){
		
		console.log("....validateExtendData: extendId --> "+ extendId);
		if(typeof extendId=='undefined'){return false;}
		var extendIdV = extendId;
		if(!extendId){
			console.log("!!! error for productId : "+ extendId + "  >>>!!!");
			return false;
		}
		
		var data=getCurrentExtend(extendId,subIdPre);
		if(''==extendId){
			extendIdV = data.extendId;
		}
		
		var mainId = data.mainId;
		var subId = data.subId;
		var mainIdChecked = validateContentEmpty(mainId, "mainId不能为空", messageTipId);
  		if(!mainIdChecked){
  			return false;
  		}
  		
  		var subIdChecked = validateContentEmpty(subId, "subId不能为空", messageTipId);
  		if(!subIdChecked){
  			return false;
  		}
  		
  		if(validateSubId && subId<1){
  			popTip("提醒：请先选择<页面>");
  			return false;
  		}
  		
  		var extendIdVChecked = validateContentEmpty(extendIdV, "extendId不能为空", messageTipId);
  		if(!extendIdVChecked){
  			return false;
  		}
		
		return true;
	}
	//删除全部位置结束
	
	
	
	function getCurrentExtend(extendId,subIdPre){
		var mainId = $("#id_hidden_id").attr("data-mainId");
		//var subId = $("#id_hidden_id").attr("data-subId");
		var name = $("#id_name"+extendId).val();
		var subId = $("#hidden_"+subIdPre).val();
		var data = {
				'extendId' : extendId,
				'mainId' : mainId,
				'subId' : subId,
				'name': name
		};
		return data;
	}
	//添加大类开始
	function addSingle(extendId,subIdPre, url, divWrapperId, messageTipId){
		var append="true";
		addSingleBase(extendId,subIdPre, url, divWrapperId, messageTipId, append);
	}
	function addSingleSub(extendId,parentId, url, divWrapperId, messageTipId){
		var append="false";
		addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append);
	}
	function refreshSub(extendId,parentId, url, divWrapperId, messageTipId){
		var append="false";
		addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append);
	}
	function addSingleBase(extendId,subIdPre, url, divWrapperId, messageTipId, append){
		console.log("....addSingle: extendId --> "+ extendId);
		//var messageTipId = "id_messageTip_paragraph";
		
		var extendIdV = null;
		if(typeof extendId !='undefined'){
			extendIdV = extendId;
		}
		var checked = validateExtendData(extendId, subIdPre,messageTipId, true);
		if(checked){
			var uploadData=getCurrentExtend(extendId,subIdPre);
			
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/add',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	})
	    	.then(function (response) {
	    	    
	    	    var respMsg=$('<div></div>').html(response.data);
	    	    if(append=="false"){
	    	    	$("#"+divWrapperId).html(response.data);
	    	    }else{
	    	    	$("#"+divWrapperId).append(response.data);
	    	    }
	    	    
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
	    	    	
	    	    	informUpdate(messageTipId);
		    	});
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
	}
	//添加大类结束
	
	
	//删除品种begin
	function getExtendData(extendId){
		var id=$("#id_id"+extendId).val();
		var variety=$("#id_variety"+extendId).val();
		var module=$("#id_module"+extendId).val();
		var project=$("#id_project"+extendId).val();
		var data={
				"id":id,
				"variety":variety,
				"module":module,
				"project":project
		}
		return data;
	}
	
	function validateVarietyDeleteData(extendId){
		//var extendId = $("#id_hidden_id").val();
		console.log("....validateDeleteData: extendId --> "+ extendId);
		if(!extendId){
			console.log("!!! error for extendId : "+ extendId + "  >>>!!!");
			return false;
		}
		return true;
	}
	
	function deleteVarietySingle(entityId, url, divWrapperId, messageTipId){
		var checked = validateVarietyDeleteData(entityId);
		var entity = getExtendData(entityId);
		var idV = entity.id;
		
		var showV="-"+entity.project+"-"+ entity.module + "-"+entity.variety+"-";
		if(showV==''){
			showV="编号为#"+idV;
		}
		//var messageTipId = "id_messageTip_paragraph";
		var tip="您确定要删除<span class='text-danger'>【"+showV+"】</span>的项目品种吗？删除后不可恢复，请谨慎操作！"
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteVarietySingle(entityId, url, divWrapperId, messageTipId);
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
	
	function doDeleteVarietySingle(entityId, url,divWrapperId, messageTipId){
		
		var uploadData=getExtendData(entityId);
		
		var checked=validateVarietyDeleteData(entityId);
		if(checked){
			axios({
	    	    method: 'post',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify(uploadData)
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    BootstrapDialog.show({
	    			title: "删除位置结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(divWrapperId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedDivWrapper(divWrapperId);
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
	
	//删除品种end
	
	
	
	//标签操作开始
	function saveTag(productId, url){
		
		var messageTipId="id_messageTip_tag";
		var name = $("#id_tag"+productId).val();
  		var tagChecked = validateEmpty("id_tag"+productId, "标签内容不能为空", messageTipId);
  		if(!tagChecked){
  			return false;
  		}
  		
  		var nameScriptChecked = validateScript("id_tag"+productId, "标签含有非法字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		var uploadData={
	    		"mainId" : productId,
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
    	    $("#id_p_tags"+productId).append(response.data);
    	    
    	    var successVal = $("#id_hidden_common_success").val();
	    	console.log("------wwww 1--- : " + successVal)
    	    var commonMsg = $("#id_common_respMsg").html();
	    	
	    	console.log("------wwww 2--- : " + commonMsg)
    	    
	    	informUpdate("id_messageTip_tag");
    	    if(successVal==1){
    	    	respMsg=commonMsg;
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
	
	
	function deleteTag(id, productId, tagId, deleteTagId, url){
		var messageTipId="id_messageTip_tag";
		$(deleteTagId).attr("disabled",true);
		
		if(typeof id == 'undefined' || typeof productId == 'undefined' || typeof tagId == 'undefined'){
			alert("数据非法，请刷新重试！");
		}
		
  		var uploadData={
  				"id" : id,
	    		"mainId" : productId,
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
    	    //$("#id_p_tags"+productId).append(response.data);
    	    
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
	
	function clearTag(productId){
		$("#id_tag"+productId).val('');
	}
	//标签操作结束

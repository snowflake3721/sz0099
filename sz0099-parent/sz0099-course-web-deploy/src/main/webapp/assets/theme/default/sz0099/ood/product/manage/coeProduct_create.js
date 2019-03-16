	console.log("!!!in create!!!!!!!!!");
	
	var paragraphLoaded=false;
	function loadData(panelName, url){
		var productId = $("#id_hidden_id").val();
		console.log("---- photo preload 1----"+productId);
		if(panelName=="#contentPhoto" && !paragraphLoaded){
			
		console.log("---- photo preload 2----"+productId);
			//加载段落数据
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/paragraph/editListUI',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({
		    	    	'mainId' : productId
	    	    	})
	    	    
	    	})
	    	.then(function (response) {
	    	    //
	    	    console.log("---- photo loaded----");
	    	    $("#id_contentPhoto_content"+productId).html(response.data);
	    	    var currentParagId= $("#id_paragId_current").val();
	    	    //initParagraph(currentParagId); TODO 仅初始化当前这个
	    	    initParagraph(productId);//将所有file组件全都初始化一遍
	    	    paragraphLoaded=true;
	    	   
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	});
		
		}
	}
	
	function refreshParagraph(productId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
	}
	
	
	
	
	
	function validateBaseinfo(productId){
		
		var messageTipBaseinfoId = "id_messageTip_baseinfo"+productId;
  		var messageTipBaseinfo=$("#"+messageTipBaseinfoId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		var titleId="id_title"+productId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var  nameId = "id_name"+productId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var descriptionId = "id_area_description"+productId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		
  		//title
  		var titleChecked = validateEmpty(titleId, "标题不能为空", messageTipBaseinfoId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "标题太长，都超50了", 50, messageTipBaseinfoId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "标题含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		var nameChecked = validateEmpty(nameId, "名称不能为空", messageTipBaseinfoId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "名称太长，都超15了", 15, messageTipBaseinfoId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		
  		var nameScriptChecked = validateScript(nameId, "名称含有非法字符，自动过滤", messageTipBaseinfoId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		//description
  		var descriptionChecked = validateEmpty(descriptionId, "描述不能为空", messageTipBaseinfoId);
  		if(!descriptionChecked){
  			return false;
  		}
  		
  		var descriptionLengthChecked = validateLength(descriptionId, "描述太长，都超200了", 200, messageTipBaseinfoId);
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
  		}else if(panelId=="#panel_price"){
  			commitPrice(btnId, productId, url);
  			//return ;
  		}else if(panelId=="#contentPhoto"){
  			btn.addClass("hidden");
  		}else if(panelId=="#contentTag"){
  			btn.addClass("hidden");
  		}
  		
  		btn.removeAttr("disabled");
  		
  		
	}
	
	function commitBaseinfo(btnId, productId, url){
		var btn = $("#"+btnId);
  		btn.attr("disabled","true");
  		
  		var titleId="id_title"+productId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var  nameId = "id_name"+productId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var descriptionId = "id_area_description"+productId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		var id_hidden=$("#id_hidden_id");
  		var id = id_hidden.val();
  		
  		var idMessageTipBaseinfoId="id_messageTip_baseinfo"+productId;
  		
  		console.log("---id----" + id);
		var checked = validateBaseinfo(productId);
		console.log("---commitBaseinfo()----" + checked);
		if(checked){
			
			axios({
	    	    method: 'post',
	    	    //url: '/sz0099/ood/product/manage/merge/baseinfo',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({
		    	    	'title' : title,
		    	    	'name' : name,
		    	    	'id' : id,
		    	    	'description' : description
	    	    	})
	    	    
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
  		
		var priceOriId = "id_priceOri"+productId;
  		var priceOriInput=$("#"+priceOriId);
  		var priceOri = priceOriInput.val();
  		
  		var priceCurId = "id_priceCur"+productId;
  		var priceCurInput = $("#"+priceCurId);
  		var priceCur = priceCurInput.val();
  		
  		var ratesId = "id_rates"+productId;
  		var ratesInput = $("#"+ratesId);
  		var rates = ratesInput.val();
  		
  		var linkId = "id_link"+productId;
  		var linkInput = $("#"+linkId);
  		var link = linkInput.val();
  		
  		var pullCodeId = "id_pullCode"+productId;
  		var pullCodeInput = $("#"+pullCodeId);
  		var pullCode = pullCodeInput.val();
  		
  		var strategyInput = $("#hidden_id_strategy");
  		var strategy = strategyInput.val();
  		
  		var gradeInput = $("#hidden_id_grade");
  		var grade = gradeInput.val();
  		
  		var pullMethodInput = $("#hidden_id_pullMethod");
  		var pullMethod = pullMethodInput.val();
  		
  		priceOri = checkOnlyNum(priceOri);
  		priceOriInput.val(priceOri);
  		var priceOriChecked = validateEmpty(priceOriId, "原价不能为空", messageTipPriceId);
  		if(!priceOriChecked){
  			return false;
  		}
  		
  		priceCur = checkOnlyNum(priceCur);
  		priceCurInput.val(priceCur);
  		var priceCurChecked = validateEmpty(priceCurId, "现价不能为空", messageTipPriceId);
  		if(!priceCurChecked){
  			return false;
  		}
  		var strategyChecked = validateEmpty("hidden_id_strategy", "优惠策略不能为空", messageTipPriceId);
  		if(!strategyChecked){
  			return false;
  		}
  		
  		if(strategy==2 || strategy==3 || strategy==5){
  			var gradeChecked = validateEmpty("hidden_id_grade", "优惠策略选择【等级or不限】时，等级设置不能为空", messageTipPriceId);
  	  		if(!gradeChecked){
  	  			return false;
  	  		}
  	  		if(strategy==2 || strategy==3 ){
		  	  	var ratesChecked = validateEmpty(ratesId, "最低折扣不能为空，保护产品最终成交价格不会低于最低设置折扣", messageTipPriceId);
		  		if(!ratesChecked ){
		  			return false;
		  		}
		  		
		  		ratesChecked=validateRange(ratesId, 35,100,"最低折扣设置范围超限", messageTipPriceId);
		  		if(!ratesChecked ){
		  			return false;
		  		}
  	  		}
  		}
  		
  		
  		var pullMethodChecked = validateEmpty("hidden_id_pullMethod", "提取方式不能为空", messageTipPriceId);
  		if(!pullMethodChecked){
  			return false;
  		}
  		
  		pullMethodChecked = validateUnkown("hidden_id_pullMethod", 0, "提取方式需要指定！！", messageTipPriceId);
  		if(!pullMethodChecked){
  			return false;
  		}
  		
  		if(pullMethod==10){
  			var pullCodeChecked = validateEmpty(pullCodeId, "提取密码不能为空", messageTipPriceId);
	  		if(!pullCodeChecked){
	  			return false;
	  		}
  		}
  		
  		if(pullMethod==10 || pullMethod==20){
	  		var linkChecked = validateEmpty(linkId, "提取链接不能为空", messageTipPriceId);
	  		if(!linkChecked){
	  			return false;
	  		}
	  		
	  		var isLink = validateIsUrl(linkId, "提取链接的网址格式不合法", messageTipPriceId);
	  		if(!isLink){
	  			return false;
	  		}
  		}
  		
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
  		
  		var priceOriId = "id_priceOri"+productId;
  		var priceOriInput=$("#"+priceOriId);
  		var priceOri = priceOriInput.val();
  		
  		var priceCurId = "id_priceCur"+productId;
  		var priceCurInput = $("#"+priceCurId);
  		var priceCur = priceCurInput.val();
  		
  		var ratesId = "id_rates"+productId;
  		var ratesInput = $("#"+ratesId);
  		var rates = ratesInput.val();
  		
  		var linkId = "id_link"+productId;
  		var linkInput = $("#"+linkId);
  		var link = linkInput.val();
  		
  		var pullCodeId = "id_pullCode"+productId;
  		var pullCodeInput = $("#"+pullCodeId);
  		var pullCode = pullCodeInput.val();
  		
  		var strategyInput = $("#hidden_id_strategy");
  		var strategy = strategyInput.val();
  		
  		var gradeInput = $("#hidden_id_grade");
  		var grade = gradeInput.val();
  		
  		var pullMethodInput = $("#hidden_id_pullMethod");
  		var pullMethod = pullMethodInput.val();
  		
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
		    	    	'priceOri' : priceOri,
		    	    	'priceCur' : priceCur,
		    	    	'id' : id,
		    	    	'rates' : rates,
		    	    	'link' : link,
		    	    	'pullCode' : pullCode,
		    	    	'strategy' : strategy,
		    	    	'grade' : grade,
		    	    	'pullMethod' : pullMethod
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
			}else{
				$("#"+id).html(value);
			}
		}
		
	}
	
	//保存单个段落
	function saveParagraphSingle(paragId, url){
		var productId = $("#id_hidden_id").val();
		
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
		
		var messageTipId="id_messageTip_paragraph"+productId;
		
		console.log(paragIdK + ": " + paragIdV);
		console.log(nameK + ": " + nameV);
		console.log(titleK + ": " + titleV);
		console.log(descriptionK + ": " + descriptionV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(paragProductIdK + ": " + paragProductIdV);
		console.log("productId" + ": " + productId);
		
		
		var checked = validateParagraphSingleParam(paragId);
		console.log("checked" + ": " + checked);
		var uploadData={
	    		"paragraph.name" : nameV,
	    		"paragraph.title" : titleV,
	    		"paragraph.description" : descriptionV,
	    		"orderSeq" : orderSeqV,
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : productId,
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
		
		var productId = $("#id_hidden_id").val();
		console.log("....validateParagraphSingleParam: paragId --> "+ paragId);
		console.log("....validateParagraphSingleParam: productId --> "+ productId);
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){
			console.log("!!! error for paragId : "+ paragId + "  >>>!!!");
			validateSessionInvalid("id_messageTip_paragraph","！！当前会话已失效，请重新登录，再操作！！");
			return false;
		}
		
		if(!productId){
			console.log("!!! error for productId : "+ productId + "  >>>!!!");
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
		
		var productId = $("#id_hidden_id").val();
		
		var paragIdInput=$("#id_paragId"+paragId);
		var paragIdV = paragIdInput.val();
		
		var uploadData={
	    		"id": paragProductIdV,
	    		"paragId" : paragIdV,
	    		"mainId" : productId,
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
		var productId = $("#id_hidden_id").val();
		console.log("....validateParagraphSingleParam: paragId --> "+ paragId);
		console.log("....validateParagraphSingleParam: productId --> "+ productId);
		
		if(typeof paragId=='undefined'){return false;}
		if(!paragId){return false;}
		
		if(!productId){
			console.log("!!! error for productId : "+ productId + "  >>>!!!");
			return false;
		}
		
		return true;
	}
	
	
	
	//删除全部部落开始
	
	function deleteAllParagraph(productId, url){
		var checked = validateDeleteAllParagData(productId);
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
                		doDeleteAllParagraph(productId, url);
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
	
	function doDeleteAllParagraph(productId, url){
		var messageTipId = "id_messageTip_paragraph";
		var uploadData={
	    		"mainId" : productId,
	    	}
		
		var checked=validateDeleteAllParagData(productId);
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
	                    	removeDeletedAllDivWrapper(productId);
	                    	BootstrapDialog.closeAll();
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	informUpdate(messageTipId);
	                    	removeDeletedAllDivWrapper(productId);
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
	
	function removeDeletedAllDivWrapper(productId){
		var id_success=$("#id_hidden_common_success");
 	    var successValue = id_success.val();
 	    console.log("---successValue:" + successValue);
   		if(successValue==1){
   			console.log("---successValue==1:" + successValue==1);
   			$("#id_contentPhoto_content"+productId).empty();
   		}
		
	}
	
	function validateDeleteAllParagData(productId){
		
		console.log("....validateDeleteAllParagData: productId --> "+ productId);
		
		if(typeof productId=='undefined'){return false;}
		var productIdV = productId;
		if(!productId){
			console.log("!!! error for productId : "+ productId + "  >>>!!!");
			return false;
		}
		
		if(''==productId){
			productIdV = $("#id_hidden_id").val();
		}
		
		if(productIdV=='' || null==productIdV){
			return false;
		}
		
		return true;
	}
	
	//删除全部段落结束
	
	
	//添加段落开始
	function addParagraphSingle(productId, url){
		console.log("....addParagraphSingle: productId --> "+ productId);
		var messageTipId = "id_messageTip_paragraph";
		var productIdV = null;
		if(typeof productId !='undefined'){
			productIdV = productId;
		}
		
		if(''==productIdV || null==productIdV){
			productIdV = $("#id_hidden_id").val();
		}
		
		if(productIdV=='' || null==productIdV ){
			return false;
		}
		
		var uploadData={
	    		"mainId" : productId
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
    	    $("#id_contentPhoto_content"+productId).append(response.data);
    	    var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    }
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	informUpdate(messageTipId);
	    	});
    	    //初始化file组件
    	    initParagraph(productId);
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
	
	
	function getProduct(productId){
		var titleId="id_title"+productId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		var  nameId = "id_name"+productId;
  		var nameInput = $("#"+nameId);
  		var name = nameInput.val();
  		
  		var descriptionId = "id_area_description"+productId;
  		var descriptionInput = $("#"+descriptionId);
  		var description = descriptionInput.val();
  		
  		
  		var priceOriId = "id_priceOri"+productId;
  		var priceOriInput=$("#"+priceOriId);
  		var priceOri = priceOriInput.val();
  		
  		var priceCurId = "id_priceCur"+productId;
  		var priceCurInput = $("#"+priceCurId);
  		var priceCur = priceCurInput.val();
  		
  		var ratesId = "id_rates"+productId;
  		var ratesInput = $("#"+ratesId);
  		var rates = ratesInput.val();
  		
  		var linkId = "id_link"+productId;
  		var linkInput = $("#"+linkId);
  		var link = linkInput.val();
  		
  		var pullCodeId = "id_pullCode"+productId;
  		var pullCodeInput = $("#"+pullCodeId);
  		var pullCode = pullCodeInput.val();
  		
  		var strategyInput = $("#hidden_id_strategy");
  		var strategy = strategyInput.val();
  		
  		var gradeInput = $("#hidden_id_grade");
  		var grade = gradeInput.val();
  		
  		var pullMethodInput = $("#hidden_id_pullMethod");
  		var pullMethod = pullMethodInput.val();
  		
  		var data={
  			"title" : title,
  			"name" : name,
  			"description" : description,
  			"priceOri" : priceOri,
  			"priceCur" : priceCur,
  			"rates" : rates,
  			"link" : link,
  			"pullCode" : pullCode,
  			"strategy" : strategy,
  			"grade" : grade,
  			"pullMethod" : pullMethod,
  			"id" : productId
  		}
  		return data;
	}
	
	function mergeForPublish(productId){
		
		var baseinfoChecked = validateBaseinfo(productId);
		if(!baseinfoChecked){
			return false;
		}
		
		var priceChecked = validatePrice(productId);
		if(!priceChecked){
			return false;
		}
		
		var messageTipId = "id_messageTip_publish";
		
		var uploadData=getProduct(productId);
		
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/manage/merge/publish',
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

	console.log("!!!in create!!!!!!!!!");
	
	var paragraphLoaded=false;
	function loadData(panelName, url){
		var productId = $("#id_hidden_id").val();
		var mainId = $("#id_hidden_id").attr("data-mainId");
		var subId =  $("#id_hidden_id").attr("data-subId");
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
		    	    	'extendId' : productId,
		    	    	'mainId' : mainId,
		    	    	'subId' : subId
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
	
	function refresh(productId, url){
		var currentPanel = $("#id_hidden_current_panel");
		var currentPanelValue = currentPanel.val();
		paragraphLoaded=false;
		loadData(currentPanelValue, url);
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
  		
  		var domainScriptChecked = validateDomain(domainId, "领域含有非法字符，自动过滤", messageTipBaseinfoId);
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
  		
  		var depthMaxnumId = "id_depthMaxnum"+entityId;
  		var depthMaxnumInput = $("#"+depthMaxnumId);
  		var depthMaxnum = depthMaxnumInput.val();
  		
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
		    	'depthMaxnum' : depthMaxnum,
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
	
	
	function getCategory(entityId){
		var extendId = $("#id_hidden_id").val();
		var mainId=$("#id_hidden_id").attr("data-mainId");
		var subId=$("#id_hidden_id").attr("data-subId");
		
		var nameInput=$("#id_category_name"+entityId);
		var codeInput=$("#id_category_code"+entityId);
		var orderSeqInput=$("#id_category_orderSeq"+entityId);
		var parentIdInput=$("#id_category_parentId"+entityId);
		
		var nameK = nameInput.attr("name");
		var nameV = nameInput.val();
		var codeK = codeInput.attr("name");
		var codeV = codeInput.val();
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
		
		var parentIdK = parentIdInput.attr("name");
		var parentIdV = parentIdInput.val();
		
		
		console.log(nameK + ": " + nameV);
		console.log(codeK + ": " + codeV);
		console.log(orderSeqK + ": " + orderSeqV);
		console.log(parentIdK + ": " + parentIdV);
		console.log("extendId" + ": " + extendId);
		
		var category={
	    		"name" : nameV,
	    		"code" : codeV,
	    		"orderSeq" : orderSeqV,
	    		"parentId": parentIdV,
	    		"extendId" : extendId,
	    		"mainId" : mainId,
	    		"subId" : subId,
	    		"id":entityId
	    	}
		return category;
	}
	
	/**
	 * 保存单个类别
	 * @param entityId  类加自身id
	 * @param url
	 * @returns
	 */
	function saveSingle(entityId, url, messageTipId){
		
		var checked = validateSingleParam(entityId, messageTipId);
		console.log("checked" + ": " + checked);
		var uploadData=getCategory(entityId);
		
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
	    			title: "保存分类结果",
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

		var orderSeqInput=$("#id_category_orderSeq"+entityId);
		var orderSeqK = orderSeqInput.attr("name");
		var orderSeqV = orderSeqInput.val();
  		orderSeqV = checkOnlyNum(orderSeqV);
  		orderSeqInput.val(orderSeqV);
  		
  		var nameId="id_category_name"+entityId;
  		var nameChecked = validateEmpty(nameId, "分类名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "分类名称太长，都超15字符了", 15, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		var nameScriptChecked = validateScript(nameId, "分类名称含有特殊字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		var codeId="id_category_code"+entityId;
  		
  		var codeChecked = validateEmpty(codeId, "Code不能为空", messageTipId);
  		if(!codeChecked){
  			return false;
  		}
  		var codeLengthChecked = validateLength(codeId, "Code太长，都超32字符了", 32, messageTipId);
	  		if(!codeLengthChecked){
	  			return false;
	  	}
  	
  		var codeScriptChecked = validateCommonOnly(codeId, "Code含有非法字符，自动过滤", messageTipId);
  		if(!codeScriptChecked){
  			return false;
  		}
  		
  		var parentIdInput="id_category_parentId"+entityId;
  		var parentId = $("#"+parentIdInput).val();
  		if(entityId==parentId){
  			popTip("父类不能选自己!");
  			return false;
  		}
  		
  		messageTipParagraph.html("段落内容验证通过...");
  		messageTipParagraph.removeClass("text-danger");
  		messageTipParagraph.addClass("text-success");
  		
  		return true;
	}
	
	function deleteSingle(entityId,url, messageTipId,divWrapperId){
		var checked = validateDeleteData(entityId);
		var category = getCategory(entityId);
		var orderSeqV = category.orderSeq;
		
		var showV=category.name;
		if(showV==''){
			showV="编号为#"+orderSeqV;
		}
		//var messageTipId = "id_messageTip_paragraph";
		var tip="您确定要删除<span class='text-danger'>【"+showV+"】</span>的分类吗？删除后不可恢复，请谨慎操作！"
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
	}
	
	function doDeleteSingle(entityId, url, messageTipId,divWrapperId){
		
		var uploadData=getCategory(entityId);
		
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
	    			title: "删除分类结果",
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
	
	
	
	//删除全部部落开始
	function deleteAll(extendId, parentId, url, divWrapperId, tipId){
		if(!divWrapperId){
			popTip("divWrapperId 不能为空");
			return false;
		}
		var checked = validateExtendData(extendId,parentId,tipId);
		var messageTipId = tipId;//"id_messageTip_paragraph";
		var tip="您确定要删除<span class='text-danger'>【全部分类】</span>吗？删除后不可恢复，请谨慎操作！"
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定全部删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteAll(extendId, parentId,url, divWrapperId, tipId);
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
	
	function doDeleteAll(extendId, parentId, url, divWrapperId, messageTipId){
		//var messageTipId = "id_messageTip_paragraph";
		var uploadData=getCurrentExtend(extendId, parentId);
		
		var checked=validateExtendData(extendId,parentId, messageTipId);
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
	    			title: "删除全部分类结果",
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
	
	function validateExtendData(extendId, parentId, messageTipId){
		
		console.log("....validateExtendData: extendId --> "+ extendId);
		if(typeof extendId=='undefined'){return false;}
		var extendIdV = extendId;
		if(!extendId){
			console.log("!!! error for productId : "+ extendId + "  >>>!!!");
			return false;
		}
		
		var data=getCurrentExtend(extendId,parentId);
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
  		
  		var extendIdVChecked = validateContentEmpty(extendIdV, "extendId不能为空", messageTipId);
  		if(!extendIdVChecked){
  			return false;
  		}
		
		return true;
	}
	//删除全部分类结束
	
	
	
	function getCurrentExtend(extendId,parentId){
		var mainId = $("#id_hidden_id").attr("data-mainId");
		var subId = $("#id_hidden_id").attr("data-subId");
		var data = {
				'extendId' : extendId,
				'mainId' : mainId,
				'subId' : subId,
				'parentId':parentId
		};
		return data;
	}
	//添加大类开始
	function addSingle(extendId,parentId, url, divWrapperId, messageTipId){
		var append="true";
		addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append);
	}
	function addSingleSub(extendId,parentId, url, divWrapperId, messageTipId){
		var append="false";
		addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append);
	}
	function refreshSub(extendId,parentId, url, divWrapperId, messageTipId){
		var append="false";
		addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append);
	}
	function addSingleBase(extendId,parentId, url, divWrapperId, messageTipId, append){
		console.log("....addSingle: extendId --> "+ extendId);
		//var messageTipId = "id_messageTip_paragraph";
		
		var extendIdV = null;
		if(typeof extendId !='undefined'){
			extendIdV = extendId;
		}
		var checked = validateExtendData(extendId, parentId,messageTipId);
		if(checked){
			var uploadData=getCurrentExtend(extendId,parentId);
			
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
	    			title: "删除分类结果",
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

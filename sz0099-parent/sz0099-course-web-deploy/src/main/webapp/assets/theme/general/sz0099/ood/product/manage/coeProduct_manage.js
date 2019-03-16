	console.log("!!!in manage!!!!!!!!!");
	//产品 上架|下架 操作开始
	function shelvedProduct(productId, shelved){
		
  		console.log("---productId----" + productId + " --- shelved: "+shelved +" ---- ");
		if(typeof productId=='undefined'){return false;}
		
		if(!productId){
			console.log("!!! error for productId : "+ productId + "  >>>!!!");
			return false;
		}
		
		var currentShelvedInput = $("#id_hidden_shelved"+productId);
		var currentShelved = currentShelvedInput.val();
		
		var uploadData={
				'shelved' : shelved,
    	    	'id' : productId
	    	}
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/manage/merge/shelved',
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
        	    	
        	    	if(shelved!=currentShelved){
	        	    	$("#id_li_shevled_"+productId+currentShelved).removeClass("hidden");
	        	    	$("#id_li_shevled_"+productId+shelved).addClass("hidden");
	        	    	currentShelvedInput.val(shelved);
	        	    	changeShelvedSpanShow(productId, shelved);
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
	
	function changeShelvedSpanShow(productId, val){
		var span = $("#id_span_shelved"+productId);
		if(val==1){
			span.html("已上架");
			span.removeClass("text-danger");
			span.addClass("text-success");
		}else{
			span.html("已下架");
			span.removeClass("text-success");
			span.addClass("text-danger");
		}
	}
	//产品 上架|下架 操作结束
	
	//刷新产品开始
	function refreshProduct(productId){
		
  		console.log("---productId----" + productId );
		if(typeof productId=='undefined'){return false;}
		
		if(!productId){
			console.log("!!! error for productId : "+ productId + "  >>>!!!");
			return false;
		}
		
		var refreshTime=$("#id_span_refreshTime"+productId).html();
		
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
    	    	'id' : productId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/manage/merge/refresh',
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
        	    	$("#id_span_refreshTime"+productId).html(refreshTime);
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
	//刷新产品结束
	
	
	//快速编辑开始
	function editQuickly(productId){
		var checked = validateEditQuickly(productId);
		if(checked){
			var nameId = "id_name"+productId;
			var nameInput = $("#"+nameId);
			var name = nameInput.val();
			
			var titleId = "id_title"+productId;
			var titleInput=$("#"+titleId);
	  		var title = titleInput.val();
	  		
	  		var priceOriId = "id_priceOri"+productId;
	  		var priceOriInput=$("#"+priceOriId);
	  		var priceOri = priceOriInput.val();
	  		
	  		var priceCurId = "id_priceCur"+productId;
	  		var priceCurInput = $("#"+priceCurId);
	  		var priceCur = priceCurInput.val();
	  		
	  		var linkId = "id_link"+productId;
	  		var linkInput = $("#"+linkId);
	  		var link = linkInput.val();
	  		
	  		var pullCodeId = "id_pullCode"+productId;
	  		var pullCodeInput = $("#"+pullCodeId);
	  		var pullCode = pullCodeInput.val();
	  		
	  		var messageTipId = "id_messageTip_edit_quickly"+productId;
	  		var messageTipEditQuickly=$("#"+messageTipId);
	  		messageTipEditQuickly.html("");
			
			
			var uploadData={
	    	    	'id' : productId,
	    	    	"name" : name,
	    	    	"title" : title,
	    	    	"priceOri" : priceOri,
	    	    	"priceCur" : priceCur,
	    	    	"link" : link,
	    	    	"pullCode" : pullCode
		    }
			
			axios({
	    	    method: 'post',
	    	    url: '/sz0099/ood/product/manage/merge/editquickly',
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
	function validateEditQuickly(productId){
		
		var nameId = "id_name"+productId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		
		var titleId = "id_title"+productId;
		var titleInput=$("#"+titleId);
  		var title = titleInput.val();
  		
  		
  		
  		var messageTipId = "id_messageTip_edit_quickly"+productId;
  		var messageTipBaseinfo=$("#"+messageTipId);
  		messageTipBaseinfo.html("");
  		messageTipBaseinfo.addClass("text-danger");
  		
  		//title
  		var titleChecked = validateEmpty(titleId, "产品标题不能为空", messageTipId);
  		if(!titleChecked){
  			return false;
  		}
  		
  		var titleLengthChecked = validateLength(titleId, "产品标题太长，都超50了", 50, messageTipId);
  		if(!titleLengthChecked){
  			return false;
  		}
  		
  		var titleScriptChecked = validateScript(titleId, "产品标题含有非法字符，自动过滤", messageTipId);
  		if(!titleScriptChecked){
  			return false;
  		}
  		
  		//name
  		var nameChecked = validateEmpty(nameId, "产品名称不能为空", messageTipId);
  		if(!nameChecked){
  			return false;
  		}
  		
  		var nameLengthChecked = validateLength(nameId, "产品名称太长，都超15了", 15, messageTipId);
  		if(!nameLengthChecked){
  			return false;
  		}
  		
  		var nameScriptChecked = validateScript(nameId, "产品名称含有非法字符，自动过滤", messageTipId);
  		if(!nameScriptChecked){
  			return false;
  		}
  		
  		var priceOriId = "id_priceOri"+productId;
  		var priceOriInput=$("#"+priceOriId);
  		var priceOri = priceOriInput.val();
  		
  		var priceCurId = "id_priceCur"+productId;
  		var priceCurInput = $("#"+priceCurId);
  		var priceCur = priceCurInput.val();
  		
  		var linkId = "id_link"+productId;
  		var linkInput = $("#"+linkId);
  		var link = linkInput.val();
  		
  		var pullCodeId = "id_pullCode"+productId;
  		var pullCodeInput = $("#"+pullCodeId);
  		var pullCode = pullCodeInput.val();
  		
  		priceOri = checkOnlyNum(priceOri);
  		priceOriInput.val(priceOri);
  		var priceOriChecked = validateEmpty(priceOriId, "原价不能为空", messageTipId);
  		if(!priceOriChecked){
  			return false;
  		}
  		
  		priceCur = checkOnlyNum(priceCur);
  		priceCurInput.val(priceCur);
  		var priceCurChecked = validateEmpty(priceCurId, "现价不能为空", messageTipId);
  		if(!priceCurChecked){
  			return false;
  		}
  		
  		if(link !=null && link !=''){
	  		var isLink = validateIsUrl(linkId, "提取链接的网址格式不合法", messageTipId);
	  		if(!isLink){
	  			return false;
	  		}
  		}
		
  		messageTipBaseinfo.html("基本信息验证通过...");
  		messageTipBaseinfo.removeClass("text-danger");
  		messageTipBaseinfo.addClass("text-success");
  		return true;
	}
	//快速编辑结束
	
	function mergeForClosed(productId){
		var nameId = "id_name"+productId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "关闭产品后将<b>不能再次开启，不可被编辑，不可搜索查看</b>，您是否考虑用【下架】功能代替此操作？<br>你确定要关闭产品【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "关闭产品提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForClosed(productId);
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
	
	function doMergeForClosed(productId){
		var messageTipId = "id_messageTip_edit_quickly"+productId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : productId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/manage/merge/closed',
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
	
	function mergeForDeleted(productId){
		var nameId = "id_name"+productId;
		var nameInput = $("#"+nameId);
		var name = nameInput.val();
		var msg = "删除产品后将<b>不可恢复</b>，您是否考虑用【下架】功能代替此操作？<br>你确定要删除产品【"+name+"】吗？";
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_DANGER,
			title: "删除产品提示",
	    	message: $('<div></div>').html(msg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除',
                cssClass : "btn-danger",
                action: function(dialog){
                	doMergeForDeleted(productId);
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
	
	function doMergeForDeleted(productId){
		var messageTipId = "id_messageTip_edit_quickly"+productId;
  		var messageTipEditQuickly=$("#"+messageTipId);
  		messageTipEditQuickly.html("");
		var uploadData={
    	    	'id' : productId
	    }
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/manage/merge/deleted',
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

function praise(mainId, dataHiddenId, wordInputId){
	var dataHidden=$("#"+dataHiddenId);
	var wordInput=$("#"+wordInputId);
	var url=dataHidden.attr("data-url");
	var uploadData={
	    	'mainId' : mainId
    }
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
		var respMsg=response.data;
	    BootstrapDialog.show({
			type: BootstrapDialog.TYPE_SUCCESS,
			title: "点赞",
	    	message: $('<div></div>').html(respMsg),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
	    		id:'btn-confirm-praise',
                label: '确认点赞',
                cssClass : "btn-success",
                action: function(dialog){
	                	var successVal = $("#id_hidden_common_success").val();
	                	if(successVal==1){
	                		var word = wordInput.val();
	                		dataHidden.val(word);
	                		doPraise(mainId, dataHiddenId, wordInputId);
	                	}
	                	dialog.close();
	                },
            }, {
                label: '关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                	dialog.close();
                }
            }],
            onshown:function(dialog){
            	var successVal = $("#id_hidden_common_success").val();
            	var $button = dialog.getButton("btn-confirm-praise");
            	$button.attr("disabled",true);
            	if(successVal!=1){
            		$button.addClass("hidden");
            	}else{
            		$button.removeClass("hidden");
            		$button.attr("disabled",false);
            	}
            }
		});
	})
	.catch(function (error) {
	    console.log(error);
	});
	
}

function doPraise( mainId, dataHiddenId, wordInputId){
	var dataHidden=$("#"+dataHiddenId);
	var url=dataHidden.attr("data-saveUrl");
	var wordInput=$("#"+wordInputId);
	var word=wordInput.val();
	var uploadData={
	    	'mainId' : mainId,
	    	'word' : word
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

function refreshPraise(id, mainId, wordId){
	var hiddenWordId=$("#"+wordId);
	var url=hiddenWordId.attr("data-refreshUrl");
	popTip("友情提示：刷新点赞功能尚在开发中，请下期版本再试！<br/>刷新点赞可提升点赞排名，增加文章or技能关联展示机率！");
	return false;
	
	var uploadData={
			'id' : id,
	    	'mainId' : mainId,
	    	'word' : word
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
function informUpdate(panelId){
	var id_success=$("#id_hidden_common_success");
	    var successValue = id_success.val();
	    var id_common_respMsg=$("#id_common_respMsg");
	    var messageTip=$("#"+panelId);
	    var btn = $("#id_btn_confirm");
	   
	    
	    console.log(id_common_respMsg.html());
	    console.log("---successValue:" + successValue);
		messageTip.html(id_common_respMsg.html());
		if(successValue==1){
			console.log("---successValue==1:" + successValue==1);
			messageTip.removeClass("text-danger");
	  		messageTip.addClass("text-success");
		}else if(successValue==2){
			messageTip.removeClass("text-success");
	  		messageTip.addClass("text-danger");
		}
		btn.removeAttr("disabled");
}

function mergeForInprocess(orderId, status, itemDivId, url){
	console.log("in mergeForInprocess : " + orderId);
	var url='/sz0099/ood/product/order/manage/mergeForInprocess';
	mergeForStatus(orderId, status, itemDivId, url)
	
}

function mergeForStatus(orderId, status, itemDivId, url){
	if(orderId){
		axios({
		    method: 'post',
		    //url: '/sz0099/ood/product/order/manage/mergeForInprocess',
		    url: url,
		    data: 
		    	Qs.stringify({
	    	    	'id' : orderId,
	    	    	'status': status
		    	})
		    
		})
		.then(function (response) {
		    
		    BootstrapDialog.show({
				title: "处理订单",
		    	message: $('<div></div>').html(response.data),
		    	size: BootstrapDialog.SIZE_SMALL,
		    	buttons: [{
	                label: '确定',
	                cssClass : "btn-primary",
	                action: function(dialog){
	                	//informUpdate(messageTipPriceId);
	                	$("#"+itemDivId).remove();
	                	dialog.close();
	                },
	            }, {
	                label: '关闭',
	                cssClass : "btn-danger",
	                action: function(dialog){
	                	//informUpdate(messageTipPriceId);
	                	dialog.close();
	                }
	            }]
			});
		   
	  		
		})
		.catch(function (error) {
		    console.log(error);
		    //informUpdate(messageTipPriceId);
		});
	}
}

function mergeForSent(orderId, status, itemDivId){
	console.log("in mergeForSent : " + orderId);
	var url='/sz0099/ood/product/order/manage/mergeForSent';
	mergeForStatus(orderId, status, itemDivId, url);
}
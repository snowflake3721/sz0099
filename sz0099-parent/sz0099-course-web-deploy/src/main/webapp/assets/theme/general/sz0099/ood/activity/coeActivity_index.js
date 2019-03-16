	
	
	console.log("!!!!!!!!!!!!");
	
	function showBuyInstruction(id){
		var logined=false;
		BootstrapDialog.show({
			onshow: function(dialog) {
                //dialog.getButton('id_btn_next_geneOrder').disable();
            },
			title: "购买说明",
			message: function(dialog) {
                var $message = $('<div id="id_div_showBuyInstruction"></div>');
                var pageToLoad = dialog.getData('showBuyInstructionLoad');
                $message.load(pageToLoad);
                return $message;
            },
			data: {
				'showBuyInstructionLoad': '/sz0099/ood/product/buyInstruction?id='+id
			},
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
	    		id: 'id_btn_next_geneOrder',
                label: '下一步生成订单',
                cssClass : "btn-primary",
                action: function(dialog){
                	var id_input_opration_resp = $("#id_input_opration_resp");   
                    var id_input_success_code = $("#id_input_success_code");   
                    var successCode='success';
                    var geneOrderButton = dialog.getButton('id_btn_next_geneOrder');
                    
                    var tip = "非法操作";
                    if(id_input_opration_resp){
                    	var respCode = id_input_opration_resp.val();
                    	if(id_input_success_code){
                    		successCode=id_input_success_code.val();
                    	}
                    	console.log(successCode + " vs " + respCode);
                    	if(respCode==successCode){
                    		logined=true;
                    		console.log(">>>>> should enable.....");
                    		dialog.getButton('id_btn_next_geneOrder').enable();
                    		geneOrderButton.html("下一步生成订单");
                    	}else{
                    		geneOrderButton.disable();
                    		if("sz0099_210014"==respCode){
                    			tip="非法操作";
                    		}
                    		if("sz0099_200004"==respCode){
                    			tip="请登录后操作";
                    		}
                    		if("sz0099_210012"==respCode){
                    			tip="产品已下架";
                    		}
                    		console.log(geneOrderButton);
                            
                            geneOrderButton.html(tip);
                        	console.log(successCode + " vs " + respCode);
                    	}
                    }else{
                    	console.log(geneOrderButton);
                        geneOrderButton.disable();
                        geneOrderButton.html("发生错误，非法操作");
                    	console.log(" error " + respCode);
                    }
                    
                    if(logined  && respCode==successCode && respCode && successCode){
	                	preOrder(id);
	                	dialog.close();
                	}else{
                		
                		alert(tip);
                	}
                	
                }
            }, {
            	id: 'id_btn_cancel_geneOrder',
                label: '取消',
                cssClass : "btn-danger",
                action: function(dialog){
                	var id_input_opration_resp = $("#id_input_opration_resp");   
                    var id_input_success_code = $("#id_input_success_code");
                    var respCode = id_input_opration_resp.val();
                    var successCode='success';
                	if(id_input_success_code){
                		successCode=id_input_success_code.val();
                	}
                	console.log(successCode + " vs " + respCode);
                    dialog.close();
                }
            }]
		});
	}
	
	var confirmOrderChecked=false;
	function preOrder(id){
		var logined=false;
		BootstrapDialog.show({
			onshow: function(dialog) {
                //dialog.getButton('id_btn_confirm_order').disable();
            },
			title: "课程下单",
			message: function(dialog) {
                var $message = $('<div id="id_div_preOrder"></div>');
                var pageToLoad = dialog.getData('preOrderLoad');
                $message.load(pageToLoad);
                
                console.log($("#id_div_preOrder"));
                
                return $message;
            },
			data: {
				'preOrderLoad': '/sz0099/ood/product/preOrder?id='+id
			},
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确认下单',
                cssClass : "btn-primary",
                action: function(dialog){
                	
                	var id_input_opration_resp = $("#id_input_opration_resp");   
                    var id_input_success_code = $("#id_input_success_code");   
                    var successCode='success';
                    var confirmOrderButton = dialog.getButton('id_btn_confirm_order');
                    
                    var tip="非法操作";
                    if(id_input_opration_resp){
                    	var respCode = id_input_opration_resp.val();
                    	if(id_input_success_code){
                    		successCode=id_input_success_code.val();
                    	}
                    	if(respCode && successCode && respCode==successCode){
                    		logined=true;
                    		confirmOrderButton.enable();
                    	}else{
                    		console.log(confirmOrderButton);
                            confirmOrderButton.disable();
                            if("sz0099_200004"==respCode){
                    			tip="请登录后操作";
                    		}
                            confirmOrderButton.html(tip);
                    	}
                    }
                	
                	if(logined){
                		confirmOrder(dialog);
                	}
                },
                id:"id_btn_confirm_order"
            }, {
                label: '取消',
                cssClass : "btn-danger",
                action: function(dialog){
                    dialog.close();
                },
                id:"id_btn_cancel_order"
            }]
		});
	}
	
	function validateOrderParam(){
		var emailInput=$("#id_input_email");
  		var email = emailInput.val();
  		var mobileInput = $("#id_input_mobile");
  		var mobile = mobileInput.val();
  		var messageTipDiv=$("#id_div_messageTip");
  		messageTipDiv.html("");
  		var tip="";
  		if(''==email){
  			tip="Email不能为空";
  			messageTipDiv.html(tip);
  			popTip(tip);
  			return false;
  		}
  		if(!isEmail(email)){
  			tip="Email格式不正确";
  			messageTipDiv.html(tip);
  			popTip(tip);
  			return false;
  		}
  		
  		if(''==mobile){
  			tip="手机号不能为空";
  			messageTipDiv.html(tip);
  			popTip(tip);
  			return false;
  		}
  		
  		if(!isPoneAvailable(mobile)){
	    	 tip="手机号格式不正确";
	  		messageTipDiv.html(tip);
	  		popTip(tip);
 			return false;
 		}
  		
  		var flowNoId = $("#id_input_flowNo");
  		var flowNo = "";
  		if(flowNoId){
  			flowNo=flowNoId.val();
  		}
  		if(flowNo=="" || flowNo==null){
	    	 tip="订单流水号不合法";
	  		messageTipDiv.html(tip);
	  		popTip(tip);
			return false;
		}
  		messageTipDiv.html("");
  		return true;
	}

	function confirmOrder(parentDialog){
		var checked = validateOrderParam();
		if(checked){
			var flowNoId = $("#id_input_flowNo");
	  		var flowNo = "";
	  		if(flowNoId){
	  			flowNo=flowNoId.val();
	  		}
			BootstrapDialog.show({
				title: "下单信息提示",
		    	message:  '请先扫码付款后再点确认下单哦，您的<br/><span class="text-danger">支付备注  -->订单流水号应填写：<strong>'+flowNo+'</strong></span>',
		    	size: BootstrapDialog.SIZE_SMALL,
		    	buttons: [{
	                label: '是的，我已付款',
	                cssClass : "btn-primary",
	                action: function(dialog){
	                	confirmOrderChecked=true;
	                	payOrder(parentDialog);
	                	dialog.close();
	                }
	            }, {
	                label: '返回扫码付款',
	                cssClass : "btn-danger",
	                action: function(dialog){
	                    	dialog.close();
	                	}
		            }]
			  });
		}
  	}
	
	/*function popTip(tip){
		BootstrapDialog.alert({
			type: BootstrapDialog.TYPE_DANGER,
			title: "下单信息提示",
	    	message:  tip,
	    	size: BootstrapDialog.SIZE_SMALL
		});
	}*/
	//执行下单提交
	function payOrder(parentDialog){
		var emailInput=$("#id_input_email");
  		var email = emailInput.val();
  		var mobileInput = $("#id_input_mobile");
  		var mobile = mobileInput.val();
  		var orderIdInput = $("#id_input_orderId");
  		var orderId = orderIdInput.val();
  		var payTypeInput = $("#id_input_payType");
  		var payType = payTypeInput.val();
  		var flowNoInput = $("#id_input_flowNo");
  		var flowNo = flowNoInput.val();
  		var checked = validateOrderParam();
		if(checked){
			axios({
	    	    method: 'post',
	    	    url: '/sz0099/ood/product/order/confirm',
	    	    data: 
	    	    	Qs.stringify({
	    	    	'email' : email,
	    	    	'mobile' : mobile,
	    	    	'id' : orderId,
	    	    	'payType' : payType,
	    	    	'flowNo' : flowNo
	    	    	})
	    	    
	    	})
	    	.then(function (response) {
	    	    
	    	    
	    	    BootstrapDialog.show({
	    			title: "课程下单结果",
	    	    	message: $('<div></div>').html(response.data),
	    	    	size: BootstrapDialog.SIZE_SMALL,
	    	    	buttons: [{
	                    label: '确定',
	                    cssClass : "btn-primary",
	                    action: function(dialog){
	                    	dialog.close();
	            			if(parentDialog){
	                    		parentDialog.close();
	                    	}
	                    },
	                }, {
	                    label: '关闭',
	                    cssClass : "btn-danger",
	                    action: function(dialog){
	                    	dialog.close();
	            			if(parentDialog){
	                    		parentDialog.close();
	                    	}
	                    }
	                }]
	    		});
	    	    
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	});
		
		}
		
	}
	
	

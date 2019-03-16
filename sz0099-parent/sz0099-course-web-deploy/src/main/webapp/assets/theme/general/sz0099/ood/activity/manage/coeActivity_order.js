function addUser(actOrderId, fieldId){
	var actOrderInput=$("#"+actOrderId);
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	if(actOrderInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
				var baseId=actOrderInput.val();
				var mainId=actOrderInput.attr("data-mainId");
				var url=actOrderInput.attr("data-addUserUrl");
				var uploadData={'baseId':baseId,'mainId':mainId};
				var actUserWrapperId=actOrderInput.attr("data-userWrapperId");
				var actUserWrapper=$("#"+actUserWrapperId);
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(actUserWrapper.length>0){
						actUserWrapper.append(response.data);
						layer.msg("已添加到最后一行");
						calTotalAmount(actOrderId);//计算费用
					}else{
						console.log("未配置data-userWrapperId");
					}
					field.attr("data-saved",0);//返回后，可以再次执行创建
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行创建
				});
		}
	}else{
		layer.msg("数据非法！actOrderId不存在");
	}
}
function calTotalAmount(actOrderId){
	var actOrderInput=$("#"+actOrderId);
	var userAmountId=actOrderInput.attr("data-userAmountId");
	var userAmount=$("#"+userAmountId).val();
	
	var actUserWrapperId=actOrderInput.attr("data-userWrapperId");
	var actUserWrapper=$("#"+actUserWrapperId);
	var userNum=actUserWrapper.find("li[id^='id_act_user_li']").length;
	var userNumId=actOrderInput.attr("data-userNumId");
	$("#"+userNumId).html(userNum);
	var totalAmountId=actOrderInput.attr("data-totalAmountId");
	var totalFen=userAmount*userNum;
	var totalYuan=changeF2Y(totalFen);
	$("#"+totalAmountId).html(totalYuan);
	console.log(totalYuan);
}

function deleteUser(actOrderId, fieldId, entityId){
	console.log(actOrderId);
	console.log(fieldId);
	console.log(entityId);
	var actOrderInput=$("#"+actOrderId);
	var field=$("#"+fieldId);
	
	var fieldShowTip=$("#"+fieldId+"_showTip");
	if(fieldShowTip.length>0){
		fieldShowTip.html('');
	}
	
	var saved=field.attr("data-saved");
	if(actOrderInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
				var baseId=actOrderInput.val();
				var mainId=actOrderInput.attr("data-mainId");
				var url=actOrderInput.attr("data-deleteUserUrl");
				var uploadData={'id':entityId, 'baseId':baseId,'mainId':mainId};
				//var actUserWrapperId=actOrderInput.attr("data-actUserWrapperId");
				//var actUserWrapper=$("#"+actUserWrapperId);
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(fieldShowTip.length>0){
						fieldShowTip.html(response.data);
						var success=fieldShowTip.find("input[id='id_hidden_common_success']").val();
						var respMsg=fieldShowTip.find("span[id='id_common_respMsg']").html();
						if(success && success==1){
							layer.msg("信息删除成功");
							field.remove();
							calTotalAmount(actOrderId);//计算费用
						}else{
							field.attr("data-saved",0);//返回后，可以再次执行
							field.removeAttr("disabled");
							layer.msg(respMsg);
						}
					}
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行创建
				});
		}
	}else{
		layer.msg("数据非法！actOrderId不存在");
	}
}


function payOrder(formId, fieldId){
	//id_act_form
	var formInput=$("#"+formId);
	console.log(formId);
	var field=$("#"+fieldId);
	
	if(formInput.length>0){
		var payurl=formInput.attr("data-payurl");
		formInput.attr("action",payurl);
		formInput.submmit();
	}
}

function confirmOrder(actOrderId, fieldId){
	var actOrderInput=$("#"+actOrderId);
	console.log(actOrderId);
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	var fieldShowTip=$("#"+fieldId+"_showTip");
	if(fieldShowTip.length>0){
		fieldShowTip.html('');
	}
	field.attr("disabled",true);
	if(actOrderInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
				var uploadData=getOrderData(actOrderId);
				var url=actOrderInput.attr("data-url");
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(fieldShowTip.length>0){
						fieldShowTip.html(response.data);
						var success=fieldShowTip.find("input[id='id_hidden_common_success']").val();
						var respMsg=fieldShowTip.find("span[id='id_common_respMsg']").html();
						if(success && success==1){
							console.log("===执行跳转，支付页面===");
							layer.msg("下单成功");
							var prePayUrl=actOrderInput.attr("data-prePayUrl");
							window.location.href=prePayUrl;
						}else{
							field.attr("data-saved",0);//返回后，可以再次执行
							field.removeAttr("disabled");
							layer.msg(respMsg);
						}
					}
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行
				    field.removeAttr("disabled");
				});
		}
	}else{
		layer.msg("数据非法！actOrderId不存在");
	}
}

function cancelOrder(actOrderId, fieldId){
	//取消订单的原因
	var msg="<p class='text-center text-danger'>确定要取消订单吗？<br/>本订单取消后，您不可以再报名本次活动了！</p>";
	BootstrapDialog.show({
		title: "取消订单",
    	message: $('<div></div>').html(msg),
    	type : BootstrapDialog.TYPE_DANGER,
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '确定取消',
            cssClass : "btn-danger",
            action: function(dialog){
            	//dialog.close();
            	doCancelOrder(actOrderId, fieldId, dialog);
            },
        }, {
            label: '关闭',
            cssClass : "btn-default",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
}

function doCancelOrder(actOrderId, fieldId,parentDialog){
	var actOrderInput=$("#"+actOrderId);
	console.log(actOrderId);
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	var fieldShowTip=$("#"+fieldId+"_showTip");
	if(fieldShowTip.length>0){
		fieldShowTip.html('');
	}
	field.attr("disabled",true);
	if(actOrderInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
			 	var actBtnWrapperId=actOrderInput.attr("data-btnWrapperId");
			 	var actBtnWrapper=$("#"+actBtnWrapperId);
				var uploadData=getOrderData(actOrderId);
				var url=actOrderInput.attr("data-url");
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(fieldShowTip.length>0){
						fieldShowTip.html(response.data);
						var success=fieldShowTip.find("input[id='id_hidden_common_success']").val();
						var respMsg=fieldShowTip.find("span[id='id_common_respMsg']").html();
						if(success && success==1){
							layer.msg("订单取消成功");
							actBtnWrapper.remove();
						}else{
							field.attr("data-saved",0);//返回后，可以再次执行
							field.removeAttr("disabled");
							layer.msg(respMsg);
						}
					}
					if(parentDialog){
						parentDialog.close();
					}
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行
				    field.removeAttr("disabled");
				});
		}
	}else{
		layer.msg("数据非法！actOrderId不存在");
	}
}

function getOrderData(actOrderId){
	var actOrderInput=$("#"+actOrderId);
	console.log(actOrderId);
	
	var baseId=actOrderInput.val();
	var mainId=actOrderInput.attr("data-mainId");
	
	var uploadData={"id":baseId,"mainId":mainId};
	var actUserWrapperId=actOrderInput.attr("data-userWrapperId");
	
	if(actUserWrapperId){
		var actUserWrapper=$("#"+actUserWrapperId);
		if(actUserWrapper.length>0){
			actUserWrapper.find("li[id^='id_act_user_li']").each(function(i,e){
				console.log(i);
				console.log(this);
				var realname=$(this).find("input[id^='id_act_user_realname']").val();
				console.log("realname:"+realname);
				var identity=$(this).find("input[id^='id_act_user_identity']").val();
				console.log("identity:"+identity);
				var mobile=$(this).find("input[id^='id_act_user_mobile']").val();
				console.log("mobile:"+mobile);
				
				var userId=$(this).find("input[id^='id_act_user_id']").val();
				console.log("userId:"+userId);
				var userUserId="userList["+i+"].id";
				uploadData[userUserId]=userId;
				
				var userRealname="userList["+i+"].realname";
				uploadData[userRealname]=realname;
				
				var userIdentity="userList["+i+"].identity";
				uploadData[userIdentity]=identity;
				
				var userMobile="userList["+i+"].mobile";
				uploadData[userMobile]=mobile;
			});
		}
	}
	console.log(uploadData);
	return uploadData;
}


function doOrderquery(fieldId){
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	var wrapperId=field.attr("data-wrapper");
	var wrapper=$("#"+wrapperId);
	if(wrapper.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
			 	var entityId=field.val();
				var uploadData={id:entityId};
				var url=field.attr("data-url");
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(wrapper.length>0){
						wrapper.html(response.data);
					}
					field.attr("data-saved",0);
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行
				});
		}
	}else{
		layer.msg("数据非法！data-wrapper不存在");
	}
}
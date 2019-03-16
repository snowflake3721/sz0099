$(document).ready(function(){
	$('#id_picker_orderBegin').datepicker({
		language: 'zh-CN',
		autoclose:true,
		defaultViewDate:'today',
		todayBtn:true
	}).on("show", function(e) {

	    }).on("change",function(){
			var begin = $("#id_picker_orderBegin").datepicker("getDate");
			console.log(begin);
	       setDatePickerEnd(begin);
		}).on("hide",function(){
			var id_picker_orderBegin=$('#id_picker_orderBegin');
			var beginValue=id_picker_orderBegin.val();
			if(''==beginValue || null==beginValue){
				beginValue=new Date();
			}
			id_picker_orderBegin.datepicker("setDate",beginValue);
			var beginDate = id_picker_orderBegin.datepicker("getDate");
			var endDateValue = $("#id_picker_orderEnd").val();
			var endDate=getTomorrow(beginDate);
			if(endDateValue){
				endDate=stringToDate(endDateValue);
				endDate = getAvailabeEndDate(beginDate, endDate);
			}
			$("#id_picker_orderEnd").datepicker("setDate",endDate);
			
			setDatePickerEnd(beginDate);
		});
	$('#id_picker_orderEnd').datepicker({
		language: 'zh-CN',
		autoclose:true
	});

	function initDatePicker(){
		var id_picker_orderBegin=$('#id_picker_orderBegin');
		var today = new Date();
		var beginValue=id_picker_orderBegin.val();
		if(''==beginValue || null==beginValue){
			beginValue=new Date();
			id_picker_orderBegin.datepicker("setDate",today);
		}
		
		var id_picker_orderEnd=$('#id_picker_orderEnd');
		var endValue=id_picker_orderEnd.val();
		if(''==endValue || null==endValue){
			var toDate = getTomorrow(today);
			id_picker_orderEnd.datepicker("setDate",toDate);
			setDatePickerEnd(today);
		}
		
	}

	function getTomorrow(date){
		console.log("----getTomorrow 1 -----: " + date);
		console.log("----getTomorrow 1.1 -----: " + (typeof date));
		console.log(date);
		if(date && typeof date == 'object'){
		console.log("----getTomorrow 2 -----: " + date);
		 return date.dateAdd('d',1);
		}
	}

	function setDatePickerEnd(begin){
		setDatePicker("id_picker_orderEnd", begin, "setStartDate","d", 1);
		setDatePicker("id_picker_orderEnd", begin, "setEndDate","m", 1);
	}

	function setDatePickerStart(begin){
		setDatePicker("id_picker_orderBegin", begin, "setStartDate","m", -1);
	}

	function setDatePicker(id_picker, begin, method, step, num){
		var end = stringToDate(begin).dateAdd(step,num);
		$("#"+id_picker).datepicker(method,end);
	}

	initDatePicker();



	function getAvailabeEndDate(begin, end){
		var between = daysBeginAndEnd(begin,end);
		var endDate = end;
		if(between<0){
			var d = Math.abs(between);
			if(d>31){
				endDate = begin.dateAdd('m',1);
			}else if(d<1){
				endDate = getTomorrow(begin);
			}
		}else{
			endDate = getTomorrow(begin);
		}
		return endDate;
	}
});

function queryPullCode(status, orderId){
	var checked= status && orderId && status>0 && orderId!='';
	if(status==0){
		popTipCustom("发生错误","该订单还未支付，请支付后操作");
	}
	if(!orderId || orderId==''){
		popTipCustom("发生错误","非法数据，请下单后操作");
	}
	
	if(checked){
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/order/queryPullCode',
    	    data: 
    	    	Qs.stringify({
    	    	'id' : orderId
    	    	})
    	})
    	.then(function (response) {
    	    
    	    
    	    BootstrapDialog.show({
    			title: "提取码查询结果",
    	    	message: $('<div></div>').html(response.data),
    	    	size: BootstrapDialog.SIZE_SMALL,
    	    	buttons: [{
                    label: '确定',
                    cssClass : "btn-primary",
                    action: function(dialog){
                    	dialog.close();
                    },
                }, {
                    label: '关闭',
                    cssClass : "btn-danger",
                    action: function(dialog){
                    	dialog.close();
                    }
                }]
    		});
    	    
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
	
}


function modifyEmail(status, orderId){
	var checked= status && orderId && status>0 && orderId!='';
	if(status==0){
		popTipCustom("发生错误","该订单还未支付，请支付后操作");
		return false;
	}else if(status>=3){
		popTipCustom("发生错误","该订单已发货，不能修改");
		return false;
	}
	
	if(!orderId || orderId==''){
		popTipCustom("发生错误","非法数据，请下单后操作");
		return false;
	}
	
	if(checked){
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/order/modifyEmail',
    	    data: 
    	    	Qs.stringify({
    	    	'id' : orderId
    	    	})
    	})
    	.then(function (response) {
    	    
    	    
    	    BootstrapDialog.show({
    			title: "订单验证结果",
    	    	message: $('<div></div>').html(response.data),
    	    	size: BootstrapDialog.SIZE_SMALL,
    	    	buttons: [{
                    label: '确定',
                    cssClass : "btn-primary",
                    action: function(dialog){
                    	doModifyEmail();
                    	//dialog.close();
                    },
                }, {
                    label: '关闭',
                    cssClass : "btn-danger",
                    action: function(dialog){
                    	dialog.close();
                    }
                }]
    		});
    	    
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
}

function doModifyEmail(){
	
	var orderId=$("#id_input_orderId").val();
	var status = $("#id_input_status").val();
	var checked= status && orderId && status>0 && orderId !='';
	var messageTipDiv=$("#id_div_messageTip");
	var tip= "";
	
	console.log("------1---orderId---"+orderId);
	console.log("------1---status---"+status);
	if(status==0){
		tip="该订单还未支付，请支付后操作";
		popTipCustom("发生错误",tip);
		messageTipDiv.html(tip);
		return false;
	}
	if(!orderId || orderId==''){
		tip="非法数据，请下单后操作";
		popTipCustom("发生错误",tip);
		messageTipDiv.html(tip);
		return false;
	}
	console.log("------2---checked---"+checked);
	var emailInput=$("#id_input_email");
	var email = emailInput.val();
	console.log("------2---email---"+email);
	var emailChecked = validateEmail(email, messageTipDiv);
	console.log("------2---emailChecked---"+emailChecked);
	if(!emailChecked){
		return false;
	}
	
	console.log("------3---emailChecked---"+emailChecked);
	console.log("------3---checked---"+checked);
	if(emailChecked && checked){
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/product/order/doModifyEmail',
    	    data: 
    	    	Qs.stringify({
    	    	'id' : orderId,
    	    	'status' : status,
    	    	'email' : email
    	    	
    	    	})
    	})
    	.then(function (response) {
    	    
    	    var id_hidden_successCode = $("#id_hidden_successCode");
    	    var successCode = id_hidden_successCode.val();
    	    BootstrapDialog.show({
    			title: "Email修改结果",
    	    	message: $('<div></div>').html(response.data),
    	    	size: BootstrapDialog.SIZE_SMALL,
    	    	buttons: [{
                    label: '确定',
                    cssClass : "btn-primary",
                    action: function(dialog){
                    	//dialog.close();
                    	var id_hidden_common_respCode=$("#id_hidden_common_respCode");
                    	var resultCode = id_hidden_common_respCode.val();
                    	console.log(successCode + " vs " + resultCode);
                    	if(successCode == resultCode){
                    		var id_span_email_show=$("#id_span_email_show_"+orderId);
                    		console.log(id_span_email_show);
                    		if(id_span_email_show.length>0){
                    			id_span_email_show.html(email);
                    		}
                    		BootstrapDialog.closeAll();
                    	}
                    },
                }, {
                    label: '关闭',
                    cssClass : "btn-danger",
                    action: function(dialog){
                    	dialog.close();
                    }
                }]
    		});
    	    
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
}


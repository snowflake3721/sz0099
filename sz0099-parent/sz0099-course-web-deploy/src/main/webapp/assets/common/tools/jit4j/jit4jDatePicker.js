function initDatePicker(inputId){
	$('#'+inputId).datepicker({
		language: 'zh-CN',
		autoclose:true,
		defaultViewDate:'today',
		todayBtn:true,
		"format":"yyyy-mm-dd"
	});
	
	var id_picker_expiredTime=$('#'+inputId);
	var today = new Date();
	var beginValue=id_picker_expiredTime.val();
	if(''==beginValue || null==beginValue){
		beginValue=new Date();
		id_picker_expiredTime.datepicker("setDate",today);
	}
}

function initMoreDateTimePicker(inputId){
	$("input[id^="+inputId+"]").each(function(){
		var id=$(this).attr("id");
		initDateTimePicker(id);
	})
}

function initDateTimePicker(inputId){
	
	$('#'+inputId).datetimepicker({
		language: 'zh-CN',
		autoclose:true,
		defaultViewDate:'today',
		todayBtn:true,
		minuteStep: 1,
		"format":"yyyy-mm-dd hh:ii"
	}).on("change",function(){
		mergeDate(inputId);
	});
	
}

function mergeDate(inputId){
	var input=$("#"+inputId);
	var showTipId=inputId+"_showTip";
	var showTipInput=$("#"+showTipId);
	if(showTipInput.length>0){
		showTipInput.addClass("hidden");
	}
	var url=input.attr("data-saveUrl");
	//var fieldName = $(this).attr("name");
	//var fieldDate = $("#"+inputId).val();
	var uploadData=getDatePickerDate(inputId);
	if(!uploadData.id){
		layer.msg("数据有误");
		return false;
	}
	console.log(uploadData);
	var data_saved=input.attr("data-saved");
	if(data_saved==0){
		input.attr("data-saved",1);
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
			console.log(response.data);
			
			input.attr("data-saved",0);
			if(showTipInput.length>0){
				showTipInput.html(response.data);
				var successInput=showTipInput.find("input[name='success']");
				if(successInput.length>0){
					var success=successInput.val();
					if(success!=1){
						showTipInput.removeClass("hidden");
					}
				}
				var msgInput=showTipInput.find("span[name='respMsg']");
				if(msgInput.length>0){
					var msg=msgInput.html();
					layer.msg(msg);
				}
			}
			
		})
		.catch(function (error) {
		    console.log(error);
		    input.attr("data-saved",0);
		});
	}
}

function getDatePickerDate(inputId){
	var input=$("#"+inputId);
	var id=input.attr("data-id");
	console.log(fieldDateSec);
	var uploadData={id:id};
	var fieldName = input.attr("name");
	var fieldDate = input.val();
	var fieldDateSec=fieldDate+":00";
	uploadData[fieldName]=fieldDateSec;
	
	var mainId = input.attr("data-mainId");
	if(mainId){
		uploadData['mainId']=mainId;
	}
	
	var baseId = input.attr("data-baseId");
	if(baseId){
		uploadData['baseId']=baseId;
	}
	console.log(uploadData);
	/*if(inputId=='id_picker_actBegin'){
		uploadData={"id" : id, "beginTime" : fieldDateSec};
	}else if(inputId=='id_picker_actEnd'){
		uploadData={"id" : id, "endTime" : fieldDateSec};
	}else if(inputId=='id_picker_offTime'){
		uploadData={"id" : id, "offTime" : fieldDateSec};
	}*/
	return uploadData;
}

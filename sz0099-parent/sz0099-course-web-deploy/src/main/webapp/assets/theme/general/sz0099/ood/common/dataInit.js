function initData(inputId){
	var field=$("#"+inputId);
	var url=field.attr("data-url");
	var input_showTip=$("#"+inputId+"_showTip");
	
	var data_saved=field.attr("data-saved");
	if(data_saved==0){
		field.attr("data-saved", 1);
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify({})
		})
		.then(function (response) {
			if(input_showTip.length>0){
				input_showTip.html(response.data);
				var successInput=input_showTip.find("input[name='success']");
				var msgInput=input_showTip.find("input[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					var msg=msgInput.val();
					layer.msg(msg);
				}
			}else{
				console.log("未配置_showTip");
			}
			field.attr("data-saved",0);//返回后，可以再次执行保存
		})
		.catch(function (error) {
		    console.log(error);
		    field.attr("data-saved",0);
		});
	}
}
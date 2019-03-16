function mergeForMainType(id, mainType, inputId){
	var dataInput=$("#"+inputId);
	if(dataInput.length>0){
		var currentId=dataInput.attr("data-id");
		var currentMainType=dataInput.attr("data-mainType");
		var url=dataInput.attr("data-url");
		var btnMainId=dataInput.attr("data-btnMainId");
		var btnMainUnId=dataInput.attr("data-btnMainUnId");
		if(currentMainType && mainType && mainType==currentMainType){
			popTip("已经设置过了，无需重复设置!");
			return false;
		}
		
		var uploadData={
    	    	'id' : id,
    	    	'mainType' : mainType
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
        	    	dataInput.attr("data-mainType", mainType);
	    	    	changeBtnForMainType(id, inputId, btnMainId, btnMainUnId);
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

function changeBtnForMainType(id, inputId, btnMainId, btnMainUnId){
	var dataInput=$("#"+inputId);
	if(dataInput.length>0){
		var mainType=dataInput.attr("data-mainType");
		if(mainType==9){
			//dataInput.attr("data-id", professionId);
			//将所有的重置一下
			$("button[id^='id_btn_btnMainId']").removeClass("hidden");
			$("button[id^='id_btn_un_btnMainId']").addClass("hidden");
			//把自身替换回来
			$("#"+btnMainId).addClass("hidden");
			$("#"+btnMainUnId).removeClass("hidden");
			
		}
	}
}
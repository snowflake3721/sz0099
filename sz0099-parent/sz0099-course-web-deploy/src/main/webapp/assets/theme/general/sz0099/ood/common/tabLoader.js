console.log("!!!in list!!!!!!!!!");

function loadDataForTab(panelId){
	var currentTab=$("#"+panelId);
	if(currentTab.length>0){
		var id = currentTab.attr("id");
		var loaded = currentTab.attr("data-loaded");
		var tabId = currentTab.attr("data-tabId");
		var tabWraperId = currentTab.attr("data-tabWrapperId");
		var url = currentTab.attr("data-url");
		console.log("====id====: "+ id);
		console.log("====url====: "+ url);
		var extraDataId = currentTab.attr("data-extraDataId");
		console.log("====tabWraperId: "+ tabWraperId + " tabId:"+tabId + " loaded:"+loaded + " extraDataId:"+extraDataId);
		var uploadData={};
		if(extraDataId){
			uploadData=extraUploadData(panelId, extraDataId);
		}
		if(loaded==1){
			//数据已加载
			return false;
		}
		
		//加载数据
		axios({
    	    method: 'post',
    	    url: url,
    	    data: 
    	    	Qs.stringify(uploadData)
    	})
    	.then(function (response) {
    	    console.log("---- photo loaded----");
    	    var tab = $("#"+tabId);
    	    if(tabWraperId){
	    	    var tabWraper = $("#"+tabWraperId);
	    	    if(tabWraper.length>0){
	    	    	tabWraper.html(response.data);
	    	    }else{
	    	    	tab.html(response.data);
	    	    }
    	    }else{
    	    	tab.html(response.data);
    	    }
    	    currentTab.attr("data-loaded",1);
    	})
    	.catch(function (error) {
    	    console.log(error);
    	});
	}
}

function extraUploadData(panelId, hiddenId){
	var hiddenIdInput=$("#"+hiddenId);
	var uploadData={};
	if(hiddenIdInput.length>0){
		var baseId = hiddenIdInput.val();
		var positionId = hiddenIdInput.attr("data-positionId");
		var mainId = hiddenIdInput.attr("data-mainId");
		var subId =  hiddenIdInput.attr("data-subId");
		console.log("---- extraUploadData preload 1----baseId: "+baseId+"positionId:" + positionId + " mainId:"+mainId+" subId:"+subId);
		uploadData={
	    		'baseId':baseId,//技能id
		    	'positionId' : positionId, //所属adaptor处理id
		    	'mainId' : mainId,//技能id
		    	'subId' : subId //段落id
	    }
	}
	return uploadData;
}

function refreshParagraph(articleId, url){
	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = currentPanel.val();
	loadData(currentPanelValue, url);
}

function refreshParagraphOrder(articleId, url, panelName){
	var currentPanel = $("#id_hidden_current_panel");
	var currentPanelValue = panelName;
	currentPanel.val(panelName);
}



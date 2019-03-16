	console.log("!!!in list!!!!!!!!!");
	
	function loadDataForTab(panelId){
		var currentTab=$("#"+panelId);
		if(currentTab.length>0){
			var id = currentTab.attr("id");
			var loaded = currentTab.attr("data-loaded");
			var tabId = currentTab.attr("data-tabId");
			var url = currentTab.attr("data-url");
			console.log("====id====: "+ id);
			console.log("====loaded====: "+ loaded);
			console.log("====tabId====: "+ tabId);
			console.log("====url====: "+ url);
			if(loaded==1){
				//数据已加载
				return false;
			}
			
			//加载数据
			axios({
	    	    method: 'post',
	    	    url: url,
	    	    data: 
	    	    	Qs.stringify({})
	    	})
	    	.then(function (response) {
	    	    console.log("---- photo loaded----");
	    	    $("#"+tabId).html(response.data);
	    	    currentTab.attr("data-loaded",1);
	    	})
	    	.catch(function (error) {
	    	    console.log(error);
	    	});
		}
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

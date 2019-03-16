function loadDataOnShow(collapseId, collapseContentId, inputLoadedId, msgTipId){
	var collapse=$("#"+collapseId);
	var inputLoaded=$("#"+inputLoadedId);
	collapse.on('show.bs.collapse', function () {
		var loaded = inputLoaded.val();
		if(loaded==0){
			console.log("------loaded--: " + loaded);
			loadRefList(collapseContentId,inputLoadedId, msgTipId);
		}
	})
	
}

function loadRefList(collapseContentId,inputLoadedId, msgTipId){
	var collapseContent=$("#"+collapseContentId);
	var inputLoaded=$("#"+inputLoadedId);
	var baseId=inputLoaded.attr("data-baseId");
	var extendId=inputLoaded.attr("data-extendId");
	var url=inputLoaded.attr("data-url");
	var page=inputLoaded.attr("data-page");
	var size=inputLoaded.attr("data-size");
	//var url="/sz0099/ood/position/ref/manage/findRefLsit";
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify({
    	    	'baseId' : baseId,
    	    	'extendId' : extendId,
    	    	'page':page,
    	    	'size':size
	    	})
	})
	.then(function (response) {
	    console.log("---- ref loaded----");
	    collapseContent.html(response.data);
	    //状态置为已加载
	    inputLoaded.val(1);
	    informUpdateThenRemove(msgTipId);
	})
	.catch(function (error) {
	    console.log(error);
	});
}

function reloadRefList(collapseContentId,inputLoadedId, msgTipId){
	loadRefList(collapseContentId,inputLoadedId, msgTipId);
}

$(function () {
	  $('[data-toggle="popover"]').popover()
})
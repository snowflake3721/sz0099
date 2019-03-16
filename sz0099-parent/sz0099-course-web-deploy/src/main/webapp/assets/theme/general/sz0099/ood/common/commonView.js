function detailPreview(url, id){
	if(!id){
		layer.msg("数据错误，请重新登录再试!");
	}
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify({
	    		'id' : id
	    	})
	})
	.then(function (response) {
	    
	    BootstrapDialog.show({
			title: "预览",
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
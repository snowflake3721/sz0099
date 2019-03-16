function addJoinItem(actTimeId, fieldId){
	var actTimeInput=$("#"+actTimeId);
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	if(actTimeInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
				var baseId=actTimeInput.val();
				var mainId=actTimeInput.attr("data-mainId");
				var url=actTimeInput.attr("data-addJoinItemUrl");
				var uploadData={'baseId':baseId,'mainId':mainId};
				var joinItemWrapperId=actTimeInput.attr("data-joinItemWrapperId");
				var joinItemWrapper=$("#"+joinItemWrapperId);
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					if(joinItemWrapper.length>0){
						joinItemWrapper.append(response.data);
						layer.msg("集合信息已添加到最后一行");
					}else{
						console.log("未配置data-joinItemWrapperId");
					}
					field.attr("data-saved",0);//返回后，可以再次执行创建
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行创建
				});
		}
	}else{
		layer.msg("数据非法！actTimeId不存在");
	}
}

function deleteJoinItem(actTimeId, fieldId, entityId){
	console.log(actTimeId);
	console.log(fieldId);
	console.log(entityId);
	var actTimeInput=$("#"+actTimeId);
	var field=$("#"+fieldId);
	var saved=field.attr("data-saved");
	if(actTimeInput.length>0){
		if(saved==0){
			 	field.attr("data-saved",1);
				var baseId=actTimeInput.val();
				var mainId=actTimeInput.attr("data-mainId");
				var url=actTimeInput.attr("data-deleteJoinItemUrl");
				var uploadData={'id':entityId, 'baseId':baseId,'mainId':mainId};
				//var joinItemWrapperId=actTimeInput.attr("data-joinItemWrapperId");
				//var joinItemWrapper=$("#"+joinItemWrapperId);
				axios({
				    method: 'post',
				    url: url,
				    data:Qs.stringify(uploadData)
				})
				.then(function (response) {
					field.attr("data-saved",0);//返回后，可以再次执行创建
					layer.msg("集合信息删除成功");
					field.remove();
				})
				.catch(function (error) {
				    console.log(error);
				    field.attr("data-saved",0);//返回后，可以再次执行创建
				});
		}
	}else{
		layer.msg("数据非法！actTimeId不存在");
	}
}
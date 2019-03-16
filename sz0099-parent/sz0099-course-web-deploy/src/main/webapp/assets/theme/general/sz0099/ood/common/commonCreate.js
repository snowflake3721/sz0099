function showSavedTip(success, savedTime){
	if(success==1){
		layer.msg("上次自动保存时间:"+savedTime);
	}else{
		layer.msg("自动保存失败，请备份数据，重新登录后手动保存！");
	}
}

var autoTitle=null;
var autoDescription=null;
var autoPenname=null;
//保存同一对象，注意时间不能设置成相同，避免并发保存
function autoTitleCommit(fieldId,entityId){
	console.log("----initAutoCommit-step4.1:"+fieldId);
	autoTitle=setInterval(doSaveTitle2,10*60*1000,fieldId,entityId);
}

function autoDescriptionCommit(fieldId,entityId){
	console.log("----initAutoCommit-step4.2:"+fieldId);
	autoDescription=setInterval(doSaveDescription2,3*60*1321,fieldId,entityId);
}

function clearAutoTitle(){
	if(null != autoTitle){
		clearInterval(autoTitle);
		console.log("----clearAutoTitle-step5.1");
		autoTitle=null;
	}
}
function clearAutoDescription(){
	if(null != autoDescription){
		clearInterval(autoDescription);
		console.log("----clearAutoDescription-step5.2");
		autoDescription=null;
	}
}
function clearAutoPenname(){
	if(null != autoPenname){
		clearTimeout(autoPenname);
		console.log("----clearAutoPenname-step5.3");
		autoPenname=null;
	}
}
/**
 * 初始无输入后保存，定时保存
 * @returns
 */
function initAutoCommit(){
	console.log("----initAutoCommit-step1");
	if(autoDescription == null){
		console.log("----initAutoCommit-step2");
		//绰号不启用异步更新
		panelTabClick('#panel_baseinfo',true, true,false);
	}
}
function panelTabClick(panel, titleEnable, descEnable, pennameEnable){
	if(panel=='#panel_baseinfo'){
		console.log("----initAutoCommit-step3");
  		var entityId = $("#id_hidden_id").val();
  		var titleInputId="id_title"+entityId;
  		var pennameInputId="id_penname"+entityId;
  		var descriptionInputId="id_area_description"+entityId;
  		if(titleEnable){
  			var fieldTitle=$("#"+titleInputId);
  			fieldTitle.bind("input propertychange",function(e){
  				delayTitle(titleInputId, entityId);
  			});
  		}
  		if(descEnable){
  			autoDescriptionCommit(descriptionInputId,entityId);
  			//watchDescription(descriptionInputId, entityId);
  			var fieldDesc=$("#"+descriptionInputId);
  			fieldDesc.bind("input propertychange",function(e){
  				delayDescription(descriptionInputId, entityId);
  			});
  		}
  		if(pennameEnable){
  			var fieldPenname=$("#"+pennameInputId);
  			fieldPenname.bind("input propertychange",function(e){
  				delayPenname(pennameInputId, entityId);
  			});
  		}
  	}else{
  		if(titleEnable){
  			clearAutoTitle();
  		}
  		if(descEnable){
  			clearAutoDescription();
  		}
  		if(pennameEnable){
  			clearAutoPenname();
  		}
  	}
	
}

var descriptionTm=null;
var titleTm=null;
var pennameTm=null;
function watchTitle(fieldId, entityId){
	showLength('id_title'+entityId, 1000, 'id_title'+entityId+'_length');
	//var field=$("#"+fieldId);
	//delayTitle(fieldId, entityId);
	/*field.bind("input propertychange",function(e){
		delayTitle(fieldId, entityId);
	});*/
}

function delayTitle(fieldId, entityId){
	if(null != titleTm){
		clearTimeout(titleTm);
	}
	titleTm=setTimeout(function(){
		doSaveTitle(fieldId, entityId);
	}, 22130);
}

function delayDescription(fieldId, entityId){
	console.log("delay desc call--");
	if(null != descriptionTm){
		clearTimeout(descriptionTm);
	}
	descriptionTm=setTimeout(function(){
		doSaveDescription(fieldId, entityId);
	}, 15000);
}

function delayPenname(fieldId, entityId){
	console.log("penname call--");
	if(null != pennameTm){
		clearTimeout(pennameTm);
	}
	pennameTm=setTimeout(function(){
		doSavePenname(fieldId, entityId);
	}, 17267);
}

function watchDescription(fieldId, entityId){
	showLength('id_area_description'+entityId, 1000, 'id_area_description'+entityId+'_length');
	//var field=$("#"+fieldId);
	//delayDescription(fieldId, entityId);
	/*field.bind("input propertychange",function(e){
		delayDescription(fieldId, entityId);
	});*/
}

function watchPenname(fieldId, entityId){
	showLength('id_penname'+entityId, 12, 'id_penname'+entityId+'_length');
}

function doSaveDescription(fieldId, entityId){
	console.log("10秒没输入，保存数据，desc");
	commitDescription(fieldId, entityId);
}
function doSaveDescription2(fieldId, entityId){
	console.log("4分钟闲置，保存数据，desc");
	commitDescription(fieldId, entityId);
}

function doSaveTitle(fieldId, entityId){
	console.log("16秒没输入，保存数据，title");
	commitTitle(fieldId, entityId);
}
function doSaveTitle2(fieldId, entityId){
	console.log("10分钟闲置，保存数据，title");
	commitTitle(fieldId, entityId);
}

function doSavePenname(fieldId, entityId){
	console.log("17秒没输入，保存数据，penname");
	commitPenname(fieldId, entityId);
}

function commitDescription(fieldId, entityId){
	showLength('id_area_description'+entityId, 1000, 'id_area_description'+entityId+'_length');
	
	var field=$("#"+fieldId);
	var url=field.attr("data-saveUrl");
	var data_saved=field.attr("data-saved");//监控是否正在执行保存动作，1时为正在保存
	var description_showTip=$("#id_area_description_showTip"+entityId);
	if(description_showTip.length>0){
		description_showTip.html('');
	}
	if(!entityId){
		layer.msg("数据错误，请先复制数据，以免丢失，重新登录再试!");
	}
	//var descriptionId="id_area_description"+entityId;
	var description=field.val();
	if(data_saved != 1 && description && description.length>0 && description.length<64){
		field.attr("data-saved",1);
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify({
		    		'id' : entityId,
		    		'description':description
		    	})
		})
		.then(function (response) {
			if(description_showTip.length>0){
				description_showTip.html(response.data);
				var successInput=description_showTip.find("input[name='success']");
				var msgInput=description_showTip.find("input[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					if(successVal==1){
						//layer.msg("概要已保存");
						console.log("概要已保存");
						clearTimeout(descriptionTm);
					}else{
						var msg=msgInput.val();
						layer.msg(msg);
					}
				}
				
			}else{
				console.log("未配置id_area_description_showTip");
			}
			field.attr("data-saved",0);//返回后，可以再次执行保存
		})
		.catch(function (error) {
		    console.log(error);
		    field.attr("data-saved",0);
		});
	}
}
function commitTitle(fieldId, entityId){
	showLength('id_title'+entityId, 32, 'id_title'+entityId+'_length');
	
	var field=$("#"+fieldId);
	var url=field.attr("data-saveUrl");
	
	var title_showTip=$("#id_title_showTip"+entityId);
	if(title_showTip.length>0){
		title_showTip.html('');
	}
	if(!entityId){
		layer.msg("数据错误，请先复制数据，以免丢失，重新登录再试!");
	}
	//var titleId="id_title"+entityId;
	var title=field.val();
	var data_saved=field.attr("data-saved");//监控是否正在执行保存动作，1时为正在保存
	
	if(data_saved!=1 && title && title.length>0 && title.length<64){
		field.attr("data-saved",1);
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify({
		    		'id' : entityId,
		    		'title':title
		    	})
		})
		.then(function (response) {
			if(title_showTip.length>0){
				title_showTip.html(response.data);
				var successInput=title_showTip.find("input[name='success']");
				var msgInput=title_showTip.find("input[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					if(successVal==1){
						//layer.msg("标题已保存");
						console.log("标题已保存");
						clearTimeout(titleTm);
					}else{
						var msg=msgInput.val();
						layer.msg(msg);
					}
				}
			}else{
				console.log("未配置id_title_showTip");
			}
			field.attr("data-saved",0);//返回后，可以再次执行保存
		})
		.catch(function (error) {
		    console.log(error);
		    field.attr("data-saved",0);//返回后，可以再次执行保存
		});
	}
}

function commitPenname(fieldId, entityId){
	showLength('id_penname'+entityId, 12, 'id_penname'+entityId+'_length');
	
	var field=$("#"+fieldId);
	var url=field.attr("data-saveUrl");
	
	var penname_showTip=$("#id_penname_showTip"+entityId);
	if(penname_showTip.length>0){
		penname_showTip.html('');
	}
	if(!entityId){
		layer.msg("数据错误，请先复制数据，以免丢失，重新登录再试!");
	}
	var penname=field.val();
	var data_saved=field.attr("data-saved");//监控是否正在执行保存动作，1时为正在保存
	
	if(data_saved!=1 && penname && penname.length>0 && penname.length<12){
		field.attr("data-saved",1);
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify({
		    		'id' : entityId,
		    		'penname':penname
		    	})
		})
		.then(function (response) {
			if(penname_showTip.length>0){
				penname_showTip.html(response.data);
				var successInput=penname_showTip.find("input[name='success']");
				var msgInput=penname_showTip.find("input[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					if(successVal==1){
						console.log("绰号已保存");
						clearTimeout(pennameTm);
					}else{
						var msg=msgInput.val();
						layer.msg(msg);
					}
				}
			}else{
				console.log("未配置id_penname_showTip");
			}
			field.attr("data-saved",0);//返回后，可以再次执行保存
		})
		.catch(function (error) {
		    console.log(error);
		    field.attr("data-saved",0);//返回后，可以再次执行保存
		});
	}
}

function commitPlace(fieldId, entityId){
	commitField(fieldId, entityId);
}

function initMoreFieldAutoCommit(fieldIdPre){
	$("textarea[id^='"+fieldIdPre+"']").each(function(){
		$(this).bind("input propertychange",function(e){
			var fieldId=$(this).attr("id");
			var entityId=$(this).attr("data-id");
			delayField(fieldId, entityId);
		});
	})
}
function initFieldAutoCommit(fieldId){
	$("textarea[id='"+fieldId+"']").bind("input propertychange",function(e){
			var fieldId=$(this).attr("id");
			var entityId=$(this).attr("data-id");
			delayField(fieldId, entityId);
	})
}

function watchField(fieldId, entityId){
	var field=$("#"+fieldId);
	var lengthId=fieldId+'_length';
	var allowedLength=field.attr("data-allowedLength");
	if(allowedLength && allowedLength>0){
		showLength(fieldId, allowedLength, lengthId);
	}else{
		layer.msg("未配置data-allowedLength!");
		return false;
	}
	//delayField(fieldId, entityId);
}

var fieldTm=null;
function delayField(fieldId, entityId){
	console.log("fieldTm call--");
	if(null != fieldTm){
		clearTimeout(fieldTm);
	}
	fieldTm=setTimeout(function(){
		commitField(fieldId, entityId);
	}, 5000);
}

function commitField(fieldId, entityId){
	var field=$("#"+fieldId);
	var lengthId=fieldId+'_length';
	
	if(!entityId){
		layer.msg("数据错误，请先复制数据，以免丢失!");
		return false;
	}
	
	var allowedLength=field.attr("data-allowedLength");
	if(allowedLength && allowedLength>0){
		showLength(fieldId, allowedLength, lengthId);
	}else{
		layer.msg("未配置data-allowedLength!");
		return false;
	}
	
	var url=field.attr("data-saveUrl");
	
	var showTip=$("#"+fieldId+"_showTip");
	if(showTip.length>0){
		showTip.html('');
	}else{
		var showTipId=fieldId+"_showTip";
		layer.msg("未配置 " + showTipId + " !");
		return false;
	}
	
	var uploadData={id:entityId}
	var fieldName=field.attr("data-singleName");
	if(!fieldName){
		fieldName=field.attr("name");
	}
	var fieldValue=field.val();
	uploadData[fieldName]=fieldValue;
	
	var mainId = field.attr("data-mainId");
	if(mainId){
		uploadData['mainId']=mainId;
	}
	
	var baseId = field.attr("data-baseId");
	if(baseId){
		uploadData['baseId']=baseId;
	}
	
	if(fieldValue && fieldValue.length>0 && fieldValue.length>allowedLength){
		layer.msg("数据长度超限! 只允许 【 " +allowedLength + " 】个字符!");
		return false;
	}
	
	var data_saved=field.attr("data-saved");//监控是否正在执行保存动作，1时为正在保存
	
	if(data_saved!=1 ){
		field.attr("data-saved",1);
		axios({
		    method: 'post',
		    url: url,
		    data:Qs.stringify(uploadData)
		})
		.then(function (response) {
			if(showTip.length>0){
				showTip.html(response.data);
				var successInput=showTip.find("input[name='success']");
				var msgInput=showTip.find("span[name='respMsg']");
				if(successInput.length>0){
					var successVal=successInput.val();
					console.log(successVal);
					if(successVal==1){
						console.log("已保存");
						clearTimeout(fieldTm);
						var msg=msgInput.html();
						layer.msg(msg);
						
					}else{
						var msg=msgInput.html();
						layer.msg(msg);
						showTip.removeClass('hidden');
					}
				}
			}else{
				console.log("未配置id_XXX_showTip");
			}
			field.attr("data-saved",0);//返回后，可以再次执行保存
		})
		.catch(function (error) {
		    console.log(error);
		    field.attr("data-saved",0);//返回后，可以再次执行保存
		});
	}
}
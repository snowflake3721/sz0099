function searchForBindedRefPage(page,collapseContentId,inputLoadedId, msgTipId){
	var inputLoaded=$("#"+inputLoadedId);
	//var page=inputLoaded.attr("data-page");
	var size=inputLoaded.attr("data-size");
	var baseId=inputLoaded.attr("data-baseId");
	var currentPage=$("#id_page_currentPage_bindedRef"+baseId).val();
	var totalPages=$("#id_page_totalPages_bindedRef"+baseId).val();
	//var size=$("#id_page_size_bindedRef"+baseId).val();
	
	if(page>0){
		if(currentPage && totalPages){
			var nextPage=parseInt(currentPage)+1;
			if(nextPage>=totalPages){
				popTip("页码超出范围");
				return false;
			}
			page=nextPage;
		}else{
			popTip("页码参数不合法！");
			return false;
		}
	}
	inputLoaded.attr("data-page",page);//页码重置
	inputLoaded.val(0);//数据加载重置
	loadRefList(collapseContentId,inputLoadedId, msgTipId);
}

/**
 * 条件搜索查询
 * @param page
 * @param refWrapperId
 * @param loadedId
 * @param panelListId
 * @param collapseContentId
 * @param inputLoadedId
 * @param msgTipId
 * @returns
 */
function searchForRefSelect(page, refWrapperId, loadedId, panelListId, collapseContentId,inputLoadedId, msgTipId){
	var loadedInput=$("#"+loadedId);
	
	var baseId=loadedInput.attr("data-id");
	var viewType=loadedInput.attr("data-viewType");
	var title=$("#id_search_title"+viewType+baseId+"_").val();
	var mainNo=$("#id_search_mainNo"+viewType+baseId+"_").val();
	loadedInput.attr("data-mainNo",mainNo);
	
	var totalPages=$("#id_page_totalPages"+viewType+baseId+"_").val();
	var currentPage=$("#id_page_currentPage"+viewType+baseId+"_").val();
	if(page>0){
		if(currentPage && totalPages){
			var nextPage=parseInt(currentPage)+1;
			if(nextPage>=totalPages){
				popTip("页码超出范围");
				return false;
			}
			page=nextPage;
		}else{
			popTip("页码参数不合法！");
			return false;
		}
	}
	loadedInput.attr("data-page",page);//页码重置
	
	console.log("-----search begin------");
	console.log(title);
	loadedInput.attr("data-title",title);
	loadedInput.val(0);//加载重置
	BootstrapDialog.closeAll();
	selectRefs(refWrapperId, loadedId, panelListId, collapseContentId,inputLoadedId, msgTipId);
	console.log("-----search end------");
}

/**
 * 弹框加载关联数据
 * @param refWrapperId
 * @param loadedId
 * @param panelListId
 * @param collapseContentId
 * @param inputLoadedId
 * @param msgTipId
 * @returns
 */
function selectRefs(refWrapperId, loadedId, panelListId,
		collapseContentId,inputLoadedId, msgTipId){
	
	var loadedInput=$("#"+loadedId);
	var loaded=loadedInput.val();
	var url=loadedInput.attr("data-url");
	var refreshLoadUrl=loadedInput.attr("data-reloadUrl");
	var baseId=loadedInput.attr("data-id");
	var positionId=loadedInput.attr("data-positionId");
	var mainId=loadedInput.attr("data-mainId");
	var viewType=loadedInput.attr("data-viewType");
	
	var page = loadedInput.attr("data-page");
	var size = loadedInput.attr("data-size");
	var title = loadedInput.attr("data-title");
	var mainNo = loadedInput.attr("data-mainNo");
	
	var panelList=$("#"+panelListId);
	//panelList.html("数据异步加载");
	
	var uploadData = {
			'baseId' : baseId, 
			'positionId' : positionId,
			'mainId' : mainId,
			'viewType' : viewType,
			'title' : title,
			'mainNo' : mainNo,
			'page' : page,
			'size' : size
	}
	
	var extendId=positionId;
	var requestUrl=refreshLoadUrl;
	
	if(loaded==0){
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
		    
		    panelList.html(response.data);
		    loadedInput.val(1);
		    
		    showRefWrapper(refWrapperId,collapseContentId, inputLoadedId,  msgTipId);
		    //移除通用元素
		    $("#id_hidden_common_success").remove();
		    $("#id_common_respMsg").remove();
		    $("#id_hidden_common_respCode").remove();
		})
		.catch(function (error) {
			//informUpdate(messageTipId);
		    console.log(error);
		});
	}else{
		showRefWrapper(refWrapperId,collapseContentId, inputLoadedId,  msgTipId);
	}
}

var currentRefWrapperDialog=null;
var currentRefWrapperId = null;
function showRefWrapper(refWrapperId,collapseContentId, inputLoadedId,  msgTipId){
	//获取隐藏数据
	var refWrapper=$("#"+refWrapperId).html();
	currentRefWrapperId=refWrapperId;
	currentRefWrapperDialog=BootstrapDialog.show({
		title: "关联列表",
    	message: $('<div id="divShowData"></div>').html(refWrapper),
    	size: BootstrapDialog.SIZE_SMALL,
    	onshown:function(){
    		$("#divShowData a[id^='id_art']").each(function(index,element){
    			console.log($(this).attr("href"));
    			var cur_href=$(this).attr("href");
    			$(this).attr("href",cur_href+"_");
    		})
    		$("#divShowData div[id^='id_art_content']").each(function(index,element){
    			console.log($(this).attr("id"));
    			var cur_id=$(this).attr("id");
    			$(this).attr("id",cur_id+"_");
    		})
    		
    		$("#divShowData input[id^='id_search']").each(function(index,element){
    			console.log($(this).attr("id"));
    			var cur_id=$(this).attr("id");
    			$(this).attr("id",cur_id+"_");
    		})
    		$("#divShowData input[id^='id_page']").each(function(index,element){
    			console.log($(this).attr("id"));
    			var cur_id=$(this).attr("id");
    			$(this).attr("id",cur_id+"_");
    		})
    		$("#divShowData a[id^='id_btn_bind']").each(function(index,element){
    			console.log($(this).attr("id"));
    			var cur_id=$(this).attr("id");
    			$(this).attr("id",cur_id+"_");
    		})
    		
    		
    	},
    	buttons: [{
            label: '确定',
            cssClass : "btn-primary",
            action: function(dialog){
            	closeRefsSelect( collapseContentId, inputLoadedId,  msgTipId);
            	BootstrapDialog.closeAll();
            },
        }, {
            label: '关闭',
            cssClass : "btn-danger",
            action: function(dialog){
            	closeRefsSelect( collapseContentId, inputLoadedId,  msgTipId);
            	BootstrapDialog.closeAll();
            }
        }]
	});
	//$("#"+refWrapperId).empty();
	
}

function reloadRefWrapper(refWrapperId){
	//获取隐藏数据
	var refWrapper=$("#"+refWrapperId).html();
	currentRefWrapperDialog.updateMessage($('<div id="divShowData"></div>').html(refWrapper));
	
	
}

function loadDataOnShowReload(collapseId){
	currentRefWrapperDialog.setMessage($('<div id="divShowData"></div>').html(""));
	reloadRefWrapper(currentRefWrapperId);
}

function changeRefBtn(operate, id, viewType, baseId, positionId, mainId, bindBtnId, unbindBtnId,url){
	if(operate=="add"){
		var url=$("#"+bindBtnId).attr("data-url");
		 $("#"+unbindBtnId).removeClass("hidden");
	    var deletePositionRef="deletePositionRef('"+id+"','"+viewType+"','"+baseId
	    +"','"+ positionId +"','"+ mainId +"','"+bindBtnId+"','"+unbindBtnId +"')";
	    $("#"+unbindBtnId).attr("onclick",deletePositionRef);
	    var data_wrapperId='id_li_positionRef_'+id;
	    $("#"+unbindBtnId).attr("data-wrapperId",data_wrapperId);
	    $("#"+bindBtnId).addClass("hidden");
	    
	    //修正弹框数据
	    $("#"+unbindBtnId+"_").removeClass("hidden");
	    $("#"+unbindBtnId+"_").attr("data-wrapperId",data_wrapperId);
	    $("#"+unbindBtnId+"_").attr("onclick",deletePositionRef);
	    $("#"+bindBtnId+"_").addClass("hidden");
	}else if(operate=="delete"){
		$("#"+unbindBtnId).addClass("hidden");
		var _spanIndex=unbindBtnId.lastIndexOf("_span");
		if(_spanIndex>0){
			var unBindBtn=unbindBtnId.substring(0,_spanIndex);
			$("#"+unBindBtn).addClass("hidden");
		}
		var data_wrapperId = $("#"+unbindBtnId).attr("data-wrapperId");
		var deleteItem=$("#"+data_wrapperId);
		if(deleteItem.length>0){
			deleteItem.remove();
		}
		$("#"+bindBtnId).removeClass("hidden");
		
		
		//修正弹框数据
	    $("#"+unbindBtnId+"_").addClass("hidden");
	    $("#"+bindBtnId+"_").removeClass("hidden");
	}
}

function searchRefsByViewType(){
	
}

function addPositionRef(viewType, baseId, positionId, mainId, bindBtnId, unbindBtnId,url){
	console.log(">>>addPositionRef...viewType>>" + viewType);
	console.log(">>>addPositionRef...positionId>>" + positionId);
	console.log(">>>addPositionRef...mainId>>" + mainId);
	console.log(">>>addPositionRef...bindBtnId>>" + bindBtnId);
	console.log(">>>addPositionRef...unbindBtnId>>" + unbindBtnId);
	var url=$("#"+bindBtnId).attr("data-url");
	
	var uploadData={
			'baseId' : baseId, 
			'positionId' : positionId,
			'extendId' : positionId,
			'mainId':mainId,
			'viewType':viewType
	}
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var content=response.data.content;
	    changeRefBtn("add",content.id, viewType, baseId, positionId, mainId, bindBtnId, unbindBtnId);
	    reloadRefWrapper(currentRefWrapperId);
	    //移除通用元素
	    $("#id_hidden_common_success").remove();
	    $("#id_common_respMsg").remove();
	    $("#id_hidden_common_respCode").remove();
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
	
}

function deletePositionRef(id, viewType, baseId,positionId, mainId, bindBtnId, unbindBtnId){
	var title=$("#id_p_title"+id).html();
	if(!title){
		//取弹出层的
		title=$("#id_p_title"+baseId+"_"+mainId).html();
	}
	var refWrapper="确定要移除【"+title+"】关联吗？<span class='text-danger'>移除后不可恢复</span>，<br/>若您将来还想启用该关联，您可以考虑使用【<span class='glyphicon glyphicon-eye-open' aria-hidden='true' >关闭</span>】按钮来代替移除。";
	BootstrapDialog.show({
		title: "移除关联",
    	message: $('<div></div>').html(refWrapper),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '确定',
            cssClass : "btn-danger",
            action: function(dialog){
            	doDeletePositionRef(id, viewType, baseId,positionId, mainId, bindBtnId, unbindBtnId);
            	dialog.close();
            },
        }, {
            label: '取消',
            cssClass : "btn-primary",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
}

function doDeletePositionRef(id, viewType, baseId,positionId, mainId, bindBtnId, unbindBtnId){
	console.log(">>>deletePositionRef...id" + id);
	console.log(">>>deletePositionRef...viewType" + viewType);
	console.log(">>>deletePositionRef...baseId" + baseId);
	console.log(">>>deletePositionRef...positionId" + positionId);
	console.log(">>>deletePositionRef...mainId" + mainId);
	console.log(">>>deletePositionRef...bindBtnId" + bindBtnId);
	console.log(">>>deletePositionRef...unbindBtnId" + unbindBtnId);
	var url=$("#"+unbindBtnId).attr("data-url");
	var uploadData={
			"id" : id,
			'baseId' : baseId, 
			'positionId' : positionId,
			'extendId' : positionId,
			'mainId':mainId,
			'viewType':viewType
	}
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var content=response.data.content;
	    changeRefBtn("delete",id, viewType, baseId, positionId, mainId, bindBtnId, unbindBtnId);
	    reloadRefWrapper(currentRefWrapperId);
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
}

function closeRefsSelect( collapseContentId, inputLoadedId, msgTipId){
	console.log(">>>closeRefsSelect...collapseContentId" + collapseContentId);
	console.log(">>>closeRefsSelect...inputLoadedId" + inputLoadedId);
	console.log(">>>closeRefsSelect...msgTipId" + msgTipId);
	loadRefList(collapseContentId,inputLoadedId, msgTipId)
}

/**
 * 开启or关闭关联文章
 * @param operate
 * @param id
 * @param viewType
 * @param baseId
 * @param positionId
 * @param mainId
 * @param openBtnId
 * @param closedId
 * @returns
 */
function changeOpenRefBtn(id,status, openBtnId, closedId){
	if(status==1){
		 $("#"+openBtnId).removeClass("hidden");
	     $("#"+closedId).addClass("hidden");
	}else if(status!=1){
		$("#"+closedId).removeClass("hidden");
	     $("#"+openBtnId).addClass("hidden");
	}
}

function openPositionRef(id, status, viewType, baseId,positionId, mainId, openBtnId, closedId){
	console.log(">>>openPositionRef...id" + id);
	console.log(">>>openPositionRef...viewType" + viewType);
	console.log(">>>openPositionRef...baseId" + baseId);
	console.log(">>>openPositionRef...positionId" + positionId);
	console.log(">>>openPositionRef...mainId" + mainId);
	console.log(">>>openPositionRef...openBtnId" + openBtnId);
	console.log(">>>openPositionRef...closedId" + closedId);
	
	var tip="关闭";
	if(status==1){
		tip="开启";
	}
	var title=$("#id_p_title"+id).html();
	var refWrapper = null;
	var refWrapper1 = "<p id='id_ref_tip'>确定要【"+tip+"】-"+title+"-关联吗？</p>"
	+"<div class='input-group input-group-sm'>"
	+"<span class='input-group-addon' id='id_addon_ref_remark'>备注</span>"
	+"<input type='text' class='form-control' aria-describedby='id_addon_ref_remark'"
  	+"value='' id='id_ref_remark'"
	+"placeholder='请填写备注'/></div>";
	BootstrapDialog.show({
		title: "开启或关闭关联",
    	message: $('<div></div>').html(refWrapper1),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '确定',
            cssClass : "btn-danger",
            action: function(dialog){
            	var remark=$("#id_ref_remark").val();
            	var messageTipId="id_ref_tip";
            	var checked=true;
            	if(status==2){
            		var remarkChecked = validateContentEmpty(remark, "关闭关联时，备注不能为空", messageTipId);
              		if(!remarkChecked){
              			checked=false;
              			return false;
              		}
            	}
            	if(checked){
            		doOpenPositionRef(id, status, remark, viewType, baseId,positionId, mainId, openBtnId, closedId,dialog);
            	}
            	//dialog.close();
            },
        }, {
            label: '取消',
            cssClass : "btn-primary",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
	
}

function doOpenPositionRef(id, status, remark, viewType, baseId,positionId, mainId, openBtnId, closedId, dialog){
	console.log(">>>openPositionRef...id" + id);
	console.log(">>>openPositionRef...status" + status);
	console.log(">>>openPositionRef...remark" + remark);
	console.log(">>>openPositionRef...viewType" + viewType);
	console.log(">>>openPositionRef...baseId" + baseId);
	console.log(">>>openPositionRef...positionId" + positionId);
	console.log(">>>openPositionRef...mainId" + mainId);
	console.log(">>>openPositionRef...openBtnId" + openBtnId);
	console.log(">>>openPositionRef...closedId" + closedId);
	var url=$("#"+openBtnId).attr("data-url");
	var uploadData={
			"id" : id,
			"status" : status,
			"remark" : remark,
			'baseId' : baseId, 
			'positionId' : positionId,
			'extendId' : positionId,
			'mainId':mainId,
			'viewType':viewType
	}
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var content=response.data.content;
	    changeOpenRefBtn(id, status, openBtnId, closedId);
	    if(dialog){
	    	dialog.close();
	    }
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
}

function saveSimpleSingle(id,url){
	var topLevel=$("#id_positionRef_topLevel"+id).val();
	if(id && topLevel && topLevel>=0){
		var uploadData={
				'id':id,
				'topLevel':topLevel
		}
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
		    
		    var content=response.data.content;
		})
		.catch(function (error) {
			//informUpdate(messageTipId);
		    console.log(error);
		});
	}
}

/**
 * 
 * @param collapseContentId 列表内容容器id
 * @param inputLoadedId 数据是否已加载
 * @param requestUrl 请求url
 * @param msgTipId 信息提示
 * @param baseId 参数,位置id
 * @param extendId 参数,扩展信息id
 * @returns
 */
function deleteRefByBaseId(collapseContentId,inputLoadedId, requestUrl, msgTipId, baseId, extendId){
	var title=$("#id_position_bind_name_show"+baseId).html();
	var refWrapper="确定要删除【"+title+"】所有关联文章吗？<span class='text-danger'>删除后不可恢复！！！</span>，<br/>若您将来还想启用该关联，您可以考虑使用【<span class='glyphicon glyphicon-eye-open' aria-hidden='true' >关闭</span>】按钮来代替移除。";
	BootstrapDialog.show({
		title: "删除所有关联",
    	message: $('<div></div>').html(refWrapper),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '确定',
            cssClass : "btn-danger",
            action: function(dialog){
            	doDeleteRefByBaseId(collapseContentId,inputLoadedId, requestUrl, msgTipId, baseId, extendId);
            	dialog.close();
            },
        }, {
            label: '取消',
            cssClass : "btn-primary",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
}
function doDeleteRefByBaseId(collapseContentId,inputLoadedId, requestUrl, msgTipId, baseId, extendId){
	if(baseId && extendId){
		var uploadData={
				'baseId':baseId,
				'extendId':extendId
		}
		axios({
		    method: 'post',
		    url: requestUrl,
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
		    //
		    //var content=response.data.content;
			reloadRefList(collapseContentId,inputLoadedId, msgTipId);
		})
		.catch(function (error) {
			//informUpdate(messageTipId);
		    console.log(error);
		});
	}
	//reloadRefWrapper(currentRefWrapperId);
}


function editPositionRef(id, editRefBtnId){
	var editRefBtn=$("#"+editRefBtnId);
	var queryUrl=editRefBtn.attr("data-queryUrl");
	var saveUrl=editRefBtn.attr("data-saveUrl");
	var uploadData={
			"id" : id
	}
	axios({
	    method: 'post',
	    url: queryUrl,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var content=response.data;
	    showEditPositionRef(content,id,saveUrl);
	    
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
}
function showEditPositionRef(content,id, saveUrl){
	BootstrapDialog.show({
		title: "编辑关联",
    	message: $('<div></div>').html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '保存',
            cssClass : "btn-danger",
            action: function(dialog){
            	mergePositionRef(id, saveUrl);
            	//dialog.close();
            },
        }, {
            label: '关闭',
            cssClass : "btn-primary",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
}

function validateForMerge(){
	var uploadData=getPositionRef(id);
	
}

function mergePositionRef(id, saveUrl){
//	var saveUrl=editRefBtn.attr("data-saveUrl");
	var uploadData=getPositionRef(id);
	var messageTipId="id_ref_tip"+id;
	var checked = true;
	if(uploadData.status==2){
		var remarkChecked = validateContentEmpty(uploadData.remark, "关闭关联时，备注不能为空", messageTipId);
  		if(!remarkChecked){
  			return false;
  		}
	}
	
	axios({
	    method: 'post',
	    url: saveUrl,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var content=response.data;
	    var respMsg=$('<div></div>').html(content);
	    $.showSuccessTimeout(respMsg, function(){
	    	var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    }
    	});
	    
	})
	.catch(function (error) {
		//informUpdate(messageTipId);
	    console.log(error);
	});
}

function getPositionRef(id){
	
	//var id=$("#id_ref_id"+id).val();
	var baseId=$("#id_ref_baseId"+id).val();
	var positionId=$("#id_ref_positionId"+id).val();
	var extendId=$("#id_ref_extendId"+id).val();
	var status=$("#hidden_id_ref_status"+id).val();
	var topLevel=$("#id_ref_topLevel"+id).val();
	var name=$("#id_ref_name"+id).val();
	var preIntro=$("#id_ref_preIntro"+id).val();
	var preIntroType=$("#hidden_id_ref_preIntroType"+id).val();
	var title=$("#id_ref_title"+id).val();
	var subTitle=$("#id_ref_subTitle"+id).val();
	var originalLink=$("#id_ref_originalLink"+id).val();
	var link=$("#id_ref_link"+id).val();
	var viewType=$("#id_ref_viewType"+id).val();
	var remark=$("#id_ref_remark"+id).val();
	var entity={
			"id":id,
			"baseId":baseId,
			"positionId":positionId,
			"extendId":extendId,
			"viewType":viewType,
			"status":status,
			"topLevel":topLevel,
			"name":name,
			"preIntro":preIntro,
			"preIntroType":preIntroType,
			"title":title,
			"subTitle":subTitle,
			"remark":remark,
			"originalLink":originalLink,
			"link":link
			
	}
	
	return entity;
}



function copyDataToEdit(extendId, id, topId, editWrapperId){
	$("#"+editWrapperId).removeClass("hidden");
	var subOrderSeq = $("#id_positionRef_orderSeq"+id).val();
	var subName = $("#id_positionRef_name"+id).val();
	var subCode = $("#id_positionRef_code"+id).val();
	var subParentId = $("#id_positionRef_parentId"+id).val();
	var parentName = $("#id_positionRef_parent_name"+id).val();
	
	
	$("#id_ref_orderSeq"+topId).val(subOrderSeq);
	$("#id_ref_name"+topId).val(subName);
	$("#id_ref_code"+topId).val(subCode);
	$("#id_ref_parentId"+topId).val(parentName);
	$("#hidden_id_ref_parentId"+topId).val(subParentId);
	$("#hidden_id_ref_id"+topId).val(id);
}

function getDataOfEdit(extendId, id,topId){
	var subOrderSeq=$("#id_ref_orderSeq"+topId).val();
	var subName=$("#id_ref_name"+topId).val();
	var subCode=$("#id_ref_code"+topId).val();
	var subParentId=$("#hidden_id_ref_parentId"+topId).val();
	var parentName=$("#id_ref_parentId"+topId).val();
	
	$("#id_positionRef_orderSeq"+id).val(subOrderSeq);
	$("#id_positionRef_name"+id).val(subName);
	$("#id_positionRef_code"+id).val(subCode);
	$("#id_positionRef_parentId"+id).val(subParentId);
	$("#id_positionRef_parent_name"+topId).val(parentName);
	var data=getCategory(id);
	/*var data={
			id : id,
			orderSeq : subOrderSeq,
			name : subName,
			code : subCode,
			parentId : subParentId,
			extendId : extendId
	};*/
	return data;
}

function editSingleSub(extendId,id, topId, url, divWrapperId, editWrapperId, messageTipId)
{
	copyDataToEdit(extendId, id, topId,editWrapperId);
}

function doEditSingleSub(extendId,id, topId, url,  messageTipId){
	var data = getDataOfEdit(extendId, id,topId);
	saveSingle(id, url, messageTipId);
	
}
function initParagraph(entityId){
	var messageTipId = "id_messageTip_paragraph"+entityId;
	var messageTipPreviewId="id_messageTip_preview";
	var photoPreviewId="id_parag_photoPreview";
	var maxFileCount = 5;
	var fileInputFilter="input[id^='id_file']";
	initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount);
}
function initParagraphForCover(entityId){
	var messageTipId = "id_messageTip_baseinfo"+entityId;
	var messageTipPreviewId="id_messageTip_preview";
	var photoPreviewId="id_cover_photoPreview";
	var maxFileCount = 2;
	var fileInputFilter="input[id^='id_cover_file']";
	initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount);
}

function initPositionRefForBanner(entityId){
	var messageTipId = "id_ref_messageTip"+entityId;
	var messageTipPreviewId="id_messageTip_preview";
	var photoPreviewId="id_ref_photoPreview";
	var maxFileCount = 3;
	var fileInputFilter="input[id^='id_cover_file']";
	initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount);
}
function initPositionRefForCover(entityId){
	var messageTipId = "id_ref_messageTip"+entityId;
	var messageTipPreviewId="id_messageTip_preview";
	var photoPreviewId="id_ref_photoPreview";
	var maxFileCount = 1;
	var fileInputFilter="input[id^='id_cover_file']";
	initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount);
}

function initParagraphForBanner(entityId){
	var messageTipId = "id_messageTip_baseinfo"+entityId;
	var messageTipPreviewId="id_messageTip_preview";
	var photoPreviewId="id_banner_photoPreview";
	var maxFileCount = 3;
	var fileInputFilter="input[id^='id_banner_file']";
	initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount);
}

function initFileUpload(fileInputFilter, messageTipId, messageTipPreviewId, photoPreviewId, maxFileCount){
	var allFileForParagId=fileInputFilter;
	if(!fileInputFilter){
		allFileForParagId="input[id^='id_file']";
	}
	console.log(allFileForParagId);
	$(allFileForParagId).each(function( index, val ) {
		
		var position = $(this).attr("data-position");
		var mainId = $(this).attr("data-mainId");
		var subId = $(this).attr("data-subId");
		var devId = $(this).attr("data-devId");
		var project = $(this).attr("data-project"); 
		var module = $(this).attr("data-module");
		var variety = $(this).attr("data-variety");
		var strategy = $(this).attr("data-strategy");
		
		var msgPreviewId=messageTipPreviewId+""+subId;
		var id_messageTip_preview=$("#"+msgPreviewId);
		var id_parag_photoPreview=$("#"+photoPreviewId+""+subId);
		//var messageTipId = "id_messageTip_paragraph";
		console.log(this);
		$(this).fileinput({
			language: 'zh', 
			hideThumbnailContent: false,
			browseClass: "btn btn-primary",
			retryErrorUploads: true,
			showPreview: false,
			showCaption: true,
	        showUpload: true,
	        showRemove: true,
	        autoOrientImage: true,
	        dropZoneEnabled: false,
	        maxFileCount: maxFileCount,
	        maxFileSize: 1024*10, 
	        mainClass: "input-group-sm",
	        'previewFileType':'any',
	        allowedFileTypes: ['image'],
	        uploadAsync: false,
	        uploadUrl: "/sz0099/ood/media/operate/upload",
	        uploadExtraData: function(){
	        	var currentSelfData={'devId': devId, "project":project, 'module': module, 'variety':variety, 'position' : position, 'strategy':strategy,'mainId':mainId, 'subId':subId, 'expectedW':300};
	        	console.log(currentSelfData);
	        	return currentSelfData;
	        }
	    }).on('filebatchpreupload', function(event, data) {
		    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		        if(!extra.mainId){
		        	alert("参数有误");
		        	return false;
		        }
		    	console.log(data.extra);
		    	console.log('File pre upload triggered');
		}).on('filebatchuploadsuccess', function(event, data) {
		         var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		         console.log(response);
		         var success=response.success;
		         if(1==success){
		        	id_messageTip_preview.removeClass("text-danger");
				    id_messageTip_preview.addClass("text-success");
				    id_messageTip_preview.html(response.respMsg);
				    if(response.content){
				    	var carray = response.content;
				    	for(var i=0;i<carray.length;i++){
				    		
				    		var media = getPreviewTemplateWithMedia(carray,i,msgPreviewId);
				    		
				    		var imageSrc = "<img src='"+carray[i].expectedUrl + "' class='file-preview-image' width='200px' >";
				    		
				    		id_parag_photoPreview.append(media);
				    		
				    	};
				    }
				    
				    
		         }else{
		        	 id_messageTip_preview.removeClass("text-success");
				     id_messageTip_preview.addClass("text-danger");
				     id_messageTip_preview.html(response.respMsg);
		         }
		         console.log('File batch upload success' + ": " +response);
		}).on('filebatchuploadcomplete', function(event, files, extra) {
		    console.log('File batch upload complete');
		}).on('filedeleted', function(event, key, jqXHR, data) {
		    console.log('Key = ' + key);	
		}).on('filebatchuploaderror', function(event, data, msg) {
		    var form = data.form, files = data.files, extra = data.extra,
		        response = data.response, reader = data.reader;
		    console.log('File batch upload error-----');
		    console.log(response);
		    id_messageTip_preview.removeClass("text-success");
		    id_messageTip_preview.addClass("text-danger");
		    id_messageTip_preview.html(response.respMsg);
		});
	
	});//end each
}

function getPreviewTemplateWithMedia(carray, i, messageTipId){
	var media = 
		"<div class='media' id='id_tpl_media_id"+ carray[i].id +"'>" +
		  "<div class='media-left'>" +
			"<a href='javascript:void(0)' onclick=\"showBigView('"+carray[i].expectedUrl+"',800,"+ carray[i].width +","+carray[i].id+")\">" + 
			  "<img id='id_tpl_media_common_img"+carray[i].id+"' class='media-object' width='200px' src='" +  carray[i].expectedUrl + "' alt='"+carray[i].id+"'>"+
			"</a>" + 
		  "</div>" + 
		  "<div class='media-body'>" + 
			"<input type='text' id='id_tpl_media_common_orderSeq"+carray[i].id+"' name='orderSeq' " + 
			"value='"+carray[i].orderSeq+"' class='form-control' onchange='keyPressPositive(this)' onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'" +
			"onblur='saveImageRef(\""+carray[i].id+"\",\""+messageTipId+"\")'" +
			"placeholder='数字排序'>" + 
			"<input type='text' id='id_tpl_media_common_title"+carray[i].id+"' name='title'" + 
			"value='' class='form-control' " +
			"onblur='saveImageRef(\""+carray[i].id+"\",\""+messageTipId+"\")'" +
			"placeholder='图片小标题'>" + 
			// "<p><button class='btn btn-xs btn-primary' type='button' onclick='saveImageRef(\""+carray[i].id+"\",\""+messageTipId+"\")'>保存</button> <p/>" + 
			"<p><button class='btn btn-xs btn-danger' type='button' onclick='deleteImageRef(\""+carray[i].id+"\",\""+messageTipId+"\")'>移除</button><p/>" + 
		  "</div>" +
		"</div>";
	return media;
}

function showBigView(viewUrl, expectedW, acturalW , aid){
	var titleHide=false;
	showBigViewCustom(viewUrl, expectedW, acturalW , aid, IMAGE_STRATEGY_1, titleHide);
}
function showBigViewOnly(viewUrl, expectedW, acturalW , aid){
	var titleHide=true;
	showBigViewCustom(viewUrl, expectedW, acturalW , aid, IMAGE_STRATEGY_1, titleHide);
}
function showBigViewSingle(viewUrl, expectedW, acturalW , aid){
	var titleHide=false;
	showBigViewCustom(viewUrl, expectedW, acturalW , aid, IMAGE_STRATEGY_2, titleHide);
}

function showBigViewCustom(viewUrl, expectedW, acturalW , aid, strategy, titleHide){
	var expectedUrl=viewUrl;
	if(''!= viewUrl){
		expectedUrl = getExpectedViewUrl(viewUrl, expectedW, acturalW,strategy);
	}
	var title = $("#id_tpl_media_common_title"+aid).val();
	popBigView(expectedUrl,title, titleHide);
}

/**
 * 保存图片小标题和序号
 * @param aid
 * @param imageRefId
 * @returns
 */
function saveImageRef(imageRefId,messageTipId){
	//var messageTipId = "id_messageTip_paragraph";
	var titleInput = $("#id_tpl_media_common_title"+imageRefId);
	var title = titleInput.val();
	var orderSeqInput = $("#id_tpl_media_common_orderSeq"+imageRefId);
	var orderSeq = orderSeqInput.val();
	if(typeof imageRefId == 'undefined'){
		return false;
	}
	orderSeq = checkOnlyNum(orderSeq);
	orderSeqInput.val(orderSeq);
		
	var uploadData={
    		"id" : imageRefId,
    		"title" : title,
    		"orderSeq" : orderSeq
    	}
	
	
	axios({
	    method: 'post',
	    url: '/sz0099/ood/media/operate/mergeImageRef',
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
	   // 
	    var respMsg=$('<div></div>').html(response.data);
	    
	    //$.showSuccessTimeout(respMsg, function(){
	    	var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
    	    if(successVal==1){
    	    	respMsg=commonMsg;
    	    	var id_messageTip_preview=$("#"+messageTipId);
    	    	id_messageTip_preview.removeClass("text-danger");
    	    	id_messageTip_preview.addClass("text-success");
    	    	id_messageTip_preview.html(commonMsg);
    	    }
    	    
	    	informUpdate(messageTipId);
	    	
    	//});
	    //移除通用元素
	    $("#id_hidden_common_success").remove();
	    $("#id_common_respMsg").remove();
	    $("#id_hidden_common_respCode").remove();
	    
	})
	.catch(function (error) {
		informUpdate(messageTipId);
	    console.log(error);
	});
	
}
function deleteImageRef(imageRefId, messageTipId){
	//var messageTipId = "id_messageTip_paragraph";
	var checked = validateDeleteImageRef(imageRefId);
	if(checked){
		var titleInput = $("#id_tpl_media_common_title"+imageRefId);
		var title = null;
		if(titleInput.length>0){
			title = titleInput.val();
		}
		var orderSeqInput = $("#id_tpl_media_common_orderSeq"+imageRefId);
		var orderSeq = null;
		var tip="您确定要<span class='text-danger'>删除图片 </span>吗？删除后不可恢复，请谨慎操作！"
		if(orderSeqInput.length>0){
			orderSeq = orderSeqInput.val();
			tip="您确定要删除<span class='text-danger'>序号为【"+orderSeq+"】的图片 "+title+" </span>吗？删除后不可恢复，请谨慎操作！"
		}
		
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除图片',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteImageRef( imageRefId, dialog, messageTipId);
                		
                	}else{
                		alert("数据有误，请刷新后重试！");
                		dialog.close();
                	}
                },
            }, {
                label: '取消',
                cssClass : "btn-primary",
                action: function(dialog){
                	informUpdate(messageTipId);
                	dialog.close();
                }
            }]
		});
	}
}

function validateDeleteImageRef(imageRefId){
	var titleInput = $("#id_tpl_media_common_title"+imageRefId);
	var title = titleInput.val();
	var orderSeqInput = $("#id_tpl_media_common_orderSeq"+imageRefId);
	var orderSeq = orderSeqInput.val();
	if(typeof imageRefId == 'undefined'){
		return false;
	}
	//orderSeq = checkOnlyNum(orderSeq);
	//orderSeqInput.val(orderSeq);
	return true;
}

function doDeleteImageRef( imageRefId, parentDialog, messageTipId){
	
	 //var messageTipId = "id_messageTip_paragraph";
	var checked = validateDeleteImageRef( imageRefId);
		if(checked){
		var uploadData={
	    		"id" : imageRefId
	    	}
		
		
		axios({
    	    method: 'post',
    	    url: '/sz0099/ood/media/operate/deleteImageRef',
    	    data: 
    	    	Qs.stringify(uploadData)
    	    
    	})
    	.then(function (response) {
    	    
    	    var respMsg=$('<div></div>').html(response.data);
    	    
    	    $.showSuccessTimeout(respMsg, function(){
    	    	var successVal = $("#id_hidden_common_success").val();
        	    var commonMsg = $("#id_common_respMsg").html();
        	    
        	    console.log("-----1-----");
        	    console.log(successVal);
        	    console.log("-----2-----");
        	    console.log(commonMsg);
        	    console.log("-----3-----");
        	    var id_tpl_media_id= $("#id_tpl_media_id"+imageRefId);
        	    if(successVal=="1"){
        	    	respMsg=commonMsg;
        	    	id_tpl_media_id.remove();
        	    	console.log("-----4-----");
        	    	var id_messageTip_preview=$("#"+messageTipId);
        	    	id_messageTip_preview.removeClass("text-danger");
        	    	id_messageTip_preview.addClass("text-success");
        	    	id_messageTip_preview.html(commonMsg);
        	    	console.log("-----4.5-----");
        	    	if(parentDialog){
        	    		parentDialog.close();
        	    	}
        	    	
        	    }
        	    
        	    console.log("-----5-----");
    	    	
    	    	informUpdate(messageTipId);
    	    	if(id_tpl_media_id.length>0){
    	    		id_tpl_media_id.remove();
    	    		console.log("-----6-----");
    	    	}
    	    	
    	    	console.log("-----7-----");
	    	});
    	    //移除通用元素
    	    $("#id_hidden_common_success").remove();
    	    $("#id_common_respMsg").remove();
    	    $("#id_hidden_common_respCode").remove();
    	    
    	})
    	.catch(function (error) {
    		informUpdate(messageTipId);
    	    console.log(error);
    	});
		}else{
			$.showErr("校验失败！");
		}
	
}



function informUpdate(panelId){
	var id_success=$("#id_hidden_common_success");
	    var successValue = id_success.val();
	    var id_common_respMsg=$("#id_common_respMsg");
	    var messageTip=$("#"+panelId);
	    var btn = $("#id_btn_confirm");
	   
	    
	    console.log(id_common_respMsg.html());
	    console.log("---successValue:" + successValue);
		messageTip.html(id_common_respMsg.html());
		if(successValue==1){
			console.log("---successValue==1:" + successValue==1);
			messageTip.removeClass("text-danger");
	  		messageTip.addClass("text-success");
		}else if(successValue==2){
			messageTip.removeClass("text-success");
	  		messageTip.addClass("text-danger");
		}
		btn.removeAttr("disabled");
}

function informUpdateThenRemove(panelId){
	var id_success=$("#id_hidden_common_success");
	    var successValue = id_success.val();
	    var id_common_respMsg=$("#id_common_respMsg");
	    var id_hidden_common_respCode=$("#id_hidden_common_respCode");
	    var messageTip=$("#"+panelId);
	    var btn = $("#id_btn_confirm");
	   
	    
	    console.log(id_common_respMsg.html());
	    console.log("---successValue:" + successValue);
		messageTip.html(id_common_respMsg.html());
		if(successValue==1){
			console.log("---successValue==1:" + successValue==1);
			messageTip.removeClass("text-danger");
	  		messageTip.addClass("text-success");
		}else if(successValue==2){
			messageTip.removeClass("text-success");
	  		messageTip.addClass("text-danger");
		}
		id_success.remove();
		id_common_respMsg.remove();
		id_hidden_common_respCode.remove();
		btn.removeAttr("disabled");
}
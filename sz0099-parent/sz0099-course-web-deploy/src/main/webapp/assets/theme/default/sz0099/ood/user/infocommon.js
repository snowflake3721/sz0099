function showSayword(nickname, word){
	showSaywordBaseAndPop(nickname, word, false);
}
function showSaywordOwn(nickname, word){
	showSaywordBaseAndPop(nickname, word, true);
}

function getSaywordGuide(){
	var tipDivContentGuide="<br/><p style='word-break: break-all;'><small>当你【发布】文章、【发布】技能、<span class='text-danger'>【参加活动】</span>、为他人【点赞】时，江湖上便会留下你的传说！在这之前，你要将你的<a href='javascript:void(0)' onclick='modifySayword()'>一句传说</a>添加上去哦！</small></p>";
	return tipDivContentGuide;
}

function showSaywordIndex(nickname, word, inputId){
	var tipDivContent = showSaywordBase(nickname, word, true);
	 tipDivContent+=getSaywordGuide();
	var tipDiv="<div style='width:300px;padding:15px'>"+tipDivContent+
	"</div>";
	//popTipCustom("一句传说", tipDiv);
	var title='一句传说 ';
	popSayword(tipDiv,title, inputId);
}

function popBigViewForSayword(nickname, word, imageSrc, inputId){
	var href=$("#"+inputId).attr("data-url");
	var hrefTitle=$("#"+inputId).attr("data-title-a");
	var title=$("#"+inputId).attr("data-title");
	var tipDivContent = showSaywordBase(nickname, word, true);
	tipDivContent+=getSaywordGuide();
	
	var tipDiv=getPopBigViewContent(imageSrc, inputId,tipDivContent);
	popSayword(tipDiv,title, inputId);
}

function getPopBigViewContent(imageSrc, inputId, tipDivContent){
	var href=$("#"+inputId).attr("data-url");
	var hrefTitle=$("#"+inputId).attr("data-title-a");
	var title=$("#"+inputId).attr("data-title");
	tipDivContent+=  "<img src='"+imageSrc + "' class='img-responsive' width='300px'/><p><a href='"+href+"'>"+hrefTitle+"</a></p>" ;
	var tipDiv="<div style='width:300px;padding:15px'>"+tipDivContent+ "</div>";
	return tipDiv;
}
function popSayword(tipDiv,title, inputId){
	BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DANGER,
        title : title,
        message : tipDiv,
        size : BootstrapDialog.SIZE_SMALL,
        buttons : [ {
            label : '进入圈里',
            cssClass : "btn-primary",
            action : function(dialogItself) {
            	if(inputId){
            		var url=$("#"+inputId).attr("data-url");
            		if(url){
            			window.location.href=url;
            		}
            	}
            	
                dialogItself.close();
            }
        },{
            label: '关闭',
            cssClass : "btn-danger",
            action: function(dialogItself){
            	dialogItself.close();
            }
        } ]
    });
}

function showSaywordBaseAndPop(nickname, word, self){
	var tipDivContent = showSaywordBase(nickname, word, self);
	var tipDiv="<div style='width:310px;padding:15px'>"+tipDivContent+
	"</div>";
	popTipCustom("一句传说", tipDiv);
}
function showSaywordBase(nickname, word, self){
	var tip="";
	if(nickname){
		tip+= "<span class='text-primary'>【"+nickname+"】</span>";
	}
	if(word){
		tip+="传说了一句：<div class='container'><hr/><br/><p class='text-center text-danger' style='word-break: break-all;'>"+ word + "</p></div>";
	}else{
		var selfTip="它的";
		if(self){
			selfTip="你的";
		}
		tip+="<p class='text-center text-danger'>可悲可叹，江湖上还没有"+selfTip+"传说!</p>";
	}
	var tipDivContent="<div style='width:310px;padding:15px'>"+tip+
		"</div>";
	return tipDivContent;
}

function showSaywordHistory(nickname, word, createdTime){
	showSaywordHistoryBase(nickname, word, createdTime, false);
}

function showSaywordHistoryBase(nickname, word, createdTime, self){
	var tip="";
	if(nickname){
		tip+= "<span class='text-primary'>【"+nickname+"】</span>";
	}
	if(word){
		tip+="曾经传说着：<hr/><br/><p class='text-center text-success'>"+ word + "</p>";
	}else{
		var selfTip="它的";
		if(self){
			selfTip="你的";
		}
		tip+="<p class='text-center text-success'>那年的江湖，还不曾有"+selfTip+"传说!</p>";
	}
	var tipDiv="<div style='width:330px;padding:15px'>"+tip+"<p class='text-right'> "+createdTime+"<p></div>";
	popTipCustomType("那时的传说", tipDiv, BootstrapDialog.TYPE_SUCCESS);
}


function validateSayword( id, descriptionId,messageTipId){
	var titleChecked = validateEmpty(id, "数据非法", messageTipId);
	if(!titleChecked){
		return false;
	}
	//sayword
	var titleChecked = validateEmpty(descriptionId, "传说不能为空", messageTipId);
	if(!titleChecked){
		return false;
	}
	
	var titleLengthChecked = validateLength(descriptionId, "你的传说太长了，都超60了", 60, messageTipId);
	if(!titleLengthChecked){
		return false;
	}
	
	var titleScriptChecked = validateScript(descriptionId, "传说中含有非法字符，自动过滤", messageTipId);
	if(!titleScriptChecked){
		return false;
	}
	return true;
}

function modifySayword(){
	var url="/sz0099/ood/product/personal/myinfo/modifySayword";
	var uploadData={};
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var respMsg=$('<div></div>').html(response.data);
	    BootstrapDialog.show({
			title: "修改一句传说",
	    	message: respMsg,
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '保存',
                cssClass : "btn-primary",
                action: function(dialog){
                	var messageTipId="id_messageTip_sayword";
                	var descriptionId="id_sayword";
                	var saywordId="id_sayword_id";
                	var checked = validateSayword(saywordId,descriptionId, messageTipId);
                	if(checked){
                		doModifySayword( saywordId,descriptionId, messageTipId);
                	}else{
                		//popTip("数据有误！");
                		//dialog.close();
                	}
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

function doModifySayword(idHidden, descriptionId,  messageTipId){
	var url="/sz0099/ood/product/personal/myinfo/doModifySayword";
	var sayword=$("#"+descriptionId).val();
	var id=$("#"+idHidden).val();
	var uploadData={'id':id,'description':sayword};
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var respMsg=$('<div></div>').html(response.data);
	    $.showSuccessTimeout(respMsg, function(){
	    	informUpdate(messageTipId);
	    	var successVal = $("#id_hidden_common_success").val();
    	    var commonMsg = $("#id_common_respMsg").html();
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

function popBigViewForDiv(divId){
	var respMsg=$("#"+ divId).html();
	var title=$("#"+divId).attr("data-title");
	var btnLabel=$("#"+divId).attr("data-btnConfirm");
	BootstrapDialog.show({
		title: title,
    	message: respMsg,
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
    		id:'id_btn_confirm',
            label: btnLabel,
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
}


function changeSingleField(id, uploadData, hiddensuccessCodeId,messageTipId, messageTip, url){
	var messageTipDiv=$("#"+messageTipId);
	axios({
	    method: 'post',
	    //url: '/sz0099/ood/product/personal/myinfo/doModifyNickname',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
	    
	    var id_hidden_successCode = $("#"+hiddensuccessCodeId);
	    var successCode = id_hidden_successCode.val();
	    BootstrapDialog.show({
			title: "修改结果",
	    	message: $('<div></div>').html(response.data),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定',
                cssClass : "btn-primary",
                action: function(dialog){
                	//dialog.close();
                	var id_hidden_common_respCode=$("#id_hidden_common_respCode");
                	var resultCode = id_hidden_common_respCode.val();
                	console.log(successCode + " vs " + resultCode);
                	var commonMsg = $("#id_common_respMsg").html();
                	if(successCode == resultCode){
                		if(messageTipDiv.length>0){
                			messageTipDiv.html(commonMsg);
                		}
                		BootstrapDialog.closeAll();
                	}
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

function initFilesForUser(){
	
	var allFilesForUser="input[id^='id_file']";
	console.log(allFilesForUser);
	$(allFilesForUser).each(function( index, val ) {
		
		var position = $(this).attr("data-position");
		var mainId = $(this).attr("data-mainId");
		var subId = $(this).attr("data-subId");
		var devId = $(this).attr("data-devId");
		var project = $(this).attr("data-project"); 
		var module = $(this).attr("data-module");
		var variety = $(this).attr("data-variety");
		var strategy = $(this).attr("data-strategy");
		
		var messageTipId="id_messageTip_preview"+subId;
		var id_messageTip_preview=$("#"+messageTipId);
		var id_parag_photoPreview=$("#id_parag_photoPreview"+subId);
		
		
		var expectedW = 1024;
		if(subId==3){
			expectedW=80;
		}else if(subId==4){
			expectedW=300;
		}
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
	        maxFileCount: 1,
	        maxFileSize: 1024*10, 
	        mainClass: "input-group-sm",
	        'previewFileType':'any',
	        allowedFileExtensions: ["jpg", "png", "gif"],
	        uploadAsync: false,
	        uploadUrl: "/sz0099/ood/media/operate/upload/single",
	        uploadExtraData: function(){
	        	var currentSelfData={'devId': devId, "project":project, 'module': module, 'variety':variety, 'position' : position, 'strategy':strategy, 'mainId':mainId, 'subId':subId, 'expectedW':expectedW};
	        	console.log(currentSelfData);
	        	return currentSelfData;
	        }
	    }).on('filebatchpreupload', function(event, data) {
		    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		    	
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
				    		var media = "";
				    		if(variety=="personal" && position=="identity" && subId==3){
				    			media = "<img src='"+carray[i].expectedUrl + "' class='img-rounded' width='60px' >";
				    			id_parag_photoPreview.html(media);
				    		}else{
				    			media = getPreviewTemplateWithMediaUser(carray[i],i, subId, messageTipId);
				    			id_parag_photoPreview.append(media);
				    		}
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

function getPreviewTemplateWithMediaUser(carray,i, subId, messageTipId){
	var media = 
		"<div class='media' id='id_tpl_media_id"+ carray.id +"'>" +
		  "<div class='media-left'>" +
			"<a href='javascript:void(0)' onclick=\"showBigViewSingle('"+carray.expectedUrl+"',1024,"+ carray.width +","+carray.id+")\">" + 
			  "<img id='id_tpl_media_common_img"+i+"' class='media-object' width='250px' src='" +  carray.expectedUrl + "' alt='"+carray.id+"'>"+
			"</a>" + 
		  "</div>" + 
		  "<div class='media-body'>" + 
			"<p><button class='btn btn-xs btn-danger' type='button' onclick='deleteImageRef(\""+carray.id+"\",\"" + messageTipId + "\")'>移除</button><p/>" + 
		  "</div>" +
		"</div>";
	return media;
}


function showInviteQrCode(wrapperId,contentId){
	var wrapper=$("#"+wrapperId);
	var dataSrc=wrapper.attr("data-src");
	var url=wrapper.attr("data-url");
	
	var loaded=wrapper.find("img").length;
	if(loaded>0){
		popInviteQrCode(wrapperId);
	}else{
		if(!dataSrc){
			console.log("should call generate>>>>");
			var uploadData={};
			axios({
			    method: 'post',
			    url: url,
			    data: 
			    	Qs.stringify(uploadData)
			    
			})
			.then(function (response) {
				var inviteQrCodeContent=$("#"+contentId);
				inviteQrCodeContent.html(response.data);
			    var success = false;
			    popInviteQrCode(wrapperId);
		    	var successVal = $("#id_hidden_common_success").val();
	    	    var commonMsg = $("#id_common_respMsg").html();
	    	    if(successVal==1){
	    	    	success=true;
	    	    }
	    	    //移除通用元素
	    	    $("#id_hidden_common_success").remove();
	    	    $("#id_common_respMsg").remove();
	    	    $("#id_hidden_common_respCode").remove();
			})
			.catch(function (error) {
			    console.log(error);
			});
		}else{
			popInviteQrCode(wrapperId);
		}
	}
}

function popInviteQrCode(divId){
	var respMsg=$("#"+ divId).html();
	var title=$("#"+divId).attr("data-title");
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: title,
    	message: $("<div</div>").html(respMsg),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '关闭',
            cssClass : "btn-danger",
            action: function(dialog){
            	dialog.close();
            }
        }]
	});
}
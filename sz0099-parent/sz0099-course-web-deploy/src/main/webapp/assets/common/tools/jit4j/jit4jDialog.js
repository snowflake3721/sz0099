function centerModals(dialog) {   
	offsetCenterModals(dialog,0);
}
function offsetCenterModals(dialog, offset) {   
	　　var $modal=dialog.getModal();
		var $modal_dialog = dialog.getModalDialog();
		var mheight=$modal.height();
		var m_top = ( $modal.height() - $modal_dialog.height() )/2;
		var mwidth=$modal.width();
		console.log("mwidth："+mwidth);
		console.log("mheight："+ mheight);
		if(m_top<10){
			m_top=10;
		}else if(m_top>105 && offset<95){
			m_top=m_top-offset;
		}
		console.log(m_top);
		$modal_dialog.animate({'top': m_top},200); 
	}
//弹出错误提示的登录框
$.showErr = function(str, func) {
    // 调用show方法
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DANGER,
        title : '错误 ',
        message : str,
        size : BootstrapDialog.SIZE_SMALL,//size为小，默认的对话框比较宽
        buttons : [ {// 设置关闭按钮
            label : '关闭',
            action : function(dialogItself) {
                dialogItself.close();
            }
        } ],
        // 对话框关闭时带入callback方法
        onhide : func
    });
};
//confirm确认选择框
$.showConfirm = function(str, funcok, funcclose) {
    BootstrapDialog.confirm({
        title : '确认',
        message : str,
        type : BootstrapDialog.TYPE_WARNING, // <-- Default value is
        // BootstrapDialog.TYPE_PRIMARY
        closable : true, // <-- Default value is false，点击对话框以外的页面内容可关闭
        draggable : true, // <-- Default value is false，可拖拽
        btnCancelLabel : '取消', // <-- Default value is 'Cancel',
        btnOKLabel : '确定', // <-- Default value is 'OK',
        btnOKClass : 'btn-warning', // <-- If you didn't specify it, dialog type
        size : BootstrapDialog.SIZE_SMALL,
        // 对话框关闭的时候执行方法
        onhide : funcclose,
        callback : function(result) {
            // 点击确定按钮时，result为true
            if (result) {
                // 执行方法
                funcok.call();
            }
        }
    });
};

//Success提示框
$.showSuccessTimeoutReload = function(str, func) {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_SUCCESS,
        title : '成功 ',
        message : str,
        size : BootstrapDialog.SIZE_SMALL,
        buttons : [ {
            label : '确定',
            action : function(dialogItself) {
                dialogItself.close();
            }
        } ],
        // 指定时间内可自动关闭
        onshown : function(dialogRef) {
            setTimeout(function() {
            	var currentUrl=window.location.href;
            	window.location.href=currentUrl;
                dialogRef.close();
            }, 2000);
        },
        onhide : func
    });
};

$.showSuccessTimeout = function(str, func) {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_SUCCESS,
        title : '成功 ',
        message : str,
        size : BootstrapDialog.SIZE_SMALL,
        buttons : [ {
            label : '确定',
            action : function(dialogItself) {
                dialogItself.close();
                func();
            }
        } ],
        // 指定时间内可自动关闭
        onshown : function(dialogRef) {
            setTimeout(function() {
                dialogRef.close();
            }, 2000);
        },
        onhide : func
    });
};

function popTip(tip){
	BootstrapDialog.alert({
		type: BootstrapDialog.TYPE_DANGER,
		title: "出错信息提示",
    	message:  tip,
    	size: BootstrapDialog.SIZE_SMALL,
    	onshown:function(dialogItself){
    		offsetCenterModals(dialogItself,90);
	    	}
	});
}
function popTipCustom(title, tip){
	var type=BootstrapDialog.TYPE_DANGER;
	popTipCustomType(title, tip, type);
}

function popTipCustomType(title, tip, type){
	var typeV=BootstrapDialog.TYPE_DANGER;
	if(type){
		typeV=type;
	}
	BootstrapDialog.alert({
		type: typeV,
		title: title,
    	message:  tip,
    	size: BootstrapDialog.SIZE_SMALL,
    	onshown:function(dialogItself){
    		centerModals(dialogItself);
    	}
	});
}

function popBigView(imageSrc,tip, titleHide){
	popBigViewCustom(imageSrc,tip, titleHide, false);
}
function popBigViewCustom(imageSrc,tip, titleHide, downloadHide){
	
	var tipV="大图";
	if(tip){
		tipV=tip;
	}
	var content =  "<img src='"+imageSrc + "' class='img-responsive' alt='"+tipV+"'>" ;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: tipV,
    	message:  $("<div id='imgContent'></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons : [ {
    		cssClass : "btn-primary",
            label : '关闭',
            action : function(dialogItself) {
                dialogItself.close();
            }
        },{
        	id:"btnDownload",
    		cssClass : "btn-danger",
            label : '下载原图',
            action : function(dialogItself) {
            	var btn=dialogItself.getButton("btnDownload");
            	$(btn).attr('disabled',true);
            	var ori=getOriUrl(imageSrc);
            	var oriContent =  "<img id='id_img_ori' src='"+ori + "' class='img-responsive' alt='"+tipV+"'/>" ;
            	$("#imgContent").html(oriContent);
            	$(btn).html('加载中...');
                console.log('image load begin---')  
                $("#id_img_ori").bind("load", function () {
                	$(btn).html('已下载');
                    console.log('image loaded---'); 
                });
            	//dialogItself.close();
            }
        } ],
    	onshown:function(dialogItself){
    		if(titleHide){
    			$(".modal-header").addClass('hidden');
    			console.log("add .modal-header hidden---");
    		}
    		if(downloadHide){
    			var btn=dialogItself.getButton("btnDownload");
    			$(btn).addClass('hidden');
    		}
    	}
	});
}

function popBigViewOnly(imageSrc,tip, titleHide){
	
	var tipV="预览";
	if(tip){
		tipV=tip;
	}
	var content =  "<img src='"+imageSrc + "' class='img-responsive' alt='"+tipV+"'>" ;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: tipV,
    	message:  $("<div id='imgContent'></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons : [ {
    		cssClass : "btn-primary",
            label : '关闭',
            action : function(dialogItself) {
                dialogItself.close();
            }
        } ],
    	onshown:function(dialogItself){
    		if(titleHide){
    			$(".modal-header").addClass('hidden');
    			console.log("add .modal-header hidden---");
    		}
    	}
	});
}

function popBigViewEdit(imageSrc,tip, titleHide){
	
	var tipV="编辑";
	if(tip){
		tipV=tip;
	}
	var content =  "<img src='"+imageSrc + "' class='img-responsive' alt='"+tipV+"'>" ;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: tipV,
    	message:  $("<div id='imgContent'></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons : [{
    		cssClass : "btn-info",
            label : '更换',
            action : function(dialogItself) {
                dialogItself.close();
            }
        }, {
    		cssClass : "btn-danger",
            label : '移除',
            action : function(dialogItself) {
                dialogItself.close();
            }
        }, {
    		cssClass : "btn-default",
            label : '关闭',
            action : function(dialogItself) {
                dialogItself.close();
            }
        } ],
    	onshown:function(dialogItself){
    		if(titleHide){
    			$(".modal-header").addClass('hidden');
    			console.log("add .modal-header hidden---");
    		}
    	}
	});
}

var paragDescTm=null;
function delayParagDesc(dialogItself, uploaderInputId, id, descriptionId, orderId){
	if(null != paragDescTm){
		clearTimeout(paragDescTm);
	}
	var needClose=false;
	paragDescTm=setTimeout(function(){
		console.log("---delayParagDesc exe---");
		doMergeForEdit(dialogItself,needClose,uploaderInputId, id, descriptionId, orderId);
	}, 15000);
}

function popForEdit( tip, uploaderInputId, id, descriptionId, orderId){
	var tipV="编辑内容";
	if(tip){
		tipV=tip;
	}
	var descriptionArea=$("#"+descriptionId);
	var descriptionAreaTmp=$("#"+descriptionId+"_tmp");
	var descriptionIdLength=descriptionId+'_length';
	var descriptionIdLengthTmp=descriptionIdLength+'_tmp';
	var description = "<div class='row'><div class='col-xs-12' id='id_div_wrapper'><div class='pull-right help-tip'><span class='glyphicon glyphicon-info-sign' onclick='showInstruction(\"id_help_font_size\",\"帮助-字号\")'><i class='w-e-icon-header help-tip-i'></i></span>&nbsp;<span class='glyphicon glyphicon-info-sign' onclick='showInstruction(\"id_help_align\",\"帮助-对齐\")'><i class='w-e-icon-paragraph-left help-tip-i'></i></span></div><div id='id_div_description'>"
		+ descriptionArea.val()
		+"</div></div></div>" +
		"<p class='text-right'>字数：<span id='"+descriptionIdLengthTmp+"'></span>/3000</p>";
	
	var content =  description;
	var clickBtn='no';
	var editor = null;
	
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: tipV,
    	message:  $("<div style='z-index:8000'></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	closable:false,
    	draggable:true,
    	buttons : [{
    		cssClass : "btn-primary",
            label : '保存并关闭',
            action : function(dialogItself) {
            	clickBtn='no';
            	if(editor){
	            	var html=editor.txt.html();
	        		descriptionArea.val(html);
	        		descriptionAreaTmp.val($(html).text());
	            	doMergeForEdit(dialogItself,true,uploaderInputId, id, descriptionId, orderId);
	                //dialogItself.close();
            	}
            }
        },{
    		cssClass : "btn-danger",
            label : '保存',
            action : function(dialogItself) {
            	clickBtn='no';
            	if(editor){
	            	var html=editor.txt.html();
	        		descriptionArea.val(html);
	        		descriptionAreaTmp.val($(html).text());
	            	doMergeForEdit(dialogItself,false,uploaderInputId, id, descriptionId, orderId);
	                //dialogItself.close();
            	}
            }
        },  {
    		cssClass : "btn-default",
            label : '取消',
            action : function(dialogItself) {
            	clickBtn='yes';
                dialogItself.close();
            }
        } ],
    	onshown:function(dialogItself){
    		var E = window.wangEditor;
    		editor = new E('#id_div_description');
    		editor.customConfig.menus = [
    		    'emoticon',  // 表情
    			'head',  // 标题
    		    //'bold',  // 粗体
    		    //'fontSize',  // 字号
    		    'justify'  // 对齐方式
    		    //'foreColor',  // 文字颜色
    		   // 'backColor'  // 背景颜色
    		];
    		editor.customConfig.zIndex = 7000;
    		editor.customConfig.onchange = function (html) {
    		    // 监控变化，同步更新到 textarea
    		    descriptionArea.val(html);
    		    descriptionArea.attr("data-saved",0);
    		    var content=$(html).text();
        		descriptionAreaTmp.val(content);
    		    //countContentLengthWithoutHtml(html,3000, descriptionIdLength);
    		    countContentLength(content,3000, descriptionIdLength);
    		    countContentLength(content,3000, descriptionIdLengthTmp);
    		    
    		    console.log("---call bind---"+html.length);
    			delayParagDesc(dialogItself, uploaderInputId, id, descriptionId, orderId);
    		}
    		editor.create();
    		var initHtml=editor.txt.html();
    		descriptionArea.val(initHtml);
    		var content=$(initHtml).text();
    		descriptionAreaTmp.val(content);
    		countContentLength(content,3000, descriptionIdLengthTmp);
    		//绑定自动保存
    		/*descriptionArea.bind("input propertychange",function(e){
    			console.log("---bind---");
    			delayParagDesc(dialogItself, uploaderInputId, id, descriptionId, orderId);
  			});*/
    		//centerModals(dialogItself);
    	},
    	onhide:function(){
    		var descriptionInput=$("#"+descriptionId);
    		var saved=descriptionInput.attr("data-saved");
    		if(saved==0 && clickBtn!='yes'){
    			doMergeForEdit(null,false,uploaderInputId, id, descriptionId, orderId);
    		}
    	}
	});
}

function doMergeForEdit(parentDialog,needClose, uploaderInputId, id, descriptionId, orderId){
	var urlDataInput=$("#"+uploaderInputId);
	var url=urlDataInput.attr("data-mergeRefDescUrl");
	var descriptionInput=$("#"+descriptionId);
	var description=descriptionInput.val();
	var descriptionIdLength=descriptionId+'_length';
	var descriptionIdLengthTmp=descriptionIdLength+'_tmp';
	var content=$(description).text();
	var length=countContentLength(content,3000, descriptionIdLengthTmp);
	if(length>3000){
		layer.msg("字数已超限！");
		return false;
	}
	var orderInput=$("#"+orderId);
	var order=orderInput.val();
	var uploadData={'id':id, 'description':description,'orderSeq':order};
	if(!id){
		layer.msg("数据有误");
		return false;
	}
	axios({
	    method: 'post',
	   // url: '/sz0099/ood/article/parag/mergeRefForDescription',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
		console.log();
		var msg="段落编号："+order+"，内容保存成功";
		layer.msg(msg);
		descriptionInput.attr("data-saved",1);
		if(parentDialog && needClose){
			parentDialog.close();
		}
		countContentLength(content,3000, descriptionIdLength);
		if(null != paragDescTm){
			clearTimeout(paragDescTm);
		}
	})
	.catch(function (error) {
	    console.log(error);
	});
}

function popForIndex(title, tip, nextStep){
	var type=BootstrapDialog.TYPE_SUCCESS;
	var nextStepV="即将进入..."
	if(nextStep){
		nextStepV=nextStep;
	}
	var tipV="<div style='width:330px;padding:20px'><p class='text-success text-center'>"+
		tip+
		"</p><br/><p class='text-center text-danger'>"+nextStepV+"</p></div>";
	popTipCustomType(title, tipV, type);
}


//显示操作说明
function showInstruction(divId,title){
	var div_showInstruction = $("#"+divId);
	var content = div_showInstruction.html();
	//console.log(content);
	BootstrapDialog.show({
		title: title,
    	message:  $('<div></div>').html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: '确定',
            cssClass : "btn-primary",
            action: function(dialog){
            	dialog.close();
            }
        }, {
            label: '关闭',
            cssClass : "btn-danger",
            action: function(dialog){
                	dialog.close();
            	}
            }]
	  });
}

function showFriendTip(title,divId){
	var div_showInstruction = $("#"+divId);
	var content = div_showInstruction.html();
	var titleV="友情提示";
	if(title){
		titleV=title;
	}
	BootstrapDialog.show({
		title: titleV,
    	message:  $('<div></div>').html(content),
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

function showTag(title,divId){
	var div_wrapper = $("#"+divId);
	var div_content = $("#"+divId+"_content");
	var div_relative = $("#"+divId+"_relative");
	var div_relative_ul = $("#"+divId+"_relative_ul");
	if(div_wrapper.length>0 && div_relative.length>0){
		var data_loaded=div_relative.attr("data-loaded");
		console.log(data_loaded);
		if(data_loaded==0){
			console.log("call find relative article...");
			var url = div_relative.attr("data-url");
			var name=div_relative.attr("data-name");
			var mainId=div_relative.attr("data-mainId");
			var uploadData={'name': name, 'mainId':mainId};
			axios({
			    method: 'post',
			    url: url,
			    data: 
			    	Qs.stringify(uploadData)
			})
			.then(function (response) {
				console.log(response);
				div_relative_ul.append(response.data);
				div_relative.attr("data-loaded",1);
				doShowTag(title,divId);
			})
			.catch(function (error) {
			    console.log(error);
			});
		}else{
			//已加载过，直接显示
			doShowTag(title,divId);
		}
	}else{
		doShowTag(title,divId);
	}
}

function doShowTag(title,divId){
	var div_wrapper = $("#"+divId);
	var content = div_wrapper.html();
	var titleV="标签导航";
	if(title){
		titleV=title;
	}
	BootstrapDialog.show({
		title: titleV,
    	message:  $('<div></div>').html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons: [{
            label: "去看标签墙 <span class='glyphicon glyphicon-hand-right'></span>",
            cssClass : "btn-primary",
            action: function(dialog){
                console.log("跳至标签墙页面");	
            	//dialog.close();
                	developing();
            	}
            },{
                label: '关闭',
                cssClass : "btn-danger",
                action: function(dialog){
                    	dialog.close();
                	}
         }],
         onshown:function(dialogItself){
        	 offsetCenterModals(dialogItself,80);
     	}
	  });
}

//修改显示文字
function onChangeShow(id, value, length){
	if(typeof value =='undefined'){
		$("#"+id).html(value);
	}
	if(typeof length =='undefined'){
		$("#"+id).html(value);
	}
	if(length>0 && ''!=value && value.length>0){
		var vl = value.length;
		if(vl>length){
			var nv = value.substring(0,length)+"...";
			$("#"+id).html(nv);
		}else{
			$("#"+id).html(value);
		}
	}
	
}

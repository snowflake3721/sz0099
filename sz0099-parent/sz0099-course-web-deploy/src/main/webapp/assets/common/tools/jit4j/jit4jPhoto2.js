var CLICKTYPE_IMG_TEXT=0;
var CLICKTYPE_IMG_ONLY=1;
var CLICKTYPE_IMG_TEXT_COPY=2;
var CLICKTYPE_IMG_CHANGE=3;
var CLICKTYPE_TEXT_ONLY=4;

//设置压缩图片的最大高度
var MAX_HEIGHT = 1200;
var imgarr=[];
function render(src,picname) {
    // 创建一个 Image 对象
    var image = new Image();
    // 绑定 load 事件处理器，加载完成后执行
    image.onload = function() {
        // 获取 canvas DOM 对象
        var canvas = document.createElement("canvas");
        // 如果高度超标
        if (image.height > MAX_HEIGHT && image.height >= image.width) {
            // 宽度等比例缩放 *=
            image.width *= MAX_HEIGHT / image.height;
            image.height = MAX_HEIGHT;
        }
       //考录到用户上传的有可能是横屏图片同样过滤下宽度的图片。
        if (image.width > MAX_HEIGHT && image.width > image.height) {
            // 宽度等比例缩放 *=
            image.height *= MAX_HEIGHT / image.width;
            image.width = MAX_HEIGHT;
        }

        // 获取 canvas的 2d 画布对象,
        var ctx = canvas.getContext("2d");
        // canvas清屏，并设置为上面宽高
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        // 重置canvas宽高
        canvas.width = image.width;
        canvas.height = image.height;
        // 将图像绘制到canvas上
        ctx.drawImage(image, 0, 0, image.width, image.height);
        // !!! 注意，image 没有加入到 dom之中
		//document.getElementById('img').src = canvas.toDataURL("image/png");
        var blob = canvas.toDataURL("image/jpeg");
        //将转换结果放在要上传的图片数组里
        imgarr.push({"pic":blob,"picname":picname});
    };
    image.src = src;
    var uploadData={'files':imgarr};
    doUpload2(uploadData);
};
//到这里我们图片压缩的代码就结束了。我们再加上，一个事件控制把需要上传的图片数组传给后台就行啦。

function doUpload2(uploadData){
	$.ajax({
		   "url":"/sz0099/ood/media/operate/upload/compress",
		   "type":"post",
		   "dataType":"json",
		   "data":imgarr,
		   "success":function(res){
			   console.log("----doUpload2----");
		   }
		});
}
function doUpload(uploadData){
	console.log("----doUpload----");
	axios({
	    method: 'post',
	    url: '/sz0099/ood/media/operate/upload/compress',
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
		console.log(response.data);
	    var respMsg=$('<div></div>').html(response.data);
	    $.showSuccessTimeout(respMsg, function(){
	    	var successVal = $("#id_hidden_common_success").val();
		    var commonMsg = $("#id_common_respMsg").html();
		    if(successVal==1){
		    	respMsg=commonMsg;
		    	var id_messageTip_preview=$("#"+messageTipId);
		    	id_messageTip_preview.removeClass("text-danger");
		    	id_messageTip_preview.addClass("text-success");
		    	id_messageTip_preview.html(commonMsg);
		    }
		});
	    //移除通用元素
	    $("#id_hidden_common_success").remove();
	    $("#id_common_respMsg").remove();
	    $("#id_hidden_common_respCode").remove();
	    
	})
	.catch(function (error) {
	    console.log(error);
	});
}
//id_wrapper_uploaderFiles
//uploaderInput
function initUpload(uploaderInputId, uploaderFilesWrapperId){
		mui.init();
        $gallery = $("#gallery"),
        $galleryImg = $("#galleryImg"),
        $uploaderFiles = $("#"+uploaderFilesWrapperId);
        
        var $uploaderInput = $("#"+uploaderInputId);
        $uploaderInput.on("click", function(e, clickType, positionDivId) {
        	console.log("======click======!!!!");
        	var type=$uploaderInput.attr("data-type");
        	if(clickType){
        		if(CLICKTYPE_IMG_TEXT_COPY==clickType){
        			if(positionDivId){
        				$uploaderInput.attr("data-loadPosition", positionDivId);
                	}
        			$uploaderInput.attr("data-refId","");
        			var url=$uploaderInput.attr("data-createRefUrl");
        			$uploaderInput.attr("data-requestUrl",url);
        		}else if(CLICKTYPE_IMG_ONLY==clickType){
        			var url=$uploaderInput.attr("data-addImageRefUrl");
        			$uploaderInput.attr("data-requestUrl",url);
        		}else if(CLICKTYPE_IMG_CHANGE==clickType){
        			var url=$uploaderInput.attr("data-changeImageRefUrl");
        			$uploaderInput.attr("data-requestUrl",url);
        		}
        		$uploaderInput.attr("data-clickType",clickType);
        		//$uploaderInput.attr("data-type",type);
        	}else{
        		var url=$uploaderInput.attr("data-createRefUrl");
    			$uploaderInput.attr("data-requestUrl",url);
    			$uploaderInput.attr("data-refId","");
    			$uploaderInput.attr("data-clickType",CLICKTYPE_IMG_TEXT);
    			//$uploaderInput.attr("data-type",type);
        	}
        });
        
        $uploaderInput.on("change", function(e) {
        	console.log("---begin load from local---");
        	var fileimg=this.files; 
        	if(fileimg.length>0){
	        	layer.msg("文件读取中...."); 
	    		var extraData=getExtendData(uploaderInputId);
	        	console.log(extraData);
	        	var clickType = $(this).attr("data-clickType");
		        //获取所选图片的列表对象
		     	//查看已经选择的图片数量
		        var arrnum=imgarr.length;
		     	//获取所有图片的数量
		        var num=arrnum+fileimg.length;
		    	//我们这里是限制了50张
		        if(num>99){
		   		//该处借用了layerjs的弹出层模块
		            layer.msg("最多上传 99 张图片");
		            return false;
		        }
		        console.log("arrnum:"+imgarr.length);
		        console.log("fileimg.length:"+fileimg.length);
		        console.log("total:"+num);
		    	//循环取出本次选择的图片
		        for(var i =0;i<fileimg.length;i++){    
		        console.log("===== fileimg : " + i + " =====");
		            /*图片转Base64 核心代码*/  
		            var file = fileimg[i];  
		            //这里我们判断下类型如果不是图片就返回中断上传，也可以continue直接过滤掉该文件
		            if (!/image\/\w+/.test(file.type)) {  
		                layer.msg("请确保文件为图像类型");  
		                return false;  
		            }
		            if(clickType==CLICKTYPE_IMG_ONLY && i>0){
		            	//如果是仅添加图片，则只处理第一张图片，忽略其余的
		            	break;
		            }
		            //创建一个文件读取的工具类
		            var reader = new FileReader(); 
		            
		            //这里利用了闭包的特性，来保留文件名
		            (function(x){
		                reader.onload = function (e) {
		                	//将读取到图片流直接拼接起来
		                	//var str='<li class="weui-uploader__file " style="background-image:url('+this.result+')"><span class="remove" style="color:red">&nbsp;X</span></li>';
		                	console.log("---000----");
		                	console.log(extraData);
		                	var guid=BootstrapDialog.newGuid();
		                	resize(uploaderInputId, this.result,x, extraData, i,fileimg.length, uploaderFilesWrapperId, guid);
		                	//塞到页面节点里                    
		                	//$("#uploaderFiles").append(str);
		                	//调用压缩文件的方法，具体实现逻辑见下面
		                	//render(this.result,x);
		                }  
			            })(file.name) 
			           //告诉文件读取工具类读取那个文件
			            reader.readAsDataURL(file);  
		        	}
        	}
	    });
	    var index; //第几张图片
	    $uploaderFiles.on("click", "img", function() {
	        index = $(this).index();
	        var img=$(this);//.find("img");
	        var imageSrc=img.attr("src");
	        var imageId=img.attr("id");
	        var refId=img.attr("data-refId");
	        var type=img.attr("data-type");
	        var imgWrapperId=img.attr("data-imgWrapperId");
	        console.log("img.id="+imageId);
	        if(type==0){
	        	$uploaderInput.attr("data-refId",refId);
	        	popBigViewEditForRef(imageSrc,'', uploaderInputId, refId,imageId);
	        }else{
	        	$uploaderInput.attr("data-loadPosition",imgWrapperId);
	        	$uploaderInput.attr("data-refId",refId);
	        	addRefForImage(uploaderInputId,imgWrapperId);
	        }
	        //$galleryImg.html(img);
	        //$galleryImg.attr("style", this.getAttribute("style"));
	        //$gallery.fadeIn(100);
	    });
	    $gallery.on("click", function() {
	        $gallery.fadeOut(100);
	    });
	    //删除图片
	    $(".weui-gallery__del").click(function() {
	        $uploaderFiles.find("li").eq(index).remove();
	    });
}

function resize(uploaderInputId,imgUrl,originalFilename,extraData, index, fileLength,uploaderFilesWrapperId, guid){
	popProgress(index, originalFilename, guid);//弹出上传进度
	progressReset(guid);//上传进度初始化
	//console.log(extraData);
	/* 压缩图片 */
    lrz(imgUrl, {
        width: 1600, //设置压缩参数
        height:1200,
        quality:1,
        fieldName:'file'
    }).then(function (rst) {
        /* 处理成功后执行 */
       // rst.formData.append('base64img', rst.base64); // 添加额外参数
    	console.log("guid:"+guid);
    	rst.formData.append('base.original', originalFilename); // 原文件名
    	if(extraData){
    		console.log("====3333----");
    		if(typeof extraData == 'object'){
    			console.log("====444----");
    			for(var key in extraData) {
    			     console.log(key+":"+extraData[key]);
    			     rst.formData.append(key,extraData[key]);
    			}
    		}
    	}
    	var uploadData=rst.formData;
    	var uploaderInput=$("#"+uploaderInputId);
    	var url=uploaderInput.attr("data-requestUrl");
    	
    	$.ajax({
    		//url: "/sz0099/ood/article/parag/create",
            url: url,
            type: "POST",
            data: uploadData,
            processData: false,
            contentType: false,
            dataType: 'html', //返回值类型 一般设置为json
            xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数  
        		myXhr = $.ajaxSettings.xhr();  
	            if(myXhr.upload){ //检查upload属性是否存在  
	                //绑定progress事件的回调函数  
	                myXhr.upload.addEventListener('progress',function(event){
	                	progressHandlingFunction(event, guid);
	                }, false);   
	            }  
	            return myXhr; //xhr对象返回给jQuery使用 
            },
            success: function (data) {
            	//var uploaderFilesWrapper=$("#"+uploaderFilesWrapperId);
            	var wrapperId=uploaderInput.attr("data-loadPosition");
            	var wrapper=$("#"+wrapperId);
            	//var uploaderFilesWrapper=$("#"+uploaderFilesWrapperId);
            	if(fileLength>0){
            		fileLength--;
            	}
            	var clickType = uploaderInput.attr("data-clickType");
            	var contentData=$(data);
            	if(contentData){
            		var respImg=contentData.find("img");
            		if(respImg && respImg.length>0){
            			console.log("========== img src replace begin ===");
        				respImg.attr("src",imgUrl);//将wrapper渲染的图片加载本地路径
        				var length=respImg.attr("src").length;
        				console.log("========== img src replaced, length:"+length);
            		}
            	}
            	var contentDataHtml=contentData.prop("outerHTML");
            	if(clickType==CLICKTYPE_IMG_TEXT){
            		if(wrapperId ==uploaderFilesWrapperId || wrapperId==''){
                		wrapper.append(contentDataHtml);//在最后添加
                		layer.msg("图片已添加至最后");
                	}
            	}else if(clickType==CLICKTYPE_IMG_TEXT_COPY){
            		wrapper.after(contentDataHtml);//相邻添加
            		layer.msg("图片添加成功");
            	}else if(clickType==CLICKTYPE_IMG_CHANGE){
            		//此时为仅替换图片
            		wrapper.html(contentDataHtml);
            		var imgContent= $("#imgContent");
         		    if(imgContent.length>0){
         		    	imgContent.find("img").attr("src",imgUrl);//加载本地路径文件
         	    		console.log("-----6-----");
         	    		layer.msg("图片更换成功");
         	    	}
            	}else{
            		wrapper.html(contentDataHtml);
            		layer.msg("图片添加成功");
            	}
            	//wrapper.append(data);
            	console.log("======finish:" + guid );
            	if(fileLength==0){
            		//重置为自身
            		uploaderInput.attr("data-loadPosition",uploaderFilesWrapperId);
            	}
            }
        });
    }).catch(function (err) {
    	console.log(err);
        /* 处理失败后执行 */
    }).always(function () {
        /* 必然执行 */
    })
}

function createTextOnly(uploaderInputId, uploaderFilesWrapperId){
	var uploaderInput=$("#"+uploaderInputId);
	var url=uploaderInput.attr("data-requestUrl");
	var uploadData=getExtendData(uploaderInputId);
	console.log(uploadData);
	
	$.ajax({
        url: url,
        type: "POST",
        data: Qs.stringify(uploadData),
        processData: false,
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'html', //返回值类型 一般设置为json
        
        success: function (data) {
        	var wrapperId=uploaderInput.attr("data-loadPosition");
        	var wrapper=$("#"+wrapperId);
        	
        	var clickType = uploaderInput.attr("data-clickType");
        	var contentData=$(data);
        	var contentDataHtml=contentData.prop("outerHTML");
        	if(clickType==CLICKTYPE_IMG_TEXT){
        		if(wrapperId ==uploaderFilesWrapperId || wrapperId==''){
            		wrapper.append(contentDataHtml);//在最后添加
            		layer.msg("文本已添加至最后");
            	}
        	}else if(clickType==CLICKTYPE_IMG_TEXT_COPY){
        		wrapper.after(contentDataHtml);//相邻添加
        		layer.msg("文本添加成功");
        	}
        	console.log("======finish:" + wrapperId);
        		//重置为自身
        	uploaderInput.attr("data-loadPosition",uploaderFilesWrapperId);
        }
    });
}

function popProgress(index, originalFilename, guid){
	var title="上传进度[" + originalFilename + "]";
	var id_progressWrapper="id_progress_wrapper"+guid;
	var id_progress_bar="id_progress_bar"+guid;
	var id_progress_bar_text="id_progress_bar_text"+guid;
	var tip='<div class="progress" id="'+id_progressWrapper+'" style="width: 100%">'+
			    '<div id="'+id_progress_bar+'" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar"'+
			         'aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;">'+
			        '<span id="'+id_progress_bar_text+'">0%</span>'+
			    '</div>'+
			'</div>';
	var typeV=BootstrapDialog.TYPE_WARNING;
	BootstrapDialog.alert({
		id:"progressModal"+guid,
		type: typeV,
		title: title,
    	message:  $("<div></div>").html(tip),
    	size: BootstrapDialog.SIZE_SMALL,
    	draggable:true,
    	onshown:function(dialogItself){
    		//centerModals(dialogItself);
    	}
	});
	
}

function progressHandlingFunction(e, guid){
	var progressWrapperLength=$("#id_progress_wrapper"+ guid).width();
	var percent = e.loaded / e.total;
	var showPercent= Math.floor(percent * 100)+"%";
	console.log(showPercent + "  : " + guid);
	var id_progress_bar="id_progress_bar"+guid;
	var id_progress_bar_text="id_progress_bar_text"+guid;
    $("#"+ id_progress_bar).css("width", (percent * progressWrapperLength));
    var ph=$("#"+ id_progress_bar_text).html();
    console.log("----originalFilename:" + guid);
    console.log("=====id_progress_bar_text:"+ph);
    $("#"+ id_progress_bar_text).html(showPercent);
    if(Math.floor(percent)==1){
    	var dialog = BootstrapDialog.getDialog("progressModal"+ guid);
    	if(dialog){
    		setTimeout(function(){
    			dialog.close();
    		},1300);
    	}
    }
}

function progressReset(guid){
	var id_progress_bar="id_progress_bar"+guid;
	var id_progress_bar_text="id_progress_bar_text"+guid;
	$("#"+id_progress_bar_text).html('');
	$("#"+id_progress_bar).css("width", (0));
}

function progressClose(index){
	
}


function validateDeleteRef(refId){
	if(typeof refId == 'undefined'){
		return false;
	}
	return true;
}

function deleteRef(uploaderInputId, refId, divId){
	//删除对象，同时移除对象本身
	var checked = validateDeleteRef(refId);
	if(checked){
		var orderSeqInput = $("#id_parag_orderSeq"+refId);
		var orderSeq = null;
		var tip="您确定要<span class='text-danger'>删除图片(或段落) </span>吗？删除后不可恢复，请谨慎操作！"
		if(orderSeqInput.length>0){
			orderSeq = orderSeqInput.val();
			tip="您确定要删除<span class='text-danger'>序号为【"+orderSeq+"】的图片(或段落) </span>吗？删除后不可恢复，请谨慎操作！"
		}
		
		BootstrapDialog.show({
			title: "删除确认？",
	    	message: $('<div></div>').html(tip),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '确定删除图片!',
                cssClass : "btn-danger",
                action: function(dialog){
                	if(checked){
                		doDeleteRef(uploaderInputId, refId, divId  , dialog);
                	}else{
                		alert("数据有误，请刷新后重试！");
                		dialog.close();
                	}
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
}

function doDeleteRef(uploaderInputId, refId, divId  , parentDialog){
	var urlData=$("#"+uploaderInputId);
	var url=urlData.attr("data-deleteRefUrl");
	var checked = validateDeleteRef( refId);
	if(checked){
		var uploadData={
	    		"id" : refId
	    	}
		axios({
		    method: 'post',
		    //url: '/sz0099/ood/article/parag/delete',
		    url: url,
		    data: Qs.stringify(uploadData)
		})
		.then(function (response) {
		    var id_tpl_media_id= $("#"+divId);
		    if(id_tpl_media_id.length>0){
	    		id_tpl_media_id.remove();
	    		console.log("-----6-----");
	    		layer.msg("图片(或段落)删除成功");
	    		if(parentDialog){
	        		parentDialog.close();
	        	}
	    	}
		})
		.catch(function (error) {
		    console.log(error);
		});
	}else{
		$.showErr("校验失败！");
	}
}

function getExtendData(inputId){
	var uploaderInput = $("#"+inputId);
	var position = uploaderInput.attr("data-position");
	var mainId = uploaderInput.attr("data-mainId");
	var subId = uploaderInput.attr("data-subId");
	var devId = uploaderInput.attr("data-devId");
	var project = uploaderInput.attr("data-project"); 
	var module = uploaderInput.attr("data-module");
	var variety = uploaderInput.attr("data-variety");
	var strategy = uploaderInput.attr("data-strategy");
	var expectedW = uploaderInput.attr("data-expectedW");
	var orderSeq = uploaderInput.attr("data-orderSeq");
	var type = uploaderInput.attr("data-type");
	var refId = uploaderInput.attr("data-refId");
	var extend = {
			'position':position,
			'mainId':mainId,
			'subId':subId,
			'devId':devId,
			'project':project,
			'module':module,
			'variety':variety,
			'strategy':strategy, 
			'expectedW':expectedW,
			'orderSeq':orderSeq,
			'type':type,
			'id':refId
	};
	return extend;
}

/**
 * 添加图片，生成一段
 * @param inputId
 * @param refOrderId
 * @param positionDivId
 * @returns
 */
function addRef(inputId, refOrderId, positionDivId){
	var inputFile=$("#"+inputId);
	var refOrder=$("#"+refOrderId);
	inputFile.attr("data-orderSeq",refOrder.val());
	inputFile.attr("data-loadPosition", positionDivId);
	inputFile.attr("data-type", 0);
	inputFile.trigger("click", CLICKTYPE_IMG_TEXT_COPY, positionDivId);
	//在元素上点击，创建一个图片位置，并将排序置为相同
}

/**
 * 添加文字，生成一段
 * 无须上传图片
 * @param inputId
 * @param refOrderId
 * @param positionDivId
 * @returns
 */
function addRefText(inputId, refOrderId, positionDivId){
	var inputFile=$("#"+inputId);
	var refOrder=$("#"+refOrderId);
	inputFile.attr("data-orderSeq",refOrder.val());
	var uploaderFilesWrapperId=inputFile.attr("data-loadPosition");
	inputFile.attr("data-loadPosition", positionDivId);
	//inputFile.trigger("click", CLICKTYPE_IMG_TEXT_COPY, positionDivId);
	//在元素上点击，创建一个图片位置，并将排序置为相同
	inputFile.attr("data-type", 1);
	if(positionDivId){
		inputFile.attr("data-loadPosition", positionDivId);
	}
	inputFile.attr("data-refId","");
	var url=inputFile.attr("data-createRefUrl");
	inputFile.attr("data-requestUrl",url);
	inputFile.attr("data-clickType",CLICKTYPE_IMG_TEXT_COPY);
	createTextOnly(inputId, uploaderFilesWrapperId);
	
	console.log("===addRefText: " + inputId);
	
	
	
}

function addRefMore(fieldId, inputId, refOrderId, positionDivId){
	var inputFile=$("#"+inputId);
	var refOrder=$("#"+refOrderId);
	//选择元素
	var field=$("#"+fieldId);
	if(field.length>0){
		field.popover({
	        trigger: 'focus',
	        placement: 'left', //top, bottom, left or right
	        title: "添加元素",
	        html: 'true',
	        content: function(){
	        	return refMoreContent(inputId, refOrderId, positionDivId);
	        },
	        container:"body"
	    });
	    field.popover('show');
	}
}

function refMoreContent(inputId, refOrderId, positionDivId){
	var content="<div class='row'><div class='col-xs-4' style='width:60px'>" +
			"<p class='text-center' onclick='addRef(\""+ inputId + "\", \"" + refOrderId + "\", \""+ positionDivId + "\")'><span class='glyphicon glyphicon-picture' style='font-size: 28px;'></span><br/><span>图片</span></p></div>" +
			"<div class='col-xs-2'></div>"+
			"<div class='col-xs-4' style='width:60px'><p class='text-center' onclick='addRefText(\""+ inputId + "\", \"" + refOrderId + "\", \""+ positionDivId + "\")'><span class='glyphicon glyphicon-font' style='font-size:28px;'></span><br/><span>文字</span></p></div>" +
			"</div>";
	return content;
}

function popSettingRefTemplate(fieldId, entityId, templateId){
	//选择元素
	var field=$("#"+fieldId);
	if(field.length>0){
		field.popover({
	        trigger: 'focus',
	        placement: 'left', //top, bottom, left or right
	        title: "模板设置",
	        html: 'true',
	        content: function(){
	        	return refTemplateContent(fieldId, entityId, templateId);
	        },
	        container:"body"
	    });
	    field.popover('show');
	}
}

function refTemplateContent(fieldId,entityId, templateId){
	var content="<div class='row'>" + 
					"<div class='col-xs-4' style='width:60px'><p class='text-center'><a href='javascript:void(0)' onclick='settingTemplate(\""+fieldId+"\", 1)'><span class='glyphicon glyphicon-flag text-danger' style='font-size: 28px;'></span><br/><span>设置</span></a></p></div>" + 
					"<div class='col-xs-2'></div>" + 
					"<div class='col-xs-4' style='width:60px'><p class='text-center' ><a href='javascript:void(0)' onclick='cancelTemplate(\""+fieldId+"\", 0)'><span class='glyphicon glyphicon-flag text-default' style='font-size:28px;'></span><br/><span>取消</span></a></p></div>" + 
				"</div>";
	return content;
}

function settingTemplate(fieldId,  template){
	changeTemplate(fieldId, template);
}

function cancelTemplate(fieldId,  template){
	changeTemplate(fieldId,  template);
}

function changeTemplate(fieldId,  template){
	var field=$("#"+fieldId);
	if(field.length>0){
		var url=field.attr("data-url");
		var entityId=field.attr("data-entityId");
		var showTipId=field.attr("data-showTip");
		var showTip=$("#"+showTipId);
		if(showTip.length>0){
			showTip.html('');
		}
		var uploadData={
	    		"id" : entityId,
	    		"template" : template
	    }
		axios({
		    method: 'post',
		    url: url,
		    data: Qs.stringify(uploadData)
		})
		.then(function (response) {
				if(showTip.length>0){
					showTip.html(response.data);
					var successInput=showTip.find("input[name='success']");
					var msgInput=showTip.find("input[name='respMsg']");
					if(successInput.length>0){
						var successVal=successInput.val();
						console.log(successVal);
						if(successVal==1){
							console.log("已保存");
							var msg=msgInput.val();
							layer.msg(msg);
							if(template>0){
								field.find("span").removeClass("text-default");
								field.find("span").addClass("text-danger");
							}else{
								field.find("span").removeClass("text-danger");
								field.find("span").addClass("text-default");
							}
						}else{
							var msg=msgInput.val();
							layer.msg(msg);
						}
					}
				}
	    	
		})
		.catch(function (error) {
		    console.log(error);
		});
	}
}

/**
 * 仅添加图片
 * @param inputId
 * @param positionDivId
 * @returns
 */
function addRefForImage(inputId, positionDivId){
	var inputFile=$("#"+inputId);
	//var refOrder=$("#"+refOrderId);
	//inputFile.attr("data-orderSeq",refOrder.val());
	inputFile.attr("data-loadPosition", positionDivId);
	inputFile.attr("data-type", 0);
	inputFile.trigger("click", CLICKTYPE_IMG_ONLY, positionDivId);
}
/**
 * 更换图片
 * @param inputId
 * @param positionDivId
 * @returns
 */
function changeRefForImage(inputId, imgId){
	var inputFile=$("#"+inputId);
	var positionDivId=$("#"+imgId).attr("data-imgWrapperId");
	inputFile.attr("data-loadPosition",positionDivId);
	inputFile.attr("data-type", 0);
	inputFile.trigger("click", CLICKTYPE_IMG_CHANGE, positionDivId);
}

function mergeRefForDescription(uploaderInputId, id, descriptionId, orderId){
	var tip='';
	popForEdit( tip, uploaderInputId, id, descriptionId, orderId);
}

function mergeRefForOrderSeq(uploaderInputId, id, orderId){
	var urlDataInput=$("#"+uploaderInputId);
	var url=urlDataInput.attr("data-mergeRefOrderUrl");
	var orderInput=$("#"+orderId);
	var order=orderInput.val();
	var uploadData={
			'id':id,
			//'description':description,
			'orderSeq':order
	};
	if(!id){
		layer.msg("数据有误");
		return false;
	}
	axios({
	    method: 'post',
	   // url: '/sz0099/ood/article/parag/refreshRefForDescription',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	})
	.then(function (response) {
		var success = response.data.content.success;
		if(success==1){
			var msg="序号修改成功";
			layer.msg(msg);
		}else{
			var msg= response.data.content.respMsg;
			layer.msg(msg);
		}
	})
	.catch(function (error) {
	    console.log(error);
	});
}

/**
 * 重新加载当前段落描述
 * @param uploaderInputId
 * @param id
 * @param descriptionId
 * @param orderId
 * @returns
 */
function refreshRefForDescription(uploaderInputId, id, descriptionId, orderId){
	var urlDataInput=$("#"+uploaderInputId);
	var url=urlDataInput.attr("data-refreshRefDescUrl");
	var descriptionInput=$("#"+descriptionId);
	var descriptionIdLength = descriptionId+"_length";
	var descriptionInputTemp=$("#"+descriptionId+"_tmp");
	//var description=descriptionInput.val();
	var orderInput=$("#"+orderId);
	var order=orderInput.val();
	var uploadData={'id':id
			//, 'description':description,'orderSeq':order
			};
	if(!id){
		layer.msg("数据有误");
		return false;
	}
	axios({
	    method: 'post',
	   // url: '/sz0099/ood/article/parag/refreshRefForDescription',
	    url: url,
	    data: Qs.stringify(uploadData)
	})
	.then(function (response) {
		console.log(response);
		descriptionInput.attr("data-saved",1);//重新加载后，初始化成1（未做改变状态）
		var descHtml=response.data.content.description;
		descriptionInput.val(descHtml);
		var descText=$(descHtml).text();
		descriptionInputTemp.val(descText);
		countContentLength(descText,3000, descriptionIdLength);
		var msg="段落编号："+order+"，重新加载成功";
		layer.msg(msg);
		
	})
	.catch(function (error) {
	    console.log(error);
	});
}

function doDeleteRefImage(uploaderInputId, refId, divId  , parentDialog){
	var urlData=$("#"+uploaderInputId);
	var url=urlData.attr("data-deleteImageRefUrl");
	var checked = validateDeleteRef( refId);
	if(checked){
		var uploadData={
	    		"id" : refId
	    	}
		axios({
		    method: 'post',
		    //url: '/sz0099/ood/article/parag/delete',
		    url: url,
		    data: 
		    	Qs.stringify(uploadData)
		})
		.then(function (response) {
		    var id_tpl_media_id= $("#"+divId);
		   // var click_upload_gif='/assets/common/images/logo/click_upload.gif';
		    var click_upload_gif=urlData.attr("data-uploadImg");
		    if(id_tpl_media_id.length>0){
	    		id_tpl_media_id.attr("src",click_upload_gif);
	    		id_tpl_media_id.attr("data-type",1);//更改类型为文本
	    		console.log("-----6-----");
	    		layer.msg("图片删除成功");
	    	}
		    $(parentDialog).find("img").attr("src",click_upload_gif);
		})
		.catch(function (error) {
		    console.log(error);
		});
	}else{
		$.showErr("校验失败！");
	}
}

function popBigViewEditForRef(imageSrc,tip, uploaderInputId, refId,imgId){
	
	var tipV="编辑图片";
	if(tip){
		tipV=tip;
	}
	var content =  "<img src='"+imageSrc + "' class='img-responsive center-block' alt='"+tipV+"'>" ;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_SUCCESS,
		title: tipV,
    	message:  $("<div id='imgContent' ></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons : [{
    		cssClass : "btn-info",
            label : '更换',
            action : function(dialogItself) {
            	changeRefForImage(uploaderInputId, imgId);
               // dialogItself.close();
            }
        }, {
    		cssClass : "btn-danger",
            label : '移除',
            action : function(dialogItself) {
            	doDeleteRefImage(uploaderInputId, refId, imgId , dialogItself);
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
    		/*if(titleHide){
    			$(".modal-header").addClass('hidden');
    			console.log("add .modal-header hidden---");
    		}*/
    	}
	});
}

function resetOrderSeq(articleId, tip, url){
	var tipV="编号重排";
	if(tip){
		tipV=tip;
	}
	var content = "<p>将图片按现有编号升序序列,<br/>重置起始编号<br/>重排后，编号为1,2,3,4...</p><p><img src='/assets/common/help/article/4o_edit_parag_1.gif' class='img-thumbnail  img-responsive' width='250px' alt='编号重排-按钮说明' onclick=\"popBigViewOnly('/assets/common/help/article/4o_edit_parag_1.gif','编号重排-按钮说明', false)\"></p>" ;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_PRIMARY,
		title: tipV,
    	message:  $("<div id='resetOrderSeq' ></div>").html(content),
    	size: BootstrapDialog.SIZE_SMALL,
    	buttons : [{
    		cssClass : "btn-danger",
            label : '确定重排',
            action : function(dialogItself) {
            	doResetOrderSeq(articleId, url, dialogItself);
            }
        }, {
    		cssClass : "btn-default",
            label : '取消',
            action : function(dialogItself) {
                dialogItself.close();
            }
        } ],
    	onshown:function(dialogItself){
    		
    	}
	});
}

function doResetOrderSeq(articleId, url, parentDialog){
	//var articleId = $("#id_hidden_id").val();
	//加载段落数据
	axios({
	    method: 'post',
	    //url: '/sz0099/ood/product/manage/paragraph/editListUI',
	    url: url,
	    data: 
	    	Qs.stringify({
    	    	'mainId' : articleId
	    	})
	})
	.then(function (response) {
	    console.log("---- photo loaded----");
	    $("#id_contentPhoto_content"+articleId).html(response.data);
	    initParagraph(articleId);//将所有file组件全都初始化一遍
	    if(parentDialog){
	    	parentDialog.close();
	    }
	})
	.catch(function (error) {
	    console.log(error);
	});
	
}
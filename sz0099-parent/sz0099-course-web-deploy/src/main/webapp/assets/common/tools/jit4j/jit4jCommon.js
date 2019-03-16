function developing(){
	popTipCustom("信息提示","模块还在开发中...感谢支持!");
}
//手机号校验
function isPoneAvailable(str) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}

var wait = 60;//默认60s
var get_code_time = function (id, waitTime, refreshTip) {
	if(waitTime<0){
		waitTime=60;
	}else{
		wait=waitTime;
	}
	
	var o=$("#"+id);
	if (wait == 0) {
		o.removeAttr("disabled");
		//vueId.refreshTip="看不清？";
		//vueId.refreshTip=refreshTip;
		o.html(refreshTip);
		o.attr("value",refreshTip);
		console.log("btnVal>>> "+refreshTip + ", wait:"+ wait);
		wait = waitTime;
	} else {
		o.attr("disabled", true);
		//btn.refreshTip= wait + "s";
		var btnVal=wait + "s";
		//vueId.refreshTip=btnVal;
		o.html(btnVal);
		o.attr("value",btnVal);
		console.log("btnVal>>> "+btnVal + ", wait:"+ wait);
		wait--;
		setTimeout(function() {
			get_code_time(id, wait, refreshTip);
		}, 1000)
	}
 }

var get_code_time_fobidden = function (id, waitTime, refreshTip, jumpToUrl) {
	if(waitTime<0){
		waitTime=5;
	}else{
		wait=waitTime;
	}
	var o=$("#"+id);
	if (wait == 0) {
		//o.removeAttr("disabled");
		//vueId.refreshTip="看不清？";
		//vueId.refreshTip=refreshTip;
		o.html(refreshTip);
		o.attr("value", refreshTip);
		console.log("btnVal>>> " + refreshTip + ", wait:" + wait);
		wait = waitTime;
		if(null != jumpToUrl && '' != jumpToUrl){
			window.location.href=jumpToUrl;
		}else{
			window.location.href="/";//跳到首页
		}
	} else {
		o.attr("disabled", true);
		//btn.refreshTip= wait + "s";
		var btnVal= refreshTip + " "+ wait + "s 跳转";
		//vueId.refreshTip=btnVal;
		o.html(btnVal);
		o.attr("value",btnVal);
		console.log("btnVal>>> " + btnVal + ", wait:"+ wait);
		wait--;
		setTimeout(function() {
			get_code_time_fobidden(id, wait, refreshTip,jumpToUrl);
		}, 1000)
	}
 }

function reloadValidateCode(id,refreshTip){
	var url="/jcaptcha_code?data=" + new Date() + Math.floor(Math.random()*24);
	document.getElementById("validateCodeImg").src=url;
    console.log("reloadValidateCode>> id: " + id + ", refreshTip:"+refreshTip);
    var sdate=new Date();
    var fdate=sdate.format('yyyyMMddHHmmss');
	$("#validateCodeImg").attr("src","/jcaptcha_code?data=" + fdate + Math.floor(Math.random()*24));
    if(id!="" && id!=null && typeof id=='string'){
    	get_code_time(id,10,refreshTip);
    }
}

function isEmail(email) {
	if(email){
		if(email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
			return true;
		} 
	}
	return false;
}

function isArrayFn(value) {
	if (typeof Array.isArray === "function") {
		return Array.isArray(value);
	} else {
		return Object.prototype.toString.call(value) === "[object Array]";
	}
}

function urlEncode(param, key, encode) {
	if (param == null)
		return '';
	var paramStr = '';
	var t = typeof (param);
	if (t == 'string' || t == 'number' || t == 'boolean') {
		paramStr += '&' + key+ '=' + ((encode == null || encode) ? encodeURIComponent(param): param);
	} else {
		for ( var i in param) {
			var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i)
			paramStr += urlEncode(param[i], k, encode)
		}
	}
	return paramStr;
}

function urlEncodeEnhance(param, key, encode) {
	if (param == null)
		return '';
	var paramStr = '';
	var t = typeof (param);
	if (t == 'string' || t == 'number' || t == 'boolean') {
		paramStr += '&' + key+ '=' + ((encode == null || encode) ? encodeURIComponent(param): param);
	} else {
		for ( var i in param) {
			var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i)
			paramStr += urlEncode(param[i], k, encode)
		}
	}
	return paramStr;
}


var special_pattern = new RegExp("[`^&=|{}''\\[\\]<>/&*']");
function stripScript(s){ 
    var pattern=special_pattern;
    var rs = ""; 
    for (var i = 0; i < s.length; i++) { 
        rs = rs+s.substr(i, 1).replace(pattern, ''); 
    } 
    return rs; 
}
function testScriptBase(filterRule, str){
	var judge= filterRule.test(str);
	return judge;
}

function testScript(str){
	return testScriptBase(special_pattern,str);
}


//validate begin
function validateLength(contentId,  tip, allowLength, showTipDivId){
	var titleInput=$("#"+contentId);
	var content = titleInput.val();
	var checked = validateContentLength(content,  tip, allowLength, showTipDivId);
	if(!checked){
		titleInput.focus();
		//titleInput.val(content.substring(0,allowLength));
	}
	return checked;
}

function validateContentLength(content,  tip, allowLength, showTipDivId){
	if(content.length>allowLength){
			if(!tip){
			tip="字段太长，都超"+allowLength+"了";
		}
			var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			
			return false;
		}
	return true;
}

function countContentLength(content, allowLength, showTipSpanId){
	var showTipSpan=$("#"+showTipSpanId);
	if(showTipSpan.length>0){
		if(content){
			var length=content.length;
			var contentTip=length;
			if(content.length>allowLength){
				contentTip="<font color='red'>"+length+"</font>";
			}
			showTipSpan.html(contentTip);
			return length;
		}else{
			showTipSpan.html(0);
			return 0;
		}
	}
	return 0;
}
function countContentLengthWithoutHtml(content, allowLength, showTipSpanId){
	if(content) {
		var filterContent=content;
	      var pattern =new RegExp("<([^>]*)>");
          while(pattern.test(filterContent)){     //value为带有html标签的字符串
              filterContent=filterContent.replace(pattern, '');
          }
		//console.log(filterContent);
		var length=countContentLength(filterContent, allowLength, showTipSpanId);
		return length;
	}
	return 0;
}

function showLength(contentId, allowLength, showTipSpanId){
	var contentInput=$("#"+contentId);
	if(contentInput.length>0){
		var content = contentInput.val();
		countContentLengthWithoutHtml(content, allowLength, showTipSpanId);
	}
}


function validateEmpty(contentId, tip, showTipDivId){
	var titleInput=$("#"+contentId);
	var content = titleInput.val();
	return validateContentEmpty(content, tip, showTipDivId);
}

function validateContentEmpty(content, tip, showTipDivId){
	if(!content || ''==content){
		if(!tip){
			tip="字段不能为空";
		}
		var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			return false;
		}
	return true;
}

function validateUnkown(contentId, fixedVal, tip, showTipDivId){
	var titleInput=$("#"+contentId);
	var content = titleInput.val();
	return validateContentUnkown(content,fixedVal, tip, showTipDivId);
}

function validateContentUnkown(content, fixedVal,tip, showTipDivId){
	if(content==fixedVal){
		if(!tip){
			tip="字段不能是未指定";
		}
		var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			return false;
		}
	return true;
}

function validateScript(contentId, tip, showTipDivId){
	var titleInput=$("#"+contentId);
	var content = titleInput.val();
	var checked = validateContentScript(content, tip, showTipDivId);
	if(!checked){
		titleInput.focus();
		var newTitle = stripScript(content);
			titleInput.val(newTitle);
	}
	return checked;
}

function validateContentScript(content, tip, showTipDivId){
	var testTitle=testScript(content);
		if(testTitle){
			if(!tip){
			tip="含有非法字符，自动过滤";
		}
			var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			
			return false;
		}
	return true;
}

//validate end


//validate base begin
function stripBase(regx,s){
	var str = "";
	if(s){
		str=s.replace(regx,"");
	}
	return str;
}
function testBase(regx, str){
	return testScriptBase(regx,str);
}

function validateBase(regx, contentId, tip, showTipDivId){
	var titleInput=$("#"+contentId);
	var content = titleInput.val();
	var checked = validateContentBase(regx, content, tip, showTipDivId);
	if(!checked){
		titleInput.focus();
		var newTitle = stripBase(regx, content);
			titleInput.val(newTitle);
	}
	return checked;
}

function validateContentBase(regx, content, tip, showTipDivId){
	var testTitle=testBase(regx, content);
		if(testTitle){
			if(!tip){
				tip="含有非法字符";
			}
			var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			
			return false;
		}
	return true;
}
function validateContentBaseRegx(regx, content){
	var test=testBase(regx, content);
	return test;
}
//validate base end

function validateContentvalidateOnlyLetterDigit(content){
	var test=validateContentBaseRegx(letter_digit_only, content);
	return test;
}

function checkPassWord(password) {
	//必须为字母加数字且长度不小于8位
   var str = password;
    if (str == null || str.length <6) {
        return false;
    }
    var reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    if (!reg1.test(str)) {
        return false;
    }
    var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
    if (reg.test(str)) {
        return true;
    } else {
        return false;
    }
}

// validate only letter and digit begin
var letter_digit_only=new RegExp("[^(a-zA-Z0-9)]",'g');
var domain_only=new RegExp("[^(a-zA-Z0-9.)]",'g');
var domain_http_only=new RegExp("[^(a-zA-Z0-9.:/-_&?)]",'g');
var common_only=new RegExp("[^(a-zA-Z0-9_.)]",'g');

function validateOnlyLetterDigit(contentId, tip, showTipDivId){
	var checked = validateBase(letter_digit_only, contentId, tip, showTipDivId);
	return checked;
}

function validateDomain(contentId, tip, showTipDivId){
	var checked = validateBase(domain_only, contentId, tip, showTipDivId);
	return checked;
}

function validateDomainHttp(contentId, tip, showTipDivId){
	var checked = validateBase(domain_http_only, contentId, tip, showTipDivId);
	return checked;
}

function validateCommonOnly(contentId, tip, showTipDivId){
	var checked = validateBase(common_only, contentId, tip, showTipDivId);
	return checked;
}

// validate only letter and digit end

//validate num
//限定正数
function keyPressPositive(obj) {
	obj.value=checkOnlyNum(obj.value);
}
function onAfterPastePositive(obj) {
	obj.value=checkOnlyNum(obj.value);
}
function keyPressUpCountLength(inputId, showId, maxLength) {
	var inputField=$("#"+inputId);
	var showField=$("#"+showId);
	if(inputField.length>0 && showField.length>0){
		var value=inputField.val();
		if(value != null){
			var valueLength=value.length;
			if(valueLength>=0 && valueLength<maxLength){
				showField.html(" 计数："+(valueLength));
			}else if(valueLength>maxLength){
				showField.html("<span class='text-danger'> 计数："+valueLength + " 已超限</span>");
			}
		}
	}
}

function checkOnlyNum(value) {
	if (value && value.length == 1) {
		value =value.replace(/[^0-9]/g, '')
	} else {
		value = value.replace(/\D/g, '')
	}
	return value;
}

//校验网址格式
function checkIsUrl(url){
	var urlReg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~_]+)\.)+([A-Za-z0-9-_~\/])+$/;
	if(!urlReg.test(url)){
		return false;
	}
	return true;
}

function validateIsUrl(contentId, tip, showTipDivId){
	var urlInput=$("#"+contentId);
	var content = urlInput.val();
	return validateContentIsUrl(content, tip, showTipDivId);
}
function validateContentIsUrl(content, tip, showTipDivId){
	var testUrl=checkIsUrl(content);
		if(!testUrl){
			if(!tip){
				tip="网址格式不合法！";
			}
			var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			
			return false;
		}
	return true;
}

//校验数值范围
function validateRange(contentId, valMin,valMax, tip, showTipDivId){
	var valInput=$("#"+contentId);
	var content = valInput.val();
	return validateContentRange(content, valMin,valMax, tip, showTipDivId);
}
function validateContentRange(content, valMin,valMax, tip, showTipDivId){
	var testNum=checkOnlyNum(content);//自动过滤出数字
	if(testNum){
		if(!tip){
			tip="数字范围超限！";
		}
		if(testNum>valMax || testNum<valMin){
			tip=tip+",必须在【"+valMin+"-"+valMax+"】之间";
			var showTipDiv=$("#"+showTipDivId);
			if(showTipDiv.length>0){
				showTipDiv.html(tip);
			}
			popTip(tip);
			
			return false;
		}
		
	}
	return true;
}


function validateSessionInvalid(showTipDivId, tip){
		if(null==tip || ''==tip){
			tip="！！当前会话已失效，请重新登录后再操作！！";
		}
		var showTipDiv=$("#"+showTipDivId);
		if(showTipDiv.length>0){
			showTipDiv.html(tip);
		}
		
		BootstrapDialog.show({
			title: "出错信息提示",
	    	message: $('<div></div>').html(tip),
	    	type: BootstrapDialog.TYPE_DANGER,
	    	size: BootstrapDialog.SIZE_SMALL,
	    	buttons: [{
                label: '跳转至登录页',
                cssClass : "btn-primary",
                action: function(dialog){
                	window.location.href="/loginUI"
                	dialog.close();
                	
                },
            }, {
                label: '取消',
                cssClass : "btn-danger",
                action: function(dialog){
                	dialog.close();
                }
            }]
		});
		
		
		return false;
	
}

function validateEmail(email , messageTipDiv){
	
	messageTipDiv.html("");
	var tip="";
	if(''==email){
		tip="Email不能为空";
		messageTipDiv.html(tip);
		popTip(tip);
		return false;
	}
	if(!isEmail(email)){
		tip="Email格式不正确";
		messageTipDiv.html(tip);
		popTip(tip);
		return false;
	}
	return true;
}

//计算图像路径begin
var IMAGE_STRATEGY_1=[120,300,720,1200];
var IMAGE_STRATEGY_2=[1200];
function getExpectedViewUrl(viewUrl, expectedW, acturalW,widthList){
	var widthArray = IMAGE_STRATEGY_1;
	if(widthList){
		widthArray=widthList;
	}
	if(viewUrl && ''!= viewUrl){
		
		var w = searchDownWidth(acturalW, widthArray);
		var wanted=w;
		if (expectedW < w) {
			wanted = searchUpWidth(expectedW, widthArray);
		}
		
		if(expectedW>=720 && wanted<720) {
			wanted=720;//源图宽度小于720时，取源图，源图存储时复制了一份为720
		}
		
		var lastIndex_dot = viewUrl.lastIndexOf(".");
		var lastIndex_unline = viewUrl.lastIndexOf("_");
		var expectedUrl = viewUrl;
		if(lastIndex_dot>-1){
			if(lastIndex_unline>-1){
				expectedUrl=viewUrl.substring(0,lastIndex_unline);
			}else{
				expectedUrl=viewUrl.substring(0,lastIndex_dot);
			}
			expectedUrl= expectedUrl+"_"+wanted+viewUrl.substring(lastIndex_dot);
		}
		return expectedUrl;
	}
	return viewUrl;
}

function getOriUrl(fullurl){
	var imageOri=fullurl.replace('imview','imori');
	var _index=imageOri.lastIndexOf("_");
	var _indexDot=imageOri.lastIndexOf(".");
	var ori_1=imageOri.substring(0,_index);
	var ori_2=imageOri.substring(_indexDot);
	var ori=ori_1+ori_2;
	return ori;
}


function searchDownWidth( actualWidth,  widthList) {
	if (null != widthList && widthList.length>0) {
		var size = widthList.length;
		for (var i = size; i > 0; i--) {
			var w = widthList[i-1];
			if (actualWidth > w) {
				return w;
			}
		}
		return actualWidth;
	}
	return 0;
}

function searchUpWidth( expectedW, widthList) {

	if (null != widthList && widthList.length>0) {
		var size = widthList.length;
		for (var i = 0; i < size; i++) {
			var w = widthList[i];
			if (expectedW <= w) {
				return w;
			}
		}
		return widthList[size-1];
	}
	return 0;
}
//计算图像路径end

function agree(url, inputId, messageTipId){
	var input=$("#"+inputId);
	if(input.length>0){
		var checked=0;
		$.each($('input:checkbox:checked'),function(){
			checked = $(this).val();
        });
		$.ajax({
			url : "/auth/user/mergeAgree",
			type : "post",
			data : {
				"agree" : checked
			},
			success : function(data) {
	    	    var id_messageTip_preview=$("#"+messageTipId);
	    	    id_messageTip_preview.html(data);
	    	    var commonMsg = $("#id_common_respMsg").html();
	    	    var successVal = $("#id_hidden_common_success").val();
	    	    if(successVal==1){
	    	    	id_messageTip_preview.removeClass("text-danger");
	    	    	id_messageTip_preview.addClass("text-success");
	    	    	input.remove();
	    	    }else{
	    	    	id_messageTip_preview.removeClass("text-success");
	    	    	id_messageTip_preview.addClass("text-danger");
	    	    }
			    //移除通用元素
			    $("#id_hidden_common_success").remove();
			    //$("#id_common_respMsg").remove();
			    $("#id_hidden_common_respCode").remove();
			},
			error : function(data) {
				console.log("请求错误..." + data);
			}

		});
	}
}

function changeF2Y(amount){
	if(!isNaN(amount)){
		var amountValue=amount*0.01+'';
		var reg=amountValue.indexOf('.') >-1 ? /(\d{1,3})(?=(?:\d{3})+\.)/g : /(\d{1,3})(?=(?:\d{3})+$)/g;//千分符的正则
		return amountValue.replace(reg,'$1,');
	}else{
		return amount;
	}
}

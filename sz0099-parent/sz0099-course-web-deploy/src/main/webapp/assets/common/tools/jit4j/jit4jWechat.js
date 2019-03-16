function initPayV3(){
	var id_wechat="id_resp_wechat";
	var wechatInput=$("#"+id_wechat);
	console.log("--- initPayV3 begin ----");
	console.log(wechatInput);
	if(wechatInput.length>0){
	   console.log("--- initPayV3 begin, step 4 ----");
	   var _appId=wechatInput.attr("data-appId");
	   var _timeStamp=wechatInput.attr("data-timeStamp");
	   var _nonceStr=wechatInput.attr("data-nonceStr");
	   var _package=wechatInput.attr("data-package");
	   var _signType=wechatInput.attr("data-signType");
	   var _paySign=wechatInput.attr("data-paySign");
	   wx.chooseWXPay({
		   "appId":_appId,     //"wx2421b1c4370ec43b" 公众号名称，由商户传入     
	       "timeStamp": _timeStamp,         //"1395712654" 时间戳，自1970年以来的秒数     
	       "nonceStr":_nonceStr, //"e61463f8efa94090b1f366cccfbbb444" 随机串     
	       "package":_package,   // "prepay_id=u802345jgfjsdfgsdg888"
	       "signType":_signType,         //"MD5" 微信签名方式：     
	       "paySign":_paySign, //"70EA570631E4BB79628FBCA90534C63FF7FADD89" 微信签名 
	       success: function (res) {
	           // 支付成功后的回调函数
	    	   console.log("---pay success---");
	       }
	   });
   }
}

function onBridgeReady(){
	var id_wechat="id_resp_wechat";
	var wechatInput=$("#"+id_wechat);
	console.log("--- initPay begin,  onBridgeReady step 3 ----");
	console.log(wechatInput);
   if(wechatInput.length>0){
	   console.log("--- initPay begin, step 4 ----");
	   var _appId=wechatInput.attr("data-appId");
	   var _timeStamp=wechatInput.attr("data-timeStamp");
	   var _nonceStr=wechatInput.attr("data-nonceStr");
	   var _package=wechatInput.attr("data-package");
	   var _signType=wechatInput.attr("data-signType");
	   var _paySign=wechatInput.attr("data-paySign");
	   var respTipId=wechatInput.attr("data-respTip");
	   var respTip=$("#"+respTipId);
	   var redirectUrl=wechatInput.attr("data-redirectUrl");
	   //alert("_paySign:" + _paySign + "  _package:" + _package);
	   WeixinJSBridge.invoke(
	      'getBrandWCPayRequest', {
	         appId:_appId,     //"wx2421b1c4370ec43b" 公众号名称，由商户传入     
	         timeStamp: _timeStamp,         //"1395712654" 时间戳，自1970年以来的秒数     
	         nonceStr:_nonceStr, //"e61463f8efa94090b1f366cccfbbb444" 随机串     
	         package:_package,   // "prepay_id=u802345jgfjsdfgsdg888"
	         signType:_signType,         //"MD5" 微信签名方式：     
	         paySign:_paySign //"70EA570631E4BB79628FBCA90534C63FF7FADD89" 微信签名  
	      },
	      function(res){
	    	  var resStr=JSON.stringify(res);
	    	  respTip.html(res.errMsg);
		      if(res.err_msg == "get_brand_wcpay_request:ok" ){
		    	  // 使用以上方式判断前端返回,微信团队郑重提示：
		          //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
		    	  console.log("request, ok!");
		    	  console.log(resStr);
		    	  window.location.href=redirectUrl;
		      }
		      if(res.err_msg == "get_brand_wcpay_request:cancel" ){
		    	  console.log("request, cancel!");
		    	  console.log(resStr);
		      }
		      if(res.err_msg == "get_brand_wcpay_request:fail" ){
		    	  console.log("request, fail!");
		    	  console.log(resStr);
		    	  respTip.html(res.errMsg);
		      }
		      
	   });
   }
}


function initPayV1(){
	console.log("--- initPay begin ----");
	if (typeof WeixinJSBridge == "undefined"){
	   if( document.addEventListener ){
	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   } else if (document.attachEvent){
	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   }
	} else {
		console.log("--- initPay begin, step 2 ----");
	    onBridgeReady();
	}
}

function getSharedUrl(sharedCode, title, desc,imgUrl){
	var _currentUrl=window.location.href;
	var _sharedUrl=_currentUrl;
	var addSlash=false;
	if(sharedCode){
		if(_sharedUrl && _sharedUrl.indexOf("?")<0){
			_sharedUrl+="?";
		}else{
			_sharedUrl+="&";
		}
		_sharedUrl=_sharedUrl+"sharedCode="+sharedCode;
	}
	var lastIndexOf = _currentUrl.length-1;
	if(addSlash && _currentUrl && _currentUrl.lastIndexOf('/')!=lastIndexOf){
		_currentUrl=_currentUrl+"/";
	}
	var appId="";
	$.ajax({
		url : "/social/wechat/getWxSignature",
		type : "get",
		data : {
			"url" : _currentUrl
		},
		dataType : "json",
		success : function(data) {
			// data是返回的值，内部通过parse()方法将json字符串转换成json对象
			wx.config({
				debug : false,
				appId : data.content.appId,
				timestamp : data.content.timestamp,
				nonceStr : data.content.nonceStr,
				signature : data.content.signature,
				jsApiList : [ 'checkJsApi', 'chooseImage',
					'onMenuShareAppMessage',
                    'onMenuShareTimeline','chooseWXPay'
					/*,
                    'updateAppMessageShareData', 'updateTimelineShareData'*/]
			});
			appId=data.content.appId;
			console.log("to get auth code... end<<<");
		},
		error : function(data) {
			console.log("wx请求错误..." + data);
		}

	});
	wx.ready(function() {
		// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
		 console.log("wx init...");
		wx.checkJsApi({
		    jsApiList: [
		    	// 所有要调用的 API 都要加到这个列表中
		    	'chooseImage',
		    	'onMenuShareAppMessage',
                'onMenuShareTimeline',
                'chooseWXPay'
		    	/*,
		        'updateAppMessageShareData',      
		        'updateTimelineShareData'*/  
		    ], 
		    success: function(res) {
		    	console.log("wx resp..." + res);
		    // 以键值对的形式返回，可用的api值true，不可用为false
		    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    }
		});
		
		// 微信分享的数据
	    var shareData = {
	        "imgUrl" : imgUrl,    // 分享显示的缩略图地址
	        "link" : _currentUrl,    // 分享地址
	        "desc" : desc,   // 分享描述
	        "title" : title,   // 分享标题
	        "id" : appId,
	        success : function () {  
	               // 分享成功可以做相应的数据处理
	        		var respMsg="分享成功了";
	        		layer.msg(respMsg);
		        	/*$.showSuccessTimeout(respMsg, function(){
		        	});*/
	              }
	         };
	       wx.onMenuShareAppMessage (shareData); 
	       wx.onMenuShareTimeline (shareData); 
	});

	wx.error(function(res) {
		console.log("wx.error: res.errMsg>>" + res.errMsg);
	});
}


var global_currentUrl=window.location.href;
var global_appId="";
function weixinLoad(callback){
	global_currentUrl=window.location.href;
	console.log("currentUrl:>>" + global_currentUrl);
	$.ajax({
		url : "/social/wechat/getWxSignature",
		type : "get",
		data : {
			"url" : global_currentUrl
		},
		dataType : "json",
		success : function(data) {
			// data是返回的值，内部通过parse()方法将json字符串转换成json对象
			global_appId=data.content.appId;
			console.log("global_appId:"+global_appId);
			wx.config({
				debug : false,
				appId : data.content.appId,
				timestamp : data.content.timestamp,
				nonceStr : data.content.nonceStr,
				signature : data.content.signature,
				jsApiList : [ 'checkJsApi', 'chooseImage','chooseWXPay']
			});
			
			/*var logined=getLoginStatus();
			if(!checkStateExist() && !logined){
				console.log("to get auth code... begin>>>");
				getAuthorizeCode(global_appId,global_currentUrl,"snsapi_base","dml");
			}*/
			
			console.log("to get auth code... end<<<");
		},
		error : function(data) {
			console.log("wx请求错误..." + data);
		}

	});
	

	wx.ready(function() {
		// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
		console.log("wx init...");
		wx.checkJsApi({
		    jsApiList: [
		    	// 所有要调用的 API 都要加到这个列表中
		    	'chooseImage','chooseWXPay'
		    ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		    	console.log("wx resp..." + res);
		    // 以键值对的形式返回，可用的api值true，不可用为false
		    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    }
		});
		
		console.log("---callback: wxpay init1---");
		if(callback && typeof(callback) === "function"){
			console.log("---callback: wxpay init2---");
			callback();
		}
		
	});

	wx.error(function(res) {
		console.log("wx.error: res.errMsg>>" + res.errMsg);
	});
}

function getLoginStatus(){
	var logined=false;
	$.ajax({
		url : "/login/status",
		type : "get",
		data : {
			
		},
		dataType : "json",
		success : function(data) {
			var loginstatus=data.content;
			console.log("loginstatus:"+loginstatus);
			if("true"==loginstatus){
				logined = true;
			}
		},
		error : function(data) {
			console.log("getLoginStatus, error..." + data);
		}

	});
	return logined;
	
}


function getOauthPageUrl(redirectUrl, scope, state){
	console.log("getOauthPageUrl:>>redirectUrl: " + redirectUrl);
	console.log("getOauthPageUrl:>>scope: " + scope);
	console.log("getOauthPageUrl:>>state: " + state);
	state=getState(state);
	//alert("--------------"+redirectUrl + " , scope:"+scope + " state:"+state);
	$.ajax({
		url : "/social/wechat/getOauthPageUrl",
		type : "get",
		data : {
			"redirectUrl" : redirectUrl,
			"scope" : getAuthScope(scope),
			"state" : state
		},
		dataType : "json",
		success : function(data) {
			var oauthUrl=data.content;
			console.log("oauthUrl:"+oauthUrl);
			if(is_weixn() && !checkOauthed()){
				console.log("to jumpAngGetAuthCode... begin>>>");
				jumpAngGetAuthCode(oauthUrl);
			}
			console.log("to jumpAngGetAuthCode... end<<<");
		},
		error : function(data) {
			console.log("getOauthPageUrl, error..." + data);
		}

	});
}

function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}
/**
 * 当前页面微信自动授权
 * @returns
 */
var global_oauthed=false;
function autoCurrentOauthPageUrl(){
	console.log("autoCurrentOauthPageUrl:>> begin>>> ");
	var loginstatus = getLoginStatus();
	console.log("autoCurrentOauthPageUrl:loginstatus >> " + loginstatus);
	if(is_weixn() && !checkOauthed() && !loginstatus){
		global_currentUrl=window.location.href;
		console.log("autoCurrentOauthPageUrl:begin >> " + global_currentUrl);
		//snsapi_base
		autoOauthPageUrl(global_currentUrl, "snsapi_userinfo", "dml");
	}
}

function autoCurrentOauthPageUrlLogin(loginStatus){
	console.log("autoCurrentOauthPageUrlLogin:>> begin>>> loginStatus: " + loginStatus);
	if("false"==loginStatus){
		//alert("not login....");
		if(is_weixn() && !checkOauthed()){
			global_currentUrl=window.location.href;
			console.log("autoCurrentOauthPageUrlLogin:begin >> " + global_currentUrl);
			//snsapi_base
			autoOauthPageUrl(global_currentUrl, "snsapi_userinfo", "dml");
		}
	}
}

function autoOauthPageUrlLogin(loginStatus){
	if("false"==loginStatus){
		autoCurrentOauthPageUrlLogin(loginStatus);
	}else{
		weixinLoad(initPayV1);
	}
}

function autoOauthPageUrl(oauthUrl,scope, state){
	
	if(is_weixn() && !checkStateExist()){
		console.log("oauthUrl:begin >> " + oauthUrl);
		getOauthPageUrl(oauthUrl, scope, state);
	}
}
	
function autoWxLoad(){
	console.log("autoWxLoad:>> begin>>> ");
	if(is_weixn() && checkOauthed()){
		console.log("autoWxLoad:>> ok>>> ");
		console.log("autoWxLoad:>> get siganature>>> ");
		weixinLoad(afterWeixinLoad);
		console.log("autoWxLoad:>> finish >>> ");
	}else{
		console.log("autoWxLoad:>> not!!! ");
	}
}

function afterWeixinLoad(){
	console.log("afterWeixinLoad:>> finish >>> ");
}

function getAuthScope(scope){
	if(''== scope || null==scope || typeof scope != "string"){
		scope="snsapi_base";
	}
	if(scope!="snsapi_base"){
		scope="snsapi_userinfo";
	}
	return scope;
}
function getState(state){
	if(''== state || null==state || !state){
		state="dml";
	}
	return state;
}

function getAuthorizeCodeUrl(appId,redirectUri,scope,state){
	if(''== scope || null==scope || typeof scope != "string"){
		scope="snsapi_base";
	}
	if(''== state || null==state || typeof state != "string"){
		state="dml";
	}
	var encodeRedirectUri = encodeURIComponent(redirectUri);
	var auth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+encodeRedirectUri+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
	console.log(auth_url);
	return auth_url;
}

function checkOauthed(){
	//检测到有state字段，视为已授权
	var stateExist = checkStateExist();
	var codeExist = checkCodeExist();
	global_oauthed = stateExist&&codeExist;
	return global_oauthed;
}
function checkCodeExist(){
	var code=getUrlParam("code");
	console.log("check code:"+ code);
	if(code){
		return true;
	}
	return false;
}
function checkStateExist(){
	var state=getUrlParam("state");
	console.log("check state:"+ state);
	if(state){
		global_oauthed=true;
		return true;
	}
	return false;
}

function decodeURI(url){
	var uri ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	var decodeUri = decodeURIComponent(url);
	console.log(decodeUri);
	return decodeUri;
}

function getAuthorizeCode(appId,redirectUri,scope, state){
	var auth_url = getAuthorizeCodeUrl(appId,redirectUri,scope,state);
	window.location.href=auth_url;
}
function jumpAngGetAuthCode(oauthUrl){
	console.log(">>>> jumpAngGetAuthCode: " + oauthUrl);
	window.location.href=oauthUrl;
}


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

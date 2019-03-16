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
	   WeixinJSBridge.invoke(
	      'getBrandWCPayRequest', {
	         "appId":_appId,     //"wx2421b1c4370ec43b" 公众号名称，由商户传入     
	         "timeStamp": _timeStamp,         //"1395712654" 时间戳，自1970年以来的秒数     
	         "nonceStr":_nonceStr, //"e61463f8efa94090b1f366cccfbbb444" 随机串     
	         "package":_package,   // "prepay_id=u802345jgfjsdfgsdg888"
	         "signType":_signType,         //"MD5" 微信签名方式：     
	         "paySign":_paySign //"70EA570631E4BB79628FBCA90534C63FF7FADD89" 微信签名  
	      },
	      function(res){
		      if(res.err_msg == "get_brand_wcpay_request:ok" ){
		    	  // 使用以上方式判断前端返回,微信团队郑重提示：
		          //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
		    	  console.log("request, ok!");
		      }
		      if(res.err_msg == "get_brand_wcpay_request:cancel" ){
		    	  console.log("request, cancel!");
		      }
		      if(res.err_msg == "get_brand_wcpay_request:fail" ){
		    	  console.log("request, fail!");
		      }
	   });
   }
}


function initPay(){
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


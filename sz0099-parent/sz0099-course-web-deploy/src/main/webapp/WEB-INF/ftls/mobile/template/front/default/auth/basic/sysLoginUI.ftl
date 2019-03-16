<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>系统用户登录</title>
</head>

<body>

<div class="container " id="body_content">

  		<div class="text-center"><h3>系  统  登   录</h3></div>
  		<div class="text-center" id="id_tip_show">${entity.respMsg}</div>
		<form id="id_form_sys_user_login" role="form" action="/sys/doLogin" method="post" enctype="application/x-www-form-urlencoded">
		
				  <input type="hidden" name="token" value="${token}"/> 
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="id">系统ID:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="id" class="form-control" id="id" placeholder="系统ID" />
				    </div>
				   </div>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="appId">APPID:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="appId" class="form-control" id="appId" placeholder="APPID" />
				    </div>
				  </div>
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="openid">openid:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="openid" class="form-control" id="openid" placeholder="openid" />
				    </div>
				  </div>
				   <button class="btn btn-primary btn-block" type="button" onclick="sysLogin()">登  录</button>
				   <hr/>
		</form>

</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">
function sysLogin(){
	var id=$("id").val();
	var openid=$("openid").val();
	var appId=$("appId").val();
	var showTipDivId="id_tip_show";
	var idChecked=validateContentEmpty(id, "数据不能为空！", showTipDivId);
	if(!idChecked){
		return false;
	}
	idChecked = checkOnlyNum(id);
	if(!idChecked){
		return false;
	}
	var openidChecked=validateContentEmpty(openid, "数据不能为空！", showTipDivId);
	if(!openidChecked){
		return false;
	}
	
	var appIdChecked=validateContentEmpty(appId, "数据不能为空！", showTipDivId);
	if(!appIdChecked){
		return false;
	}
	$("#"+id_form_sys_user_login).submit();
}
</script>
</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>用户登录</title>
</head>
<body>
<div class="container" id="top_banner">
++++++++++ header +++++++++++ header ---> top_banner ++++++++++++++++++++
</div>
<div class="container" id="top_menu">
++++++++++ PPP/OOO/UUU/KKK/LLL  ---> top_menu ++++++++++++++++++++
</div>
<div class="container" id="body_content">
in boday content
==========WWEE======${info2}================================
${info}
-------------${webInfoMessage1}-------------<br/>
+++++++++++++++${webInfoMessage2}+++++++++++<br/>
${result.allErrors} 66666666666   <br/>


request.contextPath : ${request.contextPath}   999 <br/> 

<br/>

<@spring.showErrors />


555555555555<br/>
<#-- <@spring.bind "user" /> -->
777--
<#list spring.status.errorMessages as error>  <small>${error}</small><br/> </#list>
  --888
<br/>
<@spring.url relativeUrl="/login"/>

000000000000000000000 <br/>


<br/>
======================================
<@spring.message "web.info.1"/>

<#assign ctx=request.getContextPath()>
${request.contextPath}
${request.characterEncoding}
<br/>
${ctx}*
.................333445566
<br/>
${springMacroRequestContext.contextPath}  009
<br/>
${request.servletPath}

${rc.appDomain} ++++
${rc.staticDomain} ======
${rc.basePath} -----

<img src="/assets/common/images/timg.jpg" />

<br/>
<@form.form commandName="user" role="form" action="/login">
						<input type="hidden" name="token" value="${token}"/> 
		   <@spring.bind "user.email" /> 
		  <div class="form-group <#if (spring.status.errorMessages?size>0)> has-error </#if>">
		    <label class="control-label" for="email_1">Email address:
		    <#if (spring.status.errorMessages?size>0)>
			
			  <#list spring.status.errorMessages as error>  <small>${error}</small><br/> </#list>
			
			</#if>
		    </label>
		    <@form.input path="email" class="form-control" id="email_1" placeholder="Enter email"/>
		  </div>
		  
		  <@spring.bind "user.password" /> 
		  <div class="form-group <#if (spring.status.errorMessages?size>0)> has-error </#if>">
		    <label class="control-label" for="password_1">Password:
		    <#if (spring.status.errorMessages?size>0)>
			
			  <#list spring.status.errorMessages as error>  <small>${error}</small><br/> </#list>
			
			</#if>
		    </label>
		    <@form.password path="password" class="form-control" id="password_1" placeholder="输入密码"/>
		   </div> 
		    
		    <div class="form-group <#if (spring.status.errorMessages?size>0)> has-error </#if>">
			    <label class="control-label" for="validateCode_1">验证码：
			    <#if (spring.status.errorMessages?size>0)>
				
				  <#list spring.status.errorMessages as error>  <small>${error}</small><br/> </#list>
				
				</#if>
			    </label>
			    <input type="text" name="validateCode" class="form-control" id="validateCode_1"/>&nbsp;&nbsp;<img id="validateCodeImg" src="/jcaptcha_code" />&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
		   </div> 
		    
	       <ul class="list-inline">
			  <li><input type="submit" value="登  录" class="btn btn-primary"/></li>
			  <li><p class="text-info">已有帐号，点击登录 | <a class="text-danger" href='<@spring.url relativeUrl="/registerUserUI"/>'>没有帐号，<strong>点此</strong>新注册一个</a></p></li>
		   </ul>
	  </div><#-- end panel -->
	  </div>
	      
  </@form.form>
 </div>
<div class="container" id="body_footer">
------------------- in body footer -------------------- <br/>
<script type="text/javascript">
<!--
function reloadValidateCode(){
	//var url="/jcaptcha_code?data=" + new Date() + Math.floor(Math.random()*24);
	//document.getElementById("validateCodeImg").src=url;
    $("#validateCodeImg").attr("src","/jcaptcha_code?data=" + new Date() + Math.floor(Math.random()*24));
}
//-->
</script>
</div>
</body>
</html>
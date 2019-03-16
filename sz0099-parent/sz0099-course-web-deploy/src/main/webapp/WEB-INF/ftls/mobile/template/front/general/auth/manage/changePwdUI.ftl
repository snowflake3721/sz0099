<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<div class="container " id="body_content">
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<#-- 
<#if entity.success == 1>
 -->
		<h5 class="text-success" id="id_common_respMsg">${entity.respMsg}</h5>
  		<div class="text-center text-danger" id="id_tip_show">${entity.respMsg}</div>
		<form id="id_form_changepwd" role="form" action="/auth/user/manage/doChangePwd" method="post" enctype="application/x-www-form-urlencoded">
				  <input type="hidden" name="token" value="${token}"/>
				  <p><strong><span class="text-success">用户ID:</span>${entity.username}</strong></p>
				  <#assign pwdExist=false/>
				  <#if entity.pwd!>
				  	<#assign pwdExist=true/>
				  </#if>
				  <#if pwdExist><p class="bg-danger">您已设置过密码，若无更改，点 <span class="text-danger">关闭</span> 按钮</p></#if>
				  <div class="form-group row <#if !pwdExist>hidden</#if>" id="id_pwd_yes">
				    <label class="col-sm-2 col-form-label" for="oldpwd">原密码:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="oldpwd" value="" class="form-control" id="oldpwd" placeholder="输入原密码" />
				    </div>
				   </div>
				   
				   	<p class="text-danger text-center <#if pwdExist>hidden</#if>" id="id_pwd_no">密码尚未设置，请尽快设置</p>
				  
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="pwd">新密码:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="pwd" value="" class="form-control" id="pwd" placeholder="输入新密码" />
				    </div>
				  </div>
				  <div class="form-group row">
				    <label class="col-sm-2 col-form-label" for="confirmpwd">确认新密码:
				 	</label>
				 	<div class="col-sm-10">
				    <input type="text" name="confirmpwd" value="${entity.confirmpwd}" class="form-control" id="confirmpwd" placeholder="再输一遍新密码" />
				    </div>
				  </div>
				  <p><strong>允许字符：</strong>只能是数字 0-9、大小写字母[A-Za-z]</p>
				  <p><strong>密码组合：</strong>只能是其中的2种或3种混合</p>
				  <p><strong>密码长度</strong>：6-18位数</p>
				  <p><strong>示例：</strong><span class="text-success">Qx55888</span></p>
				  <br/>
				  <p><strong><span class="text-primary">友情提示：</span></strong><br/><strong>设置密码、绑定手机号</strong>后，可在<strong><span class="text-danger">网页端登录</span></strong>本站</p>
				  <p>请牢记您的<strong>用户ID:</strong> <strong><small>${entity.username}</small></strong>，网页端登录必填！</p>
				  <hr/>
		</form>
<#-- 
<#else >
<h4 class="text-danger" id="id_common_respMsg">${entity.respMsg}</h4>
</#if>
-->
</div>
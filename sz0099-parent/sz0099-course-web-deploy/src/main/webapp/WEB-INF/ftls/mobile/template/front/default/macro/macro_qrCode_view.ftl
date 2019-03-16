<#macro M_qrCodeView entity=null> 
<#if entity!>
<div class="text-center container">
<h4>我的邀请码</h4>
<div id="inviteQrCodeContent">		
<img src="${entity.fullurl}" title="${entity.title}" alt="邀请码"/>
</div>
<h4 class="text-center text-danger">
${entity.title}
</h4>
<p class="text-left">
小提示:<br/>
<small>将此邀请二维码展示给朋友，使用微信扫码即可加入了，你的文章和技能会优先展示在被邀请人首页哦！</small>
</p>
</div>
</#if>
</#macro>

<#macro M_qrCodeViewForIndex user=null> 
<#if user!>
<#assign qrCode=user.qrCode/>
<div id="inviteQrCodeWrapper" class="container text-center hidden" 
data-title="我的邀请码"
data-src="${user.qrCode}" data-url="/auth/userqr/viewInvite/ansy"
>
	<div id="inviteQrCodeContent">	
	<#if qrCode! && qrCode!=''>	
	<img src="${user.qrCode}" title="我的邀请码" alt="邀请码"/>
	</#if>
	</div>
	<p class="text-center">
	我的邀请码
	</p>
</div>
</#if>
</#macro>

<#macro M_qrCodeViewForAnsy entity=null> 
<#if entity!>
	<img src="${entity.fullurl}" title="${entity.title}" alt="邀请码"/>
</#if>
</#macro>
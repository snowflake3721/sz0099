<#macro M_groupForPersonalCenter group=null currentMenu=null> 
<div class="text-center" style="padding: 5px 0 5px 0">
<#if group??>
<#assign navigatMenuId=group.navigatMenuId />
<#assign navigatMenuGroupList=group.navigatMenuGroupList />
	<#if navigatMenuId?? >
		${navigatMenuId}
		<#assign navigatMenu=group.navigatMenu />
		<div class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          		<img src='${(navigatMenu.user.avatar)!navigatMenu.iconPath}' alt="${navigatMenu.user.username!navigatMenu.name}" title="${navigatMenu.user.username!navigatMenu.name}" class="img-circle" /> 
		          </a>
		          <ul class="dropdown-menu dropdown-menu-right">
		            <#if navigatMenuGroupList!?size gt 0>
		            	<#list navigatMenuGroupList as nmg>
			            	<#assign nm=nmg.navigatMenu />
			            	<#assign separatorYes=nm.separatorable==NavigatMenu.SEPARATORABLE_YES />
			            	<#if separatorYes && nm.separatorDir==NavigatMenu.SEPARATOR_DIR_UP>
			            	<li role="separator" class="divider"></li>
			            	</#if>
				            <li><a href="${nm.link}">${nm.name}</a></li>
				            <#if separatorYes && nm.separatorDir==NavigatMenu.SEPARATOR_DIR_DOWN>
			            	<li role="separator" class="divider"></li>
			            	</#if>
				            <#-- 
				            <li><a href="#">我的美文</a></li>
				            <li><a href="#">所获赏金</a></li>
				            <li><a href="#">发布新文</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">赞赏过谁</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">消息 <span class="badge">4334</span></a></li>
				             -->
			            </#list>
		            </#if>
		          </ul>
		 </div>
	
	</#if>
<#else>
<button type="button" class="btn btn-default btn-xs">登录</button>
</#if>
</div>
</#macro>


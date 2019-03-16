<#macro M_tabNavigation group=null currentMenu=null>
  <!--导航开始-->
<#if group??>
<#assign navigatMenuId=group.navigatMenuId />
<#assign navigatMenuGroupList=group.navigatMenuGroupList />
<nav class="navbar navbar-default navbar-fixed-bottom">
  
<ul class="nav nav-pills" role="tablist">
	<#if navigatMenuGroupList!?size gt 0>
			<#list navigatMenuGroupList as nmg>
			<#assign nm=nmg.navigatMenu />
			    <li role="presentation" <#if currentMenu?? && currentMenu.code==nm.code>class="active"</#if> v-on:click="loadContent('${nm.code}')">
			    	<a href="${nm.link}" aria-controls="${nm.code}" role="tab" data-toggle="tab">${nm.name}</a>
			    </li>
		    </#list>
	</#if>
</ul>
  <!--导航结束-->
</#if>
</nav>
 </#macro>
 
 
<#macro M_navigationForDetailPage group=null currentMenu=null>
  <!--导航开始-->
<#if group??>
<#assign navigatMenuId=group.navigatMenuId />
<#assign navigatMenuGroupList=group.navigatMenuGroupList />
<nav class="navbar navbar-default navbar-fixed-bottom">
  
<ul class="nav nav-pills" role="tablist">
	<#if navigatMenuGroupList!?size gt 0>
			<#list navigatMenuGroupList as nmg>
			<#assign nm=nmg.navigatMenu />
			    <li role="presentation" <#if currentMenu?? && currentMenu.code==nm.code>class="active"</#if> v-on:click="loadContent('${nm.code}')">
			    	<a href="${nm.linkSub}" target="_self">${nm.name}</a>
			    </li>
		    </#list>
	</#if>
</ul>
  <!--导航结束-->
</#if>
</nav>
</#macro>


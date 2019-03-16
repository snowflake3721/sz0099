<#macro M_tabNavigation group=null currentMenu=null>
  <!--导航开始-->
<#if group??>
<#assign navigatMenuId=group.navigatMenuId />
<#assign navigatMenuGroupList=group.navigatMenuGroupList />
<ul class="nav nav-tabs" role="tablist">
	<#if navigatMenuGroupList!?size gt 0>
			<#list navigatMenuGroupList as nmg>
			<#assign nm=nmg.navigatMenu />
			    <li role="presentation" <#if currentMenu?? && currentMenu.code==nm.code>class="active"</#if>>
			    	<a href="${nm.link}" aria-controls="${nm.code}" role="tab" data-toggle="tab">${nm.name}</a>
			    </li>
			    <#-- 
			    <li role="presentation"><a href="#footing" aria-controls="footing" role="tab" data-toggle="tab">徒步</a></li>
			    <li role="presentation"><a href="#biking" aria-controls="biking" role="tab" data-toggle="tab">骑行</a></li>
			    <li role="presentation"><a href="#travel" aria-controls="travel" role="tab" data-toggle="tab">旅行</a></li>
			    <li role="presentation"><a href="#equipments" aria-controls="equipments" role="tab" data-toggle="tab">装备</a></li>
			     -->
		    </#list>
	</#if>
</ul>
  <!--导航结束-->
</#if>
 </#macro>


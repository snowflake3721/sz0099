<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#-- 图文横滚 -->
<#macro M_layout_tagsForProfession positon=null entityPage=null idPre="layout_" showHeadTip=false withPreIntro=false withTitle=true withSubTitle=false showLoadMore=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
<#if position?? && showHeadTip>
<div>${Position.PANEL.getLabel(position.panel)}</div>

</#if>
</#if>
</#macro>

<#macro M_layout_invitorPage positon=null entityPage=null idPre="ly_invitor">
<#if entityPage??>
<@M_layout_imgBannerRollerAbreast3_multi positon=null entityPage=entityPage idPre=idPre mainCircle=true/>
</#if>
</#macro>

<#macro M_layout_tagPageSimple positon=null entityPage=null idPre="ly_tag">
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if entityList!>
	<#assign size=entityList?size/>
		<#list entityList as entity0>
			<#assign tagList=entity0.proTagList/>
			<#if tagList!>
				<@M_showTagFirst tagList=tagList fontSize=10/>
			</#if>
			<#if size lt 2>
			<#assign tagSize=TagUtils.getRandomTagClazz(fontSize) />
			<span class="label label-${tagSize.clazzName}">${getSubstring(entity0.name,3,"")}</span>
			</#if>
		</#list>
	</#if>
</#if>
</#macro>

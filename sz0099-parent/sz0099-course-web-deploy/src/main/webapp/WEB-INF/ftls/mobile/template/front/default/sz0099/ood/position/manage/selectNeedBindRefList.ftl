<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_position_create.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_positionRef_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_position_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_position_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_position_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_position_list.ftl">
<#if entity!>

<#if entity.viewType==PositionRef.VIEWTYPE_1_ARTICLE.valueInt>
	<#if response!>
		<#assign content=response.content/>
		<#if content!>
			<@M_articleSearchForSelect page=content entity=entity searchUrl="/sz0099/ood/position/manage/selectNeedBindRefList"/>
		</#if>
	</#if>
<#elseif entity.viewType==PositionRef.VIEWTYPE_2_PRODUCT.valueInt>
产品列表
<#elseif entity.viewType==PositionRef.VIEWTYPE_3_PROFESSION.valueInt>
<#if response!>
	<#assign content=response.content/>
	<#if content!>
		<@M_professionSearchForSelect page=content entity=entity searchUrl="/sz0099/ood/position/manage/selectNeedBindRefList"/>
	</#if>
</#if>
</#if>

</#if>
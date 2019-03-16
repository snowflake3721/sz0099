<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForManage.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_profession_search.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_article_profession_list.ftl">
<#if entity!>

<#if entity.viewType==PositionRef.VIEWTYPE_1_ARTICLE.valueInt>
	<#if response!>
		<#assign content=response.content/>
		<#if content!>
			<@M_articleSearchForSelect page=content entity=entity searchUrl="/sz0099/ood/personal/profession/manage/selectNeedBindRefList"/>
		</#if>
	</#if>
<#elseif entity.viewType==PositionRef.VIEWTYPE_2_PRODUCT.valueInt>
产品列表
<#elseif entity.viewType==PositionRef.VIEWTYPE_3_PROFESSION.valueInt>
技能列表
</#if>

</#if>
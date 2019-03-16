<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_article_category.ftl">
<#if articleBo??>
<@MV_categorySelect articleCategory=articleBo.category />
</#if>
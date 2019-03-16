<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_article_list_item.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">
<#include "mobile/template/front/default/macro/macro_article_tag.ftl">

	<div class="text-center"><span class="label label-danger">精彩活动</span></div>
    <!--图片滚动开始,查询 最新发布的文章列表-->
    <#if articlePage??>
	    <#assign articleList=articlePage.content />
	    <#if articleList??>
	    <@M_articleImageRoller imageRollerId="home_article_" articleList=articleList/>
	    </#if>
    </#if>
    <!--图片滚动结束-->
    
    <!--群侠户外，打赏先锋开始,   查询本周打赏前10位的文章 -->
   <#--  <@M_articlePageForQX articlePage=articlePage /> -->
    <!--群侠户外, 打赏先锋结束-->
    <hr/>
    <@M_articleTag tagPage=""></@M_articleTag>
    <hr/>
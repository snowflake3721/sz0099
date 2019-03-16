<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_article.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_article_list.ftl">
<@Mg_headRoller dto=bikeDto idPre="ly_index_bike" btnLabel="更多旅行..."/>
<@Mg_bikePageWrapper itemPage=bikePage condition=entity/>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_swiper_activity.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_activity_list.ftl">
<@Mg_headRoller dto=pickDto idPre="ly_index_pick" btnLabel="更多采摘..."/>
<@Mg_pickPageWrapper itemPage=pickPage condition=entity/>
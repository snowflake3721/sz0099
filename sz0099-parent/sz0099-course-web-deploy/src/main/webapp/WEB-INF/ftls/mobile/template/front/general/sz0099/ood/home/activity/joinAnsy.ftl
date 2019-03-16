<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_home_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_activity_list.ftl">
<@Mg_headRoller dto=joinDto idPre="ly_index_join" btnLabel="更多活动..."/>
<@Mg_joinPageWrapper itemPage=joinPage condition=entity/>
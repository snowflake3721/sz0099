<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_activity.ftl">
<#macro Mg_headRoller dto=null idPre="ly_head" btnLabel="更多活动">
<#assign category=dto.category/>
<@Mg_layout_headRoller ref=dto.position dto=dto idPre=idPre showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true btnLabel=btnLabel showListLoadMore=true showPreNext=true/>
</#macro>

<#macro Mg_explorerWord2Img dto=null>
<#assign category=dto.category/>
<@Mg_layout_explorerWord2Img ref=dto.position dto=dto idPre="ly_explorer" showHeadTip=false withPreIntro=false withTitle=false withSubTitle=false showListLoadMore=true showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
</#macro>

<#macro Mg_rememberScreen dto=null>
<#assign category=dto.category/>
<@Mg_layout_rememberScreen ref=dto.position dto=dto idPre="ly_remember" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_farmstayRollerMulti dto=null>
<#assign category=dto.category/>
<@Mg_layout_farmstayRollerMulti ref=dto.position dto=dto idPre="ly_farmstay" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_outdoorRollerMulti dto=null>
<#assign category=dto.category/>
<@Mg_layout_outdoorRollerMulti ref=dto.position dto=dto idPre="ly_outdoor" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_equipRealImg2Word dto=null>
<#assign category=dto.category/>
<@Mg_layout_equipRealImg2Word ref=dto.position dto=dto idPre="ly_equipreal" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_actionFlagImg2Img dto=null>
<#assign category=dto.category/>
<@Mg_layout_actionFlagImg2Img ref=dto.position dto=dto idPre="ly_actionFlag" showHeadTip=true showPenname=true withPreIntro=true withTitle=true showListLoadMore=true/>
</#macro>


<#macro Mg_searchImg2Word dto=null idPre="ly_search">
<@Mg_layout_searchImg2Word dto=dto idPre=idPre showHeadTip=false url="#" showListLoadMore=true withPreIntro=true/>
</#macro>
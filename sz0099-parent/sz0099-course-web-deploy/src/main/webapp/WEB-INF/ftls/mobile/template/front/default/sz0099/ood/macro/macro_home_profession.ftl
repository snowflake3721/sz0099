<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_home_profession.ftl">
<#macro M_headRoller dto=null>
<#assign category=dto.category/>
<@M_layout_headRoller ref=dto.position dto=dto idPre="ly_head" showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showListLoadMore=true showPreNext=false/>
</#macro>
<#macro M_recommendImg3Multi dto=null>
<@M_layout_recommendImg3Multi dto=dto showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showPenname=true showListLoadMore=true mainCircle=true btnLabel="更多推荐..." idPre="ly_recommend"/>
</#macro>

<#macro M_outdoorWord2Img dto=null>
<#assign category=dto.category/>
<@M_layout_outdoorWord2Img dto=dto idPre="ly_outdoor" showHeadTip=false withPreIntro=false withTitle=false withSubTitle=false showListLoadMore=true showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
</#macro>

<#macro M_todayScreen dto=null>
<#assign category=dto.category/>
<@M_layout_todayScreen ref=dto.position dto=dto idPre="ly_today" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_farmstayRollerMulti dto=null>
<#assign category=dto.category/>
<@M_layout_farmstayRollerMulti ref=dto.position dto=dto idPre="ly_farmstay" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_outdoorRollerMulti dto=null>
<#assign category=dto.category/>
<@M_layout_outdoorRollerMulti ref=dto.position dto=dto idPre="ly_outdoor" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_killImg2Word dto=null>
<#assign category=dto.category/>
<@M_layout_killImg2Word dto=dto idPre="ly_kill" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_artisanImg2Img dto=null>
<#assign category=dto.category/>
<@M_layout_artisanImg2Img dto=dto idPre="ly_artisan" showHeadTip=true showPenname=true withPreIntro=true withTitle=true showListLoadMore=true/>
</#macro>


<#macro M_searchImg2Word dto=null idPre="ly_search">
<@M_layout_searchImg2Word dto=dto idPre=idPre showHeadTip=false url="#" showListLoadMore=true/>
</#macro>
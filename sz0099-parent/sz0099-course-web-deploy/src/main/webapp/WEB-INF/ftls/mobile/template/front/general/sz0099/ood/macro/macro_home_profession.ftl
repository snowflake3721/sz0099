<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_profession.ftl">
<#macro Mg_headRoller dto=null idPre="ly_head" btnLabel="更多技能">
<#assign category=dto.category/>
<@Mg_layout_headRoller ref=dto.position dto=dto idPre=idPre showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true btnLabel=btnLabel showListLoadMore=true showPreNext=true/>
</#macro>
<#macro Mg_recommendImg3Multi dto=null>
<@Mg_layout_recommendImg3Multi dto=dto showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showPenname=true showListLoadMore=true mainCircle=true btnLabel="更多推荐..." idPre="ly_recommend"/>
</#macro>

<#macro Mg_outdoorWord2Img dto=null>
<#assign category=dto.category/>
<@Mg_layout_outdoorWord2Img dto=dto idPre="ly_outdoor" showHeadTip=false withPreIntro=false withTitle=false withSubTitle=false showListLoadMore=true showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
</#macro>

<#macro Mg_todayScreen dto=null>
<#assign category=dto.category/>
<@Mg_layout_todayScreen ref=dto.position dto=dto idPre="ly_today" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_farmstayRollerMulti dto=null>
<#assign category=dto.category/>
<@Mg_layout_farmstayRollerMulti ref=dto.position dto=dto idPre="ly_farmstay" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_outdoorRollerMulti dto=null>
<#assign category=dto.category/>
<@Mg_layout_outdoorRollerMulti ref=dto.position dto=dto idPre="ly_outdoor" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_killImg2Word dto=null>
<#assign category=dto.category/>
<@Mg_layout_killImg2Word dto=dto idPre="ly_kill" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro Mg_artisanImg2Img dto=null>
<#assign category=dto.category/>
<@Mg_layout_artisanImg2Img dto=dto idPre="ly_artisan" showHeadTip=true showPenname=true withPreIntro=true withTitle=true showListLoadMore=true/>
</#macro>


<#macro Mg_searchImg2Word dto=null idPre="ly_search">
<@Mg_layout_searchImg2Word dto=dto idPre=idPre showHeadTip=false url="#" showListLoadMore=true withPreIntro=true/>
</#macro>
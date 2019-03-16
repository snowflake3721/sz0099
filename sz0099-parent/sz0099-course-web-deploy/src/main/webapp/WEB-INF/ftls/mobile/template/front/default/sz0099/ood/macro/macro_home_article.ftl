<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_home_article.ftl">
<#macro M_headRoller dto=null>
<#assign category=dto.category/>
<@M_layout_headRoller ref=dto.position dto=dto idPre="ly_head" showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showListLoadMore=true showPreNext=false/>
</#macro>

<#macro M_explorerWord2Img dto=null>
<#assign category=dto.category/>
<@M_layout_explorerWord2Img ref=dto.position dto=dto idPre="ly_explorer" showHeadTip=false withPreIntro=false withTitle=false withSubTitle=false showListLoadMore=true showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
</#macro>

<#macro M_rememberScreen dto=null>
<#assign category=dto.category/>
<@M_layout_rememberScreen ref=dto.position dto=dto idPre="ly_remember" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_farmstayRollerMulti dto=null>
<#assign category=dto.category/>
<@M_layout_farmstayRollerMulti ref=dto.position dto=dto idPre="ly_farmstay" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_outdoorRollerMulti dto=null>
<#assign category=dto.category/>
<@M_layout_outdoorRollerMulti ref=dto.position dto=dto idPre="ly_outdoor" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_equipRealImg2Word dto=null>
<#assign category=dto.category/>
<@M_layout_equipRealImg2Word ref=dto.position dto=dto idPre="ly_equipreal" showHeadTip=true showListLoadMore=true/>
</#macro>

<#macro M_actionFlagImg2Img dto=null>
<#assign category=dto.category/>
<@M_layout_actionFlagImg2Img ref=dto.position dto=dto idPre="ly_actionFlag" showHeadTip=true showPenname=true withPreIntro=true withTitle=true showListLoadMore=true/>
</#macro>


<#macro M_searchImg2Word dto=null idPre="ly_search">
<@M_layout_searchImg2Word dto=dto idPre=idPre showHeadTip=false url="#" showListLoadMore=true/>
</#macro>
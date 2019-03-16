<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#assign photo=ref.fullurl />
<#assign photoUrl='/assets/common/images/logo/click_upload.gif' />
<#if photo! && ref.fullurl! && ImageRef.TYPE_IMG.valueInt==ref.type>
<#assign photoUrl=PhotoUtil.getShowUrlForAd(ref.expectedUrl,ref.fullurl,ref.width)/>
</#if>
<img class="img-responsive" src="${photoUrl}" id="id_img_${ref.id}" data-imgWrapperId="id_img_${ref.id}_wrapper" data-type="${ref.type}" data-refId="${ref.id}"/>

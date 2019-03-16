<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_activity_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#if ref.success==ImageRef.SUCCESS_YES>
<@Mg_showImageForEdit id="bannerUploaderInput"+ref.mainId imageRef=ref />
<#else>
<p class="text-danger text-center">${ref.respMsg}<p>
</#if>

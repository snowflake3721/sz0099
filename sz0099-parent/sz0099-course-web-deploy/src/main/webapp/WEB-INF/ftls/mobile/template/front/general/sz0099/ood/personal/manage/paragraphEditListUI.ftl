<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_paragraph.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/macro/macro_help.ftl">
<#if entity.success==1>
<@Mg_paragraphListEdit page=page entity=entity/>
<div class="hidden" id="id_help_font_size">
<@M_editorFontSize />
</div>
<div class="hidden" id="id_help_align">
<@M_editorAlign />
</div>
<#else>
${entity.respMsg}
<#if CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN==entity.respCode>
<a type="button" class="btn btn-primary btn-xs" href="/sz0099/ood/home/article/index/recommend?st=general">去登录</a>
</#if>
</#if>
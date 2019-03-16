<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_create.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#if ref.success==ImageRef.SUCCESS_YES>
<@Mg_showImageForEdit id="bannerUploaderInput"+ref.mainId imageRef=ref />
<#else>
<p class="text-danger text-center extranum">${ref.respMsg}<p>
<script type="text/javascript">
$(document).ready(function(){
	layer.msg("${ref.respMsg}");
	setTimeout(function(){
		$(".extranum").remove();
		console.log("has removed--");
	},5000)
});
</script>
</#if>

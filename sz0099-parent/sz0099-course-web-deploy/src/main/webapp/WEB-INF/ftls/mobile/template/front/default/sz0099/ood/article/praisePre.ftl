<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<div class="container" style="width:300px">
<h3 class="text-center">为大侠点赞</h3><hr/>
<#if entity.success == 1>
<div class='form-group form-group-sm' >
<label for='id_word'  class='text-success'>点赞稍句话？(少于64字符)<span id="id_show_count_length"></span></label>
<input type='text' id='id_praise_word' name='word' value='${entity.word}' class='form-control' placeholder='输入 赞语'
onkeydown="keyPressUpCountLength('id_praise_word', 'id_show_count_length', 64)"
oninput="keyPressUpCountLength('id_praise_word', 'id_show_count_length', 64)"
onafterpaste="keyPressUpCountLength('id_praise_word', 'id_show_count_length', 64)"
>
<input type='hidden' id='id_praise_id' name='id' value='${entity.id}'>
<input type='hidden' id='id_praise_mainId' name='mainId' value='${entity.mainId}'>
<h4 class="<#if entity.success == 1>text-success<#else>text-danger</#if>" id="id_common_respMsg">${entity.respMsg}</h4>
<input type="hidden" name="respCode" value="${entity.respCode}" id="id_hidden_common_respCode"/>
<input type="hidden" name="success" value="${entity.success}" id="id_hidden_common_success"/>
<#if entity.id!>
<button class="btn btn-danger" onclick="refreshPraise('${entity.id}', '${entity.mainId}', 'id_praise_word')">刷新点赞</button>
<p><small>小提示：刷新点赞可以提升点赞排名，增加出场机率哦！</small></p>
</#if>
<#else >
<h4 class="text-danger" id="id_common_respMsg">${entity.respMsg}</h4>
</#if>
</div>


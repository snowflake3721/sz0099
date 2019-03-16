<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_roleAddUI entity=null >
<@M_roleEditUI entity=entity withBtnSubmit=true url="/auth/role/manage/add"/>
</#macro>
<#macro M_roleEditUI entity=null withBtnSubmit=false url="/auth/role/manage/merge">
<form action="${url}" method="post" enctype="application/x-www-form-urlencoded">
<div class='form-group form-group-sm' >
<label for='id_role_name'  class='text-success'>名称(少于32字符)<span id="id_name_show_count_length"></span></label>
<input type='text' id='id_role_name' name='name' value='${entity.name}' class='form-control' placeholder='输入 名称'
onkeyup="keyPressUpCountLength('id_role_name', 'id_name_show_count_length', 32)"
oninput="keyPressUpCountLength('id_role_name', 'id_name_show_count_length', 32)"
onafterpaste="keyPressUpCountLength('id_role_name', 'id_name_show_count_length', 32)"
>
</div>
<div class='form-group form-group-sm' >
<label for='id_role_code'  class='text-success'>代码(少于32字符,唯一)<span id="id_code_show_count_length"></span></label>
<input type='text' id='id_role_code' name='code' value='${entity.code}' class='form-control' placeholder='输入 名称'
onkeyup="keyPressUpCountLength('id_role_code', 'id_code_show_count_length', 32)"
oninput="keyPressUpCountLength('id_role_code', 'id_code_show_count_length', 32)"
onafterpaste="keyPressUpCountLength('id_role_code', 'id_code_show_count_length', 32)"
>
</div>
<div class='form-group form-group-sm' >
<label for='id_role_description'  class='text-success'>描述(少于255字符)<span id="id_description_show_count_length"></span></label>
<input type='text' id='id_role_description' name='description' value='${entity.description}' class='form-control' placeholder='输入 描述'
onkeyup="keyPressUpCountLength('id_role_description', 'id_description_show_count_length', 255)"
oninput="keyPressUpCountLength('id_role_description', 'id_description_show_count_length', 255)"
onafterpaste="keyPressUpCountLength('id_role_description', 'id_description_show_count_length', 255)"
>
</div>
<@M_dropdownBarStr id="id_role_" propertyContext=Role.CATEGORY current=Role.CATEGORY.getLabelContextStr(entity.category) readonly=false showLabel=false/>
<@M_dropdownBar id="id_role_" propertyContext=Role.PERMANENTABLE current=Role.PERMANENTABLE.getLabelContext(entity.permanentable) readonly=false showLabel=false/>

<div class='form-group form-group-sm' >
<label for='id_role_depositAmount'  class='text-success'>信誉保证金金额(数字，单位：分)</label>
<input type='text' id='id_role_depositAmount' name='depositAmount' value='${entity.depositAmount}' class='form-control' placeholder='输入 名称'
onkeyup="keyPressPositive(this)"
oninput="keyPressPositive(this)"
onafterpaste="keyPressPositive(this)"
>
</div>
<@M_dropdownBar id="id_role_" propertyContext=Role.FROZEN current=Role.FROZEN.getLabelContext(entity.frozen) readonly=false showLabel=false/>
<@M_dropdownBar id="id_role_" propertyContext=Role.VERIFIABLE current=Role.VERIFIABLE.getLabelContext(entity.verifiable) readonly=false showLabel=false/>

<div class='form-group form-group-sm' >
<label for='id_role_verifyFee'  class='text-success'>认证审核费金额(数字，单位：分)</label>
<input type='text' id='id_role_verifyFee' name='verifyFee' value='${entity.verifyFee}' class='form-control' placeholder='输入 认证审核费'
onkeyup="keyPressPositive(this)"
oninput="keyPressPositive(this)"
onafterpaste="keyPressPositive(this)"
>
</div>
<#if withBtnSubmit>
<p class="text-right"><button type="submit" class="btn btn-danger">提交</button></p>
</#if>
<input type='hidden' id='id_role_id' name='id' value='${entity.id}'
data-saveUrl="${url}">
</form>
</#macro>



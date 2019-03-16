<#macro M_homeNavigate>
<p><a href="/sz0099/ood/home/article/index/recommend?st=general" class="btn btn-danger">返回户外圈</a> <a href="/sz0099/ood/home/profession/index" class="btn btn-danger">返回技能圈</a></p>
</#macro>

<#macro M_homeIndex>
<p><a href="/sz0099/ood/home/article/index/recommend?st=general" class="btn btn-danger">返回首页</a></p>
</#macro>

<#macro M_agree user=null>
<#if user!>
<#assign agree=user.agree/>
<span class="text-danger">我已理解且同意: 
<#if agree != User.AGREE_1.valueInt>
<label class="checkbox-inline" >
	<input type="checkbox" id="id_agree" name="agree" value="1" onclick="agree('/auth/user/mergeAgree','id_agree','id_messageTip')"> 同意
</label>
<#else>
已同意
</#if>
</span>
<p id="id_messageTip" class="text-success"></p>
</#if>
</#macro>

<#macro M_editorFontSize >
<p class="text-center">改变段落字号</p>
<div class="row">
<div class="col-xs-4"> <img src="/assets/common/help/editor/font/help_editor_font_size_1.jpg" class="img-responsive" alt="1.更改字号-原字号想改小" onclick="popBigViewOnly('/assets/common/help/editor/font/help_editor_font_size_1.jpg','更改字号-1.例如：原字号太大，想改小', false)"></div>
<div class="col-xs-4"> <img src="/assets/common/help/editor/font/help_editor_font_size_2.jpg" class="img-responsive" alt="2.在段中任意一处单击，再点H，弹出字号框，选‘正文’" onclick="popBigViewOnly('/assets/common/help/editor/font/help_editor_font_size_2.jpg','更改字号-2.在段中任意一处单击，再点H，弹出字号框，选‘正文’', false)"></div>
<div class="col-xs-4"> <img src="/assets/common/help/editor/font/help_editor_font_size_3.jpg" class="img-responsive" alt="3.查看改后结果，并保存" onclick="popBigViewOnly('/assets/common/help/editor/font/help_editor_font_size_3.jpg','更改字号-3.查看改后结果，并保存', false)"></div>
</div>
</#macro>

<#macro M_editorAlign >
<p class="text-center">更改段落对齐方式</p>
<div class="row">
<div class="col-xs-4"> <img src="/assets/common/help/editor/align/help_editor_align_1.jpg" class="img-responsive" alt="1.原文是左对齐；点对齐，弹出选择对齐方式面板，选右对齐" onclick="popBigViewOnly('/assets/common/help/editor/align/help_editor_align_1.jpg','更改段落对齐方式-1.例如：原文是左对齐；点对齐，弹出选择对齐方式面板，选右对齐', false)"></div>
<div class="col-xs-4"> <img src="/assets/common/help/editor/align/help_editor_align_2.jpg" class="img-responsive" alt="2.查看改后结果，点保存" onclick="popBigViewOnly('/assets/common/help/editor/align/help_editor_align_2.jpg','更改段落对齐方式-2.查看改后结果，点保存', false)"></div>
<div class="col-xs-4"> <img src="/assets/common/help/editor/align/help_editor_align_3.jpg" class="img-responsive" alt="3.可点[预览]按钮，全文查看一下" onclick="popBigViewOnly('/assets/common/help/editor/align/help_editor_align_3.jpg','更改段落对齐方式-3.可点预览按钮，全文查看一下', false)"></div>
</div>
</#macro>
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#--段落编辑大组件开始-->
<#macro M_paragraphContainer id="001" page=null entity=null>
<div id="id_paragraph_container${001}">
	<@M_paragraphBtn btnId="btn1" id=id />
	<@Mg_paragraphListEdit page=page entity=entity />
</div>
</#macro>

<#macro M_paragraphBtn btnId="btn1" id="001">
<div id="id_paragrap_${btnId}${id}" class="btn-group btn-group-xs btn-group-justified" role="group">

<a class="btn btn-xs btn-primary" href="javascript:void(0)" onclick="addParagraphSingle('${id}','/sz0099/ood/article/manage/paragraph/add')">添加段落</a>
<a class="btn btn-xs btn-warning" href="javascript:void(0)" onclick="refreshParagraph('${id}','/sz0099/ood/article/manage/paragraph/editListUI')">刷新段落</a>
<a class="btn btn-xs btn-danger" href="javascript:void(0)" onclick="deleteAllParagraph('${id}','/sz0099/ood/article/manage/paragraph/deleteAll')">全部删除</a>
</div>
<br/>
</#macro>

<#macro Mg_paragraphListEdit page=null entity=null extend=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@Mg_paragraphEdit entity=ce extend=extend/>
		
		</#list>
	<#else>
	<@Mg_paragraphEdit entity=entity extend=extend/>
	</#if>
<#else>
<@Mg_paragraphEdit entity=entity extend=extend/>
</#if>
</#macro>

<#macro Mg_paragraphEdit entity=null extend=null>
<div id="paragragh_${entity.id}"> <!--div wrapper-->
	<div class="container" id="id_panel_paragId${entity.paragId}" name="paragraph">
				<p class="panel-title text-center">
			    <input type="hidden" id="id_paragId${entity.paragId}" name="paragId" value="${entity.paragId}" class="form-control" >
			    <input type="hidden" id="id_paragProductId${entity.paragId}" name="id" value="${entity.id}" class="form-control" >
		    	</p>
				<div id="id_parag_photoPreview${entity.paragId}" >
					<div id="id_messageTip_preview${entity.paragId}" class="text-center"></div>
					<ul class="list-group" id="id_wrapper_uploaderFiles" >
					<@Mg_paragraphPhotoEdit paragraph=entity.paragraph extend=extend/>
					</ul>
				</div>
				<@Mgp_uploadImage id="uploaderInput"+entity.paragId mainId=entity.mainId subId=entity.paragId strategy=1 extend=extend/>
				
	  </div><!--end panel-->
</div><!--end div wrapper-->
<script>
$(document).ready(function(){
initUpload("uploaderInput${entity.paragId}", 'id_wrapper_uploaderFiles');
});
</script>
</#macro>

<#macro Mg_paragraphPhotoEdit paragraph=null extend=null>
<#if paragraph??>
	<#assign photoParagList=paragraph.photoList />
	<#if photoParagList??>
		<#list photoParagList as photoParag>
		<#if photoParag!>
			<@Mg_imageRefEdit id="uploaderInput"+photoParag.paragId imageRef=photoParag />
		</#if>
		</#list>
	</#if>
</#if>
</#macro>
<#--段落编辑大组件结束-->



<#--段落View大组件开始 Page<ParagProduct> -->
<#macro Mg_paragraphViewContainer id="v001" paragPage=null>
<div id="id_paragraphView_container${id}">
	<@Mg_paragraphListView paragPage=paragPage/>
</div>
</#macro>

<#macro Mg_paragraphListView paragPage=null>
<#if paragPage??>
	<#assign content=paragPage.content>
	<#if content!?size gt 0 >
		<#list content as ce>
			<@Mg_paragraphView entity=ce />
		</#list>
	</#if>
</#if>
</#macro>

<#macro Mg_paragraphView entity>

<#assign paragraph=entity.paragraph />
<#if paragraph!>
<#-- 
<#assign pname = paragraph.name />
<#if pname!>
 -->
<div id="paragragh_view_${entity.id}"> <!--div wrapper-->
	
			    <input type="hidden" id="id_paragId${entity.paragId}" name="paragId" value="${entity.paragId}" class="form-control" >
			    <input type="hidden" id="id_paragProductId${entity.paragId}" name="id" value="${entity.id}" class="form-control" >
		  
			   <h4 class="text-center"><strong>${HtmlUtils.htmlUnescape(paragraph.title)}</strong></h4>
			   <p class="text-left">
			   		&nbsp;&nbsp;${HtmlUtils.htmlUnescape(paragraph.description)}
			   </p>
				<@Mg_paragraphPhotoView paragraph=paragraph/>
		  		<p class="text-right" style="margin:0px 0px">
		  		<small>
		    	#<span id="id_parag_orderSeq_show${entity.paragId}">${entity.orderSeq}</span>
		    	<#assign pname = paragraph.name />
		    	<span id="id_parag_name_show${entity.paragId}">${pname}</span>
		    	<span class="glyphicon glyphicon-arrow-up"></span>
		    	</small>
		    	</p>
		    	<hr/>
</div><!--end div wrapper-->

</#if>
<#-- 
</#if>
 -->
</#macro>

<#macro Mg_paragraphPhotoView paragraph=null>
<#if paragraph??>
<#assign photoParagList=paragraph.photoList />
	<#if photoParagList! && photoParagList?size gt 0>
	   <ul class="list-inline" >
		<#list photoParagList as photoParag>
		<#if photoParag!>
		<#assign description=photoParag.description/>
				<li>
					<#if ImageRef.TYPE_IMG.valueInt==photoParag.type>
			    	<a href='javascript:void(0)' onclick="showBigViewOnly('${photoParag.fullurl}',720,'${photoParag.width}','${photoParag.aid}')">
					  <img id='id_tpl_media_common_img${photoParag.aid}' class='img-responsive' src='${PhotoUtil.getShowUrlForExpected2(photoParag.fullurl,720, photoParag.width)}' alt='${HtmlUtil.substring(description,0,15)}'>
					</a>
					</#if>
					<div class="text-center">${HtmlUtils.htmlUnescape(description)}</div>
		    	</li>
		</#if>
		</#list>
		</ul>
		<hr style="margin:0px 0px"/>
	</#if>
</#if>
</#macro>
<#--段落View大组件结束-->


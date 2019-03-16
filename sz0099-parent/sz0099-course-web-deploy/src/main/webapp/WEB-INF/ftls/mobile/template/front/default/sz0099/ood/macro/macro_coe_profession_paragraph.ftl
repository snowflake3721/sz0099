<#--段落编辑大组件开始-->
<#macro M_paragraphContainer id="001" page=null entity=null>
<div id="id_paragraph_container${001}">
	<@M_paragraphBtn btnId="btn1" id=id />
	<@M_paragraphListEdit page=page entity=entity />
</div>
</#macro>

<#macro M_paragraphBtn btnId="btn1" id="001">
<div id="id_paragrap_${btnId}${id}" class="btn-group btn-group-xs btn-group-justified" role="group">

<a class="btn btn-xs btn-primary" href="javascript:void(0)" onclick="addParagraphSingle('${id}','id_contentPhoto_content${id}','/sz0099/ood/personal/profession/manage/paragraph/add')">添加段落</a>
<a class="btn btn-xs btn-warning" href="javascript:void(0)" onclick="refreshParagraph('${id}', 'id_contentPhoto_content${id}', '/sz0099/ood/personal/profession/manage/paragraph/editListUI')">刷新段落</a>
<a class="btn btn-xs btn-danger" href="javascript:void(0)" onclick="deleteAllParagraph('${id}', 'id_contentPhoto_content${id}', '/sz0099/ood/personal/profession/manage/paragraph/deleteAll')">全部删除</a>
</div>
<br/>
</#macro>

<#macro M_paragraphListEdit page=null entity=null>
<#if page??>
	<#assign content=page.content>
	<#if content?size gt 0 >
		<#list content as ce>
		<@M_paragraphEdit entity=ce />
		
		</#list>
	<#else>
	<@M_paragraphEdit entity=entity />
	</#if>
<#else>
<@M_paragraphEdit entity=entity />
</#if>
</#macro>

<#macro M_paragraphEdit entity>
<div id="paragragh_${entity.id}"> <!--div wrapper-->
	
	<div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		    	<p class="panel-title text-center">
		    	<button type="button" class="btn btn-xs btn-warning pull-left" onclick="saveParagraphSingle('${entity.paragId}','/sz0099/ood/personal/profession/manage/paragraph/mergeSingle')">保存★</button>
		    	<#-- <#assign name=entity.paragraph.name /> -->
		    	<strong>#<span id="id_parag_orderSeq_show${entity.paragId}">${entity.orderSeq}</span></strong>
		    	<#assign pname = entity.paragraph.name />
		    	
		    	<span id="id_parag_name_show${entity.paragId}">${getSubstring(pname,10,'编辑段落')}</span>
		    	<button type="button" class="btn btn-xs btn-danger pull-right" onclick="deleteParagraphSingle('${entity.paragId}', '/sz0099/ood/personal/profession/manage/paragraph/deleteSingle')">删除</button>
			    <input type="hidden" id="id_paragId${entity.paragId}" name="paragId" value="${entity.paragId}" class="form-control" >
			    <input type="hidden" id="id_paragProductId${entity.paragId}" name="id" value="${entity.id}" class="form-control" >
		    	</p>
		    	
		  </div>
		  <div class="panel-body">
		  	  <div class="form-group form-group-sm">
			    <label for="id_parag_orderSeq${entity.paragId}">段落排序（数字，例如：1,2,3...）</label>
			    <input type="text" id="id_parag_orderSeq${entity.paragId}" name="orderSeq" 
			    value="${entity.orderSeq}" class="form-control" 
			    onkeyup="keyPressPositive(this)"  
				onafterpaste="onAfterPastePositive(this)"
				onchange="onChangeShow('id_parag_orderSeq_show${entity.paragId}',this.value)"
			    placeholder="段落排序，输入数字">
			  </div>
			  
			  <div class="form-group form-group-sm">
			    <label for="id_parag_name${entity.paragId}" class="text-danger">段落名称(简短，少于20字符)★<small>显示于本段于右下角</small></label>
			    <input type="text" id="id_parag_name${entity.paragId}" 
			    name="name" 
			    onchange="onChangeShow('id_parag_name_show${entity.paragId}',this.value,10)"
			    value="${entity.paragraph.name}" class="form-control" placeholder="段落名称">
			  </div>
			  
			  <div class="form-group form-group-sm">
			    <label for="id_parag_title${entity.paragId}" class="text-danger">段落标题(少于60字符)★</label>
			    <input type="text" id="id_parag_title${entity.paragId}" name="title" value="${entity.paragraph.title}" class="form-control" placeholder="段落标题">
			  </div>
			  
			  <div class="form-group form-group-sm">
			    <label for="id_parag_description${entity.paragId}">段落内容(少于255字符)</label>
			    <textarea id="id_parag_description${entity.paragId}" name="description" rows="4" class="form-control" placeholder="段落内容，少于255字">${entity.paragraph.description}</textarea>
			  </div>
			  
				<div class="file-loading">
					<input id="id_file_${entity.paragId}" data-devId="sz0099" data-project="ood" data-module="personal" data-variety="profession" data-strategy="1" data-position="paragragh" data-mainId="${entity.mainId}" data-subId="${entity.paragId}" type="file" multiple name="files" />
				</div>
				<p class="text-danger">
					<small>
						<em>选择段落图片后（限5张以内），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</small>
				</p>
				
				<div id="id_parag_photoPreview${entity.paragId}" >
				<div id="id_messageTip_preview${entity.paragId}" class="text-center"></div>
				
				<@M_paragraphPhotoEdit paragraph=entity.paragraph/>
				<#--  -->
				
				</div>
					
		    </div><!--end panel-body-->
		    
		    <div class="panel-footer">
		    	<p class="text-center"><strong>每段都要点保存哦！</strong>-->
		    	<button type="button" class="btn btn-sm btn-warning pull-right" onclick="saveParagraphSingle('${entity.paragId}','/sz0099/ood/personal/profession/manage/paragraph/mergeSingle')">保存当前段落★</button>
		    	</p>
		    </div>
	  </div><!--end panel-->
</div><!--end div wrapper-->
<script>
$(document).ready(function(){

});
</script>
</#macro>

<#macro M_paragraphPhotoEdit paragraph=null>
<#if paragraph??>
<#assign photoParagList=paragraph.photoList />
<#if photoParagList??>
<#list photoParagList as photoParag>

<#if photoParag!>
<div class='media' id='id_tpl_media_id${photoParag.id}'>
  <div class='media-left'>
	<a href='javascript:void(0)' onclick="showBigView('${photoParag.fullurl}',800,'${photoParag.width}','${photoParag.id}')">
	  <img id='id_tpl_media_common_img${photoParag.id}' class='media-object' width='200px' src='${PhotoUtil.getShowUrlForAd(photoParag.expectedUrl,photoParag.fullurl,photoParag.width)}' alt='${photoParag.id}'>
	</a>
  </div> 
  <div class='media-body'> 
	<input type='text' id='id_tpl_media_common_orderSeq${photoParag.id}' name='orderSeq' 
	value='${photoParag.orderSeq}' class='form-control' onchange='keyPressPositive(this)' 
	onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
	onblur='saveImageRef("${photoParag.id}","id_messageTip_preview${photoParag.paragId}")'
	placeholder='数字排序'>
	<input type='text' id='id_tpl_media_common_title${photoParag.id}' name='title'
	value='${photoParag.title}' class='form-control'
	onblur='saveImageRef("${photoParag.id}","id_messageTip_preview${photoParag.paragId}")'
	placeholder='图片小标题'>
	<#--<p><button class='btn btn-xs btn-primary' type='button' onclick='saveImageRef("${photoParag.id}","id_messageTip_preview${photoParag.paragId}")'>保存</button> <p/>-->
	<p><button class='btn btn-xs btn-danger' type='button' onclick='deleteImageRef("${photoParag.id}","id_messageTip_preview${photoParag.paragId}")'>移除</button><p/>
  </div>
</div>
</#if>

</#list>
</#if>
</#if>
</#macro>
<#--段落编辑大组件结束-->





<#--段落View大组件开始 Page<ParagProduct> -->
<#macro M_paragraphViewContainer id="v001" paragPage=null>
<div id="id_paragraphView_container${001}">
	<@M_paragraphListView paragPage=paragPage/>
</div>
</#macro>

<#macro M_paragraphListView paragPage=null>
<#if paragPage??>
	<#assign content=paragPage.content>
	<#if content!?size gt 0 >
		<#list content as ce>
			<@M_paragraphView entity=ce />
		</#list>
	</#if>
</#if>
</#macro>

<#macro M_paragraphView entity>

<#assign paragraph=entity.paragraph />
<#if paragraph!>
<#assign pname = paragraph.name />
<#if pname!>
<div id="paragragh_view_${entity.id}"> <!--div wrapper-->
	
			    <input type="hidden" id="id_paragId${entity.paragId}" name="paragId" value="${entity.paragId}" class="form-control" >
			    <input type="hidden" id="id_paragProductId${entity.paragId}" name="id" value="${entity.id}" class="form-control" >
		  
			   <p class="text-center" style="margin:2px 2px"><strong>${paragraph.title}</strong></p>
			   <p class="text-left" style="margin:0px 0px">
			   		&nbsp; &nbsp; &nbsp; &nbsp; ${paragraph.description}
			   </p>
				<@M_paragraphPhotoView paragraph=paragraph/>
		  		
		    	<p class="text-right" style="margin:0px 0px">
		  		<small>
		    	#<span id="id_parag_orderSeq_show${entity.paragId}">${entity.orderSeq}</span>
		    	<span id="id_parag_name_show${entity.paragId}">${pname}</span>
		    	<span class="glyphicon glyphicon-arrow-up"></span>
		    	</small>
		    	</p>
		    	<hr/>
</div><!--end div wrapper-->

</#if>
</#if>
</#macro>

<#macro M_paragraphPhotoView paragraph=null>
<#if paragraph??>
<#assign photoParagList=paragraph.photoList />
	<#if photoParagList! && photoParagList?size gt 0>
	   <ul class="list-inline" >
		<#list photoParagList as photoParag>
		<#if photoParag!>
				<li style="margin:0px 0px">
			    	<a href='javascript:void(0)' onclick="showBigView('${photoParag.fullurl}',1024,'${photoParag.width}','${photoParag.aid}')">
					  <img id='id_tpl_media_common_img${photoParag.aid}' class='img-responsive' src='${PhotoUtil.getShowUrlForExpected2(photoParag.fullurl,720, photoParag.width)}' alt='${photoParag.title}'>
					</a>
					<p class="text-right" style="margin:0px 0px">${photoParag.title}</p>
		    	</li>
		</#if>
		</#list>
		</ul>
	</#if>
</#if>
</#macro>
<#--段落View大组件结束-->


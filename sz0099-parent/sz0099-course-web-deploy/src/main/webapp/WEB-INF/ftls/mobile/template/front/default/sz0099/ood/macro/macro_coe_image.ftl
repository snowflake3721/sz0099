<#-- image begin -->
<#macro M_showImageForEdit imageList=null subId=""> 
	<div id="id_messageTip_preview${subId}" class="text-center"></div>
 	<#if imageList! && imageList?size gt 0 >
	 	<#list imageList as image>
	 	<div class='media' id='id_tpl_media_id${image.id}'>
		  <div class='media-left'>
			<a href='javascript:void(0)' onclick="showBigView('${image.fullUrl}',720, ${image.width},'${image.id}')">
			  <img id='id_tpl_media_common_img${image.id}' class='media-object' width='200px' src='${image.expectedUrl}' alt='${image.id}'>
			</a> 
		  </div>
		  <div class='media-body'> 
		  	<input type='text' id='id_tpl_media_common_orderSeq${image.id}' name='orderSeq' 
			value='${image.orderSeq}' class='form-control' onchange='keyPressPositive(this)' 
			onkeyup='keyPressPositive(this)' onafterpaste='onAfterPastePositive(this)'
			onblur='saveImageRef("${image.id}","id_messageTip_preview${subId}")'
			placeholder='数字排序'>
			<input type='text' id='id_tpl_media_common_title${image.id}' name='title'
			value='${image.title}' class='form-control'
			onblur='saveImageRef("${image.id}","id_messageTip_preview${subId}")'
			placeholder='图片小标题'>
			<#--<p><button class='btn btn-xs btn-primary' type='button' onclick='saveImageRef("${image.id}","id_messageTip_preview${subId}")'>保存</button> <p/>-->
			<p><button class='btn btn-xs btn-danger' type='button' onclick='deleteImageRef("${image.id}","id_messageTip_preview${subId}")'>移除</button><p/>
		  </div>
		</div>
		</#list>
	</#if>
</#macro>


<#macro M_showImageForView imageList=null> 
 	<#if imageList! && imageList?size gt 0 >
	 	<#list imageList as image>
			<a href='javascript:void(0)' onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
			  <img id='id_tpl_media_common_img${image.id}' class='media-object'  width='130px' src='${image.expectedUrl}' alt='${image.id}'>
			</a> 
		</#list>
	</#if>
</#macro>
<#-- image end -->

<#macro M_showHeadImg imageList=null> 
 	<#if imageList! && imageList?size gt 0 >
 	<ul class="list-inline">
	 	<#list imageList as image>
			<li>
			<span onclick="showSaywordHistory('${image.nickname}','${image.sayword.description}','@${DateUtils.formatToString(image.refreshTime ,'yyyy-MM-dd')}')">
			  <img id='id_tpl_media_headImg${image.id}' class='img-circle'  width='40px' height='40px' src='${image.headImg}' alt='${image.nickname}' title='${image.sayword.description}' />
			</span> 
			</li>
		</#list>
	</ul>	
	</#if>
</#macro>

<#macro M_showHeadImgPage authorPage=null> 
	<#if authorPage!>
		<#assign imageList=authorPage.content />
	 	<#if imageList! && imageList?size gt 0 >
		 	<@M_showHeadImg imageList=imageList/>
		</#if>
	 	<#if authorPage.totalPages gt 1 >
	 	...
		</#if>
	</#if>
</#macro>



<#macro M_showImageForSelect imageList=null width="130px" > 
 	<#if imageList! && imageList?size gt 0 >
 	<ul class="list-inline">
	 	<#list imageList as image>
			<li>
			<a href='javascript:void(0)' onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
			  <img id='id_ad_img${image.id}' class='media-object'  width='${width}' src='${image.expectedUrl}' alt='${image.id}'>
			</a> 
			</li>
		</#list>
		</ul>
	</#if>
</#macro>

<#macro M_showImageForRefEdit imageList=null width="130px" > 
 	<#if imageList! && imageList?size gt 0 >
 	<ul class="list-inline" style="padding-left: 10px;">
	 	<#list imageList as image>
			<li>
			<a href='javascript:void(0)' onclick="showBigView('${image.fullurl}',720, 720,'${image.id}')">
			  <img id='id_ad_img_ref${image.id}' class='media-object'  width='${width}' src='${image.fullurl}' alt='${image.id}'>
			</a> 
			</li>
		</#list>
		</ul>
	</#if>
</#macro>



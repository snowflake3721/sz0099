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

<#macro Mg_coverImageForEdit id="coverUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mg_bannerImageForEdit id="bannerUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mga_coverImageForEdit id="coverUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mga_bannerImageForEdit id="bannerUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mgp_coverImageForEdit id="coverUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mgp_bannerImageForEdit id="bannerUploaderInput" imageList=null subId=null>
<@Mg_showImageListForEdit id=id imageList=imageList subId=subId />
</#macro>

<#macro Mg_showImageListForEdit id="uploaderInput" imageList=null subId=null>
<#if imageList! && imageList?size gt 0 >
<#list imageList as imageRef>
<#if imageRef!>
<@Mg_showImageForEdit id=id imageRef=imageRef subId=subId />
</#if>
</#list>
</#if>
</#macro>

<#macro Mg_showImageForEdit id="uploaderInput" imageRef=null subId=null>
<#if imageRef!>
<li class="list-group-item" style="padding:0px 0px" id="id_tpl_li_${imageRef.id}">
	<div class="row" style="padding:0px 0px;margin:0px 0px">
		  <div class="col-xs-6" style="padding:0px 0px">
			    <a href="javascript:void(0)" id="id_img_${imageRef.id}_wrapper">
			    <#assign photo=imageRef.fullurl />
			    <#assign photoUrl='/assets/common/images/logo/click_upload.gif' />
			    <#if photo! && imageRef.fullurl! && ImageRef.TYPE_IMG.valueInt==imageRef.type>
			    <#assign photoUrl=PhotoUtil.getShowUrlForAd(imageRef.expectedUrl,imageRef.fullurl,imageRef.width)/>
			    </#if>
			    <img class="img-responsive" src="${photoUrl}" id="id_img_${imageRef.id}" data-imgWrapperId="id_img_${imageRef.id}_wrapper" data-type="${imageRef.type}" data-refId="${imageRef.id}"/>
			    </a>
		  </div>
		  <div class="col-xs-5" style="padding:0px 0px">
		  		<div class="form-group form-group-sm">
				    <input type="text" id="id_orderSeq${imageRef.id}" name="orderSeq" 
				    value="${imageRef.orderSeq}" class="form-control" 
				    onkeyup="keyPressPositive(this)" 
					onafterpaste="onAfterPastePositive(this)"
					onchange="mergeRefForOrderSeq('${id}', '${imageRef.id}','id_orderSeq${imageRef.id}')"
				    placeholder="排序">
				  </div>
		  </div>
		  <div class="col-xs-1" style="padding:0px 0px">
		  		<button type="button" class="close"><span class="glyphicon glyphicon-remove" onclick="deleteRef('${id}','${imageRef.id}','id_tpl_li_${imageRef.id}')"></span></button>
		  </div>
	</div>
</li>
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
	 	<#list imageList as image>
			<#-- <a href='javascript:void(0)' onclick="showProfile(function(){alert('${image.userId}')})"> -->
			<a href='javascript:void(0)' onclick="showSaywordHistory('${image.nickname}','${image.sayword.description}','@${DateUtils.formatToString(image.refreshTime ,'yyyy-MM-dd')}')">
			  <img id='id_tpl_media_headImg${image.id}' class='img-circle'  width='40px' height='40px' src='${image.headImg}' alt='${image.nickname}' title='${image.sayword.description}' />
			</a> 
		</#list>
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

<#macro Mg_uploadConverImage id="coverUploaderInput" mainId=null subId=null strategy=1 extend=null label="封面图★" title="选择图片（限1-2张, 宽>高）">
<#assign data_wrapperId="id_wrapper_coverUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/article/cover/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/article/cover/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/article/cover/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/article/cover/delete"/>
<#assign data_createRefUrl="/sz0099/ood/article/cover/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/article/cover/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/article/cover/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/article/cover/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/article/cover/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_coverUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=true
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>

<#macro Mg_uploadBannerImage id="bannerUploaderInput" mainId=null subId=null strategy=1 extend=null label="顶部滚动图[可选传]" title="选择图片（限1-3张, 宽>高）">
<#assign data_wrapperId="id_wrapper_bannerUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/article/banner/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/article/banner/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/article/banner/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/article/banner/delete"/>
<#assign data_createRefUrl="/sz0099/ood/article/banner/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/article/banner/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/article/banner/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/article/banner/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/article/banner/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_bannerUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=false
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>


<#macro Mgp_uploadConverImage id="coverUploaderInput" mainId=null subId=null strategy=1 extend=null label="封面图★" title="选择图片（限1-2张, 宽>高）">
<#assign data_wrapperId="id_wrapper_coverUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/personal/profession/cover/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/personal/profession/cover/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/personal/profession/cover/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/personal/profession/cover/delete"/>
<#assign data_createRefUrl="/sz0099/ood/personal/profession/cover/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/personal/profession/cover/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/personal/profession/cover/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/personal/profession/cover/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/personal/profession/cover/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_coverUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=true
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>

<#macro Mgp_uploadBannerImage id="bannerUploaderInput" mainId=null subId=null strategy=1 extend=null label="顶部滚动图[可选传]" title="选择图片（限1-3张, 宽>高）">
<#assign data_wrapperId="id_wrapper_bannerUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/personal/profession/banner/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/personal/profession/banner/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/personal/profession/banner/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/personal/profession/banner/delete"/>
<#assign data_createRefUrl="/sz0099/ood/personal/profession/banner/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/personal/profession/banner/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/personal/profession/banner/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/personal/profession/banner/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/personal/profession/banner/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_bannerUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=false
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>

<#macro Mga_uploadConverImage id="coverUploaderInput" mainId=null subId=null strategy=1 extend=null label="封面图★" title="选择图片（限1-2张, 宽>高）">
<#assign data_wrapperId="id_wrapper_coverUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/activity/cover/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/activity/cover/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/activity/cover/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/activity/cover/delete"/>
<#assign data_createRefUrl="/sz0099/ood/activity/cover/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/activity/cover/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/activity/cover/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/activity/cover/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/activity/cover/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_coverUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=true
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>

<#macro Mga_uploadBannerImage id="bannerUploaderInput" mainId=null subId=null strategy=1 extend=null label="顶部滚动图[可选传]" title="选择图片（限1-3张, 宽>高）">
<#assign data_wrapperId="id_wrapper_bannerUploaderFiles"/>
<#assign data_requestUrl="/sz0099/ood/activity/banner/create"/>
<#assign data_mergeRefDescUrl="/sz0099/ood/activity/banner/mergeRefForDescription"/>
<#assign data_mergeRefOrderUrl="/sz0099/ood/activity/banner/mergeRefForOrder"/>
<#assign data_deleteRefUrl="/sz0099/ood/activity/banner/delete"/>
<#assign data_createRefUrl="/sz0099/ood/activity/banner/create"/>
<#assign data_refreshRefDescUrl="/sz0099/ood/activity/banner/refreshRefForDescription"/>
<#assign data_deleteImageRefUrl="/sz0099/ood/activity/banner/deleteImageById"/>
<#assign data_addImageRefUrl="/sz0099/ood/activity/banner/addImageById"/>
<#assign data_changeImageRefUrl="/sz0099/ood/activity/banner/changeImageById"/>
<#assign data_uploadImg="/assets/common/images/logo/click_upload.gif"/>
<#assign data_clickType="0"/>
<#assign data_type="0"/>
<#assign data_loadPosition="id_wrapper_bannerUploaderFiles"/>
<@Mg_uploadImageCommon id=id mainId=mainId subId=subId strategy=strategy extend=extend label=label title=title required=false
data_wrapperId=data_wrapperId
data_requestUrl=data_requestUrl
data_mergeRefDescUrl=data_mergeRefDescUrl
data_mergeRefOrderUrl=data_mergeRefOrderUrl
data_deleteRefUrl=data_deleteRefUrl
data_createRefUrl=data_createRefUrl
data_refreshRefDescUrl=data_refreshRefDescUrl
data_deleteImageRefUrl=data_deleteImageRefUrl
data_addImageRefUrl=data_addImageRefUrl
data_changeImageRefUrl=data_changeImageRefUrl
data_uploadImg=data_uploadImg
data_clickType=data_clickType
data_type=data_type
data_loadPosition=data_loadPosition
/>
</#macro>


<#macro Mg_uploadImageCommon id="coverUploaderInput" mainId=null subId=null strategy=1 extend=null label="" title="选择图片" required=false
data_wrapperId="id_wrapper_coverUploaderFiles"
data_requestUrl="/sz0099/ood/article/cover/create"
data_mergeRefDescUrl="/sz0099/ood/article/cover/mergeRefForDescription"
data_mergeRefOrderUrl="/sz0099/ood/article/cover/mergeRefForOrder"
data_deleteRefUrl="/sz0099/ood/article/cover/delete"
data_createRefUrl="/sz0099/ood/article/cover/create"
data_refreshRefDescUrl="/sz0099/ood/article/cover/refreshRefForDescription"
data_deleteImageRefUrl="/sz0099/ood/article/cover/deleteImageById"
data_addImageRefUrl="/sz0099/ood/article/cover/addImageById"
data_changeImageRefUrl="/sz0099/ood/article/cover/changeImageById"
data_uploadImg="/assets/common/images/logo/click_upload.gif"
data_clickType="0"
data_type="0"
data_loadPosition="id_wrapper_coverUploaderFiles"
>
<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
		<div class="weui-cell__bd">
			<div class="weui-uploader">
				<div class="weui-uploader__hd">
					<p class="text-left <#if required>text-danger</#if>">${label}</p> <p class="weui-uploader__title text-center">${title}</p>
				</div>
				<div class="weui-uploader__bd">
					<div class="weui-uploader__input-box">
						<input id="${id}" class="weui-uploader__input zjxfjs_file" accept="image/*" type="file" multiple="" name="files"
						data-devId="${extend.devId}" data-project="${extend.project}" data-module="${extend.module}" data-variety="${extend.variety}"
						data-position="${extend.position}" data-strategy="${strategy}" data-mainId="${mainId}" data-subId="${subId}" 
						data-expectedW="300"
						data-orderSeq=""
						data-refId=""
						data-wrapperId="${data_wrapperId}"
						data-requestUrl="${data_requestUrl}"
						data-mergeRefDescUrl="${data_mergeRefDescUrl}"
						data-mergeRefOrderUrl="${data_mergeRefOrderUrl}"
						data-deleteRefUrl="${data_deleteRefUrl}"
						data-createRefUrl="${data_createRefUrl}"
						data-refreshRefDescUrl="${data_refreshRefDescUrl}"
						data-deleteImageRefUrl="${data_deleteImageRefUrl}"
						data-addImageRefUrl="${data_addImageRefUrl}"
						data-changeImageRefUrl="${data_changeImageRefUrl}"
						data-uploadImg="${data_uploadImg}"
						data-clickType="${data_clickType}"
						data-type="${data_type}"
						data-loadPosition="${data_loadPosition}"
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</#macro>

<#macro Mg_uploadImage id="uploaderInput" mainId=null subId=null strategy=1 extend=null>
<#-- 
<ul class="list-group" id="uploaderFiles">
		    
</ul>
 -->

<!--图片上传-->
<div class="weui-gallery" id="gallery">
	<span class="weui-gallery__img" id="galleryImg"></span>
	<div class="weui-gallery__opr">
		<a href="javascript:" class="weui-gallery__del">
			<i class="weui-icon-delete weui-icon_gallery-delete"></i>
		</a>
	</div>
</div>
<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
		<div class="weui-cell__bd">
			<div class="weui-uploader">
				<div class="weui-uploader__hd">
					<p class="weui-uploader__title">选择图片</p>
				</div>
				<div class="weui-uploader__bd">
					<div class="weui-uploader__input-box">
						<input id="${id}" class="weui-uploader__input zjxfjs_file" accept="image/*" type="file" multiple="" name="files"
						data-devId="${extend.devId}" data-project="${extend.project}" data-module="${extend.module}" data-variety="${extend.variety}"
						data-position="${extend.position}" data-strategy="${strategy}" data-mainId="${mainId}" data-subId="${subId}" 
						data-expectedW="300"
						data-orderSeq=""
						data-refId=""
						data-wrapperId="id_wrapper_uploaderFiles"
						data-requestUrl="/sz0099/ood/article/parag/create"
						data-mergeRefDescUrl="/sz0099/ood/article/parag/mergeRefForDescription"
						data-mergeRefOrderUrl="/sz0099/ood/article/parag/mergeRefForOrder"
						data-deleteRefUrl="/sz0099/ood/article/parag/delete"
						data-createRefUrl="/sz0099/ood/article/parag/create"
						data-refreshRefDescUrl="/sz0099/ood/article/parag/refreshRefForDescription"
						data-deleteImageRefUrl="/sz0099/ood/article/parag/deleteImageById"
						data-addImageRefUrl="/sz0099/ood/article/parag/addImageById"
						data-changeImageRefUrl="/sz0099/ood/article/parag/changeImageById"
						data-uploadImg="/assets/common/images/logo/click_upload.gif"
						data-clickType="0"
						data-type="0"
						data-loadPosition="id_wrapper_uploaderFiles"
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</#macro>

<#macro Mga_uploadImage id="uploaderInput" mainId=null subId=null strategy=1 extend=null>
<!--图片上传-->
<div class="weui-gallery" id="gallery">
	<span class="weui-gallery__img" id="galleryImg"></span>
	<div class="weui-gallery__opr">
		<a href="javascript:" class="weui-gallery__del">
			<i class="weui-icon-delete weui-icon_gallery-delete"></i>
		</a>
	</div>
</div>
<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
		<div class="weui-cell__bd">
			<div class="weui-uploader">
				<div class="weui-uploader__hd">
					<p class="weui-uploader__title">选择图片</p>
				</div>
				<div class="weui-uploader__bd">
					<div class="weui-uploader__input-box">
						<input id="${id}" class="weui-uploader__input zjxfjs_file" accept="image/*" type="file" multiple="" name="files"
						data-devId="${extend.devId}" data-project="${extend.project}" data-module="${extend.module}" data-variety="${extend.variety}"
						data-position="${extend.position}" data-strategy="${strategy}" data-mainId="${mainId}" data-subId="${subId}" 
						data-expectedW="300"
						data-orderSeq=""
						data-refId=""
						data-wrapperId="id_wrapper_uploaderFiles"
						data-requestUrl="/sz0099/ood/activity/parag/create"
						data-mergeRefDescUrl="/sz0099/ood/activity/parag/mergeRefForDescription"
						data-mergeRefOrderUrl="/sz0099/ood/activity/parag/mergeRefForOrder"
						data-deleteRefUrl="/sz0099/ood/activity/parag/delete"
						data-createRefUrl="/sz0099/ood/activity/parag/create"
						data-refreshRefDescUrl="/sz0099/ood/activity/parag/refreshRefForDescription"
						data-deleteImageRefUrl="/sz0099/ood/activity/parag/deleteImageById"
						data-addImageRefUrl="/sz0099/ood/activity/parag/addImageById"
						data-changeImageRefUrl="/sz0099/ood/activity/parag/changeImageById"
						data-uploadImg="/assets/common/images/logo/click_upload.gif"
						data-clickType="0"
						data-type="0"
						data-loadPosition="id_wrapper_uploaderFiles"
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</#macro>

<#macro Mgp_uploadImage id="uploaderInput" mainId=null subId=null strategy=1 extend=null>
<!--图片上传-->
<div class="weui-gallery" id="gallery">
	<span class="weui-gallery__img" id="galleryImg"></span>
	<div class="weui-gallery__opr">
		<a href="javascript:" class="weui-gallery__del">
			<i class="weui-icon-delete weui-icon_gallery-delete"></i>
		</a>
	</div>
</div>
<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
		<div class="weui-cell__bd">
			<div class="weui-uploader">
				<div class="weui-uploader__hd">
					<p class="weui-uploader__title">选择图片</p>
				</div>
				<div class="weui-uploader__bd">
					<div class="weui-uploader__input-box">
						<input id="${id}" class="weui-uploader__input zjxfjs_file" accept="image/*" 
						type="file" multiple="" 
						name="files"
						data-devId="${extend.devId}" data-project="${extend.project}" data-module="${extend.module}" data-variety="${extend.variety}"
						data-position="${extend.position}" data-strategy="${strategy}" data-mainId="${mainId}" data-subId="${subId}" 
						data-expectedW="300"
						data-orderSeq=""
						data-refId=""
						data-wrapperId="id_wrapper_uploaderFiles"
						data-requestUrl="/sz0099/ood/personal/profession/parag/create"
						data-mergeRefDescUrl="/sz0099/ood/personal/profession/parag/mergeRefForDescription"
						data-mergeRefOrderUrl="/sz0099/ood/personal/profession/parag/mergeRefForOrder"
						data-deleteRefUrl="/sz0099/ood/personal/profession/parag/delete"
						data-createRefUrl="/sz0099/ood/personal/profession/parag/create"
						data-refreshRefDescUrl="/sz0099/ood/personal/profession/parag/refreshRefForDescription"
						data-deleteImageRefUrl="/sz0099/ood/personal/profession/parag/deleteImageById"
						data-addImageRefUrl="/sz0099/ood/personal/profession/parag/addImageById"
						data-changeImageRefUrl="/sz0099/ood/personal/profession/parag/changeImageById"
						data-uploadImg="/assets/common/images/logo/click_upload.gif"
						data-clickType="0"
						data-type="0"
						data-loadPosition="id_wrapper_uploaderFiles"
						/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</#macro>

<#macro Mg_imageRefEdit id="uploaderInput" imageRef=null showTemplate=false>
<#if imageRef!>
<li class="list-group-item" style="padding:0px 0px" id="id_tpl_li_${imageRef.id}">
	<div class="row" style="padding:0px 0px;margin:0px 0px">
		  <div class="col-xs-2" style="padding:0px 0px">
			    <a href="javascript:void(0)" id="id_img_${imageRef.id}_wrapper">
			    <#assign photo=imageRef.fullurl />
			    <#assign photoUrl='/assets/common/images/logo/click_upload.gif' />
			    <#if photo! && imageRef.fullurl! && ImageRef.TYPE_IMG.valueInt==imageRef.type>
			    <#assign photoUrl=PhotoUtil.getShowUrlForAd(imageRef.expectedUrl,imageRef.fullurl,imageRef.width)/>
			    </#if>
			      <img class="img-responsive" src="${photoUrl}" id="id_img_${imageRef.id}" data-imgWrapperId="id_img_${imageRef.id}_wrapper" data-type="${imageRef.type}" data-refId="${imageRef.id}"/>
			    </a>
			     <div class="form-group form-group-sm">
				    <input type="text" id="id_parag_orderSeq${imageRef.id}" name="orderSeq" 
				    value="${imageRef.orderSeq}" class="form-control" 
				    onkeyup="keyPressPositive(this)" 
					onafterpaste="onAfterPastePositive(this)"
					onchange="mergeRefForOrderSeq('${id}', '${imageRef.id}','id_parag_orderSeq${imageRef.id}')"
				    placeholder="排序">
				  </div>
		  </div>
		  <div class="col-xs-9" style="padding:0px 0px">
		  
		  		<div class="form-group form-group-sm" style="margin-bottom:0px;padding:0px">
		  		 <textarea id="id_parag_description${imageRef.id}_tmp" name="description" rows="5" 
				    class="form-control" style="margin-bottom:0px;padding:0px" 
				    placeholder="点击编辑内容，1000字内访问速度最佳，最多不超过3000字符"
				    readonly="true"
				    onclick="mergeRefForDescription( '${id}', '${imageRef.id}', 'id_parag_description${imageRef.id}', 'id_parag_orderSeq${imageRef.id}')"
				    >${HtmlUtil.html2Text(imageRef.description)}</textarea>
		  		
			    <textarea id="id_parag_description${imageRef.id}" name="description" rows="5" 
			    class="form-control hidden" style="margin-bottom:0px;padding:0px" 
			    placeholder="1000字内访问速度最佳，最多不超过3000字符"
			    readonly="true"
			    data-saved="1"
			    <#-- 
			    onclick="mergeRefForDescription( '${id}', '${imageRef.id}', 'id_parag_description${imageRef.id}', 'id_parag_orderSeq${imageRef.id}')"
			     -->
			    >${imageRef.description}</textarea>
			  	</div>
			  	<p class="text-right">
			  	<span class="glyphicon glyphicon-refresh" onclick="refreshRefForDescription( '${id}', '${imageRef.id}', 'id_parag_description${imageRef.id}', 'id_parag_orderSeq${imageRef.id}')"></span>
			  	<small>字数:<span id="id_parag_description${imageRef.id}_length">${HtmlUtil.countTextLength(imageRef.description)}</span>/3000</small>
			  	</p>
		  </div>
		  <div class="col-xs-1" style="padding:0px 0px">
		  		<p class="text-center">
		  		<button type="button" class="close"><span class="glyphicon glyphicon-remove" onclick="deleteRef('${id}','${imageRef.id}','id_tpl_li_${imageRef.id}')"></span></button>
		  		</p>
		  		<br/>
		  		<p class="text-center">
		  		 <#if showTemplate>
		  			<a tabindex="1" role="button" 
		  			id="id_btn_setTemplate${imageRef.id}"
		  			onclick="popSettingRefTemplate('id_btn_setTemplate${imageRef.id}','${imageRef.id}','id_settingTemplate${imageRef.id}')"
		  			data-url="/sz0099/ood/activity/parag/mergeTemplateById"
		  			data-showTip="id_settingTemplate${imageRef.id}_showTip"
		  			data-entityId="${imageRef.id}">
		  			<span class="glyphicon glyphicon-flag <#if imageRef.template gt 0>text-danger<#else>text-default</#if>" 
		  			></span>
		  			</a>
		  			
		  			<div class="hidden" id="id_settingTemplate${imageRef.id}">
			  			<#-- 
			  			<div class='row'>
			  				<div class='col-xs-4' style='width:60px'><p class='text-center'><a href='javascript:void(0)' onclick='settingTemplate("id_btn_setTemplate${imageRef.id}", 1)'><span class='glyphicon glyphicon-flag text-danger' style='font-size: 28px;'></span><br/><span>设置</span></a></p></div>
							<div class='col-xs-2'></div>
							<div class='col-xs-4' style='width:60px'><p class='text-center' ><a href='javascript:void(0)' onclick='cancelTemplate("id_btn_setTemplate${imageRef.id}", ${CoeActivity.TEMPLATE_NO.valueInt})'><span class='glyphicon glyphicon-flag text-default' style='font-size:28px;'></span><br/><span>取消</span></a></p></div>
						</div>
						 -->
						<div id="id_settingTemplate${imageRef.id}_showTip"></div>
					</div>
		  		 </#if>
		  		</p>
		  		<p class="text-center">
		  		<a tabindex="0" role="button"  id="id_btn_addRefMore${imageRef.id}" class="close" 
		  		onclick="addRefMore('id_btn_addRefMore${imageRef.id}','${id}','id_parag_orderSeq${imageRef.id}','id_tpl_li_${imageRef.id}')">
		  		<span class="glyphicon glyphicon-plus"></span>
		  		</a>
		  		</p>
		  </div>
	</div>
</li>
</#if>
</#macro>
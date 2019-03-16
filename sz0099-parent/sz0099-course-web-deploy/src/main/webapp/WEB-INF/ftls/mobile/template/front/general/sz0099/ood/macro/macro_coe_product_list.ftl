<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">

<#macro M_coeProductDraftList draftList=null url="/sz0099/ood/product/manage/create?id="> 
<div class="list-group text-center">
<#if draftList?? && draftList?size gt 0>
	<#list draftList as draft>
	  <a type="button" href="${url}${draft.id}" class="list-group-item <#if draft?is_even_item>list-group-item-warning</#if>">${draft.productNo}-${draft.name} 
	  <span class="pull-right"><strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
	  </a>
	  
	</#list>
	<#if draftList?size lt 5>
	  <a type="button" href="${url}" class="list-group-item btn btn-info">新建产品</a>
	  </#if>
<#else>
<a type="button" href="${url}" class="list-group-item btn btn-info">新建产品</a>
</#if>
</div>
</#macro>

<#macro M_coeProductPage itemPage moreLoaded="true" url="/sz0099/ood/product/index" condition="">   
<!--列表开始--> 
<!--课程产品-->
<#if itemPage??>
	  <#assign itemList=itemPage.content />
      <#list itemList as itemSingle>
      <#assign strategy=itemSingle.strategy/>
		  <div class="panel panel-default">
			  <div class="panel-heading">${getSubstring(itemSingle.name,16,'无标题')} <span class="pull-right"><@M_getStrategySymbol strategy=strategy label=CoeProduct.strategyMap.get(strategy).label/> <strong>#${itemSingle?index}</strong></span>
			  
			  </div>
			  <div class="panel-body">
				  	<div class="media">
						  
						  <div class="media-body">
						    <h5 class="media-heading">编号: ${itemSingle.productNo}</h5>
						    <h5><strong>${itemSingle.title}</strong></h5>
						    <p>简介：${getSubstring(itemSingle.description,60,'暂无')}</p>
						    
						  </div>
						  <div class="media-right">
						    	<@M_showImageForView imageList=itemSingle.coverList />
						    	<p class="text-center" style="margin:10px 0px 0px 0px">
								  <a type="button" class="btn btn-info btn-xs text-right" href="/sz0099/ood/product/detail/${itemSingle.id}">查看详细</a>
								 <#--  -- ${L.getLabel(strategy,'strategy','dml.sz0099.course.app.persist.entity.product.CoeProduct')} -->
							  	</p>
						  </div>
					</div>
			  	
			  </div>
			  <div class="panel-footer">
			  <del><span class="text-info">原价:${PriceUtil.f2y(itemSingle.priceOri)}</span></del>  
			  <span class="text-danger">现价: ${PriceUtil.f2y(itemSingle.priceCur)}</span> 
			  <a href="javascript:void(0)" onclick="showBuyInstruction('${itemSingle.id}')" class="btn btn-xs btn-danger" id="id_btn_buyinstruction_${itemSingle.id}">购买</a>
			  </div>
		 </div>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${itemPage.number+1}&size=${itemPage.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	  
 </#if>
 </#macro>

<#macro MV_coeProductPage itemPage> 
<!--课程产品列表开始,VUE控制-->
 <div class="panel panel-default" v-for="itemSingle in ${itemPage.name}.content">
	  <div class="panel-heading">{{itemSingle.title}}</div>
	  <div class="panel-body">
	    {{itemSingle.description}}
	  </div>
	  <div class="panel-footer">
	  <del><span class="text-info">原价:{{itemSingle.priceOri}}</span></del>  <span class="text-danger">现价: {{itemSingle.priceCur}}</span> <a href="#" class="btn btn-xs btn-danger">购买</a>
	  </div>
 </div>
  <#-- !"/assets/common/images/logo/img_cover_100X100.gif" -->
<p class="text-right">
  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
</p>
<!--课程产品列表结束,VUE控制-->
</#macro>

<#macro M_articleList itemList>
<!--课程产品列表开始--> 
<#if itemList??>
      <#list itemList as itemSingle>
		  <div class="media">
		      <div class="media-left">
		        <a href="${itemSingle.accessUrl}">
		           <#assign itemCoverImageList=itemSingle.coverImageUrlList />
		           <img class="media-object" data-src="holder.js/80x80" alt="80x80" src='${itemSingle.firstCoverImageUrl!"/assets/common/images/logo/img_cover_100X100.gif"}' data-holder-rendered="true" style="width: 80px; height: 80px;">
		        </a>
		      </div>
		      <div class="media-body">
		        <h4 class="media-heading">${itemSingle.title}</h4>
		        <p>${itemSingle.subTitle}</p>
		        <p>${itemSingle.roadLine}</p>
		      </div>
		  </div>
  	  </#list>
 <#-- 	   -->
 </#if>

<p class="text-right">
  <button type="button" class="btn btn-warning btn-sm text-right">加载更多...</button>
</p>

  <!--课程产品列表结束-->
 </#macro>
 
<#macro M_coeProductDetailPage detailPage=null moreLoaded="true" url="/sz0099/ood/product/manage/detail/" condition=""> 
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center">
	<#if detailList?? && detailList?size gt 0>
		<#list detailList as detail>
		  <a type="button" href="${url}${detail.id}" class="list-group-item <#if detail?is_even_item>list-group-item-warning</#if>">${detail.productNo}-${detail.name} 
		  <span class="pull-right"><strong><#if detail.shelved==CoeProduct.SHELVED_YES.valueInt>
		  <span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;">
		  </span><#else><span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;">
		  </span>
		  </#if>
		  </strong></span>
		  </a>
		</#list>
	<#else>
	<a type="button" href="/sz0099/ood/product/manage/queryDraftList" class="list-group-item btn btn-info">没有发布产品，去完成草稿</a>
	</#if>
</div>
<div class="container">
  	<ul id="pageContainer"></ul>
</div>
</#if>
</#macro>

<#-- 管理页面 课程列表，执行局部修改，如：调整价格，链接，标题，上下架操作 -->
<#macro M_coeProductManagePage page moreLoaded="true" url="/sz0099/ood/product/productManageList" condition="">   
<!--列表开始--> 
<!--课程产品-->
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
      <#assign strategy=entity.strategy/>
		  <div class="panel panel-default">
			  <div class="panel-heading">${entity.productNo} -- ${entity.name} <span class="pull-right"><@M_getStrategySymbol strategy=strategy label=CoeProduct.strategyMap.get(strategy).label/> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
			  	 
				  <div class="form-group form-group-sm">
				    <label for="id_name${entity.id}">名称(简短，少于15字符)</label>
				    <input type="text" id="id_name${entity.id}" name="name" value="${entity.name}" class="form-control" placeholder="产品名称">
				  </div>
					<div class="form-group form-group-sm">
				    	<label for="id_title${entity.id}">标题(少于45字符)</label>
				    	<input type="text" id="id_title${entity.id}" name="title" value="${entity.title}" class="form-control" placeholder="标题">
				  	</div>
			    <p>简介: ${getSubstring(entity.description,60,'暂无')}</p>
				  
					  <div class="form-group form-group-sm">
					    <label for="id_priceOri${entity.id}">原价，单位[分], 15元，即输入1500</label>
					    <input type="text" id="id_priceOri${entity.id}" name="priceOri" 
					    value="${entity.priceOri}"
					    onkeyup="keyPressPositive(this)"  
			    		onafterpaste="onAfterPastePositive(this)"
					    class="form-control" placeholder="原价，例：1500">
					  </div>
					  
					  <div class="form-group form-group-sm">
					    <label for="id_priceCur${entity.id}">现价，单位[分], 9元，即输入900</label>
					    <input type="text" id="id_priceCur${entity.id}" name="priceCur"
					    value="${entity.priceCur}"
					    onkeyup="keyPressPositive(this)"  
			    		onafterpaste="onAfterPastePositive(this)"
					    class="form-control" placeholder="现价，例：900">
					  </div>
				  
			  	  <div class="form-group form-group-sm">
				    <label for="id_link${entity.id}">提取链接</label>
				    <input type="text" id="id_link${entity.id}" name="link" value="${entity.link}"  class="form-control" placeholder="提取链接">
				  </div>
				  
				  <div class="form-group form-group-sm">
				    <label for="id_pullCode${entity.id}">提取密码</label>
				    <input type="text" id="id_pullCode${entity.id}" name="pullCode" value="${entity.pullCode}"  class="form-control" placeholder="提取密码">
				  </div>
			  	  <p>
			  	  <#assign shelvedLabel=CoeProduct.SHELVED.getLabel(entity.shelved)/>
			  	  <input type="hidden" name="shelved" id="id_hidden_shelved${entity.id}" value="${entity.shelved}" />
			  	  <strong>上架状态:</strong> 
			  	  <span id="id_span_shelved${entity.id}">${shelvedLabel}</span>
			  	  </p>
			     <p>
			     	<strong>发布状态:</strong> 
			      	<#assign publishLabel=CoeProduct.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
			      	${publishLabel}
			      </p>
			  	  <p>
			  	  <strong>发布时间:</strong> ${DateUtils.formatToString(entity.publishTime ,'yyyy-MM-dd HH:mm')}
			  	  </p>
			  	  <p>
			  	  <strong>刷新时间：</strong><span id="id_span_refreshTime${entity.id}">${DateUtils.formatToString(entity.refreshTime ,'yyyy-MM-dd HH:mm')}</span>
			  	  </p>
			  	  <p id="id_messageTip_edit_quickly${entity.id}" class="text-center"> </p>
			  	  <p>
			  	  <a href="javascript:void(0)" onclick="editQuickly('${entity.id}')" type="button" class="btn btn-success btn-xs" id="id_btn_edit_quickly_${entity.id}">快速保存</a>
			  	  <a href="/sz0099/ood/product/manage/create?id=${entity.id}" type="button" class="btn btn-danger btn-xs pull-right" id="id_btn_edit_fully_${entity.id}">全面编辑</a>
			  	  </p>
			  </div>
			  <div class="panel-footer">
			    <a id="id_li_shevled_${entity.id}${CoeProduct.SHELVED_YES.valueInt}" href="javascript:void(0)" onclick="shelvedProduct('${entity.id}', '${CoeProduct.SHELVED_YES.valueInt}')" class="btn btn-info" role="button">上架</a>
  				<a id="id_li_shevled_${entity.id}${CoeProduct.SHELVED_NO.valueInt}" href="javascript:void(0)" onclick="shelvedProduct('${entity.id}', '${CoeProduct.SHELVED_NO.valueInt}')" class="btn btn-warning" role="button">下架</a>
  				
  				<a href="javascript:void(0)" onclick="refreshProduct('${entity.id}')" class="btn btn-primary" role="button">刷新</a>
  				<a href="javascript:void(0)" onclick="mergeForClosed('${entity.id}')" class="btn btn-danger pull-right" role="button">关闭</a>
			  </div>
		 </div>
  	  </#list>
  	  <!-- 下一组 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${page.number+1}&size=${page.size}&title=${condition.title}" class="btn btn-warning btn-sm">下一组</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	  <div class="container">
	  	<ul id="pageContainer"></ul>
	  </div>
	  
 </#if>
 </#macro>
 

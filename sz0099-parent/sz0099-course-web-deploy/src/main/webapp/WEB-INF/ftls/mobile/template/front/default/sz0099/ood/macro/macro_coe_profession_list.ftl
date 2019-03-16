<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_profession_category_search.ftl">

<#macro M_professionDraftList draftList=null url="/sz0099/ood/personal/profession/manage/create?id="> 
<div class="list-group text-center">
<#if draftList?? && draftList?size gt 0>
	<#list draftList as draft>
	  
	  <a type="button" href="${url}${draft.id}" class="list-group-item <#if draft?is_even_item>list-group-item-warning</#if>">
	   <span class="pull-left"><strong>
	  <#if Profession.PUBLISH_STATUS_PUBLISH.valueInt==draft.publishStatus>
	    <span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;">&nbsp;</span>
	  <#elseif Profession.PUBLISH_STATUS_CLOSED.valueInt==draft.publishStatus>
	    <span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;"></span>
	  </#if>
	  </strong></span>
	   
	   ${draft.aid}&nbsp;-&nbsp;${draft.name} 
	  <span class="pull-right"><strong>
	  <span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span>
	  </strong></span>
	  </a>
	  
	</#list>
	<#if draftList?size lt 3>
	  <a type="button" href="${url}" class="list-group-item btn btn-info">添加新技能</a>
	  </#if>
	
<#else>
<a type="button" href="${url}" class="list-group-item btn btn-info">添加新技能</a>
</#if>
</div>
<p class="bg-warning">1.360行，行行都有技！有才华要展示呀</p>
<p class="bg-danger"><strong>2.技能是圈里人挖掘你潜力的首要形式..</strong></p>
<p class="bg-warning">3.发布技能，有可能获得别人的打赏呢！</p>
<p class="bg-danger"><strong>4.透过技能让朋友们了解你的专长、资源...</strong></p>
<p class="bg-warning">5.发布了技能，你的传说就留在圈里啦...</p>
<p class="bg-danger"><strong>6.技能配合文章，还能拓展你的业务哦！</strong></p>
<p class="bg-warning">7.技能被点赞的时候，也会留下大侠的传说呢...</p>
</#macro>

     
<#macro M_professionDetailPage detailPage=null moreLoaded="true" url="/sz0099/ood/personal/profession/manage/detail/" condition=""> 
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center">
	<#if detailList?? && detailList?size gt 0>
		<#list detailList as detail>
		  <a type="button" href="${url}${detail.id}" class="list-group-item <#if detail?is_even_item>list-group-item-warning</#if>">${detail.professionNo}-${detail.name} 
		  <span class="pull-right"><strong><#if detail.publishStatus==Profession.PUBLISH_STATUS_PUBLISH.valueInt>
		  <span class="glyphicon glyphicon-ok-circle text-success" aria-hidden="true" style="font-size: 16px;">
		  </span><#else><span class="glyphicon glyphicon-ban-circle text-danger" aria-hidden="true" style="font-size: 16px;">
		  </span>
		  </#if>
		  </strong></span>
		  </a>
		</#list>
	<#else>
	<a type="button" href="/sz0099/ood/personal/profession/manage/queryProfessionList" class="list-group-item btn btn-info">没有发布技能，去完成草稿</a>
	</#if>
</div>
<div class="container">
  	<ul id="pageContainer"></ul>
</div>
</#if>
<p class="bg-warning">技能输出很重要哦！尽快完善吧...</p>
<p class="bg-warning">圈里人有需要的时候就来找你啦！</p>
</#macro>

<#-- 管理页面 技能列表，执行局部修改，如：调整价格，链接，标题，上下架操作 -->
<#macro M_professionManagePage page moreLoaded="true" url="/sz0099/ood/personal/profession/manage/queryProfessionManageList" condition="">   
<!--技能列表开始--> 
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
		  <div class="panel panel-default">
			  <div class="panel-heading">${entity.professionNo} -- ${entity.name} <span class="pull-right"> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
			  	 
				  <div class="form-group form-group-sm">
				    <label for="id_name${entity.id}">名称(简短，少于20字符)</label>
				    <input type="text" id="id_name${entity.id}" name="name" value="${entity.name}" class="form-control" placeholder="文章名称">
				  </div>
			    <p>简介: ${getSubstring(entity.description,60,'暂无')}</p>
				<div class="form-group form-group-sm">
				    <label for="id_penname${entity.id}">技能别名，若填写则优先显示此名</label>
				    <input type="text" id="id_penname${entity.id}" name="penname" 
				    value="${entity.penname}"
				    class="form-control" placeholder="技能别名,优先显示">
				</div>
				 
				 <#assign currentPreIntro=Profession.PREINTRO_TYPE.getContext(entity.preIntroType,0)/>
				 <#-- <@M_dropdownBar id="id_" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false /> -->
				 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=Profession.PREINTRO_TYPE current=currentPreIntro readonly=false />
				<div class="form-group form-group-sm">
				    <label for="id_title${entity.id}">主标题(少于30字)</label>
				    <input type="text" id="id_title${entity.id}" name="title" 
				    value="${entity.title}"
				    class="form-control" placeholder="主标题，少于30字">
				 </div>
				 <div class="form-group form-group-sm">
				    <label for="id_subTitle${entity.id}"><small>子标题，少于18字</small></label>
				    <input type="text" id="id_subTitle${entity.id}" name="subTitle" 
				    value="${entity.subTitle}"
				    class="form-control" placeholder="子标题，少于18字">
				 </div>
				
			     <p>
			     	<strong>发布状态:</strong> 
			      	<#assign publishLabel=CoeArticle.PUBLISH_STATUS.getLabel(entity.publishStatus)/>
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
			  	  
			  	  <input type="hidden" id="id_mainType_current${entity.id}" value="${Profession.MAINTYPE_9_MAIN.valueInt}" 
					  data-url="/sz0099/ood/personal/profession/manage/merge/mainType" 
					  data-mainType=""
					  data-id="${entity.id}"
					  data-btnMainId="id_btn_btnMainId${entity.id}"
					  data-btnMainUnId="id_btn_un_btnMainId${entity.id}"
					  >
				    <button type="button" id="id_btn_un_btnMainId${entity.id}" class="btn btn-primary btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt!=entity.mainType>hidden</#if>"><span class="glyphicon glyphicon-star" aria-hidden="true" style="font-size: 16px;"></span>
				    	已是主技能
				    </button>
				    <button type="button" id="id_btn_btnMainId${entity.id}" class="btn btn-danger btn-xs <#if Profession.MAINTYPE_9_MAIN.valueInt==entity.mainType>hidden</#if>" onclick="mergeForMainType('${entity.id}','${Profession.MAINTYPE_9_MAIN.valueInt}', 'id_mainType_current${entity.id}')">设为主技能</button>
				    
			  	  <a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}" type="button" class="btn btn-danger btn-xs pull-right" id="id_btn_edit_fully_${entity.id}">全面编辑</a>
			  	  </p>
			  </div>
			  <div class="panel-footer">
  				
  				<a href="javascript:void(0)" onclick="refreshProfession('${entity.id}')" class="btn btn-primary" role="button">刷新</a>
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
 
<#-- 查看页面 技能列表  -->
<#macro M_professionWithRefPage entityPage>   
<!--技能列表开始--> 
	  <@M_professionPageWordImgAbreast_list positon=null entityPage=entityPage/>
 </#macro>
 
 <#-- 图文并排 IMG_WORD_2_ABREAST_LIST 左文右图-->
<#macro M_professionPageWordImgAbreast_list positon=null entityPage=null idPre="ly_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
<#assign totalPages=entityPage.totalPages />
	<#if position?? && showHeadTip>
	<#assign panel=position.panel />
		<#if panel??>
		<div>${Position.PANEL.getLabel(position.panel)}</div>
		</#if>
	</#if>
	<div id="id_page_profession">
	    <#list entityList as entity0>
			  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
				  <div class="panel-heading">
		  			<#assign author=entity0.author />
				  	<a href='javascript:void(0)' onclick="showSayword('${entity0.nickname}','${entity0.sayword.description}')">
					  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
					</a> 
				    <span class="pull-right"><strong><small>${entity0.nickname}</small></strong> <span class="label label-danger">修炼于</span> ${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;&nbsp;</span>
				  </div>
				  <div class="panel-body" sytle="margin-bottom:0px">
					  	<div class="row">
							  <div class="col-xs-6 col-md-6">
							  	
							    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
							    <p>${getSubstring(entity0.description,60,'暂无')}</p>
							    
							  </div>
							  <div class="col-xs-6 col-md-6">
							    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
							   </div>
						</div>
						
						<div class="container" id="id_refPage${entity0.id}">
						 
							<@M_professionRefPageImgWordAbreast_list entityPage=entity0.refPage wrapperId='id_refPage'+entity0.id showListLoadMore=true/>
						 <#-- -->
						</div>
				  </div><#-- end body -->
			 </div>
	  	  </#list>
  	  </div>
  	  <#if showListLoadMore && totalPages gt 1>
  	  <!-- more -->
		<div class="container">
		  	  <p class="text-right">
		  	  	  <a href="javarscript:void(0)" onclick="refLoadmore('${url}','${entity0.baseId}','${entityPage.number+1}','${entityPage.size}','${entityPage.totalPages}','id_page_profession')" class="btn btn-warning btn-sm">more</a>
			  </p>
		</div>
	  </#if>
 </#if>
</#macro>

<#-- 子级列表 图文并排，左图右文 -->
<#macro M_professionRefPageImgWordAbreast_list entityPage=null wrapperId="id_refPage" idPre="ly_proArticlelist" headTip="技能文章" showHeadTip=false url="#" showListLoadMore=true withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if entityPage??>
<#assign entityList=entityPage.content />
	<#if headTip! && showHeadTip>
		<div class="text-left">${headTip}</div>
	</#if>
	<hr/>
    <#list entityList as entity1>
		  	<div class="row">
		  		  <div class="col-xs-4 col-md-4">
				    	<@M_layout_imgCoverRollerH_single entity0=entity1 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
				  </div>
				  <div class="col-xs-8 col-md-8">
				    <p class="text-left"><strong><a href="${getLink(entity1, '/')}">${entity1.title}</a></strong></p>
				    <p>${getSubstring(entity1.description,60,'暂无')}</p>
				    
				  </div>
			</div>
  	  </#list>
  	  <#if showListLoadMore && entityPage.hasNext() >
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="javarscript:void(0)" onclick="refLoadmore('${url}','${entity1.baseId}','${entityPage.number+1}','${entityPage.size}','${entityPage.totalPages}','${wrapperId}')" class="btn btn-warning btn-sm">more...</a>
				  </p>
			</div>
	  </#if>
 </#if>
</#macro>

<#macro M_mainTypeProfessionList entity=null url="/sz0099/ood/personal/profession/findProfessionForPraisePage/">
<#if entity!>
<div id="id_list_mainType">
	  <#assign entityList=entity.mainTypeList />
	  <#if entityList! && entityList??>
	  <#list entityList as entity0>
	  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
  		  <#assign author=entity0.author />
		  <div class="panel-body" sytle="margin-bottom:0px">
			  	<div class="row">
					  <div class="col-xs-7 col-md-7">
					    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
					  </div>
					  <div class="col-xs-5 col-md-5">
					  		<a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
						  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
							</a> 
					    <span class="pull-right"> 
					    <span class="label label-warning">修炼于</span><br/>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;
					    </span>
					   </div>
				</div>
		  </div><#-- end body -->
	 </div><#-- end panel -->
  </#list>
  <#-- 用于详情页展示列表，始终显示，跳转至点赞用户列表页 -->
  		<div class="container">
		  	  <p class="text-right">
		  	  	  <a href="${url}${entity.id}" class="btn btn-warning btn-xs">more...</a>
			  </p>
		</div>
  </#if>
</div>
</#if>
</#macro>

<#-- 点赞用户主技能分页列表 -->
<#macro M_mainTypeProfessionForPraisePage entity=null url="/sz0099/ood/personal/profession/findProfessionForPraisePage/" showListLoadMore=false>
<#if entity!>
<#assign praisePage=entity.praisePage />
<#if praisePage!>
<#assign entityList=praisePage.content />
<div id="id_list_mainType">
	  <#if entityList! && entityList??>
	  <#list entityList as entityItem>
	  <#assign entity0=entityItem.profession />
	  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
  		  <#-- praise里面包含用户头像信息 -->
  		  <#assign author=entityItem />
		  <div class="panel-body" sytle="margin-bottom:0px">
			  	<div class="row">
					  <div class="col-xs-8 col-md-8">
					    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
					  	<div class="container">
						  	<a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
							  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
								</a> 
						    <span class="pull-right"> 
						    <span class="bg-warning"><small>&nbsp;修炼于&nbsp;</small></span>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;
						    </span>
					  	</div>
					  </div>
					  <div class="col-xs-4 col-md-4">
					  <@M_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_praise" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
					   </div>
				</div>
		  </div><#-- end body -->
	   </div><#-- end panel -->
       </#list>
       </#if>
  	    <!-- more -->
       
       <#if showListLoadMore && entityPage.hasNext() >
       <#-- 加载更多，一般不显示 -->
		<div class="container">
		  	  <p class="text-right">
		  	  	  <a href="${url}${entity.id}" class="btn btn-warning btn-xs">more...</a>
			  </p>
		</div>
	   </#if>
	   
	   <#-- 加装分页组件 -->
	   <div class="container">
	  	<ul id="pageContainer"></ul>
	   </div>
</div>
</#if>
</#if>
</#macro>



<#macro M_refreshProfessionPage entity=null showPage=false>
<#if entity!>
<#assign entityPage=entity.refreshPage />
<#if entityPage!>
<div id="id_list_refresh">
	  <#assign entityList=entityPage.content />
	  <#if entityList! && entityList??>
		  <#list entityList as entity0>
		  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px" >
	  		  <#assign author=entity0.author />
			  <div class="panel-body" sytle="margin-bottom:0px">
				  	<div class="row">
						  <div class="col-xs-7 col-md-7">
						    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
						  </div>
						  <div class="col-xs-5 col-md-5">
						  		<a href='javascript:void(0)' onclick="showSayword('${entity0.nickname}','${entity0.sayword.description}')">
							  	<img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.sayword.description}'>
								</a> 
						    	<span class="pull-right"><span class="label label-info">修炼于</span> <br/>${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')} &nbsp;</span>
						    </div>
					</div>
			  </div><#-- end body -->
		  </div><#-- end panel -->
	  	  </#list>
	  	  <#-- <#if entityPage.hasNext() > -->
	  	  <!-- more -->
		  <@M_professionCategoryForSearchHidden  entityPage=entityPage entity=entity url="/sz0099/ood/personal/profession/searchForCategoryFromDetail"/>
		 <#--  </#if> -->
		 <#-- 加装分页组件 -->
		   <#if showPage >
		   <div class="container">
		  	<ul id="pageContainer"></ul>
		   </div>
		   </#if> 
  	  </#if>
</div>
</#if>
</#if>
</#macro>
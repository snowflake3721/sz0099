<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_search.ftl">

<#macro M_professionDraftList draftList=null url="/sz0099/ood/personal/profession/manage/create?id="> 
<br/>
<ul class="list-group text-center">
<#if draftList?? && draftList?size gt 0>
    <#if draftList?? && draftList?size gt 0>
		<@Mg_profession_editList contentList=draftList url=url/>
	</#if>
	<#if draftList?size lt 3>
		<li class="list-group-item btn btn-info">
	  	<a href="${url}" type="button" class="btn btn-primary btn-xs">添加新技能</a>
	  	</li>
	</#if>
<#else>
<li class="list-group-item btn btn-info">
<a href="${url}" type="button" class="btn btn-primary btn-xs">添加新技能</a>
</li>
</#if>
</ul>

<p class="bg-warning">1.360行，行行都有技！有才华要展示呀</p>
<p class="bg-danger"><strong>2.技能是圈里人挖掘你潜力的首要形式..</strong></p>
<p class="bg-warning">3.发布技能，有可能获得别人的打赏呢！</p>
<p class="bg-danger"><strong>4.透过技能让朋友们了解你的专长、资源...</strong></p>
<p class="bg-warning">5.发布了技能，你的传说就留在圈里啦...</p>
<p class="bg-danger"><strong>6.技能配合文章，还能拓展你的业务哦！</strong></p>
<p class="bg-warning">7.技能被点赞的时候，也会留下大侠的传说呢...</p>
</#macro>

     
<#macro M_professionDetailPage position="" detailPage=null moreLoaded="true" url="/sz0099/ood/personal/profession/manage/detail/" condition=""> 
<br/>
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center">
	<#if detailList?? && detailList?size gt 0>
		<@Mg_profession_editList contentList=detailList url=url/>
	<#else>
		<#if detailPage.totalPages lt 1>
			<a type="button" href="/sz0099/ood/personal/profession/manage/queryProfessionList" class="list-group-item btn btn-info">没有发布技能，去完成草稿</a>
		</#if>
	</#if>
</div>
<div class="container">
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/personal/profession/manage/queryDetailList/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${detailPage.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${detailPage.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${detailPage.totalPages}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		initPageAnsyForProfessionManage('${position}');
  	</script>
</div>
</#if>
<p class="bg-warning">技能输出很重要哦！尽快完善吧...</p>
<p class="bg-warning">圈里人有需要的时候就来找你啦！</p>
</#macro>

<#macro M_searchProfessionPage position="" detailPage=null moreLoaded="true" url="/sz0099/ood/personal/profession/manage/detail/" condition=""> 
<br/>
<#if detailPage!>
<#assign detailList=detailPage.content />
<div class="list-group text-center" id="id_data_list_article${position}">
	<#if detailList?? && detailList?size gt 0>
		<@Mg_profession_editList contentList=detailList url=url/>
	<#else>
		没有搜索到技能
	</#if>
</div>
<div class="container">
	<input id="id_page_loaded${position}" name="loaded" type="hidden" value="0" data-index="0"/>
  	<input id="id_page_url${position}" type="hidden" name="url" value="/sz0099/ood/personal/profession/manage/searchProfessionList/ansy"/>
  	<input id="id_page_currentPage${position}" type="hidden" name="page" value="${detailPage.number}"/>
  	<input id="id_page_size${position}" type="hidden" name="size" value="${detailPage.size}"/>
  	<input id="id_page_totalPages${position}" type="hidden" name="totalPages" value="${detailPage.totalPages}"/>

  	<ul id="id_page_wrapper${position}"></ul>
  	<script>
  		initPageAnsySearchForProfessionManage('${position}');
  	</script>
</div>
</#if>
</#macro>

<#macro Mg_profession_editList contentList=null url=null>

<#list contentList as entity0>
<li class="list-group-item <#if entity0?is_even_item>list-group-item-warning</#if>">
  <div class="row">
		  <div class="col-xs-9 col-md-9">
		  		<p>
			  	  <a type="button" href="${url}${entity0.id}">
				  	<span id="id_name${entity0.id}">${entity0.professionNo}-${entity0.title}</span>
			    	  ${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd HH:mm')} &nbsp;
					  <strong><span class="glyphicon glyphicon-edit text-primary" aria-hidden="true" style="font-size: 16px;"></span></strong>
				  </a>
				  <#if entity0.publishStatus==Profession.PUBLISH_STATUS_PUBLISH.valueInt>
					  <span class="pull-right">
					  <strong>
					  <span class="glyphicon glyphicon-eye-open text-success" aria-hidden="true" style="font-size: 16px;">
					  </span>
					  </strong>
					  </span>
					  <#else>
					  <span class="pull-right">
					  <strong>
					  <span class="glyphicon glyphicon-eye-close text-danger" aria-hidden="true" style="font-size: 16px;">
					  </span>
					  </strong>
					  </span>
					  <span class="pull-left"><strong><span onclick="mergeForDeletedDraft('${entity0.id}')" class="glyphicon glyphicon-trash text-danger" aria-hidden="true" style="font-size: 16px;"></span></strong></span>
					</#if>
				</p>
		  </div>
		  <div class="col-xs-3 col-md-3">
		  <@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_manage_page" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false style=""/>
		   </div>
	</div>
</li>
</#list>
</#macro>

<#-- 管理页面 技能列表，执行局部修改，如：调整价格，链接，标题，上下架操作 -->
<#macro M_professionManagePage position="" page=null moreLoaded="true" url="/sz0099/ood/personal/profession/manage/queryProfessionManageList" condition="">   
<br/>
<!--技能列表开始--> 
<#if page??>
	  <#assign itemList=page.content />
      <#list itemList as entity>
		  <div class="panel <#if entity?is_odd_item>panel-default<#else>panel-warning</#if>">
			  <div class="panel-heading">${entity.professionNo} -- ${entity.title} <span class="pull-right"> <strong>#${entity?index}</strong></span>
			  </div>
			  <div class="panel-body">
			  	 <#-- 
				  <div class="form-group form-group-sm">
				    <label for="id_name${entity.id}">名称(简短，少于20字符)</label>
				    <input type="text" id="id_name${entity.id}" name="name" value="${entity.name}" class="form-control" placeholder="文章名称">
				  </div>
				   -->
			    <p>简介: ${getSubstring(entity.description,60,'暂无')}</p>
				<div class="form-group form-group-sm">
				    <label for="id_penname${entity.id}">技能别名，若填写则优先显示此名
				    <small><span class="text-success" id="id_penname${entity.id}_length">${HtmlUtil.countTextLength(entity.penname)}</span>/12</small>
				    </label>
				    <input type="text" id="id_penname${entity.id}" name="penname" 
				    value="${entity.penname}"
				    onblur="showLength('id_penname${entity.id}', 12, 'id_penname${entity.id}_length')"
		    		onkeyup="showLength('id_penname${entity.id}', 12, 'id_penname${entity.id}_length')"
				    class="form-control" placeholder="技能别名,优先显示">
				</div>
				 <#-- <@M_dropdownBar id="id_" propertyContext=CoeArticle.PREINTRO_TYPE current=currentPreIntro readonly=false /> -->
				 <#assign currentPreIntro=Profession.PREINTRO_TYPE.getContext(entity.preIntroType,Profession.PREINTRO_TYPE_NO.valueInt)/>
				 <@M_dropdownBarDiff idPre="id_" entityId=entity.id propertyInput="preIntro" propertyContext=Profession.PREINTRO_TYPE current=currentPreIntro readonly=false />
				<div class="form-group form-group-sm">
				    <label for="id_title${entity.id}" class="text-danger">主标题(少于32字)★
				    <small><span class="text-success" id="id_title${entity.id}_length">${HtmlUtil.countTextLength(entity.title)}</span>/32</small>
				    </label>
				    <input type="text" id="id_title${entity.id}" name="title" 
				    value="${entity.title}"
				    onblur="showLength('id_title${entity.id}', 32, 'id_title${entity.id}_length')"
		    		onkeyup="showLength('id_title${entity.id}', 32, 'id_title${entity.id}_length')"
				    class="form-control" placeholder="主标题，少于32字">
				 </div>
				 <div class="form-group form-group-sm">
				    <label for="id_subTitle${entity.id}"><small>子标题，少于18字</small>
				    <small><span class="text-success" id="id_subTitle${entity.id}_length">${HtmlUtil.countTextLength(entity.subTitle)}</span>/18</small>
				    </label>
				    <input type="text" id="id_subTitle${entity.id}" name="subTitle" 
				    value="${entity.subTitle}"
				    onblur="showLength('id_title${entity.id}', 18, 'id_title${entity.id}_length')"
		    		onkeyup="showLength('id_title${entity.id}', 18, 'id_title${entity.id}_length')"
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
			  	  <a href="javascript:void(0)" onclick="refreshProfession('${entity.id}')" class="btn btn-info btn-xs" role="button">刷新</a>
			  	  </p>
			  	  <p id="id_messageTip_edit_quickly${entity.id}" class="text-center"> </p>
			  	  <p>
			  	  
			  	  </p>
			  </div>
			  <div class="panel-footer">
			  	<a href="javascript:void(0)" onclick="editQuickly('${entity.id}')" type="button" class="btn btn-success btn-xs" id="id_btn_edit_quickly_${entity.id}">快速保存</a>
			  	<a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_btn_edit_fully_${entity.id}">全面编辑</a>

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
			    
		  	    <@shiro.hasRole name="plat_creator">
  				<a href="javascript:void(0)" onclick="mergeForClosed('${entity.id}')" class="btn btn-danger btn-xs pull-right" role="button">关闭</a>
  				</@shiro.hasRole>
			  </div>
		 </div>
		 <br/>
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
							    	<@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
							   </div>
						</div>
						
						<div class="container" id="id_refPage${entity0.id}">
						 
							<@M_professionRefPageImgWordAbreast_list entityPage=entity0.refPage wrapperId='id_refPage'+entity0.id showListLoadMore=true/>
						 <#-- -->
						</div>
				  </div><#-- end body -->
			 </div>
			<#else>
			<p class="text-center text-warning"><strong>这位大侠还没修炼好神技呢，您稍候再来切磋吧！</strong></p>
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
<#else>
<h4 class="text-center text-warning"> 这位大侠还没修炼好神技呢，您稍候再来切磋吧！</h4>
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
				    	<@Mg_layout_imgCoverRollerH_single entity0=entity1 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
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
					  <@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre="ly_praise" withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false/>
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
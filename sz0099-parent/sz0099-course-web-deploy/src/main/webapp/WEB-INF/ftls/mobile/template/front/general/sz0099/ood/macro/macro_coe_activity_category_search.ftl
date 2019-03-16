<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_image.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">

<#macro Mg_activityCategoryForSearch entityPage categoryRef=null categoryTree=null currentUser=null tagPage=null url="/sz0099/ood/activity/searchForCategory" jsFunction=null>   
<div class="row">
		<div class="col-xs-2">
		<img onclick="adToLeftTopCall()" src="/assets/common/tools/blooming-menu/image/logo_dml_2_1.png" class="img-circle img-responsive" title="卓玛拉山"/>
		</div>
		 <div class="col-xs-10">
		    <#--  
		    <@M_selectCategoryForSearch categoryRef=entity category=categoryTree id="id_search_categoryId" isAnsy=false jsFunction=jsFunction url="/sz0099/ood/activity/searchForCategory" />
		    -->
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="${url}">
		      <input type="hidden" id="id_currentPage" name="page" class="form-control" value="${entityPage.number}">
		      <input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${entityPage.totalPages}">
		      <input type="hidden" id="id_size" name="size" class="form-control" value="${entityPage.size}">
		      <input type="hidden" id="id_cayMainId" name="cayMainId" class="form-control" value="${Robot.ROBOT_PLAT.id}">
		      <input type="hidden" id="id_caySubId" name="caySubId" class="form-control" value="${Robot.ROBOT_PLAT.id}">
		      <input type="text" id="id_search_title" name="activity.title" class="form-control" placeholder="七娘山,排牙山,东西冲..." value="${categoryRef.activity.title}">
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchForCategory(0)">搜索</button>
		      </span>
		    </div>
		    <@shiro.user>
		    <p>
			    <ul class="list-inline" style="margin-bottom:0px">
				    <li>
			 			&nbsp;&nbsp; <span class="text-success" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">≡ <@shiro.principal property="nickname"/> ≡</span> 
			 			<a href="/user/logout">
			 			<span class="badge" style="background-color:#449955"><span class="glyphicon glyphicon-off"></span>退</span>
			 			</a>
		 			</li>
	 				<@M_layout_tagPageSimple positon=null entityPage=tagPage idPre="ly_tag"/>
				</ul>
 			</p>
			</@shiro.user>
			<@shiro.guest>
			   <p>&nbsp;&nbsp; 游客&nbsp;&nbsp;<a href="/user/loginUI?st=general">登录</a></p>
			</@shiro.guest>
	  	 </div>
  	</div>
 </#macro>

<#macro M_activityCategoryForSearchHidden entityPage entity=null url="/sz0099/ood/activity/searchForCategoryFromDetail">   
<#-- 获取category -->
  <#assign categoryList=entity.categoryList/>
  <#assign categoryRef=null/>
  <#if categoryList! && categoryList??>
  <#assign categoryRef=categoryList?first/>
  </#if>
<div class="container">
	<p class="text-right">
      <#assign categoryId=categoryRef.baseId />
      <input type="hidden" id="id_search_userId" name="activity.userId" value="${entity.userId}">
      <input type="hidden" id="hidden_id_search_categoryId" name="baseId" value="${categoryId}">
      <input type="hidden" id="id_search_url" name="searchUrl" value="${url}">
      <input type="hidden" id="id_currentPage" name="page" value="${entityPage.number}">
      <input type="hidden" id="id_totalPages" name="totalPages" value="${entityPage.totalPages}">
      <input type="hidden" id="id_size" name="size" value="${entityPage.size}">
      <#-- ${categoryRef.profession.title} -->
      <input type="hidden" id="id_data_mainId" name="mainId" value="${entity.id}">
	  <button class="btn btn-warning btn-xs" id="id_search_btn" type="button" onclick="searchForCategoryHidden(0)">more...</button>
	</p>
</div>
</#macro>

<#macro M_activityCategoryForSearchFromDetail entityPage categoryRef=null categoryTree=null url="/sz0099/ood/activity/searchForCategoryFromDetail" jsFunction=null>   
<div class="row">
		<div class="col-xs-2">
		<img src="/assets/common/tools/blooming-menu/image/dramala_2_1.gif" class="img-circle img-responsive" title="卓玛拉山"/>
		</div>
		 <div class="col-xs-10">
		    	<@M_selectCategoryForSearch categoryRef=entity category=categoryTree id="id_search_categoryId" isAnsy=false jsFunction=jsFunction url="/sz0099/ood/activity/searchForCategoryFromDetail" />
			    <div class="input-group">
			    	<input type="hidden" id="id_search_userId" name="activity.userId" value="${categoryRef.activity.userId}">
			      	<input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="${url}">
			      	<input type="hidden" id="id_currentPage" name="page" class="form-control" value="${entityPage.number}">
			      	<input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${entityPage.totalPages}">
			      	<input type="hidden" id="id_size" name="size" class="form-control" value="${entityPage.size}">
			      	<input type="text" id="id_search_title" name="activity.title" class="form-control" placeholder="编程,车,穿越..." value="${categoryRef.activity.title}">
			      	<input type="hidden" id="id_data_mainId" name="mainId" class="form-control" value="${categoryRef.mainId}">
			      <span class="input-group-btn">
			        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchForCategoryFromDetail(0,'${categoryRef.mainId}')">搜索</button>
			      </span>
			    </div>
	  	 </div>
</div>
<p class="text-right"><a href="${getLink(categoryRef.activity, "/")}">返回：${getSubstring(categoryRef.activity.titleLower,15,'活动详情')}</a></p>
 </#macro>
 
<#macro M_activitySearchPraisePage entity=null url="/sz0099/ood/activity/findActivityForPraisePage/">   
<#assign entityPage=entity.praisePage />
<div class="row">
		 <div class="col-xs-12">
			      	<input type="hidden" id="id_search_url" name="searchUrl" value="${url}${entity.id}">
			      	<input type="hidden" id="id_search_id" name="id" value="${entity.id}">
			      	<input type="hidden" id="id_currentPage" name="page" value="${entityPage.number}">
			      	<input type="hidden" id="id_totalPages" name="totalPages" value="${entityPage.totalPages}">
			      	<input type="hidden" id="id_size" name="size" value="${entityPage.size}">
					<p class="text-right">
					<a href="${getLink(entity, "/")}">返回：${getSubstring(entity.title,15,'活动详情')}</a
					</p>
	  	 </div>
</div>
 </#macro>
 

<#macro M_activitySearchHiddenInHomeDto dto=null url="/sz0099/ood/home/activity/searchForCategoryFromHome" btnLabel="more..." idPre="ly_">  
<#assign positonRef=dto.position />
<#assign categoryRef=dto.category />
<#assign positonPage=dto.positonPage />
<#assign categoryPage=dto.categoryPage />
<@M_activitySearchHiddenInHome positonRef=positonRef positonPage=positonPage categoryRef=categoryRef categoryPage=categoryPage url=url btnLabel=btnLabel idPre=idPre/>
</#macro>
   
<#macro M_activitySearchHiddenInHome positonRef=null positonPage=null categoryRef=null categoryPage=null url="/sz0099/ood/home/activity/searchForCategoryFromHome" btnLabel="more..." idPre="ly_">   
<div class="container">
	<p class="text-right">
      <#assign categoryId=categoryRef.baseId />
      <#assign positonId=positonRef.baseId />
      <#assign category=categoryRef.category />
      <#assign categoryCode=categoryRef.category.code />
      <input type="hidden" id="${idPre}id_search_url${positonId}" name="searchUrl" value="${url}">
      <input type="hidden" id="${idPre}id_data_categoryId${positonId}" name="category.baseId" value="${categoryId}">
      <input type="hidden" id="${idPre}id_data_categoryCode${positonId}" name="category.category.code" value="${categoryCode}">
      <input type="hidden" id="${idPre}id_data_positionBaseId${positonId}" name="position.baseId" value="${positonId}">
      <input type="hidden" id="${idPre}id_data_positionPonPanel${positonId}" name="position.ponPanel" value="${positonRef.ponPanel}">
      <input type="hidden" id="${idPre}id_data_positionPositionId${positonId}" name="position.positionId" value="${positonRef.positionId}">
      <input type="hidden" id="${idPre}id_data_positionExtendId${positonId}" name="position.extendId" value="${positonRef.extendId}">
      <input type="hidden" id="${idPre}id_data_positionPonMainId${positonId}" name="position.ponMainId" value="${positonRef.ponMainId}">
      <input type="hidden" id="${idPre}id_data_positionPonSubId${positonId}" name="position.ponSubId" value="${positonRef.ponSubId}">
      <input type="hidden" id="${idPre}id_data_title${positonId}" name="category.activity.title" value="">
      <input type="hidden" id="${idPre}id_page_currentPage${positonId}" name="page" value="${categoryPage.number}">
      <input type="hidden" id="${idPre}id_page_totalPages${positonId}" name="totalPages" value="${categoryPage.totalPages}">
      <input type="hidden" id="${idPre}id_page_size${positonId}" name="size" value="10">
	  <button class="btn btn-info btn-xs" id="${idPre}id_search_btn${positonId}" type="button" onclick="searchHiddenFromHome(0,'${positonId}','${idPre}')">${btnLabel}</button>
	</p>
</div>
</#macro>

<#macro M_activitySearchFromHomeDto dto=null url="/sz0099/ood/home/activity/searchForCategoryFromHome" category=null idPre="ly_">   
<#assign positonRef=dto.position />
<#assign categoryRef=dto.category />
<#assign positonPage=dto.positonPage />
<#assign categoryPage=dto.categoryPage />
<@M_activitySearchFromHome positonRef=positonRef positonPage=positonPage categoryRef=categoryRef categoryPage=categoryPage url=url category=category idPre=idPre/>
</#macro>

<#macro M_activitySearchFromHome positonRef=null positonPage=null categoryRef=null categoryPage=null url="/sz0099/ood/home/activity/searchForCategoryFromHome" category=null idPre="ly_">   
<div class="container">
      <#assign categoryId=categoryRef.baseId />
      <#assign positonId=positonRef.baseId />
      <#assign category=categoryRef.category />
      <#assign categoryCode=categoryRef.category.code />
      <@M_selectCategoryForSearch categoryRef=categoryRef category=category id="id_search_categoryId" isAnsy=false jsFunction=jsFunction url="/sz0099/ood/home/activity/searchForCategoryFromHome" />
      <div class="input-group">
      <input type="hidden" id="${idPre}id_search_url${positonId}" name="searchUrl" value="${url}">
      <input type="hidden" id="${idPre}id_data_categoryId${positonId}" name="category.baseId" value="${categoryId}">
      <input type="hidden" id="${idPre}id_data_categoryCode${positonId}" name="category.category.code" value="${categoryCode}">
      <input type="hidden" id="${idPre}id_data_positionBaseId${positonId}" name="position.baseId" value="${positonId}">
      <input type="hidden" id="${idPre}id_data_positionPonPanel${positonId}" name="position.ponPanel" value="${positonRef.ponPanel}">
      <input type="hidden" id="${idPre}id_data_positionPositionId${positonId}" name="position.positionId" value="${positonRef.positionId}">
      <input type="hidden" id="${idPre}id_data_positionExtendId${positonId}" name="position.extendId" value="${positonRef.extendId}">
      <input type="hidden" id="${idPre}id_data_positionPonMainId${positonId}" name="position.ponMainId" value="${positonRef.ponMainId}">
      <input type="hidden" id="${idPre}id_data_positionPonSubId${positonId}" name="position.ponSubId" value="${positonRef.ponSubId}">
      <input type="hidden" id="${idPre}id_page_currentPage${positonId}" name="page" value="${categoryPage.number}">
      <input type="hidden" id="${idPre}id_page_totalPages${positonId}" name="totalPages" value="${categoryPage.totalPages}">
      <input type="hidden" id="${idPre}id_page_size${positonId}" name="size" value="${categoryPage.size}">
      
      <input type="text" id="${idPre}id_data_title${positonId}" name="category.activity.title" class="form-control" placeholder="编程,车,穿越..." value="${categoryRef.activity.title}">
	  <span class="input-group-btn">
	  	  <button class="btn btn-default" id="${idPre}id_search_btn${positonId}" type="button" onclick="searchHiddenFromHome(0,'${positonId}','${idPre}')">搜索</button>
	  </span>
	  </div>
</div>
</#macro>
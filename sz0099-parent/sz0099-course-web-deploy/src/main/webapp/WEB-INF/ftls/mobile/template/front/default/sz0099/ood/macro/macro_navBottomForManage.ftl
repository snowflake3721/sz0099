<#include "mobile/template/front/default/function/func_basic.ftl">
<#macro M_navBottomForManage category="product" subCategory="" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
	    <ul class="nav nav-pills">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-danger" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            
			            <li class="<#if category=="article">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList">文章  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="profession">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			           <li role="separator" class="divider"></li>
			             
			             <@shiro.hasRole name="plat_creator">
			             <li class="<#if category=="orderManage">active</#if>"><a href="/sz0099/ood/product/order/manage/manageOrderList">发货</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="transfer">active</#if>"><a href="#">转单</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="product">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">产品</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="category">active</#if>"><a href="/sz0099/ood/category/extend/manage/list">类别</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="position">active</#if>"><a href="/sz0099/ood/position/extend/manage/list">位置</a></li>
			            </@shiro.hasRole>
			          </ul>
			   </li>
			   
			   <#if category=="product">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">产品<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="manageList">active</#if>"><a href="/sz0099/ood/product/manage/queryProductManageList">快速编辑</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="detail">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">产品详情</a></li>
			          	<li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="draftList">active</#if>"><a href="/sz0099/ood/product/manage/queryDraftList">草稿列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/product/manage/create?id=">新建or编辑</a></li>
			          </ul>
			   </li>
			   <#elseif category=="category">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">类别<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="list">active</#if>"><a href="/sz0099/ood/category/extend/manage/list">列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/category/extend/manage/create?id=">新建or编辑</a></li>
			          </ul>
			   </li>
			   <#elseif category=="profession">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">技能<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="manageList">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionManageList">快速编辑</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="detailList">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryDetailList">技能详情</a></li>
			             <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="draftList">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">草稿列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/personal/profession/manage/create?id=">新建or编辑</a></li>
			          </ul>
			   </li>
			   <#elseif category=="orderManage">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">发货<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="all">active</#if>"><a href="/sz0099/ood/category/extend/manage/list">全部</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="waitSent">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">待发货</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="inprocess">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">处理中</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="sent">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">已发货</a></li>
			          </ul>
			   </li>
			   <#elseif category=="position">
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">位置<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="list">active</#if>"><a href="/sz0099/ood/position/extend/manage/list">列表</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/position/extend/manage/create">新建or编辑</a></li>
			          </ul>
			   </li>
			   <#elseif category=="article">
			      <li class="dropdown">
				          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">文章<span class="caret"></span></a>
				          <ul class="dropdown-menu">
				          	<li class="<#if subCategory=="detailList">active</#if>"><a href="/sz0099/ood/article/manage/queryArticleList">文章列表</a></li>
				            <li role="separator" class="divider"></li>
				          	<li class="<#if subCategory=="manageList">active</#if>"><a href="/sz0099/ood/article/manage/queryArticleManageList">快速编辑</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory=="draftList">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList">草稿列表</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory=="new">active</#if>"><a href="/sz0099/ood/article/manage/create?id=">新建or编辑</a></li>
				          </ul>
				   </li>
			   </#if>
			   <li class="dropdown pull-right">
			          <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">群侠 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="/sz0099/ood/index">首页  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			             <li><a href="/sz0099/ood/home/profession/index">技能圈  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			             <li class="<#if category=="qunxia">active</#if>"><a href="/sz0099/ood/home/article/index">户外圈  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			          </ul>
			   </li>
	    </ul>	
  </div>
</nav>
</#macro>

<#macro M_navBreadForManage category="product" subCategory="draftList" entity=null>
<ol class="breadcrumb">
  <li><a href="#">管理</a></li>
  <#if category=="product">
  <li><a href="#">产品</a></li>
    <#if subCategory=="drafeList">
  	<li class="active"><a href="#">草稿列表</a></li>
  	<#elseif subCategory=="new">
  	<li class="active"><a href="#">新建or编辑</a></li>
    <#elseif subCategory=="manageList">
  	<li class="active"><a href="/sz0099/ood/product/manage/queryProductManageList">快速编辑</a></li>
  	<#elseif subCategory=="detail">
  	<li class="active"><a href="/sz0099/ood/product/manage/queryProductList">产品详情</a></li>
  		<#if entity!>
	  	<li class="active"><a href="#">${getSubstring(entity.name,7,'选择')}</a></li>
  		</#if>
  	</#if>
   <#elseif category=="category">
   <li><a href="#">类别</a></li>
   <#elseif category=="position">
   <li><a href="#">位置</a></li>
	   	<#if subCategory=="list">
	  	<li class="active"><a href="#">草稿列表</a></li>
	  	<#elseif subCategory=="new">
	  	<li class="active"><a href="#">新建or编辑</a></li>
	  	</#if>
   <#elseif category=="profession">
   <li><a href="/sz0099/ood/personal/profession/manage/queryDetailList">技能</a></li>
   		<#if subCategory=="draftList">
	  	<li class="active"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">列表</a></li>
	  	<#elseif subCategory=="detailList">
	  	<li class="active"><a href="/sz0099/ood/personal/profession/manage/queryDetailList">技能详情</a></li>
	  	<#elseif subCategory=="new">
	  	<li class="active"><a href="/sz0099/ood/personal/profession/manage/create?id=">新建or编辑</a></li>
	  	<#elseif subCategory=="manageList">
	  	<li class="active"><a href="/sz0099/ood/personal/profession/manage/queryProfessionManageList">快速编辑</a></li>
	  	</#if>
   <#elseif category=="article">
  	<li><a href="#">文章</a></li>
  	<#if subCategory == 'manageList'><li><a href="/sz0099/ood/article/manage/queryArticleManageList">快速编辑</a></li>
	<#elseif subCategory=="detailList"><li><a href="/sz0099/ood/article/manage/queryArticleList">文章列表</a></li>
	<#elseif subCategory=="draftList"><li><a href="/sz0099/ood/article/manage/queryDraftList">草稿列表</a></li>
	<#elseif subCategory=="new"><li><a href="/sz0099/ood/article/manage/create?id=">新建or编辑</a></li>
	</#if>
   
   <#elseif category=="orderManage">
   <li><a href="#">订单</a></li>
   
  <#elseif category=="info">
  <li><a href="#">信息</a></li>
  <#elseif category=="ood">
  <li><a href="#">课程</a></li>
  <#elseif category=="favirate">
  <li><a href="#">收藏</a></li>
  </#if>
  
  
</ol>
</#macro>


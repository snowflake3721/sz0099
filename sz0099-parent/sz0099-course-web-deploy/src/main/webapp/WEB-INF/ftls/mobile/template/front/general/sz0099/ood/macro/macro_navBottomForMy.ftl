<#macro M_navBottomForMy category="order" subCategory="" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
	    <ul class="nav nav-pills">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-danger" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if category=="info">active</#if>"><a href="/sz0099/ood/product/personal/myinfoUI">信息</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="favirate">active</#if>"><a href="#">收藏</a></li>
			            
			            <@shiro.hasRole name="plat_creator">
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="order">active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList?status=9">订单</a></li>
			          	</@shiro.hasRole>
			          	
			          	<li role="separator" class="divider"></li>
			            <li class="<#if category=="favirate">active</#if>"><a href="/sz0099/ood/home/article/index/recommend?st=general"><span class="glyphicon glyphicon-share-alt"></span>返回户外圈</a></li>
			          	
			          </ul>
			   </li>
			  <#if category=="order">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">订单<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			          
			            <li class="<#if subCategory==9 || subCategory==null>active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList?status=9">全部</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory == 0>active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList?status=0">待支付</a></li>
			            <li class="<#if subCategory lt 4 && subCategory gt 0>active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList?status=3">待收货</a></li>
			            <li class="<#if subCategory gt 3 && subCategory lt 9>active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList?status=4" class="">已完成</a></li>
			          </ul>
			   </li>
			   </#if>
			   <#if category=="info">
			      <li class="dropdown">
				          <a href="#" class="dropdown-toggle btn btn-info" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">信息<span class="caret"></span></a>
				          <ul class="dropdown-menu">
				          
				            <li class="<#if subCategory=='baseinfo'>active</#if>"><a href="/sz0099/ood/product/personal/myinfoUI">基本资料</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory=='verify'>active</#if>"><a href="/sz0099/ood/personal/myinfo/verifyUI">身份认证</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory=='recieveImg'>active</#if>"><a href="/sz0099/ood/personal/myinfo/recievImgUI">收款码</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory == 'profession'>active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能服务</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory == 'sayword'>active</#if>"><a href="javascript:void(0)" onclick="modifySayword()">一句传说</a></li>
				            <li role="separator" class="divider"></li>
				            <li class="<#if subCategory == 'inviteQr'>active</#if>"><a href="/auth/userqr/viewInvite">邀请二维码</a></li>

				          </ul>
				   </li>
				 </#if>
				 
			   <li class="dropdown pull-right">
			          <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">群侠<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="/sz0099/ood/home/article/index/recommend?st=general">首页</a></li>
			            <li class="<#if subCategory=="profession">active</#if>"><a href="/sz0099/ood/home/profession/index/recommend?st=general">技能圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li class="<#if subCategory=="article">active</#if>"><a href="/sz0099/ood/home/article/index/recommend?st=general">户外圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			          </ul>
			   </li>
	    </ul>	
  </div>
</nav>
</#macro>

<#macro M_navBreadForMy category="order" subCategory="" entity=null>
<ol class="breadcrumb">
  <li><a href="#">我的</a></li>
  <#if category=="order">
  <li><a href="#">订单</a></li>
  	<#if subCategory==9 || subCategory==null>
  	<li class="active"><a href="#">全部</a></li>
  	<#elseif subCategory==0>
  	<li class="active"><a href="#">待支付</a></li>
  	<#elseif subCategory lt 4 && subCategory gt 0>
  	<li class="active"><a href="#">待收货</a></li>
  	<#elseif subCategory gt 3 && subCategory lt 9>
  	<li class="active"><a href="#">已完成</a></li>
  	</#if>
  
  <#elseif category=="info">
  <li><a href="#">信息</a></li>
	  <#if subCategory == 'profession'>
	  <li><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能服务</a></li>
  		<#elseif subCategory=="inviteQr">
  		<li><a href="/auth/userqr/viewInvite">邀请码</a></li>
  		<#elseif subCategory=="recieveImg">
  		<li><a href="/sz0099/ood/personal/myinfo/recievImgUI">收款码</a></li>
  		<#elseif subCategory=="verify">
  		<li><a href="/sz0099/ood/personal/myinfo/verifyUI">身份认证</a></li>
	  </#if>
  <#elseif category=="ood">
  <li><a href="#">课程</a></li>
  <#elseif category=="favirate">
  <li><a href="#">收藏</a></li>
  
  <#elseif category=="outdoor">
  <li><a href="#">户外</a></li>
  	<#if subCategory == 'manageList'><li><a href="/sz0099/ood/article/manage/queryArticleManageList">快速编辑</a></li>
	<#elseif subCategory=="detailList"><li><a href="/sz0099/ood/article/manage/queryArticleList">文章列表</a></li>
	<#elseif subCategory=="draftList"><li><a href="/sz0099/ood/article/manage/queryDraftList">草稿列表</a></li>
	<#elseif subCategory=="new"><li><a href="/sz0099/ood/article/manage/create?id=">新建or编辑</a></li>
	</#if>
  </#if>
  
  
  
</ol>
</#macro>


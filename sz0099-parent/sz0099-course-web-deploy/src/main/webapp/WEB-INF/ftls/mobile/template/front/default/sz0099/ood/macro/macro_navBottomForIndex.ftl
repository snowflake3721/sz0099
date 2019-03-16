<#macro M_navBottomForCategory category="ood" subCategory="detail" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
	    <ul class="nav nav-pills">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
						
			            <li><a class="<#if subCategory=="info">active</#if>" href="/sz0099/ood/product/personal/myinfoUI">信息  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="favirate">active</#if>"><a href="javascript:void(0)" onclick="developing()">收藏 <span class="glyphicon glyphicon-time text-danger"></span></a></li>
			            
			            
			            <@shiro.hasRole name="plat_creator">
			            <li class="<#if category=="setting">active</#if>"><a href="/sz0099/ood/user/manage/setting">设置</a></li>
			            <li role="separator" class="divider"></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="order">active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList">订单</a></li>
			            </@shiro.hasRole>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory == 'sayword'>active</#if>">
			            <a href="javascript:void(0)" onclick="modifySayword()">一句传说<span class="glyphicon glyphicon-pushpin text-danger"></span></a>
			            </li>
			            
			            <li role="separator" class="divider"></li>
			            <li>
			            <a href="javascript:void(0)" onclick="showInviteQrCode('inviteQrCodeWrapper','inviteQrCodeContent')">邀请二维码<span class="glyphicon glyphicon-pushpin text-danger"></span></a>
			            </li>
			            
			          </ul>
			   </li>
			   <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <@shiro.hasRole name="plat_creator">
			            <li class="<#if category=="orderManage">active</#if>"><a href="/sz0099/ood/product/order/manage/manageOrderList">发货</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="transfer">active</#if>"><a href="#">转单</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="sale">active</#if>"><a href="/sz0099/ood/product/manage/queryProductList">产品</a></li>
			            <li role="separator" class="divider"></li>
			            </@shiro.hasRole>
			            
			            <@shiro.hasRole name="plat_register">
			            <li class="<#if category=="article">active</#if>"><a href="/sz0099/ood/article/manage/queryArticleList">文章 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="prefession">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            </@shiro.hasRole>
			            
			            <@shiro.hasRole name="plat_creator">
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="category">active</#if>"><a href="/sz0099/ood/category/extend/manage/list">类别</a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="position">active</#if>"><a href="/sz0099/ood/position/extend/manage/list">位置</a></li>
			          	</@shiro.hasRole>
			          </ul>
			   </li>
			   <@shiro.hasRole name="plat_creator">
			   <li class="dropdown">
				   <a href="#" class="dropdown-toggle btn btn-danger" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">超级 <span class="caret"></span></a>
				   <ul class="dropdown-menu">
				   		<li class="<#if subCategory=="role">active</#if>"><a href="/auth/role/manage/findRolePage">角色</a></li>
				   		<li role="separator" class="divider"></li>
				   		<li class="<#if subCategory=="user">active</#if>"><a href="/auth/role/manage/findUserPage">用户</a></li>
				   		<li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="quan">active</#if>"><a href="/quan/manage/setting">圈设置</a></li>
						<li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="usersetting">active</#if>"><a href="/auth/user/manage/setting">用户设置</a></li>
				   </ul>
			   </li>
			   </@shiro.hasRole>
			   <li class="dropdown pull-right">
			          <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">群侠&雪峰 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="index">active</#if>"><a href="/sz0099/ood/index">首页</a></li>
			            <#-- <li class="<#if subCategory=="product">active</#if>"><a href="/sz0099/ood/product/index">服务圈</a></li>
			             -->
			            <li class="<#if subCategory=="profession">active</#if>"><a href="/sz0099/ood/home/profession/index">技能圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li class="<#if subCategory=="article">active</#if>"><a href="/sz0099/ood/home/article/index">户外圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li class="<#if category=="continuing">active</#if>"><a href="javascript:void(0)" onclick="developing()">实况时播 <span class="glyphicon glyphicon-time text-danger"></span></a></li>
			            <@shiro.hasRole name="plat_creator">
			            <li class="<#if category=="activity">active</#if>"><a href="javascript:void(0)" onclick="developing()">活动召集</a></li>
			            <li class="<#if category=="requirement">active</#if>"><a href="javascript:void(0)" onclick="developing()">需求预订</a></li>
			            <li class="<#if category=="stickyword">active</#if>"><a href="javascript:void(0)" onclick="developing()">雷人雷语</a></li>
			          	</@shiro.hasRole>
			          </ul>
			   </li>
	    </ul>	
  </div>
</nav>
</#macro>

<#macro M_navBreadForCategory category="ood" subCategory="detail" entity=null>
<ol class="breadcrumb">
  	<#if category=="ood">
	  	<li><a href="/sz0099/ood/product/index">课程</a></li>
	  	<#if subCategory=="detail">
	  	<#if entity!>
	  	<li class="active"><a href="#">${entity.name}</a></li>
	  	</#if>
  		</#if>
	<#elseif category=="qunxia">
		<li><a href="/sz0099/ood/home/article/index">群侠</a></li>
	  	<#if subCategory=="article">
	  	<li class="active"><a href="/sz0099/ood/home/article/index">户外圈</a></li>
		  	<#if entity!>
		  	<li class="active"><a href="#">${entity.name}</a></li>
		  	</#if>
	  	<#elseif subCategory=="profession">
	  	<li class="active"><a href="/sz0099/ood/home/profession/index">技能圈</a></li>
		  	<#if entity!>
		  	<li class="active"><a href="#">${entity.name}</a></li>
		  	</#if>
	  	</#if>
	 <#elseif category=="supermanage">
	 <li><a href="#">超级管理</a></li>
	 	<#if subCategory=="role">
	  	<li class="active"><a href="/sz0099/ood/home/article/index">角色</a></li>
	  	<#elseif subCategory=="user">
	  	<li class="active"><a href="/sz0099/ood/home/profession/index">用户</a></li>
	  	</#if>
	 <#elseif category=="search">
		<li><a href="#">搜索</a></li>
	  	<#if subCategory=="article">
	  		<li class="active"><a href="/sz0099/ood/home/article/index">户外圈</a></li>
		  	<li class="active"><a href="#">文章列表</a></li>
		<#elseif subCategory=="profession">
	  		<li class="active"><a href="/sz0099/ood/home/profession/index">技能圈</a></li>
		  	<li class="active"><a href="#">技能列表</a></li>
	  	</#if>
  </#if>
</ol>
</#macro>


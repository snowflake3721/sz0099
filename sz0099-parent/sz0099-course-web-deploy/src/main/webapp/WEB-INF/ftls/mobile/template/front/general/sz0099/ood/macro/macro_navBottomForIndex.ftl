<#macro M_navBottomForCategory category="ood" subCategory="detail" showView="index" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom" style="vertical-align: middle;">
  <div class="container-fluid" style="vertical-align: middle;">
	     <div class="row text-center">
	     	<div class="col-xs-2">
			          <a href="/sz0099/ood/home/activity/index/recommend?st=general" class="<#if subCategory=="activity">text-danger<#else>text-primary</#if>">
			          <span class="glyphicon glyphicon-flag"><span><br/>
	     				<small>活动</small>
				  	</a>
	     	</div>
	     	<div class="col-xs-2">
			        <a href="/sz0099/ood/home/article/index/recommend?st=general" class="<#if subCategory=="article">text-danger<#else>text-primary</#if>">
			            <span class="glyphicon glyphicon-camera"><span><br/>
				     	<small>文章</small>
				  	</a>
	     	</div>
	     	<div class="col-xs-2" >
	     			<a href="javascript:void(0)" onclick="showInviteQrCode('inviteQrCodeWrapper','inviteQrCodeContent')" class="<#if subCategory=="sayword">text-danger<#else>text-primary</#if>">
			            <span class="glyphicon glyphicon-qrcode"></span><br/>
				     	<small>邀请码</small>
				  	</a>
	     	</div>
	     	<div class="col-xs-2">
	     			<a href="/sz0099/ood/home/profession/index/recommend" class="<#if subCategory=="profession">text-danger<#else>text-primary</#if>">
			            <span class="glyphicon glyphicon-briefcase"><span><br/>
				     	<small>技能</small>
				  	</a>
	     	</div>
	     	<div class="col-xs-2 dropdown">
	     			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		     				<span class="glyphicon glyphicon-pencil"><span><br/>
				          	<small>发布</small>
		     			 	<span class="caret"></span>
	     			</a>
		          <ul class="dropdown-menu">
			            <@shiro.hasRole name="plat_register">
			            <li class="<#if category=="article">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList">文章 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="profession">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="activity">active</#if>"><a href="/sz0099/ood/activity/manage/queryDraftList">活动 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            </@shiro.hasRole>
		          </ul>
	     			
			</div>
	     	<div class="col-xs-2" >
	     			<a href="/sz0099/ood/product/personal/myIndex" class="<#if subCategory=="myIndex">text-danger<#else>text-primary</#if>">
			          <span class="glyphicon glyphicon-user"><span><br/>
			          <small>我的</small>
				  	</a>
	     	</div>
	     </div>
  </div>
</nav>
</#macro>
<#macro M_navBottomForCategory2 category="ood" subCategory="detail" showView="index" entity=null>
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid">
	    <ul class="nav nav-pills">
		      <@shiro.hasRole name="plat_register">
		      <li class="dropdown">
			          <a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
						
			            
			            <li><a class="<#if subCategory=="info">active</#if>" href="/sz0099/ood/product/personal/myinfoUI">信息  <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="favirate">active</#if>"><a href="javascript:void(0)" onclick="developing()">收藏 <span class="glyphicon glyphicon-time text-danger"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="article">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList?showTab=panel_publish">已发布文章<span class="glyphicon glyphicon-pushpin text-danger"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="profession">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList?showTab=panel_publish">已发布技能<span class="glyphicon glyphicon-pushpin text-danger"></span></a></li>
			            
			             <li role="separator" class="divider"></li>
			            <li class="<#if category=="activity">active</#if>"><a href="/sz0099/ood/activity/manage/queryDraftList?showTab=panel_publish">已发布活动<span class="glyphicon glyphicon-pushpin text-danger"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="actOrder">active</#if>"><a href="/sz0099/ood/activity/order/findPage">活动订单</a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="applyClubLeader">active</#if>"><a href="/sz0099/ood/product/personal/applyClubLeader">申请领队</a></li>
			            <@shiro.hasRole name="plat_creator">
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="order">active</#if>"><a href="/sz0099/ood/product/order/myCoeOrderList">订单</a></li>
			            
			            <li role="separator" class="divider"></li>
						<li class="<#if subCategory=="setting">active</#if>"><a href="/sz0099/ood/user/manage/setting">设置</a></li>
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
			          <a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">发布 <span class="caret"></span></a>
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
			            <li class="<#if category=="article">active</#if>"><a href="/sz0099/ood/article/manage/queryDraftList">文章 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="profession">active</#if>"><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            
			            <li role="separator" class="divider"></li>
			            <li class="<#if category=="activity">active</#if>"><a href="/sz0099/ood/activity/manage/queryDraftList">活动 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
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
						<li role="separator" class="divider"></li>
			            <li class="<#if subCategory=="initData">active</#if>"><a href="/tech/initData">数据初始化</a></li>
				   </ul>
			   </li>
			   </@shiro.hasRole>
			   <li class="dropdown pull-right">
			          <a href="#" class="dropdown-toggle btn btn-default" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">群侠 <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li class="<#if subCategory=="index">active</#if>"><a href="/sz0099/ood/home/article/index/recommend?st=general">首页</a></li>
			            <#-- <li class="<#if subCategory=="product">active</#if>"><a href="/sz0099/ood/product/index">服务圈</a></li>
			             -->
			            <li class="<#if subCategory=="profession">active</#if>"><a href="/sz0099/ood/home/profession/index/recommend?st=general">技能圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li class="<#if subCategory=="article">active</#if>"><a href="/sz0099/ood/home/article/index/recommend?st=general">户外圈 <span class="glyphicon glyphicon-chevron-right text-primary"></span></a></li>
			            <li class="<#if category=="continuing">active</#if>"><a href="javascript:void(0)" onclick="developing()">实况时播 <span class="glyphicon glyphicon-time text-danger"></span></a></li>
			            
			            <@shiro.hasRole name="plat_creator">
			            <li class="<#if category=="activity">active</#if>"><a href="javascript:void(0)" onclick="developing()">活动召集</a></li>
			            <li class="<#if category=="requirement">active</#if>"><a href="javascript:void(0)" onclick="developing()">需求预订</a></li>
			            <li class="<#if category=="stickyword">active</#if>"><a href="javascript:void(0)" onclick="developing()">雷人雷语</a></li>
			          	</@shiro.hasRole>
			          	
			          </ul>
			   </li>
			   </@shiro.hasRole>
	    </ul>	
  </div>
</nav>
</#macro>

<#macro M_navBreadForCategory category="ood" subCategory="detail" showView="index" entity=null>
<ol class="breadcrumb navbar-fixed-top">
  	<#if category=="course">
	  	<li><a href="/sz0099/ood/product/index">课程</a></li>
	  	<#if subCategory=="detail">
	  	<#if entity!>
	  	<li class="active"><a href="#">${entity.name}</a></li>
	  	</#if>
  		</#if>
	<#elseif category=="ood">
	  	<#if subCategory=="article">
	  	<li class="active"><a href="/sz0099/ood/home/article/index/recommend?st=general">户外</a></li>
		  	<#if entity!>
		  	<li class="active"><a href="#">${StringUtils.substring(entity.title,0,10)}</a> 
		  		<#assign currentUserId=UserUtils.getUserId()/>
			  	<#if currentUserId! && currentUserId==entity.userId && showView=='detail'>
			  	<a href="/sz0099/ood/article/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_topbtn_edit_fully_${entity.id}">编辑</a>
			  	</#if>
		  	</li>
		  	</#if>
	  	<#elseif subCategory=="profession">
	  	<li class="active"><a href="/sz0099/ood/home/profession/index/recommend">技能</a></li>
		  	<#if entity!>
		  	<li class="active"><a href="#">${StringUtils.substring(entity.title,0,10)}</a>
		  		<#assign currentUserId=UserUtils.getUserId()/>
			  	<#if currentUserId! && currentUserId==entity.userId && showView=='detail'>
			  	<a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_topbtn_edit_fully_${entity.id}">编辑</a>
			  	</#if>
		  	</li>
		  	</#if>
		 <#elseif subCategory=="activity">
	  		<li class="active"><a href="/sz0099/ood/home/activity/index/recommend?st=general">活动</a></li>
			  	<#if entity!>
			  	<li class="active"><a href="#">${StringUtils.substring(entity.title,0,10)}</a>
			  		<#assign currentUserId=UserUtils.getUserId()/>
				  	<#if currentUserId! && currentUserId==entity.userId && showView=='detail'>
				  	<a href="/sz0099/ood/activity/manage/create?id=${entity.id}" type="button" class="btn btn-warning btn-xs" id="id_topbtn_edit_fully_${entity.id}">编辑</a>
				  	</#if>
			  	</li>
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
	  		<li class="active"><a href="/sz0099/ood/home/article/index/recommend?st=general">户外圈</a></li>
		  	<li class="active"><a href="#">文章列表</a></li>
		<#elseif subCategory=="profession">
	  		<li class="active"><a href="/sz0099/ood/home/profession/index/recommend">技能圈</a></li>
		  	<li class="active"><a href="#">技能列表</a></li>
	  	</#if>
	<#elseif category=="article">
		<li><a href="/sz0099/ood/article/manage/queryDraftList">文章</a></li>
		<li><a href="/sz0099/ood/article/manage/queryDraftList">列表</a></li>
		<#if subCategory=="new">
		<li><a href="/sz0099/ood/article/manage/create?id=${entity.id}">编辑</a></li>
		<#elseif subCategory=="detail">
		<li><a href="#">详情预览</a></li>
		</#if>
	<#elseif category=="profession">
		<li><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">技能</a></li>
		<li><a href="/sz0099/ood/personal/profession/manage/queryProfessionList">列表</a></li>
		<#if subCategory=="new">
		<li><a href="/sz0099/ood/personal/profession/manage/create?id=${entity.id}">编辑</a></li>
		<#elseif subCategory=="detail">
		<li><a href="#">详情预览</a></li>
		
		</#if>
	<#elseif category=="activity">
		<li><a href="/sz0099/ood/activity/manage/queryDraftList">活动</a></li>
		<li><a href="/sz0099/ood/activity/manage/queryDraftList">列表</a></li>
		<#if subCategory=="new">
		<li><a href="/sz0099/ood/activity/manage/create?id=${entity.id}">编辑</a></li>
		<#elseif subCategory=="detail">
		<li><a href="#">详情预览</a></li>
		
		</#if>
	<#elseif category=="actOrder">
		<li><a href="/sz0099/ood/product/personal/myIndex">我的</a></li>
		<#if subCategory == 'activity'>
			<li><a href="/sz0099/ood/activity/order/findPage">活动订单</a></li>
		</#if>
	<#elseif category=="position">
		<li><a href="/sz0099/ood/product/personal/myIndex">我的</a></li>
		<li><a href="/sz0099/ood/position/extend/manage/list">位置</a></li>
		<#if subCategory == 'list'>
			<li><a href="javascript:void(0)">列表</a></li>
		<#elseif subCategory=="new">
			<li><a href="javascript:void(0)">编辑</a></li>
		</#if>
	<#elseif category=="category">
		<li><a href="/sz0099/ood/product/personal/myIndex">我的</a></li>
		<li><a href="/sz0099/ood/category/extend/manage/list">类别</a></li>
		<#if subCategory == 'list'>
			<li><a href="javascript:void(0)">列表</a></li>
		<#elseif subCategory=="new">
			<li><a href="javascript:void(0)">编辑</a></li>
		</#if>
	<#elseif category=="myIndex">
	  <li><a href="/sz0099/ood/product/personal/myIndex">我的</a></li>
	  <li><a href="/sz0099/ood/product/personal/myinfoUI">信息</a></li>
		  	<#if subCategory == 'baseinfo'>
		  	<li><a href="/sz0099/ood/product/personal/myinfoUI">基本资料</a></li>
	  		<#elseif subCategory=="inviteQr">
	  		<li><a href="/auth/userqr/viewInvite">邀请码</a></li>
	  		<#elseif subCategory=="recieveImg">
	  		<li><a href="/sz0099/ood/personal/myinfo/recievImgUI">收款码</a></li>
	  		<#elseif subCategory=="verify">
	  		<li><a href="/sz0099/ood/personal/myinfo/verifyUI">身份认证</a></li>
	  		<#elseif subCategory=="applyClubLeader">
	  		<li><a href="/sz0099/ood/product/personal/applyClubLeader">申请领队</a></li>
		  </#if>
	<#elseif category=="role">
	  <li><a href="/sz0099/ood/product/personal/myIndex">管理</a></li>	  
	  <li><a href="/auth/role/manage/findRolePage">角色</a></li>	  
		  <#if subCategory=="userRoleList">
		  <li><a href="/auth/userRole/manage/findUserRolePage">角色用户</a></li>
		  <#elseif subCategory=="new">
		  <li><a href="/auth/role/manage/editUI?id=">新建or编辑</a></li>
		  </#if>
	<#elseif category=="setting">
	  <li><a href="/sz0099/ood/product/personal/myIndex">管理</a></li>	  
	  <li><a href="/auth/user/manage/setting">设置</a></li>	  
		  <#if subCategory=="user">
		  <li><a href="/auth/user/manage/setting">用户相关</a></li>
		  <#elseif subCategory=="quan">
		  <li><a href="/auth/quan/manage/setting">圈子设置</a></li>
		  </#if> 
	<#elseif category=="user">
	  <li><a href="/sz0099/ood/product/personal/myIndex">管理</a></li>	  
	  <li><a href="/auth/user/manage/findUserPage">用户</a></li>	  
		  <#if subCategory=="verify">
		  	<li><a href="/sz0099/ood/personal/myinfo/findVerifyPage">身份认证</a></li>
		  </#if> 
  </#if>
</ol>
</#macro>


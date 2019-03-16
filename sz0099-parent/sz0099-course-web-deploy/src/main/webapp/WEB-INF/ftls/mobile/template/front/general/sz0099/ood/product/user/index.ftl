<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/general/function/func_basic.ftl">
<#include "mobile/template/front/general/macro/macro_qrCode_view.ftl">
<html>
<head>
<title>我的</title>
</head>

<body>
<div class="container " id="body_content">
	<div id="userCenter">
		<div class="panel panel-default">
			<div class="panel-heading">个人中心</div>
			<div class="panel-body">
				<div class="alert alert-warning" role="alert">信息</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <a href="/sz0099/ood/product/personal/myinfoUI" class="btn btn-default btn-warning">
						      	<span class="glyphicon glyphicon-cog"></span><br/>
						      	基本资料
						    </a>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <a href="/sz0099/ood/personal/myinfo/verifyUI" class="btn btn-default btn-warning">
						      	<span class="glyphicon glyphicon-certificate"></span><br/>
						      	身份认证
						    </a>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <a href="/sz0099/ood/product/personal/applyClubLeader" class="btn btn-default btn-info">
						      	<span class="glyphicon glyphicon-retweet"></span><br/>
						      	申请领队
						    </a>
					  </div>
				</div>
				<br/>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <a href="/auth/userqr/viewInvite" class="btn btn-default btn-success">
						      	<span class="glyphicon glyphicon-qrcode"></span><br/>
						      	邀请码
						    </a>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <a href="/sz0099/ood/personal/myinfo/recievImgUI" class="btn btn-default btn-danger">
						      	<span class="glyphicon glyphicon-qrcode"></span><br/>
						      	收款码
						    </a>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <a href="javascript:void(0)" onclick="modifySayword()" class="btn btn-default btn-primary">
						      	<span class="glyphicon glyphicon-pushpin"></span><br/>
						      	一句传说
						    </a>
					  </div>
				</div>
				<br/>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <a href="javascript:void(0)" onclick="developing()" class="btn btn-default btn-danger">
						      	<span class="glyphicon glyphicon-flag"></span><br/>
						      	申领队旗
						    </a>
					  </div>
					  
				</div>
				
				<br/>
				<hr/>
				
				<div class="alert alert-info" role="alert">我的文章</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/article/manage/queryDraftList" class="bg-info">
						      	<span class="glyphicon glyphicon-edit"></span><br/>
						      	草稿
						    </a>
					  		</div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/article/manage/queryDraftList?showTab=panel_publish" class="bg-info">
						      	<span class="glyphicon glyphicon-eye-open"></span><br/>
						      	已发布
						    </a>
					  		</div>
					  </div>
				</div>
				<br/>
				<hr/>
				
				
				<div class="alert alert-info" role="alert">我的活动</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/activity/manage/queryDraftList" class="bg-info">
						      	<span class="glyphicon glyphicon-edit"></span><br/>
						      	草稿
						    </a>
					  		</div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/activity/manage/queryDraftList?showTab=panel_publish" class="bg-info">
						      	<span class="glyphicon glyphicon-eye-open"></span><br/>
						      	已发布
						    </a>
					  		</div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/activity/order/findPage" class="bg-info">
						      	<span class="glyphicon glyphicon-shopping-cart"></span><br/>
						      	活动订单
						    </a>
					  		</div>
					  </div>
				</div>
				<br/>
				<hr/>
				<div class="alert alert-info" role="alert">我的技能</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/personal/profession/manage/queryProfessionList" class="bg-info">
						      	<span class="glyphicon glyphicon-edit"></span><br/>
						      	草稿
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/personal/profession/manage/queryProfessionList?showTab=panel_publish" class="bg-info">
						      	<span class="glyphicon glyphicon-eye-open"></span><br/>
						      	已发布
						    </a>
						    </div>
					  </div>
					  
				</div>
				<br/>
				<hr/>
				
				<div class="alert alert-info" role="alert">出售产品</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="javascript:void(0)" onclick="developing()" class="bg-info">
						      	<span class="glyphicon glyphicon-edit"></span><br/>
						      	草稿
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="javascript:void(0)" onclick="developing()" class="bg-info">
						      	<span class="glyphicon glyphicon-eye-open"></span><br/>
						      	已上架
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="javascript:void(0)" onclick="developing()" class="bg-info">
							      	<span class="glyphicon glyphicon-shopping-cart"></span><br/>
							      	订单管理
							    </a>
						  </div>
					  </div>
				</div>
				<br/>
				<hr/>
				<div class="alert alert-info" role="alert">已购产品</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="javascript:void(0)" onclick="developing()" class="bg-info">
						      	<span class="glyphicon glyphicon-edit"></span><br/>
						      	购物车
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/sz0099/ood/product/order/myCoeOrderList" class="bg-info">
							      	<span class="glyphicon glyphicon-shopping-cart"></span><br/>
							      	商品订单
							    </a>
						  </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="javascript:void(0)" onclick="developing()" class="bg-info">
						      	<span class="glyphicon glyphicon-th-list"></span><br/>
						      	收货地址
						    </a>
						    </div>
					  </div>
				</div>
				<br/>
				<hr/>
				<@shiro.hasRole name="plat_creator">
				<div class="alert alert-danger" role="alert">管理</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/user/manage/setting" class="bg-info">
						      	<span class="glyphicon glyphicon-cog"></span><br/>
						      	设置
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/tech/initData" class="bg-info">
						      	<span class="glyphicon glyphicon-tasks"></span><br/>
						      	数据初始化
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/quan/manage/setting" class="bg-info">
							      	<span class="glyphicon glyphicon-unchecked"></span><br/>
							      	圈设置
							    </a>
						  </div>
					  </div>
				</div>
				<br/>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/sz0099/ood/category/extend/manage/list" class="bg-info">
						      	<span class="glyphicon glyphicon-list"></span><br/>
						      	类别
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/sz0099/ood/position/extend/manage/list" class="bg-info">
							      	<span class="glyphicon glyphicon-map-marker"></span><br/>
							      	位置
							    </a>
						  </div>
					  </div>
				</div>
				<br/>
				<hr/>
				
				<div class="alert alert-danger" role="alert">用户管理</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/auth/user/manage/setting" class="bg-info">
						      	<span class="glyphicon glyphicon-cog"></span><br/>
						      	清除邀请码
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/auth/role/manage/findUserPage" class="bg-info">
							      	<span class="glyphicon glyphicon-user"></span><br/>
							      	用户列表
							    </a>
						  </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/auth/user/manage/setting" class="bg-info">
						      	<span class="glyphicon glyphicon-th-list"></span><br/>
						      	分配角色
						    </a>
						    </div>
					  </div>
				</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/sz0099/ood/personal/myinfo/findVerifyPage" class="bg-info">
							      	<span class="glyphicon glyphicon-check"></span><br/>
							      	身份审核
							    </a>
						  </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						  <div class="well well-sm">
							    <a href="/sz0099/ood/position/extend/manage/list" class="bg-info">
							      	<span class="glyphicon glyphicon-check"></span><br/>
							      	领队审核
							    </a>
						  </div>
					  </div>
				</div>
				
				<br/>
				<hr/>
				
				<div class="alert alert-danger" role="alert">角色管理</div>
				<div class="row text-center">
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/auth/role/manage/findRolePage" class="bg-info">
						      	<span class="glyphicon glyphicon-pawn"></span><br/>
						      	角色列表
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/auth/userRole/manage/findUserRolePage" class="bg-info">
						      	<span class="glyphicon glyphicon-user"></span><br/>
						      	角色用户
						    </a>
						    </div>
					  </div>
					  <div class="col-xs-4 col-md-3">
						    <div class="well well-sm">
						    <a href="/auth/role/manage/editUI?id=" class="bg-info">
						      	<span class="glyphicon glyphicon-plus"></span><br/>
						      	添加
						    </a>
						    </div>
					  </div>
				</div>
				
				<br/>
				<hr/>
				
				</@shiro.hasRole>
			</div><#-- end body -->
		</div><#-- end panel -->
	</div><#-- end userCenter -->
	
<#--我的邀请二维码 	-->
<@M_qrCodeViewForIndex user=UserUtils.getUser()/>
<#-- 底部导航 -->
<@M_navBottomForCategory category="ood" subCategory="myIndex" showView="index" entity=null/>
</div>

<div class="container" id="body_footer_js">	
<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
});
</script>
</div>

</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_navigatMenu.ftl">


<div class="row">
	<div class="col-xs-7">
		<div class="input-group input-group-sm">
	      <input type="text" class="form-control input-sm" placeholder="Search for...">
	      <span class="input-group-btn">
	        <button class="btn btn-default btn-sm" type="button">Go!</button>
	      </span>
	    </div><!-- /input-group -->
	</div>
	<div class="col-xs-3 center-block">
		<span v-show="currentUser.email!=null"> {{email}} </span> 
		<span v-show="currentUser.email==null"> <button type="button" class="btn btn-default btn-xs">登录</button> </span> 
	</div>

	<div class="col-xs-2">
		<@M_groupForPersonalCenter group=group currentMenu=null />
		<#-- 
		<div class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          		<img v-show="currentUser.avatar!=null" :src="currentUser.avatar" alt="个人中心" title="个人中心" class="img-circle" /> 
		          </a>
		          <ul class="dropdown-menu dropdown-menu-right">
		            <li><a href="#">个人中心</a></li>
		            <li><a href="#">我的美文</a></li>
		            <li><a href="#">所获赏金</a></li>
		            <li><a href="#">发布新文</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">赞赏过谁</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">消息 <span class="badge">4334</span></a></li>
		          </ul>
		          <span v-show="currentUser.avatar==null" class="glyphicon glyphicon-user" width="64" height="64"></span>
		 </div>
		 -->
	</div>
</div><!-- row end -->
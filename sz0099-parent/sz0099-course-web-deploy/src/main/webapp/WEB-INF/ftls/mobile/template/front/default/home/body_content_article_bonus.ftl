<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<div class="panel panel-danger">
	  <div class="panel-heading text-center">
	    <h3 class="panel-title">发文有赏</h3>
	  </div>
	  <div class="panel-body">
		  <p class="text-left">
		  	<ul>
			    <li>活动成员发表活动帖子，可以获得打赏，你也可以打赏别人的美文。
		  		文章作者将获得您的打赏奖金哦！</li>
				<li>优秀作者更有机会获得群侠户外纪念品哦！</li>
			</ul>
		  </p>
		  <p class="text-right">
		  <button type="button" class="btn btn-danger btn-sm text-right">我要发美文</button>
		  </p>
		  
		  <p class="text-danger"><span class="label label-danger">最近打赏</span>
		  	<ul class="list-unstyled">
			    <li v-for="(user,index) in userList">
			      <img :src="user.avatar" :alt="user.email" :title="user.email" class="img-circle" />
			      {{ user.email }} {{index}}
			    </li>
		  	</ul>
		  </p>
	  
		</div><!--panel content end-->
</div><!--panel end-->
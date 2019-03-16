<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_article_list_item.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">
	<div class="text-center"><span class="label label-danger">精彩活动</span></div>
    <!--图片滚动开始-->
    <@M_articleImageRoller imageRollerId="home_article_" articleList=articleList />
    <!--图片滚动结束-->
    
    <!--群侠户外，打赏先锋开始-->
    <@M_articlePageForQX articlePage=articlePage />
    
    <#-- 
    <div class="text-center "><span class="label label-success">群侠户外</span></div>
    <div class="jumbotron text-center center-block">
	  <h2>群聚一起 侠义出行</h2>
	  <p>
		  <div class="row">
			  <div class="col-xs-6 col-md-3" v-for="article in articleList" :key="article.title">
			    <a href="#" class="thumbnail">
			      <img :src="article.imageUrl" :alt="article.title">
			    </a>
			    <div class="caption">
			        <h6>{{article.title}}</h6>
			    </div>
			  </div>
		  </div>
	  </p>
	  <p><a class="btn btn-primary btn-sm" href="#" role="button">查看更多...</a></p>
	</div>
	 -->
    <!--群侠户外, 打赏先锋结束-->
    <hr/>
    
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">

<div class="text-center"><span class="label label-danger">旅途上的心灵</span></div>
<@M_articlePageImageRoller imageRollerId="travel_imageRoller_" articlePage=articlePage withTitle="true" guideTip="直抵心灵>>>"/>
<@M_articlePageForTravel articlePage=articlePage />
<#-- 
<!-- VUE模板代码
<div class="jumbotron text-center center-block">
	  <h2>{{firstTravelTitle}}</h2>
	  <p>
		  <div class="row">
			  <div class="col-xs-6 col-md-3" v-for="travel in travelList" :key="travel.title">
			    <a href="#" class="thumbnail">
			      <img :src="travel.avatar" :alt="travel.title">
			    </a>
			    <div class="caption">
			        <h6>{{travel.title}}</h6>
			    </div>
			  </div>
		  </div>
	  </p>
	  <p><a class="btn btn-primary btn-sm" href="#" role="button">查看更多...</a></p>
</div>
 -->
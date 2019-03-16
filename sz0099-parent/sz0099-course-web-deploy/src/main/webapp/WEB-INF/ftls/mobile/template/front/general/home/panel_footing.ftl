<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">
<#include "mobile/template/front/default/macro/macro_article_list_item.ftl">
<#include "mobile/template/front/default/macro/macro_act_calling_list.ftl">
<#include "mobile/template/front/default/macro/macro_article_roller_and_list.ftl">
<#include "mobile/template/front/default/macro/macro_footing_roadline_with_act_art.ftl">


<div class="container">
		<div class="container-fluid" style="background-image: url('/assets/common/images/upload/20180422123533_900X500.jpg');background-size:cover;background-repeat:no-repeat;">
				<div class="page-header" style="margin:20px 2px">
				    <div class="bg-warning" style="vertical-align: middle !important;padding:10px 5px 5px 10px;">
				    	<small><span class="text-danger">每日精品推荐>>></span> </small> 
				    	<h3 class="text-center" style="line-height:1.5">徒步天堂</h3>
				    </div>
				</div>
				<div class="page-content" style="margin:20px 2px">
				 	<p class="bg-info">没有为什么，没有为什么，没有为什么</p>
				 	<p><a class="btn btn-danger btn-sm" href="#" role="button" >户外激发的活力</a></p>
				</div>
	   </div>
</div>
<hr/>

<@M_articlePageImageRoller imageRollerId="carousel-footing-person" articlePage=articlePage withTitle="true" guideTip="人物专访>>>"/>

<hr/>
<!--徒步路线开始-->
<@M_footingRoadLinePage actPage=actPage articlePage=articlePage></@M_footingRoadLinePage>
<!--徒步路线结束-->
<hr/>
<@M_articleRollerAndItemList imageRollerId="imageRollerId" imageInfoList=imageInfoList articlePage=articlePage></@M_articleRollerAndItemList>
 <#-- -->
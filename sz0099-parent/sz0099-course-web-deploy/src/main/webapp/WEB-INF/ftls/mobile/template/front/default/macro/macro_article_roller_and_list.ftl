<#macro M_articleRollerAndItemList imageRollerId imageInfoList articlePage>
<div class="text-center ">
	<span class="label label-danger">友情推荐</span>
</div>
<!--列表页的图片滚动，可用于广告发布，开始-->
<@M_imageRoller imageRollerId=imageRollerId imageInfoList=imageInfoList> </@M_imageRoller>
<!--列表页图片滚动结束-->
    
<hr/>
<!--文章开始-->
<@M_articlePage itemPage=articlePage moreLoaded="true"></@M_articlePage>
<!--文章结束-->

 </#macro>

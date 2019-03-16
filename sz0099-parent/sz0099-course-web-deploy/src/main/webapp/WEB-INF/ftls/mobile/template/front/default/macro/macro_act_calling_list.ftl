<#macro M_actCallingPage itemPage>   
<!--列表开始--> 
<!--活动简要信息开始，page里存放ActivityInfo-->
<#if itemPage??>
      <#list itemPage.content as actCallingItem>
		  <hr/>
		  <div class="media">
		      <div class="media-body">
		        <h4 class="media-heading">${actCallingItem.activity.title}</h4>
		        <p><small>${actCallingItem.activity.subTitle}</small></p>
		        <p><strong>路线：</strong>${actCallingItem.roadLine}</p>
		        <p><strong>活动时间：</strong>${actCallingItem.activityTime}</p>
		        <p><strong>报名截止：</strong><br/>
		        	${actCallingItem.applyBeginTime?string("yyyy-MM-dd")} 至  ${actCallingItem.applyEndTime?string("yyyy-MM-dd")}
		        </p>
		      </div>
		      <div class="media-right">
		        <a href="${actCallingItem.activity.accessUrl}">
		          <img class="media-object" data-src="holder.js/80x80" alt="80x80" src='${actCallingItem.activity.coverImageUrl!"/assets/common/images/logo/img_cover_100X100.gif"}' data-holder-rendered="true" style="width: 80px; height: 80px;">
		        </a>
		        <p>
		        	<strong>报名人数：</strong>${actCallingItem.peopleJoinNum}/${actCallingItem.peopleMaxNum}
		        	<span class="label label-info">${actCallingItem.peopleViewNum} <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></span>
		        </p>
		        <p class="text-right">
		        	<a href="${actCallingItem.activity.accessUrl}" class="btn btn-danger btn-xs" role="button">查看详情</a>
		        </p> 
		      </div>
		  </div>
  	  </#list>
 </#if>

<!--活动列表结束-->
 </#macro>

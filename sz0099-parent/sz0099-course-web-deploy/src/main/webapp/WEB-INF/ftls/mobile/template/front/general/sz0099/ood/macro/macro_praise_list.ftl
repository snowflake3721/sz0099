<#macro M_showWordList praiseList=null> 
 	<#if praiseList! && praiseList?size gt 0 >
	 	<div class="panel panel-success">
		  	<div class="panel-heading">那些赞语...</div>
		  	<ul class="list-group">
	 	<#list praiseList as praise>
			<#-- <a href='javascript:void(0)' onclick="showProfile(function(){alert('${image.userId}')})"> -->
			<li class="list-group-item list-group-item-<#if praise?is_odd_item>default<#else>warning</#if>" >
				<a href='javascript:void(0)' onclick="showSaywordHistory('${praise.nickname}','${praise.sayword.description}','@${DateUtils.formatToString(praise.refreshTime ,'yyyy-MM-dd')}')">
				  <img id='id_tpl_media_headImg${praise.id}' class='img-circle'  width='25px' height='25px' src='${praise.headImg}' alt='${praise.nickname}' title='${praise.sayword.description}'>
				${praise.nickname}
				</a>
				说：
				<span class="<#if praise?is_odd_item>bg-info<#else>bg-warning</#if>">${praise.word}</span>
			</li>
		</#list>
		  	</ul>
		</div>
		
		
	</#if>
</#macro>

<#macro M_showWordPage praisePage=null> 
	<#if praisePage!>
		<#assign praiseList=praisePage.content />
	 	<#if praiseList! && praiseList?size gt 0 >
		 	<@M_showWordList praiseList=praiseList/>
		</#if>
	 	<#if praisePage.totalPages gt 1 >
	 	...
		</#if>
	</#if>
</#macro>
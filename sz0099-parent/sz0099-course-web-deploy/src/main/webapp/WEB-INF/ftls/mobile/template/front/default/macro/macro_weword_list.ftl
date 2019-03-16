<#macro M_weWordPage page> 
<#if page??>
<#assign weWordList=page.content />
	<#list weWordList as weWord>
		<#if weWord?is_odd_item>
		<div class="container">	
			  	  	<div class="row">
						  <div class="col-xs-9 text-left">
						  		<p class="text-info" data-toggle="tooltip" data-placement="bottom" title="${weWord.nickname}">
					          		<span class="badge">${weWord.stat.replyNum}</span>
					          		${weWord.content}
					          		<#assign i=weWord?index />
					          		<span class="label label-info">${(weWord.publishTime?string("MM-dd HH:mm"))!''}</span>
				          		</p>
						  </div>
						  <div class="col-xs-3 text-right">
							  	<span class="glyphicon glyphicon-thumbs-up text-danger span-margin-4">${(weWord.stat.thumbUpNum)}</span>
				          		<span class="glyphicon glyphicon-thumbs-down span-margin-4">${(weWord.stat.thumbDownNum)}</span>
				          </div>
					</div>
		</div>
		<hr/>
		<#else>
		<div class="container">
		        <div class="row">
					<div class="col-xs-3 text-left">
						<span class="glyphicon glyphicon-thumbs-up text-danger span-margin-4">${(weWord.stat.thumbUpNum)}</span>
				        <span class="glyphicon glyphicon-thumbs-down span-margin-4">${(weWord.stat.thumbDownNum)}</span>
					</div>
					<div class="col-xs-9 text-right">
				  		<p class="text-right" data-toggle="tooltip" data-placement="top" title="${weWord.nickname}">
				      		<span class="label label-warning">${(weWord.publishTime?string("MM-dd HH:mm"))!''}</span>
				      		${weWord.content}
				      		<span class="badge">${weWord.stat.replyNum}</span>
				  		</p>
					</div>
				</div><!--row end-->
		</div>
		<hr/>
		</#if>
	</#list>
	<div class="text-center">
      		<button type="button" class="btn btn-primary">换一组</button>
    </div>
</#if>
</#macro>

<#macro M_weWordOwnself weWord=null>
<div class="alert alert-success text-left">
	<#if weWord??>
		<button class="btn btn-danger btn-xs" type="button">
	  		<span class="badge badge-danger">${weWord.stat.replyNum}</span>
		</button>
	  	 我说：${weWord.content} at ${(weWord.publishTime?string("MM-dd HH:mm"))!''}
	  	<span class="glyphicon glyphicon-thumbs-up text-danger span-margin-4">${(weWord.stat.thumbUpNum)}</span>
		<span class="glyphicon glyphicon-thumbs-down span-margin-4">${(weWord.stat.thumbDownNum)}</span>
	
	<#else>
		<button class="btn btn-danger btn-xs" type="button">
	  		<span class="badge badge-danger">0</span>
		</button> 今天我啥也没说...
	</#if>
</div>
</#macro>

<#macro M_weWordFirstRecomment weWord=null>
<div class="alert alert-danger text-left">
	<#if weWord??>
		
		<p class="text-danger" data-toggle="tooltip" data-placement="bottom" title="${weWord.nickname}">
		  	<button class="btn btn-info btn-xs" type="button">
	  			<span class="badge badge-danger">${weWord.stat.replyNum}</span>
			</button> 
		  	 头条：${weWord.content} at ${(weWord.publishTime?string("MM-dd HH:mm"))!''}
		  	<span class="glyphicon glyphicon-thumbs-up text-danger span-margin-4">${(weWord.stat.thumbUpNum)}</span>
			<span class="glyphicon glyphicon-thumbs-down span-margin-4">${(weWord.stat.thumbDownNum)}</span>
		</p>
	<#else>
		<button class="btn btn-danger btn-xs" type="button">
	  		<span class="badge badge-danger">0</span>
		</button> 今天的头条还没选出来...
	</#if>
</div>
</#macro>
<div class="container" id="body_content">

<#-- 发布结果  begin -->
	<div  id="div_artilce_publish_result">
	    <h4>文章发布结果 </h4>
	    <div class="alert <#if ActivityResponseCode.CODE_ARTICLE_PUBLISH_SUCCESS==article.respCode>alert-success<#else>alert-danger</#if>" role="alert">
	    ${article.respMsg}
	    </div>
	    
	    <p class="text-danger">
	    1.感谢您的分享<br/>
	    2.文章审核大约需要1-3个工作日，请耐心等待...<br/>
	    </p>
	    
	    <p class="head">您可以操作以下选项，观览平台内容：
	    </p>
	    
	    <p class="text-justify">
		    <a href="/activity/article/view/${article.id}" class="btn btn-info" id="id_step_preview_${article.id}">
		    <span class="glyphicon glyphicon-eye-open"></span> 
		           查看预览 <span>${article.title}</span> 
		    </a>
		    
		    <a href="/activity/article/view/${article.id}" class="btn btn-info" id="id_step_preview_${article.id}">
		    <span class="glyphicon glyphicon-share-alt"></span> 
		           <span>回到首页</span> 
		    </a>
	    </p>
	</div>
<#-- 发布结果  end -->
</div>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<div class="row">
	  	<div class="col-xs-12 col-md-8">
			<div class="text-center">
				<h4>一句传说 修改</h4>
				<hr/>
				<div class="form-group">
				<textarea class="form-control input-sm" rows="3" id="id_sayword" placeholder="留下你的一句传说" aria-describedby="id_sayword_addon">${entity.description}</textarea>
						<#-- 
						<input type="text" value="${entity.description}" class="form-control input-sm" id="id_sayword" placeholder="留下你的一句传说" aria-describedby="id_sayword_addon">
						 -->
						<input type="hidden" value="${entity.id}" class="form-control input-sm" id="id_sayword_id">
				
				<div id="id_messageTip_sayword" class="text-center"></div>
				<br/>
				<p class="text-success text-left"><small>小技巧：<br/>不定期修改传说，时常发文、点赞，<br/>你的传说才能广为流传呀！<br/>每天仅一句有效哦！</small></p>
				</div>
			</div>
		</div>
</div>
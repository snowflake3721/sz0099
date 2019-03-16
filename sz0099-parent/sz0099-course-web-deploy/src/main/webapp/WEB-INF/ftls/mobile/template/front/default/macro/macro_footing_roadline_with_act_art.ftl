<#macro M_footingRoadLinePage actPage articlePage>
<div class="panel panel-primary">
	  <div class="panel-heading text-center">
	    <h3 class="panel-title">徒步线路</h3>
	  </div>
	  <div class="panel-body">
		  <div class="text-left">
		  		<p>
			  		<button type="button" class="btn btn-danger btn-sm" aria-label="Left Align">
					  	活动召集 <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
					</button>
				</p>
				 
				<@M_actCallingPage itemPage=actPage></@M_actCallingPage>
				
				 <hr/>
				 <p>
					 <button type="button" class="btn btn-primary btn-sm" aria-label="Left Align">
					  	往期相关 <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
					 </button>
				 </p>
				 
				<!--往期相关文章开始(与活动召集相关)，每个活动取2条数据，由VUE控制 加载更多-->
				<@MV_articlePage itemPage=articlePage></@MV_articlePage>
				<!--往期相关文章结束(与活动召集相关)，每个活动取2条数据-->
			</div>    
	  </div><!--panel content end-->
</div><!--panel end-->
 </#macro>
<#macro MV_articlePreview modalId="default" imageRollerId="av_irId" article=null withTitle="true">
<!-- Modal -->
<div class="modal fade" id="modal_${modalId}" tabindex="-1" role="dialog" aria-labelledby="Label_${modalId}">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="Label_${modalId}">文章发布预览</h4>
      </div>
      <div class="modal-body">
	       <@MV_articleView isPreview=true />
      </div><!-- end modal-body -->
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">提交发布审核</button>
      </div>
    </div>
  </div>
</div>
</#macro>

<#macro MV_articleView isPreview=false article=null>
		   <h4 class="text-center">{{articleView.title}}
	       <br/>
	       <small>{{articleView.subTitle}}</small>
	       </h4>
	       <p class="text-right">[文] {{articleView.authorname}} at {{articleView.publishTime}}</p>
	       <p class="text-left">
	       <span v-for="(atag,index) in articleView.articleTagList" class="label label-info" style="display: inline-block;font-size:13px;margin:2px 2px;">
					{{atag.tag.name}}
		   </span>	
	       </p>
			
			<div class="row">
			  <div class="col-xs-3 col-md-5"></div>
			  <div class="col-xs-6 col-md-2">
			  		<nav aria-label="...">
					  <ul class="pager">
					    <li class="previous"><a href="javascript:void(0)" class="text-danger"><span class="glyphicon glyphicon-thumbs-up"></span> 顶</a></li>
					    <li class="next"><a href="javascript:void(0)" class="text-muted">踩 <span class="glyphicon glyphicon-thumbs-down"></span></a></li>
					  </ul>
					</nav>
			  </div>
			  <div class="col-xs-3 col-md-5"></div>
			</div>
	       
	       <hr/>
	       <#-- 
	       <@M_singleArticleImageRoller imageRollerId=article.id article=article withTitle="false" />
	       <#include "mobile/template/front/default/article/article_view_banner.ftl">
	        -->
        	<template>
			    <el-carousel height="180px">
			      <el-carousel-item v-for="itemImage in articleView.bannerFileJson.imagesArticle" :key="itemImage.id">
			      	<a :key="itemImage.imageId" href="javascript:void(0)" class="thumbnail">
			      	<h5 class="text-right">{{itemImage.title}}</h5>
			      	<img :src="itemImage.accessUrl" :alt="itemImage.alt" :title="itemImage.title" class="img-rounded">
			      	</a>
			      </el-carousel-item>
			    </el-carousel>
 			</template> 
 			
        	<br/>
	        <ul class="list-inline" >
	        	<li v-show="roadLine.roadPoints.length>0" >
	        		<mark>{{roadLine.name?roadLine.name:'路线'}}</mark>
	        	</li>
				<li v-for="(point,point_index) in roadLine.roadPoints" >
		      	<span class="text-right">{{point.name}}</span>
		      	<span v-show="point_index<roadLine.roadPoints.length-1" class="glyphicon glyphicon-chevron-right"></span>
		    	</li>
		    </ul>
	        <hr/>
	       <br/>
	       <template v-show="articleView.itemBoList.length>0" v-for="(item,index) in articleView.itemBoList">
		       <div v-show="item.imagesArticle.length>0 || item.content!=null || item.title!=null">
			       <h5><mark>{{item.title}}</mark></h5>
			       <p class="text-left" >
			       		{{item.content}} -- {{index}}
			       </p>
			       <ul class="list-inline" >
						<li v-for="(itemImage,itemImage_index) in item.imagesArticle" >
					    	<a :key="itemImage.imageId" href="javascript:void(0)" class="thumbnail">
					      	<img :src="itemImage.accessUrl" :alt="itemImage.alt" :title="itemImage.title" class="img-rounded">
					      	<p class="text-right">{{itemImage.title}}</p>
					    	</a>
				    	</li>
				    </ul>
				    <hr/>
		       </div>
	       </template>
	       <h3 class="text-center" id="reward_pop_container">
		       <button class="btn btn-lg btn-danger"  id="btn_reward_pop" 
		       data-container="#reward_pop_container" data-placement="top" @click='showRewardPanel'
		       title="选择打赏金额(元)" 
		       data-content="
			       <p class='text-center' id='popover_content'>
				       <div class='row'>
						  <div class='col-xs-3'><button class='btn btn-xs btn-defult'>2.68</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-info'>6.66</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-warning'>8.88</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-primary'>9.99</button></div>
						</div>
						<hr/>
						<div class='row'>
						  <div class='col-xs-3'><button class='btn btn-xs btn-success'>13.14</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-danger'>25.25</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-success'>38.38</button></div>
						  <div class='col-xs-3'><button class='btn btn-xs btn-danger'>66.88</button></div>
						</div>
						<hr/>
						<div class='row'>
							<div class='col-xs-12'>
								<div class='input-group input-group-sm'>
									      		<input type='text' class='form-control' id='id_reward_amount' v-model='articleTag.name' placeholder='其它金额(≥2.00￥)' >
									      		<span class='input-group-btn'>
									      		<button class='btn btn-danger btn-xs' type='button' >确定</button>
									      		<button class='btn btn-default btn-xs' type='button' v-on:click='clearTag()'>清空</button>
									      		</span>		
								</div>
							</div>
						</div>
					</p>
		       "
		       data-html="ture"
		       >
		       		<span class="glyphicon glyphicon-yen">赏</span>
		       </button>
	       </h3>

</#macro>


<#macro M_articleView article=null>

	<@M_bannerImageRollerForView imageRollerId=article.id entity=article withTitle="false" />
		   <h4 class="text-center">${article.title}
	       <br/>
	       <small>${article.subTitle}</small>
	       </h4>
	       <p class="text-right">[文] ${article.authorname} at ${article.publishTime?datetime}</p>
	       <br/>
	        <ul class="list-inline" >
	        	<#assign roadLine=article.roadLine/>
	        	<#if roadLine?? && roadLine.roadPoints!?size gt 0 >
		        	<li >
		        		<mark class="bg-success">${roadLine.name?default("路线")}</mark>
		        	</li>
		        	<#list roadLine.roadPoints as point>
					<li>
			      		<span class="text-right">${point.name}</span>
			      		<#if point?has_next>
			      		<span class="glyphicon glyphicon-chevron-right"></span>
			      		</#if>
			    	</li>
			    	</#list>
		    	</#if>
		    </ul>
	        <hr/>
	       <#assign atagList=article.articleTagList />
	       <#if atagList!?size gt 0 >
	       <p class="text-left">
	       		<#list atagList as atag >
			       <span class="label label-info" style="display: inline-block;font-size:13px;margin:2px 2px;">
							${atag.tag.name}
				   </span>
			   </#list>
	       </p>
		   </#if>
		   <br/>
		   		<div class="row">
				  <div class="col-xs-2 text-right center-vertical"><button type="button" class="btn btn-warning btn-xs previous"><span class="glyphicon glyphicon-thumbs-up"></span> 顶</button></div>
				  <div class="col-xs-8 text-center center-vertical"><@M_dataArticleRewardDonate id="2" position="bottom" code=StaticDataDef.DATA_CASH_REWARD_CODE /></div>
				  <div class="col-xs-2 text-left center-vertical"><button type="button" class="btn btn-primary btn-xs bg-info next">踩 <span class="glyphicon glyphicon-thumbs-down"></span></button></div>
				</div>
	       <hr/>
        	
	       <br/>
	       <#assign itemBoList=article.itemBoList/>
	       <#if itemBoList??>
	       <#list itemBoList as item>
	       <#assign imagesArticle=item.imagesArticle />
	       	   <#if imagesArticle!?size gt 0 || item.content?? || item.title??>
	       	   <div>
			       <h5><mark>${item.title}</mark></h5>
			       <p class="text-left" >
			       		${item.content} -- ${index}
			       </p>
			       <#if imagesArticle!?size gt 0>
			       <ul class="list-inline" >
			       		<#list imagesArticle as itemImage>
						<li>
					    	<a id="${itemImage.imageId}" href="javascript:void(0)" class="thumbnail">
					      	<img src="${itemImage.accessUrl}" alt="${itemImage.alt}" title="${itemImage.title}" class="img-rounded">
					      	<p class="text-right">${itemImage.title}</p>
					    	</a>
				    	</li>
				    	</#list>
				    </ul>
				    </#if>
				    <hr/>
		       </div>
		       </#if>
	       </#list>
	       </#if>
	       <p class="text-center">
	       <@M_dataArticleRewardDonate id="1" position="top" code=StaticDataDef.DATA_CASH_REWARD_CODE />
		   </p>
</#macro>
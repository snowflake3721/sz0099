<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_image_roller.ftl">
<#include "mobile/template/front/default/macro/macro_article_view_single.ftl">

<div class="container" id="top_menu">
个人中心 > 发表美文 > <span class="text-warning" id="id_span_nav">第1步 设置标题</span>
</div>
<div class="container" id="body_top">
	<#include "mobile/template/front/default/activity/article/category/category_select.ftl">
</div>

<div class="container" id="body_content">


<div class="alert alert-danger alert-dismissible" role="alert" v-show="errorMessage!=''" id="id_alert_error_1">
  <button type="button" class="close" aria-label="Close" v-on:click="errorMessage=''"><span aria-hidden="true">&times;</span></button>
  <strong>有误!</strong>
  <p><span v-html="errorMessage"></span></p>
</div>

<div class="alert alert-success alert-dismissible" role="alert" v-show="successMessage!=''" id="id_alert_success_1">
  <button type="button" class="close" aria-label="Close" v-on:click="successMessage=''"><span aria-hidden="true">&times;</span></button>
  <strong>成功!</strong>
  <p><span v-html="successMessage"></span></p>
</div>

<#-- 第1步 创建标题  begin -->
<a name="a_step_1"></a>
<div class="panel panel-warning" v-show="currentStep==1" id="div_step_1">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第1步：设置标题及选择分类</h3>
		  </div>
		  <div class="panel-body">
					<form>
						  <div class="form-group">
						    <label for="preIntro">引导语</label> {{articleBo.preIntro}}
						    <input type="text" class="form-control" id="preIntro" v-model="articleBo.preIntro" placeholder="引导语">
						  </div>
						  <div class="form-group">
						    <label for="title">标题</label> {{articleBo.title}}
						    <input type="text" class="form-control" id="title" v-model="articleBo.title" placeholder="文章标题">
						  </div>
						  <div class="form-group"> 
						    <label for="subTitle">子标题</label> {{articleBo.subTitle}}
						    <input type="text" class="form-control" id="subTitle" v-model="articleBo.subTitle" placeholder="文章子标题">
						  </div>
						  
						  <div class="form-group">
						    <label for="description">简短描述(200字以内)</label>
						    <textarea class="form-control" rows="8" id="description" v-model="articleBo.description" placeholder="文章概况200字以内"></textarea>
						  </div>
						  
						  <p class="text-right">
						  	<button type="button" class="btn btn-primary" v-on:click="mergeForTitle()">保存</button>
						  </p>
					</form>
					<button class="btn btn-info" v-on:click="chooseImage">选择图片</button>
			</div><!--panel body end-->
</div><!--panel end-->
<#-- 第1步 创建标题  end -->

<#-- 第2步 设置封面 begin -->
<a name="a_step_2"></a>
<div class="panel panel-warning" v-show="currentStep==2" id="div_step_2" name="div_step_2">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第2步 设置封面</h3>
		  </div>
		  <div class="panel-body">
		  			<p>
					<b><small>最多可设置 3 张封面图片，系统将轮询或随机展示在文章封面中</small></b>
					</p>
					<#-- 
					<div class="container">
						<button class="btn btn-info">选择已上传的活动图片</button>
					</div>
					 -->
					<br/>
					<div class="file-loading">
						<input id="id_file_cover" type="file" multiple name="photoItems" />
					</div>
					<p class="text-danger">
						<em>选择图片后，点击 上传 <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</p>
					<hr/>
					<p class="text-danger">
						<button class="btn btn-primary btn-xs" v-on:click="refreshImagePreviewInner('${articleBo.id}','${ArticlePhoto.POSITION_COVER}')">封面图片预览刷新</button>
					</p>
					<div class="row">
							 
						   <div class="col-xs-4 col-md-2" v-for="coverImage in coverImages">
						    	<a :key="coverImage.imageId" href="javascript:void(0)" class="thumbnail">
						      	<img :src="coverImage.accessUrl" :alt="coverImage.alt" :title="coverImage.title">
						    	<span class="label label-danger" v-on:click="deletePhotoItem(coverImage.articleId, coverImage.photoId,coverImage.imageId, coverImage.position)">删除</span>
						    	</a>
						    	<br/>
						  </div>
						  
					</div>
					
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第2步 设置封面 end -->

<#-- 第3步 设置横幅照片 begin -->
<a name="a_step_3"></a>
<div class="panel panel-warning" v-show="currentStep==3" id="div_step_3" name="div_step_3">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第3步 设置横幅照片</h3>
		  </div>
		  <div class="panel-body">
					<p>
						<b><small>
						1.最多可设置 5 张banner图片，将以横幅方式轮询展示在文章顶部<br/>
						2.可为每一张banner图片设置专属标题及排序,填写完成后点击<span class="label label-success">设置</span>按钮<br/>
						3.头部图片要求：>> 宽:高=4:3
						  <br/><em>推荐尺寸如：720X540 px, 1080X810 px</em>
						</small></b>
					</p>
					
					<#-- 
					<div class="container">
						<button class="btn btn-info">选择已上传的活动图片</button>
					</div>
					 -->
					<br/>
					
					<div class="file-loading">
						<input id="id_file_banner" type="file" multiple name="photoItems" />
					</div>
					<p class="text-danger">
						<em>选择图片后，点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
					</p>
					<hr/>
					<p class="text-danger">
					<button class="btn btn-primary btn-xs" v-on:click="refreshImagePreviewInner('${articleBo.id}','${ArticlePhoto.POSITION_BANNER}')">Banner头部图片预览刷新</button>
					</p>
					<div class="row">
						  
						  <div class="col-xs-12 col-md-6" v-for="(bannerImage,banner_index) in bannerImages">
							    <div class="thumbnail">
								      <img :src="bannerImage.accessUrl" :alt="bannerImage.alt" :title="bannerImage.title">
								      <div class="caption">
								     	<p>
								        		<input type="text" class="form-control input-sm" v-model="bannerImage.title" placeholder="图片标题" size="8"/>
								        		<input type="text" class="form-control input-sm" v-model="bannerImage.orderSeq" placeholder="排序" size="2"/>
								        		
								      	</p>
								        <p class="text-justify">
								        <span class="label label-danger" v-on:click="deletePhotoItem(bannerImage.articleId, bannerImage.photoId,bannerImage.imageId, bannerImage.position)">删除</span>
							    		<span class="label label-success" v-on:click="mergeForSeqAndThirdTitle(bannerImage.articleId, bannerImage.photoId,bannerImage.imageId, bannerImage.position, bannerImage.title,bannerImage.orderSeq,banner_index)">设置</span>
						    				<span v-show="bannerImage.success==2" :id="'span_photo_banner_failure_'+articlePhotoBo.id" class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
						    				<span v-show="bannerImage.success==1" :id="'span_photo_banner_success_'+articlePhotoBo.id" class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							    		</p>
							    		<p><small>点击“设置”按钮设置该图片标题与排序</small></p>
								      </div>
							    </div>
						  </div>
						  
					</div>
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第3步 设置横幅照片 end -->

<#-- 第4步编辑段落 begin -->
<a name="a_step_4"></a>
<div class="panel panel-warning" v-show="currentStep==4" id="div_step_4" name="div_step_4">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第4步 编辑段落[正文]</h3>
		  </div>
		  <div class="panel-body">
					<h5 class="text-info">段落说明  <span class="glyphicon glyphicon-minus" v-show="articleItemIconShow" v-on:click="articleItemIconShow=false"></span> <span class="glyphicon glyphicon-plus" v-show="!articleItemIconShow" v-on:click="articleItemIconShow=true"></span></h5>
					<p v-show="articleItemIconShow">
					<b>
					<small>
					1.每篇文章可以分成若干段落，最多不超过 15 个<br/>
					2.一个段落由【小标题、段落内容、排序号、若干图片】四部分组成; <br/> 
					3.每个段落最多可拥有5张图片, 上传图片时:选择本地图片 >> 点击 “上传”<span class="glyphicon glyphicon-upload"></span> 图标<br/>
					4.每添加一个段落，要填入段落小标题、内容、排序号，不要忘记点 右侧的 <span class="glyphicon glyphicon-floppy-disk text-success" aria-hidden="true"></span> “保存按钮”进行保存<br/>
					5.要注意：上传图片<span class="glyphicon glyphicon-upload"></span> 与保存段落<span class="glyphicon glyphicon-floppy-disk text-success" aria-hidden="true"></span>是相互独立的操作<br/>
					6.若您的文章过长时，不妨将其拆成几篇来写，形成一组系列文章<br/>
					7.<em class="text-danger"><span class="glyphicon glyphicon-info-sign"></span>一个良好的习惯是:每编辑一个段落，都及时保存该段落</em>
					</small>
					</b>
					</p>
					
					<br/>
					<div v-show="articleItemList.length>0" v-for="(ail,index) in articleItemList">
							<div class="row">
								  <div class="col-xs-6">
								    <div class="input-group input-group-sm">
								      <input type="text" class="form-control" v-model="ail.title" placeholder="输入段落小标题" >
								    </div>
								  </div>
								  <div class="col-xs-3">
								    <div class="input-group input-group-sm">
								      <input type="text" class="form-control input-sm" v-model="ail.orderSeq" placeholder="段排序">
								    </div>
								  </div>
								  
								  <div class="col-xs-3 text-justify middle">
								  <span class="glyphicon glyphicon-floppy-disk text-success" aria-hidden="true" v-on:click="saveArticleItem(ail.id, ail.title, ail.content, ail.orderSeq,'cycle', index)"></span>
								  &nbsp;&nbsp;&nbsp;&nbsp;
								  <span class="glyphicon glyphicon-trash text-danger" aria-hidden="true" v-on:click="deleteArticleItem(ail.id,'cycle',index)"></span>
								  </div>
							</div><!-- /.row -->
							<div class="row">
								  <div class="col-xs-12">
								      <textarea class="form-control input-sm" rows="3" v-model="ail.content" placeholder="输入段落内容"></textarea>
								      <p>
								      	<small><em>
								      	点击 右侧的 <span class="glyphicon glyphicon-floppy-disk text-success" aria-hidden="true"></span> 保存按钮进行保存
								      	</em></small>
								      </p>
								  </div>
							</div><!-- /.row -->
							
							<div class="file-loading">
								<input :id="'id_file_articleItem_'+ail.id" type="file" multiple name="photoItems" />
							</div>
							<p class="text-danger">
								<small>
								<em>选择段落图片后（限5张以内），点击 上传  <span class="glyphicon glyphicon-upload"></span> 图标</em>
								</small>
							</p>
							<div class="row">
								<div class="col-xs-4 col-md-2" >
							</div>
						    </div><!-- /.row -->
						   
							<ul class="list-inline" >
								<li v-for="(itemImage,itemImage_index) in ail.imagesArticle" >
							    	<a :key="itemImage.id" href="javascript:void(0)" class="thumbnail">
							    	<span class="label label-danger" v-on:click="deletePhotoItemForItem(itemImage.articleId, itemImage.articleItemId,itemImage.photoId, itemImage.imageId, itemImage.position, index, itemImage_index)">删除</span>
							      	<img :src="itemImage.accessUrl" :alt="itemImage.alt" :title="itemImage.title" width="90px">
							    	<input type="text" class="form-control input-sm" v-model="itemImage.title" placeholder="图标题" size="2"/>
							    	<input type="text" class="form-control input-sm" v-model="itemImage.orderSeq" placeholder="图排序" size="2"/>
							    	</a>
						    	</li>
						    	<li><kbd><small>图片标题及排序</small></kbd>
						    		<button class="btn btn-info btn-xs" type="button" v-on:click="saveArticlePhotos(ail.articleId,ail.id, index)">保存段落</button>
						    	</li>
						    </ul>
						     <div :class="ail.success==1?'alert alert-success':'alert alert-danger'" role="alert">
					    		<span v-show="ail.success==2" :id="'span_photo_sorted_failure_'+ail.id" class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
					    		<span v-show="ail.success==1" :id="'span_photo_sorted_success_'+ail.id" class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
					    		<small>{{ail.respMsg}}</small>
					    	</div>
							<hr/>
							<div class="text-center text-muted"><small> -- {{index}} -- 段落分隔线 -- </small></div>  
							<hr/>
					</div>
					
					<div class="row">
						<div class="col-xs-6">
						<button class="btn btn-primary btn-xs" type="button" v-on:click="addArticleItem()">添加一个段落</button>
						</div>
						<div class="col-xs-6">
						<button class="btn btn-primary btn-xs" type="button" v-on:click="queryItemListByArticleId()">刷新段落及排序</button>
						</div>
					</div>
					
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第4步 添加段落 end -->

<#-- 第5步 设置路线 begin -->
<div class="panel panel-warning" v-show="currentStep==5" id="div_step_5" name="div_step_5">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第5步 设置路线</h3>
		  </div>
		  <div class="panel-body">
		  			<h6>路线标题：</h6>
					<div class="row">
						<div class="col-xs-8">
							<div class="input-group input-group-sm">
						      		<input type="text" class="form-control" v-model="roadLine.name" placeholder="输入路线标题" >
						    </div><!-- /input-group -->
						</div>
						<div class="col-xs-4 text-right">
							<button class="btn btn-primary btn-xs" type="button" v-on:click="saveRoadLine()">保存标题</button>
						</div>
					</div>
					<p>
						<b><small>
						1.路线是指以 若干 行程点 串依次排序串联成的一条线路<br/>
						2.行程点是指行程中主要的标志性路口或建筑或地点，以简短文字描述; <br/>
					</p>
					
					
					<div class="row">
						<div class="col-xs-6">
						<button class="btn btn-info btn-xs" type="button" v-on:click="addRoadPoint()">添加一个行程点</button>
						</div>
						<div class="col-xs-3">
						<button class="btn btn-info btn-xs" type="button" v-on:click="queryRoadLine()">刷新排序</button>
						</div>
						<div class="col-xs-3 text-right">
						<button class="btn btn-primary btn-xs" type="button" v-on:click="saveAllRoadPoint()">保存全部</button>
						</div>
					</div>
					<br/>
					
					<div class="row" v-show="roadLine.roadPoints.length>0" v-for="(rp,index) in roadLine.roadPoints">
						  <div class="col-xs-6">
						    <div class="input-group input-group-sm">
						      <input type="text" class="form-control" v-model="rp.name" placeholder="输入行程点名称" >
						    </div><!-- /input-group -->
						  </div><!-- /.col-xs-6 -->
						  <div class="col-xs-3">
						    <div class="input-group input-group-sm">
						      <input type="text" class="form-control" v-model="rp.orderSeq" placeholder="排序">
						    </div><!-- /input-group -->
						  </div><!-- /.col-xs-4 -->
						  
						  <div class="col-xs-3 text-justify middle">
						  <span class="glyphicon glyphicon-floppy-disk text-success" aria-hidden="true" v-on:click="saveRoadPoint(rp.id, rp.name,rp.orderSeq,'cycle')"></span>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						  <span class="glyphicon glyphicon-trash text-danger" aria-hidden="true" v-on:click="deleteRoadPoint(rp.id,'cycle',index)"></span>
						  </div><!-- /.col-xs-2 -->
					</div><!-- /.row -->
					
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第5步 设置路线 end -->

<#-- 第6步 设置标签 begin -->
<div class="panel panel-warning" v-show="currentStep==6" id="div_step_6" name="div_step_6">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第6步 设置标签</h3>
		  </div>
		  <div class="panel-body">
		  			<p>
					<b><small>
					1.标签用来快速标识文章主体，方便搜索查找; <br/> 
					2.每篇文章最多设置 4 个标签<br/>
					3.系统默认将分类作为一个标签，故您不必添加与分类名称相同的标签
					</small></b>
					</p>
					
					<h6>设置标签：</h6>
					<div class="row">
						<div class="col-xs-8">
							<div class="input-group input-group-sm">
						      		<input type="text" class="form-control" v-model="articleTag.name" placeholder="输入标签名称" >
						      		<span class="input-group-btn">
						      		<button class="btn btn-primary btn-xs" type="button" v-on:click="saveTag()">添加</button>
						      		<button class="btn btn-warning btn-xs" type="button" v-on:click="clearTag()">清空</button>
						      		</span>		
						    </div><!-- /input-group -->
						</div>
						<div class="col-xs-3 text-right">
							<#-- <button class="btn btn-warning btn-xs" type="button" v-on:click="queryTags()">选择已有标签</button> -->
						</div>
					</div>
					
					<hr/>
					
					<p>
						<span v-for="(atag,index) in articleTagList" class="label label-info" style="display: inline-block;font-size:13px;margin:2px 2px;">
							{{atag.tag.name}}
						<span aria-hidden="true" v-on:click="deleteTag(atag.id, atag.articleId, atag.tagId, index)">&times;</span>
						</span>		
					</p>
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第6步 设置标签 end -->

<#-- 第7步 预览及发布 begin -->
<div class="panel panel-warning" v-show="currentStep==7" id="div_step_7" name="div_step_7">
		  <div class="panel-heading">
		    	<h3 class="panel-title">第7步 预览及发布</h3>
		  </div>
		  <div class="panel-body">
					<h4>发布说明: 本站尊重原创，推重原创，力求给原创作者更多的展示机会，感谢您的分享。</h4> 
					<p class="text-left">文章链接：</p>
					<div class="container"><p class="text-justify" style="word-break: break-all;">${rc.appDomain}${articleBo.accessUrl}</p></div>
					<#-- 
					<input type="text" class="form-control" id="accessUrl" v-model="articleBo.accessUrl" placeholder="文章链接" readonly="true">
					 -->
					<blockquote>
					<p>
						<small><br/>
						1.当您设置了笔名，将优先于昵称显示在您的文章作者栏中<br/>
						2.文章发布时，您可以修改一次笔名，一旦进入审核流程，您将不能修改该篇所用笔名<br/>
						3.当您点击发布后，您将不能编辑该文章，发布前请仔细审查您的草稿<br/>
						4.您可以点击“预览”按钮，进行文章预览<br/>
						5.当审核驳回时，您可以再次编辑该文章<br/>
						</small>
					</p>
					</blockquote>
					  <div class="form-group">
					    <label for="penName">笔名</label> {{articleBo.penName}}
					    <input type="hidden" class="form-control" id="articleId" name="id"  v-model="articleBo.id">
					    <input type="text" class="form-control" id="penname" name="penname" v-model="articleBo.penname" placeholder="笔名">
					  	<small>您可以修改一次笔名作为本文专用，一旦审核通过，您将不能修改</small>
					  </div>
					  <div class="form-group"> 
					    <label for="subTitle">昵称</label> 
					    <input type="text" disabled class="form-control" id="nickName" v-model="articleBo.nickname" placeholder="您的昵称">
					  </div>
					  <p class="text-center">
					  		<button class="btn btn-primary" v-on:click="articlePreview(articleBo.id)">预览</button>
							<button class="btn btn-danger" v-on:click="mergeToPublish()">提交发布审核</button>
							
							<#-- 
							<a href="/activity/article/publish/${article.id}" class="btn btn-danger" id="id_step_preview_${article.id}">
						    <span class="glyphicon glyphicon-send"></span> 
						           <span>发布</span> 
						    </a>
						     -->
					  </p>
		  </div><!--panel body end-->
</div><!--panel end-->
<#-- 第7步 预览及发布 end -->

<!-- 文章预览模态框 begin -->
<!-- Modal -->
<@MV_articlePreview modalId=articleBo.id />

<!-- 文章预览模态框 end -->

</div><!-- body_content end-->

<div class="container" id="body_footer">

	<div class="panel panel-info">
		  <div class="panel-heading">
		    	<h3 class="panel-title">文章发布步骤，快速导航</h3>
		  </div>
		  <div class="panel-body">
	
			<ul class="list-group">
			  <li class="list-group-item">
			    <span class="badge">1</span>
			    <button type="button" class="btn btn-warning btn-xs" id="id_step_1_nav_${category.id}" v-on:click="changeStep(1,'${category.id}')">第1步：设置标题及选择分类</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">2</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_2_nav_${category.id}" v-on:click="changeStep(2,'${category.id}')">第2步：设置封面</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">3</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_3_nav_${category.id}" v-on:click="changeStep(3,'${category.id}')">第3步：设置横幅</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">4</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_4_nav_${category.id}" v-on:click="changeStep(4,'${category.id}')">第4步：编辑段落[正文]</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">5</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_5_nav_${category.id}" v-on:click="changeStep(5,'${category.id}')">第5步：设置路线</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">6</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_6_nav_${category.id}" v-on:click="changeStep(6,'${category.id}')">第6步：设置标签</button>
			  </li>
			  <li class="list-group-item">
			    <span class="badge">7</span>
			    <button type="button" class="btn btn-default btn-xs" id="id_step_7_nav_${category.id}" v-on:click="changeStep(7,'${category.id}')">第7步：预览发布</button>
			  </li>
			</ul>
		  </div>
	</div>
</div><!-- body_footer end -->

<div class="container" id="body_footer_js">
	<#include "/mobile/template/front/default/activity/article/body_footer_js_article_publish.ftl">
</div>
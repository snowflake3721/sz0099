<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#-- 图文横滚 -->
<#macro M_layout_headRoller ref=null dto=null idPre="ly_head" showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showLoadMore=false showPreNext=true showDot=true>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>
	
	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
	<div id="id_roller_${idPre}_${ref.id}" class="carousel slide" data-ride="carousel">
	    <#if showDot>
	    <ol class="carousel-indicators">
	    	<#if positionHas>
	           <#list positionList as positionEntity>
	           		<li data-target="#id_roller_${idPre}_${ref.id}"  data-slide-to="${positionEntity?index}" class="<#if positionEntity?index==0>active</#if>" id="image_roller_${idPre}_${ref.id}_${positionEntity.id}"></li>
	           </#list>
	         </#if>
	         <#if categoryHas>
	           <#list categoryList as categoryEntity>
	           		<li data-target="#id_roller_${idPre}_${ref.id}"  data-slide-to="${categoryEntity?index}" class="<#if categoryEntity?index==0>active</#if>" id="image_roller_${idPre}_${ref.id}_${categoryEntity.id}"></li>
	           </#list>
	         </#if>
	    </ol>
	    </#if>
	    <div class="carousel-inner" role="listbox">
	    <#if positionHas>
	          <#list positionList as positionEntity>
	          	<#assign coverImageUrl=positionEntity.coverImage/>
	          	<#assign coverList=positionEntity.coverList/>
	          	<#assign bannerList=positionEntity.bannerList/>
	          	<#if coverList!  && coverList?size &gt; 0 >
	          		<#assign coverImage=coverList?first/>
	          		<#assign coverImageUrl=PhotoUtil.getShowUrlForExpected2(coverImage.fullurl,720, coverImage.width)/>
	          	<#elseif bannerList!  && bannerList?size &gt; 0 >
	          		<#assign coverImage=bannerList?first/>
	          		<#assign coverImageUrl=PhotoUtil.getShowUrlForExpected2(coverImage.fullurl,720, coverImage.width)/>
	          	</#if>
	          	  <#if coverImageUrl! >
		          <div class="item <#if positionEntity?index==0>active</#if>" style="height:'180px'">
		            	<a href="${getLink(positionEntity, "/")}" target="_self">
		            	<img data-src="holder.js/240x180/auto/#777:#777" alt="${positionEntity.title}" height="180px" src="${coverImageUrl}" title="${positionEntity.title}" data-holder-rendered="true">
		          		<div class="carousel-caption">
		          			<#if withPreIntro>
				        	<p class="text-left"><span class="text-bg-black">${positionEntity.preIntro}</span></p>
				      		</#if>
		          			<#if withTitle>
				        	<h3>${positionEntity.title}</h3>
				      		</#if>
		          			<#if withSubTitle>
				        	<p class="text-right"><span class="text-bg-black"><small>${positionEntity.subTitle}</small></span></p>
				      		</#if>
				      	</div>
		          		</a>
		           </div>
		           </#if>
	          </#list>
	     </#if>
	     <#if categoryHas>
	          <#list categoryList as category>
	          <#assign categoryEntity=category.article/>
	          	<#assign coverImageUrl=categoryEntity.coverImage/>
	          	<#assign coverList=categoryEntity.coverList/>
	          	<#assign bannerList=categoryEntity.bannerList/>
	          	<#if coverList!  && coverList?size &gt; 0 >
	          		<#assign coverImage=coverList?first/>
	          		<#assign coverImageUrl=PhotoUtil.getShowUrlForExpected2(coverImage.fullurl,720, coverImage.width)/>
	          		<#assign coverList=categoryEntity.coverList/>
	          	<#elseif bannerList!  && bannerList?size &gt; 0 >
	          		<#assign coverImage=bannerList?first/>
	          		<#assign coverImageUrl=PhotoUtil.getShowUrlForExpected2(coverImage.fullurl,720, coverImage.width)/>
	          	</#if>
	          	<#if coverImageUrl! >
		          <div class="item <#if category?index==0>active</#if>" style="height:180px">
		            	<a href="${getLink(categoryEntity, "/")}" target="_self">
		            	<img data-src="holder.js/360x270/auto/#777:#777" alt="${categoryEntity.title}"  src="${coverImageUrl}" title="${categoryEntity.title}" data-holder-rendered="true">
		          		<div class="carousel-caption">
		          			<#if withPreIntro>
				        	<p class="text-left"><span class="text-bg-black">${categoryEntity.preIntro}</span></p>
				      		</#if>
		          			<#if withTitle>
				        	<h3><span class="text-bg-red">${categoryEntity.title}</span></h3>
				      		</#if>
		          			<#if withSubTitle>
				        	<p class="text-right"><span class="text-bg-black"><small>${categoryEntity.subTitle}</small></span></p>
				      		</#if>
				      	</div>
		          		</a>
		           </div>
		          </#if>
	          </#list>
	     </#if>
	     
	     <#if positionHas gt 0 && showLoadMore>
	      <p><span class="label label-success">往期推荐</span> <span class="label label-success">记实篇章</span></p>
	      </#if>
	    </div>
	    <#if showPreNext>
	    <a class="left carousel-control" href="#id_roller_${idPre}_${ref.id}" role="button" data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#id_roller_${idPre}_${ref.id}" role="button" data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	      <span class="sr-only">Next</span>
	    </a>
	    </#if>
	</div>
	<script>
	//carouselInit();
	carouselWrapperInit('id_roller_${idPre}_${ref.id}',2300);
	</script>
	</#if>
</#if>
</#macro>

<#macro M_layout_explorerRoller ref=null dto=null idPre="ly_explorer" showHeadTip=false withPreIntro=true withTitle=false withSubTitle=true showLoadMore=false showLoadMore=true showDot=false showPreNext=false showPenname=false linkable=true>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>
	
	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
	<p>====333====</p>
		<div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
				  <div class="panel-body" sytle="margin-bottom:0px">
				  <#if positionHas>
			          <#list positionList as positionEntity>
		    			<#assign isOddItem=positionEntity?is_odd_item />
		    			<#assign isEvenItem=positionEntity?is_even_item />
		    				<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || positionEntity?is_last>
							</div>
							</#if>
		  	  			 </#list>
	  	  			 </#if><#-- end positionHas -->
	  	  			 <#if categoryHas>
	  	  			 <p>===7777===</p>
			          <#list categoryList as categoryEntity>
		    			<#assign isOddItem=categoryEntity?is_odd_item />
		    			<#assign isEvenItem=categoryEntity?is_even_item />
		    				<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								  <p>---888---8----</p>
								    	<@M_layout_imgCoverRollerH_single entity0=categoryEntity.article idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || categoryEntity?is_last>
							</div>
							</#if>
		  	  			 </#list>
	  	  			 </#if><#-- end categoryHas -->
	  	  			 
	  	  			</div><#-- end body -->
		</div><!--end panel-->
		
	  	  <#if showListLoadMore>
	  	  <!-- more -->
				<div class="container">
				  	  <p class="text-right">
				  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-xs">more</a>
						  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
					  </p>
				</div>
		  </#if>
	</#if>
</#if>
</#macro>


<#macro M_layout_explorerWord2Img ref=null dto=null idPre="ly_explorer_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
		<#if positionHas>
			<#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-heading">
			  			<#assign author=positionEntity.author />
					  	<a href='javascript:void(0)' onclick="showSayword('${positionEntity.nickname}','${positionEntity.sayword.description}')">
						  <img id='id_tpl_media_headImg${positionEntity.id}' class='img-circle'  width='25px' height='25px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
						</a> 
					    <span class="pull-right">${DateUtils.formatToString(positionEntity.publishTime ,'yyyy-MM-dd HH:mm')}<strong> &nbsp;&nbsp;</strong></span>
					  </div>
					  <div class="panel-body" sytle="margin-bottom:0px">
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p>${getSubstring(positionEntity.description,60,'暂无')}</p>
								    
								  </div>
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							</div>
					  </div><#-- end body -->
				 </div>
		  	  </#list>
	  	  </#if>
	  	  <#if categoryHas>
			<#list categoryList as categoryEntity>
				<#assign isOddItem=categoryEntity?is_odd_item />
				<#assign isEvenItem=categoryEntity?is_even_item />
				<#assign entity0=categoryEntity.article />
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <#-- 
					  <div class="panel-heading">
			  			<#assign author=entity0.author />
					  	<a href='javascript:void(0)' onclick="showSayword('${entity0.nickname}','${entity0.sayword.description}')">
						  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
						</a> 
					  </div>
					   -->
					  <div class="panel-body" sytle="margin-bottom:0px">
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
								    <p>${getSubstring(entity0.description,60,'暂无')}</p>
								    <p>
								    <span class="pull-left">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
									</a> 
									</span>
									<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${entity0.preIntro}</span><br/> ${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							</div>
					  </div><#-- end body -->
				 </div>
		  	  </#list>
	  	  </#if>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro M_layout_rememberScreen ref=null dto=null idPre="ly_remember" showHeadTip=false>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip && (positionHas || categoryHas)>
	<div class="text-center"><span class="label label-danger">${Position.PANEL.getLabel(ref.ponPanel)}</span></div>
	</#if>
	<#if positionHas>
		<#assign positionEntity=positionList?first />
		<@M_layout_jumbotron_single entity0=positionEntity idPre=idPre+"positoin" tip="独家记忆" tipShowable=false btnLabel="进入>>>"  withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true/>
	</#if>
	<#if categoryHas>
	<#assign categoryEntity=categoryList?first />
		<@M_layout_jumbotron_single entity0=categoryEntity.article idPre=idPre+"category" tip="独家记忆" tipShowable=false btnLabel="进入>>>"  withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true/>
	</#if>
</#if>
</#macro>

<#macro M_layout_farmstayRollerMulti ref=null dto=null idPre="ly_farmstay" showHeadTip=false>
<@M_layout_eatRollerMulti ref=ref dto=dto idPre=idPre showHeadTip=showHeadTip/>
</#macro>
<#macro M_layout_outdoorRollerMulti ref=null dto=null idPre="ly_farmstay" showHeadTip=false>
<@M_layout_eatRollerMulti ref=ref dto=dto idPre=idPre showHeadTip=showHeadTip/>
</#macro>

<#macro M_layout_eatRollerMulti ref=null dto=null idPre="ly_eat" showHeadTip=false showDot=true withPreIntro=true withTitle=true withSubTitle=true showPenname=true showPreNext=false showListLoadMore=true showLoadMore=false mainCircle=true>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip && (positionHas || categoryHas)>
	<div class="text-center"><span class="label label-${CssClazzUtils.getClazzByRandom()}">${Position.PANEL.getLabel(ref.ponPanel)}</span></div>
	</#if>
	
	<#if positionHas || categoryHas>
		<div id="id_roller_${idPre}_${positon.id}" class="carousel slide" data-ride="carousel">
	    <#if showDot>
	    <ol class="carousel-indicators">
	    	<#assign slideIndex=0/>
		    <#if positionHas>
			    <#list positionList as positionEntity>
			            <li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${slideIndex}" class="<#if slideIndex==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${positionEntity.id}"></li>
			            <#assign slideIndex=slideIndex+1 />
			    </#list>
		    </#if>
		    <#if categoryHas>
			    <#list categoryList as categoryEntity>
			           	<li data-target="#id_roller_${idPre}_${positon.id}"  data-slide-to="${slideIndex}" class="<#if slideIndex==0>active</#if>" id="image_roller_${idPre}_${positon.id}_${categoryEntity.id}"></li>
			            <#assign slideIndex=slideIndex+1 />
			    </#list>
		    </#if>
	    </ol>
	    </#if>
	    <div class="carousel-inner" role="listbox">
	    
	    <#assign slideIndex2=0/>
	    <#if positionHas>
	          <#list positionList as positionEntity>
	          	  <#if positionEntity! >
		          <div class="item <#if slideIndex2==0>active</#if>">
		            	<div class="panel panel-default">
						<#if showPenname>
							  <div class="container">
							  <img id='id_tpl_media_headImg${positionEntity.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${positionEntity.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
							   <span class="pull-right">--- ${positionEntity.penname}&nbsp;&nbsp;</span>
							  </div>
						</#if>
						<div class="container">
										<#assign bannerList=positionEntity.bannerList />
										
										<#if withTitle>
							        	<p class="text-center"><a href="${getLink(positionEntity, "/")}" target="_self"><strong>${positionEntity.title}</strong></a></p>
							      		</#if>
							  	
										<#if bannerList! && bannerList?size gt 0 >
										 	<div class="row">
										 	<#list bannerList as image>
												<div class="col-xs-4 col-md-4">
												<a href='javascript:void(0)' class="thumbnail" onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
												  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${image.expectedUrl}' alt='${image.id}'>
											       </img>
												</a> 
												</div>
											</#list>
											</div>
										</#if>
							  			<#if withPreIntro>
							        	<p class="text-left"><small>${positionEntity.preIntro}</small></p>
							      		</#if>
							  			
							  			<#if withSubTitle>
							        	<p class="text-right"><small>${positionEntity.subTitle}</small></p>
							      		</#if>
				          </div><!--end container-->
		          		</div><!--end panel-->
		          </div><!--end item-->
		          </#if>
		          <#assign slideIndex2=slideIndex2+1/>
	          </#list>
	      </#if>
	      
	      <#if categoryHas>
	          <#list categoryList as categoryEntity>
	          	  <#if categoryEntity! >
	          	  <#assign entity0=categoryEntity.article />
		          <div class="item <#if slideIndex2==0>active</#if>">
		            	<div class="panel panel-default">
		            	<#if showPenname>
							  <div class="container">
							  <span class="pull-left">
							  <strong> 
							  <#if entity0.articleTagList!?size gt 0 > 
							  	<@M_showTagListRandom productTagList=entity0.articleTagList fontSize=12 randomSize=false withWrapper=false/>
							  	</#if>
			  					</strong>
			  					</span>
							  <img id='id_tpl_media_headImg${entity0.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/article/findByUserId','${entity0.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
							   <span class="pull-right">--- ${entity0.penname}&nbsp;&nbsp;</span>
							  </div>
						</#if>
						<div class="container">
								<#assign bannerList=entity0.bannerList />
								
								<#if withTitle>
					        	<p class="text-center"><a href="${getLink(entity0, "/")}" target="_self"><strong>${entity0.title}</strong></a></p>
					      		</#if>
					  	
								<#if bannerList! && bannerList?size gt 0 >
								 	<div class="row">
								 	<#list bannerList as image>
										<div class="col-xs-4 col-md-4">
										<a href='javascript:void(0)' class="thumbnail" onclick="showBigView('${image.fullurl}',720, ${image.width},'${image.id}')">
										  <img id='id_tpl_media_common_img${image.id}'  height='108px' src='${image.expectedUrl}' alt='${image.id}'>
									       </img>
										</a> 
										</div>
									</#list>
									</div>
								</#if>
					  			<#if withPreIntro>
					        	<p class="pull-left">
					        	<small>${entity0.preIntro}</small>
					        	</p>
					      		</#if>
					  			
					  			<#if withSubTitle>
					        	<p class="pull-right"><small>${entity0.subTitle}</small></p>
					      		</#if>
				          </div><!--end container-->
		          		</div><!--end panel-->
		          </div><!--end item-->
		          </#if>
	          </#list>
	      </#if>
	          
          <#if totalPages gt 0 && showLoadMore>
          <p>load more</p>
          </#if>
		  </div>
		    <#if showPreNext>
		    <a class="left carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#id_roller_${idPre}_${positon.id}" role="button" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		    </#if>
		</div>
		<script>
		$(document).ready(function(){
		carouselWrapperInit('id_roller_${idPre}_${positon.id}',2000);
		});
		</script>			
						
	  	  <#if showListLoadMore>
	  	  <!-- more -->
				<div class="container">
				  	  <p class="text-right">
				  	  	  <a href="${url}?page=0&size=0" class="btn btn-warning btn-xs">more</a>
						  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
					  </p>
				</div>
		  </#if>
	</#if><#-- end if has -->
	
</#if>
</#macro>


<#macro M_layout_equipRealImg2Word ref=null dto=null idPre="ly_equipreal_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
		<#if positionHas>
			<#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p>${getSubstring(positionEntity.description,60,'暂无')}</p>
								    <p>
								    <span class="pull-left">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${positionEntity.nickname}','${positionEntity.sayword.description}', '@${DateUtils.formatToString(positionEntity.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${positionEntity.id}' class='img-circle'  width='25px' height='25px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
									</a> 
									</span>
									<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${positionEntity.preIntro}</span><br/> ${DateUtils.formatToString(positionEntity.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
					  </div><#-- end body -->
				 </div>
		  	  </#list>
	  	  </#if>
	  	  <#if categoryHas>
			<#list categoryList as categoryEntity>
				<#assign isOddItem=categoryEntity?is_odd_item />
				<#assign isEvenItem=categoryEntity?is_even_item />
				<#assign entity0=categoryEntity.article />
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
						  	<div class="row">
								  
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
								    <p>${getSubstring(entity0.description,60,'暂无')}</p>
								    <p class="text-center">
								    <span class="pull-right">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
									</a> 
									</span>
									<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${entity0.preIntro}</span><br/> @${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
					  </div><#-- end body -->
				 </div>
		  	  </#list>
	  	  </#if>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro M_layout_actionFlagImg2Img ref=null dto=null idPre="ly_actionFlag_list" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
		<#if positionHas>
			<#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
						  	<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || positionEntity?is_last>
							</div>
							</#if>
		  	  </#list>
	  	  </#if>
	  	  <#if categoryHas>
			<#list categoryList as categoryEntity>
				<#assign isOddItem=categoryEntity?is_odd_item />
				<#assign isEvenItem=categoryEntity?is_even_item />
				<#assign entity0=categoryEntity.article />
						  	<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single2 entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || categoryEntity?is_last>
							</div>
							</#if>
		  	  </#list>
	  	  </#if>
					  </div><#-- end body -->
				 </div>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro M_layout_actionFlagImg2Word ref=null dto=null idPre="ly_actionFlag_list2" showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign positionHas=false/>
	<#if (positionPage! && positionPage.totalElements gt 0) >
	<#assign positionList=positionPage.content />
	<#assign postionTotalPages=positionPage.totalPages />
	<#assign positionHas=true/>
	</#if>
	<#assign categoryHas=false/>
	<#if (categoryPage! && categoryPage.totalElements gt 0) >
	<#assign categoryList=categoryPage.content />
	<#assign categoryTotalPages=categoryPage.totalPages />
	<#assign categoryHas=true/>
	</#if>

	<#if ref?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(ref.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
		<#if positionHas>
			<#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p>${getSubstring(positionEntity.description,60,'暂无')}</p>
								    <p>
								    <span class="pull-left">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${positionEntity.nickname}','${positionEntity.sayword.description}', '@${DateUtils.formatToString(positionEntity.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${positionEntity.id}' class='img-circle'  width='25px' height='25px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
									</a> 
									</span>
									<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${positionEntity.preIntro}</span><br/> ${DateUtils.formatToString(positionEntity.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
		  	  </#list>
	  	  </#if>
	  	  <#if categoryHas>
			<#list categoryList as categoryEntity>
				<#assign isOddItem=categoryEntity?is_odd_item />
				<#assign isEvenItem=categoryEntity?is_even_item />
				<#assign entity0=categoryEntity.article />
						  	<div class="row">
								  
								  <div class="col-xs-6 col-md-4">
								    	<@M_layout_imgCoverRollerH_single2 entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.articleNo}-${entity0.title}</a></strong></h5>
								    <p>${getSubstring(entity0.description,60,'暂无')}</p>
								    <p class="text-center">
								    <span class="pull-right">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
									</a> 
									</span>
									<small><span class="label label-${CssClazzUtils.getClazzByRandom()}">${entity0.preIntro}</span><br/> @${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
		  	  </#list>
	  	  </#if>
					  </div><#-- end body -->
				 </div>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	  <p class="text-right">
			  	  	  <a href="${url}?page=${entityPage.number+1}&size=${entityPage.size}" class="btn btn-warning btn-sm">more</a>
					  <#-- <button type="button" class="btn btn-warning btn-sm text-right">下一组...</button> -->
				  </p>
			</div>
	  </#if>
	 </#if>
 </#if>
</#macro>
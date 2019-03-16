<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_coe_profession_category_search.ftl">
<#-- 图文横滚 -->
<#macro Mg_layout_headRoller ref=null dto=null showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showListLoadMore=false showPreNext=true showDot=true btnLabel="更多专业技能" idPre="ly_head">
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
	<#assign entityIndex1=0/>
	<#assign entityIndex2=0/>
	<#assign showActive1=0/>
	<#assign showActive2=0/>
	<div id="id_roller_${idPre}_${ref.id}" class="carousel slide" data-ride="carousel">
	    <#if showDot>
	    <ol class="carousel-indicators">
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
	           			  <li data-target="#id_roller_${idPre}_${ref.id}"  data-slide-to="${entityIndex1}" class="<#if showActive1==0>active</#if>" id="image_roller_${idPre}_${ref.id}_${positionEntity.id}"></li>
	           			  	<#if showActive1==0>
			          	  	<#assign showActive1=showActive1+1/>
			          	    </#if>
	           			</#if>
	           		<#assign entityIndex1=entityIndex1+1/>
	           </#list>
	         </#if>
	         <#if categoryHas>
	           <#list categoryList as category>
	           
	           		<#assign categoryEntity=category.profession/>
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
	           			<li data-target="#id_roller_${idPre}_${ref.id}"  data-slide-to="${entityIndex1}" class="<#if showActive1==0>active</#if>" id="image_roller_${idPre}_${ref.id}_${categoryEntity.id}"></li>
	           			<#if showActive1==0>
		          	  	<#assign showActive1=showActive1+1/>
		          	    </#if>
	           		</#if>
	           		<#assign entityIndex1=entityIndex1+1/>
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
		          <div class="item <#if showActive2==0>active</#if>" style="height:210px">
		          		  <#if showActive2==0>
			          	  	<#assign showActive2=showActive2+1/>
			          	  </#if>
		            	<a href="${getLink(positionEntity, "/")}" target="_self">
		            	<img data-src="holder.js/280x210/auto/#777:#777" alt="${positionEntity.title}" height="210px" src="${coverImageUrl}" title="${positionEntity.title}" data-holder-rendered="true">
		          		<div class="carousel-caption">
		          			<#if withPreIntro && positionEntity.preIntro! && CoeArticle.PREINTRO_TYPE_NO.valueInt!=positionEntity.preIntroType>
				        	<p class="text-left"><span class="text-bg-black">${positionEntity.preIntro}</span></p>
				      		</#if>
		          			<#if withTitle && positionEntity.title!>
				        	<h3>${positionEntity.title}</h3>
				      		</#if>
		          			<#if withSubTitle && positionEntity.subTitle!>
				        	<p class="text-right"><span class="text-bg-black"><small>${positionEntity.subTitle}</small></span></p>
				      		</#if>
				      	</div>
		          		</a>
		           </div>
		           </#if>
		           <#assign showActive=showActive+1/>
	          </#list>
	     </#if>
	     <#if categoryHas>
	          <#list categoryList as category>
	          	<#assign categoryEntity=category.profession/>
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
		          <div class="item <#if showActive2==0>active</#if>" style="height:210px">
		          			<#if showActive2==0>
			          	  	<#assign showActive2=showActive2+1/>
			          	    </#if>
		            	<a href="${getLink(categoryEntity, "/")}" target="_self">
		            	<img data-src="holder.js/280x210/auto/#777:#777" alt="${categoryEntity.title}" height="210px"  src="${coverImageUrl}" title="${categoryEntity.title}" data-holder-rendered="true">
		          		<div class="carousel-caption">
		          			<#if withPreIntro && categoryEntity.preIntro! && CoeArticle.PREINTRO_TYPE_NO.valueInt!=categoryEntity.preIntroType>
				        	<p class="text-left"><span class="text-bg-black">${categoryEntity.preIntro}</span></p>
				      		</#if>
		          			<#if withTitle && categoryEntity.title!>
				        	<h3><span class="text-bg-red">${categoryEntity.title}</span></h3>
				      		</#if>
		          			<#if withSubTitle && categoryEntity.subTitle!>
				        	<p class="text-right"><span class="text-bg-black"><small>${categoryEntity.subTitle}</small></span></p>
				      		</#if>
				      	</div>
		          		</a>
		           </div>
		          </#if>
		          <#assign entityIndex2=entityIndex2+1/>
	          </#list>
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
	<#if showListLoadMore>
     <@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
     </#if>
	</#if>
</#if>
</#macro>

<#macro Mg_layout_explorerRoller ref=null dto=null showHeadTip=false withPreIntro=true withTitle=false withSubTitle=true showLoadMore=false showLoadMore=true showDot=false showPreNext=false showPenname=false linkable=true btnLabel="探路行动" idPre="ly_explorer_">
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
								    	<@Mg_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || positionEntity?is_last>
							</div>
							</#if>
		  	  			 </#list>
	  	  			 </#if><#-- end positionHas -->
	  	  			 <#if categoryHas>
			          <#list categoryList as categoryEntity>
		    			<#assign isOddItem=categoryEntity?is_odd_item />
		    			<#assign isEvenItem=categoryEntity?is_even_item />
		    				<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								  <p>---888---8----</p>
								    	<@Mg_layout_imgCoverRollerH_single entity0=categoryEntity.profession idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							<#if isEvenItem || categoryEntity?is_last>
							</div>
							</#if>
		  	  			 </#list>
	  	  			 </#if><#-- end categoryHas -->
	  	  			 
	  	  			</div><#-- end body -->
		</div><!--end panel-->
		
	  	  <#if showListLoadMore>
	  	  <@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
		  </#if>
	</#if>
</#if>
</#macro>


<#macro Mg_layout_outdoorWord2Img dto=null showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false btnLabel="户外专享" idPre="ly_outdoor_">
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign ref=dto.position />
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
								    <p><small>${getSubstring(positionEntity.description,24,'<br/>')}</small></p>
								    
								  </div>
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
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
				<#assign entity0=categoryEntity.profession />
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
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
								    <p><small>${getSubstring(entity0.description,24,'<br/>')}</small></p>
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
								    	<@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
							</div>
					  </div><#-- end body -->
				 </div>
		  	  </#list>
	  	  </#if>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel  idPre=idPre/>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro Mg_layout_todayScreen ref=null dto=null showHeadTip=false showListLoadMore=false btnLabel="往日神技..." screenBtnLabel="观览★★★" idPre="ly_today">
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

	<#if (positionHas || categoryHas)>
		<#if ref?? && showHeadTip>
		<div class="text-center"><span class="label label-danger">${Position.PANEL.getLabel(ref.ponPanel)}</span></div>
		</#if>
		<#if positionHas>
			<#assign positionEntity=positionList?first />
			<@M_layout_jumbotron_single entity0=positionEntity idPre=idPre+"positoin" tip="今日神技" tipShowable=false btnLabel=screenBtnLabel  withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true/>
		</#if>
		<#if categoryHas>
		<#assign categoryEntity=categoryList?first />
			<@M_layout_jumbotron_single entity0=categoryEntity.profession idPre=idPre+"category" tip="今日神技" tipShowable=false btnLabel=screenBtnLabel  withPreIntro=true withTitle=true withSubTitle=true showPreNext=false showPenname=false linkable=true/>
		</#if>
		<#if showListLoadMore >
		<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
		</#if>
	</#if>
</#if>
</#macro>

<#macro Mg_layout_farmstayRollerMulti ref=null dto=null showHeadTip=false showListLoadMore=false btnLabel="吃的世界" idPre="ly_farmstay">
<@M_layout_eatRollerMulti ref=ref dto=dto idPre=idPre showHeadTip=showHeadTip showListLoadMore=showListLoadMore btnLabel=btnLabel idPre=idPre/>
</#macro>
<#macro Mg_layout_outdoorRollerMulti ref=null dto=null showHeadTip=false showListLoadMore=false btnLabel="野餐乐趣" idPre="ly_outdoor">
<@M_layout_eatRollerMulti ref=ref dto=dto idPre=idPre showHeadTip=showHeadTip showListLoadMore=showListLoadMore btnLabel=btnLabel idPre=idPre/>
</#macro>

<#macro Mg_layout_eatRollerMulti ref=null dto=null showHeadTip=false showDot=true withPreIntro=true withTitle=true withSubTitle=true showPenname=true showPreNext=false showListLoadMore=true showLoadMore=false mainCircle=true btnLabel="吃的世界" idPre="ly_eat">
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
	<#if (positionHas || categoryHas)>
		<#if ref?? && showHeadTip >
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
								  <img id='id_tpl_media_headImg${positionEntity.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/profession/findByUserId','${positionEntity.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
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
		          	  <#assign entity0=categoryEntity.profession />
			          <div class="item <#if slideIndex2==0>active</#if>">
			            	<div class="panel panel-default">
			            	<#if showPenname>
								  <div class="container">
								  <span class="pull-left">
								  <strong> 
								  <#if entity0.professionTagList!?size gt 0 > 
								  	<@M_showTagListRandom productTagList=entity0.professionTagList idPre="tag_list_" entity0=entity0 fontSize=12 randomSize=false withWrapper=false/>
								  	</#if>
				  					</strong>
				  					</span>
								  <img id='id_tpl_media_headImg${entity0.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/profession/findByUserId','${entity0.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
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
			<#-- 
			<script>
			$(document).ready(function(){
			carouselWrapperInit('id_roller_${idPre}_${positon.id}',2000);
			});
			</script>			
					 -->		
		  	  <#if showListLoadMore>
		  	  <!-- more -->
				<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
			  </#if>
		</#if><#-- end if has -->
	</#if>
</#if>
</#macro>

<#macro Mg_layout_recommendImg3Multi dto=null showHeadTip=false withPreIntro=true withTitle=true withSubTitle=true showPenname=true showListLoadMore=true mainCircle=true btnLabel="技能推荐" idPre="ly_recommend">
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign ref=dto.position/>
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
		<div id="id_roller_${idPre}_${positon.id}">
	    <#assign slideIndex2=0/>
	    <#if positionHas>
	          <#list positionList as positionEntity>
	          	  <#if positionEntity! >
		            	<div class="panel panel-default">
						<#if showPenname>
							  <div class="container">
							   <span class="pull-left bg-info">推</span>
							  <img id='id_tpl_media_headImg${positionEntity.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/profession/findByUserId','${positionEntity.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
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
		          </#if>
		          <#assign slideIndex2=slideIndex2+1/>
	          </#list>
	      </#if>
	      
	      <#if categoryHas>
	          <#list categoryList as categoryEntity>
	          	  <#if categoryEntity! >
	          	  <#assign entity0=categoryEntity.profession />
		            	<div class="panel panel-default">
		            	<#if showPenname>
							  <div class="container">
							  <span class="pull-left">
							  <span class="bg-info">推广</span>
							  <strong> 
							  <#if entity0.professionTagList!?size gt 0 > 
							  	<@M_showTagListRandom productTagList=entity0.professionTagList idPre="tag_list_" entity0=entity0 fontSize=12 randomSize=false withWrapper=false/>
							  	</#if>
			  					</strong>
			  					</span>
							  <img id='id_tpl_media_headImg${entity0.id}' <#if mainCircle>onclick="changeBloomingForJson('/sz0099/ood/profession/findByUserId','${entity0.userId}','id_current_userId')"</#if> class='img-circle pull-right'  width='20px' height='20px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
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
		          </#if>
	          </#list>
	      </#if>
	          
		</div>
						
	  	  <#if showListLoadMore>
	  	  <!-- more -->
			<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
		  </#if>
	</#if><#-- end if has -->
	
</#if>
</#macro>


<#macro Mg_layout_killImg2Word dto=null showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false btnLabel="大侠必杀" idPre="ly_kill">
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign ref=dto.position>
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
								    	<@Mg_layout_imgCoverRollerH_single entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p><small>${getSubstring(positionEntity.description,24,'<br/>')}</small></p>
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
				<#assign entity0=categoryEntity.profession />
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
						  	<div class="row">
								  
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
								    <p><small>${getSubstring(entity0.description,24,'<br/>')}</small></p>
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
		<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro Mg_layout_artisanImg2Img dto=null showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false btnLabel="能工巧匠" idPre="ly_artisan">
<#if dto!>
	<#assign positionPage=dto.positionPage/>
	<#assign categoryPage=dto.categoryPage/>
	<#assign ref=dto.position>
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
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
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
				<#assign entity0=categoryEntity.profession />
						  	<#if isOddItem>
						  	<div class="row">
						  	</#if>
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
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
			<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
	  </#if>
	 </#if>
 </#if>
</#macro>

<#macro Mg_layout_actionFlagImg2Word ref=null dto=null showHeadTip=false url="#" showListLoadMore=false withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false btnLabel="插旗行动回顾" idPre="ly_act_Flag2">
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
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p><small>${getSubstring(positionEntity.description,24,'<br/>')}</small></p>
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
				<#assign entity0=categoryEntity.profession />
						  	<div class="row">
								  
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
								    <p><small>${getSubstring(entity0.description,24,'<br/>')}</small></p>
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
			<@M_professionSearchHiddenInHomeDto dto=dto url="/sz0099/ood/home/profession/searchForCategoryFromHome" btnLabel=btnLabel idPre=idPre/>
	  </#if>
	 </#if>
 </#if>
</#macro>


<#macro Mg_layout_searchImg2Word dto=null idPre="ly_search" showHeadTip=false url="#" showListLoadMore=true withPreIntro=false withTitle=false withSubTitle=false showLoadMore=false showDot=false showPreNext=false showPenname=false linkable=false>
<#if dto!>
	<#assign positionRef=dto.position/>
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

	<#if positionRef?? && showHeadTip  && (positionHas || categoryHas)>
	<div>${Position.PANEL.getLabel(positionRef.panel)}</div>
	</#if>
	
	<#if positionHas || categoryHas>
				  <div class="panel panel-default" sytle="margin-bottom:0px;padding-bottom:0px">
					  <div class="panel-body" sytle="margin-bottom:0px">
		<#assign positionSize=0/>
		<#if positionHas>
		<#-- 推荐类，上面留三个 -->
			<#assign positionSize=positionList?size />
			<#assign splitIndex=getSplitIndexForList(positionList,3)/>
			
			<#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
				
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p><small>${getSubstring(positionEntity.description,24,'<br/>')}</small></p>
								    <p>
								    <span class="pull-left">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${positionEntity.nickname}','${positionEntity.sayword.description}', '@${DateUtils.formatToString(positionEntity.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${positionEntity.id}' class='img-circle'  width='25px' height='25px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
									</a> 
									</span>
									<small>
									<#if withPreIntro && positionEntity.preIntro! && Profession.PREINTRO_TYPE_NO.valueInt!=positionEntity.preIntroType>
									<span class="label label-${CssClazzUtils.getClazzByRandom()}">${positionEntity.preIntro}</span>
									</#if>
									<br/> ${DateUtils.formatToString(positionEntity.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
							<hr/>
							<#if positionEntity?counter==splitIndex>
							<#break/>
							</#if>
		  	  </#list>
	  	  </#if>
	  	  <#if categoryHas>
			<#list categoryList as categoryEntity>
				<#assign isOddItem=categoryEntity?is_odd_item />
				<#assign isEvenItem=categoryEntity?is_even_item />
				<#assign entity0=categoryEntity.profession />
						  	<div class="row">
								  
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=entity0 idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								  	
								    <h5><strong><a type="button" class="text-left" href="${getLink(entity0, '/')}">${entity0.professionNo}-${entity0.title}</a></strong></h5>
								    <p><small>${getSubstring(entity0.description,24,'<br/>')}</small></p>
								    <p class="text-center">
								    <span class="pull-right">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${entity0.nickname}','${entity0.sayword.description}', '@${DateUtils.formatToString(entity0.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${entity0.id}' class='img-circle'  width='25px' height='25px' src='${entity0.headImg}' alt='${entity0.nickname}' title='${entity0.nickname}'>
									</a> 
									</span>
									<small>
									<#if withPreIntro && entity0.preIntro! && CoeArticle.PREINTRO_TYPE_NO.valueInt!=entity0.preIntroType>
									<span class="label label-${CssClazzUtils.getClazzByRandom()}">${entity0.preIntro}</span>
									</#if>
									<br/> @${DateUtils.formatToString(entity0.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
							<hr/>
		  	  </#list>
	  	  </#if>
	  	  
	  	  <#-- 推荐类，下面留三个 -->
	  	  <#if positionSize ==3 >
	  	  <#list positionList as positionEntity>
				<#assign isOddItem=positionEntity?is_odd_item />
				<#assign isEvenItem=positionEntity?is_even_item />
				<#if positionEntity?counter gt positionSize>
						  	<div class="row">
								  <div class="col-xs-6 col-md-4">
								    	<@Mg_layout_imgCoverRollerH_single2 entity0=positionEntity idPre=idPre withPreIntro=withPreIntro withTitle=withTitle withSubTitle=withSubTitle showLoadMore=showLoadMore showDot=showDot showPreNext=showPreNext showPenname=showPenname linkable=linkable/>
								  </div>
								  <div class="col-xs-6 col-md-4">
								    <h5><strong><a type="button" class="text-left" href="${getLink(positionEntity, '/')}">${positionEntity.mainNo}-${positionEntity.title}</a></strong></h5>
								    <p><small>${getSubstring(positionEntity.description,24,'<br/>')}</small></p>
								    <p>
								    <span class="pull-left">
								    <a href='javascript:void(0)' onclick="showSaywordHistory('${positionEntity.nickname}','${positionEntity.sayword.description}', '@${DateUtils.formatToString(positionEntity.refreshTime ,'yyyy-MM-dd')}')">
									  <img id='id_tpl_media_headImg${positionEntity.id}' class='img-circle'  width='25px' height='25px' src='${positionEntity.headImg}' alt='${positionEntity.nickname}' title='${positionEntity.nickname}'>
									</a> 
									</span>
									<small>
									<#if withPreIntro && positionEntity.preIntro! && Profession.PREINTRO_TYPE_NO.valueInt!=positionEntity.preIntroType>
									<span class="label label-${CssClazzUtils.getClazzByRandom()}">${positionEntity.preIntro}</span>
									</#if>
									<br/> ${DateUtils.formatToString(positionEntity.publishTime ,'yyyy-MM-dd HH:mm')}</small>
									</p>
								  </div>
							</div>
							<hr/>
				</#if>
		  </#list>
	  	  </#if>
	  	  
					  </div><#-- end body -->
				 </div>
  	  <#if showListLoadMore>
  	  <!-- more -->
			<div class="container">
			  	 <ul id="${idPre}pageContainer_${positionRef.baseId}"></ul>
			</div>
	  </#if>
	 </#if>
 </#if>
</#macro>
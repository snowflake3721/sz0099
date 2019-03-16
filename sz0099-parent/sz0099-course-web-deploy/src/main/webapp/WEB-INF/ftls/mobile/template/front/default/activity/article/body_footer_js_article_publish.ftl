<script type="text/javascript">
	<!--
	$(document).ready(function(){
		var commonUploadUrl="/activity/article/uploadPhotoItem";
		var commonDeleteUrl="/activity/article/deletePhotoItem";
	
		var articlePublishPage = new Vue({
		  el: '#app',
		  data: {
		  	id:'${articleBo.id}',
		  	btnId:'${articleBo.categoryId}',
		    selectedCategoryId:'${articleBo.categoryId}',
		    selectedCategoryName:'请选择',
		    isSelected:false,
		    btnClazzCategoryDefault:"btn btn-default",
		    btnClazzCategorySelected:"btn btn-warning",
		    articleBo:{id:'${articleBo.id}', preIntro:'${articleBo.preIntro}', title:'${articleBo.title}',subTitle:'${articleBo.subTitle}',description:'${articleBo.description}',categoryId:'${articleBo.categoryId}', accessUrl:'${articleBo.accessUrl}','nickName':'${articleBo.nickName}', 'penName':'${articleBo.penName}'},
		    lastStep:1,
		    currentStep:1,
		    errorMessage:'',
		    successMessage:'',
		    coverImages:${GsonBuilderUtils.toJsonOmitnull(articleBo.coverFileJson.imagesArticle)},
		    bannerImages:${GsonBuilderUtils.toJsonOmitnull(articleBo.bannerFileJson.imagesArticle)},
		    roadPoint:{id:'', name:'', orderSeq:''},
		    roadLine: {id:'',name:'', roadPoints:[], articleId:'${articleBo.categoryId}', articleCategoryId:'${articleBo.categoryId}'},
		    articleTag:{'id':'', 'articleId':'', 'tagId':'', 'name' : ''},
		    articleTagList:[],
		    articleItemList:[],
		    articleItem:{},
		    articleItemListChanged:false,
		    articleItemIconShow:false,
		    articleView:{id:'${articleBo.id}', bannerFileJson:{imagesArticle:[]},coverFileJson:{imagesArticle:[]}, title:'', articlePhotoItem:{},articleTagList:[]},
		    articlePhotoBo:{}
		    
		  },
		  computed:{
		  		showable : function(){
		  		
		  			return this.isSelected?this.btnClazzCategorySelected:this.btnClazzCategoryDefault;
		  		}
		  		
		  },
		  methods:{
			  asignCategory(id){
			  		this.btnId=this.selectedCategoryId;
			  		var currentCategoryBtn=$("#id_btn_category_"+id);
			  		this.selectedCategoryId=id;
			  		this.selectedCategoryName=currentCategoryBtn.html();
			  		this.changeBtnClazzCategory(id);
			  		console.log(this.btnId+"<--->"+this.selectedCategoryId + ":" + this.selectedCategoryName);
			  		//调用异步更新
			  		 var self=this;
			  		 
			  		 var url="/activity/article/changeCategory";
			  		 
			  		 if(this.btnId != id){
				    	 //异步更新分类数据
				    	 this.$http({
								  method: 'post',
								  url: url,
								  data: Qs.stringify({
								  	'id':self.id,
								    'categoryId': id
								  })
				 			}).then(function(response){
				 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_CHANGE_CATEGORY_SUCCESS}';
				 					if(successCode==response.data.respCode){
										console.log(successCode + ": " + id);			 					
				 					}
						    
				  			});//end http
			  		}
			  		
			  },
			  changeBtnClazzCategory(id){
			  		if(this.btnId != ''){
			  			$("#id_btn_category_"+this.btnId).removeClass("btn-warning");
			  			$("#id_btn_category_"+this.btnId).addClass("btn-default");
			  		}
			  
			  		$("#id_btn_category_"+id).removeClass("btn-default");
			  		$("#id_btn_category_"+id).addClass("btn-warning");
			  		
			  },
			  changeStep(stepNum,id){
			  	 this.errorMessage='';
			  	 this.successMessage='';
			  	 
			  	 this.lastStep=this.currentStep;
			  	 this.currentStep=stepNum;
			  	 
			  	 var currentStepBtn=$("#id_step_"+this.currentStep+"_nav_"+id);
			  	 if(this.lastStep != this.currentStep){
				  	 $("#id_step_"+this.lastStep+"_nav_"+id).removeClass("btn-warning");
				  	 $("#id_step_"+this.lastStep+"_nav_"+id).addClass("btn-default");
				  	 $("#id_step_"+this.currentStep+"_nav_"+id).removeClass("btn-default");
				  	 $("#id_step_"+this.currentStep+"_nav_"+id).addClass("btn-warning");
			  	 }
			  	 console.log(currentStepBtn.html());
			  	 $("#id_span_nav").html(currentStepBtn.html());
			  	 if(stepNum==5){
			  	 	if(this.roadLine.id==''){
			  	 		this.queryRoadLine();
			  	 	}
			  	 } else if(stepNum==6){
			  	 	if(this.articleTagList.length<1){
			  	 		this.queryTags();
			  	 	}
			  	 } else if(stepNum==4){
			  	 	if(this.articleItemList.length<1){
			  	 		this.queryItemListByArticleId();
			  	 	}
			  	 } 
			  	 
			  },
			  initCategory(){
			  	this.selectedCategoryId='${articleBo.categoryId}';
			  	this.asignCategory(this.selectedCategoryId);
			  },
			  refreshImagePreviewInner(articleId,position){
			  		refreshImagePreview(articleId,position);
			  },
			  deletePhotoItem(articleId, photoId, photoItemId, position){
			  	 this.errorMessage='';
			  	 this.successMessage='';
			  	var self=this;
			  	//异步删除图片
		    	 this.$http({
						  method: 'post',
						  url: commonDeleteUrl,
						  data: Qs.stringify({
						  	'id':self.articleBo.id,
						  	'articlePhotoItemId':photoItemId,
						    'articlePhotoId': photoId,
						    'position':position
						  })
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_ITEM_DELETED_SUCCESS}';
		 					var refreshSuccessCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_REFRESH_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode || refreshSuccessCode==respCode){
								console.log(successCode + ": " + self.articleBo.id + ":photoId>>" + photoId);	
								if(position=='${ArticlePhoto.POSITION_COVER}'){
									self.coverImages=response.data.content.coverFileJson.imagesArticle; 	
								}else if(position=='${ArticlePhoto.POSITION_BANNER}'){
									self.bannerImages=response.data.content.bannerFileJson.imagesArticle;
								}
		 					}
				    
		  			});//end http
			 },//end deletePhotoItem
			 deletePhotoItemForItem(articleId, articleItemId,articlePhotoId, articlePhotoItemId,position,index,itemImage_index){
			 	this.errorMessage='';
			  	this.successMessage='';
			 	var self=this;
			 	var currentSelf=self.articleItemList[index];
			 	console.log( "deletePhotoItemForItem >>>> articleId :" + articleId + " articleItemId:"+articleItemId + " articlePhotoItemId:"+articlePhotoItemId + " position:" + position + " :index>>"+index + " :itemImage_index>>"+itemImage_index);
			 	var deleteItemPhotoUrl="/activity/article/item/deletePhoto";
			 	//异步删除段落中的图片
		    	 this.$http({
					  method: 'post',
					  url: deleteItemPhotoUrl,
					  data: {
					  	'id':articleItemId,
					  	'articleId':articleId,
					  	'articlePhotoItemId':articlePhotoItemId,
					    'articlePhotoId': articlePhotoId,
					    'position':position
					  }
	 			}).then(function(response){
	 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_DELETED_SUCCESS}';
	 					var photoItemSuccessCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_ITEM_DELETED_SUCCESS}';
			 			var respCode = response.data.respCode;
	 					if(successCode==respCode || photoItemSuccessCode==respCode){
							console.log(successCode + ": articleId>> "+articleId + " : articleItemId>>" + articleItemId + ":articlePhotoId>>" + articlePhotoId + " :articlePhotoItemId>>"+articlePhotoItemId);	
							if(position=='${ArticlePhoto.POSITION_ITEM}'){
								self.articleItemList[index].imagesArticle.splice(itemImage_index,1);	
							}
	 					}
			    
	  			});//end http
			 	
			 },//deletePhotoItemForItem
			 mergeForTitle(){
			  		this.errorMessage='';
			  		this.successMessage='';
			  		console.log(this.id + ":" + this.articleBo.title);
			  		//调用异步更新
			  		 var self=this;
			  		 
			  		 var url="/activity/article/mergeForTitle";
			  		 
			  		 var validated=true;
			  		 if(this.articleBo.title==''){
			  		 	this.errorMessage="请填写文章标题";
			  		 	validated=false;
			  		 }
			  		 
			  		 if(this.articleBo.subTitle==''){
			  		 	this.errorMessage+="<br/> 请填写文章子标题";
			  		 	validated=false;
			  		 }
			  		 
			  		 if(this.articleBo.description=='' || this.articleBo.description.length>200){
			  		 	this.errorMessage+="<br/>请简要概括该篇文章的主要内容，200字以内";
			  		 	validated=false;
			  		 }
			  		 
			  		 if(validated){
				    	 //异步更新标题数据
				    	 this.$http({
								  method: 'post',
								  url: url,
								  data: Qs.stringify({
								  	'id':self.articleBo.id,
								  	'preIntro':self.articleBo.preIntro,
								    'title': self.articleBo.title,
								    'subTitle': self.articleBo.subTitle,
								    'description': self.articleBo.description
								  })
				 			}).then(function(response){
				 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_TITLE_EDIT_SUCCESS}';
				 					var respCode = response.data.respCode;
				 					if(successCode==respCode){
										console.log(successCode + ": " + self.articleBo.id + ":" + self.articleBo.title);	
										self.successMessage=response.data.respMsg; 					
				 					}
						    
				  			});//end http
			  		}
			  		
			 },//end mergeForTitle
			 mergeForSeqAndThirdTitle(articleId, photoId, photoItemId, position, title, orderSeq, index){
			  		this.errorMessage='';
			  		this.successMessage='';
			  		console.log("photoId:"+photoId + " >> articleId:" + articleId + " >>photoItemId:" + photoItemId + " >>position:"+position + " >>title:"+title + " >>orderSeq:"+orderSeq);
			  		//调用异步更新
			  		 var self=this;
			  		 var mergeForSeqAndThirdTitleUrl="/activity/article/photo/mergeForSeqAndThirdTitle";
			  		 self.bannerImages[index].success=0;
			  		 //异步更新图片标题和排序
			    	 this.$http({
							  method: 'post',
							  url: mergeForSeqAndThirdTitleUrl,
							  data: Qs.stringify({
							  	'articleId':articleId,
							  	'articlePhotoItemId':photoItemId,
							    'id': photoId,
							    'position':position,
							    'thirdTitle':title,
							    'orderSeq':orderSeq
							  })
			 			}).then(function(response){
			 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_MERGE_TITLE_SUCCESS}';
			 					var refreshSuccessCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_REFRESH_SUCCESS}';
					 			var respCode = response.data.respCode;
			 					if(successCode==respCode){
									self.successMessage=response.data.respMsg;
									console.log(successCode + ": " + articleId + ":photoId>>" + photoId + " >>successMessage:"+self.successMessage);	
			 					}else{
			 						self.errorMessage=response.data.respMsg;
			 						console.log(successCode + ": " + articleId + ":photoId>>" + photoId + " >>errorMessage:"+self.errorMessage);
			 					}
			 					self.articlePhotoBo=response.data.content;
			 					self.bannerImages[index].success=response.data.success;
					    
			  			});//end http
			 },//end mergeForSeqAndThirdTitle
			 saveRoadPoint(id,name,orderSeq, from){
			  		console.log("roadPoint: " + id + ":" + name + " orderSeq:"+orderSeq + " from:"+from);
			  		this.saveRoadLine();
			  		
			 },
			 deleteRoadPoint(id, from, index){
			  		console.log("roadPoint: " + id + ":" + " from:" + from);
			  		if(from=='current'){
			  			this.roadPoint.name='';
			  			this.roadPoint.id='';
			  			this.roadPoint.orderSeq='';
			  		}else if(from=='cycle'){
			  			console.log("roadPoint: " + id + ":" + " from:" + from + " index:" + index);
			  			
			  			if(id != '' && typeof(id) != "undefined"){
			  				 var articleId=this.articleBo.id;
						  	 
						  	 //调用异步删除
					  		 var self=this;
					  		 var deleteRoadPointUrl="/activity/road/roadPoint/delete";
					  		 
					  		 //异步删除行程点和排序
					    	 this.$http({
									  method: 'post',
									  url: deleteRoadPointUrl,
									  data: {
									  	'articleId':articleId,
									  	'roadLineId':self.roadLine.id,
									  	'id' : id
									  }
					 			}).then(function(response){
					 					var successCode = '${ActivityResponseCode.CODE_ROADPOINT_DELETE_SUCCESS}';
							 			var respCode = response.data.respCode;
					 					if(successCode==respCode){
											self.successMessage=response.data.respMsg;
											self.deleteEmptyRoadPoint(id, from, index);
											console.log(successCode + ": " + articleId + ":roadLine.id>>" + self.roadLine.id + " >>successMessage:"+self.successMessage);
					 					}else{
					 						self.errorMessage=response.data.respMsg;
					 						console.log(successCode + ": " + articleId + ":roadLine.id>>" + self.roadLine.id + " >>errorMessage:"+self.errorMessage);
					 					}
							    
					  			});//end http
			  			}else{
			  				self.deleteEmptyRoadPoint(id, from, index);
			  			}
			  		}
			},
			deleteEmptyRoadPoint(id, from, index){
			  		//js删除元素 begin
		  			if(index>0){
		  				this.roadLine.roadPoints.splice(index,1);
		  			}else if(index==0){
		  				this.roadLine.roadPoints.splice(index,1);
		  				this.roadPoint.name='';
		  				this.roadPoint.id='';
		  				this.roadPoint.orderSeq='';
		  				this.roadLine.roadPoints.push(this.roadPoint);
		  			}
		  			//js删除元素 end
			},
			saveAllRoadPoint(){
			  		this.errorMessage='';
			  	    this.successMessage='';
				  	console.log("roadPoint: " + this.roadPoint.name + ":" + this.roadPoint.orderSeq);
				  	console.log("roadLine length: " + this.roadLine.roadPoints.length);
				  	for(var i=0;i<this.roadLine.length;i++){
				  		console.log("index:"+i + " roadPoint: "+ this.roadLine.roadPoints[i].name + this.roadLine.roadPoints[i].orderSeq);
				  	}
				  	this.saveRoadLine();
			  },
			addRoadPoint(){
			  	 this.errorMessage='';
			  	 this.successMessage='';
			  	 var obj = {id:'', name:'', orderSeq:''};
			  	 var length = this.roadLine.roadPoints.length;
			  	 if(length<1){
			  	 	obj.id=this.roadPoint.id;
			  	 	obj.name=this.roadPoint.name;
			  		obj.orderSeq=this.roadPoint.orderSeq;
			  	 }
			  	 this.roadLine.roadPoints.push(obj);
			},
			saveRoadLine(){
			  	 this.errorMessage='';
			  	 this.successMessage='';
			  	 var articleId=this.articleBo.id;
			  	 var length = this.roadLine.roadPoints.length;
			  	 console.log(this.roadLine.name + " : " + length);
			  	 
			  	 //调用异步更新
		  		 var self=this;
		  		 var saveRoadLineUrl="/activity/road/roadLine/save";
		  		 
		  		 //异步更新行程点和排序
		    	 this.$http({
						  method: 'post',
						  url: saveRoadLineUrl,
						  data: {
						  	'articleId':articleId,
						    'id': self.roadLine.id,
						    'name':self.roadLine.name,
						    'roadPoints':self.roadLine.roadPoints,
						    'position': ${RoadLine.POSITION_ARTICLE},
						    'articleCategoryId':self.selectedCategoryId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ROADLINE_SAVED_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.roadLine=response.data.content;
								console.log(successCode + ": " + articleId + ":roadLine.id>>" + self.roadLine.id + " >>successMessage:"+self.successMessage);	
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": " + articleId + ":roadLine.id>>" + id + " >>errorMessage:"+self.errorMessage);
		 					}
				    
		  			});//end http
			},//end saveRoadLine
			queryRoadLine(){
			     this.errorMessage='';
			  	 this.successMessage='';
			  	 var articleId=this.articleBo.id;
			  	 var length = this.roadLine.roadPoints.length;
			  	 console.log(this.roadLine.name + " : " + length);
			  	 
			  	 //调用异步查询
		  		 var self=this;
		  		 var queryRoadLineUrl="/activity/road/roadLine/query";
		  		 
		  		 //异步查询行程点和排序
		    	 this.$http({
						  method: 'post',
						  url: queryRoadLineUrl,
						  data: {
						  	'articleId':articleId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ROADLINE_VIEW_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.roadLine=response.data.content;
								if(self.roadLine.roadPoints.length==0){
									self.roadLine.roadPoints.push(self.roadPoint);
								}
								console.log(successCode + ": " + articleId + ":roadLine.id>>" + self.roadLine.id + " >>successMessage:"+self.successMessage);	
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": " + articleId + ":roadLine.id>>" + self.roadLine.id + " >>errorMessage:"+self.errorMessage);
		 					}
		  			});//end http
			}, //end queryRoadLine
			saveTag(){
			     this.errorMessage='';
			  	 this.successMessage='';
			  	 var articleId=this.articleBo.id;
			  	 
			  	 this.articleTag.articleId=articleId;
			  	 this.articleTag.articleCategoryId=this.selectedCategoryId;
			  	 
			  	 if(this.articleTag.name==''){
			  	 	this.errorMessage='标签名称不能为空';
			  	 	return;
			  	 }
			  	 //调用异步更新
		  		 var self=this;
		  		 var saveTagUrl="/activity/tag/article/save";
		  		 
		  		 //异步保存tag
		    	 this.$http({
						  method: 'post',
						  url: saveTagUrl,
						  data: self.articleTag
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_TAG_SAVED_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.articleTagList=response.data.content.articleTagList;
								console.log(successCode + ": " + articleId + " :self.articleTag.name>>" + self.articleTag.name + " >>successMessage:" + self.successMessage);	
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": " + articleId + " :self.articleTag.name>>" + self.articleTag.name + " >>errorMessage:" + self.errorMessage);
		 					}
				    
		  			});//end http
			  },//end saveTag
			  clearTag(){
			  	this.articleTag.name='';
			  },//end clearTag
			  queryTags(){
			     this.errorMessage='';
			  	 this.successMessage='';
			  	 console.log("call queryTags------");
			  	 var articleId=this.articleBo.id;
			  	 
			  	 //调用异步更新
		  		 var self=this;
		  		 var queryTagUrl="/activity/tag/article/query";
		  		 
		  		 //异步查询tags
		    	 this.$http({
						  method: 'post',
						  url: queryTagUrl,
						  data: {
						  	'articleId':articleId,
						    'articleCategoryId':self.selectedCategoryId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_TAG_QUERY_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.articleTagList=response.data.content.articleTagList;
								console.log(successCode + ": articleId>> " + articleId + " >>successMessage:"+self.successMessage);	
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": articleId>>" + articleId + " >>errorMessage:"+self.errorMessage);
		 					}
		  			});//end http
			  },//queryTags
			  deleteTag(id, articleId, tagId, index){
			  	 this.errorMessage='';
			  	 this.successMessage='';
			  	console.log("call deleteTag------");
			  	//调用异步删除
		  		 var self=this;
		  		 var deleteTagUrl="/activity/tag/article/delete";
			  	
			  	//异步删除tag
		    	 this.$http({
						  method: 'post',
						  url: deleteTagUrl,
						  data: {
						  	'id' : id,
						  	'articleId':articleId,
						    'tag.id':tagId,
						    'tagId':tagId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_TAG_DELETED_SUCCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								//self.articleTagList=response.data.content;
								self.articleTagList.splice(index,1);
								console.log(successCode + ": " + articleId + ":tag.id>>" + tagId + " >>successMessage:"+self.successMessage);	
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": " + articleId + ":tag.id>>" + tagId + " >>errorMessage:"+self.errorMessage);
		 					}
				    
		  			});//end http
			  }, //end deleteTag
			  addArticleItem(){
				  	 this.errorMessage='';
				  	 this.successMessage='';
				  	 
				  	 var articleId=this.articleBo.id;
				  	 console.log("call addArticleItem------");
				  	 //调用异步添加
			  		 var self=this;
			  		 var addArticleItemUrl="/activity/article/item/save";
				  	
				  	 //异步添加段落
			    	 this.$http({
							  method: 'post',
							  url: addArticleItemUrl,
							  data: {
							  	'articleId':articleId
							  }
			 			}).then(function(response){
			 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_SAVED_SUCCESS}';
					 			var respCode = response.data.respCode;
			 					if(successCode==respCode){
									self.successMessage=response.data.respMsg;
									self.articleItem=response.data.content;
									self.articleItemList.push(response.data.content);
									self.articleItemListChanged=false;
									console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>successMessage:"+self.successMessage);	
			 					}else{
			 						self.errorMessage=response.data.respMsg;
			 						console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>errorMessage:"+self.errorMessage);
			 					}
					    
			  			});//end http
			  }, //end addArticleItem
			  saveArticleItem(id, aiTitle, content, orderSeq,from, index){
				  	 this.errorMessage='';
				  	 this.successMessage='';
				  	 var articleId=this.articleBo.id;
				  	 console.log("call saveArticleItem-- title: >> " + aiTitle + "----");
				  	 //调用异步保存
			  		 var self=this;
			  		 var saveArticleItemUrl="/activity/article/item/save";
				  	
				  	 if(content=='' && aiTitle==''){
				  	 	this.errorMessage='文章段落 或小标题 至少要填一个';
				  	 }
				  	 //异步保存段落
			    	 this.$http({
							  method: 'post',
							  url: saveArticleItemUrl,
							  data: {
							  	'id' : id,
							  	'articleId':articleId,
							  	'title': aiTitle,
							  	'content' : content,
							  	'orderSeq' : orderSeq
							  }
			 			}).then(function(response){
			 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_SAVED_SUCCESS}';
			 					var successMergedCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_MERGED_SUCCESS}';
					 			var respCode = response.data.respCode;
			 					if(successCode==respCode || successMergedCode==respCode){
									self.successMessage=response.data.respMsg;
									self.articleItem=response.data.content;
									//self.articleItemList.push(response.data.content);
									console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>successMessage:"+self.successMessage);	
			 					}else{
			 						self.errorMessage=response.data.respMsg;
			 						console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>errorMessage:"+self.errorMessage);
			 					}
					    
			  			});//end http
			  }, //end saveArticleItem
			  deleteArticleItem(id,from, index){
				  	 this.errorMessage='';
				  	 this.successMessage='';
				  	 var articleId=this.articleBo.id;
				  	 console.log("call deleteArticleItem------");
				  	 //调用异步添加
			  		 var self=this;
			  		 var deleteArticleItemUrl="/activity/article/item/delete";
				  	
				  	 //异步添加段落
			    	 this.$http({
							  method: 'post',
							  url: deleteArticleItemUrl,
							  data: {
							  	'id' : id,
							  	'articleId':articleId
							  }
			 			}).then(function(response){
			 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_DELETED_SUCCESS}';
					 			var respCode = response.data.respCode;
			 					if(successCode==respCode){
									self.successMessage=response.data.respMsg;
									self.articleItemList.splice(index,1);
									self.articleItemListChanged=false;
									console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>successMessage:"+self.successMessage);	
			 					}else{
			 						self.errorMessage=response.data.respMsg;
			 						console.log(successCode + ": " + articleId + ":articleItem.id>>" + self.articleItem.id + " >>errorMessage:"+self.errorMessage);
			 					}
					    
			  			});//end http
			  }, //end deleteArticleItem
			  initItemFileUpload(id,index){
					var idName="#id_file_articleItem_"+id;
					console.log(idName);
					//调用组件初始化
		  		 	var self=this;
		  		 	var currentSelf=self.articleItemList[index];
		  		 	//currentSelf.imagesArticle=null;
		  		 	var currentSelfData={'id' : currentSelf.id,'articleId':'${articleBo.id}','position':'${ArticlePhoto.POSITION_ITEM}','title': currentSelf.title, 'content' : currentSelf.content,'orderSeq' : currentSelf.orderSeq};
		  		 	
					$("#id_file_articleItem_"+id).fileinput({
						language: 'zh', 
						hideThumbnailContent: false,
						browseClass: "btn btn-primary",
						retryErrorUploads: true,
						showPreview: true,
						showCaption: true,
				        showUpload: true,
				        showRemove: true,
				        autoOrientImage: true,
				        dropZoneEnabled: false,
				        maxFileCount: 5,
				        maxFileSize: 1024*10, 
				        mainClass: "input-group-sm",
				        'previewFileType':'any',
				        allowedFileExtensions: ["jpg", "png", "gif"],
				        uploadAsync: false,
				        uploadUrl: "/activity/article/item/uploadPhotoItem",
				        uploadExtraData: function(){
				        	var currentSelfData={'id' : currentSelf.id,'articleId':'${articleBo.id}','position':'${ArticlePhoto.POSITION_ITEM}','title':currentSelf.title, 'content':currentSelf.content,'orderSeq':currentSelf.orderSeq};
				        	return currentSelfData;
				        }
				    }).on('filebatchpreupload', function(event, data) {
					    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
					    	if(extra.title=='' || extra.title==undefined){
					    		return false;
					    	}
					    	self.errorMessage='';
			  	 			self.successMessage='';
					    	console.log(data.extra);
					    	console.log('File pre upload triggered');
					}).on('filebatchuploadsuccess', function(event, data) {
					         var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
					         var respCode=response.respCode;
			    			 console.log('File batch upload success' + ": " +respCode);
			    			 var length=response.content.imagesArticle.length;
			    			 var imagesArticleResult=response.content.imagesArticle;
			    			 if(length>0){
			    			 	for(var i=0;i<length;i++){
			    			 		self.articleItemList[index].imagesArticle.push(imagesArticleResult[i]);
			    			 	}
			    			 }
			    			 self.successMessage=response.respMsg;
					}).on('filebatchuploadcomplete', function(event, files, extra) {
					    console.log('File batch upload complete');
					}).on('filedeleted', function(event, key, jqXHR, data) {
					    console.log('Key = ' + key);	
					    //refreshImagePreview('${articleBo.id}','${ArticlePhoto.POSITION_ITEM}');
					}).on('filebatchuploaderror', function(event, data, msg) {
					    var form = data.form, files = data.files, extra = data.extra,
					        response = data.response, reader = data.reader;
					    console.log('File batch upload error-----');
					   	self.errorMessage=msg;
					});
			  },//initItemFileUpload
			  queryItemListByArticleId(id){
			     this.errorMessage='';
			  	 this.successMessage='';
			  	 var articleId=this.articleBo.id;
			  	 console.log("call queryItemListByArticleId : ------ " + articleId);
			  	 
			  	 //调用异步更新
		  		 var self=this;
		  		 var queryItemListUrl="/activity/article/item/queryListByArticleId";
		  		 
		  		 //异步查询tags
		    	 this.$http({
						  method: 'post',
						  url: queryItemListUrl,
						  data: {
						  	'articleId':articleId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_QUERY_LIST_SUCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.articleItemList=response.data.content;
								console.log(successCode + ": articleId>> " + articleId + " >>successMessage:"+self.successMessage);
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": articleId>>" + articleId + " >>errorMessage:"+self.errorMessage);
		 					}
		  			});//end http
			  }, //end queryListByArticleId
			  saveArticlePhotos(articleId, articleItemId, index){
				  	 this.errorMessage='';
				  	 this.successMessage='';
				  	 console.log("call saveArticlePhotos-- articleItemId: >> " + articleItemId + " index>>" + index + "----");
				  	 //调用异步保存
			  		 var self=this;
				  	 self.articleItemList[index].respMsg='';
			  		 var saveArticlePhotosUrl="/activity/article/item/saveArticlePhotos";
			  		 console.log(self.articleItemList[index].imagesArticle);
			  		 
			  		 
				  	 //异步保存段落中的图片排序
			    	 this.$http({
							  method: 'post',
							  url: saveArticlePhotosUrl,
							  data: {
							  	'id' : articleItemId,
							  	'articleId':articleId,
							  	'imagesArticle':self.articleItemList[index].imagesArticle
							  }
			 			}).then(function(response){
			 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_FOR_ITEM_MERGE_TITLE_SUCCESS}';
					 			var respCode = response.data.respCode;
					 			self.articleItemList[index].respMsg=response.data.respMsg;
			 					if(successCode==respCode){
			 						self.articleItemList[index].success=response.data.content.success;
									self.successMessage=response.data.respMsg;
									console.log(successCode + ": articleId>>" + articleId + ":articleItem.id>>" + articleItemId + " >>successMessage:"+self.successMessage);	
			 					}else{
			 						self.errorMessage=response.data.respMsg;
			 						console.log(successCode + ": articleId>>" + articleId + ":articleItem.id>>" + articleItemId + " >>errorMessage:"+self.errorMessage);
			 					}
					    
			  			});//end http
			  }, //end saveArticlePhotos
			  queryItemSingle(id,index){
			     this.errorMessage='';
			  	 this.successMessage='';
			  	 var articleId=this.articleBo.id;
			  	 console.log("------- call queryItemSingle : ------ id is " + id);
			  	 //调用异步更新
		  		 var self=this;
		  		 var queryItemListUrl="/activity/article/item/query";
		  		 
		  		 //异步查询tags
		    	 this.$http({
						  method: 'post',
						  url: queryItemListUrl,
						  data: {
						  	'id':id,
						  	'articleId':articleId
						  }
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_ITEM_QUERY_SUCESS}';
				 			var respCode = response.data.respCode;
		 					if(successCode==respCode){
								self.successMessage=response.data.respMsg;
								self.articleItemList[index]=response.data.content;
								console.log(successCode + ": articleId>> " + articleId + " id>>> "+ id + " index: " + index + ">>successMessage:"+self.successMessage);
		 					}else{
		 						self.errorMessage=response.data.respMsg;
		 						console.log(successCode + ": articleId>>" + articleId +  " id>>> "+ id + " index: " + index + " >>errorMessage:"+self.errorMessage);
		 					}
		  			});//end http
			  }, //end queryItemSingle
			  mergeToPublish(){
			     var mergeToPublishUrl="/activity/article/publish/ansy";
			     var self=this;
			  	 //异步更新发布数据
		    	 this.$http({
						  method: 'post',
						  url: mergeToPublishUrl,
						  data: Qs.stringify({
						  	'id':self.articleBo.id,
						  	'penname':self.articleBo.penname
						  })
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_TITLE_EDIT_SUCCESS}';
		 					var respCode = response.data.respCode;
		 					if(successCode==respCode){
								console.log(successCode + ": " + self.articleBo.id + ":" + self.articleBo.title);	
								self.successMessage=response.data.respMsg; 					
		 					}
				    
		  			});//end http
			  }, //end mergeToPublish
			  articlePreview(id){
			  	 $('#modal_' + id ).modal('show');
			  	 var articlePreviewUrl="/activity/article/view/ansy";
			     var self=this;
			  	 //异步更新发布数据
		    	 this.$http({
						  method: 'post',
						  url: articlePreviewUrl,
						  data: Qs.stringify({
						  	'id':id
						  })
		 			}).then(function(response){
		 					var successCode = '${ActivityResponseCode.CODE_ARTICLE_SUCCESS}';
		 					var respCode = response.data.respCode;
		 					if(successCode==respCode){
								console.log(successCode + ": " + self.articleBo.id + ":" + self.articleBo.title);	
								self.articleView=response.data.content;
								self.roadLine=self.articleView.roadLine;
								self.successMessage=response.data.respMsg; 					
		 					}
		  			});//end http
			  },//end articlePreview
			  showRewardPanel(){
			  		$('#btn_reward_pop').popover('toggle');
			  		console.log("----------- reward pop ------ ");
			  },
			  chooseImage(){
			  	wx.chooseImage({
					count: 1, // 默认9
					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
					success: function (res) {
						alert(res);
						var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
					}
				});
			  }
			  
		  },
		  updated: function(){
					 console.log('初始化上传组件>>>>>');
					 if(this.articleItemList.length>0 && !this.articleItemListChanged){
							for(var i=0;i<this.articleItemList.length;i++){
								var itemId=this.articleItemList[i].id;
								console.log("itemId>>> " + itemId + " fileupload init------------");
								this.initItemFileUpload(itemId, i);
							}
							this.articleItemListChanged=true;
					}
					
			    
		  },
		  
		  created: function () {
		   		this.initCategory();
		   		this.roadLine.roadPoints.push(this.roadPoint);
		   		console.log("created exe...");
	      }//end created
	      
                
		});
		
		
		$("#id_file_cover").fileinput({
			language: 'zh', 
			hideThumbnailContent: false,
			browseClass: "btn btn-primary",
			retryErrorUploads: true,
			showPreview: true,
			showCaption: true,
	        showUpload: true,
	        showRemove: true,
	        autoOrientImage: true,
	        dropZoneEnabled: false,
	        maxFileCount: 3,
	        maxFileSize: 1024*10, 
	        mainClass: "input-group-sm",
	        'previewFileType':'any',
	        allowedFileExtensions: ["jpg", "png", "gif"],
	        uploadAsync: false,
	        uploadUrl: commonUploadUrl,
	        uploadExtraData:{'id':'${articleBo.id}','position':'${ArticlePhoto.POSITION_COVER}'}
	        //initialPreview: ${GsonBuilderUtils.toJsonOmitnull(articleBo.coverFileJson.initialPreview)},
	        //initialPreviewConfig: ${GsonBuilderUtils.toJsonOmitnull(articleBo.coverFileJson.initialPreviewConfig)}
	        //deleteUrl: commonDeleteUrl,
	        //deleteExtraData:{'id':'${articleBo.id}', 'articlePhotoId':200}
	    }).on('filebatchpreupload', function(event, data) {
		    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		        var filesCount = $('#id_file_cover').fileinput('getFilesCount');
		        var fileStack = $('#id_file_cover').fileinput('getFileStack');
		    	console.log('File pre upload triggered');
		}).on('filebatchuploadsuccess', function(event, data) {
		         var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		         var respCode=response.respCode;
    			 console.log('File batch upload success' + ": " +respCode);
    			 articlePublishPage.successMessage=response.respMsg;
    			 refreshImagePreview('${articleBo.id}','${ArticlePhoto.POSITION_COVER}');
		}).on('filebatchuploadcomplete', function(event, files, extra) {
		    console.log('File batch upload complete');
		}).on('filedeleted', function(event, key, jqXHR, data) {
		    console.log('Key = ' + key);	
		    refreshImagePreview('${articleBo.id}','${ArticlePhoto.POSITION_COVER}');
		}).on('filebatchuploaderror', function(event, data, msg) {
		    var form = data.form, files = data.files, extra = data.extra,
		        response = data.response, reader = data.reader;
		    console.log('File batch upload error');
		   	articlePublishPage.errorMessage=msg;
		});
	    
	    $("#id_file_banner").fileinput({
			language: 'zh', 
			hideThumbnailContent: false,
			browseClass: "btn btn-primary",
			showCaption: true,
	        showUpload: true,
	        showRemove: true,
	        autoOrientImage: true,
	        dropZoneEnabled: false,
	        maxFileCount: 5,
	        maxFileSize: 1024*10, 
	        mainClass: "input-group-sm",
	        'previewFileType':'any',
	        allowedFileExtensions: ["jpg", "png", "gif"],
	        uploadAsync: false,
	        uploadUrl: commonUploadUrl,
	        uploadExtraData:{'id':'${articleBo.id}','position':'${ArticlePhoto.POSITION_BANNER}'}
	        //initialPreview: ${GsonBuilderUtils.toJsonOmitnull(articleBo.bannerFileJson.initialPreview)},
	        //initialPreviewConfig: ${GsonBuilderUtils.toJsonOmitnull(articleBo.bannerFileJson.initialPreviewConfig)}
	    }).on('filebatchpreupload', function(event, data) {
		    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		        var filesCount = $('#id_file_banner').fileinput('getFilesCount');
		        var fileStack = $('#id_file_banner').fileinput('getFileStack');
		    	console.log('banner File pre upload triggered');
		}).on('filebatchuploadsuccess', function(event, data) {
		         var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
		         var respCode=response.respCode;
    			 console.log('banner File batch upload success' + ": " +respCode);
    			 articlePublishPage.successMessage=response.respMsg;
    			 refreshImagePreview('${articleBo.id}','${ArticlePhoto.POSITION_BANNER}');
		}).on('filebatchuploadcomplete', function(event, files, extra) {
		    console.log('banner File batch upload complete');
		}).on('filedeleted', function(event, key, jqXHR, data) {
		    console.log('banner   Key = ' + key);	
		    refreshImagePreview('${articleBo.id}','${ArticlePhoto.POSITION_BANNER}');
		}).on('filebatchuploaderror', function(event, data, msg) {
		    var form = data.form, files = data.files, extra = data.extra,
		        response = data.response, reader = data.reader;
		    console.log('File batch upload error');
		   	articlePublishPage.errorMessage=msg;
		});
		
		
		//异步刷新图片
		function refreshImagePreview(articleId,position){
			articlePublishPage.successMessage='';
			articlePublishPage.errorMessage='';
			var refreshImageUrl="/activity/article/refreshImagePreview";
			console.log('   refreshImageUrl = ' + refreshImageUrl);
			 articlePublishPage.$http({
					  method: 'post',
					  url: refreshImageUrl,
					  data: Qs.stringify({
					  	'id':articleId,
					    'position':position
					  })
				}).then(function(response){
						var successCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_ITEM_DELETED_SUCCESS}';
						var refreshSuccessCode = '${ActivityResponseCode.CODE_ARTICLE_PHOTO_REFRESH_SUCCESS}';
						var respCode = response.data.respCode;
						var respMsg = response.data.respMsg;
						if(successCode==respCode || refreshSuccessCode==respCode){
							console.log(successCode + ": " + articleId+ ":position>>" + position);	
							if(position=='${ArticlePhoto.POSITION_COVER}'){
								articlePublishPage.coverImages=response.data.content.coverFileJson.imagesArticle;
								 	
							}else if(position=='${ArticlePhoto.POSITION_BANNER}'){
								articlePublishPage.bannerImages=response.data.content.bannerFileJson.imagesArticle;
							}
							articlePublishPage.successMessage=respMsg;
						}else{
							articlePublishPage.errorMessage=respMsg;
						}
			    
				});//end http
		}
		
		$('[data-toggle="popover"]').popover();
		
		$.goup();
		
	});
	function iosScrollBug(){}
	function toTop(){
        window.scroll(0,0);
    }
	//-->
</script>
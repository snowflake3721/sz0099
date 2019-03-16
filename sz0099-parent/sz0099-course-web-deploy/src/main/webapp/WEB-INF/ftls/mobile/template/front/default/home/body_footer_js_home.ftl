<script type="text/javascript">
	<!--
	
	$(document).ready(function(){
		var app7 = new Vue({
		  el: '#app',
		  data: {
		    userList: ${userJson},
		    currentUser: ${currentUser},
		    homeImageList:
                	 [
				    	{imageUrl:"/assets/common/images/upload/20180422122515_900X500.jpg", title:'江南七怪穿越东西冲1',id:"1",isActive:"active"},
				    	{imageUrl:"/assets/common/images/upload/20180422122515_900X500.jpg", title:'江南七怪穿越东西冲2',id:"2",isActive:""},
				    	{imageUrl:"/assets/common/images/upload/20180422122515_900X500.jpg", title:'江南七怪穿越东西冲3',id:"3",isActive:""}
				    ]
				     ,
			<#-- ${articlePage.name}:${GsonBuilderUtils.toJson(articlePage)},
			${actPage.name}:${GsonBuilderUtils.toJson(actPage)},
			 -->
			footingLoaded : false	,
			bikingLoaded : false	,
			travelLoaded : false	,
			homeLoaded : true	,
			equipmentsLoaded: false	    	
		  },
		  computed:{
                email:function(){
                    //业务逻辑代码
                    var showEmail = this.currentUser.email.substring(0,6);
                    return showEmail;
                },
                articleList:function(){
                	var result = [
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲1'},
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲2'},
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲3'},
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲4'},
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲5'},
			    		{imageUrl:this.currentUser.avatar, title:'江南七怪穿越东西冲6'}
		    		];
		    		return result;
		    	},
		    	travelList:function(){
		    		var result = new Array();
		    		for(var i=0;i<this.userList.length;i++){
		    			var travel = this.userList[i];
		    			console.log(travel.title);
		    			console.log(travel.avatar);
		    			travel.title="旅行文章"+i;
		    			result.push(travel);
		    		};
		    		return result;
		    	},
		    	firstTravelTitle:function(){
		    		return this.travelList[0].title;
		    	}
		    	
            },
            methods:{
            	loadContent : function(code){
            		
            		
            		if(code=='home' ){
            			if(!this.homeLoaded){
	            			console.log(this.homeLoaded + "<--->home");
	            			this.homeLoaded=true;
            			}
            			return;
            		}
            		if(code=='footing'){
	            		if(!this.footingLoaded){
	            			console.log(this.footingLoaded + "<--->footing");
	            			this.footingLoaded=true;
            			}
            			return;
            		}
            		
            		if(code=='biking'){
            			if(!this.bikingLoaded){
	            			console.log(this.bikingLoaded + "<--->biking");
	            			this.bikingLoaded=true;
            			}
            			return;
            		}
            		
            		if(code=='travel'){
            			if(!this.travelLoaded){
	            			console.log(this.travelLoaded + "<--->travel");
	            			this.travelLoaded=true;
            			}
            			return;
            		}
            		
            		if(code=='equipments'){
            			if(!this.equipmentsLoaded){
	            			console.log(this.equipmentsLoaded + "<--->equipments");
	            			this.equipmentsLoaded=true;
            			}
            			return;
            		}
            		
            		console.log(this.footLoaded + "<--->loadFooting");
			  		//调用异步更新
			  		 var self=this;
			  		 
			  		 var url="/activity/article/changeCategory";
			  		 
			  		 if(!this.footLoaded){
				    	 //异步获取徒步数据
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
										footLoaded=true;			 					
				 					}
						    
				  			});//end http
			  		}
            	}
            }
		});
		
		//大屏图片滚动
		$('.carousel').carousel({
		  interval: 2000
		});
		//tooltip显示,每人一句雷人语
		$(function () {
		  $('[data-toggle="tooltip"]').tooltip()
		});
	});
	//-->
</script>
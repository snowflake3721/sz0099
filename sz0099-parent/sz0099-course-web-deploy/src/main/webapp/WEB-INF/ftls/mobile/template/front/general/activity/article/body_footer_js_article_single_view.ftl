<script type="text/javascript">
	<!--
	var articleSingleView = null;
	$(document).ready(function(){
	
		articleSingleView = new Vue({
			  el: '#app',
			  data: {
			  		currentUser: ${currentUser},
			  		id:'${articleBo.id}',
			  		articleBo:{},
			  		articleView: ${GsonBuilderUtils.toJson(articleBo)},
			  		orderArticle:{'productId':'${articleBo.productId}', 'articleId':'${articleBo.id}','realAmount':'','name':''}
			  },
			  computed:{
			  		email:function(){
	                    //业务逻辑代码
	                    var showEmail = this.currentUser.email.substring(0,6);
	                    return showEmail;
                	},
                	roadLine : function(){
                		return this.articleView.roadLine;
                	},
                	product : function(){
                		return this.articleView.product;
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
            		},
            		showRewardPanel(id){
				  		$('#btn_reward_pop_'+id).popover('toggle');
				  		console.log("----------- reward pop ------ ");
			  		},
			  		clearAmount(id){
					  	this.orderArticle.realAmount='';
					  	$("#id_reward_amount_"+id).val('');
					},
					chooseAmount(id,realAmount){
						console.log("----------- realAmount ------ ");
						this.orderArticle.realAmount=realAmount;
						$("#id_reward_amount_"+id).val(realAmount);
					},
					confirmAmount(id){
						this.orderArticle.realAmount = $("#id_reward_amount_"+id).val();
						console.log("---- realAmount: " + this.orderArticle.realAmount);
						if(this.orderArticle.realAmount==''){
							alert("打赏金额不能为空!");
							return false;
						}
						if(this.orderArticle.productId==''){
							alert("文章尚未开通打赏，不能执行打赏操作!");
							return false;
						}
						
						if(this.orderArticle.articleId==''){
							alert("文章状态不合法，不能执行打赏操作!");
							return false;
						}
						
						if(this.product.shelved!=1){
							alert("文章已被下架，不能打赏了！");
							return false;
						}
						
						console.log("---- realAmount: " + this.orderArticle.realAmount);
						console.log("---- orderArticle: " + this.orderArticle);
					}
			  },
			  updated: function(){
					console.log('数据有更新>>>>>');
			  },
			  
			  created: function () {
			   		console.log("created exe...");
		      }//end created
	      
		});
		
		
		
		$('[data-toggle="popover"]').popover();
		//大屏图片滚动
		$('.carousel').carousel({
		  interval: 2000
		});
	});
	function chooseAmount(id,realAmount){
		console.log("----------- chooseAmount-1 ------ ");
		articleSingleView.chooseAmount(id,realAmount);
	}
	function clearAmount(id){
	  	console.log("----------- clearAmount-1 ------ ");
		articleSingleView.clearAmount(id);
	}
	function confirmAmount(id){
		console.log("----------- confirmAmount-1 ------ ");
		articleSingleView.confirmAmount(id);
	}
	//-->
</script>
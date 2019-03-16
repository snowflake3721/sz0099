<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "${ThreadLocalHolder.getBrowserPrefix()}/template/front/default/macro/macro_siteDataInit.ftl">
<#include "${ThreadLocalHolder.getBrowserPrefix()}/template/front/default/macro/macro_article_category_dataInit.ftl">
<html>
<head>

<title>系统初始化-卓玛拉山联盟</title>
</head>

<body>

<div class="container" id="top_login">
</div>

<div class="container" id="top_menu">
	<h4 class="text-danger">数据初始化步骤：</h4>
	<button type="button" class="btn btn-warning btn-sm text-right">1.基本数据初始化</button>
	<button type="button" class="btn btn-warning btn-sm text-right">2.相关数据初始化</button>
	<button type="button" class="btn btn-danger btn-sm text-right" @click="deleteAll">3.清空所有数据</button>
</div>

<div class="container" id="body_content">

	<div id="siteDataInit">
		<@M_siteDataInitList dataInitList=content />
		<div class="row">
				<div class="col-xs-8 text-left">
				 	<button type="button" class="btn btn-primary btn-sm text-right" @click="reload">刷新</button>
				</div>
				<div class="col-xs-4 text-right">
					<span></span>
				</div>
		</div><!--row end-->
	</div>
	
	
</div>

<div class="container" id="body_footer">

</div>

<div class="container" id="body_footer_js">
<script type="text/javascript">
	<!--
	
	$(document).ready(function(){
		//var qs = new qs();
		
		var app8 = new Vue({
		  el: '#app',
		  data: {
		    	message:'',
		    	respCode: '${SiteResponseCode.CODE_SITE_DATA_INIT_SUCCESS}',
		    	articleCategoryList:[],
		    	isWarning:true,
		    	isSuccess:false,
		    	ddStatusMsg:'',
		    	ddStatus:'',
		    	dataItemId:'',
		    	dataCategory:'',
		    	respCode:'',
		    	force:false,
		    	isForce:false
		    	
		  },
		  methods: {
			    doBasicDataInit: function(event,respCode) {
			      	
			      	 console.log('message is: ' + this.message);
			      	 this.$http({
							  method: 'post',
							  url: '/site/doSiteDataInit',
							  data: Qs.stringify({
							    'name': 'Fred',
							    'code': 'Flintstone'
							  })
						}).then(function(response) {
							    console.log(response.data);
							    alert(respCode);
							    if(response.data.respCode==respCode){
							    	window.location.reload();
							    	event.preventDefault();
							    }else{
							    	alert(response.data);
							    }
						});
			    },
			    refreshDataItem:function(dataItemId){
			    	if(null != dataItemId){
			    		//页面刷新
			    		window.location.reload();
			    	}
			    },
			    deleteAll :function(){
			    	var url="/site/deleteAll";
			    	var self=this;
				    	 //异步获取数据
				    	 this.$http({
										  method: 'post',
										  url: url,
										  data: Qs.stringify({
										  	'id':dataItemId,
										    'code': dataCategory
										  })
						 			}).then(function(response){
						 				alert(response.data.respMsg + ", 点击确定后, 页面自动刷新");
										window.location.reload();
						  			});//end then
			    },
			    reload : function(){
			    
			    	window.location.reload();
			    },
		    
			    viewDataInit:function(dataItemId,dataCategory){
			    	if(null != dataItemId){
				    	 this.message = "查看数据";
				    	 var url="";
				    	 var articleCategory="${SiteDataInit.CODE_ARTICLE_CATEGORY}";
				    	 if(dataCategory==articleCategory){
				    	 	url='/activity/article/category/data/init/ansy';
				    	 }
				    	 
				    	 var self=this;
				    	 //异步获取数据
				    	 this.$http({
										  method: 'post',
										  url: url,
										  data: Qs.stringify({
										  	'id':dataItemId,
										    'code': dataCategory
										  })
						 			}).then(function(response){
										if(dataCategory==articleCategory){
									    	self.articleCategoryList=response.data.content;
									    }
								    
						  			});//end then
			        }//end if
		    	},//end  viewDataInit
		    	
		    	changeDdStatus:function(dataItemId,dataCategory,ddStatus,ddStatusMsg,respCode,force){
			    	if(null != dataItemId){
				    	 this.message = "修改数据状态";
				    	 var url='/site/changeDdStatus/ansy';
				    	 
				    	 var self=this;
				    	 //异步修改数据
				    	 this.$http({
										  method: 'post',
										  url: url,
										  data: Qs.stringify({
										  	'id':dataItemId,
										    'code': dataCategory,
										    'ddStatus': ddStatus,
										    'ddStatusMsg': ddStatusMsg,
										    'force':force
										  })
						 			}).then(function(response){
										if(response.data.respCode==respCode){
									    	//页面刷新
									    	alert(response.data.respMsg + ", 点击确定后, 页面自动刷新");
			    							window.location.reload();
									    }else{
									    	self.message="错误提示：["+response.data.respCode+"]"+response.data.respMsg;
									    	alert(self.message);
									    }
								    
						  			});//end then
			        }//end if
		    	},//end  changeDdStatus
		    	
		    	popChangeDdStatus:function(dataItemId,dataCategory,ddStatus,ddStatusMsg,respCode,force){
		    		var self=this;
		    		self.ddStatusMsg=ddStatusMsg;
		    		self.ddStatus=ddStatus;
		    		self.dataItemId=dataItemId;
		    		self.dataCategory=dataCategory;
		    		self.respCode=respCode;
		    		self.force=force;
		    		console.log(force);
		    		console.log(force=='true');
		    		if(force){
		    			self.isForce=true;
		    			self.ddStatusMsg= '[强制]'+ ddStatusMsg;
		    		}else{
		    			self.isForce=false;
		    		}
		    		$('#myModal_ddStatus_confirm').modal('show');
		    	},
		    	
		    	confirmChangeDdStatus:function(){
		    		var self=this;
		    		//alert(self.dataItemId + self.dataCategory+ self.ddStatus+self.ddStatusMsg+self.respCode+self.force);
		    		$('#myModal_ddStatus_confirm').modal('hide');
		    		this.changeDdStatus(self.dataItemId,self.dataCategory,self.ddStatus,self.ddStatusMsg,self.respCode,self.force);
		    	}
		  },//end methods
		  computed:{
                
            }
		});
		
	});
	//-->
</script>
</div>
</body>
</html>
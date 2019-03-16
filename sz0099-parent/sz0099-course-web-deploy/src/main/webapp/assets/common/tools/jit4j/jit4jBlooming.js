var bloomingMenu = null;
function initBloomingForJsonWhenLoaded(url,userId,inputId){
	changeBloomingForJson(url,userId,inputId);
	var scrollTop = $(document).scrollTop();
	if(scrollTop<=100){
		setTimeout(function () { 
			adToLeftTop(true);
		}, 1500);
	}
	//adToLeftTop(false);
}

function initBloomingForJsonAndAdMove(circleMain,inputId){
	initBloomingForJson(circleMain,inputId);
	//var windowH=$(window).height();
	var scrollTop = $(document).scrollTop();
	if(scrollTop<=100){
		setTimeout(function () { 
			adToLeftTop(true);
		}, 1500);
	}
	
}
function initBloomingForJson(circleMain,inputId){
	if(circleMain){
		var userInput=$("#"+inputId);
		var userId=circleMain.userId;
		if(userInput.length>0){
			userInput.val(userId);
		}
		
		var itemsNum=circleMain.size;
		var items=circleMain.itemList;
		var wrapperDiv=document.getElementById("id_profession_personal");
		if(!bloomingMenu && items.length>0){
			var mainContent="<div id='id_blooming_main_content' class='container center-block' onclick='recoverToCenter()'>" +
								"<img class='img-circle img-responsive' src='"+circleMain.imageUrl+"' />" +
								"<p class='blooming-menu-carousel-caption'>" +
								"<span class='label label-"+getRandomCssClazz()+"'>"+ circleMain.label +"</span>" +
								"</p>" +
							"</div>";
			bloomingMenu = new BloomingMenu({
			      startAngle: 0,
			      endAngle: 315,
			      radius: 120,
			      itemWidth: 100,
			      itemsNum: itemsNum,
			      fatherElement:wrapperDiv,
			      mainContent:mainContent
			    })
			    bloomingMenu.render();
		
			    // Prevents "elastic scrolling" on Safari
			   /* document.addEventListener('touchmove', function(event) {
			      'use strict'
			      event.preventDefault()
			    })*/
			    
			    bloomingMenu.props.elements.items.forEach(function (item, index) {
					
					//$(item).find("button").append("<span class='label label-danger' style='z-index:2001;margin-top:20px;margin-left:0px;'>"+"灵犀一指"+index+"</span>");
			    	var itemSingle=items[index];
			    	var imageUrl=itemSingle.imageUrl;
			    	var itemLink=itemSingle.link;
			    	var itemType=itemSingle.itemType;
			    	var itemLabel=itemSingle.label;
			    	var showLabel=itemSingle.showLabel;
			    	var btnType=itemSingle.btnType;
			    	var clazz=getRandomCssClazz();
			    	var itemUserid=itemSingle.userId;
			    	var itemContent="";
			    	if(itemType==0){
				    		if(imageUrl!=""){
				    			itemContent+="<img src='"+imageUrl+"' class='img-circle img-responsive' width='50px' height='50px'/>";
				    			if(showLabel==1){
				    				itemContent+="<p class='blooming-menu-carousel-caption'><span style class='label label-"+clazz+"'>"+itemLabel+"</span></p>";
				    			}
				    		}else{
				    			itemContent+="<p class='blooming-menu-carousel-caption'><span style class='label label-"+clazz+"'>"+itemLabel+"</span></p>";
				    		}
			    	}else{
			    		itemContent="<a href='"+itemLink+"'>";
			    		if(imageUrl!=""){
			    			itemContent+="<img src='"+imageUrl+"' class='img-circle img-responsive' width='50px' height='50px'/>";
			    			if(showLabel==1){
			    				itemContent+="<p class='blooming-menu-carousel-caption'><span style class='label label-"+clazz+"'>"+itemLabel+"</span></p>";
			    			}
			    		}else{
			    			itemContent+="<p class='blooming-menu-carousel-caption'><span style class='label label-"+clazz+"'>"+itemLabel+"</span></p>";
			    		}
			    		
			    		itemContent+="</a>";
			    	}
			    	
			    	$(item).find("button").append(itemContent);
					
			    	//1是关闭
			    	if(btnType==1){
						item.addEventListener('click', function () {
							console.log('btnType1 #' + index + 'was clicked');
							adToLeftTop(false);
						});
					}else if(btnType==2){
						item.addEventListener('click', function () {
							console.log('btnType2 #' + index + 'was clicked');
							changeBloomingForJson(itemLink, itemUserid ,"id_current_userId");
						});
					}
			    	
			    });	
			    
			    var scrollTop = $(document).scrollTop();
			    if(scrollTop<=100){
			    	adToLeftTop(false);
				}else{
					recoverToCenterThenOpen();
				}
			    //bloomingMenu.open();
		}
	}
}


function changeBloomingForJson(url , userId, inputId){
	var userInput=$("#"+inputId);
	var currentUserId=userId;
	if(userInput.length>0){
		currentUserId=userInput.val();
	}
	
	var circleMain=null;
	//var url="/sz0099/ood/personal/profession/findByUserId";
	if(userId && currentUserId != userId){
		axios({
		    method: 'post',
		    url: url,
		    data: 
		    	Qs.stringify({
	    	    	'userId' : userId
		    	})
		})
		.then(function (response) {
		    console.log("---- changeBloomingForJson loaded: ----"+userId);
		    var content=response.data.content;
		    var successCode=response.data.success;
		    if(successCode==1){
		    	userInput.val(userId);
		    	circleMain=content;
			    if(bloomingMenu!=null){
					$(".blooming-menu__container").remove();
					bloomingMenu=null;
				}
				
				if(bloomingMenu==null){
					initBloomingForJson(circleMain,inputId);
				}
		    }
		})
		.catch(function (error) {
		    console.log(error);
		});
		
	}else{
		recoverToCenterThenOpen();
	}
}

function close(){
	bloomingMenu.close();
}

function adToLeftBottom(){
	var heightW=$(window).height();
	var widthW=$(window).width();
	$('.blooming-menu__container').animate({'left':widthW*0.2,'top':heightW*0.8});
}
function adToLeftTopCall(){
	if(bloomingMenu==null){
		var login_status=$("#id_login_status").val();
		var url=$("#id_login_status").attr("data-url");
		if(login_status=='true'){
			initBloomingForJsonWhenLoaded(url,'10','id_current_userId') ;
		}
	}
	//召回
	adToLeftTop(false);
}
function adToLeftTop(isInit){
	var heightW=$(window).height();
	var widthW=$(window).width();
	if(isInit){
		setTimeout(function () { 
			bloomingMenu.close(); 
			$('.blooming-menu__container').delay(400).animate({'left':widthW*0.1,'top':15},function(){
				bloomingMenu.close();
			});
		},3000);
	}else{
		bloomingMenu.close(); 
		$('.blooming-menu__container').delay(200).animate({'left':widthW*0.1,'top':15},function(){
			bloomingMenu.close();
		});
	}
	
	
}
function recoverToCenter(){
	var windowH=$(window).height();
	var scrollTop = $(document).scrollTop();
	console.log(" windowH : " + windowH);
	console.log(" scrollTop : " + scrollTop);
	var circleMainTop=scrollTop+windowH*0.5;
	
	/*if(scrollTop>windowH*0.8){
		var menuH=500;
		circleMainTop=(windowH+scrollTop)*0.5+menuH;
		console.log(" menuH : " + menuH);
	}*/
	
	console.log(" circleMainTop : " + circleMainTop);
	var bodyFull=$(document.body).outerHeight(true);
	var documentH=$(document).height()
	console.log(" bodyFull : " + bodyFull);
	console.log(" documentH : " + documentH);
	
	var windowW=$(window).width();
    var scrollLeft = $(document).scrollLeft();
    console.log(scrollTop);
    var circleMainLeft=(windowW+scrollLeft)*0.5;
	$('.blooming-menu__container').animate({'left':circleMainLeft,'top':circleMainTop});
}
function recoverToCenterThenOpen(){
	recoverToCenter();
	setTimeout(function () { 
    	bloomingMenu.open();
	}, 1500);
}
function setCurrentSlide(ele, index) {
	$(".swiper1 .swiper-slide").removeClass("selected");
	ele.addClass("selected");
	//swiper1.initialSlide=index;
}
var swiper_index_menu = null;
var swiper_index_content = null;
function initSwiper(){
	swiper_index_menu = new Swiper('.swiper1', {
	//			设置slider容器能够同时显示的slides数量(carousel模式)。
	//			可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
	//			loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
	slidesPerView: 7.5,
	paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
	spaceBetween: 10,//slide之间的距离（单位px）。
	freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
	loop: false,//是否可循环
	onTab: function(swiper) {
		var n = swiper_index_menu.clickedIndex;
	}
	});
	swiper_index_menu.slides.each(function(index, val) {
		var ele = $(this);
		ele.on("click", function() {
			loadDataAnsy(ele, index);
			setCurrentSlide(ele, index);
			swiper_index_content.slideTo(index, 500, false);
			//mySwiper.initialSlide=index;
		});
	});
	
	swiper_index_content = new Swiper('.swiper2', {
		//freeModeSticky  设置为true 滑动会自动贴合  
		direction: 'horizontal',//Slides的滑动方向，可设置水平(horizontal)或垂直(vertical)。
		loop: false,
		//			effect : 'fade',//淡入
		//effect : 'cube',//方块
		//effect : 'coverflow',//3D流
		//			effect : 'flip',//3D翻转
		autoHeight: true,//自动高度。设置为true时，wrapper和container会随着当前slide的高度而发生变化。
		onSlideChangeEnd: function(swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
			var n = swiper.activeIndex;
			setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
			swiper_index_menu.slideTo(n, 500, false);
		}
	});
}

function refreshSwiper(index){
	swiper_index_content.slideTo(index, 500, false);
}

function loadDataAnsy(menuItem,index){
	if(menuItem){
		var url = menuItem.attr("data-url");
		var contentId = menuItem.attr("data-content");
		var contentWrapper = $("#"+contentId);
		var loaded = menuItem.attr("data-loaded");
		var uploadData={};
		if(loaded==1){
			//加载中
			return ;
		}else if(loaded==2){
			//最后一页
			return ;
		}else{
			//执行下述加载，并将状态置为加载中
			menuItem.attr("data-loaded",1);
		}
		axios({
		    method: 'post',
		    url: url,
		    data: Qs.stringify(uploadData)
		})
		.then(function (response) {
			contentWrapper.html(response.data);

			setTimeout(function(){
				refreshSwiper(index);
			},100);

		    //移除通用元素
		    $("#id_hidden_common_success").remove();
		    $("#id_common_respMsg").remove();
		    $("#id_hidden_common_respCode").remove();
		})
		.catch(function (error) {
		    console.log(error);
		});
	}
}

	

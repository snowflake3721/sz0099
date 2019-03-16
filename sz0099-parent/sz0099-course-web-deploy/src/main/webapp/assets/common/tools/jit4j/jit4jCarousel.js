function carouselInit(){
	$('.carousel').carousel({
	  interval: 2500
	})
}

function carouselWrapperInit(wrapperId, time){
	if(!time){
		time=2500;
	}
	carouselInit();
	/*var wrapperDiv=$('#'+wrapperId);
	wrapperDiv.carousel({
		  interval: 2500
	})*/
}
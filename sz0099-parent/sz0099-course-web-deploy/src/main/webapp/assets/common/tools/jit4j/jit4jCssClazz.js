var cssClazz=['default','info','primary','success','warning','danger'];
function getCssClazz(i){
	if(i<cssClazz.length){
		return cssClazz[i];
	}else{
		return cssClazz[cssClazz.length-1];
	}
}
function getRandomCssClazz(){
	return getCssClazz(getRandomNum(0,cssClazz.length));
}

function getRandomNum(min, max) {
    var range = max - min;
    var rand = Math.random();
    return(min + Math.round(rand * range));
}
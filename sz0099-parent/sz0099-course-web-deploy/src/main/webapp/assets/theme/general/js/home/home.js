
$(document).ready(function(){
	var app7 = new Vue({
	  el: '#app',
	  data: {
	    userList: ${userJson},
	    currentUser: ${currentUser}
	    
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

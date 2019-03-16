//	create by nasir farhadi
//	email : nasirfarhadi92@gmail.com
//	Github : nasirfarhadi92

	$(document).ready(function(){
		let blooming3i=1;
		var radius = 250;
		var fields = $('.itemDot');
		var container = $('.dotCircle');
		var width = container.width();
			radius = width/2;
 
		var height = container.height();
		var angle = 0, step = (2*Math.PI) / fields.length;
		fields.each(function() {
			var x = Math.round(width/2 + radius * Math.cos(angle) - $(this).width()/2);
			var y = Math.round(height/2 + radius * Math.sin(angle) - $(this).height()/2);
			if(window.console) {
				console.log($(this).text(), x, y);
			}
			
			$(this).css({
				left: x + 'px',
				top: y + 'px'
			});
			angle += step;
		});
		
		
		$('.itemDot').click(function(){
			
			var dataTab= $(this).data("tab");
			$('.itemDot').removeClass('active');
			$(this).addClass('active');
			$('.CirItem').removeClass('active');
			$( '.CirItem'+ dataTab).addClass('active');
			blooming3i=dataTab;
			
			
			$('.dotCircle').css({
				"transform":"rotate("+(360-(blooming3i-1)*60)+"deg)",
				"transition":"2s",
				
			});
			$('.itemDot').css({
				"transform":"rotate("+((blooming3i-1)*60)+"deg)",
				"transition":"1s"
			});
			
			
		});
		
		setInterval(function(){
			var dataTab= $('.itemDot.active').data("tab");
			if(dataTab>6||blooming3i>6){
				dataTab=1;
				blooming3i=1;
			}
			$('.itemDot').removeClass('active');
			$('[data-tab="'+blooming3i+'"]').addClass('active');
			$('.CirItem').removeClass('active');
			$( '.CirItem'+blooming3i).addClass('active');
			blooming3i++;
			
			$('.dotCircle').css({
				"transform":"rotate("+(360-(blooming3i-2)*60)+"deg)",
				"transition":"2s"
			});
			$('.itemDot').css({
				"transform":"rotate("+((blooming3i-2)*60)+"deg)",
				"transition":"1s"
			});
			
			
		}, 3000);
		
	});




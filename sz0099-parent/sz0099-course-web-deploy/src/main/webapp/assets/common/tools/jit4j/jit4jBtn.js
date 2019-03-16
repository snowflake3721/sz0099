function showBtn(btnId,panelId){
	var btn = $("#"+btnId);
	if(panelId=="#panel_baseinfo"){
		btn.removeClass("hidden");
	}else if(panelId=="#panel_price"){
		btn.removeClass("hidden");
	}else if(panelId=="#contentPhoto"){
		btn.addClass("hidden");
	}else if(panelId=="#contentTag"){
		btn.addClass("hidden");
	}else if(panelId=="#panel_publish"){
		btn.addClass("hidden");
	}else if(panelId=="#panel_position_ref_bind"){
		btn.addClass("hidden");
	}
}
function collapseBind(){
	console.log("........lalala----");
	$('a[id^=id_a_subcategory]').click(function (e) {
	
		e.preventDefault();
	  	var href=$(e.currentTarget).attr("href");
	  	console.log(href);
		
		$(e.currentTarget).on('hidden.bs.collapse', function () {
		  // do something…
			console.log("hidden....");
		})
	});
}


function copyDataToEdit(extendId, id, topId, editWrapperId){
	$("#"+editWrapperId).removeClass("hidden");
	var subOrderSeq = $("#id_category_orderSeq"+id).val();
	var subName = $("#id_category_name"+id).val();
	var subCode = $("#id_category_code"+id).val();
	var subParentId = $("#id_category_parentId"+id).val();
	var parentName = $("#id_category_parent_name"+id).val();
	
	
	$("#id_subcategory_orderSeq"+topId).val(subOrderSeq);
	$("#id_subcategory_name"+topId).val(subName);
	$("#id_subcategory_code"+topId).val(subCode);
	$("#id_subcategory_parentId"+topId).val(parentName);
	$("#hidden_id_subcategory_parentId"+topId).val(subParentId);
	$("#hidden_id_subcategory_id"+topId).val(id);
}

function getDataOfEdit(extendId, id,topId){
	var subOrderSeq=$("#id_subcategory_orderSeq"+topId).val();
	var subName=$("#id_subcategory_name"+topId).val();
	var subCode=$("#id_subcategory_code"+topId).val();
	var subParentId=$("#hidden_id_subcategory_parentId"+topId).val();
	var parentName=$("#id_subcategory_parentId"+topId).val();
	
	$("#id_category_orderSeq"+id).val(subOrderSeq);
	$("#id_category_name"+id).val(subName);
	$("#id_category_code"+id).val(subCode);
	$("#id_category_parentId"+id).val(subParentId);
	$("#id_category_parent_name"+topId).val(parentName);
	var data=getCategory(id);
	/*var data={
			id : id,
			orderSeq : subOrderSeq,
			name : subName,
			code : subCode,
			parentId : subParentId,
			extendId : extendId
	};*/
	return data;
}

function editSingleSub(extendId,id, topId, url, divWrapperId, editWrapperId, messageTipId)
{
	copyDataToEdit(extendId, id, topId,editWrapperId);
}

function doEditSingleSub(extendId,id, topId, url,  messageTipId){
	var data = getDataOfEdit(extendId, id,topId);
	saveSingle(id, url, messageTipId);
	
}
//page
//url链接式分页
function initPage(containerId, urlId, currentPageId, totalPagesId, sizeId, condition){
	
	var containerIdDiv=$('#'+containerId);
	if(containerIdDiv){
		var searchUrl = $("#"+urlId).val();
		var currentPageIdInput = $("#"+currentPageId);
		var pageName = currentPageIdInput.attr("name");
		var pageV=parseInt(currentPageIdInput.val());
		if(pageV==null){
			pageV=0;
		}
		var totalPages=$("#"+totalPagesId).val();
		var size = $("#"+sizeId).val();
		
		if(totalPages>0){
			var options = {
				    currentPage: pageV+1,
				    totalPages: totalPages,
				    numberOfPages: size,
				    size:"normal",
				    bootstrapMajorVersion: 3,
				    pageUrl:function(type, page, current){
						var paramUrl = joinParamForArray(condition);
						if(typeof paramUrl =="undefined"){
							paramUrl="";
						}
				        return searchUrl+"?"+pageName+"="+(page-1)+paramUrl;
				    },
			}
			$('#'+containerId).bootstrapPaginator(options);
		}
	}
	
}

function joinParamForArray(condition){
	var conditionIsArray = isArrayFn(condition);
	var urlparam = "";
	if(conditionIsArray){
		for(var i=0, len = condition.length;i<len;i++ ){
			var conditionId = condition[i];
			if(conditionId){
				var conditionInput = $("#"+conditionId);
				if(conditionInput.length>0){
					var name=conditionInput.attr("name");
					var value=conditionInput.val();
					if(typeof(conditionId) == 'string' && conditionId.indexOf("id_picker")>-1){
						value=value+" 00:00:00";
					}
					var valueEncode=encodeURIComponent(value);
					urlparam += "&"+ name+"="+valueEncode;
				}
			}
		}
		
	}
	return urlparam;
}

function searchPage( urlId, currentPageId, sizeId, condition){
	var searchUrl = $("#"+urlId).val();
	var currentPageInput = $("#"+currentPageId);
	var pageV=currentPageInput.val();
	var page=0;
	if(pageV){
		page=parseInt(pageV);
	}
	var currentPageName=currentPageInput.attr("name");
	
	if(page=='' || page=='NAN'){
		page=0;
	}
	var sizeInput = $("#"+sizeId);
	var size = sizeInput.val();
	var sizeName = sizeInput.attr("name");
	var paramUrl = joinParamForArray(condition);
	if(typeof paramUrl =="undefined"){
		paramUrl="";
	}
	var url=searchUrl+"?"+currentPageName+"="+page+"&"+sizeName+"="+size+paramUrl;
	window.location.href=url;
}
/**
 * 
 * @param wrapperId 数据加载容器
 * @param loadedId 数据加载状态
 * @param urlId 请求url
 * @param currentPageId 当前页码
 * @param sizeId 条数
 * @param totalPageId 总页码
 * @param condition
 * @returns
 */
function searchPageAnsy( wrapperId, loadedId, urlId, currentPageId, sizeId, totalPagesId, condition){
	//校验本次是否正在加载
	var loadedInput=$("#"+loadedId);
	var loaded=loadedInput.val();
	
	if(loaded==1){
		//正在加载,需等待返回
		return ;
	}else if(loaded==2){
		//加载已至最后一页,无需再加载
		return ;
	}else{
		//设置成加载中 ,执行以下加载
		loadedInput.val(1);
	}
	
	var divWrapper=$("#"+wrapperId);
	var searchUrl = $("#"+urlId).val();
	var currentPageInput = $("#"+currentPageId);
	var totalPagesInput = $("#"+totalPagesId);
	var totalPages=totalPagesInput.val();
	
	
	var pageV=currentPageInput.val();
	var page=0;
	if(pageV){
		page=parseInt(pageV);
	}
	var currentPageName=currentPageInput.attr("name");
	
	if(page=='' || page=='NAN'){
		page=0;
	}
	if(page>totalPages){
		return;
	}
	
	var sizeInput = $("#"+sizeId);
	var size = sizeInput.val();
	var sizeName = sizeInput.attr("name");
	var paramUrl = joinParamForArray(condition);
	if(typeof paramUrl =="undefined"){
		paramUrl="";
	}
	var url=searchUrl+"?"+currentPageName+"="+page+"&"+sizeName+"="+size+paramUrl;
	var uploadData={};
	
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
		divWrapper.append(response.data);
		//获取成功标志
		var success = $("#id_hidden_common_success").val();
		if(success==1){
			$("#id_hidden_common_success").remove();
 	    	$("#id_common_respMsg").remove();
 	    	$("#id_hidden_common_respCode").remove();
 	    	// 加载成功，重置为0
 	    	loadedInput.val(0);
 	    	var nextPage=page+1;
 	    	currentPageInput.val(nextPage);
		}
	})
	.catch(function (error) {
		//加载不成功，也要重置为0
		loadedInput.val(0);
	    console.log(error);
	});
}


/** 产品begin***/
//产品查询
function searchCourse(page){
	searchPage("id_search_url","id_currentPage", "id_size", ['id_search_title']);
}

function searchCourseForManage(page){
	searchPage("id_search_url","id_currentPage", "id_size", ['id_search_title','id_search_productNo']);
}

//产品初始化
function initPageForProduct(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_title']);
}

//产品详情列表初始化
function initPageForProductDetailList(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_title','id_search_productNo']);
}
/** 产品end***/

function searchOrder(page){
	searchPage("id_search_url","id_currentPage", "id_size", ['id_search_title','id_picker_orderBegin','id_picker_orderEnd']);
}
//产品初始化
function initPageForOrder(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_title','id_status','id_picker_orderBegin','id_picker_orderEnd']);
}

//技能管理列表初始化
function initPageForProfessionManageList(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_title','id_search_professionNo']);
}

//文章搜索,技能搜索
function searchForCategory(page){
	searchPage("id_search_url","id_currentPage", "id_size", ['id_search_title','hidden_id_search_categoryId','id_cayMainId','id_caySubId']);
}
function initPageForSearchForCategory(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages", "id_size", ['id_search_title','hidden_id_search_categoryId','id_cayMainId','id_caySubId']);
}


//类别搜索
function searchForCategoryFromDetail(page,mainId){
	if(!mainId){
		mainId='';
	}
	searchPage("id_search_url","id_currentPage", "id_size", ['id_search_title','id_data_mainId','hidden_id_search_categoryId'+mainId,'id_cayMainId','id_caySubId','id_search_userId']);
}
function searchForCategoryHidden(page){
	searchPage("id_search_url","id_currentPage", "id_size", ['id_data_mainId','hidden_id_search_categoryId','id_cayMainId','id_caySubId','id_search_userId']);
}
function initForSearchForCategoryFromDetail(mainId){
	if(!mainId){
		mainId='';
	}
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_title','id_data_mainId','hidden_id_search_categoryId'+mainId,'id_cayMainId','id_caySubId','id_search_userId']);
}


function initPageForPraisePage(){
	initPage("pageContainer","id_search_url","id_currentPage", "id_totalPages","id_size", ['id_search_id']);
}

/**
 * 首页，更多按钮
 * @param page
 * @param baseId 所属位置id
 * @returns
 */
function searchHiddenFromHome(page, baseId, idPre){
	if(!baseId){
		baseId='';
	}
	if(!idPre){
		idPre='';
	}
	searchPage(idPre+"id_search_url"+baseId,idPre+"id_page_currentPage"+baseId, idPre+"id_page_size"+baseId, [idPre+'id_data_categoryId'+baseId,idPre+'id_data_categoryCode'+baseId,idPre+'id_data_positionBaseId'+baseId,idPre+'id_data_positionPonPanel'+baseId,idPre+'id_data_positionPonMainId'+baseId, idPre+'id_data_positionPonSubId'+baseId, idPre+'id_data_positionPositionId'+baseId, idPre+'id_data_positionExtendId'+baseId, idPre+'id_data_title'+baseId]);
}
function initPageForSearchHiddenFromHome(baseId, idPre){
	initPage(idPre+"pageContainer_"+baseId,idPre+"id_search_url"+baseId,idPre+"id_page_currentPage"+baseId, idPre+"id_page_totalPages"+baseId,idPre+"id_page_size"+baseId, [idPre+'id_data_categoryId'+baseId,idPre+'id_data_categoryCode'+baseId,idPre+'id_data_positionBaseId'+baseId,idPre+'id_data_positionPonPanel'+baseId,idPre+'id_data_positionPonMainId'+baseId, idPre+'id_data_positionPonSubId'+baseId, idPre+'id_data_positionPositionId'+baseId, idPre+'id_data_positionExtendId'+baseId, idPre+'id_data_title'+baseId]);
}

/**
 * 角色分页
 * @param page
 * @returns
 */
function searchRoleForCategory(page){
	searchPage("id_search_url","id_page_currentPage", "id_page_size", ['id_data_name','hidden_id_data_category']);
}
function initPageForSearchRoleForCategory(){
	initPage("pageContainer", "id_search_url","id_page_currentPage","id_page_totalPages", "id_page_size", ['id_data_name','hidden_id_data_category']);
}

function searchUserRole(page){
	searchPage("id_search_url","id_page_currentPage", "id_page_size", ['id_data_role_name','hidden_id_data_role_code','id_data_user_realname','id_data_user_mobile','id_data_user_identity','id_data_user_wechat_nickname']);
}
function initPageForSearchUserRole(){
	initPage("pageContainer", "id_search_url","id_page_currentPage","id_page_totalPages", "id_page_size", ['id_data_role_name','hidden_id_data_role_code','id_data_user_realname','id_data_user_mobile','id_data_user_identity','id_data_user_wechat_nickname']);
}

/**
 * 首页推荐加载更多
 */
function searchForIndexRecommend(position){
	var wrapperId="id_page_wrapper"+position; 
	var loadedId="id_page_loaded"+position;
	var urlId="id_page_url"+position;
	var currentPageId="id_page_currentPage"+position;
	var sizeId="id_page_size"+position;
	var totalPagesId="id_page_totalPages"+position;
	
	searchPageAnsy(wrapperId, loadedId, urlId, currentPageId, sizeId, totalPagesId, ['id_data_recommend']);
}

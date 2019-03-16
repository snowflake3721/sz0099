<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_order_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_order_common.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForMy.ftl">
<#-- 分页展示订单列表: 待支付、待处理、已发货、已完成、全部 -->
<div class="container " id="body_content">
	
	<@M_navBreadForMy category="order" subCategory=coeOrderBo.status entity=coeOrderBo/>
	<#--  -->
<div class="text-center">
  	<div class="input-group input-daterange">
    	<input id="id_picker_orderBegin" name="beginTime" type="text" class="form-control" value="${DateUtils.formatToString(coeOrderBo.beginTime ,'yyyy-MM-dd')}" data-date-format="yyyy-mm-dd">
    	<div class="input-group-addon">to</div>
    	<input id="id_picker_orderEnd" name="endTime" type="text" class="form-control" value="${DateUtils.formatToString(coeOrderBo.endTime ,'yyyy-MM-dd')}" data-date-format="yyyy-mm-dd">
    </div>
</div>
   
	<div class="row">
		 <div class="col-md-12">
		    <div class="input-group">
		      <input type="hidden" id="id_search_url" name="searchUrl" class="form-control" value="/sz0099/ood/product/order/myCoeOrderList">
		      <input type="hidden" id="id_currentPage" name="page" class="form-control" value="${page.number}">
		      <input type="hidden" id="id_totalPages" name="totalPages" class="form-control" value="${page.totalPages}">
		      <input type="hidden" id="id_size" name="size" class="form-control" value="${page.size}">
		      <input type="hidden" id="id_status" name="status" class="form-control" value="${coeOrderBo.status}">
		      <input type="text" id="id_search_title" name="title" class="form-control" placeholder="大数据,java,python..." value="${coeOrderBo.title}">
		      
		      <span class="input-group-btn">
		        <button class="btn btn-default" id="id_search_btn" type="button" onclick="searchOrder(0)">搜索</button>
		      </span>
		    </div>
	  	 </div>
  	</div>
  	<hr/>
	<@M_coeOrderPage itemPage=page moreLoaded="false" url="/sz0099/ood/product/order/myCoeOrderList" condition=coeOrderBo/> 
	
	<p>新的</p>
	<@M_navBottomForMy category="order" subCategory=coeOrderBo.status entity=coeOrderBo />
	
	<#-- <#include "mobile/template/front/default/sz0099/ood/product/personal/navBottomForMy.ftl" />
	 -->
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.6'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/product/personal/myCoeOrderList.js?v=1.0.13'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/clipboard/clipboard.js?v=1.0.0'><\/script>");</script>
<script type="text/javascript">
$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');	
});	
initPageForOrder();


</script>


 <#--  -->
</div>
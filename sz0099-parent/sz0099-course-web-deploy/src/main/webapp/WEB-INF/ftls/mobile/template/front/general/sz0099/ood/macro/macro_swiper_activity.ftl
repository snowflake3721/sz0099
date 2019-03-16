<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_activity.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_activity.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_activity_list.ftl">
<#macro Mg_swiper_activity dto=null entityPage=null entity=null>
<div class="swiper-container swiper1">
		<div class="swiper-wrapper">
			<div class="swiper-slide selected">推荐</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/all/ansy"
			data-content="id_index_content_activity"
			data-loaded="0"
			>全部</div>
			<div class="swiper-slide" 
			data-url="/sz0099/ood/home/activity/index/flag/ansy"
			data-content="id_index_content_flag"
			data-loaded="0"
			>
			队旗
			</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/foot/ansy"
			data-content="id_index_content_foot"
			data-loaded="0"
			>徒步</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/travel/ansy"
			data-content="id_index_content_travel"
			data-loaded="0"
			>旅行</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/bike/ansy"
			data-content="id_index_content_bike"
			data-loaded="0"
			>骑行</div>
			
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/car/ansy"
			data-content="id_index_content_car"
			data-loaded="0"
			>自驾</div>
			
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/volunteer/ansy"
			data-content="id_index_content_volunteer"
			data-loaded="0"
			>志愿者</div>
			
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/eat/ansy"
			data-content="id_index_content_eat"
			data-loaded="0"
			>聚餐</div>
			
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/pick/ansy"
			data-content="id_index_content_pick"
			data-loaded="0"
			>采摘</div>
			
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/activity/index/welfare/ansy"
			data-content="id_index_content_welfare"
			data-loaded="0"
			>公益</div>
			
		</div>
	</div>
	<!-- swiper2 -->
	<div class="swiper-container swiper2">
		<div class="swiper-wrapper">
			<div class="swiper-slide swiper-no-swiping">
				<@Mg_headRoller dto=dto idPre="ly_index_recommend"/>
				
				<@Mg_recommendPageWrapper itemPage=entityPage condition=entity/>
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_activity">活动 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_flag">
			队旗活动 加载中...
			</div>
			
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_foot">
			徒步活动 加载中...
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_travel">旅行活动 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_bike">骑行活动 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_car">自驾 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_volunteer">志愿者 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_eat">聚餐 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_pick">采摘 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_welfare">公益 加载中...</div>
		</div>
	</div>
</#macro>

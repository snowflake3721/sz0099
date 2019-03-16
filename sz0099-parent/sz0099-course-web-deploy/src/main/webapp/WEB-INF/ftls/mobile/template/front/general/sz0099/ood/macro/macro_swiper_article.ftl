<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_article.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_article_list.ftl">
<#macro Mg_swiper_article dto=null entityPage=null entity=null>
<div class="swiper-container swiper1">
		<div class="swiper-wrapper">
			<div class="swiper-slide selected">推荐</div>
			<div class="swiper-slide" 
			data-url="/sz0099/ood/home/article/index/flag/ansy"
			data-content="id_index_content_flag"
			data-loaded="0"
			>
			队旗
			</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/foot/ansy"
			data-content="id_index_content_foot"
			data-loaded="0"
			>徒步</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/travel/ansy"
			data-content="id_index_content_travel"
			data-loaded="0"
			>旅行</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/bike/ansy"
			data-content="id_index_content_bike"
			data-loaded="0"
			>骑行</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/roadline/ansy"
			data-content="id_index_content_roadline"
			data-loaded="0"
			>线路</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/equip/ansy"
			data-content="id_index_content_equip"
			data-loaded="0"
			>装备</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/eat/ansy"
			data-content="id_index_content_eat"
			data-loaded="0"
			>美食</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/article/index/welfare/ansy"
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
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_flag">
			群侠 加载中...
			</div>
			
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_foot">
			徒步 加载中...
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_travel">旅行 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_bike">骑行 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_roadline">线路 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_equip">装备 加载中...</div>
			<div class="swiper-slide swiper-no-swiping text-center" id="id_index_content_eat">美食 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_welfare">公益 加载中...</div>
		</div>
	</div>
</#macro>

<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_profession.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout_home_profession.ftl">
<#include "/mobile/template/front/general/sz0099/ood/macro/g_macro_coe_profession_list.ftl">

<#macro Mgp_swiper_profession dto=null entityPage=null entity=null>
<div class="swiper-container swiper1">
		<div class="swiper-wrapper">
			<div class="swiper-slide selected">推荐</div>
			<div class="swiper-slide" 
			data-url="/sz0099/ood/home/profession/index/major/ansy"
			data-content="id_index_content_major"
			data-loaded="0"
			>
			八仙过海
			</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/outdoor/ansy"
			data-content="id_index_content_outdoor"
			data-loaded="0"
			>出行</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/life/ansy"
			data-content="id_index_content_life"
			data-loaded="0"
			>生活</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/ingenuity/ansy"
			data-content="id_index_content_ingenuity"
			data-loaded="0"
			>匠心</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/relax/ansy"
			data-content="id_index_content_relax"
			data-loaded="0"
			>乐趣</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/native/ansy"
			data-content="id_index_content_native"
			data-loaded="0"
			>特产</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/equip/ansy"
			data-content="id_index_content_equip"
			data-loaded="0"
			>装备</div>
			<div class="swiper-slide"
			data-url="/sz0099/ood/home/profession/index/factory/ansy"
			data-content="id_index_content_factory"
			data-loaded="0"
			>直供</div>
		</div>
	</div>
	<!-- swiper2 -->
	<div class="swiper-container swiper2">
		<div class="swiper-wrapper">
			<div class="swiper-slide swiper-no-swiping">
				<@Mg_headRoller dto=dto idPre="ly_index_recommend"/>
				<@Mgp_recommendPageWrapper itemPage=entityPage condition=entity/>
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_major">
			群侠技能 加载中...
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_outdoor">
			出行技能 加载中...
			</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_life">生活 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_ingenuity">独具匠心 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_relax">乐趣 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_native">特产 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_equip">装备 加载中...</div>
			<div class="swiper-slide swiper-no-swiping" id="id_index_content_factory">直供 加载中...</div>
			<#-- 
			<div class="swiper-slide swiper-no-swiping text-center">活动召集模块正在研发中●●●</div>
			 -->
		</div>
	</div>
</#macro>


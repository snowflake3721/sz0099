<#include "mobile/template/front/general/macro/macro_help.ftl">
<#macro Mga_introduction>
<div class="jumbotron">
	  <h3>
	 	活动●简介
	  </h3>
	  <#-- 卓玛拉山公众平台是一个专为户外圈友打造的社交平台，美景实拍、户外记实、实况直传，技能共享、资源合作，【群侠户外】圈友入驻 -->
	  <p class="bg-success"><font size="3" color="black">&nbsp;&nbsp;&nbsp;&nbsp;发布活动，带领伙伴走你熟悉的线路，人人皆可当领队！完善的认证体系，保障领队优质可靠！</font></p>
	  <div class="row">
	  	<div class="col-xs-8">
		  	<a href="/help/readme?st=general" class="text-primary"><small>入圈须知●必读</small></a>
		  	<br/>
		  	<@shiro.user>
		  	<#assign user=UserUtils.getUser()/>
		  	<#assign agree=user.agree/>
			<#if agree != User.AGREE_1.valueInt>
		  	<@M_agree user=user/>
		  	</#if>
		  	</@shiro.user>
		</div>
		<div class="col-xs-4">
			<a href="javascript:void(0)" class="text-danger pull-right" onclick="popForIndex('合作入驻说明','拟订中，请稍候来访，或加微信咨询！','微信号： ly275060435 [阿清哥]<br/>@卓玛拉科技')"><small>合作入驻</small></a>
		</div>
	  </div><#-- end row -->
</div>
<@M_dramala_wechat />
</#macro>


<#macro M_introduction>
<div class="jumbotron">
	  <h3>
	 	简介
	  </h3>
	  <#-- 卓玛拉山公众平台是一个专为户外圈友打造的社交平台，美景实拍、户外记实、实况直传，技能共享、资源合作，【群侠户外】圈友入驻 -->
	  <#-- 我喜欢旅行，喜欢户外，喜欢探索未知，喜欢看风景，喜欢挖掘那些藏于民间的特产或美味，行走天涯，浪荡游侠，不同的地方有不同的特色，自得其乐，分享与人；如果你也喜欢，欢迎一起，“群侠”闯神州 -->
	  <p class="bg-success"><font size="3" color="black">&nbsp;&nbsp;&nbsp;&nbsp;这是一个户外分享的圈子社区，"群聚一起，侠义出行"</font></p>
	  <div class="row">
	  	<div class="col-xs-8">
		  	<a href="/help/readme?st=general" class="text-primary"><small>入圈须知●必读</small></a>
		  	<br/>
		  	<@shiro.user>
		  	<#assign user=UserUtils.getUser()/>
		  	<#assign agree=user.agree/>
			<#if agree != User.AGREE_1.valueInt>
		  	<@M_agree user=user/>
		  	</#if>
		  	</@shiro.user>
		</div>
		<div class="col-xs-4">
			<a href="javascript:void(0)" class="text-danger pull-right" onclick="popForIndex('合作入驻说明','拟订中，请稍候来访，或加微信咨询！','微信号： ly275060435 [阿清哥]<br/>@卓玛拉科技')"><small>合作入驻</small></a>
		</div>
	  </div><#-- end row -->
</div>
<@M_dramala_wechat />
</#macro>
<#macro M_dramala_wechat>
<div class="row">
		  <div class="col-xs-5 col-md-5 text-danger">
		  		  <h3 class="text-danger">关注公众号</h3>
		  		  <p class="text-danger">
		    <a href="javascript:void(0)" class="thumbnail" id="id_a_qr_dramala">
		      <img src="/assets/common/images/dramala_qr.jpg" alt="卓玛拉山" class="img-rounded">
		    </a>
		    </p>
		  </div>
		  <div class="col-xs-7 col-md-7">
		  	<h4>及时关注公众号，方便再次登入</h4>
		    <p>【卓玛拉山】公众号,点<span class="text-danger"><strong> 社区 </strong></span> 进入 </p>
		    <p>
		    	<a href="/help/addToDesktop">添至桌面[android]</a>
		    	<br/>
		    	<a href="/help/topInWechat" class="text-danger">置顶公众号[wechat]</a>
		    </p>
		    <p>客服微信: ly275060435 </p>
		    <p>客服QQ: 275060435<br/> </p>
		  </div>
</div>
</#macro>
<#macro Mgp_introduction>
<div class="jumbotron">
	  <h3>
	 	技能简介
	  </h3>
	  <p class="bg-success">
	  <font size="3" color="black"><strong>偶然间相识，需要时相助!</strong><br/>
	  <#-- “技能”圈是卓玛拉山公众平台是为圈友提供的个人技能（包括但不限于技能、服务、资源）发布场所，以利圈友之间互惠合作。<br/>合理公道是本圈的追求，优质、物美、高效是本圈推崇，良好口碑是长久之策，杜绝劣质欺骗是本圈之规矩，违规者必逐出圈外 -->
	  “技能”圈用于发布你所能提供的（技能、服务、资源），特产走出深山，服务用于圈友，朴实真诚相交，互惠合作相助！
	  </font>
	  </p>
	  <div class="row">
	  	<div class="col-xs-8">
		  	<a href="/help/readme?st=general" class="text-primary"><small>入圈须知●必读</small></a>
		  	<br/>
		  	<@shiro.user>
		  	<#assign user=UserUtils.getUser()/>
		  	<#assign agree=user.agree/>
			<#if agree != User.AGREE_1.valueInt>
		  	<@M_agree user=user/>
		  	</#if>
		  	</@shiro.user>
		</div>
		<div class="col-xs-4">
			<a href="javascript:void(0)" class="text-danger pull-right" onclick="popForIndex('合作入驻说明','拟订中，请稍候来访，或加微信咨询！','微信号： ly275060435 [阿清哥]<br/>@卓玛拉科技')"><small>合作入驻</small></a>
		</div>
	  </div><#-- end row -->
</div>
<@M_dramala_wechat />
</#macro>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_list.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_article_category_search.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dramala_wechat.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_navBottomForIndex.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_layout_article.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/default/macro/macro_help.ftl">
<html>
<head>

<title>群侠户外</title>
<link href="/assets/common/tools/blooming-menu3/css/circle.css?v=1.0.1" media="all" rel="stylesheet" type="text/css" />
<#-- 
<link href="/assets/common/tools/blooming-menu3/css/normalize.css" media="all" rel="stylesheet" type="text/css" />
 -->
</head>

<body>

<div class="container " id="body_content">
<div data-spy="scroll" data-target="#id_profession_personal">
	<div class="jumbotron">
	  <h3 class="text-center">
	  <@shiro.user>
 	 欢迎大神 <strong><span class="text-success bg-info" onclick="showSaywordOwn('${currentUser.nickname}','${currentUser.sayword.description}')">≡ <@shiro.principal property="nickname"/> ≡</strong></span>
	</@shiro.user>
	<@shiro.guest>
    <a href = "/basic/autologincheckUI">游客访问 </a>
	</@shiro.guest>
	  </h3>
	  <p class="bg-success"><font size="3" color="black">卓玛拉山公众平台是一个专为户外圈友打造的社交平台，美景实拍、户外记实、实况直传，技能共享、资源合作，【群侠户外】、【雪峰户外】圈友入驻</font></p>
	  <div class="row">
	  	<div class="col-xs-8">
		  	<a href="/help/readme" class="text-primary"><small>入圈须知●必读</small></a>
		  	<a href="javascript:void(0)" class="text-danger pull-right" onclick="popForIndex('合作入驻说明','拟订中，请稍候来访，或加微信咨询！','微信号： ly275060435 [阿清哥]<br/>@卓玛拉科技')"><small>合作入驻</small></a>
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
  			<a class="btn btn-danger btn-lg" href="/sz0099/ood/home/article/index" role="button">进入圈里</a>
  			
  		</div>
	  </div><#-- end row -->
	</div>
	<p><small>您可发布美景、攻略、技能、实用、合作、群聚等有实质内容的篇章，禁发灌水无用、禁发黄赌毒类、禁发政治敏感类、禁发言辞污秽类篇章，<span class="text-danger">违者将‘送你离开，踢出圈外’， 切记！</span></small></p>
		<div class="holderCircle">
	        <div class="dotCircle">
	            <span class="itemDot active itemDot1" data-tab="1" onclick="showFlag('id_hidden_fetch_load_qx','id_content_load_qx')">
	               <#--  <i class="fa fa-life-ring"></i>-->
    				<input type="hidden" id="id_hidden_fetch_load_qx" 
    				value="0" 
    				data-url="/sz0099/ood/home/fetchIndexFlagQX" 
    				data-urlConfirm="/sz0099/ood/home/article/index" 
    				data-mainId="${Robot.ROBOT_PLAT.id}" 
    				data-subId="${Robot.ROBOT_PLAT.id}" 
    				data-ponPanel="${Position.PANEL_6_FLAG_QX.valueInt}"
    				data-title="群侠大旗-最新一站"
    				data-btnConfirm="进入群侠户外"
    				data-btnCssClass="hidden btn-success"
    				data-btnType="BootstrapDialog.TYPE_SUCCESS"
    				/>
	                <a href="/sz0099/ood/home/article/index" >
	                <img src="/assets/common/tools/blooming-menu3/img/qunxia_2_1.jpg" class="img-circle img-responsive" title="群侠户外"/>
	                <span class="forActive"></span>
	                </a>
	                <div class="hidden" id="id_content_load_qx">
	                </div>
	            </span>
	            <span class="itemDot itemDot2" data-tab="2" onclick="showFlag('id_hidden_fetch_load_xf','id_content_load_xf')">
	               <#--  <i class="fa fa-bomb"></i>-->
	               <input type="hidden" id="id_hidden_fetch_load_xf" 
    				value="0" 
    				data-url="/sz0099/ood/home/fetchIndexFlagXF" 
    				data-mainId="${Robot.ROBOT_PLAT.id}" 
    				data-subId="${Robot.ROBOT_PLAT.id}" 
    				data-title="雪峰大旗-最新一站"
    				data-btnConfirm="进入雪峰户外"
    				data-btnCssClass="hidden btn-success"
    				data-btnType="BootstrapDialog.TYPE_SUCCESS"
    				/>
	                <img src="/assets/common/tools/blooming-menu3/img/xuefeng_2_1.jpg" class="img-circle img-responsive" width="72px" title="雪峰户外"/>
	                <span class="forActive"></span>
	                <div class="hidden" id="id_content_load_xf">
	                </div>
	            </span>
	            <span class="itemDot itemDot3" data-tab="3" onclick="showFlag('id_hidden_fetch_load_recommend','id_content_load_recommend')">
	               <#--  <i class="fa fa-heartbeat"></i>-->
	               <input type="hidden" id="id_hidden_fetch_load_recommend" 
    				value="0" 
    				data-url="/sz0099/ood/home/fetchIndexRecommend" 
    				data-mainId="${Robot.ROBOT_PLAT.id}" 
    				data-subId="${Robot.ROBOT_PLAT.id}" 
    				data-title="技能展示-资源合作"
    				data-btnConfirm="进入技能圈"
    				data-btnCssClass="hidden btn-success"
    				data-btnType="BootstrapDialog.TYPE_SUCCESS"
    				/>
	                <img src="/assets/common/tools/blooming-menu3/img/profession_2_1.jpg" class="img-circle img-responsive" title="资源技能合作"/>
	                <span class="forActive"></span>
	                <div class="hidden" id="id_content_load_recommend">
	                </div>
	            </span>
	            <span class="itemDot itemDot4" data-tab="4" onclick="showFlag('id_hidden_fetch_load_roadLine','id_content_load_roadLine')">
	               <#--  <i class="fa fa-leaf"></i>-->
	               <input type="hidden" id="id_hidden_fetch_load_roadLine" 
    				value="0" 
    				data-url="/sz0099/ood/home/fetchIndexRoadLine" 
    				data-mainId="${Robot.ROBOT_PLAT.id}" 
    				data-subId="${Robot.ROBOT_PLAT.id}" 
    				data-title="路线-探路行动"
    				data-btnConfirm="进入线路攻略"
    				data-btnCssClass="hidden btn-success"
    				data-btnType="BootstrapDialog.TYPE_SUCCESS"
    				/>
	                <img src="/assets/common/tools/blooming-menu3/img/roadline_2_0.gif" class="img-circle img-responsive" width="256px" height="256px" style="position:absolute;top:10px" title="路线征途"/>
	                <span class="forActive"></span>
	                <div class="hidden" id="id_content_load_roadLine">
	                </div>
	            </span>
	            <span class="itemDot itemDot5" data-tab="5" id="id_span5_sayword" onclick="popBigViewForSayword('${currentUser.nickname}','${currentUser.sayword.description}','/assets/common/tools/blooming-menu3/img/chuanshuo_2_1.gif','id_span5_sayword')" data-url="/sz0099/ood/home/article/index" data-title-a="去看看大神们的传说" data-title="户外有很多传说">
	                <#-- <i class="fa fa-magic"></i> -->
	                <img src="/assets/common/tools/blooming-menu3/img/chuanshuo_2_1.gif" class="img-circle img-responsive" title="户外传说" style="z-index:180;" />
	                <span class="forActive"></span>
	                
	            </span>
	            <span class="itemDot itemDot6" data-tab="6" id="id_span6_jiangxin" onclick="showFlag('id_hidden_fetch_load_ingenuity','id_content_load_ingenuity')">
	               <#--  <i class="fa fa-pencil-square-o"></i> -->
	               <input type="hidden" id="id_hidden_fetch_load_ingenuity" 
    				value="0" 
    				data-url="/sz0099/ood/home/fetchIndexIngenuity" 
    				data-urlConfirm="/sz0099/ood/home/profession/index" 
    				data-mainId="${Robot.ROBOT_PLAT.id}" 
    				data-subId="${Robot.ROBOT_PLAT.id}" 
    				data-ponPanel="${Position.PANEL_6_FLAG_QX.valueInt}"
    				data-title="独具匠心"
    				data-btnConfirm="进入独具匠心"
    				data-btnCssClass="hidden btn-success"
    				data-btnType="BootstrapDialog.TYPE_SUCCESS"
    				/>
	                <img src="/assets/common/tools/blooming-menu3/img/jiangxin_2_1.jpg" class="img-circle img-responsive" title="独具匠心" />
	                <span class="forActive"></span>
	                <div id="id_content_load_ingenuity" class="hidden" data-title="独具匠心" data-btnConfirm="进入独具匠心">
	                	
	                </div>
	            </span>
	            
	        </div>
	
	        <div class="contentCircle" >
	            <div class="CirItem active CirItem1" style="background:url('/assets/common/tools/blooming-menu3/img/footing.gif');background-repeat:no-repeat;background-origin:content-box;background-position:center bottom;background-size:200px 140px;">
	                <p class="contentText" style="top: 6%;">
	                <a href="/sz0099/ood/home/article/index" class="btn btn-danger" onclick="popForIndex('群侠户外','群山征途，侠照苍穹','即将自动进入...')">群侠户外</a>
	                </p>
	            </div>
	            <div class="CirItem CirItem2" style="background:url('/assets/common/tools/blooming-menu3/img/xuefeng_3_1.jpg');background-repeat:no-repeat;background-origin:content-box;background-position:center;background-size:150px 150px;">
	                <p class="contentText">
	                <a href="/sz0099/ood/home/article/index" class="btn btn-info" onclick="popForIndex('雪峰户外','雪山飞狐，峰光无限','即将自动进入...')">雪峰户外</a>
	                </p>
	            </div>
	            <div class="CirItem CirItem3" style="background:url('/assets/common/tools/blooming-menu3/img/profession_2_1.gif');background-repeat:no-repeat;background-origin:content-box;background-position:center;">
	                <p class="contentText">
	                <a href="/sz0099/ood/home/profession/index" class="btn btn-success" onclick="popForIndex('群侠技能','技能专属，资源互惠，需求合作','即将自动进入...')">技能圈</a>
	                </p>
	            </div>
	            <div class="CirItem CirItem4" style="background:url('/assets/common/tools/blooming-menu3/img/zang_2_3.jpg');background-repeat:no-repeat;background-origin:content-box;background-position:center; background-size:180px 140px;">
	                <p class="contentText"><a href="/sz0099/ood/article/searchForCategory?baseId=239654757366198272" class="btn btn-primary" onclick="popForIndex('户外线路','线路攻略，充分准备，即刻出发','即将自动进入...')">线&nbsp;路</a></p>
	            </div>
	            <div class="CirItem CirItem5" style="background:url('/assets/common/tools/blooming-menu3/img/chuanshuo_taiji_3_3.jpg');background-repeat:no-repeat;background-origin:content-box;background-position:center; background-size:150px 150px;">
	                <p class="contentText" ><a href="javascript:void(0)" id="id_a_saywork" data-url="/sz0099/ood/home/article/index" class="btn btn-warning" onclick="showSaywordIndex('${currentUser.nickname}','${currentUser.sayword.description}','id_a_saywork')">传&nbsp;说</a></p>
	            </div>
	            <div class="CirItem CirItem6" style="background:url('/assets/common/tools/blooming-menu3/img/jiangxin_3_1.jpg');background-repeat:no-repeat;background-origin:content-box;background-position:center;background-size:150px 150px;">
	                <p class="contentText"><a href="/sz0099/ood/home/profession/index" class="btn btn-danger" onclick="popForIndex('匠心独造','大自然有鬼斧神工<br/>人世间有能工巧匠','即将自动进入...')">匠&nbsp;心</a></p>
	            </div>
	        </div>
	    </div><!--end circle-->
	
	
	<@M_dramala_wechat/>
</div>
</div>

<div class="container" id="body_footer_js">	
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/commonPage.js?v=1.0.8'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/common/tools/blooming-menu3/js/circle.js?v=1.0.2'><\/script>");</script>
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/common/fetchByPosition.js?v=1.0.2'><\/script>");</script>
<#-- 
<script>document.write("<script type='text/javascript' src='/assets/theme/default/sz0099/ood/user/infocommon.js?v=1.0.2'><\/script>");</script>
 -->
<script type="text/javascript">
	$(document).ready(function(){
	autoCurrentOauthPageUrlLogin('${login_status}');
});
</script>
</div>
</body>
</html>
<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_siteDataInit.ftl">
<html>
<head>

<title>系统初始化-卓玛拉山联盟</title>
</head>

<body>

<div class="container" id="top_login">
</div>

<div class="container" id="top_menu">
	<h4 class="text-danger">数据初始化步骤：</h4>
	<button type="button" class="btn btn-warning btn-sm text-right">1.基本数据初始化</button>
	<button type="button" class="btn btn-warning btn-sm text-right">2.相关数据初始化</button>
</div>

<div class="container" id="body_content">

<@M_siteDataInitList dataInitList=content />

	<div class="row">
			<div class="col-xs-8 text-left">
			 	<button type="button" class="btn btn-warning btn-sm text-right">1.文章分类数据初始化...</button>
			</div>
			<div class="col-xs-4 text-right">
				<span></span>
			</div>
	</div><!--row end-->
</div>

<div class="container" id="body_footer">

</div>

<div class="container" id="body_footer_js">
<script type="text/javascript">
	<!--
	
	$(document).ready(function(){
		var app8 = new Vue({
		  el: '#app',
		  data: {
		    	
		  },
		  computed:{
                email:function(){
                    //业务逻辑代码
                    var showEmail = this.currentUser.email.substring(0,6);
                    return showEmail;
                }
		    	
            }
		});
		
	});
	//-->
</script>
</div>
</body>
</html>
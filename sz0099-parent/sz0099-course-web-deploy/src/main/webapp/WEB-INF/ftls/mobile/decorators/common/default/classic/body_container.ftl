

<body>
<div class="container" id="body_header">
	<div class="container">
		<sitemesh:write property="div.top_login"/>	
	</div>
	<div class="container">
		  <sitemesh:write property="div.top_banner"/>
	</div>
	<div class="container">
		  <sitemesh:write property="div.top_menu"/>
	</div>
</div>

<div class="container" id="body_content">
	<div class="container">
		  <sitemesh:write property="div.body_content"/>
	</div>
	<div class="container">
		  <sitemesh:write property="div.body_bottom"/>
	</div>
</div>

<div class="container" id="body_footer">
	<div class="container">
		  <sitemesh:write property="div.body_footer"/>
	</div>
</div>

<div class="container" id="body_footer_js">
	<sitemesh:write property="div.body_footer_js"/>
</div>
</body>
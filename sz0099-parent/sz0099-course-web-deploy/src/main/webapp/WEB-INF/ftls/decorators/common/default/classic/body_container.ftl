

<body>

	<div id="top"></div>
	<div id="body_header">
		
			  <sitemesh:write property="div.top_banner"/>
			<sitemesh:write property="div.top_login"/>	
			  <sitemesh:write property="div.top_menu"/>
	</div>
	
	<div id="body_content">
			  <sitemesh:write property="div.body_content"/>
			  <sitemesh:write property="div.body_bottom"/>
	</div>
	
	<div id="body_footer">
		<sitemesh:write property="div.body_footer"/>
	</div>
	
	<div id="body_footer_js">
		<sitemesh:write property="div.body_footer_js"/>
	</div>
</body>
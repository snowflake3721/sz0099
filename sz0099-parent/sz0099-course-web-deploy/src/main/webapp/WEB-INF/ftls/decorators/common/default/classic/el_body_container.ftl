

<body>

	<div id="top"></div>
	<div id="body_header">
		<div>
			<sitemesh:write property="div.top_login"/>	
		</div>
		<div>
			  <sitemesh:write property="div.top_banner"/>
		</div>
		<div>
			  <sitemesh:write property="div.top_menu"/>
		</div>
	</div>
	
	<div id="body_content">
		<div>
			  <sitemesh:write property="div.body_content"/>
		</div>
		<div>
			  <sitemesh:write property="div.body_bottom"/>
		</div>
	</div>
	
	<div id="body_footer">
		<div>
			  <sitemesh:write property="div.body_footer"/>
		</div>
	</div>
	
	<div id="body_footer_js">
		<sitemesh:write property="div.body_footer_js"/>
	</div>
</body>
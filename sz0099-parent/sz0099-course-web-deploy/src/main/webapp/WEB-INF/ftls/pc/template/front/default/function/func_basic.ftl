<#function getOddEvenClazz index>
	<#assign currentClazz="info" />
		<#if index%2==0>
			<#assign currentClazz="warning" />
		</#if>
	<#return currentClazz />
</#function>
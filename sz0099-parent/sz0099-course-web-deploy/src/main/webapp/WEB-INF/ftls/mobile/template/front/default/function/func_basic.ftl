<#function getOddEvenClazz index>
	<#assign currentClazz="info" />
		<#if index%2==0>
			<#assign currentClazz="warning" />
		</#if>
	<#return currentClazz />
</#function>

<#function getRandomClazz index>
	<#assign currentClazz="info" />
		<#if index%2==0>
			<#assign currentClazz="warning" />
		</#if>
	<#return currentClazz />
</#function>

<#function getClazzByDepth depth>
	<#assign currentClazz="warning" />
		<#if depth==0>
			<#assign currentClazz="default" />
			<#return currentClazz />
		<#elseif depth==1>
			<#assign currentClazz="info" />
			<#return currentClazz />
		<#elseif depth==2>
			<#assign currentClazz="warning" />
			<#return currentClazz />
		<#elseif depth==3>
			<#assign currentClazz="danger" />
			<#return currentClazz />
		<#elseif depth==4>
			<#assign currentClazz="primary" />
			<#return currentClazz />
		<#elseif depth==5>
			<#assign currentClazz="success" />
			<#return currentClazz />
		</#if>
	<#return currentClazz />
</#function>


<#function getSubstring content=null maxLength=10 defaultV=null>
	<#assign msg=defaultV />
		<#if content??>
			<#assign length=content?length>
			<#if length gt maxLength>
				<#assign msg=content[0..maxLength]+"..." />
				<#else>
				<#assign msg=content/>
			</#if>
		</#if>
	<#return msg />
</#function>

<#function getLink entity=null defaultLink="/">
	<#assign link=defaultLink />
	<#if entity??>
		<#assign link=entity.link />
		<#if link??>
			<#assign link=entity.originalLink />
			<#else>
			<#assign link=defaultLink />
		</#if>
	</#if>
	<#return link />
</#function>

<#function getSplitIndexForList contentList defaultIndex>
<#if defaultIndex==null>
<#assign defaultIndex=3 />
</#if>
<#if contentList??>
	<#assign size=contentList?size />
	<#if size gte defaultIndex>
		<#return defaultIndex />
		<#else>
		<#return size />
	</#if>
<#else>
<#return -1 />
</#if>
</#function>

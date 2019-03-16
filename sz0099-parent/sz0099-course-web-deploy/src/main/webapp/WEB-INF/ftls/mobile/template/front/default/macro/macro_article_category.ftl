<#macro MV_categorySelect articleCategory=null>

<div class="panel panel-success">
	  <div class="panel-heading">
	    	<h3 class="panel-title">选择文章归类:<span class="text-warning">【{{selectedCategoryName}}】</span></h3>
	  </div>
	  <div class="panel-body">
	  <#if articleCategory??>
	  	  <#assign categoryList=articleCategory.children >
		  <#if categoryList??>
				  <#list categoryList as category>
				  <button type="button" class="btn btn-default" v-on:click="asignCategory('${category.id}')" id="id_btn_category_${category.id}">${category.name}</button>
				  </#list>
		  </#if>
	  <#else>
	      没有分类
	  </#if>
	  </div>
</div>
</#macro>
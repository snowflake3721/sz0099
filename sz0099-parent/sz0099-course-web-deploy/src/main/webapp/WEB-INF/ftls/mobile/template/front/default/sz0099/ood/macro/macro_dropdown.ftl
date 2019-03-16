<#macro M_dropdownBarStr id="id1_" propertyContext=null current=null propertyName=null propertyEnable=false readonly=true showLabel=true instructionDivId="instructionDivId" instructionHtml="">
<#if propertyContext! && propertyContext??>
<#if showLabel>
<label for="${id}${propertyContext.property}">${propertyContext.inputLabel}  <span class="text-primary"><strong>${instructionHtml}</strong></span></label>
</#if>
<div class="input-group input-group-sm">
    <input type="text" id="${id}${propertyContext.property}" <#if readonly>readonly="true"</#if> class="form-control" value="${current.label}" placeholder="${propertyContext.placeHolder}">
    <#if propertyEnable>
    <input type="hidden" id="hidden_${id}${propertyContext.property}" name="${propertyName}" value="${current.valueStr}">
    <#else>
    <input type="hidden" id="hidden_${id}${propertyContext.property}" name="${propertyContext.property}" value="${current.valueStr}">
    </#if>
     
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    <span>${propertyContext.nameBar}</span> <span class="caret"></span>
	  </button>
	  
	  <#assign labelList=propertyContext.labelList/>
	  <ul id="${id}${propertyContext.property}" class="dropdown-menu dropdown-menu-right">
	  
	   <#if labelList?size gt 0>
	  	<#list labelList as value>
	    <li class="<#if current?? && current.valueInt==value.valueInt>active</#if>" onclick="labelSelect_${propertyContext.property}(this,'${value.valueStr}','${value.label}')"><small><span class="bg-info"><strong>${value.label}</strong> -- ${value.valueStr}</span></small></li>
	    </#list>
	    </#if>
	     
	  </ul>
	  
	</div>
	
  </div>
  <div id="${instructionDivId}" class="hidden">
	  <div class="list-group text-warning">
	    <#if labelList?size gt 0>
	   		 <a href="javascript:void(0)" class="list-group-item active">
			    <h4 class="list-group-item-heading">${propertyContext.inputLabel}</h4>
			  	<p>${propertyContext.description}</p>
			  </a>
		  	<#list labelList as value>
			  <a href="javascript:void(0)" class="list-group-item">
			    <h4 class="list-group-item-heading">${value.label}</h4>
			    <p class="list-group-item-text">${value.description} <#if value.col1??>${value.col1}</#if> <#if value.col2??>${value.col2}</#if></p>
			  </a>
		  	</#list>
	    </#if>
	  </div>
  </div>
  <script>
  function labelSelect_${propertyContext.property}(liSelft, value, label){
  		var d_p_v="${id}${propertyContext.property}";
  		$("#hidden_"+d_p_v).val(value);
  		$("#"+d_p_v).val(label);
  		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
  		$(liSelft).addClass="active";
  }
  </script>
</#if>
</#macro>


<#macro M_dropdownBar id="id1_" propertyContext=null current=null readonly=true showLabel=true instructionDivId="instructionDivId" instructionHtml="">
<#if propertyContext! && propertyContext??>
<#if showLabel>
<label for="${id}${propertyContext.property}">${propertyContext.inputLabel}  <span class="text-primary"><strong>${instructionHtml}</strong></span></label>
</#if>
  <div class="input-group input-group-sm">
    <input type="text" id="${id}${propertyContext.property}" <#if readonly>readonly="true"</#if> class="form-control" value="${current.label}" placeholder="${propertyContext.placeHolder}">
    <input type="hidden" id="hidden_${id}${propertyContext.property}" name="${propertyContext.property}" value="${current.valueInt}">
    
     
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    <span>${propertyContext.nameBar}</span> <span class="caret"></span>
	  </button>
	  
	  <#assign labelList=propertyContext.labelList/>
	  <ul id="${id}${propertyContext.property}" class="dropdown-menu dropdown-menu-right">
	  
	   <#if labelList?size gt 0>
	  	<#list labelList as value>
	    <li class="<#if current?? && current.valueInt==value.valueInt>active</#if>" onclick="labelSelect_${propertyContext.property}(this,'${value.valueInt}','${value.label}')">${value.label} -- ${value.valueInt}</li>
	    </#list>
	    </#if>
	     
	  </ul>
	  
	</div>
	
  </div>
  <div id="${instructionDivId}" class="hidden">
	  <div class="list-group text-warning">
	    <#if labelList?size gt 0>
	   		 <a href="javascript:void(0)" class="list-group-item active">
			    <h4 class="list-group-item-heading">${propertyContext.inputLabel}</h4>
			  	<p>${propertyContext.description}</p>
			  </a>
		  	<#list labelList as value>
			  <a href="javascript:void(0)" class="list-group-item">
			    <h4 class="list-group-item-heading">${value.label}</h4>
			    <p class="list-group-item-text">${value.description} <#if value.col1??>${value.col1}</#if> <#if value.col2??>${value.col2}</#if></p>
			  </a>
		  	</#list>
	    </#if>
	  </div>
  </div>
  <script>
  function labelSelect_${propertyContext.property}(liSelft, value, label){
  		var d_p_v="${id}${propertyContext.property}";
  		$("#hidden_"+d_p_v).val(value);
  		$("#"+d_p_v).val(label);
  		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
  		$(liSelft).addClass="active";
  }
  </script>
</#if>
</#macro>

<#macro M_dropdownBarSame idPre="id1_" entityId="" propertyContext=null current=null readonly=true instructionDivId="instructionDivId" instructionHtml="">
<@M_dropdownBarDiff idPre=idPre entityId=entityId propertyInput=propertyContext.property propertyContext=propertyContext current=current readonly=readonly instructionDivId=instructionDivId instructionHtml=instructionHtml />
</#macro>

<#macro M_dropdownBarDiff idPre="id1_" entityId="" propertyInput="" propertyContext=null current=null readonly=true instructionDivId="instructionDivId" instructionHtml="">

<#if propertyContext??>
<label for="${idPre}${propertyInput}${entityId}"><small>${propertyContext.inputLabel}</small><span class="text-primary"><strong>${instructionHtml}</strong></span></label>
  <div class="input-group input-group-sm">
    <input type="text" id="${idPre}${propertyInput}${entityId}" <#if readonly>readonly="true"</#if> class="form-control" value="${current.label}" placeholder="${propertyContext.placeHolder}">
    <input type="hidden" id="hidden_${idPre}${propertyContext.property}${entityId}" name="${propertyContext.property}" value="${current.valueInt}">
     
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    <span>${propertyContext.nameBar}</span> <span class="caret"></span>
	  </button>
	  
	  <#assign labelList=propertyContext.labelList/>
	  <ul id="${idPre}${propertyContext.property}${entityId}" class="dropdown-menu dropdown-menu-right">
	  
	   <#if labelList?size gt 0>
	  	<#list labelList as value>
	    <li class="<#if current?? && current.valueInt==value.valueInt>active</#if>" onclick="labelSelect_${propertyContext.property}${entityId}(this,'${value.valueInt}','${value.label}')">${value.label} -- ${value.valueInt}</li>
	    </#list>
	   </#if>
	     
	  </ul>
	  
	</div>
	
  </div>
  <div id="${instructionDivId}" class="hidden">
	  <div class="list-group text-warning">
	    <#if labelList?size gt 0>
	   		 <a href="javascript:void(0)" class="list-group-item active">
			    <h4 class="list-group-item-heading">${propertyContext.inputLabel}</h4>
			  	<p>${propertyContext.description}</p>
			  </a>
		  	<#list labelList as value>
			  <a href="javascript:void(0)" class="list-group-item">
			    <h4 class="list-group-item-heading">${value.label}</h4>
			    <p class="list-group-item-text">${value.description} <#if value.col1??>${value.col1}</#if> <#if value.col2??>${value.col2}</#if></p>
			  </a>
		  	</#list>
	    </#if>
	  </div>
  </div>
  <script>
  function labelSelect_${propertyContext.property}${entityId}(liSelft, value, label){
  		var d_p_v="${idPre}${propertyContext.property}${entityId}";
  		var d_p_v2="${idPre}${propertyInput}${entityId}";
  		$("#hidden_"+d_p_v).val(value);
  		$("#"+d_p_v2).val(label);
  		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
  		$(liSelft).addClass="active";
  }
  </script>
</#if>
</#macro>

<#macro M_dropdownBarCategory current=null category=null id="id_dropdown">
<#if category!>
<label for="${id}${current.id}">选择父类  <span id="id_tip${id}${current.id}"></span></label>
  <div class="input-group input-group-sm">
    <input type="text" id="${id}${current.id}" readonly="true" class="form-control" value="${current.name}" placeholder="选择父类">
    <input type="hidden" id="hidden_${id}${current.id}" name="parentId" value="${current.id}">
    
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
	  <#-- onclick="init_${current.id}('hidden_${id}${current.id}', '${id}${current.id}')"  -->
	  >
	    <span>选择父类</span> <span class="caret"></span>
	  </button>
		<@M_dropdownCategory current=current category=category deep=0 seporate="" id=id/>
	</div>
  </div>
 </#if>
</#macro>
<#macro M_dropdownCategory current=null category=null deep=0 seporate=">>" id="id_dropdown">
<#if category!>
<#if deep==0>
<ul id="ul_${id}${category.id}" class="dropdown-menu dropdown-menu-right">
</#if>
   <li id="li_${id}${category.id}" value="${category.id}" label="${category.name}" class="<#if current?? && current.id==category.id>active</#if>" onclick="labelSelect_${current.id}(this,'${category.id}','${category.name}')">
   ${seporate}${deep}> ${category.name} # ${category.orderSeq}
   </li>
  
   <#assign children=category.children>
   <#if children! && children?size gt 0>
  	<#list children as child>
    <@M_dropdownCategory current=current category=child deep=deep+1 seporate=seporate+"&nbsp;&nbsp;" id="id_dropdown"/>
    </#list>
   </#if>
</#if>
<#if deep==0>
</ul>

<script>
  function labelSelect_${current.id}(liSelft, value, label){
		var d_p_v="${id}${current.id}";
		
		var selectedIdInput = $("#hidden_"+d_p_v);
	  	var selectedId = selectedIdInput.val();
	  	console.log("selectedId >>>>> " + selectedId);
	  	console.log("value >>>>> " + value);
		if(selectedId==value){
			$("#id_tip"+d_p_v).html("不能选自己!");
			return false;
		}
		$("#id_tip"+d_p_v).html("");
		
		$("#hidden_"+d_p_v).val(value);
		$("#"+d_p_v).val(label);
		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
		$(liSelft).addClass="active";
  }
</script>
</#if>
</#macro>





<#macro M_dropdownBarCategoryView current=null category=null id="id_dropdown">
<#if category!>
<label for="${id}${current.id}">选择分类  <span id="id_tip${id}${current.id}"></span></label>
  <div class="input-group input-group-sm">
    <input type="text" id="${id}${current.id}" readonly="true" class="form-control" value="${current.name}" placeholder="选择分类">
    <input type="hidden" id="hidden_${id}${current.id}" name="parentId" value="${current.id}">
    
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
	  <#-- onclick="init_${current.id}('hidden_${id}${current.id}', '${id}${current.id}')"  -->
	  >
	    <span>选择父类</span> <span class="caret"></span>
	  </button>
		<@M_dropdownCategory current=current category=category deep=0 seporate="" id=id/>
	</div>
  </div>
 </#if>
</#macro>
<#macro M_dropdownCategoryView current=null category=null deep=0 seporate=">>" id="id_dropdown">
<#if category!>
<#if deep==0>
<ul id="ul_${id}${category.id}" class="dropdown-menu dropdown-menu-right">
</#if>
   
	   <li id="li_${id}${category.id}" value="${category.id}" label="${category.name}" class="<#if current?? && current.id==category.id>active</#if>" onclick="labelSelect_${current.id}(this,'${category.id}','${category.name}')">
	   ${seporate}${deep}> ${category.name} # ${category.orderSeq}
	   </li>
   <#assign children=category.children>
   <#if children! && children?size gt 0>
  	<#list children as child>
    <@M_dropdownCategoryView current=current category=child deep=deep+1 seporate=seporate+"&nbsp;&nbsp;" id="id_dropdown"/>
    </#list>
   </#if>
</#if>
<#if deep==0>
</ul>

<script>
  function labelSelect_${current.id}(liSelft, value, label){
		var d_p_v="${id}${current.id}";
		
		/**
		var selectedIdInput = $("#hidden_"+d_p_v);
	  	var selectedId = selectedIdInput.val();
	  	console.log("selectedId >>>>> " + selectedId);
	  	*/
	  	console.log("value >>>>> " + value);
		if(selectedId==value){
			$("#id_tip"+d_p_v).html("不能选自己!");
			return false;
		}
		$("#id_tip"+d_p_v).html("");
		
		$("#hidden_"+d_p_v).val(value);
		$("#"+d_p_v).val(label);
		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
		$(liSelft).addClass="active";
  }
</script>
</#if>
</#macro>










<#-- 搜索用分类begin -->
<#macro M_selectCategoryForSearch categoryRef=null category=null id="id_mainId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/category/ref/change">
	  <#if category!>
		  	<@M_dropdownBarCategoryRefSearch categoryRef=categoryRef category=category id=id isAnsy=isAnsy jsFunction=jsFunction url=url showLabel=false/>
	  </#if>
</#macro>

<#macro M_dropdownBarCategoryRefSearch categoryRef=null category=null id="id_mainId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/category/ref/change" showLabel=true>
<#if category!>
<#if showLabel>
<label for="${id}${categoryRef.mainId}">选择分类  <span id="id_tip${id}${categoryRef.mainId}"></span></label>
</#if>
  <div class="input-group input-group-sm">
    <input type="text" id="${id}${categoryRef.mainId}" readonly="true" class="form-control" value="${CategoryUtil.getName(categoryRef.baseId)}" placeholder="选择分类">
    <input type="hidden" id="hidden_${id}${categoryRef.mainId}" name="baseId" value="${categoryRef.baseId}">
    
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
	  >
	    <span>分类</span> <span class="caret"></span>
	  </button>
		<@M_dropdownCategoryRefView categoryRef=categoryRef category=category deep=0 seporate="→" id=id isAnsy=isAnsy jsFunction=jsFunction url=url showAll=true/>
	</div>
  </div>
 </#if>
</#macro>

<#-- 搜索用分类end -->


<#macro M_selectCategory entity=null id="id_mainId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/category/ref/change">
	  <#if entity!>
	  <#assign refList=entity.categoryList />
	  <#if refList! && refList?size gt 0 >
    	  <#list refList as ref>
		  	<@M_dropdownBarCategoryRefView categoryRef=ref category=entity.categoryTree id=id isAnsy=isAnsy jsFunction=jsFunction url=url />
    	  </#list>
	  </#if>
	  </#if>
</#macro>

<#macro M_dropdownBarCategoryRefView categoryRef=null category=null id="id_mainId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/category/ref/change" showLabel=true>
<#if category!>
<#if showLabel>
<label for="${id}${categoryRef.mainId}">选择分类  <span id="id_tip${id}${categoryRef.mainId}"></span></label>
</#if>
  <div class="input-group input-group-sm">
    <input type="text" id="${id}${categoryRef.mainId}" readonly="true" class="form-control" value="${CategoryUtil.getName(categoryRef.baseId)}" placeholder="选择分类">
    <input type="hidden" id="hidden_${id}${categoryRef.mainId}" name="baseId" value="${categoryRef.baseId}">
    
    <div class="input-group-btn">
	  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
	  >
	    <span>分类</span> <span class="caret"></span>
	  </button>
		<@M_dropdownCategoryRefView categoryRef=categoryRef category=category deep=0 seporate="" id=id isAnsy=isAnsy jsFunction=jsFunction url=url/>
	</div>
  </div>
 </#if>
</#macro>


<#macro M_dropdownCategoryRefView categoryRef=null category=null deep=0 seporate=">>" id="id_mainId" isAnsy=false jsFunction="changeCategory" url="/sz0099/ood/category/ref/change" showAll=false>
<#if category!>
<#if deep==0>
<ul id="ul_${id}${category.id}" class="dropdown-menu dropdown-menu-right">
<#if showAll>
<li id="li_${id}${category.id}" value="${category.id}" label="${category.name}" class="<#if categoryRef! && categoryRef.baseId! && categoryRef.baseId==category.id>active</#if>" onclick="labelSelect_${categoryRef.mainId}(this,'${url}','${categoryRef.id}','${categoryRef.mainId}','${category.id}','ALL','id_tip${id}${categoryRef.mainId}')">
   	ALL
</li>
</#if>
</#if>
	<#if deep gt 0>
   	<li id="li_${id}${category.id}" value="${category.id}" label="${category.name}" class="<#if categoryRef! && categoryRef.baseId! && categoryRef.baseId==category.id>active</#if>" onclick="labelSelect_${categoryRef.mainId}(this,'${url}','${categoryRef.id}','${categoryRef.mainId}','${category.id}','${category.name}','id_tip${id}${categoryRef.mainId}')">
   	<span class="bg-${getClazzByDepth(deep)}">${seporate}<#if deep==1>● <strong>${category.name} # ${category.orderSeq}</strong><#else>○ ${category.name} # ${category.orderSeq}</#if></span>
   	</li>
   	</#if>
   	
   <#assign children=category.children>
   <#if children! && children?size gt 0>
  	<#list children as child>
    	<@M_dropdownCategoryRefView categoryRef=categoryRef category=child deep=deep+1 seporate=seporate+"&nbsp;&nbsp;" id=id isAnsy=isAnsy jsFunction=jsFunction url=url/>
    </#list>
   </#if>
</#if>
<#if deep==0>
</ul>

<script>
  function labelSelect_${categoryRef.mainId}(liSelft, url, refId, mainId, value, label, tipId){
		var d_p_v="${id}${categoryRef.mainId}";
	  	console.log("value >>>>> " + value);
		
		$("#id_tip"+d_p_v).html("");
		
		$("#hidden_"+d_p_v).val(value);
		$("#"+d_p_v).val(label);
		$("ul#"+d_p_v+" li").each(function(){
		    $(this).removeClass("active");
		});
		$(liSelft).addClass="active";
		
		<#if isAnsy>
		if(typeof ${jsFunction} == 'function'){
			${jsFunction}(url, refId, mainId, value, label, tipId);
		}
		</#if>
  }
</script>
</#if>
</#macro>
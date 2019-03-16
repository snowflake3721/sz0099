<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_tag.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_strategy.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_coe_product_paragraph.ftl">
<#include "mobile/template/front/default/sz0099/ood/macro/macro_dropdown.ftl">




<#-- 产品详情，产品信息与段落分开查询 -->
<#macro M_coeProductDetail entity paragraphPage=null moreLoaded="true">   
<!--课程产品详情-->
<#if entity!>
      	  <#assign strategy=entity.strategy/>
		 <div class="panel panel-danger" id="id_panel_paragId${entity.paragId}" name="paragraph">
		  <div class="panel-heading">
		 ${entity.productNo} -- ${entity.name} <span class="pull-right"><@M_getStrategySymbol strategy=strategy label=CoeProduct.strategyMap.get(strategy).label/> </span>
		 
		 </div>
		  <div class="panel-body">
			  	<p> <strong>${entity.title} </strong></p>
			  	<#if entity.proTagList!?size gt 0 > 
			  	<@M_showTagListRandom productTagList=entity.proTagList idPre="tag_detail_" entity0=entity fontSize=15 randomSize=false/>
			  	</#if>
			  	<p><div class="alert alert-success" role="alert">
			  		<del><span class="text-info">原价:${PriceUtil.f2y(entity.priceOri)}</span></del>  
			  		<span class="text-danger">现价: ${PriceUtil.f2y(entity.priceCur)}</span> 
			  		<a href="javascript:void(0)" onclick="showBuyInstruction('${entity.id}')" class="btn btn-xs btn-danger" id="id_btn_buyinstruction_${entity.id}">购买</a>
			  		</div>
			  	</p>
			    <p>简介: ${entity.description}</p>
			    <p><strong>提取方式: ${CoeProduct.PULL_METHOD.getLabel(entity.pullMethod)}</strong></p>
	      	
  			</div><!--end panel-body-->
		    
		    <div class="panel-footer">
		     ${entity.productNo} -- ${entity.name}
		    </div>
	  </div><!--end panel-->
	  
	  <@M_paragraphViewContainer id="v001" paragPage=paragraphPage />
  	  
  	  <!--上一个 下一个 -->
  	  <#if moreLoaded=="true">
			<div class="container">
			  	<div class="row text-center">
				  <div class="col-xs-6"><span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span></div>
				  <div class="col-xs-6"><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></div>
				</div>
			</div>
	  </#if>
	  
 </#if>
 </#macro>
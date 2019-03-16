<#-- strategy begin -->

 <#-- ${CoeProduct.strategyMap.get(strategy)} -->
 <#-- ${CoeProduct.strategySymbolMap.get(strategy)} -->
 <#macro M_getStrategySymbol strategy=0 label="C">
 <#switch strategy>
 <#case 0> <span class="label label-default">${label}</span><#break>
 <#case 1> <span class="label label-success">${label}</span><#break>
 <#case 2> <span class="label label label-primary">${label}</span><#break>
 <#case 3> <span class="label label-warning">${label}</span><#break>
 <#case 4> <span class="label label-danger">${label}</span><#break>

 </#switch> 
 </#macro>
<#-- strategy end -->
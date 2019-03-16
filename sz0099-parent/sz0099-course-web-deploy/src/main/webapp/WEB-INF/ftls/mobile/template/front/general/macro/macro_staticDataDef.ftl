
<#macro M_dataArticleReward id="id" code=StaticDataDef.DATA_CASH_REWARD_CODE>
	<#assign data=StaticDataHolder.getData(code)/>
	<#if data??>
		<#list data as item>
			<#if (item?index+1)%4==0>
			<div class='row'>
			</#if>
			  	<div class='col-xs-3'><button class='btn btn-xs btn-${CssClazzUtils.getClazzByRandom()}' onclick='chooseAmount(${id},${item.realAmount})'>${item.name}</button></div>
			<#if (item?index+1)%4==0>
			</div>
			<hr/>
			</#if>
		</#list>
	</#if>
</#macro>

<#macro M_dataArticleRewardExtend id="id" code=StaticDataDef.DATA_CASH_REWARD_CODE>
	<p class='text-center' id='popover_content_${id}'>
	<@M_dataArticleReward id=id code=code />
	<div class='row'>
		<div class='col-xs-12'>
			<div class='input-group input-group-sm'>
	      		<input type='text' class='form-control' id='id_reward_amount_${id}' name='realAmount' placeholder='其它金额(≥2.00￥)' >
	      		<span class='input-group-btn'>
	      		<button class='btn btn-danger btn-xs' type='button' onclick='confirmAmount(${id})'>确定</button>
	      		<button class='btn btn-default btn-xs' type='button' onclick='clearAmount(${id})'>清空</button>
	      		</span>		
			</div>
		</div>
	</div>
	</p>
</#macro>

<#macro M_dataArticleRewardDonate id="id" position="top" code=StaticDataDef.DATA_CASH_REWARD_CODE>
<span class="text-center" id="reward_pop_container_${id}">
		       <button class="btn btn-lg btn-danger"  id="btn_reward_pop_${id}" 
		       data-container="#reward_pop_container_${id}" data-placement="${position}" @click='showRewardPanel(${id})'
		       title="选择打赏金额(元)" 
		       data-content="<@M_dataArticleRewardExtend id=id code=code />"
		       data-html="ture"
		       >
		       		<span class="glyphicon glyphicon-yen">赏</span>
		       </button>
</span>
</#macro>
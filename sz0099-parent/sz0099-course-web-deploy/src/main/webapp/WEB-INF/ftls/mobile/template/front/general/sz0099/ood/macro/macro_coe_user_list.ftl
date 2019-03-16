<#include "mobile/template/front/general/sz0099/ood/macro/macro_dropdown.ftl">
<#include "mobile/template/front/general/sz0099/ood/macro/macro_layout.ftl">


<#macro Mg_coeUserVerifyPage entityPage=null url="/sz0099/ood/personal/myinfo/findVerifyPage">  
<#if entityPage! && entityPage.totalElements gt 0>
	<#assign contentList=entityPage.content/> 
	<ul class="list-group" id="id_ul_userVerify">
		<#list contentList as child>
			<li class="list-group-item list-group-item-<#if child?is_odd_item>warning<#else>default</#if>" id="id_li_userVerify_${child.id}">
				<div class="row">
				  	<div class="col-xs-2">
				  	<a href='javascript:void(0)' onclick="popBigViewOnly('${child.coeUser.headImg}','${Base64Util.decode(child.coeUser.nickname)}', false)">
				  	<img class="img-thumbnail img-responsive" src="${child.coeUser.headImg}" alt="${Base64Util.decode(child.coeUser.nickname)}" />
				  	</a>
				  	</div>
				  	<div class="col-xs-7">
				  		姓名：${child.realname}<br/>
				  		手机号：${child.coeUser.mobile}<br/>
				  		身份证号：${child.identity}<br/>
				  		昵称：${Base64Util.decode(child.coeUser.nickname)}
				  	</div>
				  	<div class="col-xs-3">
				  		<a href='javascript:void(0)' onclick="popBigViewOnly('${child.identityFace}','身份证正面', false)">
						  <img class='img-thumbnail img-responsive'  width='130px' src='${child.identityFace}' alt='正面'>
						</a>
				  		<a href='javascript:void(0)' onclick="popBigViewOnly('${child.identityBack}','身份证背面', false)">
						  <img class='img-thumbnail img-responsive'  width='130px' src='${child.identityBack}' alt='背面'>
						</a>
				  	</div>
				 </div>
				<div id="id_btn_wrapper${child.id}">
					<@Mg_verifyBtn entity=child />
				</div>
			</li>
		</#list>
	</ul>
	<ul id="id_page_wrapper"></ul>
</#if>
 </#macro>
 <#macro Mg_verifyBtn entity=null>
		<#if entity.idstatus==null || entity.idstatus==CoeUserVerify.IDSTATUS_0_NO.valueInt>
			<a href="javascript:void(0)" class="btn btn-xs btn-info" onclick="popTipCustom('身份认证','用户尚未申请身份证认证！')">
			未申请
			</a>
			<#elseif entity.idstatus==CoeUserVerify.IDSTATUS_1_PROCESS.valueInt>
			<a href="javascript:void(0)" class="btn btn-xs btn-warning" onclick="showUserVerify('${entity.id}','${entity.remark}')">
			待认证
			</a>
			<#elseif entity.idstatus==CoeUserVerify.IDSTATUS_2_YES.valueInt>
			<a href="javascript:void(0)" class="btn btn-xs btn-success" onclick="showUserVerify('${entity.id}','${entity.remark}')">
			已认证
			</a>
			<#elseif entity.idstatus==CoeUserVerify.IDSTATUS_3_REJECT.valueInt>
			<a href="javascript:void(0)" class="btn btn-xs btn-danger" onclick="showUserVerify('${entity.id}','${entity.remark}')">
			已驳回
			</a>
		</#if>
		<#if entity.remark!>
			<small>${entity.remark}</small>
		</#if>
 </#macro>

<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#macro Mg_clubLeaderInstruction entity=null>
		<div class="panel panel-default">
			<div class="panel-heading">领队星级说明</div>
			<div class="panel-body">
		    	<table class="table table-condensed">
			    	  <thead>
				        <tr>
				          <th>星级</th>
				          <th>名称</th>
				          <th>带队规模</th>
				        </tr>
				      </thead>
				      <tbody>
						<tr>
						  <td >1</td>
						  <td >初入户外</td>
						  <td >悠闲为主，8人以下</td>
						</tr>
						<tr>
						  <td >2</td>
						  <td >小露锋芒</td>
						  <td >有队旗，稍有难度的野山野岭成型熟线，15人以下</td>
						</tr>
						<tr>
						  <td >3</td>
						  <td >三人成行</td>
						  <td >有组织有规划，具备越野能力，实施探路行动，30人以下</td>
						</tr>
						<tr>
						  <td >4</td>
						  <td >初具规模</td>
						  <td >队伍角色分工明确，应急备援充分，线下线上协同发挥，百人以下</td>
						</tr>
						<tr>
						  <td >5</td>
						  <td >开疆拓域</td>
						  <td >跨地域活动，百人千人</td>
						</tr>
						<tr>
						  <td >6</td>
						  <td >赛事征程</td>
						  <td >举办赛事级活动，专业团队实施，万人规模</td>
						</tr>
						<tr>
						  <td >7</td>
						  <td >极限挑战</td>
						  <td >全球级户外，珠峰，两极等</td>
						</tr>
					  </tbody>
				</table>
				<p>领队星级越高，户外资历越丰富，可带领的队伍规模与活动区域也越大</p>
			</div>
		</div><#-- end panel -->
</#macro>		
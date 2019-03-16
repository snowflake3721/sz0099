<#macro M_siteDataInitList dataInitList=null>
<h5 class="alert alert-danger">网站数据初始化</h5>
		<table class="table table-striped">
		      <thead>
		        <tr>
		          <th>序号</th>
		          <th>名称</th>
		          <th>代码</th>
		          <th>提供者</th>
		          <th>模块</th>
		          <th>子模块</th>
		          <th>状态</th>
		          <th>重复</th>
		          <th>次数</th>
		          <th>上次执行时间</th>
		          <th>操作</th>
		        </tr>
		      </thead>
		      <tbody>
		        
			        <#list dataInitList as data >
				        <tr class="<#if data?is_even_item>warning<#else>success</#if>">
				          <th scope="row">${data.orderSeq}</th>
				          <td>${data.name}</td>
				          <td>${data.code}</td>
				          <td>${data.author}</td>
				          <td>${data.module}</td>
				          <td>${data.subModule}</td>
				          <td>${SiteDataInit.MAP_DDSTATUS.get(data.ddStatus)}</td>
				          <td>${SiteDataInit.MAP_REPEATED.get(data.repeated)}</td>
				          <td>${data.repeatNum}</td>
				          <td>${data.lastExeTime?string("yyyy-MM-dd HH:mm:ss")}</td>
				          <td>
				          	<button class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal_${data.id}" @click="viewDataInit('${data.id}','${data.code}')">查看</button>
				          	<a href="" class="btn btn-primary btn-xs" @click="refreshDataItem('${data.id}')">刷新</a>
				          	<button class="btn btn-warning btn-xs" @click="popChangeDdStatus('${data.id}','${data.code}','${SiteDataInit.DDSTATUS_INIT}','${SiteDataInit.MAP_DDSTATUS.get(SiteDataInit.DDSTATUS_INIT)}','${SiteResponseCode.CODE_SITE_DATA_INIT_DDSTATUS_SUCCESS}',false)">初始化</button>
				          	<button class="btn btn-warning btn-xs"  @click="popChangeDdStatus('${data.id}','${data.code}','${SiteDataInit.DDSTATUS_UN_EXE}','${SiteDataInit.MAP_DDSTATUS.get(SiteDataInit.DDSTATUS_UN_EXE)}','${SiteResponseCode.CODE_SITE_DATA_INIT_DDSTATUS_SUCCESS}',false)">准备执行</button>
				          	<button class="btn btn-danger btn-xs" @click="popChangeDdStatus('${data.id}','${data.code}','${SiteDataInit.DDSTATUS_EXEING}','${SiteDataInit.MAP_DDSTATUS.get(SiteDataInit.DDSTATUS_EXEING)}','${SiteResponseCode.CODE_SITE_DATA_INIT_DDSTATUS_SUCCESS}',false)">执行</button>
				          	<button class="btn btn-danger btn-xs" @click="popChangeDdStatus('${data.id}','${data.code}','${SiteDataInit.DDSTATUS_INIT}','${SiteDataInit.MAP_DDSTATUS.get(SiteDataInit.DDSTATUS_INIT)}','${SiteResponseCode.CODE_SITE_DATA_INIT_DDSTATUS_SUCCESS}',true)">强制初始化</button>
				          </td>
				        </tr>
				        <tr class="<#if data?is_even_item>warning<#else>success</#if>">
				        	<th colspan="11">
				        		<@MV_articleCategoryDataInitList modalId=data.id/>
				        	</th>
				        </tr>
				        <#else>
				        <tr>
				          <th colspan="10"><h4 class="text-center text-danger">网站数据尚未进行基本初始化</h4></th>
				          <td><a href="javascript:void(0)" class="btn btn-danger btn-bg" v-on:click="doBasicDataInit($event,respCode)">执行基本初始化</a></td>
				        </tr>
			        </#list>
		        
		      </tbody>
		  </table>
		  
		  <!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal_ddStatus_confirm" tabindex="-1" role="dialog" aria-labelledby="Label_ddStatus_confirm" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="Label_ddStatus_confirm">
							修改数据状态
						</h4>
					</div>
					<div class="modal-body">
						你确定将数据状态变更为：{{ddStatusMsg}} 吗？
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button id="btn_confirm_change" type="button" v-bind:class="[force ? 'btn-danger' : 'btn-primary', 'btn']" @click="confirmChangeDdStatus()">
							提交更改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		  
</#macro>
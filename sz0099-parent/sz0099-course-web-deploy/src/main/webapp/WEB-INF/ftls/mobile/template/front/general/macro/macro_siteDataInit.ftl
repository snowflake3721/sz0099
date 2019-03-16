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
				          <td>${SiteDataInit.MAP_STATUS.get(data.status)}</td>
				          <td>${SiteDataInit.MAP_REPEATED.get(data.repeated)}</td>
				          <td>${data.repeatNum}</td>
				          <td>${data.lastExeTime?string("yyyy-MM-dd HH:mm:ss")}</td>
				          <td>
				          	<a href="${data.accessUrl}" class="btn btn-info btn-xs">查看</a>
				          	<a href="" class="btn btn-primary btn-xs">刷新</a>
				          	<a href="" class="btn btn-danger btn-xs">执行</a>
				          </td>
				        </tr>
				        <#else>
				        <tr class="active">
				          <th scope="row" colspan="10">网站数据尚未进行基本初始化</th>
				          <td><a href="" class="btn btn-danger btn-xs">执行基本初始化</a></td>
				        </tr>
			        </#list>
		        
		      </tbody>
		  </table>
</#macro>
<#macro M_articleCategoryDataInitList modalId="default" articleCategoryList=null>
<h5 class="alert alert-danger">文章分类数据初始化</h5>
	<!-- Modal -->
<div class="modal fade" id="myModal_${modalId}" tabindex="-1" role="dialog" aria-labelledby="Label_${modalId}">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="Label_${modalId}">文章分类数据列表</h4>
      </div>
      <div class="modal-body">
	        <table class="table table-striped">
			      <thead>
			        <tr>
			          <th>id</th>
			          <th>名称</th>
			          <th>代码</th>
			          <th>描述</th>
			          <th>图标</th>
			          <th>样式</th>
			          <th>显示</th>
			          <th>父级</th>
			          <th>排序</th>
			          <th>访问路径</th>
			          
			        </tr>
			      </thead>
			      <tbody>
				        <#list articleCategoryList as ac >
					        <tr class="<#if ac?is_even_item>warning<#else>success</#if>">
					          <th scope="row">${ac.id}</th>
					          <td>${ac.name}</td>
					          <td>${ac.code}</td>
					          <td>${ac.description}</td>
					          <td>${ac.icoUrl}</td>
					          <td>${ac.icoClazz}</td>
					          <td>${ArticleCategory.MAP_SHOWABLE.get(ac.showable)}</td>
					          <td>${ac.parentId}</td>
					          <td>${ac.orderSeq}</td>
					          <td>${ac.accessUrl}</td>
					        </tr>
					        <#else>
					        <tr>
					          <th colspan="11"><h4 class="text-center text-danger">文章类别数据为空</h4></th>
					        </tr>
				        </#list>
			      </tbody>
			  </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">确认</button>
      </div>
    </div>
  </div>
</div>
		  
</#macro>

<#macro MV_articleCategoryDataInitList modalId="default" >
<!-- Modal -->
<div class="modal fade" id="myModal_${modalId}" tabindex="-1" role="dialog" aria-labelledby="Label_${modalId}">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="Label_${modalId}">文章分类数据列表</h4>
      </div>
      <div class="modal-body">
	        <table class="table table-striped">
			      <thead>
			        <tr>
			          <th>id</th>
			          <th>名称</th>
			          <th>代码</th>
			          <th>描述</th>
			          <th>图标</th>
			          <th>样式</th>
			          <th>显示</th>
			          <th>父级</th>
			          <th>排序</th>
			          <th>访问路径</th>
			          
			        </tr>
			      </thead>
			      <tbody>
			      			<template v-for="ac in articleCategoryList">
					        <tr v-bind:class="{ warning: isWarning, 'success': isSuccess }">
					          <th scope="row">{{ac.id}}</th>
					          <td>{{ac.name}}</td>
					          <td>{{ac.code}}</td>
					          <td>{{ac.description}}</td>
					          <td>{{ac.icoUrl}}</td>
					          <td>{{ac.icoClazz}}</td>
					          <td>{{ac.showable}}</td>
					          <td>{{ac.parentId}}</td>
					          <td>{{ac.orderSeq}}</td>
					          <td>{{ac.accessUrl}}</td>
					        </tr>
					        <tr v-show="ac.children!=null" v-for="acc in ac.children" class="success">
					        	 <th scope="row">{{acc.id}}</th>
						          <td>{{acc.name}}</td>
						          <td>{{acc.code}}</td>
						          <td>{{acc.description}}</td>
						          <td>{{acc.icoUrl}}</td>
						          <td>{{acc.icoClazz}}</td>
						          <td>{{acc.showable}}</td>
						          <td>{{acc.parentId}}</td>
						          <td>{{acc.orderSeq}}</td>
						          <td>{{acc.accessUrl}}</td>
					        </tr>
					        </template>
					        <tr v-show="articleCategoryList==null">
					          <th colspan="11"><h4 class="text-center text-danger">文章类别数据为空</h4></th>
					        </tr>
			      </tbody>
			  </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>
		  
</#macro>
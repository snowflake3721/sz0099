<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/function/func_basic.ftl">
<#include "mobile/template/front/default/macro/macro_weword_list.ftl">
<div class="container text-center div-padding-5" ><span class="label label-danger">每人一句雷人语</span></div>

<div>
      <div class="container text-center">
          <p>
          		<span data-toggle="tooltip" data-placement="top" title="一天仅能发一句">今天你想说什么<span> <button type="button" class="btn btn-warning  btn-xs">我来说一句</button>
          </p>
      </div>
      
     <@M_weWordFirstRecomment weWord=weWord />
     
     <@M_weWordOwnself weWord=weWord />
    
     <@M_weWordPage page=weWordPage />
         
     <hr/>
</div>
  
  <!--骑行图片开始-->
  <@M_articlePageForBiking page=articlePage />
  <!--骑行图片结束-->



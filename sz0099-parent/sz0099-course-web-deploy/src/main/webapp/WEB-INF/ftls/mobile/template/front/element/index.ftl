<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">
<#include "mobile/template/front/default/macro/macro_tab_nav.ftl">
<#include "mobile/template/front/default/macro/macro_tab_content_index.ftl">

<html>
<head>

<title>群侠户外-卓玛拉山联盟</title>
</head>

<body>

<div class="container" id="top_login">
	top_login....
</div>

<div class="container" id="top_menu">
	"mobile/template/front/default/home/top_menu_index.ftl"
	<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
  <el-menu-item index="1">首页</el-menu-item>
  <el-submenu index="2">
    <template slot="title">出行</template>
    <el-menu-item index="2-1">徒步</el-menu-item>
    <el-menu-item index="2-2">骑行</el-menu-item>
    <el-menu-item index="2-3">旅行</el-menu-item>
    <el-menu-item index="2-3">自驾</el-menu-item>
    <el-submenu index="2-4">
      <template slot="title">选项4</template>
      <el-menu-item index="2-4-1">选项1</el-menu-item>
      <el-menu-item index="2-4-2">选项22222</el-menu-item>
      <el-menu-item index="2-4-3">选项3</el-menu-item>
    </el-submenu>
  </el-submenu>
  <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">装备</a></el-menu-item>
</el-menu>
<div class="line"></div>

<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
</div>

<div class="container" id="body_content">
	
	"/mobile/template/front/default/home/body_content_article_bonus.ftl"
</div>

<div class="container" id="body_footer">

</div>

<div class="container" id="body_footer_js">
	<#include "/mobile/template/front/element/home/body_footer_js_home.ftl">
</div>
</body>
</html>
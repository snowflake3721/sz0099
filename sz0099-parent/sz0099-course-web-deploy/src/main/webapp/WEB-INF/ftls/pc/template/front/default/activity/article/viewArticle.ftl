<#import "taglib/spring.ftl" as spring />
<#include "taglib/taglib.ftl">

<html>
<head>

<title>查看文章</title>
</head>

<body>


<div class="container" id="body_content">

<div id="app-2">
  <span v-bind:title="message">
    鼠标悬停几秒钟查看此处动态绑定的提示信息！
  </span>
</div>

<div id="app-3">
  <p v-if="seen">现在你看到我了</p>
</div>

<div id="app-4">
  <ol>
    <li v-for="todo in todos">
      {{ todo.text }}
    </li>
  </ol>
</div>

<div id="app-5">
  <p>{{ message }}</p>
  <button v-on:click="reverseMessage" class="btn btn-primary">逆转消息</button>
</div>

<div id="app-6">
  <p>{{ message }}</p>
  <input v-model="message">
</div>

<div id="app-7">
  <ol>
    <!--
      现在我们为每个 todo-item 提供 todo 对象
      todo 对象是变量，即其内容可以是动态的。
      我们也需要为每个组件提供一个“key”，稍后再
      作详细解释。
    -->
    <todo-item
      v-for="item in groceryList"
      v-bind:todo="item"
      v-bind:key="item.id">
    </todo-item>
  </ol>
</div>

-----------------------------------
pppppppppppppppp

<img src="/assets/common/images/timg.jpg" />

${article.id}  ==== ${article.title}




</div>

<div id="body_footer_js">
<script type="text/javascript">
<!--
var app2 = new Vue({
  el: '#app-2',
  data: {
    message: '页面加载于 ' + new Date().toLocaleString()
  }
});

var app3 = new Vue({
  el: '#app-3',
  data: {
    seen: true
  }
});

var app4 = new Vue({
  el: '#app-4',
  data: {
    todos: [
      { text: '学习 JavaScript' },
      { text: '学习 Vue' },
      { text: '整个牛项目' }
    ]
  }
});

var app5 = new Vue({
  el: '#app-5',
  data: {
    message: 'Hello Vue.js!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
});

var app6 = new Vue({
  el: '#app-6',
  data: {
    message: 'Hello Vue!'
  }
});

Vue.component('todo-item', {
  props: ['todo'],
  template: '<li>{{ todo.text }}</li>'
});

var app7 = new Vue({
  el: '#app-7',
  data: {
    groceryList: [
      { id: 0, text: '蔬菜' },
      { id: 1, text: '奶酪' },
      { id: 2, text: '随便其它什么人吃的东西' }
    ]
  }
});
//-->
</script>
</div>

</body>
</html>
<script type="text/javascript">
	<!--
	
	$(document).ready(function(){
		var appEui = new Vue({
		  el: '#appEui',
		  data: {
		    userList: ${userJson},
		    currentUser: ${currentUser}
		  },
		  computed:{
                email:function(){
                    //业务逻辑代码
                    var showEmail = this.currentUser.email.substring(0,6);
                    return showEmail;
                }
            }
		});
		
		
	});
	//-->
</script>
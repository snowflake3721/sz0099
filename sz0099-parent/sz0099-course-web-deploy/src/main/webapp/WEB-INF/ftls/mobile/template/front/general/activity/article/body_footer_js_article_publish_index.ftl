<script type="text/javascript">
	<!--
	
	$(document).ready(function(){
		var articlePublishIndexPage = new Vue({
		  el: '#app',
		  data: {
		    selectedCategoryId:'',
		    selectedCategoryName:'',
		    isSelected:false,
		    btnClazzCategoryDefault:"btn btn-default",
		    btnClazzCategorySelected:"btn btn-warning",
		    articleBo:{title:'',subTitle:'',description:''},
		    lastStep:1,
		    currentStep:1
		    
		  },
		  computed:{
		  		showable : function(){
		  		
		  			return this.isSelected?this.btnClazzCategorySelected:this.btnClazzCategoryDefault;
		  		}
		  },
		  methods:{
			  asignCategory(id, name){
			  		this.btnId=this.selectedCategoryId;
			  	
			  		this.selectedCategoryId=id;
			  		this.selectedCategoryName=name;
			  		this.changeBtnClazzCategory(id);
			  		console.log(this.btnId+"<--->"+this.selectedCategoryId + ":" + this.selectedCategoryName);
			  },
			  changeBtnClazzCategory(id){
			  		if(this.btnId != ''){
			  			$("#btn_id_"+this.btnId).removeClass("btn-warning");
			  			$("#btn_id_"+this.btnId).addClass("btn-default");
			  		}
			  
			  		$("#btn_id_"+id).removeClass("btn-default");
			  		$("#btn_id_"+id).addClass("btn-warning");
			  		
			  },
			  changeStep(stepNum,id){
			  	 this.lastStep=this.currentStep;
			  	 this.currentStep=stepNum;
			  	 
			  	 var currentStepBtn=$("#id_step_"+this.currentStep+"_nav_"+id);
			  	 if(this.lastStep != this.currentStep){
				  	 $("#id_step_"+this.lastStep+"_nav_"+id).removeClass("btn-warning");
				  	 $("#id_step_"+this.lastStep+"_nav_"+id).addClass("btn-default");
				  	 $("#id_step_"+this.currentStep+"_nav_"+id).removeClass("btn-default");
				  	 $("#id_step_"+this.currentStep+"_nav_"+id).addClass("btn-warning");
			  	 }
			  	 console.log(currentStepBtn.html());
			  	 $("#id_span_nav").html(currentStepBtn.html());
			  }
		  }
                
		});
		
	});
	//-->
</script>
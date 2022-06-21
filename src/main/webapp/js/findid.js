

$(".boxInput").hide();   

$("input[type=radio], .form-check-label").on("click",function(){
    $(".boxInput").hide();        
    $(this).parent().children().eq(2).show();
    
});

$("#findId_button").on("click",function(){
    		let $name = $("#findId").val();
    		
    		if(!isCorrectName($name)){
				return ;
			}
    		
    		$.ajax({
    			url:"/sns/findid",
    			type:"GET",
    			data:"name="+$name,
    			success:function(MemberInfo){
					$(".find_wrap").html("<i class=\"bi bi-arrow-return-right\"></i><span>아이디는&nbsp;</span><span>"+MemberInfo.id+"</span><span>&nbsp;입니다</span>");
				},
    			error:function(){
					alert("정보와 일치하는 아이디가 없습니다.");
				}
    		});
  });

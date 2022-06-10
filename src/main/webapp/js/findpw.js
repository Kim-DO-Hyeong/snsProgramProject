

$(".boxInput").hide();   
$(".find_wrap").hide();
$("input[type=radio], .form-check-label").on("click",function(){
    $(".boxInput").hide();   
    $(".find_wrap").hide();     
    $(this).parent().children().eq(2).show();
    $(this).parent().children().eq(3).show();
});

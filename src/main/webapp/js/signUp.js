// 회원가입 서블릿에 ajax 


// 회원가입 동의버튼
$("#term1").on("click",function(){
    let checked =$(this).is(":checked");
    if(checked){
        $("#term2, #term3").prop("checked",true);
    }else{
        $("#term2, #term3").prop("checked",false);
    }
});

$("#term2, #term3").on("click",function(){
    let checked1 =$("#term2").is(":checked");
    let checked2 =$("#term3").is(":checked");


    if(!(checked1 && checked2)){
        $("#term1").prop("checked",false);
    }else{
        $("#term1").prop("checked",true);
    }
});
// 회원가입 동의버튼

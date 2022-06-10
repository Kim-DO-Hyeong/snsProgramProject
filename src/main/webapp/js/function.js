/**
 * 
 */
 

 function isCorrectId(id){
	if(id.length == 0){
		alert("아이디를 입력하세요");
		return false;
	} else if(id.length < 2 || id.length > 30 ){
		alert("아이디는 2자 이상 30자 이하입니다");
		return false;
	}
	let regExp = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if(!regExp.test(id)){
		alert("이메일 형식이 아닙니다");
		return false;
	}
	
	return true;
}
 
 function isCorrectPw(pw){
	if(pw.length == 0){
		alert("비밀번호를 입력하세요");
		return false;
	}else if(pw.length < 4 || pw.length > 12 ){
		alert("비밀번호는 4자 이상 12자 이하입니다");
		return false;
	}
	
	let regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]$/;
	if(!regExp.test(pw)){
		alert("비밀번호는 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 포함해야 합니다");
		return false;
	}
	return true;
}
 
 function isCorrectName(name){
	if(name.length == 0){
		alert("이름을 입력하세요");
		return false;
	}else if(name.length < 2 || name.length > 5 ){
		alert("아이디는 2자 이상 5자 이하입니다");
		return false;
	}
	let regExp = /^[ㄱ-힣]+$/;
	if(!regExp.test(name)){
		alert("이름은 한글만 가능합니다");
		return false;
	}
	
	return true;
}

function isCorrectBirth(birth){
	if(birth.length == 0){
		alert("생일을 입력하세요");
		return false;
	}
	
	return true;
}


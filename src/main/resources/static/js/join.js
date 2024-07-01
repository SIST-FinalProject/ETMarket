/**
 * 
 */

$(function() {
	// 아이디 중복, 양식체크
	$("#userLoginId").blur(function() {
		//alert("블러");
	});
	
	// 닉네임 중복체크
	$("#userName").blur(function() {
		//alert("블러");
	});
	
	// 비밀번호 양식체크
	$("#userPassword").blur(function() {
		//alert("블러");
	});
	
	// 비밀번호&비밀번호 확인 일치 체크
	$("#userPasswordChk").blur(function() {
		var userPassword=$("#userPassword").val();
		var userPasswordChk=$("#userPasswordChk").val();

		$("#passwordChkError").hide();
		
		// 비밀번호와 비밀번호 확인이 일치하지 않을 경우 문구 출력
		if(userPassword!=userPasswordChk){
			$("#passwordChkError").show();
		}	
	});
	
	// 휴대폰번호 양식 체크, 인증번호 전송
	$("#userPhone").blur(function() {
		//alert("블러");
	});
	
	// 인증번호 체크
	$("#phoneVerifyCode").blur(function() {
		//alert("블러");
	});
	
	// 이메일 양식, 중복 체크
	$("#userEmail").blur(function() {
		//alert("블러");
	});
	
	
});

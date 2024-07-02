/**
 * 
 */

$(function() {
	// 아이디 양식, 중복 체크
	$("#userLoginId").blur(function() {
		var userLoginId=$(this).val();
		//alert(userLoginId);
		
		// 원래대로
		$("#idOverError").hide();
		$("#idRuleError").hide();
		$("#btnJoin").prop('disabled',false);
		$("#btnJoin").css("background-color","#4eb006");
		
		// 아이디 중복 체크
		$.ajax({
			type: "post",
			url: "/member/join/existLoginId",
			dataType: "json",
			data: { "userLoginId": userLoginId},
			success: function(res) {
				// 중복된 값이면 에러 출력
				if(res==true){
					$("#idOverError").show();
					
					// 회원가입 차단
					$("#btnJoin").prop('disabled',true);
					$("#btnJoin").css("background-color","#aaa");
				}

			}
		});
		
		// 아이디 양식
		let loginIdRule = /^(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}$/;
		
		if(!loginIdRule.test(userLoginId)){
			$("#idRuleError").show();
			
			// 회원가입 차단
			$("#btnJoin").prop('disabled',true);
			$("#btnJoin").css("background-color","#aaa");
		}
		
	});
	
	// 닉네임 양식, 중복 체크
	$("#userName").blur(function() {
		var userName=$(this).val();
		
		$("#nameOverError").hide();
		
		// 닉네임 중복 체크
		$.ajax({
			type: "post",
			url: "/member/join/existName",
			dataType: "json",
			data: { "userName": userName},
			success: function(res) {
				// 중복된 값이면 에러 출력
				if(res==true){
					$("#nameOverError").show();
				}

			}
		});
	});
	
	// 비밀번호 양식체크
	$("#userPassword").blur(function() {
		var userPassword=$(this).val();
		
		// 원래대로
		$("#passwordError").hide();
		$("#btnJoin").prop('disabled',false);
		$("#btnJoin").css("background-color","#4eb006");
		
		// 비밀번호 양식
		let passwordRule = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-_]).{8,16}$/;
		
		if(!passwordRule.test(userPassword)){
			$("#passwordError").show();
			
			// 회원가입 차단
			$("#btnJoin").prop('disabled',true);
			$("#btnJoin").css("background-color","#aaa");
		}
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
		var userEmail=$("#userEmail").val();
		
		$("#emailOverError").hide();
		
		// 이메일 중복 체크
		$.ajax({
			type: "post",
			url: "/member/join/existEmail",
			dataType: "json",
			data: { "userEmail": userEmail},
			success: function(res) {
				// 중복된 값이면 에러 출력
				if(res==true){
					$("#emailOverError").show();
					$("#btnJoin").prop('disabled',true);
				}
			}
		});
		
		
	});
	
});

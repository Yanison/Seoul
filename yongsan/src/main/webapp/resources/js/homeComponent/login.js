
function loginVali(){
	const memberId = $('#memberId').val() /** 1*/
	const memberPw = $('#memberPw').val() /** 2*/
	//공백 방지
	if(memberId == ""){
		alert("아이디를 입력해주세요")
		return false
	};
	if(memberPw == ""){
		alert("아이디를 입력해주세요")
		return false
	}
	console.log("입력하신 정보가 맞습니까? memberId:: " + memberId +" memberPw:: " + memberPw)
	requestUserLogin(memberId,memberPw)
}

function requestUserLogin(memberId,memberPw){
	
	$.ajax({
		async: true 
		,cache: false
		,type: "post"
		/* ,dataType:"json" */
		,url: "/yongsancode/requestUserLogin"
		/* ,data : $("#formLogin").serialize() */
		,data : { "memberId" : memberId, "memberPw" : memberPw}
		,success: function(response) {
			if(response.rp == "success") {
				location.replace('/');
			} else {
				alert("회원없음");
			}
		}
		,error : function(jqXHR, textStatus, errorThrown){
			alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});
}




// onclick Event
function naverLogin(){
	/*
	generateRandomString는 네이버에 API를 요청하는 동안 보안을위해 임시 생성하는 랜덤 문자열 입니다.
	*/
	
	var client_id = "5KpwB8RgMCCdOcSjRxPd"
	//redirect_uri == callBack Url
	var redirect_uri = "http://localhost:8082/naverLogin"
	
	
	//네이버 로그인 요청 url
	var url1 = 
	"https://nid.naver.com/oauth2.0/authorize?"+
	"response_type=code&"+
	"client_id="+client_id+"&" +
	"state="+generateRandomString(10)+
	"redirect_uri="+redirect_uri
	
	
	location.href = url1
	
}

function generateRandomString(num){
	const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
	  let result = '';
	  const charactersLength = characters.length;
	  for (let i = 0; i < num; i++) {
	      result += characters.charAt(Math.floor(Math.random() * charactersLength));
	  }
	
	  return result+"&";
}


function setLoginStatus() {
	
	if (naverLogin.user.gender == 'M'){
		$("input[name=gender]").val(5);
	} else {
		$("input[name=gender]").val(6);
	} 
	
	$.ajax({
		async: true
		,cache: false
		,type:"POST"
		,url: "/rest/member/naverLoginProc"
		,data: {
			"name": naverLogin.user.name
			, "snsId": "네이버로그인", "phone": naverLogin.user.mobile
			, "email": naverLogin.user.email
			, "gender": $("input[name=gender]").val()
			, "dob": naverLogin.user.birthyear+"-"+naverLogin.user.birthday
			, "snsImg": naverLogin.user.profile_image, "sns_id": naverLogin.user.id}
		,success : function(response) {
			if (response.rt == "fail") {
				alert("아이디와 비밀번호를 다시 확인 후 시도해 주세요.");
				return false;
			} else {
				window.location.href = "/sportMain";
			}
		},
		error : function(jqXHR, status, error) {
			alert("알 수 없는 에러 [ " + error + " ]");
		}
	});
}
/* naver login test e */
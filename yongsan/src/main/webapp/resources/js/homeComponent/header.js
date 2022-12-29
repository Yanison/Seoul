
let logOutBtn = document.getElementById('logout-btn');
logOutBtn.addEventListener('click',logOut)

function logOut(){
	var test = confirm("정말로 로그아웃 하시겠습니까?")
		
		if(test){
			//
			$.ajax({
				
				url:'/kakaoLogout',
				type:'post',
				data:{
				},
				success:function(res){
					if(res.rt == "success"){
						alert("로그아웃 됨");
						location.replace('/');
					}
					
				},
				error:function(err){
					location.replace('/');
					alert("로그아웃이 왜 안돼 스프링 ㄱ ");
				}
			});
			//
		}else if(test == false){
			alert("왜 안나가냐");
			return false;
		}
}
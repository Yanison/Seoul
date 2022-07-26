$(document).ready(function(){
	$('#login-btn').on('click',function(){
		event.preventDefault();
		location.href='./login';
		
	});
	
	$('#adduser').on('click',function(){
		event.preventDefault();
		location.href='./adduser';
		
	});
	
	$('#helpcenter').on('click',function(){
		event.preventDefault();
		location.href='./helpcenter';
		
	});

	$('market').on('click',function(){
		event.preventDefault();
		alert("아직 구현중입니다. 죄송합니다.");
		
		
	});
	
	$('earning').on('click',function(){
		alert("아직 구현중입니다. 죄송합니다.");
		event.preventDefault();
		
		
	});
	
	$('#raise').on('click',function(){
		alert("아직 구현중입니다. 죄송합니다.");
		event.preventDefault();
		
		
	});
	
	$('#logout-btn').on('click',function(){
		var test = confirm("정말로 로그아웃 하시겠습니까?")
		
		if(test){
			//
			$.ajax({
				
				url:'./ajax_logout',
				type:'get',
				data:{
					
					
				},
				success:function(res){
					if(res == "ok"){
						alert("로그아웃 됨");
						location.replace('./');
					}
					
				},
				error:function(err){
					
					alert("로그아웃이 왜 안돼 스프링 ㄱ ");
				}
				
			});
			//
		}else if(test == false){
			alert("왜 안나가냐");
			return false;
		}
	
		//버튼 누름 컨펌 변수 저장 > 정말로 로그아웃? > 조건문 시 > 아작스 던짐 
		// > 버튼 누름 컨펌 변수 저장 > 정말로 로그아웃? > 조건문 시 > 아작스 던짐> 로그아웃 됨  > 로그아웃 됨
		
		
	//logout	
	});
	
	$('#rootmenu').on('click',function(){
		event.preventDefault();
		location.href='./root_main';
		
	});

	
});
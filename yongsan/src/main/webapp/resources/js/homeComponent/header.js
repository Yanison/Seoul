$(document).ready(function(){
	var moneyfmt = $('.moneyfmt').text();
	var moneyfmt2 = moneyfmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	$('.moneyfmt').text(moneyfmt2)
	console.log("moneyfmt")
	console.log($('.moneyfmt').text())
	
	$('#login-btn').on('click',function(){
		event.preventDefault();
		location.href='./login';
		
	});
	
	
	$('#logo-link2').on('click',function(){
		event.preventDefault();
		location.href='../';
		
	});
	
	
	
	$('#adduser').on('click',function(){
		event.preventDefault();
		location.href='./adduser';
		
	});
	
	$('#helpcenter').on('click',function(){
		event.preventDefault();
		location.href='./helpcenter';
		
	});

	$('#exchange').on('click',function(){
		event.preventDefault();
		location.href='../exchange/BTC?cryptoSym=BTC';
		
		
	});
	
	$('#wod').on('click',function(){
		
		$.ajax({
			url:'http://127.0.0.1:8082/wod/wod',
			type:'post',
			data:{
			},
			success:function(rt){
				if(rt == "wod"){
					location.replace('/http://127.0.0.1:8082/wod/wod');
				}else{
					location.replace('http://127.0.0.1:8082/userLoginkko');
				}
				
			},
			error:function(err){
				location.replace('./');
				alert(" ");
			}
			
		});	
		
		event.preventDefault();
		location.href='../wod/wod';
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
				
				url:'http://127.0.0.1:8082/kakaoLogout',
				type:'post',
				data:{
				},
				success:function(res){
					if(res.rt == "success"){
						alert("로그아웃 됨");
						location.replace('http://127.0.0.1:8082/');
					}
					
				},
				error:function(err){
					location.replace('http://127.0.0.1:8082/');
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
	
	$('#adminPage').on('click',function(){
		event.preventDefault();
		location.href='http://127.0.0.1:8082/yongsancode/roothome';
		
	});
	
	
	


	
});
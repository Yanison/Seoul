








$(document).ready(function(){

	//일반 로그인
	$('#this-login-btn').click(function(){
		
		var id = $("#id").val();
		var pw = $("#pw").val();
		
		//공백 방지
		if(id == ""){
				$('#id-alert').html(
						'<div class="alert-box">' +
							'<span>' + '아이디를 입력해주세요' + '</span>' +
						'</div>'		
				);
				return false;
		}else if(id != ""){
			
			$('#id-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
		if(pw == ""){
				$('#pw-alert').html(
						'<div class="alert-box">' +
							'<span>' + '비밀번호를 입력해주세요' + '</span>' +
						'</div>'		
				);
			return false;
		}else if(pw != ""){
			
			$('#pw-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);	
		}
		
		
		//로그인하기
		$.ajax({
			url:'http://127.0.0.1:8080/seoul2/ajax_login',
			type:'get',
			data:{
				'id':id,
				'pw':pw
				
			},
			success:function(response){
				if(response=='okuser') {
					location.href="./"
						alert('일반유저로그인')
				}else if(response=='okroot') {
					location.href="./"
						alert('로그인')
				}else {
						alert('로그인 실패')
				}
				
			},
			error:function(err){
				alert('로그인 노노')
			}
		})
	});
});
$(document).ready(function(){
	
	function loginVali(){
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
	}

	//일반 로그인
	$('#this-login-btn').click(function(){
			alert("before loginVali")
		if (loginVali != false){
			alert("after loginVali")
			alert($("#id").val())
			alert($("#pw").val())
			//로그인하기
			$.ajax({
				async: true 
				,cache: false
				,type: "post"
				/* ,dataType:"json" */
				,url: "/login"
				/* ,data : $("#formLogin").serialize() */
				,data : { "id" : $("#id").val(), "pw" : $("#pw").val()}
				,success: function(response) {
					if(response.rt == "success") {
						if(response.changePwd == "true") {
							location.href = URL_CHANGE_PWD_FORM;
						} else {
							location.href = URL_INDEX_ADMIN;
						}
					} else {
						alert("회원없음");
					}
				}
				,error : function(jqXHR, textStatus, errorThrown){
					alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
				}
			});
		}else {
			alert("loginVali false")
		}
	});
});





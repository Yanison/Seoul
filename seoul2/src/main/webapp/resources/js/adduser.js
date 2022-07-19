
$(document).ready(function(){

	
	//회원가입 데이터를 받아올 데이터 변수 구현
	$('#signup-btn').on('click',function(){
		
		var id = $('#id').val();
		var pw = $('#pw').val();
		var nickname = $('#nickname').val();
		var address = $('#address').val();
		var tel = $('#tel').val();
		var gender = $('input:radio[name=gender]:checked').val();
		
		
		var pw_chk = $('#pw_chk').val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		var myinfo = $('myinfo').val();
	
		//회원가입 조건부 로직 짜기
		//1. 아이디가 중복이 되는지 아이디는 6~16자 영문,숫자
		//2. 비밀번호 일치하는지, 비밀번호가 8~16자 영문,숫자,특수문자
		//3. 닉네임 중복되는지
		//4. 주소 api 연동해보기 (옵션)
		//5. 전화번호 인증 api 연동해보기 (옵션)
		
		var arrNum1 = new Array();
        var arrNum2 = new Array();
		
       
        
        //아이디와 패스워드 값 데이터 정규화 공식
        var regul1 = /^[a-zA-Z0-9]{4,12}$/;
        //이메일 값 데이터 유효성 체크
        //[]안에 있는 값만 쓰겠다
        
        //이름 정규화 공식
        var regul3 = /^[가-힝a-zA-Z0-9]{2,10}$/;
        //var email = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
 

        
        
       
        //아이디 입력 하지 않았을 경우
        if(id == ""){
			$('#id-alert').html(
					'<div class="alert-box">' +
						'<span>' + '아이디를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#id').focus();
			return false;
			}else if(id != ""){
			
			$('#id-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
			};
        
        //focus() 메소드는 해당 객체로 집중해줌.
        if (!check(regul1,id,"아이디는 4~12자의 대소문자와 숫자로만 입력 가능합니다.")) {
        	alert("아이디체크에러");
            return false;//반환 할 곳 없이 if문 탈출
        }
        
        //비밀번호 입력 하지 않았을 경우
        
        if(pw == ""){
			$('#pw-alert').html(
					'<div class="alert-box">' +
						'<span>' + '비밀번호를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#pw').focus();
			return false;
		}else if(pw != ""){
			
			$('#pw-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
        
		
        if(pw_chk == ""){
			$('#pw-alert').html(
					'<div class="alert-box">' +
						'<span>' + '비밀번호를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#pw_chk').focus();
			return false;
		}else if(pw != ""){
			
			$('#pw_chk-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
        
        //비밀번호 유효성 검사
        //만약 내가 비밀번호에 정규화 방식을 하나라도 지키지 않으면 if문 안으로 들어가서 alert message를 띄움
        if (!check(regul1,pw,"비밀번호는 4~12자의 대소문자와 숫자로만 입력 가능합니다.")) {
        	alert("비번체크 노노");
            return false;
        }
        
        //비밀번호와 비밀번호 확인이 일치 하지 않을 경우
        
        
        if(pw != pw_chk){
			$('#pw-alert').html(
					'<div class="alert-box">' +
						'<span>' + '비밀번호가 일치하지 않습니다' + '</span>' +
					'</div>'		
			);
            $('#pw').focus();
            $('#pw_check').focus();
			return false;
		}else if(pw = pw_chk){
			
			$('#pw-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
        
        //닉네임 입력 안 한 경우
        
        
        if(nickname == ""){
			$('#nickname-alert').html(
					'<div class="alert-box">' +
						'<span>' + '입력 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#nickname').focus();
			return false;
		}else if(nickname != ""){
			
			$('#nickname-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
       
        //주소를 입력 안 한 경우
        
        if(address == ""){
			$('#address-alert').html(
					'<div class="alert-box">' +
						'<span>' + '주소를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#address').focus();
			return false;
		}else if(address != ""){
			
			$('#address-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
        //전화번호를 입력 안 한 경우
        
        if(tel == ""){
			$('#tel-alert').html(
					'<div class="alert-box">' +
						'<span>' + '전화번호를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#tel').focus();
			return false;
		}else if(tel != ""){
			
			$('#tel-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
        
        //성별을 입력하지 않은경우
        
        if(gender == undefined){
			$('#gender-alert').html(
					'<div class="alert-box" style="display: flex; justify-content: center;">' +
						'<span>' + '성별을 체크해주세요' + '</span>' +
					'</div>'		
			);
			$('#gd-men','gd-woman').focus();
			return false;
		}else if(gender == "w" || gender == "m"){
			
			$('#gender-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
	       	 $.ajax({
	  			url:'http://127.0.0.1:8080/seoul2/ajax_adduser',
	  			type:'GET',
	  			data:{
	  				'id': id,
	  				'pw': pw,
	  				'nickname': nickname,
	  				'gender': gender,
	  				'address': address,
	  				'tel': tel,
	  	
	  			},
	  			success:function(result){
	  				alert('전달 성공');
	  				if(result == 'ok') {
	  					alert('중복없음');	
	  					'<div class="alert-box-ok">' +
							'<span>' + '</span>' +
						'</div>'
	  					location.replace('./');
	  					//회원가입 이후 뒤로가기했을때 회원가입 페이지가 나오면안됨
	  					// replace 함수를 쓰면 히스토리에 남기지 않고 넘가가기 가능
	  				}else if (result == 'duplicatedId') {
	  					alert('중복, 삼계탕 드셔');
	  					$('#id-alert').html(
  								'<div class="alert-box-ok">' +
  									'<span>' + '중복된 아이디 입니다.' + '</span>' +
  								'</div>'		
  						);
	  				
	  				}else if (result == 'duplicatedNick'){
	  					$('#nickname-alert').html(
  								'<div class="alert-box-ok">' +
  									'<span>' + '중복된 닉네임 입니다.' + '</span>' +
  								'</div>'		
  						);
	  				}
	  			},
	  			error:function(err){
	  				alert('회원가입 실패했다');
	  				alert(err)
	  			}
	  			
	  		});
			
			
			
		};      
       	
        
        function check(re,what,message){
        	//정규화데이터,아이템 id,메세지
            if (re.test(what.value)) {//what의 문자열에 re의 패턴이 있는지 나타내는 함수 test
            //만약 내가 입력한 곳의 값이 정규화 데이터를 썼다면 true를 반환해서 호출 된 곳으로 리턴됨
                return true;
            }
            alert(message);
            what.value = "";
            what.focus();
        }
        

			
	
	
//	$('#signup-btn').on('click',function()
	});
//	$(document).ready(function()
});

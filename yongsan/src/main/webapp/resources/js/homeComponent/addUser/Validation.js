$(document).ready(function(){
//	회원가입 데이터를 받아올 데이터 변수 구현
	$('#signup-btn').on('click',function(){
		
		var id = $('#id').val();
		var pw = $('#pw').val();
		var pw_chk = $('#pw-chk').val();
		var name = $('#name').val();
		var nickname = $('#nickname').val();
		var email = $('#email').val();
		var emailD = $('#emailD').val();
		var address = $('#address').val();
		var detailAddress = $('#detailAddress').val();
		var tel = $('#tel').val();
		
		var postcode = $('#postcode').val();
		var email = $('#email').val();
		
		var gender = $('input:radio[name=gender]:checked').val();
		
		
		
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
        var pwVali = /^[a-zA-Z0-9]{4,12}$/;
        //이메일 값 데이터 유효성 체크
        //[]안에 있는 값만 쓰겠다
        
        //이름 정규화 공식
        var nameVali = /^[가-힝a-zA-Z0-9]{2,10}$/;
        var nickNameVali = /^[가-힝a-zA-Z0-9]{2,20}$/;
        var telVali = /^[0-9]{11}$/;
        //var email = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
 

        	
        function check(re,what){
        	//정규화데이터,아이템 id,메세지
            if (re.test(what)) {//what의 문자열에 re의 패턴이 있는지 나타내는 함수 test
            //만약 내가 입력한 곳의 값이 정규화 데이터를 썼다면 true를 반환해서 호출 된 곳으로 리턴됨           	
                return true;
            }
            what=""
        }
        
        //아이디 입력 하지 않았을 경우
        
        
        	
        
        
        
        if(id == ""){
        	
			$('#id-alert').html(
					'<div class="alert-box">' +
						'<span>' + '아이디 입력해주세요' + '</span>' +
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
		
		if (!check(pwVali,id)) {
        	$('#id-alert').html(
					'<div class="alert-box">' +
						'<span>' + "아이디는 4자리 이상입력하셔야 합니다." + '</span>' +
					'</div>'		
			);
            return false;
        }else if(check(pwVali,id)) {
        	$('#id-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
        }
		console.log('id chk')
		
		
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
			$('#pw-chk').focus();
			return false;
		}else if(pw != ""){
			
			$('#pw-chk-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
        
        //비밀번호 유효성 검사
        //만약 내가 비밀번호에 정규화 방식을 하나라도 지키지 않으면 if문 안으로 들어가서 alert message를 띄움
        if (!check(pwVali,pw)) {
        	$('#pw-alert').html(
					'<div class="alert-box">' +
						'<span>' + "비밀번호는 4~12자의 대소문자와 숫자로만 입력 가능합니다." + '</span>' +
					'</div>'		
			);
            return false;
        }else if(check(pwVali,pw)) {
        	$('#pw-chk-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
        }
        
        //비밀번호와 비밀번호 확인이 일치 하지 않을 경우
        if(pw != pw_chk){
			console.log(pw);
			console.log(pw_chk);
			console.log("123");
			$('#pw-alert').html(
					'<div class="alert-box">' +
						'<span>' + '비밀번호가 일치하지 않습니다' + '</span>' +
					'</div>'		
			);
            $('#pw').focus();
            $('#pw-check').focus();
			return false;
		}else if(pw = pw_chk){
			
			$('#pw-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		console.log('pw chk')
		
		//이름을 입력 안 한 경우
		
		if(name == ""){
			$('#name-alert').html(
					'<div class="alert-box">' +
						'<span>' + '이름을 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#name').focus();
			return false;
		}else if(name != ""){
			
			$('#nickname-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
		if (!check(nameVali,name)) {
        	$('#name-alert').html(
					'<div class="alert-box">' +
						'<span>' + "이름은 2자 이상 10자 이하로 입력해주세요" + '</span>' +
					'</div>'		
			);
            return false;
        }else if(check(nameVali,name)) {
        	$('#name-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
        }
        console.log('name chk')
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
		
		if (!check(nickNameVali,nickname)) {
        	$('#nickname-alert').html(
					'<div class="alert-box">' +
						'<span>' + "닉네임은 2자 이상 20자 이하로 입력해주세요" + '</span>' +
					'</div>'		
			);
            return false;
        }else if(check(nickNameVali,nickname)) {
        	$('#nickname-chk-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
        }
		console.log('nick chk')
		
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
		
		if (!check(telVali,tel)) {
        	$('#tel-alert').html(
					'<div class="alert-box">' +
						'<span>' + "11자의 옳바른 휴대전화번호를 입력해주세요" + '</span>' +
					'</div>'		
			);
            return false;
        }else if(check(telVali,tel)) {
        	$('#tel-chk-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
        }
		console.log('tel chk')

        //이메일을 입력 안 한 경우
        
        if(email == ""){
			$('#email-alert').html(
					'<div class="alert-box">' +
						'<span>' + '이메일 아이디를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#email').focus();
			return false;
		}else if(email != ""){
			
			$('#email-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
		if(emailD == 0){
			$('#email-alert').html(
					'<div class="alert-box">' +
						'<span>' + '이메일 주소를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#emailD').focus();
			return false;
		}else{
			
			$('#email-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		console.log('email chk')
		
		//주소를 입력 안 한 경우
        
        if(postcode == ""){
			$('#addr-alert').html(
					'<div class="alert-box">' +
						'<span>' + '주소를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('.addrBox').focus();
			return false;
		}else if(postcode != ""){
			
			$('#addr-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
		//상세주소 입력 안한경우
		 if(detailAddress == ""){
			$('#addr-alert').html(
					'<div class="alert-box">' +
						'<span>' + '상세 주소를 입력해주세요' + '</span>' +
					'</div>'		
			);
			$('#detailAddress').focus();
			return false;
		}else if(detailAddress != ""){
			
			$('#addr-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
		};
		
        console.log('addr chk')
        
        //성별을 입력하지 않은경우
        
        if(gender == undefined){
			$('#gender-alert').html(
					'<div class="alert-box" style="display: flex; justify-content: center;">' +
						'<span>' + '성별을 체크해주세요' + '</span>' +
					'</div>'		
			);
			$('#gd-men','gd-woman').focus();
			return false;
		}else if(gender == "2" || gender == "1"){
			
			$('#gender-alert').html(
					'<div class="alert-box-ok">' +
						'<span>' + '</span>' +
					'</div>'		
			);
			
	       	var queryString = $('form[name=addUser]').serialize();
		
			$.ajax({
				url:'http://127.0.0.1:8082/yongsancode/memberGroupUdpt',
				type:'GET',
				data:queryString,
				dataType:"json",
				success:function(result){
					alert(result);
					href="http://127.0.0.1:8082"
				},
				error:function(err){
					alert(err);
				}
			});
		};
	});	
	
	
	if($('#id').focus()){
		console.log('id focusout')
	}
});



 

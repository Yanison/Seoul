		//발리데이션
//		var num = pw.search(/[0-9]/g);
//		var eng = pw.search(/[a-z]/ig);
//		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
//		var myinfo = $('myinfo').val();
	
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
        
        
        //아이디
        var idVali = /^[a-zA-Z0-9]{6,20}$/;
        //이름 정규화 공식
        var nameVali = /^[가-힝a-zA-Z0-9]{2,10}$/;
        //닉네임
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
        
$(document).ready(function(){
	
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
	
	//발리데이션이 하나씩 맞을때마다 배열에 하나씩 요소가 추가가 될 것.
	valiArr = [];
		
	//발리데이션 진행 방식은, 발리데이션 체크 후에 조건에 안맞으면 false 맞으면 배열에 요소 추가
	//회원가입 버튼을 눌
	
	
//	$('#testVali').click(function(){
//		console.log("str func")
//		chkVali(id)
//	})
//	
//	
//	function chkVali(component){
//		console.log(component)
//		console.log("at func")
//		
//		var component = $('#'+component).val();
//		if(component.lenght < 1){
//			console.log(component + component.lenght+ ' << length' +"at func")
//			$('#'+component+'-alert').html(
//					'<div class="alert-box">' +
//						'<span>' + component+ '를 입력해주세요 test' + '</span>' +
//					'</div>'		
//			);
//		}else{
//			$('#'+component+'-alert').html(
//					'<div class="alert-box-ok">' +
//						'<span>' + component+ '를 입력해주세요 test' + '</span>' +
//					'</div>'		
//			);
//		}
//	}
//	
//	
//	function addUser(){
//		for(i = 0; i <= valiArr.length; i ++){
//			var a =+ 1
//			
//			if (a != 1){
//				return false;
//			}else{
//				console.log("perfect")
//			}
//		}
//	}
	

});
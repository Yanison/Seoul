const infrMmId = $('#memberId') /** 1*/
const infrMmPw = $('#memberPw') /** 2*/
const infrMmNickname = $('#memberNickname') /** 4*/
const infrMmEmailId = $('#MemberEmail') /** 9*/
const infrMmEmailAddress = $('#MemberEmailAddress') /** 11*/
const infrMmEmailVail = $('#emailVail') /** 11*/
let putHtml = (validation,msg) =>{
	if(validation == 0){
		validation ="valid"
		msg = '<i class="fa-solid fa-circle-check" style="color:green"></i>'
	}else{
		validation ="invalid"
	};
	let html = '<span class="'+validation+'" style="font-size:8; color:red">'+msg+'</span>'; 
	return html;
}

$(document).ready(function(){
//	infrMmId.on('keyup',dubpleCheck)
//	infrMmNickname.on('keyup',dubpleCheck)
	
})


let reValiId = /^[a-z]+[a-z0-9]{5,19}$/;
let reValiPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
let reValiNickName =  /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/;

function loginVali(){
	let addUserInput = $('.addUserInput')
	for(let i=0 ; i < addUserInput.length; i ++){
		
		let id = addUserInput[i].id
		console.log(id)
		let name = addUserInput[i].name
		console.log(name)
		let idVal = $('#'+addUserInput[i].id).val()
		let alertBox = $('#'+addUserInput[i].id+'_alert');
		
		
		if(idVal == '' || idVal == null){ 
			console.log(idVal)
			msg = name + " 값을 입력해주세요"
			alertBox.html(putHtml(msg))
		}else{
			msg = ""
			alertBox.html(putHtml(0,msg))
		}
		
		if(id == 'memberId'){
			reVali = /^[a-z]+[a-z0-9]{5,19}$/;
			if(!reTest(reVali,idVal)){
				msg = "5자 이상 19자 이하의 아이디를 입력해주세요"
				alertBox.html(putHtml(1,msg))
			}else{
				msg = ""
				alertBox.html(putHtml(0,msg))
			}
		}else if(id == 'memberPw'){
			reVali =  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
			if(!reTest(reVali,idVal)){
				msg = "8자리 이상 영문,숫자,특수문자 조합을 입력해주세요"
				alertBox.html(putHtml(1,msg))
			}else{
				msg = ""
				alertBox.html(putHtml(0,msg))
			}
		}else if(id == 'memberPw-chk'){
			if($('#memberPw').val() == ""){
				msg = "비밀번호를 입력해주세요"
				alertBox.html(putHtml(1,msg))
			}else{
				if($('#memberPw').val() != $('#memberPw-chk').val()){
					msg = "비밀번호가 잃치하지 않습니다."
					alertBox.html(putHtml(1,msg))
				}else{
					msg = ""
					alertBox.html(putHtml(0,msg))
				}	
			}
		}else if(id == 'memberNickname'){
			reVali =  /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/;
			if(!reTest(reVali,idVal)){
				msg = "2자이상 20자 이하의 닉네임을 입력해주세요"
				alertBox.html(putHtml(1,msg))
			}else{
				msg = ""
				alertBox.html(putHtml(0,msg))
			}
		}else if(id == 'emailVali'){
			if(idVal == ""){
				msg = "인증번호를 요청해주세요"
				alertBox.html(putHtml(1,msg))
			}else{
				if(idVal == $('#emailValiHddn').val()){
				msg = ""
				alertBox.html(putHtml(0,msg))
				}else{
					msg = "옳바른 이메일 인증번호를 입력해주세요"
					alertBox.html(putHtml(1,msg))
				}
			}
			
		}
	}
	let vail = $('.valid').length;
	console.log(vail)
	if(vail == 5){
		requestAddUser()
	}
}

function dubpleCheck(e,type){
	
	let value = $(e).val()
	let id = $(e).attr('id')
	let name = $(e).attr('name')
	$.ajax({
		async: true 
		,cache: false
		,type: "post"
		,url: "/yongsancode/duplicationCheck"
		,data : { 'shOption' : type, 'shValue' : value}
		,success: function(rt) {
			if(rt == 1) {
				let msg = "이미 존재하는 "+name+" 입니다";
				$('#'+id+"_alert").html(putHtml(1,msg))
			} else {
				let msg = "";
				$('#'+id+"_alert").html(putHtml(3,msg))
				
			}
		}
		,error : function(jqXHR, textStatus, errorThrown){
			alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});
}

function reTest(re,what){
	if (re.test(what)) {//what의 문자열에 re의 패턴이 있는지 나타내는 함수 test
    //만약 내가 입력한 곳의 값이 정규화 데이터를 썼다면 true를 반환해서 호출 된 곳으로 리턴됨           	
        return true;
    }
    what=""
}



function requestEmailValidation(){
	if(infrMmEmailId.val() == "" || infrMmEmailAddress.val() == ""){
		alert("이메일 주소를 입력해주세요")
		return false;
	}
	let memberEmail = infrMmEmailId.val() +"@"+ infrMmEmailAddress.val()
	$.ajax({
		async: true 
		,cache: false
		,type: "post"
		,data:{memberEmail : memberEmail}
		,url: "/yongsancode/requestEmailValidation"
		,success: function(rt) {
			$('#emailValiHddn').val(rt)
		}
		,error : function(err){
			alert("ajaxUpdate ");
		}
	});
}

function requestAddUser(){
	$.ajax({
		async: true 
		,cache: false
		,type: "post"
		,data:{
			memberId : infrMmId.val(),
			memberPw: infrMmPw.val(),
			memberNickname : infrMmNickname.val(),
			memberEmail : infrMmEmailId.val() +"@"+ infrMmEmailAddress.val()
		}
		,url: "/yongsancode/requestAddUser"
		,success: function(rp) {
			if(rp.rp == 'success'){
				alert("회원가입에 성공하셨습니다. 홈페이지로 돌아갑니다.")
				location.replace('/')
			}else{
				alert("회원가입에 실패하였습니다.")
			}
		}
		,error : function(err){
			alert("ajaxUpdate ");
		}
	});
}

 

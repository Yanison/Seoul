$(document).ready(function() {
	
	$('#cancel').on('click',function(){
		alert('d32323d')
		location.href="./root_main_userinfo"
	})
	
	
	$('#console1').on('click',function(){
		var address_detail  = $('#address_detail').val();
		console.log(address_detail);
	})
	$('#console2').on('click',function(){
		var job_address_detail  = $('#job_address_detail').val();
		console.log(job_address_detail);
	})
	
	
	
	$('#detail_check1').change(function() {
		
		if ($('#detail_check1').is(':checked')) {
			
			$('#addressDetail').hide();
			$('#addressDetail').val('');
			console.log('#addressDetail');
		} else {
			
			$('#addressDetail').show();
			console.log('#addressDetail');
		}

	});

	$('#detail_check2').change(function() {

		if ($('#detail_check2').is(':checked')) {
			$('#jobAddressDetail').hide();
			$('#jobAddressDetail').val('');
			console.log('#job_address_detail');
		} else {
			$('#jobAddressDetail').show();
			console.log('#job_address_detail');
		}

	});
	
	
	$('#ok').on('click',function(){
		var name = $('#name').val();
		var dob = $('#dob').val();
		var passPortNameEng = $('#passPortNameEng').val();
		var email = $('#email').val();
		var addressDetail = $('#addressDetail').val();
		var jobInfo = $('#jobInfo').val();
		var jobType = $('#jobType').val();
		var jobName = $('#jobName').val();
		var jobAddress = $('#jobAddress').val();
		var jobAddressDetail = $('#jobAddressDetail').val();
		
		
		if(name == ''){
			alert('이름을 입력해주세요.')
			return false;
		}
		
		
		if(dob == ''){
			alert('생년월일을 입력해주세요')
			return false;
		}
		
		if(passPortNameEng == ''){
			alert('여권 영문이름을 입력해주세요')
			return false;
		}
		
		if(email == ''){
			alert('이메일을 입력해주세요')
			return false;
		}
		
		if(addressDetail == ''){
			alert('상세 주소를 입력해주세요')
			return false;
		}
		
		if(jobInfo == ''){
			alert('직업을 선택해주세요')
			return false;
		}

		if( jobType == ''){
			alert('직업상세 정보를 선택해주세요')
			return false;
		}
		
		if( jobName == ''){
			alert('직장명을 입력해주세요')
			return false;
		}
		
		if(jobAddress == ''){
			alert('직장 주소를 입력해주세요')
			return false;
		}
		
		if(jobAddressDetail == ''){
			alert('직장 주소 상세를 입력해주세요')
			return false;
		}
		
		
		
		
		$.ajax({
			url:'http://127.0.0.1:8080/seoul2/ajax_addUserInfoDtail',
			type:'GET',
			data: {
				'name':name,
				'dob': dob,
				'passPortNameEng': passPortNameEng ,
				'email': email,
				'addressDetail':addressDetail,
				'jobInfo': jobInfo,
				'jobType': jobType,
				'jobName': jobName,
				'jobAddress': jobAddress,
				'jobAddressDetail' : jobAddressDetail,
			},
			success:function(result){
				alert('ok');
				location.replace('./root_main_userinfo')
			},
			error:function(err){
				alert('err');
				alert(err)
			}
			
		});
	});
	
	

	
	
});

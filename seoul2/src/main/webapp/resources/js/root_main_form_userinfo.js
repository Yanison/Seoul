$(document).ready(function() {
	
	$('#cancel').on('click',function(){
		alert('dd')
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
		var dob = $('#dob').val();
		var passPortNameEng = $('#passPortNameEng').val();
		var email = $('#email').val();
		var addressDetail = $('#addressDetail').val();
		var jobInfo = $('#jobInfo').val();
		var jobType = $('#jobType').val();
		var jobName = $('#jobName').val();
		var jobAddress = $('#jobAddress').val();
		var jobAddressDetail = $('#jobAddressDetail').val();
		
		
		if(dob == ''){
			alert('생년월일 쓰세요')
		}else {
			return false;
		}
		
		$.ajax({
			url:'http://127.0.0.1:8080/seoul2/ajax_addUserInfoDtail',
			type:'GET',
			data: {
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

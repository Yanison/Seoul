$(document).ready(function() {

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
			$('#address_detail').hide();
			$('#address_detail').val('');
			console.log('#address_detail');
		} else {
			$('#address_detail').show();
			console.log('#address_detail');
		}

	});

	$('#detail_check2').change(function() {

		if ($('#detail_check2').is(':checked')) {
			$('#job_address_detail').hide();
			$('#job_address_detail').val('');
			console.log('#job_address_detail');
		} else {
			$('#job_address_detail').show();
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
			},
			error:function(err){
				alert('err');
				alert(err)
			}
			
		});
	});
	
});

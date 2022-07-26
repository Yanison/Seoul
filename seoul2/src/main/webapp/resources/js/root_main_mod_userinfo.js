$(document).ready(function() {

	var user_idx = $('#current-user-idx').val();
	alert('유저인덱스 받고');	
	
	var jobInfo = $('#jobInfo').val();
	var jobType = $('#jobType').val();
	
	console.log(jobInfo)
	console.log(jobType)
	
	$.ajax({
		
		url:'./ajax_loadUserInfoByIdx',
		type:'get',
		data: {
			'user_idx': user_idx
		},
		success:function(result){
			$('#dob').val(result.dob),
			$('#passPortNameEng').val(result.passPortNameEng),
			$('#email').val(result.email),
			$('#jobType').val(result.jobType),
			$('#jobInfo').val(result.jobInfo),
			$('#addressDetail').val(result.addressDetail),
			$('#jobName').val(result.jobName),
			$('#jobAddress').val(result.jobAddress),
			$('#jobAddressDetail').val(result.jobAddressDetail)
			
		},
		error:function(err){
			alert(err)
		}
		
	});
	
$.ajax({
		
		url:'./ajax_loadUserBasicInfoByIdx',
		type:'get',
		data: {
			'user_idx': user_idx
		},
		success:function(result){
			$('#id').val(result.id),
			$('#pw').val(result.pw),
			$('#nickname').val(result.nickname),
			$('#tel').val(result.tel),
			$('#address').val(result.address),
			$('#gender').val(result.gender),
			$('#created_date').val(result.created_date)	
		},
		error:function(err){
			alert(err)
		}
		
	});
	
	console.log($('#jobType').val());
	console.log($('#jobInfo').val());
	
});

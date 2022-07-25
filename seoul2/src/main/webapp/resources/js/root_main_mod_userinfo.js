$(document).ready(function() {

	var user_idx = $('#current-user-idx').val();
	
	
	
	$.ajax({
		url:'./ajax_loadUserInfoByIdx',
		type:'get',
		data: {
			'user_idx': user_idx
		},
		success:function(result){
			$('userInfo.dob').val(result.dob),
			$('userInfo.passPortNameEng').val(result.passPortNameEng),
			$('userInfo.email').val(result.email),
			$('userInfo.jobType').val(result.jobType),
			$('userInfo.jobInfo').val(result.jobInfo),
			$('userInfo.addressDetail').val(result.addressDetail),
			$('userInfo.jobName').val(result.jobName),
			$('userInfo.jobAddress').val(result.jobAddress),
			$('userInfo.jobAddressDetail').val(result.jobAddressDetail)
			
		},
		error:function(err){
			alert(err)
		}
		
	});
	
	

	
});

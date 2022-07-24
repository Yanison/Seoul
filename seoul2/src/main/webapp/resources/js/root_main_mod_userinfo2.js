$(document).ready(function() {

	var getuser = 
	
	$.ajax({
		url:'./ajax_loadUserInfoByIdx',
		type:'GET',
		data: {
			'user_idx': user_idx
		},
		success:function(result){
			alert(result.user_idx);
		},
		error:function(err){
			alert('err');
			alert(err)
		}
		
	});
	
});

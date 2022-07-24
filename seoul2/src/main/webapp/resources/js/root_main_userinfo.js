$(document).ready(function(){
	
	$('#add-user').on('click',function(){
		location.href="./root_main_form_userinfo"
	});
	//수정은 select로 불러와서 저장하면 될 듯. 
//	$('#adit-user').on('click',function(){
//		location.href="./root_main_mod_userinfo"
//	});
	
	
	$.ajax({
		url:'http://127.0.0.1:8080/seoul2/ajax_loadUserInfoDtail',
		type:'get',
		data:{
		},
		success:function(response){
			$.each(response,function(index, item){
				$('#getAjax').append(
						'<tr class= " dbContainer "  data-user-idx =" '+ item.user_idx+' " >' +
							'<th scope="row">'+ 
								'<label class="form-check form-check-inline">'+ 
								'<input class="form-check-input inlineCheckbox" type="checkbox" value="'+item.user_idx+'" style="margin-right:10px;">' +
								'<label class="form-check-label inlineCheckboxLabel" for="inlineCheckboxLabel" id="inlineCheckboxLabel" >'+ item.user_idx +'</label>'+
								'</label>' +
							'</td>'+
							'<td name="name">'+ 'name' +'</td>'+
							'<td name="dob">'+ item.dob +'</td>'+
							'<td name="tel">'+ 'tel' +'</td>'+
							'<td name="address">'+ '주소' + item.addressDetail+'</td>'+
							'<td name="email">'+ item.email +'</td>'+
							'<td name="passPortNameEng">'+ item.passPortNameEng +'</td>'+
							'<td name="vali">'+ '인증' +'</td>'+
							'<td name="jobInfo">'+ item.jobInfo + item.jobType +'</td>'+
							'<td name="jobName">'+ item.jobName +'</td>'+
							'<td name="jobAddress">'+ item.jobAddress+ item.jobAddressDetail +'</td>'+
						+'</tr>'
						)
				
			})			
			
		},
		error:function(error){
			
		}
		
		
		
	})
		
	$('.dbContainer').on('click',function(){
		alert('dd')
		var userIdx = $(this).data('user-idx');
		location.href = './detail?user_idx='+ userIdx;
		
	})
	
	
	
});
$(document).ready(function(){
	$.ajax({
		url:'http://127.0.0.1:8080/seoul2/ajax_loadUserInfoDtail',
		type:'get',
		data:{

		},
		success:function(response){
			$.each(response,function(index, item){
				$('#getAjax').append(
						'<tr>' +
							'<th scope="row" id="user_idx">'+ 
								'<label class="form-check form-check-inline">'+ 
								'<input class="form-check-input inlineCheckbox" type="checkbox" id="inlineCheckbox" value=" '+ item.user_idx +' " style="margin-right:10px;">' +
								'<label class="form-check-label inlineCheckboxLabel" for="inlineCheckboxLabel" id="inlineCheckboxLabel" >'+ item.user_idx +'</label>'+
								'</label>' +
							'</td>'+
							'<td name="user_idx">'+ 'name' +'</td>'+
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
	
		$.ajax({
		url:'http://127.0.0.1:8080/seoul2/ajax_loadUserInfoDtail',
		type:'get',
		data:{

		},
		success:function(response){
			$.each(response,function(index, item){
				var userIdx = $('#cb').val(item.user_idx)
				
			})			
			
		},
		error:function(error){
			
		}
		
		
	})
	
	$('#cb').on('click',function(){
		console.log(userIdx);
	})
	
});
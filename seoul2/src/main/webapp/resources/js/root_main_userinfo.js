$(document).ready(function(){
	
	$('.dbContainer').on('click',function(){
			var userIdx = $(this).data('user-idx');
			console.log(userIdx);
			alert(userIdx)
			location.href="./root_main_mod_userinfo?user_idx=" + userIdx ;
	});
	
	$('#add-user').on('click',function(){
		location.href="./root_main_form_userinfo"
	});

	
	
	
	
	
	
	
	
	
//	$.ajax({
//		url:'http://127.0.0.1:8080/seoul2/ajax_loadUserInfoDtail',
//		type:'get',
//		data:{
//		},
//		success:function(response){
//			$.each(response,function(index, item){
//				$('#getAjax').append(
//						'<tr class= " dbContainer "  data-user-idx =" '+ item.user_idx+' " >' +
//							'<th scope="row">'+ 
//								'<div class="form-check form-check-inline inlineCheckboxLabel">'+ 
//								'<input class="form-check-input inlineCheckbox" type="checkbox" value="'+item.user_idx+'" style="margin-right:10px;" data-user-idx =" '+ item.user_idx+' " >' +
//								'<label class="form-check-label " for="inlineCheckboxLabel" id="inlineCheckboxLabel" >'+ item.user_idx +'</label>'+
//								'</div>' +
//							'</td>'+
//							'<td name="name">'+ 'name' +'</td>'+
//							'<td name="dob">'+ item.dob +'</td>'+
//							'<td name="tel">'+ 'tel' +'</td>'+
//							'<td name="address">'+ '주소' + item.addressDetail+'</td>'+
//							'<td name="email">'+ item.email +'</td>'+
//							'<td name="passPortNameEng">'+ item.passPortNameEng +'</td>'+
//							'<td name="vali">'+ '인증' +'</td>'+
//							'<td name="jobInfo">'+ item.jobInfo + item.jobType +'</td>'+
//							'<td name="jobName">'+ item.jobName +'</td>'+
//							'<td name="jobAddress">'+ item.jobAddress+ item.jobAddressDetail +'</td>'+
//							'<td>' + 
//							'<button id="adit-user" type="button" class="btn btn-primary btn-contents" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">' +
//							'<i class="fa-solid fa-pen"></i>' +
//							'</button>' +
//							'</td>' +
//						+'</tr>'
//						)
//				
//			})			
//			
//		},
//		error:function(error){
//			
//		}
//		
//		
//		
//	})
		

	
	
	
});
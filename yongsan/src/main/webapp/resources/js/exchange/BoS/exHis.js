$(document).ready(function(){
	console.log('nothin selected')
	
	
	$('.settleOrder').click(function(){
		var settleOrder = $("input[type=radio][name=settleOrder]:checked").val();
		console.log(settleOrder)
		console.log(typeof(settleOrder))
		if(settleOrder == '1'){
			$('._N').show();
			$('.viewOptionText1').css('color','#333');
			$('._Y').hide();
			$('.viewOptionText2').css('color','#666');
		}else{
			$('._Y').show();
			$('.viewOptionText2').css('color','#333');
			$('._N').hide();
			$('.viewOptionText1').css('color','#666');
		}
	})
});
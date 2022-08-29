$(document).ready(function(){
	console.log('nothin selected')
	
	
	$('.settleOrder').click(function(){
		var settleOrder = $("input[type=radio][name=settleOrder]:checked").val();
		console.log(settleOrder)
		console.log(typeof(settleOrder))
		if(settleOrder == '1'){
			$('._N').show();
			$('._Y').hide();
		}else{
			$('._Y').show();
			$('._N').hide();
		}
	})
	

			
	
});
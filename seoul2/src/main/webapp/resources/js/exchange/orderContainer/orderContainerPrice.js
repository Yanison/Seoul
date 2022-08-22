$(document).ready(function(){
	
//	$('#oderContainerPrice').addClass("active")
//	$('#oderContainerPrice').click(function(){
//		$('#oderContainerPrice').addClass("active")
//		$('#oderContainerOrderPrice').removeClass("active")
//		$('#oderContainerOrderNavigation').removeClass("active")
//	})
//	
//	$('#oderContainerOrderPrice').click(function(){
//		$('#oderContainerOrderPrice').addClass("active")
//		$('#oderContainerPrice').removeClass("active")
//		$('#oderContainerOrderNavigation').removeClass("active")
//	})
//	
//	$('#oderContainerOrderNavigation').click(function(){
//		$('#oderContainerOrderNavigation').addClass("active")
//		$('#oderContainerPrice').removeClass("active")
//		$('#oderContainerOrderPrice').removeClass("active")
//	})
	

	
	$('#amountToggle').click(function(){
		var amountToggle = $('#amountToggle').val()
		console.log(amountToggle)
		
		if(amountToggle == '1'){
			$('#amountToggle').html(
				'<b>'+'수량'+'</b>' +
				'<em>'+
				'(BTC)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'
			)
			var amountToggle = $('#amountToggle').val('2')
		}else{
			$('#amountToggle').html(
				'<b>'+'총액'+'</b>' +
				'<em>'+
				'(KRW)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'			
			)
			var amountToggle = $('#amountToggle').val('1')
		}
	})
});




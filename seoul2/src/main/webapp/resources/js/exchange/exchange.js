
$(document).ready(function(){
	
	//네비게이션 focus
	var navItem = $('.navItem')
	
	navItem.click(function(){
		$(this).addClass('active');
		navItem.not($(this)).removeClass('active');
	})

	
	//네비게이션 페이지네이션
	$('#oderContainer').scrollTop(350);
	$('#orderContaineritem2').hide()
	$('#orderContaineritem3').hide()
	
	$('#orderContainerPrice').click(function(){
		
		$('#orderContaineritem1','#orderContaineritem1_1').show();
		$('#orderContaineritem2','#orderContaineritem2_1').hide();
		$('#orderContaineritem3','#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderPrice').click(function(){
		
		$('#orderContaineritem1','#orderContaineritem1_1').hide();
		$('#orderContaineritem2','#orderContaineritem2_1').show();
		$('#orderContaineritem3','#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderNavigation').click(function(){
		
		$('#orderContaineritem1','#orderContaineritem1_1').hide();
		$('#orderContaineritem2','#orderContaineritem2_1').hide();
		$('#orderContaineritem3','#orderContaineritem3_1').show();
	})
	
	
	
		
		
		
		
//		console.log('asd')
//		
//		xhttp.onreadystatechange = function() {
//			if(this.readyState == 4 && this.status == 200){
//				$('#orderContainerWrap').outerHTML = this.requestText;
//			}else{
//				console.log('asd')
//			}
//		};
//		var xhttp = new XMLHttpRequest();
//		xhttp.open('GET','orderContainer/orderContainerOrderPrice.jsp',true)
//		xhttp.send();
	
});
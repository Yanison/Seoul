$(document).ready(function(){
	
	$('.TsTabMenu_1').click(function(){
		$('.transactedHistoryBody').show();
		$('.transactionHistoryByDayBody').hide();
	})
	
	$('.TsTabMenu_2').click(function(){
		$('.transactedHistoryBody').hide();
		$('.transactionHistoryByDayBody').show();
	})
});
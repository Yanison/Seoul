$(docment).ready(function(){
	
	alert('123123')
	
	var moneyFmt = $('.moneyFmt').text();
	var moneyFmt2 = moneyFmt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	$('.moneyFmt').text(moneyFmt2)
	console.log($('.moneyFmt').text())
	
});
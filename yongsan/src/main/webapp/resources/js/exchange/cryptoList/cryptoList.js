$(document).ready(function(){
	
	$('.thisCoin').click(function(){
		console.log('clicked');
		var cryptoSym = $(this).find('.getCryptoSym').text();
		console.log('thisCoin :: ' + cryptoSym)
		location.href='http://127.0.0.1:8082/exchange/'+cryptoSym
	})
})
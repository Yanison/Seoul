$(document).ready(function(){
	
	$('.thisCoin').click(function(){
		console.log('clicked');
		var thisCoin = $(this).find('.getCryptoSym').text();
		console.log('thisCoin :: ' + thisCoin)
		location.href='http://127.0.0.1:8082/exchange/'+thisCoin +"?cryptoSym="+thisCoin
	})
})
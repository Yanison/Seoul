function otVal(){
	var orderTypeB = $('label.otLabel input[name="orderTypeBuy"]:checked').val()
	if(orderTypeB == 1){
		$('#bidsPrice').val(null);
		$('#bidsAmount').val(null);
		$('#bidsPrice').attr("disabled", true);
		$('#bidsAmount').attr("disabled", true);
		$('#bidsSum').addClass("marketOrder")
		$('#bidsSum').attr("readonly", false);
		$('#bidsSum').val(null);
	}else if(orderTypeB == 0){
		$('#bidsPrice').attr("disabled", false);
		$('#bidsAmount').attr("disabled", false);
		$('#bidsSum').removeClass("marketOrder")
		$('#bidsSum').attr("readonly", true);
		$('#bidsSum').val(null);
	}
}

function otVal2(){
	var orderTypeS = $('label.otLabel input[name="orderTypeSell"]:checked').val()
	
	if(orderTypeS == 1){
		$('#asksPrice').val(null);
		$('#asksAmount').val(null);
		$('#asksPrice').attr("disabled", true);
		$('#asksAmount').attr("disabled", true);
		$('#asksSum').addClass("marketOrder");
		$('#asksSum').attr("readonly", false);
		$('#asksSum').val(null);
		$('#totalOrderS').text("수량");
	}else if(orderTypeS == 0){
		$('#asksPrice').attr("disabled", false);
		$('#asksAmount').attr("disabled", false);
		$('#asksSum').removeClass("marketOrder");
		$('#asksSum').attr("readonly", true);
		$('#asksSum').val(null);
		$('#totalOrderS').text("주문총액");
	}
}

function quantityPer(e,val){
	var orderTypeS = $('label.otLabel input[name="orderTypeSell"]:checked').val()
	let cryptoSeq = $('#coinSym').val();
	console.log("val :: "+ val )
	
	if(orderTypeS == 0){
		let recentPrice =Number($('#recentPrice').text());
		
		if(val == 0){
			var per = $(e).text().replace('%','');
			var calc = recentPrice * (per/100)
			console.log($(e).text()+" toFixed():: "+calc.toFixed(0))
			console.log($(e).text()+" Math.floor:: "+Math.round(calc))
			$('#bidsPrice').val(Math.round(calc))	
		}else{
			var per = $(e).text().replace('%','');
			var calc = recentPrice * (per/100)
			console.log($(e).text()+" toFixed():: "+calc.toFixed(0))
			console.log($(e).text()+" Math.floor:: "+Math.round(calc))
			$('#asksPrice').val(Math.round(calc))
		}	
	}else{
		if(val == 0){
			var userCashBal = Number($('#KRWBal').text()) ;
			var per = $(e).text().replace('%','');
			var calc = userCashBal * (per/100)
			console.log($(e).text()+" toFixed():: "+calc.toFixed(0))
			console.log($(e).text()+" Math.floor:: "+Math.round(calc))
			$('#bidsSum').val(Math.round(calc))	
		}else{
			var userCoinBal =Number($('#'+cryptoSeq+'Bal').text()) ;
			var per = $(e).text().replace('%','');
			var calc = userCoinBal * (per/100)
			console.log($(e).text()+" toFixed():: "+calc.toFixed(0))
			console.log($(e).text()+" Math.floor:: "+Math.round(calc))
			$('#asksSum').val(Math.round(calc))
		}	
	}
	
	
}



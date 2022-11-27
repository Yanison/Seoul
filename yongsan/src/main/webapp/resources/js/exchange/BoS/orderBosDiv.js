function otVal(){
	var orderTypeB = $('label.otLabel input[name="orderTypeBuy"]:checked').val()
	console.log("orderTypeB :: "+ orderTypeB)
	
	if(orderTypeB == 1){
		$('#bidsPrice').val(null);
		$('#bidsAmount').val(null);
		$('#bidsPrice').attr("disabled", true);
		$('#bidsAmount').attr("disabled", true);
		$('#bidsSum').addClass("marketOrder")
	}else if(orderTypeB == 0){
		$('#bidsPrice').attr("disabled", false);
		$('#bidsAmount').attr("disabled", false);
		$('#bidsSum').removeClass("marketOrder")
	}
	
	console.log("bidsPrice disabled? :: " + $('#bidsPrice').attr("disabled"))
	console.log("bidsAmount disabled? :: " + $('#bidsPrice').attr("disabled"))
}

function otVal2(){
	var orderTypeS = $('label.otLabel input[name="orderTypeSell"]:checked').val()
	console.log("orderTypeS :: "+ orderTypeS)
	
	if(orderTypeS == 1){
		$('#asksPrice').val(null);
		$('#asksAmount').val(null);
		$('#asksPrice').attr("disabled", true);
		$('#asksAmount').attr("disabled", true);
		$('#asksSum').addClass("marketOrder")
	}else if(orderTypeS == 0){
		$('#asksPrice').attr("disabled", false);
		$('#asksAmount').attr("disabled", false);
		$('#asksSum').removeClass("marketOrder")
		
	}
	
	console.log("asksPrice disabled? :: " + $('#asksPrice').attr("disabled"))
	console.log("asksAmount disabled? :: " + $('#asksPrice').attr("disabled"))
}

function quantityPer(e){
	var userCashBal = $('#userCashBal').val();
	var per = $(e).text().replace('%','');
	var calc = userCashBal * (per/100)
	console.log($(e).text()+" toFixed():: "+calc.toFixed(0))
	console.log($(e).text()+" Math.floor:: "+Math.round(calc))
	$('#bidsSum').val(Math.round(calc))
}



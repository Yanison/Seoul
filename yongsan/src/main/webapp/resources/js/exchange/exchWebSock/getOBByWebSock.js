$(document).ready(function(){
	console.log("${drawChart}")
	let cryptoSeq = $('#cryptoSeq').val()
	let memberSeq = $('#memberSeq').val()
	selectBOB()
	selectSOB()
	selectTransacton()
	console.log('@@@@@@@@@@@@ cryptoSeq :: ' + cryptoSeq + '// memberSeq :: ' + memberSeq)
	connect();
	marketTable();
	chartTable()
})
 
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # WebSock connect start 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/

var stompClient = null;

function connect() {
    var socket = new SockJS('/exchWebSocketService');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/observeSubmittedBids', function (observeSubmittedBids) {
	        console.log('subscribeed observeSubmittedBids ::from server //' 
	        			+ ' price :: '+ JSON.parse(observeSubmittedBids.body).price +" \n "
	        			+ ' obamount ::' + JSON.parse(observeSubmittedBids.body).obAmount +" \n "
	        			+ ' obSeq ::' + JSON.parse(observeSubmittedBids.body).obSeq +" \n "
	        			+ ' orderType ::' + JSON.parse(observeSubmittedBids.body).orderType +" \n "
	        			)
	        var price = JSON.parse(observeSubmittedBids.body).price;
	        var obAmount = JSON.parse(observeSubmittedBids.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedBids.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedBids.body).orderType;
	        var ratio = JSON.parse(observeSubmittedBids.body).ratio;
	        var amountRatio = JSON.parse(observeSubmittedBids.body).amountRatio;
			boBtrOne(price,obAmount,obSeq,orderType,ratio,amountRatio)
			limitMaxTrAppend()
			
			
			stompClient.send("/app/requestOrderMatching", {}, observeSubmittedBids.body);
   		});
	    stompClient.subscribe('/topic/observeSubmittedAsks', function (observeSubmittedAsks) {
	        console.log('subscribeed observeSubmittedAsks ::from server //' 
	        			+ 'price :: '+ JSON.parse(observeSubmittedAsks.body).price +" \n "
	        			+ 'obamount ::' + JSON.parse(observeSubmittedAsks.body).obAmount +" \n "
	        			+ 'obSeq ::' + JSON.parse(observeSubmittedAsks.body).obSeq +" \n "
	        			+ 'orderType ::' + JSON.parse(observeSubmittedAsks.body).orderType +" \n "
	        			)
	        var price = JSON.parse(observeSubmittedAsks.body).price;
	        var obAmount = JSON.parse(observeSubmittedAsks.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedAsks.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedAsks.body).orderType;
	        var ratio = JSON.parse(observeSubmittedAsks.body).ratio;
	        var amountRatio = JSON.parse(observeSubmittedAsks.body).amountRatio;
			soBtrOne(price,obAmount,obSeq,orderType,ratio,amountRatio)
			limitMaxTrAppend()
			
			stompClient.send("/app/requestOrderMatching", {}, observeSubmittedAsks.body);
	    });
	    
	    stompClient.subscribe('/topic/deleteCompleteOrderDivFromOB', function (deleteCompleteOrderDivFromOB) {
	        console.log('subscribeed deleteCompleteOrderDivFromOB ::from server //' 
	        			+ ' price :: '+ JSON.parse(deleteCompleteOrderDivFromOB.body).price +" \n "
	        			+ ' obamount ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obAmount +" \n "
	        			+ ' obSeq ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeq +" \n "
	        			+ ' obSeqSell ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeqSell +" \n "
	        			+ ' obSeqBuy ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeqBuy +" \n "
	        			+ ' orderType ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).orderType +" \n "
	        			)
	        var order = JSON.parse(deleteCompleteOrderDivFromOB.body)
	        var deleteEatten = ""
			deleteObTr(order,deleteEatten)
	    });
	    stompClient.subscribe('/topic/deleteCompleteOrderDivFromOBiep', function (deleteCompleteOrderDivFromOB) {
	        console.log('subscribeed deleteCompleteOrderDivFromOB ::from server //' 
	        			+ ' price :: '+ JSON.parse(deleteCompleteOrderDivFromOB.body).price +" \n "
	        			+ ' obamount ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obAmount +" \n "
	        			+ ' obSeq ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeq +" \n "
	        			+ ' obSeqSell ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeqSell +" \n "
	        			+ ' obSeqBuy ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeqBuy +" \n "
	        			+ ' orderType ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).orderType +" \n "
	        			)
	        var order = JSON.parse(deleteCompleteOrderDivFromOB.body)
	        var deleteBoth = "iep"
			deleteObTr(order,deleteBoth)
	    });
	     stompClient.subscribe('/topic/updateIncompleteOrderDivFromOB', function (updateIncompleteOrderDivFromOB) {
	        console.log('subscribeed updateIncompleteOrderDivFromOB ::from server //' 
	        			+ ' price :: '+ JSON.parse(updateIncompleteOrderDivFromOB.body).price +" \n "
	        			+ ' obamount ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).obAmount +" \n "
	        			+ ' obSeq ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).obSeq +" \n "
	        			+ ' orderType ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).orderType +" \n "
	        			)
	        var order = JSON.parse(updateIncompleteOrderDivFromOB.body)		
	        
			updateOrderAmount(order)
	    });
	    
	    stompClient.subscribe('/topic/transactionTable', function (transactionTable) {
	        console.log('subscribeed transactionTable ::from server //' 
	        			+ ' transactionSeq :: '+ JSON.parse(transactionTable.body).transactionSeq +" \n "
	        			+ ' cryptoSeq :: '+ JSON.parse(transactionTable.body).cryptoSeq +" \n "
	        			+ ' memberSeqSell :: '+ JSON.parse(transactionTable.body).memberSeqSell +" \n "
	        			+ ' memberSeqBuy :: '+ JSON.parse(transactionTable.body).memberSeqBuy +" \n "
	        			+ ' obSeqSell :: '+ JSON.parse(transactionTable.body).obSeqSell +" \n "
	        			+ ' obSeqBuy :: '+ JSON.parse(transactionTable.body).obSeqBuy +" \n "
	        			+ ' transactedType :: '+ JSON.parse(transactionTable.body).transactedType +" \n "
	        			+ ' price :: '+ JSON.parse(transactionTable.body).price +" \n "
	        			+ ' amount :: '+ JSON.parse(transactionTable.body).amount +" \n "
	        			+ ' tradePrice :: '+ JSON.parse(transactionTable.body).tradePrice +" \n "
	        			)
	        var transactionTable = JSON.parse(transactionTable.body)
	        
	        prependSelectTransactonTr(transactionTable);
	        limitMaxTrAppend()
	        
	        addCandle()
	    });
	    
	     stompClient.subscribe('/topic/drawChart/addCandle', function (data) {
	        var data = JSON.parse(data.body)
	        
	        let candle = [data.duration,data.low,data.startPrice,data.closingPrice,data.high]
	        chartData.push(candle)
	        if(chartData.length > 60){
				chartData.pop()
			}
	        drawChart()
	    });
	    
	    stompClient.subscribe('/topic/marketTable', function (marketTable) {
		
			console.log('subscribeed marketTable ::from server //' 
	        			+ ' high24 :: '+ JSON.parse(marketTable.body).high24 +" \n "
	        			+ ' low24 ::' + JSON.parse(marketTable.body).low24 +" \n "
	        			+ ' volume24 ::' + JSON.parse(marketTable.body).volume24 +" \n "
	        			+ ' cap24 ::' + JSON.parse(marketTable.body).cap24 +" \n "
	        			+ ' recentPrice ::' + JSON.parse(marketTable.body).recentPrice +" \n "
	        			+ ' ratioPre ::' + JSON.parse(marketTable.body).ratioPre +" \n "
	        			)
	        
	        $('#high24').text(JSON.parse(marketTable.body).high24);
	        $('#low24').text(JSON.parse(marketTable.body).low24);
	        $('#volume24').text(JSON.parse(marketTable.body).volume24);
	        $('#cap24').text(JSON.parse(marketTable.body).cap24);
	        $('#recentPrice').text(JSON.parse(marketTable.body).recentPrice);
	        $('#ratioPre').text(JSON.parse(marketTable.body).ratioPre);
	        
	        effectOfChaginghRatio(JSON.parse(marketTable.body))
	    });
	    
	    stompClient.subscribe('/topic/spread', function (spread) {
	        console.log('subscribeed selectTransactonOne ::from server //' 
	        			+ ' obSeqBuy :: '+ JSON.parse(spread.body).obSeqBuy +" \n "
	        			+ ' obSeqSell :: '+ JSON.parse(spread.body).obSeqSell +" \n "
	        			+ ' memberSeqBuy :: '+ JSON.parse(spread.body).memberSeqBuy +" \n "
	        			+ ' memberSeqSell :: '+ JSON.parse(spread.body).memberSeqSell +" \n "
	        			+ ' bPrice :: '+ JSON.parse(spread.body).bPrice +" \n "
	        			+ ' sPrice :: '+ JSON.parse(spread.body).sPrice +" \n "
	        			+ ' bAmount :: '+ JSON.parse(spread.body).bAmount +" \n "
	        			+ ' sAmount :: '+ JSON.parse(spread.body).sAmount +" \n "
	        			+ ' spread :: '+ JSON.parse(spread.body).spread +" \n "
	        			)
	    });
		 stompClient.subscribe('/topic/cancelOrder', function (cancelOrder){
			var cancelOrder = JSON.parse(cancelOrder.body)
			let obSeq = cancelOrder;
			
			let userCashBal = $('#inputKRWBal').val()
			let userCoinBal =$('#input'+cryptoSym+'Bal').val()
			let orderPrice = $('#hisObSeq'+obSeq).find('.exHis_MyPrice').text()
			let orderAmount = $('#hisObSeq'+obSeq).find('.exHis_Amount').text()
			let thisBos = $('#exHis_bos'+obSeq).val();
			
			if(thisBos == 0){
				
				var leftCah = (Number(userCashBal) + Number(orderPrice));
				console.log("cancelOrder, userCashBal :: " + userCashBal)
				console.log("cancelOrder, orderPrice :: " + orderPrice)
				$('#inputKRWBal').val(leftCah);
			}else{
				var leftCrypto = (Number(userCoinBal) + Number(orderAmount))
				console.log("cancelOrder, userCoinBal :: " + userCoinBal)
				console.log("cancelOrder, orderAmount :: " + orderAmount)
				$('#input'+cryptoSym+'Bal').val(leftCrypto);
			}
			
			$('#obSeq'+cancelOrder).remove()
			$('#hisObSeq'+cancelOrder).remove()
		});
		stompClient.subscribe('/topic/availableBalance', function (userBalance){
			var userBalance = JSON.parse(userBalance.body)
			
			console.log(userBalance)
				let cashbalance = userBalance.userBalance;
				let pendingcash = userBalance.pendingcash;
				let availableCash = userBalance.availableCash;
				console.log("userBalance :: " + availableCash)
				let availableCtpyto = userBalance.availableCtpyto;
				let cryptoSym = userBalance.cryptoSym;
				
				if(availableCash == null || availableCash == 0){
					$('#inputKRWBal').val(0)
				}else{
					
					$('#inputKRWBal').val(availableCash)
					$('#input'+cryptoSym+'Bal').val(availableCtpyto)
				}
			
			
		});
		
		stompClient.subscribe('/topic/selectCryptoList', function (cryptoList){
			var cryptoList = JSON.parse(cryptoList.body)
			
			let n = cryptoList.length;
			$('#cryptoListBar tr').remove()
			for(let i = 0 ; i < n ; i ++){
				let html = ''
				html += '<tr id="'+cryptoList[i].cryptoName+'" class="cryptoRow thisCoin" value="'+cryptoList[i].cryptoName+'" onclick="toThisCoin(this)">'
				html += '<td class="likeThis"><i class="fa-regular fa-star"></i></i></td>'
				html += '<td class="smallCandle">-</td>'
				html += '<td class="CryptoName" style="text-align:left;">'
				html += '<div class="getCryptoNm">'+cryptoList[i].cryptoName+'</div>'
				html += '<div class="getCryptoSym">'+cryptoList[i].cryptoSym+'</div>'
				html += '</td>'
				html += '<td class="CryptoPricePresent">'+cryptoList[i].recentPrice+'</td>'
				html += '<td class="24Hvari">'
				html += '<div>'+cryptoList[i].ratio+'%</div>'
				html += '<div>'+cryptoList[i].gap+'₩</div>'
				html += '</td>'
				html += '<td class="CryptoCap">'
				html += '<div><span>num</span><i>백만</i></div>'
				html += '</td>'
				html += '</tr>'
				$('#cryptoListBar').append(html)
			}
		});
    });
}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # WebSock connect end
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # start
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
//submit order request throu Ajax start
//submit Bids



function cancelOrder(e){
	if(!confirm("취소하시겠습니까?")){return false;}
	let obSeq = $(e).val();
	
	data ={
		'obSeq' : obSeq,
		'memberSeq' : memberSeq,
		'cryptoSeq' :  cryptoSeq
	}
	
	stompClient.send("/app/cancelOrder", {}, JSON.stringify(data));
}
  
function sendOrder(order) {
	let cryptoSym = $('#coinSym').val()
	let userCashBal = $('#inputKRWBal').val()
	let userCoinBal =$('#input'+cryptoSym+'Bal').val()
	if(order.price == "" || order.obAmount == "" || order.memberSeq == undefined){
		alert("주문가격 또는 수량을 입력해주세요")
		return false;
	}
	
	if(order.orderType == 0){
		if(order.bos == 0){
			console.log("order.totalPrice :: " + order.totalPrice + "//" + userCashBal)
			if(order.obAmount >= 1000){
				alert("1000 미만의 수량만 주문이 가능합니다.")
				return false;	
			}
			if(userCashBal < order.totalPrice){
				alert("주문 가능 금액을 초과하였습니다.")
				return false;	
			}
			stompClient.send("/app/submitBids", {}, JSON.stringify(order));
			return true
		}else{
			console.log("order.totalPrice :: " + order.obAmount + "//" + userCoinBal)
			if(order.obAmount >= 1000){
				alert("1000 미만의 수량만 주문이 가능합니다.")
				return false;
			}
			if(order.obAmount > userCoinBal){
				alert("매도 가능한 수량을 초과하였습니다.")
				return false;	
			}
			stompClient.send("/app/submitAsks", {}, JSON.stringify(order));
			return true
		}	
	}else{
		if(order.bos == 0){
			if(userCashBal < order.totalPrice){
				alert("주문 가능 금액을 초과하였습니다.")
				return false;	
			}
			stompClient.send("/app/submitBids", {}, JSON.stringify(order));
			return true
		}else{
			console.log("order.totalPrice :: " + order.obAmount + "//" + userCoinBal)
			if(order.obAmount >= 1000){
				alert("1000 미만의 수량만 주문이 가능합니다.")
				return false;
			}
			if(order.obAmount > userCoinBal){
				alert("매도 가능한 수량을 초과하였습니다.")
				return false;	
			}
			stompClient.send("/app/submitAsks", {}, JSON.stringify(order));
			return true
		}	
	}
	
	
}
function submitBids(){
	
	if(confirm("매수하시겠습니까?")){
		var order = 
		    {
		        "price" : $('#bidsPrice').val()
		        ,"obAmount" : $('#bidsAmount').val()
		        ,'totalPrice' : ($('#bidsPrice').val() * $('#bidsAmount').val())
		        ,"memberSeq" : $('#memberSeq').val()
		        ,"cryptoSeq" : $('#cryptoSeq').val()
		        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
		        ,'bos': 0
		    }
		
		if(order.orderType == 0){
		    if(order.price == "" || order.obAmount == ""){
				alert("주문 가격 및 수량을 입력해주세요")
				return false;
			}else if(order.price == 0 || order.obAmount == 0){
				alert("주문가격 혹은 주문수량 1 이상을 입력해주세요.")
				return false;
			}
			console.log(
				"매수주문 신청 내역입니다."+"\n"+
				"bidsPrice :: " + order.price+"\n"+
				"bidsAmount :: " + order.obAmount+"\n"+
				"totalPrice :: " + order.totalPrice+"\n"+
				"memberSeq :: " + order.memberSeq+"\n"+
				"cryptoSeq :: " + order.cryptoSeq+"\n"+
				"orderType :: " + order.orderType
				)
			if(sendOrder(order)){
				console.log("매수주문을 접수했습니다.")
				$('#bidsPrice').val(null);
				$('#bidsAmount').val(null);
				$('#bidsSum').val(null);
			}	
		}else{
			var order = 
		    {
		        'totalPrice' : $('#bidsSum').val()
		        ,"memberSeq" : $('#memberSeq').val()
		        ,"cryptoSeq" : $('#cryptoSeq').val()
		        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
		        ,'bos': 0
		    }
		    
		    if(order.price == "" && order.price == 0){
				alert("주문 총액을 입력해주세요")
				return false;
			}
			console.log(
				"매수주문 신청 내역입니다."+"\n"+
				"bidsPrice :: " + order.price+"\n"+
				"bidsAmount :: " + order.obAmount+"\n"+
				"memberSeq :: " + order.memberSeq+"\n"+
				"cryptoSeq :: " + order.cryptoSeq+"\n"+
				"orderType :: " + order.orderType
				)
			if(sendOrder(order)){
				console.log("매수주문을 접수했습니다.")
				$('#bidsPrice').val(null);
				$('#bidsAmount').val(null);
				$('#bidsSum').val(null);
			}
		}
	}else{
		$('#bidsPrice').val(null);
		$('#bidsAmount').val(null);
		$('#bidsSum').val(null);
	}
	
}
//submit Asks
function submitAsks(){
	if(confirm("매도하시겠습니까?")){
		
		var order = 
			    {
			        "price" : $('#asksPrice').val()
			        ,"obAmount" : $('#asksAmount').val()
			        ,'totalPrice' : ($('#asksPrice').val() * $('#asksAmount').val())
			        ,"memberSeq" : $('#memberSeq').val()
			        ,"cryptoSeq" : $('#cryptoSeq').val()
			        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
			         ,'bos': 1
			    }
		if(order.orderType == 0){
			console.log(
				"매수주문 신청 내역입니다."+"\n"+
				"asksPrice :: " + order.price+"\n"+
				"asksAmount :: " + order.obAmount+"\n"+
				"totalPrice :: " + order.totalPrice+"\n"+
				"memberSeq :: " + order.memberSeq+"\n"+
				"cryptoSeq :: " + order.cryptoSeq+"\n"+
				"orderType :: " + order.orderType
				)
				
				
			if(order.price == "" || order.obAmount == ""){
				alert("주문 가격 및 수량을 입력해주세요")
				return false;
			}else if(order.price == 0 || order.obAmount == 0){
				alert("주문가격 혹은 주문수량 1 이상을 입력해주세요.")
				return false;
			}
			
			if(sendOrder(order)){
				console.log("매도주문을 접수했습니다.")
				$('#asksPrice').val(null);
				$('#asksAmount').val(null);
				$('#asksSum').val(null);
				}	
		}else{
			var order = 
			    {
			        'totalPrice' : $('#asksSum').val()
			        ,"memberSeq" : $('#memberSeq').val()
			        ,"cryptoSeq" : $('#cryptoSeq').val()
			        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
			         ,'bos': 1
			    }
			
			console.log(
				"매수주문 신청 내역입니다."+"\n"+
				"asksPrice :: " + order.price+"\n"+
				"asksAmount :: " + order.obAmount+"\n"+
				"memberSeq :: " + order.memberSeq+"\n"+
				"cryptoSeq :: " + order.cryptoSeq+"\n"+
				"orderType :: " + order.orderType
				)
				
			if(order.price == "" && order.price == 0){
				alert("주문 총액을 입력해주세요")
				return false;
			}
			
			if(sendOrder(order)){
				console.log("매도주문을 접수했습니다.")
				$('#asksPrice').val(null);
				$('#asksAmount').val(null);
				$('#asksSum').val(null);
				}
		}
			
	}else{
		$('#asksPrice').val(null);
		$('#asksAmount').val(null);
		$('#asksSum').val(null);
	}
}
//submit order request throu Ajax end

function selectBOB(){
	var cryptoSeq = $('#cryptoSeq').val()
	console.log('selectBOB cryptoSeq :: '+cryptoSeq)
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectBOB"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(selectBOB){
				for(i = 0; i < selectBOB.length; i ++){
					
					var getPrice = selectBOB[i].price
					var obAmount = selectBOB[i].obAmount
					var obSeq = selectBOB[i].obSeq
					var orderType = selectBOB[i].orderType
					var ratio = selectBOB[i].ratio
					var amountRatio = selectBOB[i].amountRatio
					bidsTr(getPrice,obAmount,obSeq,orderType,ratio,amountRatio);
				}
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}

function selectSOB(){
	var cryptoSeq = $('#cryptoSeq').val()
	console.log('selectSOB cryptoSeq :: '+cryptoSeq)
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectSOB"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(selectSOB){
				for(i = 0; i < selectSOB.length; i ++){
					
					var getPrice = selectSOB[i].price
					var obAmount = selectSOB[i].obAmount
					var obSeq = selectSOB[i].obSeq
					var orderType = selectSOB[i].orderType
					var ratio = selectSOB[i].ratio
					var amountRatio = selectSOB[i].amountRatio
					soBtr(getPrice,obAmount,obSeq,orderType,ratio,amountRatio)
				}
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}
//request select OBList throu Ajax end

//request select Transaction throu Ajax start

function selectTransacton(){
	var cryptoSeq = $('#cryptoSeq').val() 
	console.log('selectTransacton :: selectTransacton')
	$.ajax({
		async:true
		,cache:false
		,type:"post"
		,url:"/exchange/selectTransacton"
		,data:{
			'cryptoSeq' : cryptoSeq
			}
		,success:function(selectTransacton){
			for(i = 0; i < selectTransacton.length; i ++ ){
				appendSelectTransactonTr(selectTransacton[i])
			}
		}
		,error:function(err){
			alert("selectTransacton err")
		}
	})
}

function transactionTable(){
	var cryptoSeq = $('#cryptoSeq').val() 
	console.log('transactionTable :: transactionTable')
	$.ajax({
		async:true
		,cache:false
		,type:"post"
		,url:"/exchange/transactionTable"
		,data:{
			'cryptoSeq' : cryptoSeq
			}
		,success:function(transactionTable){
			effectOfChaginghRatio(transactionTable)
		}
		,error:function(err){
			alert("selectTransacton err")
		}
	})
}

function marketTable(){
	var cryptoSeq = $('#cryptoSeq').val() 
	console.log('marketTable :: marketTable')
	$.ajax({
		async:true
		,cache:false
		,type:"post"
		,url:"/exchange/marketTable"
		,data:{
			'cryptoSeq' : cryptoSeq
			}
		,success:function(marketTable){
			effectOfChaginghRatio(marketTable)
			
		}
		,error:function(err){
			alert("selectTransacton err")
		}
	})
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
let chartData =[]

function chartTable(){
	var cryptoSeq = $('#cryptoSeq').val() 
	var selectChartDuration = $('#selectChartDuration option:selected').val();
	console.log('chartTable')
	$.ajax({
		async:true
		,cache:false
		,type:"post"
		,url:"/exchange/drawChart"
		,data:{
			'cryptoSeq' : cryptoSeq,
			'shTime' : selectChartDuration,
			'limit' : 60
			}
		,success:function(data){
			chartData =[]
			let len = data.length;
			for(let i = 0; i < len ; i ++){
				//[x축 시간,최솟값,초기값,최종값,최댓값,원통의 도움말 혹은 스타일(선택)]
				let candle = [data[i].duration,data[i].low,data[i].startPrice,data[i].closingPrice,data[i].high]
				chartData.push(candle)
			}
			drawChart()
			
		}
		,error:function(err){
			alert("selectTransacton err")
		}
	})
}



function drawChart() {
	        			
    data = google.visualization.arrayToDataTable(
	//[x축 시간,최솟값,초기값,최종값,최댓값,원통의 도움말 혹은 스타일(선택)]
      chartData
      // Treat first row as data as well.
    , true);

    var options = {
      legend:'none',
      candlestick: {
        fallingColor: { strokeWidth: 0, fill: '#0062df' }, // red
        risingColor: { strokeWidth: 0, fill: '#c84a31' }   // green
      },
      hAxis:{direction: -1}
    };

    var chart = new google.visualization.CandlestickChart(document.getElementById('candleChart'));

    chart.draw(data, options);
}

function addCandle(){
	var data ={
		cryptoSeq : $('#cryptoSeq').val(),
		shTime : $('#selectChartDuration option:selected').val()
	}
	stompClient.send("/app/drawChart/addCandle",{},JSON.stringify(data))
}

function appendSelectTransactonTr(list){
	console.log("appendSelectTransactonTr :: " + list)
	
	var html = "";
	
	html += '<tr class="transactonTr">'
	html += 	'<td class="first">'
	html +=			'<p>'
	html +=				'<i>'+ list.timestampMD +'</i>'
	html +=				'<i style="margin-left:10px;">'+ list.timestampR +'</i>'
	html +=			'</p>'
	html +=		'</td>'
	html +=		'<td class="">'+list.price+'</td>'
	html +=		'<td>'+list.amount+'</td>'
	html +=		'<td class="last">'+(list.price * list.amount)+'</td>'
	html +='</tr>'
	
	$('table.transactedHistoryTable tbody').append(html);
	$('#btcPrice').html(list.price)

}
function prependSelectTransactonTr(list){
	console.log("appendSelectTransactonTr :: " + list)
	
	var html = "";
	
	html += '<tr class="transactonTr">'
	html += 	'<td class="first">'
	html +=			'<p>'
	html +=				'<i>'+ list.timestampMD +'</i>'
	html +=				'<i style="margin-left:10px;">'+ list.timestampR +'</i>'
	html +=			'</p>'
	html +=		'</td>'
	html +=		'<td class="">'+list.price+'</td>'
	html +=		'<td>'+list.amount+'</td>'
	html +=		'<td class="last">'+(list.price * list.amount)+'</td>'
	html +='</tr>'
	
	$('table.transactedHistoryTable tbody').prepend(html);
	$('#btcPrice').html(list.price)
}


//request select Transaction throu Ajax end

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #Ajax request end 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #util Functions start 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 

function limitMaxTrAppend(){
	
	var boBtr = $('.boBtr').length;
	var soBtr = $('.boBtr').length;
	var transactonTr = $('.transactonTr').length;
	
	console.log ('boBtr :: '+boBtr +'// soBtr ::' + soBtr + " // transactonTr :: " + transactonTr)
	console.log('.bids ::' + $('#bidsTbody tr:last-child').text());
	console.log('.asks ::' + $('#asksTobdy tr:first-child').text());
	
	if(boBtr > 15){
		console.log("boBtr > 12 :: " + $('#bidsTbody tr:last-child').text());
		$('#bidsTbody tr:last-child').remove();
	}else if(soBtr> 15){
		console.log("soBtr > 12 :: " + $('#asksTobdy tr:first-child').text());
		$('#asksTobdy tr:first-child').remove();
	}else if(transactonTr > 12){
		console.log("transactonTr > 12 :: " + $('table.transactedHistoryTable tbody tr:last-child').text());
		$('table.transactedHistoryTable tbody tr:last-child').remove();
	}
}
// # add BOB <tr> append
function bidsTr(price,amount,obSeq,orderType,ratio,amountRatio) {
	let obAmount = amount.toFixed(4)
	
	var boBtr = null;
	boBtr += '<tr id="obSeq'+obSeq+'" class="up downtest boBtr">';
	boBtr += '<td class="upB">';
	boBtr += '<a >';
	boBtr += '<div class="ty03" onclick="selectPrice(this)">';
	boBtr += '<strong class="rPrice">'+ price +'</strong>';
	boBtr += '</div>';
	boBtr += '<div class="ty02">'+ratio+'%</div>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="bar">';
	boBtr += '<a onclick="selectAmount(this)">'; 
	boBtr += '<div style="width:'+amountRatio+'%;"></div>';
	boBtr += '<p class="obAmount">'+obAmount+'</p>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="last"></td>';
	boBtr +='<input type="hidden" name="obSeq_'+obSeq+'" value="'+obSeq+'">'
	boBtr +='<input type="hidden" name="orderType_'+orderType+'" value="'+orderType+'">'
	boBtr +='<input type="hidden" name="bos" value="0">'
	boBtr +='<input type="hidden" name="price" value="'+price+'">'
	boBtr +='<input type="hidden" name="obAmount" value="'+obAmount+'">'
	boBtr += '</tr>	';
	
	
	$('#bidsTbody').append(boBtr)
	
	return true;
}
// # add BOB <tr> prepend
function boBtrOne(price,amount,obSeq,orderType,ratio,amountRatio) {
	var obAmount = amount.toFixed(4)
	
	var boBtr = null;
	boBtr += '<tr id="obSeq'+obSeq+'" class="up downtest boBtr">';
	boBtr += '<td class="upB">';
	boBtr += '<a >';
	boBtr += '<div class="ty03" onclick="selectPrice(this)">';
	boBtr += '<strong class="rPrice">'+ price +'</strong>';
	boBtr += '</div>';
	boBtr += '<div class="ty02">'+ratio+'%</div>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="bar">';
	boBtr += '<a onclick="selectAmount(this)">';
	boBtr += '<div style="width:'+amountRatio+'%;"></div>';
	boBtr += '<p class="obAmount">'+obAmount+'</p>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="last"></td>';
	boBtr +='<input type="hidden" name="obSeq_'+obSeq+'" value="'+obSeq+'">'
	boBtr +='<input type="hidden" name="orderType_'+orderType+'" value="'+orderType+'">'
	boBtr +='<input type="hidden" name="bos" value="0">'
	boBtr +='<input type="hidden" name="price" value="'+price+'">'
	boBtr +='<input type="hidden" name="obAmount" value="'+obAmount+'">'
	boBtr += '</tr>	';
	
	$('#bidsTbody').prepend(boBtr)
}

// # add SOB <tr>
function soBtr(price,amount,obSeq,orderType,ratio,amountRatio) {
	var obAmount = amount.toFixed(4)
	
	var sobtr = null;
	sobtr += '<tr class="down downtest soBtr" id="obSeq'+obSeq+'">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a onclick="selectAmount(this)">';
	sobtr += '<div style="width:'+amountRatio+'%;"></div>';
	sobtr += '<p class="obAmount">'+obAmount+'</p>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '<td class="downB">';
	sobtr += '<a >';
	sobtr += '<div class="ty03" onclick="selectPrice(this)">';
	sobtr += '<strong class="rPrice">'+price+'</strong>';
	sobtr += '</div>';
	sobtr += '<div class="ty02">'+ratio+'%</div>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr +='<input type="hidden" name="obSeq_'+obSeq+'" value="'+obSeq+'">'
	sobtr +='<input type="hidden" name="orderType_'+orderType+'" value="'+orderType+'">'
	sobtr +='<input type="hidden" name="bos" value="1">'
	sobtr +='<input type="hidden" name="price" value="'+price+'">'
	sobtr +='<input type="hidden" name="obAmount" value="'+obAmount+'">'
	sobtr += '</tr>';
	
	$('#asksTbody').append(sobtr)
}

function soBtrOne(price,amount,obSeq,orderType,ratio,amountRatio) {
	var obAmount = amount.toFixed(4)
	
	var sobtr = null;
	sobtr += '<tr class="down downtest soBtr" id="obSeq'+obSeq+'">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a onclick="selectAmount(this)">';
	sobtr += '<div style="width:'+amountRatio+'%;"></div>';
	sobtr += '<p class="obAmount">'+obAmount+'</p>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '<td class="downB">';
	sobtr += '<a >';
	sobtr += '<div class="ty03" onclick="selectPrice(this)">';
	sobtr += '<strong class="rPrice">'+price+'</strong>';
	sobtr += '</div>';
	sobtr += '<div class="ty02">'+ratio+'%</div>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr +='<input type="hidden" name="obSeq_'+obSeq+'" value="'+obSeq+'">'
	sobtr +='<input type="hidden" name="orderType_'+orderType+'" value="'+orderType+'">'
	sobtr +='<input type="hidden" name="bos" value="1">'
	sobtr +='<input type="hidden" name="price" value="'+price+'">'
	sobtr +='<input type="hidden" name="obAmount" value="'+obAmount+'">'
	sobtr += '</tr>';
	
	$('#asksTbody').append(sobtr)
}



function deleteObTr(order,e){
	
	console.log("deleteObTr :: 거래가 성사된 주문을 삭제합니다. event ::  " + e)
	//var inputHiddenObSeq = $('input[name="obSeq'+obSeq+'"]').val()
	if(e == "iep"){
		$("#obSeq"+order.obSeqSell).remove();
		$("#obSeq"+order.obSeqBuy).remove();
		
		console.log(
		"deleteObTr() ::해당 주문의 tr id는 :: "+ order.obSeqSell+ " // "+order.obSeqBuy + " 입니다. \n"
		+"해당 요소를 삭제합니다.")
	}else{
		$("#obSeq"+order.obSeq).remove();
		
		console.log(
		"deleteObTr() ::해당 주문의 tr id는 :: "+ order.obSeq+" 입니다. \n"+"해당 요소를 삭제합니다.")
	}
}

function updateOrderAmount(order){
	console.log("updateOrderAmount target :: " + "tr#obSeq"+order.obSeq+" p.obAmount")
	$("tr#obSeq"+order.obSeq+" p.obAmount").text(order.obAmount.toFixed(4))
}

function effectOfChaginghRatio(marketTable){
	
	$('#volume24').text(marketTable.volume24)
	$('#cap24').text(marketTable.cap24)
	$('#asksPrice').val(marketTable.recentPrice)
	$('#bidsPrice').val(marketTable.recentPrice)
	
	$('#closingPrice').text(marketTable.closingPrice)
	
	$('#recentPrice').text(marketTable.recentPrice)
	$('.ratioPre').text(marketTable.ratioPre)
	$('#high24').text(marketTable.high24)
	$('#low24').text(marketTable.low24)
	$('#todayHigh24').text(marketTable.todayHigh24)
	$('#todayLow24').text(marketTable.todayLow24)
	
	
	
	//전일 가격이랑 비교대상
	recentPrice = $('input[name="recentPrice"]').val(marketTable.recentPrice),
	high24 = $('input[name="high24"]').val(marketTable.high24),
	low24 = $('input[name="low24"]').val(marketTable.low24),
	todayHigh24 = $('input[name="todayHigh24"]').val(marketTable.todayHigh24),
	todayLow24 = $('input[name="todayLow24"]').val(marketTable.todayLow24),
	closingPrice = $('input[name="closingPrice"]').val(marketTable.closingPrice)
	
	
	
	var closingPrice = marketTable.closingPrice;
	var recentPrice = marketTable.recentPrice;
	var priceGap = $('#priceGap').text(marketTable.closingPrice - marketTable.recentPrice);
	
		if(recentPrice > closingPrice){
			$('#recentPrice').css({"color":"#c84a31"})
			$('#priceGap').css({"color":"#c84a31"})
			$('.ratioPre').css({"color":"#c84a31"})
			$('#caret').removeClass()
			$('#caret').addClass('fa-solid fa-caret-up')
		}else if(recentPrice < closingPrice){
			$('#recentPrice').css({"color":"#c84a31"})
			$('#priceGap').css({"color":"#1261c4"})
			$('.ratioPre').css({"color":"#1261c4"})
			$('#caret').removeClass()
			$('#caret').addClass('fa-solid fa-caret-down')
		}else{
			$('#recentPrice').css({"color":"#333"})
			$('#priceGap').css({"color":"#333"})
			$('.ratioPre').css({"color":"#333"})
		}
	$('#high24').css({"color":"#c84a31"})
	$('#low24').css({"color":"#c84a31"})
	function rnb(e){
		var a = $(e)
		if(a < closingPrice){
			a.css({"color":"#c84a31"})
		}else if(a > closingPrice) {
			a.css({"color":"#c84a31"})
		}else{
			
		}
	}
		
}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #util Functions end
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 

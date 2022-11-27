$(document).ready(function(){
	var cryptoSeq = $('#cryptoSeq').val()
	var memberSeq = $('#memberSeq').val()
	selectBOB()
	selectSOB()
	selectTransacton()
	console.log('@@@@@@@@@@@@ cryptoSeq :: ' + cryptoSeq + '// memberSeq :: ' + memberSeq)
	connect();
	marketTable();
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
			boBtrOne(price,obAmount,obSeq,orderType)
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
	        
			soBtrOne(price,obAmount,obSeq,orderType)
			limitMaxTrAppend()
			
			stompClient.send("/app/requestOrderMatching", {}, observeSubmittedAsks.body);
	    });
	    
	    stompClient.subscribe('/topic/deleteCompleteOrderDivFromOB', function (deleteCompleteOrderDivFromOB) {
	        console.log('subscribeed deleteCompleteOrderDivFromOB ::from server //' 
	        			+ ' price :: '+ JSON.parse(deleteCompleteOrderDivFromOB.body).price +" \n "
	        			+ ' obamount ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obAmount +" \n "
	        			+ ' obSeq ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeq +" \n "
	        			+ ' orderType ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).orderType +" \n "
	        			)
	        var order = JSON.parse(deleteCompleteOrderDivFromOB.body)
	        
			deleteObTr(order)
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
    });
}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # WebSock connect end
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #Ajax request start
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/


//submit order request throu Ajax start
//submit Bids
function sendOrder(order) {
	if(order.price == "" || order.obAmount == "" || order.memberSeq == undefined){
		alert("주문가격 또는 수량을 입력해주세요")
		return false;
	}else{
		if(order.bos == 0){
			stompClient.send("/app/submitBids", {}, JSON.stringify(order));
			return true
		}else{
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
		        ,"memberSeq" : $('#memberSeq').val()
		        ,"cryptoSeq" : $('#cryptoSeq').val()
		        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
		        ,'bos': 0
		    }
		
		if(order.orderType == 0){
		    if(order.price == "" && order.obAmount == ""){
				alert("주문 가격밑 수량을 입력해주세요")
				return false;
			}else if(order.price == 0 && order.obAmount == 0){
				alert("주문가격 혹은 주문수량 1 이상을 입력해주세요.")
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
		}else{
			var order = 
		    {
		        "price" : $('#bidsSum').val()
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
				"memberSeq :: " + order.memberSeq+"\n"+
				"cryptoSeq :: " + order.cryptoSeq+"\n"+
				"orderType :: " + order.orderType
				)
				
				
			if(order.price == "" && order.obAmount == ""){
				alert("주문 가격밑 수량을 입력해주세요")
				return false;
			}else if(order.price == 0 && order.obAmount == 0){
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
			        "price" : $('#asksSum').val()
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
					bidsTr(getPrice,obAmount,obSeq,orderType);
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
					maxAppendS += 1;
					soBtr(getPrice,obAmount,obSeq,orderType)
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
function bidsTr(price,obAmount,obSeq,orderType){
	console.log("boBtr obSeq :: " + obSeq) 
	//value="'+price+'"
	var boBtr = null;
	boBtr += '<tr id="obSeq'+obSeq+'" class="up downtest boBtr">';
	boBtr += '<td class="upB">';
	boBtr += '<a href="#">';
	boBtr += '<div class="ty03">';
	boBtr += '<strong class="rPrice">'+ price +'</strong>';
	boBtr += '</div>';
	boBtr += '<div class="ty02">ratio%</div>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="bar">';
	boBtr += '<a href="#">';
	boBtr += '<div style="width: 84.4%;"></div>';
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
function boBtrOne(price,obAmount,obSeq,orderType){
	var boBtr = null;
	boBtr += '<tr id="obSeq'+obSeq+'" class="up downtest boBtr">';
	boBtr += '<td class="upB">';
	boBtr += '<a href="#">';
	boBtr += '<div class="ty03">';
	boBtr += '<strong class="rPrice">'+ price +'</strong>';
	boBtr += '</div>';
	boBtr += '<div class="ty02">ratio%</div>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="bar">';
	boBtr += '<a href="#">';
	boBtr += '<div style="width: 84.4%;"></div>';
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
function soBtr(price,obAmount,obSeq,orderType){
	console.log('soBtr obSeq :: '+ obSeq);
	
	var sobtr = null;
	sobtr += '<tr class="down downtest soBtr" id="obSeq'+obSeq+'">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a href="#">';
	sobtr += '<div style="width: 84.4%;"></div>';
	sobtr += '<p class="obAmount">'+obAmount+'</p>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '<td class="downB">';
	sobtr += '<a href="#">';
	sobtr += '<div class="ty03">';
	sobtr += '<strong class="rPrice">'+price+'</strong>';
	sobtr += '</div>';
	sobtr += '<div class="ty02">ratio%</div>';
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

function soBtrOne(price,obAmount,obSeq,orderType){
	var sobtr = null;
	sobtr += '<tr class="down downtest soBtr" id="obSeq'+obSeq+'">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a href="#">';
	sobtr += '<div style="width: 84.4%;"></div>';
	sobtr += '<p class="obAmount">'+obAmount+'</p>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '<td class="downB">';
	sobtr += '<a href="#">';
	sobtr += '<div class="ty03">';
	sobtr += '<strong class="rPrice">'+price+'</strong>';
	sobtr += '</div>';
	sobtr += '<div class="ty02">ratio%</div>';
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

function deleteObTr(order){
	console.log("deleteObTr :: 거래가 성사된 주문을 삭제합니다.")
	//var inputHiddenObSeq = $('input[name="obSeq'+obSeq+'"]').val()
	$("#obSeq"+order.obSeq).remove();
	console.log(
		"deleteObTr() ::해당 주문의 tr id는 :: "+ order.obSeq + " 입니다. \n"
		+"해당 요소를 삭제합니다.")
	
}

function updateOrderAmount(order){
	console.log("updateOrderAmount target :: " + "tr#obSeq"+order.obSeq+" p.obAmount")
	$("tr#obSeq"+order.obSeq+" p.obAmount").text(order.obAmount)
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

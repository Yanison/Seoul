$(document).ready(function(){
	var cryptoSeq = $('#cryptoSeq').val()
	var memberSeq = $('#memberSeq').val()
	selectBOB()
	selectSOB()
	selectTransacton()
	console.log('@@@@@@@@@@@@ cryptoSeq :: ' + cryptoSeq + '// memberSeq :: ' + memberSeq)
	connect();
})

var maxAppendB = 0;
var maxAppendS = 0;
 
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
	        			+ 'price :: '+ JSON.parse(observeSubmittedBids.body).price
	        			+ 'obamount ::' + JSON.parse(observeSubmittedBids.body).obAmount
	        			+ 'obSeq ::' + JSON.parse(observeSubmittedBids.body).obSeq
	        			+ 'orderType ::' + JSON.parse(observeSubmittedBids.body).orderType
	        			)
	        var price = JSON.parse(observeSubmittedBids.body).price;
	        var obAmount = JSON.parse(observeSubmittedBids.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedBids.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedBids.body).orderType;
			boBtrOne(price,obAmount,obSeq,orderType)
			limitMaxTrAppend(maxAppendB,maxAppendS)
			
			
			stompClient.send("/app/requestOrderMatching", {}, observeSubmittedBids.body);
   		});
	    stompClient.subscribe('/topic/observeSubmittedAsks', function (observeSubmittedAsks) {
	        console.log('subscribeed observeSubmittedAsks ::from server //' 
	        			+ 'price :: '+ JSON.parse(observeSubmittedAsks.body).price
	        			+ 'obamount ::' + JSON.parse(observeSubmittedAsks.body).obAmount
	        			+ 'obSeq ::' + JSON.parse(observeSubmittedAsks.body).obSeq
	        			+ 'orderType ::' + JSON.parse(observeSubmittedAsks.body).orderType
	        			)
	        var price = JSON.parse(observeSubmittedAsks.body).price;
	        var obAmount = JSON.parse(observeSubmittedAsks.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedAsks.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedAsks.body).orderType;
	        
			soBtrOne(price,obAmount,obSeq,orderType)
			limitMaxTrAppend(maxAppendB,maxAppendS)
			
			stompClient.send("/app/requestOrderMatching", {}, observeSubmittedAsks.body);
	    });
	    
	    stompClient.subscribe('/topic/deleteCompleteOrderDivFromOB', function (deleteCompleteOrderDivFromOB) {
	        console.log('subscribeed deleteCompleteOrderDivFromOB ::from server //' 
	        			+ 'price :: '+ JSON.parse(deleteCompleteOrderDivFromOB.body).price
	        			+ 'obamount ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obAmount
	        			+ 'obSeq ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).obSeq
	        			+ 'orderType ::' + JSON.parse(deleteCompleteOrderDivFromOB.body).orderType
	        			)
	        var order = JSON.parse(deleteCompleteOrderDivFromOB.body)
	        
			deleteObTr(order)
	    });
	     stompClient.subscribe('/topic/updateIncompleteOrderDivFromOB', function (updateIncompleteOrderDivFromOB) {
	        console.log('subscribeed updateIncompleteOrderDivFromOB ::from server //' 
	        			+ 'price :: '+ JSON.parse(updateIncompleteOrderDivFromOB.body).price
	        			+ 'obamount ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).obAmount
	        			+ 'obSeq ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).obSeq
	        			+ 'orderType ::' + JSON.parse(updateIncompleteOrderDivFromOB.body).orderType
	        			)
	        var order = JSON.parse(updateIncompleteOrderDivFromOB.body)		
	        
			updateOrderAmount(order)
	    });
	    
	    stompClient.subscribe('/topic/selectTransactonOne', function (selectTransactonOne) {
	        console.log('subscribeed selectTransactonOne ::from server //' 
	        			+ 'price :: '+ JSON.parse(selectTransactonOne.body).price
	        			+ 'amount ::' + JSON.parse(selectTransactonOne.body).amount
	        			+ 'transactionSeq ::' + JSON.parse(selectTransactonOne.body).transactionSeq
	        			)
	        var transaction = JSON.parse(selectTransactonOne.body)		
	        
			appendSelectTransactonTr(transaction);
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
	confirm("매수하시겠습니까?")
	var order = 
	    {
	        "price" : $('#bidsPrice').val()
	        ,"obAmount" : $('#bidsAmount').val()
	        ,"memberSeq" : $('#memberSeq').val()
	        ,"cryptoSeq" : $('#cryptoSeq').val()
	        ,"orderType" : $('label.otLabel input[name="orderTypeBuy"]:checked').val()
	        ,'bos': 0
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
	}
}
//submit Asks
function submitAsks(){
	
	confirm("매도하시겠습니까?")
	var order = 
	    {
	        "price" : $('#asksPrice').val()
	        ,"obAmount" : $('#asksAmount').val()
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
	
	if(sendOrder(order)){
		console.log("매도주문을 접수했습니다.")
		}
}
//submit order request throu Ajax end




//request select OBList throu Ajax start
function selectBOBOne(){
	var cryptoSeq = $('#cryptoSeq').val()
	console.log('cryptoSeq :: '+cryptoSeq)
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/observeSubmittedBids"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(response){
				if(response =="observeSubmittedBids"){
					console.log(
						"selectBOBOne :: 매수 주문내역 하나를 찾아옵니" + 
						"주문번호 :: "+selectBOBOne.obSeq + 
						"주문한 유저번호 :: "+selectBOBOne.memberSeq + 
						"주문한 코인 :: "+selectBOBOne.cryptoSeq 
						)
					return true;	
				}
				
			}
			,error : function(err){
				console.log("getList err")
				return false;
			}
		})
}

function selectSOBOne(){
	var cryptoSeq = $('#cryptoSeq').val()
	console.log('cryptoSeq :: '+cryptoSeq)
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/observeSubmittedAsks"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(response){
				if(response =="observeSubmittedAsks"){
					console.log(
					"selectBOBOne :: 매도 주문내역 하나를 찾아옵니" + 
					"주문번호 :: "+selectSOBOne.obSeq + 
					"주문한 유저번호 :: "+selectSOBOne.memberSeq + 
					"주문한 코인 :: "+selectSOBOne.cryptoSeq 
					)
					return true;
				}
				
			}
			,error : function(err){
				console.log("getList err")
				return false;
			}
		})
}



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
					maxAppendB += 1;
					
					boBtr(getPrice,obAmount,obSeq,orderType)
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
			'allOrOne' : 0
			,'cryptoSeq' : cryptoSeq
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

function appendSelectTransactonTr(list){
	console.log("appendSelectTransactonTr :: " + list)
	var date = new Date(list.timestamp)
	
	var html = "";
	
	html += '<tr>'
	html += 	'<td class="first">'
	html +=			'<p>'
	html +=				'<i>'+(date.getMonth()+1)+ "." +date.getDate()+'</i>'
	html +=				'<i style="margin-left:10px;">'+date.getHours()+ ":" +date.getMinutes()+'</i>'
	html +=			'</p>'
	html +=		'</td>'
	html +=		'<td>'+list.price+'</td>'
	html +=		'<td>'+list.amount+'</td>'
	html +=		'<td class="last">'+(list.price * list.amount)+'</td>'
	html +='</tr>'
	
	$('table.transactedHistoryTable tbody').append(html);
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

function limitMaxTrAppend(maxAppendB,maxAppendS){
	console.log ('maxAppendB :: '+maxAppendB +'// maxAppendS ::' + maxAppendS)
	console.log('.bids ::' + $('#bidsTbody tr:last-child').text());
	console.log('.asks ::' + $('#asksTobdy tr:first-child').text());
	if(this.maxAppendB > 12){
		console.log("maxAppendB > 12 :: " + $('#bidsTbody tr:last-child').text());
		$('#bidsTbody tr:last-child').remove();
	}else if(this.maxAppendS > 12){
		console.log("maxAppendS > 12 :: " + $('#asksTobdy tr:first-child').text());
		$('#asksTobdy tr:first-child').remove();
	}
}
// # add BOB <tr> append
function boBtr(price,obAmount,obSeq,orderType){
	//value="'+price+'"
	var boBtr = null;
	boBtr += '<tr class="up downtest boBtr" id="obSeq'+obSeq+'">';
	boBtr += '<td class="upB">';
	boBtr += '<a href="#">';
	boBtr += '<div class="ty03">';
	boBtr += '<strong>'+ price +'</strong>';
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
	maxAppendB += 1;
}
// # add BOB <tr> prepend
function boBtrOne(price,obAmount,obSeq,orderType){
	var boBtr = null;
	boBtr += '<tr class="up downtest boBtr" id="obSeq'+obSeq+'">';
	boBtr += '<td class="upB">';
	boBtr += '<a href="#">';
	boBtr += '<div class="ty03">';
	boBtr += '<strong>'+ price +'</strong>';
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
	maxAppendB += 1;
}

// # add SOB <tr>
function soBtr(price,obAmount,obSeq,orderType){
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
	sobtr += '<strong>'+price+'</strong>';
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
	sobtr += '<strong>'+price+'</strong>';
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
	maxAppendS += 1;
}

function deleteObTr(order){
	console.log("deleteObTr :: 거래가 성사된 주문을 삭제합니다.")
	//var inputHiddenObSeq = $('input[name="obSeq'+obSeq+'"]').val()
	$("#obSeq"+order.obseq).remove();
	console.log(
		"deleteObTr() ::해당 주문의 tr id는 :: "+ order.obseq + " 입니다. \n"
		+"해당 요소를 삭제합니다.")
	
}

function updateOrderAmount(order){
	$("#obSeq"+order.obseq).find('p.obAmount').html(order.obAmount)
}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #util Functions end
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 

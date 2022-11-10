$(document).ready(function(){
	var cryptoSeq = $('#cryptoSeq').val()
	var memberSeq = $('#memberSeq').val()
	selectBOB()
	selectSOB()
	console.log('@@@@@@@@@@@@ cryptoSeq :: ' + cryptoSeq + '// memberSeq :: ' + memberSeq)
	connect();
})

var maxAppendB = 0;
var maxAppendS = 0;

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # WebSock connect start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 

var stompClient = null;

function connect() {
    var socket = new SockJS('/exchWebSocketService');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/observeSubmittedBids', function (observeSubmittedBids) {
	        console.log('subscribeed observeSubmittedBids ::from server //' 
	        			+ 'price :: '+JSON.parse(observeSubmittedBids.body).price
	        			+ 'obamount ::' + JSON.parse(observeSubmittedBids.body).obAmount)
	        var price = JSON.parse(observeSubmittedBids.body).price;
	        var obAmount = JSON.parse(observeSubmittedBids.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedBids.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedBids.body).orderType;
			boBtrOne(price,obAmount,obSeq,orderType)
			limitMaxTrAppend(maxAppendB,maxAppendS)
	
   		});
	    stompClient.subscribe('/topic/observeSubmittedAsks', function (observeSubmittedAsks) {
	        console.log('subscribeed observeSubmittedBids ::from server //' 
	        			+ 'price :: '+JSON.parse(observeSubmittedAsks.body).price
	        			+ 'obamount ::' + JSON.parse(observeSubmittedAsks.body).obAmount)
	        var price = JSON.parse(observeSubmittedAsks.body).price;
	        var obAmount = JSON.parse(observeSubmittedAsks.body).obAmount;
	        var obSeq = JSON.parse(observeSubmittedAsks.body).obSeq;
	        var orderType = JSON.parse(observeSubmittedAsks.body).orderType;
	        
			soBtrOne(price,obAmount,obSeq,orderType)
			limitMaxTrAppend(maxAppendB,maxAppendS)
	    });
	    
	    stompClient.subscribe('/topic/deleteCompleteOrderDivFromOB', function (deleteCompleteOrderDivFromOB) {
	        console.log('subscribeed deleteCompleteOrderDivFromOB 완료된 주문내역을 삭제합니다. ::from server //' 
	        			+ 'obSeq :: '+JSON.parse(deleteCompleteOrderDivFromOB.body).price)
	        var obSeq = JSON.parse(deleteCompleteOrderDivFromOB.body).obSeq;
	        
			deleteObTr(obSeq)
	    });
    });
}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ # WebSock connect end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #Ajax request start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 


//submit order request throu Ajax start
//submit Bids
function submitBids(){
	var bidsPrice = $('#bidsPrice').val();
	var bidsAmount = $('#bidsAmount').val();
	var memberSeq = $('#memberSeq').val();
	var cryptoSeq = $('#cryptoSeq').val();
	var orderType = $('label.otLabel input[name="orderTypeBuy"]:checked').val()
	console.log("매수주문 신청 내역입니다.")
	console.log("bidsPrice :: " + bidsPrice)
	console.log("bidsAmount :: " + bidsAmount)
	console.log("memberSeq :: " + memberSeq)
	console.log("cryptoSeq :: " + cryptoSeq)
	console.log("orderType :: " + orderType)
	if(bidsPrice == "" || bidsAmount == "" || memberSeq == undefined){
		alert("주문가격 또는 수량을 입력해주세요")
	}else(
		$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/submitBids"
			,data:{
				"price" : bidsPrice
				,"obAmount" : bidsAmount
				,"memberSeq" : memberSeq
				,"cryptoSeq" : cryptoSeq
				,"orderType" : orderType
			}
			,success:function(rt){
				if(rt == submitBids){
					console.log("submitBids ok")
				}
			}
			,error : function(err){
				console.log("submitBids err")
			}
		})
	)
	selectBOBOne()
	console.log("getOneObFromBroker")
}
//submit Asks
function submitAsks(){
	var asksPrice = $('#asksPrice').val();
	var asksAmount = $('#asksAmount').val();
	var memberSeq = $('#memberSeq').val();
	var cryptoSeq = $('#cryptoSeq').val();
	var orderType = $('label.otLabel input[name="orderTypeSell"]:checked').val()
	console.log("매도주문 신청 내역입니다.")
	console.log("asksPrice :: " + asksPrice)
	console.log("asksAmount :: " + asksAmount)
	console.log("memberSeq :: " + memberSeq)
	console.log("cryptoSeq :: " + cryptoSeq)
	console.log("orderType :: " + orderType)
	if(asksPrice == "" || asksAmount == "" || memberSeq == undefined){
		alert("주문가격 또는 수량을 입력해주세요")
	}else(
		$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/submitAsks"
			,data:{
				"price" : asksPrice
				,"obAmount" : asksAmount
				,"memberSeq" : memberSeq
				,"cryptoSeq" : cryptoSeq
				,"orderType" : orderType
			}
			,success:function(rt){
				if(rt == submitAsks){
					console.log("submitAsks ok")
				}
				
			}
			,error : function(err){
				console.log("submitAsks err")
			}
		})
	)
	selectSOBOne()
	console.log("getOneObFromBroker")
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
			,url:"/exchange/selectBOBOne"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(selectBOBOne){
				console.log("selectBOBOne :: " + selectBOBOne)
			}
			,error : function(err){
				console.log("getList err")
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
			,url:"/exchange/selectSOBOne"
			,data:{
				"cryptoSeq" : cryptoSeq
			}
			,success:function(selectSOBOne){
				console.log("selectSOBOne :: "+ selectSOBOne)
			}
			,error : function(err){
				console.log("getList err")
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
					
					console.log("selectBOB :: "+ getPrice +" // "+ obAmount)
					maxAppendB += 1;
					
					boBtr(getPrice,obAmount)
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
				console.log("selectSOB ::" + selectSOB)
				for(i = 0; i < selectSOB.length; i ++){
					
					var getPrice = selectSOB[i].price
					var obAmount = selectSOB[i].obAmount
					
					console.log("selectSOB :: "+ getPrice +" // "+ obAmount)
					maxAppendS += 1;
					soBtr(getPrice,obAmount)
				}
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}
//request select OBList throu Ajax end

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #Ajax request end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #util Functions start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
	boBtr += '<tr class="up downtest boBtr">';
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
	boBtr += '<p>'+ obAmount +'</p>';
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
	boBtr += '<tr class="up downtest boBtr">';
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
	boBtr += '<p>'+ obAmount +'</p>';
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
	sobtr += '<tr class="down downtest soBtr">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a href="#">';
	sobtr += '<div style="width: 84.4%;"></div>';
	sobtr += '<p>'+obAmount+'</p>';
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
	sobtr += '<tr class="down downtest soBtr">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a href="#">';
	sobtr += '<div style="width: 84.4%;"></div>';
	sobtr += '<p>'+obAmount+'</p>';
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

function deleteObTr(obSeq){
	console.log("deleteObTr")
	var inputHiddenObSeq = $('input[name="obSeq'+obSeq+'"]').val()
	console.log(
		"deleteObTr() ::tr obSeq value값은 :: "+ inputHiddenObSeq + " 입니다. \n"
		+"해당 요소를 삭제합니다.")
	
}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@ #util Functions end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/ 

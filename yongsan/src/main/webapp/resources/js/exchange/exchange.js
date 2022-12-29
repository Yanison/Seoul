$(document).ready(function(){
	cryptoList()
	getUserBal();
	
	
	
	//orderContainer
	var navItem = $('.navItem')
	
	navItem.click(function(){
		$(this).addClass('active');
		navItem.not($(this)).removeClass('active');
	})

	//네비게이션 페이지네이션
	$('#oderContainer').scrollTop(350);
	$('#orderContaineritem2').hide()
	$('#orderContaineritem2_1').hide();
	$('#orderContaineritem3').hide()
	$('#orderContaineritem3_1').hide();
	
	
	$('#orderContainerPrice').click(function(){
		
		$('#orderContaineritem1').show();
		$('#orderContaineritem1_1').show();
		$('#orderContaineritem2').hide();
		$('#orderContaineritem2_1').hide();
		$('#orderContaineritem3').hide();
		$('#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderPrice').click(function(){
		
		$('#orderContaineritem1').hide();
		$('#orderContaineritem1_1').hide();
		$('#orderContaineritem2').show();
		$('#orderContaineritem2_1').show();
		$('#orderContaineritem3').hide();
		$('#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderNavigation').click(function(){
		
		$('#orderContaineritem1').hide();
		$('#orderContaineritem1_1').hide();
		$('#orderContaineritem2').hide();
		$('#orderContaineritem2_1').hide();
		$('#orderContaineritem3').show();
		$('#orderContaineritem3_1').show();
	})
	
	//bosBox
	$('.nav2_1').click(function(){
		$('.nav2_1').addClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').show();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_2').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').addClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').show();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_3').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').addClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').show();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_4').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').addClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').show();
	})
	
	
	
	
		
	
	//수량가격 체인지
	$('#amountToggle01').click(function(){
		var amountToggle = $('#amountToggle01').val()
		console.log(amountToggle)
		
		if(amountToggle == '1'){
			$('#amountToggle01').html(
				'<b>'+'수량'+'</b>' +
				'<em>'+
				'(BTC)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'
			)
			var amountToggle = $('#amountToggle01').val('2')
		}else{
			$('#amountToggle01').html(
				'<b>'+'총액'+'</b>' +
				'<em>'+
				'(KRW)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'			
			)
			var amountToggle = $('#amountToggle01').val('1')
		}
	})
	$('#amountToggle02').click(function(){
		var amountToggle = $('#amountToggle02').val()
		console.log(amountToggle)
		
		if(amountToggle == '1'){
			$('#amountToggle02').html(
				'<b>'+'수량'+'</b>' +
				'<em>'+
				'(BTC)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'
			)
			var amountToggle = $('#amountToggle02').val('2')
		}else{
			$('#amountToggle02').html(
				'<b>'+'총액'+'</b>' +
				'<em>'+
				'(KRW)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'			
			)
			var amountToggle = $('#amountToggle02').val('1')
		}
	})
	
	console.log("onload :: "+ " memberSeq :: " + memberSeq + " cryptoSeq :: " +  cryptoSeq + " cryptoSym :: " + cryptoSym)
})

const memberSeq = $('#memberSeq').val()
const cryptoSeq = $('#cryptoSeq').val()
const cryptoSym = $('#coinSym').val()
let pendingCash;
let pendingAmount;

function orderSumB(){
	let tp = bidsPrice.value*bidsAmount.value
	bidsSum.value = tp
}
function selectPrice(e){
	let thisPrice = $(e).find('strong').text()
	$('input.recentPrice').val(thisPrice)
	orderSumB()
}
function selectAmount(e){
	let thisAmount = $(e).find('p').text();
	$('input#asksAmount').val(thisAmount)
	$('input#bidsAmount').val(thisAmount)
	orderSumB()
	
}
const bidsPrice = document.getElementById("bidsPrice");
const bidsAmount = document.getElementById("bidsAmount");
const bidsSum = document.getElementById("bidsSum");
bidsPrice.addEventListener('keyup',orderSumB)
bidsAmount.addEventListener('keyup',orderSumB)
bidsPrice.addEventListener('change',orderSumB)
bidsAmount.addEventListener('change',orderSumB)


function getUserBal(){
	var cryptoSeq = $('#cryptoSeq').val()
	var memberSeq = $('#memberSeq').val()
	
	var html ="";
	html += '<div class="pleaseLogin">'
	html += '<a id="kkoadduser" class="pleaseLoginBtn" href="/userLoginkko">로그인</a>'
	html += '</div>'
	
	
	if(memberSeq == ""){
		$('.orderBoxBody').html(html)
	}else{
		html = null;
	}
	//userBalance
	if(memberSeq != ""){
		$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/userBalance"
			,data:{
				'shSelectOne': 0
				,"memberSeq" : memberSeq
				,"cryptoSeq" : cryptoSeq
				}
			,success: function(userBalance){
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
					
					$('#KRWBal').val(availableCash)
					$('#inputKRWBal').val(availableCash)
					$('#input'+cryptoSym+'Bal').val(availableCtpyto)
				}
			}
			,error : function(err){
				alert("노노")
			}
		})
	}else{
		console.log("no idTokenKko session :: " +idTokenKko);
	}
}
function getOnLoadInfo(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/userBalance"
			,data:{"idTokenKko" : idTokenKko}
			,success: function(userBalance){
				console.log(userBalance)
				$('#cryptoSeq').val(userBalance.cryptoSeq)
			}
			,error : function(err){
				alert("getOnLoadInfo userBalance :: err")
			}
			
		})
}
function  orderHis(){
	myOrder()
	myTransaction()
}

function myOrder(){
	let pendingOrdersHis = $('#pendingOrdersHis tbody')
	$('#pendingOrdersHis tbody tr').remove();
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/requestMyOrders"
			,data:{
				'memberSeq' : memberSeq,
				'cryptoSeq' : cryptoSeq,
				'limit': 20
				}
			,success: function(requestMyOrders){
				
				
				for(let i = 0 ; i < requestMyOrders.length; i ++){
					
					let bos;
					if(requestMyOrders[i].bos == 0){
						bos = "매수"
					}else{
						bos = "매도"
					}
					let html1 = ''
					html1 += '<tr id="hisObSeq'+requestMyOrders[i].obSeq+'" class="exHis"><td>'
					html1 += 	'<div class="exHis_Day">'+requestMyOrders[i].timestampMD+'</div>'
					html1 += 	'<div class="exHis_time">'+requestMyOrders[i].timestampR+'</div>'
					html1 += '</td>'
					html1 += '<td>'
					html1 += 	'<div class="exHis_Pairs"><strong>'+ cryptoSym +'/KRW</strong></div>'
					html1 += 	'<div id="exHis_bos'+requestMyOrders[i].obSeq+'" value="'+requestMyOrders[i].bos+'" class="exHis_TradeType" style="color:">'+bos+'</div>'
					html1 += '</td>'
					html1 += '<td>'
					html1 += 	'<div class="exHis_Price"> - </div>'
					html1 += 	'<div class="exHis_MyPrice">'+requestMyOrders[i].price+'</div>'
					html1 += '</td>'
					html1 += '<td>'
					html1 += 	'<div class="exHis_Amount">'+requestMyOrders[i].obAmount.toFixed(4)+'</div>'
					html1 += '</td>'
					html1 += '<td>'
					html1 += 	'<button id="cancelOrder" value="'+requestMyOrders[i].obSeq+'" onclick="cancelOrder(this)">취소</button>'
					html1 += '</td>'
					html1 += '</tr>'
					
					pendingOrdersHis.append(html1)
				}
			}
			,error : function(err){
				alert("getOnLoadInfo userBalance :: err")
			}
			
		})
}

function myTransaction(){
	const transactedOrdersHis = $('#transactedOrdersHis tbody')
	$('#transactedOrdersHis tbody tr').remove()
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/requestMytransaction"
			,data:{
				'memberSeq' : memberSeq,
				'cryptoSeq' : cryptoSeq,
				'bos': 2,
				'limit': 20
				}
			,success: function(requestMytransaction){
				
				for(let i = 0 ; i < requestMytransaction.length; i ++){
					let bos;
					if(requestMytransaction[i].memberSeqSell != null || requestMytransaction[i].memberSeqSell != undefined){
						bos = "매수"
					}else{
						bos = "매도"
					}
					
					let html2 = ''
					html2 += '<tr class="exHis">'
					html2 += '<td>'
					html2 += '<div class="exHis_Day">'+requestMytransaction[i].timestampMD+'</div>'
					html2 += '<div class="exHis_time">'+requestMytransaction[i].timestampR+'</div>'
					html2 += '</td>'
					html2 += '<td>'
					html2 += '<div class="exHis_Pairs"><strong>'+cryptoSym+'/KRW</strong></div>'
					html2 += '<div class="exHis_TradeType">'+bos+'</div>'
					html2 += '</td>'
					html2 += '<td>'
					html2 += '<div class="exHis_Price"> - </div>'
					html2 += '<div class="exHis_MyPrice">'+requestMytransaction[i].price+'</div>'
					html2 += '</td>'
					html2 += '<td>'
					html2 += '<div class="exHis_Amount">'+requestMytransaction[i].amount+'</div>'
					html2 += '</td>'
					html2 += '</tr>'
					
					transactedOrdersHis.append(html2)
				}
			
			}
			,error : function(err){
				alert("getOnLoadInfo userBalance :: err")
			}
			
		})
}

function cryptoList(){
	$.ajax({
		url:'/exchange/getCryptoList'
		,type:'get'
		,data:{}
		,success:function(cryptoList){
			for(let i = 0 ; i < cryptoList.length;i++){
				console.log(cryptoList[i])
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
		}
	})
}
function toThisCoin(e){
	let cryptoSym = $(e).find('.getCryptoSym').text();
	location.href='/exchange/'+cryptoSym
}
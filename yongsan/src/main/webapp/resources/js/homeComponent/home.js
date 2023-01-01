$(document).ready(function(){
	connect()
	cryptoList()
});
var stompClient = null;

function connect() {
    var socket = new SockJS('/exchWebSocketService');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
	
		stompClient.subscribe('/topic/selectCryptoList', function (cryptoList){
			var cryptoList = JSON.parse(cryptoList.body)
			
			let n = cryptoList.length;
			$('#cryptoListBar').remove('tr')
			for(let i = 0 ; i < n ; i ++){
				$('#'+cryptoList[i].cryptoSym+'_name').text(cryptoList[i].cryptoName)
				$('#'+cryptoList[i].cryptoSym+'_sym').text(cryptoList[i].cryptoSym)
				$('#'+cryptoList[i].cryptoSym+'_price').text(p(cryptoList[i].recentPrice)+" KRW")
				$('#'+cryptoList[i].cryptoSym+'_priceGapGap').text(cryptoList[i].ratioPre+" %")
				$('#'+cryptoList[i].cryptoSym+'_gap').text(p(cryptoList[i].priceGap) + " KRW")
				
			}
		});
    });
}

function cryptoList(){
	$.ajax({
		url:'/exchange/getCryptoList'
		,type:'get'
		,data:{}
		,success:function(cryptoList){
			
			for(let i = 0 ; i < cryptoList.length;i++){
				
				let recentPrice = cryptoList[i].recentPrice
				let gap = cryptoList[i].gap
				
				$('#'+cryptoList[i].cryptoSym+'_name').text(cryptoList[i].cryptoName)
				$('#'+cryptoList[i].cryptoSym+'_sym').text(cryptoList[i].cryptoSym)
				$('#'+cryptoList[i].cryptoSym+'_price').text(p(cryptoList[i].recentPrice)+" KRW")
				$('#'+cryptoList[i].cryptoSym+'_priceGapGap').text(cryptoList[i].ratioPre+" %")
				$('#'+cryptoList[i].cryptoSym+'_gap').text(p(cryptoList[i].priceGap) + " KRW")
				
			}
		}
	})
}

function chartTable(cryptoSeq){
	console.log('chartTable')
	$.ajax({
		async:true
		,cache:false
		,type:"post"
		,url:"/exchange/drawChart"
		,data:{
			'cryptoSeq' : cryptoSeq,
			'limit' : 30
			}
		,success:function(data){
			let chartData =[]
			let len = data.length;
			for(let i = 0; i < len ; i ++){
				//[x축 시간,최솟값,초기값,최종값,최댓값,원통의 도움말 혹은 스타일(선택)]
				let candle = [data[i].duration,data[i].low,data[i].startPrice,data[i].closingPrice,data[i].high]
				chartData.push(candle)
			}
			drawChart(chartData)
			
		}
		,error:function(err){
			alert("selectTransacton err")
		}
	})
}

function p(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}





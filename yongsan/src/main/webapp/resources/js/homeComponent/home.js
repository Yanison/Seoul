$(document).ready(function(){
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
	connect()
	cryptoList()
	chartTable(2)
	chartTable(3)
	chartTable(4)
	for(let i = 0  ; i <homeCrypto.length; i++){
		console.log("homeCrypto[i].cryptoSeq  "+homeCrypto[i].cryptoSeq)
		//chartTable(homeCrypto[i].cryptoSeq)
	}
});

let homeCrypto = {
	'cryptoSeq' : 2,
	'cryptoSeq' : 3,
	'cryptoSeq' : 4,
}

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
				console.log(cryptoList[i])
				$('#'+cryptoList[i].cryptoName+'_name').text(cryptoList[i].cryptoName)
				$('#'+cryptoList[i].cryptoSym+'_sym').text(cryptoList[i].cryptoSym)
				$('#'+cryptoList[i].recentPrice+'_price').text(cryptoList[i].recentPrice)
				$('#'+cryptoList[i].gap+'_gap').text(cryptoList[i].gap)
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
				$('#'+cryptoList[i].cryptoSym+'_price').text(priceToString(recentPrice)+" KRW")
				$('#'+cryptoList[i].cryptoSym+'_gap').text(priceToString(gap)+" KRW")
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
function drawChart(chartData) {
	var options = {
      legend:'none',
      candlestick: {
        fallingColor: { strokeWidth: 0, fill: '#0062df' }, // red
        risingColor: { strokeWidth: 0, fill: '#c84a31' }   // green
      },
      hAxis:{direction: -1}
    };

	        			
    data = google.visualization.arrayToDataTable(chartData, true);
    
    var chart4 = new google.visualization.CandlestickChart(document.getElementById('4Chart'));
    var chart3 = new google.visualization.CandlestickChart(document.getElementById('3Chart'));
    var chart2 = new google.visualization.CandlestickChart(document.getElementById('2Chart'));

    chart4.draw(data, options);
    chart3.draw(data, options);
    chart2.draw(data, options);
}



function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}





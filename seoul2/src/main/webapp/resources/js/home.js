$(document).ready(function(){
	
	init();  // 실행.
});

// 웹소켓. 

function init(){
    connectUpnitWebSocket();
}

var coinList = ["KRW-BTC","KRW-XRP","KRW-ETH"];


function connectUpnitWebSocket(){
    websocket = new WebSocket("wss://api.upbit.com/websocket/v1");
    websocket.binaryType = 'arraybuffer';
    //websocket.binaryType = 'Blob';
    //websocket.binaryType = 'String';
    // 전송하는 방식을 blob 이나 string 어떤걸로 설정할지 정할 수 있는데 본문에는 arraybuffer 배열로 한 것 같음. 
   
//    연결이 성공 되면 아래 순차적으로 진행이 돰
    websocket.onopen = function(evt) { 
    	
    	
//    	onOpen(evt); // 연결됐을때 이벤트 자동으로 들어옴. ex ) 아작스 success
    	
//    	getCoinInfo("KRW-BTC");
//    	getCoinInfo("KRW-ETH");
//    	getCoinInfo("KRW-XRP");// 여기까지 는 좋은데 코인이 하나 추가될때마다 계속 이렇게 입력해야함. 
    	
//    	그래서위에서 coinList 배열 변수 만들어주고 반복문 돌림
    	
//    	for(var i= 0; i< coinList.length; i++){
//    		var coin_name = coinList[i];
//    		getCoinInfo(coin_name);
//    		
//    		console.log(coin_name)
//    	}
    	
    	
    	// 그러나 이걸 함수로 따로 객체롬 만들거임. 
    	getAllcoins()
    	
    	
    	
    	setInterval(function(){
    		getAllcoins()
    	}, 500);
    	
    	
    };
    websocket.onclose = function(evt) {  
    	// 소켓이 연결이 끊겼을때
    };
    websocket.onmessage = function(evt) {
    	onMessage(evt);
    	// _onMessage() 로 전달. 
    	

    };
    websocket.onerror = function(evt) { 
    	
    };
}

function getAllcoins(){
	for(var i= 0; i< coinList.length; i++){
		var coin_name = coinList[i];
		getCoinInfo(coin_name);
		
		console.log(coin_name)
	}
}


function getCoinInfo(coinName){
	var msg = [
		{
			"ticket"	: "TEST",
		},
		
		{
			"type"		: "ticker",
			"codes"		: [coinName] // 여기에 보고싶은 통화쌍 입력, 배열이니깐 여러게 입력 가능. 
		}
	];
	msg = JSON.stringify(msg); // 배열 형태를 문자열로 바꾸는 과정
	doSend(msg); // 
	
}



//function onOpen(evt){
//	// 보낼 메세지를 만든것임. 
//	var msg = [
//		{
//			"ticket"	: "TEST",
//		},
//
//		{
//			"type"		: "ticker",
//			"codes"		: ["KRW-BTC"] // 여기에 보고싶은 통화쌍 입력, 배열이니깐 여러게 입력 가능. 
//		}
//
//	];
//	msg = JSON.stringify(msg); // 배열 형태를 문자열로 바꾸는 과정
//	doSend(msg); // 
//}

function doSend(message){
	websocket.send(message);
}


function onMessage(evt){
	var enc = new TextDecoder("utf-8");
	var arr = new Uint8Array(evt.data);
//	console.log(enc.decode(arr));
	
//	var obj = enc.decode(arr);
	
	var obj =JSON.parse(enc.decode(arr)) ; // byte 형태를 json 형태로 바꿔줌. 
	
//	console.log('code : ' + JSON.parse(obj).code);
	console.log(obj);
	
	
	
	if(obj.code == "KRW-BTC"){
		var tp = obj.trade_price;
		var pcp = obj.prev_closing_price;
		
		$('#btcPrice').html(tp+ '원');
		$('#btcPreprice').html(pcp+ '원');
		
		if(Number(tp) > Number(pcp)){
			$('#btcPrice').css('color','#c84a31');
			$('#priceGapbtc').css('color','#c84a31');
			$('#updownbtc').css('color','#c84a31');
			$('#priceGapGapbtc').html('+'+tp-pcp);
			$('#arrowbtc').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-up"></i>'+'</span>');
		}else if(Number(tp) < Number(pcp)) {
			$('#btcPrice').css('color','#1261c4');
			$('#priceGapbtc').css('color','#1261c4');
			$('#updownbtc').css('color','#1261c4');
			$('#priceGapGapbtc').html(tp-pcp);
			$('#arrowbtc').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-down"></i>'+'</span>');
		}else {
			$('#btcPrice').css('color','#333333');
			$('#priceGapbtc').css('color','#333333');
			$('#updownbtc').css('color','#333333');
			$('#priceGapbtc').html(tp - pcp);
		};

		var priceRatio = Math.round(tp /pcp) / 100;
		$('#updownbtc').html(priceRatio + '%');
		
		
	}else if(obj.code == "KRW-XRP"){
		var tp = obj.trade_price;
		var pcp = obj.prev_closing_price;
		
		$('#xrpPrice').html(tp+ '원');
		$('#xrpPreprice').html(pcp+ '원');
		
		if(Number(tp) > Number(pcp)){
			$('#xrpPrice').css('color','#c84a31');
			$('#priceGapxrp').css('color','#c84a31');
			$('#updownxrp').css('color','#c84a31');
			$('#priceGapGapxrp').html('+'+tp-pcp);
			$('#arrowxrp').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-up"></i>'+'</span>');
		}else if(Number(tp) < Number(pcp)) {
			$('#xrpPrice').css('color','#1261c4');
			$('#priceGapxrp').css('color','#1261c4');
			$('#updownxrp').css('color','#1261c4');
			$('#priceGapGapxrp').html(tp-pcp);
			$('#arrowxrp').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-down"></i>'+'</span>');
		}else {
			$('#xrpPrice').css('color','#333333');
			$('#priceGapxrp').css('color','#333333');
			$('#updownxrp').css('color','#333333');
			$('#priceGapxrp').html(tp - pcp);
		};

		var priceRatio = Math.round(tp /pcp) / 100;
		$('#updownxrp').html(priceRatio + '%');
		
	}else if(obj.code == "KRW-ETH"){
		var tp = obj.trade_price;
		var pcp = obj.prev_closing_price;
		
		$('#ethPrice').html(tp+ '원');
		$('#ethPreprice').html(pcp+ '원');
		
		if(Number(tp) > Number(pcp)){
			$('#ethPrice').css('color','#c84a31');
			$('#priceGapeth').css('color','#c84a31');
			$('#updowneth').css('color','#c84a31');
			$('#priceGapGapeth').html('+'+tp-pcp);
			$('#arroweth').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-up"></i>'+'</span>');
		}else if(Number(tp) < Number(pcp)) {
			$('#ethPrice').css('color','#1261c4');
			$('#priceGapeth').css('color','#1261c4');
			$('#updowneth').css('color','#1261c4');
			$('#priceGapGapeth').html(tp-pcp);
			$('#arroweth').html('<span class="arrowup">'+'<i class="fa-solid fa-caret-down"></i>'+'</span>');
		}else {
			$('#ethPrice').css('color','#333333');
			$('#priceGapeth').css('color','#333333');
			$('#updowneth').css('color','#333333');
			$('#priceGapeth').html(tp - pcp);
		};

		var priceRatio = Math.round(tp /pcp) / 100;
		$('#updowneth').html(priceRatio + '%');
		
	}
	
//	for(var i = 0; i << )
	
	
//	websocket.close(); 전화 끊는다.
	
}


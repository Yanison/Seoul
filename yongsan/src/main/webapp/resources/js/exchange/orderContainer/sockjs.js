$(document).ready(function(){
		
	    var sock = new SockJS("/replyEcho"); 
	    
    	sock.onopen = function () {
	        console.log('Info: connection opened.');
	        sock.send("hi~");
	        sock.onmessage = function (event) {
			console.log(event.data+'\n');
	        };
	        sock.onclose = function (event) {
	            console.log('Info: connection closed.');
	        };
   		};
		
		
								
//	 var ws = new SockJS("http://localhost:8082/replyEcho");
//						
//	//커넥션이 연결이 됐을때 이 메소드를 탐
//    ws.onopen = function () {
//        console.log('Info: connection opened.');
//        setTimeout( function(){ connect(); }, 1000); // retry connection!!
//    };
//
//
//    ws.onmessage = function (event) {
//        console.log(event.data+'\n');
//    };
//
//
//    ws.onclose = function (event) { console.log('Info: connection closed.'); };
//    ws.onerror = function (event) { console.log('Info: connection closed.'); };
//    
//    $('#btnSend').on('click', function(evt) {
//	  evt.preventDefault();
//  if (socket.readyState !== 1) return;
//    	  let msg = $('input#msg').val();
//    	  ws.send(msg);
//    }); 


})
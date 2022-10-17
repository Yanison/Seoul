$(document).ready(function(){
	connect();
})
var sockJS = null;
var stompClient = null;

function connect(){
	sockJS = new SockJS("/S2C")
	stompClient = Stomp.over(sockJS); // stomp client 구성
	stompClient.connect({},function(frame){
		console.log('connected stomp over sockjs');
		//subscribe msg
		stompClient.subscribe('/subscribe/notice',onMessage)
		console.log('subscribed');
	})
}

function onMessage(message){
	console.log("received Data From server" + message);
	$('chatArea').append(message.body + "<br/>");
}
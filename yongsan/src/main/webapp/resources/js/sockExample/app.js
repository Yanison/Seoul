$(document).ready(function(){
	
})

var stompClient= null;

function setConnect(connected){
	$('#connect').prop("disabled",connected)
	$('#disconnect').prop("disabled",connected);
	if(connected){
		$("#conversation").show();
	}
	else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
    
}

function connect() {
    var socket = new SockJS('/sockExmpl');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnect(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnect(false);
    console.log("Disconnected");
}

function sendName(){
	stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
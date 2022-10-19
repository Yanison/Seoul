$(document).ready(function(){
	ajaxBob()
})

var stompClient = null;
var maxAppend = 0;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/SimpMessagingTemplate', function (greeting) {
            showGreeting("subscribe test :: "+JSON.parse(greeting.body).content);
 
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr class='appendMsg'><td>" + message + "</td></tr>");
    maxAppend += 1;
    console.log("maxAppend :: " + maxAppend);
    
    if(maxAppend > 10){
		$('.appendMsg:first-child').remove();
	}
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});


function forEachBOB(){
	console.log('forEachBOB start')
	var obHtml = "" ;
	
	obHtml += '<c:choose>';
	obHtml += '<c:when test="${fn:length(selectBOB) eq 0}"><tr><td colspan="3"> no Order Data!</td></tr></c:when>';
	obHtml += '<c:otherwise>';
	obHtml += '<c:forEach items="${selectBOB}" var="selectBOB" varStatus="status">';
	obHtml += '<tr class="up downtest">';
	obHtml += '<td class="upB">';
	obHtml += '<a href="#"><div class="ty03"><strong><c:out value="${selectBOB.price}"/></strong></div><div class="ty02">ratio%</div></a>';
	obHtml += '</td>';
	obHtml += '<td class="bar"><a href="#"><div style="width: 84.4%;"></div><p><c:out value="${selectBOB.obAmount}"/></p></a>';
	obHtml += '</td>';
	obHtml += '<td class="last"></td>';
	obHtml += '</tr>';
	obHtml += '</c:forEach>';
	obHtml += '</c:otherwise>';
	obHtml += '</c:choose>';
	
	$("#greetings").html(obHtml);
	console.log('forEachBOB end')
}

function ajaxBob(){
	console.log("ajaxBob")
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/sockAjaxList"
			,data:{}
			,success:function(bobList){
				console.log(bobList)
				for(var i = 0 ; i <= bobList.length; i ++){
					var now = new Date();
					var appendHtml = null;
					appendHtml += "<tr class='appendMsg price'><td>" + now + "// price :: " +bobList[i].price+ "// obAmount :: " +bobList[i].obAmount + "</td></tr>"
					$("#greetings").append(appendHtml);
					console.log(appendHtml)
				}
				
				if(bobList.length > 10){
					$('.appendMsg:first-child').remove()
				}
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}
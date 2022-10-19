$(document).ready(function(){
	console.log("getOBByWebSock")
	connect();
})

var stompClient = null;

function connect() {
    var socket = new SockJS('/exchWebSocketService');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/submitBids', function (observeSubmittedBOB) {
            console.log('from server')
            console.log(JSON.parse(observeSubmittedBOB.body).content);
            forEachBOB()
        });
        stompClient.subscribe('/topic/submitAsks', function (observeSubmittedSOB) {
            console.log("subscribe test :: "+JSON.parse(observeSubmittedSOB.body).content);
            forEachSOB()
        });
    });
}

function forEachSOB(){
	console.log('forEachSOB start')
	var obHtml = "";
	
	obHtml += '<c:choose>';
	obHtml += '<c:when test="${fn:length(selectSOB) eq 0}"><tr><td colspan="3"> no Order Data!</td></tr></c:when>';
	obHtml += '<c:otherwise>';
	obHtml += '<c:forEach items="${selectSOB}" var="selectSOB" varStatus="status">';
	obHtml += '<tr class="down downtest">';
	obHtml += '<td></td>';
	obHtml += '<td class="bar">';
	obHtml += '<a href="#"><div style="width: 84.4%;"></div><p><c:out value="${selectSOB.obAmount}"/></p></a>';
	obHtml += '</td>';
	obHtml += '<td class="downB">';
	obHtml += '<a href="#"><div class="ty03"><strong><c:out value="${selectSOB.price}"/></strong></div><div class="ty02">ratio%</div></a>';
	obHtml += '</td>';
	obHtml += '</tr>';
	obHtml += '</c:forEach>';
	obHtml += '</c:otherwise>';
	obHtml += '</c:choose>';
	
	$('#asksTbody').html(obHtml);
	console.log('forEachSOB end')
}

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
	
	$('#bidsTbody').html(obHtml);
	console.log('forEachBOB end')
}
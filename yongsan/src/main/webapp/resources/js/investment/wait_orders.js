
$(document).ready(function(){
	connect()
	if(memberSeq != ""){
		//requestPendingHistory()
	}else{
		console.log("no Seq")
	}
})

const memberSeq = $('#memberSeq').val();
const cryptoSeq = $('#order_cryptoSeq').val();

function requestPendingHistory(){
	$.ajax({
		type:'post'
		,data:{
			memberSeq:memberSeq
		}
		,url:'/investments/wait_orders/requestPendingHistory'
		,success:function(e){
			
			$('#hitoryTable_Table_Body tbody tr').remove();
			
			for(let i=0;i < e.length;i ++){
				let timestampMD = e[i].timestampMD
				let timestampR = e[i].timestampR
				let obSeq = e[i].obSeq
				let bos = e[i].bos
				let price = e[i].price
				let orderAmount = e[i].orderAmount
				let obAmount = e[i].obAmount
				let cryptoSeq = e[i].cryptoSeq
				let cryptoName = e[i].cryptoName
				let cryptoSym = e[i].cryptoSym
				
			let html =""
			html += `
			<tr id="tr`+obSeq+`">
				<td>
					<div>
						<span id="timestampYMD">`+timestampMD+`</span>
					</div>
					<div>
						<span id="timestampHM">`+timestampR+`</span>
					</div>
				</td>
				<td class="market">
					<strong class="table_column crptoSym">`+cryptoSym+`/KRW</strong>
				</td>
				<td class="bos">
					<span id="bos">`+bos+`</span>
				</td>
				<td>
					<span class="table_column price_KRW">-</span>
					<i class="table_column KRW">KRW</i>
				</td>
				<td>
					<span class="table_column price_KRW">`+price+`</span>
					<i class="table_column KRW">KRW</i>
				</td>
				<td>
					<span class="table_column amount">`+orderAmount+`</span>
					<i class="table_column crptoSym">`+cryptoSym+`</i>
				</td>
				<td>
					<span class="table_column amount">`+obAmount+`</span>
					<i class="table_column crptoSym">`+cryptoSym+`</i>
				</td>
				<td class="cancelOrderTd">
					<button value="`+obSeq+`" onclick="cancelOrder(this)">주문취소</button>
					<input id="order_cryptoSeq" type="hidden" value="`+cryptoSeq+`">
				</td>
			</tr>
			`
			$('#hitoryTable_Table_Body tbody').append(html);
			}
			
		}
		,error:function(e){
			alert("err")
		}
	})
}


var stompClient = null;

function connect() {
    var socket = new SockJS('/exchWebSocketService');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/cancelOrder', function (cancelOrder){
			var cancelOrder = JSON.parse(cancelOrder.body)
			let obSeq = cancelOrder;
			
			$('#tr'+cancelOrder).remove()
		});
        
    });
}

function cancelOrder(e){
	if(!confirm("취소하시겠습니까?")){return false;}
	let obSeq = $(e).val();
	
	data ={
		'obSeq' : obSeq,
		'memberSeq' : $('#memberSeq').val(),
		'cryptoSeq' :  $('#order_cryptoSeq').val()
	}
	
	stompClient.send("/app/cancelOrder", {}, JSON.stringify(data));
}
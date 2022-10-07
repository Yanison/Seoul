$(document).ready(function(){

	
	
	//submit Bids

	$('#submitBids').click(function(){
		var bidsPrice = $('#bidsPrice').val();
		var bidsAmount = $('#bidsAmount').val();
		var memberSeq = $('#memberSeq').val();
		var cryptoSeq = $('#cryptoSeq').val();
	
		console.log("bidsPrice :: " + bidsPrice)
		console.log("bidsAmount :: " + bidsAmount)
		console.log("memberSeq :: " + memberSeq)
		console.log("cryptoSeq :: " + cryptoSeq)
		if(bidsPrice == "" || bidsAmount == "" || memberSeq == undefined){
			alert("주문가격 또는 수량을 입력해주세요")
		}else(
			$.ajax({
				async:true
				,cache:false
				,type:"post"
				,url:"/exchange/submitBids"
				,data:{
					"price" : bidsPrice
					,"obAmount" : bidsAmount
					,"memberSeq" : memberSeq
					,"cryptoSeq" : cryptoSeq
				}
				,success:function(rt){
					if(rt == submitBids){
						console.log("submitBids ok")
					}
					
				}
				,error : function(err){
					console.log("submitBids err")
				}
			})
		)
	})
	
	//submit Asks

	$('#submitAsks').click(function(){
		var asksPrice = $('#asksPrice').val();
		var asksAmount = $('#asksAmount').val();
		var memberSeq = $('#memberSeq').val();
		var cryptoSeq = $('#cryptoSeq').val();
	
		console.log("asksPrice :: " + asksPrice)
		console.log("asksAmount :: " + asksAmount)
		console.log("memberSeq :: " + memberSeq)
		console.log("cryptoSeq :: " + cryptoSeq)
		if(asksPrice == "" || asksAmount == "" || memberSeq == undefined){
			alert("주문가격 또는 수량을 입력해주세요")
		}else(
			$.ajax({
				async:true
				,cache:false
				,type:"post"
				,url:"/exchange/submitAsks"
				,data:{
					"price" : asksPrice
					,"obAmount" : asksAmount
					,"memberSeq" : memberSeq
					,"cryptoSeq" : cryptoSeq
				}
				,success:function(rt){
					if(rt == submitAsks){
						console.log("submitAsks ok")
					}
					
				}
				,error : function(err){
					console.log("submitAsks err")
				}
			})
		)
	})
	
});
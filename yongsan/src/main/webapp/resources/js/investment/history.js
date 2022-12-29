$(document).ready(function(){
	if(memberSeq != ""){
		//requestOrderHistory()
	}else{
		console.log("no Seq")
	}
})




const memberSeq = $('#memberSeq').val();
function requestOrderHistory(){
	$.ajax({
		type:'get'
		,data:{memberSeq:memberSeq}
		,url:'/investments/history/requestOrderHistory'
		,success:function(e){
			console.log(e)
			
			for(let i=0;i < e.length;i ++){
				let timestampMD = e[i].timestampMD
				let timestampR = e[i].timestampR
				let cryptoName = e[i].cryptoName
				let cryptoSym = e[i].cryptoSym
				let obSeq = e[i].obSeq
				let price = e[i].price
				let obAmount = e[i].obAmount
				let pp = e[i].pp
				let bos = e[i].bos
				let orderTimestampMD = e[i].orderTimestampMD
				let orderTimestampR = e[i].orderTimestampR
				
				let html = 	`
				<tr>
					<td>
						<div>
							<span id="timestampYMD">`+timestampMD+`</span>
						</div>
						<div>
							<span id="timestampHM">`+timestampR+`</span>
						</div>
					</td>
					
					<td class="cryptoSym">
						<strong class="table_column crptoSym">`+cryptoSym+`</strong>
					</td>
					
					<td class="market">
						<i class="table_column KRW">-</i>
					</td>
					
					<td class="bos">
						<span id="bos">`+bos+`</span>
					</td>
					
					<td>
						<span class="table_column amount">`+obAmount+`</span>
						<i class="table_column crptoSym">`+cryptoSym+`</i>
					</td>
					
					<td>
						<span class="table_column price_KRW">`+price+`</span>
						<i class="table_column KRW">KRW</i>
					</td>
					
					<td>
						<span class="table_column price_KRW">`+pp+`</span>
						<i class="table_column KRW">KRW</i>
					</td>
					
					<td>
						<div>
							<span id="timestampYMD">`+orderTimestampMD+`</span>
						</div>
						<div>
							<span id="timestampHM">`+orderTimestampR+`</span>
						</div>
					</td>
					
				</tr>  `
				
				$('#hitoryTable_Table_Body tbody').append(html);
			}
		}
		,error:function(e){
			alert("err")
		}
	})
}

function sh(e){
	
	console.log(e.value)
	let val = $(e).val()
	let target = $(e).attr('class')
	if(target == 'shValue'){
		val = $(e).text()
		$('#shCryptoResult').hide();
		$('#shCoinInp').val(val);
	}
//	console.log(val  +" // at "+ target)
//	$('input:hidden[name='+target+']').val(val);
//	
//	console.log("inp val() :: "+$('input:hidden[name='+target+']').val())
	$('.'+target).removeClass('active');
	$(e).addClass('active');
	let ch = $('input[name="shDuration"]:checked').val()
	let ch3 = $('input[name="shOption"]:checked').val()
	console.log(ch + ch3)
}

function shCryptoName(e){
	$.ajax({
		type:'get'
		,data:{shValue: $(e).val()}
		,url:'/investments/history/selectCryptoName'
		,success:function(e){
			$('#shCryptoResult div').remove()
			let n = e.length
			for(let i = 0 ; i < n ;i++){
				
				let cryptoName = e[i].cryptoName
				console.log(cryptoName)
				
				let html = `<div class="shValue" value="`+cryptoName+`" onclick="sh(this)">`+cryptoName+`</div>`
				$('#shCryptoResult').show();
				$('#shCryptoResult').append(html)
			}
			console.log()
		},error:function(){
			console.log('err')
		}
	})
}

function shResult(){
	let index = $('#index').val()
	goList(1)
}

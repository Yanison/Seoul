
const memberSeq = $('#memberSeq').val();
let asset;
let chartData = [['코인종류', '보유비중']]

$(document).ready(function(){
	
 
	console.log(memberSeq)
	if(memberSeq != ""){
		requestAssetEvaluation()
		requestTradeStatus()
	}else{
		console.log("no Seq")
	}
 	
})



google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
 	
function drawChart() {
	console.log(chartData)
    var data = google.visualization.arrayToDataTable(chartData);

    var options = {
      title: '보유비중',
      pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
 }


function requestTradeStatus(){
	$.ajax({
		type:'get'
		,data:{memberSeq:memberSeq}
		,url:'/investments/balance/requestTradeStatus'
		,success:function(e){
			for(let i = 0 ; i < e.length; i ++){
				let cryptoSeq  = e[i].cryptoSeq;
				let cryptoName  = e[i].cryptoName;
				let cryptoSym  = e[i].cryptoSym;
				let memberSeq  = e[i].memberSeq;
				let balance  = e[i].balance;
				let app  = e[i].app;
				let pp  = e[i].pp;
				let valuation  = e[i].valuation;
				let pnl  = e[i].pnl;
				let roi  = e[i].roi;
				
				let html = 	`
					<tr id="tr`+cryptoSeq+`">
						<td>
							<div class="table_column crptoSym_img">
								<i><i class="fa-brands fa-bitcoin"></i></i>
							</div>
							<div>
								<div class="table_column crptoName">`+cryptoName+`</div>
								<div class="table_column crptoSym">`+cryptoSym+`</div>
							</div>
						</td>
						<td>
							<strong class="table_column amount">`+p(balance)+`</strong>
							<i class="table_column crptoSym">`+cryptoSym+`</i>
						</td>
						<td>
							<strong class="table_column amount_KRW">`+p(app)+`</strong>
							<i class="table_column KRW">KRW</i>
						</td>
						<td>
							<strong class="table_column amount_KRW">`+p(pp)+`</strong>
							<i class="table_column KRW">KRW</i>
						</td>
						<td>
							<strong class="table_column amount_KRW">`+p(valuation)+`</strong>
							<i class="table_column KRW">KRW</i>
						</td>
						<td class="">
							<strong class="table_column amount_KRW">`+p(roi)+`</strong>
							<i class="table_column KRW">%</i>
							<br>
							<strong class="table_column amount_KRW">`+p(pnl)+`</strong>
							<i class="table_column KRW">KRW</i>
						</td>
					</tr> `
					let data = [cryptoSym,valuation]
					chartData.push(data)
					$('#AmontTable_Table tbody').append(html)
			}
		}
		,error:function(e){
			alert("err")
		}
	})
}
  
function requestAssetEvaluation(){
	$.ajax({
		type:'get'
		,data:{memberSeq:memberSeq}
		,url:'/investments/balance/requestAssetEvaluation'
		,success:function(e){
			
			let cashBalance = e.cashBalance
			let totalAsset = e.totalAsset
			let pnlAvg = e.pnlAvg
			let valuationAvg = e.valuationAvg
			let ppAvg = e.ppAvg
			let roiAvg = e.roiAvg
			
			$('#cashbalance').text(p(cashBalance))
			$('#totalAsset').text(p(totalAsset))
			$('#pnlAvg').text(p(pnlAvg))
			$('#valuationAvg').text(p(valuationAvg))
			$('#ppAvg').text(p(ppAvg))
			$('#roiAvg').text(p(roiAvg))
			
			let data = ['KRW',cashBalance]
			chartData.push(data)
		}
		,error:function(e){
			alert("err")
		}
	})
}

function p(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

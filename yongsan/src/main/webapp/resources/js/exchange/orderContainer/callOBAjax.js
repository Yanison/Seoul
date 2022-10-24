$(document).ready(function(){
	
})

function sobtr(price,amount){
	
	var sobtr = null;
	sobtr += '<tr class="down downtest">';
	sobtr += '<td></td>';
	sobtr += '<td class="bar">';
	sobtr += '<a href="#">';
	sobtr += '<div style="width: 84.4%;"></div>';
	sobtr += '<p>'+amount+'</p>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '<td class="downB">';
	sobtr += '<a href="#">';
	sobtr += '<div class="ty03">';
	sobtr += '<strong>'+price+'</strong>';
	sobtr += '</div>';
	sobtr += '<div class="ty02">ratio%</div>';
	sobtr += '</a>';
	sobtr += '</td>';
	sobtr += '</tr>';
	
	$('#bidsTbody').append(sobtr)
}

function boBtr(price,amount){
	
	var boBtr = null;
	boBtr += '<tr class="up downtest">';
	boBtr += '<td class="upB">';
	boBtr += '<a href="#">';
	boBtr += '<div class="ty03">';
	boBtr += '<strong>'+ price +'</strong>';
	boBtr += '</div>';
	boBtr += '<div class="ty02">ratio%</div>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="bar">';
	boBtr += '<a href="#">';
	boBtr += '<div style="width: 84.4%;"></div>';
	boBtr += '<p>'+ amount +'</p>';
	boBtr += '</a>';
	boBtr += '</td>';
	boBtr += '<td class="last"></td>';
	boBtr += '</tr>	';
	
	$('#bidsTbody').append(boBtr)
}





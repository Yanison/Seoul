$(document).ready(function(){
	console.log('callOBAjax.js')
})

var boBtr = null;
var sobtr = null;

function boBtr(price,amount){
	
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
}


function sobtr(price,amount){
	
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
}


function selectBOB(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectBOB"
			,data:{}
			,success:function(selectBOB){
			
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}

function selectBOBOne(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectBOBOne"
			,data:{}
			,success:function(selectBOBOne){
			
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}

function selectSOB(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectSOB"
			,data:{}
			,success:function(selectSOB){
			
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}

function selectSOBOne(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/selectSOBOne"
			,data:{}
			,success:function(selectSOBOne){
			
			}
			,error : function(err){
				console.log("getList err")
			}
		})
}
var form = $('form#paging');
console.log(form);
var goUrlList = $('#nowUrl').val()

goList = function(thisPage){
	
	$('input:hidden[name=thisPage]').val(thisPage);
	let rowNumToShow = $('input:hidden[name=rowNumToShow]').val();
	let startRnumForMysql  = rowNumToShow * (thisPage - 1)
	$('input:hidden[name=startRnumForMysql]').val(startRnumForMysql);
	
	
	let pagingUrl = '&thisPage='+thisPage+'&rowNumToShow='+rowNumToShow+'&startRnumForMysql='+startRnumForMysql
    let nowURl = window.location.href;
    let splitUrl = nowURl.split('?')
    let prUrl = splitUrl[0];
    let qstr = splitUrl[1];
    let param = splitUrl[1].split('&')
	
	let targetUrl = prUrl+'?'+param[0]+'&'+param[1]+'&'+param[2]+pagingUrl
	
	console.log(param)
	console.log(targetUrl)
	location.href=targetUrl
}
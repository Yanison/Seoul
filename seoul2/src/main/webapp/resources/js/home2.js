$(document).ready(function(){
	
	
//	//링크
//	$('#banner-link').on('click',function(){
//		event.preventDefault();
//		location.href='https://udc.upbit.comm/';
//	});
//	
//	
//	//시세
//	function comma(str) {
//        str = String(str);
//        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
//    }
//    function setUpbitData(){
//      $.ajax({
//        url: "https://api.upbit.com/v1/market/all",
//        dataType: "json"
//      }).done(function(markets){
//        //$("#tmp").html( JSON.stringify(markets) );
//        let arr_krw_markets = "";	// 빈칸 생성
//        let arr_korean_name = []; // 빈배열 생성
//        for(var i = 0; i < 33;i++){
//          if( markets[i].market.indexOf("KRW") > -1 ){ 
//        	 
//            arr_krw_markets += markets[i].market+(",");
//            arr_korean_name.push(markets[i].korean_name.replace("",""));
//          }
//        }
//        arr_krw_markets = arr_krw_markets.substring(0, arr_krw_markets.length-1);
//        //$("#tmp").html( arr_krw_markets );
//        
//        $.ajax({
//          url: "https://api.upbit.com/v1/ticker?markets=" +arr_krw_markets,
//          dataType: "json"
//        }).done(function(tickers){
//          $("#table_ticker > tbody > tr").remove();
//          //alert($("#table_ticker > tbody > tr").length);
//          //시세변동시 글씨 깜빡임 fadeout
//          $("#table_ticker").fadeOut(0.5);
//
//          for(let i = 0;i < tickers.length;i++){
//            let rowHtml = "<tr><td>"+arr_korean_name[i].replace("코인","").replace("토큰","")+"</td>";
//            //rowHtml += "<td>" + arr_korean_name[i] +"</td>"
//            rowHtml += "<td>" + comma(tickers[i].trade_price)+"</td>"
//            rowHtml += "<td>" + comma((tickers[i].signed_change_rate*100).toFixed(2))+"</td>"
//            rowHtml += "<td>" + comma((     tickers[i].acc_trade_price_24h>1000000 ? ( tickers[i].acc_trade_price_24h / 1000000 ) : tickers[i].acc_trade_price_24h ).toFixed(0)) + (tickers[i].acc_trade_price_24h>1000000 ? "백만" : "") + "</td>"
//            rowHtml += "</tr>";
//            $("#table_ticker > tbody:last").append(rowHtml);
//            //markets[i].korean_name
//          } // end for...
//          //시세변동시 글씨 깜빡임 fadein
//          $("#table_ticker").fadeIn(0.5);
//        })  //done(function(tickers){
//      }) // end done(function(markets){
//      .fail(function(){
//        //alert("업비트 API 접근 중 에러.")}
//        $("#tmp").text( "API 접근 중 에러." );
//      })
//      //시세변동시 글씨 깜빡임 간격
//      setTimeout(setUpbitData, 2000);
//    }
//    $(function() {
//      var color = localStorage.getItem("test_upbit_color");
//      if( color ) $("body").css("color", color);
//      setUpbitData();
//    });
//    function setColor(color){
//      localStorage.setItem("test_upbit_color", color);
//      $("body").css("color", color);
//    }
//	
//	
//	
//
//	
//
////end
});
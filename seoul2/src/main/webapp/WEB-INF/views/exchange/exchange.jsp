<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>거래소</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="./resources/css/exchange.css">

<!-- fontawsome -->
<script src="https://kit.fontawesome.com/a991bae8fd.js" crossorigin="anonymous"></script>

<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/exchange.js"></script>
<script src="./resources/js/getPrice.js"></script>



</head>
<body>
	<div class="exWrapper">
		<%@ include file="../include/header2.jsp" %>
		
		<div class="ex_main"> 
			<section class="trading">
				<div class="chartBox boxes">
				
					<div class="chartBoxHeader">					
						<div class="_chartBoxHeader">
							<div class="chartBoxHeaderLeft">
								<div class="dropdown">
								  <button class="btn btn-secondary" type="button" data-bs-toggle="dropdown" style="background:none; border:none; color: #666666">
								    <img class="btcLogo" src="https://static.upbit.com/logos/BTC.png" alt="https://static.upbit.com/logos/BTC.png" >			    
								    <strong style="font-size:20px; color:#333333; padding:5 5 5 0px;">비트코인</strong>
								    <span class="Arrow">Arrow</span>						    
								  </button>
								  <ul class="dropdown-menu">
								    <li><a class="dropdown-item" href="#"><strong>비트코인</strong>(BTC/KRW)</a></li>
								    <li><a class="dropdown-item" href="#"><strong>비트코인</strong>(BTC/USDT)</a></li>
								  </ul>
								</div>
							</div>
							<div class="chartBoxHeaderRight">
								<a class="cryptoPrice chartBoxHeaderContents">
									시세
								</a>
								<a class="cryptoInfo chartBoxHeaderContents">
									정보
								</a>
							</div>							
						</div>
						<div class="dropdown-center">
						  <button class="btn btn-secondary" type="button" data-bs-toggle="dropdown">
						    <i class="fa-solid fa-gear" style="color:#999999;"></i>
						  </button>
						  <ul class="dropdown-menu setting" style="transform: translate(-104px, 42px) !important;">
						    <li><a class="dropdown-item" href="#">Action</a></li>
						    <li><a class="dropdown-item" href="#">Action two</a></li>
						    <li><a class="dropdown-item" href="#">Action three</a></li>
						  </ul>
						</div>						
					</div>
					<div class="chartBoxIn" >
					</div>
					
					<div class="livePriceBox">
					
						<div class="livePrice">
							<div class="livePriceContents price">
								<span style="font-size:32px; height:38px;"> 
									<span id="btcPrice">num</span> 
									<span style="font-size:14px;">KRW</span> 
								</span>
								<div style="height:19px;">
									<span style="font-size:11px; margin-right:4px;">전일대비</span>
									<span id="updownbtc" style="font-size:16px; margin-right:4px;">num%</span>
									<span id="priceGapGapbtc" style="font-size:16px;">num</span>
								</div>
							</div>
							
							<div class="priceAndCap">
								<div class="livePriceContents hightAndLow">
									<div style="border-bottom:1px solid #dedede; margin-bottom:5px;">
										<span style="font-size:12px;">고가</span>
										<span style="font-size:14px;"> <strong id="highPricebtc"> num </strong> </span>
									</div >
									<div>
										<span style="font-size:12px;">저가</span>
										<span style="font-size:14px;"> <strong id="lowPricebtc"> num </strong> </span>
									</div>
								</div>
								<div class="livePriceContents cap">
									<div style="border-bottom:1px solid #dedede; margin-bottom:5px;">
										<span style="font-size:12px;">거래량(24H)</span>
										<span style="font-size:14px;"> <strong id="acc_trade_volume_24hbtc"> num </strong> <span style="font-size:11px;">BTC</span></span>
									</div>
									<div>
										<span style="font-size:12px;">거래량대금(24H)</span>
										<span style="font-size:14px;"> <strong id="acc_trade_price_24hbtc"> num </strong> <span style="font-size:11px;">KRW</span></span>
									</div>
								
								</div>
							</div>
						</div>
						<div class="otherMarket">
							<span class="otherMarketContents">
								<span class="marketName">Bitfinex</span>
								<span class="MPrice">29,893,044</span>
								<span class="MPriceUSD">($22,861.00)</span>
							</span>
							<span class="otherMarketContents">
								<span class="marketName">Kraken</span>
								<span class="MPrice">29,936,325</span>
								<span class="MPriceUSD">($22,894.00)</span>
							</span>
							<span class="otherMarketContents">
								<span class="marketName">Liquid</span>
								<span class="MPrice">29,958,307</span>
								<span class="MPriceUSD">(¥3,095,602.00)</span>
							</span>
						</div>
						<div class="liveChartbox">
							<div class="liveChartboxMenu">
								<div class="lCLeftMenu">
									<span class="menuContents">
										<i class="fa-solid fa-window-maximize"></i>
									</span>
								</div>
								<div class="lCRightMenu">
								</div>
							</div>
						</div>
					</div>
					
				</div>
				
				
				
				<div class="tradingBox">
					<div class="dibPriceBox boxes">
					</div>
					<div class="BoS boxes">
					</div>
				</div>
			</section>
			<section class="cryptoList">
				<div class="cryptoListIn">
				</div>
			</section>
		
		</div>
	</div>



</body>
</html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page session="false" %>
<html>
<head>
<title>거래소</title>

<%@ include file="../../rscs/basicRscs.jsp"%>

<link type="text/css" rel="stylesheet" href="../resources/css/exchange.css">
<script src="./resources/js/getPrice1.js"></script>
<script src="../resources/js/exchange/exchange.js"></script>
<script src="../resources/js/exchange/exchWebSock/getOBByWebSock.js"></script>

<!-- #c84a31; red
#0062df; blue 
-->


</head>
<body>
	<div class="exWrapper">
		<%@ include file="/WEB-INF/views/include/header2.jsp" %>
		<div class="ex_main"> 
			
			<section class="trading">
				<article class="exnotice">
					<div class="exnoticeL">
						<span style="display:block"><i class="fa-solid fa-exclamation"></i> <strong>공지</strong></span>
					</div>
					<div class="exnoticeR">
						<div>특정금융정보법에 따른 미신고 가상자산사업자와의 입출금 제한 및 유의사항</div>
						<div><i class="fa-thin fa-x"></i></div>
					</div>
				</article>
				<div class="chartBox boxes">
				
					<div class="chartBoxHeader">					
						<div class="_chartBoxHeader">
							<div class="chartBoxHeaderLeft">
								<div class="dropdown">
								  <button class="btn btn-secondary" type="button" data-bs-toggle="dropdown" style="background:none; border:none; color: #666666">
								    <img class="cryptoLogo" src="https://static.upbit.com/logos/BTC.png" alt="https://static.upbit.com/logos/BTC.png" >			    
								    <strong class="cryptoName"  style="font-size:20px; color:#333333; padding:5 5 5 0px;">비트코인</strong>
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
						<div class="dropdown-center" style="padding-top:5px;">
						  <button class="btn btn-secondary" type="button" data-bs-toggle="dropdown" style="margin-top:2px; width:30px; height:30px; padding:5px 8px;">
						    <i class="fa-solid fa-gear" style="color:#999999;"></i>
						  </button>
						  <ul class="dropdown-menu setting" style="transform: translate3d(-234.5px, 42px, 0px);;">
						    <li class="settingHeader settingContents">
							    <div>화면 설정</div>						
						    </li>
						    <li class="settingContents">
						    	<div class="darkMod">
						    		<div class="darkModBtn">
							    		<span class="darkModBtn1" style="border-right:1px solid #dedede;"><i class="fa-solid fa-sun"></i></span>
							    		<span class="darkModBtn2"><i class="fa-solid fa-moon"></i></span>
							    	</div>
							    	<div class="darkModText">
							    		<div style="font-size:13px; color:#666666;">화면 떼마 설정</div>
							    		<div style="font-size:12px; color:#999999;">데이(일반)모드</div>
							    	</div>	
						    	</div>							   						    							    	
						    </li>
						    <li class="settingContents">						    	
					    		<div class="form-check bidpriceChkBox">
								  <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate">
								  <label class="form-check-label" for="flexCheckIndeterminate" style="padding-top:1px;">
								    일반 호가에 평균가/누적량/누적액 노출
								  </label>
								</div>					    	
						    </li>
						    <li class="settingContents chartSetting">
						    	<div>차트 설정</div>
						    </li>
						    <li class="settingContents chartSettingHigh">
						    	<div class="darkMod">
						    		<div class="darkModBtn">
							    		<span class="darkModBtn1" style="border-right:1px solid #dedede;"><i class="fa-solid fa-plus" style=""></i></span>
							    		<span class="darkModBtn2"><i class="fa-solid fa-minus"></i></span>
							    	</div>
							    	<div class="darkModText">
							    		<div style="font-size:13px; color:#666666;">차트 높이 설정</div>
							    		<div style="font-size:12px; color:#c84a31;">가장 작은 높이의 차트입니다.</div>
							    	</div>	
						    	</div>
						    </li>
						    <li class="settingContents" >
						    	<div class="form-check bidpriceChkBox" style="border-bottom:1px solid #dedede;">
								  <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate">
								  <label class="form-check-label" for="flexCheckIndeterminate" style="padding-top:1px;">
								    차트에 매수평균가 기준선 노출
								  </label>
								</div>
						    </li>
						    <li class="settingContents" >
						    	<p style="font-size:12px; color:#666666; margin-top:18px;">
						    	차트설정 옵션은 회원님의 투자 편의를 제공하는 것으로, 업비트는 제공된 정보에 의한 투자 결과에 대해 책임을 지지 않습니다.
						    	</p>
						    </li>
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
					<div class="bidPriceBox boxes">
						<div class="bidPriceBoxNav nav">
							<div class="tabList">
								<a id="orderContainerPrice" class="nav-link navItem" aria-current="page" href="#1">일반호가</a>
								<a id="orderContainerOrderPrice" class="nav-link navItem" href="#2">누적호가</a>
								<a id="orderContainerOrderNavigation" class="nav-link navItem" href="#3">호가주문</a>
								<a class="myBorder">|</span>
							</div>
							<div>
								<div class="dropdown">
								  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" style="color:#333333; width:80px; height: 30px; margin-top:3px">
								    모아보기
								   <!--  모아보기는 옵션 단위로 축약해서 호가창을 볼 수 있는 기능임 -->
								  </button>
								  <ul class="dropdown-menu" style="transform: translate3d(-60px, 35px, 0px)">
								    <li><a class="dropdown-item" href="#">기본값</a></li>
								    <li><a class="dropdown-item" href="#">10,000</a></li>
								    <li><a class="dropdown-item" href="#">100,000</a></li>
								    <li><a class="dropdown-item" href="#">1,000,000</a></li>
								    <li><a class="dropdown-item" href="#">10,000,000</a></li>
								  </ul>
								</div>
							</div>
						</div>
						
						
						<!-- include -->
						<div id= "orderContainer" class="orderContainer">
							
								<!-- orderContainerPrice.jsp -->
								
								<div id="orderContaineritem1">
										<%@include file="/WEB-INF/views/exchange/orderContainer/orderContainerPrice2.jsp" %>						
								</div>
								<div id="orderContaineritem2">									
										<%@include file="/WEB-INF/views/exchange/orderContainer/orderContainerOrderPrice.jsp" %>								
								</div>
								<div id="orderContaineritem3">									
										<%@include file="/WEB-INF/views/exchange/orderContainer/orderContainerOrderNavigation.jsp" %>								
								</div>
						</div>
						<div id="orderContaineritem1_1">
								<div class="orderContainerTotal">
									<table class="total">
										<colgroup>
											<col width="42">
											<col width="120">
											<col width="*">
											<col width="120">
											<col width="42">
										</colgroup>
										<tr>
											<td></td>
											<td class="lAlign">soldamount</td>
											<td class="amountToggle">
												<a id="amountToggle01">
													<b>수량</b>
													<em>(BTC)<em>
													<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>
												</a>
											</td>
											<td class="rAlign">boughtamount</td>
											<td class="last"></td>
										</tr>									
									</table>									
								</div>
						</div>
						<div id="orderContaineritem2_1">
							<div class="orderContainerTotal">
								<table class="total">
									<colgroup>
										<col width="*">
										<col width="95">
										<col width="92">
										<col width="95">
										<col width="43">
									</colgroup>
									<tr>
										<td></td>
										<td class="amount">
											<b>수량</b>
											<em>(BTC)<em>	
										</td>
										<td class="orderPrice">
											<b>금액</b>
											<em>(KRW)<em>
										</td>
										<td class="total">
											<b>누적금액</b>
											<em>(KRW)<em>
										</td>
										<td class="last"></td>
									</tr>
								</table>
							</div>
						</div>
						<div id="orderContaineritem3_1">
							<div class="orderContainerTotal">
								<table class="total">
									<colgroup>
										<col width="42">
										<col width="120">
										<col width="*">
										<col width="120">
										<col width="42">
									</colgroup>
										<tr>
											<td></td>
											<td class="lAlign">soldamount</td>
											<td class="amountToggle">
												<a id="amountToggle02">
													<b>수량</b>
													<em>(BTC)<em>
													<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>
												</a>
											</td>
											<td class="rAlign">boughtamount</td>
											<td class="last"></td>
										</tr>
									<tr>
								</table>
							</div>
						</div>
						
					</div>
					<div class="BoS boxes">
						<article class="orderBox">
							<span class="orderBoxTab">
								<ul>
									<li class="tabBuy">
										<a href="#1" class="navItem2 nav2_1 active2Buy" title="매수">매수</a>
									</li>
									<li class="tabSell">
										<a href="#2" class="navItem2 nav2_2" title="매도">매도</a>
									</li>
									<li class="easyorder">
										<a href="#3" class="navItem2 nav2_3" title="간편주문">간편주문</a>
									</li>
									<li class="transactionHis">
										<a href="#4" class="navItem2 nav2_4" title="거래내역">거래내역</a>
									</li>
								</ul>
							</span>
							<div id="orderBoxBody1" class="orderBoxBody toggleMenueBody navItem2_1" style="display:block">									
									<%@ include file="/WEB-INF/views/exchange/bosBox/buy.jsp" %>
							</div>
							
							<div id="orderBoxBody2" class="orderBoxBody toggleMenueBody navItem2_2">
									<%@ include file="/WEB-INF/views/exchange/bosBox/sell.jsp" %>	
							</div>
							
							<div id="orderBoxBody3" class="orderBoxBody toggleMenueBody navItem2_3" >
									<%@ include file="/WEB-INF/views/exchange/bosBox/easyOrder.jsp" %>	
							</div>
							
							<div id="orderBoxBody4" class="orderBoxBody toggleMenueBody navItem2_4" >
									<%@ include file="/WEB-INF/views/exchange/bosBox/transactionHis.jsp" %>	
							</div>
							
						</article>
						<div class="miniChartDiv">
							<%@ include file="/WEB-INF/views/exchange/minichart/minichart.jsp" %>		
						</div>
											
					</div>
					
				</div>
			
				<div class="trasactedHistoryDiv">
					<%@ include file="/WEB-INF/views/exchange/transacationHistory/transacationHistory.jsp" %>
				</div>
			</section>
			<div class="cryptoListDiv">
				<%@ include file="/WEB-INF/views/exchange/cryptoList/cryptoList.jsp" %>
			</div>
			

		</div>
		
		
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>


</body>
</html>

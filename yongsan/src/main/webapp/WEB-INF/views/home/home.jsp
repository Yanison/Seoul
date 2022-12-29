<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>CoinHwang</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- CSS only -->
<link type="text/css" rel="stylesheet" href="/resources/css/home.css">
<%@ include file="../rscs/basicRscs.jsp"%>

</head>
<body style="min-width: 1600px;">

	<div class="wrapper">

		<%@ include file="../include/header.jsp"%>


		<section class="home-main">
			<div class="home-main-content-wrapper">
				<div class="home-main-content">
					<h1 style="margin: 0 0 5px 0;">가장 신뢰받는 글로벌 표준 디지털 자산 거래소</h1>
					<span style="margin: 0 0 30px 0;">안전하고 투명한 시스템으로 빠르고 편리한 거래
						환경을 제공합니다.</span>
					<!-- <div class=app-box>
						<div class="app-box-content">
							<div class="left-app-box-content">
								<span class="coin-num contents-num">
									<p>주간상승률</p> <span> <i class="fa-solid fa-coins"></i>
										Coins
								</span>
								</span> <a id="market" class="market linkbox" title="NFT Market 바로가기"
									style="background: #0062DF;" href="./exchange">거래소</a>
							</div>
							<div style="width: 1px; height: 42px; background: #7588AA;"></div>

							<div class="right-app-box-content">
								<span class="user-num contents-num">
									<p>주간수익률</p> <span> <i class="fa-solid fa-users"></i>
										User
								</span>
								</span> <a id="earning" class="user linkbox" title="Who's Earning?"
									style="background: #093687;" href="">Who's Earning?</a>
							</div>
						</div>
					</div> -->
				</div>
			</div>
		</section>

		<section class="home-banner">
			<img id="banner-link" class="banner-link"
				src="./resources/image/banner.png" href="https://udc.upbit.com/" />
		</section>

		<section class="home-price">
			<div class="price-view-wrapper">
				<div class="priceBox priceBTC">

					<div class="priceBoxTop">
						<div class="coinName">
							<span class="coinCode"> <span><img
									class="coinSymbol" src="https://static.upbit.com/logos/BTC.png"
									alt="https://static.upbit.com/logos/BTC.png"></span> <span>BTC</span>
							</span> <span style="font-size: 16px" id="BTC_sym">Bitcoin</span>
						</div>
						<div class="coinPrice">
							<div id="BTC_price" class="tradePrice money">123123213231231</div>
							<div class="priceDetail">
								<span id="BTC_gap">전일대비</span>
								<div id="updownbtc" class="updown"></div>
								<div id="priceGapbtc" class="priceGap">
									<div id="arrowbtc" class="arrow"></div>
									<div id="priceGapGapbtc" class="priceGapGap"></div>
								</div>
								<span id="btcPreprice" style="display: none;"></span>
							</div>
						</div>
					</div>
					<div id="4Chart" class="priceBoxBot">
						<div class="priceBoxBotChart"></div>
					</div>

				</div>
				<div class="priceBox priceETH">

					<div class="priceBoxTop">
						<div class="coinName">
							<span class="coinCode"> <span><img
									class="coinSymbol" src="https://static.upbit.com/logos/ETH.png"
									alt="https://static.upbit.com/logos/ETH.png"></span> <span>SYC</span>
							</span> <span style="font-size: 16px">SYCoin</span>
						</div>
						<div class="coinPrice">
							<div id="ethPrice" class="tradePrice money"></div>
							<div class="priceDetail">
								<span id="SYC_gap">전일대비</span>
								<div id="updowneth" class="updown"></div>
								<div id="priceGapeth" class="priceGap">
									<div id="arroweth" class="arrow"></div>
									<div id="priceGapGapeth" class="priceGapGap"></div>
								</div>
								<span id="ethPreprice" style="display: none;"></span>
							</div>
						</div>
					</div>
					<div id="3Chart" class="priceBoxBot">
						<div class="priceBoxBotChart"></div>
					</div>

				</div>
				<div class="priceBox priceXRP">

					<div class="priceBoxTop">
						<div class="coinName">
							<span class="coinCode"> <span><img
									class="coinSymbol" src="https://static.upbit.com/logos/XRP.png"
									alt="https://static.upbit.com/logos/XRP.png"></span> <span>EZC</span>
							</span> <span style="font-size: 16px">EZCoin</span>
						</div>
						<div class="coinPrice">
							<div id="xrpPrice" class="tradePrice"></div>
							<div class="priceDetail">
								<span>전일대비</span>
								<div id="updownxrp" class="updown"></div>
								<div id="priceGapxrp" class="priceGap">
									<div id="arrowxrp" class="arrow"></div>
									<div id="priceGapGapxrp" class="priceGapGap"></div>
								</div>
								<span id="xrpPreprice" style="display: none;"></span>
							</div>
						</div>
					</div>
					<div id="2Chart" class="priceBoxBot">
						<div class="priceBoxBotChart"></div>
					</div>

				</div>
			</div>
		</section>

		<section class="home-moblie-ads">
			<div class="mob-contents-wrapper">
				<div class="mob-contents-top">
					<h1 style="margin: 40px 0 20px;">빠르고 간편한 모바일 앱</h1>
					<span style="margin: 0 0 30px; display: block; font-size: 15px;">코인
						정보부터 주문까지 모바일에서도 간편하고 빠른 디지털 자산 거래소를 만나세요.</span>
					<div class="app-link-box">
						<a class="app-link" title="Google play"
							href="https://play.google.com/store/apps/details?id=com.dunamu.exchange">
							<i class="fa-brands fa-google-play"></i> &nbsp; 안드로이드
						</a> <a class="app-link" title="App Store"
							href="https://apps.apple.com/kr/app/%EC%97%85%EB%B9%84%ED%8A%B8-%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD-%EC%B5%9C%EB%8B%A4-%EA%B0%80%EC%83%81%ED%99%94%ED%8F%90-%EA%B1%B0%EB%9E%98%EC%86%8C/id1256371195">
							<i class="fa-brands fa-apple"></i> &nbsp; 아이폰앱
						</a>
					</div>
				</div>

				<div class="mob-contents-slider">
					<img class="iphone"
						src="https://cdn.upbit.com/upbit-web/images/mobileFrame.61a5450.png"
						alt="">
					<div id="carouselExampleDark" class="carousel carousel-dark slide"
						data-bs-ride="carousel" style="position: absolute; top: 20%;">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleDark"
								data-bs-slide-to="0" class="active" aria-current="true"
								aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleDark"
								data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleDark"
								data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner"
							style="width: 234px !important; height: 412px !important;">
							<div class="carousel-item active" data-bs-interval="10000">
								<img src="./resources/image/bit.png" class="d-block w-100"
									alt="..."
									style="object-fit: cover !important; width: 234px !important; height: 412px !important;">
								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item" data-bs-interval="2000"
								style="width: 234px !important; height: 412px !important;">
								<img src="./resources/image/coin_info.png" class="d-block w-100"
									alt="..."
									style="object-fit: cover !important; width: 234px !important; height: 412px !important;">
								<div class="carousel-caption d-none d-md-block"></div>
							</div>
							<div class="carousel-item"
								style="width: 234px !important; height: 412px !important;">
								<img src="./resources/image/coinmarket.png"
									class="d-block w-100" alt="..."
									style="object-fit: cover !important; width: 234px !important; height: 412px !important;">
								<div class="carousel-caption d-none d-md-block"></div>
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>






				</div>


				<div class="mob-contents-text -right text-1">
					<div class="text">
						<h3>실시간 시세 간편 확인</h3>
						<span>엡을 실행하지 않아도 락스크린에서</span> <span>실시간 시세를 간편하게 확인</span>
					</div>
					<div class="button">
						<img class="app__off--2q6Iq" id="onHover1"
							src="https://cdn.upbit.com/upbit-web/images/sliderbt.1135ba0.png"
							onmouseover="onHover1();" onmouseout="offHover11();">
					</div>
				</div>
				<div class="mob-contents-text -right text-2">
					<div class="text">
						<h3>증권사 MTS 수준의 안전한 거래</h3>
						<span>빠르고 간편한 모바일 주문 지원</span> <span>모바일에 최적화된 거래</span>
					</div>
					<div class="button">
						<img class="app__off--2q6Iq" id="onHover2"
							src="https://cdn.upbit.com/upbit-web/images/sliderbt.1135ba0.png"
							onmouseover="onHover2();" onmouseout="offHover22();">
					</div>
				</div>
				<div class="mob-contents-text -left text-3">
					<div class="text">
						<h3>지정가 도달 시 푸시 알림</h3>
						<span>차트를 보면서 지정가 알림을 설정</span> <span>빠른 알림 수신</span>
					</div>
					<div class="button">
						<img class="app__off--2q6Iq" id="onHover3"
							src="https://cdn.upbit.com/upbit-web/images/sliderbt.1135ba0.png"
							onmouseover="onHover3();" onmouseout="offHover33();">
					</div>
				</div>
				<div class="mob-contents-text -left text-4">
					<div class="text">
						<h3>실시간 코인 동향과 정보</h3>
						<span>다양한 알트코인 분석</span> <span>한눈에 파악할 수 있는 실시간 코인 동향</span>
					</div>
					<div class="button">
						<img class="app__off--2q6Iq" id="onHover4"
							src="https://cdn.upbit.com/upbit-web/images/sliderbt.1135ba0.png"
							onmouseover="onHover4();" onmouseout="offHover44();">
					</div>
				</div>



			</div>

		</section>

		<section class="home-secu-ads">
			<div class="secu-cotent-wrapper">
				<h1 style="margin: 0 0 10px;">강력한 멀티팩터 인증으로 24시간 안심거래</h1>
				<h6>글로벌 최고 수준의 파트너들과 함께 가장 안전한 보안체계를 구축합니다.</h6>
				<img class="secu-banner" src="./resources/image/secu_banner.png" />
				<div class="secu-cotent-textbox">
					<div class="secu-cotent-text">
						<h4>월렛보안</h4>
						<p>
							콜드월렛에 안전하게 분리 보관 <br> 최고 수준의 보안 시스템을 기반으로 <br> 다양한 디지털
							지갑을 안전하게 운영
						</p>
					</div>
					<div class="secu-cotent-text">
						<h4>계정보안</h4>
						<p>
							OTP보다 간편하고 안전한 카카오페이 <br> 간편인증으로 2채널 추가인증 <br> 체계적으로
							고안된 보안시스템으로 24시간 모니터링
						</p>
					</div>
				</div>

			</div>
		</section>

		<section class="home-license-ads">
			<div class="home-license-wrappper">
				<h1>국제 표준 및 국내 주요 보안 인증 획득</h1>
				<div class="license-box">
					<img alt="ISMS*P"
						src="https://cdn.upbit.com/upbit-web/images/IsmsIco22.5a16b7c.svg">
					<img alt="ACERTi"
						src="https://cdn.upbit.com/upbit-web/images/ACT_Ico.8be9249.svg">
					<img alt="KAB"
						src="https://cdn.upbit.com/upbit-web/images/KAB_Ico.78ff587.svg">
					<img alt="IAF"
						src="https://cdn.upbit.com/upbit-web/images/IAF_ico.0a0a39c.svg">
				</div>
				<strong>ISMS-P & ISO/IEC 27001, 27017, 27018</strong>
				<p>
					고객의 자산을 안전하게 보호하기 위해 국가에서 주관하는 정보보호 및 개인정보 관리체계 인증과<br>
					국제표준화기구 및 국제정기기술위원회가 제정한 국제 보안 표준 인증을 획득했습니다.
				</p>
			</div>
		</section>

		<section class="home-service">
			<div class="home-service-wrapper">
				<div class="home-service-box">
					<h3 class="home-service-box-contents">
						<i class="app__ico01--15xJ1 appico appico"></i>황비트 고객센터
					</h3>
					<p class="home-service-box-contents">
						<span style="font-size: 34px; margin: 0 10px 0 0;">1588-5682</span><span
							style="font-size: 18px;">(평일 09:00 ~ 18:00)</span>
					</p>
					<a class="kakao-link home-service-box-contents" title="카카오톡 상담"
						href="https://open.kakao.com/o/s33aKdqe" style="margin: 5px 0 0;">카카오톡
						상담하기(24시간)</a>
				</div>
				<div class="home-service-box">
					<h3 class="home-service-box-contents">
						<i class="app__ico02--1oplF appico"></i>황비트 라운지
					</h3>
					<p class="home-service-box-contents"
						style="color: #999999; font-size: 16px; margin: 0 0 10px 0;">
						방문 상담은 황비트 라운지를 이용해주십시오.<br> 라운지는 회원님을 위한 상담과 교류의 공간입니다.
					</p>
					<ul style="color: #999999; font-size: 16px;">
						<li>운영시간 평일 09:00 ~ 18:00</li>
						<li>서울특별시 강남구 테헤란로4길 14, 미림타워 2층</li>
					</ul>
				</div>
				<div class="home-service-box">
					<h3 class="home-service-box-contents">
						<i class="app__ico03--1DO14 appico"></i> 황비트 앱
					</h3>
					<div class="app-down-box">
						<div class="app-qr">
							<img class="qrcode" src="./resources/image/qrcode.png" />
						</div>
						<div class="app-down-link">
							<a class="app-down-link-link" title="Google play"
								href="https://play.google.com/store/apps/details?id=com.dunamu.exchange">
								<i class="fa-brands fa-google-play"></i> &nbsp; 안드로이드
							</a> <a class="app-down-link-link" title="App Store"
								href="https://apps.apple.com/kr/app/%EC%97%85%EB%B9%84%ED%8A%B8-%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD-%EC%B5%9C%EB%8B%A4-%EA%B0%80%EC%83%81%ED%99%94%ED%8F%90-%EA%B1%B0%EB%9E%98%EC%86%8C/id1256371195">
								<i class="fa-brands fa-apple"></i> &nbsp; 아이폰앱
							</a>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- footer -->
		<%@ include file="../include/footer.jsp"%>



	</div>


</body>
</html>


<script src="./resources/js/getPrice1.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/../resources/js/homeComponent/home.js"></script>

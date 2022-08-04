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


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/exchange.js"></script>


</head>
<body>
	<div class="exWrapper">
		<%@ include file="../include/header2.jsp" %>
		
		<div class="ex_main"> 
			<section class="trading">
				<div class="chartBox">
					<div class="chartBoxHeader"> 
						<div class="chartBoxHeaderLeft">
							<img class="btcLogo" src="https://static.upbit.com/logos/BTC.png" alt="https://static.upbit.com/logos/BTC.png" >
							<strong style="font-size:20px; color:#333333; padding:5 5 5 0px;">비트코인</strong>
							<span style="font-size:12px; margin:0 4px;">BTC/KRW</span>
							<a href="" class="Arrow">Arrow</a>
						</div>
						<div class="chartBoxHeaderRight">
							<a class="cryptoPrice">
								
							</a>
							<a class="cryptoInfo">
							</a>
						</div>
					</div>
					<div class="chartBoxIn" >
					</div>
				</div>
				
				
				
				<div class="tradingBox">
					<div class="dibPriceBox">
					</div>
					<div class="BoS">
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="../rscs/basicRscs.jsp" %>
	<link type="text/css" rel="stylesheet" href="/resources/css/investments.css">
</head>
<body>
	
	<div class="wrapper">
		<%@ include file="/WEB-INF/views/include/header2.jsp" %>
		<main>
			<article>
				<%@ include file="include_nav.jsp" %>
				
				<section class="myTrades">
					<div class="MyTrade__TradeState TradeState">
						<div class="MyTrade__TradeState TradeState_amount">
							<div class="TradeState_amount_totalAmount">
								<div class="left" style="margin-right:10px;">
									<span class="rowTitle">보유 KRW</span>
									<span><strong id="cashbalance">num</strong><i>KRW</i></span>
								</div>
								<div class="right">
									<span class="rowTitle">총 보유 자산</span>
									<span><strong id="totalAsset" >num</strong><i>KRW</i></span>
								</div>
							</div>
							<div class="TradeState_amount_totalRow">
								<div class="row1">
									<div>
										<span class="rowTitle">총매수금액</span>
										<span><strong id="ppAvg" >num</strong><i>KRW</i></span>
									</div>
									<div>
										<span class="rowTitle">총평가손익</span>
										<span><strong id="pnlAvg" >num</strong><i>KRW</i></span>
									</div>
								</div>
								<div class="row2">
									<div>
										<span class="rowTitle">총평가금액</span>
										<span><strong id="valuationAvg">num</strong><i>KRW</i></span>
									</div>
									<div>
										<span class="rowTitle">총평가수익률</span>
										<span><strong id="roiAvg" >num</strong><i>KRW</i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="MyTrade__TradeState TradeState_graph">
							<div class="chart_div">
								<div id="piechart" clss="chart_pieChart">
									
								</div>
							</div>
						</div>
					</div>
					<div class="AmontTable">
						<div class="AmontTable_header">
							<strong>보유자산 목록</strong>
						</div>
						
						<table id="AmontTable_Table" class="AmontTable_Table">
							<colgroup>
								<col width="*">
								<col width="80">
								<col width="140">
								<col width="140">
								<col width="170">
								<col width="160">
							</colgroup>
							<thead>
								<tr>
									<th>보유자산</th>
									<th>보유수량</th>
									<th>매수평균가</th>
									<th>매수금액</th>
									<th>평가금액</th>
									<th>평가손익</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div class="AmontTable">
				</section>
			</article>
		</main>
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	</div>
	
</body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/resources/js/investment/balance.js"></script>

</html>

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
				
				<section class="myHistory">
					<div class="myHistory__OrderHistory selectOption">
						<select>
							<option>전체 주문</option>
							<option value="0">매수</option>
							<option value="1">매도</option>
						</select>
					</div>
					
					<div class="hitoryTable">
						<table id="hitoryTable_Table" class="hitoryTable_Table hitoryTable_Table_Head">
							<colgroup>
								<col width="95">
								<col width="90">
								<col width="70">
								<col width="160">
								<col width="160">
								<col width="160">
								<col width="160">
								<col width="*">
							</colgroup>
							<thead>
								<tr>
									<th>시간</th>
									<th>마켓명</th>
									<th>거래종류</th>
									<th>감시가격</th>
									<th>주문가격</th>
									<th>주문수량</th>
									<th>미체결량</th>
									<th>주문취소</th>
								</tr>
							</thead>
						</table>
						<table id="hitoryTable_Table_Body" class="hitoryTable_Table hitoryTable_Table_Body">
							<colgroup>
								<col width="95">
								<col width="90">
								<col width="70">
								<col width="160">
								<col width="160">
								<col width="160">
								<col width="160">
								<col width="*">
							</colgroup>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(pendingHistory) eq 0}}">
										<tr>
											<td>no</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${pendingHistory}" var="p" varStatus="status">
											<tr id="tr${p.obSeq}">
													<td>
														<div>
															<span id="timestampYMD">${p.timestampMD}</span>
														</div>
														<div>
															<span id="timestampHM">${p.timestampR}</span>
														</div>
													</td>
													<td class="market">
														<strong class="table_column crptoSym">${p.cryptoSym}/KRW</strong>
													</td>
													<td class="bos">
														<span id="bos">${p.bos}</span>
													</td>
													<td>
														<span class="table_column price_KRW">-</span>
														<i class="table_column KRW">KRW</i>
													</td>
													<td>
														<span class="table_column price_KRW"><fmt:formatNumber value="${p.price}" pattern="#,###"/></span>
														<i class="table_column KRW">KRW</i>
													</td>
													<td>
														<span class="table_column amount"><fmt:formatNumber value="${p.orderAmount}" pattern="#,###"/></span>
														<i class="table_column crptoSym">${p.cryptoSym}</i>
													</td>
													<td>
														<span class="table_column amount"><fmt:formatNumber value="${p.obAmount}" pattern="#,###"/></span>
														<i class="table_column crptoSym">${p.cryptoSym}</i>
													</td>
													<td class="cancelOrderTd">
														<button value="${p.obSeq}" onclick="cancelOrder(this)">주문취소</button>
														<input id="order_cryptoSeq" type="hidden" value="${p.cryptoSeq}">
													</td>
												</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								
							</tbody>
						</table>
						<%@ include file="../include/pagination.jsp" %>
					</div>
				</section>
			</article>
		</main>
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	</div>
</body>

<script src="/resources/js/investment/wait_orders.js"></script>
</html>
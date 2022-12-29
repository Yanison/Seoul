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
					 <input type="hidden" name="thisPage" value="<c:out value="${vo.thisPage}" default="1"/>">
				 	<input type="hidden" name="rowNumToShow" value="<c:out value="${vo.rowNumToShow}"/>">
				 	<input type="hidden" name="startRnumForMysql" value="<c:out value="${vo.startRnumForMysql}"/>">
					 <form id="paging" method="get">
						<div class="myHistory__TradeHistory selectOption">
								
								<div class="selectOptionsDiv selectByPeriod">
									<div>
										<span class="rowTitle">기간</span>
									</div>
									<ul class="btnDiv">
										<li class="optionBtn">
											<label>
											<input type="radio" class="shDuration" name="shDuration" value="1 week" onclick="sh(this)">
											1주일
											</label>
										</li>
										<li class="optionBtn">
											<label><input type="radio" class="shDuration" name="shDuration" value="1 month" onclick="sh(this)">
											1개월
											</label>
										</li>
										<li class="optionBtn">
											<label>
											<input type="radio" class="shDuration" name="shDuration" value="3 month" onclick="sh(this)">3개월</label>
										</li>
										<li class="optionBtn">
											<label>
											<input type="radio" class="shDuration" name="shDuration"  value="6 month" onclick="sh(this)">6개월</label>
										</li>
										<li class="optionBtn">
											<label>
											<input type="radio" class="shDuration" name="shDuration"  value="" onclick="sh(this)">전체</label>
										</li>
									</ul>
									
								</div>
								<div class="selectOptionsDiv selectByTransactedSort">
									<div>
										<span class="rowTitle">종류</span>
									</div>
									<ul class="btnDiv">
										<li class="optionBtn">
											<label>
											<input type="radio" class="shOption" name="shOption" value=null onclick="sh(this)">전체</label>
										</li>
										<li class="optionBtn">
											<label>
											<input type="radio" class="shOption"  name="shOption" value="0" onclick="sh(this)">매수</label>
										</li>
										<li class="optionBtn">
											<label>
											<input type="radio" class="shOption"  name="shOption" value="1" onclick="sh(this)">매도</label>
										</li>
									</ul>
								</div>
								<div class="selectOptionsDiv selectByCoin">
									<div>
										<span class="rowTitle">코인선택</span>
									</div>
									<div class="shCryptoDiv">
										<input id="shCoinInp" class="shValue" type="text" placeholder="전체" name="shValue" onkeyup="shCryptoName(this)" autocomplete='off'/>
										<span><i class="fa-solid fa-magnifying-glass-dollar"></i></span>
										<button id="shResult" class="shResult" onclick="shResult()">찾기</button>
										<div id="shCryptoResult" class="shCryptoResult" style="display:none;">
										</div>
									</div>
								</div>
								
						</div>
						<input type="submit" id="submit" style="display:none;">
						</form>
					<div class="hitoryTable">
						<table class="hitoryTable_Table_Head">
							<colgroup>
								<col width="94">
								<col width="64">
								<col width="48">
								<col width="40">
								<col width="144">
								<col width="144">
								<col width="144">
								<col width="94">
							</colgroup>
							<thead>
								<tr>
									<th>주문시간</th>
									<th>코인</th>
									<th>유저 / 마켓</th>
									<th>종류</th>
									<th>거래수량</th>
									<th>거래단가</th>
									<th>거래금액</th>
									<th>체결시간</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
						<table id="hitoryTable_Table_Body" class="hitoryTable_Table_Body" style="height:610px !important">
							<colgroup>
								<col width="94">
								<col width="64">
								<col width="48">
								<col width="40">
								<col width="144">
								<col width="144">
								<col width="144">
								<col width="94">
							</colgroup>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(ordersHistory) eq 0}}">
										<tr>
											<td>no</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${ordersHistory}" var="o" varStatus="status">
											<tr>
											
												<td>
													<div>
														<span id="timestampYMD">${o.orderTimestampMD}</span>
													</div>
													<div>
														<span id="timestampHM">${o.orderTimestampR}</span>
													</div>
												</td>
												
												<td class="cryptoSym">
													<strong class="table_column crptoSym">${o.cryptoSym}</strong>
												</td>
												
												<td class="market">
													<span class="table_column price_KRW" style="font-size:11">${o.memberNickname}</span><br>
													<i class="table_column KRW">-</i>
												</td>
												
												<td class="bos">
													<span id="bos">${o.bos}</span>
												</td>
												
												<td>
													<span class="table_column amount"><fmt:formatNumber value="${o.obAmount}" pattern="#,###"/></span>
													<i class="table_column crptoSym">${o.cryptoSym}</i>
												</td>
												
												<td>
													<span class="table_column price_KRW"><fmt:formatNumber value="${o.price}" pattern="#,###"/></span>
													<i class="table_column KRW">KRW</i>
												</td>
												
												<td>
													<span class="table_column price_KRW"><fmt:formatNumber value="${o.pp}" pattern="#,###"/></span>
													<i class="table_column KRW">KRW</i>
												</td>
												
												<td>
													<div>
														<span id="timestampYMD">${o.timestampMD}</span>
													</div>
													<div>
														<span id="timestampHM">${o.timestampR}</span>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<%@ include file="paginationHis.jsp" %>
					</div class="AmontTable">
				</section>
			</article>
		</main>
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	</div>
</body>
<script src="/resources/js/investment/history.js"></script>
</html>
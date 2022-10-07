<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<div class="myCryptoHeader">
	<div>총 보유자산</div>
	<div>
		<p style="color:var(--up-color);">num <i style="color:#666; font-size:11px;">KRW</i></p>
	</div>
</div>

<div class="searchCoin">
	<div class="searchInput">
		<input type="text" class="searchMyCoin" name="searchMyCoin" placeholder="코인명/심볼검색">
		<div><i class="fa-solid fa-magnifying-glass"></i></div>
	</div>
	<div class="onlyMyCoins">
		<label class="checkMyCoin">
			<input type="checkbox" name="mycoins" style="margin-right:5px;">
			<span>보유자산만<span>
		</label>
	</div>
</div>
<div class="tableWarpper">
	<table class="myCryptoList">
		<colgroup>
			<col width="170">
			<col width="130">
			<col width="190">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th>코인명</th>
				<th>보유비중</th>
				<th>보유수량(평가금액)</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(selectBal) eq 0}">
					<tr>
						<td colspan="4"> no data!!</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${selectBal}" var="selectBal" varStatus="status">
						<tr>
							<td class="coinInfo">
								<div>
									<em class="coinLogo">
										<i class="fa-brands fa-btc"></i>
									</em>
									<div>
										<strong><c:out value="${selectBal.cryptoSym}"/></strong><br>
										<i><c:out value="${selectBal.cryptoSym}"/></i>
									</div>
								</div>								
							</td>
							<td class="holding">0.00%</td>
							<td class="balance">
								<strong><c:out value="${selectBal.amount}"/></strong>
								<i><c:out value="${selectBal.cryptoSym}"/></i>
							</td>
							<td class="wobActve">
								<i>${selectBal.wodAvailable}</i>
							</td>
						</tr>	
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
				
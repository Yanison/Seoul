<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<script src="../resources/js/root/cryptoGroup/cryptoGroupList.js"></script>

</script>


<article class="cryptoListBox">
	<section class="cryptoListHeader">
		<div class="searchCrypto">
		
			<div class="cryptoSearchBar">
				<div class="cryptoSearchBarI">
					<input type="search" id="CryptoVo" name="CryptoVo" class="CryptoVo" placeholder="코인명/심볼검색"/>
					<div style=" width:39; heigth:42pxl; position:relative;">
						<i style="color:#0062df;" class="fa-solid fa-magnifying-glass"></i>
					</div>
				</div>
				<div class="addAndRemove">
					<button class="addCoinBtn addAndRemoveBtn">신규코인 등록</button>
					<button class="addCoinBtn addAndRemoveBtn">수정</button>
					<button class="removeCoinBtn addAndRemoveBtn">코인삭제</button>
				</div>
			</div>
			<div class="showCryptoOption" style="border-left: 1px solid #d5d6dc; padding-top:3px;">
				<i style="color:#999;" class="fa-solid fa-gear"></i>
			</div>
			
		</div>
		<div class="cryptoNavi">
			<ul class="cryptoNaviTab">
				<li class="TabActive">원화</li>
				<li>BTC</li>
				<li>USDT</li>
				<li>보유</li>
				<li>관심</li>
			</ul>
		</div>
	</section>
	<section class="cryptoListR">
		<table>
			<colgroup>
				<col width="26"/>
				<col width="26"/>
				<col width="94"/>
				<col width="98"/>
				<col width="58"/>
				<col width="*"/>			
			</colgroup>
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th>cryptoSeq</th>
					<th class="CryptoName" style="text-align:left;">한글명</th>
					<th>현재가</th>
					<th>전일대비</th>
					<th>거래대금</th>
					<th>regDate</th>
					<th>regBy</th>
					<th>modDate</th>
					<th>modBy</th>
					<th>WoDavailable</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(getCryptoList) eq 0}">
						<tr><td colspan="6"> no crypto data</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${getCryptoList}" var="getCryptoList" varStatus="status">
							<tr id="${getCryptoList.cryptoName}" class="cryptoRow thisCoin" value="${getCryptoList.cryptoName}">
								<td class="likeThis"><i class="fa-regular fa-star"></i></i></td>
								<td class="smallCandle">-</td>
								<td>${getCryptoList.cryptoSeq}</td>
								<td class="CryptoName" style="text-align:left;">
									<div class="getCryptoNm">${getCryptoList.cryptoName}</div>
									<div class="getCryptoSym">${getCryptoList.cryptoSym}</div>
								</td>
								<td class="CryptoPricePresent">num</td>
								<td class="24Hvari">
									<div>%</div>
									<div>₩</div>
								</td>
								<td class="CryptoCap">
									<div><span>num</span><i>백만</i></div>
								</td>
								<td><c:out value="${getCryptoList.regDateAt}"/></td>
								<td><c:out value="${getCryptoList.regDateBy}"/></td>
								<td><c:out value="${getCryptoList.modDateAt}"/></td>
								<td><c:out value="${getCryptoList.modDateBy}"/></td>
								<td><c:out value="${getCryptoList.wodAvailable}"/></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</section>
</article>
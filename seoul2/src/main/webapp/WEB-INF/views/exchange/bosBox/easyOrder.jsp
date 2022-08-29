<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<!-- <link type="text/css" rel="stylesheet" href="./resources/css/입력.css">
#c84a31; red
#0062df; blue
<script src="./resources/js/입력.js"></script> -->



<div class="easyOrderSetting">
	<div>
		<span >간편주문</span>
		<span><i class="fa-regular fa-circle-question"></i></span>
	</div>
	<div>
		<span>편집</span>
		<span style="color:#dedede;">|</span>
		<span>등록</span>
	</div>
</div>

<ul class="availableOrder">
	<li class="availableOrder_1 availableBuy">
		<div style="color:#c84a31">매수 가능</div>
		<div>0 <strong>KRW</strong>
		</div>
	</li>
	<li class="availableOrder_1 availableSell">
		<div style="color:#0062df">매도 가능</div>
		<div>
			<div>0 <strong>KRW</strong></div>
			<div style="color:#999; font-size:10px;">≈ 0 <i>KRW</i></div>
		</div>
	</li>
</ul>
<div class="easyOrderOptionsWrapper">
	<ul class="easyOrderOptions">
		<li class="orderOptions _a">
			<div>
				<div> 시장가 전액 매수</div>
				<div>가격:시장가, 총액: 가능 100%</div>
			</div>
			<div>
				<div>Option(⌥)+1</div>
				<span class="orderBtn_ orderBtn_a">매수</span>
			</div>
		</li>
		<li class="orderOptions _b">
			<div>
				<div> 시장가 전액 매도</div>
				<div>가격:시장가, 수량: 가능 100%</div>
			</div>
			<div>
				<div>Option(⌥)+2</div>
				<span class="orderBtn_ orderBtn_b">매도</span>
			</div>		
		</li>
		<li class="orderOptions _a">
			<div>
				<div> 지정가 전액 매수</div>
				<div>가격: <span name="orderOptionsPrice">num</span> KRW, 수량: 가능 100%</div>
			</div>
			<div>
				<div>Option(⌥)+3</div>
				<span class="orderBtn_ orderBtn_a">매수</span>
			</div>
		</li>
		<li class="orderOptions _b">
			<div>
				<div> 지정가 전액 매도</div>
				<div>가격: <span name="orderOptionsPrice">num</span> KRW, 수량: 가능 100%</div>
			</div>
			<div>
				<div>Option(⌥)+4</div>
				<span class="orderBtn_ orderBtn_b">매도</span>
			</div>
		</li>
		<li class="orderOptions _a">
			<div>
				<div>일반 주문 취소</div>
				<div>대상:일반 미체결 주문 최근 1건</div>
			</div>
			<div>
				<div>Option(⌥)+5</div>
				<span class="orderBtn_ orderBtn_c">취소</span>
			</div>
		</li>
		<li class="orderOptions _b">
			<div>
				<div>평단가 도달시 전액 매도</div>
				<div>감시가: 평단가, 가격: 평단가, 수량: 가능 100%</div>
			</div>
			<div>
				<div>Option(⌥)+6</div>
				<span class="orderBtn_ orderBtn_b">예약매도</span>
			</div>
		</li>
	</ul>
</div>

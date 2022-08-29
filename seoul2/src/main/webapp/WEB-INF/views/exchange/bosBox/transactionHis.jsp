<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<link type="text/css" rel="stylesheet" href="./resources/css/입력.css">
<script src="./resources/js/exchange/BoS/exHis.js"></script>

<div class="THWrapper">
	<div class="viewOption">
		<div class="radioOption">
			<div class="radioCheck">
				<label>
					<input type="radio" name="settleOrder" class="settleOrder" value="1" checked></input><span>미체결</span>
				</label>			 
			</div>
			<div class="radioCheck">	
				<div class="form-check">
				<label>
					<input type="radio" name="settleOrder" class="settleOrder"  value="2"><span>체결</span>
				</label>			 
			</div>		
			</div>
		</div>
		<div class="dropOption">
			<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" >
			    현재 마켓
			  </button>
			  <ul class="dropdown-menu" style="width:75px !important;">
			    <li><button class="dropdown-item" type="button">전체 마켓</button></li>
			    <li><button class="dropdown-item" type="button">현재 마켓</button></li>
			    <li><button class="dropdown-item" type="button">BTC 마켓</button></li>
			  </ul>
			</div>
		</div>
	</div>
	<div class="settleOrderWrapper">
		<div class="settleOrderNY _N">
			<table class="settleOrderTable">
				<colgroup>
					<col width="82px">
					<col width="90px">
					<col width="104px">
					<col >
					<col width="64px">
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">주문시간</th>
						<th>마켓명</th>
						<th>감시가격</th>
						<th>주문가격</th>
						<th rowspan="2">취소</th>
					</tr>
					<tr>
						<th>구분</th>
						<th>주문가격</th>
						<th>미체결량</th>											
					</tr>
				</thead>
				<tbody>
					<tr class="exHis">
						<td>
							<div class="exHis_Day">2022.08.29</div>
							<div class="exHis_time">10:49:33</div>
						</td>
						<td>
							<div class="exHis_Pairs">BTC/KRW</div>
							<div class="exHis_TradeType">매수</div>
						</td>
						<td>
							<div class="exHis_Price">num</div>
							<div class="exHis_MyPrice">num</div>
						</td>
						<td>
							<div class="exHis_Amount">num</div>							
						</td>
						<td>
							<button>취소</button>						
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="settleOrderNY _Y" style="display:none">
			<table class="settleOrderTable">
				<colgroup>
					<col width="94px">
					<col width="100px">
					<col width="120px">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">체결시간</th>
						<th>마켓명</th>
						<th>체결가격</th>
						<th rowspan="2">체결수량</th>
					</tr>
					<tr>
						<th>구분</th>
						<th>체결금액</th>											
					</tr>
				</thead>
				<tbody>
					<tr class="exHis">
						<td>
							<div class="exHis_Day">2022.08.29</div>
							<div class="exHis_time">10:49:33</div>
						</td>
						<td>
							<div class="exHis_Pairs">BTC/KRW</div>
							<div class="exHis_TradeType">매수</div>
						</td>
						<td>
							<div class="exHis_Price">num</div>
							<div class="exHis_MyPrice">num</div>
						</td>
						<td>
							<div class="exHis_Amount">num</div>							
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
	
</div>

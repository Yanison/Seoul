<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<!-- css,js _ (입력)에서 상속받음. --> 
<script src="../resources/js/exchange/orderContainer/orderContainerPrice.js"></script>
<script src="../resources/js/exchange/orderContainer/sockjs.js"></script>
<script src="../resources/js/exchange/orderContainer/callOBAjax.js"></script>


<div id="orderContainerWrap" class="orderContainerWrap">
	<div class="orderContainerPrice">
		<div class="bidsWarpper bidsAsksWarpper">
			<div class="tableWrap">
				<table class="Asks">
					<colgroup>
						<col width="35">
						<col width="120">
						<col width="160">
					</colgroup>
					<tbody id="asksTbody">
					</tbody>
				</table>
			</div>
			<div class="baDiv">
				<table>
					<tr>
						<th>거래량</th>
						<td>num <i>btc</i></td>
					</tr>
					<tr>
						<th>거래대금</th>
						<td>
							num <i>백만원</i> 
							<em>(최근24시간)</em>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th>52주 최고</th>
						<td class="up">
							num 
							<em>
							0000.00.00
							</em>
						</td>
					</tr>
					<tr>
						<th>52주 최저</th>
						<td class="down">
							num 
							<em>
							0000.00.00
							</em>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th>전일 종가</th>
						<td>
							num
						</td>
					</tr>
					<tr>
						<th>당일 고가</th>
						<td class="up">
						num
						</td>
					</tr>
					<tr>
						<th>당일 저가</th>
						<td class="down">
						num
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="asksWarpper bidsAsksWarpper">
		<div class="baDiv trscIntense">
				<table>
					<thead>
						<tr>
							<th>체결강도</th>
							<td></td>
						</tr>
						<tr>
							<th>채결가</th>
							<th>체결량</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>price</td>
							<td>amount</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<div class="tableWrap">
				<table class="Bids">
					<colgroup>
						<col width="160">
						<col width="120">
						<col width="35">
					</colgroup>
					<tbody id="bidsTbody">
					</tbody>
				</table>
			</div>
		</div>
	</div>							
</div>
								
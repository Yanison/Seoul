<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<div id="orderContainerWrap" class="orderContainerWrap">
<div class="orderContainerOrderNavigation">
	<table>
		<colgroup>
			<col width="42">
			<col width="120">
			<col width="*">
			<col width="120">
			<col width="42">
		</colgroup>
		<tbody>
			<tr class="down">
				<td>매도</td>
				<td class="bar left">
					<a href="#">
						<div style="width:20%"><!-- 체결수량에 비례해서 div가 늘어남 --></div>
						<p>amout</p>
					</a>
				</td>
				<td class="downB">
					<a href="">
						<div class="ty03">
							<strong>price</strong>
						</div>
						<div class="ty02">ratio%</div>
					</a>
				</td>
				<td class="inner01">
					<dl>
						<dt>거래대금</dt>
						<dd>num
						<i>백만원</i>
						<em>(최근24시간)</em>
						</dd>
					</dl>
					<dl>
						<dt>52주 최고</dt>
						<dd id="updown">num
						<em>(date)</em>
						</dd>
						<dt>52주 최저</dt>
						<dd id="updown">num
						<em>(date)</em>
						</dd>
					</dl>
					<dl>
						<dt>전일 종가</dt>
						<dd id="updown">num
						</dd>
						<dt>당일 고가</dt>
						<dd id="updown">num
						</dd>
						<dt>당일 고가</dt>
						<dd id="updown">num
						</dd>
					</dl>
				</td>
				<td class="last">매수</td>
			</tr>
			<tr class="up">
				<td>매도</td>
				<td class="inner02">
					<dl>
					<dt>체결강도</dt>
					<dd>ratio%</dd>
				</dl>
				<div class="overflow">
					<table>
						<colgroup>
							<col width="50%">
							<col width="*">
						</colgroup>
						<thead>
							<tr>
								<th>체결가</th>													
								<th>체결물량</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>price</td>
								<td class="up">amount</td>
							<tr>
							
							<tr>
								<td>price</td>
								<td class="up">amount</td>
							<tr>
							
							<tr>
								<td>price</td>
								<td class="down">amount</td>
							<tr>
							
							<tr>
								<td>price</td>
								<td class="up">amount</td>
							<tr>
							
							<tr>
								<td>price</td>
								<td class="down">amount</td>
							<tr>
							</tbody>
						</table>
					</div>	
				</td>
				<td class="upB">
					<a href="#">
						<div class="ty03">
							<strong>price</strong>
						</div>
						<div class="ty02">ratio%</div>
					</a>				
				</td>
				<td class="bar right">
					<a href="#">
						<div style="width:14%"><!-- 체결수량에 비례해서 div가 늘어남 --></div>
						<p>amout</p>
					</a>
				</td>
				<td class="last">매수</td>
			</tr>
		</tbody>
	</table>

</div>
</div>
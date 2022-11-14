<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<script src="../resources/js/exchange/transacationHistory/transacationHistory.js"></script>


<article class="transactedHistory">
	<section class="transactedHistoryMenuTab">
		<div class="TsTabMenu TsTabMenu_1">체결</div>
		<div class="TsTabMenu TsTabMenu_2">일별</div>
	</section>
	
	<section class="transactedHistoryBody" style="display:block">
		<table class="transactedHistoryTable">
			<colgroup>
				<col width="96">
				<col width="300">
				<col width=280"">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<td class="frist" style="text-align:center;">체결시간</td>
					<td>체결가격(KRW)</td>
					<td>체결량(BTC)</td>
					<td class="last">체결금액(KRW)</td>
				</tr>
			</thead>
			<tbody>
				<!-- <tr>
					<td class="first">
						<p>
							<i>00.00</i>
							<i>00.00</i>
						</p>
					</td>
					<td>27,437,000</td>
					<td>0.4560020</td>
					<td class="last">378,032</td>
				</tr> -->
			</tbody>
		</table>
	</section>
	
	<section class="transactionHistoryByDayBody" style="display:none">
		<table class="transactionHistoryByDayTable">
			<colgroup>
				<col width="100">
				<col width="334">
				<col width="190">
				<col width="80">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<td class="frist" style="text-align:center;">일자</td>
					<td>종가(KRW)</td>
					<td></td>
					<td>전일대비</td>
					<td class="last">거래량(BTC)</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="first">
						<p>
							<i>09.05</i>
						</p>
					</td>
					<td>27,431,000</td>
					<td>97000</td>
					<td>-0.43%</td>
					<td class="last">161</td>
				</tr>
			</tbody>
		</table>
	</section>
</article>
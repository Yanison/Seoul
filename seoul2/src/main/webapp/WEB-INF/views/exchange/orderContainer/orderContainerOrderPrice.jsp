<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<script src="./resources/js/exchange/exchange.js"></script>





<div id="orderContainerWrap" class="orderContainerWrap">
<div class="orderContainerOrderPrice">
	<table class="">
		<colgroup>
			<col width="*">
			<col width="95">
			<col width="92">
			<col width="95">
			<col width="43">
		</colgroup>
		<tbody>
			<tr class="down">
				<td class="updown">
					<a href="#">
						<div class="ty03">
							<strong>price</strong>
						</div>
						<div class="ty02">ratio%</div>
					</a>
				</td>
				<td class="bar left">													
						<!-- 체결수량 -->
						<a href="#">
							<div style="width:20%"><!-- 체결수량에 비례해서 div가 늘어남 --></div>
							<p>amout</p>
						</a>
				</td>
				<td class="DefualutHover">
					<p>num</p>
				</td>
				<td class="bar right">
					<a>
						<div style="width: 12.7897%;"></div>
						<p>111,249,156</p>
					</a>	
				</td>
				<td class="last"></td>
			</tr>
			<tr class="up">
				<td class="updown">
					<a href="#">
						<div class="ty03">
							<strong>price</strong>
						</div>
						<div class="ty02">ratio%</div>
					</a>
				</td>
				<td class="bar left">													
						<!-- 체결수량 -->
						<a href="#">
							<div style="width: 35.7897%;"><!-- 체결수량에 비례해서 div가 늘어남 --></div>
							<p>amout</p>
						</a>
				</td>
				<td class="DefualutHover">
					<p>num</p>
				</td>
				<td class="bar right">
					<div style="width: 55.7897%;"></div>
					<p>111,249,156</p>
				</td>
				<td class="last"></td>
			
			</tr>
		</tbody>
	</table>
</div>
</div>


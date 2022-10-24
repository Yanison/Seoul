<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<!-- <script src="./resources/js/.js"></script>
#c84a31; red
#0062df; blue
 -->


<dl>
	<dt>
		<strong>주문구분 <i class="fa-regular fa-circle-question"></i></strong>
	</dt>
	<dd style="width:340px;">
		<div class="orderTpye">
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="1" checked="checked">
			  <label class="form-check-label" for="flexRadioDefault1">
			    지정가
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="2" >
			  <label class="form-check-label" for="flexRadioDefault2">
			    시장가
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="3" >
			  <label class="form-check-label" for="flexRadioDefault2">
			    예약-지정가
			  </label>
			</div>
		</div>
	</dd>
	
	<dt>
	<strong>주문가능</strong>
	</dt>
	<dd>
		<strong class="userCashBal">num</strong><em>KRW</em>
	</dd>
	
	<dt>
	<strong>매도가격</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input id="asksPrice" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		  <span class="input-group-text" id="inputGroup-sizing-default"><i class="fa-solid fa-minus"></i></span>
		  <span class="input-group-text" id="inputGroup-sizing-default"><i class="fa-solid fa-plus"></i></span>
		</div>
	</dd>
	
	<dt>
		<strong>주문수량</strong><em>(BTC)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input type="text" id="asksAmount" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">										 
		</div>
		
	</dd>
	<dt></dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">10%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default">25%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default">50%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default">100%</span>									  										 
		</div>
	</dd>
	
	<dt>
		<strong>주문총액</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <input type="text" class="form-control" value="0" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">										 
		</div>
	</dd>
	<dd class="orderNotice">
	<span><i class="fa-solid fa-circle-dot"></i>최소주문금액 1,000 KRW</span><span><i class="fa-solid fa-circle-dot"></i>수수료(부가세 포함):0.05%</span>									
	</dd>
	
	<dd class="last">
		<span class="orderBtn1"> 초기화 </span>
		<button id="submitAsks" class="orderBtn2" style="background:#1261c4;" onclick="submitAsks()"> 매도 </button>
	</dd>

</dl>
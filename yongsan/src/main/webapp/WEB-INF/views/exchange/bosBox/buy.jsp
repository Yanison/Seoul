


<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<script src="../resources/js/exchange/BoS/tabBuy.js"></script>


<dl>
	<dt>
		<strong>주문구분 <i class="fa-regular fa-circle-question"></i></strong>
	</dt>
	<dd style="width:340px;">
		<div class="orderTpye">
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1" value="1" checked="checked">
			  <label class="form-check-label" for="flexRadioDefault1">
			    지정가
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault2" value="2" >
			  <label class="form-check-label" for="flexRadioDefault2">
			    시장가
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault2" value="3">
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
		
		<strong id="userCashBal" class="moneyfmt userCashBal"></strong><em>KRW</em>
	</dd>
	
	<dt>
	<strong>매수가격</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input id="bidsPrice" name="price" type="text" class="form-control bidsPrice moneyFmt" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
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
		  <input type="text" id="bidsAmount" name="obAmount" class="form-control bidsAmount moneyFmt" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">										 
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
		  <input id="bidSum" type="text" class="form-control bidSum moneyFmt" value="0" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">										 
		</div>
	</dd>
	<dd class="orderNotice">
	<span><i class="fa-solid fa-circle-dot"></i>최소주문금액 1,000 KRW</span><span><i class="fa-solid fa-circle-dot"></i>수수료(부가세 포함):0.05%</span>									
	</dd>
	
	<dd class="last">
		<span class="orderBtn1"> 초기화 </span>
		<span id="submitBids" class="orderBtn2 orderType" value="0" style="background:#c84a31"> 매수 </span>
	</dd>

</dl>
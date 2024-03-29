<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<dl>
	<dt>
		<strong>주문구분 <i class="fa-regular fa-circle-question"></i></strong>
	</dt>
	<dd style="width:340px;">
		<div class="orderTpye">
			<div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="limit" onClick="otVal()">
			    <input class="orderTypeLabel" type="radio" name="orderTypeBuy" id="orderType1" value="0" checked="checked">
			    지정가
			  </label>
			</div>
			<div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="market" onClick="otVal()">
			  <input class="orderTypeLabel" type="radio" name="orderTypeBuy" id="orderType2" value="1" >
			    시장가
			  </label>
			</div>
			<!-- <div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="reserve" onClick="otVal()">
			  <input class="orderTypeLabel" type="radio" name="orderTypeBuy" id="orderType3" value="10">
			    예약-지정가
			  </label>
			</div> -->
			<input type="hidden" id="orderType"/>
		</div>
	</dd>
	<dt>
	<strong>주문가능</strong>
	</dt>
	<dd>
		
		<strong id="KRWBal" class="moneyfmt userCashBal"><input id="inputKRWBal" class="inputNumber inputNumberLeft" type="number" readOnly></strong><em>KRW</em>
		<!-- <input id="inputKRWBal" type="hidden"> -->
	</dd>
	
	<dt>
	<strong>매수가격</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input id="bidsPrice" name="price" type="number" class="form-control bidsPrice moneyFmt recentPrice" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		  <span class="input-group-text" id="inputGroup-sizing-default"><i class="fa-solid fa-minus"></i></span>
		  <span class="input-group-text" id="inputGroup-sizing-default"><i class="fa-solid fa-plus"></i></span>
		</div>
	</dd>
	
	<dt>
		<strong>주문수량</strong><em>(<c:out value="${selectCrpytoOne.cryptoSym}"/>)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input type="number" id="bidsAmount" name="obAmount" class="form-control bidsAmount moneyFmt" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">										 
		</div>
		
	</dd>
	<dt></dt>
	<dd>
		<div id="QuantityPer" class="input-group mb-3">
		  <span class="input-group-text qp" id="inputGroup-sizing-default" value="0" onclick="quantityPer(this,0)" >10%</span>
		  <span class="input-group-text qp" id="inputGroup-sizing-default" value="0" onclick="quantityPer(this,0)" >25%</span>
		  <span class="input-group-text qp" id="inputGroup-sizing-default" value="0" onclick="quantityPer(this,0)" >50%</span>
		  <span class="input-group-text qp" id="inputGroup-sizing-default" value="0" onclick="quantityPer(this,0)" >100%</span>									  										 
		</div>
	</dd>
	
	<dt>
		<strong>주문총액</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <input id="bidsSum" type="number" class="form-control bidSum moneyFmt" value="0" name="calcOrder" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" onchange="calcOrder('#bidsPrice','#bidsAmount')" readOnly>										 
		</div>
	</dd>
	<dd class="orderNotice">
	<span><i class="fa-solid fa-circle-dot"></i>최소주문금액 1,000 KRW</span><span><i class="fa-solid fa-circle-dot"></i>수수료(부가세 포함):0.05%</span>									
	</dd>
	
	<dd class="last">
		<button id="resetB"class="orderBtn1"> 초기화 </button>
		<button id="submitBids" class="orderBtn2 orderType" onclick='submitBids()' value="0" style="background:#c84a31"> 매수 </button>
	</dd>

</dl>
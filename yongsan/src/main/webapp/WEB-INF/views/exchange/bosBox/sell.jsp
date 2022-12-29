<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


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
			<div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="limit" onClick="otVal2()">
			    <input class="orderTypeLabel" type="radio" name="orderTypeSell" id="orderType1" value="0" checked="checked">
			    지정가
			  </label>
			</div>
			<div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="market" onClick="otVal2()">
			  <input class="orderTypeLabel" type="radio" name="orderTypeSell" id="orderType2" value="1" >
			    시장가
			  </label>
			</div>
			<!-- <div class="orderTpyeRadioDiv">
			  <label class="otLabel" id="reserve" onClick="otVal2()">
			  <input class="orderTypeLabel" type="radio" name="orderTypeSell" id="orderType3" value="20">
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
		<strong id="<c:out value="${selectCrpytoOne.cryptoSym}"/>Bal" class="userCoinBal moneyFmt"><input id="input<c:out value="${selectCrpytoOne.cryptoSym}"/>Bal" class="inputNumber inputNumberLeft" type="number" readOnly></strong><em><c:out value="${selectCrpytoOne.cryptoSym}"/></em>
		
	</dd>
	<dt>
	<strong>매도가격</strong><em>(KRW)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">num</span>
		  <input id="asksPrice" type="number" class="form-control recentPrice" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
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
		  <input type="number" id="asksAmount" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" >										 
		</div>
		
	</dd>
	<dt></dt>
	<dd>
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default" value="1" onclick="quantityPer(this,1)" >10%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default" value="1" onclick="quantityPer(this,1)">25%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default" value="1" onclick="quantityPer(this,1)">50%</span>
		  <span class="input-group-text" id="inputGroup-sizing-default" value="1" onclick="quantityPer(this,1)">100%</span>									  										 
		</div>
	</dd>
	
	<dt>
		<strong id="totalOrderS">주문총액</strong><em>(<c:out value="${selectCrpytoOne.cryptoSym}"/>)</em>
	</dt>
	<dd>
		<div class="input-group mb-3">
		  <input id="asksSum" type="number" class="form-control" value="0" name="calcOrder" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readOnly>										 
		</div>
	</dd>
	<dd class="orderNotice">
	<span><i class="fa-solid fa-circle-dot"></i>최소주문금액 1,000 KRW</span><span><i class="fa-solid fa-circle-dot"></i>수수료(부가세 포함):0.05%</span>									
	</dd>
	
	<dd class="last">
		<button id="resetS"class="orderBtn1"> 초기화 </button>
		<button id="submitAsks" class="orderBtn2" style="background:#1261c4;" onclick="submitAsks()"> 매도 </button>
	</dd>

</dl>
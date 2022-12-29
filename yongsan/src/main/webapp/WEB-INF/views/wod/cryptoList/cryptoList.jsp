<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<script src="/../resources/js/exchange/cryptoList/cryptoList.js"></script>

<article class="cryptoListBox">
	<section class="cryptoListHeader">
		<div class="searchCrypto">
		
			<div class="cryptoSearchBar">
				<input type="search" id="CryptoVo" name="CryptoVo" class="CryptoVo" placeholder="코인명/심볼검색"/>
				<div style=" width:39; heigth:42pxl; position:relative;"><i style="color:#0062df;" class="fa-solid fa-magnifying-glass"></i></div>
			</div>
			<div class="showCryptoOption" style="border-left: 1px solid #d5d6dc; padding-top:3px;">
				<i style="color:#999;" class="fa-solid fa-gear"></i>
			</div>
			
		</div>
		<div class="cryptoNavi">
			<ul class="cryptoNaviTab">
				<li class="TabActive">원화</li>
				<li>BTC</li>
				<li>USDT</li>
				<li>보유</li>
				<li>관심</li>
			</ul>
		</div>
	</section>
	<section class="cryptoListR">
		<table>
			<colgroup>
				<col width="26"/>
				<col width="26"/>
				<col width="94"/>
				<col width="98"/>
				<col width="58"/>
				<col width="*"/>			
			</colgroup>
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th class="CryptoName" style="text-align:left;">한글명</th>
					<th>현재가</th>
					<th>전일대비</th>
					<th>거래대금</th>
				</tr>
			</thead>
			<tbody id="cryptoListBar">
			
			</tbody>
		</table>
	</section>
</article>
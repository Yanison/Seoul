<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<article class="cryptoListBox">
	<section class="cryptoListHeader">
		<div class="searchCrypto">
			<div class="cryptoSearchBar">
				<input type="search" id="CryptoVo" name="CryptoVo" class="CryptoVo" placeholder="코인명/심볼검색"/>
				<span><i class="fa-solid fa-magnifying-glass"></i></span>
			</div>
			<div class="showCryptoOption"></div>
		</div>
		<div class="cryptoNavi">
			<ul>
				<li>원화</li>
				<li>BTC</li>
				<li>USDT</li>
				<li>보유</li>
				<li>관심</li>
			</ul>
		</div>
	</section>
	<section class="cryptoList">
		
	</section>
</article>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<!-- <link type="text/css" rel="stylesheet" href="./resources/css/입력.css">
#c84a31; red
#0062df; blue
<script src="./resources/js/입력.js"></script> -->



<div class="easyOrderSetting">
	<div>
		<span >간편주문</span>
		<span><i class="fa-regular fa-circle-question"></i></span>
	</div>
	<div>
		<span>편집</span>
		<span style="color:#dedede;">|</span>
		<span>등록</span>
	</div>
</div>

<ul class="availableOrder">
	<li class="availableOrder_1 availableBuy">
		<div style="color:#c84a31">매수가능</div>
		<div>0 <strong>KRW</strong>
		</div>
	</li>
	<li class="availableOrder_1 availableSell">
		<div style="color:#0062df">매도가능</div>
		<div>0 <strong>KRW</strong>
		</div>
	</li>
</ul>

<div class="easyOrderOptions">
	<div class="orderOptions">
	</div>
	<div class="orderOptions">
	</div>
	<div class="orderOptions">
	</div>
	<div class="orderOptions">
	</div>
	<div class="orderOptions">
	</div>
	<div class="orderOptions">
	</div>
</div>
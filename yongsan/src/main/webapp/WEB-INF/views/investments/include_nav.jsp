
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<nav>
	<ul>
		<li class="<c:if test="${nowPage eq 'balance'}">active</c:if>" onclick="goInvestments()"><a>보유자산</a></li>
		<li class="<c:if test="${nowPage eq 'history'}">active</c:if>" onclick="goHistory()"><a>거래내역</a></li>
		<li class="<c:if test="${nowPage eq 'wait_orders'}">active</c:if>"onclick="goWait_orders()" ><a>미체결</a></li>
	</ul>
</nav>
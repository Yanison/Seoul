
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<nav>
	<ul>
		<li class="<c:if test="${nowPage eq 'history'}">active</c:if>" onclick="userOrderHistory_hitory()"><a>유저 거래내역</a></li>
		<li class="<c:if test="${nowPage eq 'wait_orders'}">active</c:if>"onclick="userOrderHistory_wait_order()" ><a>유저 거래 미체결</a></li>
	</ul>
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="../rscs/basicRscs.jsp" %>
</head>
<body>
publicCorona1List

<br>aaa : <c:out value="${resultCode }"/>
<br>aaa : <c:out value="${resultMsg }"/>
<br>aaa : <c:out value="${pageNo }"/>
<br>aaa : <c:out value="${totalCount }"/>
<br>aaa : <c:out value="${numOfRows }"/>
<br>aaa : <c:out value="${items }"/>
<br>aaa : <c:out value="${fn:length(items) }"/>

<c:forEach items="${items}" var="item" varStatus="status">
	<c:out value="${item.YYYY }"/>
	<br><c:out value="${item.MM }"/>	
	<br><c:out value="${item.KIT_PROD_QTY }"/>	
	<br><c:out value="${item.KIT_EXPRT_QTY }"/>	
	<br><c:out value="${item.KIT_STOCK_QTY }"/>	
</c:forEach>

</body>
</html>
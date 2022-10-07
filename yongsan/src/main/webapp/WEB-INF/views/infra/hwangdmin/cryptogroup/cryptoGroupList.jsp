<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>CryptoGroup</title>
<%@ include file="../rsrcHwangdmin.jsp" %>
<link href="../resources/css/root/cryptogroup/cryptogroup.css" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<nav class="navi">
			<%@ include file="../hwangdminComponent/rootNavi.jsp" %>
		</nav>
		<div class="mainDiv">
			<header class="navheader">
				<%@ include file="../hwangdminComponent/rootNaviHeader.jsp" %>	
			</header>
			
			<article class="mainArticle">
				<%@ include file="adminCryptoList.jsp" %>
			</article>
		</div>
	</div>
</div>
</body>
</html>

<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<html>
<head>
<title>CoinHwang</title>

<%@ include file="../rscs/basicRscs.jsp" %>
<script src="/resources/js/helplcenter.js"></script>
<script src="./resources/js/home.js"></script>
</head>
<body style="min-width:1600px;">

<div class="wrapperDiv">
	
	<%@ include file="../include/header2.jsp" %>
	
	<main class="main">
		<%@ include file="sideNavi.jsp" %>
		
		
		<section class="section-right" style="margin-left: 0px;">
			<div class="section-right-info">
				  <div class="section-right-info-top">
				    <h3 style="margin:0px; font-weight:700;">문의사항 게시판</h3>
				  </div>
				  <div class="section-right-info-table">
				  		<table class="container info-table">
				  			<colgroup>
				  				<col width="*">
				  				<col width="155">
				  				<col width="155">
				  				<col width="130">
				  			</colgroup>
				  			<thead class="info-table-thead">
					  			<tr>
						  			<th >제목</th>
						  			<th >작성자</th>
						  			<th >등록일</th>
						  			<th >조회수</th>
					  			</tr>	
				  			</thead>
				  			<tbody class="info-table-tbody">
				  				<c:choose>
									<c:when test="${fn:length(selectBdList) eq 0}}">
									</c:when>
									<c:otherwise>
										<c:forEach items="${selectBdList}" var="bd" varStatus="status">
											<tr onclick="goThisBd(${bd.bdSeq})">
									  			<td class="col_start" style="text-align:left;">${bd.bdTitle} (${bd.cnt})</td>
									  			<td class="col_start" style="text-align:center;">${bd.memberNickname}</td>						
									  			<td class="col_mid" style="text-align:center;"><fmt:formatDate value="${bd.bdRegDateTime}" pattern="yyyy-MM-dd"/></td>
									  			<td class="col_end" style="text-align:center;">${bd.view}</td>
						  					</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
				  			</tbody>
				  		</table>
				  </div>
				  <div class="section-right-footer">
				  	<div>
				  		<%@ include file="../include/pagination.jsp" %>
				  	</div>
				  	<div>
				  		<button type="button" id="board_write" onclick="goHelpcenterBoardWrite()">글쓰기</button>
				  	</div>
				  </div>
			</div>
		</section>
	</main>
	
	
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>
<link type="text/css" rel="stylesheet" href="/resources/css/helpcenter.css">
</html>

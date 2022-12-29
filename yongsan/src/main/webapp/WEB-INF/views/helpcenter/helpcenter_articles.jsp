<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<html>
<head>
<title>CoinHwang</title>

<%@ include file="../rscs/basicRscs.jsp" %>

</head>

<body style="min-width:1600px;">

<div class="wrapperDiv">
	
	<%@ include file="../include/header2.jsp" %>
	
	<main class="main">
		<%@ include file="sideNavi.jsp" %>
		
		
		<section class="section-right" style="margin-left: 0px;">
			<form action="/helpcenter/board/write" method="post">
			<div class="section-right-info">
				  <div class="section-right-info-top">
				    <h3 style="margin:0px; font-weight:700;">문의내역</h3>
				  </div>
				  <div class="section-right-info-table">
				  		<table class="container info-table">
				  			<colgroup>
				  				<col width="40">
				  				<col width="*">
				  				<col width="155">
				  				<col width="130">
				  				<col width="130">
				  			</colgroup>
				  			<thead class="info-table-thead">
					  			<tr>
					  				<th ><input type="checkbox" id="chkAll" class="chkall" ></th>
						  			<th >제목</th>
						  			<th >등록일</th>
						  			<th >조회수</th>
						  			<th>수정하기</th>
					  			</tr>	
				  			</thead>
				  			<tbody class="info-table-tbody">
				  				<c:choose>
									<c:when test="${fn:length(selectBdList) eq 0}}">
									</c:when>
									<c:otherwise>
										<c:forEach items="${selectBdList}" var="bd" varStatus="status">
											<tr>
												<td class="col_start" style="text-align:left;"><input type="checkbox" class="chkBox" name="bdChk" value="${bd.bdSeq}"></td>
									  			<td class="col_start" style="text-align:left;" onclick="goThisBd(${bd.bdSeq})">${bd.bdTitle} (${bd.cnt})</td>							
									  			<td class="col_mid" style="text-align:center;"><fmt:formatDate value="${bd.bdRegDateTime}" pattern="yyyy-MM-dd"/></td>
									  			<td class="col_end" style="text-align:center;">${bd.view}</td>
									  			<td><button type="button" onclick="goThisBdUpt(${bd.bdSeq})">수정하기</button></td>
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
				  	<div class="btnDiv">
				  		<button type="button" onclick="goHelpcenterBoardWrite()">글쓰기</button>
				  		<button id="bdDelBtn"  type="button" >삭제하기</button>
				  	</div>
				  	<script>
				  	
				  	</script>
				  </div>
			</div>
			</form>
		</section>
	</main>
	
	
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>
<script src="/resources/js/helplcenter.js"></script>
<link type="text/css" rel="stylesheet" href="/resources/css/helpcenter.css">
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>CoinHwang</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="/resources/css/helpcenter.css">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="./resources/js/helpcenter.js"></script>
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
				    <h3 style="margin:0px; font-weight:700;">form title</h3>
				      <form class="d-flex" role="search">
				        <input class="form-control me-2" type="search" placeholder="검색어를 입력해주세요" aria-label="Search" style="height:44px; width:295px; padding:20px;">
				        <button class="btn btn-outline-success" type="submit" style="margin: 0 0 0 10px;">검색</button>
				      </form>
				  </div>
				  <div class="section-right-info-table">
				  		<table class="container info-table">
				  			<colgroup>
				  				<col width="*">
				  				<col width="155">
				  				<col width="130">
				  			</colgroup>
				  			<thead class="info-table-thead">
					  			<tr>
						  			<th >제목</th>
						  			<th >등록일</th>
						  			<th >조회수</th>
					  			</tr>	
				  			</thead>
				  			<tbody class="info-table-tbody">
			  					<tr>
						  			<td class="col_start" style="text-align:left;">공지사항 제목</td>							
						  			<td class="col_mid" style="text-align:center;">2022.01.01</td>
						  			<td class="col_end" style="text-align:center;">00</td>
			  					</tr>			
				  			</tbody>
				  		</table>
				  </div>
				  <div class="section-right-footer">
				  	<div>
				  		<%@ include file="../include/pagination.jsp" %>
				  	</div>
				  	<div>
				  		<button>글쓰기</button>
				  	</div>
				  </div>
			</div>
		</section>
	</main>
	
	
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>
</html>

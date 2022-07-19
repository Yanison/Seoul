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
<link type="text/css" rel="stylesheet" href="./resources/css/helpcenter.css">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="./resources/js/helpcenter.js"></script>
<script src="./resources/js/home.js"></script>


</head>
<body style="min-width:1600px;">

<div class="wrapper">
	
	<%@ include file="../include/header2.jsp" %>
	
	<main class="main">
		<section class="section-left" style="margin-right: 15px;">
			<div class="left-navi">
				<div class="title-box">
				<h3 class="title-box-text left-navi-contents1" style="margin:0 0 0 30px; font-size:20px;">고객센터</h3>
				</div>
				<div class="menu-bar">
					<ul>
					
						
			  			<li class="left-navi-contents <c:if test="${now menu eq 'helpcenter'}">active</c:if>"  data-page="helpcenter">
							<span class="on">공지사항</span>
						</li>			
						<li class="left-navi-contents">
							<span class="on">업비트소식</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">입출금 이용 안내</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">입출금 현황</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">정책 및 거래지원 문의</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">1:1문의하기</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">문의내역</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">이용자 가이드</span>
						</li>
						<li class="left-navi-contents">
							<span class="on">카카오톡 문의(24시간)</span>
						</li>
					</ul>
					
					
				</div>
			</div>
		</section>
		
		
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
				  			<thead class="info-table-thead">
					  			<tr>
					  			<td class="col">제목</td>
					  			<td class="col">등록일</td>
					  			<td class="col">조회수</td>
					  			</tr>	
				  			</thead>
				  			<tbody class="info-table-tbody">
				  					<tr>
							  			<td class="col">공지사항 제목</td>							
							  			<td class="col">2022.01.01</td>
							  			<td class="col">00</td>
				  					</tr>			
				  			</tbody>
				  		
				  		</table>
				  </div>
			</div>
		</section>
	</main>
	
	
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>
</html>

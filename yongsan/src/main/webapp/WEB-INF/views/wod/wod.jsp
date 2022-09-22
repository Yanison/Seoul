<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<html>
<head>
<title>입출금</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href="../resources/css/wod.css">

<!-- fontawsome -->
<script src="https://kit.fontawesome.com/a991bae8fd.js" crossorigin="anonymous"></script>

<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="../resources/js/wod/wod.js"></script>


</head>
<body>

<div class="WodMain">
	<div class="header">
		<%@ include file="../include/header2.jsp" %>
	</div>
	
	<div class="WodWrapper">
		<section class="sectionL">
			<article class="headNotice">
				<div class="headNoticeL">
					<span style="display:block"><i class="fa-solid fa-exclamation"></i> <strong>공지</strong></span>
				</div>
				<div class="headNoticeR">
					<div>특정금융정보법에 따른 미신고 가상자산사업자와의 입출금 제한 및 유의사항</div>
					<div><i class="fa-thin fa-x"></i></div>
				</div>
			</article>
			
			<article class="myCrypto">
				<%@ include file="../wod/searchCoin.jsp" %>
			</article>
			
			<article class="coinWod">
				<div class="coinWodHeader">
					<div>
						<strong>KRW 입출금</strong>
					</div>
					<div><i class="fa-solid fa-rotate-right"></i></div>
				</div>
				<div class="myBalance">
					<table>
						<colgroup>
							<col width="40%">
							<col width="60%">
						</colgroup>
						<thead>
							<tr>
								<th>보유금액</th>
								<th>
									<strong>0</strong>
									<i>KRW</i>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<span>거래대기</span>
									<span>
										<button>보기</button>
									</span>								
								</td>
								<td>
									<em>0</em>
									<i>KRW</i>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="wodOptions">
					<div class="wobNavTab">
						<div class="wobNavTab_contents KRW">KRW충전</div>
						<div class="wobNavTab_contents withdrawal">출금신청</div>
						<div class="wobNavTab_contents wobHis">입출금내역</div>
					</div>
					<div class="wodOptionPage _1">
						<div class="account">
							<table>
								<tr>
									<th>연계계좌</th>
									<td>
										<p>
											<em>01031744295</em>
											&nbsp;
											은행이름
										</p>
										<i>|</i>
										<em>황선야</em>
									</td>
								</tr>
								<tr>
									<th>입금금액(KRW)</th>
									<td>
										<input type="text" placeholder="최소 5,000 KRW">
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<div class="wodOptionPage _2">
					
					</div>
					
					<div class="wodOptionPage _3">
					
					</div>
				</div>
			</article>
		</section>
		
		<section class="sectionR">
		</section>
	</div>
	
	<div class="footer">
		<%@ include file="../include/footer.jsp" %>
	</div>

</div>
</body>
</html>

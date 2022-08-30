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
	
<link type="text/css" rel="stylesheet" href="./resources/css/root.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/root.js"></script>


</head>
<body>



	<div class="body-wrapper">
	
	
			<div class="left-wrapper">
			
				<%@ include file="../root_left_section/leftnavi_cg.jsp" %>
			</div>

			
			<div class="right-wrapper">
				<section class="right">
					<header class="head-box">
						<div class="logo-box"></div>
					</header>
					<div class="search-bar">				
						<div class="searchBar">
							<div class="searchBar1">
								<form>								
									<select class="formcontents formTypeSelect delNY">
										<option value="">N</option>
										<option value="">Y</option>
										<option value="">Y</option>
										<option value="">Y</option>
									</select>
									<select class="formcontents formTypeSelect modDate">
										<option value="">N</option>
										<option value="">Y</option>
										<option value="">Y</option>
										<option value="">Y</option>
									</select>
									<input class="formcontents formTypeDate dateStart" type="date" data-placeholder="날짜 선택">
									<input class="formcontents formTypeDate dateEnd" type="date" data-placeholder="날짜 선택">
								</form>	
							</div>
							<div class="searchBar2">
								<form>								
									<select class="formcontents formTypeSelect delNY">
										<option value="">검색구분</option>
										<option value="">Y</option>
										<option value="">Y</option>
										<option value="">Y</option>
									</select>
									<input class="formcontents formTypeSearch " type="search">
								</form>	
							</div>
						</div>
						<div class="UMbox">
							<button type="button" class="btn btn-info">등록</button>
							<button type="button" class="btn btn-info">수정</button>	
						</div>						
					</div>
					<div class="cgTableWrapper">
						<table class="cgTable">
							<colgroup>
								<col width="20px">
								<col width="30px">
								<col width="200px">
								<col width="">
								<col width="">
								<col width="100px">
								<col width="150px"">
								<col width="150px">
							</colgroup>
							<thead>
								<tr>
									<th>
									<div><input type="checkbox" name="codecheck"></div>
									</th>
									<th>#</th>
									<th>
									코드그룹 코드
									</th>
									<th>
										코드그룹 이름(한글)
									</th>
									<th>
										코드그룹 이름(영문)
									</th>
									<th>
										코드갯수
									</th>
									<th>
										등록일
									</th>
									<th>
									수정일
									</th>
								</tr>
							</thead>
							<tbody class="cgBody">
								<tr>
									<td><input type="checkbox" name="codecheck"></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>	
							</tbody>
						</table>
					</div>
				</section>
			</div>

		

	
	</div>


</body>
</html>

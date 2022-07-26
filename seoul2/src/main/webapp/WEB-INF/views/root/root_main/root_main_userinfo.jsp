<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>root_code_manage</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href="./resources/css/root.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/root_main_userinfo.js"></script>


</head>
<body>



	<div class="body-wrapper">
			
			<div class="left-wrapper">
			
				<%@ include file="../root_left_section/leftnavi_main.jsp" %>			
			</div>

			<div class="right-wrapper">
				<section class="right">
					
					<%@ include file="../root_right_section/right_include/layout_right_header.jsp" %>
					
					<div class="search-bar">
					
						<div class="search-bar-title">
						유저 정보
						</div>
					
					<form class="form">
						<div class="form-box">
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
						</div>
						<div class="form-box">
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
							<span style="margin-right:10px"> ~ </span>				
							<input class="form-control" type="text" placeholder="" aria-label="default input example">
							<select class="form-select form-control-items" aria-label="Default select example" style="margin-right:10px;">
							  <option selected>성별</option>
							  <option value="1">남자</option>
							  <option value="2">여자</option>
							  
							</select>
						 	<button class="btn btn-outline-success" type="submit" style="margin-right:93px">검색</button>
						</div>
						
					</form>
						<div class="delete" style="margin-right:47px; padding: 45px 0 0 0;">
							
							<button id="add-user" type="button" class="btn btn-primary btn-contents"
							        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">
							<i class="fa-brands fa-wpforms">등록</i> 
							</button>
					
							<button id="del-user" type="button" class="btn btn-primary btn-contents"
							        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">
							 <i class="fa-solid fa-trash-can">삭제</i>
							</button>	
						</div>
					
					</div>
					
					<main class="info_list_tb">
						<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">#
						      <div class="form-check form-check-inline btn-contents">
										  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">	 
							 </div>
						      
						      </th>
						      <th scope="col">이름</th>
						      <th scope="col">생년월일</th>
						      <th scope="col">전화번호</th>
						      <th scope="col">주소</th>
						      <th scope="col">이메일</th>
						      <th scope="col">여권 영문이름</th>
						      <th scope="col">성별</th>
						      <th scope="col">직장명</th>
						      <th scope="col">직장정보</th>					      
						      <th scope="col">직장주소</th>
						      <th></th>
						      
						    </tr>
						  </thead>
						  <tbody id="getAjax">

						  
						  
						  
						  <c:forEach var="user" items="${userlist}">
							 	<tr class="dbContainer" data-user-idx="${user.user_idx}">
							      <th scope="row" id="user_idx" >
							      	<div class="form-check form-check-inline">
									  <input class="form-check-input" type="checkbox" id="inlineCheckbox" value="option1" style="margin-right:10px;">
									  <label class="form-check-label" for="inlineCheckboxLabel" id="inlineCheckboxLabel" > ${user.user_idx} </label>
									</div>
							      </th>
							      <td>name</td>
							      <td id="dob" > ${user.dob}</td>
							      <td>tel</td>
							      <td>
								      <span>주소_</span><br>
								      <span>상세주소_${user.addressDetail}</span>
							      </td>							     	
							      <td id="email"> ${user.email} </td> 
							      <td id="passPortNameEng"> ${user.passPortNameEng} </td>
							      <td id="gender">성별</td>		 
							      <td id="jobName"> ${user.jobName} </td>
							      <td id="job" >
								      <span>직장명_${user.jobInfo}</span><br>
								      <span>${user.jobType}</span>	
							       </td>
							      
							      <td id="jobLoca">
								      <span>직장주소_${user.jobAddress}</span><br>
								      <span>직장상세주소_${user.jobAddressDetail}</span>
							      </td>
							      <td>
							      <i class="fa-solid fa-pen"></i>
							      <button id="del-user" type="button" class="btn btn-primary btn-contents"
							        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">
									 <i class="fa-solid fa-pen">수정</i>
									</button>
							      </td>   
							    </tr>
						</c:forEach>
						  
						  
						    
						    
						    
						  </tbody>
						</table>
					
					</main>
						<div class="footer"></div>
						<div class="paging">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
						    <li class="page-item">
						      <a class="page-link" href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li class="page-item"><a class="page-link" href="#" data-page="1">1</a></li>
						    <li class="page-item"><a class="page-link" href="#">2</a></li>
						    <li class="page-item"><a class="page-link" href="#">3</a></li>
						    <li class="page-item"><a class="page-link" href="#">4</a></li>
						    <li class="page-item"><a class="page-link" href="#">5</a></li>
						    <li class="page-item"><a class="page-link" href="#">6</a></li>
						    <li class="page-item"><a class="page-link" href="#">7</a></li>
						    <li class="page-item">
						      <a class="page-link" href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
					</div>
				</section>
			</div>
	

	
	</div>


</body>
</html>

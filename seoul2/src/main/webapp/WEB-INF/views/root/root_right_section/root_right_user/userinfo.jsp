<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>


<!-- <!-- css,js _ root.jsp에서 상속받음. --> 
<link type="text/css" rel="stylesheet" href="./resources/css/root.css">
<script src="./resources/js/root.js"></script> -->

				<section class="right">
					
					<%@ include file="../right_include/layout_right_header.jsp" %>
					
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
							<i class="fa-brands fa-wpforms"></i> 등록
							</button>
							<button id="adit-user" type="button" class="btn btn-primary btn-contents"
							        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">
							<i class="fa-solid fa-pen"></i>  수정
							</button>
							
							<div class="form-check form-check-inline btn-contents">
										  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
										  <label class="form-check-label" for="inlineCheckbox1">전체선택</label>
							</div>
							
							<button id="del-user" type="button" class="btn btn-primary btn-contents"
							        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; ">
							 <i class="fa-solid fa-trash-can"></i>삭제
							</button>	
						</div>
					
					</div>
					
					<main class="info_list_tb">
						<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">이름</th>
						      <th scope="col">생년월일</th>
						      <th scope="col">전화번호</th>
						      <th scope="col">주소</th>
						      <th scope="col">이메일</th>
						      <th scope="col">여권 영문이름</th>
						      <th scope="col">인증여부</th>
						      <th scope="col">직장정보</th>
						      <th scope="col">직장명</th>
						      <th scope="col">직장주소</th>
						      
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row" id="user_idx" >
						      	<div class="form-check form-check-inline">
								  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
								  <label class="form-check-label" for="inlineCheckbox1">1</label>
								</div>
						      </th>
						      <td id="name" ></td>
						      <td id="dob"></td>
						      <td id="address"></td>
						      <td id="tel"></td>
						      <td id="email" ></td>
						      <td id="passport_name_eng" ></td>
						      <td id="verifi"></td>
						      <td id="job"></td>
						      <td id="company_name" ></td>
						      <td id="job_loca" ></td>
						    </tr>
						    
						    
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
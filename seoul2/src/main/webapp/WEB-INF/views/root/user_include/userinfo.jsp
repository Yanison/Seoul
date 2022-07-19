<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>


<link type="text/css" rel="stylesheet" href="./resources/css/root.css">
<script src="./resources/js/root.js"></script>

				<section class="right">
					<header class="head-box">
						<div class="logo-box"></div>
					</header>
					<div class="search-bar">
					
						<div></div>
					
					<form class="form">
						<div class="form-box">
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
						</div>
						<div class="form-box">
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
							<span style="margin-right:10px"> ~ </span>				
							<input class="form-control" type="text" placeholder="Default input" aria-label="default input example">
							<select class="form-select form-control-items" aria-label="Default select example" style="margin-right:10px;">
							  <option selected>성별</option>
							  <option value="1">남자</option>
							  <option value="2">여자</option>
							  
							</select>
						 	<button class="btn btn-outline-success" type="submit" style="margin-right:93px">검색</button>
						</div>
						
					</form>
					
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
						      <th scope="col">인증레벨</th>
						      <th scope="col">직장정보</th>
						      <th scope="col">직장명</th>
						      <th scope="col">직장주소</th>
						      
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      
						      
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						       <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      <td></td>
						      
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td >Larry </td>
						      <td></td>
						      <td></td>
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
					
					</main>
					











<!-- 					<nav class="navbar bg-light">
					  <div class="container-fluid">
					    <form class="d-flex" role="search">
							<input class="form-control me-2 form-control-items" type="search" placeholder="" aria-label="Search">
						      <input class="form-control me-2 form-control-items" type="search" placeholder="" aria-label="Search">
						      <input class="form-control me-2 form-control-items" type="search" placeholder="" aria-label="Search">
						      <input class="form-control me-2 form-control-items" type="search" placeholder="" aria-label="Search">
						     <select class="form-select form-control-items" aria-label="Default select example" style="margin-right:15px;">
							  <option selected>성별</option>
							  <option value="1">남자</option>
							  <option value="2">여자</option>
							  
							</select>
					     
					    </form>
					  </div>
					  <div class="container-fluid">
					    <form class="d-flex" role="search" style="width:350px; display:flex; align-items:center;">
							<input class="form-control me-2 form-control-items" type="search" placeholder="0000.00.00" aria-label="Search">
							<span> ~ </span>
						     <input class="form-control me-2 form-control-items" type="search" placeholder="0000.00.00" aria-label="Search">
					    </form>
					  </div>
					</nav> -->
					
					
					</div>
					<main class="search-bar-table"></main>
				</section>
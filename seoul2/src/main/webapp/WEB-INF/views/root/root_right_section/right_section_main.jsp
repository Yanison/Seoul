<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>


<!-- css,js _ root.jsp에서 상속받음. --> 
<!-- <link type="text/css" rel="stylesheet" href="./resources/css/root.css">
<script src="./resources/js/root.js"></script> -->

				<section class="right">
					<%@ include file="./right_include/layout_right_header.jsp" %>
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
									<input class="formcontents formTypeDate dateStart" type="date">
									<input class="formcontents formTypeDate dateEnd" type="date" placeholder="종료일">
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
					</div>
					<div class="cgTableWrapper">
						<table class="cgTable">
							<colspan>
								<col width="20px">
								<col width="30px">
								<col width="200px">
								<col width="">
								<col width="">
								<col width="100px">
								<col width="150px"">
								<col width="150px">
							</colspan>
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
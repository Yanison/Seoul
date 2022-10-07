<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>CodeGroup</title>
<%@ include file="../rsrcHwangdmin.jsp" %>
</head>
<body>
	<div class="wrapper">
		<nav class="navi">
			<%@ include file="../hwangdminComponent/rootNavi.jsp" %>
		</nav>
		<div class="mainDiv">
			<header class="navheader">
				<%@ include file="../hwangdminComponent/rootNaviHeader.jsp" %>	
			</header>
			
			<article class="adminDiv">
				<section class="adminHeader">
					<h1>
						CodeGroupSearch
					</h1>	
					<form>
						<div class="inputGroup" class="form-control">
								<div class="input-group mb-3 shValue">
									<select id="shOption" class="shOption form-control select" name="shOption">
										<option value="0">SelectOption</option>
										<option value="1">Date</option>
										<option value="2">Seq</option>
										<option value="3">Name</option>
										<option value="4">ActiveNY</option>									 
									</select>									
									<span class="input-group-text" id="addon-wrapping">⬇</span>
								</div>								
								<div class="datePick">
									<div class="input-group mb-3 shValue">
									  <input type="text" name="shValue" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
									</div>
									
									<div class="input-group mb-3 shValue">
									  <input type="text" name="shValue" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
									</div>
																		
								</div>		
						</div>
						<div class="inputGroup" class="form-control">					
							<div class="input-group mb-3 shValue">
							  <input type="text" name="shValue" placeholder="shValue" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
							</div>	
							
							<div class="input-group mb-3 submit">
							  <input type="submit" name="shValue" placeholder="shValue" value="submit" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">				  	
							</div>
							<a class="refresh" href="http://127.0.0.1:8082/yongsancode/codeGroupList"><i class="fa-solid fa-arrows-rotate"></i></a>									
						</div>
					</form>
						
				</section>
				<section class="adminBody">
					<div class="groupListWrapper">
						<table>
							<colgroup>
								<col width="25px"/>
								<col width="30px"/>
								<col width="150"/>
								<col width="200"/>
								<col width="50"/>
								<col width="50"/>
							</colgroup>
							<thead>
								<tr>
									<th><input name="listCheckAll" type="checkbox"/></th>
									<th>#</th>
									<th>infrCgName</th>
									<th>infrCgNameEng</th>
									<th>infrCgUseNy</th>
									<th>infrCgDelNy</th>
								</tr>
							</thead>
							<tbody>
								<%-- <c:choose>
									<c:when test="${fn:length(list) eq 0}">
										<tr>
											<td colspan="6">none</td>
										</tr>
									</c:when>
									<c:otherwise>
										<forEach items="${list}" var="list" varStatus="status">
											<tr>
												<td><input name="listCheck" type="checkbox"/></td>
												<td><c:out value="${list.infrCgName}"/></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</forEach>									
									</c:otherwise>
								</c:choose> --%>
								
							</tbody>
						</table>	
					</div>
					<div class="pageDiv">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
						    <li class="page-item">
						      <a class="page-link" href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li class="page-item"><a class="page-link" href="#">1</a></li>
						    <li class="page-item"><a class="page-link" href="#">2</a></li>
						    <li class="page-item"><a class="page-link" href="#">3</a></li>
						    <li class="page-item">
						      <a class="page-link" href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
					</div>
					
				</section>
			</article>
		</div>
	</div>
</body>
</html>

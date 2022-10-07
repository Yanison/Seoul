<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<jsp:useBean id="CodeGroupServiceImpl" class="com.seoul.infra.modules.codegroup.CodeGroupServiceImpl"/>


<html>
<head>
<title>memberGroupList</title>
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
						MemberGroupSearch
					</h1>	
					<form method="get" action="memberGroupList">
						<div class="inputGroup" class="form-control">
								<div class="input-group mb-3 shValue">
									<select id="shOption" class="shOption form-control select" name="shOption">
										<option value="0">SelectOption</option>
										<option value="1" <c:if test="${vo.shOption eq 1}"> selected </c:if> >Date</option>
										<option value="2" <c:if test="${vo.shOption eq 2}"> selected </c:if> >Seq</option>
										<option value="3" <c:if test="${vo.shOption eq 3}"> selected </c:if> >Name</option>
										<option value="4" <c:if test="${vo.shOption eq 4}"> selected </c:if> >ActiveNY</option>									 
									</select>									
									<span class="input-group-text" id="addon-wrapping">⬇</span>
								</div>
								<p> 날짜조회 : <input type="text" id="startDate" name="shValueDate" placeholder="${vo.shValueDate}"</p>
								<p style="margin-left:5px;"> ~ <input type="text" id="endDate" name="shValueDate" placeholder="${vo.shValueDate}"</p>						
								<!-- <div class="datePick">
									<div class="input-group mb-3 shValue">
									  <input type="text" name="shValueDate" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
									</div>
									
									<div class="input-group mb-3 shValue">
									  <input type="text" name="shValueDate" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
									</div>
																		
								</div> -->		
						</div>
						<div class="inputGroup" class="form-control">					
							<div class="input-group mb-3 shValue">
							  <input type="text" name="shValue" placeholder="${vo.shValue}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
							</div>	
							
							<div class="input-group mb-3 submit">
							  <input type="submit"  value="submit" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">				  	
							</div>
							<a class="refresh" href="http://127.0.0.1:8082/yongsancode/memberGroupList"><i class="fa-solid fa-arrows-rotate"></i></a>									
						</div>
					</form>
						
				</section>
				<section class="adminBody">
					<div class="groupListWrapper">
						<table id="tbl">
							<colgroup>
								<col width="25"/>
								<col width="50"/>
								<col width="200"/>
								<col width="200"/>
								<col width="200"/>
								<col width="200"/>
								<col width="200"/>
								<col width="300"/>
								<col width="300"/>
								<col width="250"/>
								<col width="250"/>
								<col width="200"/>
								<col width="200"/>
								<col width="25"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input name="listCheckAll" type="checkbox"/>
									</th>
									<th>#</th>
									<th>Id</th>
									<th>Pw</th>
									<th>Name</th>
									<th>Nickname</th>
									<th>gender</th>
									<th>AddrType1</th>
									<th>AddrDetail</th>
									<th>Tel</th>
									<th>Email</th>
									<th>regDate / regBy</th>
									<th>ModDate / ModBy</th>
									<th>AcvtiveNy</th>
								</tr>
								<c:set var="ccgGender" value="${CodeGroupServiceImpl.selectListCachedCode('1')}"/>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(list) eq 0}">
										<tr>
											<td colspan="11"> no data found</td>
										</tr>
									</c:when>
									
									<c:otherwise>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr class="thisTr" value="${list.memberSeq}">
												<td>
													<input name="listCheck" type="checkbox"/>
												</td>
												<td>
												${list.memberSeq}
												<input name="thisMember" type="hidden" >
												</td>
												<td>${list.id}</td>
												<td>${list.pw}</td>
												<td>${list.name}</td>
												<td>${list.nickname}</td>
												<td>						
														
													<%-- <c:forEach items="${ccgGender}" var="ccgGender" varStatus="statusGender">													
														<c:if test="${list.gender eq ccgGender.infrCcgSeq}"><c:out value="${ccgGender.infrCcgName}"/></c:if>
													</c:forEach> 
													이게 왜 안될까
													선생님, forEach 안에서 사용된 변수에서 들어오는 값에 의해 초기화가 됐습니다. 
													c:set에서 들어온 값을 항상 가지고 있어야하는데 두번째 forEach문에서 초기화가 되었기 때문에 반복문이 한번밖에 적용이 되지 않았던거지요.
													var = "ccgGender"
													--%>
													<c:forEach items="${ccgGender}" var="ccgGender2" varStatus="statusGender">
														<c:if test="${list.gender eq ccgGender2.infrCcgSeq}"><c:out value="${ccgGender2.infrCcgName }"/></c:if>
													</c:forEach>
													
												</td>
												<td>${list.addrType1}</td>
												<td>${list.addrDetail}</td>
												<td>${list.tel}</td>
												<td>${list.email}</td>
												<td>${list.regDateAt} / ${list.regDateBy}</td>
												<td>${list.modDateAt} / ${list.modDateBy}</td>
												<td>${list.activeNy}</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>	
					</div>
					<div class="pageDiv">
						<%@ include file="../pagination.jsp"%>					
					</div>
				</section>
			</article>
		</div>
	</div>
</body>
</html>

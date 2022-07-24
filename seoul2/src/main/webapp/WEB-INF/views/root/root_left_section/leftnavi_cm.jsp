<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<link type="text/css" rel="stylesheet" href="./resources/css/root.css">
<script src="./resources/js/root_left_section.js"></script>

<section class="left">
<div class="left-navi-header">
	<div id="root_home" class="logo">코인황</div>


	  	<ul class="nav nav-tabs" data-pade="root_code_manage">
	  <li class="nav-item">
	    <a id="nav-link-1" class="nav-link active" aria-current="page" href="./root_main"style="border:none">User</a>
	  </li>
	  <li class="nav-item">
	    <a id="nav-link-2" class="nav-link active" aria-current="page" href="./root_codegroup" style="border:none">코드그룹관리</a>
	  </li>
	  <li class="nav-item">
	    <a id="nav-link-3" class="nav-link active" aria-current="page" href="./root_code_manage" >코드관리</a>
	  </li>
	</ul>	  
		  
		</div>  
		  
<div class="left-navi">		  


	<div class="accordion" id="accordionPanelsStayOpenExample">
	  <div class="accordion-item">
	    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
	      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
	        코드관리 메인
	      </button>
	    </h2>
	    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
	      <div class="accordion-body">
	        	<div class="accordion-body-menue">코드관리 메뉴</div>
	        	<div class="accordion-body-menue">코드관리 메뉴</div>
	        	<div class="accordion-body-menue">코드관리 메뉴</div>
	        	
	      </div>
	    </div>
	  </div>
	  <div class="accordion-item">
	    <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
	      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
	        회원관리
	      </button>
	    </h2>
	    <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
	      <div class="accordion-body">
	      		<div class="accordion-body-menue" id="user_info">회원 정보</div>
	      		<div class="accordion-body-menue">회원 문의</div>
	      		<div class="accordion-body-menue">회원 관리 메뉴</div>			      
	      </div>
	    </div>
	  </div>
	  <div class="accordion-item">
	    <h2 class="accordion-header" id="panelsStayOpen-headingThree">
	      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
	        환경설정
	      </button>
	    </h2>
	    <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
	      <div class="accordion-body">
	      		<div class="accordion-body-menue">UI 관리</div>
	      		<div class="accordion-body-menue">환경설정 메뉴</div>
	      		<div class="accordion-body-menue">환경설정 메뉴</div>						      		
	      </div>
	    </div>
	  </div>
	</div>


</div>
</section>
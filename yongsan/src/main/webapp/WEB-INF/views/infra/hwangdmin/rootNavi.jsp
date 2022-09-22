<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<label class="naviLink">
	<i class="fa-solid fa-users-gear"></i>
	<a style="margin-left:10px;" href="http://127.0.0.1:8082/yongsancode/roothome">Admin</a>
</label>


<div class="naviItems">
	<p style="margin:20px 17.5px;">DashBoard</p>
</div>
<div class="accordion" id="accordionPanelsStayOpenExample">
	<div class="naviItems">
	 <div class="accordion-item">
	    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
	      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
	        CodeGroup
	      </button>
	    </h2>
	    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
	      <div class="accordion-body">
	        	<a class="accordion-body-menue" href="http://127.0.0.1:8082/yongsancode/codeGroupList">코드그룹</a>
	        	<a class="accordion-body-menue">코드그룹관리</a>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="naviItems">
		<div class="accordion-item">
		    <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
		        MemberGroup
		      </button>
		    </h2>
		    <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
		      <div class="accordion-body">
		      		<a id="nvai-user-info" class="accordion-body-menue" href="http://127.0.0.1:8082/yongsancode/memberGroupList">회원 정보</a>
		      		<a class="accordion-body-menue">회원 문의</a>
		      		<a class="accordion-body-menue">회원 관리 메뉴</a>			      
		      </div>
		    </div>
		  </div>
	</div>
</div>
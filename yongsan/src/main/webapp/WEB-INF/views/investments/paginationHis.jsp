<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<script src="/resources/js/pagination.js"></script>

<input id="index" type="hidden" value="${i.index}">

<div class="container-fluid px-0 mt-2">
    <div class="row">
        <div class="col">
            <!-- <ul class="pagination pagination-sm justify-content-center mb-0"> -->
            <ul class="pagination justify-content-center mb-0">
                <!-- <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-angles-left"></i></a></li> -->
				<c:if test="${vo.startPage gt vo.pageNumToShow}">
				                <li class="page-item"><a class="page-link" href="javascript:goList(${vo.startPage - 1})"><i class="fa-solid fa-angle-left"></i></a></li>
				</c:if>
				<c:forEach begin="${vo.startPage}" end="${vo.endPage}" varStatus="i">
					<c:choose>
						<c:when test="${i.index eq vo.thisPage}">
				                <li class="page-item active"><a class="page-link" href="javascript:goList(${i.index})">${i.index}</a></li>
						</c:when>
						<c:otherwise>             
				                <li class="page-item"><a class="page-link" href="javascript:goList(${i.index})">${i.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>                
				<c:if test="${vo.endPage ne vo.totalPages}">                
				                <li class="page-item"><a class="page-link" href="javascript:goList(${vo.endPage + 1})"><i class="fa-solid fa-angle-right"></i></a></li>
				</c:if>
                <!-- <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-angles-right"></i></a></li> -->
            </ul>
        </div>
    </div>
</div>






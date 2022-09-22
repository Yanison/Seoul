<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<!-- jQuery -->


<script>
	var form = $('form#paging');
	console.log(form);
	var goUrlList = "./yongsancode/memberGroupList"
	goList = function(thisPage){
		$('input:hidden[name=thisPage]').val(thisPage);
		/* form.attr("action",goUrlList).submit(); */
		$('#submit').click();
	}
</script>

    <form id="paging" action="memberGroupList">
    	<input type="hidden" name="mainkey">
    	<input type="hidden" name="thisPage" value="<c:out value="${vo.thisPage}" default="1"/>">
    	<input type="hidden" name="rowNumToShow" value="<c:out value="${vo.rowNumToShow}"/>">
    	<input type="hidden" name="checkboxSeqArray">
    	<input type="hidden" name="shValue" value="<c:out value="${vo.shOption}"/>">
    	<input type="hidden" name="shValue" value="<c:out value="${vo.shValue}"/>">
    	<input type="submit" id="submit" style="display:none;">
    </form>


<div class="container-fluid px-0 mt-2">

    <div class="row">
        <div class="col">
            <!-- <ul class="pagination pagination-sm justify-content-center mb-0"> -->
            <!-- 페이징되는 부분 -->
            <ul class="pagination justify-content-center mb-0">
                <!-- <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-angles-left"></i></a></li> -->
				<!-- 시작페이지가 보여지는페이지수 보다 클 경우 -->
				<c:if test="${vo.startPage gt vo.pageNumToShow}"> <!-- gt 는 > -->
				                <li class="page-item">
					                <a class="page-link" href="javascript:goList(${vo.startPage - 1})">
					                	<
					                </a>
				                </li>
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
				              
				<c:if test="${vo.endPage ne vo.totalPages}">  <!-- ne는 !=  -->            
				                <li class="page-item">
					                <a class="page-link" href="javascript:goList(${vo.endPage + 1})">
						                >
					                </a>
				                </li>
				</c:if>
                <!-- <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-angles-right"></i></a></li> -->
            </ul>
        </div>
    </div>
</div>






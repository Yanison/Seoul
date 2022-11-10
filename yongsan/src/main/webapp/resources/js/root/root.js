$(document).ready(function(){
	
	
	$( function() {
	    $( "#startDate" ).datepicker({
	    	changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
	    	changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	    	dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
	    	yearRange: "1700:2023"
	    });
	  } );
	  $( function() {
	    $( "#endDate" ).datepicker({
	    	changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
	    	changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	    	dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
	    	minDate: '-100y'
	    });
	  } );	
	
	var dpdiv = $('.datePick');
	dpdiv.hide();
	
	$('select[name="shOption"]').change(function(){
		console.log($(this).val());
		var dp = $(this).val();
		
		if (dp == 1){
			dpdiv.show();
		}else{
			dpdiv.hide();
		}
	})
	

	$('.thisTr').click(function(){
		var str =''
		var tdArr = new Array(); //배열 선언
		
		//현재 클릭된 Row(<tr>)
		var tr = $(this)
		var td = tr.children();
		
		//tr.text() 는 클릭된 row 즉 tr에 있는 모든 값을 가져온다.
		console.log('클릭한 row의 모든 데이타:' + tr.text());
		
		//반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
		td.each(function(i){
			tdArr.push(td.eq(i).text());
		});
		
		console.log("배열에 담긴 값 : " + tdArr);
		console.log('@@'+td.eq(1).text());
		console.log(td.eq(2).text());
		console.log(td.eq(3).text());
	})
	
	$("#btnExel").click(function() {
		console.log("btnExcel")
		$.ajax({
			url:'./excelDownload'
			,success:function(rp){
				console.log("sccss")
			}
			,err:function(){
				alert("err")
			}
		})
	});
	
});
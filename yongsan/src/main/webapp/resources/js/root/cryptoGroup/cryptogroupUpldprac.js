upload = function(objName, seq, allowedMaxTotalFileNumber, allowedExtdiv, allowedEachFileSize, allowedTotalFileSize, uiType) {
//		objName 과 seq 는 jsp 내에서 유일 하여야 함.
//		memberProfileImage: 1
//		memberImage: 2
//		memberFile : 3
		
		var totalFileSize = 0;
		var obj = $("#" + objName +"")[0].files;	
		var fileCount = obj.length;
		
		allowedMaxTotalFileNumber = allowedMaxTotalFileNumber == 0 ? MAX_TOTAL_FILE_NUMBER : allowedMaxTotalFileNumber;
		allowedEachFileSize = allowedEachFileSize == 0 ? MAX_EACH_FILE_SIZE : allowedEachFileSize;
		allowedTotalFileSize = allowedTotalFileSize == 0 ? MAX_TOTAL_FILE_SIZE : allowedTotalFileSize;
		
		if(checkUploadedTotalFileNumber(obj, allowedMaxTotalFileNumber, fileCount) == false) { return false; }
		
		
		
/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@ 업로드된 파일 총 합 크기 체크  start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
		for (var i = 0 ; i < fileCount ; i++) {
			//확장자 체크,사이즈 체크를 하나, 확장자가 맞지 않으면 그 즉시 중지. 
			if(checkUploadedExt($("#" + objName +"")[0].files[i].name, seq, allowedExtdiv) == false) { return false; }
			//개별 사이즈 체크
			if(checkUploadedEachFileSize($("#" + objName +"")[0].files[i], seq, allowedEachFileSize) == false) { return false; }
			// 위 두가지 체크에 만족하면 totalFileSize에 연산. 
			totalFileSize += $("#" + objName +"")[0].files[i].size;
		}
		if(checkUploadedTotalFileSize(seq, totalFileSize, allowedTotalFileSize) == false) { return false; }
/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@ 업로드된 파일 총 합 크기 체크  end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
		if (uiType == 1) {
/* 			
			$("#ulFile" + seq).children().remove();
			
			for (var i = 0 ; i < fileCount ; i++) {
				addUploadLi(seq, i, $("#" + objName +"")[0].files[i].name);
			}
 */			
			for (var i = 0 ; i < fileCount ; i++) {
				
	 			var divImage = "";
	 			divImage += '<div style="display: inline-block; height: 95px;">';
				/* divImage += '	<img src="/resources/common/image/default_111.jpg" class="rounded" width= "85px" height="85px">'; */
				divImage += '	<img id="aaa'+i+'" src="" class="rounded" width= "85px" height="85px">';
				divImage += '	<div style="position: relative; top:-85px; left:5px"><span style="color: red;">X</span></div>';
				divImage += '</div> ';
				
				$("#ifmmUploadedImage1View").append(divImage);
				
				var fileReader = new FileReader();
				 fileReader.readAsDataURL($("#" + objName +"")[0].files[i]);
				alert($("#" + objName +"")[0].files[i]);
				 fileReader.onload = function () {
				 /* alert(i + " : " + fileReader.result); */
				 alert($("#aaa"+i+""));
				 
				 if(i == 0) {
					 $("#aaa0").attr("src", fileReader.result);		/* #-> */
				 } else if (i == 1) {
					 $("#aaa0").attr("src", fileReader.result);		/* #-> */
				 } else {
					 
				 }
					 /* $("#aaa"+i+"").attr("src", fileReader.result);		/* #-> */
					 /* $("#aaa1").attr("src", fileReader.result);		/* #-> */ 
				 }
			}			
 			
		} else if(uiType == 2) {
			$("#ulFile" + seq).children().remove();
			
			for (var i = 0 ; i < fileCount ; i++) {
				addUploadLi(seq, i, $("#" + objName +"")[0].files[i].name);
			}
		} else if (uiType == 3) {
			var fileReader = new FileReader();
			 fileReader.readAsDataURL($("#" + objName +"")[0].files[0]);
			
			 fileReader.onload = function () {
				 $("#imgProfile").attr("src", fileReader.result);		/* #-> */
			 }		
		} else {
			return false;
		}
		return false;
	}

//checkUP List

divSearchControl = function() {
    var screenWidth = $(window).width();
    if (screenWidth < BOOTSTRAP5_SM_DIMENSIONS) {
        $('#divSearch').hide();
        $('#iSearchControlUp').hide();
        $('#iSearchControlDown').show();
    } else {
        // by pass
    }
}
 
 
$('#divSearchControl').on('click', function() {
    if ($('#divSearch').is(':visible')) {
         $('#divSearch').hide();
         $('#iSearchControlUp').hide();
         $('#iSearchControlDown').show();
    } else {
         $('#divSearch').show();
         $('#iSearchControlUp').show();
         $('#iSearchControlDown').hide();
    }
 });


checkUploadedTotalFileNumber = function(obj, allowedMaxTotalFileNumber, fileCount) {
	if(allowedMaxTotalFileNumber < fileCount){
		alert("전체 파일 갯수는 "+ allowedMaxTotalFileNumber +"개 까지 허용됩니다.");
//		$("#file"+seq).val("");
//		obj.val("");
		return false;
	}
}

/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@ 확장자 체크 start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
checkUploadedExt = function(objName, seq, div) {
	var ext = objName.split('.').pop().toLowerCase(); //objName 이 들어왔을때 . 이하 split 그리고 소문자로 변환. 
	var extArray = eval("extArray" + div);
		//extArray 배열을 불러서 체크.
	if(extArray.indexOf(ext) == -1) {
		alert("허용된 확장자가 아닙니다.");
//		$("#file"+seq).val("");
		return false;
	}
}
/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@ 확장자 체크 end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/

checkUploadedEachFileSize = function(obj, seq, allowedEachFileSize) {

	if(obj.size > allowedEachFileSize){
		alert("각 첨부 파일 사이즈는 "+kbToMb(allowedEachFileSize)+"MB 이내로 등록 가능합니다.");
		$("#file"+seq).val("");
		return false;
	}
}


checkUploadedTotalFileSize = function(seq, totalSize, allowedTotalFileSize) {
	if(totalSize > allowedTotalFileSize){
		alert("전체 용량은 "+kbToMb(allowedTotalFileSize)+"M를 넘을 수 없습니다.");
		$("#file"+seq).val("");
		return false;
	}
}


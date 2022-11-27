$(document).ready(function(){
	
	getUserBal(idTokenKko);
	
	
	
	//orderContainer
	var navItem = $('.navItem')
	
	navItem.click(function(){
		$(this).addClass('active');
		navItem.not($(this)).removeClass('active');
	})

	//네비게이션 페이지네이션
	$('#oderContainer').scrollTop(350);
	$('#orderContaineritem2').hide()
	$('#orderContaineritem2_1').hide();
	$('#orderContaineritem3').hide()
	$('#orderContaineritem3_1').hide();
	
	
	$('#orderContainerPrice').click(function(){
		
		$('#orderContaineritem1').show();
		$('#orderContaineritem1_1').show();
		$('#orderContaineritem2').hide();
		$('#orderContaineritem2_1').hide();
		$('#orderContaineritem3').hide();
		$('#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderPrice').click(function(){
		
		$('#orderContaineritem1').hide();
		$('#orderContaineritem1_1').hide();
		$('#orderContaineritem2').show();
		$('#orderContaineritem2_1').show();
		$('#orderContaineritem3').hide();
		$('#orderContaineritem3_1').hide();
	})
	
	$('#orderContainerOrderNavigation').click(function(){
		
		$('#orderContaineritem1').hide();
		$('#orderContaineritem1_1').hide();
		$('#orderContaineritem2').hide();
		$('#orderContaineritem2_1').hide();
		$('#orderContaineritem3').show();
		$('#orderContaineritem3_1').show();
	})
	
	//bosBox
	$('.nav2_1').click(function(){
		$('.nav2_1').addClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').show();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_2').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').addClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').show();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_3').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').addClass('active2');
		$('.nav2_4').removeClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').show();
		$('#orderBoxBody4').hide();
	})
	
	$('.nav2_4').click(function(){
		$('.nav2_1').removeClass('active2Buy');
		$('.nav2_2').removeClass('active2Sell');
		$('.nav2_3').removeClass('active2');
		$('.nav2_4').addClass('active2');
		
		$('#orderBoxBody1').hide();		
		$('#orderBoxBody2').hide();
		$('#orderBoxBody3').hide();
		$('#orderBoxBody4').show();
	})
	
	
	
	
		
	
	//수량가격 체인지
	$('#amountToggle01').click(function(){
		var amountToggle = $('#amountToggle01').val()
		console.log(amountToggle)
		
		if(amountToggle == '1'){
			$('#amountToggle01').html(
				'<b>'+'수량'+'</b>' +
				'<em>'+
				'(BTC)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'
			)
			var amountToggle = $('#amountToggle01').val('2')
		}else{
			$('#amountToggle01').html(
				'<b>'+'총액'+'</b>' +
				'<em>'+
				'(KRW)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'			
			)
			var amountToggle = $('#amountToggle01').val('1')
		}
	})
	$('#amountToggle02').click(function(){
		var amountToggle = $('#amountToggle02').val()
		console.log(amountToggle)
		
		if(amountToggle == '1'){
			$('#amountToggle02').html(
				'<b>'+'수량'+'</b>' +
				'<em>'+
				'(BTC)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'
			)
			var amountToggle = $('#amountToggle02').val('2')
		}else{
			$('#amountToggle02').html(
				'<b>'+'총액'+'</b>' +
				'<em>'+
				'(KRW)'+
				'<em>'+
				'<i class="fa-solid fa-right-left" style="margin-left:5px; color:#333;"></i>'			
			)
			var amountToggle = $('#amountToggle02').val('1')
		}
	})
});

function getUserBal(idTokenKko){
	var idTokenKko = $('#idTokenKko').val()
	var cryptoSeq = $('#cryptoSeq').val()
	
	var html ="";
	html += '<div class="pleaseLogin">'
	html += '<a id="kkoadduser" class="pleaseLoginBtn" href="http://127.0.0.1:8082/userLoginkko">로그인</a>'
	html += '</div>'
	
	
	if(idTokenKko == ""){
		$('.orderBoxBody').html(html)
	}else{
		html = null;
	}
	//userBalance
	if(idTokenKko != ""){
		console.log("idTokenKko session :: " +idTokenKko);
		$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/userBalance"
			,data:{
				"idTokenKko" : idTokenKko
				,"cryptoSeq" : cryptoSeq
				}
			,success: function(userBalance){
				console.log(userBalance)
				if(userBalance.balance == null || userBalance.balance == 0){
					$('.userCashBal').text("잔고 없음")
				}else{
					$('.userCashBal').val(userBalance.balance)
					$('.userCashBal').text(userBalance.balance)
					$('#memberSeq').val(userBalance.memberSeq)
					$('#cryptoSeq').val(userBalance.cryptoSeq)
				}
				
				
			}
			,error : function(err){
				alert("노노")
			}
			
		})
	}else{
		console.log("no idTokenKko session :: " +idTokenKko);
	}
}


function getOnLoadInfo(){
	$.ajax({
			async:true
			,cache:false
			,type:"post"
			,url:"/exchange/userBalance"
			,data:{"idTokenKko" : idTokenKko}
			,success: function(userBalance){
				console.log(userBalance)
				$('#cryptoSeq').val(userBalance.cryptoSeq)
			}
			,error : function(err){
				alert("getOnLoadInfo userBalance :: err")
			}
			
		})
	
	
}
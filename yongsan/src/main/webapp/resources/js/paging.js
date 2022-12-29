function goLoginkko(){
	event.preventDefault();
	location.href='./login'
}

let logoLink = document.getElementById('logo-link')
logoLink.addEventListener('click',()=>{
	goHome()
})

let logoLink2 = document.getElementById('logo-link2')
logoLink2.addEventListener('click',()=>{
	goHome()
})
function goHome(){
	event.preventDefault();
	location.href='/'
}
let userLogin = document.getElementById('userLogin');
userLogin.addEventListener('click',()=>{
	goLogin()
});
function goLogin(){
	event.preventDefault();
	location.href='/userLogin'
}
let userSignIn = document.getElementById('userSignIn');
userSignIn.addEventListener('click',()=>{
	goUserSignIn()
});
function goUserSignIn(){
	event.preventDefault();
	location.href='/adduserkko'
}
let exchange = document.getElementById('exchange');
exchange.addEventListener('click',()=>{
	goExchange()
});
function goExchange(){
	event.preventDefault();
	location.href='/exchange/BTC'
}


let investments = document.getElementById('investments');
investments.addEventListener('click',()=>{
	goInvestments()
})
function goInvestments(){
	event.preventDefault();
	location.href='/investments/balance'
}
function goHistory(){
	event.preventDefault();
	location.href='/investments/history'
}
function goWait_orders(){
	event.preventDefault();
	location.href='/investments/wait_orders'
}



let helpcenter = document.getElementById('helpcenter');
helpcenter.addEventListener('click',()=>{
	goHelpcenter()
})

function goHelpcenter(){
	event.preventDefault();
	location.href='/helpcenter/press'
}
function goHelpcenterBoard(){
	event.preventDefault();
	location.href='/helpcenter/board'
}
function goHelpcenterArticles(){
	event.preventDefault();
	location.href='/helpcenter/articles'
}
function goHelpcenterBoardWrite(){
	event.preventDefault();
	location.href='/helpcenter/board_write'
}







function adminPage(){
	event.preventDefault();
	location.href='/yongsancode/roothome';
}
let adminPageBtn = document.getElementById('adminPage');
adminPageBtn.addEventListener('click',adminPage)

function goAddUser(){
	event.preventDefault();
	location.href='/adduser'
}
function goAddUserKko(){
	event.preventDefault();
	location.href='/adduserkko'
}
function goExchange(){
	event.preventDefault();
	location.href='/exchange/BTC'
}

function goTradeHis(){
	event.preventDefault();
	location.href='/investments/'
}

function userOrderHistory(){
	event.preventDefault();
	location.href='/investments/userOrderHistory/userOrderHistory_hitory'
}
function userOrderHistory_hitory(){
	event.preventDefault();
	location.href='/investments/userOrderHistory/userOrderHistory_hitory'
}
function userOrderHistory_wait_order(){
	event.preventDefault();
	location.href='/investments/userOrderHistory/userOrderHistory_wait_order'
}




function goHelpcenterOpenKko(){
	event.preventDefault();
	let link="https://open.kakao.com/o/s33aKdqe"
	window.open(link);
}




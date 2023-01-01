
function goThisBd(seq){
	location.href="/helpcenter/board/bdView/"+seq;
}


function cmtWrite(e){
	let memberSeq = $('#memberSeq').val()
	let bdSeq = $(e).val()
	let text = $('#cmTextArea').val()
	console.log(memberSeq)
	console.log($(e).val())
	console.log(text)
	$.ajax({
		url:'/helpcenter/board/commnet/write'
		,method:'post'
		,data:{
			memberSeq:memberSeq,
			bdSeq:bdSeq,
			text:$('#cmTextArea').val()
		},success:function(e){
			console.log(e)
			
			let html=`
				<div class="cmtDiv" id="`+e.cmtSeq+`">
					<span>
					<strong>`+e.memberNickname+`</strong>
					</span>
					<div class="textDiv" id="`+e.memberSeq+`">
						<div>
							`+e.text+`
						</div>
						<div>
							<button value="`+e.cmtSeq+`" onclick="cmtDel(this)"><i class="fa-solid fa-xmark"></i></button>
						</div>
					</div>
					<i>`+new Date().toLocaleDateString()+`</i>
				</div>`	
				
			$('#commentTable').prepend(html)
		}
	})
}

function cmtDel(e){
	let cmtSeq = $(e).val()
	console.log($(e).val())
	$.ajax({
		url:'/helpcenter/board/commnet/delete'
		,method:'post'
		,data:{
			cmtSeq: cmtSeq
		}

		,success:function(e){
			$('.cmtDiv#'+e).remove();
		},error:function(){
			
		}
	})
}


let bdDelBtn = document.getElementById('bdDelBtn');
let inpChkd = document.getElementsByName('bdChk');
let inpChkAll = document.getElementById('chkAll');
let bool = false;
inpChkAll.addEventListener('click', function(){
	if(!bool){
		bool = true;
		for(let i = 0 ; i < inpChkd.length;i++){
		inpChkd[i].checked = true;
		}
	}else{
		bool=false;
		for(let i = 0 ; i < inpChkd.length;i++){
		inpChkd[i].checked = false;
		}
	}
	
})

bdDelBtn.addEventListener('click',()=>{
	let arr =[]
	for(let i = 0 ; i < inpChkd.length;i++){
		let is_chked = inpChkd[i].checked
		if(is_chked){
			
			let data = {bdSeq : inpChkd[i].value};
			arr.push(data)
		}
	}
	console.log(arr)
	$.ajax({
		url:'/helpcenter/board/delete'
		,traditional: true
		,method:'post'
		,data:{bdSeq:JSON.stringify(arr)}
		,dataType:'json'
		,success:function(e){
			for(let i = 0 ; i < arr.length;i++){
				let seq = arr[i].bdSeq
				
				$('#bd'+seq).remove()
			}		
		},error:function(e){
			console.log(e)
		}
	})
})
function goThisBdUpt(e){
	let val = e
	console.log(val)
	location.href='/helpcenter/board/bdUpdate/'+val
}

function goThisBd(e){
	let val = e
	console.log(val)
	location.href='/helpcenter/board/bdView/'+val
}
package com.seoul.infra.fb;
//본인에게 맞는 package로 변경하도록 합니다.

public class Chat {
	
	private Integer chatSeq;
	private Integer chatDelNy;
	private Integer cuMember;
	private String chatCreateDate;
	
	//채팅 맴버 정보 추출을 위한 필드
	//아래 필드도 본인의 DB테이블에 맞게 변경해주세요
	private Integer memberSeq;       //유저 시퀀스
	private String memberNickName;   //유저 이름 or 닉네임
	
	public Integer getChatSeq() {
		return chatSeq;
	}
	public void setChatSeq(Integer chatSeq) {
		this.chatSeq = chatSeq;
	}
	public Integer getChatDelNy() {
		return chatDelNy;
	}
	public void setChatDelNy(Integer chatDelNy) {
		this.chatDelNy = chatDelNy;
	}
	public Integer getCuMember() {
		return cuMember;
	}
	public void setCuMember(Integer cuMember) {
		this.cuMember = cuMember;
	}
	public String getChatCreateDate() {
		return chatCreateDate;
	}
	public void setChatCreateDate(String chatCreateDate) {
		this.chatCreateDate = chatCreateDate;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	
	
	
	
}

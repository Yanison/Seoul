package com.seoul.infra.modules.membergroup;

import java.util.Date;

public class MemberGroup extends MemberGroupVO {
	
	private Integer memberSeq;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private Integer memberGender;
	private String memberAddrLoad;
	private String memberAddrJibun;
	private String addrDetail;
	private String postCode;
	private String addrLat;
	private String addrLong;
	private String memberTel;
	private String memberEmail;
	private Date regDateAt;
	private String regDateBy;
	private Date modDateAt;
	private String modDateBy;
	private Integer activeNy;
	private Integer autoLoginNy;
	private Integer joinThrou;
	private String idTokenKko;
	
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public Integer getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(Integer memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberAddrLoad() {
		return memberAddrLoad;
	}
	public void setMemberAddrLoad(String memberAddrLoad) {
		this.memberAddrLoad = memberAddrLoad;
	}
	public String getMemberAddrJibun() {
		return memberAddrJibun;
	}
	public void setMemberAddrJibun(String memberAddrJibun) {
		this.memberAddrJibun = memberAddrJibun;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddrLat() {
		return addrLat;
	}
	public void setAddrLat(String addrLat) {
		this.addrLat = addrLat;
	}
	public String getAddrLong() {
		return addrLong;
	}
	public void setAddrLong(String addrLong) {
		this.addrLong = addrLong;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Date getRegDateAt() {
		return regDateAt;
	}
	public void setRegDateAt(Date regDateAt) {
		this.regDateAt = regDateAt;
	}
	public String getRegDateBy() {
		return regDateBy;
	}
	public void setRegDateBy(String regDateBy) {
		this.regDateBy = regDateBy;
	}
	public Date getModDateAt() {
		return modDateAt;
	}
	public void setModDateAt(Date modDateAt) {
		this.modDateAt = modDateAt;
	}
	public String getModDateBy() {
		return modDateBy;
	}
	public void setModDateBy(String modDateBy) {
		this.modDateBy = modDateBy;
	}
	public Integer getActiveNy() {
		return activeNy;
	}
	public void setActiveNy(Integer activeNy) {
		this.activeNy = activeNy;
	}
	public Integer getAutoLoginNy() {
		return autoLoginNy;
	}
	public void setAutoLoginNy(Integer autoLoginNy) {
		this.autoLoginNy = autoLoginNy;
	}
	public Integer getJoinThrou() {
		return joinThrou;
	}
	public void setJoinThrou(Integer joinThrou) {
		this.joinThrou = joinThrou;
	}
	
	public String getIdTokenKko() {
		return idTokenKko;
	}
	public void setIdTokenKko(String idTokenKko) {
		this.idTokenKko = idTokenKko;
	}



	private String memberEmailId;
	private String EmailDM;


	public String getMemberEmailId() {
		return memberEmailId;
	}
	public void setMemberEmailId(String memberEmailId) {
		this.memberEmailId = memberEmailId;
	}
	public String getEmailDM() {
		return EmailDM;
	}
	public void setEmailDM(String emailDM) {
		EmailDM = emailDM;
	}
	
	
	
	
	
	
}

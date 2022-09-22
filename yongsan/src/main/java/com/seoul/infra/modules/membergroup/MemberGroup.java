package com.seoul.infra.modules.membergroup;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class MemberGroup extends MemberGroupVO {
	
	private Integer memberSeq;
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private Integer gender;
	private String addrType1;
	private String addrType2;
	private String addrDetail;
	private String tel;
	private String email;
	private Date regDateAt;
	private String regDateBy;
	private Date ModDateAt;
	private String ModDateBy;
	
	private String postCode;
	private String addrLoca;
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddrLoca() {
		return addrLoca;
	}
	public void setAddrLoca(String addrLoca) {
		this.addrLoca = addrLoca;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddrType1() {
		return addrType1;
	}
	public void setAddrType1(String addrType1) {
		this.addrType1 = addrType1;
	}
	public String getAddrType2() {
		return addrType2;
	}
	public void setAddrType2(String addrType2) {
		this.addrType2 = addrType2;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return ModDateAt;
	}
	public void setModDateAt(Date modDateAt) {
		ModDateAt = modDateAt;
	}
	public String getModDateBy() {
		return ModDateBy;
	}
	public void setModDateBy(String modDateBy) {
		ModDateBy = modDateBy;
	}
}

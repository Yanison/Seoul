package com.seoul.infra.modules.membergroup;

import java.sql.Date;

public class MemberGroupByKko {
	
	public Integer kmSeq;
	public String kmIdToken;
	public String kmBirthday;
	public Integer kmGender;
	public String kmNick;
	public String kmEmail;
	public Date regDateAt;
	public String regDateBy;
	public Date modDateAt;
	public String modDateBy;
	
	
	
	public String getKmIdToken() {
		return kmIdToken;
	}
	public void setKmIdToken(String kmIdToken) {
		this.kmIdToken = kmIdToken;
	}
	public String getKmBirthday() {
		return kmBirthday;
	}
	public void setKmBirthday(String kmBirthday) {
		this.kmBirthday = kmBirthday;
	}
	public Integer getKmGender() {
		return kmGender;
	}
	public void setKmGender(Integer kmGender) {
		this.kmGender = kmGender;
	}
	public Integer getKmSeq() {
		return kmSeq;
	}
	public void setKmSeq(Integer kmSeq) {
		this.kmSeq = kmSeq;
	}
	public String getKmNick() {
		return kmNick;
	}
	public void setKmNick(String kmNick) {
		this.kmNick = kmNick;
	}
	public String getKmEmail() {
		return kmEmail;
	}
	public void setKmEmail(String kmEmail) {
		this.kmEmail = kmEmail;
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
	
	
}

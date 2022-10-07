package com.seoul.infra.dto;

import java.util.Date;

public class Crypto {
	
	private Integer cryptoSeq;
	private String cryptoSym;
	private String cryptoName;
	private Integer activeNy;
	private Date regDateAt;
	private String regDateBy;
	private Date ModDateAt;
	private String ModDateBy;
	private Integer wodAvailable;
	
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public String getCryptoSym() {
		return cryptoSym;
	}
	public void setCryptoSym(String cryptoSym) {
		this.cryptoSym = cryptoSym;
	}
	public String getCryptoName() {
		return cryptoName;
	}
	public void setCryptoName(String cryptoName) {
		this.cryptoName = cryptoName;
	}
	public Integer getActiveNy() {
		return activeNy;
	}
	public void setActiveNy(Integer activeNy) {
		this.activeNy = activeNy;
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
	public Integer getWodAvailable() {
		return wodAvailable;
	}
	public void setWodAvailable(Integer wodAvailable) {
		this.wodAvailable = wodAvailable;
	}
	
	
	
}

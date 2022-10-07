package com.seoul.infra.modules.wod;

import java.math.BigDecimal;

public class WodDTO {
	private String idTokenKko;
	private String memberName;
	
	public String getIdTokenKko() {
		return idTokenKko;
	}
	public void setIdTokenKko(String idTokenKko) {
		this.idTokenKko = idTokenKko;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	private Integer exchangeBalanceSeq;
	private Integer cryptoSeq;
	private Integer memberSeq;
	private BigDecimal amount;
	private Integer wodAvailable;
	
	public Integer getExchangeBalanceSeq() {
		return exchangeBalanceSeq;
	}
	public void setExchangeBalanceSeq(Integer exchangeBalanceSeq) {
		this.exchangeBalanceSeq = exchangeBalanceSeq;
	}
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getWodAvailable() {
		return wodAvailable;
	}
	public void setWodAvailable(Integer wodAvailable) {
		this.wodAvailable = wodAvailable;
	}
	
	private String cryptoSym;
	private String cryptoName;

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
	
	

}

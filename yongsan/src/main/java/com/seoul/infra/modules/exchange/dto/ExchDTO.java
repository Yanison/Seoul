package com.seoul.infra.modules.exchange.dto;


import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.ExchangeDao;

public class ExchDTO {
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #memberInfo start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	private String idTokenKko;
	private String memberName;
	private Integer memberSeq;
	
	
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
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
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #memberInfo end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #cryptoInfo start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	private Integer cryptoSeq;
	private String cryptoSym;
	
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
	
	
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #cryptoInfo end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	

	

	private double amount;
	private double price;
	private double obAmount;
	private int orderType;
	private int bos;

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getObAmount() {
		return obAmount;
	}
	public void setObAmount(double obAmount) {
		this.obAmount = obAmount;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getBos() {
		return bos;
	}
	public void setBos(int bos) {
		this.bos = bos;
	}
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	
}

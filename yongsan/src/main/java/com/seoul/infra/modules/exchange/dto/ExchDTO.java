package com.seoul.infra.modules.exchange.dto;

import java.math.BigDecimal;

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
	
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #cryptoInfo end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	

	private BigDecimal amount;
	private BigDecimal price;
	private BigDecimal obAmount;

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getObAmount() {
		return obAmount;
	}
	public void setObAmount(BigDecimal obAmount) {
		this.obAmount = obAmount;
	}
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	
}

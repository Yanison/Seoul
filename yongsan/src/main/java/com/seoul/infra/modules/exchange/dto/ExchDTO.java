package com.seoul.infra.modules.exchange.dto;


import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderVo;

public class ExchDTO extends OrderVo {
	
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
	private String cryptoName;
	private String cryptoNameKor;
	
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
	public String getCryptoNameKor() {
		return cryptoNameKor;
	}
	public void setCryptoNameKor(String cryptoNameKor) {
		this.cryptoNameKor = cryptoNameKor;
	}
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #cryptoInfo end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	

	

	

	private double balance;
	private double price;
	private double obAmount;
	private int orderType;
	private int bos;
	private double cashBalance;
	private double pendingCash;
	private double cryptoBalance;
	private double pendingCrypto;
	private double availableCtpyto;
	private double availableCash;
	
	public double getAvailableCash() {
		return availableCash;
	}
	public void setAvailableCash(double availableCash) {
		this.availableCash = availableCash;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
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
	public double getCashBalance() {
		return cashBalance;
	}
	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}
	public double getPendingCash() {
		return pendingCash;
	}
	public void setPendingCash(double pendingCash) {
		this.pendingCash = pendingCash;
	}
	public double getCryptoBalance() {
		return cryptoBalance;
	}
	public void setCryptoBalance(double cryptoBalance) {
		this.cryptoBalance = cryptoBalance;
	}
	public double getPendingCrypto() {
		return pendingCrypto;
	}
	public void setPendingCrypto(double pendingCrypto) {
		this.pendingCrypto = pendingCrypto;
	}
	public double getAvailableCtpyto() {
		return availableCtpyto;
	}
	public void setAvailableCtpyto(double availableCtpyto) {
		this.availableCtpyto = availableCtpyto;
	}
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@ #ExchComponent end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	
	
}

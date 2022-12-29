package com.seoul.infra.modules.investments;

import java.util.Date;

import com.seoul.infra.common.basic.commonVO;

public class InvestmentsDto extends commonVO {
	
	/**
	 * basic Field
	 */
	private int cryptoSeq;
	private String cryptoName;
	private String cryptoSym;
    private int memberSeq;
	
    public int getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(int cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public String getCryptoName() {
		return cryptoName;
	}
	public void setCryptoName(String cryptoName) {
		this.cryptoName = cryptoName;
	}
	public String getCryptoSym() {
		return cryptoSym;
	}
	public void setCryptoSym(String cryptoSym) {
		this.cryptoSym = cryptoSym;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	
	/*
	 * TradeStatus
	 */
    private long balance;
    private long app; // 매수평균가
    private double  pp; //매수금액
    private long valuation; //평가금액
    private long pnl; //평가손익
    private long roi; // 수익률
    
	
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getApp() {
		return app;
	}
	public void setApp(long app) {
		this.app = app;
	}
	public double getPp() {
		return pp;
	}
	public void setPp(double pp) {
		this.pp = pp;
	}
	public long getValuation() {
		return valuation;
	}
	public void setValuation(long valuation) {
		this.valuation = valuation;
	}
	public long getPnl() {
		return pnl;
	}
	public void setPnl(long pnl) {
		this.pnl = pnl;
	}
	public long getRoi() {
		return roi;
	}
	public void setRoi(long roi) {
		this.roi = roi;
	}
	
	/*
	 * BalanceStatus
	 */
	private long cashBalance; //보유 원화
	private long totalAsset; // 총 자산
	private long pnlAvg; // 총 평가손익
	private long valuationAvg; // 총 평가 금액
	private long ppAvg; // 총 매수 금액
	private double roiAvg; // 총 평가 수익률

	public long getCashBalance() {
		return cashBalance;
	}
	public void setCashBalance(long cashBalance) {
		this.cashBalance = cashBalance;
	}
	public long getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(long totalAsset) {
		this.totalAsset = totalAsset;
	}
	public long getPnlAvg() {
		return pnlAvg;
	}
	public void setPnlAvg(long pnlAvg) {
		this.pnlAvg = pnlAvg;
	}
	public long getValuationAvg() {
		return valuationAvg;
	}
	public void setValuationAvg(long valuationAvg) {
		this.valuationAvg = valuationAvg;
	}
	public long getPpAvg() {
		return ppAvg;
	}
	public void setPpAvg(long ppAvg) {
		this.ppAvg = ppAvg;
	}
	public double getRoiAvg() {
		return roiAvg;
	}
	public void setRoiAvg(double roiAvg) {
		this.roiAvg = roiAvg;
	}
	/**
	 * chart
	 */
	
	private double holdingWeight;
	
	public double getHoldingWeight() {
		return holdingWeight;
	}
	public void setHoldingWeight(double asset) {
		double calc = (asset / this.totalAsset) * 100;
		this.holdingWeight = Math.round(calc / 100);
	}

	
	/**
	 * tradeHistory
	 */
	
	
	
	/**
	 * pendingHistory
	 */
	private String timestampMD;
	private String timestampR;
	private int obSeq;
	private String bos;
	private long price;
	private double orderAmount;
	private double obAmount;

	
	public String getTimestampMD() {
		return timestampMD;
	}
	public void setTimestampMD(String timestampMD) {
		this.timestampMD = timestampMD;
	}
	public String getTimestampR() {
		return timestampR;
	}
	public void setTimestampR(String timestampR) {
		this.timestampR = timestampR;
	}
	public int getObSeq() {
		return obSeq;
	}
	public void setObSeq(int obSeq) {
		this.obSeq = obSeq;
	}
	public String getBos() {
		return bos;
	}
	public void setBos(String bos) {
		this.bos = bos;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getObAmount() {
		return obAmount;
	}
	public void setObAmount(double obAmount) {
		this.obAmount = obAmount;
	}
	
	private String orderTimestampR;
	private String orderTimestampMD;

	public String getOrderTimestampR() {
		return orderTimestampR;
	}
	public void setOrderTimestampR(String orderTimestampR) {
		this.orderTimestampR = orderTimestampR;
	}
	public String getOrderTimestampMD() {
		return orderTimestampMD;
	}
	public void setOrderTimestampMD(String orderTimestampMD) {
		this.orderTimestampMD = orderTimestampMD;
	}
	
	private String memberNickname;

	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	
}

package com.seoul.infra.dto;

import java.util.Date;

public class Crypto {
	
	private Integer cryptoSeq;
	private String cryptoSym;
	private String cryptoName;
	private String cryptoNameKor;
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
	public String getCryptoNameKor() {
		return cryptoNameKor;
	}
	public void setCryptoNameKor(String cryptoNameKor) {
		this.cryptoNameKor = cryptoNameKor;
	}
	
	private double high24;			//most higher price within 24 hours
	private double low24;			//most lower price within 24 hours
	private double todayHigh24;		//most higher price within today
	private double todayLow24;		//most lower price within today
	private double volume24;		// traded anount within 24 hours
	private double cap24;			// traded sum of price within 24 hours
	private double recentPrice;		// last price traded	
	private double closingPrice;	// last price of last day
	private double ratioRe;			// up&down ratio comparing with last price
	private double ratioPre;
	private double spread;
	private double priceGap;
	private double ratio;
	private double amountRatio;

	public double getHigh24() {
		return high24;
	}
	public void setHigh24(double high24) {
		this.high24 = high24;
	}
	public double getLow24() {
		return low24;
	}
	public void setLow24(double low24) {
		this.low24 = low24;
	}
	public double getTodayHigh24() {
		return todayHigh24;
	}
	public void setTodayHigh24(double todayHigh24) {
		this.todayHigh24 = todayHigh24;
	}
	public double getTodayLow24() {
		return todayLow24;
	}
	public void setTodayLow24(double todayLow24) {
		this.todayLow24 = todayLow24;
	}
	public double getVolume24() {
		return volume24;
	}
	public void setVolume24(double volume24) {
		this.volume24 = volume24;
	}
	public double getCap24() {
		return cap24;
	}
	public void setCap24(double cap24) {
		this.cap24 = cap24;
	}
	public double getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(double recentPrice) {
		this.recentPrice = recentPrice;
	}
	public double getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}
	public double getRatioRe() {
		return ratioRe;
	}
	public void setRatioRe(double ratioRe) {
		this.ratioRe = ratioRe;
	}
	public double getRatioPre() {
		return ratioPre;
	}
	public void setRatioPre(double ratioPre) {
		this.ratioPre = ratioPre;
	}
	public double getSpread() {
		return spread;
	}
	public void setSpread(double spread) {
		this.spread = spread;
	}
	public double getPriceGap() {
		return priceGap;
	}
	public void setPriceGap(double priceGap) {
		this.priceGap = priceGap;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public double getAmountRatio() {
		return amountRatio;
	}
	public void setAmountRatio(double amountRatio) {
		this.amountRatio = amountRatio;
	}
	
	
	
}

package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.sql.Date;

public class Order extends OrderVo  {
	
	/*
	 * 주문자 정보
	 */
	private Integer memberSeq;
	/*
	 * 거래된 코인 정보
	 */
	private Integer cryptoSeq;
	
	/*
	 * 주문 수량
	 */
	private double obAmount;
	/*
	 * 주문 가격
	 */
	private double price;
	/*
	 * 주문정보
	 */
	private Integer obSeq;
	/*
	 * 주문 타입 Limit or Market
	 */
	private Integer orderType;
	/*
	 * 주문 타입 buy or sell
	 */
	private Integer bos;
	/*
	 * 주문 시간
	 */
	private Date timestamp;
	
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(final Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(final Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public double getObAmount() {
		return obAmount;
	}
	public void setObAmount(final double obAmount) {
		this.obAmount = obAmount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(final double price) {
		this.price = price;
	}
	public Integer getObSeq() {
		return obSeq;
	}
	public void setObSeq(final Integer obSeq) {
		this.obSeq = obSeq;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(final Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getBos() {
		return bos;
	}
	public void setBos(Integer bos) {
		this.bos = bos;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}
	
	/*
	 * 거래내역 정보 요소들
	 */
	private Integer transactionSeq;
	private Integer memberSeqSell;
	private Integer memberSeqBuy;
	private Integer obSeqSell;
	private Integer obSeqBuy;
	private Integer orderStatus;
	private Integer transactedType;
	private double amount;
	private String timestampMD;
	private String timestampR;
	
	
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
	public Integer getTransactionSeq() {
		return transactionSeq;
	}
	public void setTransactionSeq(Integer transactionseq) {
		this.transactionSeq = transactionseq;
	}
	public Integer getMemberSeqSell() {
		return memberSeqSell;
	}
	public void setMemberSeqSell(Integer memberSeqSell) {
		this.memberSeqSell = memberSeqSell;
	}
	public Integer getMemberSeqBuy() {
		return memberSeqBuy;
	}
	public void setMemberSeqBuy(Integer memberSeqBuy) {
		this.memberSeqBuy = memberSeqBuy;
	}
	public Integer getObSeqSell() {
		return obSeqSell;
	}

	public void setObSeqSell(Integer obSeqSell) {
		this.obSeqSell = obSeqSell;
	}

	public Integer getObSeqBuy() {
		return obSeqBuy;
	}

	public void setObSeqBuy(Integer obSeqBuy) {
		this.obSeqBuy = obSeqBuy;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getTransactedType() {
		return transactedType;
	}
	public void setTransactedType(Integer transactedType) {
		this.transactedType = transactedType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/*
	 * market table
	 */
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

	
	public double getAmountRatio() {
		return amountRatio;
	}
	public void setAmountRatio(double amountRatio) {
		this.amountRatio = amountRatio;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public double getPriceGap() {
		return priceGap;
	}
	public void setPriceGap(double priceGap) {
		this.priceGap = priceGap;
	}
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
	public double getSpread() {
		return spread;
	}
	public void setSpread(double spread) {
		this.spread = spread;
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



	/*
	 * transactionTable
	 */
	private double tradePrice;
	private double bPrice;
	private double sPrice;
	private double bAmount;
	private double sAmount;


	public double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public double getbPrice() {
		return bPrice;
	}
	public void setbPrice(double bPrice) {
		this.bPrice = bPrice;
	}
	public double getsPrice() {
		return sPrice;
	}
	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}
	public double getbAmount() {
		return bAmount;
	}
	public void setbAmount(double bAmount) {
		this.bAmount = bAmount;
	}
	public double getsAmount() {
		return sAmount;
	}
	public void setsAmount(double sAmount) {
		this.sAmount = sAmount;
	}
	
	private double balance;
	private double totalPrice;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//chartFeild
	private Date year;
	private Date date;
	private String duration;
	private String durationSub;
	private String y1;
	private String d1;
	private String w1;
	private String h4;
	private String h1;
	private String m30;
	private String m15;
	private String m10;
	private String m5;
	private String m1;
	private double high;
	private double low;
	private double startPrice;
	
	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDurationSub() {
		return durationSub;
	}
	public void setDurationSub(String durationSub) {
		this.durationSub = durationSub;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getY1() {
		return y1;
	}
	public void setY1(String y1) {
		this.y1 = y1;
	}
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getW1() {
		return w1;
	}
	public void setW1(String w1) {
		this.w1 = w1;
	}
	public String getH4() {
		return h4;
	}
	public void setH4(String h4) {
		this.h4 = h4;
	}
	public String getH1() {
		return h1;
	}
	public void setH1(String h1) {
		this.h1 = h1;
	}
	public String getM30() {
		return m30;
	}
	public void setM30(String m30) {
		this.m30 = m30;
	}
	public String getM15() {
		return m15;
	}
	public void setM15(String m15) {
		this.m15 = m15;
	}
	public String getM10() {
		return m10;
	}
	public void setM10(String m10) {
		this.m10 = m10;
	}
	public String getM5() {
		return m5;
	}
	public void setM5(String m5) {
		this.m5 = m5;
	}
	public String getM1() {
		return m1;
	}
	public void setM1(String m1) {
		this.m1 = m1;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	public void setOrders(Integer mmSeqBuy,Integer mmSeqSell,Integer obSeqBuy,Integer obSeqSell,double obAmount,double price) {
		setMemberSeqBuy(mmSeqBuy); //매수자
		setMemberSeqSell(mmSeqSell);// 매도자
		setObSeqBuy(obSeqBuy);// 매수 OB
		setObSeqSell(obSeqSell); // 매도 OB
		setObAmount(obAmount); // 소화될 수량
		setPrice(price); // 가격
	}
	
	private String cryptoName;
	private String cryptoNameKor;
	private String cryptoSym;


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
	
	private double gap;


	public double getGap() {
		return gap;
	}
	public void setGap(double gap) {
		this.gap = gap;
	}
	
	private Integer submittedCrypto;
	private Integer submittedBy;


	public Integer getSubmittedCrypto() {
		return submittedCrypto;
	}
	public void setSubmittedCrypto(Integer submittedCrypto) {
		this.submittedCrypto = submittedCrypto;
	}
	public Integer getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}
	
}


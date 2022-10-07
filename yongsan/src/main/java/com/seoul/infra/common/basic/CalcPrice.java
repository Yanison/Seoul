package com.seoul.infra.common.basic;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.dto.OderBookDto;

public class CalcPrice {
	@Autowired
	ExchangeDao dao;
	
//	private BigDecimal midPrice;
//	private BigDecimal spread;
//	private BigDecimal marketDepth;
//
//	public BigDecimal getMidPrice() {
//		return midPrice;
//	}
//	public void setMidPrice(BigDecimal midPrice,OderBookDto dto) {
//		
//		BigDecimal getBBO = dao.selectBBO(dto).getOrderPrice();
//		BigDecimal getSBO = dao.selectSBO(dto).getOrderPrice();
//		BigDecimal int_2 = new BigDecimal("2");
//		
//		midPrice = (getBBO.add(getSBO)).divide(int_2);
//		
//		
//		this.midPrice = midPrice;
//	}
//	public BigDecimal getSpread() {
//		return spread;
//	}
//	public void setSpread(BigDecimal spread,OderBookDto dto) {
//		
//		BigDecimal getBBO = dao.selectBBO(dto).getOrderPrice();
//		BigDecimal getSBO = dao.selectSBO(dto).getOrderPrice();
//		
//		spread = getSBO.subtract(getBBO);
//		
//		this.spread = spread;
//	}
//	public BigDecimal getMarketDepth() {
//		return marketDepth;
//	}
//	public void setMarketDepth(BigDecimal marketDepth) {
//		this.marketDepth = marketDepth;
//	}

}

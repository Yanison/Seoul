package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

public interface OrderBookService {
	
	public boolean processLimitBuy(Order order)throws Exception;
	public boolean processMarketBuy(Order order)throws Exception;
	public boolean processLimitSell(Order order)throws Exception;
	public boolean processMarketSell(Order order)throws Exception;
}

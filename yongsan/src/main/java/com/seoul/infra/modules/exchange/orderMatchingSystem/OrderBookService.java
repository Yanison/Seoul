package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

public interface OrderBookService {
	
	
	public double getSpread(Order order);
	public boolean processLimitBuy(Order order, List<Order> sellOrders)throws Exception;
	public boolean processMarketBuy(Order order, List<Order> sellOrders)throws Exception;
	public boolean processLimitSell(Order order,List<Order> buyOrders)throws Exception;
	public boolean processMarketSell(Order order,List<Order> buyOrders)throws Exception;
}

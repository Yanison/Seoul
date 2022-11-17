package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

public interface OrderBookService {
	
	
	public double getSpread(Order order);
	public void processLimitBuy(Order order, List<Order> sellOrders);
	public void processMarketBuy(Order order, List<Order> sellOrders);
	public void processLimitSell(Order order,List<Order> buyOrders);
	public void processMarketSell(Order order,List<Order> buyOrders);
}

package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.controller.ExchangeWSController;

@Service
public class OrderBook3 implements OrderBookService{
	@Autowired
	OrderMatchingSystemDao omsDao;
	@Autowired
	ExchangeDao exchDao;
	@Autowired
	ExchangeWSController exchangeController;
	@Autowired
	OrderMatching3 om;
	
	//spread값 구하기.

	//지정매수
	@Override
	public synchronized boolean processLimitBuy(Order paramOrder) throws Exception{
		
		// sellOrders :: 매도 주문 리스트
		loop:
		while(true) {
			List<Order> sellOrders = omsDao.selectOBListForMatching(paramOrder);
			int n = omsDao.selectOBListForMatching(paramOrder).size();
			if(n == 0) {break loop;}
			
			for(int i = 0 ; i < n;i ++) {
				int orderStatus = omsDao.selectOrderStatus(paramOrder);
				if(orderStatus == 3) {break loop;}
				//불러온 주문 리스트 중 하나. 
				final Order index0SO = sellOrders.get(i);
				//신규 지정매수 주문과 불러온 매도주문의 가격이 같으면 주문 매칭 시작. 
				if(paramOrder.getPrice() == index0SO.getPrice()){
					boolean resultMatcging = om.matchingProcessByAmount(paramOrder,index0SO);
					if(!resultMatcging) {
						System.out.println("processMarketSell resultMatcging :: " + resultMatcging);
						return false;
					}
				}
			}	
		}
		return true;
	}
	
	/*
	 * 시장매수
	 */
	@Override
	public synchronized boolean processMarketBuy(Order paramOrder)throws Exception {
		loop:
		while(true) {
			// sellOrders :: 매도 주문 리스트
			List<Order> sellOrders = omsDao.selectOBListForMatching(paramOrder);
			int n = omsDao.selectOBListForMatching(paramOrder).size();
			if(n == 0) {break loop;}
			
			for(int i = 0 ; i < n;i ++) {
				int orderStatus = omsDao.selectOrderStatus(paramOrder);
				if(orderStatus == 3) {break loop;}
				//불러온 주문 리스트 중 하나. 
				final Order index0SO = sellOrders.get(i);
				//시장매수는 본인 가격과 같거나 작은 주문만을 찾음. 불러온 매도주문의 가격이 같거나 크면 주문 매칭 시작. 
				
				boolean resultMatcging = om.matchingProcessBySum(paramOrder,index0SO);
				
				if(!resultMatcging) {
					System.out.println("processMarketBuy resultMatcging" + resultMatcging);
					return false;
				}
			}	
		}
		
		return true;
	}
	
	/*
	 * 지정매도
	 */
	@Override
	public synchronized boolean processLimitSell(Order paramOrder)throws Exception {
		
		loop:
		while(true) {
			// sellOrders :: 매도 주문 리스트
			List<Order> buyOrders = omsDao.selectOBListForMatching(paramOrder);
			int n = omsDao.selectOBListForMatching(paramOrder).size();
			if(n == 0) {break loop;}
			for(int i = 0 ; i < n;i ++) {
				int orderStatus = omsDao.selectOrderStatus(paramOrder);
				if(orderStatus == 3) {break loop;}
				//불러온 주문 리스트 중 하나. 
				final Order index0BO = buyOrders.get(i);
				//지정매도는 본인과 가격이 같은 주문만을 바라봄. 
				if(paramOrder.getPrice() == index0BO.getPrice()){
					boolean resultMatcging = om.matchingProcessByAmount(paramOrder,index0BO);
					if(!resultMatcging) {
						System.out.println("processLimitSell resultMatcging" + resultMatcging);
						return false;
					}
				}
			}	
		}
		return true;
	}
	/*
	 * 시장매도
	 */
	@Override
	public synchronized boolean processMarketSell(Order paramOrder)throws Exception {
		
		/**
		 * 시장매도는 
		 */
		int obseq = paramOrder.getObSeq();
		loop:
		while(true) {
			// sellOrders :: 매도 주문 리스트
			List<Order> buyOrders = omsDao.selectOBListForMatching(paramOrder);
			int n = omsDao.selectOBListForMatching(paramOrder).size();
			if(n == 0) {break loop;}
			for(int i = 0 ; i < n;i ++) {
				
				int orderStatus = omsDao.selectOrderStatus(paramOrder);
				if(orderStatus == 3) {break loop;}
				//불러온 주문 리스트 중 하나. 
				final Order index0BO = buyOrders.get(i);
				//지정매도는 본인과 가격이 같은 주문만을 바라봄. 
				if(paramOrder.getPrice() >= index0BO.getPrice()){
					boolean resultMatcging = om.matchingProcessBySum(paramOrder,index0BO);
					
					if(!resultMatcging) {
						System.out.println("processMarketSell resultMatcging" + resultMatcging);
						return false;
					}
				}
			}	
		}
		return true;
	}
}



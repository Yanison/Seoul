package com.seoul.infra.modules.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.OrderBook;

@Service
public class ExchangeServiceImpl implements ExchangeService {
	
	@Autowired
	ExchangeDao dao;

	@Autowired 
	OrderMatchingSystemDao omsDao;

	@Autowired 
	OrderBook orderbook;
	

	public ExchDTO getOnlaodInfo(ExchDTO dto) throws Exception{
		return dao.getOnlaodInfo(dto);
	};
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get userBalance into submitBidsBox
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public ExchDTO userBalance(ExchDTO dto) throws Exception{
		return dao.userBalance(dto);
	}

/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get OB Bids&Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public List<Order> selectBOB(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.selectBOB :: 매수주문을 모두 가져오는 메소드입니다.");
		return omsDao.selectBOB(dto);
	};
	@Override
	public Order selectBOBOne(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.selectBOB :: 최근 하나의 매수주문을 가져오는 메소드입니다.");
		return omsDao.selectBOBOne(dto);
	}
	
	@Override
	public List<Order> selectSOB(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.selectSOB :: 매도주문을 모두 가져오는 메소드입니다.");
		return omsDao.selectSOB(dto);
	};
	
	@Override
	public Order selectSOBOne(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.selectSOBOne :: 최근 하나의 매도주문을 가져오는 메소드입니다.");
		return omsDao.selectSOBOne(dto);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Bids & Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public int submitBids(ExchDTO dto) throws Exception{
		System.out.println("ExchangeServiceImpl.submitBids :: 매수주문을 신청합니다.");
		return dao.submitBids(dto);
	}
	@Override
	public int submitAsks(ExchDTO dto) throws Exception{
		System.out.println("ExchangeServiceImpl.submitAsks :: 매도주문을 신청합니다.");
		return dao.submitAsks(dto);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get OBList throu STOMP over SockJS
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	
	
	public void orderMatchingBuy(Order bobOne,List<Order> sellOrders) {
		
		System.out.println(
				"ExchangeServiceImpl.selectBOBOne :: 최근 매수주문 내역 하나를 불러옵니다."
				+ "dto.getBos():: "+bobOne.getBos()+"\n" + ""
				);
		
		System.out.println(
				"최근 매수 주문의 주문 타입을 판별합니다."+ "\n"
				+ " 0이면 지정매수, 1이면 시장매수 입니다." + "\n"
				+ "bobOne.getOrderType():: "+bobOne.getOrderType()+"\n" + ""
				);
		if(bobOne.getOrderType() == 0) {
			System.out.println(
					"들어온 매수주문이 0(지정매수)인 경우입니다. bobOne.getOrderType() == 0 :: " + bobOne.getOrderType() + "\n" +
					"지정매수 메소드를 실행합니다. " + "\n"
					+ "매게변수는 bobOne과 sellOrders 입니다."+ "\n"
					);
			orderbook.processLimitBuy(bobOne, sellOrders);
		}else if(bobOne.getOrderType() == 1) {
			System.out.println(
					"들어온 주문이 1(시장매수)인경우 입니다.  bobOne.getOrderType() == 1 :: " + bobOne.getOrderType() + "\n" +
					"시장매수 메소드를 실행합니다." + "\n"
					+ "매게변수는 bobOne과 sellOrders 입니다."+ "\n"
					);
			orderbook.processMarketBuy(bobOne,sellOrders);
		}
	}
	public void orderMatchingSell(Order sobOne,List<Order> buyOrders) {
		
		System.out.println(
				"ExchangeServiceImpl.selectSOBOne :: 최근 매도주문 내역 하나를 불러옵니다."
				+ "dto.getBos():: "+sobOne.getBos()+"\n" + ""
				);
		
		System.out.println(
				"최근 매도주문의 주문 타입을 판별합니다."+ "\n"
				+ " 0이면 지정매수, 1이면 시장매수 입니다."+ "\n"
				+ "sobOne.getOrderType():: "+sobOne.getOrderType()+"\n" + ""
				);
		if(sobOne.getOrderType() == 0) {
			System.out.println(
					"들어온 매도주문이 0(지정매도)인 경우입니다. sobOne.getOrderType() == 0 :: " + sobOne.getOrderType() + "\n" +
					"지정매도 메소드를 실행합니다. " + "\n"
					+ "매게변수는 sobOne과 buyOrders 입니다."+ "\n"
					);
			orderbook.processLimitBuy(sobOne, buyOrders);
			
		}else if(sobOne.getOrderType() == 1) {
			System.out.println(
					"들어온 주문이 1(시장매도)인경우 입니다.  sobOne.getOrderType() == 1 :: " + sobOne.getOrderType() + "\n" +
					"시장매도 메소드를 실행합니다." + "\n"
					+ "매게변수는 sobOne과 buyOrders 입니다."+ "\n"
					);
			orderbook.processMarketBuy(sobOne,buyOrders);
		}
	}
	
	
}

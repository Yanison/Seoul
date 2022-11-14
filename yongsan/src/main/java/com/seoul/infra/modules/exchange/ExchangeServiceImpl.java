package com.seoul.infra.modules.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderBook;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

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
 * @@@@@@ select OB Bids&Asks
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
 * @@@@@@ selectTransacton
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public List<Order> selectTransacton(Order order)throws Exception{
		System.out.println("ExchangeServiceImpl.selectTransacton :: 최근 거래내역을 가져옵니다.");
		return omsDao.selectTransacton(order);
	}
	
	
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Bids & Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public int submitBids(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.submitBids :: 매수주문을 신청합니다.");
		return omsDao.submitBids(dto);
	}
	@Override
	public int submitAsks(Order dto) throws Exception{
		System.out.println("ExchangeServiceImpl.submitAsks :: 매도주문을 신청합니다.");
		return omsDao.submitAsks(dto);
	}
	
	
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ order Matching Logic start
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public void orderMatchingBuy(Order bobOne,List<Order> sellOrders)throws Exception {
		
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
	
	@Override
	public void orderMatchingSell(Order sobOne,List<Order> buyOrders)throws Exception {
		
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

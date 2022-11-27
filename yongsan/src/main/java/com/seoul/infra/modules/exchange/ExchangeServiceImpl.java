package com.seoul.infra.modules.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.exchange.controller.ExchangeWSController;
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
	
	@Autowired
	ExchangeWSController exchangeWSController;
	

	public ExchDTO getOnlaodInfo(ExchDTO dto) throws Exception{
		return dao.getOnlaodInfo(dto);
	};
	public ExchDTO selectCrpytoOne(ExchDTO dto) throws Exception{
		return dao.selectCrpytoOne(dto);
	}
	public List<Crypto> selectCryptoList(Crypto crypto)  throws Exception{
		return dao.selectCryptoList(crypto);
	}
	
	
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ User
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public ExchDTO userBalance(ExchDTO dto) throws Exception{
		return dao.userBalance(dto);
	}
	@Override
	public int selectUserBalance(Order order)throws Exception{
		return dao.selectUserBalance(order);
	}
	@Override
	public int updateUserBalance(Order order)throws Exception{
		return dao.updateUserBalance(order);
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
	
	@Override
	public Integer selectOrderStatus(Order order) throws Exception{
		System.out.println("ExchangeServiceImpl.selectAmount :: 주문번호 "+order.getObSeq()+"의 매도주문을 가져오는 메소드입니다.");
		return omsDao.selectOrderStatus(order);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ selectTransacton
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public List<Order> selectTransacton(Order order)throws Exception{
		System.out.println("ExchangeServiceImpl.selectTransacton :: 거래내역 목록을 가져옵니다.");
		return omsDao.selectTransacton(order);
	}
	@Override
	public Order transactionTable(Order order) throws Exception{
		
		return omsDao.transactionTable(order);
	}
	@Override
	public Order marketTable(Order order) throws Exception{
		
		
		return omsDao.marketTable(order);
	}
	@Override
	public List<Order> spread(Order order) throws Exception{
		
		return omsDao.spread(order);
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
			if(orderbook.processLimitBuy(bobOne, sellOrders) == true) {
				try {
					
					exchangeWSController.transactionTable(bobOne);
					System.out.println("ExchangeServiceImpl.completeOrder :: transactionTable() 정보를 갱신합니다."+ "\n" +
							"거래번호 :: "+ bobOne.getTransactionSeq()+ "\n" +
							"거래 통화 :: " + bobOne.getCryptoSeq()+ "\n" +
							"매수자 :: " +bobOne.getMemberSeqSell()+ "\n" +
							"매수번호 :: " + bobOne.getObSeqBuy()+ "\n" +
							"매도번호 :: " + bobOne.getObSeqSell()+ "\n" +
							"매도자 :: " +bobOne.getMemberSeqBuy()+ "\n" +
							"거래타입 :: "+ bobOne.getTransactedType()+ "\n" +
							"거래수량 :: " + bobOne.getAmount()+ "\n" +
							"거래가격 :: " + bobOne.getbPrice()+ "\n" +
							"거래 시간 :: " + bobOne.getTimestamp()+ "\n" +
							"전일대비 :: " + bobOne.getRatioPre()+ "\n" +
							"직전대비 :: " + bobOne.getRatioRe()
							);
					exchangeWSController.marketTable(bobOne);
					System.out.println("ExchangeServiceImpl.marketTable :: 화폐의 시세정보 테이블을 갱신합니다." + "\n" +
							"코인 :: " +bobOne.getCryptoSeq()+ "\n" +
							"최근 24시간 고가 :: " + bobOne.getHigh24()+ "\n" +
							"최근 24시간 저가 :: " + bobOne.getLow24()+ "\n" +
							"최근 24시간 거래량 :: " + bobOne.getVolume24()+ "\n" +
							"최근 24시간 거래대금 :: " + bobOne.getCap24()+ "\n" +
							"최근 거래가격 :: " + bobOne.getRecentPrice()+ "\n" +
							"전일 종가 :: " + bobOne.getClosingPrice() + "\n" +
							"전일대비 상승비율 :: "+ bobOne.getRatioPre()
							);
					exchangeWSController.spread(bobOne);
					System.out.println("ExchangeServiceImpl.spread :: spread 정보를 가져옵니다." + "\n" +
							"매수자 // 매수번호 :: " +bobOne.getMemberSeqBuy()+ " // " + bobOne.getObSeqBuy()+ "\n" +
							"매수가격 :: "+bobOne.getbPrice()+ "\n" +
							"매도자 // 매도번호 :: " + bobOne.getMemberSeqSell() + " // " + bobOne.getObSeqSell()+ "\n" +
							"매도가격 :: "+bobOne.getsPrice()+ "\n" +
							"spread :: "+bobOne.getSpread()
						);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(bobOne.getOrderType() == 1) {
			System.out.println(
					"들어온 주문이 1(시장매수)인경우 입니다.  bobOne.getOrderType() == 1 :: " + bobOne.getOrderType() + "\n" +
					"시장매수 메소드를 실행합니다." + "\n"
					+ "매게변수는 bobOne과 sellOrders 입니다."+ "\n"
					);
			
			if( orderbook.processMarketBuy(bobOne,sellOrders) == true) {
				try {
					
					
					exchangeWSController.transactionTable(bobOne);
					System.out.println("ExchangeServiceImpl.completeOrder :: transactionTable() 정보를 갱신합니다."+ "\n" +
							"거래번호 :: "+ bobOne.getTransactionSeq()+ "\n" +
							"거래 통화 :: " + bobOne.getCryptoSeq()+ "\n" +
							"매수자 :: " +bobOne.getMemberSeqSell()+ "\n" +
							"매수번호 :: " + bobOne.getObSeqBuy()+ "\n" +
							"매도번호 :: " + bobOne.getObSeqSell()+ "\n" +
							"매도자 :: " +bobOne.getMemberSeqBuy()+ "\n" +
							"거래타입 :: "+ bobOne.getTransactedType()+ "\n" +
							"거래수량 :: " + bobOne.getAmount()+ "\n" +
							"거래가격 :: " + bobOne.getbPrice()+ "\n" +
							"거래 시간 :: " + bobOne.getTimestamp()+ "\n" +
							"전일대비 :: " + bobOne.getRatioPre()+ "\n" +
							"직전대비 :: " + bobOne.getRatioRe()
							);
					exchangeWSController.marketTable(bobOne);
					System.out.println("ExchangeServiceImpl.marketTable :: 화폐의 시세정보 테이블을 갱신합니다." + "\n" +
							"코인 :: " +bobOne.getCryptoSeq()+ "\n" +
							"최근 24시간 고가 :: " + bobOne.getHigh24()+ "\n" +
							"최근 24시간 저가 :: " + bobOne.getLow24()+ "\n" +
							"최근 24시간 거래량 :: " + bobOne.getVolume24()+ "\n" +
							"최근 24시간 거래대금 :: " + bobOne.getCap24()+ "\n" +
							"최근 거래가격 :: " + bobOne.getRecentPrice()+ "\n" +
							"전일 종가 :: " + bobOne.getClosingPrice() + "\n" +
							"전일대비 상승비율 :: "+ bobOne.getRatioPre()
							);
					exchangeWSController.spread(bobOne);
					System.out.println("ExchangeServiceImpl.spread :: spread 정보를 가져옵니다." + "\n" +
							"매수자 // 매수번호 :: " +bobOne.getMemberSeqBuy()+ " // " + bobOne.getObSeqBuy()+ "\n" +
							"매수가격 :: "+bobOne.getbPrice()+ "\n" +
							"매도자 // 매도번호 :: " + bobOne.getMemberSeqSell() + " // " + bobOne.getObSeqSell()+ "\n" +
							"매도가격 :: "+bobOne.getsPrice()+ "\n" +
							"spread :: "+bobOne.getSpread()
						);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
			
			if( orderbook.processLimitBuy(sobOne, buyOrders) == true) {
				
				exchangeWSController.transactionTable(sobOne);
				System.out.println("ExchangeServiceImpl.completeOrder :: transactionTable() 정보를 갱신합니다."+ "\n" +
						"거래번호 :: "+ sobOne.getTransactionSeq()+ "\n" +
						"거래 통화 :: " + sobOne.getCryptoSeq()+ "\n" +
						"매수자 :: " +sobOne.getMemberSeqSell()+ "\n" +
						"매수번호 :: " + sobOne.getObSeqBuy()+ "\n" +
						"매도번호 :: " + sobOne.getObSeqSell()+ "\n" +
						"매도자 :: " +sobOne.getMemberSeqBuy()+ "\n" +
						"거래타입 :: "+ sobOne.getTransactedType()+ "\n" +
						"거래수량 :: " + sobOne.getAmount()+ "\n" +
						"거래가격 :: " + sobOne.getbPrice()+ "\n" +
						"거래 시간 :: " + sobOne.getTimestamp()+ "\n" +
						"전일대비 :: " + sobOne.getRatioPre()+ "\n" +
						"직전대비 :: " + sobOne.getRatioRe()
						);
				exchangeWSController.marketTable(sobOne);
				System.out.println("ExchangeServiceImpl.marketTable :: 화폐의 시세정보 테이블을 갱신합니다." + "\n" +
						"코인 :: " +sobOne.getCryptoSeq()+ "\n" +
						"최근 24시간 고가 :: " + sobOne.getHigh24()+ "\n" +
						"최근 24시간 저가 :: " + sobOne.getLow24()+ "\n" +
						"최근 24시간 거래량 :: " + sobOne.getVolume24()+ "\n" +
						"최근 24시간 거래대금 :: " + sobOne.getCap24()+ "\n" +
						"최근 거래가격 :: " + sobOne.getRecentPrice()+ "\n" +
						"전일 종가 :: " + sobOne.getClosingPrice() + "\n" +
						"전일대비 상승비율 :: "+ sobOne.getRatioPre()
						);
				exchangeWSController.spread(sobOne);
				System.out.println("ExchangeServiceImpl.spread :: spread 정보를 가져옵니다." + "\n" +
						"매수자 // 매수번호 :: " +sobOne.getMemberSeqBuy()+ " // " + sobOne.getObSeqBuy()+ "\n" +
						"매수가격 :: "+sobOne.getbPrice()+ "\n" +
						"매도자 // 매도번호 :: " + sobOne.getMemberSeqSell() + " // " + sobOne.getObSeqSell()+ "\n" +
						"매도가격 :: "+sobOne.getsPrice()+ "\n" +
						"spread :: "+sobOne.getSpread()
					);
			}
			
		}else if(sobOne.getOrderType() == 1) {
			System.out.println(
					"들어온 주문이 1(시장매도)인경우 입니다.  sobOne.getOrderType() == 1 :: " + sobOne.getOrderType() + "\n" +
					"시장매도 메소드를 실행합니다." + "\n"
					+ "매게변수는 sobOne과 buyOrders 입니다."+ "\n"
					);
			
			if( orderbook.processMarketSell(sobOne,buyOrders) == true) {
				
				exchangeWSController.transactionTable(sobOne);
				System.out.println("ExchangeServiceImpl.completeOrder :: transactionTable() 정보를 갱신합니다."+ "\n" +
						"거래번호 :: "+ sobOne.getTransactionSeq()+ "\n" +
						"거래 통화 :: " + sobOne.getCryptoSeq()+ "\n" +
						"매수자 :: " +sobOne.getMemberSeqSell()+ "\n" +
						"매수번호 :: " + sobOne.getObSeqBuy()+ "\n" +
						"매도번호 :: " + sobOne.getObSeqSell()+ "\n" +
						"매도자 :: " +sobOne.getMemberSeqBuy()+ "\n" +
						"거래타입 :: "+ sobOne.getTransactedType()+ "\n" +
						"거래수량 :: " + sobOne.getAmount()+ "\n" +
						"거래가격 :: " + sobOne.getbPrice()+ "\n" +
						"거래 시간 :: " + sobOne.getTimestamp()+ "\n" +
						"전일대비 :: " + sobOne.getRatioPre()+ "\n" +
						"직전대비 :: " + sobOne.getRatioRe()
						);
				exchangeWSController.marketTable(sobOne);
				System.out.println("ExchangeServiceImpl.marketTable :: 화폐의 시세정보 테이블을 갱신합니다." + "\n" +
						"코인 :: " +sobOne.getCryptoSeq()+ "\n" +
						"최근 24시간 고가 :: " + sobOne.getHigh24()+ "\n" +
						"최근 24시간 저가 :: " + sobOne.getLow24()+ "\n" +
						"최근 24시간 거래량 :: " + sobOne.getVolume24()+ "\n" +
						"최근 24시간 거래대금 :: " + sobOne.getCap24()+ "\n" +
						"최근 거래가격 :: " + sobOne.getRecentPrice()+ "\n" +
						"전일 종가 :: " + sobOne.getClosingPrice() + "\n" +
						"전일대비 상승비율 :: "+ sobOne.getRatioPre()
						);
				exchangeWSController.spread(sobOne);
				System.out.println("ExchangeServiceImpl.spread :: spread 정보를 가져옵니다." + "\n" +
						"매수자 // 매수번호 :: " +sobOne.getMemberSeqBuy()+ " // " + sobOne.getObSeqBuy()+ "\n" +
						"매수가격 :: "+sobOne.getbPrice()+ "\n" +
						"매도자 // 매도번호 :: " + sobOne.getMemberSeqSell() + " // " + sobOne.getObSeqSell()+ "\n" +
						"매도가격 :: "+sobOne.getsPrice()+ "\n" +
						"spread :: "+sobOne.getSpread()
					);
			}
		}
	}
	
	
}

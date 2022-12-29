package com.seoul.infra.modules.exchange.orderMatchingSystem.trash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.controller.ExchangeWSController;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

@Service
public class OrderMatching4 {
	@Autowired 
	OrderMatchingSystemDao omsDao;
	@Autowired
	ExchangeWSController exchangeController;
	
	private ExchangeDao exchDao;
	
	//matchingProcessByAmount 지정주문이 들어올 경우
	public boolean matchingProcessByAmount(Order paramOrder, Order index0Order)throws Exception{
		
		System.out.println();
		
		if(paramOrder.getObAmount() < index0Order.getObAmount()) {
			/*
			 * i.ProcessIndex0MinusParam 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. start
			 * ii.리스트로 불러온 주문[0 수량에서 매개변수로 들어온 주문 수량 뺌 
			 */
			System.out.println(
					"ProcessIndex0MinusParam :: "+ "\n" +
					"paramOrder.getMemberSeqBuy() :: " + paramOrder.getMemberSeqBuy()+ "\n" +
					"paramOrder.getMemberSeqSell() :: " + paramOrder.getMemberSeqSell()+ "\n" +
					"paramOrder.getObSeqBuy() :: " + paramOrder.getObSeqBuy()+ "\n" +
					"paramOrder.getObSeqSell() :: " + paramOrder.getObSeqSell()+ "\n" +
					"paramOrder.getbPrice() :: " + paramOrder.getbPrice()+ "\n" +
					"paramOrder.getObAmount() :: " + paramOrder.getObAmount()+ "\n"+"\n"+
					
					"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
					"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
					"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
					"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
					"index0Order.getbPrice() :: " + index0Order.getbPrice()+ "\n" +
					"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"
			);
			
		}else if(paramOrder.getObAmount() > index0Order.getObAmount()) {
			/*
			 * ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. start
			 * 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
			 */
			
			System.out.println(
					"ProcessParamMinusIndex0 :: "+ "\n" +
					"paramOrder.getMemberSeqBuy() :: " + paramOrder.getMemberSeqBuy()+ "\n" +
					"paramOrder.getMemberSeqSell() :: " + paramOrder.getMemberSeqSell()+ "\n" +
					"paramOrder.getObSeqBuy() :: " + paramOrder.getObSeqBuy()+ "\n" +
					"paramOrder.getObSeqSell() :: " + paramOrder.getObSeqSell()+ "\n" +
					"paramOrder.getbPrice() :: " + paramOrder.getbPrice()+ "\n" +
					"paramOrder.getObAmount() :: " + paramOrder.getObAmount()+ "\n"+"\n"+
					
					"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
					"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
					"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
					"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
					"index0Order.getbPrice() :: " + index0Order.getbPrice()+ "\n" +
					"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"
			);
			
		}else {
			/*
			 * ProcessParamEqaulIndex0 :: 매개변수로 들어온 주문의 수량이 매칭된 주문의 수량과 같은경우
			 * paramOrder,index0Order 둘다 소화됨. 
			 */
			
			System.out.println(
					"ProcessParamMinusIndex0 :: "+ "\n" +
					"paramOrder.getMemberSeqBuy() :: " + paramOrder.getMemberSeqBuy()+ "\n" +
					"paramOrder.getMemberSeqSell() :: " + paramOrder.getMemberSeqSell()+ "\n" +
					"paramOrder.getObSeqBuy() :: " + paramOrder.getObSeqBuy()+ "\n" +
					"paramOrder.getObSeqSell() :: " + paramOrder.getObSeqSell()+ "\n" +
					"paramOrder.getbPrice() :: " + paramOrder.getbPrice()+ "\n" +
					"paramOrder.getObAmount() :: " + paramOrder.getObAmount()+ "\n"+"\n"+
					
					"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
					"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
					"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
					"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
					"index0Order.getbPrice() :: " + index0Order.getbPrice()+ "\n" +
					"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"
			);
			
		}
		return false;
	}
	
	//matchingProcessBySum 시장주문이 들어올 경우
	public boolean matchingProcessBySum(Order paramOrder, Order index0Order)throws Exception{
		
		double paramOrderSum = paramOrder.getObAmount() * paramOrder.getPrice();
		double index0OrderSum = index0Order.getObAmount() * index0Order.getPrice();
		
		if(paramOrderSum < index0OrderSum) {
			
			/*
			 * i.ProcessIndex0MinusParam 매개변수로 들어온 총 주문가격이 수량이 매칭된 총 주문 가격보다 작은경우. start
			 * ii.리스트로 불러온 총 주문가격에서 매개변수로 들어온 총 주문가격 차감 
			 * index0Order - paramOrder
			 */
			//index0Order.setTotalPirce(index0OrderSum - paramOrderSum);
			
			index0Order.setObAmount(index0Order.getTotalPirce() / index0Order.getPrice());
			
			/* 
			 * iii.차감된 이후 변경된 주문(index0Order)수량 정보를 저장.
			 */
			index0Order.setObSeq(index0Order.getObSeq());
			if(omsDao.updtObAmount(index0Order) == 1) {
				//클라이언트에 변경된 주문 정보 알림
				exchangeController.updateIncompleteOrderDivFromOB(index0Order);
			}else {
				return false;
			}
			
			/*
			 * iv.거래내역에 성사된 주문 저장
			 * 매개변수로 들어온 주문이 매수주문(=0)일경우와 매도주문(=1)일 경우로 분기를 나누어서 거래된 가격 정보 저장.
			 * 저장될 주문은 수량에 의해서 소화된 주문임 
			 * (수량이 작은 주문 :: paramOrder)
			 */
			if(paramOrder.getBos() == 0) {
				
				/* 
				 * paramOrder, 신청된 주문이 매수주문(0)일 경우 저장되는 주문
				 * 매수주문 :: paramOrder 매도주문 :: index0Order
				 * 
				 * @@시장 주문은 총 주문가격으로만 주문이 들어오기 때문에
				 * 거래 내역에 저장되는 가격과 수량은 index0Order의 수량과 가격과 동일하다. 
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(index0Order.getObAmount()); // 소화될 수량
				paramOrder.setPrice(index0Order.getPrice()); // 가격
				
				
			}else {
				/* 
				 * paramOrder, 신청된 주문이 매도주문(1)일 경우 저장되는 주문
				 * 매수주문은 index0Order 매도주문 :: paramOrder
				 */
				paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				paramOrder.setObAmount(index0Order.getObAmount()); // 소화될 수량
				paramOrder.setPrice(index0Order.getPrice()); // 가격
				
			}
			
			if(omsDao.insertTransactions(paramOrder) == 1) {
				//매개변수 주문 OrderStatus 변경													
				paramOrder.setObSeq(paramOrder.getObSeqSell());
				if(omsDao.completeOrder(paramOrder) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
				}	
			}
			//주문자 잔고 차감
			//method
			return false;
			// ProcessIndex0MinusParam 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. end 주문 종료
		}else if(paramOrder.getObAmount() > index0Order.getObAmount()) {
			/*
			 * ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. start
			 * 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
			 */
			
			paramOrder.setObAmount(paramOrder.getObAmount() - index0Order.getObAmount());
			
			//차감된 이후 주문 정보를 저장
			paramOrder.setObSeq(paramOrder.getObSeq());
			if(omsDao.updtObAmount(paramOrder) == 1) {
				//클라이언트에 변경된 주문 정보 알림
				exchangeController.updateIncompleteOrderDivFromOB(paramOrder);
			}
			
			if(paramOrder.getBos() == 0) {
				/* 
				 * paramOrder, 신청된 주문이 매수주문(0)일 경우 저장되는 주문
				 * 매수주문 :: paramOrder 매도주문 :: index0Order
				 */
				index0Order.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				index0Order.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
			}else {
				/* 
				 * paramOrder, 신청된 주문이 매도주문(1)일 경우 저장되는 주문
				 * 매수주문 :: index0Order 매도주문 :: paramOrder
				 */
				index0Order.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				index0Order.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
			}
			if(omsDao.insertTransactions(index0Order) == 1) {
				index0Order.setObSeq(index0Order.getObSeqBuy());
				if(omsDao.completeOrder(index0Order) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
				}
			}
			
			return false;
		}else {
			/*
			 * ProcessParamEqaulIndex0 :: 매개변수로 들어온 주문의 수량이 매칭된 주문의 수량과 같은경우
			 * paramOrder,index0Order 둘다 소화됨. 
			 */
			
			if(paramOrder.getBos() == 0) {
				/* 
				 * paramOrder, 신청된 주문이 매수주문(0)일 경우 저장되는 주문
				 * 매수주문 :: paramOrder 매도주문 :: index0Order
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
				
				
				index0Order.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				index0Order.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
			}else {
				/* 
				 * paramOrder, 신청된 주문이 매도주문(1)일 경우 저장되는 주문
				 * 매수주문 :: index0Order 매도주문 :: paramOrder
				 */
				paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
				
				index0Order.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				index0Order.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
			}
			
			if(omsDao.insertTransactions(index0Order) == 1) {
				index0Order.setObSeq(index0Order.getObSeqSell());
				if(omsDao.completeOrder(index0Order) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
				}
			}
			
			if(omsDao.insertTransactions(paramOrder) == 1) {
				index0Order.setObSeq(paramOrder.getObSeqSell());
				if(omsDao.completeOrder(paramOrder) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
				}
			}
			return false;
			//ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. end
		}
	}
	
	/**
	 * @param eatten
	 * @param eatter
	 * @return
	 * 
	 * 거래가 성사 되고 난 후에 진행되는 메소드.
	 * 0.체결내역에 체결된 주문 추가.  
	 * 1.클라이언트 측에 완료된 거래 삭제 요청
	 * 2.클라이언트 측에 체결내역 추가 요청
	 * 3.유저의 잔고 수정
	 * 4.클라이언트 측에 수정된 유저의 잔고 수정 요청
	 */
	private boolean afterEatten(Order eatten,Order eatter) {
		
		System.out.println(
				"eatten.getMemberSeqBuy() :: " + eatten.getMemberSeqBuy()+ "\n" +
				"eatten.getMemberSeqSell() :: " + eatten.getMemberSeqSell()+ "\n" +
				"eatten.getObSeqBuy() :: " + eatten.getObSeqBuy()+ "\n" +
				"eatten.getObSeqSell() :: " + eatten.getObSeqSell()+ "\n" +
				"eatten.getbPrice() :: " + eatten.getbPrice()+ "\n" +
				"eatten.getObAmount() :: " + eatten.getObAmount()+ "\n"+"\n"+
				
				"eatter.getMemberSeqBuy() :: " + eatter.getMemberSeqBuy()+ "\n" +
				"eatter.getMemberSeqSell() :: " + eatter.getMemberSeqSell()+ "\n" +
				"eatter.getObSeqBuy() :: " + eatter.getObSeqBuy()+ "\n" +
				"eatter.getObSeqSell() :: " + eatter.getObSeqSell()+ "\n" +
				"eatter.getbPrice() :: " + eatter.getbPrice()+ "\n" +
				"eatter.getObAmount() :: " + eatter.getObAmount()+ "\n"
				);
		
		if(eatten.getMemberSeqBuy() == null) { System.out.println("afterEatten :: nullPointExeption"); return false;}
		/*
		 * setter 메소드로 설정된 데이터들은 각각 거래내역에 저장.
		 * eatten은 완전히 소화된 주문, eatter는 불완전 소화된 주문
		 */
		int insertTransactions = omsDao.insertTransactions(eatten);
		System.out.println("omsDao.insertTransactions(paramOrder) == 1 :: " + (insertTransactions == 1));
		if(insertTransactions == 1 ) {
			
			int completeOrder = omsDao.completeOrder(eatter);
			System.out.println("omsDao.completeOrder(index0Order) == 0 :: " + completeOrder);
			//paramOrder.getObAmount() < index0Order.getObAmount(), 소화되는 주문(paramOrder)만 OrderStatus 변경	
			
			if(omsDao.completeOrder(eatten) == 0) {
				// 클라이언트로 거래가 성사된 주문 삭제 요청
				if(exchangeController.deleteCompleteOrderDivFromOB(eatten) == true) {
					//주문자 잔고 변경사항 저장.
					System.out.println("paramOrder.getBos() :: 매수 0 , 매도 1 :: "+eatten.getBos());
					
					
					eatten.setMemberSeq(eatten.getMemberSeqSell()); // 매도자 잔고 저장. 
					eatten.setTotalPirce(eatter.getPrice() * eatten.getObAmount());
					
					/*
					 * 널포인트
					 */
					System.out.println("updateUserBalance ::  유저 잔고 업데이트");
					int updateUserBalanceParam = exchDao.updateUserBalance(eatten);
					
					System.out.println("updateUserBalanceParam 유저 잔고 성공여부 :: " + updateUserBalanceParam);
					if(updateUserBalanceParam == 1) {
						boolean updateUserBalanceDiv = exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(eatten));
						System.out.println(updateUserBalanceDiv);
					}
					
					 // 매도자 잔고 변경사항 저장. 
					System.out.println("index0Order.getBos() :: 매수 0 , 매도 1 :: "+eatten.getBos());
					eatten.setMemberSeq(eatten.getMemberSeqBuy());
					eatten.setTotalPirce(eatter.getPrice() * eatten.getObAmount());
					
					int updateUserBalanceIdx0 = exchDao.updateUserBalance(eatten);
					System.out.println("updateUserBalanceIdx0 유저 잔고 성공여부 :: " + updateUserBalanceIdx0);
					if(updateUserBalanceIdx0 == 1){
						boolean updateUserBalanceDiv = exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(eatter));
						System.out.println(updateUserBalanceDiv);
					}
					
				}
			}else {
				return false;
			}
		}
		return true;
	}
}

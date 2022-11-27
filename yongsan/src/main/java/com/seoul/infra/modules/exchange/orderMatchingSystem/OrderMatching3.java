package com.seoul.infra.modules.exchange.orderMatchingSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.controller.ExchangeWSController;

@Service
public class OrderMatching3 {
	@Autowired 
	OrderMatchingSystemDao omsDao;
	@Autowired
	ExchangeWSController exchangeController;
	
	private ExchangeDao exchDao;
	
	// 0 :: not end
	// 1 :: end
	private boolean endingSiganl;
	
	public boolean getEndingSiganl() {
		return endingSiganl;
	}
	public void setEndingSiganl(boolean endingSiganl) {
		this.endingSiganl = endingSiganl;
	}
	//matchingProcessByAmount 지정주문이 들어올 경우
	public boolean matchingProcessByAmount(Order paramOrder, Order index0Order)throws Exception{
		
		if(paramOrder.getObAmount() < index0Order.getObAmount()) {
			
			/*
			 * i.ProcessIndex0MinusParam 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. start
			 * ii.리스트로 불러온 주문[0 수량에서 매개변수로 들어온 주문 수량 뺌 
			 * index0Order - order
			 */
			
			double index0MinusParam = index0Order.getObAmount() - paramOrder.getObAmount();
			index0Order.setObAmount(index0MinusParam);
			
			/* 
			 * iii.차감된 이후 주문수량 정보를 클라이언트에 저장
			 */
			index0Order.setObSeq(index0Order.getObSeq());
			if(omsDao.updtObAmount(index0Order) == 1) {
				//클라이언트에 변경된 주문 정보 알림
				exchangeController.updateIncompleteOrderDivFromOB(index0Order);
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
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
				
//				paramOrder.setOrders( 나중에 써보자.. 
//						paramOrder.getMemberSeq(),
//						index0Order.getMemberSeq(),
//						paramOrder.getObSeq(),
//						index0Order.getObSeq(),
//						paramOrder.getObAmount(),
//						paramOrder.getPrice()
//						);
			}else {
				/* 
				 * paramOrder, 신청된 주문이 매도주문(1)일 경우 저장되는 주문
				 * 매수주문은 index0Order 매도주문 :: paramOrder
				 */
				paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
			}
			// setter 메소드로 설정된 데이터들은 각각 거래내역에 저장.
			System.out.println("omsDao.insertTransactions(paramOrder) == 1 :: " + (omsDao.insertTransactions(paramOrder) == 1));
			if(omsDao.insertTransactions(paramOrder) == 1 ) {
				System.out.println("omsDao.completeOrder(index0Order) == 0 :: " + (omsDao.completeOrder(index0Order)));
				//paramOrder.getObAmount() < index0Order.getObAmount(), 소화되는 주문(paramOrder)만 OrderStatus 변경	
				if(omsDao.completeOrder(paramOrder) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
				}else {
					return false;
				}
				
				//주문자 잔고 변경사항 저장.
				paramOrder.setMemberSeq(paramOrder.getMemberSeqSell()); // 매도자 잔고 저장. 
				paramOrder.setTotalPirce(index0Order.getPrice(),paramOrder.getObAmount());
				
				if(exchDao.updateUserBalance(paramOrder) == 1) {
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(paramOrder));
				}
				index0Order.setMemberSeq(paramOrder.getMemberSeqBuy()); // 매도자 잔고 저장. 
				index0Order.setTotalPirce(index0Order.getPrice(),paramOrder.getObAmount());
				
				if(exchDao.updateUserBalance(index0Order) == 1){
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(index0Order));
				}
				
			}
			
			return false;
			// ProcessIndex0MinusParam 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. end 주문 종료
		}else if(paramOrder.getObAmount() > index0Order.getObAmount()) {
			/*
			 * ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. start
			 * 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
			 */
			
			// 남은 수량
			double paramMinusIndex0 = paramOrder.getObAmount() - index0Order.getObAmount();
			paramOrder.setObAmount(paramMinusIndex0);
			
			//차감된 이후 주문 정보를 클라이언트에 저장
			paramOrder.setObSeq(paramOrder.getObSeq());
			if(omsDao.updtObAmount(paramOrder) == 1) {
				//클라이언트에 변경된 주문 정보 알림
				exchangeController.updateIncompleteOrderDivFromOB(paramOrder);
			}
			
			if(paramOrder.getBos() == 0) {
				/* 
				 * 거래내역에 저장될 데이터들을 셋팅하는 구간. 저장될 주문은 완전히 소화되는 주문 :: index0Order
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
			System.out.println("omsDao.insertTransactions(paramOrder) == 1 :: " + (omsDao.insertTransactions(index0Order) == 1));
			// setter 메소드로 설정된 데이터들은 각각 거래내역에 저장.
			if(omsDao.insertTransactions(index0Order) == 1) {
				System.out.println("omsDao.completeOrder(index0Order) == 0 :: " + (omsDao.completeOrder(index0Order)));
				//paramOrder.getObAmount() > index0Order.getObAmount(), 소화되는 주문(index0Order)만 OrderStatus 변경	
				if(omsDao.completeOrder(index0Order) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
				}else {
					return false;
				}
				//주문자 잔고 변경사항 저장.
				paramOrder.setMemberSeq(index0Order.getMemberSeqSell()); // 매도자 잔고 저장. 
				paramOrder.setTotalPirce(index0Order.getPrice(),index0Order.getObAmount());
				if(exchDao.updateUserBalance(paramOrder) == 0) {
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(paramOrder));
				}
				index0Order.setMemberSeq(index0Order.getMemberSeqBuy()); // 매수자 잔고 저징.
				index0Order.setTotalPirce(index0Order.getPrice(),index0Order.getObAmount());
				if(exchDao.updateUserBalance(index0Order) == 0){
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(index0Order));
				}
			}
			
			return true;
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
			}
			System.out.println("omsDao.insertTransactions(index0Order) == 1 :: " + (omsDao.insertTransactions(index0Order) == 1));
			if(omsDao.insertTransactions(paramOrder) == 1) {
				System.out.println("omsDao.completeOrder(index0Order) == 0 :: " + (omsDao.completeOrder(index0Order)));
				//paramOrder.getObAmount() > index0Order.getObAmount(), 소화되는 주문(index0Order)만 OrderStatus 변경	
				if(omsDao.completeOrder(index0Order) == 0 && omsDao.completeOrder(paramOrder) == 0) {
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
				}else {
					return false;
				}
				
				//주문자 잔고 변경사항 저장.
				paramOrder.setMemberSeq(paramOrder.getMemberSeqSell()); // 매도자 잔고 저장. 
				paramOrder.setTotalPirce(paramOrder.getPrice(),paramOrder.getObAmount());
				
				System.out.println(
						"paramOrder.getMemberSeqSell() :: "
						);
				if(exchDao.updateUserBalance(paramOrder) == 0) {
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(paramOrder));
				}
				index0Order.setMemberSeq(index0Order.getMemberSeqBuy()); // 매도자 잔고 저장. 
				index0Order.setTotalPirce(index0Order.getPrice(),index0Order.getObAmount());
				if(exchDao.updateUserBalance(index0Order) == 1){
					exchangeController.updateUserBalanceDiv(exchDao.selectUserBalance(index0Order));
				}
			}
			return false;
			//ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. end
		}
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
	
	
}

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
	@Autowired
	ExchangeDao exchDao;
	
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
			
			//public boolean matchingProcessByAmount(Order paramOrder, Order index0Order)throws Exception{
			
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
				
//				paramOrder.setOrders(
//						paramOrder.getMemberSeq(), // 매수자
//						index0Order.getMemberSeq(), // 매도자
//						paramOrder.getObSeq(), // 매수OB
//						index0Order.getObSeq(), // 매도OB
//						paramOrder.getObAmount(),// 소화될 수량
//						paramOrder.getPrice() // 가격
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
				
//				paramOrder.setOrders(
//						index0Order.getMemberSeq(), // 매수자
//						paramOrder.getMemberSeq(), // 매도자
//						index0Order.getObSeq(), // 매수OB
//						paramOrder.getObSeq(), // 매도OB
//						paramOrder.getObAmount(),// 소화될 수량
//						paramOrder.getPrice() // 가격
//						);
			}
			
			System.out.println(
					"ProcessIndex0MinusParam :: "+ "\n" +
					"paramOrder.getMemberSeqBuy() :: " + paramOrder.getMemberSeqBuy()+ "\n" +
					"paramOrder.getMemberSeqSell() :: " + paramOrder.getMemberSeqSell()+ "\n" +
					"paramOrder.getObSeqBuy() :: " + paramOrder.getObSeqBuy()+ "\n" +
					"paramOrder.getObSeqSell() :: " + paramOrder.getObSeqSell()+ "\n" +
					"paramOrder.getPrice() :: " + paramOrder.getPrice()+ "\n" +
					"paramOrder.getObAmount() :: " + paramOrder.getObAmount()+ "\n"+"\n"
					);
			
			
			//comp -> SeqSell
			boolean afterEatten = afterEatten(paramOrder,"imp");
			
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
			}
			// ProcessIndex0MinusParam 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. end 주문 종료
		}else if(paramOrder.getObAmount() > index0Order.getObAmount()) {
			System.out.println("매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우");
			/*
			 * ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. start
			 * 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
			 */
			// 남은 수량
			double paramMinusIndex0 = paramOrder.getObAmount() - index0Order.getObAmount();
			System.out.println("남은 잔여 수량 :: " + paramMinusIndex0);
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
				
				System.out.println(
						"index0Order.getBos() == 0 ProcessParamMinusIndex0 :: "+ "\n" +
						"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
						"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
						"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
						"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
						"index0Order.getPrice() :: " + index0Order.getPrice()+ "\n" +
						"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"+"\n"
				);
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
				
				System.out.println(
						"index0Order.getBos() == 1 ProcessParamMinusIndex0 :: "+ "\n" +
						"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
						"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
						"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
						"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
						"index0Order.getPrice() :: " + index0Order.getPrice()+ "\n" +
						"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"
				);
			}
			/**
			 * afterEatten(eatten,eatter)
			 * 거래가 성사 되고 난 후에 진행되는 메소드.
			 * 0.체결내역에 체결된 주문 추가.  
			 * 1.클라이언트 측에 완료된 거래 삭제 요청
			 * 2.클라이언트 측에 체결내역 추가 요청
			 * 3.유저의 잔고 수정
			 * 4.클라이언트 측에 수정된 유저의 잔고 수정 요청
			 * 
			 * 필요한 정보는 
			 * 매수 번호 // 매수자seq
			 * 매도 번호 // 매도자seq
			 * 성사된 가격
			 * 성사된 주문 수량
			 */
			boolean afterEatten = afterEatten(index0Order,"pmi");
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
			}
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
			
			System.out.println(
					"ProcessParamEqaulIndex0 :: "+ "\n" +
					"paramOrder.getMemberSeqBuy() :: " + paramOrder.getMemberSeqBuy()+ "\n" +
					"paramOrder.getMemberSeqSell() :: " + paramOrder.getMemberSeqSell()+ "\n" +
					"paramOrder.getObSeqBuy() :: " + paramOrder.getObSeqBuy()+ "\n" +
					"paramOrder.getObSeqSell() :: " + paramOrder.getObSeqSell()+ "\n" +
					"paramOrder.getPrice() :: " + paramOrder.getPrice()+ "\n" +
					"paramOrder.getObAmount() :: " + paramOrder.getObAmount()+ "\n"+"\n"+
					
					"index0Order.getMemberSeqBuy() :: " + index0Order.getMemberSeqBuy()+ "\n" +
					"index0Order.getMemberSeqSell() :: " + index0Order.getMemberSeqSell()+ "\n" +
					"index0Order.getObSeqBuy() :: " + index0Order.getObSeqBuy()+ "\n" +
					"index0Order.getObSeqSell() :: " + index0Order.getObSeqSell()+ "\n" +
					"index0Order.getPrice() :: " + index0Order.getPrice()+ "\n" +
					"index0Order.getObAmount() :: " + index0Order.getObAmount()+ "\n"
					);
			
			boolean afterEatten = afterEatten(paramOrder,"iep");
			
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
			}
			//ProcessParamMinusIndex0.Start 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우. end
		}
		
		System.out.println("matchingProcessByAmount true를 반환합니다.");
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//matchingProcessBySum 시장주문이 들어올 경우
	public boolean matchingProcessBySum(Order paramOrder, Order index0Order)throws Exception{
		
		double paramOrderTotalPrice = paramOrder.getTotalPrice();
		double index0OrderTotalPrice = index0Order.getTotalPrice();
		
		if(paramOrderTotalPrice < index0OrderTotalPrice) {
			
			/*
			 * i.ProcessIndex0MinusParam 매개변수로 들어온 총 주문가격이 수량이 매칭된 총 주문 가격보다 작은경우. start
			 * ii.리스트로 불러온 총 주문가격에서 매개변수로 들어온 총 주문가격 차감 
			 * index0OrderTotalPrice - paramOrderTotalPrice
			 */
			double leftTotalPrice = (index0OrderTotalPrice - paramOrderTotalPrice);
			//매칭된 주문의 수량은 잔여 총 주문가격에서 주문가격으로 나눔한 값임.
			index0Order.setObAmount(leftTotalPrice);
			
			/* 
			 * iii.차감된 이후 변경된 주문(index0Order)수량 정보를 저장.
			 */
			int updtObAmount = omsDao.updtObAmount(index0Order);
			if(updtObAmount != 1) {return false;}
			exchangeController.updateIncompleteOrderDivFromOB(index0Order);
			
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
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
				paramOrder.setTotalPrice(paramOrder.getTotalPrice());//총 주문가격
				//comp order -> 
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
				paramOrder.setTotalPrice(paramOrder.getTotalPrice());//총 주문가격
			}
			
			boolean afterEatten = afterEatten(paramOrder,"pmi_M");
			
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
			}
			
			return false;
		}else if(paramOrderTotalPrice > index0OrderTotalPrice) {

			
			/*
			 * i.ProcessIndex0MinusParam 매개변수로 들어온 총 주문가격이 수량이 매칭된 총 주문 가격보다 작은경우. start
			 * ii.리스트로 불러온 총 주문가격에서 매개변수로 들어온 총 주문가격 차감 
			 * index0OrderTotalPrice - paramOrderTotalPrice
			 */
			double leftTotalPrice = (paramOrderTotalPrice - index0OrderTotalPrice);
			//매칭된 주문의 수량은 잔여 총 주문가격에서 주문가격으로 나눔한 값임.
			paramOrder.setObAmount(leftTotalPrice);
			/* 
			 * iii.차감된 이후 변경된 주문(index0Order)수량 정보를 저장.
			 */
			int updtObAmount = omsDao.updtObAmount(paramOrder);
			if(updtObAmount != 1) {return false;}
			exchangeController.updateIncompleteOrderDivFromOB(paramOrder);
			
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
				index0Order.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				index0Order.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
				//comp order -> 
			}else {
				/* 
				 * paramOrder, 신청된 주문이 매도주문(1)일 경우 저장되는 주문
				 * 매수주문은 index0Order 매도주문 :: paramOrder
				 */
				index0Order.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				index0Order.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				index0Order.setObSeqBuy(index0Order.getObSeq());// 매수 OB
				index0Order.setObSeqSell(paramOrder.getObSeq()); // 매도 OB
				index0Order.setObAmount(index0Order.getObAmount()); // 소화될 수량
				index0Order.setPrice(index0Order.getPrice()); // 가격
			}
			
			boolean afterEatten = afterEatten(index0Order,"imp_M");
			
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
			}
			
			return false;
		}else {
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
				//comp order -> 
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
			
			boolean afterEatten = afterEatten(paramOrder,"iep");
			
			if(!afterEatten) {
				System.out.println("afterEatten == false");
				return false;
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
	 * 3.유저의 잔고 수정 매도자, 매수자.
	 * 
	 * 4.클라이언트 측에 수정된 유저의 잔고 수정 요청
	 */
	private boolean afterEatten(Order resultOrder,String e) {
		
		
		System.out.println(
				"resultOrder.getMemberSeqBuy() :: " + resultOrder.getMemberSeqBuy()+ "\n" +
				"resultOrder.getMemberSeqSell() :: " + resultOrder.getMemberSeqSell()+ "\n" +
				"resultOrder.getCryptoSeq() :: " + resultOrder.getCryptoSeq()+ "\n" +
				"resultOrder.getObSeqBuy() :: " + resultOrder.getObSeqBuy()+ "\n" +
				"resultOrder.getObSeqSell() :: " + resultOrder.getObSeqSell()+ "\n" +
				"resultOrder.getPrice() :: " + resultOrder.getPrice()+ "\n" +
				"resultOrder.getObAmount() :: " + resultOrder.getObAmount()+ "\n"+"\n"
				);
		
		if(resultOrder.getMemberSeqBuy() == null) { System.out.println("afterEatten :: nullPointExeption"); return false;}
		/*
		 * setter 메소드로 설정된 데이터들은 각각 거래내역에 저장.
		 * eatten은 완전히 소화된 주문, eatter는 불완전 소화된 주문
		 */
		int insertTransactions = omsDao.insertTransactions(resultOrder);
		System.out.println("omsDao.insertTransactions(paramOrder) == 1 :: " + (insertTransactions == 1));
		if(insertTransactions != 1 ) {return false;}
		System.out.println("거래내역 저장 성공");
		
		if(e == "iep") {
			/**
			 * eatten p,i
			 * 
			 * div 삭제 요청은 둘다.
			 */
			int completeOrderB = omsDao.completeOrder(resultOrder.getObSeqBuy());
			int completeOrderS = omsDao.completeOrder(resultOrder.getObSeqSell());
			System.out.println("성사된 주문 orderStatus 변경" + completeOrderB+" // " + completeOrderS);
			if(completeOrderB != 1 || completeOrderS != 1 ) {return false;}
			System.out.println("성사된 주문 orderStatus 변경 성공");
			
			
			System.out.println("클라이언트 측 OrderbookDiv 에서 거래 완료된 주문 삭제 요청. ");
			if(!exchangeController.deleteCompleteOrderDivFromOB(resultOrder,"iep")) {return false;}
			System.out.println("클라이언트 측 OrderbookDiv 에서 거래 완료된 주문 삭제 요청 성공 ");
			
		}else {
			int completeOrderB = omsDao.completeOrder(resultOrder.getObSeq());
			System.out.println("성사된 주문 orderStatus 변경" + completeOrderB);
			if(completeOrderB != 1) {return false;}
			System.out.println("성사된 주문 orderStatus 변경 성공");
			
			
			System.out.println("클라이언트 측 OrderbookDiv 에서 거래 완료된 주문 삭제 요청. ");
			if(!exchangeController.deleteCompleteOrderDivFromOB(resultOrder,"eatten")) {return false;}
			System.out.println("클라이언트 측 OrderbookDiv 에서 거래 완료된 주문 삭제 요청 성공 ");
		}
		
		double totalPrice = resultOrder.getPrice() * resultOrder.getObAmount();
		
		System.out.println("매수자 매도자 잔고 업데이트 요청");
		
		//매수자 잔고 데이터 set
		Order buyer = new Order();
		buyer.setMemberSeq(resultOrder.getMemberSeqBuy());
		buyer.setCryptoSeq(resultOrder.getCryptoSeq());
		buyer.setBos(0);
		buyer.setTotalPrice(totalPrice);
		buyer.setObAmount(resultOrder.getObAmount());
		int updateUserBalanceBuyerA = omsDao.updateUserBalanceA(buyer);
		int updateUserBalanceBuyerB = omsDao.updateUserBalanceB(buyer);
		int updateResultB = updateUserBalanceBuyerA + updateUserBalanceBuyerB;
		if(updateUserBalanceBuyerA != 1 && updateUserBalanceBuyerB != 1) {return false;}
		System.out.println("매수자 잔고 업데이트 성공");
		
		//매도자 잔고 데이터 set
		Order seller = new Order();
		seller.setMemberSeq(resultOrder.getMemberSeqSell());
		seller.setCryptoSeq(resultOrder.getCryptoSeq());
		seller.setBos(1);
		seller.setTotalPrice(totalPrice);
		seller.setObAmount(resultOrder.getObAmount());
		int updateUserBalanceSellerA = omsDao.updateUserBalanceA(seller);
		int updateUserBalanceSellerB = omsDao.updateUserBalanceB(seller);
		int updateResultA = updateUserBalanceSellerA + updateUserBalanceSellerB;
		System.out.println(updateResultB + "//" + updateResultA);
		
		if(updateUserBalanceSellerA != 1 && updateUserBalanceSellerB != 1) {return false;}
		System.out.println("매도자 잔고 업데이트 성공");
		
		return true;
	}
}

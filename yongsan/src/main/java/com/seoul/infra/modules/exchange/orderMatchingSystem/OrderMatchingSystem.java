package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.dto.ExchDTO;

public class OrderMatchingSystem {
	@Autowired
	ExchangeDao exchDao;
	
	//매수주문과 매도 주문 매칭 알고리즘 구현
	//주문 하나를 불러온다.
	//해당 주문이 다른 주문을 스캔한다.
	/*
	 * 매수 주문일 경우
	 * 1.시장매수
	 * 시장매수주문은 해당 매수주문 가격보다 큰 매도 주문만을 감시한다. 
	 * 
	 * 2.지정매수
	 * 지정매도일 경우 해당 지정매도는 매도 가격이 같은 조건의 매도 주문들을 계속 감시한다. 
	 * while(true) 인 반복문에서 주문들을 계속 감시하고
	 * 해당 주문의 수량이 0으로 수렴이 되면 
	 * false를 반환하고 해당 주문은 완료된 거래로 전환 
	 * 
	 * 
	 * 큰 로직 플로우
	 * 1.주문이 들어오면 해당 주문을 현재 클래스로 가져온다.
	 * 
	 */
	
	//mathing BLO to SO
	public void matchingBLO(ExchDTO dto){
		 exchDao.selectBOB(dto);
		 
		 BigDecimal price = dto.getPrice();
		 BigDecimal amount = dto.getAmount();
		 System.out.println("price :: "+price +"// amount ::"+amount);
	}
	
	
	
	
	/*
	 * 매도 주문일경우
	 * 1.시장매도
	 * 시장매도주문은 해당 매도 주문보다 싼 매도 주문만 찾는다.
	 * 
	 *  2.지정매도
	 */
}

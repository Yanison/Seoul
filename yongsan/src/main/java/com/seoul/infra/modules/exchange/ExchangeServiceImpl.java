package com.seoul.infra.modules.exchange;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.seoul.infra.modules.exchange.dto.ExchDTO;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	
	@Autowired
	ExchangeDao dao;

	private SimpMessagingTemplate template;
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
	public List<ExchDTO> selectBOB(ExchDTO dto) throws Exception{
		return dao.selectBOB(dto);
	};
	@Override
	public List<ExchDTO> selectSOB(ExchDTO dto) throws Exception{
		return dao.selectSOB(dto);
	};
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Bids & Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public int submitBids(ExchDTO dto) throws Exception{
		System.out.println("ObserveSubmitBids :: "+ObserveSubmitBids(dto));
		if(ObserveSubmitBids(dto) == 1) {
			sendBOBListToClient(dto);
		}
		return dao.submitBids(dto);
	}
	@Override
	public int submitAsks(ExchDTO dto) throws Exception{
		System.out.println("ObserveSubmitAsks ::"+ObserveSubmitAsks(dto));
		if(ObserveSubmitAsks(dto) == 1) {
			sendSOBListToClient(dto);
		}
		return dao.submitAsks(dto);
	}
	
	
	
	//주문 탐지, 오더가 들어올때 마다 1을 리턴함.
	public int ObserveSubmitBids(ExchDTO dto) {
		System.out.println("ObserveSubmitBids :: "+dao.submitBids(dto));
		if(dao.submitBids(dto) == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	//웹소켓을 구독한 클라이언트에게 주문 내역 전달
	@RequestMapping(path="/observeSubmittedOrder", method = RequestMethod.POST)
	public void sendBOBListToClient(ExchDTO dto) {
		System.out.println("On sendBOBListToClient :: " + dao.selectBOB(dto));
		String bob = new Gson().toJson(dao.selectBOB(dto));
		System.out.println("bob :: "+ bob);
		/*
		 *  이렇게 부르면 전체를 가져오게 되는건데 
		 *  하나씩 불러서 자바스크립트로 append 찍어줘야함
		 */
		this.template.convertAndSend("/topic/observeSubmittedBOB", bob);
	}
	
	
	public int ObserveSubmitAsks(ExchDTO dto) {
		System.out.println("ObserveSubmitAsks :: "+dao.submitAsks(dto));
		if(dao.submitAsks(dto) == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	@RequestMapping(path="/observeSubmittedOrder", method = RequestMethod.POST)
	public void sendSOBListToClient(ExchDTO dto) {
		System.out.println("On sendSOBListToClient :: " + dao.selectSOB(dto));
		String sob = new Gson().toJson(dao.selectSOB(dto));
		System.out.println("sob :: "+sob);
		this.template.convertAndSend("/topic/observeSubmittedSOB", sob);
	}
	
	// 한 건 들어갈 때마다 웹소켓에 주문 내역을 던진다. 
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get OBList throu STOMP over SockJS
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	
	
	
	
	
}

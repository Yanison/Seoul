package com.seoul.infra.modules.exchange;

import java.util.Date;
import java.util.List;

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
	public ExchDTO selectBOBOne(ExchDTO dto) throws Exception{
		return dao.selectBOBOne(dto);
	}
	
	@Override
	public List<ExchDTO> selectSOB(ExchDTO dto) throws Exception{
		return dao.selectSOB(dto);
	};
	@Override
	public ExchDTO selectSOBOne(ExchDTO dto) throws Exception{
		return dao.selectSOBOne(dto);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Bids & Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public int submitBids(ExchDTO dto) throws Exception{
		return dao.submitBids(dto);
	}
	@Override
	public int submitAsks(ExchDTO dto) throws Exception{
		
		return dao.submitAsks(dto);
	}
	//웹소켓을 구독한 클라이언트에게 주문 내역 전달
//	@RequestMapping(path="/observeSubmittedOrder", method = RequestMethod.POST)
//	public void sendBOBListToClient(ExchDTO dto) {
//		System.out.println("On sendBOBListToClient :: " + dao.selectBOB(dto));
//		String bob = new Gson().toJson(dao.selectBOB(dto));
//		System.out.println("bob :: "+ bob);
//		this.template.convertAndSend("/topic/observeSubmittedBOB", bob);
//	}
//	@RequestMapping(path="/observeSubmittedOrder", method = RequestMethod.POST)
//	public void sendSOBListToClient(ExchDTO dto) {
//		System.out.println("On sendSOBListToClient :: " + dao.selectSOB(dto));
//		String sob = new Gson().toJson(dao.selectSOB(dto));
//		System.out.println("sob :: "+sob);
//		this.template.convertAndSend("/topic/observeSubmittedSOB", sob);
//	}
	
	// 한 건 들어갈 때마다 웹소켓에 주문 내역을 던진다. 
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get OBList throu STOMP over SockJS
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	
	
	
	
	
}

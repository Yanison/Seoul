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
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get OBList throu STOMP over SockJS
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	
	
	
	
	
}

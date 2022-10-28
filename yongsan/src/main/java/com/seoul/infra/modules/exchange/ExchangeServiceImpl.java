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
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	
	@Autowired
	ExchangeDao dao;
	@Autowired
	OrderMatchingSystemDao omsDao;

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
		return omsDao.selectBOB(dto);
	};
	@Override
	public Order selectBOBOne(Order dto) throws Exception{
		return omsDao.selectBOBOne(dto);
	}
	
	@Override
	public List<Order> selectSOB(Order dto) throws Exception{
		return omsDao.selectSOB(dto);
	};
	@Override
	public Order selectSOBOne(Order dto) throws Exception{
		return omsDao.selectSOBOne(dto);
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

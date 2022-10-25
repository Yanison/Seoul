package com.seoul.infra.modules.exchange;

import java.util.List;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;

public interface ExchangeService {
	
	public ExchDTO userBalance(ExchDTO dto) throws Exception;
	
	public List<Order> selectBOB(Order dto) throws Exception;
	public Order selectBOBOne(Order dto) throws Exception;
	public Order selectSOBOne(Order dto) throws Exception;
	public List<Order> selectSOB(Order dto) throws Exception;
	
	public int submitBids(ExchDTO dto) throws Exception;
	public int submitAsks(ExchDTO dto) throws Exception;
	public ExchDTO getOnlaodInfo(ExchDTO dto) throws Exception;
//	public int countOB(ExchDTO dto) throws Exception;
//	public List<OderBookDto> selectAllOB(OderBookDto dto) throws Exception;
//	
//	public List<OderBookDto> selectAllAsks(OderBookDto dto) throws Exception;
//	
//	public OderBookDto selectBO(OderBookDto dto) throws Exception;
}

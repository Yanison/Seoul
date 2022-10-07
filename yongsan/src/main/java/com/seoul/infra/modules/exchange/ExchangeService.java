package com.seoul.infra.modules.exchange;

import java.util.List;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;

public interface ExchangeService {
	
	public ExchDTO userBalance(ExchDTO dto) throws Exception;
	public List<ExchDTO> selectBOB(ExchDTO dto) throws Exception;
	public List<ExchDTO> selectSOB(ExchDTO dto) throws Exception;
	public int submitBids(ExchDTO dto) throws Exception;
	public int submitAsks(ExchDTO dto) throws Exception;
//	public List<OderBookDto> selectAllOB(OderBookDto dto) throws Exception;
//	
//	public List<OderBookDto> selectAllAsks(OderBookDto dto) throws Exception;
//	
//	public OderBookDto selectBO(OderBookDto dto) throws Exception;
}

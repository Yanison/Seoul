package com.seoul.infra.modules.exchange;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.seoul.infra.modules.exchange.dto.ExchDTO;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	
	@Autowired
	ExchangeDao dao;
	
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
 * @@@@@@ submitBids
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	@Override
	public int submitBids(ExchDTO dto) throws Exception{
		return dao.submitBids(dto);
	}
	@Override
	public int submitAsks(ExchDTO dto) throws Exception{
		return dao.submitBids(dto);
	}
}

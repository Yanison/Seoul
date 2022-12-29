package com.seoul.infra.modules.investments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentsServiceImpl {
	
	@Autowired
	InvestmentsDao invDao;
	
	public List<InvestmentsDto> tradeStatus (InvestmentsDto invDto){
		
		return invDao.tradeStatus(invDto);
	}
	
	public InvestmentsDto assetEvaluation (InvestmentsDto invDto){
		
		return invDao.assetEvaluation(invDto);
	}
	
	public List<InvestmentsDto> ordersHistory (InvestmentsDto invDto){
	
		return invDao.ordersHistory(invDto);
	}
	
	public List<InvestmentsDto> pendingHistory (InvestmentsDto invDto){
		
		return invDao.pendingHistory(invDto);
	}
}

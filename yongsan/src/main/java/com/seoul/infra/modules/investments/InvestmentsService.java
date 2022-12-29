package com.seoul.infra.modules.investments;

import java.util.List;


public interface InvestmentsService {
	public List<InvestmentsDto> tradeStatus (InvestmentsDto invDto) throws Exception;
	
	public InvestmentsDto assetEvaluation (InvestmentsDto invDto) throws Exception;
	
	public List<InvestmentsDto> ordersHistory (InvestmentsDto invDto) throws Exception;
	
	public List<InvestmentsDto> pendingHistory (InvestmentsDto invDto) throws Exception;
}

package com.seoul.infra.modules.investments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/investments/")
public class InvestmentsRestController {
	
	@Autowired
	InvestmentsServiceImpl invService;
	@Autowired
	InvestmentsDao invDao;
	
	@ResponseBody
	@RequestMapping(value="/balance/requestTradeStatus")
	public List<InvestmentsDto> requestTradeStatus(Model model,InvestmentsDto invDto) {
		model.addAttribute("nowPage", "balance");
		
		List<InvestmentsDto> tradeStatus = invService.tradeStatus(invDto);
		
		return tradeStatus;
	}
	@ResponseBody
	@RequestMapping(value="/balance/requestAssetEvaluation")
	public InvestmentsDto requestAssetEvaluation(Model model,InvestmentsDto invDto) {
		
		
		InvestmentsDto assetEvaluation = invService.assetEvaluation(invDto);
		
		return assetEvaluation;
	}
	@ResponseBody
	@RequestMapping(value="/history/requestOrderHistory")
	public List<InvestmentsDto> ordersHistory(Model model,InvestmentsDto invDto) {
		
		List<InvestmentsDto> ordersHistory = invService.ordersHistory(invDto);
		
		return ordersHistory;
	}
	@ResponseBody
	@RequestMapping(value="/wait_orders/requestPendingHistory")
	public List<InvestmentsDto> pendingHistory(Model model,@ModelAttribute("vo") InvestmentsDto invDto) {
		
		int cnt = invDao.pendingHistoryCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		List<InvestmentsDto> pendingHistory = invService.pendingHistory(invDto);
		
		
		
		System.out.println(pendingHistory);
		
		return pendingHistory;
	}
	
	@ResponseBody
	@RequestMapping(value="/history/selectCryptoName")
	public List<InvestmentsDto> selectCryptoName(Model model,@ModelAttribute("vo") InvestmentsDto invDto) {
		
		List<InvestmentsDto> selectCryptoName = invDao.selectCryptoName(invDto);
		
		
		return selectCryptoName;
	}
}
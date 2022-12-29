package com.seoul.infra.modules.investments;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoul.infra.common.basic.commonVO;

@Controller
@RequestMapping(value="/investments/")
public class InvestmentsController {
	
	@Autowired
	InvestmentsServiceImpl invService;
	@Autowired
	HttpSession session;
	@Autowired
	InvestmentsDao invDao;
	
	public void getBasicList(Model model)throws Exception {
		//get UserSession
		Object memberName = session.getAttribute("memberName");
		Object idTokenKko  = session.getAttribute("idTokenKko");
		Object memberSeq  = session.getAttribute("memberSeq");
		model.addAttribute("memberName", memberName);
		model.addAttribute("idTokenKko", idTokenKko);
		model.addAttribute("memberSeq", memberSeq);
		}
	
	@RequestMapping(value="/balance")
	public String helpcenter(Model model,@ModelAttribute("vo") InvestmentsDto invDto)throws Exception {
		
		
		model.addAttribute("nowPage", "balance");
		model.addAttribute("nowUrl", "/investments/balance");
		getBasicList(model);
		
		if(session.getAttribute("memberSeq") != null) {
			invDto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		List<InvestmentsDto> tradeStatus = invService.tradeStatus(invDto);
		InvestmentsDto assetEvaluation = invService.assetEvaluation(invDto);
		
		int cnt = invDao.tradeStatusCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		model.addAttribute("tradeStatus", tradeStatus);
		model.addAttribute("assetEvaluation", assetEvaluation);
		
		
		return "investments/balance";
	}
	
	@RequestMapping(value="/history")
	public String history(Model model,@ModelAttribute("vo") InvestmentsDto invDto)throws Exception {
		
		model.addAttribute("nowPage", "history");
		model.addAttribute("nowUrl", "/investments/wait_orders");
		if(session.getAttribute("memberSeq") != null) {
			invDto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		List<InvestmentsDto> ordersHistory = invService.ordersHistory(invDto);
		model.addAttribute("ordersHistory", ordersHistory);
		
		int cnt = invDao.ordersHistoryCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		getBasicList(model);
		return "investments/history";
	}
	
	@RequestMapping(value="/wait_orders")
	public String wait_orders(Model model,@ModelAttribute("vo") InvestmentsDto invDto)throws Exception {
		
		if(session.getAttribute("memberSeq") != null) {
			invDto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		List<InvestmentsDto> pendingHistory = invService.pendingHistory(invDto);
		model.addAttribute("pendingHistory", pendingHistory);
		
		int cnt = invDao.pendingHistoryCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		model.addAttribute("nowPage", "wait_orders");
		model.addAttribute("nowUrl", "/investments/wait_orders");
		
		getBasicList(model);
		return "investments/wait_orders";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="userOrderHistory/userOrderHistory_hitory")
	public String userOrderHistory_hitory(Model model,@ModelAttribute("vo") InvestmentsDto invDto)throws Exception {
		
		model.addAttribute("nowPage", "history");
		model.addAttribute("nowUrl", "/investments/wait_orders");
		if(session.getAttribute("memberSeq") != null) {
			invDto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		List<InvestmentsDto> ordersHistory = invDao.uoh_ordersHistory(invDto);
		model.addAttribute("ordersHistory", ordersHistory);
		
		int cnt = invDao.uoh_ordersHistoryCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		getBasicList(model);
		return "userOrderHistory/history";
	}
	
	@RequestMapping(value="userOrderHistory/userOrderHistory_wait_order")
	public String userOrderHistory_wait_order(Model model,@ModelAttribute("vo") InvestmentsDto invDto)throws Exception {
		
		if(session.getAttribute("memberSeq") != null) {
			invDto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		List<InvestmentsDto> pendingHistory = invDao.uoh_pendingHistory(invDto);
		model.addAttribute("pendingHistory", pendingHistory);
		
		int cnt = invDao.uoh_pendingHistoryCnt(invDto);
		invDto.setParamsPaging(cnt);
		
		model.addAttribute("nowPage", "wait_orders");
		model.addAttribute("nowUrl", "/investments/wait_orders");
		
		getBasicList(model);
		return "userOrderHistory/wait_orders";
	}
}
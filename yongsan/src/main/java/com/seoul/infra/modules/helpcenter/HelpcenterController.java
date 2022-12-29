package com.seoul.infra.modules.helpcenter;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/helpcenter/")
public class HelpcenterController {
	
	@Autowired
	HelpcenterDao dao;
	public void getBasicList(Model model,HttpSession session)throws Exception {
		//get UserSession
	Object memberName = session.getAttribute("memberName");
	Object idTokenKko  = session.getAttribute("idTokenKko");
	Object memberSeq  = session.getAttribute("memberSeq");
	model.addAttribute("memberName", memberName);
	model.addAttribute("idTokenKko", idTokenKko);
	model.addAttribute("memberSeq", memberSeq);
	}
	
	@RequestMapping(value="/press")
	public String press(@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		getBasicList(model,session);
		model.addAttribute("nowPage", "press");
		model.addAttribute("nowUrl", "/helpcenter/press");
		
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		List<HelpcenterDto> selectBdList = dao.selectBdList(dto);
		model.addAttribute("selectBdList", selectBdList);
		
		int cnt = dao.selectBdListCnt(dto);
		dto.setParamsPaging(cnt);
		
		return "helpcenter/helpcenter_board";
	}
	@RequestMapping(value="/articles")
	public String articles(@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		getBasicList(model,session);
		model.addAttribute("nowPage", "articles");
		model.addAttribute("nowUrl", "/helpcenter/balance");
		
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		dto.setShOption(1);
		List<HelpcenterDto> selectBdList = dao.selectBdList(dto);
		model.addAttribute("selectBdList", selectBdList);
		
		int cnt = dao.selectBdListCnt(dto);
		dto.setParamsPaging(cnt);
		
		return "helpcenter/helpcenter_articles";
	}
	
	@RequestMapping(value="/board")
	public String board(@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		getBasicList(model,session);
		model.addAttribute("nowPage", "board");
		model.addAttribute("nowUrl", "/helpcenter/board");
		
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		List<HelpcenterDto> selectBdList = dao.selectBdList(dto);
		model.addAttribute("selectBdList", selectBdList);
		
		int cnt = dao.selectBdListCnt(dto);
		dto.setParamsPaging(cnt);
		
		return "helpcenter/helpcenter_board";
	}
	
	@RequestMapping(value="/board_write")
	public String board_write(@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		getBasicList(model,session);
		
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		
		return "helpcenter/helpcenter_board_write";
	}
	
	
	@RequestMapping(value="/board/bdView/{bdSeq}")
	public String boardContent(@PathVariable int bdSeq,@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		
		getBasicList(model,session);
		
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		dto.setShOption(0);
		dto.setBdSeq(bdSeq);
		
		List<HelpcenterDto> selectBdList = dao.selectBdList(dto);
		
		HelpcenterDto bdOne = selectBdList.get(0);
		model.addAttribute("bdOne", bdOne);
		
		dao.view(dto);
		
		
		List<HelpcenterDto> selectCmtList = dao.selectCmtList(dto);
		int cnt2 = dao.selectCmtListCnt(dto);
		dto.setParamsPaging(cnt2);
		model.addAttribute("selectCmtList", selectCmtList);
		
		return "helpcenter/helpcenter_board_view";
	}
	
	@RequestMapping(value="/board/bdUpdate/{bdSeq}")
	public String bdUpdate(@PathVariable int bdSeq,@ModelAttribute("vo") HelpcenterDto dto,HttpSession session,Model model)throws Exception {
		
		getBasicList(model,session);
		if(session.getAttribute("memberSeq") != null) {
			dto.setMemberSeq((int) session.getAttribute("memberSeq"));
		}
		
		dto.setShOption(0);
		dto.setBdSeq(bdSeq);
		
		List<HelpcenterDto> selectBdList = dao.selectBdList(dto);
		
		HelpcenterDto bdOne = selectBdList.get(0);
		model.addAttribute("bdOne", bdOne);
		
		return "helpcenter/helpcenter_board_upt";
	}
	
	
}

package com.seoul.infra.modules.membergroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/yongsancode/")
public class MemberGroupController {
	
	@Autowired
	MemberGroupServiceImpl service;
	
	@RequestMapping(value = "memberGroupList")
	public String memberGroupList(@ModelAttribute("vo") MemberGroup vo,Model model) throws Exception {
		

		vo.setParamsPaging(service.selectOneCnt(vo));
		
		List<MemberGroup> list = service.selectMlist(vo);
		model.addAttribute("list", list);
		int selectOneCnt = service.selectOneCnt(vo);
		System.out.println("@@@selectOneCnt" + selectOneCnt);
		
		

		return "infra/hwangdmin/membergroup/memberGroupList"; 
	}
	
	@RequestMapping(value="memberGroupUpdt")
	public String memberGroupUdpt(@ModelAttribute("vo") MemberGroup vo,Model model, MemberGroup dto ) throws Exception{
		
		int addUser = service.addUser(dto);
		System.out.println(addUser);
		
		return "redirect:home";
	}

}

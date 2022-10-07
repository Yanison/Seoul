package com.seoul.infra.modules.membergroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@RequestMapping(value="memberGroupUpdt")
	public String memberGroupUdpt(@ModelAttribute("vo") MemberGroup vo,Model model, MemberGroup dto ) throws Exception{
		System.out.println("pw ::"+ vo.getMemberPw());
		int addUser = service.addUser(dto);
		System.out.println(addUser);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "isDupleId")
	public Map<String, Object> checkId(MemberGroup dto) throws Exception {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int result = service.isDupleId(dto);

		if (result > 0) {
			returnMap.put("rt", "fail");
		} else {
			returnMap.put("rt", "success");
		}
		return returnMap;
	}
	
	 

}

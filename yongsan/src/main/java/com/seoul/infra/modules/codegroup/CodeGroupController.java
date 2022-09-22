package com.seoul.infra.modules.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/yongsancode/")
public class CodeGroupController {
	
	@Autowired
	CodeGroupServiceImpl service;
	
	@RequestMapping(value = "roothome")
	public String roothome(Model model) {
		
		return "infra/hwangdmin/rootHome";
	}
	
	@RequestMapping(value = "codeGroupList")
	public String codeGroupList(@ModelAttribute("vo") CodeGroup vo ,Model model) throws Exception {
		
		List<CodeGroup> list = service.selectCList(vo);
		model.addAttribute("list", list);
		
		
		return "infra/hwangdmin/codegroup/codeGroupList"; 
	}
	
	

}

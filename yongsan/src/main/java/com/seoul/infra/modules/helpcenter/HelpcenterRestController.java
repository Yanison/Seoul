package com.seoul.infra.modules.helpcenter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/helpcenter/")
public class HelpcenterRestController {
	
	@Autowired
	HelpcenterDao dao;
	
	@RequestMapping(value="/board/write")
	public String writeBd(HelpcenterDto dto,HttpSession session) {
		
		dao.write(dto);
		
		return "redirect:/helpcenter/press";
	}
	@RequestMapping(value="/board/update")
	public String updateBd(HelpcenterDto dto,HttpSession session) {
		
		dao.uptBd(dto);
		
		return "redirect:/helpcenter/press";
	}
	@ResponseBody
	@RequestMapping(path="/board/delete")
	public HashMap<String, String> deleteBd(@RequestParam(value="bdSeq") String data,HttpSession session) {
		
		HashMap<String, String> reusult = new HashMap<String, String> ();
		int del = 0;
		int i =0;
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = new JSONObject();
			JSONArray arr = (JSONArray) parser.parse(data);
			for(i = 0 ; i < arr.size();i++) {
				
				obj = (JSONObject) arr.get(i);
				int bdSeq = Integer.parseInt((String) obj.get("bdSeq")) ;
				System.out.println(bdSeq);
				HelpcenterDto dto = new HelpcenterDto();
				dto.setBdSeq(bdSeq);
				dao.delCmt(dto);
				del += dao.delBd(dto);
				
				if(del == i) {
					 reusult.put("rp", "success");
				}else {
					 reusult.put("rp", "fail");
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return reusult;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/board/commnet/write")
	public HelpcenterDto writeCmt(HelpcenterDto dto,HttpSession session) {
		
		int cmt = dao.cmt(dto);
		dao.selectCmtList(dto);
		dto.setShValue("mm");
		return dao.selectCmtList(dto).get(0);
	}
	@RequestMapping(value="/board/commnet/update")
	public String updateCmt(HelpcenterDto dto,HttpSession session) {
		
		return "rp";
	}
	@ResponseBody
	@RequestMapping(value="/board/commnet/delete")
	public int deleteCmt(HelpcenterDto dto,HttpSession session) {
		
		int cmt = dao.delCmt(dto);
		
		return dto.getCmtSeq();
	}
}

package com.seoul.infra;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.infra.common.basic.Constants;
import com.seoul.infra.modules.membergroup.MemberGroup;
import com.seoul.infra.modules.membergroup.MemberGroupByKko;
import com.seoul.infra.modules.membergroup.MemberGroupServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	MemberGroupServiceImpl service;
	@Autowired
	private HttpSession session;
	
	//세션을 불러오는 메소드
	public static String getSessionSeqCore(HttpServletRequest httpServletRequest) {
		HttpSession httpSession =  httpServletRequest.getSession();
		String rtSeq = (String) httpSession.getAttribute("sessSeq");
		return rtSeq;
	}
	
	


	@RequestMapping(value="/")
	public String home(Model model) {
		
		Object memberName = session.getAttribute("memberName");
		Object idTokenKko  = session.getAttribute("idTokenKko");
		Object memberSeq  = session.getAttribute("memberSeq");
		
		//Object dto 타입이 아닌 Object타입으로 선언한 이유 기술해야함. 
		System.out.println("session.getAttribute memberName :: " + memberName);
		System.out.println("session.getAttribute idTokenKko :: " + idTokenKko);
		System.out.println("session.getAttribute memberSeq :: " + memberSeq);
		
		model.addAttribute("memberName", memberName);
		model.addAttribute("idTokenKko", idTokenKko);
		model.addAttribute("memberSeq", memberSeq);
		
		
		return "home/home";
	}

	
	 @RequestMapping("/kakaologin")
	    public String adduserkko(@RequestParam(value = "code", required = false) String code) throws Exception{
	        System.out.println("#########" + code);
	       
	        
	        String access_Token = service.getAccessToken(code);
	        MemberGroup userInfo = service.getUserInfo(access_Token);
	       
	        System.out.println("##access_Token## :: " + access_Token);
	        System.out.println("##userInfo## :: " + userInfo);
	        System.out.println("##getMemberName## :: " + userInfo.getMemberName());
	        
	        session.invalidate();
//	        
//	        session.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE); // 60second * 30 = 30minute
	        session.setAttribute("memberName", userInfo.getMemberName());
	        session.setAttribute("idTokenKko", userInfo.getIdTokenKko());
	        session.setAttribute("memberSeq", userInfo.getMemberSeq());
	        
	        //HttpSession에 대한 이해를 서술해야함 HttpServletRequest vs HttpSession
	        System.out.println("session.setAttribute : getMemberName :: "+ userInfo.getMemberName());
	        System.out.println("session.setAttribute : getIdTokenKko :: "+ userInfo.getIdTokenKko());
	        System.out.println("session.setAttribute : getMemberSeq :: "+ userInfo.getMemberSeq());
		      
	      
	        return "redirect:/";
	        
	        //redirect의 동작 방식을 기술해야함
	    }
	
	@RequestMapping(value="/userLogin")
	public String userLogin(MemberGroup dto, HttpSession httpSession) throws Exception{
		
		
		
		return "home/userLogin";
	}
	
	@ResponseBody
	@RequestMapping(value = "login")
	public Map<String, Object> login(MemberGroup dto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();

		MemberGroup rtMember = service.selectOneId(dto);
		System.out.println("id :: "+dto.getMemberId());
		System.out.println("pw :: "+dto.getMemberPw());
		//유저 아이디가 존재한다면,
		if (rtMember != null) {
			//유저 아이디와 비밀번호를 불러와서
			MemberGroup rtMember2 = service.selectOneLogin(dto);
			System.out.println("db pw :: "+rtMember2.getMemberPw()+" vs "+" input pw :: "+dto.getMemberPw());
			//유저 아이디와 비밀번호가 존재한다
			if (rtMember2 != null) {
				System.out.println("db pw :: "+rtMember2.getMemberPw()+" == "+" input pw :: "+dto.getMemberPw());
				
				httpSession.setAttribute("sessSeq", rtMember2.getMemberSeq());
				httpSession.setAttribute("sessId", rtMember2.getMemberId());
				httpSession.setAttribute("sessName", rtMember2.getMemberName());

				returnMap.put("rt", "success");
			//유저 아이디와 비밀번호가 존재하지 않는다
			} else {
				returnMap.put("rt", "fail");
			}
		//유저 아이디가 존재하지 않는다면
		} else {

			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@RequestMapping(value="/adduser")
	public String adduser() {
		
		return "home/adduser";
	}
	
	@RequestMapping(value="/adduserkko")
	public String adduserkko(Model model) {
		
		model.addAttribute("nowPage", "adduserkko");
		
		return "home/userLoginkko";
	}
	
	@RequestMapping(value="/userLoginkko")
	public String userLogin(Model model) {
		
		model.addAttribute("nowPage", "userLoginkko");
		
		return "home/userLoginkko";
	}
	
	
	 
	 @ResponseBody
	 @RequestMapping("/kakaoLogout")
	 public Map<String, Object> kakaoLogout()throws Exception {
		 
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 session.invalidate();
		 
		 returnMap.put("rt","success");
		 System.out.println("session isInvalidated? memberName :: " + session.getAttribute("memberName"));
		 System.out.println("session isInvalidated? idTokenKko :: " + session.getAttribute("idTokenKko"));
			
		 
		 return returnMap;
	 }
	
	
	
}

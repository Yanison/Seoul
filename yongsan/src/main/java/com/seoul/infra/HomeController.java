package com.seoul.infra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.membergroup.MemberGroup;
import com.seoul.infra.modules.membergroup.MemberGroupServiceImpl;
import com.seoul.infra.modules.membergroup.NaverLoginService;


@Controller
public class HomeController {
	
	@Autowired
	MemberGroupServiceImpl service;
	@Autowired
	NaverLoginService naverLoginService;
	@Autowired
	ExchangeServiceImpl serviceExch;
	@Autowired
	private HttpSession session;
	
	//세션을 불러오는 메소드
	public static String getSessionSeqCore(HttpServletRequest httpServletRequest) {
		HttpSession httpSession =  httpServletRequest.getSession();
		String rtSeq = (String) httpSession.getAttribute("sessSeq");
		return rtSeq; 
	}
	
	@RequestMapping(value="/")
	public String home(
			Model model
			,Crypto crypto) {
		
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
		
		try {
			List<Crypto> cryptoList = serviceExch.selectCryptoList(crypto);
			model.addAttribute("cryptoList", cryptoList);
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return "home/home";
	}
	
	@RequestMapping(value="/naverLogin")
	public String naverLogin (
			@RequestParam String code,
			@RequestParam String state,
			Model model)throws Exception {
		
		System.out.println("HomeController.naverLogin() getAuthCode :: " +  code);
		String access_token = naverLoginService.getAuthCode(code,state);
		System.out.println("HomeController.naverLogin() access_token :: " +  access_token);
		String getInfoUser = naverLoginService.getUserInfo(access_token);
		System.out.println("HomeController.naverLogin() access_token :: " +  getInfoUser);
		
		/*
		 * 이후 세션처리. 
		 */
		
		return "redirect:/";
	}
	
	 @RequestMapping("/kakaologin")
	    public String adduserkko(@RequestParam(value = "code", required = false) String code){
	        System.out.println("#########" + code);
	       
	        try {
	        	 String access_Token = service.getAccessToken(code);
	 	        MemberGroup userInfo = service.getUserInfo(access_Token);
	 	       
	 	        System.out.println("##access_Token## :: " + access_Token);
	 	        System.out.println("##userInfo## :: " + userInfo);
	 	        System.out.println("##getMemberName## :: " + userInfo.getMemberName());
	 	        
	 	        session.invalidate();
//	 	        
	 	        session.setMaxInactiveInterval(60 * 60); // 60second * 30 = 30minute
	 	        session.setAttribute("memberName", userInfo.getMemberName());
	 	        session.setAttribute("idTokenKko", userInfo.getIdTokenKko());
	 	        session.setAttribute("memberSeq", userInfo.getMemberSeq());
	 	        
	 	        //HttpSession에 대한 이해를 서술해야함 HttpServletRequest vs HttpSession
	 	        System.out.println("session.setAttribute : getMemberName :: "+ userInfo.getMemberName());
	 	        System.out.println("session.setAttribute : getIdTokenKko :: "+ userInfo.getIdTokenKko());
	 	        System.out.println("session.setAttribute : getMemberSeq :: "+ userInfo.getMemberSeq());
	 	        
	 	       return "redirect:/";
	        }catch(Exception e){
	        	
	        	return "redirect:/userLogin";
	        }
	        //redirect의 동작 방식을 기술해야함
	    }
	
	@RequestMapping(value="/userLogin")
	public String userLogin(MemberGroup dto, HttpSession httpSession) throws Exception{
		
		
		
		return "home/userLogin";
	}
	@RequestMapping(value="/userLoginFor")
	public String userLoginFor(MemberGroup dto, HttpSession httpSession) throws Exception{
		
		return "home/userLoginFor";
	}
	
	@ResponseBody
	@RequestMapping(value = "login")
	public Map<String, Object> login(MemberGroup dto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		MemberGroup rtMember = service.selectOneLogin(dto);
		if (rtMember.getMemberId() != null) {
			//유저 아이디와 비밀번호를 불러와서
			
			System.out.println("db pw :: "+rtMember.getMemberPw()+" vs "+" input pw :: "+dto.getMemberPw());
			//유저 아이디와 비밀번호가 존재한다
		//유저 아이디가 존재하지 않는다면
			returnMap.put("rt", "success");
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
		
		return "home/userLogin";
	}
	
	@RequestMapping(value="/userLoginkko")
	public String userLogin(Model model) {
		
		model.addAttribute("nowPage", "userLoginkko");
		
		return "home/userLogin";
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

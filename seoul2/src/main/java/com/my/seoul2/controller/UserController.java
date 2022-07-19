package com.my.seoul2.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.seoul2.dao.UserDao;
import com.my.seoul2.vo.User;

@Controller
public class UserController {
	
	@Resource(name="UserDao")
	private UserDao userDao;
	
	@RequestMapping(value = "/getTest/{idx}", method = RequestMethod.GET)
	public @ResponseBody String getTest(@PathVariable int idx) {
		
		return "";
	}
	
//		@RequestMapping(value = "/ajax_root", method = RequestMethod.GET)
//		public @ResponseBody String getRoot (
//				@RequestParam(value="user_idx") int user_idx,
//				HttpSession session) {
//			
//			User userRoot = new User();
//			userRoot.setUser_idx(user_idx);
//			
//			User user = userDao.getUserRoot(userRoot);
//			
//			
//			return "ok";
//		}


		@RequestMapping(value = "/ajax_login", method = RequestMethod.GET)
		public @ResponseBody String getLogin (
				@RequestParam(value="id") String id,
				@RequestParam(value="pw") String pw,
				HttpSession session) {
			
			
			User userLogin = new User();
			userLogin.setId(id);
			userLogin.setPw(pw);
			
			User user = userDao.getUserByIdAndPw(userLogin);
			
			
			//프로젝트 전체에 공유가 됨. 
//			session.setAttribute("me", user);
			//근데 여기다 쓰면 로그인 승인 상관없이 세션에 데이터를 저장하게 됨. 
			
			
			if(user != null) {
				session.setAttribute("me", user);
				//그래서 로그인 로직에다 구현하면 안전성이 생김
				
				return "ok";
			}else {
				return "fail";
			}
			
			
		}

		
		@RequestMapping(value = "/ajax_logout", method = RequestMethod.GET)
		private @ResponseBody String ajax_logout(HttpSession session) {
			
			
			//session 파기
			session.invalidate();
			
			return "ok";
		}
		
//		@RequestMapping(value = "/ajax_getAll", method = RequestMethod.GET)
//		private @ResponseBody List<User> getAll() {
//			
//			
//			
//			List<User> list = userDao.getAll();
//			
//			return list;
//		}
	
	
	
	@RequestMapping(value= "/ajax_adduser", method = RequestMethod.GET)
	public @ResponseBody String ajax_adduser(
			@RequestParam(value="id") String id,
			@RequestParam(value="pw") String pw,
			@RequestParam(value="nickname") String nickname,
			@RequestParam(value="gender") String gender,
			@RequestParam(value="address") String address,
			@RequestParam(value="tel") String tel
			) {
		
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		user.setNickname(nickname);
		user.setGender(gender);
		user.setAddress(address);
		user.setTel(tel);
		
		User resultUser = userDao.getUserById(user);
		
		
		if(resultUser == null) {
			userDao.addUser(user);
			return "ok";
		} else {
			return "duplicated";
		}
		
		
		
		
		
		
		
	
	}
	
	
	
}

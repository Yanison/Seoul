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
import com.my.seoul2.vo.UserInfoDetail;

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
	
	
		@RequestMapping(value = "/ajax_loadUserInfoByIdx", method = RequestMethod.GET)
		private @ResponseBody UserInfoDetail ajax_loadUserInfoByIdx(
				@RequestParam(value="user_idx") int user_idx
				) {
			
			UserInfoDetail user = new UserInfoDetail();
			user.setUser_idx(user_idx);
			
			UserInfoDetail dUser = userDao.getAllInfoByIdx(user);
			
			return dUser;
		}
	
	
	


		@RequestMapping(value = "/ajax_login", method = RequestMethod.GET)
		public @ResponseBody String getLogin (
				@RequestParam(value="id") String id,
				@RequestParam(value="pw") String pw,
				HttpSession session) {
			
			
			User userLogin = new User();
			userLogin.setId(id);
			userLogin.setPw(pw);
			//유저의 기본정보도 불러와야함.
			
			User user = userDao.getUserByIdAndPw(userLogin);
			
			//프로젝트 전체에 공유가 됨. 
//			session.setAttribute("me", user);
			//근데 여기다 쓰면 로그인 승인 상관없이 세션에 데이터를 저장하게 됨. 
			
			
			if(user != null) {
				session.setAttribute("me", user);
//				session.setAttribute("me", userInfo);
				//그래서 로그인 로직에다 구현하면 안전성이 생김
				
				return "ok";
			}else {
				return "fail";
			}
			
			
		}
		@RequestMapping(value = "/ajax_form", method = RequestMethod.GET)
		private @ResponseBody String ajax_form(HttpSession session) {
			
			return "ok";
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
	
	
////////////////////////////////////////////////////////////////////////////////
//root
////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value= "/ajax_addUserInfoDtail", method = RequestMethod.GET)
	public @ResponseBody String ajax_addUserInfoDtail(
			@RequestParam(value="dob") String dob,
			@RequestParam(value="email") String email,
			@RequestParam(value="passPortNameEng") String passPortNameEng,
			@RequestParam(value="addressDetail") String addressDetail,
			@RequestParam(value="jobInfo") String jobInfo,
			@RequestParam(value="jobName") String jobName,
			@RequestParam(value="jobType") String jobType,
			@RequestParam(value="jobAddress") String jobAddress,
			@RequestParam(value="jobAddressDetail") String jobAddressDetail
			
			) {
		System.out.println("파라미터 받고");
		UserInfoDetail userInfo = new UserInfoDetail();
		userInfo.setDob(dob);
		userInfo.setEmail(email);
		userInfo.setPassPortNameEng(passPortNameEng);
		userInfo.setAddressDetail(jobAddressDetail);
		userInfo.setJobInfo(jobInfo);
		userInfo.setJobName(jobName);
		userInfo.setJobType(jobType);
		userInfo.setJobAddress(jobAddressDetail);
		userInfo.setJobAddressDetail(jobAddressDetail);
		
		System.out.println("인스턴스 어서오고");
		
		userDao.addUserInfoDtail(userInfo);
		//Mapped Statements collection does not contain 
		//value for user.adduserInfo
		return "ok";
	}
	
	@RequestMapping(value= "/ajax_loadUserInfoDtail", method = RequestMethod.GET)
	public @ResponseBody List<UserInfoDetail> getAllInfo() {
			
		List<UserInfoDetail> listInfo = userDao.getAllInfo();
		
		return listInfo;
	}
	
//	@RequestMapping(value= "/ajax_loadUserInfoDtail", method = RequestMethod.GET)
//	public @ResponseBody List<UserInfoDetail> getAllInfoByIdx(
//			@RequestParam(value="user_idx") int user_idx
//			) {
//		
//		
//		List<UserInfoDetail> listInfo = userDao.getAllInfoByIdx();
//		
//		
//		return listInfo;
//	}
	
	
	
}

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
	
	
		@RequestMapping(value = "/ajax_loadUserInfoByIdx", method = RequestMethod.GET)
		private @ResponseBody User ajax_loadUserInfoByIdx(
				@RequestParam(value="user_idx") int user_idx
				) {
			
			User user = new User();
			user.setUser_idx(user_idx);
			
			User dUser = userDao.getAllInfoByIdx(user);
			dUser.getUser_idx();
			
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
			
			User me = userDao.getUserByIdAndPw(userLogin);
			User root = userDao.getUserRoot(userLogin);
			
			
			
			if( me != null) {
				System.out.println("Check pont1 >>> " + userLogin.getId());
				System.out.println("Check pont2 >>> " + root.getId());
				if(userLogin.getId().equals(root.getId())) {
					System.out.println('1');
					System.out.println(userLogin.getId());
					System.out.println(root.getId());
					session.setAttribute("root", root);				
				}
				System.out.println('2');
				System.out.println(userLogin.getId());
				System.out.println(root.getId());
				session.setAttribute("me", me);
				return "okroot";
				//그래서 로그인 로직에다 구현하면 안전성이 생김
			}else {
				return "fail" ;
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
		
		User getUserById = userDao.getUserById(user);
		User getUserByNick = userDao.getUserByNick(user);
		
		if(getUserById == null) {
			if(getUserByNick == null) {
				userDao.addUser(user);
				return "ok";
			}else {
				return "duplicatedNick";
			}	
		} else {
			return "duplicatedId";
		}
		
		
	
	}
	
	
////////////////////////////////////////////////////////////////////////////////
//root
////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value= "/ajax_addUserInfoDtail", method = RequestMethod.GET)
	public @ResponseBody String ajax_addUserInfoDtail(
			@RequestParam(value="name") String name,
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
		User userInfo = new User();
		userInfo.setName(name);
		userInfo.setDob(dob);
		userInfo.setEmail(email);
		userInfo.setPassPortNameEng(passPortNameEng);
		userInfo.setAddressDetail(addressDetail);
		userInfo.setJobInfo(jobInfo);
		userInfo.setJobName(jobName);
		userInfo.setJobType(jobType);
		userInfo.setJobAddress(jobAddress);
		userInfo.setJobAddressDetail(jobAddressDetail);
		
		System.out.println("인스턴스 어서오고");
		
		userDao.addUserInfoDtail(userInfo);
		System.out.println("이제 리턴하자");
		//Mapped Statements collection does not contain 
		//value for user.adduserInfo
		return "ok";
	}
	
	@RequestMapping(value= "/ajax_loadUserInfoDetail", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllInfo() {
			
		List<User> listInfo = userDao.getAllInfo();
		
		return listInfo;
	}
	
	@RequestMapping(value= "/ajax_loadUserInfoDtail", method = RequestMethod.GET)
	public @ResponseBody User getAllInfoByIdx(
			@RequestParam(value="user_idx") int user_idx
			) {
		
		User userInfo = new User();
		userInfo.setUser_idx(user_idx);
		
		
		User userIdx = userDao.getAllInfoByIdx(userInfo);
		
		
		return userIdx;
	}
	
	
	
}

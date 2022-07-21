package com.my.seoul2.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.my.seoul2.vo.User;
import com.my.seoul2.vo.UserInfoDetail;

public class UserDao extends SqlSessionDaoSupport{
	
	
	public List<User> getAll() {
		return this.getSqlSession().selectList("user.getAll");
	}
	
	
	public User getUserByIdAndPw(User user) {
		return this.getSqlSession().selectOne("user.getUserByIdAndPw", user);
	}
	
	public User getUserByNick(User user) {
		return this.getSqlSession().selectOne("user.getUserByNick", user);
	}
	
	public User getUserById(User user) {
		return this.getSqlSession().selectOne("user.getUserById", user);
	}
	
	public User getUserRoot(User user) {
		return this.getSqlSession().selectOne("user.getUserRoot", user);
	}
	
	public int addUser(User user) {
		return this.getSqlSession().insert("user.adduser", user);
	}

	
	public int addUserInfoDtail(UserInfoDetail user) {
		return this.getSqlSession().insert("user.adduserInfo", user);
	}
	
}

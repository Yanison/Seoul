package com.my.seoul2.dao;



import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.my.seoul2.vo.User;

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

	
	public int addUserInfoDtail(User user) {
		return this.getSqlSession().insert("user.addUserInfoDtail", user);
	}
	
	public  List<User> getAllInfo() {
		return this.getSqlSession().selectList("user.getAllInfo");
	}
	
	public  User getAllInfoByIdx (User user) {
		return this.getSqlSession().selectOne("user.getAllInfoByIdx", user);
	}
	
}

package com.seoul.infra.modules.membergroup;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.modules.codegroup.CodeGroup;

@Repository
public class MemberGroupDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace = "com.seoul.infra.modules.membergroup.MemberGroupMapper";
	
//UserController BY Admin
	public List<MemberGroup> selectMlist(MemberGroup vo){
		
		return sqlSession.selectList(namespace + ".selectMlist", vo);
	}
	
	public int selectOneCnt (MemberGroup vo) {
		return sqlSession.selectOne(namespace + ".selectOneCnt", vo);
	}
	
	
	
	
	
//User
	
	public int addUser(MemberGroup dto) {
		return sqlSession.update(namespace + ".addUser", dto);
	}
	public int addBalance(MemberGroup dto) {
		return sqlSession.update(namespace + ".addBalance", dto);
	}
	
	//카카오로그인
	public int kkoLogin(HashMap<String, Object> userInfo) {
		System.out.println("kkoLogin :: " + userInfo.get("memberName"));
		System.out.println("kkoLogin ::" + userInfo.get("memberEmail"));
		System.out.println("kkoLogin :: " + userInfo.get("idTokenKko"));
		System.out.println("kkoLogin :: " + userInfo.get("memberDob"));
		System.out.println("kkoLogin :: " + userInfo.get("memberGender"));
		return sqlSession.insert(namespace +".kkoLogin", userInfo);
	}
	//카카오회원찾기
	public MemberGroup findKkoMember(HashMap<String, Object> userInfo) {
		System.out.println("findKkoMember :: " + userInfo.get("memberName"));
		System.out.println("findKkoMember :: " + userInfo.get("memberEmail"));
		System.out.println("findKkoMember :: " + userInfo.get("idTokenKko"));
		System.out.println("findKkoMember :: " + userInfo.get("memberDob"));
		System.out.println("findKkoMember :: " + userInfo.get("memberGender"));
		return sqlSession.selectOne(namespace + ".findKkoMember", userInfo);
	}
	
	public MemberGroup selectOneLogin (MemberGroup dto) {
		return sqlSession.selectOne(namespace + ".selectOneLogin", dto);
	}
	
	public MemberGroup selectOneId (MemberGroup dto) {
		return sqlSession.selectOne(namespace + ".selectOneId", dto);
	}
	
	public int isDuple (MemberGroup vo) {
		return sqlSession.selectOne(namespace +".isDuple",vo);
	}
	
	public List<MemberGroup> findUserId (MemberGroup dto){
		return sqlSession.selectList(namespace+".findUserId", dto);
	}

	public MemberGroup selectMOne (MemberGroup dto) {
		return sqlSession.selectOne(namespace + ".selectMOne", dto);
	}
	
	

		
}

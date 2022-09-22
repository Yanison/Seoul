package com.seoul.infra.modules.membergroup;

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
	
	public List<MemberGroup> selectMlist(MemberGroup vo){
		
		return sqlSession.selectList(namespace + ".selectMlist", vo);
	}
	
	public int selectOneCnt (MemberGroup vo) {
		return sqlSession.selectOne(namespace + ".selectOneCnt", vo);
	}
	
	public int addUser(MemberGroup dto) {
		return sqlSession.update(namespace + ".addUser", dto);
	}
	
////	for cache
//	public List<CodeGroup> selectListCachedCodeArrayList(){ 
//		return sqlSession.selectList(namespace + ".selectListCachedCodeArrayList", null);
//	}
		
}

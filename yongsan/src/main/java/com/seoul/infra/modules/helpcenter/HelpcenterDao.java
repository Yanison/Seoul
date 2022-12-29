package com.seoul.infra.modules.helpcenter;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class HelpcenterDao {
	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String mapper = "com.seoul.infra.modules.helpcenter.HelpcenterMapper";
	
	
	/**
	 * board
	 */
	public List<HelpcenterDto> selectBdList(HelpcenterDto dto){
		
		return sqlSession.selectList(mapper+".selectBdList", dto);
	}
	
	
	public int selectBdListCnt(HelpcenterDto dto) {
		
		return sqlSession.selectOne(mapper+".selectBdListCnt", dto);
	}
	
	public int write(HelpcenterDto dto) {
		
		return sqlSession.insert(mapper+".write", dto);
	}
	public int uptBd(HelpcenterDto dto) {
			
			return sqlSession.update(mapper+".uptBd", dto);
	}
	public int view(HelpcenterDto dto) {
		
		return sqlSession.update(mapper+".view", dto);
	}
	public int delBd(HelpcenterDto dto) {
		
		return sqlSession.delete(mapper+".delBd", dto);
	}
	
	
	
	
	/**
	 * comment
	 */
	public List<HelpcenterDto> selectCmtList(HelpcenterDto dto){
		
		return sqlSession.selectList(mapper+".selectCmtList", dto);
	}
	
	public int selectCmtListCnt(HelpcenterDto dto) {
		
		return sqlSession.selectOne(mapper+".selectCmtListCnt", dto);
	}
	public int cmt(HelpcenterDto dto) {
		
		return sqlSession.insert(mapper+".cmt", dto);
	}
	public int uptCmt(HelpcenterDto dto) {
			
			return sqlSession.update(mapper+".uptCmt", dto);
	}
	public int delCmt(HelpcenterDto dto) {
		
		return sqlSession.delete(mapper+".delCmt", dto);
	}
	
}

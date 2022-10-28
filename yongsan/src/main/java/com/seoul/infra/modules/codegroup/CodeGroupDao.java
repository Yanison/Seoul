package com.seoul.infra.modules.codegroup;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.dto.Crypto;


@Repository
public class CodeGroupDao {
	
	@Inject
	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace = "com.seoul.infra.modules.codegroup.CodeGroupMapper";
	public static String cryptoMapper = "com.seoul.infra.modules.cryptogroup.CryptoGroupMapper";
	
	public List<CodeGroup> selectCList(CodeGroup vo){
		return sqlSession.selectList(namespace + ".selectCList", vo);
	}
	
	public List<Crypto> selectAllCrypto(Crypto vo2){
		return sqlSession.selectList(cryptoMapper + ".selectAllCrypto", vo2);
	}
	
//	for cache
	public List<CodeGroup> selectListCachedCodeArrayList(){ 
		return sqlSession.selectList(namespace + ".selectListCachedCodeArrayList", null); 
	}

}

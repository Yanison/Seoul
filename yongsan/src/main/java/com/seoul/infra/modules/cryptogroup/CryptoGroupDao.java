package com.seoul.infra.modules.cryptogroup;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.dto.Crypto;

@Repository
public class CryptoGroupDao {

		@Inject
		@Resource (name = "sqlSession")
		private SqlSession sqlSession;
		
		public static String namespace = "com.seoul.infra.modules.cryptogroup.CryptoGroupMapper";
		
		public List<Crypto> selectAllCrypto(Crypto vo){
			
			return sqlSession.selectList(namespace + ".selectAllCrypto", vo);
		}
	
}

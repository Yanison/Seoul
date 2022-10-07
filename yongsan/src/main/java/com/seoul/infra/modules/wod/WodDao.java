package com.seoul.infra.modules.wod;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class WodDao {
	
	@Inject
	@Resource (name="sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace="com.seoul.infra.modules.wod.WodMapper";
	
	public List<WodDTO> selectBal(WodDTO voW) {
		System.out.println("@@@@@@ dao getMyBal ::"+ voW.getCryptoName());
		System.out.println("@@@@@@ dao idtokenkko ::"+ voW.getIdTokenKko());
		System.out.println("@@@@@@ dao cryptosym ::"+ voW.getCryptoSym());
		System.out.println("@@@@@@ dao amount ::"+ voW.getAmount());
		
		return sqlSession.selectList(namespace + ".selectBal", voW);
	}

}

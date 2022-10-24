package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;

@Repository
public class OrderMatchingSystemDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace="com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSysMapper";
	

}

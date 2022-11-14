package com.seoul.infra.modules.exchange;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;

@Repository
public class ExchangeDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace = "com.seoul.infra.modules.exchange.mapper.ExchMapper";
	public static String omsNamespace = "com.seoul.infra.modules.exchange.mapper.OrderMathigSystemMapper";
	
	
	public ExchDTO getOnlaodInfo(ExchDTO dto) {
		return sqlSession.selectOne(namespace+".getOnlaodInfo", dto);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get userBalance into submitBidsBox
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public ExchDTO userBalance(ExchDTO dto) {
		return sqlSession.selectOne(namespace+".userBalance", dto);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Order
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public int submitBids(ExchDTO dto) {
		return sqlSession.insert(namespace +".submitBids", dto);
	}
	public int submitAsks(ExchDTO dto) {
		return sqlSession.insert(namespace +".submitAsks", dto);
	}
}

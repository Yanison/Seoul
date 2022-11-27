package com.seoul.infra.modules.exchange;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;

@Repository
public class ExchangeDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String namespace = "com.seoul.infra.modules.exchange.mapper.ExchMapper";
	public static String omsNamespace = "com.seoul.infra.modules.exchange.mapper.OrderMathigSystemMapper";
	public static String crypto= "com.seoul.infra.modules.cryptogroup.CryptoGroupMapper";
	
	
	
	public ExchDTO getOnlaodInfo(ExchDTO dto) {
		return sqlSession.selectOne(namespace+".getOnlaodInfo", dto);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ user
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public ExchDTO userBalance(ExchDTO dto) {
		return sqlSession.selectOne(namespace+".userBalance", dto);
	}
	
	public int selectUserBalance(Order order) {
		return sqlSession.selectOne(namespace+".selectUserBalance", order);
	}
	
	public int updateUserBalance(Order order) {
		return sqlSession.update(namespace +".updateUserBalance", order);
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
	
	public List<Crypto> selectCryptoList(Crypto crypto) {
		return sqlSession.selectList(namespace + ".selectCryptoList", crypto);
	}
	
	public ExchDTO selectCrpytoOne(ExchDTO dto) {
		return sqlSession.selectOne(namespace + ".selectCryptoOne", dto);
	}
}

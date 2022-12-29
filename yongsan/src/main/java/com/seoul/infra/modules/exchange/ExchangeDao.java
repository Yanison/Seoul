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
	
	public static String exchMapper = "com.seoul.infra.modules.exchange.ExchMapper";
	public static String crypto= "com.seoul.infra.modules.cryptogroup.CryptoGroupMapper";
	public static String omsMapper = "com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMathigSystemMapper";
	
	
	
	public List<Order> selectOBList(Order order){
		
		return sqlSession.selectList(exchMapper+".selectOBList",order);
	}
	public ExchDTO getOnlaodInfo(ExchDTO dto) {
		return sqlSession.selectOne(exchMapper+".getOnlaodInfo", dto);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ user
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public ExchDTO userBalance(ExchDTO dto) {
		return sqlSession.selectOne(exchMapper+".userBalance", dto);
	}
	
	public double selectAvailableCashBalance(Integer memberSeq) {
		
		return sqlSession.selectOne(exchMapper+".selectAvailableCashBalance",memberSeq);
	}
	
	public int selectUserBalance(Order order) {
		
		return sqlSession.selectOne(exchMapper+".selectUserBalance", order);
	}
	public int updateUserBalance(Order order) {
		System.out.println();
		
		return sqlSession.update(exchMapper +".updateUserBalance", order);
	}
	public List<Order> selectMyOrder(Order order){
		
		return sqlSession.selectList(exchMapper+".selectMyOrder",order);
	}
	public List<Order> selectMytransaction(Order order){
		
		return sqlSession.selectList(exchMapper+".selectMytransaction",order);
	}
	public List<Order> selectListCryptoTrend(Order order){
		
		return sqlSession.selectList(exchMapper+".selectListCryptoTrend", order);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit Order
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public int submitBids(ExchDTO dto) {
		return sqlSession.insert(exchMapper +".submitBids", dto);
	}
	public int submitAsks(ExchDTO dto) {
		return sqlSession.insert(exchMapper +".submitAsks", dto);
	}
	
	public List<Crypto> selectCryptoList(Crypto crypto) {
		return sqlSession.selectList(exchMapper + ".selectCryptoList", crypto);
	}
	
	public ExchDTO selectCrpytoOne(ExchDTO dto) {
		return sqlSession.selectOne(exchMapper + ".selectCryptoOne", dto);
	}
}

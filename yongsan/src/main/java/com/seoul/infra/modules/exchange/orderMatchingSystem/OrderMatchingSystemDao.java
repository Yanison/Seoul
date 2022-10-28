package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Trade;

@Repository
public class OrderMatchingSystemDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	public static String omsNamespace = "com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMathigSystemMapper";
	
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get table OB Bids&Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public List<Order> selectBOB(Order dto){
		return sqlSession.selectList(omsNamespace + ".selectBOB", dto);
	}
	public Order selectBOBOne(Order dto){
		return sqlSession.selectOne(omsNamespace + ".selectBOBOne", dto);
	}
	
	public List<Order> selectSOB(Order dto){
		return sqlSession.selectList(omsNamespace + ".selectSOB", dto);
	}
	public Order selectSOBOne(Order dto){
		return sqlSession.selectOne(omsNamespace + ".selectSOBOne", dto);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ delNy obseq
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public int delObseq (Order dto) {
		return sqlSession.update(omsNamespace + ".delObseq", dto);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ updt Amount
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */	
	public int updtObAmount (Order dto) {
		return sqlSession.update(omsNamespace + ".updtObAmount", dto);
	}
	
	public int insertTransactions(Trade trade) {
		return sqlSession.insert(omsNamespace + ".insertTransactions", trade);
	}
	
}


package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoul.infra.modules.exchange.controller.ExchangeWSController;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;

@Repository
public class OrderMatchingSystemDao {

	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	public static String omsMapper = "com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMathigSystemMapper";
	public static String exchMapper = "com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMathigSystemMapper";
	
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ get table OB Bids&Asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public List<Order> selectBOB(Order dto){
		return sqlSession.selectList(omsMapper + ".selectBOB", dto);
	}
	public Order selectBOBOne(Order dto){
		return sqlSession.selectOne(omsMapper + ".selectBOBOne", dto);
	}
	
	public List<Order> selectSOB(Order dto){
		return sqlSession.selectList(omsMapper + ".selectSOB", dto);
	}
	public Order selectSOBOne(Order dto){
		return sqlSession.selectOne(omsMapper + ".selectSOBOne", dto);
	}
	public List<Order> selectOBListForMatching(Order order){
		return sqlSession.selectList(omsMapper+".selectOBListForMatching", order);
	}
	public Integer selectOrderStatus(Order order) {
		return sqlSession.selectOne(omsMapper + ".selectOrderStatus", order);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ selectTransacton
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public List<Order> selectTransacton(Order order){
		return sqlSession.selectList(omsMapper + ".selectTransacton", order);
	}
	
	public Order transactionTable(Order order) {
		return sqlSession.selectOne(omsMapper + ".transactionTable", order);
	}
	
	public Order marketTable(Order order) {
		return sqlSession.selectOne(omsMapper + ".marketTable",order);
	}
	
	public List<Order> spread(Order order) {
		return sqlSession.selectList(omsMapper + ".spread",order);
	}
	public List<Order> drawChart(Order order){
		return sqlSession.selectList(omsMapper + ".drawChart",order);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ submit bids & asks
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public int submitBids(Order order) {
		return sqlSession.insert(omsMapper +".submitBids", order);
	}
	public int submitAsks(Order order) {
		return sqlSession.insert(omsMapper +".submitAsks", order);
	}
	
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ change OrderStatus
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	public int delObseq (int obSeq) {
		return sqlSession.update(omsMapper + ".delObseq", obSeq);
	}
	
	public int completeOrder (int obSeq) {
		System.out.print("OrderMatchingSystemDao.completeOrder(Order order)" + "\n"
				+ "주문이 완전히 소화가 될 경우 업데이트 정보입니다." + "\n"
				+"업데이트 될 주문번호 :: " + obSeq + "\n"
				);
		return sqlSession.update(omsMapper + ".completeOrder", obSeq);
	}
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@@@ updt Amount
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */	
	public int updtObAmount (Order order) {
		System.out.print(
				"OrderMatchingSystemDao.updtObAmount(Order order)" + "\n"
				+"마저 소화되지 못한 주문의 업데이트 정보입니다." + "\n"
				+"order.getMemberSeq() 주문자번호 :: " + order.getMemberSeq() + "\n"
				+"order.getObSeq() 주문번호:: " + order.getObSeq() + "\n"
				+"order.getOrderType() 주문유형(지정/시장):: " + order.getOrderType() + "\n"
				+"order.getBos() 주문유형(매수/매도):: " + order.getBos() + "\n"
				+"order.getObAmount() 잔여수량:: " + order.getObAmount() + "\n"
				+"order.getPrice() 주문가격:: " + order.getPrice() + "\n"
				);
		
		return sqlSession.update(omsMapper + ".updtObAmount", order);
	}
	
	public int insertTransactions(Order trade) {
		System.out.print(
				"OrderMatchingSystemDao.insertTransactions(Order trade)" + "\n"
				+"거래가 성사되어 거래내역에 저장될 정보 입니다" + "\n"
				+"trade.getBuyMemberSeq() 매수주문자 번호:: " + trade.getMemberSeqBuy() + "\n" // 매수자
				+"trade.getSellMemberSeq() 매도주문자 번호:: " + trade.getMemberSeqSell() + "\n" // 매도자 
				+"Buy trade.getObSeq() 매수주문 번호:: " + trade.getObSeqBuy() + "\n" //매수 OB
				+"Sell trade.getObSeq() 매도주문 번호:: " + trade.getObSeqSell() + "\n" // 매도 OB
				+"trade.getObAmount() 거래수량:: " + trade.getObAmount() + "\n" // 수량
				+"trade.getPrice() 거래가격:: " + trade.getPrice() + "\n" //가격
				);
		return sqlSession.insert(omsMapper + ".insertTransactions", trade);
	}
	
	public int updateUserBalanceA(Order order) {
		
		return sqlSession.update(omsMapper+".updateUserBalanceA",order);
	}
public int updateUserBalanceB(Order order) {
		
		return sqlSession.update(omsMapper+".updateUserBalanceB",order);
	}
	
}

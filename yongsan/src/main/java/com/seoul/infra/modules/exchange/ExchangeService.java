package com.seoul.infra.modules.exchange;

import java.util.List;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.dto.OderBookDto;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;

public interface ExchangeService {
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ User
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	public ExchDTO userBalance(ExchDTO dto) throws Exception;
	public int selectUserBalance(Order order) throws Exception;
	public double selectAvailableCashBalance(Integer seq) throws Exception;
	public int updateUserBalance(Order order) throws Exception;
	public List<Order> selectMyOrder(Order order) throws Exception;
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ crypto
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	public ExchDTO selectCrpytoOne(ExchDTO dto) throws Exception;
	public List<Crypto> selectCryptoList(Crypto crypto)  throws Exception;
	public List<Order> selectMytransaction(Order order) throws Exception;
	public List<Order> selectListCryptoTrend(Order order) throws Exception;
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ orders
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	public List<Order> selectBOB(Order dto) throws Exception;
	public Order selectBOBOne(Order dto) throws Exception;
	public Order selectSOBOne(Order dto) throws Exception;
	public List<Order> selectSOB(Order dto) throws Exception;
	public List<Order> selectOBList(Order order) throws Exception;
	public Integer selectOrderStatus(Order order) throws Exception;
	
	public List<Order> selectTransacton(Order order)throws Exception;
	public Order transactionTable(Order order) throws Exception;
	public Order marketTable(Order order) throws Exception;
	public List<Order> spread(Order order) throws Exception;
	
	public int submitBids(Order dto) throws Exception;
	public int submitAsks(Order dto) throws Exception;
	public ExchDTO getOnlaodInfo(ExchDTO dto) throws Exception;
	public int delObseq (int obSeq) throws Exception;
	
	public void orderMatchingBuy(Order bobOne)throws Exception;
	public void orderMatchingSell(Order sobOne)throws Exception;
	
	public List<Order> drawChart(Order order)throws Exception;
}

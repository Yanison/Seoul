package com.seoul.infra.modules.investments;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class InvestmentsDao {
	@Inject
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public static String invMapper = "com.seoul.infra.modules.investments.InvestmentsMapper";
	public static String uohMapper = "com.seoul.infra.modules.investments.userOrderHistoryMapper";
	
	public List<InvestmentsDto> selectCryptoName (InvestmentsDto invDto){
		
		return sqlSession.selectList(invMapper+".selectCryptoName", invDto);
	}
	
	public List<InvestmentsDto> tradeStatus (InvestmentsDto invDto){
		
		return sqlSession.selectList(invMapper+".tradeStatus", invDto);
	}
	public int tradeStatusCnt (InvestmentsDto invDto){
		
		return sqlSession.selectOne(invMapper+".tradeStatusCnt", invDto);
	}
	
	public InvestmentsDto assetEvaluation (InvestmentsDto invDto){
		
		return sqlSession.selectOne(invMapper+".assetEvaluation", invDto);
	}
	
	public List<InvestmentsDto> ordersHistory (InvestmentsDto invDto){
	
		return sqlSession.selectList(invMapper+".ordersHistory", invDto);
	}
	public int ordersHistoryCnt (InvestmentsDto invDto){
		
		return sqlSession.selectOne(invMapper+".ordersHistoryCnt", invDto);
	}
	
	public List<InvestmentsDto> pendingHistory (InvestmentsDto invDto){
		
		return sqlSession.selectList(invMapper+".pendingHistory", invDto);
	}
	public int pendingHistoryCnt (InvestmentsDto invDto){
		
		return sqlSession.selectOne(invMapper+".pendingHistoryCnt", invDto);
	}
	
	
	/**
	 * 유저 거래 현황
	 * @param invDto
	 * @return
	 */
	
	public List<InvestmentsDto> uoh_ordersHistory (InvestmentsDto invDto){
		
		return sqlSession.selectList(uohMapper+".uoh_ordersHistory", invDto);
	}
	public int uoh_ordersHistoryCnt (InvestmentsDto invDto){
		
		return sqlSession.selectOne(uohMapper+".uoh_ordersHistoryCnt", invDto);
	}
	
	public List<InvestmentsDto> uoh_pendingHistory (InvestmentsDto invDto){
		
		return sqlSession.selectList(uohMapper+".uoh_pendingHistory", invDto);
	}
	public int uoh_pendingHistoryCnt (InvestmentsDto invDto){
		
		return sqlSession.selectOne(uohMapper+".uoh_pendingHistoryCnt", invDto);
	}
}

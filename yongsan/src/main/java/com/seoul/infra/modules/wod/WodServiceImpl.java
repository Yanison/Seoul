package com.seoul.infra.modules.wod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WodServiceImpl implements WodService {
	@Autowired
	WodDao dao;
	
	@Override
	public List<WodDTO> selectBal(WodDTO voW) throws Exception {
		System.out.println("@@@@@@ service getMyBal ::"+ voW.getCryptoName());
		System.out.println("@@@@@@ service idtokenkko ::"+ voW.getIdTokenKko());
		System.out.println("@@@@@@ service cryptosym ::"+ voW.getCryptoSym());
		System.out.println("@@@@@@ service amount ::"+ voW.getAmount());
		
		return dao.selectBal(voW);
	}

}

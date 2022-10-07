package com.seoul.infra.modules.cryptogroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.dto.Crypto;

@Service
public class CryptoGroupServiceImpl {
	
	@Autowired
	CryptoGroupDao dao;
	
	
	public List<Crypto> selectAllCrypto(Crypto vo) throws Exception{
		
		return dao.selectAllCrypto(vo);
	}

}

package com.seoul.infra.modules.cryptogroup;

import java.util.List;

import com.seoul.infra.dto.Crypto;

public interface CryptoGroupService {
	
	public List<Crypto> selectAllCrypto(Crypto vo) throws Exception;

}

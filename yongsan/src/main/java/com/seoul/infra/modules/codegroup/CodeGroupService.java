package com.seoul.infra.modules.codegroup;

import java.util.List;

import com.seoul.infra.dto.Crypto;


public interface CodeGroupService {

	
	public List<CodeGroup> selectCList(CodeGroup vo) throws Exception;
	public List<Crypto> selectAllCrypto(Crypto vo2) throws Exception;

}

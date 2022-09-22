package com.seoul.infra.modules.codegroup;

import java.util.List;

public interface CodeGroupService {

	
	public List<CodeGroup> selectCList(CodeGroup vo) throws Exception;

}

package com.seoul.infra.modules.wod;

import java.util.List;

public interface WodService {
	
	public List<WodDTO> selectBal(WodDTO voW) throws Exception;
}

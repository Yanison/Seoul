package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.dto.ExchDTO;


@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class OMTest {
	
	ExchangeDao exchDao;
	
	@Test
	public void test() {
		ExchDTO dto = new ExchDTO();
		List<ExchDTO> getOBList = new ArrayList<ExchDTO>();
		
		getOBList = exchDao.selectBOB(dto);
		System.out.println(getOBList);
		
		BigDecimal price = dto.getPrice();
		BigDecimal amount = dto.getAmount();
	
		System.out.println("price :: "+price +"// amount ::"+amount);
	}

}

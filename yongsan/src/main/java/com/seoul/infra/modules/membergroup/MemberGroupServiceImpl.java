package com.seoul.infra.modules.membergroup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.codegroup.CodeGroup;

@Service
public class MemberGroupServiceImpl implements  MemberGroupService{
	
	@Autowired
	MemberGroupDao dao;
	
	@Override
	public List<MemberGroup> selectMlist(MemberGroup vo) throws Exception{
		return dao.selectMlist(vo);
	}
	
	@Override
	public int selectOneCnt (MemberGroup vo) throws Exception{
		return dao.selectOneCnt(vo);
	}
	
	@Override
	public int addUser(MemberGroup dto) throws Exception{
		return dao.addUser(dto);
	}


}

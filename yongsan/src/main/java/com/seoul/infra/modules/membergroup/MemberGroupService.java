package com.seoul.infra.modules.membergroup;

import java.util.List;

public interface MemberGroupService {
	
	public List<MemberGroup> selectMlist(MemberGroup vo) throws Exception;
	
	public int selectOneCnt (MemberGroup vo) throws Exception;

	public int addUser(MemberGroup dto) throws Exception;

}

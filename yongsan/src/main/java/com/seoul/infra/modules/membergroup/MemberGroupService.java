package com.seoul.infra.modules.membergroup;

import java.util.List;

public interface MemberGroupService {
	
	public List<MemberGroup> selectMlist(MemberGroup vo) throws Exception;
	
	public int selectOneCnt (MemberGroup vo) throws Exception;
	
	public MemberGroup selectOneLogin (MemberGroup dto) throws Exception;
	public MemberGroup selectOneId (MemberGroup dto) throws Exception;
	
	public int isDuple (MemberGroup vo) throws Exception;

	public int addUser(MemberGroup dto) throws Exception;
	public int addBalance(MemberGroup dto) throws Exception;
	
	public MemberGroup selectMOne (MemberGroup dto) throws Exception;

}
